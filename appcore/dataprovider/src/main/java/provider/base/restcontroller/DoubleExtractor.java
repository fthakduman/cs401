package provider.base.restcontroller;

/**
 * An object which can return a double representation of passed objects.
 *
 * @author Philipp Buluschek
 *
 */
public class DoubleExtractor {

    /**
     * @return the double representation of the passed object.
     */
    public double doubleValue(Object o){
        if(o instanceof Double) {
            return ((Double) o ).doubleValue();
        }
        return 0;
    }
}