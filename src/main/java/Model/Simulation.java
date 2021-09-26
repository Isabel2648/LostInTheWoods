package Model;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private Forest forest; //Reference to the Forest in the simulation
    private List<BallPerson> ballPersons; //Reference to a list of people in the simulation that will wander.

    /**
     * The constructor for the simulation.  This creates the necessary elements in the simulation (A forest and a list of
     * two people).
     */
    public Simulation(){
        forest = new Forest();
        BallPerson ballPerson1 = new BallPerson(0,-1, forest); //Starting positions
        BallPerson ballPerson2 = new BallPerson(1, 1, forest);
        ballPersons = new ArrayList<>();
        ballPersons.add(ballPerson1);
        ballPersons.add(ballPerson2);
    }

    /**
     * This sets the forest size if the input is valid.
     * @param width Width of the forest (2 <= width <= 50)
     * @param height Height of the forest (2 <= height <= 50)
     * @return boolean representing valid input. true -> valid input
     */
    public boolean setup(int width, int height){
        boolean validInput = forest.setForestSize(width, height);

        if (validInput) {
            ballPersons.get(1).setX(forest.getWidth()-1);
            ballPersons.get(1).setY(forest.getHeight()-1);
            ballPersons.get(0).setX(0);
            ballPersons.get(0).setY(-1);
        }
        return validInput;
    }

    /**
     * Moves the people and draws model components based on new data
     * @param gc
     */
    public String update(GraphicsContext gc) {
        forest.draw(gc);//Forest should be drawn first as it is the bottom layer
        ballPersons.forEach(ballPerson -> {
            ballPerson.move(); // Each person moves
            ballPerson.draw(gc); // Each person draws itself
        });
        if (ballPersons.get(0).getX() == ballPersons.get(1).getX())
            if (ballPersons.get(0).getY() == ballPersons.get(1).getY()) {
                return "They found each other!";
            }
        return null;
    }
}
