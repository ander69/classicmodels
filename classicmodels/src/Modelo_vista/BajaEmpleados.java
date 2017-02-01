package Modelo_vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BajaEmpleados extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfEmployeeNumber;


	/**
	 * Create the dialog.
	 */
	public BajaEmpleados() {
		setBounds(100, 100, 719, 536);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(68, 133, 562, 267);
		contentPanel.add(scrollPane);
		
		JLabel lblEmployeeNumber = new JLabel("Employee Number:");
		lblEmployeeNumber.setBounds(164, 70, 126, 16);
		contentPanel.add(lblEmployeeNumber);
		{
			tfEmployeeNumber = new JTextField();
			tfEmployeeNumber.setBounds(286, 67, 116, 22);
			contentPanel.add(tfEmployeeNumber);
			tfEmployeeNumber.setColumns(10);
		}
		{
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.setBounds(436, 66, 97, 25);
			contentPanel.add(btnBuscar);
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		this.setVisible(true);
	}
}
