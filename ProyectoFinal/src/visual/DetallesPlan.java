package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Altice;
import logico.Empleado;
import logico.Internet;
import logico.Minutos;
import logico.Plan;
import logico.Servicio;
import logico.Television;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class DetallesPlan extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static Plan auxPlan = null;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JSpinner spnPrecio;
	private JTextField txtInternet;
	private JTextField txtMinutos;
	private JTextField txtTelevision;
	private JTable tableInternet;
	private JTable tableMinutos;
	private JTable tableTelevision;
	private static DefaultTableModel modelInternet;
	private static DefaultTableModel modelMinutos;
	private static DefaultTableModel modelTelevision;
	private static Object[] row;
	private static Servicio selected = null;
	private JPanel panelAgregarInternet;
	private JPanel panelAgregarMinutos;
	private JPanel panelAgregarTelevision;
	private static JComboBox cbxTipoServicio;
	private JLabel lblTipoServicio;
	private JButton btnAplicar;
	private JButton btnVerServicioInternet;
	private JButton btnVerServicioMinutos;
	private JButton btnVerServicioTelevision;
	private JLabel lblImagenInternet;
	private JLabel lblImagenMinutos;
	private JLabel lblImagenTelevision;
	private JButton btnModificar;
	private JButton btnAgregarServicio;
	private String modo;
	private Empleado auxEmpleado = null;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			Servicio s1 = null;
			Servicio s2 = null;
			Servicio s3 = null;
			s1 = new Internet("S-1", "Desc1", 50, 100, "Móvil");
			s2 = new Television("S-2", "Desc2", 150, "Hogar");
			s3 = new Internet("S-3", "Desc3", 100, 200, "Hogar");
			Altice.getInstance().getServicios().add(s1);
			Altice.getInstance().getServicios().add(s2);
			Altice.getInstance().getServicios().add(s3);
			ArrayList<Servicio> serviciosEjemplo = new ArrayList<Servicio>();
			serviciosEjemplo.add(null);
			serviciosEjemplo.add(null);
			serviciosEjemplo.add(null);
			serviciosEjemplo.set(0, s1);
			//serviciosEjemplo.set(2, s2);
			Plan p1 = new Plan("1234", "Ejemplo", serviciosEjemplo, (float)2300);
			DetallesPlan dialog = new DetallesPlan(p1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.///
	 */
	public DetallesPlan(Plan plan, Empleado empleado) {
		auxEmpleado = empleado;
		setIconImage(Toolkit.getDefaultToolkit().getImage(DetallesPlan.class.getResource("/media/imgPlan32px.png")));
		auxPlan = plan;
		setTitle("Detalles del plan");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 960, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelBotones = new JPanel();
			panelBotones.setLayout(null);
			panelBotones.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelBotones.setBounds(10, 11, 247, 369);
			contentPanel.add(panelBotones);
			{
				btnModificar = new JButton("     Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						modo = "Modificar";
						btnModificar.setEnabled(false);
						btnAgregarServicio.setEnabled(false);
						btnAplicar.setEnabled(true);
						txtNombre.setEditable(true);
					}
				});
				btnModificar.setIcon(new ImageIcon(DetallesPlan.class.getResource("/media/imgModificar32px.png")));
				btnModificar.setHorizontalAlignment(SwingConstants.LEADING);
				btnModificar.setBackground(Color.WHITE);
				btnModificar.setBounds(23, 229, 201, 50);
				panelBotones.add(btnModificar);
				if(auxEmpleado.getTipoEmpleado().equalsIgnoreCase("Empleado")) {
					btnModificar.setEnabled(false);
				}
			}
			
			btnAgregarServicio = new JButton("     Agregar servicio");
			btnAgregarServicio.setIcon(new ImageIcon(DetallesPlan.class.getResource("/media/imgAgregarServicio32px.png")));
			btnAgregarServicio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					modo = "Agregar";
					btnModificar.setEnabled(false);
					btnAgregarServicio.setEnabled(false);
					lblTipoServicio.setVisible(true);
					cbxTipoServicio.setVisible(true);
					loadServicios();
				}
			});
			btnAgregarServicio.setHorizontalAlignment(SwingConstants.LEADING);
			btnAgregarServicio.setBackground(Color.WHITE);
			btnAgregarServicio.setBounds(23, 290, 201, 50);
			panelBotones.add(btnAgregarServicio);
			if(auxEmpleado.getTipoEmpleado().equalsIgnoreCase("Empleado")) {
				btnAgregarServicio.setEnabled(false);
			}
			
			lblImagenInternet = new JLabel("");
			if(auxPlan.getMisServicios().get(0) != null) {
				lblImagenInternet.setIcon(new ImageIcon(DetallesPlan.class.getResource("/media/imgInternet64px.png")));
			}
			else {
				lblImagenInternet.setIcon(new ImageIcon(DetallesPlan.class.getResource("/media/imgInternet64pxN.png")));
			}
			lblImagenInternet.setHorizontalAlignment(SwingConstants.CENTER);
			lblImagenInternet.setBounds(88, 11, 70, 70);
			panelBotones.add(lblImagenInternet);
			
			lblImagenMinutos = new JLabel("");
			if(auxPlan.getMisServicios().get(1) != null) {
				lblImagenMinutos.setIcon(new ImageIcon(DetallesPlan.class.getResource("/media/imgMinutos64px.png")));
			}
			else {
				lblImagenMinutos.setIcon(new ImageIcon(DetallesPlan.class.getResource("/media/imgMinutos64pxN.png")));
			}
			lblImagenMinutos.setHorizontalAlignment(SwingConstants.CENTER);
			lblImagenMinutos.setBounds(37, 92, 70, 70);
			panelBotones.add(lblImagenMinutos);
			
			lblImagenTelevision = new JLabel("");
			if(auxPlan.getMisServicios().get(2) != null) {
				lblImagenTelevision.setIcon(new ImageIcon(DetallesPlan.class.getResource("/media/imgTelevision64px.png")));
			}
			else {
				lblImagenTelevision.setIcon(new ImageIcon(DetallesPlan.class.getResource("/media/imgTelevision64pxN.png")));
			}
			lblImagenTelevision.setHorizontalAlignment(SwingConstants.CENTER);
			lblImagenTelevision.setBounds(140, 92, 70, 70);
			panelBotones.add(lblImagenTelevision);
		}
		{
			JPanel panelInfoGeneral = new JPanel();
			panelInfoGeneral.setLayout(null);
			panelInfoGeneral.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n general", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelInfoGeneral.setBounds(267, 11, 667, 174);
			contentPanel.add(panelInfoGeneral);
			{
				JLabel lblCodigo = new JLabel("C\u00F3digo:");
				lblCodigo.setBounds(10, 25, 79, 14);
				panelInfoGeneral.add(lblCodigo);
			}
			{
				txtCodigo = new JTextField();
				txtCodigo.setText(auxPlan.getIdplan());
				txtCodigo.setEditable(false);
				txtCodigo.setColumns(10);
				txtCodigo.setBounds(77, 22, 230, 20);
				panelInfoGeneral.add(txtCodigo);
			}
			{
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setBounds(333, 25, 79, 14);
				panelInfoGeneral.add(lblNombre);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setText(auxPlan.getNombre());
				txtNombre.setEditable(false);
				txtNombre.setBounds(405, 22, 252, 20);
				panelInfoGeneral.add(txtNombre);
				txtNombre.setColumns(10);
			}
			{
				JLabel lblPrecio = new JLabel("Precio:");
				lblPrecio.setBounds(10, 53, 79, 14);
				panelInfoGeneral.add(lblPrecio);
			}
			
			spnPrecio = new JSpinner();
			spnPrecio.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(50)));
			spnPrecio.setEnabled(false);
			spnPrecio.setValue(auxPlan.getPrecio());
			spnPrecio.setBounds(77, 50, 230, 20);
			panelInfoGeneral.add(spnPrecio);
			
			JLabel lblInternet = new JLabel("Internet:");
			lblInternet.setBounds(10, 78, 66, 14);
			panelInfoGeneral.add(lblInternet);
			
			txtInternet = new JTextField();
			txtInternet.setText("");
			for(Servicio servicio : auxPlan.getMisServicios()) {
				if(servicio instanceof Internet) {
					txtInternet.setText(servicio.getCodigo());
				}
			}
			txtInternet.setEditable(false);
			txtInternet.setBounds(77, 75, 230, 20);
			panelInfoGeneral.add(txtInternet);
			txtInternet.setColumns(10);
			
			JLabel lblMinutos = new JLabel("Minutos:");
			lblMinutos.setBounds(10, 106, 66, 14);
			panelInfoGeneral.add(lblMinutos);
			
			txtMinutos = new JTextField();
			txtMinutos.setText("");
			for(Servicio servicio : auxPlan.getMisServicios()) {
				if(servicio instanceof Minutos) {
					txtMinutos.setText(servicio.getCodigo());
				}
			}
			txtMinutos.setEditable(false);
			txtMinutos.setColumns(10);
			txtMinutos.setBounds(77, 103, 230, 20);
			panelInfoGeneral.add(txtMinutos);
			
			JLabel lblTelevision = new JLabel("Televisi\u00F3n:");
			lblTelevision.setBounds(10, 134, 66, 14);
			panelInfoGeneral.add(lblTelevision);
			
			txtTelevision = new JTextField();
			txtTelevision.setText("");
			for(Servicio servicio : auxPlan.getMisServicios()) {
				if(servicio instanceof Television) {
					txtTelevision.setText(servicio.getCodigo());
				}
			}
			txtTelevision.setEditable(false);
			txtTelevision.setColumns(10);
			txtTelevision.setBounds(77, 131, 230, 20);
			panelInfoGeneral.add(txtTelevision);
			
			btnVerServicioInternet = new JButton("Ver servicio");
			btnVerServicioInternet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DetallesServicio detServicio = new DetallesServicio(auxPlan.getMisServicios().get(0), auxEmpleado);
					detServicio.setVisible(true);
				}
			});
			btnVerServicioInternet.setEnabled(false);
			btnVerServicioInternet.setBackground(Color.WHITE);
			btnVerServicioInternet.setBounds(333, 74, 108, 23);
			panelInfoGeneral.add(btnVerServicioInternet);
			
			btnVerServicioMinutos = new JButton("Ver servicio");
			btnVerServicioMinutos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DetallesServicio detServicio = new DetallesServicio(auxPlan.getMisServicios().get(1), auxEmpleado);
					detServicio.setVisible(true);
				}
			});
			btnVerServicioMinutos.setEnabled(false);
			btnVerServicioMinutos.setBackground(Color.WHITE);
			btnVerServicioMinutos.setBounds(333, 102, 108, 23);
			panelInfoGeneral.add(btnVerServicioMinutos);
			
			btnVerServicioTelevision = new JButton("Ver servicio");
			btnVerServicioTelevision.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DetallesServicio detServicio = new DetallesServicio(auxPlan.getMisServicios().get(2), auxEmpleado);
					detServicio.setVisible(true);
				}
			});
			btnVerServicioTelevision.setEnabled(false);
			btnVerServicioTelevision.setBackground(Color.WHITE);
			btnVerServicioTelevision.setBounds(333, 130, 108, 23);
			panelInfoGeneral.add(btnVerServicioTelevision);
			
			loadBotones();
		}
		{
			panelAgregarInternet = new JPanel();
			panelAgregarInternet.setVisible(false);
			panelAgregarInternet.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelAgregarInternet.setBounds(267, 258, 667, 122);
			contentPanel.add(panelAgregarInternet);
			panelAgregarInternet.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panelAgregarInternet.add(scrollPane, BorderLayout.CENTER);
			
			{
				tableInternet = new JTable();
				tableInternet.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = tableInternet.getSelectedRow();
						if(index >= 0) {
							String codigo = tableInternet.getValueAt(index, 0).toString();
							selected = Altice.getInstance().buscarServicioByCod(codigo);
							btnAplicar.setEnabled(true);
						}
					}
				});
				tableInternet.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				modelInternet = new DefaultTableModel();
				String[] headers = {"Código", "Velocidad", "Cantidad MB", "Tipo"};
				modelInternet.setColumnIdentifiers(headers);
				tableInternet.setModel(modelInternet);
				scrollPane.setViewportView(tableInternet);
			}
		}
		
		{
			panelAgregarMinutos = new JPanel();
			panelAgregarMinutos.setVisible(false);
			panelAgregarMinutos.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelAgregarMinutos.setBounds(267, 258, 667, 122);
			contentPanel.add(panelAgregarMinutos);
			panelAgregarMinutos.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panelAgregarMinutos.add(scrollPane, BorderLayout.CENTER);
			
			{
				tableMinutos = new JTable();
				tableMinutos.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = tableMinutos.getSelectedRow();
						if(index >= 0) {
							String codigo = tableMinutos.getValueAt(index, 0).toString();
							selected = Altice.getInstance().buscarServicioByCod(codigo);
							btnAplicar.setEnabled(true);
						}
					}
				});
				tableMinutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				modelMinutos = new DefaultTableModel();
				String[] headers = {"Código", "Duración", "Cantidad de minutos", "Tipo"};
				modelMinutos.setColumnIdentifiers(headers);
				tableMinutos.setModel(modelMinutos);
				scrollPane.setViewportView(tableMinutos);
			}
		}
		
		{
			panelAgregarTelevision = new JPanel();
			panelAgregarTelevision.setVisible(false);
			panelAgregarTelevision.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelAgregarTelevision.setBounds(267, 258, 667, 122);
			contentPanel.add(panelAgregarTelevision);
			panelAgregarTelevision.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panelAgregarTelevision.add(scrollPane, BorderLayout.CENTER);
			
			{
				tableTelevision = new JTable();
				tableTelevision.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = tableTelevision.getSelectedRow();
						if(index >= 0) {
							String codigo = tableTelevision.getValueAt(index, 0).toString();
							selected = Altice.getInstance().buscarServicioByCod(codigo);
							btnAplicar.setEnabled(true);
						}
					}
				});
				tableTelevision.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				modelTelevision = new DefaultTableModel();
				String[] headers = {"Código", "Duración", "Cantidad de canales", "Tipo"};
				modelTelevision.setColumnIdentifiers(headers);
				tableTelevision.setModel(modelTelevision);
				scrollPane.setViewportView(tableTelevision);
			}
		}
		
		lblTipoServicio = new JLabel("Tipo de servicio:");
		lblTipoServicio.setVisible(false);
		lblTipoServicio.setBounds(267, 215, 109, 14);
		contentPanel.add(lblTipoServicio);
		
		cbxTipoServicio = new JComboBox();
		cbxTipoServicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(cbxTipoServicio.getSelectedIndex() == 0) {
					panelAgregarInternet.setVisible(false);
					panelAgregarMinutos.setVisible(false);
					panelAgregarTelevision.setVisible(false);
					//btnAgregar.setEnabled(false);
				}
				if(cbxTipoServicio.getSelectedIndex() == 1) {
					panelAgregarInternet.setVisible(true);
					panelAgregarMinutos.setVisible(false);
					panelAgregarTelevision.setVisible(false);
				}
				if(cbxTipoServicio.getSelectedIndex() == 2) {
					panelAgregarMinutos.setVisible(true);
					panelAgregarInternet.setVisible(false);
					panelAgregarTelevision.setVisible(false);
				}
				if(cbxTipoServicio.getSelectedIndex() == 3) {
					panelAgregarTelevision.setVisible(true);
					panelAgregarInternet.setVisible(false);
					panelAgregarMinutos.setVisible(false);
				}
				loadServicios();
			}
		});

		cbxTipoServicio.setVisible(false);
		cbxTipoServicio.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>","Internet", "Minutos", "Televisi\u00F3n"}));
		cbxTipoServicio.setSelectedIndex(0);
		cbxTipoServicio.setBounds(386, 212, 188, 20);
		contentPanel.add(cbxTipoServicio);
		
		

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{
				btnAplicar = new JButton("Aplicar cambios");
				btnAplicar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(modo.equalsIgnoreCase("Agregar")) {
							if(!verificarServicioRepetido(selected)) {
								if(selected instanceof Internet) {
									auxPlan.getMisServicios().set(0, selected);
								}
								if(selected instanceof Minutos) {
									auxPlan.getMisServicios().set(1, selected);
								}
								if(selected instanceof Television) {
									auxPlan.getMisServicios().set(2, selected);
								}
								JOptionPane.showMessageDialog(null, "Operación exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
								dispose();
								DetallesPlan detPlan = new DetallesPlan(auxPlan, auxEmpleado);
								detPlan.setVisible(true);
							}
							else {
								JOptionPane.showMessageDialog(null, "Ya existe ese tipo de servicio en este plan", "Advertencia", JOptionPane.WARNING_MESSAGE);
							}
						}
						if(modo.equalsIgnoreCase("Modificar")) {
							auxPlan.setNombre(txtNombre.getText().toString());
							JOptionPane.showMessageDialog(null, "Operación exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
							DetallesPlan detPlan = new DetallesPlan(auxPlan, auxEmpleado);
							detPlan.setVisible(true);
						}
					}
				});
				btnAplicar.setBackground(Color.WHITE);
				btnAplicar.setEnabled(false);
				btnAplicar.setActionCommand("OK");
				buttonPane.add(btnAplicar);
				getRootPane().setDefaultButton(btnAplicar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setBackground(Color.WHITE);
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
				
				
			}
		}
	}
	private static void loadServicios() {
		if(cbxTipoServicio.getSelectedIndex() == 1){
			modelInternet.setRowCount(0);
			row = new Object[modelInternet.getColumnCount()];
			for (Servicio servicio : Altice.getInstance().getServicios()) {
				if(servicio instanceof Internet && servicio != auxPlan.getMisServicios().get(0)) {
					row[0] = servicio.getCodigo();
					row[1] = Integer.toString(((Internet)servicio).getVelocidad());
					row[2] = Integer.toString(((Internet)servicio).getCantMB());
					row[3] = ((Internet) servicio).getTipo();
					modelInternet.addRow(row);
				}
			}
		}
		if(cbxTipoServicio.getSelectedIndex() == 2){
			modelMinutos.setRowCount(0);
			row = new Object[modelMinutos.getColumnCount()];
			for (Servicio servicio : Altice.getInstance().getServicios()) {
				if(servicio instanceof Minutos && servicio != auxPlan.getMisServicios().get(1)) {
					row[0] = servicio.getCodigo();
					row[1] = Integer.toString(((Minutos)servicio).getCantMins());
					row[2] = ((Minutos) servicio).getTipo();
					modelMinutos.addRow(row);
				}
			}
		}
		if(cbxTipoServicio.getSelectedIndex() == 3){
			modelTelevision.setRowCount(0);
			row = new Object[modelTelevision.getColumnCount()];
			for (Servicio servicio : Altice.getInstance().getServicios()) {
				if(servicio instanceof Television && servicio != auxPlan.getMisServicios().get(2)) {
					row[0] = servicio.getCodigo();
					row[1] = Integer.toString(((Television)servicio).getCantCanales());
					row[2] = ((Television) servicio).getTipo();
					modelTelevision.addRow(row);
				}
			}
		}

	}
	boolean verificarServicioRepetido(Servicio servicio) {
		boolean repetido = false;
		int i = 0;
		while(!repetido && i < auxPlan.getMisServicios().size()) {
			if(auxPlan.getMisServicios().get(i) != null) {
				if(servicio.getClass() == auxPlan.getMisServicios().get(i).getClass()) {
					repetido = true;
				}
			}
			i++;
		}
		return repetido;
	}
	
	private void loadBotones() {
		btnVerServicioInternet.setEnabled(false);
		btnVerServicioMinutos.setEnabled(false);
		btnVerServicioTelevision.setEnabled(false);
		if(auxPlan.getMisServicios().get(0) != null) {
			btnVerServicioInternet.setEnabled(true);
		}
		if(auxPlan.getMisServicios().get(1) != null) {
			btnVerServicioMinutos.setEnabled(true);
		}
		if(auxPlan.getMisServicios().get(2) != null) {
			btnVerServicioTelevision.setEnabled(true);
		}
	}
}
