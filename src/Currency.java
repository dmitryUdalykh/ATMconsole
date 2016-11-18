/**
 * Created by Test on 10/30/2016.
 */
enum Currency {
    USD,
    RUR,
    CHF,
    EUR,
    JPY;

    static boolean checkCurrency(String currencyForChecking) throws AtmStateException {
        for (Currency z : Currency.values()) {
            if (currencyForChecking.equals(z.toString())) {
                return true;
            }
        }
        throw new AtmStateException("ILLEGAL CURRENCY TYPE");
    }
}
