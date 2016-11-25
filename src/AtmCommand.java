/**
 * Created by Test on 10/30/2016.
 */

import java.util.Map;

interface AtmCommand {
    Map<BankNote, Integer> execute(String... arguments) throws AtmStateException;
}
