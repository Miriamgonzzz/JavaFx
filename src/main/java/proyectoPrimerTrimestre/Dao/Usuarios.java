package proyectoPrimerTrimestre.Dao;

import java.sql.SQLException;

public class Usuarios {
	
	private int id_usuario;
	private String nombre;
	private String apellidos;
	private String usuario;
	private String correo;
	private String contrasena;
	private int permiso;
	
	public Usuarios() {
		
	}
	public Usuarios(int id_usuario, String nombre, String apellidos,String usuario, String correo, int permiso) {
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.usuario=usuario;
		this.correo = correo;
		this.permiso = permiso;
	}
	public Usuarios(String nombre,String apellidos,String usuario,String correo, String password) {
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.usuario=usuario;
		this.correo = correo;
		this.contrasena = password;
	}
	public Usuarios(String usuario,String correo, String password) {
		this.usuario=usuario;
		this.correo = correo;
		this.contrasena = password;
	}
	
	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public int getPermiso() {
		return permiso;
	}

	public void setPermiso(int permiso) {
		this.permiso = permiso;
	}
	

}
