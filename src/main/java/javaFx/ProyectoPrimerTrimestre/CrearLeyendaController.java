package javaFx.ProyectoPrimerTrimestre;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import proyectoPrimerTrimestre.Dao.CampoNombre;
import proyectoPrimerTrimestre.Dao.Leyendas;
import proyectoPrimerTrimestre.Dao.Usuarios;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

import conexion.Conexion;

public class CrearLeyendaController implements Initializable{

	@FXML
    private TextField nombreLeyenda;
	
    @FXML
    private TextField errorField;
    
    @FXML
    private Label rutaArchivo;
	
    @FXML
    private Button leyenda;
    
    @FXML
    private Button salir;

    @FXML
    private Button subirimagen;

    @FXML
    private DatePicker fechaLeyenda;
    
    @FXML
    private TextArea descripcionLeyenda;
    
    @FXML
    private Button chat;

    @FXML
    private Button inicio;

    @FXML
    private Button registroLoguin;

    @FXML
    private Button crearLeyenda;
    
    @FXML
    private Button enviarLeyenda;
    
    @FXML
    private Label labelFecha;

    @FXML
    void swichToIndex(ActionEvent event)throws IOException {
    	App.setRoot("index");
    }

    @FXML
    void swichToLeyendas(ActionEvent event)throws IOException {
    	App.setRoot("leyendas");
    }

    @FXML
    void swichToCrear(ActionEvent event)throws IOException {
    	
    }

    @FXML
    void swichToRegLog(ActionEvent event)throws IOException {
    	App.setRoot("registroLoguin");
    }
    
    public void initialize(URL location, ResourceBundle resources) {
    	if(CampoNombre.getNombre()== null && CampoNombre.getApellido()== null) {
    		
    	}else {
    		registroLoguin.setVisible(false);
    		registroLoguin.setDisable(false);
		}
    }
    
    Leyendas leye;
	Connection con=null;
	PreparedStatement pstmt=null;
	
	//Enviar leyenda a la base de datos
    @FXML
    void swichtEnviarLeyenda(ActionEvent event) {

    	Object evt = event.getSource();
    	
    	if(enviarLeyenda.equals(evt)) {
    		if(!nombreLeyenda.getText().isEmpty() && !fechaLeyenda.getValue().toString().isEmpty() && !descripcionLeyenda.getText().isEmpty()) {
    	
    			leye=new Leyendas();
    			leye.setNombre(nombreLeyenda.getText());
    			leye.setImagen(rutaArchivo.getText());
    			leye.setFechas(fechaLeyenda.getValue().toString());
    			leye.setDescripcion(descripcionLeyenda.getText());
    			
    			try {
    				con=Conexion.getConnection();
					String sql = "INSERT INTO leyendas (nombre, imagen,fechas, descripcion) VALUES (?,?,?,?)";
					
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, leye.getNombre());
					pstmt.setString(2, leye.getImagen());
					pstmt.setString(3, leye.getFechas());
					pstmt.setString(4, leye.getDescripcion());
					
					pstmt.executeUpdate();
					pstmt.close();
					
					Paint value0 =Paint.valueOf("5DDF57");
					errorField.setStyle("-fx-control-inner-background: #"+value0.toString().substring(2));
					errorField.setVisible(true);
					errorField.setEditable(false);
		    		errorField.setText("Se envio correctamente");
					
		    		App.setRoot("leyendas");
		    		
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println("Ocurrio un error en el envio");
					System.err.println("Mensaje del error: " + e.getMessage());
					System.err.println("Detalle del error: ");
					e.printStackTrace();
				}
    			
    		}else {
    			Paint value0 =Paint.valueOf("EA475E");
    			errorField.setStyle("-fx-control-inner-background: #"+value0.toString().substring(2));
    			errorField.setVisible(true);
    			errorField.setEditable(false);
    			errorField.setText("Debe llenar todos los campos obligatorios");
    		}
    		
    	}else {
    		Paint value0 =Paint.valueOf("EA475E");
			errorField.setStyle("-fx-control-inner-background: #"+value0.toString().substring(2));
			errorField.setVisible(true);
			errorField.setEditable(false);
			errorField.setText("Debe llenar todos los campos obligatorios");
    	}
    	
    	
    }
    
    //Boton elegir imagen
    @FXML
    void elegirImagen(ActionEvent evt) {
    	
    	FileChooser file = new FileChooser();
    	file.setTitle("Buscar imagen");
    	
    	file.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    	
		File imgFile = file.showOpenDialog(null);
    	
    	if(file != null) {
    		try {
				String dest = System.getProperty("user.dir")+"/src/main/resources/imagenes/"+imgFile.getName();
				Path destino = Paths.get(dest);
				
				String orig = imgFile.getPath();
				Path origen = Paths.get(orig);
				
				rutaArchivo.setText(dest);;
				Files.copy(origen, destino);
				 //mostrar mensaje
			} catch (Exception e) {
				// TODO: handle exception
				System.err.println("Ocurrio un error en el envio");
				System.err.println("Mensaje del error: " + e.getMessage());
				System.err.println("Detalle del error: ");
				e.printStackTrace();
			}
    	}
     }

    //mostrar fecha en el label
    @FXML
    void mostrarLabel(ActionEvent event) {

    	LocalDate fechaDate = fechaLeyenda.getValue();
    	labelFecha.setText(fechaDate.toString());
    }
    /*private String fechadata() {
    	Date fecha1 = null;
    	String fecha2 =null;
    	
    	try {
			SimpleDateFormat sddf = new SimpleDateFormat("yyyy-MM-dd");
			fecha1 = sddf.parse(fechaLeyenda.getValue().toString());
			sddf.applyPattern("yyyy-MM-dd");
			fecha2 = sddf.format(fecha1);
			return fecha2;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	
    	
    }*/
    
    @FXML
    void swichToRegLogout(ActionEvent event)throws IOException {
    	
  		Usuarios usuarios = new Usuarios();
  		
  		CampoNombre.campoNombre(usuarios);
  		App.setRoot("index");
  
    }
    
}
