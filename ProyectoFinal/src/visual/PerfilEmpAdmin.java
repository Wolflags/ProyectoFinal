package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Altice;
import logico.Empleado;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;

public class PerfilEmpAdmin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Empleado auxEmpleado = null;
	private Empleado auxEmpleadoActual = null;
	private JTextField txtNombre;
	private JTextField txtCedula;
	private JTextField txtApellidos;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtUsuario;
	private JTextField txtPuesto;
	private JTextField txtClave;
	private JComboBox cbxTipo;
	private JComboBox cbxEstadoCivil;
	private JSpinner spnCantHijos;
	private JSpinner spnAnnosExperiencia;
	private JSpinner spnSalario;
	private JButton btnModificar;
	private JButton btnAplicar;
	private JComboBox cbxEstado;
	
	/*public static void main(String[] args) {
		try {
			PerfilEmpAdmin dialog = new PerfilEmpAdmin((Empleado)Altice.getInstance().getPersonas().get(0));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public PerfilEmpAdmin(Empleado empleado, Empleado empleadoActual) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PerfilEmpAdmin.class.getResource("/media/imgVerPerfil32px.png")));
		auxEmpleado = empleado;
		auxEmpleadoActual = empleadoActual;
		setTitle("Perfil de " + auxEmpleado.getTipoEmpleado());
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 911, 407);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelInfoGeneral = new JPanel();
			panelInfoGeneral.setLayout(null);
			panelInfoGeneral.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n general", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelInfoGeneral.setBounds(217, 11, 667, 120);
			contentPanel.add(panelInfoGeneral);
			{
				JLabel lblNombres = new JLabel("Nombres:");
				lblNombres.setBounds(10, 25, 79, 14);
				panelInfoGeneral.add(lblNombres);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setText(auxEmpleado.getNombre());
				txtNombre.setEditable(false);
				txtNombre.setColumns(10);
				txtNombre.setBounds(77, 22, 230, 20);
				panelInfoGeneral.add(txtNombre);
			}
			{
				JLabel lblCedula = new JLabel("C\u00E9dula:");
				lblCedula.setBounds(10, 53, 79, 14);
				panelInfoGeneral.add(lblCedula);
			}
			{
				txtCedula = new JTextField();
				txtCedula.setText(auxEmpleado.getCedula());
				txtCedula.setEditable(false);
				txtCedula.setColumns(10);
				txtCedula.setBounds(77, 50, 230, 20);
				panelInfoGeneral.add(txtCedula);
			}
			{
				JLabel lblApellidos = new JLabel("Apellidos:");
				lblApellidos.setBounds(335, 25, 79, 14);
				panelInfoGeneral.add(lblApellidos);
			}
			{
				txtApellidos = new JTextField();
				txtApellidos.setText(auxEmpleado.getApellido());
				txtApellidos.setEditable(false);
				txtApellidos.setColumns(10);
				txtApellidos.setBounds(402, 22, 230, 20);
				panelInfoGeneral.add(txtApellidos);
			}
			{
				JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
				lblDireccion.setBounds(10, 81, 79, 14);
				panelInfoGeneral.add(lblDireccion);
			}
			{
				txtDireccion = new JTextField();
				txtDireccion.setText(auxEmpleado.getDireccion());
				txtDireccion.setEditable(false);
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(77, 78, 230, 20);
				panelInfoGeneral.add(txtDireccion);
			}
			{
				JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
				lblTelefono.setBounds(335, 53, 79, 14);
				panelInfoGeneral.add(lblTelefono);
			}
			{
				txtTelefono = new JTextField();
				txtTelefono.setText(auxEmpleado.getTelefono());
				txtTelefono.setEditable(false);
				txtTelefono.setColumns(10);
				txtTelefono.setBounds(402, 50, 230, 20);
				panelInfoGeneral.add(txtTelefono);
			}
		}
		{
			JPanel panelInfoEmpleado = new JPanel();
			panelInfoEmpleado.setBorder(new TitledBorder(null, "Informaci\u00F3n de " + auxEmpleado.getTipoEmpleado().toLowerCase(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelInfoEmpleado.setBounds(217, 142, 667, 180);
			contentPanel.add(panelInfoEmpleado);
			panelInfoEmpleado.setLayout(null);
			
			JLabel lblUsuario = new JLabel("Usuario:");
			lblUsuario.setBounds(10, 24, 79, 14);
			panelInfoEmpleado.add(lblUsuario);
			
			txtUsuario = new JTextField();
			txtUsuario.setText(auxEmpleado.getCedula());
			txtUsuario.setEditable(false);
			txtUsuario.setColumns(10);
			txtUsuario.setBounds(77, 21, 230, 20);
			panelInfoEmpleado.add(txtUsuario);
			
			JLabel lblSalario = new JLabel("Salario:");
			lblSalario.setBounds(10, 52, 79, 14);
			panelInfoEmpleado.add(lblSalario);
			
			JLabel lblPuesto = new JLabel("Puesto:");
			lblPuesto.setBounds(10, 80, 79, 14);
			panelInfoEmpleado.add(lblPuesto);
			
			txtPuesto = new JTextField();
			txtPuesto.setText(auxEmpleado.getPuestoTrabajo());
			txtPuesto.setEditable(false);
			txtPuesto.setColumns(10);
			txtPuesto.setBounds(77, 77, 230, 20);
			panelInfoEmpleado.add(txtPuesto);
			
			spnSalario = new JSpinner();
			spnSalario.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(100)));
			spnSalario.setValue(auxEmpleado.getSalario());
			spnSalario.setEnabled(false);
			spnSalario.setBounds(77, 49, 230, 20);
			panelInfoEmpleado.add(spnSalario);
			
			JLabel lblClave = new JLabel("Clave:");
			lblClave.setBounds(335, 24, 79, 14);
			panelInfoEmpleado.add(lblClave);
			
			txtClave = new JTextField();
			txtClave.setText(auxEmpleado.getPassword());
			txtClave.setEditable(false);
			txtClave.setColumns(10);
			txtClave.setBounds(402, 21, 230, 20);
			panelInfoEmpleado.add(txtClave);
			
			JLabel lblEstado = new JLabel("Estado:");
			lblEstado.setBounds(335, 52, 79, 14);
			panelInfoEmpleado.add(lblEstado);
			
			JLabel lblTipo = new JLabel("Tipo:");
			lblTipo.setBounds(335, 80, 79, 14);
			panelInfoEmpleado.add(lblTipo);
			
			JLabel lblEstadoCivil = new JLabel("Estado civil:");
			lblEstadoCivil.setBounds(10, 108, 79, 14);
			panelInfoEmpleado.add(lblEstadoCivil);
			
			JLabel lblHijos = new JLabel("Cantidad de hijos:");
			lblHijos.setBounds(335, 108, 121, 14);
			panelInfoEmpleado.add(lblHijos);
			
			JLabel lblAnnosExperiencia = new JLabel("A\u00F1os de experiencia:");
			lblAnnosExperiencia.setBounds(10, 136, 153, 14);
			panelInfoEmpleado.add(lblAnnosExperiencia);
			
			cbxTipo = new JComboBox();
			cbxTipo.setEnabled(false);
			cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Empleado"}));
			cbxTipo.setBounds(402, 77, 230, 20);
			cbxTipo.setSelectedItem(auxEmpleado.getTipoEmpleado());
			panelInfoEmpleado.add(cbxTipo);
			
			cbxEstadoCivil = new JComboBox();
			cbxEstadoCivil.setEnabled(false);
			cbxEstadoCivil.setModel(new DefaultComboBoxModel(new String[] {"Casado", "Soltero", "Divorciado"}));
			cbxEstadoCivil.setBounds(99, 105, 208, 20);
			cbxEstadoCivil.setSelectedItem(auxEmpleado.getEstadoCivil());
			panelInfoEmpleado.add(cbxEstadoCivil);
			
			spnCantHijos = new JSpinner();
			spnCantHijos.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnCantHijos.setEnabled(false);
			spnCantHijos.setBounds(462, 105, 170, 20);
			spnCantHijos.setValue(auxEmpleado.getCantHijos());
			panelInfoEmpleado.add(spnCantHijos);
			
			spnAnnosExperiencia = new JSpinner();
			spnAnnosExperiencia.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnAnnosExperiencia.setEnabled(false);
			spnAnnosExperiencia.setBounds(148, 133, 159, 20);
			spnAnnosExperiencia.setValue(auxEmpleado.getAnnosExperiencia());
			panelInfoEmpleado.add(spnAnnosExperiencia);
			
			cbxEstado = new JComboBox();
			cbxEstado.setModel(new DefaultComboBoxModel(new String[] {"Contratado", "Cancelado"}));
			cbxEstado.setEnabled(false);
			cbxEstado.setBounds(402, 49, 230, 20);
			if(auxEmpleado.isEstado()) {
				cbxEstado.setSelectedItem("Contratado");
			}
			else {
				cbxEstado.setSelectedItem("Cancelado");
			}
			panelInfoEmpleado.add(cbxEstado);
		}
		
		JPanel panelModificar = new JPanel();
		panelModificar.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelModificar.setBounds(10, 11, 197, 311);
		contentPanel.add(panelModificar);
		panelModificar.setLayout(null);
		
		JLabel lblImagenLogo = new JLabel("");
		if(auxEmpleado.getTipoEmpleado().equalsIgnoreCase("Administrador")) {
			lblImagenLogo.setIcon(new ImageIcon(PerfilEmpAdmin.class.getResource("/media/imgAdmin128px.png")));
		}
		else {
			lblImagenLogo.setIcon(new ImageIcon(PerfilEmpAdmin.class.getResource("/media/imgCajero128px.png")));
		}
		lblImagenLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagenLogo.setBounds(32, 27, 133, 143);
		panelModificar.add(lblImagenLogo);
		
		btnModificar = new JButton("     Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				celdasEditables(true);
				btnModificar.setEnabled(false);
				btnAplicar.setEnabled(true);
			}
		});
		btnModificar.setBackground(Color.WHITE);
		btnModificar.setIcon(new ImageIcon(PerfilEmpAdmin.class.getResource("/media/imgModificar32px.png")));
		btnModificar.setHorizontalAlignment(SwingConstants.LEADING);
		btnModificar.setBounds(18, 198, 161, 50);
		panelModificar.add(btnModificar);
		if(auxEmpleadoActual.getTipoEmpleado().equalsIgnoreCase("Empleado")){
			btnModificar.setEnabled(false);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAplicar = new JButton("Aplicar");
				btnAplicar.setBackground(Color.WHITE);
				btnAplicar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(isCompleted()) {
							auxEmpleado.setNombre(txtNombre.getText().toString());
							auxEmpleado.setApellido(txtApellidos.getText().toString());
							auxEmpleado.setDireccion(txtDireccion.getText().toString());
							auxEmpleado.setTelefono(txtTelefono.getText().toString());
							auxEmpleado.setPuestoTrabajo(txtPuesto.getText().toString());
							auxEmpleado.setPassword(txtClave.getText().toString());
							auxEmpleado.setSalario(Float.parseFloat(spnSalario.getValue().toString()));
							if(cbxEstado.getSelectedItem().equals("Contratado")) {
								auxEmpleado.setEstado(true);
							}
							else {
								auxEmpleado.setEstado(false);
							}
							auxEmpleado.setTipoEmpleado(cbxTipo.getSelectedItem().toString());
							auxEmpleado.setEstadoCivil(cbxEstadoCivil.getSelectedItem().toString());
							auxEmpleado.setCantHijos(Integer.parseInt(spnCantHijos.getValue().toString()));
							auxEmpleado.setAnnosExperiencia(Integer.parseInt(spnAnnosExperiencia.getValue().toString()));
							celdasEditables(false);
							btnModificar.setEnabled(true);
							btnAplicar.setEnabled(false);
							JOptionPane.showMessageDialog(null, "Cambios realizados exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Debe rellenar todos los espacios en blanco", "Advertencia", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				btnAplicar.setEnabled(false);
				btnAplicar.setActionCommand("OK");
				buttonPane.add(btnAplicar);
				getRootPane().setDefaultButton(btnAplicar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setBackground(Color.WHITE);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
	
	private void celdasEditables(boolean editable) {
		txtNombre.setEditable(editable);
		txtApellidos.setEditable(editable);
		txtDireccion.setEditable(editable);
		txtTelefono.setEditable(editable);
		txtPuesto.setEditable(editable);
		txtClave.setEditable(editable);
		spnSalario.setEnabled(editable);
		cbxEstado.setEnabled(editable);
		cbxTipo.setEnabled(editable);
		cbxEstadoCivil.setEnabled(editable);
		spnCantHijos.setEnabled(editable);
		spnAnnosExperiencia.setEnabled(editable);
	}
	
	private boolean isCompleted() {
		boolean completo = false;
		if(!txtNombre.getText().equals("") && !txtApellidos.getText().equals("") && !txtDireccion.getText().equals("")
				&& !txtTelefono.getText().equals("") && !txtPuesto.getText().equals("") && !txtClave.getText().equals("")
				&& Float.parseFloat(spnSalario.getValue().toString()) != 0) {
			completo = true;
		}
		return completo;
	}
}
