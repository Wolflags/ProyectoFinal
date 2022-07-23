package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import logico.Cliente;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PerfilCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombres;
	private JTextField txtCedula;
	private JTextField txtApellidos;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private Cliente auxCliente = null;

	public PerfilCliente(Cliente cliente) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PerfilCliente.class.getResource("/media/imgCliente64px.png")));
		auxCliente = cliente;
		setTitle("Perfil de cliente");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 692, 362);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelInfoGeneral = new JPanel();
			panelInfoGeneral.setBounds(5, 5, 667, 120);
			panelInfoGeneral.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n general", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panelInfoGeneral);
			panelInfoGeneral.setLayout(null);
			{
				JLabel lblNombres = new JLabel("Nombres:");
				lblNombres.setBounds(10, 25, 79, 14);
				panelInfoGeneral.add(lblNombres);
			}
			{
				txtNombres = new JTextField();
				txtNombres.setEditable(false);
				txtNombres.setBounds(77, 22, 230, 20);
				panelInfoGeneral.add(txtNombres);
				txtNombres.setColumns(10);
				txtNombres.setText(auxCliente.getNombre());
			}
			{
				JLabel lblCedula = new JLabel("C\u00E9dula:");
				lblCedula.setBounds(10, 53, 79, 14);
				panelInfoGeneral.add(lblCedula);
			}
			{
				txtCedula = new JTextField();
				txtCedula.setEditable(false);
				txtCedula.setColumns(10);
				txtCedula.setBounds(77, 50, 230, 20);
				panelInfoGeneral.add(txtCedula);
				txtCedula.setText(auxCliente.getCedula());
			}
			{
				JLabel lblApellidos = new JLabel("Apellidos:");
				lblApellidos.setBounds(335, 25, 79, 14);
				panelInfoGeneral.add(lblApellidos);
			}
			{
				txtApellidos = new JTextField();
				txtApellidos.setEditable(false);
				txtApellidos.setColumns(10);
				txtApellidos.setBounds(402, 22, 230, 20);
				panelInfoGeneral.add(txtApellidos);
				txtApellidos.setText(auxCliente.getApellido());
			}
			{
				JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
				lblDireccion.setBounds(10, 81, 79, 14);
				panelInfoGeneral.add(lblDireccion);
			}
			{
				txtDireccion = new JTextField();
				txtDireccion.setEditable(false);
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(77, 78, 230, 20);
				panelInfoGeneral.add(txtDireccion);
				txtDireccion.setText(auxCliente.getDireccion());
			}
			{
				JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
				lblTelefono.setBounds(335, 53, 79, 14);
				panelInfoGeneral.add(lblTelefono);
			}
			{
				txtTelefono = new JTextField();
				txtTelefono.setEditable(false);
				txtTelefono.setColumns(10);
				txtTelefono.setBounds(402, 50, 230, 20);
				panelInfoGeneral.add(txtTelefono);
				txtTelefono.setText(auxCliente.getTelefono());
			}
		}
		{
			JPanel panelBotones = new JPanel();
			panelBotones.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelBotones.setBounds(5, 136, 667, 120);
			contentPanel.add(panelBotones);
			panelBotones.setLayout(null);
			{
				JButton btnModificarCliente = new JButton("     Modificar cliente");
				btnModificarCliente.setHorizontalAlignment(SwingConstants.LEADING);
				btnModificarCliente.setIcon(new ImageIcon(PerfilCliente.class.getResource("/media/imgModificarCliente64px.png")));
				btnModificarCliente.setBounds(27, 21, 201, 76);
				panelBotones.add(btnModificarCliente);
			}
			{
				JButton btnVerPlanes = new JButton("     Ver planes");
				btnVerPlanes.setHorizontalAlignment(SwingConstants.LEADING);
				btnVerPlanes.setIcon(new ImageIcon(PerfilCliente.class.getResource("/media/imgVerPlanes64px.png")));
				btnVerPlanes.setBounds(238, 21, 201, 76);
				panelBotones.add(btnVerPlanes);
			}
			{
				JButton btnVerFacturas = new JButton("     Ver facturas");
				btnVerFacturas.setHorizontalAlignment(SwingConstants.LEADING);
				btnVerFacturas.setIcon(new ImageIcon(PerfilCliente.class.getResource("/media/imgFactura64px.png")));
				btnVerFacturas.setBounds(449, 21, 201, 76);
				panelBotones.add(btnVerFacturas);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}

}
