package Reader;

public interface ReaderFactoryIF {

    /**
     * Return the reader to collect the correct information
     */
    SteelReader createReader(String discrim);

}
