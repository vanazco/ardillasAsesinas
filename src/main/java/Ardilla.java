import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

class Ardilla {
    private Image izq, der, centro;
    private double imgWidth, imgHeight, x, y;

    double getWidth() {
        return imgWidth;
    }

    double getHeight() {
        return imgHeight;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    void cargarArdilla() {
        centro = new Image("ardillaCen160.png");
        izq = new Image("ardillaIzq160.png");
        der = new Image("ardillaDer160.png");
        imgWidth = centro.getWidth();
        imgHeight = centro.getHeight();
        x = (800 / 2.0) - (getWidth() / 2.0);
        y = 600 - getHeight();
    }

    boolean setImage(ArrayList<String> input, GraphicsContext gc) {
        if (input.contains("LEFT")) {
            gc.drawImage(izq, getX(), getY());
            return true;
        }
        else if (input.contains("RIGHT")) {
            gc.drawImage(der, getX(), getY());
            return true;
        }
        else {
            gc.drawImage(centro, getX(), getY());
            return false;
        }
    }
}
