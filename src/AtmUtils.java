/**
 * Created by Test on 11/24/2016.
 */
public class AtmUtils {
    static int parseInt(String stringToParse, AtmStateException thrownAtmException) throws AtmStateException {
        try {
            return Integer.parseInt(stringToParse);
        } catch (NumberFormatException e) {
            throw thrownAtmException;
        }
    }
}
