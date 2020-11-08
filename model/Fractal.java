package FallProject.model;

public class Fractal {
    public static double radius = 3;
    public static int iterationLimit = 100;
    public static int iterate = 0;
    public static double scaling = 0.006;
    
    public static void Mandelbrot(double x, double y) {
        double outputX, outputY;
        
        double initialX =  x;  //Deja Vu
        double initialY = y;   //I've just been in this place before
        
        int[] temp = Plane.getCoord((int)Math.floor(x/Fractal.scaling), (int)Math.floor(y/Fractal.scaling));
        
        
        if(Fractal.iterate != 0) {
            initialY = temp[0]*scaling;
            initialX = temp[1]*scaling;;
          // if (FractalRender.CheckCoordOutOfRadius(temp[0]*Fractal.scaling,  temp[1]*Fractal.scaling)) System.out.println("yes");
        } else {
            initialY = y;
            initialX = x;
        }
        
      // outputX = x+10;
      // outputY = y;
       


        outputY = initialX*initialX - initialY*initialY + x;
        outputX = 2*initialX*initialY + y;
        
        
       temp[0] = (int)Math.floor(outputX/Fractal.scaling);
       temp[1] = (int)Math.floor(outputY/Fractal.scaling);
       
      //  System.out.println(temp[0]);
       
      
       
       if(!FractalRender.CheckCoordOutOfRadius(outputX, outputY)) {
           

       Fractal.iterate++;
       } else {
           
       }
        
       temp[2] = Fractal.iterate;
    }
}