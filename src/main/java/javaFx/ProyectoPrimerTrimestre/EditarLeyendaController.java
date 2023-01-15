package javaFx.ProyectoPrimerTrimestre;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.ObservableList;
import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import proyectoPrimerTrimestre.Dao.CampoNombre;
import proyectoPrimerTrimestre.Dao.Leyendas;
import proyectoPrimerTrimestre.Dao.Usuarios;

public class EditarLeyendaController implements Initializable{
	@FXML
    private Button editarLeyenda1;

    @FXML
    private TextField nombreEditarLeyenda;

    @FXML
    private Button subirEditarimagen;

    @FXML
    private Label rutaEditarArchivo;
    
    @FXML
    private Button salir;

    @FXML
    private Button registroLoguin;

    @FXML
    private Button crearLeyenda1;

    @FXML
    private Button inicio;

    @FXML
    private DatePicker fechaEditarLeyenda;

    @FXML
    private TextField errorField;
    
    @FXML
    private TextField idEditarLeyenda;

    @FXML
    private Button enviarEditadoLeyenda;

    @FXML
    private Button leyenda;

    @FXML
    private TextArea descripcionEditarLeyenda;

    @FXML
    private Label labelEditarFecha;

    //Botones menu
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
    	App.setRoot("crearLeyendas");
    	
    }

    @FXML
    void swichToRegLog(ActionEvent event)throws IOException {
    	App.setRoot("registroLoguin");
    	
    }
    
    //ObservableList que trae parametros de la lista 
    static ObservableList<Leyendas> listaLeyendas;
    
    //Inicializable
     @FXML
    public void initialize(URL location, ResourceBundle resources) {
    	 
    	 /*if(CampoNombre.getNombre()!= "null" && CampoNombre.getApellido()!= "null") {
   			registroLoguin.setVisible(false);
   			registroLoguin.setDisable(false);
   		}else {
   			 registroLoguin.setVisible(true);
   			 registroLoguin.setDisable(true);
 		}*/
   		
    	Leyendas leyendas;
    	listaLeyendas=LeyendasController.getSelectedItems();
			System.out.println(listaLeyendas.toString());
			if(listaLeyendas.size()!=0) {
				leyendas =listaLeyendas.get(0);
				mostrarParametros(leyendas);
			}
    }
    
     //Muestra parametros en los TextField
    void mostrarParametros(Leyendas leyenda){
 
    	idEditarLeyenda.setText(Integer.toString(leyenda.getId_leyendas()) );
    	nombreEditarLeyenda.setText(leyenda.getNombre());
    	rutaEditarArchivo.setText(leyenda.getImagen());
    	labelEditarFecha.setText(leyenda.getFechas());
    	fechaEditarLeyenda.setValue(mostrarDatePicker(leyenda));;
    	descripcionEditarLeyenda.setText(leyenda.getDescripcion());
    }
    
  //Boton para enviar la edicion de la leyenda a sql
    @FXML
    void swichtEnviarEditadoLeyenda(ActionEvent event) throws IOException {
    	
    	Leyendas leye = new Leyendas();
    	leye.setId_leyendas(Integer.parseInt(idEditarLeyenda.getText()));
    	leye.setNombre(nombreEditarLeyenda.getText());
		leye.setImagen(rutaEditarArchivo.getText());
		leye.setFechas(fechaEditarLeyenda.getValue().toString());
		leye.setDescripcion(descripcionEditarLeyenda.getText());

    	Leyendas.actualizarRegistro(leye);
    	App.setRoot("Leyendas");
    	
    	
    }
    
    //Cambiar label de fecha a localDate
    private LocalDate  mostrarDatePicker(Leyendas leyenda) {
  	  
  	  LocalDate fecha = LocalDate.parse(leyenda.getFechas());
  	
  	  return fecha ;
     }
    
    //seleccion imagen en editar
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
				
				rutaEditarArchivo.setText(dest);;
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
    
    //Mostrar fecha en el label editar
    @FXML
    void mostrarLabel(ActionEvent event) {

    	LocalDate fechaDate = fechaEditarLeyenda.getValue();
    	labelEditarFecha.setText(fechaDate.toString());
    }
    
   /* private void borrarCampos() {
    	idEditarLeyenda.setText("");
    	nombreEditarLeyenda.setText("");
    	rutaEditarArchivo.setText("");
    	labelEditarFecha.setText("");
    	descripcionEditarLeyenda.setText("");
    	
    }*/
    
    @FXML
    void swichToRegLogout(ActionEvent event)throws IOException {
    	
  		Usuarios usuarios = new Usuarios();
  		
  		CampoNombre.campoNombre(usuarios);
  		App.setRoot("index");
  
    }
}
