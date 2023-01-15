package javaFx.ProyectoPrimerTrimestre;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.Delayed;

import Encryptar.ControllerCorreo;
import Encryptar.Encryptor;
import conexion.Conexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import proyectoPrimerTrimestre.Dao.CampoNombre;
import proyectoPrimerTrimestre.Dao.Usuarios;

public class RegistroLoginController implements Initializable{

	@FXML
    private TextField apellidos;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField errorField1;

    @FXML
    private Button inicio;

    @FXML
    private TextField nombre;

    @FXML
    private TextField confPasswordTextField;

    @FXML
    private Button envio;

    @FXML
    private CheckBox ckecContrasena;

    @FXML
    private TextField errorField;

    @FXML
    private PasswordField configContrasena;

    @FXML
    private Button leyenda;

    @FXML
    private Button chat;

    @FXML
    private CheckBox checkConf;

    @FXML
    private TextField correo;

    @FXML
    private Button registroLoguin;

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField contrasena;

    @FXML
    private Button crearLeyenda;

    @FXML
    private Button confUsuario;
    
    @FXML
    private TextField usarioCorreo;
    
    @FXML
    private TextField contrasenaLoginText;
    
    @FXML
    private PasswordField contrasenaLogin;
    
    @FXML
    private CheckBox checkContrasenaLogin;

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
    	
    }
    
    public void initialize(URL location, ResourceBundle resources) {

  		if(CampoNombre.getNombre()== null && CampoNombre.getApellido()== null) {
  			leyenda.setDisable(true);
  			crearLeyenda.setDisable(true);
  		}else {
  
  			
  		}
  		
  		if(CampoNombre.getPermiso()<1) {
  			crearLeyenda.setDisable(true);
  		}else {
  			
  		}
  		
  	}
    
    //Nuevo usuario conexion y preparedStatement
    Usuarios usu;
	Connection con=null;
	PreparedStatement pstmt=null;
	
	Encryptor encryptor=new Encryptor();
	
	//Registrarse y envion a base de datos
    @FXML
    void EnviarMySql(ActionEvent event) {
    	
    	Object evt = event.getSource();
		
    	if(envio.equals(evt)) {
    		if(!nombre.getText().isEmpty() && !apellidos.getText().isEmpty() && 
    	               !correo.getText().isEmpty() && !usuario.getText().isEmpty()&& !contrasena.getText().isEmpty()&& 
    	               !configContrasena.getText().isEmpty()) {
    			
    			if(!nombre.getText().equals("null") && !apellidos.getText().equals("null") && 
     	               !correo.getText().equals("null") && !usuario.getText().equals("null")&& !contrasena.getText().equals("null")) {	
    			
    			if(ControllerCorreo.validateEmail(correo.getText())) {
    				
    				if(usuario.getText().length()>=6 && usuario.getText().length()<=20){
    					
    					if(nombre.getText().length()<=40){
    						
    						if(apellidos.getText().length()>=6 && apellidos.getText().length()<=40){
    							
    							if(contrasena.getText().length()>=6 && contrasena.getText().length()<=40){
    					
    					if(configContrasena.getText().equals(contrasena.getText())) {
    						
    					usu=new Usuarios();
    					usu.setNombre(nombre.getText());
    					usu.setApellidos(apellidos.getText());
    					usu.setUsuario(usuario.getText());
    					usu.setCorreo(correo.getText());
    					usu.setContrasena(encryptor.encryptString(getPassword(passwordTextField, contrasena)));
    					
    					try {
    						con=Conexion.getConnection();
    						String sql = "INSERT INTO usuario (nombre, apellidos,usuario, correo, contrasena) VALUES (?,?,?,?,?)";
    						
    						pstmt=con.prepareStatement(sql);
    						pstmt.setString(1, usu.getNombre());
    						pstmt.setString(2, usu.getApellidos());
    						pstmt.setString(3, usu.getUsuario());
    						pstmt.setString(4, usu.getCorreo());
    						pstmt.setString(5, usu.getContrasena());
    						
    						pstmt.executeUpdate();
    						pstmt.close();
    						borrarCampos();
    						
    						Paint value0 =Paint.valueOf("5DDF57");
    						errorField.setStyle("-fx-control-inner-background: #"+value0.toString().substring(2));
    						errorField.setVisible(true);
    						errorField.setEditable(false);
    			    		errorField.setText("Se envio correctamente");
    						
    					} catch (SQLException e) {
    						// TODO Auto-generated catch block
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
    		    		errorField.setText("Las contraseñas no son iguales");

					}
    							}else {
    		    					
    		    					 Paint value0 =Paint.valueOf("EA475E");
    		    						errorField.setStyle("-fx-control-inner-background: #"+value0.toString().substring(2));
    		    						errorField.setVisible(true);
    		    						errorField.setEditable(false);
    		    		    		errorField.setText("La contraseña tiene que tener entre seis y cuarentas caracteres");

    							}
    						}else {
    	    					
    	    					 Paint value0 =Paint.valueOf("EA475E");
    	    						errorField.setStyle("-fx-control-inner-background: #"+value0.toString().substring(2));
    	    						errorField.setVisible(true);
    	    						errorField.setEditable(false);
    	    		    		errorField.setText("El apellido tiene que tener entre seis y cuarentas caracteres");

    						}
    					}else {
        					
       					 Paint value0 =Paint.valueOf("EA475E");
       						errorField.setStyle("-fx-control-inner-background: #"+value0.toString().substring(2));
       						errorField.setVisible(true);
       						errorField.setEditable(false);
       		    		errorField.setText("El nombre tiene que tener menos de cuarentas caracteres");

   					}
    				
    			}else {
    				
    				Paint value0 =Paint.valueOf("EA475E");
					errorField.setStyle("-fx-control-inner-background: #"+value0.toString().substring(2));
					errorField.setVisible(true);
					errorField.setEditable(false);
    	    		errorField.setText("El Usuario tiene que tener mas de seis y menos de veinte caracteres");
    				
    			}
    			}else {
    				
    				Paint value0 =Paint.valueOf("EA475E");
					errorField.setStyle("-fx-control-inner-background: #"+value0.toString().substring(2));
					errorField.setVisible(true);
					errorField.setEditable(false);
    	    		errorField.setText("El Email que ha ingresado no es valido");
    			}	
    			}else {
    				Paint value0 =Paint.valueOf("EA475E");
        			errorField.setStyle("-fx-control-inner-background: #"+value0.toString().substring(2));
        			 errorField.setVisible(true);
    					errorField.setEditable(false);
        			errorField.setText("Ningun parametro puede ser null");
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

    //Loguearse
    @FXML
    void confirmarUsuario(ActionEvent event) {

    	 Object evt = event.getSource();
    	 
    	 if(confUsuario.equals(evt)){ 
    		 if(!usarioCorreo.getText().isEmpty() && !contrasenaLogin.getText().isEmpty()){
    			 
    			 Usuarios resultado = null;
    			 
    			 usu=new Usuarios();
    			 usu.setUsuario(usarioCorreo.getText());
    			 usu.setCorreo(usarioCorreo.getText());
    			 usu.setContrasena(encryptor.encryptString(getPassword(contrasenaLoginText,contrasenaLogin)));
					
    			 
    			 String sql = "SELECT * from usuario where (usuario = ? or correo=?) and contrasena= ?";
    			 
    			 try {
    				 
    					
						con=Conexion.getConnection();
						PreparedStatement ps=con.prepareStatement(sql);

    					ps.setString(1, usu.getUsuario());
    					ps.setString(2, usu.getCorreo());
    					ps.setString(3, usu.getContrasena());
    					
    					ResultSet rs = ps.executeQuery();
    					if (rs.next()) {
    						
    					Paint value0 =Paint.valueOf("5DDF57");
    					errorField1.setStyle("-fx-control-inner-background: #"+value0.toString().substring(2));
						errorField1.setVisible(true);
						errorField1.setEditable(false);
						errorField1.setText("Se encontro el resultado");
						
						resultado= new Usuarios (rs.getInt("id_usuario"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("usuario"),rs.getString("correo"), rs.getInt("permiso"));
						CampoNombre.campoNombre(resultado);

						
						App.setRoot("index");
					
						}else {
							
							Paint value0 =Paint.valueOf("EA475E");
			    			errorField1.setStyle("-fx-control-inner-background: #"+value0.toString().substring(2));
			    			errorField1.setVisible(true);
			    			errorField1.setEditable(false);
			    			errorField1.setText("No se encontro datos coincidentes");
						}
			    		//return resultado;
    					
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println("Ocurrio un error en el envio");
					System.err.println("Mensaje del error: " + e.getMessage());
					System.err.println("Detalle del error: ");
					e.printStackTrace();
					
				}
    			 
    		 }else {
    			 
    			Paint value0 =Paint.valueOf("EA475E");
    			errorField1.setStyle("-fx-control-inner-background: #"+value0.toString().substring(2));
    			errorField1.setVisible(true);
    			errorField1.setEditable(false);
    			errorField1.setText("Debe llenar todos los campos obligatorios");
    		 }
    	 }else {
    		 
    		 Paint value0 =Paint.valueOf("EA475E");
    		 errorField1.setStyle("-fx-control-inner-background: #"+value0.toString().substring(2));
    		 errorField1.setVisible(true);
    		 errorField1.setEditable(false);
    		 errorField1.setText("Debe llenar todos los campos obligatorios");
    	 }
    	
    }
    
    //Cambiar contraseñas
    @FXML
    void changeVisibility(ActionEvent event) {
    	
    	mostrarContrasenas(ckecContrasena,passwordTextField,contrasena);
    	mostrarContrasenas(checkConf,confPasswordTextField,configContrasena);
    	mostrarContrasenas(checkContrasenaLogin, contrasenaLoginText, contrasenaLogin);

    }
    
    //Cambiar contraseña
private String getPassword(TextField text,PasswordField password){
    if(text.isVisible()){
        return text.getText();
    } else {
        return password.getText();
    }
}

//Cambiar contraseña con checkbox
private void mostrarContrasenas(CheckBox check, TextField text, PasswordField password) {
	if (check.isSelected()) {
		text.setText(password.getText());
		text.setVisible(true);
        password.setVisible(false);
        return;
	}
	password.setText(text.getText());
    password.setVisible(true);
    text.setVisible(false);
}

//Poner campos vacios
private void borrarCampos() {
	nombre.setText("");
	apellidos.setText("");
	usuario.setText("");
	correo.setText("");
	contrasena.setText("");
	configContrasena.setText("");
	passwordTextField.setText("");
	confPasswordTextField.setText("");
	
}
}

