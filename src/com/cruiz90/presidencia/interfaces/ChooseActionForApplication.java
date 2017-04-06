package com.cruiz90.presidencia.interfaces;

import com.cruiz90.presidencia.database.models.Application;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author ISC. Carlos Alfredo Ruiz Calderon <car.ruiz90@gmail.com>
 */
public class ChooseActionForApplication extends JDialog {

    private JButton delete, changeStatus;
    JPanel mainPanel;
    private final Integer applicationId;
    private Application application;
    private ApplicationsForTown dataContainer;

    public ChooseActionForApplication(Frame owner, boolean modal, Integer applicationId) {
        super(owner, modal);
        this.applicationId = applicationId;
        initComponents();
    }

    private void initComponents() {
        setSize(250, 250);
        setTitle("Editar solicitud");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //height of the task bar
        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int taskBarSize = scnMax.bottom;
        //available size of the screen
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - taskBarSize - getHeight()) / 2);
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        application = Application.findById(applicationId);

        delete = new JButton("Borrar");
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/cruiz90/presidencia/images/delete.png")).getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH));
        delete.setIcon(imageIcon);
        delete.addActionListener(delete());
        delete.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        delete.setBounds(10, 10, 160, 30);
        mainPanel.add(delete);

        changeStatus = new JButton("Cambiar estatus");
        if (application.isCompleted()) {
            imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/cruiz90/presidencia/images/checked.png")).getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH));
        } else {
            imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/cruiz90/presidencia/images/unchecked.png")).getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH));
        }
        changeStatus.setIcon(imageIcon);
        changeStatus.addActionListener(changeStatus());
        changeStatus.setBounds(10, 50, 160, 30);
        changeStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        mainPanel.add(changeStatus);

        mainPanel.setBounds(10, 10, 200, 200);
        mainPanel.setSize(200, 200);
        add(mainPanel);
    }

    public void setUpDataContainer(ApplicationsForTown dataContainer) {
        this.dataContainer = dataContainer;
    }

    private ActionListener delete() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea borrar la solicitud de " + application.getBeneficiary() + "?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    application.delete();
                    dataContainer.updateData();
                }
                closeDialog();
            }
        };
    }

    private ActionListener changeStatus() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea marcar como " + (application.isCompleted() ? "pendiente" : "completada") + " la solicitud de " + application.getBeneficiary() + "?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    application.setCompleted(!application.isCompleted());
                    application.update();
                    dataContainer.updateData();
                }
                closeDialog();
            }
        };
    }

    private void closeDialog() {
        setVisible(false);
        dispose();
    }

}
