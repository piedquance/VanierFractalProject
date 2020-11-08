package FallProject.model;

import FallProject.view.controller;

public class Plane {

    public static int grid[][][] = new int[(int)controller.screenHeight][(int)controller.screenWidth][3];


    public Plane() {
       for(int n: grid[0][0]) System.out.println(n);
            
    }
    
    public static void setCoord(int x, int y, int[] value) {
        int mappedX = x + grid[0].length/2;
        int mappedY = grid.length/2 - y;
        
        for(int n = 0; n < value.length; n++) grid[mappedY][mappedX][n] = value[n];
    }
    
    
    public static int[]  getCoord(int x, int y) {
        int mappedX = x + grid[0].length/2;
        int mappedY = grid.length/2 - y;
        
        return grid[mappedY][mappedX];
    }
    
    
    public static byte[] toByte() {
        byte[] stream = new byte[(int)controller.screenWidth * (int)controller.screenHeight * 3];
        int marker = 0;
        for(int[][] n : grid) for(int[] m : n) for(int o : m) {
                    stream[marker] = (byte)o;       
                    marker++;
                }
        return stream;
    }
    
    
    
    public int[][][] getGrid() {
        return this.grid;
    }

    public void setGrid(int[][][] grid) {
        this.grid = grid;
    }
}
