/**
 * Created by Test on 10/30/2016.
 */

import java.util.Arrays;

enum Currency {
    CHF,
    EUR,
    JPY,
    RUR,
    USD;

    static Currency getCurrency(String currencyForChecking) throws AtmStateException {
        return Arrays.stream(Currency.values())
                .filter(currency -> currency.toString().equals(currencyForChecking))
                .findFirst()
                .orElseThrow(() -> new AtmStateException("ILLEGAL CURRENCY TYPE"));
    }
}
