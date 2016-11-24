/**
 * Created by Test on 10/30/2016.
 * TODO: please write a unit test for this class checking that valid currencies are asserted ok and invalid result in throwing AtmStateException
 */
enum Currency {
    CHF,
    EUR,
    JPY,
    RUR,
    USD;

    //TODO: rename to "getCurrency"; return Currency instead
    static boolean checkCurrency(String currencyForChecking) throws AtmStateException {
        for (Currency z : Currency.values()) {
            //TODO: this is risky; of "currencyForChecking" is null then NullPointerException will be thrown
            // It's better to do it the other way around: z.toString().equals(currencyForChecking) - this way even if currencyForChecking is null
            // no NPE would be thrown
            if (currencyForChecking.equals(z.toString())) {
                // should be just "return"
                return true;
            }
        }

        throw new AtmStateException("ILLEGAL CURRENCY TYPE");

        /*
         * JDK 8 alternative:
         *
         * return Arrays.stream(Currency.values())
         *           .filter(currency -> currency.toString().equals(currencyForChecking))
         *           .findFirst()
         *           .orElseThrow(() -> new AtmStateException("ILLEGAL CURRENCY TYPE"));
         */
    }
}
