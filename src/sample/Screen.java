package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Screen implements Initializable {

    private Thread t ;
    private Stage stagePrincipal;
    private static StackPane conteneurPrincipale;
    private Main_App main_app ;


    @FXML
    private AnchorPane screenPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        main_app = new Main_App();
        new Loading().start();
    }

    /*private void initialisationConteneurPrincipal(){
        FXMLLoader loader = new FXMLLoader();
        //On lui spécifie le chemin relatif à notre classe
        //du fichier FXML a charger : dans le sous-dossier view
        loader.setLocation(Main.class.getResource("ConteneurParent.fxml"));
        try {
            //Le chargement nous donne notre conteneur
            conteneurPrincipale = (StackPane) loader.load();
            //On définit une scène principale avec notre conteneur
            Scene scene = new Scene(conteneurPrincipale);
            //scene.getStylesheets().addAll(css);
            //Que nous affectons à notre Stage
            stagePrincipal = new Stage();
            stagePrincipal.setScene(scene);
            //Pour l'afficher
            stagePrincipal.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialisationContenu() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Login.fxml"));
        try {
            //Nous récupérons notre conteneur qui contiendra les données
            //Pour rappel, c'est un AnchorPane...
            AnchorPane loginPane = (AnchorPane) loader.load();
            //AnchorPane bp = (AnchorPane) loader.load();
            //Login loginsession = loader.getController();
            // loginsession.setParentPane(conteneurPrincipale);
            //formulaire = new Formulaire();
            //formulaire.setParentPane(conteneurPrincipale);
            conteneurPrincipale.getChildren().addAll(loginPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    class Loading extends Thread {
        public void run(){
            try {
                Thread.sleep(5000);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        main_app.initialisationConteneurPrincipal();
                        main_app.initialisationContenu();
                        screenPane.getScene().getWindow().hide();
                    }
                });
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        }

            }
}
