package javaFx.ProyectoPrimerTrimestre;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import proyectoPrimerTrimestre.Dao.CampoNombre;
import proyectoPrimerTrimestre.Dao.Usuarios;

public class IndexController implements Initializable{

	    @FXML
	    private Button leyenda;

	    @FXML
	    private Button chat;

	    @FXML
	    private Button inicio;

	    @FXML
	    private Button registroLoguin;
	    
	    @FXML
	    private Button salir;

	    @FXML
	    private Button crearLeyenda;
	    
	    @FXML
	    private Text nombreBienvenida;
	    
	    @FXML
	    private Text iniciaSesion;

	    //Botones menu
	    @FXML
	    void swichToIndex(ActionEvent event)throws IOException {
	    	
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
	    
	  	public void initialize(URL location, ResourceBundle resources) {
	  		
	  		nombreBienvenida.setText("Bienvenido "+ CampoNombre.getNombre()+ " " + CampoNombre.getApellido());
	  		iniciaSesion.setText("Inicia sesion si quieres ver leyendas");

	  		if(CampoNombre.getNombre()== null && CampoNombre.getApellido()== null) {
	  			leyenda.setDisable(true);
	  			crearLeyenda.setDisable(true);
	  			iniciaSesion.setVisible(true);
	  			nombreBienvenida.setVisible(false);
	  		}else {
	  			nombreBienvenida.setVisible(true);
	  			iniciaSesion.setVisible(false);
	  			registroLoguin.setVisible(false);
	  			registroLoguin.setDisable(false);
	  			
	  		}
	  		
	  		if(CampoNombre.getPermiso()<1) {
	  			crearLeyenda.setDisable(true);
	  		}else {
	  			
	  		}
	  	}
	  	
	  	@FXML
	    void swichToRegLogout(ActionEvent event)throws IOException {
	    	
	  		Usuarios usuarios = new Usuarios();
	  		
	  		CampoNombre.campoNombre(usuarios);
	  		App.setRoot("index");
	  
	    }
}
