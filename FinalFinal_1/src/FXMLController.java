/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ngmen
 */
public class FXMLController implements Initializable {

    @FXML
    private ImageView canonBase;
    @FXML
    private Circle projectile;
    @FXML
    private TextField angleTextField;
    @FXML
    private Slider angleSlider;
    @FXML
    private TextField gForceTextField;
    @FXML
    private Button beachBallButton;
    @FXML
    private Button bowlingBallButton;
    @FXML
    private Button rockButton;
    @FXML
    private Button saturnButton;
    @FXML
    private Button earthButton;
    @FXML
    private Button marsButton;
    @FXML
    private Button moonButton;
    @FXML
    private Button customButton;
    @FXML
    private Button lightModeButton;
    @FXML
    private Button darkModeButton;
    @FXML
    private ImageView imageBackground;
    @FXML
    private ImageView explosionAnimation;
    @FXML
    private Button exitButton;
    @FXML
    private Button aboutButton;
    @FXML
    private Button returnButton;
    private MediaPlayer player;
    private MediaPlayer soundEffect;
    private AnimationTimer collisionTimer;
    //Use for the canon's pivot point vv
    private double canonx = 20;
    private double canony = 22;
    @FXML
    private Rectangle dummyObj;
    @FXML
    private Circle rhythmCircle;
    private Circle mainRhythmCircle;
    private double angle;
    private Target target1;
    private Target target2;
    private Target target3;
    @FXML
    private Rectangle rec1;
    @FXML
    private Rectangle rec2;
    @FXML
    private Rectangle rec3;
    private ArrayList<Target> arrTargets;
    private Timeline curveTimeline;
    private int totalPoints;
    @FXML
    private Slider speedSlider;
    @FXML
    private TextField speedText;
    @FXML
    private Pane mainPane; //
    @FXML
    private Button shootButton;
    @FXML
    private Label mainLabel;
    @FXML
    private VBox controlsVBox;
    @FXML
    private VBox planetsVbox;
    @FXML
    private Label pointsLabel;
    @FXML
    private TextField pointsTextField;
    @FXML
    private HBox modeHbox;
    @FXML
    private TextArea creatorsTextArea;
    @FXML
    private Slider volumeSlider;
    @FXML
    private ImageView imageRhythm;//
    private double speed;
    private double rotateConstant;
    private double soundVolume;
    private Media media;
    private MediaPlayer mediaPlayer;
    private double projectileX;
    private double projectileY;
    @FXML
    private Text logo;
    @FXML
    private Rectangle clickableMenu;
    @FXML
    private ImageView backgroundImage;
    @FXML
    private Rectangle blackScreen;
    @FXML
    private Text studioName;
    @FXML
    private Text introText;
    @FXML
    private Rectangle settingsBackgroundColor;
    @FXML
    private Text volumeSliderLabel;
    @FXML
    private ImageView menuBackground;
    private Timeline tempTimeline;
    private boolean checkVisibleOrNot;
    private boolean outOfCannon;
    @FXML
    private ImageView settings;
    private Projectile pro;
    private NM_Button nm_saturn;
    private NM_Button nm_earth;
    private NM_Button nm_mars;
    private NM_Button nm_moon;
    private NM_Button nm_beach;
    private NM_Button nm_rock;
    private NM_Button nm_bowling;
    private NM_Button nm_shoot;
    private NM_Button nm_light;
    private NM_Button nm_dark;
    private NM_Button nm_custom;

    /*
     * Initializes the controller class.
     */

@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pointsTextField.setEditable(false);
        pro = Projectile.getProjectile(projectile);

        nm_saturn = new NM_Button(saturnButton, 0, true, 10.44);
        nm_earth = new NM_Button(earthButton, 1, true, 9.81);
        nm_mars = new NM_Button(marsButton, 2, true, 3.7);
        nm_moon = new NM_Button(moonButton, 3, true, 1.6);

        nm_beach = new NM_Button(beachBallButton, 0, false, 1);
        nm_rock = new NM_Button(rockButton, 1, false, 0.7);
        nm_bowling = new NM_Button(bowlingBallButton, 2, false, 0.5);

