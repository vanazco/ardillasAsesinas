import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args)
    {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle( "Ez clap" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        stage.setScene( theScene );

        Canvas canvas = new Canvas( 800, 600 );
        root.getChildren().add( canvas );

        stage.show();
        
    }
}
