package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Empleado;
import java.awt.Toolkit;
import java.util.Date;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Inicio extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private Empleado auxEmpleado = null;
	private Dimension dim;
	private Date hoy;

	public Inicio(Empleado empleado) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/media/imgLogoPequeno.jpg")));
		setTitle("Altice - Inicio");
		auxEmpleado = empleado;
		hoy = new Date();
		setBounds(100, 100, 900, 500);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height-40);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelPlanes = new JPanel();
			panelPlanes.setBackground(Color.LIGHT_GRAY);
			panelPlanes.setBounds(547, 250, 265, 183);
			contentPanel.add(panelPlanes);
			panelPlanes.setLayout(null);
			{
				JLabel lblPlanes = new JLabel("PLANES");
				lblPlanes.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblPlanes.setHorizontalAlignment(SwingConstants.CENTER);
				lblPlanes.setBounds(78, 11, 109, 32);
				panelPlanes.add(lblPlanes);
			}
			{
				JButton btnRegistrarPlan = new JButton("Registrar plan");
				btnRegistrarPlan.setBackground(Color.WHITE);
				btnRegistrarPlan.setBounds(30, 54, 205, 45);
				panelPlanes.add(btnRegistrarPlan);
			}
			{
				JButton btnListadoPlanes = new JButton("Listado de planes");
				btnListadoPlanes.setBackground(Color.WHITE);
				btnListadoPlanes.setBounds(30, 110, 205, 45);
				panelPlanes.add(btnListadoPlanes);
			}
		}
		{
			JPanel panelPersonal = new JPanel();
			panelPersonal.setLayout(null);
			panelPersonal.setBackground(Color.LIGHT_GRAY);
			panelPersonal.setBounds(139, 250, 265, 183);
			contentPanel.add(panelPersonal);
			{
				JLabel lblPersonal = new JLabel("PERSONAL");
				lblPersonal.setHorizontalAlignment(SwingConstants.CENTER);
				lblPersonal.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblPersonal.setBounds(78, 11, 109, 32);
				panelPersonal.add(lblPersonal);
			}
			{
				JButton btnRegistrarEmpleado = new JButton("Registrar empleado");
				btnRegistrarEmpleado.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						RegEmpAdmin regEmpleado = new RegEmpAdmin();
						regEmpleado.setVisible(true);
					}
				});
				btnRegistrarEmpleado.setBackground(Color.WHITE);
				btnRegistrarEmpleado.setBounds(30, 54, 205, 45);
				panelPersonal.add(btnRegistrarEmpleado);
			}
			{
				JButton btnListadoEmpleados = new JButton("Listado de empleados");
				btnListadoEmpleados.setBackground(Color.WHITE);
				btnListadoEmpleados.setBounds(30, 110, 205, 45);
				panelPersonal.add(btnListadoEmpleados);
			}
		}
		{
			JPanel panelClientes = new JPanel();
			panelClientes.setLayout(null);
			panelClientes.setBackground(Color.LIGHT_GRAY);
			panelClientes.setBounds(953, 250, 265, 183);
			contentPanel.add(panelClientes);
			{
				JLabel lblClientes = new JLabel("CLIENTES");
				lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
				lblClientes.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblClientes.setBounds(78, 11, 109, 32);
				panelClientes.add(lblClientes);
			}
			{
				JButton btnListadoClientes = new JButton("Listado de clientes");
				btnListadoClientes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ListadoClientes listClientes = new ListadoClientes();
						listClientes.setVisible(true);
					}
				});
				btnListadoClientes.setBackground(Color.WHITE);
				btnListadoClientes.setBounds(30, 54, 205, 45);
				panelClientes.add(btnListadoClientes);
			}
		}
		{
			JLabel lblImagenLogo = new JLabel("");
			lblImagenLogo.setVerticalAlignment(SwingConstants.TOP);
			lblImagenLogo.setIcon(new ImageIcon(Inicio.class.getResource("/media/imgLogoTransparente.png")));
			lblImagenLogo.setBounds(41, 30, 375, 209);
			contentPanel.add(lblImagenLogo);
		}
		{
			JLabel lblBienvenida = new JLabel("");
			lblBienvenida.setForeground(Color.LIGHT_GRAY);
			lblBienvenida.setFont(new Font("Tahoma", Font.BOLD, 14));
			if(hoy.getHours() >= 6 && hoy.getHours() < 12) {
				lblBienvenida.setText("¡BUENOS DÍAS, " + auxEmpleado.getNombre().toUpperCase() + "!");
			}
			if(hoy.getHours() >= 12 && hoy.getHours() < 18) {
				lblBienvenida.setText("¡BUENAS TARDES, " + auxEmpleado.getNombre().toUpperCase() + "!");
			}
			if(hoy.getHours() >= 18 && hoy.getHours() <= 24) {
				lblBienvenida.setText("¡BUENAS NOCHES, " + auxEmpleado.getNombre().toUpperCase() + "!");
			}
			if(hoy.getHours() >= 0 && hoy.getHours() < 6) {
				lblBienvenida.setText("¡BUENAS NOCHES, " + auxEmpleado.getNombre().toUpperCase() + "!");
			}
			lblBienvenida.setBounds(328, 47, 600, 34);
			contentPanel.add(lblBienvenida);
		}
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBackground(Color.LIGHT_GRAY);
			panel.setBounds(139, 457, 265, 183);
			contentPanel.add(panel);
			{
				JLabel lblFacturacion = new JLabel("FACTURACI\u00D3N");
				lblFacturacion.setHorizontalAlignment(SwingConstants.CENTER);
				lblFacturacion.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblFacturacion.setBounds(62, 11, 140, 32);
				panel.add(lblFacturacion);
			}
			{
				JButton btnListadoFacturas = new JButton("Listado de facturas");
				btnListadoFacturas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				btnListadoFacturas.setBackground(Color.WHITE);
				btnListadoFacturas.setBounds(30, 54, 205, 45);
				panel.add(btnListadoFacturas);
			}
		}
	}

}
