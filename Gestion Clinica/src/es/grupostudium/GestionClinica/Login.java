package es.grupostudium.GestionClinica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;

	GridBagLayout gridbag = new GridBagLayout(); 
	GridBagConstraints constraints = new GridBagConstraints(); 
	
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();

	JLabel usuario = new JLabel("Usuario:");
	JLabel contrasenya = new JLabel("Contraseña:");
	JLabel derechosAutor = new JLabel("Desarrollado por: David Borrego Asencio - Todos los derechos reservados © Version: 1.0");

	JTextField textousuario = new JTextField(15);
	JPasswordField textocontrasenya = new JPasswordField(15);

	JButton btnAceptar = new JButton("Aceptar");
	JButton btnLimpiar = new JButton(" Limpiar ");


	JDialog dialogo0 = new JDialog(this, "LOGIN — CLÍNICA SALLE", false);
	JLabel mensaje0 = new JLabel("Usuario y Contraseña Incorrectos.");
	JButton btnAceptar0= new JButton("Volver a intentar");

	ResultSet usuarios_contraseñas = null;
	
	Login()
	{
		setLayout(new BorderLayout());
		setTitle("LOGIN — CLÍNICA SALLE");
		setResizable(true);
		setSize(800, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		
		panel1.setLayout(gridbag);
		panel2.setLayout(new FlowLayout());
	
		
		//INSERTAR LA IMAGEN EN EL LAYOUT
		ImageIcon image = new ImageIcon("SIMBOLOCLINICA.png");  
		int scale = 1;  
		  
		int width = image.getIconWidth();  
		int height = image.getIconHeight();  
		BufferedImage buffer = new BufferedImage(scale * width, scale * height, BufferedImage.TYPE_INT_ARGB);  
		Graphics2D graphics = buffer.createGraphics();  
		
		graphics.scale(scale,scale);  
		image.paintIcon(null, graphics, 0, 0);  
		graphics.dispose();  
		JLabel label = new JLabel(new ImageIcon(buffer)); 
		panel2.add(label);
		add("East", panel2);
	
		// CONFIGURAR EL GRIDBAGLAYOUT
		
		constraints.insets = new Insets(0,0,0,40); //MARGEN A LA DERECHA
		constraints.anchor =  GridBagConstraints.LAST_LINE_END;
		constraints.fill =  GridBagConstraints.NONE;
		constraints.weighty=0.6;
		constraints.weightx=0.1;
		constraints.gridx = 0 ; // Establece gridx -->COLUMNA
		constraints.gridy = 0 ; // Establece gridy --> FILA
		constraints.gridwidth = 1 ; // Establece gridwidth --> ANCHURA
		constraints.gridheight = 1 ; // Establece gridheight --> ALTURA
		panel1.add(usuario, constraints);
		
		
		constraints.insets = new Insets(0,0,0,0);
		constraints.anchor =  GridBagConstraints.LAST_LINE_START;
		constraints.fill =  GridBagConstraints.NONE;
		constraints.weighty=0.6;
		constraints.weightx=0.1;
		constraints.gridx = 1 ; // Establece gridx -->COLUMNA
		constraints.gridy = 0 ; // Establece gridy --> FILA
		constraints.gridwidth = 1 ; // Establece gridwidth --> ANCHURA
		constraints.gridheight = 1 ; // Establece gridheight --> ALTURA
		panel1.add(textousuario, constraints);
		
		constraints.insets = new Insets(0,0,0,40); //MARGEN A LA DERECHA
		constraints.anchor =  GridBagConstraints.LINE_END;
		constraints.fill =  GridBagConstraints.NONE;
		constraints.weighty=0.2;
		constraints.weightx=0.1;
		constraints.gridx = 0 ; // Establece gridx -->COLUMNA
		constraints.gridy = 1 ; // Establece gridy --> FILA
		constraints.gridwidth = 1 ; // Establece gridwidth --> ANCHURA
		constraints.gridheight = 1 ; // Establece gridheight --> ALTURA
		panel1.add(contrasenya, constraints);
		
		constraints.insets = new Insets(0,0,0,0);
		constraints.anchor =  GridBagConstraints.LINE_START;
		constraints.fill =  GridBagConstraints.NONE;
		constraints.weighty=0.2;
		constraints.weightx=0.1;
		constraints.gridx = 1 ; // Establece gridx -->COLUMNA
		constraints.gridy = 1 ; // Establece gridy --> FILA
		constraints.gridwidth = 1 ; // Establece gridwidth --> ANCHURA
		constraints.gridheight = 1 ; // Establece gridheight --> ALTURA
		panel1.add(textocontrasenya, constraints);
		
		
		constraints.insets = new Insets(60,0,0,40); //MARGEN A LA DERECHA
		constraints.anchor =  GridBagConstraints.LINE_END;
		constraints.weighty=0.0;
		constraints.weightx=0.0;
		constraints.fill =  GridBagConstraints.NONE;
		constraints.gridx = 0 ; // Establece gridx -->COLUMNA
		constraints.gridy = 2 ; // Establece gridy --> FILA
		constraints.gridwidth = 1 ; // Establece gridwidth --> ANCHURA
		constraints.gridheight = 1 ; // Establece gridheight --> ALTURA
		btnAceptar.addActionListener(this);
		panel1.add(btnAceptar, constraints);
		
		
		constraints.insets = new Insets(60,0,0,0); //MARGEN A LA IZQUIERDA
		constraints.anchor =  GridBagConstraints.LINE_START;
		constraints.fill =  GridBagConstraints.NONE;
		constraints.weighty=0.0;
		constraints.weightx=0.0;
		constraints.gridx = 1 ; // Establece gridx -->COLUMNA
		constraints.gridy = 2 ; // Establece gridy --> FILA
		constraints.gridwidth = 1 ; // Establece gridwidth --> ANCHURA
		constraints.gridheight = 1 ; // Establece gridheight --> ALTURA
		btnLimpiar.addActionListener(this);
		panel1.add(btnLimpiar, constraints);
		
		constraints.insets = new Insets(0,0,0,0); 
		constraints.anchor =  GridBagConstraints.PAGE_END;
		constraints.fill =  GridBagConstraints.NONE;
		constraints.weighty=1.0;
		constraints.weightx=1.0;
		constraints.gridx = 0 ; // Establece gridx -->COLUMNA
		constraints.gridy = 3 ; // Establece gridy --> FILA
		constraints.gridwidth = 2 ; // Establece gridwidth --> ANCHURA
		constraints.gridheight = 1 ; // Establece gridheight --> ALTURA
		panel1.add(derechosAutor, constraints);
		
		add("Center", panel1);
		textousuario.requestFocus();
		
		
		addWindowListener(this);
		setVisible(true);

		dialogo0.setLayout(new FlowLayout());
		dialogo0.setSize(220,150);
		dialogo0.setResizable(false);
		dialogo0.setLocationRelativeTo(null);
		dialogo0.addWindowListener(this);
		dialogo0.add(mensaje0);
		btnAceptar0.addActionListener(this);
		dialogo0.add(btnAceptar0);
		

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

	public static void main(String[] args)
	{		
		new Login();		
	}

	public void actionPerformed(ActionEvent ae)
	{
		Object a;
		a=ae.getSource();
		char[] arrayC = textocontrasenya.getPassword(); 
		String pass = new String(arrayC); 
		boolean llave=false;
		
		String Login = "SELECT * FROM usuarios WHERE nombreUsuario='"+ textousuario.getText() + "' AND contrasenyaUsuario='" + pass +"';";
		
		if(a.equals(btnLimpiar))
		{
			llave=true;
			textousuario.selectAll();
			textousuario.setText("");
			textocontrasenya.selectAll();
			textocontrasenya.setText("");
			textousuario.requestFocus();
		}

		if(a.equals(btnAceptar))
		{
			
			if(textousuario.getText().equals("admin") && new ConectaBBDD().comprobar_Usuario(Login)==true)
			{
				new Movimientos().guardar(textousuario.getText(), "Login");
				new GestionClinica1();
				this.setVisible(false); 
				llave=true;
			}else
			{
				if(textousuario.getText().equals("user") && new ConectaBBDD().comprobar_Usuario(Login)==true)
				{
					new Movimientos().guardar(textousuario.getText(), "Login");
					new GestionClinica2();
					this.setVisible(false); 
					llave=true;
				}
			}
		}

		if(llave==false)
		{
			dialogo0.setVisible(true);
			if(a.equals(btnAceptar0))
			{
				dialogo0.setVisible(false);
			}
		}


	}


}
