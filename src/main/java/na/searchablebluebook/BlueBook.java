package na.searchablebluebook;

import Reader.Reader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BlueBook extends Application {

    View view;
    Reader r;



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

        r = new Reader();
        r.read();
    }















    public void displayResults() { view.displayResults(); }







}
