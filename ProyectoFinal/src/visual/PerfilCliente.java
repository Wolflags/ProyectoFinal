package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Altice;
import logico.Cliente;
import logico.Empleado;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PerfilCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Cliente auxCliente = null;
	private Empleado auxEmpleado = null;
	private JTextField txtNombres;
	private JTextField txtCedula;
	private JTextField txtApellidos;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JButton btnAplicar;
	private JButton btnVerFacturas;
	
	public PerfilCliente(Cliente cliente, Empleado empleado) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PerfilCliente.class.getResource("/media/imgCliente64px.png")));
		auxCliente = cliente;
		auxEmpleado = empleado;
		setTitle("Perfil de " + auxCliente.getNombre() + " " + auxCliente.getApellido());
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 911, 407);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panelModificar = new JPanel();
		panelModificar.setLayout(null);
		panelModificar.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelModificar.setBounds(10, 11, 197, 311);
		contentPanel.add(panelModificar);
		
		JLabel labelImagenCliente = new JLabel("");
		labelImagenCliente.setIcon(new ImageIcon(PerfilCliente.class.getResource("/media/imgCliente128px.png")));
		labelImagenCliente.setHorizontalAlignment(SwingConstants.CENTER);
		labelImagenCliente.setBounds(32, 27, 133, 143);
		panelModificar.add(labelImagenCliente);
		
		JButton btnModificar = new JButton("     Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				celdasEditables(true);
				btnModificar.setEnabled(false);
				btnVerFacturas.setEnabled(false);
				btnAplicar.setEnabled(true);
			}
		});
		btnModificar.setIcon(new ImageIcon(PerfilCliente.class.getResource("/media/imgModificar32px.png")));
		btnModificar.setHorizontalAlignment(SwingConstants.LEADING);
		btnModificar.setBackground(Color.WHITE);
		btnModificar.setBounds(18, 198, 161, 50);
		panelModificar.add(btnModificar);
		if(auxEmpleado.getTipoEmpleado().equalsIgnoreCase("Empleado")) {
			btnModificar.setEnabled(false);
		}
		
		JPanel panelInformacionGeneral = new JPanel();
		panelInformacionGeneral.setLayout(null);
		panelInformacionGeneral.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n general", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelInformacionGeneral.setBounds(217, 11, 667, 120);
		contentPanel.add(panelInformacionGeneral);
		
		JLabel lblNombres = new JLabel("Nombres:");
		lblNombres.setBounds(10, 25, 79, 14);
		panelInformacionGeneral.add(lblNombres);
		
		txtNombres = new JTextField();
		txtNombres.setText(auxCliente.getNombre());
		txtNombres.setEditable(false);
		txtNombres.setColumns(10);
		txtNombres.setBounds(77, 22, 230, 20);
		panelInformacionGeneral.add(txtNombres);
		
		JLabel lblCedula = new JLabel("C\u00E9dula:");
		lblCedula.setBounds(10, 53, 79, 14);
		panelInformacionGeneral.add(lblCedula);
		
		txtCedula = new JTextField();
		txtCedula.setText(auxCliente.getCedula());
		txtCedula.setEditable(false);
		txtCedula.setColumns(10);
		txtCedula.setBounds(77, 50, 230, 20);
		panelInformacionGeneral.add(txtCedula);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(335, 25, 79, 14);
		panelInformacionGeneral.add(lblApellidos);
		
		txtApellidos = new JTextField();
		txtApellidos.setText(auxCliente.getApellido());
		txtApellidos.setEditable(false);
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(402, 22, 230, 20);
		panelInformacionGeneral.add(txtApellidos);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setBounds(10, 81, 79, 14);
		panelInformacionGeneral.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setText(auxCliente.getDireccion());
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(77, 78, 230, 20);
		panelInformacionGeneral.add(txtDireccion);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setBounds(335, 53, 79, 14);
		panelInformacionGeneral.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setText(auxCliente.getTelefono());
		txtTelefono.setEditable(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(402, 50, 230, 20);
		panelInformacionGeneral.add(txtTelefono);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(217, 142, 667, 180);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblCantPlanesComprados = new JLabel("Cantidad de planes comprados:");
		lblCantPlanesComprados.setBounds(67, 47, 183, 14);
		panel.add(lblCantPlanesComprados);
		
		JLabel lblValorCantPlanesComprados = new JLabel(Integer.toString(auxCliente.getMisPlanes().size()));
		lblValorCantPlanesComprados.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValorCantPlanesComprados.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorCantPlanesComprados.setBounds(260, 47, 99, 14);
		panel.add(lblValorCantPlanesComprados);
		
		JLabel lblCantDineroDebe = new JLabel("Dinero pendiente por pagar:");
		lblCantDineroDebe.setBounds(67, 86, 183, 14);
		panel.add(lblCantDineroDebe);
		
		JLabel lblValorCantDineroDebe = new JLabel(Float.toString(Altice.getInstance().dineroClientePendiente(auxCliente.getCedula())));
		lblValorCantDineroDebe.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValorCantDineroDebe.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorCantDineroDebe.setBounds(260, 86, 99, 14);
		panel.add(lblValorCantDineroDebe);
		
		btnVerFacturas = new JButton("     Ver facturas");
		btnVerFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListadoFacturas listFacturas = new ListadoFacturas(auxEmpleado, auxCliente);
				listFacturas.setVisible(true);
			}
		});
		btnVerFacturas.setIcon(new ImageIcon(PerfilCliente.class.getResource("/media/imgListadoFacturas32pxB.png")));
		btnVerFacturas.setBackground(Color.WHITE);
		btnVerFacturas.setBounds(448, 61, 170, 57);
		panel.add(btnVerFacturas);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAplicar = new JButton("Aplicar");
				btnAplicar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(isCompleted()) {
							realizarCambios();
							celdasEditables(false);
							btnModificar.setEnabled(true);
							btnVerFacturas.setEnabled(true);
							btnAplicar.setEnabled(false);
							JOptionPane.showMessageDialog(null, "Cambios realizados exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Debe rellenar todos los espacios en blanco", "Advertencia", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				btnAplicar.setEnabled(false);
				btnAplicar.setBackground(Color.WHITE);
				btnAplicar.setActionCommand("OK");
				buttonPane.add(btnAplicar);
				getRootPane().setDefaultButton(btnAplicar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setBackground(Color.WHITE);
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
	private void celdasEditables(boolean editable) {
		txtNombres.setEditable(editable);
		txtApellidos.setEditable(editable);
		txtDireccion.setEditable(editable);
		txtTelefono.setEditable(editable);
	}
	
	private void realizarCambios() {
		auxCliente.setNombre(txtNombres.getText().toString());
		auxCliente.setApellido(txtApellidos.getText().toString());
		auxCliente.setDireccion(txtDireccion.getText().toString());
		auxCliente.setTelefono(txtTelefono.getText().toString());
	}
	
	private boolean isCompleted() {
		boolean completo = false;
		if(!txtNombres.getText().equals("") && !txtApellidos.getText().equals("") && !txtDireccion.getText().equals("")
				&& !txtTelefono.getText().equals("")) {
			completo = true;
		}
		return completo;
	}
}
