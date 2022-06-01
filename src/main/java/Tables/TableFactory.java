package Tables;

public class TableFactory {
    public SteelTableView createTable(String discrim) {

        //dataset for Universal Beams
        if(discrim.equals("UB")) {
            return new UBTableView();
        }
        else {
            return null;
        }
    }

}
