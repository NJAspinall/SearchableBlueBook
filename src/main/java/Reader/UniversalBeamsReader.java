package Reader;

import Results.UniversalBeam;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class UniversalBeamsReader extends SteelReader {

    public static final String FILEPATH = "src/main/java/Data/UniversalBeams/UB-secpropsdimsprops.csv";





    public UniversalBeamsReader() {

    }


    /**
     * @param designation -
     * @return
     */
    @Override
    public UniversalBeam searchDesignation(String designation) {

        //search file for designation
        UniversalBeam d = new UniversalBeam(NO_RESULTS);
        boolean found = false;

        try {
            File file = new File(FILEPATH);
            Scanner reader = new Scanner(file);

            String preDesignation = "";

            while(reader.hasNextLine()) {
                if(reader.nextLine().equals(designation)) {
                    List<String> line = List.of(reader.nextLine().split(","));

                    if(!line.get(0).trim().isBlank()) {
                        preDesignation = line.get(0);
                        System.out.println("new preDesignation: " +preDesignation);
                    } else {
                        line.set(0, preDesignation);
                        System.out.println("kept");
                    }
                    d = new UniversalBeam(line);
                }
            }
        } catch(Exception e) {
            System.out.println("[Error] : Cannot find specified designation.");
        }

        if (!found) {
            return null;
        } else {
            return d;
        }
    }


    /**
     * Returns all rows in the file
     *
     * @return ArrayList<SectionProperties> - a list of returned SectionProperties objects
     */
    public List<UniversalBeam> read() {
        ArrayList<UniversalBeam> results = new ArrayList<>();

        int count = 0;

        try {
            File myObj = new File(FILEPATH);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();

                /* ignore first 9 lines which is just supplementary
                information about the source */
                if(count >= 10) {
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
            System.out.println("The file could not be found.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unknown Error!");
            e.printStackTrace();
        }

        return results;
    }










}
