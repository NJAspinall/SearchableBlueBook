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

    View view;
    TableFactory factory;
    public SteelReader r;
    public ReaderFactory rFactory;

    public List<UniversalBeam> sections;

    //2d Array to hold the designations
    public ArrayList< ArrayList<String> > Designations = new ArrayList< ArrayList<String> >();


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
    }




    //Designation is subclassed to hold unique fields for each
    //row


    //used to build lists for further filtering of results
    public List<UniversalBeam> getAllDesignations(String shape) {
        sections = new ArrayList<>();
        //System.out.println(shape);

        //get the results from reading the file
        r = rFactory.createReader(shape);
        sections = r.read();
        return sections;
    }








    /**
     * When a section type is selected, this method will be
     * called setting the available designations (e.g x405) as
     * options in the next Combo box drop-down menu.
     */
    public ArrayList<ArrayList<String>> setDesignations() {
        //2d Array to hold the designations
        ArrayList< ArrayList<String> > list = new ArrayList< ArrayList<String> >();

        if(!this.sections.isEmpty()) {
            int x = 0;
            int i = 1;
            for(UniversalBeam d : this.sections) {
                //add to 2d array

                //if it contains a header e.g. '1016 x 305'
                if(!d.getPreDesignation().trim().isBlank()) {
                    i = 0;

                    System.out.println("New List: " +d.getPreDesignation().trim());
                    System.out.println("Next Element : " +d.getDesignation());

                    Designations.add(new ArrayList<>());
                    Designations.get(x).add(d.getPreDesignation());
                    Designations.get(x).add(d.getDesignation());
                    x++;
                }
                else if(!d.getDesignation().trim().isBlank()) { // if a designation
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
            //not running
            for (ArrayList<String> list : Designations) {
                preDesignations.add(list.get(0));
            }
        }


        System.out.println("Pre-Designations :");
        for(String s : preDesignations) {
            System.out.println(s);
        }
        return preDesignations;
    }

    public ArrayList<String> setSubDesignations(String preDes) {
        ArrayList<String> subDesignations = new ArrayList<>();

        for(ArrayList<String> list : Designations) {
            if(list.get(0).equals(preDes)) {
                for(int i=0; i<list.size(); i++) {
                    subDesignations.add(list.get(i));
                    //System.out.println(list.get(i)+ " added.");
                }
            }
        }

        System.out.println("Designation " +preDes+ "selected.");

        System.out.println("Pre-Designations :");
        for(String s : subDesignations) {
            System.out.println(s);
        }
        return subDesignations;
    }






    public void viewList() { //max 13 rows in array

        //System.out.println("2D Array : " +Designations.size()+ " elements.");
        //System.out.println("The current 2D array is as follows: ");

        for(ArrayList<String> list : Designations){
            if((list != null) && (!list.isEmpty())){
                //System.out.println("Next PreDesignation : " +list.get(0));
            }
        }
    }





    /**
     * @param event
     */
    @Override
    public void handle(Event event) {
        System.out.println("New Event : " +event);


        if(event.getSource() == this.view.sectionTypes) {
            System.out.println("Section Type : " +view.sectionTypes.getValue()+ " Selected");

            String type = this.view.sectionTypes.getValue();
            if(type.equals("Section Type")) {
                //Do nothing
            }
            else {
                //read results from the relevant csv file
                this.sections = this.getAllDesignations(type);

                //string of section designations e.g. '1016 x 305' 'x 584'
                ArrayList<ArrayList<String>> Designations = this.setDesignations();

                //group part of section designation e.g. '1016 x 305'
                ArrayList<String> preDesignations = this.setPreDesignations();
                view.setPreDesignations(preDesignations);
                view.definePreDesignations();
                view.borderPane.setLeft(view.createLeft());
            }
        }

        if(event.getSource() == this.view.sectionPreDes) {
            String preDes = this.view.sectionPreDes.getValue();

            System.out.println("Sub Designations");

            ArrayList<String> subDesignations = setSubDesignations(preDes);
            view.setSubDesignations(subDesignations);
            view.defineSubDesignations();
            view.borderPane.setLeft(view.createLeft());
        }


        if(event.getSource() == this.view.searchButton) {
            //Search with criteria
            System.out.println("Search!");
        }
    }
}
