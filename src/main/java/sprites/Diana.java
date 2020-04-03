package sprites;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Diana {
    private Image image;
    private double posX, posY, width, height;

    public Diana(Image image) {
        this.posX = Math.random()*1000;
        this.posY = Math.random()*290;
        setImage(image);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, posX, posY);
    }

    public void setImage(Image i) {
        image = i;
        width = image.getWidth();
        height = image.getHeight();
    }


    /**
     * Netejar la zona que ocupa l'objecte dins del graphicsContext
     * Al clearRect li passem la posició i les mides de la imatge.
     *
     * @param gc
     */




    public void clear(GraphicsContext gc) {
        gc.clearRect(posX,posY, width, height);
    }



    public Rectangle2D getBoundary() {
        return new Rectangle2D(posX,posY,width,height);
    }

    public boolean isClicked(Point2D p) {
        if(getBoundary().contains(p)) return true;
        else return false;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
}
