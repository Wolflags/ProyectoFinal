package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Altice;
import logico.Cliente;
import logico.Empleado;
import logico.Factura;
import logico.Internet;
import logico.Plan;
import logico.Servicio;
import logico.Television;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class DetallesFactura extends JDialog {

	private static DecimalFormat df = new DecimalFormat("#.##");
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtFecha;
	private JTextField txtEmpleado;
	private JTextField txtCliente;
	private JTextField txtPlan;
	private JTextField txtEstado;
	private Factura auxFactura = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Date hoy = new Date();
			Servicio s1 = null;
			Servicio s2 = null;
			Servicio s3 = null;
			s1 = new Internet("S-1", "Desc1", 30, true, 50, 100, "Móvil");
			s2 = new Television("S-2", "Desc2", 15, false, 150, "Hogar");
			s3 = new Internet("S-3", "Desc3", 20, true, 100, 200, "Hogar");
			Altice.getInstance().getServicios().add(s1);
			Altice.getInstance().getServicios().add(s2);
			Altice.getInstance().getServicios().add(s3);
			ArrayList<Servicio> serviciosEjemplo = new ArrayList<Servicio>();
			serviciosEjemplo.add(null);
			serviciosEjemplo.add(null);
			serviciosEjemplo.add(null);
			serviciosEjemplo.set(0, s1);
			Cliente c1 = new Cliente("123", "Leonardo", "La Zurza II", "8299741202", "Tejada", hoy);
			Altice.getInstance().insertarPersona(c1);
			Empleado e1 = new Empleado("402", "Marlon", "La Zurza", "829", "1234", (float)50000, 0, "Soltero", 5, "Administrador", "Oficina 1", "Tejada", hoy);
			Plan p1 = new Plan("1234", "Ejemplo", serviciosEjemplo, (float)2300);
			Factura f1 = new Factura("F-1", hoy, (float)3400, e1, c1, p1);
			c1.getMisFacturas().add(f1);
			Altice.getInstance().getFacturas().add(f1);
			DetallesFactura dialog = new DetallesFactura(f1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DetallesFactura(Factura factura) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DetallesFactura.class.getResource("/media/imgFactura64px.png")));
		auxFactura = factura;
		df.setRoundingMode(RoundingMode.CEILING);
		setTitle("Detalles de factura");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 911, 307);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelImagen = new JPanel();
			panelImagen.setLayout(null);
			panelImagen.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelImagen.setBounds(10, 11, 197, 202);
			contentPanel.add(panelImagen);
			{
				JLabel lblImagenFactura = new JLabel("");
				lblImagenFactura.setIcon(new ImageIcon(DetallesFactura.class.getResource("/media/imgFactura128px.png")));
				lblImagenFactura.setHorizontalAlignment(SwingConstants.CENTER);
				lblImagenFactura.setBounds(32, 27, 133, 143);
				panelImagen.add(lblImagenFactura);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n general", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(217, 11, 660, 202);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblCodigo = new JLabel("C\u00F3digo:");
				lblCodigo.setBounds(10, 29, 61, 14);
				panel.add(lblCodigo);
			}
			{
				txtCodigo = new JTextField();
				txtCodigo.setText(auxFactura.getCodigo());
				txtCodigo.setEditable(false);
				txtCodigo.setBounds(81, 26, 230, 20);
				panel.add(txtCodigo);
				txtCodigo.setColumns(10);
			}
			{
				JLabel lblFecha = new JLabel("Fecha:");
				lblFecha.setBounds(337, 29, 61, 14);
				panel.add(lblFecha);
			}
			{
				txtFecha = new JTextField();
				txtFecha.setText(auxFactura.getFecha().getDay() + "/" + (auxFactura.getFecha().getMonth() + 1) + "/" + (auxFactura.getFecha().getYear() + 1900));
				txtFecha.setEditable(false);
				txtFecha.setColumns(10);
				txtFecha.setBounds(408, 26, 230, 20);
				panel.add(txtFecha);
			}
			{
				JLabel lblEmpleado = new JLabel("Empleado:");
				lblEmpleado.setBounds(10, 60, 61, 14);
				panel.add(lblEmpleado);
			}
			{
				txtEmpleado = new JTextField();
				txtEmpleado.setText(auxFactura.getEmpleado().getNombre() + " " + auxFactura.getEmpleado().getApellido());
				txtEmpleado.setEditable(false);
				txtEmpleado.setColumns(10);
				txtEmpleado.setBounds(81, 57, 230, 20);
				panel.add(txtEmpleado);
			}
			{
				JLabel lblCliente = new JLabel("Cliente:");
				lblCliente.setBounds(337, 60, 61, 14);
				panel.add(lblCliente);
			}
			{
				txtCliente = new JTextField();
				txtCliente.setText(auxFactura.getCliente().getNombre() + " " + auxFactura.getCliente().getApellido());
				txtCliente.setEditable(false);
				txtCliente.setColumns(10);
				txtCliente.setBounds(408, 57, 230, 20);
				panel.add(txtCliente);
			}
			{
				JLabel lblPlan = new JLabel("Plan:");
				lblPlan.setBounds(10, 91, 61, 14);
				panel.add(lblPlan);
			}
			{
				txtPlan = new JTextField();
				txtPlan.setText(auxFactura.getPlan().getNombre());
				txtPlan.setEditable(false);
				txtPlan.setColumns(10);
				txtPlan.setBounds(81, 88, 230, 20);
				panel.add(txtPlan);
			}
			{
				JLabel lblSubtotal = new JLabel("Subtotal:");
				lblSubtotal.setBounds(337, 91, 61, 14);
				panel.add(lblSubtotal);
			}
			{
				JLabel lblMostrarSubtotal = new JLabel("RD$" + df.format(auxFactura.getSubtotal()));
				lblMostrarSubtotal.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblMostrarSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
				lblMostrarSubtotal.setBounds(492, 91, 146, 14);
				panel.add(lblMostrarSubtotal);
			}
			{
				JLabel lblEstado = new JLabel("Estado:");
				lblEstado.setBounds(10, 122, 61, 14);
				panel.add(lblEstado);
			}
			{
				txtEstado = new JTextField();
				if(auxFactura.isEstado()) {
					txtEstado.setText("Pagada");
				}
				else {
					txtEstado.setText("No pagada");
				}
				txtEstado.setEditable(false);
				txtEstado.setColumns(10);
				txtEstado.setBounds(81, 119, 230, 20);
				panel.add(txtEstado);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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

}
