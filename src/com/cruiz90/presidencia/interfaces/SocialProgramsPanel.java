package com.cruiz90.presidencia.interfaces;

import com.cruiz90.presidencia.database.models.SocialProgram;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
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
        setRequestFocusEnabled(false);
        setLayout(null);

        JButton back = new JButton();
        back.setBounds(10, 30, 50, 50);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/cruiz90/presidencia/images/back.png")).getImage().getScaledInstance(back.getWidth(), back.getHeight(), Image.SCALE_SMOOTH));
        back.setIcon(imageIcon);
        back.setToolTipText("Atras");
        back.addActionListener(backListener());
        add(back);

        JButton manager = new JButton();
        manager.setBounds(70, 30, 50, 50);
        imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/cruiz90/presidencia/images/list.png")).getImage().getScaledInstance(manager.getWidth(), manager.getHeight(), Image.SCALE_SMOOTH));
        manager.setIcon(imageIcon);
        manager.setToolTipText("Administrar programas sociales");
        manager.addActionListener(managerListener());
        add(manager);

        JPanel socialProgramsGrid = new JPanel();
        socialProgramsGrid.setLayout(new GridLayout(4, 5, 3, 3));

        List<SocialProgram> programs = SocialProgram.getAll();
        for (SocialProgram sp : programs) {
            JButton button = new JButton(sp.getName());
            button.setActionCommand(sp.getSocialProgramId().toString());
            button.addActionListener(this);
            socialProgramsGrid.add(button, 0, 0);
        }
        socialProgramsGrid.setBounds(10, 100, 850, 290);
        socialProgramsGrid.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
        add(socialProgramsGrid);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        changeContentPanel(new ApplicationsForTown(townId, e.getActionCommand()));
    }

    private ActionListener backListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                changeContentPanel(new TownPanel());
            }
        };
    }

    private ActionListener managerListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                changeContentPanel(new SocialProgramsManager(townId));
            }
        };
    }

    private void changeContentPanel(JPanel panel) {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        panel.setBounds(10, 120, 870, 400);
        parentFrame.add(panel);
        parentFrame.remove(this);
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}
