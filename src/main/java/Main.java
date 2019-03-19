import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args)
    {
        launch(args);
    }


    @Override
    public void start(Stage stage) {
        int windowWidth = 800;
        int windwowHeight = 600;

        stage.setTitle( "Ez clap" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        stage.setScene( theScene );

        Canvas canvas = new Canvas(windowWidth, windwowHeight);
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image centro = new Image("ardillaCen160.png");

        new AnimationTimer() {
            @Override
            public void handle(long l) {
                gc.clearRect(0, 0, 800, 600);

                gc.drawImage(centro, windowWidth/2.0, windwowHeight/2.0);
            }
        }.start();

        stage.show();

    }
}
