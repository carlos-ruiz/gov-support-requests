package com.cruiz90.presidencia.interfaces;

import com.cruiz90.presidencia.database.models.Application;
import com.cruiz90.presidencia.database.models.SocialProgram;
import com.cruiz90.presidencia.database.models.Town;
import com.cruiz90.presidencia.utils.DateLabelFormatter;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author ISC. Carlos Alfredo Ruiz Calderon <car.ruiz90@gmail.com>
 */
public class ApplicationForm extends JDialog implements ActionListener {

    private JLabel productLabel, quantityLabel, descriptionLabel, beneficiaryLabel, dateLabel;
    private JTextField product, beneficiary;
    private JDatePickerImpl date;
    private JFormattedTextField quantity;
    private JTextArea description;
    private Integer townId, socialProgramId;
    private ApplicationsForTown dataContainer;

    public void setUpIds(Integer townId, Integer socialProgramId) {
        this.townId = townId;
        this.socialProgramId = socialProgramId;
        initComponents();
    }

    public void setUpDataContainer(ApplicationsForTown dataContainer) {
        this.dataContainer = dataContainer;
    }

    private void initComponents() {
        setSize(350, 350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setTitle("Agregar solicitud");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //height of the task bar
        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int taskBarSize = scnMax.bottom;
        //available size of the screen
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - taskBarSize - getHeight()) / 2);
        productLabel = new JLabel("Producto");
        quantityLabel = new JLabel("Cantidad");
        descriptionLabel = new JLabel("Descripción");
        beneficiaryLabel = new JLabel("Beneficiario");
        dateLabel = new JLabel("Fecha");

        product = new JTextField();
        NumberFormat amountFormat = NumberFormat.getNumberInstance();
        quantity = new JFormattedTextField(amountFormat);
        beneficiary = new JTextField();
        description = new JTextArea();
        java.util.Date now = new java.util.Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        Properties p = new Properties();
        InputStream fileProperties;
        try {
            fileProperties = new FileInputStream("src/com/cruiz90/presidencia/properties/Messages.properties");
            p.load(fileProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        UtilDateModel modelFrom = new UtilDateModel();
        modelFrom.setDate(year, month, day);
        modelFrom.setSelected(true);
        JDatePanelImpl datePanelFrom = new JDatePanelImpl(modelFrom, p);
        date = new JDatePickerImpl(datePanelFrom, new DateLabelFormatter());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 350, 200);
        panel.setSize(350, 300);
        productLabel.setBounds(10, 10, 80, 30);
        productLabel.setLabelFor(product);
        panel.add(productLabel);
        product.setBounds(90, 10, 200, 30);
        panel.add(product);
        quantityLabel.setBounds(10, 50, 80, 30);
        quantityLabel.setLabelFor(quantity);
        panel.add(quantityLabel);
        quantity.setBounds(90, 50, 200, 30);
        panel.add(quantity);
        descriptionLabel.setBounds(10, 90, 80, 30);
        descriptionLabel.setLabelFor(description);
        panel.add(descriptionLabel);
        description.setBounds(90, 90, 200, 60);
        panel.add(description);
        beneficiaryLabel.setBounds(10, 160, 80, 30);
        beneficiaryLabel.setLabelFor(beneficiary);
        panel.add(beneficiaryLabel);
        beneficiary.setBounds(90, 160, 200, 30);
        panel.add(beneficiary);
        dateLabel.setBounds(10, 200, 80, 30);
        dateLabel.setLabelFor(date);
        panel.add(dateLabel);
        date.setBounds(90, 200, 200, 30);
        panel.add(date);

        JButton save = new JButton("Guardar");
        save.setBounds((getWidth() - 100) / 2, 250, 100, 30);
        save.addActionListener(this);
        panel.add(save);
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String errors = validateFields();
        if (!errors.isEmpty()) {
            JOptionPane.showMessageDialog(dataContainer, errors, "Error de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date dateObj;
        try {
            dateObj = formater.parse(date.getJFormattedTextField().getText());
        } catch (ParseException ex) {
            ex.printStackTrace();
            dateObj = new java.util.Date();
        }
        Application app = new Application(product.getText(), Integer.parseInt(quantity.getText()), beneficiary.getText(), description.getText(), new Date(dateObj.getTime()), Town.findById(townId), SocialProgram.findById(socialProgramId));
        app.save();

        dataContainer.updateData();
        setVisible(false);
    }

    public ApplicationForm(Frame owner, boolean modal) {
        super(owner, modal);
    }

    private String validateFields() {
        String errors = "";
        if (product.getText().isEmpty()) {
            errors += "El campo producto es obligatorio\n";
        }
        if (quantity.getText().isEmpty()) {
            errors += "El campo cantidad es obligatorio\n";
        }
        if (beneficiary.getText().isEmpty()) {
            errors += "El campo beneficiario es obligatorio\n";
        }
        return errors;
    }
}
