import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Test on 10/30/2016.
 */
public class AtmTest {

    private Atm atm;
    private Map<BankNote, Integer> ztest = new TreeMap<>();

    @Before
    public void setUp() {
        atm = new Atm();
    }

    @Test
    public void sampleSession() throws AtmStateException {

        Map<BankNote, Integer> result = atm.runCommand("?");
        Assert.assertEquals(ztest, result);

        result = atm.runCommand("+", "USD", "100", "30");
        ztest.put(new BankNote(Currency.USD, 100), 30);
        Assert.assertEquals(ztest, result);

        try {
            atm.runCommand("+", "RUR", "250", "10");
        } catch (AtmStateException e) {
        }

        result = atm.runCommand("+", "CHF", "100", "5");
        ztest.clear();
        ztest.put(new BankNote(Currency.CHF, 100), 5);
        Assert.assertEquals(ztest, result);

        result = atm.runCommand("+", "USD", "10", "50");
        ztest.clear();
        ztest.put(new BankNote(Currency.USD, 10), 50);
        Assert.assertEquals(ztest, result);

        result = atm.runCommand("?");
        ztest.clear();
        ztest.put(new BankNote(Currency.USD, 10), 50);
        ztest.put(new BankNote(Currency.CHF, 100), 5);
        ztest.put(new BankNote(Currency.USD, 100), 30);
        Assert.assertEquals(ztest, result);

        result = atm.runCommand("-", "USD", "120");
        ztest.clear();
        ztest.put(new BankNote(Currency.USD, 100), 1);
        ztest.put(new BankNote(Currency.USD, 10), 2);
        Assert.assertEquals(ztest, result);

        try {
            atm.runCommand("-", "RUR", "500");
        } catch (AtmStateException e) {
        }

        try {
            atm.runCommand("-", "CHF", "250");
        } catch (AtmStateException e) {
        }

        result = atm.runCommand("?");
        ztest.clear();
        ztest.put(new BankNote(Currency.CHF, 100), 5);
        ztest.put(new BankNote(Currency.USD, 10), 48);
        ztest.put(new BankNote(Currency.USD, 100), 29);
        Assert.assertEquals(ztest, result);

        try {
            atm.runCommand("+", "eur", "100", "5");
        } catch (AtmStateException e) {
        }

        result = atm.runCommand("-", "CHF", "400");
        ztest.clear();
        ztest.put(new BankNote(Currency.CHF, 100), 4);
        Assert.assertEquals(ztest, result);

        result = atm.runCommand("+", "CHF", "10", "50");
        ztest.clear();
        ztest.put(new BankNote(Currency.CHF, 10), 50);
        Assert.assertEquals(ztest, result);

        result = atm.runCommand("?");
        ztest.clear();
        ztest.put(new BankNote(Currency.CHF, 10), 50);
        ztest.put(new BankNote(Currency.CHF, 100), 1);
        ztest.put(new BankNote(Currency.USD, 10), 48);
        ztest.put(new BankNote(Currency.USD, 100), 29);
        Assert.assertEquals(ztest, result);

    }

    @Test
    public void testAtmCommand() {
        CommandType commandCheck = CommandType.getCommandType(null);
        Assert.assertEquals(commandCheck, CommandType.UNDEFINED);
    }
}
