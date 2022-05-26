package Reader;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files




/*
 * This class will need to read a CSV file and extract info about the beams
 */


public class Reader {


    public Reader() {

    }

    /*
     * Test read
     */
    public void read() {
        try {
            File myObj = new File("C:\\Users\\Nathan\\IdeaProjects\\SearchableBlueBook\\src\\main\\java\\Data\\UniversalBeams\\UB-secpropsdimsprops.csv");
            Scanner myReader = new Scanner(myObj);

            int count = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                if(count >= 10) {

                    System.out.println("Line : " +count+ " | " +data);
                }
                count++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



    /**
     * Read a line of data into an object
     */
    public void readLine() {

    }





}
