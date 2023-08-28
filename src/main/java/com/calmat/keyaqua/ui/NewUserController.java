package com.calmat.keyaqua.ui;

import com.calmat.keyaqua.Logic.Database;
import com.calmat.keyaqua.Logic.Key;
import com.calmat.keyaqua.Logic.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NewUserController {

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

    public void logIn(){
        KeyAquaApplication m = new KeyAquaApplication();
        try {
            m.changeScene("lockedKeyMenu.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void returnHome(){
        KeyAquaApplication m = new KeyAquaApplication();
        try {
            m.changeScene("lockedKeyMenu.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
