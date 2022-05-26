package na.searchablebluebook;

import javafx.event.Event;
import javafx.event.EventHandler;

public class Controller implements EventHandler {

    View view;


    public Controller(View view) {
        this.view = view;
    }


    /**
     * @param event
     */
    @Override
    public void handle(Event event) {
        if(event.getSource() == this.view.searchButton) {

        }
    }
}
