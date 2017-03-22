package com.cruiz90.presidencia.interfaces;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ISC. Carlos Alfredo Ruiz Calderon <car.ruiz90@gmail.com>
 */
public class ApplicationsForTown extends JPanel implements ActionListener {

    private final String townId;
    private final String socialProgramId;

    public ApplicationsForTown(String townId, String socialProgramId) {
        this.townId = townId;
        this.socialProgramId = socialProgramId;
        initComponents();
    }

    private void initComponents() {
        setBorder(BorderFactory.createTitledBorder(null, "Solicitudes de apoyo", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Comic Sans MS", 0, 14))); // NOI18N
        setSize(800, 500);
        setRequestFocusEnabled(false);
        JButton addApplication = new JButton("Nueva solicitud");
        addApplication.addActionListener(addApplication());
        addApplication.setBounds(10, 10, 50, 30);
        addApplication.setSize(50, 30);
        add(addApplication);

        setLayout(new GridLayout(4, 5, 3, 3));

        JScrollPane scrollPane = new JScrollPane();
        JTable applicationTable = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null}
                },
                new String[]{
                    "Número", "Producto", "Cantidad", "Beneficiario", "Fecha"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        applicationTable.setModel(tableModel);
        scrollPane.setViewportView(applicationTable);

        add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private ActionListener addApplication() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Agregando solicitud town: " + townId + " sp: " + socialProgramId);
                ApplicationForm addForm = new ApplicationForm();
                addForm.setVisible(true);
            }
        };
    }

}
