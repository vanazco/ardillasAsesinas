import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class Controller {
    @FXML
    public Button reset;

    Main main = new Main();

    @FXML
    public void resetGame(javafx.event.ActionEvent event) throws Exception {
        System.out.println("aaaaaaa");
        Stage stage = Main.getPrimaryStage();
        main.start(stage);
    }
}
