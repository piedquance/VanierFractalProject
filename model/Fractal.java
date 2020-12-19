package FallProject.model;

public class Fractal {

        public static String name = "Mandelbrot";

    public static double radius = "Newton".equals(name) ? 1999999999 : 2;
    public static int iterationLimit = 200;
    public static int breakpoint = 50;
    public static int iterate = 0;
    public static double scaling = 0.004;

    public static double k = 0;
    public static double h = 0;

    public static void Formula(double x, double y, String type) {
        double outputX, outputY;

        double initialX = x * Fractal.scaling;  
        double initialY = y * Fractal.scaling;   
        
        int[] temp = Plane.getCoord((int)x, (int)y);

        if (Fractal.iterate != 0) {
            initialY = temp[0] * scaling - k;
            initialX = temp[1] * scaling - h;
        } else {
            initialY = y * Fractal.scaling - k;
            initialX = x * Fractal.scaling - h;
        }

        
        switch (type) {

            case "Mandelbrot":
                outputY = (initialX * initialX - initialY * initialY + x* Fractal.scaling);
                outputX = (2 * initialX * initialY + y* Fractal.scaling);
                break;
            case "Newton":
                double z1 = initialX;
                double z2 = initialY;
                
                outputY = initialX - (((-Math.pow(z1,2) + Math.pow(z1, 5) + Math.pow(z2,2) + z1*Math.pow(z2,4)+ 2*z1*z1*z1*z2*z2) / (3*Math.pow(z1,4) + 3*Math.pow(z2,4)+ 6*z1*z1*z2*z2)));
                outputX = initialY - ((Math.pow(z2, 5) + z2*Math.pow(z1,4) + 2*z1*z1*z2*z2*z2 + 2*z1*z2) / (3*Math.pow(z1,4) + 3*Math.pow(z2,4)+ 6*z1*z1*z2*z2));
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
                double zx = initialX;
                double zy = initialY;

                outputX = 2 * zx * zy; 
                outputY = zx * zx - zy * zy + (1 - 1.6180339887);

                break;


            default:
                outputX = x * Fractal.scaling;
                outputY = y * Fractal.scaling;
                break;
        }
        

        
        temp[0] = (int) (Math.floor(outputX / Fractal.scaling));
        temp[1] = (int) (Math.floor(outputY / Fractal.scaling));

        if (!CheckCoord(outputX, outputY)) {
            Fractal.iterate++;
        }

        temp[2] = Fractal.iterate;
    }
        
    
    public static boolean CheckCoord(double x, double y) {
        switch(name) {
            case "Newton" : return Math.sqrt(Math.pow((x*x*x -3*y*y*x - 1),2) + Math.pow((3*x*x*y-x*x*x), 2))  > Fractal.radius ? true:false;

            case "Julia": 
                
            case "Mandelbrot": return Math.sqrt(x*x + y*y) > Fractal.radius ? true:false;
            
            default: return false;
        }
    }
    
    public static boolean Color() {
        switch(name) {
            case "Netwon" : return false;
            
            case "Julia":
                
            case "Mandelbrot": return true;
            
            default: return false;
        }
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

    public static double getScaling() {
        return Fractal.scaling;
    }

    public static void setScaling(double scaling) {
        Fractal.scaling = scaling;
    }

    public static int getBreakpoint() {
        return breakpoint;
    }
    public static void setBreakpoint(int breakpoint) {
        Fractal.breakpoint = breakpoint;
    }

    
}


