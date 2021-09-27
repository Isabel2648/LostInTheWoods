package View;

import javafx.scene.image.Image;
import Controller.Controller;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.awt.Toolkit;

public class View {

    private Controller controller; //Reference to the controller instance that processes requests for the Model/Simulation
    private Stage stage; //The window the application resides in.
    private Scene theScene; // The scene is the main structure that
    private StackPane root; // This is the main container for all UI elements that are added to the stage.

    private VBox simulationArea; //Vertical Box that organizes the main UI elements vertically

    private Label message; // The message displayed to users for instructions

    private Button submitSize; // The button used to submit the width and height entered
    private TextField width; // The text box to enter a width in
    private TextField height; // The text box to enter a height in

    private Canvas gameCanvas; // The canvas to draw graphics on for the simulation
    private GraphicsContext gc; // Various commands used to draw graphics on the canvas

    private boolean simulationRunning; // Flag used to determine if the simulation should continue running
    private boolean simulationPaused; // Flag used to determine if a running simulation is paused.

    /**
     * Constructor for the View that requires a Controller to work
     * @param controller
     */
    public View(Controller controller) {
        this.controller = controller;
    }

    /**
     * This method initializes the stage and sets relevant listeners and handlers for events
     * @param theStage The main stage of the application
     */
    public void initialize(Stage theStage) {
        this.stage = theStage;
        createView(stage);

        //Pauses the simulation only if the simulation is running and the enter key is pressed.
        theScene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER && simulationRunning) {
                simulationPaused = !simulationPaused;
                gc.fillText("Simulation Paused", 200, 200);
            }
        });

        //The event handler for the submitSize button
        submitSize.setOnAction(e -> {
            String widthText = width.getText(); //Pulls value from width textBox
            String heightText = height.getText(); //Pulls value from height textBox
            //If any text entry is not an integer, display an error message.  Otherwise, set up the simulation.
            if (isInt(widthText)
                    && isInt(heightText)
                    && controller.setUp(e, Integer.parseInt(widthText), Integer.parseInt(heightText))){
                gc.clearRect(0,0,520,520);
                gameCanvas.requestFocus();
            } else {
                setMessage("Invalid Input: Please enter a width and height for the forest between 2 and 50.");
                Toolkit.getDefaultToolkit().beep();
            }
        });

        theStage.show(); //Makes the stage visible

        //This implements a loop that runs the simulation.
        new AnimationTimer()
        {
            public void handle(long currentNanoTime) // Implementation of the AnimationTimer's handle method
            {
                if (currentNanoTime % 1 ==0) { //Mod allows the simulation to slow down.  Higher numbers slow it down
                    //If the simulation is running, clear the graphics context and then update the simulation
                    if (simulationRunning && !simulationPaused) {
                        gc.clearRect(0, 0, 520, 520); //The graphics context must be cleared as the original graphics persist
                        controller.update(gc);
                    }
                }
            }
        }.start(); //Starts the animation timer

    }

    //https://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java

    /**
     * Determines if a string is an integer.
     * @param string String to test
     * @return Returns true if the value is an int
     */
    private boolean isInt(String string)  // assuming integer is in decimal number system
    {
        if (string.isBlank()) //Null values are not ints
            return false;
        //Loops through each character and checks if they are digits
        for(int i=0;i<string.length();i++) {
            if(!Character.isDigit(string.charAt(i))) return false;
        }
        return true;
    }

    /**
     * Sets up all the UI elements displayed and used by the user.
     * @param theStage
     */
    public void createView(Stage theStage) {
        theStage.setTitle( "Lost in the Woods" );
        //https://www.pinclipart.com/pindetail/oTwJ_free-forest-clipart-free-forest-clipart-at-getdrawings/
        theStage.getIcons().add(new Image("forest.png"));

        // Creates and groups the high level components for the view
        root = new StackPane(); // Automatically centers itself when window grows.
        theScene = new Scene(root);
        theStage.setScene(theScene);

        //TODO Labels do not have scroll bars.  Find an element that would let the user scroll for long results sections.
        //Creates message and sets formatting
        message = new Label("Please enter any width and height for the forest from 2 to 50. This will set the size " +
                "of a forest that two people will randomly wander until they find each other or run out of moves.");
        message.setWrapText(true);
        message.setMaxSize(500, 700);
        message.setPadding(new Insets(10,10,10,10));
        message.setAlignment(Pos.TOP_CENTER);

        //Creates canvas and retrieves graphics context
        gameCanvas = new Canvas(520, 520);
        gc = gameCanvas.getGraphicsContext2D();

        //Creates labels and text boxes to gather user input for the simulation
        Label widthLabel = new Label("Width");
        width = new TextField ();
        Label heightLabel = new Label("Height");
        height = new TextField ();

        //Creates submit button that will attempt to set the width and height
        submitSize = new Button("Submit");

        //Creates horizontal box that organizes and formats text boxes and submit button
        HBox input = new HBox();
        input.setSpacing(10);
        input.setPadding(new Insets(10,10,10,10));
        input.setAlignment(Pos.BOTTOM_CENTER);
        input.getChildren().addAll(widthLabel, width, heightLabel, height, submitSize);

        //Creates vertical box that organizes and formats message, canvas, and input HBox.
        simulationArea = new VBox(10);
        simulationArea.setAlignment(Pos.CENTER);
        simulationArea.getChildren().addAll(gameCanvas, input);

        HBox displayArea = new HBox();
        displayArea.setAlignment(Pos.CENTER);
        displayArea.getChildren().addAll(simulationArea, message);

        //Adds all UI components to the root (Stackpane)
        root.getChildren().addAll(displayArea);

    }

    /**
     * Setter for simulationRunning
     * @param running true -> running
     */
    public void setSimulationRunning(boolean running){
        simulationRunning = running;
    }

    /**
     * Setter for message
     * @param message Message to display in the message label
     */
    public void setMessage(String message) {
        this.message.setText(message);
    }

    /**
     * Getter for graphicsContext
     */
    public GraphicsContext getGc() {
        return gc;
    }

}

