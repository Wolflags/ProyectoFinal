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

public class RegistrarServicio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

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
		setBounds(100, 100, 472, 445);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 11, 446, 236);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("C\u00F3digo:");
			lblNewLabel.setBounds(10, 24, 56, 14);
			panel.add(lblNewLabel);
			
			textField = new JTextField();
			textField.setEditable(false);
			textField.setBounds(10, 44, 134, 20);
			panel.add(textField);
			textField.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Nombre del servicio:");
			lblNewLabel_1.setBounds(174, 24, 141, 14);
			panel.add(lblNewLabel_1);
			
			textField_1 = new JTextField();
			textField_1.setBounds(174, 44, 262, 20);
			panel.add(textField_1);
			textField_1.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel("Descripci\u00F3n:");
			lblNewLabel_2.setBounds(10, 126, 80, 14);
			panel.add(lblNewLabel_2);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 145, 426, 79);
			panel.add(scrollPane);
			
			JTextArea textArea = new JTextArea();
			scrollPane.setViewportView(textArea);
			
			JLabel lblNewLabel_3 = new JLabel("Tipo de facturaci\u00F3n:");
			lblNewLabel_3.setBounds(10, 75, 134, 14);
			panel.add(lblNewLabel_3);
			
			JRadioButton rdbtnNewRadioButton = new JRadioButton("Mensual");
			rdbtnNewRadioButton.setSelected(true);
			rdbtnNewRadioButton.setBounds(21, 96, 109, 23);
			panel.add(rdbtnNewRadioButton);
			
			JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Duraci\u00F3n espec\u00EDfica");
			rdbtnNewRadioButton_1.setBounds(151, 96, 141, 23);
			panel.add(rdbtnNewRadioButton_1);
			
			JSpinner spinner = new JSpinner();
			spinner.setEnabled(false);
			spinner.setModel(new SpinnerNumberModel(new Integer(30), new Integer(1), null, new Integer(1)));
			spinner.setBounds(313, 97, 109, 20);
			panel.add(spinner);
			
			JLabel lblNewLabel_4 = new JLabel("Dias vigencia:");
			lblNewLabel_4.setBounds(312, 75, 109, 14);
			panel.add(lblNewLabel_4);
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo de servicio:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 256, 446, 80);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Internet");
		rdbtnNewRadioButton_2.setSelected(true);
		rdbtnNewRadioButton_2.setBounds(29, 32, 109, 23);
		panel.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Minutos");
		rdbtnNewRadioButton_3.setBounds(167, 32, 109, 23);
		panel.add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("Television");
		rdbtnNewRadioButton_4.setBounds(305, 32, 109, 23);
		panel.add(rdbtnNewRadioButton_4);
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
