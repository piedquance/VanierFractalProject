/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FallProject.view;

import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

/**
 * FXML Controller class
 *
 * @author Mykyta
 */
public class controller implements Initializable {

    @FXML
    private ImageView image;
    private VBox vbox;
    private HBox hbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    Rectangle2D screen = Screen.getPrimary().getBounds();

       image.setFitHeight(screen.getHeight());
       image.setFitWidth(screen.getWidth());
        
        WritableImage img = new WritableImage((int)image.getFitWidth(), (int)image.getFitHeight());
        PixelWriter writer = img.getPixelWriter();
   
        image.setImage(img);

        PixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteRgbInstance();
        
        byte imageData[] = new byte[(int)image.getFitWidth() * (int)image.getFitHeight() * 3];
         //HELLO
        int k = 0;
        double x = 0;
        double y = 0;
        double spacing = 0.1;
        for(int j = 0; j < (int)image.getFitHeight(); j++) {
            
            for(int i = 0; i < (int)image.getFitWidth(); i++) {
                
            x = 0 - (image.getFitWidth()/2 - i);
            x /= 1/spacing;   
            y = 0 + (image.getFitHeight()/2 - j);
            y /= 1/spacing;
            

            
            
            if(x%1 == 0 ) imageData[k] = (byte) 100;
            
            
            if(x*x == y) imageData[k+1] = (byte) 255;
            
            
            if(x == 0 || y == 0) imageData[k] = (byte) 255;
           k+= 3;
            }

        }
        
        
        writer.setPixels(0, 0, (int)image.getFitWidth(), (int)image.getFitHeight(), pixelFormat, imageData, 0, (int)image.getFitWidth()*3);
        

        
        image.setImage(img);
        

    }    
 
}
