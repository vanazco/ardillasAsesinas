import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class Main extends Application {
    private final Info info = new Info();
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

        Ardilla ardilla = new Ardilla();
        ardilla.cargarArdilla();

        final long startNanoTime = System.nanoTime();

        //Gallinas
        Gallina gallina = Gallina.CreateGallina();
        Gallina gallina1 = Gallina.CreateGallina();

        Image mirIzq = new Image("mira160.png");
        Image mirDer = new Image("mira160.png");

        //Min del rand para todas las gallinas
        gallina.minrand = 600 - ardilla.getHeight();
        gallina1.minrand = 600 - ardilla.getHeight();


        double posicionMirIzq = (winwWidth / 2.0) - (mirIzq.getWidth() * 1.75);
        double posicionMirDer = (winwWidth / 2.0) + (mirDer.getWidth() * 0.7);

        yMirilla = ardilla.getX();

        ArrayList<String> input = new ArrayList<>();

        //Poner fondo
        Image fondo = new Image("fondo.jpg",800,600,false,false);

        //Musica klk
        String path = "src/main/resources/outro_song.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);


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

                    if (code.contains("LEFT") || code.contains("RIGHT"))
                        ardilla.setMuni(1);

                    input.remove(code);
                });

        Collisions collisions = new Collisions(input);

        new AnimationTimer() {
            @Override
            public void handle(long l) {
                //Que no pare la fiesta (dont stop the party)
                mediaPlayer.setAutoPlay(true);

                double t = (l - startNanoTime) / 1000000000.0;
                gc.clearRect(0, 0, 800, 600);
                //Dibujar fondo
                gc.drawImage(fondo,0,0);
                //Dibujar puntuacion
                info.setText(gc, "PUNTUACION: ", ardilla.points, 10, 20);
                //Dibujar municion
                info.setText(gc, "MUNICION: ", ardilla.muni, ardilla.getWidth() * 4, 580);
                //Dibujar vida
                info.setText(gc, "VIDAS: ", ardilla.vida, 700, 20);

                gc.drawImage(mirIzq, posicionMirIzq, yMirilla);
                gc.drawImage(mirDer, posicionMirDer, yMirilla);

                if (ardilla.setImage(input, gc)) {
                    resetearMirillas = true;
                    if (collisions.checkCollisions(gallina, posicionMirIzq, posicionMirDer, yMirilla)) {
                        gallina.die(t, gc, gallina);
                        resetearMirillas = true;
                        ardilla.points += 20;
                    }
                    if (collisions.checkCollisions(gallina1, posicionMirIzq, posicionMirDer, yMirilla)) {
                        gallina1.die(t, gc, gallina1);
                        resetearMirillas = true;
                        ardilla.points += 20;
                    }
                }

                yMirilla -= 0.8;

                //Calcular la posicion de la gallina en la que sale
                gallina.y = (gallina.numrand-gallina.getFrame(t).getHeight());
                gallina1.y = (gallina1.numrand-gallina1.getFrame(t).getHeight());

                if (yMirilla < (0 -mirDer.getHeight()) || resetearMirillas) {
                    yMirilla = ardilla.getY();
                    resetearMirillas = false;
                }

                //Dibujar gallina
                gallina.checkGallina(t, gc, gallina, ardilla.getHeight(), false);
                gallina1.checkGallina(t,gc, gallina1, ardilla.getHeight(), true);

                if(gallina.x == 0 || gallina1.x == 800){
                    ardilla.SetVida(ardilla);
                }

            }
        }.start();

        stage.show();
    }
}
