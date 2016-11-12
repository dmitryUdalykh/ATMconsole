import java.util.Map;

/**
 * Created by Test on 10/30/2016.
 */
public interface AtmCommand {

    Map<BankNote, Integer> execute(Object... arguments) throws AtmStateException;
}
