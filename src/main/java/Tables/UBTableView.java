package Tables;

import Results.UniversalBeam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class UBTableView extends SteelTableView {

    /*
     * Table
     */
    protected TableView<UniversalBeam> table = new TableView<>();


    /*
     * Columns
     */
    protected TableColumn<UniversalBeam,String> designationCol;
    protected TableColumn<UniversalBeam, Double> massCol;
    protected TableColumn<UniversalBeam, Double> depthCol;
    protected TableColumn<UniversalBeam, Double> widthCol;
    protected TableColumn<UniversalBeam, Double> thicknessCol;
    protected TableColumn<UniversalBeam, Double> webCol;
    protected TableColumn<UniversalBeam, Double> flangeCol;
    protected TableColumn<UniversalBeam, Double> radiusCol;
    protected TableColumn<UniversalBeam, Double> dCol;
    protected TableColumn<UniversalBeam, Double> ratioCol;
    protected TableColumn<UniversalBeam, Double> ratioWebCol;
    protected TableColumn<UniversalBeam, Double> ratioFlangeCol;


    /***
     * Constructor
     */
    public UBTableView() {
        System.out.println("UB Table created");
        this.setColumns();
    }


    /**
     * Define Column headings
     */
    @Override
    public void setColumns() {
        //make public fields?
        designationCol = new TableColumn<>("Section Designation");
        massCol = new TableColumn<>("Mass per metre (kg/m)");
        depthCol = new TableColumn<>("Depth of Section - h (mm)");
        widthCol = new TableColumn<>("Width of Section - b (mm)");

        thicknessCol = new TableColumn<>("Thickness");
        webCol = new TableColumn<>("Web t^w (mm)");
        flangeCol = new TableColumn<>("Flange t^f (mm)");
        thicknessCol.getColumns().addAll(webCol, flangeCol);

        radiusCol = new TableColumn<>("Root Radius - r (mm)");
        dCol = new TableColumn<>("Depth between fillets - d (mm)");

        ratioCol = new TableColumn<>("Ratios for local buckling");
        ratioWebCol = new TableColumn<>("Web - c^w/t^w");
        ratioFlangeCol = new TableColumn<>("Flange - c^f/t^f");
        ratioCol.getColumns().addAll(ratioWebCol, ratioFlangeCol);



        this.table.getColumns().addAll(
                designationCol,
                massCol,
                depthCol,
                widthCol,
                thicknessCol,
                radiusCol,
                dCol,
                ratioCol
        );

        System.out.println("Columns set.");
    }








    /**
     * return the table in its current state to be displayed
     */
    @Override
    public TableView getTable() {
        System.out.println("UB Table Retrieved.");
        return this.table;
    }


    /**
     * populate table with the results of the query
     */
    @Override
    public void setTableData(List<UniversalBeam> rows) {
        ObservableList<UniversalBeam> tableItems =
                FXCollections.observableArrayList();

        for (UniversalBeam d: rows) {
            System.out.println("Adding element to table. Designation: " + d.getDesignation());

            designationCol.setCellValueFactory(new PropertyValueFactory<>("strDesignation"));

            massCol.setCellValueFactory(new PropertyValueFactory<>("mass"));
            //System.out.print("Mass:" +massCol.getCellObservableValue(d));
            tableItems.add(d);
        }

        table.setItems(tableItems);
        System.out.println("Table updated.");
    }

}
