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
				btnListadoEmpleados.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ListadoEmpleados listEmpleados = new ListadoEmpleados();
						listEmpleados.setVisible(true);
					}
				});
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
				lblBienvenida.setText("?BUENOS D?AS, " + auxEmpleado.getNombre().toUpperCase() + "!");
			}
			if(hoy.getHours() >= 12 && hoy.getHours() < 18) {
				lblBienvenida.setText("?BUENAS TARDES, " + auxEmpleado.getNombre().toUpperCase() + "!");
			}
			if(hoy.getHours() >= 18 && hoy.getHours() <= 24) {
				lblBienvenida.setText("?BUENAS NOCHES, " + auxEmpleado.getNombre().toUpperCase() + "!");
			}
			if(hoy.getHours() >= 0 && hoy.getHours() < 6) {
				lblBienvenida.setText("?BUENAS NOCHES, " + auxEmpleado.getNombre().toUpperCase() + "!");
			}
			lblBienvenida.setBounds(328, 47, 600, 34);
			contentPanel.add(lblBienvenida);
		}
		{
			JPanel panelFacturacion = new JPanel();
			panelFacturacion.setLayout(null);
			panelFacturacion.setBackground(Color.LIGHT_GRAY);
			panelFacturacion.setBounds(139, 457, 265, 183);
			contentPanel.add(panelFacturacion);
			{
				JLabel lblFacturacion = new JLabel("FACTURACI\u00D3N");
				lblFacturacion.setHorizontalAlignment(SwingConstants.CENTER);
				lblFacturacion.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblFacturacion.setBounds(62, 11, 140, 32);
				panelFacturacion.add(lblFacturacion);
			}
			{
				JButton btnListadoFacturas = new JButton("Listado de facturas");
				btnListadoFacturas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				btnListadoFacturas.setBackground(Color.WHITE);
				btnListadoFacturas.setBounds(30, 54, 205, 45);
				panelFacturacion.add(btnListadoFacturas);
			}
		}
		{
			JPanel panelServicios = new JPanel();
			panelServicios.setLayout(null);
			panelServicios.setBackground(Color.LIGHT_GRAY);
			panelServicios.setBounds(547, 457, 265, 183);
			contentPanel.add(panelServicios);
			{
				JLabel lblServicios = new JLabel("SERVICIOS");
				lblServicios.setHorizontalAlignment(SwingConstants.CENTER);
				lblServicios.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblServicios.setBounds(78, 11, 109, 32);
				panelServicios.add(lblServicios);
			}
			{
				JButton btnNuevoServicio = new JButton("Nuevo servicio");
				btnNuevoServicio.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegistrarServicio regServ = new RegistrarServicio();
						regServ.setVisible(true);
						regServ.setModal(true);
					}
				});
				btnNuevoServicio.setBackground(Color.WHITE);
				btnNuevoServicio.setBounds(30, 54, 205, 45);
				panelServicios.add(btnNuevoServicio);
			}
			{
				JButton btnListadoDeServicios = new JButton("Listado de servicios");
				btnListadoDeServicios.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ListadoServicios lisServ = new ListadoServicios();
						lisServ.setVisible(true);
						lisServ.setModal(true);
					}
				});
				
				btnListadoDeServicios.setBackground(Color.WHITE);
				btnListadoDeServicios.setBounds(30, 110, 205, 45);
				panelServicios.add(btnListadoDeServicios);
			}
		}
	}

}
