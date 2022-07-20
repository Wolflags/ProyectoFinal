package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
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
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;

public class RegEmpAdmin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static SpinnerNumberModel spnModel;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtPuestoTrabajo;

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
	public RegEmpAdmin() {
		setTitle("Registrar Empleado");
		setBounds(100, 100, 451, 531);
		getContentPane().setLayout(new BorderLayout());
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
		
		txtCedula = new JTextField();
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
		
		JComboBox cbxMes = new JComboBox();
		cbxMes.setModel(new DefaultComboBoxModel(new String[] {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"}));
		cbxMes.setBounds(267, 50, 50, 22);
		panel_Personales.add(cbxMes);
		
		JSpinner spnDia = new JSpinner();
		spnDia.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		spnDia.setBounds(214, 50, 41, 22);
		panel_Personales.add(spnDia);
		
		JSpinner spnYear = new JSpinner();
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
		
		JSpinner spnExpLaboral = new JSpinner();
		spnExpLaboral.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spnExpLaboral.setBounds(277, 180, 107, 22);
		panel_Personales.add(spnExpLaboral);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(12, 220, 55, 16);
		panel_Personales.add(lblTelfono);
		
		JLabel lblEstadoCivil = new JLabel("Estado Civil:");
		lblEstadoCivil.setBounds(198, 220, 70, 16);
		panel_Personales.add(lblEstadoCivil);
		
		JLabel lblCantHijos = new JLabel("Cant. Hijos:");
		lblCantHijos.setBounds(314, 220, 70, 16);
		panel_Personales.add(lblCantHijos);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(12, 245, 170, 22);
		panel_Personales.add(txtTelefono);
		
		JSpinner spnCantHijos = new JSpinner();
		spnCantHijos.setBounds(314, 245, 70, 22);
		panel_Personales.add(spnCantHijos);
		
		JComboBox cbxEstCivil = new JComboBox();
		cbxEstCivil.setModel(new DefaultComboBoxModel(new String[] {"Soltero/a", "Casado/a", "Divorciado/a"}));
		cbxEstCivil.setBounds(198, 245, 98, 22);
		panel_Personales.add(cbxEstCivil);
		
		JPanel panel_Laborales = new JPanel();
		panel_Laborales.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos Laborales", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Laborales.setBounds(12, 318, 409, 112);
		contentPanel.add(panel_Laborales);
		panel_Laborales.setLayout(null);
		
		JLabel lblSalarioMensual = new JLabel("Salario Mensual:");
		lblSalarioMensual.setBounds(12, 25, 96, 16);
		panel_Laborales.add(lblSalarioMensual);
		
		JSpinner spnSalario = new JSpinner();
		spnSalario.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnSalario.setBounds(12, 50, 170, 22);
		panel_Laborales.add(spnSalario);
		
		JLabel lblPuestoDeTrabajo = new JLabel("Puesto De Trabajo:");
		lblPuestoDeTrabajo.setBounds(211, 25, 111, 16);
		panel_Laborales.add(lblPuestoDeTrabajo);
		
		txtPuestoTrabajo = new JTextField();
		txtPuestoTrabajo.setColumns(10);
		txtPuestoTrabajo.setBounds(211, 50, 170, 22);
		panel_Laborales.add(txtPuestoTrabajo);
		spnModel = new SpinnerNumberModel(1, 1, 30, 1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
}