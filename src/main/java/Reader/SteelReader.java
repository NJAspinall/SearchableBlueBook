package Reader;

import Results.Section;
import Results.UniversalBeam;
import Results.UniversalColumn;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




/*
 * This class will need to read a CSV file and extract info about the beams
 */

/***
 * This abstract Class can be subclassed to create concrete readers to read
 * different csv files which will have different headings.
 */
public abstract class SteelReader {


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


    /***
     * Constructor
     */
    public SteelReader() {

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


    /***
     * read all rows from a file and return them as Section objects
     *
     * @return - ArrayList<Section> - the array of Section objects
     */
    public abstract ArrayList<Section> read();



    /*
     * Use factory object here to create Section sub-objects
     */

    /**
     * Read a line of data into an object
     */
    public void readLine() {

    }





}
