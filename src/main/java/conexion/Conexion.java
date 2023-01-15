package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public final class Conexion {

	private static Connection instance =null;
	private static String usuario="root";
	private static String password="";
	private static String puerto = "127.0.0.1";
	private static String nombreDB="proyectoprimerjavafx";
	
	private static String JBDC_URL="jdbc:mysql://"+puerto+"/"+nombreDB+"?serverTimezone=UTC";
	
	private static String driver="com.mysql.jdbc.Driver";
	
	public static Connection getConnection(){
		try {
			Class.forName(driver);
			instance = DriverManager.getConnection(JBDC_URL,usuario,password);
			if(instance!=null) {
				System.out.println("Conecxion realizada correctamente");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Ocurrio un error en la conexion");
			System.err.println("Mensaje del error: " + e.getMessage());
			System.err.println("Detalle del error: ");
			
			e.printStackTrace();
		}
		return instance;
	}
}
