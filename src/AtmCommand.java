import java.util.Map;

/**
 * Created by Test on 10/30/2016.
 */
interface AtmCommand {
    Map<BankNote, Integer> execute(String... arguments) throws AtmStateException;
}
