import Controller.ControllerImpl;
import Model.Model;

/*
• Title: Lost in the Woods
• What the program does:
• Program File:
• External Files Needed to Run:

• External Files Program Creates:

• Resources used to complete program
    -
    -


• Programmers:
    - Isabel Flores (isabelyflores@lewisu.edu)
    - Justin Keller (justinakeller@lewisu.edu)
• Course: CPSC 605000 Section 001
• Date Finished and Submitted:
 */

public class Application {

    public static void main(String[] args) {
        Model model = new Model();
        ControllerImpl controller = new ControllerImpl(model);
    }
}