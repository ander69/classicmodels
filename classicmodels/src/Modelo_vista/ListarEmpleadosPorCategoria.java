package Modelo_vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modeloDB_DAO.EmployeesDAO;
import modeloDB_DTO.EmployeesDTO;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class ListarEmpleadosPorCategoria extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private ArrayList<EmployeesDTO> empleados;
	private EmployeesDAO emp = new EmployeesDAO();
	private static JTable table;
	private JRadioButton rbtnSalesManager;
	private JRadioButton rbtnSalesRep;


	/**
	 * Create the dialog.
	 */
	public ListarEmpleadosPorCategoria() {
		setBounds(100, 100, 764, 585);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		rbtnSalesRep = new JRadioButton("Sales Rep");
		buttonGroup.add(rbtnSalesRep);
		rbtnSalesRep.setBounds(536, 23, 127, 25);
		contentPanel.add(rbtnSalesRep);
		
		rbtnSalesManager = new JRadioButton("Sales Manager");
		buttonGroup.add(rbtnSalesManager);
		rbtnSalesManager.setBounds(536, 75, 127, 25);
		contentPanel.add(rbtnSalesManager);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(29, 127, 692, 318);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"EmployeeNumber", "LastName", "firstName", "OfficeCode", "ReportsTo", "JobTitle"
					}
				));
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btBuscar = new JButton("Buscar");
				btBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cargarEmpleados();
						
						
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
	}

	private void cargarEmpleados() {
		
		limpiar();
		
		String cat = null;
		if(rbtnSalesRep.isSelected()){
			cat="Sales Rep";
			
		}
		if(rbtnSalesManager.isSelected()){
			
			cat="Sales Man%";
			
		}
		
		empleados = emp.listarTodosCat(cat);
		System.out.println(empleados.size());
		
		
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
