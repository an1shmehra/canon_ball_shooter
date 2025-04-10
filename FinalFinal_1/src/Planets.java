/*
*Static class used to calculate distance,height and tiime of projectile
*/
public abstract class Planets {
    public static double gravity;
    public static double speed;
    public static double initialSpeed;
    /*
    *method used for finding the time for the projectile to get to the top of the parabola, and to go from the top to the bottom. 
    * Method uses the angle provided form the angleSlider in the FXMLController class and the speed and initialSpeed from the class itself
    */
    public static double findTimeToTopOfParabola(double angle) {
        return (2 * ((speed * initialSpeed) * Math.sin(angle)) / gravity);
    }
    /*
    *Method used to find the height to the top of the parabola
    * requires the angle from the FXML class and the speed and initial speed
    */
    public static double findHeighOfParabola(double angle) {
        double d = (speed * initialSpeed) * Math.sin(angle);
        return 0.2 * (Math.pow(d, 2) * Math.pow(Math.sin((angle)), 2) / (2 * gravity));
    }
    /*
    *Method used to find how much distance does the projectile need to travel
    * Method uses the angle provided form the angleSlider in the FXMLController class and the speed and initialSpeed from the class itself
    */
    public static double findRange(double angle) {
        return 0.2 * ((Math.pow((speed * initialSpeed), 2) * Math.sin(2 * angle)) / gravity);
    }

}