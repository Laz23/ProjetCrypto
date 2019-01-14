package sample;

import algorithmes_sym_as.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SimpleUser implements Initializable {

    @FXML
    private AnchorPane userSimplePane;

    @FXML
    private JFXButton btnChoiceFile;

    @FXML
    private TextField outTextFile;

    @FXML
    private JFXTextArea outTextArea;

    @FXML
    private JFXButton btnCrypter;

    @FXML
    private JFXButton btnDecrypter;


    @FXML
    private JFXButton btnExport;

    @FXML
    private JFXButton btnShowKeys;

    private Pane exportFilePane;
    private ExportFile exportFile;
    private String nameFile;
    
    private BorderPane pickPane;
    private PickMyAlgo pickAlgo;


    private AlgoCryptage algoC;

    private boolean isClickerCrypter;
    private boolean isClickerDecrypter;


    private int hillKey1;
    private int hillKey2;
    private int hillKey3;
    private int hillKey4;

    private int cesarKey;

    private int affineKey1;
    private int affineKey2;

    private int transpositionKey;

    private String desKey;

    private AnchorPane showKeysPane;
    private ShowKeys showKeys;

    private AnchorPane decryptagePane;
    private DecrypterSimpleUser decryptageIns;

    private StringBuffer stringBuffer;

    private boolean isHill , isAffine, isCesar,isDes , isTransposition;


    public JFXTextArea getOutTextArea() {
        return outTextArea;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isAffine = false;
        isHill = false;
        isDes = false;
        isTransposition = false;
        isCesar = false;
        btnExport.setOpacity(0);
        btnExport.setDisable(true);
        btnShowKeys.setOpacity(0);
        btnShowKeys.setDisable(true);
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
               /* if (btnExport.getOpacity() == 0){
                    btnExport.setDisable(false);
                    makeFadeOut();

                }*/
                if(outTextArea.getText().isEmpty()){
                    Alert probleme = new Alert(Alert.AlertType.ERROR);
                    probleme.setTitle("Erreur");
                    probleme.setHeaderText("Veuillez saisir votre text à crypter!!! ");
                    probleme.showAndWait();
                }else{
                    btnExport.setDisable(false);
                    makeFadeOut();
                    dialogPickAlgo();
                }
            }

        });

        btnDecrypter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                isClickerCrypter = false;
                isClickerDecrypter = true;
                if(outTextArea.getText().isEmpty()){
                    Alert probleme = new Alert(Alert.AlertType.ERROR);
                    probleme.setTitle("Erreur");
                    probleme.setHeaderText("Veuillez saisir votre text à decrypter!!! ");
                    probleme.showAndWait();
                }else{
                    btnExport.setDisable(false);
                    makeFadeOut();
                    dialogDecrypter();
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
        btnShowKeys.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dialogShowKeys();
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

    // ---------------------------- algorithme à choisir  -----------------------------------------------------------------

    private void pickAlgorithme(){
        isAffine = false;
        isHill = false;
        isDes = false;
        isTransposition = false;
        isCesar = false;

        if (pickAlgo.getBtnRadioRapide().isSelected()){
            if (pickAlgo.getBtnRadioFaible().isSelected()){
                isCesar = true;
                cesarKey  = RandomKeyGenerator.getCesarKey();
                algoC = new Cesar(cesarKey);
            }
            if (pickAlgo.getBtnRadioMoyen().isSelected()){
                isTransposition = true;
                transpositionKey = RandomKeyGenerator.getCesarKey();
                algoC = new Transposition(transpositionKey);
            }
            if(pickAlgo.getBtnRadioHaut().isSelected()){
                isHill = true;
                hillKey1 = RandomKeyGenerator.getCesarKey();
                hillKey2 = RandomKeyGenerator.getCesarKey();
                hillKey3 = RandomKeyGenerator.getCesarKey();
                hillKey4 = RandomKeyGenerator.getCesarKey();
                algoC = new Hill(hillKey1,hillKey2,hillKey3,hillKey4);

            }
        }
        if(pickAlgo.getBtnRadioLent().isSelected()){
            if(pickAlgo.getBtnRadioGrand().isSelected() || pickAlgo.getBtnRadioNormal().isSelected()){
                isDes = true;
                desKey = RandomKeyGenerator.getDESKey();
                algoC = new DES(desKey);
            }

            if(pickAlgo.getBtnRadioPetit().isSelected()){
                isAffine = true;
                affineKey1 = RandomKeyGenerator.getCesarKey();
                affineKey2 = RandomKeyGenerator.getCesarKey();
                algoC = new Affine(affineKey1,affineKey2);
            }

        }
            crypter();

        /*if(isClickerDecrypter){
            decrypter();
        }*/
    }

    /*private void pickAlgorithmeDecrypter(){
        if (pickAlgo.getBtnRadioRapide().isSelected()){
            if (pickAlgo.getBtnRadioFaible().isSelected()){
                algoC = new Cesar(cesarKey);
            }
            if (pickAlgo.getBtnRadioMoyen().isSelected()){
                algoC = new Transposition(transpositionKey);
            }
            if(pickAlgo.getBtnRadioHaut().isSelected()){
                algoC = algoC = new Hill(hillKey1,hillKey2,hillKey3,hillKey4);

            }
        }
        if(pickAlgo.getBtnRadioLent().isSelected()){
            if(pickAlgo.getBtnRadioGrand().isSelected() || pickAlgo.getBtnRadioNormal().isSelected()){
                algoC = new DES(RandomKeyGenerator.getDESKey());
            }

            if(pickAlgo.getBtnRadioPetit().isSelected()){
                algoC = new Affine(RandomKeyGenerator.getCesarKey(),RandomKeyGenerator.getCesarKey());
            }

        }

        if (isClickerCrypter){
            crypter();
        }
        /*if(isClickerDecrypter){
            decrypter();
        }*
    }
    /*private void giveActionToBtnValide(){
        if (isClickerCrypter){
            crypter();
        }
        if(isClickerDecrypter){
            decrypter();
        }
    }*/

    private void crypter(){
        outTextArea.setText(algoC.crypter(outTextArea.getText().toString()));
    }
    private void decrypterText(){
        outTextArea.setText(algoC.decrypter(outTextArea.getText().toString()));
    }

    // -------------------------------------------------------------------------------------------------------------------


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

    private void dialogExport(){
        BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialogExport = new JFXDialog(Main.getConteneurPrincipale() ,dialogLayout, JFXDialog.DialogTransition.BOTTOM);
        JFXButton btnFermer = new JFXButton("Fermer");
        btnFermer.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent mouseEvent) -> {

            userSimplePane.setEffect(null);
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
        userSimplePane.setEffect(blur);

    }

    private void dialogPickAlgo(){
        BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialogPick = new JFXDialog(Main.getConteneurPrincipale() ,dialogLayout, JFXDialog.DialogTransition.BOTTOM);
        JFXButton btnFermer = new JFXButton("Fermer");
        btnFermer.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent mouseEvent) -> {

            userSimplePane.setEffect(null);
            dialogPick.close();
            btnShowKeys.setDisable(false);
            makeShowKeys();
        });
        loadPickAlgo();
        dialogLayout.setBody(pickPane);
        dialogLayout.setPrefSize(246, 312);
        dialogLayout.setStyle("-fx-background-color : #34495e;");

        btnFermer.setButtonType(JFXButton.ButtonType.RAISED);
        btnFermer.setStyle("-jfx-rippler-fill: #2c3e50;" +
                "-fx-background-color : #3498db;" +
                "-fx-text-fill:#fff;");
        ///dialogLayoutPropo.setPrefSize(450 , 250);
        dialogLayout.setActions(btnFermer);
        pickAlgo.getBtnValider().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(pickAlgo.selectedKeySize() && pickAlgo.selectedSecurity() && pickAlgo.selectedSpeed()){
                    pickAlgorithme();
                    dialogShowKeys();
                }else{
                    Alert probleme = new Alert(Alert.AlertType.ERROR);
                    probleme.setTitle("Erreur");
                    probleme.setHeaderText("Veuillez séléctionner votre choix pour chaque section!!! ");
                    probleme.showAndWait();
                }
            }
        });

        dialogPick.show();
        userSimplePane.setEffect(blur);

    }

    private void dialogShowKeys(){
        //BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayoutPropo = new JFXDialogLayout();
        JFXDialog dialogShowKeys = new JFXDialog(Main.getConteneurPrincipale() ,dialogLayoutPropo, JFXDialog.DialogTransition.BOTTOM);
        JFXButton btnFermer = new JFXButton("Fermer");
        btnFermer.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent mouseEvent) -> {
            //userParentPane.setEffect(null);
            dialogShowKeys.close();
        });
        loadShowKeys();
        dialogLayoutPropo.setPrefSize(400,510);
        dialogLayoutPropo.setStyle("-fx-background-color : #34495e;");
        dialogLayoutPropo.setBody(showKeysPane);
        initialeShowKeys();

        btnFermer.setButtonType(JFXButton.ButtonType.RAISED);
        btnFermer.setStyle("-jfx-rippler-fill: #2c3e50;" +
                "-fx-background-color : #3498db;" +
                "-fx-text-fill:#fff;");
        ///dialogLayoutPropo.setPrefSize(450 , 250);
        dialogLayoutPropo.setActions(btnFermer);

        dialogShowKeys.show();
        //userParentPane.setEffect(blur);



    }


    private void dialogDecrypter(){
        BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayoutPropo = new JFXDialogLayout();
        JFXDialog dialogDecryptage = new JFXDialog(Main.getConteneurPrincipale() ,dialogLayoutPropo, JFXDialog.DialogTransition.BOTTOM);
        JFXButton btnFermer = new JFXButton("Fermer");
        btnFermer.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent mouseEvent) -> {
            userSimplePane.setEffect(null);
            dialogDecryptage.close();
        });
        loadDecryptage();
        showChamps();
        dialogLayoutPropo.setPrefSize(400,510);
        dialogLayoutPropo.setStyle("-fx-background-color : #34495e;");
        dialogLayoutPropo.setBody(decryptagePane);

        decryptageIns.getBtnValider().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (isAffine){
                    if (decryptageIns.getC1().getText().isEmpty() || decryptageIns.getC2().getText().isEmpty()){
                        Alert probleme = new Alert(Alert.AlertType.ERROR);
                        probleme.setTitle("Erreur");
                        probleme.setHeaderText("Veuillez saisir les clés!!! ");
                        probleme.showAndWait();
                    }else{
                        if(!decryptageIns.getC1().getText().equals(String.valueOf(affineKey1)) || !decryptageIns.getC2().getText().equals(String.valueOf(affineKey2))){
                            Alert probleme = new Alert(Alert.AlertType.ERROR);
                            probleme.setTitle("Erreur");
                            probleme.setHeaderText("Veuillez saisir les bonnes clés!!! ");
                            probleme.showAndWait();
                        }else{
                            decrypterText();
                        }
                    }
                }else if (isHill) {
                    if (decryptageIns.getC1().getText().isEmpty() || decryptageIns.getC2().getText().isEmpty()
                           || decryptageIns.getC3().getText().isEmpty() || decryptageIns.getC4().getText().isEmpty() ){
                        Alert probleme = new Alert(Alert.AlertType.ERROR);
                        probleme.setTitle("Erreur");
                        probleme.setHeaderText("Veuillez saisir les clés!!! ");
                        probleme.showAndWait();
                    }else{
                        if(!decryptageIns.getC1().getText().equals(String.valueOf(hillKey1)) || !decryptageIns.getC2().getText().equals(String.valueOf(hillKey2))
                           || !decryptageIns.getC3().getText().equals(String.valueOf(hillKey3))  || !decryptageIns.getC4().getText().equals(String.valueOf(hillKey4)) ){
                            Alert probleme = new Alert(Alert.AlertType.ERROR);
                            probleme.setTitle("Erreur");
                            probleme.setHeaderText("Veuillez saisir les bonnes clés!!! ");
                            probleme.showAndWait();
                        }else{
                            decrypterText();
                        }
                    }
                }else{
                    if(decryptageIns.getC1().getText().isEmpty()){
                        Alert probleme = new Alert(Alert.AlertType.ERROR);
                        probleme.setTitle("Erreur");
                        probleme.setHeaderText("Veuillez saisir la clé!!! ");
                        probleme.showAndWait();
                    }else {

                        if(isCesar){
                            if(decryptageIns.getC1().getText().isEmpty()){
                                Alert probleme = new Alert(Alert.AlertType.ERROR);
                                probleme.setTitle("Erreur");
                                probleme.setHeaderText("Veuillez saisir la clé !!! ");
                                probleme.showAndWait();
                            }else {
                                if(!decryptageIns.getC1().getText().equals(String.valueOf(cesarKey))){
                                    Alert probleme = new Alert(Alert.AlertType.ERROR);
                                    probleme.setTitle("Erreur");
                                    probleme.setHeaderText("Veuillez saisir la bonne clé !!! ");
                                    probleme.showAndWait();
                                }else{
                                    decrypterText();
                                }
                            }
                        }

                        if(isDes){
                            if(decryptageIns.getC1().getText().isEmpty()){
                                Alert probleme = new Alert(Alert.AlertType.ERROR);
                                probleme.setTitle("Erreur");
                                probleme.setHeaderText("Veuillez saisir la clé !!! ");
                                probleme.showAndWait();
                            }else {
                                if(!decryptageIns.getC1().getText().equals(desKey)){
                                    Alert probleme = new Alert(Alert.AlertType.ERROR);
                                    probleme.setTitle("Erreur");
                                    probleme.setHeaderText("Veuillez saisir la bonne clé !!! ");
                                    probleme.showAndWait();
                                }else{
                                    decrypterText();
                                }

                            }
                        }

                        if(isTransposition){
                            if(decryptageIns.getC1().getText().isEmpty()){
                                Alert probleme = new Alert(Alert.AlertType.ERROR);
                                probleme.setTitle("Erreur");
                                probleme.setHeaderText("Veuillez saisir les clés!!! ");
                                probleme.showAndWait();
                            }else {
                                if(!decryptageIns.getC1().getText().equals(String.valueOf(transpositionKey))){
                                    Alert probleme = new Alert(Alert.AlertType.ERROR);
                                    probleme.setTitle("Erreur");
                                    probleme.setHeaderText("Veuillez saisir la bonne clé !!! ");
                                    probleme.showAndWait();
                                }else {
                                    decrypterText();

                                }

                            }
                        }

                    }
                }
            }
        });
       // initialeShowKeys();



        btnFermer.setButtonType(JFXButton.ButtonType.RAISED);
        btnFermer.setStyle("-jfx-rippler-fill: #2c3e50;" +
                "-fx-background-color : #3498db;" +
                "-fx-text-fill:#fff;");
        ///dialogLayoutPropo.setPrefSize(450 , 250);
        dialogLayoutPropo.setActions(btnFermer);

        dialogDecryptage.show();
        userSimplePane.setEffect(blur);



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

    private void loadPickAlgo() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("pickMyAlgo.fxml"));

        try {
            pickPane = loader.load();
            pickAlgo = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private void loadShowKeys() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("ShowKeys.fxml"));

        try {
            showKeysPane = loader.load();
            showKeys = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private void loadDecryptage() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("DecrypterSimpleUser.fxml"));

        try {
            decryptagePane = loader.load();
            decryptageIns = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void initialeShowKeys(){
        if(isHill){
            stringBuffer = new StringBuffer();
            stringBuffer.append("clé 1 : "+hillKey1 + "\n");
            //stringBuffer.append("\n-----\n");
            stringBuffer.append("clé 2 : "+hillKey2 + " \n");
            stringBuffer.append("clé 3 : "+hillKey3 + "\n");
            stringBuffer.append("clé 4 : "+hillKey4 + "\n");
            showKeys.setOutTextKeys(stringBuffer.toString());
            showKeys.setOutTextNameAlgo("Hill");
        }
        if (isCesar){
            stringBuffer = new StringBuffer();
            stringBuffer.append("clé 1 : "+cesarKey + "\n");

            showKeys.setOutTextKeys(stringBuffer.toString());
            showKeys.setOutTextNameAlgo("Cesar");
        }

        if (isTransposition){
            stringBuffer = new StringBuffer();
            stringBuffer.append("clé 1 : "+transpositionKey + "\n");

            showKeys.setOutTextKeys(stringBuffer.toString());
            showKeys.setOutTextNameAlgo("Transposition");
        }
        if (isAffine){
            stringBuffer = new StringBuffer();
            stringBuffer.append("clé 1 : "+affineKey1 + "\n");
            stringBuffer.append("clé 2 : "+affineKey2 + "\n");

            showKeys.setOutTextKeys(stringBuffer.toString());
            showKeys.setOutTextNameAlgo("Affine");
        }
        if (isDes){
            stringBuffer = new StringBuffer();
            stringBuffer.append("clé 1 : "+desKey+ "\n");


            showKeys.setOutTextKeys(stringBuffer.toString());
            showKeys.setOutTextNameAlgo("DES");
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

    private void makeShowKeys(){
        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(500));
        ft.setNode(btnShowKeys);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                btnShowKeys.setOpacity(1);

            }
        });
        ft.play();
    }

    private void showChamps(){
        if(isHill){
            decryptageIns.getC2().setDisable(false);
            decryptageIns.makeAppearField2();
            decryptageIns.getC3().setDisable(false);
            decryptageIns.makeAppearField3();
            decryptageIns.getC4().setDisable(false);
            decryptageIns.makeAppearField4();
        }

        if(isAffine){
            decryptageIns.getC2().setDisable(false);
            decryptageIns.makeAppearField2();
        }
    }


}
