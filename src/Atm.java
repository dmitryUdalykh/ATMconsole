/**
 * Created by Test on 10/30/2016.
 */

import java.io.Console;
import java.util.Arrays;
import java.util.Map;

public class Atm {
    private AtmCommandFactory atmCommandFactory;
    private MoneyStorage moneyStorage;

    Atm() {
        moneyStorage = new MoneyStorage();
        atmCommandFactory = new AtmCommandFactory(moneyStorage);
    }

    Map<BankNote, Integer> runCommand(String command, String... arguments) throws AtmStateException {

        AtmCommand atmCommand = atmCommandFactory.create(command);
        return atmCommand.execute(arguments);
    }

    MoneyStorage getMoneyStorage() {
        return moneyStorage;
    }

    public static void main(String[] args) {
        Console atmConsole = System.console();

        Atm atm = new Atm();
        while (true) {
            // pass parameters from console to ATM
            try {
                String[] lineToRead = atmConsole.readLine().split("\\s");
                String command = lineToRead[0];

                // TODO: create private static final String constants for "QUITE", "OK", and "ERROR"
                if (command.equalsIgnoreCase("QUIT")) {
                    System.exit(0);
                }

                String[] arguments = Arrays.copyOfRange(lineToRead, 1, lineToRead.length);
                Map<BankNote, Integer> response = atm.runCommand(command, arguments);

                response.entrySet().forEach(entry ->
                        System.out.println(entry + " " + entry.getValue())
                );
                System.out.println("OK");
            } catch (AtmStateException e) {
                System.out.print("ERROR");
            }
        }
    }
}
