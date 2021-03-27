package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Circle implements Shape{
    private double x;
    private double y;
    private double radius;
    private double stroke;
    private Paint  color;


    public Circle(double x, double y, double radius, double stroke, Paint color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.stroke = stroke;
        this.color = color;
    }

    @Override
    public boolean isInside(double x0, double y0) {
        if(Math.sqrt(Math.pow(Math.abs(x-x0),2)+Math.pow(Math.abs(y-y0),2)) < radius) {
            return true;
        }
        return false;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        Paint temp = graphicsContext.getStroke();
        double aux = graphicsContext.getLineWidth();
        graphicsContext.setStroke(color);
        graphicsContext.setLineWidth(stroke);
        graphicsContext.strokeOval(x-radius/2,y-radius/2,radius,radius);
        graphicsContext.setStroke(temp);
        graphicsContext.setLineWidth(aux);
    }
}
