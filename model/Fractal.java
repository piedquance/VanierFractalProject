package FallProject.model;

public class Fractal {

    public static double radius = 3;
    public static int iterationLimit = 300;
    public static int breakpoint = 50;
    public static int iterate = 0;
    public static double scaling = 0.004;
    //0.004 gives no scale lines
    //Scaling value will sometimes not work, idk why. If it happens just change add a 1 and hope for the best

    public static double k = 0;
    public static double h = 0.5;
    public static String name = "";

    public static void Formula(double x, double y, String type) {
        double outputX, outputY;

        double initialX = x;  //Deja Vu
        double initialY = y;   //I've just been in this place before
                               //Lmao
        int[] temp = Plane.getCoord((int) Math.floor(x / Fractal.scaling), (int) Math.floor(y / Fractal.scaling));

        if (Fractal.iterate != 0) {
            initialY = temp[0] * scaling - k;
            initialX = temp[1] * scaling - h;
        } else {
            initialY = y - k;
            initialX = x - h;
        }

        //You can add your extended formula here
        switch (type) {
//Mykyta
            case "Mandelbrot":
                outputY = initialX * initialX - initialY * initialY + x;
                outputX = 2 * initialX * initialY + y;
                break;
            case "Failed Newton":
                outputX = initialX * initialX * initialX - 3 * initialY * initialX - 1;
                outputY = 3 * initialY * initialX * initialX - initialX;
                break;
            case "Cos Blocks":
                outputX = x / Math.cos(x + initialX);
                outputY = y / Math.cos(y + initialY);
                break;
            case "Sin Blocks":
                outputX = x / Math.sin(x + initialX);
                outputY = y / Math.sin(y + initialY);
                break;
            case "Tan Blocks":
                outputX = x / Math.tan(x + initialX);
                outputY = y / Math.tan(y + initialY);
                break;
            case "A":
                outputX = x / Math.cos(initialX);
                outputY = y / Math.cos(initialY);
                break;
            case "Julia":
                outputX = initialX * initialX - initialY * initialY + 0.285*initialX; //just to handle it 
                outputY = 2* initialX * initialY + 0.285*initialY;

                break;
//// 

            default:
                outputX = x;
                outputY = y;
                break;
        }
        //Don't forget break; statement!

        temp[0] = (int) (Math.floor(outputX/ Fractal.scaling));
        temp[1] = (int) (Math.floor(outputY/ Fractal.scaling));

        if (!FractalRender.CheckCoordOutOfRadius(outputX, outputY)) {
            Fractal.iterate++;
        }

        temp[2] = Fractal.iterate;
    }

    
    
    public static double getRadius() {
        return radius;
    }
    public static void setRadius(double radius) {
        Fractal.radius = radius;
    }

    
    public static int getIterationLimit() {
        return iterationLimit;
    }
    public static void setIterationLimit(int iterationLimit) {
        Fractal.iterationLimit = iterationLimit;
    }

    
    public static double getK() {
        return Fractal.k;
    }
    public static void setK(double k) {
        Fractal.k = k;
    }

    
    public static double getH() {
        return Fractal.h;
    }
    public static void setH(double h) {
        Fractal.h = h;
    }
}
