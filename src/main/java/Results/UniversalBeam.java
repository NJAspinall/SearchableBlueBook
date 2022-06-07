package Results;

import java.util.List;


/**
 * This class will hold each row of the Dimensions & Properties
 * table for any Steel Shape (such as Universal Beams, ).
 *
 * TODO - Create a Steel Superclass that this one and all other section types will inherit from.
 */


public class UniversalBeam {

    /*
     * Dimensions
     */
    //section PreDesignation (1016 x 305)
    protected String preDesignation;
    //Section Designation (x 584)
    protected String designation;
    //Mass per metre (kg/m)
    protected double mass;
    //Depth of Section (millimetres)
    protected double depth;
    //Width of Section (millimetres)
    protected double width;
    //Thickness (millimetres)
    protected double web; // t^w
    protected double flange; // t^f
    //Root radius (millimetres)
    protected double radius;
    //Depth between fillets (millimetres)
    protected double d;
    //Ratios for local buckling
    protected double ratioWeb; // c^w/t^f
    protected double ratioFlange; //c^f/t^f
    //Dimensions for detailing (millimetres)
    protected double endClearance;
    protected double N; //Notch
    protected double n; //Notch
    //Surface area (metre^2)
    protected double perMetre;
    protected double perTonne;


    /*
     * Properties
     */


    //Second moment of area (centimetres^4)
    protected double secondAxisY;
    protected double secondAxisZ;
    //Radius of gyration (centimetres)
    protected double gyrAxisY;
    protected double gyrAxisZ;
    //Elastic Modulus
    protected double elasticAxisY;
    protected double elasticAxisZ;
    //Plastic Modulus
    protected double plasticAxisY;
    protected double plasticAxisZ;
    //Buckling Parameter
    protected double buckling;
    //Torsional Index
    protected double torsionIndex;
    //Warping Constant (dm^6)
    protected double warpConst;
    //Torsional Constant (centimetres^4)
    protected double torsionConst;
    //Area of section (centimetres^2)
    protected double area;


    /**
     * Constructor
     **/
    public UniversalBeam(List<String> line) {
        //take table row as parameter (Map() ?)
        //assign to fields

        this.preDesignation = line.get(0);
        System.out.println("[INSTANCE] Pre-Designation : " +this.preDesignation);

        this.designation = line.get(1);
        //line[2] is blank
        this.mass = Double.parseDouble(line.get(3));
        this.depth = Double.parseDouble(line.get(4));
        this.width = Double.parseDouble(line.get(5));
        this.web = Double.parseDouble(line.get(6));
        this.flange = Double.parseDouble(line.get(7));
        this.radius = Double.parseDouble(line.get(8));
        this.d = Double.parseDouble(line.get(9));

        this.ratioWeb = Double.parseDouble(line.get(10));
        this.ratioFlange = Double.parseDouble(line.get(11));

        this.endClearance = Double.parseDouble(line.get(12));
        this.N = Double.parseDouble(line.get(13));
        this.n = Double.parseDouble(line.get(14));

        this.perMetre = Double.parseDouble(line.get(15));
        this.perTonne = Double.parseDouble(line.get(16));

        this.secondAxisY = Double.parseDouble(line.get(17));
        this.secondAxisZ = Double.parseDouble(line.get(18));

        this.gyrAxisY = Double.parseDouble(line.get(19));
        this.gyrAxisZ = Double.parseDouble(line.get(20));

        this.elasticAxisY = Double.parseDouble(line.get(21));
        this.elasticAxisZ = Double.parseDouble(line.get(22));

        this.plasticAxisY = Double.parseDouble(line.get(23));
        this.plasticAxisZ = Double.parseDouble(line.get(24));

        this.buckling = Double.parseDouble(line.get(23));
        this.torsionIndex = Double.parseDouble(line.get(24));
        this.warpConst = Double.parseDouble(line.get(25));
        this.torsionConst = Double.parseDouble(line.get(26));
        this.area = Double.parseDouble(line.get(27));

    }


    /*
     *  Getters for displaying fields
     */

    public String getDesignation() {
        return designation;
    }

    public double getMass() {
        return mass;
    }

    public double massProperty() {
        return mass;
    }

    public double getDepth() {
        return depth;
    }

    public double getWidth() {
        return width;
    }

    public double getWeb() {
        return web;
    }

    public double getFlange() {
        return flange;
    }

    public double getRadius() {
        return radius;
    }

    public double getD() {
        return d;
    }

    public double getRatioWeb() {
        return ratioWeb;
    }

    public double getRatioFlange() {
        return ratioFlange;
    }

    public double getEndClearance() {
        return endClearance;
    }

    public double getN() {
        return N;
    }

    public double getn() {
        return n;
    }

    public double getPerMetre() {
        return perMetre;
    }

    public double getPerTonne() {
        return perTonne;
    }

    public double getSecondAxisY() {
        return secondAxisY;
    }

    public double getSecondAxisZ() {
        return secondAxisZ;
    }

    public double getGyrAxisY() {
        return gyrAxisY;
    }

    public double getGyrAxisZ() {
        return gyrAxisZ;
    }

    public double getElasticAxisY() {
        return elasticAxisY;
    }

    public double getElasticAxisZ() {
        return elasticAxisZ;
    }

    public double getPlasticAxisY() {
        return plasticAxisY;
    }

    public double getPlasticAxisZ() {
        return plasticAxisZ;
    }

    public double getBuckling() {
        return buckling;
    }

    public double getTorsionIndex() {
        return torsionIndex;
    }

    public double getWarpConst() {
        return warpConst;
    }

    public double getArea() {
        return area;
    }

    public double getTorsionConst() {
        return torsionConst;
    }

    public String getPreDesignation() {
        return preDesignation;
    }

    public void setPreDesignation(String currentReadDesignation) {
        this.preDesignation = currentReadDesignation;
    }
}
