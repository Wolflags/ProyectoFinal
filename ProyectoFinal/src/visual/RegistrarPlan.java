package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import java.awt.Font;

public class RegistrarPlan extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtNoSeleccionado;
	private JTextField txtNoSeleccionado_1;
	private JTextField txtNoSeleccionado_2;
	private JTextField textField_2;
	private JCheckBox cbxInternet;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarPlan dialog = new RegistrarPlan();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarPlan() {
		setTitle("Nuevo Plan");
		setModal(true);
		setBounds(100, 100, 450, 430);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n General:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 414, 96);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(10, 21, 46, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(66, 18, 142, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(10, 55, 63, 14);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(66, 52, 336, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Servicios", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 113, 414, 229);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		cbxInternet = new JCheckBox("Internet");
		cbxInternet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxInternet.isSelected()) {
				System.out.println("S");
				}
			}
		});
		cbxInternet.setBounds(30, 38, 97, 23);
		panel_1.add(cbxInternet);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Minutos");
		chckbxNewCheckBox_1.setBounds(157, 38, 97, 23);
		panel_1.add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Television");
		chckbxNewCheckBox_2.setBounds(284, 38, 97, 23);
		panel_1.add(chckbxNewCheckBox_2);
		
		JLabel lblNewLabel_2 = new JLabel("Servicio de internet:");
		lblNewLabel_2.setBounds(10, 80, 117, 14);
		panel_1.add(lblNewLabel_2);
		
		txtNoSeleccionado = new JTextField();
		txtNoSeleccionado.setText("No seleccionado");
		txtNoSeleccionado.setEditable(false);
		txtNoSeleccionado.setBounds(140, 77, 264, 20);
		panel_1.add(txtNoSeleccionado);
		txtNoSeleccionado.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Servicio de minutos:");
		lblNewLabel_3.setBounds(10, 118, 117, 14);
		panel_1.add(lblNewLabel_3);
		
		txtNoSeleccionado_1 = new JTextField();
		txtNoSeleccionado_1.setText("No seleccionado");
		txtNoSeleccionado_1.setEditable(false);
		txtNoSeleccionado_1.setBounds(140, 115, 264, 20);
		panel_1.add(txtNoSeleccionado_1);
		txtNoSeleccionado_1.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Servicio de televisi\u00F3n:");
		lblNewLabel_4.setBounds(10, 158, 133, 14);
		panel_1.add(lblNewLabel_4);
		
		txtNoSeleccionado_2 = new JTextField();
		txtNoSeleccionado_2.setText("No seleccionado");
		txtNoSeleccionado_2.setEditable(false);
		txtNoSeleccionado_2.setBounds(140, 155, 264, 20);
		panel_1.add(txtNoSeleccionado_2);
		txtNoSeleccionado_2.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Precio del plan:");
		lblNewLabel_5.setBounds(40, 193, 103, 14);
		panel_1.add(lblNewLabel_5);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_2.setText("0.00");
		textField_2.setEditable(false);
		textField_2.setBounds(140, 190, 114, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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
	}
}
