package Reader;

import Results.UniversalBeam;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class UniversalBeamsReader extends SteelReader {

    public static final String FILEPATH = "src/main/java/Data/UniversalBeams/UB-secpropsdimsprops.csv";

    protected Image img1;
    protected Image img2;
    protected Image img3;


    public UniversalBeamsReader() {

        try {
            this.img1 = new Image(new FileInputStream("src/main/java/images/universalbeams/1.png"));
            this.img2  = new Image(new FileInputStream("src/main/java/images/universalbeams/2.png"));
            this.img3 = new Image(new FileInputStream("src/main/java/images/universalbeams/3.png"));
        }
        catch(FileNotFoundException e) {

        }
    }








    /**
     * @return
     */
    @Override
    public Image getImg1() {
        return this.img1;
    }

    /**
     * @return
     */
    @Override
    public Image getImg2() {
        return this.img2;
    }

    /**
     * @return
     */
    @Override
    public Image getImg3() {
        return this.img3;
    }









    /***
     * Search specifically for Sections by various filters
     *
     * @param filter String - What to search by
     * @param value String - The value to search for
     * @return
     */
    @Override
    public ArrayList<UniversalBeam> searchByFilter(String filter, String value) {

        //search file for designation
        UniversalBeam d = new UniversalBeam(NO_RESULTS);
        boolean found = false;

        int column = 0;

        switch (filter) {
            case "pre-designation" -> column = 0;
            case "sub-designation" -> column = 1;
            case "mass" -> column = 2;
            case "depth" -> column = 3;
            case "width" -> column = 4;
            case "web" -> column = 5;
            case "flange" -> column = 6;
            case "radius" -> column = 7;
            case "d" -> column = 8;
        }

        //list to hold any results
        ArrayList<UniversalBeam> results = new ArrayList<>();

        try {

            //read the csv file
            File file = new File(FILEPATH);
            Scanner reader = new Scanner(file);

            while(reader.hasNextLine()) {
                //get the next line
                List<String> line = Arrays.asList(reader.nextLine().split(","));

                //compare the value against the correct column
                if(line.get(column).equals(value)) {
                    System.out.println("[SEARCH] : Object found with " +filter +" : " +value);

                    //create object of matched row
                    UniversalBeam ub = new UniversalBeam(line);

                    //add the object to the list
                    results.add(ub);
                }
            }
        }

        catch(Exception e) {
            e.printStackTrace();
        }

        return results;
    }









    /**
     * Reads all rows in the file into UniversalBeam objects
     *
     * @return ArrayList<UniversalBeam> - a list of returned UniversalBeam objects
     */
    public List<UniversalBeam> read() {
        ArrayList<UniversalBeam> results = new ArrayList<>();

        int count = 0;

        try {
            File myObj = new File(FILEPATH);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                String data = myReader.nextLine().trim();

                /* ignore first 9 lines which is just supplementary
                information about the source of the data */
                if((count >= 10) && (count <= 117)) {
                    List<String> line = Arrays.asList(data.split(","));

                    if(line.size() >= 5) { //do not read empty lines or lines containing notes and comments
                        try {
                            UniversalBeam newDes = new UniversalBeam(line);
                            results.add(newDes);
                        } catch (InstantiationError e) {
                            e.printStackTrace();
                            //System.out.println("Cannot instantiate 'Designation' object");
                        }
                    }
                }
                count++;

            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("The Universal Beams file could not be found.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unknown Error!");
            e.printStackTrace();
        }

        return results;
    }

}
