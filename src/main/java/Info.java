import javafx.scene.canvas.GraphicsContext;

public class Info {
    public Info() {

    }

    void setText(GraphicsContext gc, String s, int points, double i, int i2) {
        //Dibujar puntuacion
        String pointext = s + points;
        gc.fillText(pointext, i, i2);
        gc.strokeText(pointext, i, i2);
    }
}