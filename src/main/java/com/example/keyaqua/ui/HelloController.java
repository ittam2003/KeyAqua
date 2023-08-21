package com.example.keyaqua.ui;

import com.example.keyaqua.Logic.Database;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
        File file = new File("Keys/key1.txt");
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                key1String = reader.readLine();
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(key1String);
                clipboard.setContent(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void copy2(){
        File file = new File("Keys/key2.txt");
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                key2String = reader.readLine();
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(key2String);
                clipboard.setContent(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void copy3(){
        File file = new File("Keys/key3.txt");
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                key3String = reader.readLine();
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(key3String);
                clipboard.setContent(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void copy4(){
        File file = new File("Keys/key4.txt");
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                key4String = reader.readLine();
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(key4String);
                clipboard.setContent(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void copy5(){
        File file = new File("Keys/key5.txt");
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                key5String = reader.readLine();
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(key5String);
                clipboard.setContent(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
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

        TextField keyTextField = new TextField();
        keyTextField.setPromptText("Key");
        gridPane.add(new Label("Key:"), 0, 1);
        gridPane.add(keyTextField, 1, 1);


        // Create a button to submit the form
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event1 -> {
            String keyText = keyTextField.getText();

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

        TextField keyTextField = new TextField();
        keyTextField.setPromptText("Key");
        gridPane.add(new Label("Key:"), 0, 1);
        gridPane.add(keyTextField, 1, 1);


        // Create a button to submit the form
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event1 -> {
            String keyText = keyTextField.getText();

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

        TextField keyTextField = new TextField();
        keyTextField.setPromptText("Key");
        gridPane.add(new Label("Key:"), 0, 1);
        gridPane.add(keyTextField, 1, 1);


        // Create a button to submit the form
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event1 -> {
            String keyText = keyTextField.getText();

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

        TextField keyTextField = new TextField();
        keyTextField.setPromptText("Key");
        gridPane.add(new Label("Key:"), 0, 1);
        gridPane.add(keyTextField, 1, 1);


        // Create a button to submit the form
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event1 -> {
            String keyText = keyTextField.getText();

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

        TextField keyTextField = new TextField();
        keyTextField.setPromptText("Key");
        gridPane.add(new Label("Key:"), 0, 1);
        gridPane.add(keyTextField, 1, 1);


        // Create a button to submit the form
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event1 -> {
            String keyText = keyTextField.getText();

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
        ImageView logo = new ImageView("/com/example/keyaqua/clear.png");
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
        dialogPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/keyaqua/alert.css")).toExternalForm());
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
        ImageView logo = new ImageView("/com/example/keyaqua/clear.png");
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
        dialogPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/keyaqua/alert.css")).toExternalForm());
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
        ImageView logo = new ImageView("/com/example/keyaqua/clear.png");
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
        dialogPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/keyaqua/alert.css")).toExternalForm());
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
        ImageView logo = new ImageView("/com/example/keyaqua/clear.png");
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
        dialogPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/keyaqua/alert.css")).toExternalForm());
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
        ImageView logo = new ImageView("/com/example/keyaqua/clear.png");
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
        dialogPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/keyaqua/alert.css")).toExternalForm());
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
        File file1 = new File("Keys/key1.txt");
        if (file1 != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
                key1String = reader.readLine();
                if(key1String == null){
                    key1.setText("No Key");
                } else {
                    key1.setText(key1String);
                }
                //key1.setText(key1String);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file2 = new File("Keys/key2.txt");
        if (file2 != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file2))) {
                key2String = reader.readLine();
                if(key2String == null){
                    key2.setText("No Key");
                } else {
                    key2.setText(key2String);
                }
                //key2.setText(key2String);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file3 = new File("Keys/key3.txt");
        if (file3 != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file3))) {
                key3String = reader.readLine();
                if(key3String == null){
                    key3.setText("No Key");
                } else {
                    key3.setText(key3String);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file4 = new File("Keys/key4.txt");
        if (file4 != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file4))) {
                key4String = reader.readLine();
                if(key4String == null){
                    key4.setText("No Key");
                } else {
                    key4.setText(key4String);
                }
                //key4.setText(key4String);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file5 = new File("Keys/key5.txt");
        if (file5 != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file5))) {
                key5String = reader.readLine();
                if(key5String == null){
                    key5.setText("No Key");
                } else {
                    key5.setText(key5String);
                }
                //key5.setText(key5String);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void aboutPage(){
        Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
        ImageView logo = new ImageView("/com/example/keyaqua/6676583.png");
        logo.setFitWidth(100);
        logo.setFitHeight(100);
        alertDialog.setGraphic(logo);
        alertDialog.setTitle("About");
        alertDialog.setHeaderText("Version 0.0.4");
        alertDialog.setContentText("Created by Calm Matt");
        DialogPane dialogPane = alertDialog.getDialogPane();
        dialogPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/keyaqua/alert.css")).toExternalForm());
        dialogPane.getStyleClass().add("custom-alert-dialog");
        alertDialog.getDialogPane().lookup(".header-panel").setStyle("-fx-background-color: #3d3d3d;");
        Optional<ButtonType> respons = alertDialog.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();

    }
}