package com.cruiz90.presidencia.interfaces;

import com.cruiz90.presidencia.database.models.Town;
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
public class TownsManager extends JPanel {

    private List<Town> towns;
    private JFrame parentFrame;

    public TownsManager() {
        initComponents();
    }

    private void initComponents() {
        setBorder(BorderFactory.createTitledBorder(null, "Administración de Comunidades", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Comic Sans MS", 0, 14))); // NOI18N
        setRequestFocusEnabled(false);
        setLayout(null);

        parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

        JButton back = new JButton();
        back.setBounds(10, 30, 50, 50);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/cruiz90/presidencia/images/back.png")).getImage().getScaledInstance(back.getWidth(), back.getHeight(), Image.SCALE_SMOOTH));
        back.setIcon(imageIcon);
        back.setToolTipText("Atrás");
        back.addActionListener(backListener());
        add(back);

        JButton add = new JButton();
        add.setBounds(70, 30, 50, 50);
        imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/cruiz90/presidencia/images/add.png")).getImage().getScaledInstance(add.getWidth(), add.getHeight(), Image.SCALE_SMOOTH));
        add.setIcon(imageIcon);
        add.setToolTipText("Nueva comunidad");
        add.addActionListener(addTownListener());
        add(add);

        int x, y, height;
        x = 10;
        y = 85;
        height = 30;
        ImageIcon deleteImage = new ImageIcon(new ImageIcon(getClass().getResource("/com/cruiz90/presidencia/images/delete.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        ImageIcon editImage = new ImageIcon(new ImageIcon(getClass().getResource("/com/cruiz90/presidencia/images/edit.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        towns = Town.getAll();
        int listPosition = 0;
//        for (int i = 0; i < 6; i++) {
        for (Town town : towns) {
            JLabel townName = new JLabel(town.getName());
            JButton deleteTown = new JButton(deleteImage);
            deleteTown.setActionCommand(listPosition + "");
            deleteTown.setToolTipText("Eliminar " + town.getName());
            deleteTown.addActionListener(deleteTownListener());
            JButton editTown = new JButton(editImage);
            editTown.setActionCommand(listPosition + "");
            editTown.setToolTipText("Editar " + town.getName());
            editTown.addActionListener(editTownListener());
            if (y > 365) {
                x += 300;
                y = 40;
            }
            townName.setBounds(x, y, 150, height);
            add(townName);
            deleteTown.setBounds(x + 160, y, 30, height);
            add(deleteTown);
            editTown.setBounds(x + 200, y, 30, height);
            add(editTown);
            y += 40;
            listPosition++;
        }
//    }
    }

    private ActionListener backListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                changeContentPanel(new TownPanel());
            }
        };
    }

    private void changeContentPanel(JPanel panel) {
        parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        panel.setBounds(10, 120, 870, 400);
        parentFrame.add(panel);
        parentFrame.remove(this);
        parentFrame.revalidate();
        parentFrame.repaint();
    }

    private ActionListener addTownListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addTown(new TownForm(parentFrame, true));
            }
        };
    }

    private ActionListener deleteTownListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Town town = towns.get(Integer.parseInt(e.getActionCommand()));
                int response = JOptionPane.showConfirmDialog(null, "¿Seguro que desa eliminar " + town.getName() + "?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    town.delete();
                    updateView();
                }
            }
        };
    }

    private ActionListener editTownListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addTown(new TownForm(parentFrame, true, towns.get(Integer.parseInt(e.getActionCommand()))));
            }
        };
    }

    private void addTown(TownForm tf) {
        tf.setDataContainer(this);
        tf.setVisible(true);
    }

    public void updateView() {
        removeAll();
        initComponents();
    }

}
