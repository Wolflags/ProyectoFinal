package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Altice;
import logico.Plan;
import logico.Servicio;
import logico.Internet;
import logico.Minutos;
import logico.Television;
import logico.Empleado;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ListadoServicios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private Servicio selected = null;
	private JComboBox cbxTipoServicio;
	private ArrayList<Servicio> servicios;
	private Empleado auxEmpleado = null;
	private JButton btnVerServicio;

	public ListadoServicios(Empleado empleado) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoServicios.class.getResource("/media/imgListadoServicios32px.png")));
		auxEmpleado = empleado;
		servicios = new ArrayList<Servicio>();
		initArrayList(servicios);
		setTitle("Listado de servicios");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 900, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 874, 416);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(2, 60, 870, 353);
			panel.add(scrollPane);
			{
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = table.getSelectedRow();
						if(index >= 0) {
							String codServicio = table.getValueAt(index, 0).toString();
							selected = Altice.getInstance().buscarServicioByCodigo(codServicio);
							btnVerServicio.setEnabled(true);
						}
					}
				});
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				model = new DefaultTableModel();
				String[] headers = {"Código", "Tipo", "Precio"};
				model.setColumnIdentifiers(headers);
				table.setModel(model);
				scrollPane.setViewportView(table);
			}
		}
		loadServicios();
		
		JLabel lblTipoServicio = new JLabel("Tipo de servicio:");
		lblTipoServicio.setBounds(196, 23, 114, 14);
		panel.add(lblTipoServicio);
		
		cbxTipoServicio = new JComboBox();
		cbxTipoServicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbxTipoServicio.getSelectedIndex() == 0) {
					servicios.removeAll(servicios);
					initArrayList(servicios);
					loadServicios();
				}
				if(cbxTipoServicio.getSelectedIndex() == 1) {
					servicios.removeAll(servicios);
					initInternet(servicios);
					loadServicios();
				}
				if(cbxTipoServicio.getSelectedIndex() == 2) {
					servicios.removeAll(servicios);
					initMinutos(servicios);
					loadServicios();
				}
				if(cbxTipoServicio.getSelectedIndex() == 3) {
					servicios.removeAll(servicios);
					initTelevision(servicios);
					loadServicios();
				}
			}
		});
		cbxTipoServicio.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Internet", "Minutos", "Televisi\u00F3n"}));
		cbxTipoServicio.setBounds(320, 20, 170, 20);
		panel.add(cbxTipoServicio);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnVerServicio = new JButton("Ver servicio");
				btnVerServicio.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DetallesServicio detServicio = new DetallesServicio(selected, auxEmpleado);
						detServicio.setModal(true);
						detServicio.setVisible(true);
						loadServicios();
						btnVerServicio.setEnabled(false);
					}
				});
				btnVerServicio.setEnabled(false);
				btnVerServicio.setBackground(Color.WHITE);
				btnVerServicio.setActionCommand("OK");
				buttonPane.add(btnVerServicio);
				getRootPane().setDefaultButton(btnVerServicio);
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
	
	private void initArrayList(ArrayList<Servicio> servicios) {
		for(Servicio servicio : Altice.getInstance().getServicios()) {
			servicios.add(servicio);
		}
	}
	
	private void initInternet(ArrayList<Servicio> servicios) {
		for(Servicio servicio : Altice.getInstance().getServicios()) {
			if(servicio instanceof Internet) {
				servicios.add(servicio);
			}
		}
	}
	
	private void initMinutos(ArrayList<Servicio> servicios) {
		for(Servicio servicio : Altice.getInstance().getServicios()) {
			if(servicio instanceof Minutos) {
				servicios.add(servicio);
			}
		}
	}
	
	private void initTelevision(ArrayList<Servicio> servicios) {
		for(Servicio servicio : Altice.getInstance().getServicios()) {
			if(servicio instanceof Television) {
				servicios.add(servicio);
			}
		}
	}

	private void loadServicios() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Servicio servicio : servicios) {
			row[0] = servicio.getCodigo().toString();
			if(servicio instanceof Internet) {
				row[1] = "Internet";
			}
			if(servicio instanceof Minutos) {
				row[1] = "Minutos";
			}
			if(servicio instanceof Television) {
				row[1] = "Televisión";
			}
			row[2] = Float.toString(servicio.getPrecio());
			model.addRow(row);
		}
	}
}
