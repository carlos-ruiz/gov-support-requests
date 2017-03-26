package com.cruiz90.presidencia.interfaces;

import com.cruiz90.presidencia.database.models.SocialProgram;
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
public class SocialProgramForm extends JDialog implements ActionListener {

    private SocialProgramsManager dataContainer;
    private JTextField name;
    private SocialProgram sp;
    private boolean isNew;

    public SocialProgramForm(Frame owner, boolean modal) {
        super(owner, modal);
        initComponents();
    }

    public SocialProgramForm(Frame owner, boolean modal, SocialProgram sp) {
        super(owner, modal);
        this.sp = sp;
        initComponents();
    }

    private void initComponents() {
        setTitle("Nuevo Programa Social");
        setSize(350, 150);
        if (sp == null) {
            sp = new SocialProgram();
            isNew = true;
        }

        JLabel nameLabel = new JLabel("Nombre del programa");
        name = new JTextField(sp.getName());
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

    public void setDataContainer(SocialProgramsManager dataContainer) {
        this.dataContainer = dataContainer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sp.setName(name.getText());
        if (isNew) {
            sp.save();
        } else {
            System.out.println("Id: " + sp.getSocialProgramId());
            sp.update();
        }
        dataContainer.updateView();
        dispose();
    }
}
