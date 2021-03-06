package Reader;

public class ReaderFactory implements ReaderFactoryIF {


    /**
     * Return the reader to collect the correct information
     */
    @Override
    public SteelReader createReader(String discrim) {
        if(discrim.equals("(UB) Universal Beams")) {
            return new UniversalBeamsReader();
        }

        else {
            return null;
        }
    }
}
