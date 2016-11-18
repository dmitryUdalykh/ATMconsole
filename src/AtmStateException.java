/**
 * Created by Test on 11/06/2016.
 */
public class AtmStateException extends Exception {
    // TODO: it's not needed
    String CauseOfAnError;

    // TODO: "COAE" is invalid name for a method attribute; should be in camel-case; can be named just as a message
    public AtmStateException(String COAE) {
        // Let's remove System.out.println and pass "message" to "super" controller as an attribute
        System.out.println(COAE);
    }
}
