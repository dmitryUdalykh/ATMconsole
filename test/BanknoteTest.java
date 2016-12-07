import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Test on 12/07/2016.
 */
public class BanknoteTest {

    @Test
    public void testBanknoteToString() throws AtmStateException {
        BankNote testBanknote = new BankNote(Currency.USD, 100);
        String expected = "USD 100";
        Assert.assertEquals(testBanknote.toString(), expected);
    }
}
