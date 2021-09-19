package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Scanner;

public class Forest implements Draw {

    private int height;
    private int width;
    private final int PADDING = 10;
    private int cellSize;
    private final int CANVASSIZE = 520;

    private List<Person> wanderingPeople;

    public Forest(int width, int height){
        this.width = width;
        this.height = height;
        Scanner scanner ;
    }

    public int getHeight() { return height; }

    public boolean setHeight(int height) {
        boolean validInput = true;
        if (height >= 2 && height <= 50) {
            this.height = height;
        } else {
            validInput = false;
        }
        return validInput;
    }

    public int getWidth() { return width; }

    public boolean setWidth(int width) {
        boolean validInput = true;
        if (width >= 2 && width <= 50) {
            this.width = width;
        } else {
            validInput = false;
        }
        return validInput;
    }

    public List<Person> getWanderingPeople() { return wanderingPeople; }

    public void setWanderingPeople(List<Person> wanderingPeople) { this.wanderingPeople = wanderingPeople; }

    @Override
    public void draw(GraphicsContext gc) {
        int paddingX;
        int paddingY;

        gc.setFill(Color.BLACK);
        cellSize = (CANVASSIZE - PADDING * 2) / Math.max(width, height);
        for (int row = 0; row < height; row++){
            for (int column = 0; column < width; column++){
                if (Math.max(width, height) == width){
                    paddingX = PADDING;
                    paddingY = (CANVASSIZE - height * cellSize) / 2 + PADDING;
                } else {
                    paddingX = (CANVASSIZE - width * cellSize) / 2 + PADDING;
                    paddingY = PADDING;
                }
                gc.strokeRect(paddingX + column * cellSize, paddingY + row * cellSize, cellSize, cellSize);
            }
        }
    }

}