        nm_shoot = new NM_Button(shootButton);

        nm_light = new NM_Button(lightModeButton, true);
        nm_dark = new NM_Button(darkModeButton, false);

        outOfCannon = false;

        blackScreen.setVisible(true);
        studioName.setVisible(true);

        media = new Media(new File("menuAudio.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        creatorsTextArea.setVisible(false);// creaters name TextArea.
        mainPane.setVisible(false);
        shootButton.setVisible(false);
        mainLabel.setVisible(false);
        controlsVBox.setVisible(false);
        planetsVbox.setVisible(false);         //Code for the menu, putting everything transparent initially
        pointsLabel.setVisible(false);
        pointsTextField.setVisible(false);
        modeHbox.setVisible(false);

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
        
        /**
         * Listener to change the volume of main menu music.
         */
        volumeSlider.valueProperty().addListener(ee -> {    // Slider volume increase and decrease
            soundVolume = volumeSlider.getValue();
            mediaPlayer.setVolume(soundVolume);
        });

        rotateConstant = 0;
        arrTargets = new ArrayList<>();
        totalPoints = 0;
        canonBase.getTransforms().add(new Rotate(-45, canonx, canony)); //(The points picked are relative to the center)

        /**
         * media File responsible for the main music that plays throughout the 
         * project. 
         */
        File soundFile = new File("04Blue Plastic Resistance - bermei.inazawa.mp3");
        Media media = new Media(soundFile.toURI().toString());
        player = new MediaPlayer(media);
        player.setVolume(0);          //volume
        player.play();
        player.setOnEndOfMedia(()
                -> {
            player.play();
        });
        player.setCycleCount(-1);

        angleTextField.setText((int) angleSlider.getValue() + "°");

        nm_custom = new NM_Button(customButton, new TextInputDialog(), 4);

        pointsTextField.setText("0");

        angle = Math.toRadians(angleSlider.getValue());

        speedText.setText(speedSlider.getValue() + "m/s");

        
        /**
         * Sets the textField to the angle value picked by the user
         * 
         */
        angleSlider.valueProperty().addListener(ev -> {
            angle = Math.toRadians((int) angleSlider.getValue());
            angleTextField.setText((int) angleSlider.getValue() + "°");
            rotationAnimation();
        });

        
        /**
         * Sets the textField to the speed value picked by the user
         * 
         */
        speedSlider.valueProperty().addListener(ev -> {
            DecimalFormat df = new DecimalFormat("#.##");
            speed = speedSlider.getValue();
            Planets.speed = speed;
            String formattedValue = df.format(speed);
            speedText.setText(formattedValue + " m/s");
        });

        target1 = new Target(rec1, 1, 2.5, -50,0);
        target2 = new Target(rec2, 2, 2, -100,1);
        target3 = new Target(rec3, 3, 1.5, -125,2);

        arrTargets.add(target1);
        arrTargets.add(target2);
        arrTargets.add(target3);

        
        /**
         * The AnimationTimer responsible for tracking whether or not the projectile
         * is in contact with the targets by activating the "checkCollision" class
         * for every frame until impact
         */
        collisionTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (Target t : arrTargets) {
                    checkCollision(t);

                }
            }
        };
        
        /**
         * The following lines of code consist of the creation of multiple FadeTransitions. To simplify
         * the code we coded it so that we simply have to declare a FadeTransition and input it along with
         * its values into a separate method "initializeFade". These are therefore simply declarations
         * and/or small changes made to the FadeTransition in question which aren't possible within the
         * aforementioned method. I listed their exact use above each one.
         */
        //Rhythm Vignette (game area vignette effect)
        FadeTransition rhythm = new FadeTransition(new Duration(908), imageRhythm);
        initializeFade(rhythm, 908, 100, 1, 0.15, 0.10);
        rhythm.setCycleCount(Animation.INDEFINITE);
        rhythm.play();
        
        //Rhythm Circle present within the main menu
        FadeTransition rhythmMenu = new FadeTransition(new Duration(454), rhythmCircle);
        initializeFade(rhythmMenu, 454, 100, 1, 0.6, 0);
        rhythmMenu.setCycleCount(Animation.INDEFINITE);
        rhythmMenu.play();

