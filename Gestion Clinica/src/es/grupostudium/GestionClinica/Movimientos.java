package es.grupostudium.GestionClinica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Movimientos
{

	public Movimientos()
	{
	}
	public void guardar(String usuario, String accion)
	{
		//FileWriter también puede lanzar una excepción
		try
		{
			Calendar calendario = new GregorianCalendar();
			// Destino de los datos
			FileWriter fw = new FileWriter("Archivo.log", true);
			// Buffer de escritura
			BufferedWriter bw = new BufferedWriter(fw);
			// Objeto para la escritura
			PrintWriter salida = new PrintWriter(bw);
			//Añadimos la segunda frase y un double

			if(calendario.get(Calendar.DAY_OF_MONTH) < 10 && calendario.get(Calendar.MONTH) < 10)
			{
				salida.print("[" + calendario.get(Calendar.HOUR_OF_DAY) +":"+ calendario.get(Calendar.MINUTE) + ":"+ calendario.get(Calendar.SECOND) + "]" );
				salida.print("["+"0"+calendario.get(Calendar.DAY_OF_MONTH)+"/"+"0"+((calendario.get(Calendar.MONTH))+1)+"/"+calendario.get(Calendar.YEAR)+"]");
				salida.print("["+usuario+"]");
				salida.print("["+accion+"]");
			}else
			{
				if(calendario.get(Calendar.DAY_OF_MONTH) < 10)
				{
					salida.print("[" + calendario.get(Calendar.HOUR_OF_DAY) +":"+ calendario.get(Calendar.MINUTE) + ":"+ calendario.get(Calendar.SECOND) + "]" );
					salida.print("["+"0"+calendario.get(Calendar.DAY_OF_MONTH)+"/"+((calendario.get(Calendar.MONTH))+1)+"/"+calendario.get(Calendar.YEAR)+"]");
					salida.print("["+usuario+"]");
					salida.print("["+accion+"]");
				}else
				{
					if(calendario.get(Calendar.MONTH) < 10)
					{
						salida.print("[" + calendario.get(Calendar.HOUR_OF_DAY) +":"+ calendario.get(Calendar.MINUTE) + ":"+ calendario.get(Calendar.SECOND) + "]" );
						salida.print("["+calendario.get(Calendar.DAY_OF_MONTH)+"/"+"0"+((calendario.get(Calendar.MONTH))+1)+"/"+calendario.get(Calendar.YEAR)+"]");
						salida.print("["+usuario+"]");
						salida.print("["+accion+"]");
					}
				}
			}


			salida.println();
			salida.close();
			bw.close();
			fw.close();
			System.out.println("Archivo creado correctamente!");
		}
		catch(IOException i)
		{
			System.out.println("Se produjo un error de Archivo");
		}
	}

	public String recoge_fecha_actual()
	{
		
		String fecha = new String();

		Calendar calendario = new GregorianCalendar();

		if(calendario.get(Calendar.DAY_OF_MONTH) < 10 && calendario.get(Calendar.MONTH) < 10)
		{
			fecha = (calendario.get(Calendar.YEAR)+ "/" + "0"+((calendario.get(Calendar.MONTH))+1) + "/" + "0"+ calendario.get(Calendar.DAY_OF_MONTH));

		}else
		{
			if(calendario.get(Calendar.DAY_OF_MONTH) < 10)
			{
				fecha = (calendario.get(Calendar.YEAR)+ "/" + ((calendario.get(Calendar.MONTH))+1) + "/" + "0"+ calendario.get(Calendar.DAY_OF_MONTH));
				
			}else
			{
				if(calendario.get(Calendar.MONTH) < 10)
				{
					fecha = (calendario.get(Calendar.YEAR)+ "/" + "0" + ((calendario.get(Calendar.MONTH))+1) + "/" + calendario.get(Calendar.DAY_OF_MONTH));

				}
			}
		}

		return fecha;
	}
	
	public void recoge_sentencia(String sentencia)
	{
		try
		{
			Calendar calendario = new GregorianCalendar();
			// Destino de los datos
			FileWriter fw = new FileWriter("Archivo.log", true);
			// Buffer de escritura
			BufferedWriter bw = new BufferedWriter(fw);
			// Objeto para la escritura
			PrintWriter salida = new PrintWriter(bw);
			//Añadimos la segunda frase y un double

			if(calendario.get(Calendar.DAY_OF_MONTH) < 10 && calendario.get(Calendar.MONTH) < 10)
			{
				salida.print("[" + calendario.get(Calendar.HOUR_OF_DAY) +":"+ calendario.get(Calendar.MINUTE) + ":"+ calendario.get(Calendar.SECOND) + "]" );
				salida.print("["+"0"+calendario.get(Calendar.DAY_OF_MONTH)+"/"+"0"+((calendario.get(Calendar.MONTH))+1)+"/"+calendario.get(Calendar.YEAR)+"]");
				salida.print("["+ sentencia +"]");
			}else
			{
				if(calendario.get(Calendar.DAY_OF_MONTH) < 10)
				{
					salida.print("[" + calendario.get(Calendar.HOUR_OF_DAY) +":"+ calendario.get(Calendar.MINUTE) + ":"+ calendario.get(Calendar.SECOND) + "]" );
					salida.print("["+"0"+calendario.get(Calendar.DAY_OF_MONTH)+"/"+((calendario.get(Calendar.MONTH))+1)+"/"+calendario.get(Calendar.YEAR)+"]");
					salida.print("["+ sentencia +"]");
				}else
				{
					if(calendario.get(Calendar.MONTH) < 10)
					{
						salida.print("[" + calendario.get(Calendar.HOUR_OF_DAY) +":"+ calendario.get(Calendar.MINUTE) + ":"+ calendario.get(Calendar.SECOND) + "]" );
						salida.print("["+calendario.get(Calendar.DAY_OF_MONTH)+"/"+"0"+((calendario.get(Calendar.MONTH))+1)+"/"+calendario.get(Calendar.YEAR)+"]");
						salida.print("["+ sentencia +"]");
					}
				}
			}
			
			salida.println();
			salida.close();
			bw.close();
			fw.close();
			
		}
		catch(IOException i)
		{
			System.out.println("Se produjo un error de Archivo Log");
		}

	}
}
