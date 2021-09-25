package Model;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private Forest forest; //Reference to the Forest in the simulation
    private List<Person> persons; //Reference to a list of people in the simulation that will wander.

    /**
     * The constructor for the simulation.  This creates the necessary elements in the simulation (A forest and a list of
     * two people).
     */
    public Simulation(){
        forest = new Forest();
        Person person1 = new Person();
        Person person2 = new Person();
        persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);
    }

    /**
     * This sets the forest size if the input is valid.
     * @param width Width of the forest (2 <= width <= 50)
     * @param height Height of the forest (2 <= height <= 50)
     * @return boolean representing valid input. true -> valid input
     */
    public boolean setup(int width, int height){
        return forest.setForestSize(width, height);
    }

    /**
     * Moves the people and draws model components based on new data
     * @param gc
     */
    public void update(GraphicsContext gc) {
        forest.draw(gc);//Forest should be drawn first as it is the bottom layer
//        persons.forEach(person -> {
//            person.move()); // Each person moves
//            person.paint(); // Each person draws itself
//        }
    }

}
