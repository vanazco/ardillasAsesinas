import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
    double x;


    public static void main(String[] args)
    {
        launch(args);
    }


    @Override
    public void start(Stage stage) {

        stage.setTitle( "Ez clap" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        stage.setScene( theScene );

        Canvas canvas = new Canvas( 800, 600 );
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        final long startNanoTime = System.nanoTime();

        Gallina gallina = new Gallina();
        Image[] imageArray = new Image[2];
        imageArray[0] = new Image("gallina_0.png");
        imageArray[1] = new Image("gallina_1.png");
        gallina.frames = imageArray;
        gallina.duration = 0.100;

        new AnimationTimer() {
            @Override
            public void handle(long l) {
                gc.clearRect(0, 0, 800, 600);
                double t = (l - startNanoTime) / 1000000000.0;
                gc.drawImage( gallina.getFrame(t), x, 0);
                x+=0.5;
            }
        }.start();

        stage.show();
    }
}
