package sample;

import com.jfoenix.controls.*;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
//import javafx.scene.transform.Affine;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import algorithmes_sym_as.*;

public class SpecialisteUser implements Initializable {
    @FXML
    private AnchorPane anchorUserSpe;
    @FXML
    private JFXButton btnChoiceFile;

    @FXML
    private TextField outTextFile;

    @FXML
    private JFXButton btnCrypter;

    @FXML
    private JFXButton btnDecrypter;

    @FXML
    private JFXTextArea outTextArea;

    @FXML
    private JFXRadioButton btnRadioDES;

    @FXML
    private JFXRadioButton btnRadioRSA;

    @FXML
    private JFXRadioButton btnRadioElga;

    @FXML
    private JFXRadioButton btnRadioTran;

    @FXML
    private JFXRadioButton btnRadioHill;

    @FXML
    private JFXRadioButton btnRadioCesar;
    @FXML
    private JFXButton btnExport;
    public Thread t ;



    private Pane DialogAsPane;
    private DialogInputKeysAs dialogInputKeysAs;

    private Pane DialogSymPane;
    private DialogInputKeySym dialogInputKeySym;


    private Pane DialogHillPane;
    private DialogHill dialogHill;

    private Pane exportFilePane;
    private ExportFile exportFile;
    private boolean inputeFileName;
    private String nameFile;

    private AlgoCryptage algoC;

    private String strKeySym;

    private String strKeySPub;
    private String strKeySPriv;


    private String strHill1;
    private String strHill2;
    private String strHill3;
    private String strHill4;

    private boolean isClickerCrypter;
    private boolean isClickerDecrypter;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnExport.setOpacity(0);
        btnExport.setDisable(true);

        btnChoiceFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                float deb = System.currentTimeMillis();
                 openFiles();
                 float fin  = System.currentTimeMillis();
                 System.out.println(fin - deb);
            }
        });
        btnCrypter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                isClickerCrypter = true;
                isClickerDecrypter = false;
                if (btnExport.getOpacity() == 0 && (asSelected() == true || symeSelected() == true || btnRadioHill.isSelected() || btnRadioRSA.isSelected())){
                    btnExport.setDisable(false);
                    makeFadeOut();

                }

                if (asSelected()){

                    dialogKeyAs();
                   // pickALgo();
                    //crypter();
                }else if(symeSelected()){
                    dialogKeySym();
                    //pickALgo();
                    //crypter();
                }else if (btnRadioRSA.isSelected()){
                    pickALgo();
                    //crypter();
                }
                else if (btnRadioHill.isSelected()){
                    dialogHill();
                }
                else{
                    Alert probleme = new Alert(Alert.AlertType.ERROR);
                    probleme.setTitle("Erreur");
                    probleme.setHeaderText("Il faut choisir un algorithme pour passer à l'action !!!!");
                    probleme.showAndWait();
                }

            }
        });
        btnDecrypter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                isClickerDecrypter = true;
                isClickerCrypter = false;
                if (btnExport.getOpacity() == 0 && (asSelected() == true || symeSelected() == true || btnRadioHill.isSelected() || btnRadioRSA.isSelected())){
                    btnExport.setDisable(false);
                    makeFadeOut();

                }
                if (asSelected()){
                    dialogKeyAs();
                    //pickALgo();
                   // decrypter();
                }else if(symeSelected()){
                    dialogKeySym();
                   // pickALgo();
                   // decrypter();

                }else if (btnRadioRSA.isSelected()){
                    pickALgo();
                   // decrypter();
                }
                else if (btnRadioHill.isSelected()){
                    dialogHill();
                }
                else{
                    Alert probleme = new Alert(Alert.AlertType.ERROR);
                    probleme.setTitle("Erreur");
                    probleme.setHeaderText("Il faut choisir un algorithme pour passer à l'action !!!!");
                    probleme.showAndWait();
                }
            }
        });
        btnExport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dialogExport();

                //btnExport.setDisable(true);
                //makeFadeIn();

            }
        });

    }

    private void exportTextToFile(){
        //Path path = Paths.get(Main.class.getName());
        BufferedWriter writer;

            try{
                writer = new BufferedWriter(new FileWriter("../"+nameFile+".txt"));
                writer.write(outTextArea.getText());
                writer.close();

            }catch (IOException e) {
                Alert probleme = new Alert(Alert.AlertType.ERROR);
                probleme.setTitle("Erreur");
                probleme.setHeaderText(e.getMessage());
                probleme.showAndWait();
            }



    }

    private void pickALgo(){
        if (btnRadioTran.isSelected()){
            algoC = new Transposition(Integer.valueOf(strKeySym));
        }

        if (btnRadioCesar.isSelected()){
            algoC = new Cesar(Integer.valueOf(strKeySym));
        }
        if (btnRadioRSA.isSelected()){
            algoC = (AlgoCryptage) new RSABIGInteger();
        }
        if (btnRadioElga.isSelected()){
            algoC = (AlgoCryptage) new Affine(Integer.valueOf(strKeySPub),Integer.valueOf(strKeySPriv));
        }

        if (btnRadioDES.isSelected()){
            algoC = (AlgoCryptage) new DES(strKeySym);
        }

        if(btnRadioHill.isSelected()){
            algoC = (AlgoCryptage) new Hill(Integer.valueOf(strHill1),Integer.valueOf(strHill2),Integer.valueOf(strHill3),Integer.valueOf(strHill4));
        }

        if (isClickerCrypter){
            crypter();
        }
        if(isClickerDecrypter){
            decrypter();
        }
        /*if (btnRadioHill.isSelected()){
            algoC = (AlgoCryptage) new Hill();
        }*/




    }

    private void crypter(){
        outTextArea.setText(algoC.crypter(outTextArea.getText().toString()));
    }
    private void decrypter(){
        outTextArea.setText(algoC.decrypter(outTextArea.getText().toString()));
    }

    private  boolean asSelected(){
        if ( btnRadioElga.isSelected()){
            return true;
        }
        return false;
    }
    private  boolean symeSelected(){
        if (btnRadioCesar.isSelected() || btnRadioDES.isSelected() || btnRadioTran.isSelected()){
            return true;
        }
        return false;
    }

    public void openFiles (){

        FileChooser fc  = new FileChooser();
        File file = fc.showOpenDialog(null);
       readFile(file);

    }
    private void readFile(File file){
        effacer();
        outTextFile.setText(file.getAbsolutePath());
        BufferedReader reader;
        FileReader fileReader;
        String str ;
        try{
            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);
            do{
                str  = reader.readLine();
                if (str != null)
                   outTextArea.setText(outTextArea.getText() + str + "\n");
            }while (str != null);
        }catch(FileNotFoundException e){
            callAlerte(e);
        }catch(IOException e){
           callAlerte(e);
        }

    }
    private void callAlerte(Exception e){
        Alert probleme = new Alert(Alert.AlertType.ERROR);
        probleme.setTitle("Erreur");
        probleme.setHeaderText(e.getMessage());
        probleme.showAndWait();
    }
    private void effacer(){
        outTextFile.setText("");
        outTextArea.setText("");
    }

    public JFXTextArea getOutTextArea() {
        return outTextArea;
    }
    private void dialogKeyAs(){
        BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialogKeys = new JFXDialog(Main.getConteneurPrincipale() ,dialogLayout, JFXDialog.DialogTransition.BOTTOM);
        JFXButton btnFermer = new JFXButton("Fermer");
        btnFermer.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent mouseEvent) -> {
            anchorUserSpe.setEffect(null);
            dialogKeys.close();
        });
        loadKeysAs();
        dialogInputKeysAs.getBtnValider().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (dialogInputKeysAs.getInPrivateKey().getText().isEmpty() || dialogInputKeysAs.getInPublicKey().getText().isEmpty()){
                    Alert probleme = new Alert(Alert.AlertType.ERROR);
                    probleme.setTitle("Erreur");
                    probleme.setHeaderText("Veuillez saisir les deux clés !!! ");
                    probleme.showAndWait();
                }else{
                    strKeySPriv = dialogInputKeysAs.getInPrivateKey().getText();
                    strKeySPub = dialogInputKeysAs.getInPublicKey().getText();
                    pickALgo();
                    crypter();
                }

            }

        });
        dialogLayout.setBody(DialogAsPane);
        dialogLayout.setPrefSize(246, 312);
        dialogLayout.setStyle("-fx-background-color : #34495e;");

        btnFermer.setButtonType(JFXButton.ButtonType.RAISED);
        btnFermer.setStyle("-jfx-rippler-fill: #2c3e50;" +
                "-fx-background-color : #3498db;" +
                "-fx-text-fill:#fff;");
        ///dialogLayoutPropo.setPrefSize(450 , 250);
        dialogLayout.setActions(btnFermer);


        dialogKeys.show();
        anchorUserSpe.setEffect(blur);

    }

    private void dialogKeySym(){
        BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialogKeys = new JFXDialog(Main.getConteneurPrincipale() ,dialogLayout, JFXDialog.DialogTransition.BOTTOM);
        JFXButton btnFermer = new JFXButton("Fermer");
        btnFermer.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent mouseEvent) -> {
            anchorUserSpe.setEffect(null);
            dialogKeys.close();
        });
        loadKeysSym();
        dialogInputKeySym.getBtnValider().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (dialogInputKeySym.getInPrivateKey().getText().isEmpty()){
                    Alert probleme = new Alert(Alert.AlertType.ERROR);
                    probleme.setTitle("Erreur");
                    probleme.setHeaderText("Veuillez saisir les deux clés !!! ");
                    probleme.showAndWait();
                }else{
                    strKeySym = dialogInputKeySym.getInPrivateKey().getText();
                    pickALgo();
                }

            }
        });
        dialogLayout.setBody(DialogSymPane);
        dialogLayout.setPrefSize(246, 312);
        dialogLayout.setStyle("-fx-background-color : #34495e;");

        btnFermer.setButtonType(JFXButton.ButtonType.RAISED);
        btnFermer.setStyle("-jfx-rippler-fill: #2c3e50;" +
                "-fx-background-color : #3498db;" +
                "-fx-text-fill:#fff;");
        ///dialogLayoutPropo.setPrefSize(450 , 250);
        dialogLayout.setActions(btnFermer);


        dialogKeys.show();
        anchorUserSpe.setEffect(blur);

    }

    private void dialogHill(){
        BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialogKeys = new JFXDialog(Main.getConteneurPrincipale() ,dialogLayout, JFXDialog.DialogTransition.BOTTOM);
        JFXButton btnFermer = new JFXButton("Fermer");
        btnFermer.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent mouseEvent) -> {
            anchorUserSpe.setEffect(null);
            dialogKeys.close();
        });
        loadHill();
        dialogHill.getBtnValider().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (dialogHill.getInKey1().getText().isEmpty() || dialogHill.getInKey2().getText().isEmpty() || dialogHill.getInKey3().getText().isEmpty() || dialogHill.getInKey4().getText().isEmpty()){
                    Alert probleme = new Alert(Alert.AlertType.ERROR);
                    probleme.setTitle("Erreur");
                    probleme.setHeaderText("Veuillez saisir les clés !!! ");
                    probleme.showAndWait();
                }else{
                    strHill1 = dialogHill.getInKey1().getText();
                    strHill2 = dialogHill.getInKey2().getText();
                    strHill3 = dialogHill.getInKey3().getText();
                    strHill4 = dialogHill.getInKey4().getText();

                    pickALgo();
                }

            }
        });
        dialogLayout.setBody(DialogHillPane);
        dialogLayout.setPrefSize(246, 315);
        dialogLayout.setStyle("-fx-background-color : #34495e;");

        btnFermer.setButtonType(JFXButton.ButtonType.RAISED);
        btnFermer.setStyle("-jfx-rippler-fill: #2c3e50;" +
                "-fx-background-color : #3498db;" +
                "-fx-text-fill:#fff;");
        ///dialogLayoutPropo.setPrefSize(450 , 250);
        dialogLayout.setActions(btnFermer);


        dialogKeys.show();
        anchorUserSpe.setEffect(blur);

    }

    private void dialogExport(){
        BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialogExport = new JFXDialog(Main.getConteneurPrincipale() ,dialogLayout, JFXDialog.DialogTransition.BOTTOM);
        JFXButton btnFermer = new JFXButton("Fermer");
        btnFermer.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent mouseEvent) -> {

            anchorUserSpe.setEffect(null);
            dialogExport.close();
        });
        loadExport();
        dialogLayout.setBody(exportFilePane);
        dialogLayout.setPrefSize(246, 312);
        dialogLayout.setStyle("-fx-background-color : #34495e;");

        btnFermer.setButtonType(JFXButton.ButtonType.RAISED);
        btnFermer.setStyle("-jfx-rippler-fill: #2c3e50;" +
                "-fx-background-color : #3498db;" +
                "-fx-text-fill:#fff;");
        ///dialogLayoutPropo.setPrefSize(450 , 250);
        dialogLayout.setActions(btnFermer);
        exportFile.getBtnValider().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(exportFile.getInFileName().getText().isEmpty()){
                    Alert probleme = new Alert(Alert.AlertType.ERROR);
                    probleme.setTitle("Erreur");
                    probleme.setHeaderText("Veuillez donner un nom à votre fichier !!! ");
                    probleme.showAndWait();

                }else{
                    nameFile = exportFile.getInFileName().getText();
                    exportTextToFile();
                }
            }
        });

        dialogExport.show();
        anchorUserSpe.setEffect(blur);

    }

    private void loadKeysAs() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("DialogInputKeysAs.fxml"));

        try {
            DialogAsPane = loader.load();
            dialogInputKeysAs = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void loadKeysSym() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("DialogInputKeySym.fxml"));

        try {
            DialogSymPane = loader.load();
            dialogInputKeySym = (DialogInputKeySym) loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void loadHill() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("DialogHill.fxml"));

        try {
            DialogHillPane = loader.load();
            dialogHill = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void loadExport() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("ExportFile.fxml"));

        try {
            exportFilePane = loader.load();
            exportFile = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void makeFadeOut(){
        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(500));
        ft.setNode(btnExport);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                btnExport.setOpacity(1);

            }
        });
        ft.play();
    }

    // --Methode bannie-------------------
    private void makeFadeIn(){
        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(500));
        ft.setNode(btnExport);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                btnExport.setOpacity(0);

            }
        });
        ft.play();
    }
    //------------------------

     class PlayOpenFile implements Runnable{
        File file;
        PlayOpenFile(File file){
            this.file = file;
        }
        public void run(){
            readFile(file);
        }
    }


}
