package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.event.ChangeListener;
import javax.swing.text.MaskFormatter;

import logico.Altice;
import logico.Empleado;
import logico.Persona;

import javax.swing.event.ChangeEvent;
import java.awt.Toolkit;

public class RegEmpAdmin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static SpinnerNumberModel spnDiaModel;
	@SuppressWarnings("deprecation")
	private Date fechaActual;
	private JFormattedTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDireccion;
	private JFormattedTextField txtTelefono;
	private JTextField txtPuestoTrabajo;
	private JSpinner spnDia;
	private JComboBox cbxMes;
	private JSpinner spnYear;
	private JSpinner spnExpLaboral;
	private JSpinner spnCantHijos;
	private JComboBox cbxEstCivil;
	private JSpinner spnSalario;
	private JRadioButton rbAdministrador;
	private JRadioButton rbEmpleado;
	private JTextField txtUsuario;
	private JTextField txtContraseña;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegEmpAdmin dialog = new RegEmpAdmin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("deprecation")
	public RegEmpAdmin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegEmpAdmin.class.getResource("/media/imgAgregarEmpleado32px.png")));
		fechaActual = new Date();
		setTitle("Registrar Empleado");
		setBounds(100, 100, 451, 694);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel_Personales = new JPanel();
		panel_Personales.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos Personales", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Personales.setBounds(12, 13, 409, 292);
		contentPanel.add(panel_Personales);
		panel_Personales.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("C\u00E9dula:");
		lblNewLabel.setBounds(12, 25, 56, 16);
		panel_Personales.add(lblNewLabel);
		MaskFormatter formatterced = null;
		
		try {
			formatterced = new MaskFormatter("###-#######-#");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		txtCedula = new JFormattedTextField(formatterced);
		txtCedula.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtUsuario.setText(txtCedula.getText());
			}
		});
		
		txtCedula.setBounds(12, 50, 170, 22);
		panel_Personales.add(txtCedula);
		txtCedula.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 90, 56, 16);
		panel_Personales.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(12, 115, 170, 22);
		panel_Personales.add(txtNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(214, 90, 56, 16);
		panel_Personales.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(214, 115, 170, 22);
		panel_Personales.add(txtApellido);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha De Nacimiento:");
		lblFechaDeNacimiento.setBounds(214, 25, 125, 16);
		panel_Personales.add(lblFechaDeNacimiento);
		
		cbxMes = new JComboBox();
		cbxMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Meses de 30
				if (cbxMes.getSelectedIndex() == 3 || cbxMes.getSelectedIndex() == 5 || cbxMes.getSelectedIndex() == 8 || cbxMes.getSelectedIndex() == 10) {
					if (Integer.parseInt(spnDia.getValue().toString()) > 30)
						spnDia.setValue(30);
					spnDiaModel.setMaximum(30);
				}
				//Meses de 31
				else if (cbxMes.getSelectedIndex() == 0 || cbxMes.getSelectedIndex() == 2 || cbxMes.getSelectedIndex() == 4 || cbxMes.getSelectedIndex() == 6 || cbxMes.getSelectedIndex() == 7 || cbxMes.getSelectedIndex() == 9 || cbxMes.getSelectedIndex() == 11) {
					if (Integer.parseInt(spnDia.getValue().toString()) > 31)
						spnDia.setValue(31);
					spnDiaModel.setMaximum(31);
				}
				//Febrero
				else if (cbxMes.getSelectedIndex() == 1) {
					//Bisiesto
					if ((Integer.parseInt(spnYear.getValue().toString()) % 4 == 0) && ((Integer.parseInt(spnYear.getValue().toString()) % 100 != 0) || (Integer.parseInt(spnYear.getValue().toString()) % 400 == 0))) {
						if (Integer.parseInt(spnDia.getValue().toString()) > 29)
							spnDia.setValue(29);
						spnDiaModel.setMaximum(29);
					}else {//No bisiesto
						if (Integer.parseInt(spnDia.getValue().toString()) > 28)
							spnDia.setValue(28);
						spnDiaModel.setMaximum(28);
					}
				}
				
			}
		});
		cbxMes.setModel(new DefaultComboBoxModel(new String[] {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"}));
		cbxMes.setBounds(267, 50, 50, 22);
		panel_Personales.add(cbxMes);
		
		spnDia = new JSpinner();
		spnDiaModel = new SpinnerNumberModel(1 ,1 ,31 ,1);
		spnDia.setModel(spnDiaModel);
		spnDia.setBounds(214, 50, 41, 22);
		spnDia.setValue(fechaActual.getDate());
		panel_Personales.add(spnDia);
		
		spnYear = new JSpinner();
		spnYear.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//Febrero
				if (cbxMes.getSelectedIndex() == 1) {
					//Bisiesto
					if ((Integer.parseInt(spnYear.getValue().toString()) % 4 == 0) && ((Integer.parseInt(spnYear.getValue().toString()) % 100 != 0) || (Integer.parseInt(spnYear.getValue().toString()) % 400 == 0))) {
						if (Integer.parseInt(spnDia.getValue().toString()) > 29)
							spnDia.setValue(29);
						spnDiaModel.setMaximum(29);
					}else {//No bisiesto
						if (Integer.parseInt(spnDia.getValue().toString()) > 28)
							spnDia.setValue(28);
						spnDiaModel.setMaximum(28);
					}
				}
			}
		});
		spnYear.setModel(new SpinnerNumberModel(2022, 1900, 2022, 1));
		spnYear.setBounds(329, 50, 56, 22);
		panel_Personales.add(spnYear);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(12, 155, 80, 16);
		panel_Personales.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(12, 180, 243, 22);
		panel_Personales.add(txtDireccion);
		
		JLabel lblAosDeExperiencia = new JLabel("A\u00F1os Exp. Laboral:");
		lblAosDeExperiencia.setBounds(277, 155, 107, 16);
		panel_Personales.add(lblAosDeExperiencia);
		
		spnExpLaboral = new JSpinner();
		spnExpLaboral.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spnExpLaboral.setBounds(277, 180, 107, 22);
		panel_Personales.add(spnExpLaboral);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(12, 220, 55, 16);
		panel_Personales.add(lblTelfono);
		
		JLabel lblEstadoCivil = new JLabel("Estado Civil:");
		lblEstadoCivil.setBounds(164, 220, 70, 16);
		panel_Personales.add(lblEstadoCivil);
		
		JLabel lblCantHijos = new JLabel("Cant. Hijos:");
		lblCantHijos.setBounds(314, 220, 70, 16);
		panel_Personales.add(lblCantHijos);
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter("(###) ###-####");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        txtTelefono = new JFormattedTextField(formatter);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(12, 245, 125, 22);
		panel_Personales.add(txtTelefono);
		
		spnCantHijos = new JSpinner();
		spnCantHijos.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spnCantHijos.setBounds(314, 245, 70, 22);
		panel_Personales.add(spnCantHijos);
		
		cbxEstCivil = new JComboBox();
		cbxEstCivil.setModel(new DefaultComboBoxModel(new String[] {"Soltero/a", "Casado/a", "Divorciado/a"}));
		cbxEstCivil.setBounds(164, 245, 132, 22);
		panel_Personales.add(cbxEstCivil);
		
		JPanel panel_Laborales = new JPanel();
		panel_Laborales.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos Laborales", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Laborales.setBounds(12, 318, 409, 95);
		contentPanel.add(panel_Laborales);
		panel_Laborales.setLayout(null);
		
		JLabel lblSalarioMensual = new JLabel("Salario Mensual:");
		lblSalarioMensual.setBounds(12, 25, 96, 16);
		panel_Laborales.add(lblSalarioMensual);
		
		spnSalario = new JSpinner();
		spnSalario.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnSalario.setBounds(12, 50, 170, 22);
		panel_Laborales.add(spnSalario);
		
		JLabel lblPuestoDeTrabajo = new JLabel("Puesto De Trabajo:");
		lblPuestoDeTrabajo.setBounds(214, 25, 111, 16);
		panel_Laborales.add(lblPuestoDeTrabajo);
		
		txtPuestoTrabajo = new JTextField();
		txtPuestoTrabajo.setColumns(10);
		txtPuestoTrabajo.setBounds(214, 50, 170, 22);
		panel_Laborales.add(txtPuestoTrabajo);
		
		JPanel panel_Tipo = new JPanel();
		panel_Tipo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Tipo.setBounds(12, 426, 409, 69);
		contentPanel.add(panel_Tipo);
		panel_Tipo.setLayout(null);
		
		rbAdministrador = new JRadioButton("Administrador\r\n");
		rbAdministrador.setSelected(true);
		rbAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbAdministrador.isSelected()) {
					rbEmpleado.setSelected(false);
				}
			}
		});
		rbAdministrador.setBounds(51, 24, 127, 25);
		panel_Tipo.add(rbAdministrador);
		
		rbEmpleado = new JRadioButton("Empleado");
		rbEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbEmpleado.isSelected()) {
					rbAdministrador.setSelected(false);
				}
			}
		});
		rbEmpleado.setBounds(229, 24, 127, 25);
		panel_Tipo.add(rbEmpleado);
		
		JPanel panel_Plataforma = new JPanel();
		panel_Plataforma.setBorder(new TitledBorder(null, "Datos De Plataforma", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Plataforma.setBounds(12, 508, 409, 91);
		contentPanel.add(panel_Plataforma);
		panel_Plataforma.setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre De Usuario:");
		lblNombreDeUsuario.setBounds(12, 25, 116, 16);
		panel_Plataforma.add(lblNombreDeUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setEditable(false);
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(12, 50, 170, 22);
		panel_Plataforma.add(txtUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(214, 25, 116, 16);
		panel_Plataforma.add(lblContrasea);
		
		txtContraseña = new JTextField();
		txtContraseña.setColumns(10);
		txtContraseña.setBounds(214, 50, 170, 22);
		panel_Plataforma.add(txtContraseña);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.setBackground(Color.WHITE);
				btnRegistrar.addActionListener(new ActionListener() {
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent e) {
						Date fechaNacimiento = new Date();
						fechaNacimiento.setDate(Integer.parseInt(spnDia.getValue().toString()));
						fechaNacimiento.setMonth(cbxMes.getSelectedIndex());
						fechaNacimiento.setYear(Integer.parseInt(spnYear.getValue().toString())-1900);
						if (validarDatos(fechaNacimiento)) {
							if(rbAdministrador.isSelected()) {
								Date fecNac = new Date(1, 1, 1);
								fecNac.setDate(Integer.parseInt(spnDia.getValue().toString()));
								fecNac.setMonth(cbxMes.getSelectedIndex());
								fecNac.setYear(Integer.parseInt(spnYear.getValue().toString())-1900);
								Persona empleado = new Empleado(txtCedula.getText(), txtNombre.getText(), txtDireccion.getText(), txtTelefono.getText(), txtContraseña.getText(), Float.parseFloat(spnSalario.getValue().toString()), 
										Integer.parseInt(spnCantHijos.getValue().toString()), cbxEstCivil.getSelectedItem().toString(), Integer.parseInt(spnExpLaboral.getValue().toString()), 
										"Administrador", txtPuestoTrabajo.getText(), txtApellido.getText(), fecNac);
								Altice.getInstance().insertarPersona(empleado);
								clean();
								JOptionPane.showMessageDialog(null, "Registro existoso.", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
							}else {
								Date fecNac = new Date(1, 1, 1);
								fecNac.setDate(Integer.parseInt(spnDia.getValue().toString()));
								fecNac.setMonth(cbxMes.getSelectedIndex());
								fecNac.setYear(Integer.parseInt(spnYear.getValue().toString())-1900);
								Persona empleado = new Empleado(txtCedula.getText(), txtNombre.getText(), txtDireccion.getText(), txtTelefono.getText(), txtContraseña.getText(), Float.parseFloat(spnSalario.getValue().toString()), 
										Integer.parseInt(spnCantHijos.getValue().toString()), cbxEstCivil.getSelectedItem().toString(), Integer.parseInt(spnExpLaboral.getValue().toString()), 
										"Empleado", txtPuestoTrabajo.getText(), txtApellido.getText(), fecNac);
								Altice.getInstance().insertarPersona(empleado);
								clean();
								JOptionPane.showMessageDialog(null, "Registro existoso.", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setBackground(Color.WHITE);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		cbxMes.setSelectedIndex(fechaActual.getMonth());
	}
	
	private boolean validarDatos(Date fechaNacimiento) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dia = String.format("%02d", fechaNacimiento.getDate());
		String mes = String.format("%02d", fechaNacimiento.getMonth()+1);
		String year = String.format("%04d", fechaNacimiento.getYear()+1900);
		
		LocalDate fechaNac = LocalDate.parse(dia+"/"+mes+"/"+year, fmt);
		LocalDate ahora = LocalDate.now();
		
		Period periodo = Period.between(fechaNac, ahora);
		
		if (txtCedula.getText().equalsIgnoreCase("") || txtNombre.getText().equalsIgnoreCase("") || txtApellido.getText().equalsIgnoreCase("") || txtDireccion.getText().equalsIgnoreCase("") || txtTelefono.getText().equalsIgnoreCase("") || txtContraseña.getText().equalsIgnoreCase("") || txtPuestoTrabajo.getText().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Debe rellenar todos los espacios en blanco.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if (Altice.getInstance().buscarEmpleadoByCedula(txtCedula.getText()) != null) {
			JOptionPane.showMessageDialog(null, "¡Cédula ya existente!", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if (Altice.getInstance().buscarDireccionExistente(txtDireccion.getText())) {
			JOptionPane.showMessageDialog(null, "¡Dirección ya existente!", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if (Altice.getInstance().buscarTelefonoExistente(txtTelefono.getText())) {
			JOptionPane.showMessageDialog(null, "¡Teléfono ya existente!", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if (periodo.getYears() < 18) {
			JOptionPane.showMessageDialog(null, "¡Empleado debe ser mayor de edad!", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
			
		
		
		return true;
	}
	private void clean() {
		txtCedula.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		cbxMes.setSelectedIndex(fechaActual.getMonth());
		spnDia.setValue(fechaActual.getDate());
		spnYear.setValue(fechaActual.getYear() + 1900);
		txtDireccion.setText("");
		txtTelefono.setText("");
		spnExpLaboral.setValue(0);
		spnCantHijos.setValue(0);
		cbxEstCivil.setSelectedIndex(0);
		spnSalario.setValue(0.0);
		txtPuestoTrabajo.setText("");
		rbAdministrador.setSelected(true);
		rbEmpleado.setSelected(false);
		txtUsuario.setText("");
		txtContraseña.setText("");
	}
}
