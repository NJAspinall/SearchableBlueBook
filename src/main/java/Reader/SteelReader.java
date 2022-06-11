package Reader;

import Results.Section;
import Results.UniversalBeam;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




/*
 * This class will need to read a CSV file and extract info about the beams
 */


public abstract class SteelReader {

    public Image img1;
    public Image img2;
    public Image img3;


    //empty object to return for empty searches
    protected static final List<String> NO_RESULTS = new ArrayList<String>(
            Arrays.asList(
                    "null","null","null","null",
                    "null","null","null","null",
                    "null","null","null","null",
                    "null","null","null","null",
                    "null","null","null","null",
                    "null","null","null","null"
            )
    );


    //UniversalBeams
    protected static final String FILEPATH = "C:\\Users\\Nathan\\IdeaProjects\\SearchableBlueBook\\src\\main\\java\\Data\\UniversalBeams\\UB-secpropsdimsprops.csv";
    protected String testFile = "src/main/java/Data/UniversalBeams/UB-secpropsdimsprops.csv";


    /***
     * Constructor
     */
    public SteelReader() {

    }








    public Image getImg1() {
        return img1;
    }

    public Image getImg2() {
        return img2;
    }

    public Image getImg3() {
        return img3;
    }









    /***
     * Search specifically for Sections by various filters
     *
     * @param filter String - What to search by
     * @param value String - The value to search for
     * @return
     */
    public UniversalBeam searchDesignation(String filter, String value) {
        return null;
    }


    public abstract ArrayList<UniversalBeam> searchByFilter(String filter, String value);

    /*
     * Test read
     */
    public ArrayList<Section> read() {






        return null;
    }



    /**
     * Read a line of data into an object
     */
    public void readLine() {

    }





}
