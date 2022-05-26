package Results;

public class Designation {
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
    //Second moment of area (centimetres^4)
    protected double secondAxisY;
    protected double secondAxisZ;
    //Radius of gyration (centimetres)
    protected double gyrAxisY;
    protected double gyrAxisZ;
    //Elastic Modulus
    protected double ElasticAxisY;
    protected double ElasticAxisZ;
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


    /*
     * Constructor
     */
    public Designation() {
        //take table row as parameter (Map())
        //assign to fields
    }


    /*
     *  Getters for displaying fields
     */

    public double getMass() {
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
        return ElasticAxisY;
    }

    public double getElasticAxisZ() {
        return ElasticAxisZ;
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
}
