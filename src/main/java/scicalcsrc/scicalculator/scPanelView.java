package scicalcsrc.scicalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class scPanelView extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scPanel.fxml"));
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("Scientific Calculator v0.1 alpha");
        stage.getIcons().add(new Image("file:src/main/resources/Images/scLogo.png"));
        stage.setScene(scene);
        stage.show();
    }
}
