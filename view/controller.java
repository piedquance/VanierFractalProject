package FallProject.view;

import FallProject.model.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class controller implements Initializable {

    
    @FXML
    private ImageView image;
    @FXML
    private MenuItem Help;
    @FXML
    private MenuItem About;
    @FXML
    private MenuItem IterationCount;
    @FXML
    private MenuItem Radius;
    @FXML
    private MenuItem Position;
    @FXML
    private MenuItem Axis;
    @FXML
    private MenuItem ColorGradient;
    @FXML
    private MenuItem FontSize;
    @FXML
    private TextField newItCount;
    @FXML
    private TextField newRadius;
    @FXML
    private TextField newH;
    @FXML
    private TextField newK;
    
    
    public static Stage secondaryStage = new Stage();
    
   public static int fontSize = 12;
    
    public static Rectangle2D screen = Screen.getPrimary().getBounds();

    //added a -100 to be able to view the menu bar.
    public static double screenHeight = screen.getHeight() - 80;
    public static double screenWidth = screen.getWidth();

    //public static double screenHeight = 600;
    //public static double screenWidth = 600;
    
     public WritableImage img = new WritableImage((int) screenWidth, (int) screenHeight);

    public PixelWriter writer = img.getPixelWriter();
    
    public PixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteRgbInstance();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if(this.image != null) {
        image.setFitHeight(screenHeight);
        image.setFitWidth(screenWidth);

        image.setImage(img);

        byte imageData[] = FractalRender.GetRender();

        writer.setPixels(0, 0, (int) image.getFitWidth(), (int) image.getFitHeight(), pixelFormat, imageData, 0, (int) image.getFitWidth() * 3);

        image.setImage(img);

        }
    }

    @FXML
    private void menuButtons(ActionEvent event) throws Exception {
        Parent root = new Pane();
        String title = " ";
        Scene scene = new Scene(root);
        


        if (event.getSource().equals(Help)) {
            root = FXMLLoader.load(getClass().getResource("help.fxml"));
            scene.getStylesheets().add(getClass().getResource("help.css").toExternalForm());
           // scene.getRoot().getChildrenUnmodifiable().get(0).setStyle("fx-font-size:" + fontSize +";");
            title = "Help";
            
        } else if (event.getSource().equals(About)) {
            root = FXMLLoader.load(getClass().getResource("about.fxml"));
            scene.getStylesheets().add(getClass().getResource("about.css").toExternalForm());
           // scene.getRoot().getChildrenUnmodifiable().get(0).setStyle("fx-font-size:" + fontSize +";");
            title = "About";
        }
        else if (event.getSource().equals(Axis)) {
            root = FXMLLoader.load(getClass().getResource("axis.fxml"));
//            scene.getStylesheets().add(getClass().getResource("axis.css").toExternalForm());
            title = "Axis";
        }
        else if (event.getSource().equals(IterationCount)) {
            root = FXMLLoader.load(getClass().getResource("iterationCount.fxml"));
//            scene.getStylesheets().add(getClass().getResource("iterationcount.css").toExternalForm());
            title = "Iteration Count";
        }
        else if (event.getSource().equals(Radius)) {
            root = FXMLLoader.load(getClass().getResource("radius.fxml"));
//            scene.getStylesheets().add(getClass().getResource("radius.css").toExternalForm());
            title = "Radius";
        }
        else if (event.getSource().equals(Position)) {
            root = FXMLLoader.load(getClass().getResource("position.fxml"));
//            scene.getStylesheets().add(getClass().getResource("position.css").toExternalForm());
            title = "Position";
        }
        else if (event.getSource().equals(ColorGradient)) {
            root = FXMLLoader.load(getClass().getResource("colorGradient.fxml"));
//            scene.getStylesheets().add(getClass().getResource("colorgradient.css").toExternalForm());
            title = "Color Gradient";
        }
        else if (event.getSource().equals(FontSize)) {
            root = FXMLLoader.load(getClass().getResource("fontSize.fxml"));
//            scene.getStylesheets().add(getClass().getResource("fontsize.css").toExternalForm());
            title = "Font Size";
        }
       
        
        
        
        
        scene = new Scene(root);

        
        
        
        secondaryStage.setTitle(title);
        secondaryStage.setScene(scene);
        
        if(event.getSource().equals(About) || event.getSource().equals(Help)) secondaryStage.getScene().getRoot().getChildrenUnmodifiable().get(0).setStyle("-fx-font-size:" + fontSize +";");
        
        secondaryStage.show();
        
               
        
    }

    private void printFractal() {

        image.setImage(img);

        byte imageData[] = FractalRender.GetRender();

        writer.setPixels(0, 0, (int) image.getFitWidth(), (int) image.getFitHeight(), pixelFormat, imageData, 0, (int) image.getFitWidth() * 3);

        image.setImage(img);
    }

    @FXML
    private void FractalHandlerJulia(ActionEvent event) {

        Fractal.name = "Julia";
        Fractal.radius = 2;
        printFractal();

    }

    @FXML
    private void FractalHandlerMandelbrot(ActionEvent event) {

        Fractal.name = "Mandelbrot";
        Fractal.radius = 2;
        printFractal();

    }

    @FXML
    private void FractalHandlerNewton(ActionEvent event) {

        Fractal.name = "Newton";
        Fractal.radius = 1999999999;
        printFractal();

    }

    @FXML
    private void FractalHandlerkKoch(ActionEvent event) {

        Fractal.name = "Koch";
        Fractal.radius = 2;
        printFractal();

    }

    @FXML
    private void ExitApplication(ActionEvent event) {
        Platform.exit();
    }
    
        
    @FXML
    private void closeWindow(ActionEvent event) {
    secondaryStage.close();
    }
    
    @FXML
    private void submitIterationCount(ActionEvent event){
        int iterationCount;
        iterationCount = Integer.parseInt(newItCount.getText());
        Fractal.setIterationLimit(iterationCount);
        
        secondaryStage.close();
        printFractal();
    }
    
    @FXML
    private void submitRadius(ActionEvent event){
        double radius;
        radius = Integer.parseInt(newRadius.getText());
        Fractal.setRadius(radius);
        
        secondaryStage.close();
        printFractal();
    }
    
    @FXML
    private void sumbitFontSize(ActionEvent event) {
  
        //this changes the text font size to given size.
        fontSize = 40;
        
        secondaryStage.close();
    }
    
    @FXML
    private void submitPosition(ActionEvent event){
        secondaryStage.close();
        
        double H;
        H = Integer.parseInt(newH.getText());
        Fractal.setH(H);
        double K;
        K = Integer.parseInt(newK.getText());
        Fractal.setK(K);
        
        //printFractal();
        
        Fractal.name = "Mandelbrot";

        printFractal();

//        image.setImage(img);
//
//       byte imageData[] = FractalRender.GetRender();
//
//        writer.setPixels(0, 0, (int) image.getFitWidth(), (int) image.getFitHeight(), pixelFormat, imageData, 0, (int) image.getFitWidth() * 3);
//
//        image.setImage(img);
    }
    
}
