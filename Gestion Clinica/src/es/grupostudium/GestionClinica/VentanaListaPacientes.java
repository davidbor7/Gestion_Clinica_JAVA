package es.grupostudium.GestionClinica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
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


public class VentanaListaPacientes extends JFrame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;

	JButton imprime = new JButton("Exportar a PDF");
	Panel panel1 = new Panel();
	DefaultTableModel modelo = new DefaultTableModel();
	JTable tabla = new JTable(modelo);
	ResultSet rs_lista_pacientes = null;

	VentanaListaPacientes()
	{
		setLayout(new BorderLayout());
		setTitle("LISTA DE PACIENTES");
		setSize(800,600);
		setLocationRelativeTo(null);
		setResizable(true);

		//AÑADIMOS LAS COLUMNAS AL JTABLE
		modelo.addColumn("ID");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellidos");
		modelo.addColumn("Fecha de Nacimiento");
		modelo.addColumn("Dirección");
		modelo.addColumn("DNI");

		//HACEMOS QUE EL TEXTO DENTRO DE LA TABLA SALGA CENTRADO
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER); //ALINEA LOS STRINGS EN EL CENTRO DE LA CELDA
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(4).setCellRenderer(tcr); 
		tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);

		rs_lista_pacientes = (ResultSet) new ConectaBBDD().obtener_todos_los_datos_de_los_pacientes("SELECT idPaciente, nombrePaciente, apellidosPaciente, fechaNacimientoPaciente, direccionPaciente, dniPaciente FROM pacientes;");	

		new Movimientos().recoge_sentencia("SELECT idPaciente, nombrePaciente, apellidosPaciente, fechaNacimientoPaciente, direccionPaciente, dniPaciente FROM pacientes;"); //ESCRIBE EN EL LOG

		Object [] encabezado = {"ID", "NOMBRE", "APELLIDOS", "FECHA NACIMIENTO", "DIRECCIÓN", "DNI"};
		modelo.addRow(encabezado); 

		try
		{
			// BUCLE PARA EL RESULSET CON LOS DATOS DE LOS PACIENTES

			while (rs_lista_pacientes.next())
			{
				// SE CREA UN ARRAY DE TIPO OBJETO QUE SERÁN LAS FILAS DE LA TABLA 

				Object [] fila = new Object[6];		 // HAY 6 COLUMNAS

				// SE RELLENA CADA POSICIÓN DEL ARRAY CON UNA DE LAS COLUMNAS DE LA TABLA EN LA BASE DE DATOS

				for (int i=0;i<6;i++)
				{

					fila[i] = rs_lista_pacientes.getObject(i+1);		 // EL PRIMER INDICE EN EL RS ES EL 1, NO EL 0, POR ESO SE SUMA 1.

				}

				modelo.addRow(fila);                               // SE AÑADE AL MODELO LA FILA COMPLETA

			}
		}catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL" + e.getMessage());
		}


		imprime.addActionListener(this);
		panel1.setLayout(new FlowLayout());
		panel1.add(imprime);
		add(tabla, "North");
		add(panel1, "Center");

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

	public void actionPerformed(ActionEvent ae){


		Document documento = new Document();
		String [] encabezado = {"ID", "NOMBRE", "APELLIDOS", "FECHA NACIMIENTO", "DIRECCIÓN", "DNI"};

		try
		{
			rs_lista_pacientes.beforeFirst();


			// Se crea el OutputStream para el fichero donde queremos dejar el pdf.
			FileOutputStream ficheroPdf = new FileOutputStream("Listado de Pacientes.pdf");

			// Se asocia el documento al OutputStream y se indica que el espaciado entre
			// lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
			PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);

			// Se abre el documento.
			documento.open();
			documento.add(new Paragraph("  LISTADO DE PACIENTES   -   CLÍNICA SALLE",FontFactory.getFont("arial", // fuente
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

			PdfPTable tabla = new PdfPTable(6);

			for( int i = 0 ; i < 6; i++)
			{
				tabla.addCell(encabezado[i]);
			}


			while(rs_lista_pacientes.next())
			{
				for (int i = 0; i < 6; i++)
				{
					tabla.addCell(rs_lista_pacientes.getString(i+1));
				}
				;
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
