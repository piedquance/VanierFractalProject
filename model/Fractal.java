package FallProject.model;

public class Fractal {
    public static double radius = 3;
    public static int iterationLimit = 300;
    public static int breakpoint = 50;
    public static int iterate = 0;
    public static double scaling = 0.004;
    //Scaling value will sometimes not work, idk why. If it happens just change it and hope for the best.
    
    public static double k = 0;
    public static double h = 0.5;
    public static String name = "";
    
    public static void Formula(double x, double y, String type) {
        double outputX, outputY;
        
        double initialX =  x;  //Deja Vu
        double initialY = y;   //I've just been in this place before
        
        int[] temp = Plane.getCoord((int)Math.floor(x/Fractal.scaling), (int)Math.floor(y/Fractal.scaling));
        
        
        if(Fractal.iterate != 0) {
            initialY = temp[0]*scaling + k;
            initialX = temp[1]*scaling - h;
        } else {
            initialY = y + k;
            initialX = x - h;
        }
        
        //You can add your extended formula here
        
        switch(type) {
            case "Mandelbrot":
                outputY = initialX*initialX - initialY*initialY + x;
                outputX = 2*initialX*initialY + y;
                break;
            case "Failed Newton":
                outputX = initialX*initialX*initialX - 3*initialY*initialX - 1;
                outputY = 3*initialY*initialX*initialX - initialX;
                break;
            default:
                outputX = x;
                outputY = y;
                break; 
        }

       temp[0] = (int)Math.floor(outputX/Fractal.scaling);
       temp[1] = (int)Math.floor(outputY/Fractal.scaling);

       if(!FractalRender.CheckCoordOutOfRadius(outputX, outputY))Fractal.iterate++;
       
       temp[2] = Fractal.iterate;
    }
    
    
    
    
    
    
    
    
    
    
    
}
