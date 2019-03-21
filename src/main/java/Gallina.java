import javafx.scene.image.Image;

public class Gallina {
    public Image[] frames;
    public double duration;

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
}
