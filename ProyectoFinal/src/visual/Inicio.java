package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Empleado;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Inicio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Empleado auxEmpleado = null;

	public Inicio(Empleado empleado) {
		setModal(true);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/media/imgLogoPequeno.jpg")));
		setTitle("Altice - Inicio");
		auxEmpleado = empleado;
		setBounds(100, 100, 770, 389);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelClientes = new JPanel();
			panelClientes.setBackground(Color.LIGHT_GRAY);
			panelClientes.setBounds(244, 50, 265, 250);
			contentPanel.add(panelClientes);
			panelClientes.setLayout(null);
			{
				JLabel lblPlanes = new JLabel("PLANES");
				lblPlanes.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblPlanes.setHorizontalAlignment(SwingConstants.CENTER);
				lblPlanes.setBounds(78, 11, 109, 32);
				panelClientes.add(lblPlanes);
			}
			{
				JButton btnRegistrarPlan = new JButton("Registrar plan");
				btnRegistrarPlan.setBackground(Color.WHITE);
				btnRegistrarPlan.setBounds(30, 54, 205, 45);
				panelClientes.add(btnRegistrarPlan);
			}
			{
				JButton btnVerPlanes = new JButton("Ver planes");
				btnVerPlanes.setBackground(Color.WHITE);
				btnVerPlanes.setBounds(30, 110, 205, 45);
				panelClientes.add(btnVerPlanes);
			}
		}
	}

}
