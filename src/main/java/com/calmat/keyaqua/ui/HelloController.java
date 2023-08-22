package com.calmat.keyaqua.ui;

import com.calmat.keyaqua.Logic.Database;

import java.io.*;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
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

public class HelloController implements Initializable {

    private String key1String;
    private String key2String;
    private String key3String;
    private String key4String;
    private String key5String;
    @FXML
    private Label key1;
    @FXML
    private Label key2;
    @FXML
    private Label key3;
    @FXML
    private Label key4;
    @FXML
    private Label key5;

    //-fx-background-color:  linear-gradient(from 0.0% 0.0% to 100.0% 0.0%, #382ea9 0.0%, #6d51d0 100.0%);

    public void copy1(){
        File file = new File("Keys/key1.dat");
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                key1String = reader.readLine().split("-")[1];
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(key1String);
                clipboard.setContent(content);
            } catch (ArrayIndexOutOfBoundsException e) {
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString("");
                clipboard.setContent(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void copy2(){
        File file = new File("Keys/key2.dat");
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                key2String = reader.readLine().split("-")[1];
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(key2String);
                clipboard.setContent(content);
            } catch (ArrayIndexOutOfBoundsException e) {
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString("");
                clipboard.setContent(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void copy3(){
        File file = new File("Keys/key3.dat");
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                key3String = reader.readLine().split("-")[1];
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(key3String);
                clipboard.setContent(content);
            } catch (ArrayIndexOutOfBoundsException e) {
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString("");
                clipboard.setContent(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void copy4(){
        File file = new File("Keys/key4.dat");
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                key4String = reader.readLine().split("-")[1];
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(key4String);
                clipboard.setContent(content);
            } catch (ArrayIndexOutOfBoundsException e) {
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString("");
                clipboard.setContent(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void copy5(){
        File file = new File("Keys/key5.dat");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            key5String = reader.readLine().split("-")[1];
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(key5String);
            clipboard.setContent(content);
        } catch (ArrayIndexOutOfBoundsException e) {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString("");
            clipboard.setContent(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
            String keyText = nameTextField.getText() + "-" + keyTextField.getText();

            Database database = new Database("key1");
            try {
                database.writeKeyToFile(keyText);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            refresh();
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

    public void addKey2(ActionEvent event) throws IOException {
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
            String keyText = nameTextField.getText() + "-" + keyTextField.getText();

            Database database = new Database("key2");
            try {
                database.writeKeyToFile(keyText);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            refresh();
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

    public void addKey3(ActionEvent event) throws IOException {
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
            String keyText = nameTextField.getText() + "-" + keyTextField.getText();

            Database database = new Database("key3");
            try {
                database.writeKeyToFile(keyText);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            refresh();
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

    public void addKey4(ActionEvent event) throws IOException {
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
            String keyText = nameTextField.getText() + "-" + keyTextField.getText();

            Database database = new Database("key4");
            try {
                database.writeKeyToFile(keyText);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            refresh();
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

    public void addKey5(ActionEvent event) throws IOException {
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
            String keyText = "No key";
            if(nameTextField.getText() != null | keyTextField.getText() != null){
                keyText = nameTextField.getText() + "-" + keyTextField.getText();
            }
            Database database = new Database("key5");
            try {
                database.writeKeyToFile(keyText);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            refresh();
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

    public void clearKey1(ActionEvent event) throws IOException {
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

        // Handle the user's choice
        if (result.isPresent() && result.get() == buttonTypeYes) {
            Database database = new Database("key1");
            try {
                database.writeKeyToFile("No Key");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            refresh();
        } else if (result.isPresent() && result.get() == buttonTypeNo) {
            refresh();
        }
    }

    public void clearKey2(ActionEvent event) throws IOException {
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

        // Handle the user's choice
        if (result.isPresent() && result.get() == buttonTypeYes) {
            Database database = new Database("key2");
            try {
                database.writeKeyToFile("No Key");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            refresh();
        } else if (result.isPresent() && result.get() == buttonTypeNo) {
            refresh();
        }
    }

    public void clearKey3(ActionEvent event) throws IOException {
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

        // Handle the user's choice
        if (result.isPresent() && result.get() == buttonTypeYes) {
            Database database = new Database("key3");
            try {
                database.writeKeyToFile("No Key");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            refresh();
        } else if (result.isPresent() && result.get() == buttonTypeNo) {
            refresh();
        }
    }

    public void clearKey4(ActionEvent event) throws IOException {
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

        // Handle the user's choice
        if (result.isPresent() && result.get() == buttonTypeYes) {
            Database database = new Database("key4");
            try {
                database.writeKeyToFile("No Key");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            refresh();
        } else if (result.isPresent() && result.get() == buttonTypeNo) {
            refresh();
        }
    }

    public void clearKey5(ActionEvent event) throws IOException {
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

        // Handle the user's choice
        if (result.isPresent() && result.get() == buttonTypeYes) {
            Database database = new Database("key5");
            try {
                database.writeKeyToFile("No Key");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            refresh();
        } else if (result.isPresent() && result.get() == buttonTypeNo) {
            refresh();
        }
    }

    public void refresh(){
        File file1 = new File("Keys/key1.dat");
        try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
            key1String = reader.readLine().split("-")[0];
            key1.setText(Objects.requireNonNullElse(key1String, "No Key"));
        } catch (ArrayIndexOutOfBoundsException e) {
            key1.setText("No Key");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File file2 = new File("Keys/key2.dat");
        try (BufferedReader reader = new BufferedReader(new FileReader(file2))) {
            key2String = reader.readLine().split("-")[0];
            key2.setText(Objects.requireNonNullElse(key2String, "No Key"));
        } catch (ArrayIndexOutOfBoundsException e) {
            key2.setText("No Key");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File file3 = new File("Keys/key3.dat");
        try (BufferedReader reader = new BufferedReader(new FileReader(file3))) {
            key3String = reader.readLine().split("-")[0];
            key3.setText(Objects.requireNonNullElse(key3String, "No Key"));
        } catch (ArrayIndexOutOfBoundsException e) {
            key3.setText("No Key");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File file4 = new File("Keys/key4.dat");
        try (BufferedReader reader = new BufferedReader(new FileReader(file4))) {
            key4String = reader.readLine().split("-")[0];
            key4.setText(Objects.requireNonNullElse(key4String, "No Key"));
        } catch (ArrayIndexOutOfBoundsException e) {
            key4.setText("No Key");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File file5 = new File("Keys/key5.dat");
        try (BufferedReader reader = new BufferedReader(new FileReader(file5))) {
            key5String = reader.readLine().split("-")[0];
            key5.setText(Objects.requireNonNullElse(key5String, "No Key"));
        } catch (ArrayIndexOutOfBoundsException e) {
            key5.setText("No Key");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void aboutPage(){
        Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
        ImageView logo = new ImageView("/com/calmat/keyaqua/images/6676583.png");
        logo.setFitWidth(100);
        logo.setFitHeight(100);
        alertDialog.setGraphic(logo);
        alertDialog.setTitle("About");
        alertDialog.setHeaderText("Version 0.0.4");
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


        // Create a button to submit the form
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event1 -> {
            String password;
            File file5 = new File("Keys/password.dat");
            try (BufferedReader reader = new BufferedReader(new FileReader(file5))) {
                password = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String keyText = passwordField.getText();
            String newKeyText = newPasswordField.getText();
            if (keyText.equals(password) | password == null){
                Database database = new Database("password");
                try {
                    database.writeKeyToFile(newKeyText);
                } catch (IOException e) {
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

    @FXML
    private Button lockButton;

    public void lock() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/calmat/keyaqua/lockedKeyMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,1000, 650);
            scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/com/calmat/keyaqua/themes/theme.css")).toExternalForm());
            Stage stage = (Stage) lockButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();

    }
}