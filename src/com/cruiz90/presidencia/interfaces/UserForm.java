package com.cruiz90.presidencia.interfaces;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author ISC. Carlos Alfredo Ruiz Calderon <car.ruiz90@gmail.com>
 */
public class UserForm extends JDialog implements ActionListener {

    private JTextField userName;
    private JPasswordField password, matchPassword;

    public UserForm(Frame owner, boolean modal) {
        super(owner, modal);
        initComponents();
    }

    private void initComponents() {
        setSize(330, 250);
        setTitle("Nuevo usuario");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel userNameLabel = new JLabel("Nombre de Usuario");
        JLabel passwordLabel = new JLabel("Contraseña");
        JLabel matchPasswordLabel = new JLabel("Repite Contraseña");
        userName = new JTextField();
        password = new JPasswordField();
        matchPassword = new JPasswordField();
        JButton save = new JButton("Guardar");
        save.addActionListener(this);

        userNameLabel.setBounds(20, 20, 120, 30);
        add(userNameLabel);
        userName.setBounds(150, 20, 150, 30);
        add(userName);
        passwordLabel.setBounds(20, 60, 120, 30);
        add(passwordLabel);
        password.setBounds(150, 60, 150, 30);
        add(password);
        matchPasswordLabel.setBounds(20, 100, 120, 30);
        add(matchPasswordLabel);
        matchPassword.setBounds(150, 100, 150, 30);
        add(matchPassword);
        save.setBounds(100, 160, 100, 30);
        add(save);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
