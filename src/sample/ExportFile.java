package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ExportFile implements Initializable {

    @FXML
    private TextField inFileName;

    @FXML
    private JFXButton btnValider;

    private boolean truth = false;
    public TextField getInFileName(){
        return inFileName;
    }
    public JFXButton getBtnValider(){
        return btnValider;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
