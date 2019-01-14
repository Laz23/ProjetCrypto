package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserConteneur implements Initializable {

    @FXML
    private BorderPane userParentPane;

    @FXML
    private Circle circleImg;

    @FXML
    private Label labelNom;

    @FXML
    private Label labelPrenom;

    @FXML
    private JFXButton btnSetting;

    private JFXButton btnDeconnexion = new JFXButton("Déconnexion");
    private JFXButton btnPropo = new JFXButton("À propos");
    private AnchorPane propoPane ;
    private AnchorPane loginPane;
    private JFXDialog dialog;
    private String userType;
    private AnchorPane  simpleUserPane;
    private AnchorPane specialisteUserPane;


     public void setLabelNom(String str){
         labelNom.setText(str);
     }
     public void setLabelPrenom(String str){
         labelPrenom.setText(str);
     }

     public void setUserType(String user){
         userType = user;
     }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        insertImgTiCircle();
        btnSetting.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dialogSetting();
            }
        });
        btnPropo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dialogPropo();
            }
        });
        btnDeconnexion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                backTologin();
            }
        });




    }
    public void setUserPaneType(){
        if(userType.equals("simple")){
            loadSimpleUserPane();
        }
        if(userType.equals("spécialiste")){
            loadSpecialisteUserPane();
        }
    }


    private void insertImgTiCircle(){
        Image img = new Image(String.valueOf(UserConteneur.class.getResource("../img/freeLogo.jpeg")),false);
        circleImg.setFill(new ImagePattern(img));


    }

    public void dialogSetting(){

        BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialog = new JFXDialog(Main.getConteneurPrincipale() ,dialogLayout, JFXDialog.DialogTransition.TOP);
        JFXButton btnFermer = new JFXButton("Fermer");


            btnFermer.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent mouseEvent) -> {
                userParentPane.setEffect(null);
                dialog.close();
            });
            VBox vb = new VBox();
            vb.getChildren().add(btnDeconnexion);
            vb.getChildren().add(btnPropo);
            vb.setSpacing(10);
            dialogLayout.setPrefSize(180,50);
            Label lb = new Label("Paramètres :");

            lb.setStyle("-fx-text-fill:#34495e;");
            //Text textPara = new Text("Paramètres") ;

            //textPara.setStyle("-fx-text-fill:#3498db;");
            dialogLayout.setHeading(lb);

            dialogLayout.setBody(vb);
            dialogLayout.setActions(btnFermer);
        btnDeconnexion.setButtonType(JFXButton.ButtonType.RAISED);
        btnDeconnexion.setStyle("-jfx-rippler-fill: #2c3e50;" +
                    "-fx-background-color : #e74c3c;" +
                    "-fx-text-fill:#fff;");
            btnPropo.setButtonType(JFXButton.ButtonType.RAISED);
            btnPropo.setStyle("-jfx-rippler-fill: #2c3e50;" +
                    "-fx-background-color : #f39c12;" +
                    "-fx-text-fill:#fff;");
            btnFermer.setButtonType(JFXButton.ButtonType.RAISED);
            btnFermer.setStyle("-jfx-rippler-fill: #2c3e50;" +
                    "-fx-background-color : #3498db;" +
                    "-fx-text-fill:#fff;");

            dialog.show();
        userParentPane.setEffect(blur);
    }

    private void dialogPropo(){
        //BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayoutPropo = new JFXDialogLayout();
        JFXDialog dialogPropo = new JFXDialog(Main.getConteneurPrincipale() ,dialogLayoutPropo, JFXDialog.DialogTransition.BOTTOM);
        JFXButton btnFermer = new JFXButton("Fermer");
        btnFermer.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent mouseEvent) -> {
            //userParentPane.setEffect(null);
            dialogPropo.close();
        });
        loadPropoPane();
        dialogLayoutPropo.setBody(propoPane);

        btnFermer.setButtonType(JFXButton.ButtonType.RAISED);
        btnFermer.setStyle("-jfx-rippler-fill: #2c3e50;" +
                "-fx-background-color : #3498db;" +
                "-fx-text-fill:#fff;");
        ///dialogLayoutPropo.setPrefSize(450 , 250);
        dialogLayoutPropo.setActions(btnFermer);

        dialogPropo.show();
        //userParentPane.setEffect(blur);



    }
    private void backTologin(){
        makeFadeOut();

    }
    private void loadPropoPane(){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(UserConteneur.class.getResource("propo.fxml"));

        try {
            propoPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeFadeOut(){
        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(500));
        ft.setNode(userParentPane);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                userParentPane.setEffect(null);
                dialog.close();
                loadLoginPane();
                Main.getConteneurPrincipale().getChildren().addAll(loginPane);


            }
        });
        ft.play();
    }
    private void loadLoginPane(){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(UserConteneur.class.getResource("Login.fxml"));

        try {
            loginPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSimpleUserPane() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("SimpleUser.fxml"));

        try {
            simpleUserPane = loader.load();
           // userConteneur = loader.getController();
            userParentPane.setCenter(simpleUserPane);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private void loadSpecialisteUserPane() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("SpecialisteUser.fxml"));

        try {
            specialisteUserPane = loader.load();
            // userConteneur = loader.getController();
            userParentPane.setCenter(specialisteUserPane);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
