package javaFx.ProyectoPrimerTrimestre;

import java.io.Console;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.text.TabableView;

import conexion.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import proyectoPrimerTrimestre.Dao.*;
import javaFx.ProyectoPrimerTrimestre.*;

public class LeyendasController implements Initializable{

    @FXML
    private Button leyenda;

    @FXML
    private Button chat;

    @FXML
    private Button inicio;
    
    @FXML
    private Button salir;

    @FXML
    private Button registroLoguin;

    @FXML
    private Button crearLeyenda;
    
    @FXML
    private TextField buscador;
    
    @FXML
    private TableView<Leyendas> tabla;
    
    @FXML
    private Button editar;
    
    @FXML
    private Button borrar;
    
    @FXML
    private Button botonBuscar;

    //Botones menu
    @FXML
    void swichToIndex(ActionEvent event)throws IOException {
    	App.setRoot("index");
    }

    @FXML
    void swichToLeyendas(ActionEvent event)throws IOException {
    	
    }

    @FXML
    void swichToCrear(ActionEvent event)throws IOException {
    	App.setRoot("crearLeyendas");
    }

    @FXML
    void swichToRegLog(ActionEvent event)throws IOException {
    	App.setRoot("registroLoguin");
    }
    
    //Conexion y observablelist
    Connection con=Conexion.getConnection();
  	 private static ObservableList<Leyendas>listaLeyendas;
  	
  	 //Observablelist que contiene las filas que seleccionas
  	 public static ObservableList<Leyendas> getSelectedItems() {
		return listaLeyendas;
	}
  
  	 //imagenView
   	private ImageView image;
   	
   	//inicializable crea la tableview
  	public void initialize(URL location, ResourceBundle resources) {
  		
  		if(CampoNombre.getNombre()== null && CampoNombre.getApellido()== null) {
  			
  		}else {
  			registroLoguin.setVisible(false);
  			registroLoguin.setDisable(false);
		}
  		
  		if(CampoNombre.getPermiso()<1) {
  			crearLeyenda.setDisable(true);
  			editar.setDisable(true);
  			borrar.setDisable(true);
  		}else {
  			
  		}
  		
  		tabla.setEditable(true);
    	
  		TableColumn idCol = new TableColumn("ID");
    	TableColumn nombreCol = new TableColumn("Nombre");
        TableColumn fotoCol = new TableColumn("Imagen");
        TableColumn fechaCol = new TableColumn("Fecha");
        TableColumn descripcionCol = new TableColumn("Descripcion");
        
        idCol.setMaxWidth(50);idCol.setMinWidth(50);idCol.setStyle("-fx-background-color: linear-gradient(to top, #5A2F77,#AAE8E5 );-fx-alignment: CENTER");
        nombreCol.setMaxWidth(200);nombreCol.setMinWidth(200);nombreCol.setStyle( "-fx-alignment: CENTER;-fx-background-color: linear-gradient(to top, #5A2F77,#AAE8E5 )");
        fotoCol.setMaxWidth(200);fotoCol.setMinWidth(200);fotoCol.setStyle( "-fx-alignment: CENTER;-fx-background-color: linear-gradient(to top, #5A2F77,#AAE8E5 )");
        fechaCol.setMaxWidth(150);fechaCol.setMinWidth(150);fechaCol.setStyle( "-fx-alignment: CENTER;-fx-background-color: linear-gradient(to top, #5A2F77,#AAE8E5 )");
        descripcionCol.setMaxWidth(350);descripcionCol.setMinWidth(350);descripcionCol.setStyle( "-fx-alignment: CENTER;-fx-background-color: linear-gradient(to top, #5A2F77,#AAE8E5 )");
        
        listaLeyendas = FXCollections.observableArrayList();
        Leyendas.llenarInformacionLeyendas(con, listaLeyendas);
        
        tabla.getColumns().addAll(idCol);
				tabla.getColumns().addAll(nombreCol);
				tabla.getColumns().addAll(fotoCol);
				tabla.getColumns().addAll(fechaCol);
				tabla.getColumns().addAll(descripcionCol);
		
				tabla.setItems(listaLeyendas);
				
				
				idCol.setCellValueFactory(new PropertyValueFactory<Leyendas,Integer>("id_leyendas"));
				nombreCol.setCellValueFactory(new PropertyValueFactory<Leyendas,String>("nombre"));
				fotoCol.setCellValueFactory(new PropertyValueFactory<Leyendas,ImageView>("fotoImageView"));
				fechaCol.setCellValueFactory(new PropertyValueFactory<Leyendas,String>("fechas"));
				descripcionCol.setCellValueFactory(new PropertyValueFactory<Leyendas,String>("descripcion"));
				
				//buscador
				FilteredList<Leyendas> filteredData = new FilteredList<>(listaLeyendas, p -> true);
		  		tabla.setItems(filteredData);
		  		buscador.textProperty().addListener((prop, old, text) -> {
		  		    filteredData.setPredicate(leyenda -> {
		  		        if(text == null || text.isEmpty()) return true;
		  		        
		  		        String name = leyenda.getNombre().toLowerCase();  
		  		        return name.contains(text.toLowerCase());
		  		  }); 
		  		    });

		  		    SortedList<Leyendas> sortedData = new SortedList<>(filteredData);
		  		    sortedData.comparatorProperty().bind(tabla.comparatorProperty());

		  		    tabla.setItems(sortedData);
    }
  	
	 //Boton que lleva a la pagina editar 
  	@FXML
     void editarBoton(ActionEvent event) throws IOException {
  		//selectedItems=LeyendasController.getSelectedItems();
  		TableViewSelectionModel<Leyendas> selectionModel = tabla.getSelectionModel();
  		listaLeyendas = selectionModel.getSelectedItems();
  		 System.out.println(listaLeyendas);
  		App.setRoot("editarLeyenda");
     }
  	 
  	//boton para borrar
  	@FXML
    void borrarBoton(ActionEvent event) {
  		try {
		var seleccion=tabla.getSelectionModel().getSelectedItem().getId_leyendas();
  		System.out.print(seleccion);
  		int resultado = Leyendas.borrarLeyenda(seleccion);
		if (resultado == 1){
			listaLeyendas.remove(tabla.getSelectionModel().getSelectedIndex());
		}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Ocurrio un error en el envio");
			System.err.println("Mensaje del error: " + e.getMessage());
			System.err.println("Detalle del error: ");
			e.printStackTrace();
		}
  		
  	}
  	
  	@FXML
    void swichToRegLogout(ActionEvent event)throws IOException {
    	
  		Usuarios usuarios = new Usuarios();
  		
  		CampoNombre.campoNombre(usuarios);
  		App.setRoot("index");
  
    }
}
