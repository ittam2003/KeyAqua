package com.calmat.keyaqua.ui;

import com.calmat.keyaqua.Logic.Database;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import com.calmat.keyaqua.Logic.Key;
import com.calmat.keyaqua.Logic.KeyChain;
import com.calmat.keyaqua.fileHandling.FileToKey;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class KeyMenuController implements Initializable {

    //-fx-background-color:  linear-gradient(from 0.0% 0.0% to 100.0% 0.0%, #382ea9 0.0%, #6d51d0 100.0%);

    @FXML
    private ListView<String> keyList;

    private Key selectedKey;

    @FXML
    private Label selectedKeyLabel;


    public void populateList() throws Exception {
        Database database = new Database();
        KeyChain keychain = database.loadKeys();

        ObservableList<String> observableKeyList = FXCollections.observableArrayList(keychain.getKeyNames());
        keyList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedKey = keychain.retrieveKeyFromName(newValue);
                selectedKeyLabel.setText(newValue);
            }
        });

        keyList.setItems(observableKeyList);
    }

    public void addKey(ActionEvent event) throws IOException {
        // Create a GridPane to hold the controls
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(300, 150);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        //Add an error message
        Label errorMessage = new Label();
        errorMessage.setText("");
        errorMessage.setTextFill(Color.color(1, 0, 0));
        gridPane.add(errorMessage, 1, 0);

        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Name");
        gridPane.add(new Label("Name:"), 0, 1);
        gridPane.add(nameTextField, 1, 1);

        TextField keyTextField = new TextField();
        keyTextField.setPromptText("Key");
        gridPane.add(new Label("Key:"), 0, 2);
        gridPane.add(keyTextField, 1, 2);


        // Create a button to submit the form
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event1 -> {
            Key newKey = new Key(nameTextField.getText(), keyTextField.getText());

            Database database = new Database();
            try {
                database.writeKeyToFile(newKey);
                populateList();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            ((Stage) submitButton.getScene().getWindow()).close(); // Close the window
        });


        // Add the button to the GridPane
        gridPane.add(submitButton, 1, 6);

        // Create a new Scene with the GridPane as the root node
        Scene scene = new Scene(gridPane);

        // Create a new Stage to hold the Scene
        Stage popupStage = new Stage();
        popupStage.setScene(scene);

        // Set the title of the Stage and show it
        popupStage.setTitle("Add activity");
        popupStage.setResizable(false);
        popupStage.show();
    }

    public void deleteKey(ActionEvent event) throws IOException {
        Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
        ImageView logo = new ImageView("/com/calmat/keyaqua/images/clear.png");
        logo.setFitWidth(100);
        logo.setFitHeight(100);
        alertDialog.setGraphic(logo);
        alertDialog.setTitle("Warning");
        alertDialog.setHeaderText("Are you sure you want to delete this key?");
        // Create buttons for the alert
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        alertDialog.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        DialogPane dialogPane = alertDialog.getDialogPane();
        dialogPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/calmat/keyaqua/themes/alert.css")).toExternalForm());
        dialogPane.getStyleClass().add("custom-alert-dialog");
        alertDialog.getDialogPane().lookup(".header-panel").setStyle("-fx-background-color: #3d3d3d;");


        // Show the alert and wait for the user's response
        Optional<ButtonType> result = alertDialog.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYes) {
            Database database = new Database();
            try {
                database.deleteKey(selectedKey);
                populateList();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (result.isPresent() && result.get() == buttonTypeNo) {
            try {
                populateList();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }

    public void copy(){
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(selectedKey.getKey());
        clipboard.setContent(content);
    }

    public void aboutPage(){
        Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
        ImageView logo = new ImageView("/com/calmat/keyaqua/images/6676583.png");
        logo.setFitWidth(100);
        logo.setFitHeight(100);
        alertDialog.setGraphic(logo);
        alertDialog.setTitle("About");
        alertDialog.setHeaderText("Version 0.0.5");
        alertDialog.setContentText("Created by Calm Matt");
        DialogPane dialogPane = alertDialog.getDialogPane();
        dialogPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/calmat/keyaqua/themes/alert.css")).toExternalForm());
        dialogPane.getStyleClass().add("custom-alert-dialog");
        alertDialog.getDialogPane().lookup(".header-panel").setStyle("-fx-background-color: #3d3d3d;");
        Optional<ButtonType> respons = alertDialog.showAndWait();
    }

    public void underDevelopment(){
        Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
        ImageView logo = new ImageView("/com/calmat/keyaqua/images/6676583.png");
        logo.setFitWidth(100);
        logo.setFitHeight(100);
        alertDialog.setGraphic(logo);
        alertDialog.setTitle("Unlock");
        alertDialog.setHeaderText("This feature is currently under development");
        alertDialog.setContentText("Check out the git page: https://github.com/mattkje/KeyAqua");
        DialogPane dialogPane = alertDialog.getDialogPane();
        dialogPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/calmat/keyaqua/themes/alert.css")).toExternalForm());
        dialogPane.getStyleClass().add("custom-alert-dialog");
        alertDialog.getDialogPane().lookup(".header-panel").setStyle("-fx-background-color: #3d3d3d;");
        Optional<ButtonType> respons = alertDialog.showAndWait();
    }

    public void changePassword(){
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(300, 150);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        //Add an error message
        Label errorMessage = new Label();
        errorMessage.setText("");
        errorMessage.setTextFill(Color.color(1, 0, 0));
        gridPane.add(errorMessage, 1, 0);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Key");
        gridPane.add(new Label("Old password:"), 0, 1);
        gridPane.add(passwordField, 1, 1);

        PasswordField newPasswordField = new PasswordField();
        newPasswordField.setPromptText("Key");
        gridPane.add(new Label("New password:"), 0, 2);
        gridPane.add(newPasswordField, 1, 2);


        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event1 -> {
            String password;
            File file5 = new File("data/users.dat");
            try (BufferedReader reader = new BufferedReader(new FileReader(file5))) {
                password = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String keyText = passwordField.getText();
            String newKeyText = newPasswordField.getText();
            if (keyText.equals(password) | password == null){
                Database database = new Database();
                try {
                    database.writeKeyToFile(new Key("",""));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                ((Stage) submitButton.getScene().getWindow()).close(); // Close the window
            } else {
                errorMessage.setText("Wrong password...");
            }
        });

        gridPane.add(submitButton, 1, 6);
        Scene scene = new Scene(gridPane);
        Stage popupStage = new Stage();
        popupStage.setScene(scene);
        popupStage.setTitle("Password");
        popupStage.setResizable(false);
        popupStage.show();
    }

    public void lock() {
        KeyAquaApplication m = new KeyAquaApplication();
        try {
            m.changeScene("lockedKeyMenu.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            populateList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}