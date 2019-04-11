import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

class Gallina {
    private Image[] frames;
    private double duration;
    private boolean Fgallina = false;
    double x, y,minrand,numrand;
    private int winwWidth = 800;
    private double speed = 0.5;

    Image getFrame(double time) {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    static Gallina CreateGallina() {
        Gallina gallina = new Gallina();
        Image[] imageArray = new Image[2];
        imageArray[0] = new Image("gallina_2.png");
        imageArray[1] = new Image("gallina_1.png");
        gallina.frames = imageArray;
        gallina.duration = 0.100;
        return gallina;
    }

    void die(double t, GraphicsContext gc, Gallina gallina, boolean right){
        if(right){
            gallina.x = 0;
            numrand = 76 + (Math.random() * ((minrand - 76) + 1));
        }else{
            gallina.x = winwWidth;
            numrand = 76 + (Math.random() * ((minrand - 76) + 1));
        }
        gc.drawImage(gallina.getFrame(t), x, y);
    }

    void setSpeed(double speed) {
        this.speed += speed;
    }

    void checkGallina(double t, GraphicsContext gc, Gallina gallina, double imgHeight, boolean right) {
        if (right) {
            if (!Fgallina) {
                gc.drawImage(gallina.getFrame(t), x, y);
                x += speed;
                if (x >= winwWidth) {
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
                x -= speed;
                if (x <= 0) {
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
