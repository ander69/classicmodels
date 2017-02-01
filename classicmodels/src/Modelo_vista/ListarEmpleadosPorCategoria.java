package Modelo_vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;

public class ListarEmpleadosPorCategoria extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();


	/**
	 * Create the dialog.
	 */
	public ListarEmpleadosPorCategoria() {
		setBounds(100, 100, 764, 585);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JRadioButton rdbtnSalesRep = new JRadioButton("Sales Rep");
		buttonGroup.add(rdbtnSalesRep);
		rdbtnSalesRep.setBounds(536, 23, 127, 25);
		contentPanel.add(rdbtnSalesRep);
		
		JRadioButton rdbtnSalesManager = new JRadioButton("Sales Manager");
		buttonGroup.add(rdbtnSalesManager);
		rdbtnSalesManager.setBounds(536, 75, 127, 25);
		contentPanel.add(rdbtnSalesManager);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(29, 127, 692, 318);
			contentPanel.add(scrollPane);
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
