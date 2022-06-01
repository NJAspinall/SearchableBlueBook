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
        stage.setTitle("Steel Blue Book");
        Pane root = new Pane();


        view = new View(root, stage);

        controller = new Controller(view);
        controller.runController();


        //r = new SteelReader();
        //ArrayList<Designation> results = r.read();


        //displayResults(results);
    }















    public void displayResults(ArrayList<UniversalBeam> results) { view.displayResults(results); }







}
