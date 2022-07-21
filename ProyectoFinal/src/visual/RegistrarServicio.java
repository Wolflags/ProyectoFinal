package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
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

public class RegistrarServicio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPanel panel_minutos;
	private JPanel panel_internet;
	private JPanel panel_tv;
	private JRadioButton rdbInternet;
	private JRadioButton rdbMinutos;
	private JRadioButton rdbTelevision;
	private JSpinner spn_diasVigencia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarServicio dialog = new RegistrarServicio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarServicio() {
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
			
			textField = new JTextField();
			textField.setEditable(false);
			textField.setBounds(21, 44, 116, 20);
			panel.add(textField);
			textField.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel("Descripci\u00F3n:");
			lblNewLabel_2.setBounds(10, 75, 80, 14);
			panel.add(lblNewLabel_2);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 94, 426, 86);
			panel.add(scrollPane);
			
			JTextArea textArea = new JTextArea();
			scrollPane.setViewportView(textArea);
			
			JLabel lblNewLabel_3 = new JLabel("Tipo de facturaci\u00F3n:");
			lblNewLabel_3.setBounds(158, 24, 134, 14);
			panel.add(lblNewLabel_3);
			
			spn_diasVigencia = new JSpinner();
			spn_diasVigencia.setModel(new SpinnerNumberModel(new Integer(30), new Integer(1), null, new Integer(1)));
			spn_diasVigencia.setBounds(313, 44, 109, 20);
			panel.add(spn_diasVigencia);
			
			JLabel lblNewLabel_4 = new JLabel("Dias vigencia:");
			lblNewLabel_4.setBounds(313, 24, 109, 14);
			panel.add(lblNewLabel_4);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Mensual", "Anual", "Agotable"}));
			comboBox.setBounds(158, 44, 134, 20);
			panel.add(comboBox);
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
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setBounds(54, 29, 141, 20);
		panel_tv.add(spinner_3);
		
		JLabel label = new JLabel("Tipo:");
		label.setBounds(249, 11, 46, 14);
		panel_tv.add(label);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Televisi\u00F3n por Fibra", "Televisi\u00F3n Satelital"}));
		comboBox_2.setBounds(249, 29, 141, 20);
		panel_tv.add(comboBox_2);
		
		panel_minutos = new JPanel();
		panel_minutos.setVisible(false);
		panel_minutos.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_minutos.setBounds(10, 298, 446, 74);
		contentPanel.add(panel_minutos);
		panel_minutos.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Cantidad de minutos:");
		lblNewLabel_8.setBounds(54, 11, 126, 14);
		panel_minutos.add(lblNewLabel_8);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(54, 29, 141, 20);
		panel_minutos.add(spinner_2);
		
		JLabel lblNewLabel_9 = new JLabel("Tipo:");
		lblNewLabel_9.setBounds(249, 11, 46, 14);
		panel_minutos.add(lblNewLabel_9);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Minutos M\u00F3vil", "Minutos del Hogar"}));
		comboBox_1.setBounds(249, 29, 141, 20);
		panel_minutos.add(comboBox_1);
		
		panel_internet = new JPanel();
		panel_internet.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_internet.setBounds(10, 298, 446, 74);
		contentPanel.add(panel_internet);
		panel_internet.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Velocidad (Mbps):");
		lblNewLabel_5.setBounds(17, 11, 110, 14);
		panel_internet.add(lblNewLabel_5);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(17, 29, 125, 20);
		panel_internet.add(spinner);
		
		JLabel lblNewLabel_6 = new JLabel("Cantidad (Mb):");
		lblNewLabel_6.setBounds(159, 11, 92, 14);
		panel_internet.add(lblNewLabel_6);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(159, 29, 125, 20);
		panel_internet.add(spinner_1);
		
		JLabel lblNewLabel_7 = new JLabel("Tipo:");
		lblNewLabel_7.setBounds(301, 11, 92, 14);
		panel_internet.add(lblNewLabel_7);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Internet M\u00F3vil", "Internet del Hogar"}));
		comboBox.setBounds(301, 29, 125, 20);
		panel_internet.add(comboBox);
		
		
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
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
		// TODO Auto-generated method stub
		spn_diasVigencia.setEnabled(false);
		
	}
}
