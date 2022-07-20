package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class Inicio extends JFrame {

	private JPanel contentPane;
	private Dimension dim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inicio() {
		setTitle("Altice");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height-40);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblImagenLogoGrande = new JLabel("");
		lblImagenLogoGrande.setIcon(new ImageIcon(Inicio.class.getResource("/media/imgLogoGrande.jpg")));
		lblImagenLogoGrande.setBounds(280, 32, 800, 312);
		contentPane.add(lblImagenLogoGrande);
		
		JPanel panelIniciarSesion = new JPanel();
		panelIniciarSesion.setBorder(new LineBorder(new Color(128, 128, 128), 3, true));
		panelIniciarSesion.setBackground(Color.LIGHT_GRAY);
		panelIniciarSesion.setBounds(503, 355, 353, 282);
		contentPane.add(panelIniciarSesion);
		panelIniciarSesion.setLayout(null);
		
		JButton btnInicioAdmin = new JButton("Iniciar sesi\u00F3n como administrador");
		btnInicioAdmin.setIcon(new ImageIcon(Inicio.class.getResource("/media/imgAdmin64px.png")));
		btnInicioAdmin.setBackground(Color.WHITE);
		btnInicioAdmin.setBounds(10, 27, 333, 70);
		panelIniciarSesion.add(btnInicioAdmin);
		
		JButton btnInicioCajero = new JButton("              Iniciar sesi\u00F3n como cajero");
		btnInicioCajero.setBackground(Color.WHITE);
		btnInicioCajero.setIcon(new ImageIcon(Inicio.class.getResource("/media/imgCajero64px.png")));
		btnInicioCajero.setBounds(10, 108, 333, 70);
		panelIniciarSesion.add(btnInicioCajero);
		
		JButton btnInicioCliente = new JButton("            Iniciar sesi\u00F3n como cliente");
		btnInicioCliente.setBackground(Color.WHITE);
		btnInicioCliente.setIcon(new ImageIcon(Inicio.class.getResource("/media/imgCliente64px.png")));
		btnInicioCliente.setBounds(10, 187, 333, 70);
		panelIniciarSesion.add(btnInicioCliente);
	}
}
