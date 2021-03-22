package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Triangle implements Shape{
    private double x;
    private double y;
    private double size;
    private double stroke;
    private Paint  color;


    public Triangle(double x, double y, double size, double stroke, Paint color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.stroke = stroke;
        this.color = color;
    }

    private double sign(double x1, double y1, double x2, double y2, double x3, double y3) {
        return (x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 - y3);
    }

    @Override
    public boolean isInside(double x0, double y0) {
        double x1, y1;
        double x2, y2;
        double x3, y3;
        double d1, d2, d3;
        boolean hasNeg, hasPos;


        x1 = x - size/2;
        y1 = y + size/2;

        x2 = x + size/2;
        y2 = y + size/2;

        x3 = x;
        y3 = y - size/2;

        d1 = this.sign(x0,y0,x1,y1,x2,y2);
        d2 = this.sign(x0,y0,x2,y2,x3,y3);
        d3 = this.sign(x0,y0,x3,y3,x1,y1);

        hasNeg = (d1 < 0) || (d2 < 0) || (d3 < 0);
        hasPos = (d1 > 0) || (d2 > 0) || (d3 > 0);

        return !(hasNeg && hasPos);
    }

    private void drawLine(GraphicsContext graphicsContext, double x0, double y0, double x1, double y1) {
        double aux = graphicsContext.getLineWidth();
        graphicsContext.setLineWidth(stroke);
        graphicsContext.strokeLine(x0,y0,x1,y1);
        graphicsContext.setLineWidth(aux);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        Paint temp = graphicsContext.getStroke();
        graphicsContext.setStroke(color);
        this.drawLine(graphicsContext, x-size/2,   y+size/2, x+size/2,y+size/2);
        this.drawLine(graphicsContext, x,y-size/2, x-size/2, y+size/2);
        this.drawLine(graphicsContext, x,y-size/2, x+size/2, y+size/2);
        graphicsContext.setStroke(temp);
    }
}
