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
	private JTable table;
	private JTextField tfCity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarEmpleadosEnCadaOficina dialog = new ListarEmpleadosEnCadaOficina();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarEmpleadosEnCadaOficina() {
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
		
	}
}
