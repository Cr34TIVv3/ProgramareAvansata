package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Square implements Shape{
    private double x;
    private double y;
    private double size;
    private double stroke;
    private Paint color;


    public Square(double x, double y, double size, double stroke, Paint color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.stroke = stroke;
        this.color = color;
    }

    @Override
    public boolean isInside(double x0, double y0) {
        double xBoundLeft = x - size/2;
        double xBoundRight = x + size/2;
        if(x0 < xBoundLeft || x0 > xBoundRight) {
            return  false;
        }

        double yBoundLeft = y - size/2;
        double yBoundRight = y + size/2;

        if(y0 < yBoundLeft || y0 > yBoundRight) {
            return  false;
        }

        return true;
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
        this.drawLine(graphicsContext, x-size/2,y-size/2,x+size/2,y-size/2);
        this.drawLine(graphicsContext, x-size/2,y-size/2,x-size/2,y+size/2);
        this.drawLine(graphicsContext, x+size/2,y+size/2,x+size/2,y-size/2);
        this.drawLine(graphicsContext, x+size/2,y+size/2,x-size/2,y+size/2);
        graphicsContext.setStroke(temp);
    }


}
