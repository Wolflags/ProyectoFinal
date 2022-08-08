package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Altice;
import logico.Empleado;
import logico.Internet;
import logico.Minutos;
import logico.Servicio;
import logico.Television;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class ListadoServiciosModal extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtBuscar;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private Servicio selected = null;
	private String ini = "";
	private JButton btnDetalles;
	private Empleado auxEmpleado = null;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			ListadoServiciosModal dialog = new ListadoServiciosModal(0);
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ListadoServiciosModal(int tipo, Empleado empleado) {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoServiciosModal.class.getResource("/media/imgListadoServicios32px.png")));
		auxEmpleado = empleado;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				RegistrarPlan.setCancel(tipo);
				dispose();
			}
		});
		setTitle("Seleccionar Servicio");
		setModal(true);
		setBounds(100, 100, 700, 445);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 83, 664, 279);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = table.getSelectedRow();
						if(index>=0) {
						btnDetalles.setEnabled(true);
						String codServ = table.getValueAt(index, 0).toString();
						selected=Altice.getInstance().buscarServicioByCodigo(codServ);
						}
					}
				});
				model = new DefaultTableModel();
				String[] headers = {"C�digo","Tipo de servicio","Descripci�n", "Precio"};
				model.setColumnIdentifiers(headers);
				table.setModel(model);
				scrollPane.setViewportView(table);
			}
		}
		{
			JLabel lblNewLabel = new JLabel("Buscar:");
			lblNewLabel.setBounds(28, 58, 66, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			txtBuscar = new JTextField();
			txtBuscar.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					ini=txtBuscar.getText();
					cargarServ(tipo);
				}
			});
			
			
			
			txtBuscar.setBounds(88, 55, 245, 20);
			contentPanel.add(txtBuscar);
			txtBuscar.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Seleccionar servicio");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
			lblNewLabel_1.setBounds(248, 11, 201, 33);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Seleccionar");
				okButton.setBackground(Color.WHITE);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegistrarPlan.selected=selected;
						RegistrarPlan.cargarDatos();
						dispose();
					}
				});
				{
					btnDetalles = new JButton("Detalles");
					btnDetalles.setBackground(Color.WHITE);
					btnDetalles.setEnabled(false);
					btnDetalles.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(selected!=null) {
								DetallesServicio detServ = new DetallesServicio(selected, auxEmpleado);
								detServ.setVisible(true);
								detServ.setModal(true);
							}
						}
					});
					buttonPane.add(btnDetalles);
				}
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setBackground(Color.WHITE);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegistrarPlan.setCancel(tipo);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		cargarServ(tipo);
	}

	private void cargarServ(int tipo) {
		if(tipo==0) {
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
				row[2]=servicio.getDescripcion();
				row[3]=servicio.getPrecio();
				if(servicio instanceof Internet) {
				model.addRow(row);
				}
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
						row[2]=servicio.getDescripcion();
						row[3]=servicio.getPrecio();
						if(servicio instanceof Internet) {
							model.addRow(row);
							}
					}
				}
			}
	}else if(tipo==1) {
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
				row[2]=servicio.getDescripcion();
				row[3]=servicio.getPrecio();
				if(servicio instanceof Minutos) {
				model.addRow(row);
				}
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
						row[2]=servicio.getDescripcion();
						row[3]=servicio.getPrecio();
						if(servicio instanceof Minutos) {
							model.addRow(row);
							}
					}
				}
			}
	}else if(tipo==2) {
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
				row[2]=servicio.getDescripcion();
				row[3]=servicio.getPrecio();
				if(servicio instanceof Television) {
				model.addRow(row);
				
				}
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
						row[2]=servicio.getDescripcion();
						row[3]=servicio.getPrecio();
						if(servicio instanceof Television) {
							model.addRow(row);
							}
					}
				}
			}
	}
	}

}
