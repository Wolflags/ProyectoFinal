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
	private JTextField txtCodigo;
	private JTextField txtTipoServ;
	private String ini="";
	private JButton btnEditarServ;
	private Servicio selected = null;
	private JComboBox cmbTipoFac;
	private JSpinner spn_DiasVigencia;
	private JTextArea txtDescripcion;
	private JSpinner spnVelInter;
	private JSpinner spnCantInter;
	private JComboBox cmbTipoInter;
	private JSpinner spnCantMinutos;
	private JComboBox cmbTipoMinutos;
	private JSpinner spnCantCanales;
	private JComboBox cmbTipoTv;
	private JPanel panelTelevision;
	private JPanel panelMinutos;
	private JPanel panelInternet;

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
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int index = table.getSelectedRow();
					if(index>=0) {
					btnEditarServ.setEnabled(true);
					String codServ = table.getValueAt(index, 0).toString();
					selected=Altice.getInstance().buscarServicioByCod(codServ);
					cargarServSel();
					}
				}

				
			});
			model = new DefaultTableModel();
			String[] headers = {"Código","Tipo de servicio","Tipo de facturación","Dias Vigencia", "Precio"};
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
			comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"C\u00F3digo", "Tipo de servicio", "Tipo de facturaci\u00F3n", "Dias de vigencia", "Precio"}));
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
			
			txtCodigo = new JTextField();
			txtCodigo.setText("S-00");
			txtCodigo.setEditable(false);
			txtCodigo.setBounds(10, 105, 178, 20);
			panel.add(txtCodigo);
			txtCodigo.setColumns(10);
			
			JLabel lblNewLabel_6 = new JLabel("Tipo de facturaci\u00F3n:");
			lblNewLabel_6.setBounds(10, 136, 119, 14);
			panel.add(lblNewLabel_6);
			
			cmbTipoFac = new JComboBox();
			cmbTipoFac.setModel(new DefaultComboBoxModel(new String[] {"No seleccionada", "Mensual", "Anual", "Agotable"}));
			cmbTipoFac.setBounds(10, 161, 178, 20);
			panel.add(cmbTipoFac);
			
			JLabel lblNewLabel_7 = new JLabel("Dias de Vigencia:");
			lblNewLabel_7.setBounds(10, 192, 119, 14);
			panel.add(lblNewLabel_7);
			
			spn_DiasVigencia = new JSpinner();
			spn_DiasVigencia.setModel(new SpinnerNumberModel(0, 0, 0, 1));
			spn_DiasVigencia.setBounds(10, 217, 178, 20);
			panel.add(spn_DiasVigencia);
			
			JLabel lblNewLabel_8 = new JLabel("Descripci\u00F3n:");
			lblNewLabel_8.setBounds(10, 248, 119, 14);
			panel.add(lblNewLabel_8);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 273, 327, 151);
			panel.add(scrollPane);
			
			txtDescripcion = new JTextArea();
			txtDescripcion.setEditable(false);
			scrollPane.setViewportView(txtDescripcion);
			
			JLabel lblNewLabel_9 = new JLabel("Tipo de Servicio:");
			lblNewLabel_9.setBounds(10, 435, 119, 14);
			panel.add(lblNewLabel_9);
			
			txtTipoServ = new JTextField();
			txtTipoServ.setText("<No seleccionado>");
			txtTipoServ.setEditable(false);
			txtTipoServ.setBounds(10, 460, 178, 20);
			panel.add(txtTipoServ);
			txtTipoServ.setColumns(10);
		}
		panelTelevision = new JPanel();
		panelTelevision.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTelevision.setBounds(10, 513, 347, 138);
		contentPanel.add(panelTelevision);
		panelTelevision.setLayout(null);
		
		JLabel lblNewLabel_15 = new JLabel("Cantidad de canales:");
		lblNewLabel_15.setBounds(10, 11, 149, 14);
		panelTelevision.add(lblNewLabel_15);
		
		spnCantCanales = new JSpinner();
		spnCantCanales.setBounds(10, 32, 188, 20);
		panelTelevision.add(spnCantCanales);
		
		JLabel lblNewLabel_16 = new JLabel("Tipo:");
		lblNewLabel_16.setBounds(10, 63, 149, 14);
		panelTelevision.add(lblNewLabel_16);
		
		cmbTipoTv = new JComboBox();
		cmbTipoTv.setModel(new DefaultComboBoxModel(new String[] {"Televisi\u00F3n por Fibra", "Televisi\u00F3n Satelital"}));
		cmbTipoTv.setBounds(10, 85, 188, 20);
		panelTelevision.add(cmbTipoTv);
		
		panelMinutos = new JPanel();
		panelMinutos.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMinutos.setBounds(10, 513, 347, 138);
		contentPanel.add(panelMinutos);
		panelMinutos.setLayout(null);
		
		JLabel lblNewLabel_13 = new JLabel("Cantidad de minutos:");
		lblNewLabel_13.setBounds(10, 11, 146, 14);
		panelMinutos.add(lblNewLabel_13);
		
		spnCantMinutos = new JSpinner();
		spnCantMinutos.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spnCantMinutos.setBounds(10, 36, 207, 20);
		panelMinutos.add(spnCantMinutos);
		
		JLabel lblNewLabel_14 = new JLabel("Tipo:");
		lblNewLabel_14.setBounds(10, 67, 146, 14);
		panelMinutos.add(lblNewLabel_14);
		
		cmbTipoMinutos = new JComboBox();
		cmbTipoMinutos.setModel(new DefaultComboBoxModel(new String[] {"Minutos M\u00F3vil", "Minutos del Hogar"}));
		cmbTipoMinutos.setBounds(10, 92, 207, 20);
		panelMinutos.add(cmbTipoMinutos);
		
		panelInternet = new JPanel();
		panelInternet.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInternet.setBounds(10, 513, 347, 138);
		contentPanel.add(panelInternet);
		panelInternet.setLayout(null);
		
		JLabel lblNewLabel_10 = new JLabel("Velocidad (Mbps):");
		lblNewLabel_10.setBounds(10, 11, 147, 14);
		panelInternet.add(lblNewLabel_10);
		
		spnVelInter = new JSpinner();
		spnVelInter.setModel(new SpinnerNumberModel(0, 0, 0, 1));
		spnVelInter.setBounds(10, 36, 147, 20);
		panelInternet.add(spnVelInter);
		
		JLabel lblNewLabel_11 = new JLabel("Cantidad (Mb):");
		lblNewLabel_11.setBounds(179, 11, 108, 14);
		panelInternet.add(lblNewLabel_11);
		
		spnCantInter = new JSpinner();
		spnCantInter.setModel(new SpinnerNumberModel(0, 0, 0, 1));
		spnCantInter.setBounds(179, 36, 158, 20);
		panelInternet.add(spnCantInter);
		
		JLabel lblNewLabel_12 = new JLabel("Tipo de internet:");
		lblNewLabel_12.setBounds(10, 67, 108, 14);
		panelInternet.add(lblNewLabel_12);
		
		cmbTipoInter = new JComboBox();
		cmbTipoInter.setModel(new DefaultComboBoxModel(new String[] {"No seleccionado", "Internet M\u00F3vil", "Internet del Hogar"}));
		cmbTipoInter.setBounds(10, 92, 147, 20);
		panelInternet.add(cmbTipoInter);
		
		
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnEditarServ = new JButton("Modificar servicio");
			btnEditarServ.setEnabled(false);
			buttonPane.add(btnEditarServ);
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
	
	private void cargarServSel() {
		if(selected!=null) {
		txtCodigo.setText(selected.getCodigo());
		if(selected.getDuracion()==30) {
			cmbTipoFac.setSelectedIndex(1);
		}else if(selected.getDuracion()==365) {
			cmbTipoFac.setSelectedIndex(2);
		}else {
			cmbTipoFac.setSelectedIndex(3);
		}
		spn_DiasVigencia.setValue(selected.getDuracion());
		txtDescripcion.setText(selected.getDescripcion());
		if(selected instanceof Internet) {
			txtTipoServ.setText("Internet");
		}else if(selected instanceof Minutos) {
			txtTipoServ.setText("Minutos");
		}else {
			txtTipoServ.setText("Television");
		}
		
		if(selected instanceof Internet) {
			
			panelTelevision.setVisible(false);
			panelInternet.setVisible(true);
			panelMinutos.setVisible(false);
			
			spnVelInter.setValue(((Internet) selected).getVelocidad());
			spnCantInter.setValue(((Internet) selected).getCantMB());
			if(((Internet) selected).getTipo().equalsIgnoreCase("Internet Móvil")) {
			cmbTipoInter.setSelectedIndex(1);
			}else if(((Internet) selected).getTipo().equalsIgnoreCase("Internet del Hogar")) {
				cmbTipoInter.setSelectedIndex(2);
				}
		}else if(selected instanceof Minutos) {
			
			panelTelevision.setVisible(false);
			panelInternet.setVisible(false);
			panelMinutos.setVisible(true);
			
			spnCantMinutos.setValue(((Minutos) selected).getCantMins());
			if(((Minutos) selected).getTipo().equalsIgnoreCase("Minutos Móvil")) {
			cmbTipoMinutos.setSelectedIndex(0);
			}else if(((Minutos) selected).getTipo().equalsIgnoreCase("Minutos Móvil")) {
				cmbTipoMinutos.setSelectedIndex(1);
			}
		}else if(selected instanceof Television) {
			
			panelTelevision.setVisible(true);
			panelInternet.setVisible(false);
			panelMinutos.setVisible(false);
			
			spnCantCanales.setValue(((Television) selected).getCantCanales());
			if(((Television) selected).getTipo().equalsIgnoreCase("Televisión por Fibra")) {
				cmbTipoTv.setSelectedIndex(0);
			}else if(((Television) selected).getTipo().equalsIgnoreCase("Televisión por Cable")) {
				cmbTipoTv.setSelectedIndex(1);
			}
			
		}
		
		
		
		}
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
				if(servicio.getDuracion()==30) {
					row[2]="Mensual";
				}else if(servicio.getDuracion()==365) {
					row[2]="Anual";
				}else {
					row[2]="Agotable";
				}
				row[3]=servicio.getDuracion();
				row[4]=servicio.getPrecio();
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
						if(servicio.getDuracion()==30) {
							row[2]="Mensual";
						}else if(servicio.getDuracion()==365) {
							row[2]="Anual";
						}else {
							row[2]="Agotable";
						}
						row[3]=servicio.getDuracion();
						row[4]=servicio.getPrecio();
					model.addRow(row);
					}
				}
			}
		
	}
}
