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
    private double x, yGallina, yMirilla, minrand, numrand;
    private int winwWidth = 800;
    private int winHeight = 600;
    private Canvas canvas = new Canvas(winwWidth, winHeight);
    boolean Fgallina = false;
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
        Image mirIzq = new Image("mira160.png");
        Image mirDer = new Image("mira160.png");

        minrand = 600 - imgHeight;
        numrand = imgHeight +(Math.random() * ((minrand - imgHeight)+1));

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
                yGallina = (numrand-gallina.getFrame(t).getHeight());

                if (yMirilla < (0 -mirDer.getHeight()) || resetearMirillas) {
                    yMirilla = posicionYArdilla;
                    resetearMirillas = false;
                }
                if(!Fgallina){
                    gc.drawImage( gallina.getFrame(t), x, yGallina);
                    x+=0.5;
                    if(x > winwWidth){
                        Fgallina = true;
                    }
                }else{
                    x=0;
                    numrand = imgHeight +(Math.random() * ((minrand - imgHeight)+1));
                    gc.drawImage( gallina.getFrame(t), x, yGallina);
                    Fgallina = false;
                }
            }
        }.start();

        stage.show();
    }
}
