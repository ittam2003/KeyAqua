package com.example.keyaqua.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;


public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/keyaqua/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("KeyAqua");
        stage.setScene(scene);
        stage.setResizable(false);
        scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/com/example/keyaqua/theme.css")).toExternalForm());
        stage.getIcons().add(new Image("/com/example/keyaqua/6676583.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}