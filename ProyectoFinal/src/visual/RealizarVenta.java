package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import logico.Altice;
import logico.Cliente;
import logico.Plan;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;

public class RealizarVenta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Date fechaActual;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtPrecioTotal;
	private JButton btnAnnadirPlan;
	private JButton btnBuscar;
	private JTextField txtApellido;
	private JSpinner spnDia;
	private JComboBox cbxMes;
	private JSpinner spnYear;
	private static SpinnerNumberModel spnDiaModel;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private JScrollPane spPlanes;
	
	public static ArrayList<Plan> carrito =  new ArrayList<Plan>();
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RealizarVenta dialog = new RealizarVenta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RealizarVenta() {
		fechaActual = new Date();
		setResizable(false);
		setModal(true);
		setTitle("Realizar Venta");
		setBounds(100, 100, 623, 570);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel_InfoCliente = new JPanel();
		panel_InfoCliente.setBorder(new TitledBorder(null, "Informaci\u00F3n del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_InfoCliente.setBounds(12, 13, 578, 192);
		contentPanel.add(panel_InfoCliente);
		panel_InfoCliente.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("C\u00E9dula:");
		lblNewLabel.setBounds(79, 30, 44, 16);
		panel_InfoCliente.add(lblNewLabel);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(135, 27, 170, 22);
		panel_InfoCliente.add(txtCedula);
		txtCedula.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCedula.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "Introduzca una c�dula.", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);
				}else {
					Cliente cliente = Altice.getInstance().buscarClienteByCedula(txtCedula.getText());
					if (cliente == null) {
						txtNombre.setEditable(true);
						txtApellido.setEditable(true);
						txtTelefono.setEditable(true);
						txtDireccion.setEditable(true);
						spnDia.setEnabled(true);
						spnYear.setEnabled(true);
						cbxMes.setEnabled(true);
						spPlanes.setEnabled(true);
						btnAnnadirPlan.setEnabled(true);
					}else {
						txtNombre.setText(cliente.getNombre());
						txtApellido.setText(cliente.getApellido());
						txtTelefono.setText(cliente.getTelefono());
						txtDireccion.setText(cliente.getDireccion());
						spnDia.setValue(cliente.getFechaNacimiento().getDate());
						cbxMes.setSelectedIndex(cliente.getFechaNacimiento().getMonth());
						spnYear.setValue(cliente.getFechaNacimiento().getYear()+1900);
						spPlanes.setEnabled(true);
						btnAnnadirPlan.setEnabled(true);
						txtNombre.setEditable(false);
						txtApellido.setEditable(false);
						txtTelefono.setEditable(false);
						txtDireccion.setEditable(false);
						spnDia.setEnabled(false);
						spnYear.setEnabled(false);
						cbxMes.setEnabled(false);
					}
				}
			}
		});
		btnBuscar.setBounds(336, 26, 97, 25);
		panel_InfoCliente.add(btnBuscar);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(76, 70, 50, 16);
		panel_InfoCliente.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(135, 67, 170, 22);
		panel_InfoCliente.add(txtNombre);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(325, 110, 108, 16);
		panel_InfoCliente.add(lblTelfono);
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(389, 107, 170, 22);
		panel_InfoCliente.add(txtTelefono);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(69, 150, 57, 16);
		panel_InfoCliente.add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(135, 147, 424, 22);
		panel_InfoCliente.add(txtDireccion);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(326, 70, 50, 16);
		panel_InfoCliente.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setColumns(10);
		txtApellido.setBounds(389, 67, 170, 22);
		panel_InfoCliente.add(txtApellido);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fec. de Nacimiento:");
		lblFechaDeNacimiento.setBounds(12, 110, 114, 16);
		panel_InfoCliente.add(lblFechaDeNacimiento);
		
		spnDia = new JSpinner();
		spnDia.setEnabled(false);
		spnDiaModel = new SpinnerNumberModel(fechaActual.getDate() ,1 ,31 ,1);
		spnDia.setModel(spnDiaModel);
		spnDia.setBounds(135, 107, 41, 22);
		panel_InfoCliente.add(spnDia);
		
		cbxMes = new JComboBox();
		cbxMes.setEnabled(false);
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
		cbxMes.setSelectedIndex(fechaActual.getMonth());
		cbxMes.setBounds(188, 107, 50, 22);
		panel_InfoCliente.add(cbxMes);
		
		spnYear = new JSpinner();
		spnYear.setEnabled(false);
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
		spnYear.setBounds(249, 107, 56, 22);
		panel_InfoCliente.add(spnYear);
		
		JPanel panel_SelPlanes = new JPanel();
		panel_SelPlanes.setBorder(new TitledBorder(null, "Selecci\u00F3n de Planes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_SelPlanes.setBounds(12, 214, 578, 261);
		contentPanel.add(panel_SelPlanes);
		panel_SelPlanes.setLayout(null);
		
		btnAnnadirPlan = new JButton("A\u00F1adir");
		btnAnnadirPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoPlanesModal lisMod = new ListadoPlanesModal();
				lisMod.setVisible(true);
				lisMod.setModal(true);
				
			}
		});
		btnAnnadirPlan.setBounds(10, 203, 89, 23);
		panel_SelPlanes.add(btnAnnadirPlan);
		
		JLabel lblNewLabel_1 = new JLabel("Precio Total:");
		lblNewLabel_1.setBounds(360, 206, 73, 16);
		panel_SelPlanes.add(lblNewLabel_1);
		
		txtPrecioTotal = new JTextField();
		txtPrecioTotal.setEnabled(false);
		txtPrecioTotal.setBounds(452, 203, 116, 22);
		panel_SelPlanes.add(txtPrecioTotal);
		txtPrecioTotal.setColumns(10);
		
		spPlanes = new JScrollPane();
		spPlanes.setBounds(10, 23, 558, 169);
		panel_SelPlanes.add(spPlanes);
		
		table = new JTable();
		model = new DefaultTableModel();
		String[] headers = {"C�digo","Nombre","Cantidad de servicios", "Precio"};
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		spPlanes.setViewportView(table);
		
		btnNewButton = new JButton("Remover");
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(109, 203, 89, 23);
		panel_SelPlanes.add(btnNewButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnFacturar = new JButton("Facturar");
				btnFacturar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				btnFacturar.setActionCommand("OK");
				buttonPane.add(btnFacturar);
				getRootPane().setDefaultButton(btnFacturar);
			}
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
			cargarPlanesSel();
		}
	}
	
	public static void cargarPlanesSel() {
		
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (Plan plan : carrito) {
			row[0]=plan.getIdplan();
			row[1]=plan.getNombre();
			int cantserv=0;
			if(plan.getMisServicios().get(0)!=null) {
				cantserv++;
			}
			if(plan.getMisServicios().get(1)!=null) {
				cantserv++;
			}
			if(plan.getMisServicios().get(2)!=null) {
				cantserv++;
			}
			row[2]=cantserv;
			row[3]=plan.getPrecio();
			model.addRow(row);
		}
		
	}

	private void clear() {
		txtCedula.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		spnDia.setValue(fechaActual.getDate());
		spnYear.setValue(fechaActual.getYear()+1900);
		cbxMes.setSelectedIndex(fechaActual.getMonth());
		txtPrecioTotal.setText("");
		spPlanes.setEnabled(false);
		btnAnnadirPlan.setEnabled(false);
		txtNombre.setEditable(false);
		txtApellido.setEditable(false);
		txtTelefono.setEditable(false);
		txtDireccion.setEditable(false);
		spnDia.setEnabled(false);
		spnYear.setEnabled(false);
		cbxMes.setEnabled(false);
	}
}
