package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import logico.Altice;
import logico.Empleado;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class Login extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private JTextField txtUsuario;
	private Empleado auxEmpleado = null;
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
		txtUsuario.setText("Admin");
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setBounds(55, 122, 79, 14);
		panelIniciarSesion.add(lblPassword);
		
		JLabel lblIniciarSesion = new JLabel("Iniciar sesi\u00F3n");
		lblIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciarSesion.setBounds(50, 11, 205, 36);
		panelIniciarSesion.add(lblIniciarSesion);
		
		JButton btnIniciarSesion = new JButton("Iniciar sesi\u00F3n");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtUsuario.getText().equalsIgnoreCase("")) {
					if(Altice.getInstance().validarClave(txtUsuario.getText().toString(), String.valueOf(passwordField.getPassword()))) {
						auxEmpleado = Altice.getInstance().buscarEmpleadoByCedula(txtUsuario.getText().toString());
						JOptionPane.showMessageDialog(null, "Inicio de sesión satisfactorio", "Información", JOptionPane.INFORMATION_MESSAGE);
						Inicio inicio = new Inicio(auxEmpleado);
						dispose();
						inicio.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto", "Advertencia", JOptionPane.WARNING_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnIniciarSesion.setBackground(Color.WHITE);
		btnIniciarSesion.setBounds(97, 188, 116, 23);
		panelIniciarSesion.add(btnIniciarSesion);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(55, 147, 200, 20);
		panelIniciarSesion.add(passwordField);
		passwordField.setText("1234");
		
		JLabel lblImagenContrasenaVisible = new JLabel("");
		lblImagenContrasenaVisible.setIcon(new ImageIcon(Login.class.getResource("/media/imgVerClaveNo16px.png")));
		lblImagenContrasenaVisible.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblImagenContrasenaVisible.setIcon(new ImageIcon(Login.class.getResource("/media/imgVerClaveSi16px.png")));
				passwordField.setEchoChar((char)0);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblImagenContrasenaVisible.setIcon(new ImageIcon(Login.class.getResource("/media/imgVerClaveNo16px.png")));
				passwordField.setEchoChar('•');
			}
		});
		
		lblImagenContrasenaVisible.setBounds(264, 147, 21, 20);
		panelIniciarSesion.add(lblImagenContrasenaVisible);
	}
}