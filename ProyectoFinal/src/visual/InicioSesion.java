package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Persona;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;

public class InicioSesion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JPasswordField passwordField;

	public InicioSesion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InicioSesion.class.getResource("/media/imgInicioSesion128px.png")));
		setTitle("Iniciar sesi\u00F3n");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 492, 232);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.DARK_GRAY);
			panel.setBorder(new LineBorder(Color.WHITE, 3, true));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblUsuario = new JLabel("Usuario:");
				lblUsuario.setForeground(Color.WHITE);
				lblUsuario.setBounds(184, 38, 64, 14);
				panel.add(lblUsuario);
			}
			
			txtUsuario = new JTextField();
			txtUsuario.setBounds(258, 35, 189, 20);
			panel.add(txtUsuario);
			txtUsuario.setColumns(10);
			
			JLabel lblPassword = new JLabel("Contrase\u00F1a:");
			lblPassword.setForeground(Color.WHITE);
			lblPassword.setBounds(184, 82, 81, 14);
			panel.add(lblPassword);
			
			passwordField = new JPasswordField();
			passwordField.setBounds(258, 79, 189, 20);
			panel.add(passwordField);
			
			JButton btnIniciarSesion = new JButton("Iniciar sesi\u00F3n");
			btnIniciarSesion.setBackground(Color.WHITE);
			btnIniciarSesion.setBounds(304, 123, 130, 23);
			panel.add(btnIniciarSesion);
			
			JButton btnCancelar = new JButton("Cancelar");
			btnCancelar.setBackground(Color.WHITE);
			btnCancelar.setBounds(196, 123, 98, 23);
			panel.add(btnCancelar);
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(InicioSesion.class.getResource("/media/imgInicioSesion128pxB.png")));
			lblNewLabel.setBounds(24, 25, 150, 143);
			panel.add(lblNewLabel);
		}
	}
}
