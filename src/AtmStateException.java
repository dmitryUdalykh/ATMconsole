/**
 * Created by Test on 11/06/2016.
 */
public class AtmStateException extends Exception {
    String CauseOfAnError;

    public AtmStateException(String COAE) {
        System.out.println(COAE);
    }
}
