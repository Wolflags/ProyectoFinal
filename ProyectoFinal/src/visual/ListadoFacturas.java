package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Altice;
import logico.Cliente;
import logico.Empleado;
import logico.Factura;
import logico.Internet;
import logico.Plan;
import logico.Servicio;
import logico.Television;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ListadoFacturas extends JDialog {

	private static DecimalFormat df = new DecimalFormat("#.##");
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedulaCliente;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private Factura selected = null;
	private Cliente auxCliente = null;
	private JButton btnVerFactura;
	private JTextField txtClienteEncontrado;
	private JButton btnVerCliente;
	private JButton btnMarcar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Date hoy = new Date();
			Servicio s1 = null;
			Servicio s2 = null;
			Servicio s3 = null;
			s1 = new Internet("S-1", "Desc1", 30, true, 50, 100, "Móvil");
			s2 = new Television("S-2", "Desc2", 15, false, 150, "Hogar");
			s3 = new Internet("S-3", "Desc3", 20, true, 100, 200, "Hogar");
			Altice.getInstance().getServicios().add(s1);
			Altice.getInstance().getServicios().add(s2);
			Altice.getInstance().getServicios().add(s3);
			ArrayList<Servicio> serviciosEjemplo = new ArrayList<Servicio>();
			serviciosEjemplo.add(null);
			serviciosEjemplo.add(null);
			serviciosEjemplo.add(null);
			serviciosEjemplo.set(0, s1);
			Cliente c1 = new Cliente("123", "Leonardo", "La Zurza II", "8299741202", "Tejada", hoy);
			Altice.getInstance().insertarPersona(c1);
			Empleado e1 = new Empleado("402", "Marlon", "La Zurza", "829", "1234", (float)50000, 0, "Soltero", 5, "Administrador", "Oficina 1", "Tejada", hoy);
			//serviciosEjemplo.set(2, s2);
			Plan p1 = new Plan("1234", "Ejemplo", serviciosEjemplo, (float)2300);
			Factura f1 = new Factura("F-1", hoy, (float)3400, e1, c1, p1);
			c1.getMisFacturas().add(f1);
			Altice.getInstance().getFacturas().add(f1);
			ListadoFacturas dialog = new ListadoFacturas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoFacturas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoFacturas.class.getResource("/media/imgListadoFacturas32px.png")));
		df.setRoundingMode(RoundingMode.CEILING);
		setTitle("Listado de facturas");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 750, 550);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelBuscar = new JPanel();
			panelBuscar.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelBuscar.setBounds(10, 11, 712, 129);
			contentPanel.add(panelBuscar);
			panelBuscar.setLayout(null);
			{
				JLabel lblCedulaCliente = new JLabel("C\u00E9dula de cliente:");
				lblCedulaCliente.setBounds(129, 31, 118, 14);
				panelBuscar.add(lblCedulaCliente);
			}
			{
				txtCedulaCliente = new JTextField();
				txtCedulaCliente.setBounds(257, 28, 200, 20);
				panelBuscar.add(txtCedulaCliente);
				txtCedulaCliente.setColumns(10);
			}
			{
				JButton btnBuscar = new JButton("Buscar");
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(Altice.getInstance().buscarClienteByCedula(txtCedulaCliente.getText().toString()) != null) {
							auxCliente = Altice.getInstance().buscarClienteByCedula(txtCedulaCliente.getText().toString());
							loadFacturas();
							txtClienteEncontrado.setText(auxCliente.getNombre() + " " + auxCliente.getApellido());
							btnVerCliente.setEnabled(true);
						}
						else {
							JOptionPane.showMessageDialog(null, "No existe ningún cliente con esa cédula.", "Advertencia", JOptionPane.WARNING_MESSAGE);
						}
						
					}
				});
				btnBuscar.setBackground(Color.WHITE);
				btnBuscar.setBounds(467, 27, 109, 23);
				panelBuscar.add(btnBuscar);
			}
			{
				JLabel lblClienteEncontrado = new JLabel("Cliente encontrado:");
				lblClienteEncontrado.setBounds(129, 73, 127, 14);
				panelBuscar.add(lblClienteEncontrado);
			}
			{
				txtClienteEncontrado = new JTextField();
				txtClienteEncontrado.setEditable(false);
				txtClienteEncontrado.setBounds(257, 70, 200, 20);
				panelBuscar.add(txtClienteEncontrado);
				txtClienteEncontrado.setColumns(10);
			}
			{
				btnVerCliente = new JButton("Ver cliente");
				btnVerCliente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						PerfilCliente perCliente = new PerfilCliente(auxCliente);
						perCliente.setVisible(true);
					}
				});
				btnVerCliente.setEnabled(false);
				btnVerCliente.setBackground(Color.WHITE);
				btnVerCliente.setBounds(467, 69, 109, 23);
				panelBuscar.add(btnVerCliente);
			}
		}
		{
			JPanel panelTabla = new JPanel();
			panelTabla.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelTabla.setBounds(10, 162, 712, 302);
			contentPanel.add(panelTabla);
			panelTabla.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panelTabla.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int index = table.getSelectedRow();
							if(index >= 0) {
								String codFactura = table.getValueAt(index, 0).toString();
								selected = Altice.getInstance().buscarFacturaByCodigo(codFactura);
								btnVerFactura.setEnabled(true);
								btnMarcar.setEnabled(true);
								if(selected != null) {
									if(selected.isEstado() == true) {
										btnMarcar.setText("Marcar no pagada");
									}
									else {
										btnMarcar.setText("Marcar pagada");
									}
								}
							}
						}
					});
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					model = new DefaultTableModel();
					String[] headers = {"Código", "Plan", "Empleado", "Subtotal", "Fecha", "Estado"};
					model.setColumnIdentifiers(headers);
					table.setModel(model);
					scrollPane.setViewportView(table);
				}
			}
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnMarcar = new JButton("Marcar pagada");
				btnMarcar.setBackground(Color.WHITE);
				btnMarcar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(selected.isEstado() == true) {
							selected.setEstado(false);
							btnMarcar.setText("Marcar pagada");
						}
						else {
							selected.setEstado(true);
							btnMarcar.setText("Marcar no pagada");
						}
						loadFacturas();
					}
				});
				btnMarcar.setEnabled(false);
				buttonPane.add(btnMarcar);
			}
			{
				btnVerFactura = new JButton("Ver factura");
				btnVerFactura.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DetallesFactura detFactura = new DetallesFactura(selected);
						detFactura.setVisible(true);
					}
				});
				btnVerFactura.setEnabled(false);
				btnVerFactura.setBackground(Color.WHITE);
				btnVerFactura.setActionCommand("OK");
				buttonPane.add(btnVerFactura);
				getRootPane().setDefaultButton(btnVerFactura);
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
	private void loadFacturas() {
		btnMarcar.setEnabled(false);
		btnVerFactura.setEnabled(false);
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Factura factura : auxCliente.getMisFacturas()) {
			row[0] = factura.getCodigo().toString();
			row[1] = factura.getPlan().getNombre().toString();
			row[2] = factura.getEmpleado().getNombre() + " " +factura.getEmpleado().getApellido();
			row[3] = df.format(factura.getSubtotal());
			row[4] = factura.getFecha().getDay() + "/" + (factura.getFecha().getMonth() + 1) + "/" + (factura.getFecha().getYear() + 1900);
			if(factura.isEstado()) {
				row[5] = "Pagada";
			}
			else {
				row[5] = "No pagada";
			}
			model.addRow(row);
		}
	}
}
