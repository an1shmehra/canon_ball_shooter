
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ngmen
 *Singelton class to make sure there is only one projectile inthe entrire project and other classes can interact with the projectile
 */
public class Projectile {  
    private Circle circle;
    public static Projectile projectile;
    /*
    *private constructor for singleon pattern
    */
    private Projectile( Circle c) {
        circle = c;
    }   
    /*
    *method that makes sure 1 projectile is created
    */
    public static Projectile getProjectile(Circle c){
        if(projectile == null){
            projectile =  new Projectile(c);
        }
       return projectile;
    }
    /*
    *getter for the circle of the projectile
    */
    public Circle getCircle() {
        return circle;
    }
}