import org.junit.Test;

/**
 * Created by Test on 12/07/2016.
 */
public class UtilsTest {

    @Test(expected = AtmStateException.class)
    public void utilTestOne() throws AtmStateException {
        AtmUtils.parseInt("ABC", "STRING IS NOT PARSABLE");
    }

    @Test(expected = AtmStateException.class)
    public void utilTestTwo() throws AtmStateException {
        String[] stringArray = {"One", "Two"};
        AtmUtils.assertLengthCheck(3, stringArray);
    }
}