        //Rhythm Text ("Press anywhere to start")
        FadeTransition rhythmText = new FadeTransition(new Duration(908), introText);
        initializeFade(rhythmText, 908, 100, 1, 0.6, 0.3);
        rhythmText.setCycleCount(Animation.INDEFINITE);
        rhythmText.play();

        //Main Logo Fade ("Tardieu's Adventure" text)
        FadeTransition logoFade = new FadeTransition(new Duration(908), logo);
        initializeFade(logoFade, 908, 100, 1, 1, 0.8);
        logoFade.setCycleCount(Animation.INDEFINITE);
        logoFade.play();

        //(Intro Sequece) Text Fade-In "Tardieu Studios"
        FadeTransition studioFade = new FadeTransition(new Duration(908), studioName);
        initializeFade(studioFade, 908, 702, 1, 0, 1);
        studioFade.setOnFinished(e -> {
            studioName.setOpacity(1);
        });
        studioFade.play();

        //(Intro Sequece) Text Fade-Out "Tardieu Studios"
        FadeTransition studioFade2 = new FadeTransition(new Duration(908), studioName);
        initializeFade(studioFade2, 908, 2528, 1, 1, 0);
        studioFade2.setOnFinished(e -> {
            studioName.setOpacity(0);
            studioName.setDisable(true);
        });     
        studioFade2.play();

        //(Intro Sequence) Black Screen Fade-Out (fades the initial black screen to show the main menu)
        FadeTransition blackFade = new FadeTransition(new Duration(3500), blackScreen);
        initializeFade(blackFade, 908, 3500, 1, 1, 0);
        blackFade.setOnFinished(e -> {
            blackScreen.setOpacity(0);
            blackScreen.setDisable(true);
        });
        blackFade.play();

        
        /**
         * This is the code to make the ball invisible after it touches the ground. It takes the ball 0.5 seconds to make 
         * the ball diappear once it has touched the forund. The collision with the floor is detected by using the getbounds 
         * inbuilt java method.
         */
        Duration delay = Duration.seconds(0.5);
        KeyFrame keyFrame = new KeyFrame(delay, et -> {
            collisionTimer.stop();
            projectile.setVisible(false);
        });
        tempTimeline = new Timeline(keyFrame);

        nm_shoot.button.setOnAction(e -> {
            shoot();
        });

        NM_Button.backGroundImage = imageBackground;

