package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

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

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.ScrollPaneConstants;

public class ListadoPlanes extends JDialog {

	private static DecimalFormat df = new DecimalFormat("#.##");
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private Plan selected = null;
	private Empleado auxEmpleado = null;
	private JButton btnVerPlan;
	private JRadioButton rbTodos;
	private JRadioButton rbServicios;
	private JRadioButton rbPrecio;
	private JPanel panelContenido;
	private JPanel panelServicios;
	private JLabel lblSeleccioneServicio;
	private JComboBox cbxTipoServicio;
	private JPanel panelPrecio;
	private JLabel lblOrden;
	private JComboBox cbxOrden;

	public ListadoPlanes(Empleado empleado) {
		auxEmpleado = empleado;
		df.setRoundingMode(RoundingMode.CEILING);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoPlanes.class.getResource("/media/imgListadoPlanes32px.png")));
		setTitle("Listado de planes");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 900, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			panelContenido = new JPanel();
			panelContenido.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Todos los planes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelContenido.setBounds(215, 11, 669, 416);
			contentPanel.add(panelContenido);
			panelContenido.setLayout(null);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPane.setBounds(10, 68, 649, 337);
				panelContenido.add(scrollPane);
				{
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int index = table.getSelectedRow();
							if(index >= 0) {
								String codPlan = table.getValueAt(index, 0).toString();
								selected = Altice.getInstance().buscarPlanByCodigo(codPlan);
								btnVerPlan.setEnabled(true);
							}
						}
					});
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					model = new DefaultTableModel();
					String[] headers = {"Código", "Nombre", "Cantidad de servicios", "Precio"};
					model.setColumnIdentifiers(headers);
					table.setModel(model);
					scrollPane.setViewportView(table);
				}
			}
			{
				panelServicios = new JPanel();
				panelServicios.setVisible(false);
				panelServicios.setBounds(20, 21, 586, 36);
				panelContenido.add(panelServicios);
				panelServicios.setLayout(null);
				
				lblSeleccioneServicio = new JLabel("Seleccione tipo de servicio:");
				lblSeleccioneServicio.setBounds(151, 11, 168, 14);
				panelServicios.add(lblSeleccioneServicio);
				
				cbxTipoServicio = new JComboBox();
				cbxTipoServicio.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						loadPlanes();
					}
				});
				cbxTipoServicio.setModel(new DefaultComboBoxModel(new String[] {"Internet", "Minutos", "Televisi\u00F3n"}));
				cbxTipoServicio.setBounds(311, 8, 176, 20);
				panelServicios.add(cbxTipoServicio);
			}
			
			panelPrecio = new JPanel();
			panelPrecio.setVisible(false);
			panelPrecio.setBounds(20, 21, 586, 36);
			panelContenido.add(panelPrecio);
			panelPrecio.setLayout(null);
			
			lblOrden = new JLabel("Orden:");
			lblOrden.setBounds(151, 11, 168, 14);
			panelPrecio.add(lblOrden);
			
			cbxOrden = new JComboBox();
			cbxOrden.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadPlanes();
				}
			});
			cbxOrden.setModel(new DefaultComboBoxModel(new String[] {"Ascendente", "Descendente"}));
			cbxOrden.setBounds(270, 8, 176, 20);
			panelPrecio.add(cbxOrden);
			
			JPanel panelFiltros = new JPanel();
			panelFiltros.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelFiltros.setBounds(10, 11, 195, 416);
			contentPanel.add(panelFiltros);
			panelFiltros.setLayout(null);
			
			rbTodos = new JRadioButton("Todos");
			rbTodos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rbTodos.isSelected()) {
						rbServicios.setSelected(false);
						rbPrecio.setSelected(false);
						panelContenido.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Todos los planes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
						panelServicios.setVisible(false);
						panelPrecio.setVisible(false);
						loadPlanes();
					}
				}
			});
			rbTodos.setSelected(true);
			rbTodos.setBounds(28, 86, 109, 23);
			panelFiltros.add(rbTodos);
			
			rbServicios = new JRadioButton("Por servicios");
			rbServicios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rbServicios.isSelected()) {
						rbTodos.setSelected(false);
						rbPrecio.setSelected(false);
						panelContenido.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Planes por servicio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
						panelServicios.setVisible(true);
						panelPrecio.setVisible(false);
						loadPlanes();
					}
				}
			});
			rbServicios.setBounds(28, 195, 109, 23);
			panelFiltros.add(rbServicios);
			
			rbPrecio = new JRadioButton("Por precio");
			rbPrecio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rbPrecio.isSelected()) {
						rbServicios.setSelected(false);
						rbTodos.setSelected(false);
						panelContenido.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Planes por precio ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
						panelPrecio.setVisible(true);
						panelServicios.setVisible(false);
						loadPlanes();
					}
				}
			});
			rbPrecio.setBounds(28, 304, 109, 23);
			panelFiltros.add(rbPrecio);
			loadPlanes();
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnVerPlan = new JButton("Ver plan");
				btnVerPlan.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DetallesPlan detPlan = new DetallesPlan(selected, auxEmpleado);
						detPlan.setVisible(true);
					}
				});
				btnVerPlan.setEnabled(false);
				btnVerPlan.setBackground(Color.WHITE);
				btnVerPlan.setActionCommand("OK");
				buttonPane.add(btnVerPlan);
				getRootPane().setDefaultButton(btnVerPlan);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancelar.setBackground(Color.WHITE);
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
	private void loadPlanes() {
		if(rbTodos.isSelected()) {
			model.setRowCount(0);
			row = new Object[model.getColumnCount()];
			for (Plan plan : Altice.getInstance().getPlanes()) {
				//{"Código", "Nombre", "Cantidad de servicios", "Precio", "Estado"}
				row[0] = plan.getIdplan().toString();
				row[1] = plan.getNombre().toString();
				row[2] = Integer.toString(plan.cantServicios());
				row[3] = df.format(plan.getPrecio());
				model.addRow(row);
			}
		}
		if(rbServicios.isSelected()) {
			panelServicios.setVisible(true);
			model.setRowCount(0);
			row = new Object[model.getColumnCount()];
			for (Plan plan : Altice.getInstance().getPlanes()) {
				if(planContiene(plan, cbxTipoServicio.getSelectedIndex())) {
					row[0] = plan.getIdplan().toString();
					row[1] = plan.getNombre().toString();
					row[2] = Integer.toString(plan.cantServicios());
					row[3] = df.format(plan.getPrecio());
					model.addRow(row);
				}
			}
		}
		if(rbPrecio.isSelected()) {
			ArrayList<Plan> planesOrdenados = new ArrayList<Plan>();
			for(Plan plan : Altice.getInstance().getPlanes()) {
				planesOrdenados.add(plan);
			}
			if(cbxOrden.getSelectedIndex() == 0) {
				model.setRowCount(0);
				row = new Object[model.getColumnCount()];
				Collections.sort(planesOrdenados);
				for (int i = planesOrdenados.size()-1; i >= 0; i--) {
					row[0] = planesOrdenados.get(i).getIdplan().toString();
					row[1] = planesOrdenados.get(i).getNombre().toString();
					row[2] = Integer.toString(planesOrdenados.get(i).cantServicios());
					row[3] = df.format(planesOrdenados.get(i).getPrecio());
					model.addRow(row);
				}
			}
			if(cbxOrden.getSelectedIndex() == 1) {
				model.setRowCount(0);
				row = new Object[model.getColumnCount()];
				Collections.sort(planesOrdenados);
				for (Plan plan : planesOrdenados) {
					row[0] = plan.getIdplan().toString();
					row[1] = plan.getNombre().toString();
					row[2] = Integer.toString(plan.cantServicios());
					row[3] = df.format(plan.getPrecio());
					model.addRow(row);
				}
			}
		}
	}
	
	private boolean planContiene(Plan plan, int indice) {
		boolean contiene = false;
		if(plan.getMisServicios().get(indice) != null) {
			contiene = true;
		}
		return contiene;
	}
}
