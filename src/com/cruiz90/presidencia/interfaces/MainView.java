package com.cruiz90.presidencia.interfaces;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ISC. Carlos Alfredo Ruiz Calderon <car.ruiz90@gmail.com>
 */
public class MainView extends JFrame {

    public MainView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Control de solicitudes de apoyo");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setName("Frame principal");
        JLabel title = new JLabel("H. AYUNTAMIENTO DE HUANIQUEO 2015 - 2018");
        title.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setBounds(10, 10, 790, 40);
        add(title);

        JLabel imageLogo = new JLabel();
        imageLogo.setBounds(790, 10, 80, 103);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/cruiz90/presidencia/images/logo_huaniqueo.png")).getImage().getScaledInstance(imageLogo.getWidth(), imageLogo.getHeight(), Image.SCALE_SMOOTH));
        imageLogo.setIcon(imageIcon);
        add(imageLogo);

        JPanel towns = new TownPanel();
        towns.setBounds(10, 120, 870, 400);
        add(towns);
    }
}
