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

public class VentanaEliminarTratamiento extends JFrame implements WindowListener, ActionListener
{

	private static final long serialVersionUID = 1L;

	GridBagConstraints constraints = new GridBagConstraints(); //Crear la distribuci�n
	GridBagLayout gridbag = new GridBagLayout(); //Crear las restricciones

	JComboBox choice1 = new JComboBox();
	JComboBox choice2 = new JComboBox();

	String str1 = "Elegir Paciente...";
	String str2 = "Elegir Tratamiento...";

	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();
	JPanel panel6 = new JPanel();
	JPanel panel7 = new JPanel();
	JPanel panel8 = new JPanel();

	JButton btnB1 = new JButton("Seleccionar");
	JButton btnB2 = new JButton("Cancelar");
	JButton btnB3 = new JButton("Eliminar"); 
	JButton btnB4 = new JButton("Cancelar");
	JButton btnB5 = new JButton("Aceptar");
	JButton btnB6 = new JButton("Cancelar");
	JButton btnB7 = new JButton("Aceptar");

	JDialog dialogo1 = new JDialog(this, "ELIMINAR TRATAMIENTO", true);
	JDialog dialogo2 = new JDialog(this, "ELIMINAR TRATAMIENTO", true);
	JDialog dialogo3 = new JDialog(this, "ELIMINAR TRATAMIENTO", true);

	JLabel mensaje1 = new JLabel("�Est�s seguro que quieres eliminar este Tratamiento?");
	JLabel mensaje2 = new JLabel("Tratamiento eliminado con �xito.");

	ResultSet rs_podologos = null;
	ResultSet rs_tratamientos = null;

	ResultSet rs_id_pacientes = null;
	ResultSet rs_nombres_pacientes = null;
	ResultSet rs_apellidos_pacientes = null;



