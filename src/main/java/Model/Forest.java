package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The Forest handles data and methods related to a grid of forest cells
 */
public class Forest implements Draw {

    private int height; // Represents the height or number of cells in the y direction
    private int width; // Represents the width or number of cells in the x direction
    private final int PADDING = 10; // Minimum amount of padding to use when drawing
    private int cellSize; // The size of each cell of the forest
    private final int CANVASSIZE = 520; //The size of the graphics context canvas
    private int paddingX; // The actual padding in the x direction.  Will not be smaller than PADDING
    private int paddingY; // The actual padding in the y direction.  Will not be smaller than PADDING

    /**
     * Constructs the forest with the minimum acceptable height and width.
     */
    public Forest(){
        this.width = 2;
        this.height = 2;
    }

    /**
     * This sets both the width and height.  If both values are valid input, then both values are set
     * @param width of the forest
     * @param height of the forest
     * @return returns whether the input was valid.  true -> valid input
     */
    public boolean setForestSize(int width, int height) {
        if (setWidth(width) && setHeight(height)) { //TODO Potential bug if width is valid/set but height isn't valid
            setCellSizeandPadding();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Getter for height
     * @return height of the forest
     */
    public int getHeight() { return height; }

    /**
     * Private setter for the height.  Width and height should be set at the same time so setForestSize is the public
     * method for setting the height.  Checks for valid input as well (2 <= height <= 50)
     * @param height Height of the forest.
     * @return Returns whether the input was valid. true -> valid input
     */
    private boolean setHeight(int height) {
        boolean validInput = true;
        if (height >= 2 && height <= 50) {
            this.height = height;
        } else {
            validInput = false;
        }
        return validInput;
    }

    /**
     * Getter for width
     * @return Width of the forest
     */
    public int getWidth() { return width; }

    /**
     * Private setter for the width.  Width and height should be set at the same time so setForestSize is the public
     * method for setting the width.  Checks for valid input as well (2 <= width <= 50)
     * @param width Width of the forest.
     * @return Returns whether the input was valid. true -> valid input
     */
    private boolean setWidth(int width) {
        boolean validInput = true;
        if (width >= 2 && width <= 50) {
            this.width = width;
        } else {
            validInput = false;
        }
        return validInput;
    }

    /**
     * Getter for cellSize
     * @return Cell size based on the height and width of the forest
     */
    public int getCellSize () {
        return cellSize;
    }

    /**
     * Dyanmically sets the cell size and x and y padding based on the Canvas size of the graphics context and the
     * width and height of the forest.  This uses as much space of the graphics context as possible.
     */
    private void setCellSizeandPadding() {
        cellSize = (CANVASSIZE - PADDING * 2) / Math.max(width, height);
        if (Math.max(width, height) == width){
            paddingX = PADDING;
            paddingY = (CANVASSIZE - height * cellSize) / 2 + PADDING;
        } else {
            paddingX = (CANVASSIZE - width * cellSize) / 2 + PADDING;
            paddingY = PADDING;
        }
    }

    /**
     * Draws a representation of each forest cell
     * @param gc Reference to the graphics context to draw the forest on.
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        //Loops through each cell in a row from left to right before going down the next column.  Draws a box at each cell.
        for (int row = 0; row < height; row++){
            for (int column = 0; column < width; column++){
                gc.strokeRect(paddingX + column * cellSize, paddingY + row * cellSize, cellSize, cellSize);
            }
        }
    }

    public int getPaddingX() {
        return paddingX;
    }

    public void setPaddingX(int paddingX) {
        this.paddingX = paddingX;
    }

    public int getPaddingY() {
        return paddingY;
    }

    public void setPaddingY(int paddingY) {
        this.paddingY = paddingY;
    }
}
