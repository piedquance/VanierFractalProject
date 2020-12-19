package FallProject.view;

import FallProject.model.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.animation.FadeTransition;
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
import javafx.scene.layout.Pane;
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

    public static double screenHeight = screen.getHeight() - 80;
    public static double screenWidth = screen.getWidth();

    
     public WritableImage img = new WritableImage((int) screenWidth, (int) screenHeight);

    public PixelWriter writer = img.getPixelWriter();
    
    public PixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteBgraInstance();
    
    
    public static boolean RadiusCheck = false;
    public static boolean IterationCheck = false;
    public static boolean PositionCheck = false;
    public static boolean ScalingCheck = false;
    public static boolean ColorCheck = false;
    public static boolean FontCheck = false;
    
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
        
        if(IterationCheck) {
            newItCount.promptTextProperty().setValue(String.valueOf(Fractal.iterationLimit));
            IterationCheck = false;
        } else if(RadiusCheck) {
            newRadius.promptTextProperty().setValue(String.valueOf(Fractal.radius));
            RadiusCheck = false;
        } else if(PositionCheck) {
            newH.promptTextProperty().setValue(String.valueOf(Fractal.h));
            newK.promptTextProperty().setValue(String.valueOf(Fractal.k));
            PositionCheck = false;
        } else if(ScalingCheck) {
            scaleFactor.promptTextProperty().setValue(String.valueOf(1 / (Fractal.scaling * 250)));
            ScalingCheck = false;
        } else if(ColorCheck) {
            ColorNumber.promptTextProperty().setValue(String.valueOf(FractalRender.gradientName));
            ColorCheck = false;
        } else if(FontCheck) {
            fontsizetext.promptTextProperty().setValue(String.valueOf(fontSize));
            FontCheck = false;
        }
         
    }
    
    @FXML
    private void menuButtons(ActionEvent event) throws Exception {
        
        if(event.getSource().equals(IterationCount)) IterationCheck = true;
        if(event.getSource().equals(Radius)) RadiusCheck = true;
        if(event.getSource().equals(Position)) PositionCheck = true;
        if(event.getSource().equals(Scaling)) ScalingCheck = true;
        if(event.getSource().equals(ColorGradient)) ColorCheck = true;
        if(event.getSource().equals(FontSize)) FontCheck = true;
         
        
        Parent root = new Pane();
        String title = " ";
        Scene scene = new Scene(root);
        if (event.getSource().equals(Help)) {
            root = FXMLLoader.load(getClass().getResource("help.fxml"));
            scene.getStylesheets().add(getClass().getResource("help.css").toExternalForm());
            title = "Help";
            
        } else if (event.getSource().equals(About)) {
            root = FXMLLoader.load(getClass().getResource("about.fxml"));
            scene.getStylesheets().add(getClass().getResource("about.css").toExternalForm());
            title = "About";
        }
        else if (event.getSource().equals(Scaling)) {
            root = FXMLLoader.load(getClass().getResource("scaling.fxml"));
            title = "Scaling";
        }
        else if (event.getSource().equals(IterationCount)) {
            root = FXMLLoader.load(getClass().getResource("iterationCount.fxml"));
            title = "Iteration Count";
        }
        else if (event.getSource().equals(Radius)) {
            root = FXMLLoader.load(getClass().getResource("radius.fxml"));
                    
            title = "Radius";
        }
        else if (event.getSource().equals(Position)) {
            root = FXMLLoader.load(getClass().getResource("position.fxml"));
            title = "Position";
        }
        else if (event.getSource().equals(ColorGradient)) {
            root = FXMLLoader.load(getClass().getResource("colorGradient.fxml"));
            title = "Color Gradient";
        }
        else if (event.getSource().equals(FontSize)) {
            root = FXMLLoader.load(getClass().getResource("fontSize.fxml"));
            title = "Font Size";
        }

        scene = new Scene(root);
        
        secondaryStage.setTitle(title);
        secondaryStage.setScene(scene);
        
        if(event.getSource().equals(About) || event.getSource().equals(Help)) secondaryStage.getScene().getRoot().getChildrenUnmodifiable().get(0).setStyle("-fx-font-family:monospace; -fx-font-size:" + fontSize +";");
        
        secondaryStage.show();
    }
    
    @FXML
    private void printFractal() throws IOException {

        
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
        
 
        
        
        
        
    }
    @FXML
    private void FractalHandlerJulia(ActionEvent event) throws IOException {

        Fractal.name = "Julia";
        Fractal.radius = 2;
        printFractal();

    }
    @FXML
    private void FractalHandlerMandelbrot(ActionEvent event) throws IOException {

        Fractal.name = "Mandelbrot";
        Fractal.radius = 2;
        printFractal();

    }
    @FXML
    private void FractalHandlerNewton(ActionEvent event) throws IOException {

        Fractal.name = "Newton";
        Fractal.radius = 1999999999;
        printFractal();

    }
    @FXML
    private void FractalHandlerkKoch(ActionEvent event) throws IOException {

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
    private void submitIterationCount(ActionEvent event) throws IOException{
        int iterationCount;
        iterationCount = Integer.parseInt(newItCount.getText());
        if(Fractal.name == "Koch"){
            FractalRender.setLevel(iterationCount);
        }
        else{
            Fractal.setIterationLimit(iterationCount);
            Fractal.setBreakpoint(iterationCount/4);
        };
        
        secondaryStage.close();
    }
    @FXML
    private void submitRadius(ActionEvent event) throws IOException{
        double radius;
        radius = Double.parseDouble(newRadius.getText());
        Fractal.setRadius(radius);
        
        secondaryStage.close();
        
    }
    @FXML
    private void sumbitFontSize(ActionEvent event) {
  
        //this changes the text font size to given size.
        fontSize = Integer.parseInt(fontsizetext.getText());
        
        secondaryStage.close();
    }
    @FXML
    private void submitPosition(ActionEvent event) throws IOException{
        secondaryStage.close();
        
        double H;
        H = Double.parseDouble(newH.getText());
        Fractal.h = H;
        double K;
        K = Double.parseDouble(newK.getText());
        Fractal.k = K;
        

        
        Fractal.name = "Mandelbrot";
        
      


    }
    @FXML
    private void submitScaling(ActionEvent event){
              
        double scale = Double.parseDouble(scaleFactor.getText());
        Fractal.setScaling(1/(scale*250));
       
        

        
        
        secondaryStage.close();
    }
    
    @FXML
    private void submitColorGradient(ActionEvent event){
       FractalRender.gradientName = ColorNumber.getText();
        secondaryStage.close();
        
        
    }
    
    /*The database that we decided to go for a simple one: using a text file 
      where we can both read and write from the file. The text will consist of 
      variables that can compose a fractal. When the user changes to a fractal, 
      its variables will be written in the text so that we can refer back to it. 
      Unfortunately, this remains incompleted */
    
    /*This function reads from the database (file)  
    Note that this is not completed, still need to add stuffs*/
    public void ReadFile() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("data.txt")));

        Scanner scanner = new Scanner(reader);
        while (scanner.hasNext()) {
            String stringRead = scanner.nextLine();
            String[] values = stringRead.split(" ");

            String name = values[0];
            System.out.println(values[0]);
            int iterationLimit = Integer.parseInt(values[1]);
            double radius = Double.parseDouble(values[2]);
            double scaling = Double.parseDouble(values[3]);
            double h = Double.parseDouble(values[4]);
            double k = Double.parseDouble(values[5]);

            
        }
        scanner.close();

    }
    
    /*This is the function that writes on the database (file)
    Note that this is not completed, still need to stuffs*/ 
    public  void WriteFile() throws IOException {

        try {
            URL url = getClass().getResource("data.txt");
            FileWriter fileWriter = new FileWriter(url.getPath());
            BufferedWriter writer = new BufferedWriter(fileWriter);

            String FractalName = Fractal.name;
            String iterationLimit = "" + Fractal.iterationLimit;
            String radius = "" + Fractal.getRadius();
            String scaling = "" + Fractal.scaling;
            String h = "" + Fractal.h;
            String k = "" + Fractal.k;

            writer.write("\n" + FractalName + " " + iterationLimit + " " + radius + " " + scaling + " " + h + " " + k);
            writer.close();
            System.out.println("Sucess");

        } catch (FileNotFoundException e) {
            System.out.println("File is not found");
            e.printStackTrace();
        }
    }

}
