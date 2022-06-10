package Results;

public class Section {

    protected String preDesignation;
    protected String designation;


    /***
     * Constructor
     */
    public Section(String preDes, String des) {
        preDesignation = preDes;
        designation = des;
    }






    public String getPreDesignation() {
        return preDesignation;
    }

    public String getDesignation() {
        return designation;
    }
}
