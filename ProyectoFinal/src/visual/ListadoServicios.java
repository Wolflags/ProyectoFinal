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
import logico.Internet;
import logico.Minutos;
import logico.Servicio;

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

public class ListadoServicios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Dimension dim;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private JTextField textField;
	private JTextField txtS;
	private JTextField txtNoSeleccionado;
	private String ini="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoServicios dialog = new ListadoServicios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoServicios() {
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
			panel.setBounds(367, 11, 983, 640);
			dim = getToolkit().getScreenSize();
			setSize(dim.width,dim.height-34);
			setLocationRelativeTo(null);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 160, 963, 469);
			panel.add(scrollPane);
			
			table = new JTable();
			model = new DefaultTableModel();
			String[] headers = {"Código","Tipo de facturación","Dias Vigencia"};
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
			comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo", "Tipo de servicio", "Tipo de facturaci\u00F3n", "Dias de vigencia"}));
			comboBox_1.setBounds(807, 129, 166, 20);
			panel.add(comboBox_1);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 347, 491);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel_4 = new JLabel("Servicio Seleccionado");
			lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblNewLabel_4.setBounds(93, 11, 178, 39);
			panel.add(lblNewLabel_4);
			
			JLabel lblNewLabel_5 = new JLabel("C\u00F3digo:");
			lblNewLabel_5.setBounds(10, 80, 61, 14);
			panel.add(lblNewLabel_5);
			
			txtS = new JTextField();
			txtS.setText("S-00");
			txtS.setEditable(false);
			txtS.setBounds(10, 105, 178, 20);
			panel.add(txtS);
			txtS.setColumns(10);
			
			JLabel lblNewLabel_6 = new JLabel("Tipo de facturaci\u00F3n:");
			lblNewLabel_6.setBounds(10, 136, 119, 14);
			panel.add(lblNewLabel_6);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"No seleccionada"}));
			comboBox.setBounds(10, 161, 178, 20);
			panel.add(comboBox);
			
			JLabel lblNewLabel_7 = new JLabel("Dias de Vigencia:");
			lblNewLabel_7.setBounds(10, 192, 119, 14);
			panel.add(lblNewLabel_7);
			
			JSpinner spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(0, 0, 0, 1));
			spinner.setBounds(10, 217, 178, 20);
			panel.add(spinner);
			
			JLabel lblNewLabel_8 = new JLabel("Descripci\u00F3n:");
			lblNewLabel_8.setBounds(10, 248, 119, 14);
			panel.add(lblNewLabel_8);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 273, 327, 151);
			panel.add(scrollPane);
			
			JTextArea textArea = new JTextArea();
			textArea.setEditable(false);
			scrollPane.setViewportView(textArea);
			
			JLabel lblNewLabel_9 = new JLabel("Tipo de Servicio:");
			lblNewLabel_9.setBounds(10, 435, 119, 14);
			panel.add(lblNewLabel_9);
			
			txtNoSeleccionado = new JTextField();
			txtNoSeleccionado.setText("<No seleccionado>");
			txtNoSeleccionado.setEditable(false);
			txtNoSeleccionado.setBounds(10, 460, 178, 20);
			panel.add(txtNoSeleccionado);
			txtNoSeleccionado.setColumns(10);
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 513, 347, 138);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_10 = new JLabel("Velocidad (Mbps):");
		lblNewLabel_10.setBounds(10, 11, 147, 14);
		panel.add(lblNewLabel_10);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 0, 1));
		spinner.setBounds(10, 36, 147, 20);
		panel.add(spinner);
		
		JLabel lblNewLabel_11 = new JLabel("Cantidad (Mb):");
		lblNewLabel_11.setBounds(179, 11, 108, 14);
		panel.add(lblNewLabel_11);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(0, 0, 0, 1));
		spinner_1.setBounds(179, 36, 158, 20);
		panel.add(spinner_1);
		
		JLabel lblNewLabel_12 = new JLabel("Tipo de internet:");
		lblNewLabel_12.setBounds(10, 67, 108, 14);
		panel.add(lblNewLabel_12);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"No seleccionado"}));
		comboBox.setBounds(10, 92, 147, 20);
		panel.add(comboBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnNewButton = new JButton("Modificar servicio");
			btnNewButton.setEnabled(false);
			buttonPane.add(btnNewButton);
			{
				JButton okButton = new JButton("Guardar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
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
				row[2]=servicio.getDuracion();
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
						row[2]=servicio.getDuracion();
					model.addRow(row);
					}
				}
			}
		
	}
}
