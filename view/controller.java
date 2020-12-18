package FallProject.view;

import FallProject.model.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
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
import javafx.util.Duration;

public class controller implements Initializable {

    
    @FXML
    private ImageView image;
    @FXML
    private ImageView image2;
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
    private MenuItem Scaling;
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
    @FXML
    private TextField scaleFactor;
    @FXML
    private TextField ColorNumber;

    private byte imageData[] = new byte[(int)controller.screenWidth * (int)controller.screenHeight * 4];
    private byte imageDataPrevious[] = new byte[(int)controller.screenWidth * (int)controller.screenHeight * 4];
    
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
    
    public PixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteBgraInstance();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if(this.image != null) {
        image.setFitHeight(screenHeight);
        image.setFitWidth(screenWidth);
        
        image2.setFitHeight(screenHeight);
        image2.setFitWidth(screenWidth);

        image.setImage(img);

         imageData = FractalRender.GetRender();

        writer.setPixels(0, 0, (int) image.getFitWidth(), (int) image.getFitHeight(), pixelFormat, imageData, 0, (int) image.getFitWidth() * 4);

        image.setImage(img);
        String p = "";
                
          for(int i = 0; i < 50; i++)  p += imageData[i] + " ";
          
            System.out.println(p);
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
        else if (event.getSource().equals(Scaling)) {
            root = FXMLLoader.load(getClass().getResource("scaling.fxml"));
//            scene.getStylesheets().add(getClass().getResource("axis.css").toExternalForm());
            title = "Scaling";
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
        
        WritableImage NewImg = new WritableImage((int) screenWidth, (int) screenHeight); 
        PixelWriter writer2 = NewImg.getPixelWriter();
        imageDataPrevious = imageData;
        
        writer2.setPixels(0, 0, (int) image.getFitWidth(), (int) image.getFitHeight(), pixelFormat, imageDataPrevious, 0, (int) image.getFitWidth() * 4);
        
        image2.setImage(NewImg);
        
        FadeTransition transition = new FadeTransition(Duration.seconds(2.5), image); 
        
        imageData = FractalRender.GetRender();
        
        image.setOpacity(0);
       
        transition.setFromValue(0);
        transition.setToValue(1.0);
        transition.play();
        
      
        

        writer.setPixels(0, 0, (int) image.getFitWidth(), (int) image.getFitHeight(), pixelFormat, imageData, 0, (int) image.getFitWidth() * 4);

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
        if(Fractal.name == "Koch"){
            FractalRender.setLevel(iterationCount);
        }
        else{Fractal.setIterationLimit(iterationCount);}
        
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
        Fractal.h = H;
        double K;
        K = Integer.parseInt(newK.getText());
        Fractal.k = K;
        
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
    
    @FXML
    private void submitScaling(ActionEvent event){
        
        double scale = Integer.parseInt(scaleFactor.getText());
        Fractal.setScaling(scale);
    }
    
    
    @FXML
    private void submitColorGradient(ActionEvent event){
        secondaryStage.close();
        
        int[][] option1 = {
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
        int[][] option2 = {
        {64, 255, 0},
        {128, 255, 0},
        {192, 255, 0},
        {255, 255, 0},
        {255, 192, 0},
        {255, 128, 0},
        {255, 64, 0},
        {255, 0, 0},
        {255, 0, 64},
        {255, 0, 128},
        {255, 0, 192},
        {255, 0, 255},
        {192, 0, 255},
        {128, 0, 255},
        {64, 0, 255},
        {0, 0, 255},
        {0, 64, 255},
        {0, 128, 255},
        {0, 192, 255},
        {0, 255, 255},
        {0, 255, 192},
        {0, 255, 128},
        {0, 255, 64},
        {0, 255, 0},
        };
        int[][] option3 = {
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
        int[][] option4 = {
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

        int selected = Integer.parseInt(ColorNumber.getText());
        switch (selected) {
            case 1:
                FractalRender.setGradient(option1);
                break;
            case 2:
                FractalRender.setGradient(option2);
                break;
            case 3:
                FractalRender.setGradient(option3);
                break;
            case 4:
                FractalRender.setGradient(option4);
                break;
            default:
                break;
        }
        
    }
    
}
