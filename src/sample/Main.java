package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    private  Stage stagePrincipal;
    private static StackPane conteneurPrincipale;
    private Formulaire formulaire;


    /*private AnchorPane screenPane;
    private Screen screenController;*/
    //String css = getClass().getResource("css-style/radio-button.css").toExternalForm();


    public static StackPane getConteneurPrincipale(){
        return  conteneurPrincipale;
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Image img = new Image(String.valueOf(Main.class.getResource("/img/freeLogo.jpeg")));
        stagePrincipal = primaryStage;
        stagePrincipal.getIcons().add(img);
        //Ca ne vous rappelle pas une JFrame ?
        stagePrincipal.setTitle("Tower Defender");
        //stagePrincipal.initStyle(StageStyle.UNDECORATED);

        //Nous allons utiliser nos fichier FXML dans ces deux méthodes
        //screenSplash();

        initialisationConteneurPrincipal();
        initialisationContenu();


    }

    /*private void screenSplash(){
        FXMLLoader loader = new FXMLLoader();
        //On lui spécifie le chemin relatif à notre classe
        //du fichier FXML a charger : dans le sous-dossier view
        loader.setLocation(Main.class.getResource("screen.fxml"));
        try {
            //Le chargement nous donne notre conteneur
            screenPane = (AnchorPane) loader.load();
            //screenController = loader.getController();
            //On définit une scène principale avec notre conteneur
            Scene scene = new Scene(screenPane);
            //scene.getStylesheets().addAll(css);
            //Que nous affectons à notre Stage
            stagePrincipal.setScene(scene);
            //Pour l'afficher
            stagePrincipal.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    private void initialisationConteneurPrincipal(){
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
    }


    public static void main(String[] args) {
        launch(args);
    }
}
