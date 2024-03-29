package FallProject.model;

import FallProject.view.controller;
import java.awt.Point;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

public class FractalRender {
    
    public static String gradientName = "6";
    
    static int[][] gradient = new int[24][3];
    
     static int[][] option1 = {
        {0, 255, 255},
        {0, 192, 255},
        {0, 128, 255},
        {0, 64, 255},
        {0, 0, 255},
        {64, 0, 255},
        {128, 0, 255},
        {192, 0, 255},
        {255, 0, 255},
        {255, 0, 192},
        {255, 0, 128},
        {255, 0, 64},
        {255, 0, 0},
        {255, 64, 0},
        {255, 128, 0},
        {255, 192, 0},
        {255, 255, 0},
        {192, 255, 0},
        {128, 255, 0},
        {64, 255, 0},
        {0, 255, 0},
        {0, 255, 64},
        {0, 255, 128},
        {0, 255, 192}
};
        static int[][] option2 = {
        {255, 192, 0},
        {255, 128, 0},
        {255, 64, 0},
        {255, 0, 0},
        {255, 0, 64},
        {255, 0, 128},
        {255, 0, 192},
        {255, 0, 255},
        {192, 0, 255},
        {128, 0, 255},
        {64, 0, 255},
        {0, 0, 255},
        {0, 64, 255},
        {0, 128, 255},
        {0, 192, 255},
        {0, 255, 255},
        {0, 255, 192},
        {0, 255, 128},
        {0, 255, 64},
        {0, 255, 0},
        {64, 255, 0},
        {128, 255, 0},
        {192, 255, 0},
        {255, 255, 0}
        };
       static int[][] option3 = {
        {255, 255, 255},
        {234, 234, 234},
        {213, 213, 213},
        {192, 192, 192},
        {171, 171, 171},
        {150, 150, 150},
        {129, 129, 129},
        {108, 108, 108},
        {87, 87, 87},
        {66, 66, 66},
        {45, 45, 45},
        {21, 21, 21},
        {0, 0, 0},
        {21, 21, 21},
        {45, 45, 45},
        {66, 66, 66},
        {87, 87, 87},
        {108, 108, 108},
        {129, 129, 129},
        {150, 150, 150},
        {171, 171, 171},
        {192, 192, 192},
        {213, 213, 213},
        {234, 234, 234},
        };
       static int[][] option4 = {
        {0, 0, 0},
        {21, 21, 21},
        {45, 45, 45},
        {66, 66, 66},
        {87, 87, 87},
        {108, 108, 108},
        {129, 129, 129},
        {150, 150, 150},
        {171, 171, 171},
        {192, 192, 192},
        {213, 213, 213},
        {234, 234, 234},
        {255, 255, 255},
        {234, 234, 234},
        {213, 213, 213},
        {192, 192, 192},
        {171, 171, 171},
        {150, 150, 150},
        {129, 129, 129},
        {108, 108, 108},
        {87, 87, 87},
        {66, 66, 66},
        {45, 45, 45},
        {21, 21, 21},
        {0, 0, 0},
        };
       static int[][] option5 = {
        {255, 255, 255},
        {244, 244, 244},
        {234, 234, 234},
        {223, 223, 223},
        {213, 213, 213},
        {202, 202, 202},
        {192, 192, 192},
        {181, 181, 181},
        {171, 171, 171},
        {160, 160, 160},
        {150, 150, 150},
        {139, 139, 139},
        {129, 129, 129},
        {118, 118, 118},
        {108, 108, 108},
        {97, 97, 97},
        {118, 87, 87},
        {139, 76, 76},
        {160, 66, 66},
        {181, 55, 55},
        {202, 45, 45},
        {223, 34, 34},
        {234, 21, 21},
        {255, 13, 13},
        };
       static int[][] option6 = {
        {0, 0, 0},
        {13, 13, 13},
        {21, 21, 21},
        {34, 34, 34},
        {45, 45, 45},
        {55, 55, 55},
        {66, 66, 66},
        {76, 76, 76},
        {87, 87, 87},
        {97, 97, 97},
        {108, 108, 108},
        {118, 118, 118},
        {129, 129, 129},
        {139, 139, 139},
        {150, 150, 150},
        {160, 160, 160},
        {171, 171, 171},
        {181, 181, 181},
        {192, 192, 192},
        {202, 202, 202},
        {213, 213, 213},
        {223, 223, 223},
        {237, 237, 237},
        {255, 255, 255},
        };


    //GetRender renders the selected fractal.
    public static byte[] GetRender() {

        if (Fractal.name.equals("Koch")) {

            SVGPath path = new SVGPath();

            paintComponent();
            path.setContent(string);
            
            path.setFill(Paint.valueOf("#FFF"));

            Group root = new Group(path);
     
            Scene scene = new Scene(root, controller.screenWidth, controller.screenHeight, Color.valueOf("#000"));
            
            WritableImage wi = new WritableImage((int) controller.screenWidth, (int) controller.screenHeight);
            scene.snapshot(wi);
            
            PixelReader reader = wi.getPixelReader();
            

             byte[] array = new byte[(int)controller.screenWidth * (int)controller.screenHeight * 10];
             
             reader.getPixels(0, 0, (int) controller.screenWidth, (int) controller.screenHeight, PixelFormat.getByteBgraInstance(), array, 0, (int) controller.screenWidth * 4);
             
            return array;
        } else {
            Plane.resetGrid();
            
            try {
            iterateAll();
            } catch(ArrayIndexOutOfBoundsException e) {
               String temp = String.valueOf(Fractal.scaling);
               temp += "1";
               Fractal.scaling = Double.parseDouble(temp);
               iterateAll();
            }
            
            addColor();
            return Plane.toByte();
        }

    }

    
    //This method iterates a coordinate until it reaches the limit of iterations or the coordinate escapes the radius after the breakpoint
    public static void iterate(int x, int y) {

        double xd = x * Fractal.scaling;
        double yd = y * Fractal.scaling;

        for (int n = 0; n < Fractal.iterationLimit; n++) {

            Fractal.Formula(x, y, Fractal.name);

            int temp[] = Plane.getCoord(x, y);

            if (Fractal.CheckCoord(temp[0], temp[1]) && n > Fractal.breakpoint) {
                break;
            }

        }
        Fractal.iterate = 0;
    }

