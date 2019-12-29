package es.grupostudium.GestionClinica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaNuevoPaciente extends JFrame implements WindowListener, ActionListener
{

	private static final long serialVersionUID = 1L;

	GridBagConstraints constraints = new GridBagConstraints(); //Crear la distribución
	GridBagLayout gridbag = new GridBagLayout(); //Crear las restricciones

	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();

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

	JButton btnCrear = new JButton("Crear Paciente");
	JButton btnLimpiar = new JButton("Limpiar");

	JDialog dialogo1 = new JDialog(this, "NUEVO PACIENTE", true);
	JLabel mensaje1 = new JLabel("");
	JButton btnAceptar1= new JButton("Aceptar");

	JDialog dialogo2 = new JDialog(this, "NUEVO PACIENTE", true);
	JLabel mensaje2 = new JLabel("");
	JButton btnAceptar2= new JButton("Aceptar");



	VentanaNuevoPaciente()
	{
		setLayout(gridbag);
		setTitle("NUEVO PACIENTE");
		setResizable(false);
		setSize(600,400);
		setLocationRelativeTo(null);


		constraints.insets = new Insets(0,0,0,50); //MARGEN A LA DERECHA
		constraints.anchor =  GridBagConstraints.LINE_END;


		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty=0.5;
		add(nombre,constraints);

		constraints.weighty=0.0;

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty=0.5;
		add(apellidos,constraints);

		constraints.weighty=0.0;

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty=0.5;
		add(fechaNacimiento,constraints);

		constraints.weighty=0.0;

		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty=0.5;
		add(direccion,constraints);

		constraints.weighty=0.0;

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty=0.5;
		add(dni,constraints);

		constraints.weighty=0.0;

		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(textonombre,constraints);

		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(textoapellidos,constraints);

		constraints.anchor =  GridBagConstraints.LINE_START;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(textofechaNacimiento,constraints);
		constraints.anchor =  GridBagConstraints.LINE_END;

		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(textodireccion,constraints);

		constraints.anchor =  GridBagConstraints.LINE_START;
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(textodni,constraints);
		constraints.anchor =  GridBagConstraints.LINE_END;

		constraints.insets = new Insets(0,0,0,0); //MARGEN A LA DERECHA
		constraints.anchor =  GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.NONE;

		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(btnCrear,constraints);
		btnCrear.addActionListener(this);

		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(btnLimpiar,constraints);
		btnLimpiar.addActionListener(this);

		dialogo1.setLayout(new BorderLayout());
		dialogo1.setSize(600,400);
		dialogo1.setResizable(false);
		dialogo1.setLocationRelativeTo(null);
		dialogo1.addWindowListener(this);
		btnAceptar1.addActionListener(this);
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		panel1.add(mensaje1);
		panel2.add(btnAceptar1);
		dialogo1.add(panel1, "North");
		dialogo1.add(panel2, "Center");

		dialogo2.setLayout(new BorderLayout());
		dialogo2.setSize(600,400);
		dialogo2.setResizable(true);
		dialogo2.setLocationRelativeTo(null);
		dialogo2.addWindowListener(this);
		btnAceptar2.addActionListener(this);
		panel3.setLayout(new FlowLayout());
		panel4.setLayout(new FlowLayout());
		panel3.add(mensaje2);
		panel4.add(btnAceptar2);
		dialogo2.add(panel3, "North");
		dialogo2.add(panel4, "Center");

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
		if(a.equals(btnLimpiar))
		{
			textonombre.selectAll();
			textonombre.setText("");
			textoapellidos.selectAll();
			textoapellidos.setText("");
			textofechaNacimiento.selectAll();
			textofechaNacimiento.setText("");
			textodireccion.selectAll();
			textodireccion.setText("");
			textodni.selectAll();
			textodni.setText("");
			textonombre.requestFocus();
		}
		if(a.equals(btnCrear))
		{
			String sentencia_nuevo_paciente = "INSERT INTO pacientes (nombrePaciente, apellidosPaciente, fechaNacimientoPaciente, direccionPaciente, dniPaciente) VALUES('" +textonombre.getText()+ "','" + textoapellidos.getText() + "','" + textofechaNacimiento.getText() + "','" +  textodireccion.getText() + "','" + textodni.getText() + "');";

			if(new ConectaBBDD().agregar_paciente(sentencia_nuevo_paciente)==true)  //SENTENCIA PARA AGREGAR UN NUEVO PACIENTE A LA BD
			{
				new Movimientos().recoge_sentencia("INSERT INTO pacientes (nombrePaciente, apellidosPaciente, fechaNacimientoPaciente, direccionPaciente, dniPaciente) VALUES('" +textonombre.getText()+ "','" + textoapellidos.getText() + "','" + textofechaNacimiento.getText() + "','" +  textodireccion.getText() + "','" + textodni.getText() + "');"); //ESCRIBE EN EL LOG LA SENTENCIA
				
				mensaje1.setText("Paciente creado con éxito");
				dialogo1.setVisible(true);
				
				//LIMPIAMOS TODOS LOS JTEXTFIELD PARA INTRODUCIR OTRO PACIENTE
				textonombre.selectAll();
				textonombre.setText("");
				textoapellidos.selectAll();
				textoapellidos.setText("");
				textofechaNacimiento.selectAll();
				textofechaNacimiento.setText("");
				textodireccion.selectAll();
				textodireccion.setText("");
				textodni.selectAll();
				textodni.setText("");
				textonombre.requestFocus();
				
			}else
			{
				mensaje2.setText("<html><body>Error al insertar los datos por parte del Usuario.<br> EJEMPLO DE FORMATO DE FECHA VÁLIDO AAAA-MM-DD --> 2019-04-18<br>EJEMPLO DE FORMATO DE DNI VÁLIDO (9 CARACTERES) --> 00000000A <br>INSERTAR TODOS LOS CAMPOS</body></html>");
				dialogo2.setVisible(true);
			}

		}
		if(a.equals(btnAceptar1))
		{
			dialogo1.setVisible(false);
		}
		if(a.equals(btnAceptar2))
		{
			dialogo2.setVisible(false);
		}

	}

}
