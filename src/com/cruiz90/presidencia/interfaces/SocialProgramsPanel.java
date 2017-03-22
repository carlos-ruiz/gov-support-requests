package com.cruiz90.presidencia.interfaces;

import com.cruiz90.presidencia.database.models.SocialProgram;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ISC. Carlos Alfredo Ruiz Calderon <car.ruiz90@gmail.com>
 */
public class SocialProgramsPanel extends JPanel implements ActionListener {

    private final String townId;

    public SocialProgramsPanel(String townId) {
        this.townId = townId;
        initComponents();
    }

    private void initComponents() {
        setBorder(BorderFactory.createTitledBorder(null, "Programas Sociales", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Comic Sans MS", 0, 14))); // NOI18N
        setSize(800, 500);
        setRequestFocusEnabled(false);
        setLayout(new GridLayout(4, 5, 3, 3));

        List<SocialProgram> programs = SocialProgram.getAll();
        for (SocialProgram sp : programs) {
            JButton button = new JButton(sp.getName());
            button.setActionCommand(sp.getSocialProgramId().toString());
            button.addActionListener(this);
            System.out.println("Text: " + button.getText() + " Id: " + button.getActionCommand());
            add(button, 0, 0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Social Program: " + e.getActionCommand());
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        JPanel applications = new ApplicationsForTown(townId, e.getActionCommand());
        applications.setBounds(10, 120, 870, 400);
        parentFrame.add(applications);
        parentFrame.remove(this);
        System.out.println(parentFrame.getName());
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}
