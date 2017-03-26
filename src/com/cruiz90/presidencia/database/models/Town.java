package com.cruiz90.presidencia.database.models;

import com.cruiz90.presidencia.utils.Util;
import java.sql.Connection;
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
public class Town {

    private Integer townId;
    private String name;

    public Town() {
    }

    public Town(Integer townId, String name) {
        this.townId = townId;
        this.name = name;
    }

    public Town(String name) {
        this.name = name;
    }

    public Integer getTownId() {
        return townId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Town saveTown(Town town) {
        return null;
    }

    public void save() {
        Connection conn = new Util().getDatabaseConnection();
        if (conn != null) {
            PreparedStatement ps = null;
            try {
                String query = "INSERT INTO towns (name) VALUES(?)";
                ps = conn.prepareStatement(query);
                ps.setString(1, name);
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
                String query = "DELETE FROM towns WHERE town_id=?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, townId);
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
                String query = "UPDATE towns SET"
                        + " name = ?"
                        + " WHERE town_id=?";
                ps = conn.prepareStatement(query);
                ps.setString(1, name);
                ps.setInt(2, townId);
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

    public static List<Town> getAll() {
        Connection conn = new Util().getDatabaseConnection();
        List<Town> result = null;
        if (conn != null) {
            Statement ps = null;
            try {
                String query = "SELECT * FROM towns";
                ps = conn.createStatement();
                ResultSet rs = ps.executeQuery(query);
                result = new ArrayList<>();
                while (rs.next()) {
                    result.add(new Town(rs.getInt("town_id"), rs.getString("name")));
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

    public static void deleteAll() {
        Connection conn = new Util().getDatabaseConnection();
        List<Town> result = null;
        if (conn != null) {
            Statement ps = null;
            try {
                String query = "DELETE FROM towns";
                ps = conn.createStatement();
                ps.execute(query);
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

    public static Town findById(Integer id) {
        Connection conn = new Util().getDatabaseConnection();
        Town town = null;
        if (conn != null) {
            PreparedStatement ps = null;
            try {
                String query = "SELECT * FROM towns WHERE town_id=?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    town = new Town(rs.getInt("town_id"), rs.getString("name"));
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
        return town;
    }
}
