package es.grupostudium.GestionClinica;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class VentanaNuevoTratamiento extends JFrame implements WindowListener, ActionListener, MouseListener
{
	private static final long serialVersionUID = 1L;

	String recoge_fecha = new String();
	String fecha_invertida = new String();
	
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();

	JLabel profesional = new JLabel("Profesional");
	JLabel paciente = new JLabel("Paciente");
	JLabel importe = new JLabel("Importe del Tratamiento");
	JLabel fecha = new JLabel("Fecha del Tratamiento");

	JComboBox textoprofesional = new JComboBox();
	JComboBox textopaciente = new JComboBox();
	JTextField textoimporte = new JTextField(30);
	JTextField textofecha = new JTextField(30);

	JButton btnAceptar= new JButton("Aceptar");
	JButton btnEliminar = new JButton("Eliminar");

	JDialog dialogo1 = new JDialog(this, "NUEVO TRATAMIENTO", true);
	JLabel mensaje1 = new JLabel("Tratamiento añadido con éxito.");
	JButton btnAceptar1= new JButton("Aceptar");

	ResultSet rs_personal = null;

	ResultSet rs_id_pacientes = null;
	ResultSet rs_nombres_pacientes = null;
	ResultSet rs_apellidos_pacientes = null;


	VentanaNuevoTratamiento()
	{
		setLayout(new BorderLayout());
		setTitle("NUEVO TRATAMIENTO");
		setResizable(true);
		setSize(800,300);
		setLocationRelativeTo(null);
		
		
		panel1.setLayout(new GridLayout(2,4));
		panel2.setLayout(new FlowLayout());

		btnAceptar.addActionListener(this);
		panel2.add(btnAceptar);

		panel1.add(profesional);
		panel1.add(paciente);
		panel1.add(importe);
		panel1.add(fecha);
		panel1.add(textoprofesional);
		panel1.add(textopaciente);
		panel1.add(textoimporte);
		panel1.add(textofecha);


		add(panel1,"North");
		add(panel2, "Center");

		dialogo1.setLayout(new BorderLayout());
		dialogo1.setSize(800,300);
		dialogo1.setTitle("NUEVO TRATAMIENTO");
		dialogo1.setResizable(true);
		dialogo1.setLocationRelativeTo(null);
		dialogo1.addWindowListener(this);
		btnAceptar1.addActionListener(this);
		panel3.add(mensaje1);
		panel4.add(btnAceptar1);
		dialogo1.add(panel3, "North");
		dialogo1.add(panel4, "Center");

		rs_personal = (ResultSet) new ConectaBBDD().obtener_todos_los_datos_del_personal("SELECT * FROM personal;");

		try
		{

			textoprofesional.addItem("Elegir Profesional...");

			while(rs_personal.next())
			{

				textoprofesional.addItem(rs_personal.getString("idPersonal") +"-"+ rs_personal.getString("nombrePersonal") +" "+ rs_personal.getString("apellidosPersonal"));//AÑADE NOMBRE Y APELLIDOS DE LOS RS USADOS ANTERIORMENTE Y LOS METE EN UN JCOMBOBOX

			}
			
			rs_personal.close();
			
			
		} catch (SQLException e)
		{
			System.out.println("Error al cargar el JCOMBOBOX 1 con las consultas de la Base de Datos");

		}


		rs_id_pacientes = (ResultSet) new ConectaBBDD().obtener_id_de_los_pacientes("SELECT idPaciente FROM pacientes;"); //DEVUELVE RS CON LOS NOMBRES DE LOS PACIENTES
		rs_nombres_pacientes = (ResultSet) new ConectaBBDD().obtener_nombres_de_los_pacientes("SELECT nombrePaciente FROM pacientes;"); //DEVUELVE RS CON LOS NOMBRES DE LOS PACIENTES
		rs_apellidos_pacientes = (ResultSet) new ConectaBBDD().obtener_apellidos_de_los_pacientes("SELECT apellidosPaciente from pacientes;");//DEVUELVE RS CON LOS APELLIDOS DE LOS PACIENTES


		try //USAMOS UN WHILE PARA RELLENAR EL JCOMBOX CON LOS RESULTADOS DEL RESULSET
		{
			textopaciente.addItem("Elegir Paciente...");

			while(rs_id_pacientes.next() && rs_nombres_pacientes.next() && rs_apellidos_pacientes.next()) 
			{
				textopaciente.addItem(rs_id_pacientes.getString("idPaciente") +"-"+ rs_nombres_pacientes.getString("nombrePaciente") +" "+ rs_apellidos_pacientes.getString("apellidosPaciente"));//AÑADE NOMBRE Y APELLIDOS DE LOS RS USADOS ANTERIORMENTE Y LOS METE EN UN JCOMBOBOX
			}
			
			rs_id_pacientes.close();
			rs_nombres_pacientes.close();
			rs_apellidos_pacientes.close();
			
		}
		catch (SQLException e)
		{

			System.out.println("Error al cargar el JCOMBOBOX 2 con las consultas de la Base de Datos");
		}

		
		recoge_fecha = new Movimientos().recoge_fecha_actual();
		textofecha.setText(recoge_fecha);
		textoimporte.setText("0.00");
		
		textoimporte.addMouseListener(this);
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


		if(a.equals(btnAceptar))
		{

			try {

				
				String nombre_completo_paciente = (String) textopaciente.getSelectedItem(); //RECOGE LO QUE SELECCIONA EN EL JCOMBOBOX Y LO CONVIERTE EN STRING

				String[] seleciona_id_1 = nombre_completo_paciente.split("-");  //METODO PARA SEPARAR EL ID DEL RESTO DEL NOMBRE
				
				String nombre_completo_podologo = (String) textoprofesional.getSelectedItem(); //RECOGE LO QUE SELECCIONA EN EL JCOMBOBOX Y LO CONVIERTE EN STRING

				String[] seleciona_id_2 = nombre_completo_podologo.split("-");  //METODO PARA SEPARAR EL ID DEL RESTO DEL NOMBRE

				double importe = Double.parseDouble(textoimporte.getText());

				if(new ConectaBBDD().agregar_tratamiento("INSERT INTO tratamientopersonal (idPacienteFK, idPersonalFK, importeTratamiento, fechaTratamiento) VALUES ("+seleciona_id_1[0]+", "+ seleciona_id_2[0] +", "+ importe +", '"+ textofecha.getText()+"');") == true)
				{
					new Movimientos().recoge_sentencia("INSERT INTO tratamientopersonal (idPacienteFK, idPersonalFK, importeTratamiento, fechaTratamiento) VALUES ("+seleciona_id_1[0]+", "+ seleciona_id_2[0] +", "+ importe +", '"+ textofecha.getText()+"');"); //ESCRIBE EN EL LOG LA SENTENCIA
					dialogo1.setVisible(true);
				}
				else
				{
					mensaje1.setText("<html><body>Error al insertar los datos por parte del Usuario.<br> EJEMPLO DE FORMATO DE FECHA VÁLIDO AAAA-MM-DD --> 2019-04-18<br>EJEMPLO DE FORMATO DE IMPORTE VÁLIDO   00.00 --> 50.05<br>SE DEBE ELEGIR UN PROFESIONAL Y UN PACIENTE<br>TODOS LOS CAMPOS SON OBLIGATORIOS</html></body>");
					dialogo1.setVisible(true);
				}


			}
			/*catch(Exception e)
			{
				e.printStackTrace();
				mensaje1.setText("<html><body>Error al insertar los datos por parte del Usuario.<br> EJEMPLO DE FORMATO DE FECHA VÁLIDO AAAA-MM-DD --> 2019-04-18<br>EJEMPLO DE FORMATO DE IMPORTE VÁLIDO   00.00 --> 50.05<br>SE DEBE ELEGIR UN PROFESIONAL Y UN PACIENTE<br>TODOS LOS CAMPOS SON OBLIGATORIOS</html></body>");
				dialogo1.setVisible(true);
			}*/
			catch(NumberFormatException e)
			{
				textoimporte.setText("");
				mensaje1.setText("<html><body>Error al insertar los datos por parte del Usuario.<br> EJEMPLO DE FORMATO DE FECHA VÁLIDO AAAA-MM-DD --> 2019-04-18<br>EJEMPLO DE FORMATO DE IMPORTE VÁLIDO   00.00 --> 50.05<br>SE DEBE ELEGIR UN PROFESIONAL Y UN PACIENTE<br>TODOS LOS CAMPOS SON OBLIGATORIOS</html></body>");
				dialogo1.setVisible(true);
			}
			

		}
		
		if(a.equals(btnAceptar1))
		{
			dialogo1.setVisible(false);
		}

	}

	public void mouseClicked(MouseEvent me){}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}

	public void mousePressed(MouseEvent me)
	{
		if(textoimporte.requestFocus(true))
		{
			textoimporte.setText("");
		}	
	}
	
}
