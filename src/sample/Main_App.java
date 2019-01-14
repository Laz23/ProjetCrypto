package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main_App {

    private  Stage stagePrincipal;
    private static StackPane conteneurPrincipale;

    public  void initialisationConteneurPrincipal(){
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

    public  void initialisationContenu() {
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
    }
}
