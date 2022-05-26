package na.searchablebluebook;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

public class View {

    Pane root;
    Stage stage;

    Button searchButton;


    public View(Pane root, Stage stage) {
        this.root = root;
        this.stage = stage;

        //add ui elements



        stage.setScene(new Scene(root, 800, 500));
        stage.show();
    }





    public void loadUI() {
        BorderPane borderPane = new BorderPane();


        //Top - toolbar
        borderPane.setTop( createTop() );

        //center - query results
        borderPane.setCenter( createCenter() );

        //left - search pane
        borderPane.setLeft( createLeft() );

        root.getChildren().add( borderPane );
    }



    protected ToolBar createTop() {

        ToolBar tbar = new ToolBar();
        tbar.setPrefWidth(600);
        tbar.setPrefHeight(40);


        return tbar;
    }

    protected VBox createLeft() {
        VBox leftPane = new VBox();
        leftPane.setPrefHeight(400);
        leftPane.setPrefWidth(200);
        leftPane.setStyle ("-fx-background-color: #650871");

        //search criteria

        //Sections - DropDown
        ChoiceBox<String> sections = new ChoiceBox<>();
        sections.getItems().addAll("(UB) Universal Beams", "", "");

        //Design Data

        //section designation


        //Search Button
        searchButton = new Button("Search");
        searchButton.setPrefWidth(150);
        searchButton.setPrefHeight(40);


        leftPane.getChildren().addAll(sections, searchButton);
        return leftPane;
    }






    //results
    private HBox createCenter() {
        HBox centerPane = new HBox();
        centerPane.setPrefHeight(400);
        centerPane.setPrefWidth(550);
        centerPane.setStyle("-fx-background-color: #0b5394");
        //query results

        return centerPane;
    }








    public void displayResults() {
        //display results from query
    }

}
