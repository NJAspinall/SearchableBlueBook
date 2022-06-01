package Reader;

import Results.UniversalBeam;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files




/*
 * This class will need to read a CSV file and extract info about the beams
 */


public class SteelReader {


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





    public SteelReader() {

    }


    public UniversalBeam searchDesignation(String designation) {
        return null;
    }



    /*
     * Test read
     */
    public List<UniversalBeam> read() {
        ArrayList<UniversalBeam> results = new ArrayList<>();

        int count = 0;

        try {
            File myObj = new File(testFile);
            System.out.println("=======================");
            System.out.println(myObj.getPath());
            System.out.println("=======================");

            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();

                if(count >= 10) {
                    //System.out.println("Line : " +count+ " | " +data);

                    String[] elements = data.split(",");

                    List<String> line = Arrays.asList(elements);

                    if(line.size() >= 5) { //do not try to read empty lines or lines containing notes and comments
                        try {
                            UniversalBeam newDes = new UniversalBeam(line);
                            results.add(newDes);
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Cannot instantiate 'Designation' object");
                        }
                    }














                }
                count++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unknown Error!");
            e.printStackTrace();
        }

        return results;
    }



    /**
     * Read a line of data into an object
     */
    public void readLine() {

    }





}
