import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Test on 10/30/2016.
 */
public class AtmTest {

    private Atm atm;
    public Map ztest = new TreeMap();

    @Before
    public void setUp() {
        atm = new Atm();
    }
    Map <BankNote, Integer> result;

    @Test
    public void sampleSession() {
        result = atm.runCommand("?");
        Assert.assertEquals(ztest, result);
        result = atm.runCommand("+",Currency.USD, 100, 30);
        ztest.put(new BankNote(Currency.USD, 100), 30);
        Assert.assertEquals(ztest, result);
        result = atm.runCommand("+",Currency.RUR, 250, 10);
        Assert.assertEquals(null, result);
        result = atm.runCommand("+",Currency.CHF, 100, 5);
        ztest.clear();
        ztest.put(new BankNote(Currency.CHF, 100), 5);
        Assert.assertEquals(ztest, result);
        result = atm.runCommand("+",Currency.USD, 10, 50);
        ztest.clear();
        ztest.put(new BankNote(Currency.USD, 10), 50);
        Assert.assertEquals(ztest, result);
        result = atm.runCommand("?");
        ztest.clear();
        ztest.put(new BankNote(Currency.USD, 10), 50);
        ztest.put(new BankNote(Currency.CHF, 100), 5);
        ztest.put(new BankNote(Currency.USD, 100), 30);
        Assert.assertEquals(ztest, result);
        result = atm.runCommand("-",Currency.USD, 120);
        ztest.clear();
        ztest.put(new BankNote(Currency.USD, 100), 1);
        ztest.put(new BankNote(Currency.USD, 10), 2);
        Assert.assertEquals(ztest, result);
        result = atm.runCommand("-",Currency.RUR, 500);
        Assert.assertEquals(null, result);
        result = atm.runCommand("-",Currency.CHF, 250);
        Assert.assertEquals(null, result);
        result = atm.runCommand("?");
        ztest.clear();
        ztest.put(new BankNote(Currency.CHF, 100), 5);
        ztest.put(new BankNote(Currency.USD, 10), 48);
        ztest.put(new BankNote(Currency.USD, 100), 29);
        Assert.assertEquals(ztest, result);
        result = atm.runCommand("-",Currency.CHF, 400);
        ztest.clear();
        ztest.put(new BankNote(Currency.CHF, 100), 4);
        Assert.assertEquals(ztest, result);
        result = atm.runCommand("+",Currency.CHF, 10, 50);
        ztest.clear();
        ztest.put(new BankNote(Currency.CHF, 10), 50);
        result = atm.runCommand("?");
        ztest.clear();
        ztest.put(new BankNote(Currency.CHF, 10), 50);
        ztest.put(new BankNote(Currency.CHF, 100), 1);
        ztest.put(new BankNote(Currency.USD, 10), 48);
        ztest.put(new BankNote(Currency.USD, 100), 29);
        Assert.assertEquals(ztest, result);
    }

    @Test
    public void testShouldWithdrawChf() {
        atm.getMoneyStorage().getBanknotes().put(new BankNote(Currency.CHF, 100), 4);
    }

}
