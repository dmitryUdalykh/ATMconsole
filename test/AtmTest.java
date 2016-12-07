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
    private Map<BankNote, Integer> atmTest = new TreeMap<>();

    @Before
    public void setUp() {
        atm = new Atm();
    }

    @Test
    public void sampleSession() throws AtmStateException {

        Map<BankNote, Integer> result = atm.runCommand("?");
        Assert.assertEquals(atmTest, result);

        result = atm.runCommand("+", "USD", "100", "30");
        atmTest.put(new BankNote(Currency.USD, 100), 30);
        Assert.assertEquals(atmTest, result);

        try {
            atm.runCommand("+", "RUR", "250", "10");
        } catch (AtmStateException e) {
        }

        result = atm.runCommand("+", "CHF", "100", "5");
        atmTest.clear();
        atmTest.put(new BankNote(Currency.CHF, 100), 5);
        Assert.assertEquals(atmTest, result);

        result = atm.runCommand("+", "USD", "10", "50");
        atmTest.clear();
        atmTest.put(new BankNote(Currency.USD, 10), 50);
        Assert.assertEquals(atmTest, result);

        result = atm.runCommand("?");
        atmTest.clear();
        atmTest.put(new BankNote(Currency.USD, 10), 50);
        atmTest.put(new BankNote(Currency.CHF, 100), 5);
        atmTest.put(new BankNote(Currency.USD, 100), 30);
        Assert.assertEquals(atmTest, result);

        result = atm.runCommand("-", "USD", "120");
        atmTest.clear();
        atmTest.put(new BankNote(Currency.USD, 100), 1);
        atmTest.put(new BankNote(Currency.USD, 10), 2);
        Assert.assertEquals(atmTest, result);

        try {
            atm.runCommand("-", "RUR", "500");
        } catch (AtmStateException e) {
        }

        try {
            atm.runCommand("-", "CHF", "250");
        } catch (AtmStateException e) {
        }

        result = atm.runCommand("?");
        atmTest.clear();
        atmTest.put(new BankNote(Currency.CHF, 100), 5);
        atmTest.put(new BankNote(Currency.USD, 10), 48);
        atmTest.put(new BankNote(Currency.USD, 100), 29);
        Assert.assertEquals(atmTest, result);

        try {
            atm.runCommand("+", "eur", "100", "5");
        } catch (AtmStateException e) {
        }

        result = atm.runCommand("-", "CHF", "400");
        atmTest.clear();
        atmTest.put(new BankNote(Currency.CHF, 100), 4);
        Assert.assertEquals(atmTest, result);

        result = atm.runCommand("+", "CHF", "10", "50");
        atmTest.clear();
        atmTest.put(new BankNote(Currency.CHF, 10), 50);
        Assert.assertEquals(atmTest, result);

        result = atm.runCommand("?");
        atmTest.clear();
        atmTest.put(new BankNote(Currency.CHF, 10), 50);
        atmTest.put(new BankNote(Currency.CHF, 100), 1);
        atmTest.put(new BankNote(Currency.USD, 10), 48);
        atmTest.put(new BankNote(Currency.USD, 100), 29);
        Assert.assertEquals(atmTest, result);

        result = atm.runCommand("+", "RUR", "100", "1");
        atmTest.clear();
        atmTest.put(new BankNote(Currency.RUR, 100), 1);
        Assert.assertEquals(atmTest, result);

        result = atm.runCommand("+", "RUR", "10", "20");
        atmTest.clear();
        atmTest.put(new BankNote(Currency.RUR, 10), 20);
        Assert.assertEquals(atmTest, result);

        result = atm.runCommand("-", "RUR", "200");
        atmTest.clear();
        atmTest.put(new BankNote(Currency.RUR, 100), 1);
        atmTest.put(new BankNote(Currency.RUR, 10), 10);
        Assert.assertEquals(atmTest, result);
    }
}
