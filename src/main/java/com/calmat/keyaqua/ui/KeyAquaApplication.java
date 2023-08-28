package com.calmat.keyaqua.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;


public class KeyAquaApplication extends Application {

    private static Stage stg;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(KeyAquaApplication.class.getResource("/com/calmat/keyaqua/fxml/lockedKeyMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 650);
        stg = stage;
        stage.setMinHeight(650);
        stage.setScene(scene);
        scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/com/calmat/keyaqua/themes/theme.css")).toExternalForm());
        stage.getIcons().add(new Image("/com/calmat/keyaqua/images/6676583.png"));
        stage.show();
    }

    /**
     * This method changes the fxml file in the same scene.
     * @param fxml name of the requested fxml file.
     * @throws IOException Exception
     */
    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/calmat/keyaqua/fxml/"+ fxml)));
        stg.getScene().setRoot(pane);
    }


    public static void main(String[] args) {
        launch();
    }
}