package com.cruiz90.presidencia.interfaces;

import com.cruiz90.presidencia.database.models.Application;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ISC. Carlos Alfredo Ruiz Calderon <car.ruiz90@gmail.com>
 */
public class ApplicationsForTown extends JPanel implements ActionListener {

    private final Integer townId;
    private final Integer socialProgramId;
    private DefaultTableModel tableModel;
    private JTable applicationTable;
    private JFrame parentFrame;
    private ApplicationsForTown dataContainer;

    public ApplicationsForTown(String townId, String socialProgramId) {
        this.townId = Integer.parseInt(townId);
        this.socialProgramId = Integer.parseInt(socialProgramId);
        initComponents();
    }

    private void initComponents() {
        this.dataContainer = this;
        parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        setBorder(BorderFactory.createTitledBorder(null, "Solicitudes de apoyo", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Comic Sans MS", 0, 14))); // NOI18N
        setName("applicationsPanel");
        setRequestFocusEnabled(false);
        setLayout(null);

        JButton back = new JButton();
        back.setBounds(10, 30, 50, 50);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/cruiz90/presidencia/images/back.png")).getImage().getScaledInstance(back.getWidth(), back.getHeight(), Image.SCALE_SMOOTH));
        back.setIcon(imageIcon);
        back.setToolTipText("Atras");
        back.addActionListener(backListener());
        add(back);

        JButton addApplication = new JButton("Nueva solicitud");
        addApplication.addActionListener(this);
        addApplication.setBounds(80, 30, 150, 50);
        add(addApplication);

        JScrollPane scrollPane = new JScrollPane();
        applicationTable = new JTable();
        updateData();

        scrollPane.setViewportView(applicationTable);
        scrollPane.setBounds(10, 100, 860, 290);
        add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ApplicationForm addForm = new ApplicationForm(parentFrame, true);
        addForm.setUpIds(townId, socialProgramId);
        addForm.setUpDataContainer(this);
        addForm.setVisible(true);
    }

    public void updateData() {
        List<Application> applications = Application.findByTownAndSocialProgram(townId, socialProgramId);
        Object[][] applicationsArray = new Object[applications.size()][6];
        int row = 0;
        for (Application app : applications) {
            applicationsArray[row][0] = app.getApplicatinId();
            applicationsArray[row][1] = app.getProduct();
            applicationsArray[row][2] = app.getQuantity();
            applicationsArray[row][3] = app.getBeneficiary();
            applicationsArray[row][4] = app.getDate().toString();
            applicationsArray[row][5] = app.isCompleted() ? "Completada" : "Pendiente";
            row++;
        }

        tableModel = new DefaultTableModel(
                applicationsArray,
                new String[]{
                    "Número", "Producto", "Cantidad", "Beneficiario", "Fecha", "Estatus"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        applicationTable.setModel(tableModel);
        applicationTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evnt) {
                if (evnt.getClickCount() == 1) {
                    ChooseActionForApplication dialog = new ChooseActionForApplication(parentFrame, true, (Integer) applicationTable.getValueAt(applicationTable.getSelectedRow(), 0));
                    dialog.setUpDataContainer(dataContainer);
                    dialog.setVisible(true);
                }
            }
        });
        applicationTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                String status = (String) table.getModel().getValueAt(row, 5);
                if ("Completada".equals(status)) {
                    setBackground(Color.YELLOW);
                } else {
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }
                return this;
            }
        });
    }

    private ActionListener backListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                back();
            }
        };
    }

    private void back() {
        JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
        JPanel programs = new SocialProgramsPanel(townId.toString());
        programs.setBounds(10, 120, 870, 400);
        parent.add(programs);
        parent.remove(this);
        parent.revalidate();
        parent.repaint();
    }

}
