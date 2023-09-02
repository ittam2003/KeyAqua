package com.calmat.gitLocker.ui;

import com.calmat.gitLocker.Logic.Database;
import com.calmat.gitLocker.Logic.User;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * The type Locked controller.
 */
public class LockedController implements Initializable {



    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Label errorMessage;

    /**
     * Password.
     */
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
            }else {
                errorMessage.setText("Wrong username/password...");
            }
        }else {
            errorMessage.setText("Please fill all fields");
        }
    }

    /**
     * Create new user.
     */
    public void createNewUser(){
        KeyAquaApplication m = new KeyAquaApplication();
        try {
            m.changeScene("newUserMenu.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Forgotten password.
     */
    public void forgottenPassword(){
        KeyAquaApplication m = new KeyAquaApplication();
        try {
            m.changeScene("resetPasswordMenu.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Unlock.
     */
    public void unlock() {
        KeyAquaApplication m = new KeyAquaApplication();
        try {
            m.changeScene("keyMenu.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private MediaView mediaPlayer;

    /**
     * Gets loading video.
     */
    public void getLoadingVideo() {

        Media media = new Media(
                Objects.requireNonNull(getClass().getResource("/com/calmat/keyaqua/media/background.mp4"))
                        .toString());
        MediaPlayer player = new MediaPlayer(media);
        mediaPlayer.setMediaPlayer(player);
        player.setOnEndOfMedia(() -> {
            player.seek(Duration.ZERO);
        });
        player.play();

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
        getLoadingVideo();

    }
}
