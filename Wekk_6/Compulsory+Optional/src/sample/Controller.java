package sample;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private int toolSelected;

    private LinkedList<Shape> shapeList;

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private RadioButton rbtn1;
    @FXML
    private RadioButton rbtn2;
    @FXML
    private RadioButton rbtn3;
    @FXML
    private RadioButton rbtn4;

    private GraphicsContext graphicsContext;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toolSelected = 0;
        graphicsContext = canvas.getGraphicsContext2D();

        shapeList = new LinkedList<>();

        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        graphicsContext.setFill(Color.WHITE);

        graphicsContext.setStroke(colorPicker.getValue());
        graphicsContext.setLineWidth(Math.random()*1000%15+5);

        double[] topLeft  = new double[2];
        double[] botRight = new double[2];

        topLeft[0] = 0;
        topLeft[1] = 0;

        botRight[0] = canvas.getWidth();
        botRight[1] = canvas.getHeight();

        canvas.setOnMouseDragged(e -> {
            if ( toolSelected == 0) {
                double size = 25;
                double x = e.getX()-size/2;
                double y = e.getY()-size/2;

                graphicsContext.setFill(colorPicker.getValue());
                graphicsContext.fillOval(x,y,size,size);
            }else if( toolSelected == 5) {
                double x = e.getX();
                double y = e.getY();
                if( x > topLeft[0] && y > topLeft[1]) {
                    topLeft[0] = x;
                    topLeft[1] = y;
                }
                if( x < botRight[0] && y < botRight[1]) {
                    botRight[0] = x;
                    botRight[1] = y;
                }
            }
        });

        double []startingPoint = new double[2];
        double []endingPoint = new double[2];

        canvas.setOnMousePressed(e -> {
            if(toolSelected == 5) {
                startingPoint[0] = e.getX();
                startingPoint[1] = e.getY();
                System.out.println("Mouse pressed at " + startingPoint[0] + " " + startingPoint[1]);
            }
        });

        canvas.setOnMouseReleased(e -> {
            if(toolSelected == 5) {
                endingPoint[0] = e.getX();
                endingPoint[1] = e.getY();
                System.out.println("Mouse released at " + endingPoint[0] + " " + endingPoint[1]);
                double proximity = 50;
                if( Math.abs(startingPoint[0]-endingPoint[0]) < proximity && Math.abs(startingPoint[1]-endingPoint[1]) < proximity) {
                    //is a circle
                    double radius = Math.sqrt(Math.pow(topLeft[0]-botRight[0],2)+Math.pow(topLeft[1]-botRight[1],2))/2;
                    double x = (topLeft[0] + botRight[0])/2;
                    double y = (topLeft[1] + botRight[1])/2;
                    Shape shape = new Circle(x,y,radius,graphicsContext.getLineWidth(), graphicsContext.getStroke());
                    shape.draw(graphicsContext);
                    shapeList.addFirst(shape);

                    topLeft[0] = 0;
                    topLeft[1] = 0;

                    botRight[0] = canvas.getWidth();
                    botRight[1] = canvas.getHeight();
                }
                else {
                    //is a line
                    drawLine(startingPoint[0],startingPoint[1],endingPoint[0],endingPoint[1]);

                    topLeft[0] = 0;
                    topLeft[1] = 0;

                    botRight[0] = canvas.getWidth();
                    botRight[1] = canvas.getHeight();
                }
            }
        });

    }



    public void squareSelected(ActionEvent actionEvent) {
        toolSelected = 1;
    }

    public void triangleSelected(ActionEvent actionEvent) {
        toolSelected = 2;
    }

    public void circleSelected(ActionEvent actionEvent) {
        toolSelected = 3;
    }

    public void freeSelected(ActionEvent actionEvent) {
        toolSelected = 0;
    }

    public void deleteSelected(ActionEvent actionEvent) {
        toolSelected = 4;
    }

    public void shapeRecoSelected(ActionEvent actionEvent) {
        toolSelected = 5;
    }

    private void drawLine(double x0, double y0, double x1, double y1) {
        graphicsContext.strokeLine(x0,y0,x1,y1);
    }


    public void canvasClick(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        // 5 - 20
        double stroke = Math.random()*1000%15+5;

        graphicsContext.setLineWidth(stroke);
        graphicsContext.setStroke(colorPicker.getValue());
        double size = 100;

        Shape newShape;

        switch (toolSelected){
            case 1: //Square
                newShape = new Square(x,y,size, stroke, colorPicker.getValue());
                newShape.draw(graphicsContext);
                shapeList.addFirst(newShape);
                System.out.println("New shape loaded...");
                break;

            case 2: //Triangle
                newShape = new Triangle(x,y,size, stroke, colorPicker.getValue());
                newShape.draw(graphicsContext);
                shapeList.addFirst(newShape);
                System.out.println("New shape loaded...");
                break;

            case 3: //Circle
                newShape = new Circle(x,y,size, stroke, colorPicker.getValue());
                newShape.draw(graphicsContext);
                shapeList.addFirst(newShape);
                System.out.println("New shape loaded...");
                break;

            case 4: //Delete shape
                boolean somethingDeleted = false;
                for(var shape : shapeList) {
                    if(shape.isInside(x,y)) {
                        shapeList.remove(shape);
                        somethingDeleted = true;
                        System.out.println("I deleted something..");
                        break;
                    }
                }

                if(somethingDeleted) {
                    this.refreshBoard();
                }
                break;
        }
    }

    private void refreshBoard() {
        this.clearTheBoard();
        for( var shape : shapeList) {
            shape.draw(graphicsContext);
        }
    }

    public void load(ActionEvent actionEvent) {
        try {
            FileChooser fc = new FileChooser();

            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("PNG Files", "*.png"),
                    new FileChooser.ExtensionFilter("BMP Files", "*.bmp"),
                    new FileChooser.ExtensionFilter("GIF Files", "*.gif"));

            File file = fc.showOpenDialog(null);
            String path = file.getPath();
            Image img = new Image(file.toURI().toString());
            graphicsContext.drawImage(img,0,0);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }

    public void save(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG Files", "*.png"),
                new FileChooser.ExtensionFilter("BMP Files", "*.bmp"),
                new FileChooser.ExtensionFilter("GIF Files", "*.gif"));

        fc.setInitialFileName("img.png");

        File file = fc.showSaveDialog(null);

        try {
            Image snapshot = canvas.snapshot(null, null);
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void reset(ActionEvent actionEvent) {
        this.clearTheBoard();
        shapeList = new LinkedList<>();
    }

    private void clearTheBoard() {
        Paint aux = graphicsContext.getFill();
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        graphicsContext.setFill(aux);
    }

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }



}