        curveTimeline = new Timeline();
        Planets.gravity = 10.44;
        Planets.initialSpeed = 1;
        Planets.speed = 2.5;
        gForceTextField.setText(10.44 + "m/s^2");
        //listener for when the gravDouble doubleproperty is change, so that the textfield changes value
        NM_Button.gravDouble.addListener(e -> {
            gForceTextField.setText(NM_Button.gravDouble.get() + "m/s^2");
        }
        );
        
    }

    /**
     * Method that updates that pointsTextField depending on the target it hits
     */
     private void updatePoints(int points) {
        totalPoints += points;
        pointsTextField.setText(String.valueOf(totalPoints));
    }
    

    /**
     * Method used to simplify the creation of the FadeTransitions used for the introductory sequence and the rhythm
     * effects. The method takes in a FadeTransition created beforehand along with modifiable parameters which consist of 
     * its duration (in milliseconds), delay (in milliseconds), the amount of cycles (if its infinite we declare it separately) and the 
     * from and to values for opacity.
     */
    private void initializeFade(FadeTransition t, int duration, int delay, int cycle, double from, double to) {
        t.setDuration(Duration.millis(duration));
        t.setDelay(Duration.millis(delay));
        t.setCycleCount(cycle);
        t.setFromValue(from);
        t.setToValue(to);
    }
    

    /**
     * This method is responsible for removing the settings menu upon clicking
     * the "Return" button within said menu. It does this by making every element
     * within the menu invisible and setting certain element's mouse transparency
     * to true. This is because setting it to true allows the user to bypass the
     * page and click through the large rectangles since they would otherwise act
     * as a solid wall blocking mouse events from accessing methods behind them
     * whether they were invisible or not.
     */
     @FXML
    private void letsReturn(MouseEvent event) { //Exit Button
        creatorsTextArea.setVisible(false);
        settingsBackgroundColor.setVisible(false);
        settingsBackgroundColor.setMouseTransparent(true);
        backgroundImage.setVisible(false);
        backgroundImage.setMouseTransparent(true);
        aboutButton.setVisible(false);
        volumeSlider.setVisible(false);
        volumeSliderLabel.setVisible(false);
        returnButton.setVisible(false);
        exitButton.setVisible(false);
    }
    

    /**
     * Displays the textArea containing the creators of the game. The
     * if methods make it so that clicking on it when the textArea is on display
     * makes it disappear, instead of continually stay on screen.
     */
    @FXML
    private void letsAbout(MouseEvent event) { //Exit Button
        if (creatorsTextArea.isVisible() == false) {
            creatorsTextArea.setVisible(true);
        } else {
            creatorsTextArea.setVisible(false);
        }
    }

    
    /**
     * This method simply leaves the game entirely when you click on the "Exit" button located in the settings menu.
    */
    @FXML
    private void letsExit(MouseEvent event) { //Exit Button
        Platform.exit();
    }

    /**
     * This is the main method which makes the the projectile motion take place. The timeline is used to make the parabolic trajectory.
     * Firstly the x and y axis are set to the initial value of the projectile in the sceneBuilder. Using the findRange method from the 
     * planet class, the displacement in the x direction is calculated. Using the findHeighOfParabola maethod from the planet class, the 
     * displacement method in the y direction is caculated. The Interpolator is the one that does the job of making a curve. There are some 
     * scaling values in the code to make it visually more pleasing. For example, if the x value of the frames is between 60 and 7, the ball 
     * is set to 60  frames so that it appears to be out of the canon. To add on the scling constant of 1000 is also added so that the trajectory
     * in the x and y direction is longer and more visual.
     */
    private void shoot() {
        tempTimeline.stop();
        curveTimeline.stop();

        checkVisibleOrNot = false;
        outOfCannon = false;
        if (angleSlider.getValue() < 5) {
            angle = Math.toRadians(5);
        }
        if (speedSlider.getValue() < 0.2) {
            Planets.speed = 0.2;
        }
        if (tempTimeline.getStatus().equals(Animation.Status.RUNNING)) {
            tempTimeline.stop();
        }
        DoubleProperty tx = projectile.translateXProperty();
        DoubleProperty ty = projectile.translateYProperty();
        KeyValue kvStartX = new KeyValue(tx, projectile.getCenterX());//canonShape.getTranslateX()
        
        double x = Planets.findRange(angle) * 1000;
        //if statement for aesthetic reasons (if x is too small, it will not appear any where
        if (x < 60 && x > 7) {
            x = 60; // number of pixels
        }
        //because of error transformation between degrees and angles, 0 is never acheived, so for anything under a value of 7, the value can be replaced by 0 for the projectile to look as if it is going up and down
        if (x <= 7) {
            x = 0;
        }

        KeyValue kvEndX = new KeyValue(tx, x);
        KeyValue kvStartY = new KeyValue(ty, projectile.getCenterY());

        double dd = Planets.findHeighOfParabola(angle) * -1000;
        //if statement so that there is always a small parabola when the projectile is launch (even with small values)
        if (dd < 25) {
            dd = dd - 25;
        }

        KeyValue kvEndY = new KeyValue(ty, (dd), new Interpolator() {
            @Override
            public double curve(double t) {
    
                return 4 * t * (1 - t);
            }
        });

        curveTimeline = new Timeline();
        KeyFrame kfStart = new KeyFrame(Duration.ZERO, kvStartX, kvStartY);
        curveTimeline.getKeyFrames().add(kfStart);
        KeyFrame kfEnd;
    
        double time = Planets.findTimeToTopOfParabola(angle);
        //change the time for when it's too small aesthetic reasons
        if (time < 0.01) {
            time = 0.01;
        }

        kfEnd = new KeyFrame(Duration.seconds(time), kvEndX, kvEndY);
        curveTimeline.getKeyFrames().add(kfEnd);
        curveTimeline.setCycleCount(1);
        curveTimeline.play();
        collisionTimer.start();

        curveTimeline.setOnFinished(e -> {
            tempTimeline.play();
        });
    }
    
    
    /**
     * This method is used for rotating the canon about a specific point (canonX, canonY). It updates the canon's value relative to
     * where it currently is meaning that it is not an absolute measurement. Upon moving the slider, the canon resets to 45 degrees
     * and updates its position relative to that point. This is done by subtracting 45 from the canon's current value. This is done because
     * the getTransforms method is relative meaning 15 degrees changed would be 15 degrees changed relative to 45 degrees (60 or 30 degrees
     * depending on whether its positive or minus). The rotate constant is used to reset it to 45 degrees since, being a relative value, we
     * have to add 360 degrees plus whatever the offset cause by the angle is in order to return it to its original position.
     */
    private void rotationAnimation() {
        canonBase.getTransforms().add(new Rotate(rotateConstant, canonx, canony));  // Reset the cannon to 45 degrees (values are relative)
        canonBase.getTransforms().add(new Rotate(45 - angleSlider.getValue(), canonx, canony));

        rotateConstant = 0;
        rotateConstant = rotateConstant - (45 - angleSlider.getValue());
    }

    
    /**
     * This method first checks if the projectile is inside the imageview where its traveling. If it goes outside the imageview, it becomes invicible
     * The method also checks if the projectile left the cannon imageview and once it leaves, the cannon becomes visible
     * This method is responsible for detecting whether or not the projectile (cannon ball) is currently in contact with 
     * one of the targets. It does this by making use of the AnimationTimer "collisionTimer" present in the
     * initialize method which runs for every frame as long as said timer is on. Once the projectile comes in contact with a target,
     * the projectile's visibility will be set to false and an explosion sound effect will be played. This is in addition to
     * the "explosionAnimate" method activating which will go on to create an explosion animation. The collisionTimer is then stopped
     * in order to prevent excessive lagging, and will next activate when the user presses shoot once again.
    */
    private void checkCollision(Target t) {
        
          if ((projectile.getBoundsInParent().intersects(NM_Button.backGroundImage.getBoundsInParent())) && !checkVisibleOrNot) {
            projectile.setVisible(true);
        } else {
            projectile.setVisible(false);
        }

        if (!outOfCannon) {
            if (projectile.getBoundsInParent().intersects(canonBase.getBoundsInParent())) {
                projectile.setVisible(false);
            } else {
                projectile.setVisible(true);
                outOfCannon = true;
            }
        }

        if (projectile.getBoundsInParent().intersects(t.shape.getBoundsInParent())) {

            projectile.setVisible(false);

            //obtaining projectile's x and y values...
            Bounds bounds = projectile.getBoundsInParent();
            projectileX = bounds.getCenterX();
            projectileY = bounds.getCenterY();
            explosionAnimate(); //animates the explosion

            updatePoints(t.points);
            collisionTimer.stop();

            explosionAnimation.setX(projectileX - 228);
            explosionAnimation.setY(projectileY - 190);
            collisionTimer.stop();

            //explosion sound effect upon impact
            File test = new File("explosion-6055.mp3");
            Media mediaT = new Media(test.toURI().toString());
            soundEffect = new MediaPlayer(mediaT);
            soundEffect.setVolume(0.4);          //volume
            soundEffect.setCycleCount(1);
            soundEffect.play();

            checkVisibleOrNot = true;
        }
    }

    
    /**
     * This method is responsible for creating the explosion effect one sees when the ball comes into contact
     * with the target. It does this by creating a new GIF based on the one we provided in the image class and
     * setting its position to the ball's location upon impact. The GIF is created so that the animation plays
     * from the beginning instead of at a random point. After a few seconds, the GIF is then removed with the use
     * of a dummy FadeTransition which acts as a timer for how long the explosion GIF should be displayed for.
    */
    private void explosionAnimate() {
        Image image = new Image(getClass().getResourceAsStream("/image/output-onlinegiftools (1).gif"));
        explosionAnimation.setImage(image);
        explosionAnimation.setVisible(true);

        FadeTransition fadeTimer = new FadeTransition(new Duration(870), dummyObj);
        fadeTimer.setFromValue(0);
        fadeTimer.setToValue(0);
        fadeTimer.setCycleCount(1);
        fadeTimer.play();

        fadeTimer.setOnFinished(e -> {
            explosionAnimation.setVisible(false);
            fadeTimer.stop();
        });

    }


    
    /**
     * This method is responsible for entering into the game itself from the main menu. It is activated by clicking
     * on an invisible rectangle that fills the screen within the main menu (it is below the settings option so that
     * it may work as well) and that deactivates upon entering the game. In this case the main menu element are faded
     * out in order to provide a seamless transition between the menu and the game area. The elements are then set
     * to visibility(false), with the clickable menu being disabled, in order to display said game area.
    */
    @FXML
    private void menuClick(MouseEvent event) {
        FadeTransition menuFade = new FadeTransition(new Duration(200), menuBackground);
        menuFade.setDelay(Duration.millis(200));
        menuFade.setFromValue(1);
        menuFade.setToValue(0);
        menuFade.setCycleCount(1);
        menuFade.setAutoReverse(false);
        menuFade.setOnFinished(e -> {
            menuBackground.setDisable(true);
        });
        menuFade.play();

        FadeTransition circleFade = new FadeTransition(new Duration(200), rhythmCircle);
        circleFade.setFromValue(rhythmCircle.getOpacity());
        circleFade.setToValue(0);
        circleFade.setCycleCount(1);
        circleFade.setAutoReverse(false);
        circleFade.setOnFinished(e -> {
            rhythmCircle.setDisable(true);
            rhythmCircle.setVisible(false);
            introText.setVisible(false);
            logo.setVisible(false);
        });
        circleFade.play();

        clickableMenu.setVisible(false);
        clickableMenu.setDisable(true);

        //"let's play" methods @Anish
        mainPane.setVisible(true);
        shootButton.setVisible(true);
        mainLabel.setVisible(true);
        controlsVBox.setVisible(true);
        planetsVbox.setVisible(true);
        pointsLabel.setVisible(true);
        pointsTextField.setVisible(true);
        modeHbox.setVisible(true);

        settings.setVisible(false);

    }

    
    /**
     * This method is responsible for displaying the settings menu by clicking on the icon in
     * the top left corner of the menu. As stated with the previous display methods, it simply
     * displays the elements using setVisible(true) and disables mouse transparency for the
     * background images in order to not accidentally bypass the main menu and start a game.
    */
    @FXML
    private void settingsClick(MouseEvent event) {
        settingsBackgroundColor.setVisible(true);
        settingsBackgroundColor.setMouseTransparent(false);
        backgroundImage.setVisible(true);
        aboutButton.setVisible(true);
        volumeSlider.setVisible(true);
        volumeSliderLabel.setVisible(true);
        returnButton.setVisible(true);
        exitButton.setVisible(true);
        backgroundImage.setMouseTransparent(false);
    } 

    /**
     * This method is responsible for returning to the main menu from the main game area. It works similarly to the "letsReturn" method
     * which I've explained previously. It simply makes all the elements in the main menu visible and all the elements in the game area
     * invisible.
    */
    @FXML
    private void changeToMenu(ActionEvent event) {

        mainPane.setVisible(false);
        shootButton.setVisible(false);
        mainLabel.setVisible(false);
        controlsVBox.setVisible(false);
        planetsVbox.setVisible(false);
        pointsLabel.setVisible(false);
        pointsTextField.setVisible(false);
        modeHbox.setVisible(false);

        menuBackground.setVisible(true);
        menuBackground.setDisable(false);
        menuBackground.setOpacity(1);
        rhythmCircle.setVisible(true);
        clickableMenu.setVisible(true);
        clickableMenu.setDisable(false);
        logo.setVisible(true);
        introText.setVisible(true);

        settings.setVisible(true);
    }
}