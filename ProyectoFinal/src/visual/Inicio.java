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
		panelIniciarSesion.setBounds(525, 355, 310, 282);
		contentPane.add(panelIniciarSesion);
		panelIniciarSesion.setLayout(null);
		
		JButton btnInicioSesion = new JButton("         Iniciar sesi\u00F3n");
		btnInicioSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InicioSesion iniSesion = new InicioSesion();
				iniSesion.setVisible(true);
			}
		});
		btnInicioSesion.setIcon(new ImageIcon(Inicio.class.getResource("/media/imgInicioSesion64px.png")));
		btnInicioSesion.setBackground(Color.WHITE);
		btnInicioSesion.setBounds(28, 27, 253, 70);
		panelIniciarSesion.add(btnInicioSesion);
	}
}
