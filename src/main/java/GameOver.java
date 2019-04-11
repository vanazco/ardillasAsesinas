import com.sun.javafx.geom.Rectangle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

public class GameOver extends Application {

    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        GameOver.primaryStage = primaryStage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        setPrimaryStage(stage);
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameOver.fxml")));

        Scene scene = new Scene(pane, 350, 200);
        stage.setScene(scene);
        stage.setTitle("GAME OVER");
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
