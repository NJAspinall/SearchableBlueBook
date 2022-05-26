package na.searchablebluebook;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BlueBook extends Application {

    View view;



    /**
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Steel Blue Book");
        Pane root = new Pane();
        view = new View(root, stage);

        view.loadUI();
    }















    public void displayResults() { view.displayResults(); }







}
