package com.cruiz90.presidencia.interfaces;

import com.cruiz90.presidencia.database.models.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author ISC. Carlos Alfredo Ruiz Calderon <car.ruiz90@gmail.com>
 */
public class Login extends JFrame implements ActionListener {

    private JTextField userName;
    private JPasswordField password;

    public Login() {
        initComponents();
    }

    private void initComponents() {
        if (User.getAllUsers().isEmpty()) {
            UserForm uf = new UserForm(this, true);
            uf.setVisible(true);
        }
        setSize(330, 200);
        setLayout(null);
        setTitle("Control de solicitudes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel userNameLabel = new JLabel("Nombre de usuario");
        JLabel passwordLabel = new JLabel("Contraseña");
        userName = new JTextField();
        password = new JPasswordField();
        JButton login = new JButton("Entrar");
        login.addActionListener(this);

        userNameLabel.setBounds(20, 20, 120, 30);
        add(userNameLabel);
        userName.setBounds(150, 20, 150, 30);
        add(userName);
        passwordLabel.setBounds(20, 60, 120, 30);
        add(passwordLabel);
        password.setBounds(150, 60, 150, 30);
        add(password);
        login.setBounds(100, 110, 100, 30);
        add(login);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
