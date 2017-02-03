package Modelo_vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class ModificarEmpleados extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private EmployeesDAO empeDAO = new EmployeesDAO();
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
	public ModificarEmpleados() {
		setBounds(100, 100, 841, 662);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("Employee Number:");
			label.setBounds(26, 53, 116, 16);
			contentPanel.add(label);
		}
		{
			tfEmployeeNumber = new JTextField();
			tfEmployeeNumber.setColumns(10);
			tfEmployeeNumber.setBounds(179, 50, 116, 22);
			contentPanel.add(tfEmployeeNumber);
		}
		{
			JLabel label = new JLabel("Last Name:");
			label.setBounds(26, 108, 116, 16);
			contentPanel.add(label);
		}
		{
			tfLastName = new JTextField();
			tfLastName.setColumns(10);
			tfLastName.setBounds(179, 105, 116, 22);
			contentPanel.add(tfLastName);
		}
		{
			JLabel label = new JLabel("FirstName:");
			label.setBounds(26, 173, 116, 16);
			contentPanel.add(label);
		}
		{
			tfFirstName = new JTextField();
			tfFirstName.setColumns(10);
			tfFirstName.setBounds(179, 170, 116, 22);
			contentPanel.add(tfFirstName);
		}
		{
			JLabel label = new JLabel("Extension:");
			label.setBounds(26, 229, 116, 16);
			contentPanel.add(label);
		}
		{
			tfExtension = new JTextField();
			tfExtension.setColumns(10);
			tfExtension.setBounds(179, 226, 116, 22);
			contentPanel.add(tfExtension);
		}
		{
			JLabel label = new JLabel("Email:");
			label.setBounds(26, 300, 56, 16);
			contentPanel.add(label);
		}
		{
			tfEmail = new JTextField();
			tfEmail.setColumns(10);
			tfEmail.setBounds(179, 297, 116, 22);
			contentPanel.add(tfEmail);
		}
		{
			JLabel label = new JLabel("ReportsTo:");
			label.setBounds(26, 381, 116, 16);
			contentPanel.add(label);
		}
		{
			tfReportsTo = new JTextField();
			tfReportsTo.setColumns(10);
			tfReportsTo.setBounds(179, 378, 116, 22);
			contentPanel.add(tfReportsTo);
		}
		{
			JLabel label = new JLabel("JobTitle:");
			label.setBounds(26, 434, 116, 16);
			contentPanel.add(label);
		}
		{
			tfJobTitle = new JTextField();
			tfJobTitle.setColumns(10);
			tfJobTitle.setBounds(179, 431, 116, 22);
			contentPanel.add(tfJobTitle);
		}
		{
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cargarDatos();
				}
			});
			btnBuscar.setBounds(334, 49, 97, 25);
			contentPanel.add(btnBuscar);
		}
		{
			JLabel lblNewLabel = new JLabel("OfficeCode");
			lblNewLabel.setBounds(26, 337, 116, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			tfOfficeCode = new JTextField();
			tfOfficeCode.setBounds(179, 330, 116, 20);
			contentPanel.add(tfOfficeCode);
			tfOfficeCode.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btModificar = new JButton("Modificar");
				btModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						modificar();
					}
				});
				btModificar.setActionCommand("OK");
				buttonPane.add(btModificar);
				getRootPane().setDefaultButton(btModificar);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		this.setVisible(true);
	}
	private void cargarDatos() {

		EmployeesDTO empleado = empeDAO.buscar(Integer.parseInt(tfEmployeeNumber.getText()));
		tfLastName.setText(empleado.getLastName());
		tfFirstName.setText(empleado.getFirstName());
		tfExtension.setText(empleado.getExtension());
		tfEmail.setText(empleado.getEmail());
		tfOfficeCode.setText(empleado.getOfficeCode());
		tfReportsTo.setText(Integer.toString(empleado.getReportsTo()));
		tfJobTitle.setText(empleado.getJobTitle());
		

	}
	private void modificar() {
		try {
			int confirmar = JOptionPane.showConfirmDialog(contentPanel, "Estás seguro de que deseas modificar el empleado?");
			if(confirmar != 0) {
				return;
			}
			int employyeNumber = Integer.parseInt(tfEmployeeNumber.getText());
			String lastName = tfLastName.getText();
			String firstName = tfFirstName.getText();
			String extension = tfExtension.getText();
			String email = tfEmail.getText();
			String officeCode = tfOfficeCode.getText();
			int reportsTo = Integer.parseInt(tfReportsTo.getText());
			String jobTitle = tfJobTitle.getText();
			EmployeesDTO empleado = new EmployeesDTO(employyeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle);
			
			
			if(empeDAO.actualizar(empleado)) {
				JOptionPane.showMessageDialog(contentPanel, "Empleado modificado!");
			} else {
				JOptionPane.showMessageDialog(contentPanel, "Error al modificar empleado!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(contentPanel, "Error al modificar empleado!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
