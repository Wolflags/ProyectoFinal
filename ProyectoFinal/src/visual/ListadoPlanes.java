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

public class ListadoPlanes extends JDialog {

	private static DecimalFormat df = new DecimalFormat("#.##");
	private Dimension dim;
	private final JPanel contentPanel = new JPanel();
	private JPanel panelFiltro;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private Plan selected = null;
	private JButton btnVerPlan;

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
		setBounds(100, 100, 670, 450);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height-40);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			panelFiltro = new JPanel();
			panelFiltro.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelFiltro.setBounds(394, 11, 945, 64);
			contentPanel.add(panelFiltro);
			panelFiltro.setLayout(null);
		}
		{
			JPanel panelTabla = new JPanel();
			panelTabla.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelTabla.setBounds(10, 86, 638, 291);
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
