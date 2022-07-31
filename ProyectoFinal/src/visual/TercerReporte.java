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
import logico.Factura;
import logico.Plan;
import logico.Servicio;

import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TercerReporte extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private Object[] row;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TercerReporte dialog = new TercerReporte();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TercerReporte() {
		setResizable(false);
		setModal(true);
		setTitle("Ventas Por Planes");
		setBounds(100, 100, 815, 410);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado de Planes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(12, 13, 774, 315);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 30, 750, 272);
		panel.add(scrollPane);
		
		
		table = new JTable();
		model = new DefaultTableModel();
		String[] headers = {"ID", "Nombre", "Cantidad de Servicios", "Dinero Generado", "Precio"};  
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegresar = new JButton("Regresar");
				btnRegresar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnRegresar.setActionCommand("Cancel");
				buttonPane.add(btnRegresar);
			}
		}
		loadPlanes();
	}

	private void loadPlanes() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Plan plan : Altice.getInstance().getPlanes()) {
			if (plan.getNumero().equalsIgnoreCase("0")) {
				row[0] = plan.getIdplan();
				row[1] = plan.getNombre();
				row[2] = Integer.valueOf(cantidadServiciosPorPlan(plan)).toString();
				row[3] = Float.valueOf(cantidadDineroGeneradoPorPlan(plan));
				row[4] = Float.valueOf(plan.getPrecio()).toString();
				model.addRow(row);
			}
		}
		return;
	}
	
	private int cantidadServiciosPorPlan(Plan plan) {
		int cantidad = 0;
		for (Servicio servicio: plan.getMisServicios()) {
			if (servicio != null) {
				cantidad++;
			}
		}
		
		return cantidad;
	}
	
	private float cantidadDineroGeneradoPorPlan(Plan plan) {
		float cantidad = 0;
		
		for (Factura factura: Altice.getInstance().getFacturas()) {
			if (factura.getPlan().getNombre().equalsIgnoreCase(plan.getNombre())) {
				cantidad += factura.getPlan().getPrecio();
			}
		}
		return cantidad;
	}
}
