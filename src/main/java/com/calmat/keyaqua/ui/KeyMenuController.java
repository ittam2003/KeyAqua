package com.calmat.keyaqua.ui;

import com.calmat.keyaqua.Logic.Database;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import com.calmat.keyaqua.Logic.Key;
import com.calmat.keyaqua.Logic.KeyChain;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.WHITE;

public class KeyMenuController implements Initializable {

    //-fx-background-color:  linear-gradient(from 0.0% 0.0% to 100.0% 0.0%, #382ea9 0.0%, #6d51d0 100.0%);

    @FXML
    private VBox keyBox;

    private Key selectedKey;

    @FXML
    private Label selectedKeyLabel;

    @FXML
    private VBox optionBox;

    @FXML
    private BorderPane bg;

    @FXML
    private Label activeUserLabel;

    public void populateList() {
        Database database = new Database();
        KeyChain keychain = database.loadKeys();
        List<String> keyList = keychain.getKeyNames();
        keyBox.getChildren().clear();
        activeUserLabel.setText(database.getActiveUserAsString());


        for (int i = 0; i < keyList.size(); i++){
            String keyName = keyList.get(i);
            Button button = new Button();
            button.setId("keyButtons");
            button.setAlignment(Pos.CENTER_LEFT);

            Label keyNameLabel = new Label(keyName);
            Label keyInfo = new Label("Key length: " + keychain.retrieveKeyFromName(keyName).getKey().length());
            keyInfo.setId("keyInfoLabel");
            HBox buttonTextBox = new HBox(keyNameLabel, keyInfo);
            buttonTextBox.setAlignment(Pos.CENTER_LEFT);
            buttonTextBox.setSpacing(30);
            button.setGraphic(buttonTextBox);
            keyBox.getChildren().add(button);

            button.setOnAction(e ->{
                selectedKeyLabel.setText(keyName);
                selectedKey = keychain.retrieveKeyFromName(keyName);
                optionBox.setVisible(true);
            });
        }
    }

