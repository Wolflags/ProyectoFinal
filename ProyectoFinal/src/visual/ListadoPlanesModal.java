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
import logico.Internet;
import logico.Minutos;
import logico.Plan;
import logico.Servicio;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;

public class ListadoPlanesModal extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtBuscar;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private static String ini="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoPlanesModal dialog = new ListadoPlanesModal();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoPlanesModal() {
		setResizable(false);
		setBounds(100, 100, 825, 493);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 799, 409);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccionar Plan");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setBounds(290, 11, 181, 23);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 111, 779, 287);
		panel.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel();
		String[] headers = {"Código","Nombre","Cantidad de servicios", "Precio"};
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Buscar:");
		lblNewLabel_1.setBounds(20, 86, 67, 14);
		panel.add(lblNewLabel_1);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(71, 83, 239, 20);
		panel.add(txtBuscar);
		txtBuscar.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSeleccionar = new JButton("Seleccionar");
				btnSeleccionar.setActionCommand("OK");
				buttonPane.add(btnSeleccionar);
				getRootPane().setDefaultButton(btnSeleccionar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadPlanes();
	}

	private void loadPlanes() {
		if(ini=="") {
			model.setRowCount(0);
			row = new Object[model.getColumnCount()];
			ArrayList<Plan> planes = Altice.getInstance().getPlanes();
			for (Plan plan : planes) {
				row[0]=plan.getIdplan();
				row[1]=plan.getNombre();
				int cantserv=0;
				if(plan.getMisServicios().get(0)!=null) {
					cantserv++;
				}
				if(plan.getMisServicios().get(1)!=null) {
					cantserv++;
				}
				if(plan.getMisServicios().get(2)!=null) {
					cantserv++;
				}
				row[2]=cantserv;
				row[3]=plan.getPrecio();
				model.addRow(row);
			}
			}else {
				model.setRowCount(0);
				row = new Object[model.getColumnCount()];
				ArrayList<Plan> planes = Altice.getInstance().getPlanes();
				for (Plan plan : planes) {
					if(plan.getIdplan().contains(ini)||plan.getNombre().contains(ini)) {
					row[0]=plan.getIdplan();
					row[1]=plan.getNombre();
					int cantserv=0;
					if(plan.getMisServicios().get(0)!=null) {
						cantserv++;
					}
					if(plan.getMisServicios().get(1)!=null) {
						cantserv++;
					}
					if(plan.getMisServicios().get(2)!=null) {
						cantserv++;
					}
					row[2]=cantserv;
					row[3]=plan.getPrecio();
					
					model.addRow(row);
					}
				}
			}
		
	}
}
