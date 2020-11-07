/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FallProject.model;

import java.util.ArrayList;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 *
 * @author Mykyta
 */
public class Plane {
    public ArrayList grid = new ArrayList();
    
    
    
    public Plane() {
        Rectangle2D screen = Screen.getPrimary().getBounds();
        
        grid = new ArrayList((int)screen.getWidth());
            
    }
}
