package control;


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
import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.util.Duration;
import sprites.Dashboard;
import sprites.Diana;
import sprites.Pato;

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

    Timeline timelinePato = new Timeline(new KeyFrame(Duration.seconds(0.0517), new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event) {

            if (pato.getEstaMuerto()){
                pato.clear(gc);
                pato.setDirection("DOWN");
                pato.render(gc);

            }else {
                pato.clear(gc);
                pato.setDirection("RIGHT");
                pato.render(gc);

            }

            if (pato.getPosY()>=550){
                pato.setEstaMuerto(false);
                timelinePato.stop();
                pato.clear(gc);
            }
        }
    })
    );

    Timeline timelineDiana = new Timeline(new KeyFrame(Duration.seconds(0.5017), new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                diana.clear(gc);
                diana.render(gc);
            }
        })
    );

    @FXML
    Label lblInfo;
    @FXML
    Canvas mainCanvas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(url);
        System.out.println(resourceBundle.getString("key1"));

        dashboard = new Dashboard(new Image("images/dashboard.png"));
        diana = new Diana(new Image("images/diana.png"));
        pato = new Pato();
        pato.setImage(new Image("images/pato-perfil-1.png"));
        gc = mainCanvas.getGraphicsContext2D();

        dashboard.render(gc);
        diana.render(gc);

        pointsText = "SCORE: " + points;

        gc.setFont(new Font("Arial",16));

        timelineDiana.setCycleCount(Timeline.INDEFINITE);
        timelinePato.setCycleCount(Timeline.INDEFINITE);

        timelinePato.play();
        timelineDiana.play();
    }


    public void setScene(Scene sc) {
        scene = sc;

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Point2D point = new Point2D(mouseEvent.getX(),mouseEvent.getY());
                if(diana.isClicked(point)) {
                    gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
                    diana.clear(gc);

                    dashboard.render(gc);
                    diana = new Diana(new Image("images/diana.png"));

                    String pointsDiana = "10 POINTS";

                    gc.setStroke( Color.BLACK );
                    gc.strokeText(pointsDiana,diana.getPosX(),diana.getPosY()+diana.getHeight()+20);

                    gc.setFill( Color.YELLOW );
                    gc.fillText(pointsDiana,diana.getPosX(),diana.getPosY()+diana.getHeight()+20);


                    points += 10;
                    String pointsText = "SCORE: " + points;

                    gc.setFill( Color.WHITE );
                    gc.fillText( pointsText, 680, 682 );

                    gc.setStroke( Color.WHITE );
                    gc.strokeText( pointsText, 680, 682 );

                    gc.setStroke( Color.TRANSPARENT );
                    gc.setFill( Color.TRANSPARENT );
                }
                if (pato.isClicked(point)){
                    gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
                    timelinePato.stop();
                    pato.setImage(new Image("images/duck-dead.png"));
                    pato.setNUM_SPRITES(1);
                    pato.render(gc);
                    dashboard.render(gc);

                    try {
                        Thread.sleep((long) (Math.random() * 3000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pato.setImage(new Image("images/duck-abajo.png"));

                    pato.setEstaMuerto(true);
                    timelinePato.play();
                }
            }
        });
    }
}
