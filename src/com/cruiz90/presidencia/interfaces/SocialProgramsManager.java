package com.cruiz90.presidencia.interfaces;

import com.cruiz90.presidencia.database.models.SocialProgram;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ISC. Carlos Alfredo Ruiz Calderon <car.ruiz90@gmail.com>
 */
public class SocialProgramsManager extends JPanel {

    private final String townId;
    private List<SocialProgram> socialPrograms;
    private JFrame parentFrame;

    public SocialProgramsManager(String townId) {
        this.townId = townId;
        initComponents();
    }

    private void initComponents() {
        setBorder(BorderFactory.createTitledBorder(null, "Administración de Programas Sociales", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Comic Sans MS", 0, 14))); // NOI18N
        setRequestFocusEnabled(false);
        setLayout(null);

        parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

        JButton back = new JButton();
        back.setBounds(10, 30, 50, 50);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/cruiz90/presidencia/images/back.png")).getImage().getScaledInstance(back.getWidth(), back.getHeight(), Image.SCALE_SMOOTH));
        back.setIcon(imageIcon);
        back.setToolTipText("Atras");
        back.addActionListener(backListener());
        add(back);

        JButton add = new JButton();
        add.setBounds(70, 30, 50, 50);
        imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/cruiz90/presidencia/images/add.png")).getImage().getScaledInstance(add.getWidth(), add.getHeight(), Image.SCALE_SMOOTH));
        add.setIcon(imageIcon);
        add.setToolTipText("Nuevo programa social");
        add.addActionListener(addSocialProgramListener());
        add(add);

        int x, y, height;
        x = 10;
        y = 85;
        height = 30;
        ImageIcon deleteImage = new ImageIcon(new ImageIcon(getClass().getResource("/com/cruiz90/presidencia/images/delete.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        ImageIcon editImage = new ImageIcon(new ImageIcon(getClass().getResource("/com/cruiz90/presidencia/images/edit.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        socialPrograms = SocialProgram.getAll();
        int listPosition = 0;
//        for (int i = 0; i < 6; i++) {
        for (SocialProgram sp : socialPrograms) {
            JLabel spName = new JLabel(sp.getName());
            JButton deleteSp = new JButton(deleteImage);
            deleteSp.setActionCommand(listPosition + "");
            deleteSp.setToolTipText("Eliminar " + sp.getName());
            deleteSp.addActionListener(deleteSocialProgramListener());
            JButton editSp = new JButton(editImage);
            editSp.setActionCommand(listPosition + "");
            editSp.setToolTipText("Editar " + sp.getName());
            editSp.addActionListener(editSocialProgramListener());
            if (y > 365) {
                x += 300;
                y = 40;
            }
            spName.setBounds(x, y, 150, height);
            add(spName);
            deleteSp.setBounds(x + 160, y, 30, height);
            add(deleteSp);
            editSp.setBounds(x + 200, y, 30, height);
            add(editSp);
            y += 40;
            listPosition++;
        }
//    }
    }

    private ActionListener backListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                changeContentPanel(new SocialProgramsPanel(townId));
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

    private ActionListener addSocialProgramListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addSocialProgram(new SocialProgramNew(parentFrame, true));
            }
        };
    }

    private ActionListener deleteSocialProgramListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SocialProgram sp = socialPrograms.get(Integer.parseInt(e.getActionCommand()));
                int response = JOptionPane.showConfirmDialog(null, "¿Seguro que desa eliminar " + sp.getName() + "?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    sp.delete();
                    updateView();
                }
            }
        };
    }

    private ActionListener editSocialProgramListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addSocialProgram(new SocialProgramNew(parentFrame, true, socialPrograms.get(Integer.parseInt(e.getActionCommand()))));
            }
        };
    }

    private void addSocialProgram(SocialProgramNew spn) {
        spn.setDataContainer(this);
        spn.setVisible(true);
    }

    public void updateView() {
        removeAll();
        initComponents();
    }
}
