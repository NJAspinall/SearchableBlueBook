package Results;

public class Section {

    protected String preDesignation;
    protected String designation;


    /***
     * Constructor
     *
     * @param preDes - String, the Sections pre designation e.g.
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

    public void setPreDesignation(String preDesignation) {
        this.preDesignation = preDesignation;
    }
}
