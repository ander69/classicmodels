package Modelo_vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.parser.Parser;

import modeloDB_DAO.EmployeesDAO;

import modeloDB_DTO.EmployeesDTO;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.event.ActionEvent;

public class BajaEmpleados extends JDialog {

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
	public BajaEmpleados() {
		setBounds(100, 100, 719, 536);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("Employee Number:");
		label.setBounds(25, 53, 116, 16);
		contentPanel.add(label);
		
		tfEmployeeNumber = new JTextField();
		tfEmployeeNumber.setColumns(10);
		tfEmployeeNumber.setBounds(178, 50, 116, 22);
		contentPanel.add(tfEmployeeNumber);
		
		JLabel label_1 = new JLabel("Last Name:");
		label_1.setBounds(25, 108, 116, 16);
		contentPanel.add(label_1);
		
		tfLastName = new JTextField();
		tfLastName.setColumns(10);
		tfLastName.setBounds(178, 105, 116, 22);
		contentPanel.add(tfLastName);
		
		JLabel label_2 = new JLabel("FirstName:");
		label_2.setBounds(25, 173, 116, 16);
		contentPanel.add(label_2);
		
		tfFirstName = new JTextField();
		tfFirstName.setColumns(10);
		tfFirstName.setBounds(178, 170, 116, 22);
		contentPanel.add(tfFirstName);
		
		JLabel label_3 = new JLabel("Extension:");
		label_3.setBounds(25, 229, 116, 16);
		contentPanel.add(label_3);
		
		tfExtension = new JTextField();
		tfExtension.setColumns(10);
		tfExtension.setBounds(178, 226, 116, 22);
		contentPanel.add(tfExtension);
		
		JLabel label_4 = new JLabel("Email:");
		label_4.setBounds(25, 300, 56, 16);
		contentPanel.add(label_4);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(178, 297, 116, 22);
		contentPanel.add(tfEmail);
		
		JLabel label_5 = new JLabel("ReportsTo:");
		label_5.setBounds(25, 385, 116, 16);
		contentPanel.add(label_5);
		
		tfReportsTo = new JTextField();
		tfReportsTo.setColumns(10);
		tfReportsTo.setBounds(178, 382, 116, 22);
		contentPanel.add(tfReportsTo);
		
		JLabel label_6 = new JLabel("JobTitle:");
		label_6.setBounds(25, 434, 116, 16);
		contentPanel.add(label_6);
		
		tfJobTitle = new JTextField();
		tfJobTitle.setColumns(10);
		tfJobTitle.setBounds(178, 431, 116, 22);
		contentPanel.add(tfJobTitle);
		
		JButton button = new JButton("Buscar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarDatos();
			}
		});
		button.setBounds(333, 49, 97, 25);
		contentPanel.add(button);
		
		JLabel lblOfficecode = new JLabel("OfficeCode:");
		lblOfficecode.setBounds(25, 339, 116, 14);
		contentPanel.add(lblOfficecode);
		
		tfOfficeCode = new JTextField();
		tfOfficeCode.setBounds(178, 336, 116, 20);
		contentPanel.add(tfOfficeCode);
		tfOfficeCode.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btBaja = new JButton("Baja");
				btBaja.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						eliminar();
					}
				});
				btBaja.setActionCommand("OK");
				buttonPane.add(btBaja);
				getRootPane().setDefaultButton(btBaja);
			}
			{
				JButton cancelButton = new JButton("Cancel");
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
	private void eliminar() {
		try {
			int confirmar = JOptionPane.showConfirmDialog(contentPanel, "Estás seguro de que deseas eliminar el empleado?");
			if(confirmar != 0) {
				return;
			}
			
			if(empeDAO.borrar(Integer.parseInt(tfEmployeeNumber.getText()))) {
				JOptionPane.showMessageDialog(contentPanel, "Empleado eliminado!");
			} else {
				JOptionPane.showMessageDialog(contentPanel, "Error al eliminar empleado!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(contentPanel, "Error al eliminar empleado!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
