/**
 * Created by Test on 11/24/2016.
 */
class AtmUtils {
    static int parseInt(String stringToParse, String exceptionMessage) throws AtmStateException {
        try {
            return Integer.parseInt(stringToParse);
        } catch (NumberFormatException e) {
            throw new AtmStateException(exceptionMessage);
        }
    }

    static void assertLengthCheck(int lengthCheck, String... stringArray) throws AtmStateException {
        if (stringArray.length != lengthCheck) {
            throw new AtmStateException("WRONG NUMBER OF PARAMETERS");
        }
    }
}
