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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarEmpleadosPorOficina extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> comboBox;
	private JScrollPane scrollPane;
	//private ArrayList<OfficesDTO> oficinas;
	//private OfficesDAO ofi = new OfficesDAO();
	private ArrayList<EmployeesDTO> empleados;
	private EmployeesDAO emp = new EmployeesDAO();
	private static JTable table;

	

	/**
	 * Create the dialog.
	 */
	public ListarEmpleadosPorOficina() {
		setBounds(100, 100, 809, 631);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(33, 174, 703, 333);
			contentPanel.add(scrollPane);
			
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"EmployeeNumber", "LastName", "FirstName", "OfficeCode", "ReportsTo", "JobTitle"
				}
			));
			table.getColumnModel().getColumn(0).setPreferredWidth(99);
			scrollPane.setViewportView(table);
		}
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(360, 86, 341, 22);
		contentPanel.add(comboBox);
		
		JLabel lblOficina = new JLabel("Oficina:");
		lblOficina.setBounds(262, 89, 56, 16);
		contentPanel.add(lblOficina);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btBuscar = new JButton("Buscar");
				btBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cargarTabla();
					}
				});
				btBuscar.setActionCommand("OK");
				buttonPane.add(btBuscar);
				getRootPane().setDefaultButton(btBuscar);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		this.setVisible(true);
		cargarOficinas();
	}
	private void cargarOficinas() {
		OfficesDAO OffiDAO = new OfficesDAO();
	
		List<OfficesDTO> offis = OffiDAO.listarTodos();
		
		String[] Oficinas = new String[offis.size()];
		
		for(int i=0; i<offis.size(); i++) {
			OfficesDTO dpt = offis.get(i);
			Oficinas[i] = dpt.getOfficeCode();
			
		}
		
		comboBox.setModel(new DefaultComboBoxModel<String>(Oficinas));
	}
	
	private void cargarTabla() {
		limpiar();
		
	

		empleados = emp.listarTodosOfi(comboBox.getSelectedItem().toString());
		
		
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		int numcols = modelo.getColumnCount();
		for (EmployeesDTO emp : empleados) {
			Object[] fila = new Object[numcols];
			fila[0] = emp.getEmployeeNumber();
			fila[1] = emp.getLastName();
			fila[2] = emp.getFirstName();
			fila[3] = emp.getOfficeCode();
			fila[4] = emp.getReportsTo();
			fila[5] = emp.getJobTitle();

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
