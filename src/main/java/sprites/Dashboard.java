package sprites;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Dashboard {
    private Image image;
    private double posX, posY, width, height;

    public Dashboard(Image image) {
        this.posX = 0;
        this.posY = 0;
        setImage(image);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, posX, posY);
    }

    public void setImage(Image i) {
        image = i;
        width = 64.0;
        height = 64.0;
        System.out.println(width+" - "+height);
    }
}
