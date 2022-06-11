package na.searchablebluebook;

import Results.Section;
import Results.UniversalBeam;
import Tables.SteelTableView;
import Tables.UniversalBeamResults;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;

import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class View {


    //JavaFX UI Elements
    Pane root;
    Stage stage;

    //The MVC Controller
    public Controller controller;


    //TODO - update to be a generic section superclass, not only UniversalBeam
    //Single Result
    protected Section currentResult;



    public Image img1;
    public Image img2;
    public Image img3;



    /*
     * Uniform Font Styles
     */
    protected final static Font TEXT_FONT = Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 16);
    protected final static Font TITLE_FONT = Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 24);

    /*
     * Uniform Colours
     */
    protected final static String COLOUR_TOP = "#3f88c0";
    protected final static String COLOUR_BG = "#fffac0";

    protected TableView table = new TableView();
    protected SteelTableView stb;


    protected HBox resultView = new HBox();



    public Button searchButton;

    /*
     * Drop-down containing SectionTypes e.g. 'Universal Beams'
     */
    public ComboBox<String> sectionTypes;



    /*
     * Sub Designations are the second individual part of the Section Designation
     */
    protected ArrayList<String> subDesignations = new ArrayList<>();
    public ComboBox<String> sectionPreDes;
    public String currentPreDes;
    protected ObservableList<String> obSubDesignations = FXCollections.observableArrayList();



    /*
     * Pre Designations are the first group part of the Section Designation
     */
    protected ArrayList<String> preDesignations = new ArrayList<>();
    public ComboBox<String> sectionDes;
    public String currentDes;
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

        //set the javafx stage and make it visible
        Scene scene = new Scene(root, 1200, 600);
        scene.getStylesheets().add("src/main/java/CSS/stylesheet.css");
        stage.setScene(scene);
        stage.show();
    }








    /**
     * Sets the MVC controller for the View (this)
     * @param controller
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }










    /**
     * This method initialises the View
     */
    public void loadUI() {
        //BorderPane element to split the view into different sections
        borderPane = new BorderPane();

        //Elements that make up the BorderPane

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
        topPane.setStyle("-fx-background-color: "+ COLOUR_TOP);


        //Sections - DropDown
        sectionTypes = new ComboBox<>();
        sectionTypes.setPrefWidth(350);
        sectionTypes.setId("sectionTypes");
        //sectionTypes.setStyle("-fx-font-weight: bold");
        sectionTypes.setStyle("-fx-font-size: 24px");


        sectionTypes.getItems().addAll("Section Type","(UB) Universal Beams", "PlaceHolder", "PlaceHolder");
        sectionTypes.getSelectionModel().selectFirst();





        //Adding action to the choice box
        sectionTypes.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {

                });


        topPane.getChildren().addAll(sectionTypes);
        return topPane;
    }









    /**
     * Sets the options in the Section Pre-Designation drop down menu
     *@param preDesignations - ArrayList<String>, a list of the available
     *      Pre-Designations based on the selected Section Type.
     */
    public void setPreDesignations(ArrayList<String> preDesignations) {
        System.out.println("Getting Pre-Designations");
        this.preDesignations = preDesignations;
    }

    public void definePreDesignations() {
        System.out.println("Defining Pre-Designations");
        obPreDesignations.clear();
        obPreDesignations.addAll(preDesignations);
    }









    /**
     * Sets the options in the Sub-Designation drop down menu
     * @param subDesignations - ArrayList<String>, a list of the available
     *      sub designations chosen based on the selected Pre-Designation.
     */
    public void setSubDesignations(ArrayList<String> subDesignations) {
        System.out.println("Getting Sub-Designations");
        this.subDesignations = subDesignations;

        /*
        System.out.println("Following Sub-Designation options loaded :");
        for(String s : subDesignations) {
            System.out.println(s);
        }
        */
    }

    /**
     * Populates an Observable list for the
     */
    public void defineSubDesignations() {
        //System.out.println("Defining Sub-Designations");
        obSubDesignations.clear();
        obSubDesignations.addAll(subDesignations);
    }







    public void setLeftMenu(VBox left) {
        borderPane.setLeft(left);
    }

    public void setCenterResults(HBox center) {
        borderPane.setCenter(center);
    }


    public VBox createLeft() {
        /*
         * VBox to represent entire left window
         */
        leftPane = new VBox();
        leftPane.setPrefHeight(400);
        leftPane.setPrefWidth(350);
        leftPane.setStyle("-fx-background-color: " +COLOUR_BG);



        /*
         * section designation
         */
        HBox designationSection = new HBox();
        designationSection.setPadding(new Insets(10));

        Label sectionDesLabel = new Label("Select Designation :");
        sectionDesLabel.setFont(TEXT_FONT);
        sectionDesLabel.setPrefWidth(160);


        /*
         * A ComboBox to select from Pre-Designation values, as
         *  well as an event listener that calls a handler from
         *  the Controller class
         */
        sectionPreDes = new ComboBox<>();
        sectionPreDes.setPrefWidth(110);
        if(currentPreDes != null && !currentPreDes.equals("")) {
            sectionPreDes.setValue(controller.currentPreDes);
        }
        sectionPreDes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Call event Handler
                controller.handlePreDesignation(sectionPreDes.getValue());
            }
        });



        /*
         * A ComboBox to select from Sub-Designation values, as
         *  well as an event listener that calls a handler from
         *  the Controller class
         */
        sectionDes = new ComboBox<>();
        sectionDes.setPrefWidth(80);
        if(currentDes != null && !currentDes.equals("")) {
            sectionDes.setValue(controller.currentDes);
        }
        sectionDes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //call Event Handler
                controller.handleDesignation(sectionDes.getValue());
            }
        });



        /*
         * Pre-Designations ComboBox populated from ObservableList
         */
        if(obPreDesignations != null && !obPreDesignations.isEmpty()) {
            sectionPreDes.setItems(this.obPreDesignations);
        } else {
            //System.out.println("Failed to load Pre-Designations");
        }

        /*
         * Sub-Designations ComboBox populated from ObservableList
         */
        if(obSubDesignations != null && !obSubDesignations.isEmpty()) {
            sectionDes.setItems(this.obSubDesignations);
        }  else {
            //System.out.println("Failed to load Sub-Designations");
        }

        designationSection.setStyle("-fx-background-color: white; -fx-border-style: solid");

        //add ComboBoxes and Label to the HBox 'designationSection'
        designationSection.getChildren().addAll(sectionDesLabel, sectionPreDes, sectionDes);


        /*
         * Search Button
         */
        searchButton = new Button("Search");
        searchButton.setPrefWidth(150);
        searchButton.setPrefHeight(40);


        /* place all elements on the left pane and return it to the
            method loadUI() */
        leftPane.getChildren().addAll(designationSection, searchButton);
        return leftPane;
    }









    //results
    public HBox createCenter() {

        if(currentResult != null) {
            System.out.println("Creating Center with " + currentResult.getPreDesignation() + currentResult.getDesignation());
        }

        centerPane = new HBox();
        centerPane.setPrefHeight(400);
        centerPane.setPrefWidth(950);
        centerPane.setStyle("-fx-background-color: #0b5394");


        //select designation?


        //query results
        table.setPrefWidth(900);
        centerPane.getChildren().add(resultView);

        UniversalBeamResults ubr = new UniversalBeamResults(img1, img2, img3);

        HBox table = ubr.singleResult(currentResult);

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


}
