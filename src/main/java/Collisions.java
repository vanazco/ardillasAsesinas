import java.awt.*;
import java.util.ArrayList;

public class Collisions {
    private ArrayList<String> input;
    private Rectangle gallina;
    private Rectangle mirilla;


    Collisions(ArrayList<String> input) {
        this.input = input;
        gallina = new Rectangle();
        mirilla = new Rectangle();
    }

    boolean checkCollisions(Gallina gallina, double posicionMirIzq, double posicionMirDer, double yMirilla) {
        if (input.contains("LEFT") || input.contains("RIGHT")) {
            this.gallina.setBounds((int) gallina.x, (int) gallina.y, 54, 66);
            this.mirilla.setBounds((int) posicionMirIzq, (int) yMirilla, 45, 45);
            if (this.mirilla.intersects(this.gallina))
                return true;
            else {
                this.mirilla.setBounds((int) posicionMirDer, (int) yMirilla, 45, 45);
                if (this.mirilla.intersects(this.gallina))
                    return true;
            }
        }
        return false;
    }
}
