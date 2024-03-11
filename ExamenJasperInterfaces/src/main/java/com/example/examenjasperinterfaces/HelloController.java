package com.example.examenjasperinterfaces;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;
import utils.MYSQLConecction;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextField textNombre;
    @FXML
    private TextField textSexo;
    @FXML
    private TextField textPeso;
    @FXML
    private TextField textEdad;
    @FXML
    private TextField textTalla;
    @FXML
    private TextField textActividad;
    @FXML
    private TextArea textObservaciones;
    @FXML
    private Button botonGuardar;
    @FXML
    private Button botonSalir;
    @FXML
    private Button botonDescargar;
    @FXML
    private Label idLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }

    @FXML
    public void guardar(ActionEvent actionEvent) {
        String nombre = textNombre.getText();
        String sexo = textSexo.getText().toLowerCase();
        double peso = Double.parseDouble(textPeso.getText());
        int edad = Integer.parseInt(textEdad.getText());
        double talla = Double.parseDouble(textTalla.getText());
        double actividad;
        String actividadText = textActividad.getText().toLowerCase();
        if (sexo.equals("hombre")) {
            switch (actividadText) {
                case "sedentario":
                    actividad = 1.3;
                    break;
                case "moderado":
                    actividad = (sexo.equals("hombre")) ? 1.6 : 1.5;
                    break;
                case "activo":
                    actividad = (sexo.equals("hombre")) ? 1.7 : 1.6;
                    break;
                case "muy activo":
                    actividad = (sexo.equals("hombre")) ? 2.1 : 1.9;
                    break;
                default:
                    idLabel.setText("Por favor, ingrese una actividad válida (sedentario, moderado, activo, muy activo).");
                    return;
            }
        } else if (sexo.equals("mujer")){
            switch (actividadText) {
                case "sedentario":
                    actividad = 1.3;
                    break;
                case "moderado":
                    actividad = (sexo.equals("mujer")) ? 1.6 : 1.5;
                    break;
                case "activo":
                    actividad = (sexo.equals("mujer")) ? 1.7 : 1.6;
                    break;
                case "muy activo":
                    actividad = (sexo.equals("mujer")) ? 2.1 : 1.9;
                    break;
                default:
                    idLabel.setText("Por favor, ingrese una actividad válida (sedentario, moderado, activo, muy activo).");
                    return;
            }

            //este if es por sin faltan campos
            if (nombre.isEmpty() || sexo.isEmpty() || textPeso.getText().isEmpty() || textEdad.getText().isEmpty() || textTalla.getText().isEmpty() || actividadText.isEmpty()) {
                idLabel.setText("Por favor, complete todos los campos.");
                return;
            }

            // calculo de cosas
            double metabolismoBasal;
            double gastoEnergeticoTotal;

            if (sexo.equals("hombre")) {
                metabolismoBasal = 10 * peso + 6.25 * talla - 5 * edad + 5;
            } else if (sexo.equals("mujer")) {
                metabolismoBasal = 10 * peso + 6.25 * talla - 5 * edad - 161;
            } else {
                idLabel.setText("Por favor, ingrese un sexo válido (hombre o mujer).");
                return;
            }

            gastoEnergeticoTotal = metabolismoBasal * actividad;


            idLabel.setText("Metabolismo Basal: " + metabolismoBasal + "Gasto Energético Total: " + gastoEnergeticoTotal);
        }
    }



    @FXML
    public void salir(ActionEvent actionEvent) {
        Stage stage = (Stage) botonSalir.getScene().getWindow();
        // Cierra la ventana
        stage.close();
    }

    @FXML
    public void descargarPDF(ActionEvent actionEvent) { Connection connection = MYSQLConecction.getConexion();
        try {
            JasperPrint jasper = JasperFillManager.fillReport("CalculadoraJasper.jasper",new HashMap<>(),connection);
            JRViewer visor = new JRViewer(jasper);
            JFrame ventana = new JFrame("Listado Clientes");
            ventana.getContentPane().add(visor);
            ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
            ventana.pack();
            ventana.setVisible(true);
            JRPdfExporter exp = new JRPdfExporter();
            exp.setExporterInput(new SimpleExporterInput(jasper));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput("CalculadoraJasper.pdf"));
            exp.setConfiguration(new SimplePdfExporterConfiguration());
            exp.exportReport();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}