package Modelo_vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modeloDB_DAO.EmployeesDAO;
import modeloDB_DAO.OfficesDAO;
import modeloDB_DTO.OfficesDTO;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Offices extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfOffices;
	private JTextField tfCity;
	private JTextField tfPhone;
	private JTextField tfAddressLine;
	private JTextField tfState;
	private JTextField tfCountry;
	private JTextField tfPostalCode;
	private JTextField tfTerritory;
	private JTextField tfRecaudado;
	private JTextField tfEmpleados;
	private OfficesDAO offices = new OfficesDAO();
	private List<OfficesDTO> officeslist;
	private EmployeesDAO emp = new EmployeesDAO();

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public Offices() {
		setBounds(100, 100, 716, 386);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblOfficces = new JLabel("Oficces:");
		lblOfficces.setBounds(56, 35, 46, 14);
		contentPanel.add(lblOfficces);
		
		tfOffices = new JTextField();
		tfOffices.setBounds(112, 32, 33, 20);
		contentPanel.add(tfOffices);
		tfOffices.setColumns(10);
		
		JButton btnPrimero = new JButton("<<");
		btnPrimero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarDatos(0);
			}
		});
		btnPrimero.setBounds(166, 31, 89, 23);
		contentPanel.add(btnPrimero);
		
		JButton btnAnterior = new JButton("<");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int posicionActual = Integer.parseInt(tfOffices.getText());
				if(posicionActual <=0){
					posicionActual=officeslist.size();
				}
				posicionActual--;
				cargarDatos(posicionActual);
			}
		});
		btnAnterior.setBounds(265, 31, 89, 23);
		contentPanel.add(btnAnterior);
		
		JButton btnSiguiente = new JButton(">");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int posicionActual = Integer.parseInt(tfOffices.getText());
				if(posicionActual>=officeslist.size()-1){
					posicionActual=0;
				}
				posicionActual++;
				cargarDatos(posicionActual);
				
			}
		});
		btnSiguiente.setBounds(364, 31, 89, 23);
		contentPanel.add(btnSiguiente);
		
		JButton btnUltimo = new JButton(">>");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDatos(officeslist.size()-1);
			}
		});
		btnUltimo.setBounds(463, 31, 89, 23);
		contentPanel.add(btnUltimo);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(34, 123, 46, 14);
		contentPanel.add(lblCity);
		
		tfCity = new JTextField();
		tfCity.setBounds(59, 120, 46, 20);
		contentPanel.add(tfCity);
		tfCity.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(130, 123, 46, 14);
		contentPanel.add(lblPhone);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(166, 120, 86, 20);
		contentPanel.add(tfPhone);
		tfPhone.setColumns(10);
		
		JLabel lblAddressline = new JLabel("AddressLine:");
		lblAddressline.setBounds(265, 123, 68, 14);
		contentPanel.add(lblAddressline);
		
		tfAddressLine = new JTextField();
		tfAddressLine.setBounds(329, 120, 316, 20);
		contentPanel.add(tfAddressLine);
		tfAddressLine.setColumns(10);
		
		JLabel lblState = new JLabel("State:");
		lblState.setBounds(36, 200, 46, 14);
		contentPanel.add(lblState);
		
		tfState = new JTextField();
		tfState.setBounds(79, 197, 86, 20);
		contentPanel.add(tfState);
		tfState.setColumns(10);
		
		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setBounds(188, 200, 46, 14);
		contentPanel.add(lblCountry);
		
		tfCountry = new JTextField();
		tfCountry.setBounds(244, 197, 86, 20);
		contentPanel.add(tfCountry);
		tfCountry.setColumns(10);
		
		JLabel lblPostalcode = new JLabel("PostalCode:");
		lblPostalcode.setBounds(344, 200, 68, 14);
		contentPanel.add(lblPostalcode);
		
		tfPostalCode = new JTextField();
		tfPostalCode.setBounds(417, 197, 86, 20);
		contentPanel.add(tfPostalCode);
		tfPostalCode.setColumns(10);
		
		JLabel lblTerritory = new JLabel("Territory:");
		lblTerritory.setBounds(513, 200, 46, 14);
		contentPanel.add(lblTerritory);
		
		tfTerritory = new JTextField();
		tfTerritory.setBounds(569, 197, 86, 20);
		contentPanel.add(tfTerritory);
		tfTerritory.setColumns(10);
		
		JLabel lblRecaudado = new JLabel("Recaudado:");
		lblRecaudado.setBounds(79, 272, 77, 14);
		contentPanel.add(lblRecaudado);
		
		tfRecaudado = new JTextField();
		tfRecaudado.setBounds(166, 269, 86, 20);
		contentPanel.add(tfRecaudado);
		tfRecaudado.setColumns(10);
		
		JLabel lblNEmpleados = new JLabel("N\u00BA Empleados:");
		lblNEmpleados.setBounds(329, 273, 81, 14);
		contentPanel.add(lblNEmpleados);
		
		tfEmpleados = new JTextField();
		tfEmpleados.setBounds(420, 270, 86, 20);
		contentPanel.add(tfEmpleados);
		tfEmpleados.setColumns(10);
		
		JButton btnVerEmpleados = new JButton("Ver Empleados");
		btnVerEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ListarEmpleadosEnCadaOficina(Integer.parseInt((tfOffices.getText())) ,tfCity.getText());
			}
		});
		btnVerEmpleados.setBounds(531, 269, 114, 23);
		contentPanel.add(btnVerEmpleados);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btVolver = new JButton("Volver");
				btVolver.setActionCommand("Cancel");
				buttonPane.add(btVolver);
			}
		}
		this.setVisible(true);
		cargarOficinas();
		
	}
	private void cargarOficinas() {
		try{
			officeslist = offices.listarTodos();
			cargarDatos(0);
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void cargarDatos(int pos){
		OfficesDTO office =  officeslist.get(pos);
		tfOffices.setText(Integer.toString(pos));
		tfCity.setText(office.getCity());
		tfPhone.setText(office.getPhone());
		tfAddressLine.setText(office.getAddressLine1()+"-"+office.getAddressLine2());
		tfState.setText(office.getState());
		tfCountry.setText(office.getCountry());
		tfPostalCode.setText(office.getPostalCode());
		tfTerritory.setText(office.getTerritory());	
		tfEmpleados.setText(Integer.toString(emp.NumeroEmpleados(Integer.toString(pos))));

	}
}
