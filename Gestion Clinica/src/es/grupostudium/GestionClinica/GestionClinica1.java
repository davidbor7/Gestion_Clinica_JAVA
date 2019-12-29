package es.grupostudium.GestionClinica;

import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GestionClinica1 extends JFrame implements WindowListener, ActionListener
{

	private static final long serialVersionUID = 1L;

	JMenuBar barraMenu = new JMenuBar();

	JMenu pacientes = new JMenu("Pacientes");
	JMenu tratamientos = new JMenu("Tratamientos");
	JMenu personal = new JMenu("Personal");
	JMenu ayuda = new JMenu("Ayuda");

	JMenuItem nuevoPaciente = new JMenuItem("Nuevo Paciente");
	JMenuItem eliminarPaciente = new JMenuItem("Eliminar Paciente");
	JMenuItem modificarPaciente = new JMenuItem("Modificar Paciente");
	JMenuItem listadePacientes = new JMenuItem("Lista de Pacientes");

	JMenuItem nuevoTratamiento = new JMenuItem("Nuevo Tratamiento");
	JMenuItem eliminarTratamiento = new JMenuItem("Eliminar Tratamiento");
	JMenuItem verTratamientos = new JMenuItem("Ver Tratamientos");

	JMenu podologosPersonal = new JMenu("Podólogos");
	JMenuItem nuevoPodologo = new JMenuItem("Nuevo Podólogo");
	JMenuItem eliminarPodologo = new JMenuItem("Eliminar Podólogo");
	JMenuItem modificarPodologo = new JMenuItem("Modificar Podólogos");
	JMenuItem verPodologos = new JMenuItem("Ver Podólogos");

	JMenuItem manual = new JMenuItem("Ver Manual de Usuario");




	public GestionClinica1() 
	{

		setLayout(new FlowLayout());
		setTitle("CLÍNICA SALLE");
		setSize(800,600);
		setLocationRelativeTo(null);

		setJMenuBar(barraMenu); //Estableces la barra de menu;

		pacientes.add(nuevoPaciente);
		pacientes.add(eliminarPaciente);
		pacientes.add(modificarPaciente);
		pacientes.add(listadePacientes);

		nuevoPaciente.addActionListener(this);
		eliminarPaciente.addActionListener(this);
		modificarPaciente.addActionListener(this);
		listadePacientes.addActionListener(this);

		tratamientos.add(nuevoTratamiento);
		tratamientos.add(eliminarTratamiento);
		tratamientos.add(verTratamientos);

		nuevoTratamiento.addActionListener(this);
		eliminarTratamiento.addActionListener(this);
		verTratamientos.addActionListener(this);

		podologosPersonal.add(nuevoPodologo);
		podologosPersonal.add(eliminarPodologo);
		podologosPersonal.add(modificarPodologo);
		podologosPersonal.add(verPodologos);
		personal.add(podologosPersonal);

		nuevoPodologo.addActionListener(this);
		eliminarPodologo.addActionListener(this);
		modificarPodologo.addActionListener(this);
		verPodologos.addActionListener(this);

		ayuda.add(manual);
		manual.addActionListener(this);

		barraMenu.add(pacientes);
		barraMenu.add(tratamientos);
		barraMenu.add(personal);
		barraMenu.add(ayuda);


		//INSERTAR LA IMAGEN EN EL LAYOUT
		ImageIcon image = new ImageIcon("PRINCIPAL1.png");  
		int scale = 1;  

		int width = image.getIconWidth();  
		int height = image.getIconHeight();  
		BufferedImage buffer = new BufferedImage(scale * width, scale * height, BufferedImage.TYPE_INT_ARGB);  
		Graphics2D graphics = buffer.createGraphics();  

		graphics.scale(scale,scale);  
		image.paintIcon(null, graphics, 0, 0);  
		graphics.dispose();  
		JLabel label = new JLabel(new ImageIcon(buffer)); 
		add(label);


		addWindowListener(this);
		setVisible(true);


	}



	public void actionPerformed(ActionEvent ae)
	{
		Object a;
		a = ae.getSource();

		if(a.equals(manual))
		{
			new Ayuda();

		}

		if(a.equals(nuevoPaciente))
		{
			new VentanaNuevoPaciente();
		}else
		{
			if(a.equals(eliminarPaciente))
			{
				new VentanaEliminarPaciente();

			}else
			{
				if(a.equals(modificarPaciente))
				{
					new VentanaModificarPaciente();
				}else
				{
					if(a.equals(listadePacientes))
					{
						new VentanaListaPacientes();
					}
				}
			}
		}

		if(a.equals(nuevoPodologo))
		{
			new VentanaNuevoPodologo();
		}else
		{
			if(a.equals(eliminarPodologo))
			{
				new VentanaEliminarPodologo();
			}
			else
			{
				if(a.equals(modificarPodologo))
				{
					new VentanaModificarPodologo();
				}
				else
				{
					if(a.equals(verPodologos))
					{
						new VentanaListaPodologos();
					}
				}
			}
		}

		if(a.equals(nuevoTratamiento))
		{
			new VentanaNuevoTratamiento();

		}else
			if(a.equals(eliminarTratamiento))
			{
				new VentanaEliminarTratamiento();

			}
			else
			{
				if(a.equals(verTratamientos))
				{
					new VentanaListaTratamientos();
				}
			}

	}
	public void windowActivated(WindowEvent we){}
	public void windowClosed(WindowEvent we)
	{
		System.exit(0);  	
	}
	public void windowDeactivated(WindowEvent we){}
	public void windowDeiconified(WindowEvent we){}
	public void windowIconified(WindowEvent we){}
	public void windowOpened(WindowEvent we){}
	public void windowClosing(WindowEvent we){}

}
