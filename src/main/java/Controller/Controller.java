package Controller;

import javafx.event.ActionEvent;
import javafx.scene.canvas.GraphicsContext;

public interface Controller {
    void update(GraphicsContext gc);
    boolean setUp(ActionEvent e, int width, int height);
}