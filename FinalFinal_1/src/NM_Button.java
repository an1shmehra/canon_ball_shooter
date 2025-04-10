
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ngmen
 */
public class NM_Button {
    //use a doubleproperty so a listener can be added to know when the value of gravity was changed
    public static DoubleProperty gravDouble = new SimpleDoubleProperty();
    //imageview which will be used as the background (only need 1)
    public static ImageView backGroundImage;
    //static buttons so the program can know which one is selected
    private static Button currentProjectile;
    private static Button currentPlanet;
    private static Button currentLightOrDark;
    // array of images
    private static Image[] imageArr = {new Image("image//CGF_STILL_00032.0.jpg"), new Image("image//earth.jpg"), new Image("image//istockphoto-945479754-612x612.jpg"),
        new Image("image//moon-surface-002-3d-model-max.jpg"), new Image("image//customPlanet.jpg")};
    private static Image[] proImage = {new Image("image//image_processing20200908-32184-1m7an5b.png"), new Image("image//harvestable-resources-rock.png"), new Image("image//R.png")};
    public Button button;
    //index for the program to know the index of the array of images it will use
    private int index;

    //testTF starts as true since if its's used for lightOrDark method, it will need to start as light(true), else, value will be change is the other constructor.
    private static boolean testTF = true;
    //double for gravity/speed depending on the button
    private double gravValue;
    //textinputdialog for the custom planet
    private TextInputDialog textDialogue;

    /**
     *
     * @param b
     * @param num
     * @param boo
     * @param d
     *constructor used for creating either a planet button or a projectile button (decided by boolean ) if boolean is true, the button is a planetbutton else, its a projectilebutton.
     *The constructor will initialize the variables and also define the action method to change for either planet or projectile
     *the constructor will also make the highlight/unhighlight events
     */
    public NM_Button(Button b, int num, boolean boo, double d) {
        button = b;
        index = num;
        gravValue = d;
        Projectile p = Projectile.projectile;
        //if idex is 0, it is the first element (the project will start with this planet or projectile selected
        if (index == 0) {
            if (boo) {
                //set the current button to that button
                currentPlanet = button;
                gravDouble.set(d);
            } else {
                //choose the projectile that it will start, set currentProjectile to that button
                currentProjectile = button;
                currentProjectile.setStyle("-fx-background-color:blue");
                p.getCircle().setFill(new ImagePattern(proImage[index]));
                Planets.initialSpeed = gravValue;
            }
        }

        if (boo) {
            button.setOnAction(e -> {
                //change the background image to the acording image
                backGroundImage.setImage(imageArr[index]);
                currentPlanet.setStyle("-fx-background-color:transparent");
                currentPlanet = this.button;
                currentPlanet.setStyle("-fx-background-color:blue");
                Planets.gravity = gravValue;
                gravDouble.set(gravValue);

            });

            b.setOnMouseExited(e -> {
                if (currentPlanet.getId().equals(button.getId())) {
                    button.setStyle("-fx-background-color:blue");
                } else {
                    button.setStyle("-fx-background-color:transparent");
                }
            });
        } else {
            button.setOnAction(e -> {
                //change image for the projectile
                currentProjectile.setStyle("-fx-background-color:transparent");
                currentProjectile = button;
                currentProjectile.setStyle("-fx-background-color:blue");

                p.getCircle().setFill(new ImagePattern(proImage[index]));
                Planets.initialSpeed = gravValue;
            });
            button.setOnMouseExited(e -> {
                if (currentProjectile.getId().equals(button.getId())) {
                    button.setStyle("-fx-background-color:blue");
                } else {
                    button.setStyle("-fx-background-color:transparent");
                }
            });
        }
        button.setOnMouseEntered(e -> {
            b.setStyle("-fx-background-color:lightblue");
        });

    }

