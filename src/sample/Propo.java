package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class Propo implements Initializable {


    @FXML
    private Circle circleImg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img = new Image(String.valueOf(UserConteneur.class.getResource("../img/freeLogo.jpeg")),false);
        circleImg.setFill(new ImagePattern(img));

    }
}
