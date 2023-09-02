package com.calmat.gitLocker.ui;

import com.calmat.gitLocker.Logic.Database;
import com.calmat.gitLocker.Logic.User;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
 * The type New user controller.
 */
public class NewUserController implements Initializable {

    @FXML
    private PasswordField newPasswordField1;

    @FXML
    private PasswordField newPasswordField2;

    @FXML
    private TextField newUsernameField;

    @FXML
    private Label errorMessage;

    @FXML
    private Button createUserButton;

    @FXML
    private Button returnButton;

    @FXML
    private Button resetPasswordButton;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField backupKeyField;

    /**
     * Create user.
     */
    public void createUser(){
        if( (newPasswordField1.getText().isEmpty() || (newPasswordField2.getText().isEmpty())
                || (newUsernameField.getText().isEmpty()))){
            errorMessage.setText("Please fill all fields");
        } else {
            if (newPasswordField1.getText().equals(newPasswordField2.getText())
                    && (!newUsernameField.getText().isEmpty())){
                User newUser = new User(newUsernameField.getText(), newPasswordField1.getText());

                Database database = new Database();
                try {
                    database.writeUserToFile(newUser);
                    errorMessage.setText("Successfully created User");
                    createUserButton.setDisable(true);
                    createUserButton.setText("User created!");
                    returnButton.setOpacity(1);
                    returnButton.setDisable(false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else
                errorMessage.setText("Passwords does not match.");

        }
    }

    /**
     * Reset password.
     */
    public void resetPassword(){
        if( (newPasswordField1.getText().isEmpty() || (newPasswordField2.getText().isEmpty())
                || (usernameField.getText().isEmpty()) || backupKeyField.getText().isEmpty())){
            errorMessage.setText("Please fill all fields");
        } else {
            if (newPasswordField1.getText().equals(newPasswordField2.getText())
                    && (!usernameField.getText().isEmpty())){
                User newUserPassword = new User(usernameField.getText(), newPasswordField1.getText());

                Database database = new Database();
                String userBackupKey = database.getBackupKey(newUserPassword);
                if (backupKeyField.getText().equals(userBackupKey)){
                    try {
                        database.writeChangesToFile(newUserPassword);
                        errorMessage.setText("New password set successfully");
                        resetPasswordButton.setDisable(true);
                        resetPasswordButton.setText("Password reset!");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else
                    errorMessage.setText("Incorrect backup key");
            } else
                errorMessage.setText("Passwords does not match.");

        }
    }

    /**
     * Log in.
     */
    public void logIn(){
        KeyAquaApplication m = new KeyAquaApplication();
        try {
            m.changeScene("lockedKeyMenu.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return home.
     */
    public void returnHome(){
        KeyAquaApplication m = new KeyAquaApplication();
        try {
            m.changeScene("lockedKeyMenu.fxml");
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
