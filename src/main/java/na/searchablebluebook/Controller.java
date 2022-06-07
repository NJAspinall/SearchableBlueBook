package na.searchablebluebook;

import Reader.ReaderFactory;
import Reader.SteelReader;
import Results.UniversalBeam;
import Tables.TableFactory;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.List;

public class Controller implements EventHandler {

    //MVC View class
    View view;

    //Table Factory. TODO : remove if obsolete
    TableFactory factory;


    //Reader to read separate csv files
    public SteelReader r;

    //Factory to return the correct Reader
    public ReaderFactory rFactory;


    //TODO : update to be a superclass holding the UniversalBeam objects
    public List<UniversalBeam> sections;


    //2d Array to hold the designations
    public ArrayList< ArrayList<String> > Designations = new ArrayList< ArrayList<String> >();


    //Currently selected Pre-Designation
    public String currentDes = "";
    //Currently selected Sub-Designation
    public String currentPreDes = "";


    /***
     * Constructor
     *
     * @param view - The MVC View class
     */
    public Controller(View view) {
        this.view = view;
        this.factory = new TableFactory();
        this.rFactory = new ReaderFactory();
    }








    /**
     * First method run by BlueBook.java
     */
    public void runController() {
        view.loadUI();

        this.view.searchButton.setOnAction(this);
        this.view.sectionTypes.setOnAction(this);
        this.view.sectionPreDes.setOnAction(this);
        this.view.sectionDes.setOnAction(this);
    }



    //used to build lists for further filtering of results

    /***
     * Returns a list containing all Section objects passed back from the Reader
     *
     * @param shape - The Steel section shape, e.g. Universal Beams
     * @return List<UniversalBeam> - The list of Section Objects
     *
     * TODO : Update with Section Superclass
     * TODO : PLACE INTO 2D ARRAY HERE, NOT IN SETALLDESIGNATIONS
     */
    public List<UniversalBeam> getAllDesignations(String shape) {
        //TODO: Create Shape Class and make UniversalBeams a subclass,
            //as well as move over common fields

        sections = new ArrayList<>();

        ArrayList<ArrayList<UniversalBeam>> sections2 = new ArrayList<>();

        //get the results from reading the file
        r = rFactory.createReader(shape);
        sections = r.read();



        int x = 0;
        int i = 1;

        String currentReadDes = "";

        for(UniversalBeam d : this.sections) {
            //add to 2d array

            //if it contains a header e.g. '1016 x 305'
            if(!d.getPreDesignation().isBlank()) {
                i = 0;

                System.out.println("sections2 New List: " +d.getPreDesignation());
                System.out.println("sections2 Next Element : " +d.getDesignation());

                sections2.add(new ArrayList<>());
                sections2.get(x).add(d);
                currentReadDes = d.getPreDesignation();
                x++;
            }
            else if(!d.getDesignation().isBlank()) { // if a designation
                System.out.println("sections2 Next Element : " +d.getDesignation());
                d.setPreDesignation(currentReadDes);
                sections2.get(x-1).add(d);
            }
            i++;
        }






        return sections;
    }










    public ArrayList<ArrayList<String>> setDesignations() {
        if(!this.sections.isEmpty()) {

            int x = 0;
            int i = 1;

            for(UniversalBeam d : this.sections) {
                //add to 2d array

                //if it contains a header e.g. '1016 x 305'
                if(!d.getPreDesignation().isBlank()) {
                    i = 0;

                    //System.out.println("New List: " +d.getPreDesignation());
                    //System.out.println("Next Element : " +d.getDesignation());

                    Designations.add(new ArrayList<>());
                    Designations.get(x).add(d.getPreDesignation());
                    Designations.get(x).add(d.getDesignation());
                    x++;
                }
                else if(!d.getDesignation().isBlank()) { // if a designation

                    Designations.get(x-1).add(d.getDesignation());
                }
                i++;
            }
        }

        viewList();

        return Designations;
    }









    public ArrayList<String> setPreDesignations() {
        ArrayList<String> preDesignations = new ArrayList<>();

        if((this.Designations != null) && (!this.Designations.isEmpty())) {
            for (ArrayList<String> list : Designations) {
                preDesignations.add(list.get(0));
            }
        }

//        System.out.println("Pre-Designations :");
//        for(String s : preDesignations) {
//            System.out.println(s);
//        }
        return preDesignations;
    }









    public ArrayList<String> setSubDesignations(String preDes) {
        //System.out.println("Setting Sub-Designations");

        ArrayList<String> subDesignations = new ArrayList<>();

        for(ArrayList<String> list : Designations) {
            if(preDes.equals(list.get(0))) {
                for(int i=1; i<list.size(); i++) {
                    subDesignations.add(list.get(i));
                    //System.out.println(list.get(i)+ " added.");
                } break;
            }
        }

        /*
        System.out.println("Designation " +preDes+ " selected.");

        System.out.println("Sub-Designations :");
        for(String s : subDesignations) {
            System.out.println(s);
        }
        */

        return subDesignations;
    }









