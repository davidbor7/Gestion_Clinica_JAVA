package es.grupostudium.GestionClinica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConectaBBDD 
{
	//BASE DE DATOS

	String driver = "com.mysql.jdbc.Driver";
	String url ="jdbc:mysql://localhost:3306/policlinica?autoReconnect=true&useSSL=false";  //DRIVER-->PROTOCOLO DEL DRIVER-->DETALLES DE LA CONEXIÓN
	String login = "administrativo";
	String password = "studium2019;";
	String sentencia = null;
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	public ConectaBBDD()
	{
		//BASE DE DATOS

		//1.CARGAR EL DRIVER
		try
		{
			Class.forName(driver);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Se ha producido un error al cargar el Driver");
		}

		// 2. ESTABLECER LA CONEXIÓN CON LA BASE DE DATOS
		try
		{
			connection = DriverManager.getConnection(url, login, password);
			
		}
		catch(SQLException e)
		{
			System.out.println("Se produjo un error al conectar a la Base de Datos");
		}
	}

	
	
	
	public boolean agregar_paciente(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			statement.executeUpdate(sentencia);
			statement.close();
			connection.close();
			return true;
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return false;
		}
	}
	
	public boolean agregar_personal(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			statement.executeUpdate(sentencia);
			statement.close();
			connection.close();
			return true;
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return false;
		}
	}
	
	public boolean agregar_podologo(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			statement.executeUpdate(sentencia);
			statement.close();
			connection.close();
			return true;
		}
		catch(SQLException e)
		{
			System.out.println("Error AQUÍ");
			return false;
		}
	}
	
	public Object obtener_id_de_los_pacientes(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			rs= statement.executeQuery(sentencia);
			return rs;
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return rs;
		}
	}
	
	public Object obtener_nombres_de_los_pacientes(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			rs= statement.executeQuery(sentencia);
			return rs;
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return rs;
		}
	}
	public Object obtener_apellidos_de_los_pacientes(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			rs= statement.executeQuery(sentencia);
			return rs;
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return rs;
		}
	}
	
	public Object obtener_id_del_personal(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			rs= statement.executeQuery(sentencia);
			return rs;
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return rs;
		}
	}
	
	public Object obtener_id_de_los_podologos(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			rs= statement.executeQuery(sentencia);
			return rs;
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return rs;
		}
	}
	
	public Object obtener_nombres_del_personal(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			rs= statement.executeQuery(sentencia);
			return rs;
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return rs;
		}
	}
	public Object obtener_apellidos_del_personal(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			rs= statement.executeQuery(sentencia);
			return rs;
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return rs;
		}
	}
	
	public Object nombre_personal_que_atiende(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			rs= statement.executeQuery(sentencia);
			return rs;
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return rs;
		}
	}
	
	public Object obtener_todos_los_datos_de_los_pacientes(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			rs= statement.executeQuery(sentencia);
			return rs;
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return rs;
		}
	}
	
	public Object obtener_tratamientos(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			rs= statement.executeQuery(sentencia);
			return rs;
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return rs;
		}
	}
	
	public Object obtener_todos_los_datos_del_personal(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			rs= statement.executeQuery(sentencia);
			return rs;
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return rs;
		}
	}
	
	public Object obtener_todos_los_datos_de_los_podologos(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			rs= statement.executeQuery(sentencia);
			return rs;
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return rs;
		}
	}
	
	public void eliminar_paciente(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			statement.executeUpdate(sentencia);
			statement.close();
			connection.close();
	
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			
		}
	}
	public boolean eliminar_podologo(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			statement.executeUpdate(sentencia);
			statement.close();
			connection.close();
			return true;
	
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return false;
		}
	}
	
	public boolean eliminar_personal(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			statement.executeUpdate(sentencia);
			statement.close();
			connection.close();
			return true;
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return false;
		}
	}
	public void eliminar_tratamiento(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			statement.executeUpdate(sentencia);
			statement.close();
			connection.close();
	
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			
		}
	}
	
	public boolean modificar_paciente(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			statement.executeUpdate(sentencia);
			statement.close();
			connection.close();
			return true;
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return false;
		}
	}
	
	public boolean modificar_podologo(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			statement.executeUpdate(sentencia);
			statement.close();
			connection.close();
			return true;
	
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return false;
		}
	}
	
	public boolean modificar_personal(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			statement.executeUpdate(sentencia);
			statement.close();
			connection.close();
			return true;
	
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return false;
		}
	}
	
	public boolean agregar_tratamiento(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			statement.executeUpdate(sentencia);
			statement.close();
			connection.close();
			return true;
	
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL agregar tratamiento.");
			return false;
		}
	}
	
	public boolean comprobar_Usuario(String sentencia)
	{
		// 3. CREAR UN OBJETO DE TIPO STATEMENT 
		// 4. EJECUTAR SENTENCIA SQL
		// 5. POR ÚLTIMO, CERRAMOS LOS OBJETOS PARA LIBERAR MEMORIA.
		try
		{
			statement=connection.createStatement(); //UTILIZAMOS EL MÉTODO CREATESTATEMENT() DEL OBJETO CONNECTION QUE NOS DEVUELVE UN OBJETO DE TIPO STATEMENT
			rs= statement.executeQuery(sentencia);
			
			if (rs.next())
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
			return false;
		}
	}
}