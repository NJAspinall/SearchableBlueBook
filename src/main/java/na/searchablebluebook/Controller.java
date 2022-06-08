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
    //public List<ArrayList<UniversalBeam>> sections;

    public ArrayList<ArrayList<UniversalBeam>> sections = new ArrayList<>();


    //2d Array to hold the designations
    public ArrayList< ArrayList<String> > Designations = new ArrayList< ArrayList<String> >();


    //The currently selected Pre-Designation
    public String currentDes = "";
    //The currently selected Sub-Designation
    public String currentPreDes = "";


    /***
     * Constructor, assigns the MVC View class used to present and collect
     * information from the user.
     *
     * @param view - The MVC View class
     */
    public Controller(View view) {
        this.view = view;
        this.factory = new TableFactory();
        this.rFactory = new ReaderFactory();
    }








    /**
     * First method run by BlueBook.java, tells the View class to
     * load the GUI and assign event handlers.
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
     * Forms the read list of objects into a 2d array containing an array for each
     * section grouped by their Pre-Designation.
     *
     * @param shape - The Steel section shape, e.g. Universal Beams
     */
    public void getAllDesignations(String shape) {
        //TODO: Create Shape Class and make UniversalBeams a subclass,
            //as well as move over common fields

        List<UniversalBeam> readSections = new ArrayList<>();



        //get the results from reading the file
        r = rFactory.createReader(shape);
        readSections = r.read();



        int x = 0;
        int i = 1;

        String currentReadDes = "";

        for(UniversalBeam d : readSections) {
            //add to 2d array

            //if it contains a header e.g. '1016 x 305'
            if(!d.getPreDesignation().isBlank()) {
                i = 0;

                //System.out.println("New List: " +d.getPreDesignation());
                //System.out.println("Next Element : " +d.getDesignation());

                sections.add(new ArrayList<>());
                sections.get(x).add(d);
                currentReadDes = d.getPreDesignation();
                x++;
            }
            else if(!d.getDesignation().isBlank()) { // if a designation
                //System.out.println("Next Element : " +d.getDesignation());
                d.setPreDesignation(currentReadDes);
                sections.get(x-1).add(d);
            }
            i++;
        }
    }










    public void setDesignations() {

        System.out.println();
        System.out.println("Setting Designations...");
        System.out.println();

        if(this.sections != null) {
            if (!this.sections.isEmpty()) {


                int x = 0;

                //TODO : The loop creates one list will all the results

                for (ArrayList<UniversalBeam> list : this.sections) {

                    //add to 2d array

                    for(int i=0; i<list.size(); i++) {

                        if (i == 0) {
                            Designations.add(new ArrayList<>());
                            Designations.get(x).add(list.get(i).getPreDesignation());
                            Designations.get(x).add(list.get(i).getDesignation());
                            x++;


                            //Test Statements
                            //System.out.println("New List created : " + list.get(i).getPreDesignation());
                            //System.out.println("Next Element : " +list.get(i).getDesignation());
                        } else {
                            Designations.get(x-1).add(list.get(i).getDesignation());
                            //System.out.println("Next Element : " +list.get(i).getDesignation());
                        }
                    }
                }

            }
            else {
                System.out.println("[ERROR] : ArrayList sections is empty ");
            }



                    /*

                    //if it contains a header e.g. '1016 x 305'
                    if (!d.getPreDesignation().isBlank()) {
                        i = 0;

                        //System.out.println("New List: " +d.getPreDesignation());
                        //System.out.println("Next Element : " +d.getDesignation());

                        Designations.add(new ArrayList<>());
                        Designations.get(x).add(d.getPreDesignation());
                        Designations.get(x).add(d.getDesignation());
                        x++;
                    } else if (!d.getDesignation().isBlank()) { // if a designation

                        Designations.get(x - 1).add(d.getDesignation());
                    }
                    i++;

                }
            }

                     */
        }

        else {
            System.out.println("[ERROR] : Cannot set Designation objects.");
        }

        viewList();
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
        System.out.println("-= LIST END =-");

        for(ArrayList<String> list : Designations){
            if((list != null) && (!list.isEmpty())){
                System.out.println("Next PreDesignation : " +list.get(0));
                for(int i=1; i<list.size(); i++) {
                    System.out.println("Sub-Designation : " +list.get(i));
                }
            }

            else {
                System.out.println("[ERROR] : Cannot find Pre-Designation!");
            }
        }

        System.out.println("-= LIST START =-");
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
        this.getAllDesignations(type);

        //string of section designations e.g. '1016 x 305' 'x 584'
        this.setDesignations();

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



            //Find and load the Section Objects info

            //Test Statement
            System.out.println("Searching for " +currentPreDes +currentDes);

            //iterate through list of all read sections
            for (ArrayList<UniversalBeam> list : sections) {

                if(list.get(0).getPreDesignation().equals(currentPreDes)) {
                    for(UniversalBeam ub : list) {
                        if(ub.getDesignation().equals(currentDes)) {
                            System.out.println("OBJECT FOUND ! :");
                            System.out.println("Designation : " +ub.getPreDesignation() + " " +ub.getDesignation());
                            view.currentResult = ub;
                        }
                    }
                }

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
