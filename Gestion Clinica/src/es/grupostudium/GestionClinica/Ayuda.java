package es.grupostudium.GestionClinica;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.JFrame;

public class Ayuda extends JFrame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;


	public Ayuda()
	{

		
		try
		{
		Runtime.getRuntime().exec("hh.exe Ayuda.chm");
		}
		catch (IOException e)
		{
		e.printStackTrace();
		}


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

	}
}

