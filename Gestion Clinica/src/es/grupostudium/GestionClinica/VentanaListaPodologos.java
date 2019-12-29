package es.grupostudium.GestionClinica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class VentanaListaPodologos extends JFrame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;

	DefaultTableModel modelo = new DefaultTableModel();
	JTable tabla = new JTable(modelo);
	ResultSet rs_personal = null;
	ResultSet rs_podologos = null;
	Panel panel1 = new Panel();
	JButton imprime = new JButton("Exportar a PDF");
	ResultSet rs_id_pacientes = null;
	ResultSet rs_nombres_pacientes = null;
	ResultSet rs_apellidos_pacientes = null;
	JComboBox choice = new JComboBox();
	
	VentanaListaPodologos()
	{
		setLayout(new BorderLayout());
		setTitle("LISTA DE PODÓLOGOS");
		setSize(800,600);
		setLocationRelativeTo(null);
		setResizable(true);

		//AÑADIMOS LAS COLUMNAS AL JTABLE
		modelo.addColumn("ID PERSONAL");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("APELLIDOS");
		modelo.addColumn("FECHA DE NACIMIENTO");
		modelo.addColumn("DIRECCIÓN");
		modelo.addColumn("NÚMERO COLEGIADO");
		modelo.addColumn("ESPECIALIDAD");

		//HACEMOS QUE EL TEXTO DENTRO DE LA TABLA SALGA CENTRADO
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER); //ALINEA LOS STRINGS EN EL CENTRO DE LA CELDA
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(4).setCellRenderer(tcr); 
		tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(6).setCellRenderer(tcr);

		rs_personal = (ResultSet) new ConectaBBDD().obtener_todos_los_datos_del_personal("SELECT idPersonal, nombrePersonal, apellidosPersonal, fechaNacimientoPersonal, direccionPersonal FROM personal;");	
		rs_podologos = (ResultSet) new ConectaBBDD().obtener_todos_los_datos_de_los_podologos("SELECT numeroColegiadoPodologo, especialidadPodologo FROM podologos;");	
		
		new Movimientos().recoge_sentencia("SELECT idPersonal, nombrePersonal, apellidosPersonal, fechaNacimientoPersonal, direccionPersonal FROM personal; " + "SELECT numeroColegiadoPodologo, especialidadPodologo FROM podologos;"); //ESCRIBE EN EL LOG

		Object [] encabezado = {"ID PERSONAL", "NOMBRE", "APELLIDOS", "FECHA NACIMIENTO", "DIRECCIÓN", "NÚMERO COLEGIADO", "ESPECIALIDAD"};
		modelo.addRow(encabezado); 

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
			e.printStackTrace();
		}

		try //CERRAMOS LOS RESULTSET USADOS
		{			
			rs_id_pacientes.close(); 
			rs_nombres_pacientes.close(); 
			rs_apellidos_pacientes.close();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			// BUCLE PARA EL RESULSET CON LOS DATOS DE LOS PACIENTES

			
			
			while (rs_personal.next() && rs_podologos.next())
			{
				// SE CREA UN ARRAY DE TIPO OBJETO QUE SERÁN LAS FILAS DE LA TABLA 

				Object [] fila = new Object[7];		 // HAY 7 COLUMNAS
				

				// SE RELLENA CADA POSICIÓN DEL ARRAY CON UNA DE LAS COLUMNAS DE LA TABLA EN LA BASE DE DATOS

				
				for (int i = 0; i < 7 ; i++)
				{
		
						if( i < 5)
						{
							fila[i] = rs_personal.getObject(i+1);	 // EL PRIMER INDICE EN EL RS ES EL 1, NO EL 0, POR ESO SE SUMA 1.
						}
						else
						{
							fila[i] = rs_podologos.getObject(i-4);		//SE LE RESTA 4 PARA OBTENER EL VALOR 1 Y 2 DEL RESULSET
						}		
				}
				

				modelo.addRow(fila);                  // SE AÑADE AL MODELO LA FILA COMPLETA

			}
		}catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL" + e.getMessage());
			e.printStackTrace();
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



	public void actionPerformed(ActionEvent ae)
	{
		String [] encabezado = {"ID PERSONAL", "NOMBRE", "APELLIDOS", "FECHA NACIMIENTO", "DIRECCIÓN", "NÚMERO COLEGIADO", "ESPECIALIDAD"};
		Document documento = new Document();
		
		try
		{
			rs_personal.beforeFirst();
			rs_podologos.beforeFirst();

			// Se crea el OutputStream para el fichero donde queremos dejar el pdf.
			FileOutputStream ficheroPdf = new FileOutputStream("Listado de Podólogos.pdf");

			// Se asocia el documento al OutputStream y se indica que el espaciado entre
			// lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
			PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);

			// Se abre el documento.
			documento.open();
			documento.add(new Paragraph("  LISTADO DE PODÓLOGOS   -   CLÍNICA SALLE",FontFactory.getFont("arial", // fuente
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

			PdfPTable tabla = new PdfPTable(7);

			for( int i = 0 ; i < 7; i++)
			{
				tabla.addCell(encabezado[i]);
			}


			while(rs_personal.next() && rs_podologos.next())
			{
				for (int i = 0; i < 7 ; i++)
				{
		
						if( i < 5)
						{
							tabla.addCell(rs_personal.getString(i+1));	 // EL PRIMER INDICE EN EL RS ES EL 1, NO EL 0, POR ESO SE SUMA 1.
						}
						else
						{
							tabla.addCell(rs_podologos.getString(i-4));		//SE LE RESTA 4 PARA OBTENER EL VALOR 1 Y 2 DEL RESULSET
						}		
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
