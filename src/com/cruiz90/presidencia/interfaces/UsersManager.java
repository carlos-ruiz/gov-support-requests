package com.cruiz90.presidencia.interfaces;

import com.cruiz90.presidencia.database.models.User;
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
public class UsersManager extends JPanel {

    private List<User> users;
    private JFrame parentFrame;

    public UsersManager() {
        initComponents();
    }

    private void initComponents() {
        setBorder(BorderFactory.createTitledBorder(null, "Administración de Usuarios", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Comic Sans MS", 0, 14))); // NOI18N
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
        add.setToolTipText("Nuevo usuario");
        add.addActionListener(addUserListener());
        add(add);

        int x, y, height;
        x = 10;
        y = 85;
        height = 30;
        ImageIcon deleteImage = new ImageIcon(new ImageIcon(getClass().getResource("/com/cruiz90/presidencia/images/delete.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        ImageIcon editImage = new ImageIcon(new ImageIcon(getClass().getResource("/com/cruiz90/presidencia/images/edit.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        users = User.getAll();
        int listPosition = 0;
//        for (int i = 0; i < 6; i++) {
        for (User user : users) {
            JLabel townName = new JLabel(user.getName());
            JButton deleteTown = new JButton(deleteImage);
            deleteTown.setActionCommand(listPosition + "");
            deleteTown.setToolTipText("Eliminar " + user.getName());
            deleteTown.addActionListener(deleteUserListener());
            JButton editTown = new JButton(editImage);
            editTown.setActionCommand(listPosition + "");
            editTown.setToolTipText("Editar " + user.getName());
            editTown.addActionListener(editUserListener());
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
    }

    private ActionListener deleteUserListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                User user = users.get(Integer.parseInt(e.getActionCommand()));
                int response = JOptionPane.showConfirmDialog(null, "¿Seguro que desa eliminar " + user.getName() + "?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    user.delete();
                    updateView();
                }
            }
        };
    }

    private ActionListener editUserListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addUser(new UserForm(parentFrame, true, users.get(Integer.parseInt(e.getActionCommand()))));
            }
        };
    }

    private ActionListener addUserListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addUser(new UserForm(parentFrame, true));
            }
        };
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

    private void addUser(UserForm uf) {
        uf.setDataContainer(this);
        uf.setVisible(true);
    }

    public void updateView() {
        removeAll();
        initComponents();
    }

}
