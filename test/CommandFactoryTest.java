import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Test on 12/07/2016.
 */
public class CommandFactoryTest {
    private MoneyStorage moneyStorage = new MoneyStorage();
    private AtmCommandFactory atmCommandFactory;

    @Before
    public void setUp() {
        atmCommandFactory = new AtmCommandFactory(moneyStorage);
    }

    @Test(expected = AtmStateException.class)
    public void defaultAtmCommand() throws AtmStateException {
        atmCommandFactory.create("ABC");
    }
}
