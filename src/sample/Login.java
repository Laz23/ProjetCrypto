package sample;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login implements Initializable {


    private AnchorPane anchorForm;
    private BorderPane usersPane;

    @FXML
    private AnchorPane anchor_login;

    @FXML
    private TextField textFPseudo;

    @FXML
    private TextField textFPassWord;

    @FXML
    private JFXButton btn_se_connecter;

    @FXML
    private JFXButton btn_s_inscrire;

    private BaseDP bd;
    //private Connection conne;
    private Formulaire formulaire;
    private UserConteneur userConteneur;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // conne = SdzConnection.getInstance();
        bd = new BaseDP();
        loadUserPane();


    }

    public void goToForm() {
        makeFadeOut();

    }

    public void goToUserPane() {
        boolean erreur = false;
        //userConteneur = new UserConteneur();
        ResultSet result = null;
        try{
            result = bd.searchUser(textFPseudo.getText(), textFPassWord.getText());

            userConteneur.setLabelNom(result.getString("nom"));
            userConteneur.setLabelPrenom(result.getString("prenom"));
            userConteneur.setUserType(result.getString("type"));
            userConteneur.setUserPaneType();

        }catch(SQLException e){
            Alert probleme = new Alert(Alert.AlertType.ERROR);
            probleme.setTitle("Erreur");
            if (e.getMessage() == "ResultSet closed"){
                probleme.setHeaderText("Pseudo ou Mot de passe incorrecte ! ");
            }else{
                probleme.setHeaderText(e.getMessage());
            }
           // probleme.setHeaderText(e.getMessage());

            probleme.showAndWait();
            erreur = true;
        }
        if (!erreur){
            MakeFadeOutUserPane();
        }

    }

    private void makeFadeOut() {
        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(500));
        ft.setNode(anchor_login);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loadForm();
                Main.getConteneurPrincipale().getChildren().addAll(anchorForm);

            }
        });
        ft.play();
    }

    private void loadForm() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Formulaire.fxml"));

        try {
            anchorForm = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void MakeFadeOutUserPane() {
        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(500));
        ft.setNode(anchor_login);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //loadUserPane();
                Main.getConteneurPrincipale().getChildren().addAll(usersPane);

            }
        });
        ft.play();
    }

    private void loadUserPane() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("UserConteneur.fxml"));

        try {
            usersPane = loader.load();
            userConteneur = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
