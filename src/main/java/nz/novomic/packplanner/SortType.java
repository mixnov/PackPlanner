package nz.novomic.packplanner;

/**
 * The parameter of the Items list sort
 *
 * @author Mikhail
 */
public enum SortType {

    /**
     * To leave the order as it is
     */
    NATURAL,
    /**
     * Sort the Items by length from short to long
     */
    SHORT_TO_LONG,
    /**
     * Sort the Items by length from long to short
     */
    LONG_TO_SHORT
}
