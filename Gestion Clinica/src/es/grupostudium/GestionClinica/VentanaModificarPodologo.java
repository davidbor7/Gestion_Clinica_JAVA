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

public class VentanaModificarPodologo extends JFrame implements WindowListener, ActionListener, ItemListener
{
	private static final long serialVersionUID = 1L;

	String str1 = "Elegir Podólogo...";
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
	JLabel numerocolegiado = new JLabel("Número Colegiado:");
	JLabel especialidad = new JLabel("Especialidad:");

	JTextField textonombre = new JTextField(14);
	JTextField textoapellidos = new JTextField(14);
	JTextField textofechaNacimiento = new JTextField(7);
	JTextField textodireccion = new JTextField(14);
	JTextField textonumerocolegiado = new JTextField(7);
	JTextField textoespecialidad = new JTextField(14);

	JButton btnGuardarCambios = new JButton("Guardar Cambios");
	JButton btnLimpiar = new JButton("Limpiar");

	JDialog dialogo3 = new JDialog(this,"MODIFICAR PODÓLOGO", true);
	JLabel mensaje3 = new JLabel("");
	JButton btnAceptar= new JButton("Aceptar");

	JComboBox choice = new JComboBox();

	ResultSet rs_podologos = null;
	ResultSet rs_personal = null;