    //This method iterates over every single coordinate
    public static void iterateAll() {

        for (int y = 0; y < Plane.grid.length; y++) {
            for (int x = 0; x < Plane.grid[y].length; x++) {

                int mappedX = 0 - (Plane.grid[0].length / 2 - x);
                int mappedY = 0 + (Plane.grid.length / 2 - y);

                iterate(mappedX, mappedY);
            }
        }
    }
    
    

    public static void setGradient() {
        switch(gradientName) {
            case "1" :
                FractalRender.gradient = FractalRender.option1;
                break;
            case "2":
                FractalRender.gradient = FractalRender.option2;
            break;
            case "3":
                FractalRender.gradient = FractalRender.option3;
            break;
            case "4":
                FractalRender.gradient = FractalRender.option4;
            break;
            case "5":
                FractalRender.gradient = FractalRender.option5;
            break;
            case "6":
                FractalRender.gradient = FractalRender.option6;
            break;
            default:
                System.out.println("No gradient found");
            break;
        }   
    }
       
    
    //This method assigns a rgb value to each pixel based on the iteration count.
    static public void addColor() {
        
        int colors = 24;

//Here we have the color gradient.
        setGradient();

        for (int y = 0; y < Plane.grid.length; y++) {
            for (int x = 0; x < Plane.grid[y].length; x++) {

                int temp[] = Plane.grid[y][x];

                int mappedX = 0 - (Plane.grid[0].length / 2 - x);
                int mappedY = 0 + (Plane.grid.length / 2 - y);

                if (Fractal.CheckCoord(temp[0], temp[1])) {

                    //We assign a color based on the number of iterations.
                    int i = (temp[2]) % (colors);

                    for (int n = 0; n < temp.length; n++) {
                        temp[n] = gradient[i][n];
                    }

                    if (((mappedX) * Fractal.scaling) == 0) {
                        temp[0] = 255;
                        temp[1] = 0;
                        temp[2] = 0;
                        
                    }
                    if (((mappedY) * Fractal.scaling) == 0) {
                        temp[0] = 255;
                        temp[1] = 0;
                        temp[2] = 0;
                    }
                    //If it's in the set, the pixel is black
                } else if (Fractal.Color()) {
                    temp[0] = 0;
                    temp[1] = 0;
                    temp[2] = 0;
                }
            }
        }
    }

    //This method creates the color gradient 
    public static int[][] Gradient(int spacing) {
        int[][] gradient = new int[spacing][3];
        int level;
        int marker = 0;
        int step = (int) Math.ceil(spacing / 6);

        for (int n = 0; n < 3; n++) {
            for (int m = 0; m < spacing; m++) {

                if (m == 0) {
                    level = 1;
                } else {
                    level = (int) Math.ceil(m / 6) - marker;
                }
                if (level > 6) {
                    level -= 6;
                }

                switch (level) {
                    case 1:
                    case 2:
                        gradient[m][n] = 0;
                        break;

                    case 3:
                        gradient[m][n] = ((m % step) * 255 / step);
                        break;

                    case 4:
                    case 5:
                        gradient[m][n] = 255;
                        break;

                    case 6:
                        gradient[m][n] = (255 - (m % step) * 255 / step);
                        break;
                }
            }
            marker++;
        }
        return gradient;
    }

    /*The Koch Fractal works entirely different from the others fractal. 
    As a result, we had to do a seperate tasks/methods for it*/
    private static double angle;

    private static String string = "";

    private static Point currPt, pt = new Point();

    private static void right(double x) {
        angle += x;
    }

    private static void left(double x) {
        angle -= x;
    }

    private static void drawFourLines(double side, int level) {
        if (level == 0) {
            pt.x = ((int) (Math.cos(angle * Math.PI / 180) * side)) + currPt.x;
            pt.y = ((int) (Math.sin(angle * Math.PI / 180) * side)) + currPt.y;

            string += " L " + pt.x + " " + pt.y;

            currPt.x = pt.x;
            currPt.y = pt.y;
        } else {
            drawFourLines(side / 3.0, level - 1);
            left(60);
            drawFourLines(side / 3.0, level - 1);
            right(120);
            drawFourLines(side / 3.0, level - 1);
            left(60);
            drawFourLines(side / 3.0, level - 1);
        }
    }
    
    private static int level = 1;

    
    public static int getLevel() {
        return level;
    }
    public static void setLevel(int level) {
        FractalRender.level = level;
    }
    

    public static void paintComponent() {

        int level = FractalRender.level;
        double side = 600;
        
        int s = (int)Math.sqrt(Math.pow(side, 2) - Math.pow(side/2, 2))/2;
        
        currPt = new Point((int)(controller.screenWidth/2-side/2),(int) (controller.screenHeight/2 - s + s/3));
        
        string = "M " + currPt.x + " " + currPt.y;
        
        angle = 0;
        for (int i = 1; i <= 3; i++) {
            drawFourLines(side, level);
            right(120);
        }
        string += " Z";
    }

}
