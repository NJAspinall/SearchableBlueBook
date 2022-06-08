package na.searchablebluebook;

import Reader.SteelReader;
import Results.UniversalBeam;
import Tables.TableFactory;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BlueBook extends Application {

    View view;
    Controller controller;
    SteelReader r;

    TableFactory factory;



    /**
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        //Setup JavaFX GUI
        stage.setTitle("Steel Blue Book");
        Pane root = new Pane();

        //Create MVC - View Class that will create the GUI
        view = new View(root, stage);

        /* Create MVC - Controller Class that will handle all events
           and provide data to the View. */
        controller = new Controller(view);

        //assign event handlers and tell the view to load the GUI
        controller.runController();

        //the view can internally call the controller when events are fired
        view.setController(controller);
    }
}
