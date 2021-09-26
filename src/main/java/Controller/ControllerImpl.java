package Controller;

import Model.Simulation;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import View.View;

public class ControllerImpl implements Controller{

    Simulation simulation; //Reference to the Simulation that handles the data and model specific behavior
    View view; //Reference to the View that is the UI for the application.

    /**
     * The constructor for the ControllerImpl (Impl -> Implementation).  The simulation is set and the View is
     * created and initialized with the UI to run the simulation.
     * @param simulation
     */
    public ControllerImpl(Simulation simulation){
        this.simulation = simulation;
        view = new View(this);
        Platform.startup(() -> {//launch JavaFx application
            Stage stage = new Stage();
            try {
                view.initialize(stage); //Starts the Animation Timer/Game Loop
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    }


    /**
     * The method lets the simulation know to update the data to draw the components
     * @param gc A reference to the graphics context in the view to draw model components on
     */
    @Override
    public void update(GraphicsContext gc) {
        String message = simulation.update(gc);
        if (message != null) {
            view.setMessage(message);
            view.setSimulationRunning(false);
        }
    }

    /**
     * This method handles the logic of submitting the width and height output.  If the input is within an acceptable
     * range, the simulation starts and the message changes.  If the input is bad, the simulation stops and an input
     * error is displayed.
     * @param event The event created by the button push
     * @param width The width entered for the forest
     * @param height The height entered for the forest
     */
    @Override
    public void setUp(ActionEvent event, int width, int height) {
        if (simulation.setup(width,height)){
            view.setSimulationRunning(true);
            view.setMessage("People are wandering in the woods.  How long will it take for them to find each other");
        } else {
            view.setMessage("Invalid Input: Please enter a width and height between 2 and 50.");
            view.setSimulationRunning(false);
        }
    }


}