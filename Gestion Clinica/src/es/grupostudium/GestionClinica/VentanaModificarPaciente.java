package es.grupostudium.GestionClinica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JTextField;

public class VentanaModificarPaciente extends JFrame implements WindowListener, ActionListener, ItemListener
{           
	private static final long serialVersionUID = 1L;

	String str1 = "Elegir Paciente...";
	GridBagConstraints constraints = new GridBagConstraints(); //Crear la distribución
	GridBagLayout gridbag = new GridBagLayout(); //Crear las restricciones

	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();

	JLabel nombre = new JLabel("Nombre:");
	JLabel apellidos = new JLabel("Apellidos:");
	JLabel fechaNacimiento = new JLabel("Fecha de Nacimiento:");
	JLabel direccion = new JLabel("Dirección:");
	JLabel dni= new JLabel("DNI:");

	JTextField textonombre = new JTextField(14);
	JTextField textoapellidos = new JTextField(14);
	JTextField textofechaNacimiento = new JTextField(7);
	JTextField textodireccion = new JTextField(14);
	JTextField textodni = new JTextField(7);

	JButton btnGuardarCambios = new JButton("Guardar Cambios");
	JButton btnLimpiar = new JButton("Limpiar");

	JDialog dialogo3 = new JDialog(this,"MODIFICAR PACIENTE", true);
	JLabel mensaje3 = new JLabel("");
	JButton btnAceptar= new JButton("Aceptar");

	JComboBox choice = new JComboBox();

	ResultSet rs_id_pacientes = null;
	ResultSet rs_nombres_pacientes = null;
	ResultSet rs_apellidos_pacientes = null;

	ResultSet rs_datos_pacientes = null;

	VentanaModificarPaciente()
	{

		setLayout(new BorderLayout());       
		setSize(600,400);
		setTitle("MODIFICAR PACIENTE");
		setResizable(false);
		setLocationRelativeTo(null);

		panel1.setLayout(gridbag);
		panel2.setLayout(new FlowLayout());


		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty=0.5;
		panel1.add(nombre,constraints);

		constraints.weighty=0.0;

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty=0.5;
		panel1.add(apellidos,constraints);

		constraints.weighty=0.0;

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty=0.5;
		panel1.add(fechaNacimiento,constraints);

		constraints.weighty=0.0;

		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty=0.5;
		panel1.add(direccion,constraints);

		constraints.weighty=0.0;

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty=0.5;
		panel1.add(dni,constraints);

		constraints.weighty=0.0;

		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		panel1.add(textonombre,constraints);

		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		panel1.add(textoapellidos,constraints);

		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		panel1.add(textofechaNacimiento,constraints);


		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		panel1.add(textodireccion,constraints);

		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		panel1.add(textodni,constraints);


		btnGuardarCambios.addActionListener(this);
		panel2.add(btnGuardarCambios,constraints);

		add(panel1,"Center");
		add(panel2, "South");

		panel3.setLayout(new FlowLayout());		
		choice.addItemListener(this);
		choice.addItem("Elegir Paciente...");
		panel3.add(choice);
		add(panel3, "North");


		dialogo3.setLayout(new BorderLayout());
		dialogo3.setSize(600,400);
		dialogo3.setResizable(false);
		dialogo3.setLocationRelativeTo(null);
		dialogo3.addWindowListener(this);
		btnAceptar.addActionListener(this);
		panel4.setLayout(new FlowLayout());
		panel5.setLayout(new FlowLayout());
		panel4.add(mensaje3);
		panel5.add(btnAceptar);
		dialogo3.add(panel4, "North");
		dialogo3.add(panel5, "Center");


		rs_id_pacientes = (ResultSet) new ConectaBBDD().obtener_id_de_los_pacientes("SELECT idPaciente FROM pacientes;"); //DEVUELVE RS CON LOS NOMBRES DE LOS PACIENTES
		rs_nombres_pacientes = (ResultSet) new ConectaBBDD().obtener_nombres_de_los_pacientes("SELECT nombrePaciente FROM pacientes;"); //DEVUELVE RS CON LOS NOMBRES DE LOS PACIENTES
		rs_apellidos_pacientes = (ResultSet) new ConectaBBDD().obtener_apellidos_de_los_pacientes("SELECT apellidosPaciente from pacientes;");//DEVUELVE RS CON LOS APELLIDOS DE LOS PACIENTES


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


		addWindowListener(this);
		setVisible(true);
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
		a=ae.getSource();
		
		String nombre_completo_paciente = (String) choice.getSelectedItem(); //RECOGE LO QUE SELECCIONA EN EL JCOMBOBOX Y LO CONVIERTE EN STRING
		
		if(a.equals(btnGuardarCambios) && !nombre_completo_paciente.equals("Elegir Paciente...")) //La segunda parte del AND lo hacemos para que no puedas realizar cambios cuando esta marcada la opcion "elegir paciente".
		{
			try 
			{

				String[] seleciona_id = nombre_completo_paciente.split("-");  //METODO PARA SEPARAR EL ID DEL RESTO DEL NOMBRE

				String sentencia_modificar_paciente = "UPDATE pacientes SET nombrePaciente = '" + textonombre.getText() + "', apellidosPaciente = '" + textoapellidos.getText() + "', fechaNacimientoPaciente = '" + textofechaNacimiento.getText() + "', direccionPaciente = '" + textodireccion.getText() + "', dniPaciente = '" + textodni.getText() + "' WHERE idPaciente = '" + seleciona_id[0] + "';"; //SENTENCIA PARA ACTUALIZAR PACIENTE EN LA BD

				if(new ConectaBBDD().modificar_paciente(sentencia_modificar_paciente)==true)
				{
					
					new Movimientos().recoge_sentencia(sentencia_modificar_paciente); //ESCRIBE EN EL LOG LA SENTENCIA
					
					mensaje3.setText("Cambios realizados con éxito");
					dialogo3.setVisible(true);
				}else
				{
					System.out.println("Error en la sentencia UPDATE");
					mensaje3.setText("ERROR AL ACTUALIZAR LOS DATOS");
					dialogo3.setVisible(true);
				}
		
				

			}catch(Exception e)
			{
				System.out.println(e);
			}


		}


		if(a.equals(btnAceptar))
		{
			dialogo3.setVisible(false);	
		}                       
	}

