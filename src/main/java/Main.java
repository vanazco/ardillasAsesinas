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
    private double yMirilla;
    private int winwWidth = 800;
    private int winHeight = 600;
    private Canvas canvas = new Canvas(winwWidth, winHeight);
    private boolean Fgallina = false;
    private boolean resetearMirillas;

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

        Ardilla ardilla = new Ardilla();
        ardilla.cargarArdilla();

        final long startNanoTime = System.nanoTime();

        //Gallinas
        Gallina gallina = Gallina.CreateGallina();
        Gallina gallina1 = Gallina.CreateGallina();
        gallina.duration = 0.100;

        Image mirIzq = new Image("mira160.png");
        Image mirDer = new Image("mira160.png");

        //Min del rand para todas las gallinas
        gallina.minrand = 600 - ardilla.getHeight();

        //Randoms de las gallinas
        gallina.numrand = ardilla.getHeight() +(Math.random() * ((gallina.minrand - ardilla.getHeight())+1));
        gallina1.numrand = ardilla.getHeight() +(Math.random() * ((gallina.minrand - ardilla.getHeight())+1));

        double posicionMirIzq = (winwWidth / 2.0) - (mirIzq.getWidth() * 1.75);
        double posicionMirDer = (winwWidth / 2.0) + (mirDer.getWidth() * 0.7);

        yMirilla = ardilla.getX();

        ArrayList<String> input = new ArrayList<>();

        theScene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();

                    // only add once... prevent duplicates
                    if ( !input.contains(code) )
                        input.add(code);
                });

        theScene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove(code);
                });

        new AnimationTimer() {
            @Override
            public void handle(long l) {
                gc.clearRect(0, 0, 800, 600);
                gc.drawImage(mirIzq, posicionMirIzq, yMirilla);
                gc.drawImage(mirDer, posicionMirDer, yMirilla);

                resetearMirillas = ardilla.setImage(input, gc);

                double t = (l - startNanoTime) / 1000000000.0;

                yMirilla -= 0.8;

                //Calcular la posicion de la gallina en la que sale
                gallina.yGallina = (gallina.numrand-gallina.getFrame(t).getHeight());
                gallina1.yGallina = (gallina1.numrand-gallina1.getFrame(t).getHeight());

                if (yMirilla < (0 -mirDer.getHeight()) || resetearMirillas) {
                    yMirilla = ardilla.getY();
                    resetearMirillas = false;
                }
                //Dibujar gallina
                gallina.checkGallina(t, gc, gallina, ardilla.getHeight());
                gallina1.checkGallina(t,gc, gallina1, ardilla.getHeight());
            }
        }.start();

        stage.show();
    }
}
