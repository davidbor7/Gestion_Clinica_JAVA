package es.grupostudium.GestionClinica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaEliminarPaciente extends JFrame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;

	GridBagConstraints constraints = new GridBagConstraints(); //Crear la distribución
	GridBagLayout gridbag = new GridBagLayout(); //Crear las restricciones

	JComboBox choice = new JComboBox();

	String str1 = "Elegir Paciente...";

	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();
	JPanel panel6 = new JPanel();

	JButton btnB1 = new JButton("Eliminar");
	JButton btnB2 = new JButton("Cancelar");
	JButton btnB3 = new JButton("Aceptar"); //
	JButton btnB4 = new JButton("Cancelar");
	JButton btnB5 = new JButton("Aceptar");

	JDialog dialogo1 = new JDialog(this, "ELIMINAR PACIENTE", true);
	JLabel mensaje1 = new JLabel("¿Estás seguro que quieres eliminar este paciente?");
	JDialog dialogo2 = new JDialog(this, "ELIMINAR PACIENTE", true);
	JLabel mensaje2 = new JLabel("Paciente eliminado con éxito.");
	ResultSet rs_id_pacientes = null;
	ResultSet rs_nombres_pacientes = null;
	ResultSet rs_apellidos_pacientes = null;

	public VentanaEliminarPaciente()
	{

		btnB1.addActionListener(this);
		btnB2.addActionListener(this);
		btnB3.addActionListener(this);
		btnB4.addActionListener(this);
		btnB5.addActionListener(this);


		setLayout(gridbag);
		setSize(600,400);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("ELIMINAR PACIENTE");

		rs_id_pacientes = (ResultSet) new ConectaBBDD().obtener_id_de_los_pacientes("SELECT idPaciente FROM pacientes;"); //DEVUELVE RS CON LOS NOMBRES DE LOS PACIENTES
		rs_nombres_pacientes = (ResultSet) new ConectaBBDD().obtener_nombres_de_los_pacientes("SELECT nombrePaciente FROM pacientes;"); //DEVUELVE RS CON LOS NOMBRES DE LOS PACIENTES
		rs_apellidos_pacientes = (ResultSet) new ConectaBBDD().obtener_apellidos_de_los_pacientes("SELECT apellidosPaciente from pacientes;");//DEVUELVE RS CON LOS APELLIDOS DE LOS PACIENTES

		choice.addItem(str1);


		try //USAMOS UN WHILE PARA RELLENAR EL JCOMBOX CON LOS RESULTADOS DEL RESULSET
		{
			while(rs_id_pacientes.next() && rs_nombres_pacientes.next() && rs_apellidos_pacientes.next()) 
			{
				choice.addItem(rs_id_pacientes.getString("idPaciente") +"-"+ rs_nombres_pacientes.getString("nombrePaciente") +" "+ rs_apellidos_pacientes.getString("apellidosPaciente"));//AÑADE NOMBRE Y APELLIDOS DE LOS RS USADOS ANTERIORMENTE Y LOS METE EN UN JCOMBOBOX
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		try //CERRAMOS LOS RESULTSET USADOS
		{			
			rs_id_pacientes.close(); 
			rs_nombres_pacientes.close(); 
			rs_apellidos_pacientes.close();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}



		panel1.setLayout(new FlowLayout());
		panel1.add(btnB1);
		panel1.add(btnB2);


		constraints.anchor =  GridBagConstraints.CENTER;
		constraints.fill =  GridBagConstraints.NONE;
		constraints.weighty=0.6;
		constraints.weightx=0.6;
		constraints.gridx = 0 ; // Establece gridx -->COLUMNA
		constraints.gridy = 0 ; // Establece gridy --> FILA
		constraints.gridwidth = 1 ; // Establece gridwidth --> ANCHURA
		constraints.gridheight = 1 ; // Establece gridheight --> ALTURA
		add(choice, constraints);

		constraints.anchor =  GridBagConstraints.PAGE_START;
		constraints.fill =  GridBagConstraints.NONE;
		constraints.weighty=0.9;
		constraints.weightx=0.9;
		constraints.gridx = 0 ; // Establece gridx -->COLUMNA
		constraints.gridy = 1 ; // Establece gridy --> FILA
		constraints.gridwidth = 1 ; // Establece gridwidth --> ANCHURA
		constraints.gridheight = 1 ; // Establece gridheight --> ALTURA
		add(panel1, constraints);


		addWindowListener(this);
		setVisible(true);


		dialogo1.setLayout(new BorderLayout());
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());
		dialogo1.setSize(600,400);
		dialogo1.setResizable(false);
		dialogo1.setLocationRelativeTo(null);
		dialogo1.addWindowListener(this);
		panel2.add(mensaje1);
		dialogo1.add("North",panel2);
		panel3.add(btnB3);
		panel3.add(btnB4);
		dialogo1.add("Center", panel3);

		dialogo2.setLayout(new BorderLayout());
		panel4.setLayout(new FlowLayout());
		panel5.setLayout(new FlowLayout());
		dialogo2.setSize(600,400);
		dialogo2.setResizable(false);
		dialogo2.setLocationRelativeTo(null);
		dialogo2.addWindowListener(this);
		panel4.add(mensaje2);
		dialogo2.add("North",panel4);
		panel5.add(btnB5);
		dialogo2.add("Center", panel5);

	}

	public static void main(String[] args)
	{
		new VentanaEliminarPaciente();
	}

	public void windowActivated(WindowEvent we){}

	public void windowClosed(WindowEvent we){}

	public void windowClosing(WindowEvent we)

	{

		this.setVisible(false);   

	}

	public void windowDeactivated(WindowEvent we){}

	public void windowDeiconified(WindowEvent we){}

	public void windowIconified(WindowEvent we){}

	public void windowOpened(WindowEvent we){}



	public void actionPerformed(ActionEvent ae)
	{
		Object a;
		a = ae.getSource();
		String nombre_completo_paciente = (String) choice.getSelectedItem(); //RECOGE LO QUE SELECCIONA EN EL JCOMBOBOX Y LO CONVIERTE EN STRING
		
		if(a.equals(btnB1) && !nombre_completo_paciente.equals("Elegir Paciente..."))
		{
			dialogo1.setVisible(true);
		}
		if(a.equals(btnB2))
		{
			this.setVisible(false);
		}
		if(a.equals(btnB3))
		{

			String[] seleciona_id = nombre_completo_paciente.split("-");  //METODO PARA SEPARAR EL ID DEL RESTO DEL NOMBRE

			String sentencia_eliminar_paciente = "DELETE FROM pacientes WHERE idPaciente='"+ seleciona_id[0] +"';"; //SENTENCIA PARA ELIMINAR PACIENTE EN LA BD

			new ConectaBBDD().eliminar_paciente(sentencia_eliminar_paciente); 
			
			new Movimientos().recoge_sentencia(sentencia_eliminar_paciente); //ESCRIBE EN EL LOG LA SENTENCIA
			

			dialogo1.setVisible(false);
			dialogo2.setVisible(true);    

			//LIMPIAR EL JCOMBOBOX Y ACTUALIZAR LOS DATOS

			choice.removeAllItems();

			rs_id_pacientes = (ResultSet) new ConectaBBDD().obtener_id_de_los_pacientes("SELECT idPaciente FROM pacientes;"); //DEVUELVE RS CON LOS NOMBRES DE LOS PACIENTES
			rs_nombres_pacientes = (ResultSet) new ConectaBBDD().obtener_nombres_de_los_pacientes("SELECT nombrePaciente FROM pacientes;"); //DEVUELVE RS CON LOS NOMBRES DE LOS PACIENTES
			rs_apellidos_pacientes = (ResultSet) new ConectaBBDD().obtener_apellidos_de_los_pacientes("SELECT apellidosPaciente from pacientes;");//DEVUELVE RS CON LOS APELLIDOS DE LOS PACIENTES

			choice.addItem(str1);


			try //VOLVEMOS A USAR UN WHILE PARA RELLENAR EL JCOMBOX CON LOS RESULTADOS DEL RESULSET
			{
				while(rs_id_pacientes.next() && rs_nombres_pacientes.next() && rs_apellidos_pacientes.next()) 
				{
					choice.addItem(rs_id_pacientes.getString("idPaciente") +"-"+ rs_nombres_pacientes.getString("nombrePaciente") +" "+ rs_apellidos_pacientes.getString("apellidosPaciente"));//AÑADE NOMBRE Y APELLIDOS DE LOS RS USADOS ANTERIORMENTE Y LOS METE EN UN JCOMBOBOX
				}
			} catch (SQLException e)
			{
				e.printStackTrace();
			}

			try //VOLVEMOS A CERRAR LOS RESULTSET USADOS
			{			
				rs_id_pacientes.close(); 
				rs_nombres_pacientes.close(); 
				rs_apellidos_pacientes.close();

			} catch (SQLException e)
			{
				e.printStackTrace();
			}

		}
		if(a.equals(btnB4))
		{
			dialogo1.setVisible(false);
		}
		if(a.equals(btnB5))
		{
			dialogo2.setVisible(false);

		}

	}
}
