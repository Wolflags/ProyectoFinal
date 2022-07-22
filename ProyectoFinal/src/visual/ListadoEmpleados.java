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

public class ListadoEmpleados extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JRadioButton rbPorNombre;
	private JRadioButton rbPorTipo;
	private JRadioButton rbPorEstado;
	private JTextField txtNombre;
	private JTable tablePorNombre;
	private DefaultTableModel model;
	private Object[] row;
	private JComboBox cbxTipo;
	private JTable tablePorTipo;
	private JTable tablePorEstado;
	private JComboBox cbxEstado;
	private JPanel panel_ListNombre;
	private JPanel panel_ListTipo;
	private JPanel panel_ListEstado;
	private JButton btnBuscar;
	private Persona selected;
	private JButton btnSeleccionar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoEmpleados dialog = new ListadoEmpleados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoEmpleados() {
		setResizable(false);
		setTitle("Listado de empleados");
		setBounds(100, 100, 900, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		String[] headers = {"Cédula", "Nombres y Apellidos", "Tipo de empleado",  "Puesto", "Estado"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(headers);
		
		panel_ListEstado = new JPanel();
		panel_ListEstado.setVisible(false);
		
		panel_ListNombre = new JPanel();
		panel_ListNombre.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado Por Nombre", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_ListNombre.setBounds(219, 13, 663, 392);
		contentPanel.add(panel_ListNombre);
		panel_ListNombre.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Introduzca un nombre:");
		lblNewLabel.setBounds(12, 30, 131, 16);
		panel_ListNombre.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(155, 27, 155, 22);
		panel_ListNombre.add(txtNombre);
		txtNombre.setColumns(10);
		
		JScrollPane spPorNombre = new JScrollPane();
		spPorNombre.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spPorNombre.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spPorNombre.setBounds(12, 60, 639, 319);
		panel_ListNombre.add(spPorNombre);
		
		tablePorNombre = new JTable();
		tablePorNombre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tablePorNombre.getSelectedRow();
				if (index >= 0) {
					String cedula = tablePorNombre.getValueAt(index, 0).toString();
					selected = Altice.getInstance().buscarEmpleadoByCedula(cedula);
					btnSeleccionar.setEnabled(true);
				}
			}
		});
		tablePorNombre.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePorNombre.setModel(model);
		spPorNombre.setViewportView(tablePorNombre);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Persona> empleados = new ArrayList<Persona>();
				btnSeleccionar.setEnabled(false);
				
				if (txtNombre.getText().equalsIgnoreCase("")) {
					initArrayList(empleados);
					loadEmpleados(empleados);
					return;
				}
				
				empleados.addAll(Altice.getInstance().buscarTodosEmpleadoByNombre(txtNombre.getText()));
				if (!empleados.isEmpty()) {
					loadEmpleados(empleados);
				}else {
					JOptionPane.showMessageDialog(null, "Empleados no encontrados.", "Información", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnBuscar.setBounds(343, 26, 97, 25);
		panel_ListNombre.add(btnBuscar);
		panel_ListEstado.setBorder(new TitledBorder(null, "Listado Por Estado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_ListEstado.setBounds(219, 13, 663, 392);
		contentPanel.add(panel_ListEstado);
		panel_ListEstado.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Seleccione un estado:");
		lblNewLabel_2.setBounds(12, 30, 126, 16);
		panel_ListEstado.add(lblNewLabel_2);
		
		cbxEstado = new JComboBox();
		cbxEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Persona> empleados = new ArrayList<Persona>();
				switch(cbxEstado.getSelectedIndex()) {
					case 0:
						initArrayList(empleados);
						loadEmpleados(empleados);
						break;
					case 1:
						initArrayContratado(empleados);
						loadEmpleados(empleados);
						break;
					case 2:
						initArrayCancelado(empleados);
						loadEmpleados(empleados);
						break;
				}
			}
		});
		cbxEstado.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Administradores", "Empleados"}));
		cbxEstado.setBounds(150, 27, 155, 22);
		panel_ListEstado.add(cbxEstado);
		
		JScrollPane spPorEstado = new JScrollPane();
		spPorEstado.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spPorEstado.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spPorEstado.setBounds(12, 60, 639, 319);
		panel_ListEstado.add(spPorEstado);
		
		tablePorEstado = new JTable();
		tablePorEstado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tablePorEstado.getSelectedRow();
				if (index >= 0) {
					String cedula = tablePorEstado.getValueAt(index, 0).toString();
					selected = Altice.getInstance().buscarEmpleadoByCedula(cedula);
					btnSeleccionar.setEnabled(true);
				}
			}
		});
		tablePorEstado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePorEstado.setModel(model);
		spPorEstado.setViewportView(tablePorEstado);
		
		panel_ListTipo = new JPanel();
		panel_ListTipo.setVisible(false);
		panel_ListTipo.setBorder(new TitledBorder(null, "Listado Por Tipo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_ListTipo.setBounds(219, 13, 663, 392);
		contentPanel.add(panel_ListTipo);
		panel_ListTipo.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione un tipo:");
		lblNewLabel_1.setBounds(12, 30, 109, 16);
		panel_ListTipo.add(lblNewLabel_1);
		
		cbxTipo = new JComboBox();
		cbxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Persona> empleados = new ArrayList<Persona>();
				switch(cbxTipo.getSelectedIndex()) {
					case 0:
						initArrayList(empleados);
						loadEmpleados(empleados);
						break;
					case 1:
						initArrayAdmin(empleados);
						loadEmpleados(empleados);
						break;
					case 2:
						initArrayEmpleados(empleados);
						loadEmpleados(empleados);
						break;
				}
			}
		});
		cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Administradores", "Empleados"}));
		cbxTipo.setBounds(133, 27, 155, 22);
		panel_ListTipo.add(cbxTipo);
		
		JScrollPane spPorTipo = new JScrollPane();
		spPorTipo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spPorTipo.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spPorTipo.setBounds(12, 60, 639, 319);
		panel_ListTipo.add(spPorTipo);
		
		tablePorTipo = new JTable();
		tablePorTipo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tablePorTipo.getSelectedRow();
				if (index >= 0) {
					String cedula = tablePorTipo.getValueAt(index, 0).toString();
					selected = Altice.getInstance().buscarEmpleadoByCedula(cedula);
					btnSeleccionar.setEnabled(true);
				}
			}
		});
		tablePorTipo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePorTipo.setModel(model);
		spPorTipo.setViewportView(tablePorTipo);
		
		JPanel panel_Filtro = new JPanel();
		panel_Filtro.setBorder(new TitledBorder(null, "Filtro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Filtro.setBounds(12, 13, 195, 392);
		contentPanel.add(panel_Filtro);
		panel_Filtro.setLayout(null);
		
		rbPorNombre = new JRadioButton("Por Nombre");
		rbPorNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbPorNombre.setSelected(true);
				if (rbPorNombre.isSelected()) {
					ArrayList<Persona> empleados = new ArrayList<Persona>();
					rbPorTipo.setSelected(false);
					rbPorEstado.setSelected(false);
					panel_ListNombre.setVisible(true);
					panel_ListEstado.setVisible(false);
					panel_ListTipo.setVisible(false);
					btnSeleccionar.setEnabled(false);
					txtNombre.setText("");
					initArrayList(empleados);
					loadEmpleados(empleados);
				}
			}
		});
		rbPorNombre.setSelected(true);
		rbPorNombre.setBounds(8, 130, 127, 25);
		panel_Filtro.add(rbPorNombre);
		
		rbPorTipo = new JRadioButton("Por Tipo");
		rbPorTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbPorTipo.setSelected(true);
				if (rbPorTipo.isSelected()) {
					ArrayList<Persona> empleados = new ArrayList<Persona>();
					rbPorNombre.setSelected(false);
					rbPorEstado.setSelected(false);
					panel_ListTipo.setVisible(true);
					panel_ListNombre.setVisible(false);
					panel_ListEstado.setVisible(false);
					btnSeleccionar.setEnabled(false);
					cbxTipo.setSelectedIndex(0);
					initArrayList(empleados);
					loadEmpleados(empleados);
				}
			}
		});
		rbPorTipo.setBounds(8, 185, 127, 25);
		panel_Filtro.add(rbPorTipo);
		
		rbPorEstado = new JRadioButton("Por Estado");
		rbPorEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbPorEstado.setSelected(true);
				if (rbPorEstado.isSelected()) {
					ArrayList<Persona> empleados = new ArrayList<Persona>();
					rbPorNombre.setSelected(false);
					rbPorEstado.setSelected(false);
					panel_ListEstado.setVisible(true);
					panel_ListNombre.setVisible(false);
					panel_ListTipo.setVisible(false);
					btnSeleccionar.setEnabled(false);
					cbxEstado.setSelectedIndex(0);
					initArrayList(empleados);
					loadEmpleados(empleados);
				}
			}
		});
		rbPorEstado.setBounds(8, 240, 127, 25);
		panel_Filtro.add(rbPorEstado);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSeleccionar = new JButton("Seleccionar");
				btnSeleccionar.setEnabled(false);
				btnSeleccionar.setActionCommand("OK");
				buttonPane.add(btnSeleccionar);
				getRootPane().setDefaultButton(btnSeleccionar);
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
	}
	private void initArrayContratado (ArrayList<Persona> empleados) {
		for (Persona empleado : Altice.getInstance().getPersonas()) {
			if (empleado instanceof Empleado) {
				if (((Empleado) empleado).isEstado())
					empleados.add(empleado);
			}
		}
	}
	private void initArrayCancelado (ArrayList<Persona> empleados) {
		for (Persona empleado : Altice.getInstance().getPersonas()) {
			if (empleado instanceof Empleado) {
				if (!((Empleado) empleado).isEstado())
					empleados.add(empleado);
			}
		}
	}
	private void initArrayAdmin(ArrayList<Persona> empleados) {
		for (Persona empleado : Altice.getInstance().getPersonas()) {
			if (empleado instanceof Empleado) {
				if (((Empleado) empleado).getTipoEmpleado().equalsIgnoreCase("Administrador"))
					empleados.add(empleado);
			}
		}
		
	}
	private void initArrayEmpleados(ArrayList<Persona> empleados) {
		for (Persona empleado : Altice.getInstance().getPersonas()) {
			if (empleado instanceof Empleado) {
				if (((Empleado) empleado).getTipoEmpleado().equalsIgnoreCase("Empleado"))
					empleados.add(empleado);
			}
		}
		
	}
	private void initArrayList (ArrayList<Persona> empleados) {
		for (Persona empleado : Altice.getInstance().getPersonas())
			if (empleado instanceof Empleado) 
				empleados.add(empleado);
		return;
	}
	private void loadEmpleados(ArrayList<Persona> empleados) {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Persona empleado : empleados) {
			row[0] = empleado.getCedula();
			row[1] = ""+empleado.getNombre()+" "+empleado.getApellido();
			if (((Empleado) empleado).getTipoEmpleado().equalsIgnoreCase("Administrador"))
				row[2] = "Administrador";
			else
				row[2] = "Empleado";
			row[3] = ((Empleado) empleado).getPuestoTrabajo();
			if (((Empleado) empleado).isEstado())
				row[4] = "Contratado";
			else
				row[4] = "Cancelado"; 
		}
	}
}
