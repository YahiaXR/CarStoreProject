package CarStore;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class carStoreGUI extends Application {

    @Override
    public void start(Stage primaryStage) {

        Label welcomeLabel = new Label("See it's working yay");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Label statusLabel = new Label("Hello, world");
        statusLabel.setStyle("-fx-text-fill: green;");

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(welcomeLabel, statusLabel);

        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("ayo!!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}