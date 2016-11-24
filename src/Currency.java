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
        /*
        for (Currency z : Currency.values()) {
            if (z.toString().equals(currencyForChecking)) {
                return z;
            }
        }
        throw new AtmStateException("ILLEGAL CURRENCY TYPE");
*/
        //JDK 8 alternative:

        return Arrays.stream(Currency.values())
                .filter(currency -> currency.toString().equals(currencyForChecking))
                .findFirst()
                .orElseThrow(() -> new AtmStateException("ILLEGAL CURRENCY TYPE"));
    }
}