	public void itemStateChanged(ItemEvent ie)
	{

		String nombre_completo_paciente = (String) choice.getSelectedItem(); //RECOGE LO QUE SELECCIONA EN EL JCOMBOBOX Y LO CONVIERTE EN STRING

		String[] seleciona_id = nombre_completo_paciente.split("-");  //METODO PARA SEPARAR EL ID DEL RESTO DEL NOMBRE

		rs_datos_pacientes = (ResultSet) new ConectaBBDD().obtener_todos_los_datos_de_los_pacientes("SELECT nombrePaciente, apellidosPaciente, fechaNacimientoPaciente, direccionPaciente, dniPaciente FROM pacientes WHERE idPaciente = '" + seleciona_id[0] +"';");	//SENTENCIA QUE RECOGE LOS DATOS DE LOS PACIENTES MENOS EL ID PARA LUEGO RELLENAR LOS JTEXTFIELD

		if(!nombre_completo_paciente.equals("Elegir Paciente..."))
		{
			try
			{
				// BUCLE PARA EL RESULSET CON LOS DATOS DE LOS PACIENTES

				while (rs_datos_pacientes.next())
				{
					textonombre.setText(rs_datos_pacientes.getString(1));  //ESTO VA RELLENANDO LOS JTEXTFIELD CON LOS DATOS DEL PACIENTE SELECCIONADO EN EL JCOMBOBOX
					textoapellidos.setText(rs_datos_pacientes.getString(2));
					textofechaNacimiento.setText(rs_datos_pacientes.getString(3));														
					textodireccion.setText(rs_datos_pacientes.getString(4));
					textodni.setText(rs_datos_pacientes.getString(5));
				}
			}catch(SQLException e)
			{
				System.out.println("Error en la sentencia SQL" + e.getMessage());
			}

		}else
		{
			textonombre.setText(""); 
			textoapellidos.setText("");
			textofechaNacimiento.setText("");														
			textodireccion.setText("");
			textodni.setText("");
		}

	}
	
}
