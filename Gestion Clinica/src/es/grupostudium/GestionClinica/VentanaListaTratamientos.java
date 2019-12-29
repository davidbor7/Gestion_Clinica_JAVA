package es.grupostudium.GestionClinica;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class VentanaListaTratamientos extends JFrame implements WindowListener, ActionListener, ItemListener
{

	private static final long serialVersionUID = 1L;

	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JButton imprime = new JButton("Exportar a PDF");

	DefaultTableModel modelo = new DefaultTableModel();
	Choice choice = new Choice();

	String str1 = "Elegir Paciente...";
	JTable tabla = new JTable(modelo);

	ResultSet rs_id_pacientes = null;
	ResultSet rs_nombres_pacientes = null;
	ResultSet rs_apellidos_pacientes = null;

	ResultSet rs_datos_tratamientos = null;

	Object [] encabezado = {"ID DEL PERSONAL QUE ATIENDE", "IMPORTE", "FECHA"};

	public VentanaListaTratamientos()
	{
		setLayout(new BorderLayout());       
		setSize(800,600);
		setTitle("LISTA DE TRATAMIENTOS");
		setResizable(true);


		//AÑADIMOS LAS COLUMNAS AL JTABLE
		modelo.addColumn("ID DEL PERSONAL QUE ATIENDE");
		modelo.addColumn("IMPORTE");
		modelo.addColumn("FECHA");

		//HACEMOS QUE EL TEXTO DENTRO DE LA TABLA SALGA CENTRADO
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER); //ALINEA LOS STRINGS EN EL CENTRO DE LA CELDA
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);


		choice.addItemListener(this);
		choice.addItem(str1);


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
			System.out.println("Error al rellenar el JComboBox con los datos de las consultas.");
		}

		try //CERRAMOS LOS RESULTSET USADOS
		{			
			rs_id_pacientes.close(); 
			rs_nombres_pacientes.close(); 
			rs_apellidos_pacientes.close();

		} catch (SQLException e)
		{
			System.out.println("Error al cerrar los ResultSet de las consultas.");
		}



		panel1.setLayout( new FlowLayout());


		panel1.add(choice);

		panel2.setLayout( new BorderLayout());
		panel2.add(tabla, "Center");

		imprime.addActionListener(this);
		panel3.setLayout(new FlowLayout());
		panel3.add(imprime);
		add(panel1,"North");
		add(panel2,"Center");
		add(panel3, "South");

		modelo.addRow(encabezado); //AÑADE ENCABEZADO DESPUÉS DE LIMPIAR




		addWindowListener(this);
		setLocationRelativeTo(null);
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

		if(ae.getSource().equals(imprime))
		{
			String [] encabezado = {"ID PERSONAL QUE ATIENDE", "IMPORTE", "FECHA NACIMIENTO"};
			Document documento = new Document();

			try
			{
				rs_datos_tratamientos.beforeFirst();

				String nombre_completo_paciente = (String) choice.getSelectedItem(); //RECOGE LO QUE SELECCIONA EN EL JCOMBOBOX Y LO CONVIERTE EN STRING

				String[] seleciona_nombre = nombre_completo_paciente.split("-");
				
				seleciona_nombre[1].toUpperCase();
		
				// Se crea el OutputStream para el fichero donde queremos dejar el pdf.
				FileOutputStream ficheroPdf = new FileOutputStream("Listado de Tratamientos.pdf");

				// Se asocia el documento al OutputStream y se indica que el espaciado entre
				// lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
				PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);

				// Se abre el documento.
				documento.open();
				documento.add(new Paragraph("  LISTADO DE TRATAMIENTOS - "+seleciona_nombre[1].toUpperCase() +" - CLÍNICA SALLE",FontFactory.getFont("arial", // fuente
						22, // tamaño
						Font.ITALIC, // estilo
						BaseColor.BLACK)));// color
				documento.add(new Paragraph("  "));

				/*
			 Image foto = Image.getInstance("SIMBOLOCLINICA.png");
			foto.scaleToFit(100, 100);
			foto.setAlignment(Chunk.ALIGN_MIDDLE);
			documento.add(foto);
			PdfPTable tabla = new PdfPTable(3);
				 */

				PdfPTable tabla = new PdfPTable(3);

				for( int i = 0 ; i < 3; i++)
				{
					tabla.addCell(encabezado[i]);
				}


				while(rs_datos_tratamientos.next())
				{
					for (int i = 0; i < 3 ; i++)
					{

						tabla.addCell(rs_datos_tratamientos.getString(i+1));				
					}

				}

				documento.add(tabla);
				documento.close();
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
		}
	}

	public void itemStateChanged(ItemEvent ie)
	{

		String nombre_completo_paciente = (String) choice.getSelectedItem(); //RECOGE LO QUE SELECCIONA EN EL JCOMBOBOX Y LO CONVIERTE EN STRING

		String[] seleciona_id = nombre_completo_paciente.split("-");  //METODO PARA SEPARAR EL ID DEL RESTO DEL NOMBRE


		if(!nombre_completo_paciente.equals("Elegir Paciente..."))
		{

			try
			{
				
				//LIMPIAR EL JTABLE
				for (int i = 0; i < tabla.getRowCount(); i++) 
				{
					modelo.removeRow(i);
					i -= 1;
				}
				modelo.addRow(encabezado); //AÑADE ENCABEZADO DESPUÉS DE LIMPIAR

				rs_datos_tratamientos = (ResultSet) new ConectaBBDD().obtener_todos_los_datos_de_los_pacientes("SELECT idPersonalFK, importeTratamiento, fechaTratamiento FROM tratamientopersonal WHERE idPacienteFK = '"+ seleciona_id[0] + "';");	//SENTENCIA QUE RECOGE LOS DATOS DE LOS TRATAMIENTOS

				new Movimientos().recoge_sentencia("SELECT idPersonalFK, importeTratamiento, fechaTratamiento FROM tratamientopersonal WHERE idPacienteFK = '"+ seleciona_id[0] + "';"); //ESCRIBE EN EL LOG

				// BUCLE PARA EL RESULSET CON LOS DATOS DEL PACIENTE

				while (rs_datos_tratamientos.next())
				{

					Object [] fila = new Object[3];		


					for (int i = 0; i < 3; i++)
					{

						fila[i] = rs_datos_tratamientos.getObject(i+1);  //SE SUMA 1 PORQUE EL RESULTSET NO EMPIEZA EN 0
					}


					modelo.addRow(fila);                               // SE AÑADE AL MODELO LA FILA COMPLETA

				}

			}
			catch(SQLException e)
			{
				System.out.println("Error en la sentencia SQL" + e.getMessage());
			}

		}else
		{
			//LIMPIAR EL JTABLE
			for (int i = 0; i < tabla.getRowCount(); i++) 
			{
				modelo.removeRow(i);
				i -= 1;
			}
			modelo.addRow(encabezado); //AÑADE ENCABEZADO DESPUÉS DE LIMPIAR
		}
	}
}

