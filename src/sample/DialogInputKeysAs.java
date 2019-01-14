package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DialogInputKeysAs implements Initializable {


    @FXML
    private TextField inPrivateKey;

    @FXML
    private TextField inPublicKey;

    @FXML
    private JFXButton btnValider;


    public JFXButton getBtnValider(){
        return btnValider;
    }

    public TextField getInPrivateKey(){
        return inPrivateKey;
    }

    public TextField getInPublicKey(){
        return inPublicKey;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnValider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               if( verificationINputKeys()){
                   Alert probleme = new Alert(Alert.AlertType.ERROR);
                   probleme.setTitle("Erreur");
                   probleme.setHeaderText("Veuillez saisir les deux clés !!! ");
                   probleme.showAndWait();
               }else{
                   //faire appelle à l'algorithme de cryptage celui qui a été séléctionné

               }
            }
        });

    }

    private boolean verificationINputKeys(){
        if (inPrivateKey.getText().isEmpty() || inPublicKey.getText().isEmpty()){
                return true;
        }
        return false;
    }
}
