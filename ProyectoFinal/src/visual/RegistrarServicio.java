package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Altice;
import logico.Internet;
import logico.Minutos;
import logico.Servicio;
import logico.Television;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegistrarServicio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JPanel panel_minutos;
	private JPanel panel_internet;
	private JPanel panel_tv;
	private JRadioButton rdbInternet;
	private JRadioButton rdbMinutos;
	private JRadioButton rdbTelevision;
	private JSpinner spn_diasVigencia;
	private JSpinner spn_velocidad;
	private JComboBox cmbTipoMinutos;
	private JSpinner spn_cantMin;
	private JComboBox cmbTipoTv;
	private JSpinner spn_cantCanales;
	private JComboBox cmbTipoFac;
	private JTextArea txtDescripcion;
	private boolean autocobro = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarServicio dialog = new RegistrarServicio();
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarServicio() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int opc = JOptionPane.showConfirmDialog(null,"Está seguro que desea salir?","Advertencia",JOptionPane.YES_NO_OPTION);
				if(opc==0) {
					dispose();
				}else {
					
				}
			}
		});
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setTitle("RegistrarServicio");
		setResizable(false);
		setBounds(100, 100, 472, 456);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 11, 446, 191);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("C\u00F3digo:");
			lblNewLabel.setBounds(21, 24, 56, 14);
			panel.add(lblNewLabel);
			
			txtCodigo = new JTextField();
			txtCodigo.setEditable(false);
			txtCodigo.setBounds(21, 44, 116, 20);
			panel.add(txtCodigo);
			txtCodigo.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel("Descripci\u00F3n:");
			lblNewLabel_2.setBounds(10, 75, 80, 14);
			panel.add(lblNewLabel_2);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 94, 426, 86);
			panel.add(scrollPane);
			
			txtDescripcion = new JTextArea();
			scrollPane.setViewportView(txtDescripcion);
			
			JLabel lblNewLabel_3 = new JLabel("Tipo de facturaci\u00F3n:");
			lblNewLabel_3.setBounds(158, 24, 134, 14);
			panel.add(lblNewLabel_3);
			
			spn_diasVigencia = new JSpinner();
			spn_diasVigencia.setModel(new SpinnerNumberModel(30, 30, 30, 1));
			cmbTipoFac = new JComboBox();
			cmbTipoFac.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						if(cmbTipoFac.getSelectedIndex()==0) {
						spn_diasVigencia.setModel(new SpinnerNumberModel(30, 30, 30, 1));
						}else if(cmbTipoFac.getSelectedIndex()==1) {
						spn_diasVigencia.setModel(new SpinnerNumberModel(365, 365, 365, 1));
						}else {
						spn_diasVigencia.setModel(new SpinnerNumberModel(1, 1, null, 1));
						}
				}
			});
			cmbTipoFac.setEditable(true);
			cmbTipoFac.setModel(new DefaultComboBoxModel(new String[] {"Mensual", "Anual", "Agotable"}));
			cmbTipoFac.setBounds(158, 44, 134, 20);
			panel.add(cmbTipoFac);
			
			
			spn_diasVigencia.setBounds(313, 44, 109, 20);
			panel.add(spn_diasVigencia);
			
			JLabel lblNewLabel_4 = new JLabel("Dias vigencia:");
			lblNewLabel_4.setBounds(313, 24, 109, 14);
			panel.add(lblNewLabel_4);
			
			
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo de servicio:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 207, 446, 80);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		rdbInternet = new JRadioButton("Internet");
		rdbInternet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbInternet.isSelected()) {
					rdbInternet.setSelected(true);
					rdbMinutos.setSelected(false);
					rdbTelevision.setSelected(false);
					panel_internet.setVisible(true);
					panel_tv.setVisible(false);
					panel_minutos.setVisible(false);
				}
			}
		});
		rdbInternet.setSelected(true);
		rdbInternet.setBounds(29, 32, 109, 23);
		panel.add(rdbInternet);
		
		rdbMinutos = new JRadioButton("Minutos");
		rdbMinutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbMinutos.isSelected()) {
					rdbInternet.setSelected(false);
					rdbMinutos.setSelected(true);
					rdbTelevision.setSelected(false);
					panel_internet.setVisible(false);
					panel_tv.setVisible(false);
					panel_minutos.setVisible(true);
				}
			}
		});
		rdbMinutos.setBounds(167, 32, 109, 23);
		panel.add(rdbMinutos);
		
		rdbTelevision = new JRadioButton("Television");
		rdbTelevision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbTelevision.isSelected()) {
					rdbInternet.setSelected(false);
					rdbMinutos.setSelected(false);
					rdbTelevision.setSelected(true);
					panel_internet.setVisible(false);
					panel_tv.setVisible(true);
					panel_minutos.setVisible(false);
				}
			}
		});
		rdbTelevision.setBounds(305, 32, 109, 23);
		panel.add(rdbTelevision);
		
		panel_tv = new JPanel();
		panel_tv.setVisible(false);
		panel_tv.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_tv.setBounds(10, 298, 446, 74);
		contentPanel.add(panel_tv);
		panel_tv.setLayout(null);
		
		JLabel lblNewLabel_10 = new JLabel("Cantidad de canales:");
		lblNewLabel_10.setBounds(54, 11, 126, 14);
		panel_tv.add(lblNewLabel_10);
		
		spn_cantCanales = new JSpinner();
		spn_cantCanales.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spn_cantCanales.setBounds(54, 29, 141, 20);
		panel_tv.add(spn_cantCanales);
		
		JLabel label = new JLabel("Tipo:");
		label.setBounds(249, 11, 46, 14);
		panel_tv.add(label);
		
		cmbTipoTv = new JComboBox();
		cmbTipoTv.setModel(new DefaultComboBoxModel(new String[] {"Televisi\u00F3n por Fibra", "Televisi\u00F3n Satelital"}));
		cmbTipoTv.setBounds(249, 29, 141, 20);
		panel_tv.add(cmbTipoTv);
		
		panel_minutos = new JPanel();
		panel_minutos.setVisible(false);
		panel_minutos.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_minutos.setBounds(10, 298, 446, 74);
		contentPanel.add(panel_minutos);
		panel_minutos.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Cantidad de minutos:");
		lblNewLabel_8.setBounds(54, 11, 126, 14);
		panel_minutos.add(lblNewLabel_8);
		
		spn_cantMin = new JSpinner();
		spn_cantMin.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spn_cantMin.setBounds(54, 29, 141, 20);
		panel_minutos.add(spn_cantMin);
		
		JLabel lblNewLabel_9 = new JLabel("Tipo:");
		lblNewLabel_9.setBounds(249, 11, 46, 14);
		panel_minutos.add(lblNewLabel_9);
		
		cmbTipoMinutos = new JComboBox();
		cmbTipoMinutos.setModel(new DefaultComboBoxModel(new String[] {"Minutos M\u00F3vil", "Minutos del Hogar"}));
		cmbTipoMinutos.setBounds(249, 29, 141, 20);
		panel_minutos.add(cmbTipoMinutos);
		
		panel_internet = new JPanel();
		panel_internet.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_internet.setBounds(10, 298, 446, 74);
		contentPanel.add(panel_internet);
		panel_internet.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Velocidad (Mbps):");
		lblNewLabel_5.setBounds(17, 11, 110, 14);
		panel_internet.add(lblNewLabel_5);
		
		spn_velocidad = new JSpinner();
		spn_velocidad.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spn_velocidad.setBounds(17, 29, 125, 20);
		panel_internet.add(spn_velocidad);
		
		JLabel lblNewLabel_6 = new JLabel("Cantidad (Mb):");
		lblNewLabel_6.setBounds(159, 11, 92, 14);
		panel_internet.add(lblNewLabel_6);
		
		JSpinner spn_cantMb = new JSpinner();
		spn_cantMb.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spn_cantMb.setBounds(159, 29, 125, 20);
		panel_internet.add(spn_cantMb);
		
		JLabel lblNewLabel_7 = new JLabel("Tipo:");
		lblNewLabel_7.setBounds(301, 11, 92, 14);
		panel_internet.add(lblNewLabel_7);
		
		JComboBox cmbTipoInternet = new JComboBox();
		cmbTipoInternet.setModel(new DefaultComboBoxModel(new String[] {"Internet M\u00F3vil", "Internet del Hogar"}));
		cmbTipoInternet.setBounds(301, 29, 125, 20);
		panel_internet.add(cmbTipoInternet);
		
		
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txtDescripcion.getText().length()>5) {
						Servicio auxServicio = null;
						if(cmbTipoFac.getSelectedIndex()==2) {
							autocobro=false;
						}
						if(rdbInternet.isSelected()) {
							auxServicio = new Internet(txtCodigo.getText(), txtDescripcion.getText(), Integer.parseInt(spn_diasVigencia.getValue().toString()), autocobro, Integer.parseInt(spn_velocidad.getValue().toString()), Integer.parseInt(spn_cantMb.getValue().toString()), cmbTipoInternet.getSelectedItem().toString());							
						}else if(rdbMinutos.isSelected()) {
							auxServicio = new Minutos(txtCodigo.getText(), txtDescripcion.getText(), Integer.parseInt(spn_diasVigencia.getValue().toString()), autocobro, Integer.parseInt(spn_cantMin.getValue().toString()),cmbTipoMinutos.getSelectedItem().toString());
						}else if(rdbTelevision.isSelected()) {
							auxServicio = new Television(txtCodigo.getText(), txtDescripcion.getText(), Integer.parseInt(spn_diasVigencia.getValue().toString()), autocobro, Integer.parseInt(spn_cantCanales.getValue().toString()),cmbTipoTv.getSelectedItem().toString());
						}
						if(auxServicio!=null){
						Altice.getInstance().getServicios().add(auxServicio);
						Servicio.genIdServicio++;
						JOptionPane.showMessageDialog(null, "Registro existoso.", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
						clear();
						}
						}else {
							JOptionPane.showMessageDialog(null, "Por favor ingrese una descripción válida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
						}
					}

					private void clear() {
						txtDescripcion.setText("");
						cargar();
						cmbTipoFac.setSelectedIndex(0);
						spn_diasVigencia.setModel(new SpinnerNumberModel(30, 30, 30, 1));
						autocobro=false;
						rdbInternet.setSelected(true);
						rdbMinutos.setSelected(false);
						rdbTelevision.setSelected(false);
						panel_internet.setVisible(true);
						panel_tv.setVisible(false);
						panel_minutos.setVisible(false);
						spn_cantCanales.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
						spn_cantMin.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
						spn_velocidad.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
						spn_cantMb.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
						cmbTipoInternet.setSelectedIndex(0);
						cmbTipoMinutos.setSelectedIndex(0);
						cmbTipoTv.setSelectedIndex(0);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		cargar();
	}
	

	private void cargar() {
		txtCodigo.setText("S-"+Servicio.genIdServicio);
		
	}
}
