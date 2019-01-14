package sample;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowKeys implements Initializable {

    @FXML
    private TextField outTextNameAlgo;

    @FXML
    private JFXTextArea outTextKeys;


    public JFXTextArea getOutTextKeys(){
        return outTextKeys;
    }

    public TextField getOutTextNameAlgo(){
        return outTextNameAlgo;
    }
    public void setOutTextNameAlgo(String str){
        outTextNameAlgo.setText(str);
    }

    public void setOutTextKeys(String str){
        outTextKeys.setText(str);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
