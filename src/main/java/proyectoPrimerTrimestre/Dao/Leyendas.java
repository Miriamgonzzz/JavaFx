package proyectoPrimerTrimestre.Dao;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.Conexion;
import javaFx.ProyectoPrimerTrimestre.LeyendasController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Leyendas {

	private int id_leyendas;
	private String nombre;
	private String imagen;
	private String fechas;
	private String descripcion;
	private ImageView fotoImageView;
	
	
	public Leyendas() {
		
	}
	public Leyendas(int id_leyendas) {
		this.id_leyendas=id_leyendas;
	}
	public Leyendas(int id_leyendas,String nombre,String imagen,String fechas, String descripcion) {
		this.id_leyendas=id_leyendas;
		this.nombre=nombre;
		this.imagen=imagen;
		this.fechas=fechas;
		this.descripcion=descripcion;
		
	}
	public Leyendas(String nombre, String imagen, String fechas, String descripcion) {
		this.nombre = nombre;
		this.imagen = imagen;
		this.fechas = fechas;
		this.descripcion = descripcion;
	
	}
	
	public Leyendas(int id_leyendas,String nombre,String imagen, ImageView foto, String fechas, String descripcion) {
		this.id_leyendas=id_leyendas;
		this.nombre = nombre;
		this.imagen = imagen;
		this.fotoImageView = foto;
		this.fechas = fechas;
		this.descripcion = descripcion;
	
	}
	
	public int getId_leyendas() {
		return id_leyendas;
	}
	public void setId_leyendas(int id_leyendas) {
		this.id_leyendas = id_leyendas;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getFechas() {
		return fechas;
	}
	public void setFechas(String fechas) {
		this.fechas = fechas;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public ImageView getFotoImageView() {
		return fotoImageView;
	}
	public void setFotoImageView(ImageView fotoImageView) {
		this.fotoImageView = fotoImageView;
	}
	
	//codigo sql recoger datos de leyendas de la base de datos
	public static void llenarInformacionLeyendas(Connection connection,ObservableList<Leyendas> listaLeyendas) {
		try {
			/*Statement instruccion = connection.createStatement();
			ResultSet rs = instruccion.executeQuery(
					"SELECT * from leyendas where id_leyendas=?, nombre = ?, imagen=?, fechas=? and descripcion= ?" 
			);*/
			Connection con=Conexion.getConnection();
			String sql = "SELECT * from leyendas ";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) { 
				
				FileInputStream input = new FileInputStream(rs.getString("imagen"));
					Image image = new Image(input);
					ImageView imageView = new ImageView(image);
					imageView.setFitWidth(150);
					imageView.setFitHeight(150);
					
				listaLeyendas.add(
				
						new Leyendas(rs.getInt("id_leyendas"),rs.getString("nombre"),rs.getString("imagen"),imageView,
						
						rs.getString("fechas"),rs.getString("descripcion")));
				
			}	
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Ocurrio un error en el envio");
			System.err.println("Mensaje del error: " + e.getMessage());
			System.err.println("Detalle del error: ");
			e.printStackTrace();
		}
	}
	
	//codigo sql de borrar
	public static int borrarLeyenda(int consol) {
		
		try {
			Connection con=Conexion.getConnection();
  			String sql = "DELETE FROM leyendas WHERE id_leyendas=?";
			
			 PreparedStatement pstmt=con.prepareStatement(sql);
			 pstmt.setInt(1,consol);
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("Ocurrio un error en el envio");
			System.err.println("Mensaje del error: " + e.getMessage());
			System.err.println("Detalle del error: ");
			e.printStackTrace();
			return 0;
		}
		
	}
	
	//codigo sql de editar
	public static int actualizarRegistro(Leyendas leye){
		try {
			Connection con=Conexion.getConnection();
			String sql ="UPDATE leyendas SET nombre = ?,imagen = ?,fechas = ?,descripcion = ? WHERE id_leyendas=? ";
			PreparedStatement pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, leye.getNombre());
			pstmt.setString(2, leye.getImagen());
			pstmt.setString(3, leye.getFechas());
			pstmt.setString(4, leye.getDescripcion());
			pstmt.setInt(5, leye.getId_leyendas());
			System.out.println(pstmt);
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
