package Modelo_vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import modeloDB_DAO.EmployeesDAO;
import modeloDB_DAO.OfficesDAO;

import modeloDB_DTO.EmployeesDTO;
import modeloDB_DTO.OfficesDTO;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarEmpleadosEnCadaOficina extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private JTextField tfCity;
	private ArrayList<EmployeesDTO> empleados;
	private EmployeesDAO emp = new EmployeesDAO();
	private int office;
	private String city;
	

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public ListarEmpleadosEnCadaOficina(int office ,String city) {
		this.city = city;
		this.office = office;
		setBounds(100, 100, 549, 397);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(69, 77, 400, 203);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"EmployeeNumber", "Name", "email", "JobTitle"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(103);
		scrollPane.setViewportView(table);
		
		tfCity = new JTextField();
		tfCity.setBounds(256, 26, 86, 20);
		contentPanel.add(tfCity);
		tfCity.setColumns(10);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(176, 29, 46, 14);
		contentPanel.add(lblCity);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnVolver = new JButton("Volver");
				btnVolver.setActionCommand("Cancel");
				buttonPane.add(btnVolver);
			}
		}
		this.setVisible(true);
		cargarTabla();
		
	}
	private void cargarTabla() {
		limpiar();
		
	

		empleados = emp.listarTodosOfi(Integer.toString(office));
		tfCity.setText(city);
		
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		int numcols = modelo.getColumnCount();
		for (EmployeesDTO emp : empleados) {
			Object[] fila = new Object[numcols];
			fila[0] = emp.getEmployeeNumber();
			fila[1] = emp.getLastName()+" "+emp.getFirstName();
			fila[2] = emp.getEmail();
			fila[3] = emp.getJobTitle();
			modelo.addRow(fila);
		}
	}
	public static void limpiar(){
		int fila;
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		fila=model.getRowCount();
		for(int i = 0;i<fila;i++){
			model.removeRow(0);
		}
		
	}
}


