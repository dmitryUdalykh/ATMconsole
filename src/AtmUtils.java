/**
 * Created by Test on 11/24/2016.
 */
//TODO: the code should be formatted - it's not at the moment
public class AtmUtils {
    static int parseInt(String stringToParse, AtmStateException parseException) throws AtmStateException {
        try {
            return Integer.parseInt(stringToParse);
        } catch (NumberFormatException e) {
            throw parseException;
        }
    }
    // TODO: name it "assertLengthCheck"
    // the signature should be lengthCheck(int lengthValue, String... stringArray)

    static void lengthCheck(int lengthValue, AtmStateException lengthException,  String... stringArray) throws AtmStateException{
        if (stringArray.length != lengthValue) {
            // TODO: throw new AtmStateException("WRONG NUMBER OF PARAMETERS") from here
            throw lengthException;
        }
    }
}
