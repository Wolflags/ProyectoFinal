package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Altice;
import logico.Plan;

public class SegundoReporte extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private Object[] row;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SegundoReporte dialog = new SegundoReporte();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SegundoReporte() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TercerReporte.class.getResource("/media/imgReporte32px.png")));
		setResizable(false);
		setModal(true);
		setTitle("Reporte No. 3 - Cantidad de dinero por plan");
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
		String[] headers = {"ID", "Nombre", "Precio", "Ganancias esperadas", "Ganancias generadas"};  
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
				btnRegresar.setBackground(Color.WHITE);
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
				row[2] = Altice.formatearDecimales(plan.getPrecio(), 2);
				row[3] = Float.valueOf(plan.cantidadDineroDebioGenerar());
				row[4] = Float.valueOf(plan.cantidadDineroGenerado());
				model.addRow(row);
			}
		}
		return;
		
	}

}
