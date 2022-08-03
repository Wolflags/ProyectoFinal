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
import logico.Plan;
import logico.Servicio;
import logico.Television;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Spliterator;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import java.awt.Font;
import java.awt.SystemColor;

public class RegistrarPlan extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTextField txtId;
	private JTextField txtNombre;
	private static JTextField txtInternet;
	private static JTextField txtMinutos;
	private static JTextField txtTelevision;
	private static JTextField txtPrecio;
	private static JCheckBox cbxInternet;
	public static Servicio selected = null;
	private static int lastSelected = -1;
	private static JCheckBox cbxTelevision;
	private static JCheckBox cbxMinutos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarPlan dialog = new RegistrarPlan();
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarPlan() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
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
		setTitle("Nuevo Plan");
		setModal(true);
		setBounds(100, 100, 514, 466);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.menu);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n General:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 478, 96);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(10, 21, 46, 14);
		panel.add(lblNewLabel);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(66, 18, 163, 20);
		panel.add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(10, 55, 63, 14);
		panel.add(lblNewLabel_1);

		txtNombre = new JTextField();
		txtNombre.setBounds(66, 52, 384, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Servicios", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 113, 478, 265);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		cbxInternet = new JCheckBox("Internet");
		cbxInternet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxInternet.isSelected()) {
					lastSelected=0;
					ListadoServiciosModal lisServM = new ListadoServiciosModal(0);
					lisServM.setVisible(true);
					lisServM.setModal(true);
				}else {
					txtInternet.setText("No seleccionado");
				}
				cargarPrecio();
			}
		});
		cbxInternet.setBounds(46, 38, 97, 23);
		panel_1.add(cbxInternet);

		cbxMinutos = new JCheckBox("Minutos");
		cbxMinutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxMinutos.isSelected()) {
					lastSelected=1;
					ListadoServiciosModal lisServM = new ListadoServiciosModal(1);
					lisServM.setVisible(true);
					lisServM.setModal(true);
				}else {
					txtMinutos.setText("No seleccionado");
				}
				cargarPrecio();
			}
		});
		cbxMinutos.setBounds(189, 38, 97, 23);
		panel_1.add(cbxMinutos);

		cbxTelevision = new JCheckBox("Television");
		cbxTelevision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxTelevision.isSelected()) {
					lastSelected=2;
					ListadoServiciosModal lisServM = new ListadoServiciosModal(2);
					lisServM.setVisible(true);
					lisServM.setModal(true);
				}else {
					txtTelevision.setText("No seleccionado");
				}
				cargarPrecio();
			}
		});
		cbxTelevision.setBounds(332, 38, 97, 23);
		panel_1.add(cbxTelevision);

		JLabel lblNewLabel_2 = new JLabel("Servicio de internet:");
		lblNewLabel_2.setBounds(10, 80, 117, 14);
		panel_1.add(lblNewLabel_2);

		txtInternet = new JTextField();
		txtInternet.setText("No seleccionado");
		txtInternet.setEditable(false);
		txtInternet.setBounds(140, 77, 311, 20);
		panel_1.add(txtInternet);
		txtInternet.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Servicio de minutos:");
		lblNewLabel_3.setBounds(10, 118, 117, 14);
		panel_1.add(lblNewLabel_3);

		txtMinutos = new JTextField();
		txtMinutos.setText("No seleccionado");
		txtMinutos.setEditable(false);
		txtMinutos.setBounds(140, 115, 311, 20);
		panel_1.add(txtMinutos);
		txtMinutos.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Servicio de televisi\u00F3n:");
		lblNewLabel_4.setBounds(10, 158, 133, 14);
		panel_1.add(lblNewLabel_4);

		txtTelevision = new JTextField();
		txtTelevision.setText("No seleccionado");
		txtTelevision.setEditable(false);
		txtTelevision.setBounds(140, 155, 311, 20);
		panel_1.add(txtTelevision);
		txtTelevision.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Precio del plan:");
		lblNewLabel_5.setBounds(40, 193, 103, 14);
		panel_1.add(lblNewLabel_5);

		txtPrecio = new JTextField();
		txtPrecio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtPrecio.setText("0.00");
		txtPrecio.setEditable(false);
		txtPrecio.setBounds(140, 190, 162, 20);
		panel_1.add(txtPrecio);
		txtPrecio.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Crear Plan");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(txtNombre.getText().length()>3) {
							if(cbxInternet.isSelected()||cbxMinutos.isSelected()||cbxTelevision.isSelected()) {
								Plan auxPlan = null;
								ArrayList<Servicio> auxServicios = new ArrayList<Servicio>();
								auxServicios.add(null);

								if(cbxInternet.isSelected()) {
									String[] split = txtInternet.getText().split(" ");
									auxServicios.add(0, Altice.getInstance().buscarServicioByCod(split[0]));
								}else
								{
									auxServicios.add(0, null);
								}

								if(cbxMinutos.isSelected()) {
									String[] split = txtMinutos.getText().split(" ");
									auxServicios.add(1, Altice.getInstance().buscarServicioByCod(split[0]));
								}else {
									auxServicios.add(1, null);
								}

								if(cbxTelevision.isSelected()) {
									String[] split = txtTelevision.getText().split(" ");
									auxServicios.add(2, Altice.getInstance().buscarServicioByCod(split[0]));
								}else {
									auxServicios.add(2, null);
								}

								if(!auxServicios.isEmpty()){
									auxPlan = new Plan("P-"+Altice.getInstance().getGenIdPlan(),txtNombre.getText(),auxServicios,Float.parseFloat(txtPrecio.getText().toString()));

								}else {
									//JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un servicio!", "Error", ERROR);
								}
								if(auxPlan!=null){
									Altice.getInstance().insertarPlan(auxPlan);
									JOptionPane.showMessageDialog(null, "Registro existoso.", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
									clear();
								}
							}else {
								JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un servicio.", "Advertencia", JOptionPane.WARNING_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(null, "Por favor ingrese un nombre válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
						}
					}

					private void clear() {
						setCancel(0);
						setCancel(1);
						setCancel(2);
						txtNombre.setText("");
						txtId.setText("P-"+Altice.getInstance().getGenIdPlan());
						selected = null;
						lastSelected = -1;
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
		cargarDatos();
	}

	public static void cargarDatos() {

		txtId.setText("P-"+Altice.getInstance().getGenIdPlan());

		if(lastSelected==0) {
			Internet auxint = (Internet) selected;
			txtInternet.setText(selected.getCodigo()+" || "+auxint.getCantMB()+"MB"+" || "+auxint.getVelocidad()+"Mbps"+" || "+auxint.getDuracion()+" Dias"+" || "+selected.getPrecio());
		}else if(lastSelected==1) {
			Minutos auxmins = (Minutos) selected;
			txtMinutos.setText(selected.getCodigo()+" || "+auxmins.getCantMins()+" Minutos"+" || "+auxmins.getDuracion()+" Dias"+" || "+selected.getPrecio());
		}else if(lastSelected==2) {
			Television auxtv = (Television) selected;
			txtTelevision.setText(selected.getCodigo()+" || "+auxtv.getCantCanales()+" Canales"+" || "+auxtv.getDuracion()+" Dias"+" || "+selected.getPrecio());
		}
		cargarPrecio();

	}

	public static void setCancel(int tipo) {
		if(tipo==0) {
			txtInternet.setText("No seleccionado");
			cbxInternet.setSelected(false);
		}else if(tipo==1) {
			txtMinutos.setText("No seleccionado");
			cbxMinutos.setSelected(false);
		}else {
			txtTelevision.setText("No seleccionado");
			cbxTelevision.setSelected(false);
		}
		cargarPrecio();

	}

	private static void cargarPrecio() {
		float precio = 0;
		if(cbxInternet.isSelected()) {
			String[] split = txtInternet.getText().split(" ");
			if(Altice.getInstance().buscarServicioByCod(split[0])!=null) {
				precio = precio + Altice.getInstance().buscarServicioByCod(split[0]).getPrecio();
			}
		} 
		if(cbxMinutos.isSelected()) {
			String[] split = txtMinutos.getText().split(" ");
			if(Altice.getInstance().buscarServicioByCod(split[0])!=null) {
				precio = precio + Altice.getInstance().buscarServicioByCod(split[0]).getPrecio();
			}
		} 
		if(cbxTelevision.isSelected()) {
			String[] split = txtTelevision.getText().split(" ");
			if(Altice.getInstance().buscarServicioByCod(split[0])!=null) {
				precio = precio + Altice.getInstance().buscarServicioByCod(split[0]).getPrecio();
			}
		}
		txtPrecio.setText(precio+"");

	}
}
