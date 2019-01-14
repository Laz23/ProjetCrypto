package sample;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class DecrypterSimpleUser implements Initializable {

    @FXML
    private TextField c1;

    @FXML
    private TextField c2;

    @FXML
    private TextField c3;

    @FXML
    private TextField c4;

    @FXML
    private JFXButton btnValider;

    private int nbr ;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        c2.setOpacity(0);
        c3.setOpacity(0);
        c4.setOpacity(0);
        c2.setDisable(true);
        c3.setDisable(true);
        c4.setDisable(true);
        }

    public TextField getC1(){
        return c1;
    }
    public TextField getC2(){
        return c2;
    }
    public TextField getC3(){
        return c3;
    }
    public TextField getC4(){
        return c4;
    }

    public JFXButton getBtnValider(){
        return btnValider;
    }



    public void makeAppearField2(){

            FadeTransition ft = new FadeTransition();
            ft.setDuration(Duration.millis(500));
            ft.setNode(c2);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    c2.setOpacity(1);

                }
            });
            ft.play();
    }
    public void makeAppearField3(){
            FadeTransition ft = new FadeTransition();
            ft.setDuration(Duration.millis(500));
            ft.setNode(c3);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    c3.setOpacity(1);

                }
            });
            ft.play();

    }
    public void makeAppearField4(){
            FadeTransition ft = new FadeTransition();
            ft.setDuration(Duration.millis(500));
            ft.setNode(c4);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    c4.setOpacity(1);

                }
            });
            ft.play();

    }

}
