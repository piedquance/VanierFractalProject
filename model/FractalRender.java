package FallProject.model;

import FallProject.view.controller;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javax.imageio.ImageIO;

public class FractalRender {

    //GetRender renders the selected fractal.
    public static byte[] GetRender() {

        if (Fractal.name.equals("Koch")) {

            SVGPath path = new SVGPath();

            paintComponent();
            path.setContent(string);
            
            path.setFill(Paint.valueOf("#0AF"));

            Group root = new Group(path);
     
            Scene scene = new Scene(root, controller.screenWidth, controller.screenHeight, Color.valueOf("#0FF"));
            
            WritableImage wi = new WritableImage((int) controller.screenWidth, (int) controller.screenHeight);
            scene.snapshot(wi);
            
            PixelReader reader = wi.getPixelReader();
            

             byte[] array = new byte[(int)controller.screenWidth * (int)controller.screenHeight * 10];
             
             reader.getPixels(0, 0, (int) controller.screenWidth, (int) controller.screenHeight, PixelFormat.getByteBgraInstance(), array, 0, (int) controller.screenWidth * 4);
             
            return array;
            //System.out.println(string);
        } else {
            iterateAll();
            addColor();
            return Plane.toByte();
        }

    }

    //You know what this does
    //This method iterates a coordinate until it reaches the limit of iterations or the coordinate escapes the radius after the breakpoint
    public static void iterate(int x, int y) {

        double xd = x * Fractal.scaling;
        double yd = y * Fractal.scaling;

        for (int n = 0; n < Fractal.iterationLimit; n++) {

            Fractal.Formula(xd, yd, Fractal.name);

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

    //This method assigns a rgb value to each pixel based on the iteration count.
    public static void addColor() {

        int colors = 24;
        //int[][] gradient = Gradient(colors);

//Here we have the color gradient.
        int[][] gradient = {
            {0, 255, 0},
            {0, 255, 64},
            {0, 255, 128},
            {0, 255, 192},
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
            {64, 255, 0}
        };

//        for(int[] m: gradient){
//            for(int n: m) System.out.print(n + " ");
//            System.out.println("");
//        }
        for (int y = 0; y < Plane.grid.length; y++) {
            for (int x = 0; x < Plane.grid[y].length; x++) {

                int temp[] = Plane.grid[y][x];

                int mappedX = 0 - (Plane.grid[0].length / 2 - x);
                int mappedY = 0 + (Plane.grid.length / 2 - y);

                if (Fractal.CheckCoord(temp[0] * Fractal.scaling, temp[1] * Fractal.scaling)) {

                    //We assign a color based on the number of iterations.
                    int i = (temp[2] + 4) % (colors);

                    for (int n = 0; n < temp.length; n++) {
                        temp[n] = gradient[i][n];
                    }

                    if (((mappedX) * Fractal.scaling) == 0) {
                        temp[0] = 255;
                    }
                    if (((mappedY) * Fractal.scaling) == 0) {
                        temp[0] = 255;
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

    //This method attempts to generate a gradient. It doesn't work very well.
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

    //Koch
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

    public static void paintComponent() {

        int level = 5;
        double side = 600;
        
        int s = (int)Math.sqrt(Math.pow(side, 2) - Math.pow(side/2, 2))/2;
        
        currPt = new Point((int)(controller.screenWidth/2-side/2),(int) (controller.screenHeight/2 - s + s/3));
        
        string += "M " + currPt.x + " " + currPt.y;
        
        angle = 0;
        for (int i = 1; i <= 3; i++) {
            drawFourLines(side, level);
            right(120);
        }
        string += " Z";
        System.out.println(string);
    }

}
