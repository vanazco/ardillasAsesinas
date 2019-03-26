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

        final long startNanoTime = System.nanoTime();

        //Gallinas
        Gallina gallina = Gallina.CreateGallina();
        Gallina gallina1 = Gallina.CreateGallina();

        Image centro = new Image("ardillaCen160.png");
        double imgWidth = centro.getWidth();
        double imgHeight = centro.getHeight();

        Image izq = new Image("ardillaIzq160.png");
        Image der = new Image("ardillaDer160.png");
        Image mirIzq = new Image("mira160.png");
        Image mirDer = new Image("mira160.png");

        //Min del rand para todas las gallinas
        gallina.minrand = 600 - imgHeight;

        //Randoms de las gallinas
        gallina.numrand = imgHeight +(Math.random() * ((gallina.minrand - imgHeight)+1));
        gallina1.numrand = imgHeight +(Math.random() * ((gallina.minrand - imgHeight)+1));

        double posicionXArdilla = (winwWidth / 2.0) - (imgWidth / 2.0);
        double posicionYArdilla = winHeight - imgHeight;
        double posicionMirIzq = (winwWidth / 2.0) - (mirIzq.getWidth() * 1.75);
        double posicionMirDer = (winwWidth / 2.0) + (mirDer.getWidth() * 0.7);

        yMirilla = posicionYArdilla;

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
                gc.drawImage(mirIzq, posicionMirIzq, yMirilla);
                gc.drawImage(mirDer, posicionMirDer, yMirilla);

                if (input.contains("LEFT")) {
                    gc.drawImage(izq, posicionXArdilla, posicionYArdilla);
                    resetearMirillas = true;
                }
                else if (input.contains("RIGHT")) {
                    gc.drawImage(der, posicionXArdilla, posicionYArdilla);
                    resetearMirillas = true;
                }
                else {
                    gc.drawImage(centro, posicionXArdilla, posicionYArdilla);
                }

                double t = (l - startNanoTime) / 1000000000.0;

                yMirilla -= 0.8;

                //Calcular la posicion de la gallina en la que sale
                gallina.yGallina = (gallina.numrand-gallina.getFrame(t).getHeight());
                gallina1.yGallina = (gallina1.numrand-gallina1.getFrame(t).getHeight());

                if (yMirilla < (0 -mirDer.getHeight()) || resetearMirillas) {
                    yMirilla = posicionYArdilla;
                    resetearMirillas = false;
                }
                //Dibujar gallina
                gallina.checkGallina(t, gc, gallina, imgHeight);
                gallina1.checkGallina(t,gc, gallina1, imgHeight);
            }
        }.start();
        stage.show();
    }

}
