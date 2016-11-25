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

    //TODO: this test should be in a separate class CommandTypeTest
    @Test
    public void testAtmCommand() {
        CommandType addCheck = CommandType.getCommandType("+");
        Assert.assertEquals(addCheck, CommandType.ADD);

        CommandType withdrawalCheck = CommandType.getCommandType("-");
        Assert.assertEquals(withdrawalCheck, CommandType.WITHDRAW);

        CommandType remainCheck = CommandType.getCommandType("?");
        Assert.assertEquals(remainCheck, CommandType.REMAININGS);

        CommandType elseCheck = CommandType.getCommandType("Abcde");
        Assert.assertEquals(elseCheck, CommandType.UNDEFINED);

        CommandType nullCheck = CommandType.getCommandType(null);
        Assert.assertEquals(nullCheck, CommandType.UNDEFINED);
    }

    //TODO: this test should be in a separate class CurrencyTest
    @Test
    public void currencyTest() throws AtmStateException {
        Currency getUsd = Currency.getCurrency("USD");
        Assert.assertEquals(getUsd, Currency.USD);

        Currency getEur = Currency.getCurrency("EUR");
        Assert.assertEquals(getEur, Currency.EUR);

        Currency getJpy = Currency.getCurrency("JPY");
        Assert.assertEquals(getJpy, Currency.JPY);

        Currency getRur = Currency.getCurrency("RUR");
        Assert.assertEquals(getRur, Currency.RUR);

        Currency getChf = Currency.getCurrency("CHF");
        Assert.assertEquals(getChf, Currency.CHF);

        //TODO: this should be a separate method with annotation
        // @Test(expected = AtmStateException.class)
        try {
            Currency.getCurrency("Abcde");
        } catch (AtmStateException e) {
        }

    }
}
