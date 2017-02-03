package Modelo_vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import modeloDB_DAO.EmployeesDAO;
import modeloDB_DTO.EmployeesDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaEmpleados extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfEmployeeNumber;
	private JTextField tfLastName;
	private JTextField tfFirstName;
	private JTextField tfExtension;
	private JTextField tfEmail;
	private JTextField tfReportsTo;
	private JTextField tfJobTitle;
	private JTextField tfOfficeCode;



	/**
	 * Create the dialog.
	 */
	public AltaEmpleados() {
		setBounds(100, 100, 665, 579);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblEmployeeNumber = new JLabel("Employee Number:");
		lblEmployeeNumber.setBounds(12, 36, 116, 16);
		contentPanel.add(lblEmployeeNumber);
		
		tfEmployeeNumber = new JTextField();
		tfEmployeeNumber.setBounds(165, 33, 116, 22);
		contentPanel.add(tfEmployeeNumber);
		tfEmployeeNumber.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(12, 91, 116, 16);
		contentPanel.add(lblLastName);
		
		tfLastName = new JTextField();
		tfLastName.setBounds(165, 88, 116, 22);
		contentPanel.add(tfLastName);
		tfLastName.setColumns(10);
		
		JLabel lblFirstname = new JLabel("FirstName:");
		lblFirstname.setBounds(12, 156, 116, 16);
		contentPanel.add(lblFirstname);
		
		tfFirstName = new JTextField();
		tfFirstName.setBounds(165, 153, 116, 22);
		contentPanel.add(tfFirstName);
		tfFirstName.setColumns(10);
		
		JLabel lblExtension = new JLabel("Extension:");
		lblExtension.setBounds(12, 212, 116, 16);
		contentPanel.add(lblExtension);
		
		tfExtension = new JTextField();
		tfExtension.setBounds(165, 209, 116, 22);
		contentPanel.add(tfExtension);
		tfExtension.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 283, 56, 16);
		contentPanel.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(165, 280, 116, 22);
		contentPanel.add(tfEmail);
		tfEmail.setColumns(10);
		
		JLabel lblReportsto = new JLabel("ReportsTo:");
		lblReportsto.setBounds(12, 370, 116, 16);
		contentPanel.add(lblReportsto);
		
		tfReportsTo = new JTextField();
		tfReportsTo.setBounds(165, 367, 116, 22);
		contentPanel.add(tfReportsTo);
		tfReportsTo.setColumns(10);
		
		JLabel lblJobtitle = new JLabel("JobTitle:");
		lblJobtitle.setBounds(12, 417, 116, 16);
		contentPanel.add(lblJobtitle);
		
		tfJobTitle = new JTextField();
		tfJobTitle.setBounds(165, 414, 116, 22);
		contentPanel.add(tfJobTitle);
		tfJobTitle.setColumns(10);
		
		JLabel lblOfficecode = new JLabel("OfficeCode:");
		lblOfficecode.setBounds(10, 326, 118, 14);
		contentPanel.add(lblOfficecode);
		
		tfOfficeCode = new JTextField();
		tfOfficeCode.setBounds(165, 323, 116, 20);
		contentPanel.add(tfOfficeCode);
		tfOfficeCode.setColumns(10);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btAlta = new JButton("Alta");
				btAlta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						darAlta();
					}
				});
				btAlta.setActionCommand("OK");
				buttonPane.add(btAlta);
				getRootPane().setDefaultButton(btAlta);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		this.setVisible(true);
	}

	private void darAlta() {
		try {
			EmployeesDAO empleDAO = new EmployeesDAO();
			int employyeNumber = Integer.parseInt(tfEmployeeNumber.getText());
			String lastName = tfLastName.getText();
			String firstName = tfFirstName.getText();
			String extension = tfExtension.getText();
			String email = tfEmail.getText();
			String officeCode = tfOfficeCode.getText();
			int reportsTo = Integer.parseInt(tfReportsTo.getText());
			String jobTitle = tfJobTitle.getText();

			EmployeesDTO empleado = new EmployeesDTO(employyeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle);
			if (empleDAO.insertar(empleado)) {
				JOptionPane.showMessageDialog(contentPanel, "Empleado creado!");
				tfEmployeeNumber.setText("");
				tfLastName.setText("");
				tfFirstName.setText("");
				tfExtension.setText("");
				tfEmail.setText("");
				tfOfficeCode.setText("");
				tfReportsTo.setText("");
				tfJobTitle.setText("");
			} else {
				JOptionPane.showMessageDialog(contentPanel, "Error al crear empleado!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(contentPanel, "Error al crear empleado!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
