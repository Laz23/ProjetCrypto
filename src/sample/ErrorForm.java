package sample;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ErrorForm implements Initializable {

    @FXML
    private JFXTextArea textAreaErrorForm;

    public void setTextAreaErrorForm(String str){
        textAreaErrorForm.setText(str);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
