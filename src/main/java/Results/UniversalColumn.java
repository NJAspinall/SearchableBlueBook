package Results;

import java.util.List;

public class UniversalColumn extends StructuralHollowSection {




    /**
     * Constructor
     **/
    public UniversalColumn(List<String> line) {

        //TODO take table row as parameter (Map() ?)
        //assign to fields

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
}