	VentanaModificarPodologo()
	{

		setLayout(new BorderLayout());       
		setSize(600,400);
		setTitle("MODIFICAR PODÓLOGO");
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
		panel1.add(numerocolegiado,constraints);

		constraints.weighty=0.0;

		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		panel1.add(especialidad,constraints);

		constraints.weighty=0.0;

		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		panel1.add(textonombre,constraints);

		constraints.weighty=0.0;
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
		panel1.add(textonumerocolegiado,constraints);

		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		panel1.add(textoespecialidad,constraints);


		btnGuardarCambios.addActionListener(this);
		panel2.add(btnGuardarCambios,constraints);

		add(panel1,"Center");
		add(panel2, "South");

		panel3.setLayout(new FlowLayout());	
		choice.addItemListener(this);
		choice.addItem("Elegir Podólogo...");
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



		try
		{	
			
			rs_podologos = (ResultSet) new ConectaBBDD().obtener_todos_los_datos_de_los_podologos("SELECT idPodologo, numeroColegiadoPodologo, especialidadPodologo, idPersonalFK FROM podologos;");

			if(rs_podologos.next())
			{
				rs_podologos.beforeFirst(); //LO DEVOLVEMOS A SU ESTADO INICIAL DESPUES DEL "IF"
				
				while(rs_podologos.next())
				{
					rs_personal = (ResultSet) new ConectaBBDD().obtener_todos_los_datos_del_personal("SELECT idPersonal, nombrePersonal, apellidosPersonal, fechaNacimientoPersonal, direccionPersonal FROM personal WHERE idPersonal='"+ rs_podologos.getString("idPersonalFK") + "';");

					rs_personal.next();

					choice.addItem(rs_personal.getString("idPersonal") +"-"+ rs_personal.getString("nombrePersonal") +" "+ rs_personal.getString("apellidosPersonal"));//AÑADE NOMBRE Y APELLIDOS DE LOS RS USADOS ANTERIORMENTE Y LOS METE EN UN JCOMBOBOX
				}
				
	
				rs_personal.close(); //CERRAMOS RESULSETS
				rs_podologos.close(); 
				
			}
			
		} catch (SQLException e)
		{
			System.out.println("Error al cargar el JCOMBOBOX con las consultas de la Base de Datos");
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
		
		String nombre_completo_podologo = (String) choice.getSelectedItem(); //RECOGE LO QUE SELECCIONA EN EL JCOMBOBOX Y LO CONVIERTE EN STRING
		
		if(a.equals(btnGuardarCambios) && !nombre_completo_podologo.equals("Elegir Podólogo..."))
		{

			String[] seleciona_id = nombre_completo_podologo.split("-");  //METODO PARA SEPARAR EL ID DEL RESTO DEL NOMBRE
			

			String sentencia_modificar_podologo = "UPDATE podologos SET numeroColegiadoPodologo = '" + textonumerocolegiado.getText() + "', especialidadPodologo = '" + textoespecialidad.getText() + "' WHERE idPodologo='"+ seleciona_id[0] +"';";

			if(new ConectaBBDD().modificar_podologo(sentencia_modificar_podologo)==true)
			{
				String sentencia_modificar_personal = "UPDATE personal SET nombrePersonal = '" + textonombre.getText() + "', apellidosPersonal = '" + textoapellidos.getText() + "', fechaNacimientoPersonal = '" + textofechaNacimiento.getText() + "', direccionPersonal = '" + textodireccion.getText() + "' WHERE idPersonal='"+ seleciona_id[0] +"';";

				new Movimientos().recoge_sentencia(sentencia_modificar_personal); //ESCRIBE EN EL LOG LA SENTENCIA
				
				if(new ConectaBBDD().modificar_personal(sentencia_modificar_personal)==true) 
				{
					mensaje3.setText("Cambios realizados con éxito");
					dialogo3.setVisible(true);
				}
				else
				{
					mensaje3.setText("ERROR AL ACTUALIZAR LOS DATOS");
					dialogo3.setVisible(true);
				}

			}else
			{
				mensaje3.setText("ERROR AL ACTUALIZAR LOS DATOS");
				dialogo3.setVisible(true);
			}


		}
		if(a.equals(btnAceptar))
		{
			dialogo3.setVisible(false);
		}                       
	}


	public void itemStateChanged(ItemEvent ie)
	{

		String nombre_completo_podologo = (String) choice.getSelectedItem(); //RECOGE LO QUE SELECCIONA EN EL JCOMBOBOX Y LO CONVIERTE EN STRING

		String[] seleciona_id = nombre_completo_podologo.split("-");  //METODO PARA SEPARAR EL ID DEL RESTO DEL NOMBRE


		if(!nombre_completo_podologo.equals("Elegir Podólogo..."))
		{
			try
			{

				rs_podologos = (ResultSet) new ConectaBBDD().obtener_todos_los_datos_de_los_podologos("SELECT numeroColegiadoPodologo, especialidadPodologo, idPersonalFK FROM podologos WHERE idPodologo='"+ seleciona_id[0] +"';");		

				rs_podologos.next();

				rs_personal = (ResultSet) new ConectaBBDD().obtener_todos_los_datos_del_personal("SELECT nombrePersonal, apellidosPersonal, fechaNacimientoPersonal, direccionPersonal FROM personal WHERE idPersonal='"+ rs_podologos.getString("idPersonalFK") +"';");

				rs_personal.next();

			} 
			catch (SQLException e)
			{	
				e.printStackTrace();
				System.out.println("Error al capturar los datos para el JCOMBOBOX");
			}	

			try
			{
				//ESTO VA RELLENANDO LOS JTEXTFIELD CON LOS DATOS DEL PODÓLOGO SELECCIONADO EN EL JCOMBOBOX

				textonombre.setText(rs_personal.getString(1)); 
				textoapellidos.setText(rs_personal.getString(2));
				textofechaNacimiento.setText(rs_personal.getString(3));														
				textodireccion.setText(rs_personal.getString(4));
				textonumerocolegiado.setText(rs_podologos.getString(1));
				textoespecialidad.setText(rs_podologos.getString(2));

				rs_podologos.close();
				rs_personal.close();

			}
			catch (Exception e)
			{
				System.out.println("Error al rellenar los JTEXTFIELD con los datos del RESULTSET");
			}		

		}else
		{
			textonombre.setText(""); 
			textoapellidos.setText("");
			textofechaNacimiento.setText("");														
			textodireccion.setText("");
			textonumerocolegiado.setText("");
			textoespecialidad.setText("");
		}

	}

}

