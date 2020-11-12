package FallProject.view;

import FallProject.model.*;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class controller implements Initializable {

    @FXML private ImageView image;
    @FXML private MenuItem Help;
    @FXML private MenuItem About;
    
    public static Rectangle2D screen = Screen.getPrimary().getBounds();
    
    //added a -100 to be able to view the menu bar.
    public static double screenHeight = screen.getHeight() - 80;
    public static double screenWidth = screen.getWidth();

    //public static double screenHeight = 600;
    //public static double screenWidth = 600;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       image.setFitHeight(screenHeight);
       image.setFitWidth(screenWidth);
        
        WritableImage img = new WritableImage((int)image.getFitWidth(), (int)image.getFitHeight());
        
        PixelWriter writer = img.getPixelWriter();
   
        image.setImage(img);

        PixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteRgbInstance();
        
        byte imageData[] = FractalRender.GetRender();
       
        writer.setPixels(0, 0, (int)image.getFitWidth(), (int)image.getFitHeight(), pixelFormat, imageData, 0, (int)image.getFitWidth()*3);
        
        image.setImage(img);

    } 
    
    @FXML
    private void menuButtons(ActionEvent event) throws Exception{
        Parent root = new Pane();
        
        String title = " ";
        if(event.getSource().equals(Help)){
            root = FXMLLoader.load(getClass().getResource("view/help.fxml"));
            title = "Help";
        }
        else if(event.getSource().equals(About)){
                title = "About";
        }
        
        Stage secondaryStage = new Stage();
        Scene scene = new Scene(root);
        
        
        secondaryStage.setTitle(title);
        secondaryStage.setScene(scene);
        secondaryStage.show();
        
    }
}
