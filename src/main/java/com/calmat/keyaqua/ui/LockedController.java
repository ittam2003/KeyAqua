package com.calmat.keyaqua.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class LockedController {



    @FXML
    private PasswordField passwordField;

    @FXML
    private Button passwordButton;

    @FXML
    private Label errorMessage;

    public void password(){
        String password;
        File file5 = new File("Keys/password.dat");
        try (BufferedReader reader = new BufferedReader(new FileReader(file5))) {
            password = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String keyText = passwordField.getText();
        if (keyText.equals(password) | password == null){
            unlock();
        } else {
            errorMessage.setText("Wrong password...");
        }
    }

    public void unlock() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/calmat/keyaqua/KeyMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,1000, 650);
            scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/com/calmat/keyaqua/themes/theme.css")).toExternalForm());
            Stage stage = (Stage) passwordButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
