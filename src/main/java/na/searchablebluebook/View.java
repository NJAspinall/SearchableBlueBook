package na.searchablebluebook;

import Results.UniversalBeam;
import Tables.SteelTableView;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;

import javafx.scene.control.*;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import java.util.ArrayList;

public class View {

    Pane root;
    Stage stage;

    protected TableView table = new TableView();
    protected SteelTableView stb;


    Button searchButton;

    /*
     * Drop-down containing SectionTypes e.g. 'Universal Beams'
     */
    ComboBox<String> sectionTypes;


    /*
     * 2D array conating
     */

    protected ArrayList<ArrayList<String>> Designations = new ArrayList<>();


    /*
     * Sub Designations are the second individual part of the Section Designation
     */
    protected ArrayList<String> subDesignations = new ArrayList<>();
    ComboBox<String> sectionPreDes;
    protected ObservableList<String> obSubDesignations = FXCollections.observableArrayList();


    /*
     * Pre Designations are the first group part of the Section Designation
     */
    protected ArrayList<String> preDesignations = new ArrayList<>();
    ComboBox<String> sectionDes;
    protected ObservableList<String> obPreDesignations = FXCollections.observableArrayList();


    /*
     * BorderPane elements
     */
    BorderPane borderPane;

    HBox topPane;
    VBox leftPane;
    HBox centerPane;


    public View(Pane root, Stage stage) {
        this.root = root;
        this.stage = stage;

        //add ui elements



        stage.setScene(new Scene(root, 1200, 600));
        stage.show();
    }





    public void loadUI() {
        borderPane = new BorderPane();


        //Top - toolbar
        borderPane.setTop( createTop() );

        //center - query results
        borderPane.setCenter( createCenter() );

        //left - search pane
        borderPane.setLeft( createLeft() );

        root.getChildren().add( borderPane );
    }



    protected HBox createTop() {

        topPane = new HBox();
        topPane.setPrefWidth(800);
        topPane.setPrefHeight(40);
        topPane.setStyle("-fx-background-color: #0000ff");


        //Sections - DropDown
        sectionTypes = new ComboBox<>();
        sectionTypes.setPrefWidth(300);
        sectionTypes.getItems().addAll("Section Type","(UB) Universal Beams", "", "");
        sectionTypes.getSelectionModel().selectFirst();

        sectionTypes.setStyle("-fx-font-weight: bold");
        sectionTypes.setStyle("-fx-font-size: 24px");



        //Adding action to the choice box
        sectionTypes.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {

                });


        topPane.getChildren().addAll(sectionTypes);
        return topPane;
    }


    /**
     * Sets the options in the Section PreDesignation drop down menu
     *@param preDesignations
     */
    public void setPreDesignations(ArrayList<String> preDesignations) {
        this.preDesignations = preDesignations;

        System.out.println("Following Pre-Designation options loaded :");
        for(String s : preDesignations) {
            System.out.println(s);
        }
    }

    public void definePreDesignations() {
        obPreDesignations.addAll(preDesignations);
    }


    /**
     * Sets the options in the Sub-Designation drop down menu
     * @param subDesignations
     */
    public void setSubDesignations(ArrayList<String> subDesignations) {
        this.subDesignations = subDesignations;

        System.out.println("Following Sub-Designation options loaded :");
        for(String s : subDesignations) {
            System.out.println(s);
        }
    }
    public void defineSubDesignations() {
        obSubDesignations.addAll(subDesignations);
    }



    public VBox createLeft() {
        /*
         * VBox to represent entire left window
         */
        leftPane = new VBox();
        leftPane.setPrefHeight(400);
        leftPane.setPrefWidth(300);
        leftPane.setStyle("-fx-background-color: #09ea69");



        /*
         * section designation
         */
        HBox section2 = new HBox();
        Label sectionDesLabel = new Label("Select Designation :");
        sectionDesLabel.setPrefWidth(130);

        sectionPreDes = new ComboBox<>();
        sectionPreDes.setPrefWidth(130);

        sectionDes = new ComboBox<>();
        sectionDes.setPrefWidth(100);

        if(obPreDesignations != null && !obPreDesignations.isEmpty()) {
            sectionPreDes.setItems(this.obPreDesignations);
        } else {
            System.out.println("Failed to load Pre-Designations");
        }


        if(obSubDesignations != null && !obSubDesignations.isEmpty()) {
            sectionDes.setItems(this.obSubDesignations);
        }  else {
            System.out.println("Failed to load Sub-Designations");
        }
        section2.getChildren().addAll(sectionDesLabel, sectionPreDes, sectionDes);



        /*
         * Search Button
         */
        searchButton = new Button("Search");
        searchButton.setPrefWidth(150);
        searchButton.setPrefHeight(40);


        leftPane.getChildren().addAll(section2, searchButton);
        return leftPane;
    }






    //results
    public HBox createCenter() {
        centerPane = new HBox();
        centerPane.setPrefHeight(400);
        centerPane.setPrefWidth(950);
        centerPane.setStyle("-fx-background-color: #0b5394");


        //select designation?


        //query results
        table.setPrefWidth(900);
        centerPane.getChildren().add(table);

        return centerPane;
    }





    public void updateTable(SteelTableView steelTable) {
        if(steelTable != null) {
            table = steelTable.getTable();
            System.out.println("Table has been updated");
        } else {
            System.out.println("Appropriate table not found");
        }
    }


    public void updateSearchScreen() {

    }




    public void displayResults(ArrayList<UniversalBeam> results) {
        //display results from query

        for (UniversalBeam d : results) {
            System.out.println("result designation : " +d.getDesignation());
            System.out.println("result mass : " +d.getMass());
            System.out.println("result width : " +d.getWidth());
            System.out.println("result width : " +d.getWidth());
        }
    }

}
