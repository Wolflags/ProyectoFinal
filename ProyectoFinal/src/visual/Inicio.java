package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Altice;
import logico.Empleado;
import java.awt.Toolkit;
import java.util.Date;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class Inicio extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private Empleado auxEmpleado = null;
	private Dimension dim;
	private Date hoy;
	public Inicio(Empleado empleado) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				
			}
		});
		
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
				JButton btnRegistrarPlan = new JButton("     Registrar plan");
				btnRegistrarPlan.setHorizontalAlignment(SwingConstants.LEFT);
				btnRegistrarPlan.setIcon(new ImageIcon(Inicio.class.getResource("/media/imgAgregarPlan32px.png")));
				btnRegistrarPlan.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegistrarPlan regPlan = new RegistrarPlan();
						regPlan.setVisible(true);
						regPlan.setModal(true);
					}
				});
				btnRegistrarPlan.setBackground(Color.WHITE);
				btnRegistrarPlan.setBounds(30, 54, 205, 45);
				panelPlanes.add(btnRegistrarPlan);
			}
			{
				JButton btnListadoPlanes = new JButton("     Listado de planes");
				btnListadoPlanes.setIcon(new ImageIcon(Inicio.class.getResource("/media/imgListadoPlanes32px.png")));
				btnListadoPlanes.setHorizontalAlignment(SwingConstants.LEFT);
				btnListadoPlanes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegistrarPlan regPlan = new RegistrarPlan();
						regPlan.setVisible(true);
						regPlan.setModal(true);
					}
				});
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
				JButton btnRegistrarEmpleado = new JButton("     Registrar empleado");
				btnRegistrarEmpleado.setHorizontalAlignment(SwingConstants.LEFT);
				btnRegistrarEmpleado.setIcon(new ImageIcon(Inicio.class.getResource("/media/imgAgregarEmpleado32px.png")));
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
				JButton btnListadoEmpleados = new JButton("     Listado de empleados");
				btnListadoEmpleados.setHorizontalAlignment(SwingConstants.LEFT);
				btnListadoEmpleados.setIcon(new ImageIcon(Inicio.class.getResource("/media/imgListadoEmpleados32px.png")));
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
				JButton btnListadoClientes = new JButton("     Listado de clientes");
				btnListadoClientes.setHorizontalAlignment(SwingConstants.LEFT);
				btnListadoClientes.setIcon(new ImageIcon(Inicio.class.getResource("/media/imgListadoClientes32px.png")));
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
				JButton btnListadoFacturas = new JButton("     Listado de facturas");
				btnListadoFacturas.setIcon(new ImageIcon(Inicio.class.getResource("/media/imgListadoFacturas32pxB.png")));
				btnListadoFacturas.setHorizontalAlignment(SwingConstants.LEFT);
				btnListadoFacturas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ListadoFacturas listFacturas = new ListadoFacturas();
						listFacturas.setVisible(true);
					}
				});
				btnListadoFacturas.setBackground(Color.WHITE);
				btnListadoFacturas.setBounds(29, 109, 205, 45);
				panelFacturacion.add(btnListadoFacturas);
			}
			
			JButton btnRealizarVenta = new JButton("     Realizar venta");
			btnRealizarVenta.setIcon(new ImageIcon(Inicio.class.getResource("/media/imgRealizarVenta32px.png")));
			btnRealizarVenta.setHorizontalAlignment(SwingConstants.LEFT);
			btnRealizarVenta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					RealizarVenta reVen = new RealizarVenta(empleado);
					reVen.setVisible(true);
					reVen.setModal(true);
				}
			});
			btnRealizarVenta.setBackground(Color.WHITE);
			btnRealizarVenta.setBounds(29, 53, 205, 45);
			panelFacturacion.add(btnRealizarVenta);
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
				JButton btnNuevoServicio = new JButton("     Nuevo servicio");
				btnNuevoServicio.setIcon(new ImageIcon(Inicio.class.getResource("/media/imgAgregarServicio32pxB.png")));
				btnNuevoServicio.setHorizontalAlignment(SwingConstants.LEFT);
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
				JButton btnListadoDeServicios = new JButton("     Listado de servicios");
				btnListadoDeServicios.setIcon(new ImageIcon(Inicio.class.getResource("/media/imgListadoServicios32px.png")));
				btnListadoDeServicios.setHorizontalAlignment(SwingConstants.LEFT);
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
		
		JPanel panelSesion = new JPanel();
		panelSesion.setLayout(null);
		panelSesion.setBackground(Color.LIGHT_GRAY);
		panelSesion.setBounds(953, 457, 265, 183);
		contentPanel.add(panelSesion);
		
		JLabel lblSesion = new JLabel("SESI\u00D3N");
		lblSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblSesion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSesion.setBounds(78, 11, 109, 32);
		panelSesion.add(lblSesion);
		
		JButton btnVerMiPerfil = new JButton("     Ver mi perfil");
		btnVerMiPerfil.setIcon(new ImageIcon(Inicio.class.getResource("/media/imgVerPerfil32px.png")));
		btnVerMiPerfil.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerMiPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PerfilEmpAdmin perEmpAdm = new PerfilEmpAdmin(auxEmpleado);
				perEmpAdm.setVisible(true);
			}
		});
		btnVerMiPerfil.setBackground(Color.WHITE);
		btnVerMiPerfil.setBounds(30, 54, 205, 45);
		panelSesion.add(btnVerMiPerfil);
		
		JButton btnCerrarSesion = new JButton("     Cerrar sesi\u00F3n");
		btnCerrarSesion.setHorizontalAlignment(SwingConstants.LEADING);
		btnCerrarSesion.setIcon(new ImageIcon(Inicio.class.getResource("/media/imgCerrarSesion32px.png")));
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro que quiere cerrar sesión?");
				if(opcion == 0) {
					dispose();
					Login login = new Login();
					login.setVisible(true);
				}
			}
		});
		btnCerrarSesion.setBackground(Color.WHITE);
		btnCerrarSesion.setBounds(30, 110, 205, 45);
		panelSesion.add(btnCerrarSesion);
	}
}
