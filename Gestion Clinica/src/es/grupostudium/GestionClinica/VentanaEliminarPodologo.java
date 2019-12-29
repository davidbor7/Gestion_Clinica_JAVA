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

public class VentanaEliminarPodologo extends JFrame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;

	GridBagConstraints constraints = new GridBagConstraints(); //Crear la distribución
	GridBagLayout gridbag = new GridBagLayout(); //Crear las restricciones

	JComboBox choice = new JComboBox();

	String str1 = "Elegir Podólogo...";

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

	JDialog dialogo1 = new JDialog(this, "ELIMINAR PODÓLOGO", true);
	JLabel mensaje1 = new JLabel("<html><body>¿Estás seguro que quieres eliminar este Podólogo?<br>ADVERTENCIA: Se eliminarán todos los tratamientos asociados a este Profesional.</body></html>");
	JDialog dialogo2 = new JDialog(this, "ELIMINAR PODÓLOGO", true);
	JLabel mensaje2 = new JLabel("Podólogo eliminado con éxito.");

	ResultSet rs_podologos = null;
	ResultSet rs_personal = null;

	public VentanaEliminarPodologo()
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
		setTitle("ELIMINAR PODÓLOGO");


		choice.addItem(str1);

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

				rs_personal.close(); //CERRAMOS LOS RESULTSET USADOS
				rs_podologos.close(); 

			}

		} catch (SQLException e)
		{
			System.out.println("Error al cargar el JCOMBOBOX con las consultas de la Base de Datos");
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

		if(a.equals(btnB1))
		{
			dialogo1.setVisible(true);
		}
		if(a.equals(btnB2))
		{
			this.setVisible(false);
		}
		if(a.equals(btnB3))
		{

			String nombre_completo_podologo = (String) choice.getSelectedItem(); //RECOGE LO QUE SELECCIONA EN EL JCOMBOBOX Y LO CONVIERTE EN STRING

			String[] seleciona_id = nombre_completo_podologo.split("-");  //METODO PARA SEPARAR EL ID DEL RESTO DEL NOMBRE

			String sentencia_eliminar_podologo = "DELETE FROM podologos WHERE idPodologo='"+ seleciona_id[0] +"';"; //SENTENCIA PARA ELIMINAR PODOLOGO EN LA BD

			String sentencia_eliminar_personal = "DELETE FROM personal WHERE idPersonal='"+ seleciona_id[0] +"';"; //SENTENCIA PARA ELIMINAR PACIENTE EN LA BD


			if(new ConectaBBDD().eliminar_podologo(sentencia_eliminar_podologo)==true ) 
			{
				
				//SENTENCIA QUE ELIMINA TODOS LOS TTOS DEL PODÓLOGO QUE ESTÁS BORRANDO
				
				String sentencia_eliminar_tratamiento = "DELETE FROM tratamientopersonal WHERE idPersonalFK='"+ seleciona_id[0] + "';";    //SENTENCIA PARA ELIMINAR TRATAMIENTO EN LA BD
				
				new ConectaBBDD().eliminar_tratamiento(sentencia_eliminar_tratamiento);
				
				
				if (new ConectaBBDD().eliminar_personal(sentencia_eliminar_personal)==true)
				{

					new Movimientos().recoge_sentencia(sentencia_eliminar_podologo + " " + sentencia_eliminar_tratamiento + " " + sentencia_eliminar_personal); //ESCRIBE EN EL LOG LA SENTENCIA, ELIMINA TANTO EL PODÓLOGO LOS TTOS ASOCIADOS COMO EL PERSONAL CORRESPONDIENTE
					dialogo1.setVisible(false);
					dialogo2.setVisible(true); 
				}

			}
			else
			{
				dialogo1.setVisible(false);
				mensaje2.setText("ERROR AL ELIMINAR PODÓLOGO");
				dialogo2.setVisible(true); 
			}



			//LIMPIAR EL JCOMBOBOX Y ACTUALIZAR LOS DATOS

			choice.removeAllItems();

			choice.addItem(str1);

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

					rs_personal.close(); //CERRAMOS LOS RESULTSET USADOS
					rs_podologos.close(); 

				}
			} catch (SQLException e)
			{
				System.out.println("Error al cargar el JCOMBOBOX con las consultas de la Base de Datos");
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
