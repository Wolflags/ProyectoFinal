package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import logico.Altice;
import logico.Cliente;
import logico.Internet;
import logico.Minutos;
import logico.Persona;
import logico.Plan;
import logico.Servicio;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.awt.Color;
import java.awt.Toolkit;

public class ListadoPlanesModal extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtBuscar;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private static String ini="";
	private static Plan selected = null;
	private JFormattedTextField txtTelefono;
	private ArrayList<Plan> planes;
	private JButton btnSeleccionar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoPlanesModal dialog = new ListadoPlanesModal();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoPlanesModal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoPlanesModal.class.getResource("/media/imgPlan32px.png")));
		planes = new ArrayList<Plan>();
		initArrayList(planes);
		setTitle("Seleccionar Plan");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 825, 470);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 799, 409);
		contentPanel.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 75, 779, 304);
		panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = table.getSelectedRow();
				if(index>=0) {
					btnSeleccionar.setEnabled(true);
					String codPlan = table.getValueAt(index, 0).toString();
					selected=Altice.getInstance().buscarPlanByCod(codPlan);
				}
			}
		});
		model = new DefaultTableModel();
		String[] headers = {"Código","Nombre","Cantidad de servicios", "Precio"};
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_1 = new JLabel("Buscar:");
		lblNewLabel_1.setBounds(251, 32, 67, 14);
		panel.add(lblNewLabel_1);

		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				planes.removeAll(planes);
				if(txtBuscar.getText().equalsIgnoreCase("")) {
					initArrayList(planes);
					loadPlanes();
				}else {
					planes.addAll(Altice.getInstance().buscarTodosPlanesByNombre(txtBuscar.getText()));
					loadPlanes();
				}
			}
		});
		txtBuscar.setBounds(302, 29, 239, 20);
		panel.add(txtBuscar);
		txtBuscar.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSeleccionar = new JButton("Seleccionar");
				btnSeleccionar.setEnabled(false);
				btnSeleccionar.setBackground(Color.WHITE);
				btnSeleccionar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MaskFormatter formatter = null;
						try {
							formatter = new MaskFormatter("(###) ###-####");
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						txtTelefono = new JFormattedTextField(formatter);
						int opc = JOptionPane.showConfirmDialog(null, txtTelefono,"Numero de telefono para el plan",JOptionPane.OK_CANCEL_OPTION);
						while(((txtTelefono.getText().charAt(1)==' ')&&opc==0)&&opc==0) {
							JOptionPane.showMessageDialog(null, "El número que intentas registrar no está disponible!",
									"Número Inválido!", JOptionPane.ERROR_MESSAGE);
							opc = JOptionPane.showConfirmDialog(null, txtTelefono,"Numero de telefono para el plan",JOptionPane.OK_CANCEL_OPTION);
						}
						if(opc==0&&selected!=null) {
							Plan auxPlan = new Plan(selected.getIdplan(), selected.getNombre(), selected.getMisServicios(), selected.getPrecio());
							auxPlan.setNumero(txtTelefono.getText());
							RealizarVenta.carrito.add(auxPlan);
							RealizarVenta.cargarPlanesSel();
						}
						dispose();
					}
				});
				btnSeleccionar.setActionCommand("OK");
				buttonPane.add(btnSeleccionar);
				getRootPane().setDefaultButton(btnSeleccionar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setBackground(Color.WHITE);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadPlanes();
	}

	private void initArrayList(ArrayList<Plan> planes) {
		for (Plan plan : Altice.getInstance().getPlanes()) {
			planes.add(plan);
		}
	}

	private void loadPlanes() {
		if(ini=="") {
			model.setRowCount(0);
			row = new Object[model.getColumnCount()];
			for(Plan plan : planes) {
				row[0] = plan.getIdplan();
				row[1] = plan.getNombre();
				int cantserv = 0;
				if(plan.getMisServicios().get(0) != null) {
					cantserv++;
				}
				if(plan.getMisServicios().get(1) != null) {
					cantserv++;
				}
				if(plan.getMisServicios().get(2) != null) {
					cantserv++;
				}
				row[2] = cantserv;
				row[3] = plan.getPrecio();
				model.addRow(row);
			}
		}else {
			model.setRowCount(0);
			row = new Object[model.getColumnCount()];
			for (Plan plan : planes) {
				if(plan.getIdplan().contains(ini)||plan.getNombre().contains(ini)) {
					row[0]=plan.getIdplan();
					row[1]=plan.getNombre();
					int cantserv=0;
					if(plan.getMisServicios().get(0)!=null) {
						cantserv++;
					}
					if(plan.getMisServicios().get(1)!=null) {
						cantserv++;
					}
					if(plan.getMisServicios().get(2)!=null) {
						cantserv++;
					}
					row[2]=cantserv;
					row[3]=plan.getPrecio();
					model.addRow(row);
				}
			}
		}
	}
}
