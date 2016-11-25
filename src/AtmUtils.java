/**
 * Created by Test on 11/24/2016.
 */
public class AtmUtils {
    static int parseInt(String stringToParse, AtmStateException parseException) throws AtmStateException {
        try {
            return Integer.parseInt(stringToParse);
        } catch (NumberFormatException e) {
            throw parseException;
        }
    }
    static void lengthCheck(int lengthValue, AtmStateException lengthException,  String... stringArray) throws AtmStateException{
        if (stringArray.length != lengthValue) {
            throw lengthException;
        }
    }
}
