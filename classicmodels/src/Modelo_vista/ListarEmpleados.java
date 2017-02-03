package Modelo_vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import modeloDB_DAO.EmployeesDAO;

import modeloDB_DTO.EmployeesDTO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarEmpleados extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private ArrayList<EmployeesDTO> empleados;
	private EmployeesDAO emp = new EmployeesDAO();


	/**
	 * Create the dialog.
	 */
	public ListarEmpleados() {
		setBounds(100, 100, 755, 543);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(58, 88, 626, 343);
			contentPanel.add(scrollPane);
			
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"EmployeeNumber", "LastName", "FirstName", "OfficeCode", "ReportsTo", "JobTitle"
				}
			));
			table.getColumnModel().getColumn(0).setPreferredWidth(113);
			scrollPane.setViewportView(table);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
		try{
			cargarEmpleados();
		}catch(Exception e){
			JOptionPane.showMessageDialog(contentPanel, "Error al leer los empleados!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		this.setVisible(true);
	}
	private void cargarEmpleados(){
		
		empleados = emp.listarTodos();
		DefaultTableModel modelo = (DefaultTableModel)table.getModel();
		int numcols = modelo.getColumnCount();
		for(EmployeesDTO emp : empleados){
			Object [] fila=new Object[numcols];
			fila[0] = emp.getEmployeeNumber();
			fila[1] = emp.getLastName();
			fila[2] = emp.getFirstName();
			fila[3] = emp.getOfficeCode();
			fila[4] = emp.getReportsTo();
			fila[5] = emp.getJobTitle();
			
			modelo.addRow(fila);
		}
	}
}
