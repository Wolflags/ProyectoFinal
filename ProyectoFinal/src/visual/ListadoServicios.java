package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Altice;
import logico.Cliente;
import logico.Empleado;
import logico.Internet;
import logico.Minutos;
import logico.Servicio;
import logico.Television;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListadoServicios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Dimension dim;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private JTextField textField;
	private String ini="";
	private JButton btnVerDetalles;
	private Servicio selected = null;
	private Empleado auxEmpleado = null;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			ListadoServicios dialog = new ListadoServicios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ListadoServicios(Empleado empleado) {
		auxEmpleado = empleado;
		setTitle("Listado de Servicios");
		setResizable(false);
		setBounds(100, 100, 759, 468);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 1340, 640);
			dim = getToolkit().getScreenSize();
			setSize(dim.width,dim.height-34);
			setLocationRelativeTo(null);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 160, 1320, 469);
			panel.add(scrollPane);
			
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int index = table.getSelectedRow();
					if(index>=0) {
					btnVerDetalles.setEnabled(true);
					String codServ = table.getValueAt(index, 0).toString();
					selected=Altice.getInstance().buscarServicioByCod(codServ);
					
					}
				}

				
			});
			model = new DefaultTableModel();
			String[] headers = {"Código","Tipo de servicio","Descripción", "Precio"};
			model.setColumnIdentifiers(headers);
			table.setModel(model);
			scrollPane.setViewportView(table);
			{
				JLabel lblNewLabel = new JLabel("Listado de Servicios");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 27));
				lblNewLabel.setBounds(374, 30, 254, 33);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Buscar:");
				lblNewLabel_1.setBounds(10, 132, 64, 14);
				panel.add(lblNewLabel_1);
			}
			{
				textField = new JTextField();
				textField.setBounds(64, 129, 405, 20);
				panel.add(textField);
				textField.setColumns(10);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Filtrar:");
				lblNewLabel_2.setBounds(479, 132, 54, 14);
				panel.add(lblNewLabel_2);
			}
			
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Internet", "Minutos", "Television"}));
			comboBox.setBounds(530, 129, 166, 20);
			panel.add(comboBox);
			
			JLabel lblNewLabel_3 = new JLabel("Ordenar por:");
			lblNewLabel_3.setBounds(723, 132, 86, 14);
			panel.add(lblNewLabel_3);
			
			JComboBox comboBox_1 = new JComboBox();
			comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo", "Tipo de servicio", "Descripción", "Precio"}));
			comboBox_1.setBounds(807, 129, 166, 20);
			panel.add(comboBox_1);
		}
		
		
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnVerDetalles = new JButton("Ver detalles");
			btnVerDetalles.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DetallesServicio DetServ = new DetallesServicio(selected, auxEmpleado);
					DetServ.setVisible(true);
					DetServ.setModal(true);
				}
			});
			btnVerDetalles.setEnabled(false);
			buttonPane.add(btnVerDetalles);
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadServicios("");
	}
	


	private void loadServicios(String string) {
		if(ini=="") {
			model.setRowCount(0);
			row = new Object[model.getColumnCount()];
			ArrayList<Servicio> servicios = Altice.getInstance().getServicios();
			for (Servicio servicio : servicios) {
				row[0]=servicio.getCodigo();
				
				if(servicio instanceof Internet) {
					row[1]="Internet";
				}else if(servicio instanceof Minutos) {
					row[1]="Minutos";
				}else {
					row[1]="Television";
				}
				row[2]=servicio.getDescripcion();
				
				row[3]=servicio.getPrecio();
				model.addRow(row);
			}
			}else {
				model.setRowCount(0);
				row = new Object[model.getColumnCount()];
				ArrayList<Servicio> servicios = Altice.getInstance().getServicios();
				for (Servicio servicio : servicios) {
					if(servicio.getCodigo().contains(ini)) {
						row[0]=servicio.getCodigo();
						if(servicio instanceof Internet) {
							row[1]="Internet";
						}else if(servicio instanceof Minutos) {
							row[1]="Minutos";
						}else {
							row[1]="Television";
						}
						row[4]=servicio.getPrecio();
					model.addRow(row);
					}
				}
			}
		
	}
}
