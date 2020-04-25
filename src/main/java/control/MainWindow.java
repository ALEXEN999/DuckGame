package control;


import com.sun.tools.javac.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.util.Duration;
import sprites.Dashboard;
import sprites.Diana;
import sprites.Pato;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    private Scene scene;
    private GraphicsContext gc;
    private Diana diana;
    private Pato pato;
    private Dashboard dashboard;

    private String pointsText;
    int points = 0;
    private String s = getClass().getClassLoader().getResource("music/bgmusic.wav").toExternalForm();
    private Media sound = new Media(s);
    private MediaPlayer audioClip = new MediaPlayer(sound);

    Timeline timelinePato = new Timeline(new KeyFrame(Duration.seconds(0.0517), new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event) {

            if (pato.getPosY() < -50 | pato.getPosX() < -50 | pato.getPosY() > 850 | pato.getPosX() > 1200){
                pato.setDirection((int) (Math.random()*3));
                pato.move();
            }

            if (pato.getEstaMuerto()){//pato caido
                pato.clear(gc);
                pato.setNUM_SPRITES(1);
                pato.setDirection(3);//abajo
                pato.setImage(new Image("images/duck-abajo.png"));
                dashboard.render(gc);
                diana.render(gc);
                pato.render(gc);
                paintScore();


            }else {//movimientos random
                pato.clear(gc);
                pato.setNUM_SPRITES(3);


                if (pato.getDirection()==0){//derecha
                    pato.setDirection(0);
                    pato.setImage(new Image("images/pato-perfil-derecha.png"));
                } else if (pato.getDirection()==1){//izquierda
                    pato.setDirection(1);
                    pato.setImage(new Image("images/pato-perfil-izquierda.png"));
                } else if (pato.getDirection()==2){//arriba
                    pato.setDirection(2);
                    pato.setImage(new Image("images/pato-arriba-1.png"));
                }

                dashboard.render(gc);
                diana.render(gc);
                pato.render(gc);
                paintScore();



            }

            if (pato.getPosY()>=550){//Cuando choca contra el suelo se para
                pato.setEstaMuerto(false);

                timelinePato.stop();
                dashboard.render(gc);
                pato.move();
                pato.render(gc);
                paintScore();
                pato.setDirection((int) (Math.random()*3));
                timelinePato.play();

            }
        }
    })
    );

    private void paintScore() {
        String pointsText = "SCORE: " + points;

        gc.setFill( Color.WHITE );
        gc.fillText( pointsText, 680, 682 );

        gc.setStroke( Color.WHITE );
        gc.strokeText( pointsText, 680, 682 );
    }


    @FXML
    Label lblInfo;
    @FXML
    Canvas mainCanvas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(url);
        System.out.println(resourceBundle.getString("key1"));

        System.out.println("duració:" + sound.getDuration().toString() + " loc:" + sound.getSource());
        audioClip.setCycleCount(MediaPlayer.INDEFINITE);

        dashboard = new Dashboard(new Image("images/dashboard.png"));
        diana = new Diana(new Image("images/diana.png"));
        pato = new Pato();
        gc = mainCanvas.getGraphicsContext2D();

        diana.render(gc);


        gc.setFont(new Font("Arial",16));

        timelinePato.setCycleCount(Timeline.INDEFINITE);

        timelinePato.play();
        audioClip.play();

        dashboard.render(gc);



    }


    public void setScene(Scene sc) {
        scene = sc;

        scene.setOnMouseClicked(mouseEvent -> {
            Point2D point = new Point2D(mouseEvent.getX(),mouseEvent.getY());
            if(diana.isClicked(point)) {
                gc.clearRect(0, 0, gc.getCanvas().getWidth(),550);
                dashboard.render(gc);
                diana.clear(gc);
                diana.move();
                diana.render(gc);

                points += 10;

            }
            if (pato.isClicked(point)){
                gc.clearRect(0, 0, gc.getCanvas().getWidth(), 550);

                pato.setEstaMuerto(true);

                points += 25;

            }

            String pointsText = "SCORE: " + points;

            gc.setFill( Color.WHITE );
            gc.fillText( pointsText, 680, 682 );

            gc.setStroke( Color.WHITE );
            gc.strokeText( pointsText, 680, 682 );


        });
    }

}
