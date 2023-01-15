package Encryptar;

public class ControllerCorreo {

	 public static boolean validateEmail(String email) {
	        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	        return email.matches(regex);                
	    }
}
