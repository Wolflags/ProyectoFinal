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

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Altice;
import logico.Factura;
import logico.Plan;

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

public class ListadoPlanes extends JDialog {

	private static DecimalFormat df = new DecimalFormat("#.##");
	private Dimension dim;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private Plan selected = null;
	private JButton btnVerPlan;
	private JRadioButton rbTodos;
	private JRadioButton rbServicios;
	private JRadioButton rbPrecio;
	private JPanel panelContenido;
	private JPanel panelServicios;
	private JCheckBox ckbxInternet;
	private JCheckBox ckbxMinutos;
	private JCheckBox ckbxTelevision;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoPlanes dialog = new ListadoPlanes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoPlanes() {
		df.setRoundingMode(RoundingMode.CEILING);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoPlanes.class.getResource("/media/imgListadoPlanes32px.png")));
		setTitle("Listado de planes");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 950, 650);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			panelContenido = new JPanel();
			panelContenido.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Todos los planes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelContenido.setBounds(296, 11, 627, 566);
			contentPanel.add(panelContenido);
			panelContenido.setLayout(null);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(2, 68, 623, 402);
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
					String[] headers = {"Código", "Nombre", "Cantidad de servicios", "Precio", "Estado"};
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
				
				ckbxInternet = new JCheckBox("Internet");
				ckbxInternet.setSelected(true);
				ckbxInternet.setBounds(55, 7, 122, 23);
				panelServicios.add(ckbxInternet);
				
				ckbxMinutos = new JCheckBox("Minutos");
				ckbxMinutos.setSelected(true);
				ckbxMinutos.setBounds(232, 7, 122, 23);
				panelServicios.add(ckbxMinutos);
				
				ckbxTelevision = new JCheckBox("Televisi\u00F3n");
				ckbxTelevision.setSelected(true);
				ckbxTelevision.setBounds(409, 7, 122, 23);
				panelServicios.add(ckbxTelevision);
			}
			
			JPanel panelFiltros = new JPanel();
			panelFiltros.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelFiltros.setBounds(10, 11, 276, 566);
			contentPanel.add(panelFiltros);
			panelFiltros.setLayout(null);
			
			rbTodos = new JRadioButton("Todos");
			rbTodos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rbTodos.isSelected()) {
						rbServicios.setSelected(false);
						rbPrecio.setSelected(false);
						panelContenido.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Todos los planes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
					}
				}
			});
			rbTodos.setSelected(true);
			rbTodos.setBounds(38, 124, 109, 23);
			panelFiltros.add(rbTodos);
			
			rbServicios = new JRadioButton("Por servicios");
			rbServicios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rbServicios.isSelected()) {
						rbTodos.setSelected(false);
						rbPrecio.setSelected(false);
						panelContenido.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Planes por servicio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
					}
				}
			});
			rbServicios.setBounds(38, 271, 109, 23);
			panelFiltros.add(rbServicios);
			
			rbPrecio = new JRadioButton("Por precio");
			rbPrecio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rbPrecio.isSelected()) {
						rbServicios.setSelected(false);
						rbTodos.setSelected(false);
						panelContenido.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Planes por precio ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
					}
				}
			});
			rbPrecio.setBounds(38, 418, 109, 23);
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
						DetallesPlan detPlan = new DetallesPlan(selected);
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
				if(plan.isEstado()) {
					row[4] = "Vigente";
				}
				else {
					row[4] = "Cancelado";
				}
				model.addRow(row);
			}
		}
		
	}
}
