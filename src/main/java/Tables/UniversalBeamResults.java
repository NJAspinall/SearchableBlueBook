package Tables;

import Results.UniversalBeam;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class UniversalBeamResults {

    protected final static Font TEXT_FONT = Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 16);


    protected HBox table;


    /**
     * Constructor
     */
    public UniversalBeamResults() {
        table = new HBox();
    }


    /**
     *
     */
    public void displayResults() {

    }


    /**
     *
     */
    public void populateResults(ArrayList<UniversalBeam> results) {

    }


    /**
     * Test Method to display all info for a single result.
     */
    public HBox singleResult(UniversalBeam ub) {

        if(ub != null) {

            VBox labels = new VBox();
            VBox values = new VBox();


            /*
             * Dimensions - Section Measurements
             */

            //Designation
            Label desLabel = new Label("Designation : ");
            Label desValue = new Label(ub.getPreDesignation() + ub.getDesignation());
            desLabel.setFont(TEXT_FONT);
            desValue.setFont(TEXT_FONT);

            //Mass
            Label massLabel = new Label("Mass (mm) : ");
            Label massValue = new Label("" + ub.getMass());
            massLabel.setFont(TEXT_FONT);
            massValue.setFont(TEXT_FONT);

            //Depth
            Label depthLabel = new Label("Depth (mm) : ");
            Label depthValue = new Label("" + ub.getDepth());
            depthLabel.setFont(TEXT_FONT);
            depthValue.setFont(TEXT_FONT);

            //Width
            Label widthLabel = new Label("Width : ");
            Label widthValue = new Label("" + ub.getWidth());
            widthLabel.setFont(TEXT_FONT);
            widthValue.setFont(TEXT_FONT);

            /*
             * Dimensions - Thickness
             */


            //Web
            Label webLabel = new Label("Web : ");
            Label webValue = new Label("" + ub.getWeb());
            webLabel.setFont(TEXT_FONT);
            webValue.setFont(TEXT_FONT);

            //Flange
            Label flangeLabel = new Label("Flange : ");
            Label flangeValue = new Label("" + ub.getFlange());
            flangeLabel.setFont(TEXT_FONT);
            flangeValue.setFont(TEXT_FONT);


            //Labels
            labels.getChildren().addAll(
                    desLabel,
                    massLabel,
                    depthLabel,
                    widthLabel,

                    webLabel,
                    flangeLabel
            );

            //Values
            values.getChildren().addAll(
                    desValue,
                    massValue,
                    depthValue,
                    widthValue,

                    webValue,
                    flangeValue
            );

            table.getChildren().addAll(
                    labels,
                    values
            );


        }

        table.setPrefWidth(900);
        table.setPrefHeight(400);
        return table;
    }
}
