package transimations;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Transimations extends Application {
    private static final int      KEYBOARD_MOVEMENT_DELTA = 5;
    private static final Duration TRANSLATE_DURATION      = Duration.seconds(0.10);
    
    public void start(Stage primaryStage) {
        //create 2 circle objects
        final Circle circle = createCircle();
        
        final Circle circle1 = createCircle();
        //create a group
        final Group group = new Group(createInstructions(), circle, circle1);
        
        //create a transition for the cirlce
        final TranslateTransition transition = createTranslateTransition(circle);
        
        //create a scene that the shape will appear on
        final Scene scene = new Scene(group, 750, 500, Color.BLUE);
        
        //cirlce moves on key press
        moveCircleOnKeyPress(scene, circle);

        //circle moves on mouse press
        moveCircle1OnMousePress(scene, circle1, transition);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
    private void moveCircleOnKeyPress(Scene scene, final Circle circle) {
        //allow user to move cirlce through the arrow keys
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP:    
                    circle.setCenterY(circle.getCenterY() - KEYBOARD_MOVEMENT_DELTA); 
                break;
                case RIGHT: 
                    circle.setCenterX(circle.getCenterX() + KEYBOARD_MOVEMENT_DELTA); 
                break;
                case DOWN:  
                    circle.setCenterY(circle.getCenterY() + KEYBOARD_MOVEMENT_DELTA); 
                break;
                case LEFT:  
                    circle.setCenterX(circle.getCenterX() - KEYBOARD_MOVEMENT_DELTA); 
                break;
            }
        });
    }
    
    private void moveCircle1OnMousePress(Scene scene, final Circle circle1, final TranslateTransition transition) {
        //allow the user to move cirlce based on using a touchpad or mouse
        scene.setOnMousePressed((MouseEvent event) -> {   
            circle1.setCenterX(event.getSceneX());
            circle1.setCenterY(event.getSceneY());
            transition.setToX(event.getSceneX() - circle1.getCenterX());
            transition.setToY(event.getSceneY() - circle1.getCenterY());
            transition.playFromStart();      
        });
    }
    
    private Circle createCircle() {
        //cirlce method
        final Circle circle = new Circle(200, 150, 50, Color.WHITE);
        circle.setOpacity(0.8);
        return circle;
    }
    
    private Circle createCircle1() {
        final Circle circle1 = new Circle(350,200, 100, Color.PURPLE);
        circle1.setOpacity(0.8);
        return circle1; 
    }
    
    private Label createInstructions() {
        //create instructions for the user, so they know what to do
        Label instructions = new Label("Welcome to Transimations.\n"
                + "This is where you will experience two circles being moved throughout this window with your mouse or arrow keys\n"
                + "Use the arrow keys to move 1 circle" 
                + " and use the trackpad(click) to move the other circle throughout the window\n");
        instructions.setTextFill(Color.YELLOW);
        return instructions;
    }
    
    private TranslateTransition createTranslateTransition(final Circle circle) {
        //Tells the circle what to do with a TranslateTransition
        final TranslateTransition transition = new TranslateTransition(TRANSLATE_DURATION, circle);
        transition.setOnFinished((ActionEvent t) -> {//again, netbeans suggested using a lambda expression.
            circle.setCenterX(circle.getTranslateX() + circle.getCenterX());
            circle.setCenterY(circle.getTranslateY() + circle.getCenterY());
            circle.setTranslateX(0);
            circle.setTranslateY(0);
        });
    return transition;
    }
    
    public TranslateTransition createTranslateTransition1(final Circle circle1) {
        //Tells the circle what to do with a TranslateTransition
        final TranslateTransition transition1 = new TranslateTransition(TRANSLATE_DURATION, circle1);
        transition1.setOnFinished((ActionEvent t) -> {//again, netbeans suggested using a lambda expression.
            circle1.setCenterX(circle1.getTranslateX() + circle1.getCenterX());
            circle1.setCenterY(circle1.getTranslateY() + circle1.getCenterY());
            circle1.setTranslateX(0);
            circle1.setTranslateY(0);
        });
    return transition1;
    }
}
