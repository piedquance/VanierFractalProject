package FallProject.model;

import java.util.HashSet;
import java.util.Set;

public class FractalRender {
    
    public static boolean CheckCoordOutOfRadius(double x,double y) {
        
      //  System.out.println(x + " " + y);
        
        return Math.sqrt(x*x + y*y) > Fractal.radius ? true:false;
        
       }

    public static void iterate(int x,int y) {
       
        double xd = x * Fractal.scaling;
        double yd = y * Fractal.scaling;
        
       // System.out.println(xd + " " + yd);
        
        for(int n = 0; n<Fractal.iterationLimit; n++) {
            
          Fractal.iterate = n;
            // System.out.println(temp[2]+ " " + Fractal.iterationLimit);
             
          Fractal.Mandelbrot(xd, yd);
          
        }
        
        Fractal.iterate = 0;
        
    }
    
    
    public static void iterateAll(){
        
        //System.out.println(Plane.grid[0].length);
        
        for(int y = 0; y < Plane.grid.length; y++) {
            for(int x = 0; x < Plane.grid[y].length; x++) {
                
                int mappedX =  0 - (Plane.grid[0].length/2 - x);
                int mappedY =  0 + (Plane.grid.length/2 - y);
                
               // System.out.println(x);
                
                
               // System.out.println(mappedX + " " + mappedY);

                iterate(mappedX, mappedY);
            }
        }
        
    }
    
    
    public static void addColor() {
         for(int y = 0; y < Plane.grid.length; y++) {
            for(int x = 0; x < Plane.grid[y].length; x++) {
                
                int temp[] = Plane.grid[y][x];
                                
                int mappedX =  0 - (Plane.grid[0].length/2 - x);
                int mappedY =  0 + (Plane.grid.length/2 - y);
                
                
                //System.out.println(temp[0]);
                
               // temp[1] = 255;
                
              //  Plane.setCoord((int)(temp[0]*Fractal.scaling), (int)(temp[1]*Fractal.scaling), new int[]{255, 0, 0});

                
                if (CheckCoordOutOfRadius(temp[0]*Fractal.scaling,  temp[1]*Fractal.scaling)) {
                    temp[0] = 0;
                    temp[1] = 0;
                    temp[2] = temp[2];
                } else {
                    temp[0] = 0;
                    temp[1] = 0;
                    temp[2] = 0;
                    
                }
                
                if((mappedX*Fractal.scaling)%1 == 0) temp[0] = 255;
                
                if((mappedY*Fractal.scaling)%1 == 0) temp[0] = 255;
                

                
            }
        }
    }
    
    
    public static byte[] GetRender() {
        iterateAll();
        addColor();
        return Plane.toByte();
    }

}