package proyectoPrimerTrimestre.Dao;

public class CampoNombre {

	private static String nombre;
	private static String apellido;
	private static int permiso;
	
	public CampoNombre(String nombre,String apellido,int permiso) {
		CampoNombre.nombre=nombre;
		CampoNombre.apellido=apellido;
		CampoNombre.permiso=permiso;
	}
	
	
	public static String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		CampoNombre.nombre = nombre;
	}
	
	

	public static String getApellido() {
		return apellido;
	}



	public static void setApellido(String apellido) {
		CampoNombre.apellido = apellido;
	}
	
	

	public static int getPermiso() {
		return permiso;
	}



	public static void setPermiso(int permiso) {
		CampoNombre.permiso = permiso;
	}



	public static String campoNombre(Usuarios usuario) {
		
		usuario=new Usuarios(usuario.getId_usuario(),usuario.getNombre(),usuario.getApellidos(),usuario.getCorreo(),usuario.getUsuario(),usuario.getPermiso());
		
		nombre = usuario.getNombre();
		apellido = usuario.getApellidos();
		permiso=usuario.getPermiso();
		
		return nombre + apellido + permiso;
	}
	
}
