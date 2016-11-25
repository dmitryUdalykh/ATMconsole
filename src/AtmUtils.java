/**
 * Created by Test on 11/24/2016.
 */
class AtmUtils {
    static int parseInt(String stringToParse, AtmStateException parseException) throws AtmStateException {
        try {
            return Integer.parseInt(stringToParse);
        } catch (NumberFormatException e) {
            throw parseException;
        }
    }

    static void assertLengthCheck(int lengthCheck, String... stringArray) throws AtmStateException {
        if (stringArray.length != lengthCheck) {
            throw new AtmStateException("WRONG NUMBER OF PARAMETERS");
        }
    }
}
