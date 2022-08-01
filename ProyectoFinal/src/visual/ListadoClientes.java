package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Altice;
import logico.Cliente;
import logico.Empleado;
import logico.Persona;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ListadoClientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private Cliente selected = null;
	private Cliente auxCliente = null;
	private JButton btnVerCliente;
	private JTextField txtNombre;
	private ArrayList<Persona> clientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoClientes dialog = new ListadoClientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoClientes() {
		clientes = new ArrayList<Persona>();
		setResizable(false);
		setTitle("Listado de clientes");
		setModal(true);
		setBounds(100, 100, 727, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(12, 13, 688, 392);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(12, 42, 664, 337);
				panel.add(scrollPane);
				{
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int index = table.getSelectedRow();
							if(index >= 0) {
								String cedula = table.getValueAt(index, 0).toString();
								selected = Altice.getInstance().buscarClienteByCedula(cedula);
								btnVerCliente.setEnabled(true);
							}
						}
					});
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					model = new DefaultTableModel();
					String[] headers = {"Cédula", "Nombre", "Cantidad de planes"};
					model.setColumnIdentifiers(headers);
					table.setModel(model);
					scrollPane.setViewportView(table);
				}
			}
			
			JLabel lblNewLabel = new JLabel("Introduzca un nombre:");
			lblNewLabel.setBounds(24, 13, 137, 16);
			panel.add(lblNewLabel);
			
			txtNombre = new JTextField();
			txtNombre.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if(txtNombre.getText().equalsIgnoreCase("")) {
						initArrayList(clientes);
						loadClientes(clientes);
					}else {
						conditionalArrayList(clientes);
						loadClientes(clientes);
					}
				}
			});
			txtNombre.setBounds(160, 10, 174, 22);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnVerCliente = new JButton("Ver cliente");
				btnVerCliente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						PerfilCliente perCliente = new PerfilCliente(selected);
						perCliente.setVisible(true);
					}
				});
				btnVerCliente.setEnabled(false);
				btnVerCliente.setActionCommand("OK");
				buttonPane.add(btnVerCliente);
				getRootPane().setDefaultButton(btnVerCliente);
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
		}
		initArrayList(clientes);
		loadClientes(clientes);
	}
	private void conditionalArrayList(ArrayList<Persona> clientes) {
		String nombreCompleto = "";
		for (Persona cliente : Altice.getInstance().getPersonas()) {
			if (cliente instanceof Cliente) {
				nombreCompleto = cliente.getNombre()+" "+cliente.getApellido();
				if(nombreCompleto.substring(0, txtNombre.getText().length()).equalsIgnoreCase(txtNombre.getText())) {
					clientes.add(cliente);
				}
			}
		}
		
	}
	private void initArrayList (ArrayList<Persona> clientes) {
		for (Persona cliente : Altice.getInstance().getPersonas())
			if (cliente instanceof Cliente) 
				clientes.add(cliente);
		return;
	}
	public static void loadClientes(ArrayList<Persona> clientes) {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Persona cliente : clientes) {
			row[0] = cliente.getCedula();
			row[1] = ""+cliente.getNombre()+" "+cliente.getApellido();
			row[2] = ((((Cliente) cliente).getMisPlanes().size()));
			model.addRow(row);
		}
	}
}
