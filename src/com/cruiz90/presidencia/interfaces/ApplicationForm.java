package com.cruiz90.presidencia.interfaces;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

/**
 *
 * @author ISC. Carlos Alfredo Ruiz Calderon <car.ruiz90@gmail.com>
 */
public class ApplicationForm extends JFrame {

    public ApplicationForm() {
        initComponents();
    }

    private void initComponents() {

        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Agregar solicitud");

        JLabel productLabel, quantityLabel, descriptionLabel, beneficiaryLabel, dateLabel;
        JTextField product, quantity, beneficiary;
        JTextArea description;
        JFormattedTextField date;

        productLabel = new JLabel("Producto");
        quantityLabel = new JLabel("Cantidad");
        descriptionLabel = new JLabel("Descripción");
        beneficiaryLabel = new JLabel("Beneficiario");
        dateLabel = new JLabel("Fecha");

        product = new JTextField();
        quantity = new JTextField();
        beneficiary = new JTextField();
        description = new JTextArea();
        date = new JFormattedTextField();
        date.setFormatterFactory(new DefaultFormatterFactory(new DateFormatter()));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 3, 3));

        panel.add(productLabel);
        panel.add(product);
        panel.add(quantityLabel);
        panel.add(quantity);
        panel.add(descriptionLabel);
        panel.add(description);
        panel.add(beneficiaryLabel);
        panel.add(beneficiary);
        panel.add(dateLabel);
        panel.add(date);

        panel.setBounds(0, 0, 300, 200);
        add(panel);

        JButton save = new JButton("Guardar");
        save.setBounds((getWidth() - 100) / 2, getHeight() - 50, 100, 30);
        add(save);
    }
}
