package FallProject.model;

import java.util.HashSet;
import java.util.Set;

public class FractalRender {
    
    public static boolean CheckCoordOutOfRadius(double x,double y) {
                
        return Math.sqrt(x*x + y*y) > Fractal.radius ? true:false;
        
       }

    public static void iterate(int x,int y) {
       
        double xd = x * Fractal.scaling;
        double yd = y * Fractal.scaling;
        
       // System.out.println(xd + " " + yd);
        
        for(int n = 0; n<Fractal.iterationLimit; n++) {
            
             
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
        
        int colors = 24;
        //int[][] gradient = Gradient(colors);
        
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
        
         for(int y = 0; y < Plane.grid.length; y++) {
            for(int x = 0; x < Plane.grid[y].length; x++) {
                
                int temp[] = Plane.grid[y][x];
                                
                int mappedX =  0 - (Plane.grid[0].length/2 - x);
                int mappedY =  0 + (Plane.grid.length/2 - y);

                if (CheckCoordOutOfRadius(temp[0]*Fractal.scaling,  temp[1]*Fractal.scaling)) {
                    
                    int i = (temp[2] + 4)%(colors);

                    for(int n = 0; n < temp.length; n++) {
                        temp[n] = gradient[i][n];
                    }


                if(((mappedX)*Fractal.scaling)  == 0) temp[0] = 255;
                if(((mappedY)*Fractal.scaling)  == 0) temp[0] = 255;

                } else {
                        temp[0] = 0;
                        temp[1] = 0;
                        temp[2] = 0;
                    
                }
            }
        }
    }
    
    public static int[][] Gradient(int spacing) {
        int[][] gradient = new int[spacing][3];
        int level;
        int marker = 0;
        int step = (int) Math.ceil(spacing/6);
        
        for(int n = 0; n < 3; n++) {
            for(int m = 0; m < spacing; m++) {
                
                if(m == 0) level = 1;
                else level = (int) Math.ceil(m/6) - marker;
                if(level > 6) level -= 6;
                
                switch(level) {
                    case 1:case 2: 
                        gradient[m][n] = 0;
                        break;
                        
                    case 3:
                        gradient[m][n] = ((m%step) * 255 / step);
                        break;
                            
                    case 4:case 5:
                        gradient[m][n] =  255;
                        break;
                        
                    case 6:
                        gradient[m][n] = (255 - (m%step) * 255 / step);
                        break;
                      
                }
                

            }
            marker++;
        }
        return gradient;
    }
    
    
    public static byte[] GetRender() {
        iterateAll();
        addColor();
        return Plane.toByte();
    }

}