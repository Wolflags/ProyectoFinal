package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class RealizarVenta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombreComp;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTable table;
	private JTextField txtPrecioTotal;
	private JButton btnAnnadirPlan;
	private JButton btnBuscar;
	private JScrollPane spPlanes;

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
		setResizable(false);
		setModal(true);
		setTitle("Realizar Venta");
		setBounds(100, 100, 623, 530);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel_InfoCliente = new JPanel();
		panel_InfoCliente.setBorder(new TitledBorder(null, "Informaci\u00F3n del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_InfoCliente.setBounds(12, 13, 578, 149);
		contentPanel.add(panel_InfoCliente);
		panel_InfoCliente.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("C\u00E9dula:");
		lblNewLabel.setBounds(76, 30, 44, 16);
		panel_InfoCliente.add(lblNewLabel);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(135, 27, 170, 22);
		panel_InfoCliente.add(txtCedula);
		txtCedula.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(336, 26, 97, 25);
		panel_InfoCliente.add(btnBuscar);
		
		JLabel lblNombreCompleto = new JLabel("Nombre Completo:");
		lblNombreCompleto.setBounds(12, 70, 108, 16);
		panel_InfoCliente.add(lblNombreCompleto);
		
		txtNombreComp = new JTextField();
		txtNombreComp.setEditable(false);
		txtNombreComp.setColumns(10);
		txtNombreComp.setBounds(135, 67, 170, 22);
		panel_InfoCliente.add(txtNombreComp);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(325, 70, 108, 16);
		panel_InfoCliente.add(lblTelfono);
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(389, 64, 170, 22);
		panel_InfoCliente.add(txtTelefono);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(63, 110, 57, 16);
		panel_InfoCliente.add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(135, 107, 424, 22);
		panel_InfoCliente.add(txtDireccion);
		
		JPanel panel_SelPlanes = new JPanel();
		panel_SelPlanes.setBorder(new TitledBorder(null, "Selecci\u00F3n de Planes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_SelPlanes.setBounds(12, 175, 578, 261);
		contentPanel.add(panel_SelPlanes);
		panel_SelPlanes.setLayout(null);
		
		spPlanes = new JScrollPane();
		spPlanes.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spPlanes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spPlanes.setBounds(70, 37, 426, 153);
		panel_SelPlanes.add(spPlanes);
		
		table = new JTable();
		spPlanes.setViewportView(table);
		
		btnAnnadirPlan = new JButton("A\u00F1adir Planes");
		btnAnnadirPlan.setEnabled(false);
		btnAnnadirPlan.setBounds(80, 203, 111, 25);
		panel_SelPlanes.add(btnAnnadirPlan);
		
		JLabel lblNewLabel_1 = new JLabel("Precio Total:");
		lblNewLabel_1.setBounds(282, 207, 73, 16);
		panel_SelPlanes.add(lblNewLabel_1);
		
		txtPrecioTotal = new JTextField();
		txtPrecioTotal.setEnabled(false);
		txtPrecioTotal.setBounds(367, 204, 116, 22);
		panel_SelPlanes.add(txtPrecioTotal);
		txtPrecioTotal.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnFacturar = new JButton("Facturar");
				btnFacturar.setActionCommand("OK");
				buttonPane.add(btnFacturar);
				getRootPane().setDefaultButton(btnFacturar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
}
