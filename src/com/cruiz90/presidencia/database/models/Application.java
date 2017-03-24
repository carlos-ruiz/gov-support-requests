package com.cruiz90.presidencia.database.models;

import com.cruiz90.presidencia.utils.Util;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ISC. Carlos Alfredo Ruiz Calderon <car.ruiz90@gmail.com>
 */
public class Application {

    private Integer applicatinId;
    private String product;
    private int quantity;
    private String beneficiary;
    private String description;
    private Date date;
    private Town town;
    private SocialProgram socialProgram;
    private boolean completed; // false - Pendiente, true - Completa

    public Application() {
    }

    public Application(String product, int quantity, String beneficiary, String description, Date date, Town town, SocialProgram socialProgram) {
        this.product = product;
        this.quantity = quantity;
        this.beneficiary = beneficiary;
        this.description = description;
        this.date = date;
        this.town = town;
        this.socialProgram = socialProgram;
        this.completed = false;
    }

    public Integer getApplicatinId() {
        return applicatinId;
    }

    public void setApplicatinId(Integer applicatinId) {
        this.applicatinId = applicatinId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public SocialProgram getSocialProgram() {
        return socialProgram;
    }

    public void setSocialProgram(SocialProgram socialProgram) {
        this.socialProgram = socialProgram;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void save() {
        Connection conn = new Util().getDatabaseConnection();
        if (conn != null) {
            PreparedStatement ps = null;
            try {
                String query = "INSERT INTO applications ("
                        + "product, quantity, beneficiary, description, date, town_id, social_program_id, status)"
                        + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                ps = conn.prepareStatement(query);
                ps.setString(1, product);
                ps.setInt(2, quantity);
                ps.setString(3, beneficiary);
                ps.setString(4, description);
                ps.setDate(5, date);
                ps.setInt(6, town.getTownId());
                ps.setInt(7, socialProgram.getSocialProgramId());
                ps.setBoolean(8, completed);
                ps.execute();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    public void delete() {
        Connection conn = new Util().getDatabaseConnection();
        if (conn != null) {
            PreparedStatement ps = null;
            try {
                String query = "DELETE FROM applications WHERE application_id=?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, applicatinId);
                ps.execute();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    public void update() {
        Connection conn = new Util().getDatabaseConnection();
        if (conn != null) {
            PreparedStatement ps = null;
            try {
                String query = "UPDATE applications SET"
                        + " product = ?, quantity = ?, beneficiary = ?, description = ?, date = ?, town_id = ?, social_program_id = ?, status = ?"
                        + " WHERE application_id=?";
                ps = conn.prepareStatement(query);
                ps.setString(1, product);
                ps.setInt(2, quantity);
                ps.setString(3, beneficiary);
                ps.setString(4, description);
                ps.setDate(5, date);
                ps.setInt(6, town.getTownId());
                ps.setInt(7, socialProgram.getSocialProgramId());
                ps.setBoolean(8, completed);
                ps.setInt(9, applicatinId);
                ps.execute();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    public static List<Application> getAll() {
        Connection conn = new Util().getDatabaseConnection();
        List<Application> result = null;
        if (conn != null) {
            Statement ps = null;
            try {
                String query = "SELECT * FROM applications";
                ps = conn.createStatement();
                ResultSet rs = ps.executeQuery(query);
                result = new ArrayList<>();
                while (rs.next()) {
                    Application sp = new Application();
                    sp.setApplicatinId(rs.getInt("application_id"));
                    sp.setProduct(rs.getString("product"));
                    sp.setQuantity(rs.getInt("quantity"));
                    sp.setBeneficiary(rs.getString("beneficiary"));
                    sp.setDescription(rs.getString("description"));
                    sp.setDate(rs.getDate("date"));
                    sp.setTown(Town.findById(rs.getInt("town_id")));
                    sp.setSocialProgram(SocialProgram.findById(rs.getInt("social_program_id")));
                    sp.setCompleted(rs.getBoolean("status"));
                    result.add(sp);
                }
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
        return result;
    }

    public static List<Application> findByTownAndSocialProgram(Integer townId, Integer socialProgramId) {
        Connection conn = new Util().getDatabaseConnection();
        List<Application> result = null;
        if (conn != null) {
            PreparedStatement ps = null;
            try {
                String query = "SELECT * FROM applications WHERE town_id=? AND social_program_id=?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, townId);
                ps.setInt(2, socialProgramId);
                ResultSet rs = ps.executeQuery();
                result = new ArrayList<>();
                while (rs.next()) {
                    Application sp = new Application();
                    sp.setApplicatinId(rs.getInt("application_id"));
                    sp.setProduct(rs.getString("product"));
                    sp.setQuantity(rs.getInt("quantity"));
                    sp.setBeneficiary(rs.getString("beneficiary"));
                    sp.setDescription(rs.getString("description"));
                    sp.setDate(rs.getDate("date"));
                    sp.setTown(Town.findById(rs.getInt("town_id")));
                    sp.setSocialProgram(SocialProgram.findById(rs.getInt("social_program_id")));
                    sp.setCompleted(rs.getBoolean("status"));
                    result.add(sp);
                }
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
        return result;
    }

    public static Application findById(Integer id) {
        Connection conn = new Util().getDatabaseConnection();
        Application app = null;
        if (conn != null) {
            PreparedStatement ps = null;
            try {
                String query = "SELECT * FROM applications WHERE application_id=?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    app = new Application();
                    app.setApplicatinId(rs.getInt("application_id"));
                    app.setProduct(rs.getString("product"));
                    app.setQuantity(rs.getInt("quantity"));
                    app.setBeneficiary(rs.getString("beneficiary"));
                    app.setDescription(rs.getString("description"));
                    app.setDate(rs.getDate("date"));
                    app.setTown(Town.findById(rs.getInt("town_id")));
                    app.setSocialProgram(SocialProgram.findById(rs.getInt("social_program_id")));
                    app.setCompleted(rs.getBoolean("status"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                    }
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
        return app;
    }

}
