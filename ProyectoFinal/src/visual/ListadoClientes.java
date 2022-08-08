package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Altice;
import logico.Empleado;
import logico.Persona;
import logico.Cliente;

import javax.swing.JRadioButton;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ListadoClientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTable tablePorNombre;
	private DefaultTableModel model;
	private Object[] row;
	private JPanel panelNombres;
	private Persona selected;
	private JButton btnSeleccionar;
	private ArrayList<Cliente> clientes;
	private Empleado auxEmpleado = null;
	private JTable tableTodos;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			ListadoEmpleados dialog = new ListadoEmpleados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ListadoClientes(Empleado empleado) {
		auxEmpleado = empleado;
		clientes = new ArrayList<Cliente>();
		initArrayList(clientes);
		setResizable(false);
		setTitle("Listado de clientes");
		setBounds(100, 100, 900, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		String[] headers = {"Cédula", "Nombres y Apellidos", "Planes contratados",};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(headers);
		
		tableTodos = new JTable();
		tableTodos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableTodos.getSelectedRow();
				if (index >= 0) {
					String cedula = tableTodos.getValueAt(index, 0).toString();
					selected = Altice.getInstance().buscarClienteByCedula(cedula);
					btnSeleccionar.setEnabled(true);
				}
			}
		});
		tableTodos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableTodos.setModel(model);
		
		panelNombres = new JPanel();
		panelNombres.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado Por Nombre", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelNombres.setBounds(10, 13, 872, 392);
		contentPanel.add(panelNombres);
		panelNombres.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Introduzca un nombre:");
		lblNewLabel.setBounds(12, 30, 131, 16);
		panelNombres.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				clientes.removeAll(clientes);
				if(txtNombre.getText().equalsIgnoreCase("")) {
					initArrayList(clientes);
					loadClientes(clientes);
				}else {
					clientes.addAll(Altice.getInstance().buscarTodosClientesByNombre(txtNombre.getText()));
					loadClientes(clientes);
				}
			}
		});
		txtNombre.setBounds(155, 27, 155, 22);
		panelNombres.add(txtNombre);
		txtNombre.setColumns(10);
		
		JScrollPane scNombre = new JScrollPane();
		scNombre.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scNombre.setBounds(12, 60, 850, 319);
		panelNombres.add(scNombre);
		
		tablePorNombre = new JTable();
		tablePorNombre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tablePorNombre.getSelectedRow();
				if (index >= 0) {
					String cedula = tablePorNombre.getValueAt(index, 0).toString();
					selected = Altice.getInstance().buscarClienteByCedula(cedula);
					btnSeleccionar.setEnabled(true);
				}
			}
		});
		tablePorNombre.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePorNombre.setModel(model);
		scNombre.setViewportView(tablePorNombre);
		
		loadClientes(clientes);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSeleccionar = new JButton("Seleccionar");
				btnSeleccionar.setBackground(Color.WHITE);
				btnSeleccionar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						PerfilCliente perCliente = new PerfilCliente((Cliente) selected, auxEmpleado);
						perCliente.setVisible(true);
						perCliente.setModal(true);
						loadClientes(clientes);
						btnSeleccionar.setEnabled(false);
					}
				});
				btnSeleccionar.setEnabled(false);
				btnSeleccionar.setActionCommand("OK");
				buttonPane.add(btnSeleccionar);
				getRootPane().setDefaultButton(btnSeleccionar);
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
	}
	private void initArrayList (ArrayList<Cliente> clientes) {
		for (Persona cliente : Altice.getInstance().getPersonas()) {
			if (cliente instanceof Cliente) 
				clientes.add((Cliente)cliente);
		}
	}
	
	private void loadClientes(ArrayList<Cliente> clientes) {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Cliente cliente : clientes) {
			row[0] = cliente.getCedula();
			row[1] = ""+cliente.getNombre()+" "+cliente.getApellido();
			row[2] = Integer.toString(cliente.getMisPlanes().size());
			model.addRow(row);
		}
	}
}
