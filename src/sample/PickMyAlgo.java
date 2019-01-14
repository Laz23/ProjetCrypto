package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PickMyAlgo implements Initializable {




    @FXML
    private BorderPane pickPane;


    @FXML
    private JFXButton btnValider;

    @FXML
    private JFXRadioButton btnRadioRapide;



    @FXML
    private JFXRadioButton btnRadioLent;

    @FXML
    private JFXRadioButton btnRadioMoyen;


    @FXML
    private JFXRadioButton btnRadioHaut;

    @FXML
    private JFXRadioButton btnRadioFaible;

    @FXML
    private JFXRadioButton btnRadioPetit;



    @FXML
    private JFXRadioButton btnRadioGrand;

    @FXML
    private JFXRadioButton btnRadioNormal;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    /*private void loadExport() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("ExportFile.fxml"));

        //exportFilePane = loader.load();
        //exportFile = loader.getController();


    }*/

    public boolean selectedSpeed(){
        return (btnRadioRapide.isSelected() || btnRadioLent.isSelected()) ;
    }

    public boolean selectedSecurity(){
        return (btnRadioFaible.isSelected() || btnRadioMoyen.isSelected() || btnRadioHaut.isSelected());
    }

    public boolean selectedKeySize(){
        return (btnRadioPetit.isSelected() || btnRadioNormal.isSelected() || btnRadioGrand.isSelected());
    }


    public BorderPane getPickPane() {
        return pickPane;
    }

    public JFXButton getBtnValider() {
        return btnValider;
    }


    public JFXRadioButton getBtnRadioFaible() {
        return btnRadioFaible;
    }

    public JFXRadioButton getBtnRadioGrand() {
        return btnRadioGrand;
    }

    public JFXRadioButton getBtnRadioHaut() {
        return btnRadioHaut;
    }

    public JFXRadioButton getBtnRadioLent() {
        return btnRadioLent;
    }

    public JFXRadioButton getBtnRadioMoyen() {
        return btnRadioMoyen;
    }

    public JFXRadioButton getBtnRadioNormal() {
        return btnRadioNormal;
    }

    public JFXRadioButton getBtnRadioPetit() {
        return btnRadioPetit;
    }

    public JFXRadioButton getBtnRadioRapide() {
        return btnRadioRapide;
    }
}
