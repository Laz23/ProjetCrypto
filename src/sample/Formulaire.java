package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRadioButton;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Formulaire implements Initializable {


    @FXML
    private AnchorPane anchorForm;

    @FXML
    private TextField textNom;

    @FXML
    private TextField textPrenom;

    @FXML
    private TextField textPseudo;

    @FXML
    private JFXRadioButton radioSpecialiste;

    @FXML
    private JFXRadioButton radioSimple;

    @FXML
    private JFXButton btnValider;

    @FXML
    private PasswordField textPassWard;

    @FXML
    private JFXButton back;

    @FXML
    private JFXButton btnHowDoIt;

    private  String userType ;

   // private BorderPane borderPaneParent;
    private AnchorPane anchorLogin;
    private BaseDP bd ;
    private AnchorPane howPane;
    private AnchorPane errorPane;
    private ErrorForm errorForm;
    private UserConteneur userConteneur;
    private BorderPane usersPane;
   // private Connection conne;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchorForm.setOpacity(0);
        makeFadeIn();

        //conne = SdzConnection.getInstance();
        bd = new BaseDP();
        loadUserPane();
        loadError();

        btnHowDoIt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dialogHow();
            }
        });
        btnValider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                boolean insertionValidee = false;
                boolean remplir = verificationForm();
                if (!remplir){
                   String str = new String("Remplissez tous les champs pour avoir un compte et séléctionnez la manière d'utilisation");
                    errorForm.setTextAreaErrorForm(str);
                    dialogError();
                }else{
                    insertionValidee = insertUser(remplir);
                }

                goToUserPane(insertionValidee);
            }
        });


    }

    private boolean insertUser(boolean remplir){
        boolean insertionValide = true ;
        if (remplir == true){
            if (radioSimple.isSelected()){
                userType = new String ("simple");

            }else{
                userType = new String ("spécialiste");
            }

            try {
                bd.insert(textPseudo.getText(), textNom.getText(),textPrenom.getText(),textPassWard.getText(),userType);
            } catch (SQLException e) {
                Alert probleme = new Alert(Alert.AlertType.ERROR);
                probleme.setTitle("Erreur");
                probleme.setHeaderText(e.getMessage());
                probleme.showAndWait();
                insertionValide = false;
            }
        }
        return insertionValide;


    }
    private boolean verificationForm(){
        boolean succe = true;
        String str = null;
        if (textNom.getText().isEmpty() || textPassWard.getText().isEmpty() || textPrenom.getText().isEmpty() || textPseudo.getText().isEmpty() ||
                (!radioSimple.isSelected() && !radioSpecialiste.isSelected())){
            succe = false ;
        }

        return succe ;


    }

    public void backToLogin(){
        makeFadeOut();

    }

    private void makeFadeIn(){
        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(500));
        ft.setNode(anchorForm);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    private void makeFadeOut(){
        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(500));
        ft.setNode(anchorForm);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loadForm();
                Main.getConteneurPrincipale().getChildren().addAll(anchorLogin);

            }
        });
        ft.play();
    }
    private void dialogHow(){
        BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialogHow = new JFXDialog(Main.getConteneurPrincipale() ,dialogLayout, JFXDialog.DialogTransition.BOTTOM);
        JFXButton btnFermer = new JFXButton("Fermer");
        btnFermer.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent mouseEvent) -> {
            anchorForm.setEffect(null);
            dialogHow.close();
        });
        loadHowPane();
        dialogLayout.setBody(howPane);

        btnFermer.setButtonType(JFXButton.ButtonType.RAISED);
        btnFermer.setStyle("-jfx-rippler-fill: #2c3e50;" +
                "-fx-background-color : #3498db;" +
                "-fx-text-fill:#fff;");
        ///dialogLayoutPropo.setPrefSize(450 , 250);
        dialogLayout.setActions(btnFermer);

        dialogHow.show();
        anchorForm.setEffect(blur);

    }
    private void dialogError(){
        BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialogError = new JFXDialog(Main.getConteneurPrincipale() ,dialogLayout, JFXDialog.DialogTransition.TOP);
        JFXButton btnFermer = new JFXButton("Fermer");
        btnFermer.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent mouseEvent) -> {
            anchorForm.setEffect(null);
            dialogError.close();
        });
        //loadError();
        dialogLayout.setBody(errorPane);
        dialogLayout.setPrefSize(500 , 300);

        btnFermer.setButtonType(JFXButton.ButtonType.RAISED);
        btnFermer.setStyle("-jfx-rippler-fill: #2c3e50;" +
                "-fx-background-color : #3498db;" +
                "-fx-text-fill:#fff;");
        ///dialogLayoutPropo.setPrefSize(450 , 250);
        dialogLayout.setActions(btnFermer);

        dialogError.show();
        anchorForm.setEffect(blur);

    }

    private void loadError(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("ErrorForm.fxml"));

        try {
            errorPane = (AnchorPane) loader.load();
            errorForm = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadHowPane(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("CommentLeFaire.fxml"));

        try {
            howPane = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadForm(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Login.fxml"));

        try {
            anchorLogin = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    // user pane -fade- et -load pane-
    private void MakeFadeOutUserPane() {
        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(500));
        ft.setNode(anchorForm);
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

    public void goToUserPane(boolean insertionValide) {
        userConteneur.setLabelNom(textNom.getText());
        userConteneur.setLabelPrenom(textPrenom.getText());
        userConteneur.setUserType(userType);
        if (insertionValide){
            userConteneur.setUserPaneType();
            MakeFadeOutUserPane();
        }

    }
}
