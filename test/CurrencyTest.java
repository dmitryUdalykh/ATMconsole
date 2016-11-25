/**
 * Created by Test on 11/25/2016.
 */

import org.junit.Assert;
import org.junit.Test;

public class CurrencyTest {

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
    }

    @Test(expected = AtmStateException.class)
    public void testCurrncyException() throws AtmStateException {
        Currency.getCurrency("Abcde");
    }
}
