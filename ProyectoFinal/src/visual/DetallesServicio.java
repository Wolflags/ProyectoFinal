package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Internet;
import logico.Minutos;
import logico.Servicio;
import logico.Television;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class DetallesServicio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Servicio auxServicio = null;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private JSpinner spnCantCanales;
	private JComboBox cbxTipoTelevision;
	private JPanel panelInternet;
	private JPanel panelTelevision;
	private JSpinner spnVelocidad;
	private JSpinner spnCantMB;
	private JComboBox cbxTipoInternet;
	private JPanel panelMinutos;
	private JSpinner spnCantMinutos;
	private JComboBox cbxTipoMinutos;
	private JButton btnAplicar;
	private JButton btnModificar;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		Television s1 = new Television("S-1", "Esta es la descripcion", 150, "Cable");
		Internet s2 = new Internet("S-2", "Esta es la descripcion 2", 40, 15000, "Internet Móvil");
		Minutos s3 = new Minutos("S-3", "Esta es la descripcion 3", 200, "Minutos Hogar");
		try {
			DetallesServicio dialog = new DetallesServicio(s3);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog////
	 */
	public DetallesServicio(Servicio servicio) {
		auxServicio = servicio;
		if(auxServicio instanceof Television) {
			setIconImage(Toolkit.getDefaultToolkit().getImage(DetallesServicio.class.getResource("/media/imgTelevision128px.png")));
		}
		if(auxServicio instanceof Internet) {
			setIconImage(Toolkit.getDefaultToolkit().getImage(DetallesServicio.class.getResource("/media/imgInternet128px.png")));
		}
		if(auxServicio instanceof Minutos) {
			setIconImage(Toolkit.getDefaultToolkit().getImage(DetallesServicio.class.getResource("/media/imgMinutos128px.png")));
		}
		setTitle("Servicio (" + auxServicio.getCodigo() + ")");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 911, 407);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelBotones = new JPanel();
			panelBotones.setLayout(null);
			panelBotones.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelBotones.setBounds(10, 11, 197, 311);
			contentPanel.add(panelBotones);
			{
				JLabel lblImagenLogo = new JLabel("");
				if(auxServicio instanceof Television) {
					lblImagenLogo.setIcon(new ImageIcon(DetallesServicio.class.getResource("/media/imgTelevision128px.png")));
				}
				if(auxServicio instanceof Internet) {
					lblImagenLogo.setIcon(new ImageIcon(DetallesServicio.class.getResource("/media/imgInternet128px.png")));
				}
				if(auxServicio instanceof Minutos) {
					lblImagenLogo.setIcon(new ImageIcon(DetallesServicio.class.getResource("/media/imgMinutos128px.png")));
				}
				lblImagenLogo.setHorizontalAlignment(SwingConstants.CENTER);
				lblImagenLogo.setBounds(32, 27, 133, 143);
				panelBotones.add(lblImagenLogo);
			}
			{
				btnModificar = new JButton("     Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						celdasEditables(true);
						btnModificar.setEnabled(false);
						btnAplicar.setEnabled(true);
					}
				});
				btnModificar.setIcon(new ImageIcon(DetallesServicio.class.getResource("/media/imgModificar32px.png")));
				btnModificar.setHorizontalAlignment(SwingConstants.LEADING);
				btnModificar.setBackground(Color.WHITE);
				btnModificar.setBounds(18, 198, 161, 50);
				panelBotones.add(btnModificar);
			}
		}
		{
			JPanel panelInfoGeneral = new JPanel();
			panelInfoGeneral.setLayout(null);
			panelInfoGeneral.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n general", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelInfoGeneral.setBounds(217, 11, 667, 152);
			contentPanel.add(panelInfoGeneral);
			{
				JLabel lblCodigo = new JLabel("C\u00F3digo:");
				lblCodigo.setBounds(10, 25, 79, 14);
				panelInfoGeneral.add(lblCodigo);
			}
			{
				txtCodigo = new JTextField();
				txtCodigo.setText(auxServicio.getCodigo());
				txtCodigo.setEditable(false);
				txtCodigo.setColumns(10);
				txtCodigo.setBounds(77, 22, 230, 20);
				panelInfoGeneral.add(txtCodigo);
			}
			{
				JLabel lblDescripcion = new JLabel("Descripci\u00F3n:");
				lblDescripcion.setBounds(335, 25, 93, 14);
				panelInfoGeneral.add(lblDescripcion);
			}
			{
				txtDescripcion = new JTextField();
				txtDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
				txtDescripcion.setText(auxServicio.getDescripcion());
				txtDescripcion.setEditable(false);
				txtDescripcion.setBounds(424, 22, 221, 101);
				panelInfoGeneral.add(txtDescripcion);
				txtDescripcion.setColumns(10);
			}
			{
				JLabel lblTipo = new JLabel("Tipo de servicio:");
				lblTipo.setBounds(10, 65, 107, 14);
				panelInfoGeneral.add(lblTipo);
			}
			{
				JComboBox cbxTipoServicio = new JComboBox();
				cbxTipoServicio.setModel(new DefaultComboBoxModel(new String[] {"Internet", "Minutos", "Televisión"}));
				if(auxServicio instanceof Internet) {
					cbxTipoServicio.setSelectedItem("Internet");
				}
				if(auxServicio instanceof Minutos) {
					cbxTipoServicio.setSelectedItem("Minutos");
				}
				if(auxServicio instanceof Television) {
					cbxTipoServicio.setSelectedItem("Televisión");
				}
				cbxTipoServicio.setEnabled(false);
				cbxTipoServicio.setBounds(116, 62, 191, 20);
				panelInfoGeneral.add(cbxTipoServicio);
			}
		}
		{
			panelTelevision = new JPanel();
			panelTelevision.setVisible(false);
			if(auxServicio instanceof Television) {
				panelTelevision.setVisible(true);
			}
			panelTelevision.setBorder(new TitledBorder(null, "Detalles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelTelevision.setBounds(217, 174, 667, 148);
			contentPanel.add(panelTelevision);
			panelTelevision.setLayout(null);
			{
				JLabel lblCantCanales = new JLabel("Cantidad de canales:");
				lblCantCanales.setBounds(10, 67, 128, 14);
				panelTelevision.add(lblCantCanales);
			}
			{
				spnCantCanales = new JSpinner();
				spnCantCanales.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
				if(auxServicio instanceof Television) {
					spnCantCanales.setValue(((Television)auxServicio).getCantCanales());
				}
				else {
					spnCantCanales.setValue(0);
				}
				spnCantCanales.setEnabled(false);
				spnCantCanales.setBounds(148, 64, 159, 20);
				panelTelevision.add(spnCantCanales);
			}
			{
				JLabel lblTipoTelevision = new JLabel("Tipo:");
				lblTipoTelevision.setBounds(335, 67, 64, 14);
				panelTelevision.add(lblTipoTelevision);
			}
			{
				cbxTipoTelevision = new JComboBox();
				cbxTipoTelevision.setModel(new DefaultComboBoxModel(new String[] {"Cable", "Sat\u00E9lite"}));
				if(auxServicio instanceof Television) {
					cbxTipoTelevision.setSelectedItem(((Television)auxServicio).getTipo());
				}
				else {
					cbxTipoTelevision.setSelectedIndex(0);
				}
				cbxTipoTelevision.setEnabled(false);
				cbxTipoTelevision.setBounds(424, 64, 221, 20);
				panelTelevision.add(cbxTipoTelevision);
			}
		}
		{
			panelInternet = new JPanel();
			panelInternet.setVisible(false);
			if(auxServicio instanceof Internet) {
				panelInternet.setVisible(true);
			}
			panelInternet.setBorder(new TitledBorder(null, "Detalles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelInternet.setBounds(217, 174, 667, 148);
			contentPanel.add(panelInternet);
			panelInternet.setLayout(null);
			{
				JLabel lblVelocidad = new JLabel("Velocidad (MB/s):");
				lblVelocidad.setBounds(10, 45, 120, 14);
				panelInternet.add(lblVelocidad);
			}
			{
				spnVelocidad = new JSpinner();
				spnVelocidad.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(5)));
				if(auxServicio instanceof Internet) {
					spnVelocidad.setValue(((Internet)auxServicio).getVelocidad());
				}
				else {
					spnVelocidad.setValue(0);
				}
				spnVelocidad.setEnabled(false);
				spnVelocidad.setBounds(167, 42, 140, 20);
				panelInternet.add(spnVelocidad);
			}
			{
				JLabel lblCantMB = new JLabel("Cantidad (MB):");
				lblCantMB.setBounds(335, 45, 120, 14);
				panelInternet.add(lblCantMB);
			}
			{
				spnCantMB = new JSpinner();
				spnCantMB.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(5)));
				if(auxServicio instanceof Internet) {
					spnCantMB.setValue(((Internet)auxServicio).getCantMB());
				}
				else {
					spnCantMB.setValue(0);
				}
				spnCantMB.setEnabled(false);
				spnCantMB.setBounds(455, 42, 190, 20);
				panelInternet.add(spnCantMB);
			}
			{
				JLabel lblTipoInternet = new JLabel("Tipo:");
				lblTipoInternet.setBounds(10, 90, 120, 14);
				panelInternet.add(lblTipoInternet);
			}
			{
				cbxTipoInternet = new JComboBox();
				cbxTipoInternet.setEnabled(false);
				cbxTipoInternet.setModel(new DefaultComboBoxModel(new String[] {"Internet Móvil", "Internet Hogar"}));
				if(auxServicio instanceof Internet) {
					cbxTipoInternet.setSelectedItem(((Internet)auxServicio).getTipo());
				}
				else {
					cbxTipoInternet.setSelectedIndex(0);
				}
				cbxTipoInternet.setBounds(167, 87, 140, 20);
				panelInternet.add(cbxTipoInternet);
			}
		}
		{
			panelMinutos = new JPanel();
			panelMinutos.setVisible(false);
			if(auxServicio instanceof Minutos) {
				panelMinutos.setVisible(true);
			}
			panelMinutos.setBorder(new TitledBorder(null, "Detalles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelMinutos.setBounds(217, 174, 667, 148);
			contentPanel.add(panelMinutos);
			panelMinutos.setLayout(null);
			{
				JLabel lblCantMinutos = new JLabel("Cantidad de minutos:");
				lblCantMinutos.setBounds(10, 67, 128, 14);
				panelMinutos.add(lblCantMinutos);
			}
			{
				spnCantMinutos = new JSpinner();
				spnCantMinutos.setEnabled(false);
				spnCantMinutos.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(5)));
				if(auxServicio instanceof Minutos) {
					spnCantMinutos.setValue(((Minutos)auxServicio).getCantMins());
				}
				else {
					spnCantMinutos.setValue(0);
				}
				spnCantMinutos.setBounds(148, 64, 159, 20);
				panelMinutos.add(spnCantMinutos);
			}
			{
				JLabel lblTipoMinutos = new JLabel("Tipo:");
				lblTipoMinutos.setBounds(335, 67, 64, 14);
				panelMinutos.add(lblTipoMinutos);
			}
			{
				cbxTipoMinutos = new JComboBox();
				cbxTipoMinutos.setEnabled(false);
				cbxTipoMinutos.setModel(new DefaultComboBoxModel(new String[] {"Minutos M\u00F3vil", "Minutos Hogar"}));
				if(auxServicio instanceof Minutos) {
					cbxTipoMinutos.setSelectedItem(((Minutos)auxServicio).getTipo());
				}
				else {
					cbxTipoMinutos.setSelectedIndex(0);
				}
				cbxTipoMinutos.setBounds(424, 64, 221, 20);
				panelMinutos.add(cbxTipoMinutos);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAplicar = new JButton("Aplicar");
				btnAplicar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(isCompleted()) {
							auxServicio.setDescripcion(txtDescripcion.getText().toString());
							if(auxServicio instanceof Television) {
								((Television)auxServicio).setCantCanales(Integer.parseInt(spnCantCanales.getValue().toString()));
								((Television)auxServicio).setTipo(cbxTipoTelevision.getSelectedItem().toString());
							}
							if(auxServicio instanceof Internet) {
								((Internet)auxServicio).setVelocidad(Integer.parseInt(spnVelocidad.getValue().toString()));
								((Internet)auxServicio).setCantMB(Integer.parseInt(spnCantMB.getValue().toString()));
								((Internet)auxServicio).setTipo(cbxTipoInternet.getSelectedItem().toString());
							}
							if(auxServicio instanceof Minutos) {
								((Minutos)auxServicio).setCantMins(Integer.parseInt(spnCantMinutos.getValue().toString()));
								((Minutos)auxServicio).setTipo(cbxTipoMinutos.getSelectedItem().toString());
							}
							celdasEditables(false);
							btnModificar.setEnabled(true);
							btnAplicar.setEnabled(false);
							JOptionPane.showMessageDialog(null, "Cambios realizados exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Debe rellenar todos los espacios en blanco", "Advertencia", JOptionPane.WARNING_MESSAGE);
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
	private void celdasEditables(boolean editable) {
		
		txtDescripcion.setEditable(editable);
		if(auxServicio instanceof Television) {
			spnCantCanales.setEnabled(editable);
			cbxTipoTelevision.setEnabled(editable);
		}
		if(auxServicio instanceof Internet) {
			spnVelocidad.setEnabled(editable);
			spnCantMB.setEnabled(editable);
			cbxTipoInternet.setEnabled(editable);
		}
		if(auxServicio instanceof Minutos) {
			spnCantMinutos.setEnabled(editable);
			cbxTipoMinutos.setEnabled(editable);
		}
	}
	private boolean isCompleted() {
		boolean completed = false;
		if(!txtDescripcion.getText().equals("")) {
			if(auxServicio instanceof Television) {
				if(Integer.parseInt(spnCantCanales.getValue().toString()) != 0) {
					completed = true;
				}
			}
			if(auxServicio instanceof Internet) {
				if(Integer.parseInt(spnCantMB.getValue().toString()) != 0 
						&& Integer.parseInt(spnVelocidad.getValue().toString()) != 0) {
					completed = true;
				}
			}
			if(auxServicio instanceof Minutos) {
				if(Integer.parseInt(spnCantMinutos.getValue().toString()) != 0) {
					completed = true;
				}
			}
		}
		return completed;
	}

}
