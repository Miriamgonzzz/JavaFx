module javaFx.ProyectoPrimerTrimestre {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
	requires java.sql;
	requires javafx.graphics;
	requires java.desktop;

    opens javaFx.ProyectoPrimerTrimestre to javafx.fxml;
    exports javaFx.ProyectoPrimerTrimestre;
    exports proyectoPrimerTrimestre.Dao;
}
