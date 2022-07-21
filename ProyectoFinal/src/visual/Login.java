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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Login extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private JTextField txtUsuario;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
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
		lblImagenLogoGrande.setIcon(new ImageIcon(Login.class.getResource("/media/imgLogoGrande.jpg")));
		lblImagenLogoGrande.setBounds(280, 32, 800, 312);
		contentPane.add(lblImagenLogoGrande);
		
		JPanel panelIniciarSesion = new JPanel();
		panelIniciarSesion.setBorder(new LineBorder(new Color(128, 128, 128), 3, true));
		panelIniciarSesion.setBackground(Color.LIGHT_GRAY);
		panelIniciarSesion.setBounds(525, 355, 310, 282);
		contentPane.add(panelIniciarSesion);
		panelIniciarSesion.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(55, 67, 79, 14);
		panelIniciarSesion.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(55, 91, 200, 20);
		panelIniciarSesion.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setBounds(55, 122, 79, 14);
		panelIniciarSesion.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(55, 147, 200, 20);
		panelIniciarSesion.add(passwordField);
		
		JLabel lblIniciarSesion = new JLabel("Iniciar sesi\u00F3n");
		lblIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciarSesion.setBounds(50, 11, 205, 36);
		panelIniciarSesion.add(lblIniciarSesion);
		
		JButton btnIniciarSesion = new JButton("Iniciar sesi\u00F3n");
		btnIniciarSesion.setBackground(Color.WHITE);
		btnIniciarSesion.setBounds(97, 188, 116, 23);
		panelIniciarSesion.add(btnIniciarSesion);
	}
}
