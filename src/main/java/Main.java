import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    double x;
    private int winwWidth = 800;
    private int winHeight = 600;
    private Canvas canvas = new Canvas(winwWidth, winHeight);

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

        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        final long startNanoTime = System.nanoTime();

        Gallina gallina = new Gallina();
        Image[] imageArray = new Image[2];
        imageArray[0] = new Image("gallina_2.png");
        imageArray[1] = new Image("gallina_1.png");

        gallina.frames = imageArray;
        gallina.duration = 0.100;

        Image centro = new Image("ardillaCen160.png");
        double imgWidth = centro.getWidth();
        double imgHeight = centro.getHeight();

        Image izq = new Image("ardillaIzq160.png");
        Image der = new Image("ardillaDer160.png");

        double posicionXArdilla = (winwWidth / 2.0) - (imgWidth / 2.0);
        double posicionYArdilla = winHeight - imgHeight;

        ArrayList<String> input = new ArrayList<String>();

        theScene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();

                    // only add once... prevent duplicates
                    if ( !input.contains(code) )
                        input.add( code );
                });

        theScene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove( code );
                });

        new AnimationTimer() {
            @Override
            public void handle(long l) {
                gc.clearRect(0, 0, 800, 600);

                if (input.contains("LEFT"))
                    gc.drawImage(izq, posicionXArdilla, posicionYArdilla);
                else if (input.contains("RIGHT"))
                    gc.drawImage(der, posicionXArdilla, posicionYArdilla);
                else
                    gc.drawImage(centro, posicionXArdilla, posicionYArdilla);

                double t = (l - startNanoTime) / 1000000000.0;
                gc.drawImage( gallina.getFrame(t), x, 0);
                x+=0.5;
            }
        }.start();

        stage.show();
    }
}
