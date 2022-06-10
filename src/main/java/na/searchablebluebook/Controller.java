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

    //2D Array to hold the Section Objects
    public ArrayList<ArrayList<UniversalBeam>> sections = new ArrayList<>();


    //2D Array to hold the identifying String Designation for each object
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

        //get the results from reading the file
        r = rFactory.createReader(shape);
        List<UniversalBeam> readSections = r.read();

        view.img1 = r.getImg1();

        //keeps track of which array is currently being populated
        int x = 0;

        String currentReadDes = "";

        for(UniversalBeam d : readSections) {
            //add to 2d array

            //if it contains a header e.g. '1016 x 305'
            if(!d.getPreDesignation().isBlank()) {

                /* create a new list for the following elements with
                the same pre-designation */
                sections.add(new ArrayList<>());
                //add Section object to the newly created list
                sections.get(x).add(d);

                /* set the current Pre-Designation to assign to the objects
                whose preDesignation field is blank */
                currentReadDes = d.getPreDesignation();

                //add all future objects to this list
                x++;
            }
            else if(!d.getDesignation().isBlank()) { // if a designation
                //set the Section objects Pre-Designation
                d.setPreDesignation(currentReadDes);
                //add Section object to the current list
                sections.get(x-1).add(d);
            }
        }
    }








    /***
     * Populates a second 2D array that just holds the designations
     * for each object (used to populate drop-down menus).
     */
    public void setDesignations() {

        //if the Sections have been read successfully
        if(this.sections != null) {
            if (!this.sections.isEmpty()) {

                //keep track of the current place in the 2D array
                int x = 0;

                //for each list of Section objects
                for (ArrayList<UniversalBeam> list : this.sections) {

                    /* go through the list of objects, adding their designations
                    in order to the 2D array */
                    for(int i=0; i<list.size(); i++) {

                        /* the first element in each array is the
                         grouping pre-designation. Copy the designations
                         into a new list */
                        if (i == 0) {
                            Designations.add(new ArrayList<>());
                            Designations.get(x).add(list.get(i).getPreDesignation());
                            Designations.get(x).add(list.get(i).getDesignation());
                            x++;


                        } else {
                            //copy designation into list
                            Designations.get(x-1).add(list.get(i).getDesignation());
                        }
                    }
                }

            }
            else {
                System.out.println("[ERROR] : ArrayList sections is empty ");
            }

        }

        else {
            System.out.println("[ERROR] : Cannot set Designation objects.");
        }

        //viewList();
    }










    /***
     * Creates the list of Pre-designations to be selected from a drop-down menu.
     *
     * @return ArrayList<String> - list containing all Pre-Designations for the
     *  selected Section type.
     */
    public ArrayList<String> setPreDesignations() {
        //list to hold drop-down elements
        ArrayList<String> preDesignations = new ArrayList<>();

        //if Designations has been correctly populated
        if((this.Designations != null) && (!this.Designations.isEmpty())) {
            /* get the first entry of each list which is the Pre-designation
            that groups the sections within and add it to the list */
            for (ArrayList<String> list : Designations) {
                preDesignations.add(list.get(0));
            }
        }

        return preDesignations;
    }









    /***
     * Creates a list of all the sub-designations grouped by the given
     * Pre-Designation.
     *
     * @param preDes - the Pre-Designation chosen by the user.
     * @return ArrayList<String> - the list of the returned sub-designations
     */
    public ArrayList<String> setSubDesignations(String preDes) {

        ArrayList<String> subDesignations = new ArrayList<>();

        for(ArrayList<String> list : Designations) {
            /* if the given pre-designation matches the first
             element in the list. */
            if(preDes.equals(list.get(0))) {
                /* loop through that list and add the sub-designations
                 to the list to be returned */
                for(int i=1; i<list.size(); i++) {
                    subDesignations.add(list.get(i));
                } break; //stop searching
            }
        }

        return subDesignations;
    }









    /***
     * For testing purposes, prints the Section designation of each object stored
     * in the 2D array grouped by the common Pre-Designations.
     */
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
        view.setLeftMenu(view.createLeft());
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

        /* if the user has selected a designation and fired this event. */
        if(des != null && !des.equals("")) {

            //Set the currently selected Sub-Designation
            currentDes = des;

            //Find and load the Section Objects info

            //iterate through list of all read sections
            for (ArrayList<UniversalBeam> list : sections) {

                //find the list it is in
                if(list.get(0).getPreDesignation().equals(currentPreDes)) {

                    //iterate through the list and find the object itself
                    for(UniversalBeam ub : list) {
                        if(ub.getDesignation().equals(currentDes)) {

                            //set the retrieved object in the view
                            view.currentResult = ub;
                        }
                    }
                }

            }

            //update the centre pane with the currently selected Section object information
            view.setCenterResults(view.createCenter());
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