    public void addKey(ActionEvent event) throws IOException {
        // Create a GridPane to hold the controls
        VBox addKeyBox = new VBox();
        addKeyBox.setPrefSize(300, 150);
        addKeyBox.setPadding(new Insets(10));

        Label titleLabel = new Label("Add key");
        titleLabel.setId("titleLabelAK");
        titleLabel.setTextFill(Color.color(1, 0, 0));

        //Add an error message
        Label errorMessage = new Label();
        errorMessage.setText("");
        errorMessage.setTextFill(Color.color(1, 0, 0));


        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Name");

        TextField keyTextField = new TextField();
        keyTextField.setPromptText("Key");

        GridPane keyGrid = new GridPane();
        keyGrid.add(new Label("Name:"), 0,0);
        keyGrid.add(nameTextField,1,0);
        keyGrid.add(new Label("Key:"), 0,1);
        keyGrid.add(keyTextField,1,1);
        keyGrid.setHgap(10);
        keyGrid.setVgap(10);

        // Create a button to submit the form
        VBox vBox = new VBox(addKeyBox);
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
            bg.getChildren().remove(vBox);
        });


        addKeyBox.getChildren().addAll(titleLabel, errorMessage, keyGrid, submitButton);
        addKeyBox.setStyle("-fx-spacing: 10; -fx-background-radius: 20; -fx-background-color: #151515");
        addKeyBox.setPrefHeight(2000);

        vBox.setStyle("-fx-padding: 10 10 10 0; -fx-background-color: black");
        bg.setRight(vBox);
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

        ImageView logo = new ImageView("/com/calmat/keyaqua/images/6676583.png");
        logo.setFitWidth(100);
        logo.setFitHeight(100);
        Button collapseButton = new Button("X");
        Label titleLabel = new Label("About");
        Label versionLabel = new Label("Version 0.1.0-beta");
        Label creditLabel = new Label("Created by Calm Matt");

        VBox aboutBox = new VBox(collapseButton, titleLabel, logo, versionLabel, creditLabel);
        aboutBox.setStyle("-fx-padding: 10; -fx-spacing: 10; -fx-background-radius: 20; -fx-background-color: #151515");
        aboutBox.setPrefHeight(2000);
        VBox borderBox = new VBox(aboutBox);
        borderBox.setStyle("-fx-padding: 10 10 10 0; -fx-background-color: black");
        collapseButton.setOnAction(e ->{
            bg.getChildren().remove(borderBox);
        });

        bg.setRight(borderBox);
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
        VBox vBox = new VBox();
        vBox.setPrefSize(300, 150);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));

        //Add an error message
        Label errorMessage = new Label();
        errorMessage.setText("");
        errorMessage.setTextFill(Color.color(1, 0, 0));

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Key");
        Label label = new Label("Old password:");
        HBox hBox = new HBox(label, passwordField);

        PasswordField newPasswordField = new PasswordField();
        newPasswordField.setPromptText("Key");
        HBox hBox2 = new HBox(new Label("New password:"), newPasswordField);

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event1 -> {
            Database database = new Database();
            String keyText = passwordField.getText();
            String newKeyText = newPasswordField.getText();
            if (keyText.equals(database.getActiveUser().getPassword())){
                try {
                    database.writePasswordToFile(newKeyText);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                ((Stage) submitButton.getScene().getWindow()).close(); // Close the window
            } else {
                errorMessage.setText("Wrong password...");
            }
        });

        vBox.getChildren().addAll(errorMessage, hBox, hBox2, submitButton);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox);
        scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/com/calmat/keyaqua/themes/popUp.css")).toExternalForm());
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

    public void genBackupKey(){
        VBox vbox = new VBox();
        vbox.setId("backupKeyBox");
        vbox.setPrefSize(300, 150);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        Text info = new Text("This key can be used to restore your password should you lose it. " +
                "Make sure you copy, and store this key in a safe location");
        info.setFill(WHITE);
        info.setWrappingWidth(300);


        TextField backupKeyText = new TextField();
        backupKeyText.setEditable(false);
        HBox hbox = new HBox(new Label("Generated key:"),backupKeyText);
        hbox.setSpacing(10);


        Button submitButton = new Button("Generate key");
        submitButton.setOnAction(event1 -> {
            Database database = new Database();
            if(database.checkIfKeyExists()){
                vbox.getChildren().remove(hbox);
                info.setText("Key already exists! If you have lost your original key, you will not be able to generate a new one." +
                        " This is for security purposes");
                submitButton.setText("No key was generated");

            } else {
                String backupKey = Database.generatePassword();
                backupKeyText.setText(backupKey);
                try {
                    database.addBackupKey(database.getActiveUserAsString(), backupKey);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        Button closeButton = new Button("Close");
        HBox growBox =  new HBox();
        HBox.setHgrow(growBox, Priority.ALWAYS);
        HBox buttonBox = new HBox(closeButton,growBox, submitButton);
        buttonBox.setSpacing(10);
        vbox.getChildren().addAll(info,hbox,buttonBox);
        Scene scene = new Scene(vbox);
        scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/com/calmat/keyaqua/themes/popUp.css")).toExternalForm());
        Stage popupStage = new Stage();
        popupStage.setScene(scene);
        popupStage.setTitle("Password");
        popupStage.setResizable(false);
        popupStage.show();


        closeButton.setOnAction(event1 -> {
            popupStage.close();
        });

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            populateList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(ActionEvent event) throws IOException {
        Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
        ImageView logo = new ImageView("/com/calmat/keyaqua/images/clear.png");
        logo.setFitWidth(100);
        logo.setFitHeight(100);
        alertDialog.setGraphic(logo);
        alertDialog.setTitle("Warning");
        alertDialog.setHeaderText("Are you sure you want to delete this user?");
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
                database.deleteUserFromFile(database.getActiveUser());
                lock();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (result.isPresent() && result.get() == buttonTypeNo) {
            try {
                alertDialog.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}