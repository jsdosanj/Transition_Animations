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

public class transimations extends Application {
    private static final int      KEYBOARD_MOVEMENT_DELTA = 5;
    private static final Duration TRANSLATE_DURATION      = Duration.seconds(0.25);
    
    @Override
    public void start(Stage primaryStage) {
        //create a circle object
        final Circle circle = createCircle();
        
        final Circle circle1 = createCircle();
        //create a group
        final Group group = new Group(createInstructions(), circle, circle1);
        
        //create a transition for the cirlce
        final TranslateTransition transition = createTranslateTransition(circle);
        
        //create a scene that the shape will appear on
        final Scene scene = new Scene(group, 600, 400, Color.BLACK);
        
        //cirlce moves on key press
        moveCircleOnKeyPress(scene, circle);
        moveCircleOnKeyPress(scene, circle1);
        //circle moves on mouse press
        moveCircleOnMousePress(scene, circle, transition);
        moveCircleOnMousePress(scene, circle1, transition);
       
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
    private void moveCircle1OnKeyPress(Scene scene, final Circle circle1) {
        //allow user to move cirlce through the arrow keys
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP:    
                    circle1.setCenterY(circle1.getCenterY() - KEYBOARD_MOVEMENT_DELTA); 
                break;
                case RIGHT: 
                    circle1.setCenterX(circle1.getCenterX() + KEYBOARD_MOVEMENT_DELTA); 
                break;
                case DOWN:  
                    circle1.setCenterY(circle1.getCenterY() + KEYBOARD_MOVEMENT_DELTA); 
                break;
                case LEFT:  
                    circle1.setCenterX(circle1.getCenterX() - KEYBOARD_MOVEMENT_DELTA); 
                break;
            }
        });
    }
    private void moveCircleOnMousePress(Scene scene, final Circle circle, final TranslateTransition transition) {
        //allow the user to move cirlce based on using a touchpad or mouse
        scene.setOnMousePressed((MouseEvent event) -> {//netbeans suggested using a lambda expression. I'm not sure what it does it just added a -> into my code
        //The arrow operator -> divides the lambda expression
        //Left Side: Parameters required by Lambda expression -> Right Side: body which specifies the actions of the lambda expression.    
            circle.setCenterX(event.getSceneX());
            circle.setCenterY(event.getSceneY());
            transition.setToX(event.getSceneX() - circle.getCenterX());
            transition.setToY(event.getSceneY() - circle.getCenterY());
            transition.playFromStart();      
        });
    }
    private void moveCircle1OnMousePress(Scene scene, final Circle circle1, final TranslateTransition transition) {
        //allow the user to move cirlce based on using a touchpad or mouse
        scene.setOnMousePressed((MouseEvent event) -> {//netbeans suggested using a lambda expression. I'm not sure what it does it just added a -> into my code
        //The arrow operator -> divides the lambda expression
        //Left Side: Parameters required by Lambda expression -> Right Side: body which specifies the actions of the lambda expression.    
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
        circle.setOpacity(0.7);
        return circle;
    }
    private Circle createCircle1() {
        final Circle circle1 = new Circle(250,200, 100, Color.GREEN);
        circle1.setOpacity(0.6);
        return circle1; 
    }
    private Label createInstructions() {
        //create instructions for the user, so they know what to do
        Label instructions = new Label("Use the arrow keys or the mouse(click) to move the circle throughout the window\n");
        instructions.setTextFill(Color.WHITE);
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
