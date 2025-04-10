import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ngmen
 */
public class Target {
    
    public Rectangle shape;
    public TranslateTransition trans;
    public int points;
    public double time;
    public double finalY;
    public int num;
   public static Image[] targImage = {new Image ("image//wood.jpeg"),new Image ("image//Rock.jpeg"), new Image ("image//diamond.jpeg")  };
    public Target(Rectangle shape, int points, double time, double finalY, int num) {
        this.shape = shape;
        this.points = points;
        this.time = time;
        this.finalY = finalY;
        this.num = num;
        shape.setFill(new ImagePattern (targImage[num]));
        
        setTransTransition();
    }

    /*
    * Method used to set the transition of the target
    */
    private TranslateTransition setTransTransition( ) {
        trans = new TranslateTransition(Duration.seconds(time));
        trans.setNode(shape);
        trans.setToX(0);
        trans.setToY(finalY);
        trans.setAutoReverse(true);
        trans.setCycleCount(TranslateTransition.INDEFINITE);
        trans.play();
        return trans;
    }
    
}