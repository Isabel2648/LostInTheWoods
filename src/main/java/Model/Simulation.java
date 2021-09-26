package Model;

import javafx.scene.canvas.GraphicsContext;

import java.util.*;

/**
 * This class manages the items necessary to run the simulation of two people randomly wandering a forest to find
 * each other.
 */
public class Simulation {

    private Forest forest; //Reference to the Forest in the simulation
    private List<BallPerson> ballPersons; //Reference to a list of people in the simulation that will wander.
    private Map<Integer, SimulationResult> results; //Summary of previous simulation results.
    private int moves; //Tracks how many moves the simulation has gone through.  Used to stop simulation at a designated amoun
    private static final int MAXMOVES = 1000000; //Represents the number of maximum moves the simulation can run for.

    /**
     * The constructor for the simulation.  This creates the necessary elements in the simulation (A forest, a list of
     * two people, and a SimulationResult collection).
     */
    public Simulation(){
        forest = new Forest();
        BallPerson ballPerson1 = new BallPerson(0,0, forest); //Starting positions
        BallPerson ballPerson2 = new BallPerson(1, 1, forest);
        ballPersons = new ArrayList<>(); //Array list is used to referen
        ballPersons.add(ballPerson1);
        ballPersons.add(ballPerson2);
        this.results = new TreeMap<>(); //Tree maps return values in the order of the key (Integer area)
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
            ballPersons.get(0).setY(0);
            moves = 0;
        }
        return validInput;
    }

    /**
     * Handles the necessary things that happen each time the simulation is updated.
     * Moves the people, draws model components based on new positions, and checks if the people found each other.
     * @param gc
     */
    public String update(GraphicsContext gc) {
        if (moves < MAXMOVES) { //Only update if there are still moves to make.
            this.move();
            this.draw(gc);
            moves++;
            return this.simulationFinished();
        }
        return null;
    }

    //TODO Area to consider for refactoring.  Far too many things are going on in this method beyond checking if the people
    // are in the same spot and therefore found.
    /**
     * Checks if the simulation is finished (Either the two people found each other or the max # of moves is reached.
     * @return Message to display to the user. If null is returned, the simulation isn't finished.
     */
    public String simulationFinished() {
        StringBuilder message = new StringBuilder();
        //If the two people occupy the same location, then create a message to display the simulation results.
        if (ballPersons.get(0).getX() == ballPersons.get(1).getX())
            if (ballPersons.get(0).getY() == ballPersons.get(1).getY()) {
                message.append("They found each other! To run simulation again, change the width " +
                        "and height if desired and click submit.\n\n");
                message.append("Result for this Round:\n");
                message.append(this.addResults(true) + "\n\n");
                message.append("Summary of All Rounds:\n");
                message.append(this.getResults());
                return message.toString();
            }
        if (moves >= MAXMOVES){
                message.append("Ran out of moves... The people were eternally lost. To run simulation again, change the width " +
                        "and height if desired and click submit.\n\n");
                message.append("Result for this Round:\n");
                message.append(this.addResults(false)+ "\n\n");
                message.append("Summary of All Rounds:\n");
                message.append(this.getResults());
                return message.toString();
        }
        return null;//Simulation isn't finished;
    }

    /**
     * Used to move each person (or attempt to move since they won't move if they go out of the forest)
     */
    private void move(){
        ballPersons.forEach(ballPerson -> {
            ballPerson.move(); // Each person attempts to move
        });
    }

    /**
     * Draws each model component.
     * @param gc Graphics Context to draw on.
     */
    public void draw(GraphicsContext gc){
        forest.draw(gc);//Forest should be drawn first as it is the bottom layer
        ballPersons.forEach(ballPerson -> {
            ballPerson.draw(gc); // Each person draws itself
        });
    }

    /**
     * Adds a new record to the results if a key doesn't exist for the forest area.  If a results
     * record already exists for the area, then the result is updated with the new attempt's results.
     * @param found Whether the people found each other or not at the end of the simulation.
     * @return String representation of the most recent attempts results.
     */
    public String addResults (boolean found){
        Integer area = forest.getWidth() * forest.getHeight();
        if (results.containsKey(area)) {
            SimulationResult existingResult = results.get(area); //Retrieves existing result.
            existingResult.updateResults(moves,found); //Updates the existing results data.
        } else {
            //Creates new SimulationResult and adds it to the map.
            SimulationResult newResult = new SimulationResult(area, this.moves, found);
            results.put(area, newResult);
        }
        return "Area: " + area + "; People Found Each Other?: " + found + "; Moves Made: " + moves;
    }

    /**
     * Gets all results related to the simulation since starting.
     * @return String of all results
     */
    public String getResults (){
        StringBuilder resultsBuilder = new StringBuilder(); //Used to construct string message
        Collection<SimulationResult> collectionResults = results.values(); //Gets a collection to iterate over
        for (SimulationResult result : collectionResults) {
            resultsBuilder.append(result + "\n");  //Each result summary is displayed on its own line.
        }
        return resultsBuilder.toString();
    }
}
