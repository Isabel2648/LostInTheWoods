package View;

import javafx.scene.image.Image;
import Controller.Controller;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;

public class View {

    Controller controller;
    Stage stage;
    StackPane root;
    Scene theScene;
    Canvas gameCanvas;
    GraphicsContext gc;
    Button submitSize;
    TilePane buttonPane;
    VBox vBox1;
    Label instructions;
    private boolean simulationRunning;
    private int moves = 0;
    private TextField width;
    private TextField height;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void start(Stage theStage) {
        this.stage = theStage;
        createView(stage);
        submitSize.setOnAction(e -> {
            String widthText = width.getText();
            String heightText = height.getText();
            if (isInt(widthText) && isInt(heightText)){
                controller.setUp(e, Integer.parseInt(widthText), Integer.parseInt(heightText));
            } else {
                setInstructions("Invalid Input: Please enter a width and height for the forest between 2 and 50.");
                Toolkit.getDefaultToolkit().beep();
            }
        });

        theStage.show();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                if (moves > 10000)
                    setSimulationRunning(false);
                gc.clearRect(0, 0, 512, 512);
                if (simulationRunning){
                    controller.update(gc);
                    moves++;
                }
            }
        }.start();

    }

    //https://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
    static boolean isInt(String s)  // assuming integer is in decimal number system
    {
        if (s.isBlank())
            return false;
        for(int a=0;a<s.length();a++)
        {
            if( !Character.isDigit(s.charAt(a)) ) return false;
        }
        return true;
    }

    public void createView(Stage theStage) {
        theStage.setTitle( "Lost in the Woods" );
        //https://www.pinclipart.com/pindetail/oTwJ_free-forest-clipart-free-forest-clipart-at-getdrawings/
        theStage.getIcons().add(new Image("forest.png"));

        root = new StackPane();
        theScene = new Scene(root);
        theStage.setScene(theScene);

        gameCanvas = new Canvas(520, 520);
        gc = gameCanvas.getGraphicsContext2D();

        submitSize = new Button("Submit");

        instructions = new Label("Please enter a width and height for the forest between 2 and 50.");
        instructions.setWrapText(true);
        instructions.setPadding(new Insets(10,10,10,10));

        Label widthLabel = new Label("Width");
        width = new TextField ();
        Label heightLabel = new Label("Height");
        height = new TextField ();

        HBox input = new HBox();
        input.setSpacing(10);
        input.setPadding(new Insets(10,10,10,10));
        input.setAlignment(Pos.BOTTOM_CENTER);
        input.getChildren().addAll(widthLabel, width, heightLabel, height, submitSize);


        vBox1 = new VBox(10);
        vBox1.setAlignment(Pos.CENTER);
        vBox1.getChildren().addAll(instructions, gameCanvas, input);

        root.getChildren().addAll(vBox1);

    }

    public void setSimulationRunning(boolean running){
        simulationRunning = running;
    }

    public void setInstructions(String instructions) {
        this.instructions.setText(instructions);
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

}

