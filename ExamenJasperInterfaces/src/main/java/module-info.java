module com.example.examenjasperinterfaces {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires jasperreports;
    requires javafx.swing;

    //abrir todos los paquetes

    opens utils;
    opens clase;


    opens com.example.examenjasperinterfaces to javafx.fxml;
    exports com.example.examenjasperinterfaces;
}