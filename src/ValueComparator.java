/**
 * Created by Test on 11/04/2016.
 */

import java.util.Comparator;

class ValueComparator implements Comparator<BankNote> {

    @Override
    public int compare(BankNote alpha, BankNote beta) {
        return Integer.compare(alpha.getValue(), beta.getValue());
    }
}
