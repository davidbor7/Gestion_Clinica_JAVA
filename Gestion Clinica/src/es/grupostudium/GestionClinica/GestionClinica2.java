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

public class GestionClinica2 extends JFrame implements WindowListener, ActionListener
{

	private static final long serialVersionUID = 1L;

	JMenuBar barraMenu = new JMenuBar();

	JMenu pacientes = new JMenu("Pacientes");
	JMenu tratamientos = new JMenu("Tratamientos");
	JMenu personal = new JMenu("Personal");
	JMenu ayuda = new JMenu("Ayuda");

	JMenuItem nuevoPaciente = new JMenuItem("Nuevo Paciente");

	JMenuItem nuevoTratamiento = new JMenuItem("Nuevo Tratamiento");

	JMenu podologosPersonal = new JMenu("Podólogos");
	JMenuItem nuevoPodologo = new JMenuItem("Nuevo Podólogo");

	JMenuItem manual = new JMenuItem("Ver Manual de Usuario");

	

	public GestionClinica2() 
	{

		setLayout(new FlowLayout());
		setTitle("Gestión Clínica");
		setSize(800,600);
		setLocationRelativeTo(null);

		setJMenuBar(barraMenu); 

		nuevoPaciente.addActionListener(this);
		pacientes.add(nuevoPaciente);

		nuevoTratamiento.addActionListener(this);
		tratamientos.add(nuevoTratamiento);


		nuevoPodologo.addActionListener(this);
		podologosPersonal.add(nuevoPodologo);

		personal.add(podologosPersonal);

		ayuda.add(manual);
		manual.addActionListener(this);

		barraMenu.add(pacientes);
		barraMenu.add(tratamientos);
		barraMenu.add(personal);
		barraMenu.add(ayuda);

		
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
		}

		if(a.equals(nuevoPodologo))
		{
			new VentanaNuevoPodologo();
		}
		if(a.equals(nuevoTratamiento))
		{
			new VentanaNuevoTratamiento();

		}
	}
	public void windowActivated(WindowEvent we){}
	public void windowClosed(WindowEvent we){}
	public void windowClosing(WindowEvent we)
	{
		
		System.exit(0);       
	}
	public void windowDeactivated(WindowEvent we){}
	public void windowDeiconified(WindowEvent we){}
	public void windowIconified(WindowEvent we){}
	public void windowOpened(WindowEvent we){}

}

