import Controller.ControllerImpl;
import Model.Simulation;

/*
• Title: Lost in the Woods
• What the program does: Simulates two people walking randomly in the woods.  The woods can be any combination of
  length and width from 2 to 50.  Once the people find each other, results are recorded and the simulation stops.
  The simulation can be run multiple times with the same forest or a forest of a different size.  If the simulation
  is run with the same forest, the results will average all results for that particular size/area. The simulation
  can be paused by pushing the enter button when it is running.
• Program File:  Application.java contains the main method to run the program. The main module is called LostInTheWoods
• External Files Needed to Run:
  JavaFx Library - This dependency is managed and Maven
  Image file - forest.png for icon.
• External Files Program Creates:
  N/A
• Resources used to complete program
    - https://stackoverflow.com/questions/42895933/how-can-i-make-an-object-move-randomly
    - https://stackoverflow.com/questions/37829905/java-how-to-iterate-through-maps-in-order
    - https://www.tabnine.com/code/java/methods/javafx.scene.Scene/setOnKeyPressed

• Programmers:
    - Isabel Flores (isabelyflores@lewisu.edu)
    - Justin Keller (justinakeller@lewisu.edu)
• Course: CPSC 605000 Section 001
• Date Finished and Submitted:
 */

public class Application {

    /**
     * Main method that begins the process of setting up the three components necessary to run the simulation.
     * This method initializes the Simulation (Model).  A Controller is created with a reference to the simulation.
     * The controller will handle the creation of the view and starting the UI.
     * @param args Optional command line arguments.
     */
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        ControllerImpl controller = new ControllerImpl(simulation);
    }
}