import Controller.ControllerImpl;
import Model.Simulation;

/*
• Title: Lost in the Woods
• What the program does:
• Program File:
• External Files Needed to Run:

• External Files Program Creates:

• Resources used to complete program
    - https://stackoverflow.com/questions/42895933/how-can-i-make-an-object-move-randomly
    -


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