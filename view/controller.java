package FallProject.view;

import FallProject.model.*;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ResourceBundle;
import javafx.application.Platform;
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

        WritableImage img = new WritableImage((int) image.getFitWidth(), (int) image.getFitHeight());

        PixelWriter writer = img.getPixelWriter();

        image.setImage(img);

        PixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteRgbInstance();

        byte imageData[] = FractalRender.GetRender();

        writer.setPixels(0, 0, (int) image.getFitWidth(), (int) image.getFitHeight(), pixelFormat, imageData, 0, (int) image.getFitWidth() * 3);

        image.setImage(img);

    }

    @FXML
    private void menuButtons(ActionEvent event) throws Exception {
        Parent root = new Pane();


        String title = " ";
        if (event.getSource().equals(Help)) {
            root = FXMLLoader.load(getClass().getResource("help.fxml"));
//            scene.getStylesheets().add(getClass().getResource("help.css").toExternalForm());
            title = "Help";
            
        } else if (event.getSource().equals(About)) {
            root = FXMLLoader.load(getClass().getResource("about.fxml"));
//            scene.getStylesheets().add(getClass().getResource("about.css").toExternalForm());
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
        
        Scene scene = new Scene(root);
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle(title);
        secondaryStage.setScene(scene);
        secondaryStage.show();
    }
    
    

    private void printFractal() {
        WritableImage img = new WritableImage((int) image.getFitWidth(), (int) image.getFitHeight());

        PixelWriter writer = img.getPixelWriter();

        image.setImage(img);

        PixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteRgbInstance();

        byte imageData[] = FractalRender.GetRender();

        writer.setPixels(0, 0, (int) image.getFitWidth(), (int) image.getFitHeight(), pixelFormat, imageData, 0, (int) image.getFitWidth() * 3);

        image.setImage(img);
    }

    @FXML
    private void FractalHandlerJulia(ActionEvent event) {

        Fractal.name = "Julia";

        printFractal();

    }

    @FXML
    private void FractalHandlerMandelbrot(ActionEvent event) {

        Fractal.name = "Mandelbrot";

        printFractal();

    }

    @FXML
    private void FractalHandlerNewton(ActionEvent event) {

        Fractal.name = "Newton";

        printFractal();

    }

    @FXML
    private void FractalHandlerkKoch(ActionEvent event) {

        Fractal.name = "Koch";

        printFractal();

    }

    @FXML
    private void ExitApplication(ActionEvent event) {

        Platform.exit();

    }

}
