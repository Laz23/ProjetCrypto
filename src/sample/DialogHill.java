package sample;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DialogHill implements Initializable {


    @FXML
    private TextField inKey1;

    @FXML
    private TextField inKey2;

    @FXML
    private JFXButton btnValider;

    @FXML
    private TextField inKey3;

    @FXML
    private TextField inKey4;

    public JFXButton getBtnValider(){
        return btnValider;
    }

    public TextField getInKey1(){
        return inKey1;
    }

    public TextField getInKey2(){
        return inKey2;
    }
    public TextField getInKey3(){
        return inKey3;
    }
    public TextField getInKey4(){
        return inKey4;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
