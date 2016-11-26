/**
 * Created by Test on 11/25/2016.
 */

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.util.Map;
import java.util.TreeMap;

public class MoneyStorageTest {

    private MoneyStorage moneyStorage;
    private Map<BankNote, Integer> storageCheck = new TreeMap<>();

    @Before
    public void setUp() {
        moneyStorage = new MoneyStorage();
    }

    @Test
    public void moneyStorageTest() throws AtmStateException {
        moneyStorage.addNotes(Currency.USD, 100, 1);
        moneyStorage.addNotes(Currency.USD, 100, 2);
        storageCheck.put(new BankNote(Currency.USD, 100), 3);
        Assert.assertEquals(storageCheck, moneyStorage.getBanknotes());
    }

    @Test(expected = AtmStateException.class)
    public void moneyStorageTest2() throws AtmStateException {
        moneyStorage.addNotes(Currency.RUR, 120, 1);
    }

    @Test(expected = AtmStateException.class)
    public void moneyStorageTest3() throws AtmStateException {
        moneyStorage.addNotes(Currency.USD, 0, 1);
    }
}
