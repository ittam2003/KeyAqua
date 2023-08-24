package com.calmat.keyaqua.ui;

import com.calmat.keyaqua.Logic.Database;
import com.calmat.keyaqua.Logic.User;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LockedController implements Initializable {



    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Label errorMessage;

    public void password(){
        if (!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()){
            User user = new User(usernameField.getText(), passwordField.getText());
            Database database = new Database();
            if (database.checkUser(user)){
                try {
                    database.setActiveUser(user.getUsername());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                unlock();
            }
        }else {
            errorMessage.setText("Wrong username/password...");
        }
    }

    public void unlock() {
        KeyAquaApplication m = new KeyAquaApplication();
        try {
            m.changeScene("keyMenu.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private ImageView secureIcon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(secureIcon);
        translate.setDuration(Duration.millis(3000));
        translate.setCycleCount(Animation.INDEFINITE);
        translate.setByY(-15);
        translate.setAutoReverse(true);
        translate.play();
        FadeTransition fade = new FadeTransition();
        fade.setNode(secureIcon);
        fade.setDuration(Duration.millis(2000));
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

    }
}
