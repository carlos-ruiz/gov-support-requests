package com.cruiz90.presidencia.interfaces;

import com.cruiz90.presidencia.database.models.Town;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author ISC. Carlos Alfredo Ruiz Calderon <car.ruiz90@gmail.com>
 */
public class TownForm extends JDialog implements ActionListener {

    private Town town;
    private TownsManager dataContainer;
    private JTextField name;
    private boolean isNew;

    public TownForm(Frame owner, boolean modal) {
        super(owner, modal);
        initComponents();
    }

    public TownForm(Frame owner, boolean modal, Town town) {
        super(owner, modal);
        this.town = town;
        initComponents();
    }

    private void initComponents() {
        setTitle("Nuevo Programa Social");
        setSize(350, 150);
        if (town == null) {
            town = new Town();
            isNew = true;
        }

        JLabel nameLabel = new JLabel("Nombre del programa");
        name = new JTextField(town.getName());
        JButton save = new JButton("Guardar");
        save.addActionListener(this);

        setLayout(null);
        nameLabel.setBounds(10, 10, 150, 30);
        add(nameLabel);
        name.setBounds(170, 10, 150, 30);
        add(name);
        save.setBounds(110, 50, 100, 30);
        add(save);
    }

    public void setDataContainer(TownsManager dataContainer) {
        this.dataContainer = dataContainer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        town.setName(name.getText());
        if (isNew) {
            town.save();
        } else {
            System.out.println("Id: " + town.getTownId());
            town.update();
        }
        dataContainer.updateView();
        dispose();
    }

}
