/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruiz90.presidencia.interfaces;

import com.cruiz90.presidencia.database.models.Town;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author ISC. Carlos Alfredo Ruiz Calderon <car.ruiz90@gmail.com>
 */
public class TownPanel extends JPanel implements ActionListener {

    private List<JButton> townsButtonList;

    /**
     * Creates new form ComunityPanel
     */
    public TownPanel() {
        initComponents();
        setupScreen();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Comunidades", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14))); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 500));
        setRequestFocusEnabled(false);
        setLayout(new java.awt.GridLayout(4, 5, 3, 3));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private void setupScreen() {
        List<Town> towns = Town.getAll();
        townsButtonList = new ArrayList<>();
        for (Town town : towns) {
            JButton button = new JButton(town.getName());
            button.setActionCommand(town.getTownId().toString());
            townsButtonList.add(button);
            button.addActionListener(this);
            add(button, 0, 0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        JPanel programs = new SocialProgramsPanel(e.getActionCommand());
        programs.setBounds(10, 120, 870, 400);
        parentFrame.add(programs);
        parentFrame.remove(this);
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}
