package Model;

/**
 * This class tracks data on the results of the simulations run for a given area.
 */
public class SimulationResult {

    private int area; //The area (width x height)
    private int numOfAttempts; //Total attempts regardless of success
    private int numOfTimesFound; //Total number of successful attempts
    private double avgMovesToFind; //Average moves to complete simulation for an area

    /**
     * Constructor for Simulation result.  Sets the record up for one attempt.
     * @param area
     * @param movesToFind
     * @param found
     */
    public SimulationResult(int area, int movesToFind, boolean found) {
        this.area = area;
        updateResults(movesToFind, found);
    }

    /**
     * Allows the record to be updated with subsequent attempts
     * @param movesToFind
     * @param found
     */
    public void updateResults(int movesToFind, boolean found){
        //Takes average value and multiplies by number of attempts.  This gets a total moves.
        //Then adds the next amount of moves.  Divides by a pre-incremented number of attempts.
        this.avgMovesToFind = (this.avgMovesToFind * numOfAttempts + movesToFind) / ++numOfAttempts;
        if (found) {
            numOfTimesFound++;
        }
    }

    public int getArea() {
        return area;
    }

    /**
     * Returns a percent of successful attempts out of the total number of attempts
     * @return
     */
    public int getPercentSuccessful() {
        //Integer division rounds values so its either 0 or 100.  Cast to a double as a consequence.
        return (int) Math.round((double)numOfTimesFound/(double)numOfAttempts*100);
    }

    public double getAvgMovesToFind() {
        return avgMovesToFind;
    }

    /**
     * Generates a string showing the summary of the result.
     * @return String representation of the result summary
     */
    @Override
    public String toString() {
        return "Area: " + area + "; % of Attempts Found: " + getPercentSuccessful() + "%; Avg. Moves Made: " + avgMovesToFind;
    }
}
