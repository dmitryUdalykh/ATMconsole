import java.util.Map;

/**
 * Created by Test on 10/30/2016.
 */
public interface AtmCommand {

    // TODO: I think we should accept an array of Strings and not just objects
    Map<BankNote, Integer> execute(Object... arguments) throws AtmStateException;
}