    /**
     * @param b
     * @param boo this boolean is to know if the button is the light mode button
     * (true) or dark mode button (false). If its light, the current button will
     * be equal to b(because project starts in light mode)
     */
    public NM_Button(Button b, boolean boo) {
        button = b;

        if (boo) {
            this.button.setOnMouseExited(e -> {
                if (testTF) {
                    this.button.setStyle("-fx-background-color:blue");
                } else {
                    this.button.setStyle("-fx-background-color:gray");
                }
            });

            this.button.setOnAction(e -> {
                //change css 
                Cannon_Anish_Marco_Noe_Main.scene.getStylesheets().setAll("lightmode.css");
                currentLightOrDark.setStyle("-fx-background-color:gray");
                currentLightOrDark = button;
                testTF = true;
            });

            this.button.setOnMouseExited(e -> {
                if (testTF) {
                    this.button.setStyle("-fx-background-color:blue");
                } else {
                    this.button.setStyle("-fx-background-color:gray");
                }
            });

            currentLightOrDark = button;
        } else {
            this.button.setOnMouseExited(e -> {
                if (!testTF) {
                    this.button.setStyle("-fx-background-color:blue");
                } else {
                    this.button.setStyle("-fx-background-color:gray");
                }
            });

            this.button.setOnAction(e -> {
                //change css
                Cannon_Anish_Marco_Noe_Main.scene.getStylesheets().setAll("darkmode.css");
                currentLightOrDark.setStyle("-fx-background-color:gray");
                currentLightOrDark = this.button;
                testTF = false;
            });
        }

        this.button.setOnMouseEntered(e -> {
            this.button.setStyle("-fx-background-color:lightblue");
        });

    }

    /**
     *
     * @param b constructor for the shoot button(no need to compare to anything)
     */
    public NM_Button(Button b) {
        button = b;
        button.setOnMouseExited(e -> {
            button.setStyle("-fx-background-color:red");
        });

        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-background-color:orange");
        });
    }

    /**
     *
     * @param b
     * @param tx constructor used for creating the custom button and dealing
     * with the custom values for gravity that the user is going to chose
     * @param i
     * the int i is to get the index of the image from the array
     *this constructor will also initialize the set on action for the custom planet and also the highlight method
     */
    public NM_Button(Button b, TextInputDialog tx, int i) {
        index = i;
        button = b;
        textDialogue = tx;
        textDialogue.setTitle("CUSTOM PLANET");
        textDialogue.setHeaderText("PLEASE ENTER A GAVITATIONAL FORCE FOR YOUR CUSTOM PLANET:");
        textDialogue.setContentText("GRAVITATIONAL FORCE:");
        ImageView image = new ImageView(new Image("image//1945947.png"));
        image.setFitHeight(50);
        image.setFitWidth(50);
        textDialogue.setGraphic(image);
        button.setOnAction(e -> {
            getResultFromDP();

        });

        b.setOnMouseExited(e -> {
            if (currentPlanet.getId().equals(button.getId())) {
                button.setStyle("-fx-background-color:blue");
            } else {
                button.setStyle("-fx-background-color:transparent");
            }
        });
        button.setOnMouseEntered(e -> {
            b.setStyle("-fx-background-color:lightblue");
        });
    }
/**
*this method is used for showing the dialogue box and getting the value for the custom gravity of the user.
*If the user presses cancel, it will not change to costume planet and it will stay in the previouos planet
* try and catch is used to make sure the user enters a number
* recursion is used to repeat the method when the user did not enter a valid double
 */   
    public void getResultFromDP() {
        textDialogue.showAndWait().ifPresentOrElse(
                result -> {
                    try {
                        Planets.gravity = Math.abs(Double.valueOf(result));
                        gravDouble.set(Planets.gravity);
                        backGroundImage.setImage(imageArr[index]);
                        currentPlanet.setStyle("-fx-background-color:transparent");
                        currentPlanet = this.button;
                        currentPlanet.setStyle("-fx-background-color:blue");
                    } catch (NumberFormatException nul) {
                        textDialogue.setHeaderText("PLEASE ENTER A VALID NUMBER:");
                        getResultFromDP();
                        textDialogue.setHeaderText("PLEASE ENTER A GAVITATIONAL FORCE FOR YOUR CUSTOM PLANET:");
                    }
                },
                () -> {

                });
    }
}