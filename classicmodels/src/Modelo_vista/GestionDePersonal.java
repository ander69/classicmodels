package Modelo_vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionDePersonal extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnAltaEmpleados;
	private JButton btnBajaEmpleados;
	private JButton btnModificarEmpleados;
	private JButton btnListarPorOficina;
	private JButton btnListarEmpleados;
	private JButton btnListarPorCategoria;

	/**
	 * Launch the application.
	 */

	public GestionDePersonal() {
		setBounds(100, 100, 546, 310);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			btnAltaEmpleados = new JButton("Alta Empleados");
			btnAltaEmpleados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new AltaEmpleados();
				}
			});
			btnAltaEmpleados.setBounds(12, 13, 173, 25);
			contentPanel.add(btnAltaEmpleados);
		}
		{
			btnBajaEmpleados = new JButton("Baja Empleados");
			btnBajaEmpleados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new BajaEmpleados();
				}
			});
			btnBajaEmpleados.setBounds(12, 70, 173, 25);
			contentPanel.add(btnBajaEmpleados);
		}
		{
			btnListarEmpleados = new JButton("Listar Empleados");
			btnListarEmpleados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ListarEmpleados();
				}
			});
			btnListarEmpleados.setBounds(343, 13, 173, 25);
			contentPanel.add(btnListarEmpleados);
		}
		{
			btnListarPorOficina = new JButton("Listar Por Oficina");
			btnListarPorOficina.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ListarEmpleadosPorOficina();
				}
			});
			btnListarPorOficina.setBounds(343, 70, 173, 25);
			contentPanel.add(btnListarPorOficina);
		}
		{
			btnListarPorCategoria = new JButton("Listar Por Categoria");
			btnListarPorCategoria.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ListarEmpleadosPorCategoria();
				}
			});
			btnListarPorCategoria.setBounds(341, 126, 173, 25);
			contentPanel.add(btnListarPorCategoria);
		}
		{
			btnModificarEmpleados = new JButton("Modificar Empleados");
			btnModificarEmpleados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ModificarEmpleados();
				}
			});
			btnModificarEmpleados.setBounds(12, 126, 173, 25);
			contentPanel.add(btnModificarEmpleados);
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
