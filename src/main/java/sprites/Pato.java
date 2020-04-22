package sprites;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Pato {
    public void setNUM_SPRITES(int NUM_SPRITES) {
        this.NUM_SPRITES = NUM_SPRITES;
    }

    private int NUM_SPRITES = 3;
    private Image image;
    private double posX, posY, velX, velY, width, height;
    private int dirX, dirY;
    private int index = 0;
    private boolean estaMuerto = false;
    private int direction;

    public Pato() {
        this.posX = 0.0f;
        this.posY = 0.0f;
        this.velX = 10.0f;
        this.velY = 10.0f;
        this.dirX = 1;
        this.dirY = 1;
    }

    public void moveRight() {
        posX += velX;
        index++;
    }

    public void moveLeft() {
        posX -= velX;
    }

    public void moveDown() {
        posY += velY;
    }


    public void moveUp() {
        posY -= velY;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, (index%NUM_SPRITES)*(width/NUM_SPRITES), 0, (width/NUM_SPRITES), height, posX,posY,(width/NUM_SPRITES),height ); //, CANVAS_WIDTH/2 - WIDTH/2, CANVAS_HEIGHT/2 - HEIGHT/2, WIDTH, HEIGHT);
    }

    public void setImage(Image i) {
        image = i;
        width = image.getWidth();
        height = image.getHeight();
    }

//    public void clear(GraphicsContext gc) {
//        gc.clearRect(posX,posY, width/4, height);
//    }
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
    public void move() {
        this.posX = Math.random()*1000;
        this.posY = Math.random()*500;
    }
    public void setDirection(int direction) {
        this.direction = direction;

        switch (direction) {
            case 0://"RIGHT"
                moveRight();  break;
            case 1://"LEFT"
                moveLeft();
                break;
            case 2://"UP"
                moveUp();
                break;
            case 3://"DOWN"
                moveDown();
                break;
        }
    }

    public int getDirection() {
        return direction;
    }

    public boolean getEstaMuerto() {
        return estaMuerto;
    }

    public void setEstaMuerto(boolean estaMuerto) {
        this.estaMuerto = estaMuerto;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
