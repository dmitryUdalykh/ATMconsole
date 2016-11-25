/**
 * Created by Test on 11/18/2016.
 */

import java.util.Set;
import java.util.TreeSet;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;

class ExistingBanknotes {
    private static final Set<BankNote> exBank = new TreeSet<>(new CurrencyComparator().thenComparing(new ValueComparator()).reversed());

    static {
        asList(
                singletonMap(Currency.USD, asList(100, 50, 20, 10, 5, 2, 1)),
                singletonMap(Currency.RUR, asList(5000, 1000, 500, 100, 50, 10)),
                singletonMap(Currency.CHF, asList(1000, 200, 100, 50, 20, 10)),
                singletonMap(Currency.JPY, asList(10000, 5000, 1000)),
                singletonMap(Currency.EUR, asList(500, 200, 100, 50, 20, 10, 5))
        ).forEach(
                currencyListMap -> currencyListMap.forEach(
                        (currency, values) -> values.forEach(
                                value -> exBank.add(new BankNote(currency, value))
                        )
                )
        );
    }

    static void assertBanknote(Currency assertCurrency, int assertValue) throws AtmStateException {
        if (!exBank.contains(new BankNote(assertCurrency, assertValue))) {
            throw new AtmStateException("NOT EXISTING BANKNOTE");
        }
    }

    static Set<BankNote> getExistingBanknotes() {
        return exBank;
    }
}
