package Controller;

import Model.Model;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import View.View;

public class ControllerImpl implements Controller{

    Model model;
    View view;

    public ControllerImpl(Model model){
        this.model = model;
        view = new View(this);
        Platform.startup(() -> {//launch JavaFx application
            Stage stage = new Stage();
            try {
                view.start(stage); //Starts the Animation Timer/Game Loop
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    }


    @Override
    public void update(GraphicsContext gc) {
        model.update(gc);
    }

    @Override
    public void setUp(ActionEvent event, int width, int height) {
        if (model.setup(width,height)){
            view.setSimulationRunning(true);
            view.setInstructions("People are wandering in the woods.  How long will it take for them to find each other");
        } else {
            view.setInstructions("Invalid Input: Please enter a width and height between 2 and 50.");
            view.setSimulationRunning(false);
        }
    }


}