    public void viewList() { //max 13 rows in array

        System.out.println("2D Array : " +Designations.size()+ " elements.");
        System.out.println("The current 2D array is as follows: ");

        for(ArrayList<String> list : Designations){
            if((list != null) && (!list.isEmpty())){
                System.out.println("Next PreDesignation : " +list.get(0));
                for(int i=1; i<list.size(); i++) {
                    System.out.println("Sub-Designation : " +list.get(i));
                }
            }
        }
    }








    /**
     * The Steel section could be one of many specific shapes that will be
     * split up into unique tables with different measurements and different
     * headings.
     *
     * @param type - the Steel Section Shape e.g. 'Universal Beam', Hollow etc.
     */
    public void handleSectionSelect(String type) {
        //read results from the relevant csv file
        this.sections = this.getAllDesignations(type);

        //string of section designations e.g. '1016 x 305' 'x 584'
        ArrayList<ArrayList<String>> Designations = this.setDesignations();

        //group part of section designation e.g. '1016 x 305'
        ArrayList<String> preDesignations = this.setPreDesignations();

        //set contents of next the next drop-down menu (Pre-Designation)
        view.setPreDesignations(preDesignations);
        view.definePreDesignations();

        //update left menu with updated drop-down lists
        view.borderPane.setLeft(view.createLeft());
    }







    /**
     * The section designation is split into two parts, the Prepending
     *  part that is used to group similar measurements.
     *  e.g. many steel designations may start with '1016 x 305'
     *
     * @param preDes - the Prepending Steel Section measurement
     */
    public void handlePreDesignation(String preDes) {
        System.out.println("Handling Pre-Designations...");

        //set the current Pre-Designation to keep the drop-down contents available
        currentPreDes = preDes;


        System.out.println("Section Type : " +preDes+ " selected.");
        System.out.println("Sub-Designations : ");

        //Set the list of Sub-Designations for the chosen Pre-Designation
        ArrayList<String> subDesignations = setSubDesignations(preDes);

        //Set the list for the next drop-down menu (Sub-Designations)
        view.setSubDesignations(subDesignations);
        view.defineSubDesignations();

        //preserve the current selection when updating the left menu
        view.currentPreDes = preDes;

        //update the left menu with the updated lists
        view.borderPane.setLeft(view.createLeft());
    }







    /**
     * The second part of the section designation is used to find specific
     * instances of the Steel Sections
     */
    public void handleDesignation(String des) {

        /* if the event is fired without a value being selected,
         such as when a new PreDesignation is selected resetting
         the ComboBox containing Sub-Designations. */
        if(des != null && !des.equals("")) {

            //Set the currently selected Sub-Designation
            currentDes = des;

            //Test Statements
            System.out.println("Handling Designations...");
            System.out.println("Sub-Designation " + currentPreDes + " " + currentDes + " selected.");
            System.out.println();



            /*TODO - after the first if statement succeeds, the second
            will only succeed if it is the first sub-designation in the
            list. why?*/

            //Find and load the Section Objects info

            //Test Statement
            System.out.println("Searching for " +currentPreDes +currentDes);

            //iterate through list of all read sections
            for (UniversalBeam ub : sections) {

                if(ub.getPreDesignation().equals("")) {
                    System.out.println("[ERROR] : NO PRE-DESIGNATION FOUND.");
                }


                //Test Statement
                System.out.println("NEXT OBJECT : " +ub.getPreDesignation() +" | "+ ub.getDesignation());


                //Find the object with the matching Pre-Designation
                if (ub.getPreDesignation().equals(currentPreDes)) {

                    //Test Statements
                    System.out.println("Found object's pre-designation : " + currentPreDes);
                    System.out.println("-== "+ currentPreDes + currentDes +" ==-");
                    System.out.println();
                    System.out.println("Target :" +currentDes+ ". Result : " +ub.getDesignation());

                    //Check if the object also has the matching Sub-Designation
                    if(ub.getDesignation().equals(currentDes)) {

                        //Test Statements
                        System.out.println("Object found - ");
                        System.out.println("PreDes : " + ub.getPreDesignation());
                        System.out.println("Des : " + ub.getDesignation());

                        //Set the currently selected Section object
                        view.currentResult = ub;

                        //End the for loop
                        break;
                    }
                }
                System.out.println("-= NEXT RESULT =-");
            }

            //update the centre pane with the currently selected Section object information
            view.borderPane.setCenter(view.createCenter());
        }
    }














    /**
     * Catch all events fired in the View class
     *
     * @param event - The event that fired this handler
     */
    @Override
    public void handle(Event event) {

        /*
         * When the Steel Shape type is selected.
         * e.g. Universal Beams
         */
        if(event.getSource() == this.view.sectionTypes) {
            System.out.println("Section Type : " +view.sectionTypes.getValue()+ " selected.");

            String type = this.view.sectionTypes.getValue();
            if(!type.equals("Section Type")) {
                handleSectionSelect(type);
            }
            System.out.println("Event 1 Cleared.");
        }




        /*
         * If the Search Button has been pressed
         */
        else if(event.getSource() == this.view.searchButton) {
            //Search with criteria
            System.out.println("Search!");
        }
    }
}