	public VentanaEliminarTratamiento()
	{

		btnB1.addActionListener(this);
		btnB2.addActionListener(this);
		btnB3.addActionListener(this);
		btnB4.addActionListener(this);
		btnB5.addActionListener(this);
		btnB6.addActionListener(this);
		btnB7.addActionListener(this);


		setLayout(gridbag);
		setSize(600,400);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("ELIMINAR TRATAMIENTO");


		rs_podologos = (ResultSet) new ConectaBBDD().obtener_todos_los_datos_de_los_podologos("SELECT idPodologo, numeroColegiadoPodologo, especialidadPodologo, idPersonalFK FROM podologos;");

		choice1.addItem(str1);
		choice2.addItem(str2);

		rs_id_pacientes = (ResultSet) new ConectaBBDD().obtener_id_de_los_pacientes("SELECT idPaciente FROM pacientes;"); //DEVUELVE RS CON LOS NOMBRES DE LOS PACIENTES
		rs_nombres_pacientes = (ResultSet) new ConectaBBDD().obtener_nombres_de_los_pacientes("SELECT nombrePaciente FROM pacientes;"); //DEVUELVE RS CON LOS NOMBRES DE LOS PACIENTES
		rs_apellidos_pacientes = (ResultSet) new ConectaBBDD().obtener_apellidos_de_los_pacientes("SELECT apellidosPaciente from pacientes;");//DEVUELVE RS CON LOS APELLIDOS DE LOS PACIENTES


		try //USAMOS UN WHILE PARA RELLENAR EL JCOMBOX CON LOS RESULTADOS DEL RESULSET
		{
			while(rs_id_pacientes.next() && rs_nombres_pacientes.next() && rs_apellidos_pacientes.next()) 
			{
				choice1.addItem(rs_id_pacientes.getString("idPaciente") +"-"+ rs_nombres_pacientes.getString("nombrePaciente") +" "+ rs_apellidos_pacientes.getString("apellidosPaciente"));//A�ADE NOMBRE Y APELLIDOS DE LOS RS USADOS ANTERIORMENTE Y LOS METE EN UN JCOMBOBOX
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
		add(choice1, constraints);

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
		panel2.add(choice2);
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
		panel4.add(mensaje1);
		dialogo2.add("North",panel4);
		panel5.add(btnB5);
		panel5.add(btnB6);
		dialogo2.add("Center", panel5);
		
		dialogo3.setLayout(new BorderLayout());
		panel7.setLayout(new FlowLayout());
		panel8.setLayout(new FlowLayout());
		dialogo3.setSize(600,400);
		dialogo3.setResizable(false);
		dialogo3.setLocationRelativeTo(null);
		dialogo3.addWindowListener(this);
		panel7.add(mensaje2);
		dialogo3.add("North",panel7);
		panel8.add(btnB7);
		dialogo3.add("Center", panel8);



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

		if(a.equals(btnB1))
		{
			String nombre_completo_paciente = (String) choice1.getSelectedItem(); //RECOGE LO QUE SELECCIONA EN EL JCOMBOBOX Y LO CONVIERTE EN STRING

			if(!nombre_completo_paciente.equals("Elegir Paciente..."))
			{

				String[] seleciona_id = nombre_completo_paciente.split("-");  //METODO PARA SEPARAR EL ID DEL RESTO DEL NOMBRE

				String sentencia_obtener_tratamientos = "SELECT * FROM tratamientopersonal WHERE idPacienteFK='"+ seleciona_id[0] + "';";

				rs_tratamientos = (ResultSet)new ConectaBBDD().obtener_tratamientos(sentencia_obtener_tratamientos); 

				try //USAMOS UN WHILE PARA RELLENAR EL JCOMBOX CON LOS RESULTADOS DEL RESULSET
				{
					while(rs_tratamientos.next()) 
					{
						choice2.addItem(rs_tratamientos.getString(1) + "-IMPORTE: " + rs_tratamientos.getString(4) +"�  FECHA: "+ rs_tratamientos.getString(5));//A�ADE NOMBRE Y APELLIDOS DE LOS RS USADOS ANTERIORMENTE Y LOS METE EN UN JCOMBOBOX
					}
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
				dialogo1.setVisible(true);

			}
		}
		
		if(a.equals(btnB2))
		{
			this.setVisible(false);
		}


		if(a.equals(btnB3))
		{
			String nombre_tratamiento = (String) choice2.getSelectedItem(); //RECOGE LO QUE SELECCIONA EN EL JCOMBOBOX Y LO CONVIERTE EN STRING
		
			if(!nombre_tratamiento.equals("Elegir Tratamiento..."))
			{
			dialogo2.setVisible(true);
			}
		}

		if(a.equals(btnB4))
		{
			this.setVisible(false);
		}
		
		if(a.equals(btnB5))
		{
			
			String nombre_tratamiento = (String) choice2.getSelectedItem(); //RECOGE LO QUE SELECCIONA EN EL JCOMBOBOX Y LO CONVIERTE EN STRING
			
			if(!nombre_tratamiento.equals("Elegir Tratamiento..."))
			{

			String[] seleciona_id =  nombre_tratamiento.split("-");  //METODO PARA SEPARAR EL ID DEL RESTO DEL NOMBRE
			
			String sentencia_eliminar_tratamiento = "DELETE FROM tratamientopersonal WHERE idTratamiento='"+ seleciona_id[0] +"'"; //SENTENCIA PARA ELIMINAR TRATAMIENTO EN LA BD
			
			new ConectaBBDD().eliminar_tratamiento(sentencia_eliminar_tratamiento);
			
			new Movimientos().recoge_sentencia(sentencia_eliminar_tratamiento);
			
			dialogo3.setVisible(true);
			
			}
		}
		if(a.equals(btnB6))
		{
			
		this.setVisible(false);
	
		}
		if(a.equals(btnB7))
		{
	
		this.setVisible(false);
		}
		

	}



}
