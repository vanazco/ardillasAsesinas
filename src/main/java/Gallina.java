import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javax.lang.model.type.IntersectionType;
import java.util.Random;

public class Gallina {
    public Image[] frames;
    public double duration;
    boolean Fgallina = false;
    public double x, y,minrand,numrand;
    Random r = new Random();
    public int winwWidth = 800;

    public Image getFrame(double time)
    {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    public static Gallina CreateGallina() {
        Gallina gallina = new Gallina();
        Image[] imageArray = new Image[2];
        imageArray[0] = new Image("gallina_2.png");
        imageArray[1] = new Image("gallina_1.png");
        gallina.frames = imageArray;
        gallina.duration = 0.100;
        return gallina;
    }

    public void die(double t,GraphicsContext gc,Gallina gallina){
        Image[] imageArray2 = new Image[1];
        imageArray2[0] = new Image("u_dead.png");
        gallina.frames = imageArray2;
        gallina.duration = 0.100;
        gc.drawImage(gallina.getFrame(t), x, y);
    }




    public void checkGallina(double t, GraphicsContext gc, Gallina gallina, double imgHeight, boolean right) {
        if (right) {
            if (!Fgallina) {
                gc.drawImage(gallina.getFrame(t), x, y);
                x += 0.5;
                if (x > winwWidth) {
                    Fgallina = true;
                    numrand =  (Math.random()*gallina.minrand- 60) + 60;
                }
            } else {
                    x = 0;
                    numrand = imgHeight + (Math.random() * ((minrand - imgHeight) + 1));
                    gc.drawImage(gallina.getFrame(t), x, y);
                    Fgallina = false;
                }
        } else {
            if (!Fgallina) {
                gc.drawImage(gallina.getFrame(t), x, y);
                x -= 0.5;
                if (x < 0) {
                    Fgallina = true;
                    numrand =  (Math.random()*gallina.minrand- 60) + 60;
                }
            } else {
                    x = winwWidth;
                    numrand = imgHeight + (Math.random() * ((minrand - imgHeight) + 1));
                    gc.drawImage(gallina.getFrame(t), x, y);
                    Fgallina = false;
                }
            }

        }
    }
