package sample;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DialogInputKeySym implements Initializable {

    @FXML
    private TextField inPrivateKey;

    @FXML
    private JFXButton btnValider;

    public JFXButton getBtnValider(){
        return btnValider;
    }

    public TextField getInPrivateKey(){
        return inPrivateKey;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
