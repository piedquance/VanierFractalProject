package FallProject.view;

import FallProject.model.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
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
    @FXML
    private TextField fontsizetext;

    private byte imageData[] = new byte[(int)controller.screenWidth * (int)controller.screenHeight * 4];
    private byte imageDataPrevious[] = new byte[(int)controller.screenWidth * (int)controller.screenHeight * 4];
    
    public static Stage secondaryStage = new Stage();
    
   public static int fontSize = 12;
   
   public static double imageX, imageY;
    
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

        imageX = image.getFitWidth();
        imageY = image.getFitHeight();
        
        image.setImage(img);

         imageData = FractalRender.GetRender();

        writer.setPixels(0, 0, (int) image.getFitWidth(), (int) image.getFitHeight(), pixelFormat, imageData, 0, (int) image.getFitWidth() * 4);

        image.setImage(img);
        String p = "";
        
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
        
        if(event.getSource().equals(About) || event.getSource().equals(Help)) secondaryStage.getScene().getRoot().getChildrenUnmodifiable().get(0).setStyle("-fx-font-family:monospace; -fx-font-size:" + fontSize +";");
        
        secondaryStage.show();
   
    }
    @FXML
    private void printFractal() {
        
        WritableImage NewImg = new WritableImage((int) screenWidth, (int) screenHeight); 
        PixelWriter writer2 = NewImg.getPixelWriter();
        imageDataPrevious = imageData;
        
        writer2.setPixels(0, 0, (int) imageX, (int) imageY, pixelFormat, imageDataPrevious, 0, (int) imageX * 4);
        
        image2.setImage(NewImg);
        
        FadeTransition transition = new FadeTransition(Duration.seconds(2.5), image); 
        
        imageData = FractalRender.GetRender();
        
        image.setOpacity(0);
       
        transition.setFromValue(0);
        transition.setToValue(1.0);
        transition.play();
        
      
        

        writer.setPixels(0, 0, (int) imageX, (int) imageY, pixelFormat, imageData, 0, (int) imageX * 4);

        image.setImage(img);
        
        
        System.out.println("BRRRRRRRRRRR");
        WriteFile("data");
        
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
        radius = Double.parseDouble(newRadius.getText());
        Fractal.setRadius(radius);
        
        secondaryStage.close();
        printFractal();
        
    }
    @FXML
    private void sumbitFontSize(ActionEvent event) {
  
        //this changes the text font size to given size.
        fontSize = Integer.parseInt(fontsizetext.getText());
        
        secondaryStage.close();
    }
    @FXML
    private void submitPosition(ActionEvent event){
        secondaryStage.close();
        
        double H;
        H = Double.parseDouble(newH.getText());
        Fractal.h = H;
        double K;
        K = Double.parseDouble(newK.getText());
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
        
        System.err.println("scaling");
        
        double scale = Double.parseDouble(scaleFactor.getText());
        Fractal.setScaling(scale);
        
        System.out.println(Fractal.scaling);
        
       // printFractal();
        
        
        secondaryStage.close();
    }
    
    @FXML
    private void submitColorGradient(ActionEvent event){
        
        
       FractalRender.gradientName = ColorNumber.getText();
        
        System.out.println(FractalRender.gradientName);
        
        secondaryStage.close();
        
        
    }
    
    /*The database that we decided to go for a simple one: using a text file 
      where we can both read and write from the file. The text will consist of 
      variables that can compose a fractal. When the user changes to a fractal, 
      its variables will be written in the text so that we can refer back to it.*/
    
    /*This function reads from the database (file)*/
    public static void ReadFile(File file) {

        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()) {
                String fractalName = scanner.next();
                scanner.useDelimiter("|");
                int iterationCount = scanner.nextInt();
                scanner.useDelimiter("|");
                int radius = scanner.nextInt();
                
                Fractal.iterate = iterationCount;
                Fractal.setRadius(radius);
                Fractal.name = fractalName;
            }
            scanner.close();
            

        } catch (FileNotFoundException e) {
            System.out.println("File is not found");
            e.printStackTrace();
        }
    }
    
    /*This it the function that writes on the database (file)*/
    public static void WriteFile(String file) {

        try {
            PrintWriter writer = new PrintWriter(file);
            String FractalName = Fractal.name;
            String iterationCount = "" + Fractal.iterate;
            String radius = "" + Fractal.getRadius();
            String scaling = "" +Fractal.scaling;
            String h = ""+Fractal.h;
            String k = ""+ Fractal.k;
            
            writer.println(FractalName + " | " + iterationCount + " | " + radius + " | " + scaling + " | " + h + " | " + k);
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("File is not found");
            e.printStackTrace();
        }
    }

}
