package sample;

import javafx.scene.canvas.GraphicsContext;

public interface Shape {
    boolean isInside(double x0, double y0);
    void draw(GraphicsContext graphicsContext);
}
