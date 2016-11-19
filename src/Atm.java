/**
 * Created by Test on 10/30/2016.
 */

import java.io.Console;
import java.util.Arrays;
import java.util.Map;

class Atm {
    private static final String quitWord = "QUIT";
    private static final String okWord = "OK";
    private static final String errorWord = "ERROR";

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
                String command = "";
                String[] arguments = null;
                if (System.console() != null) {
                    String[] lineToRead = atmConsole.readLine().split("\\s");
                    command = lineToRead[0];
                    arguments = Arrays.copyOfRange(lineToRead, 1, lineToRead.length);
                    if (command.equalsIgnoreCase(quitWord)) {
                        System.exit(0);
                    }

                    Map<BankNote, Integer> response = atm.runCommand(command, arguments);
                    response.entrySet().forEach(entry ->
                            System.out.println(entry + " " + entry.getValue())
                    );
                    System.out.println(okWord);
                }

            } catch (AtmStateException e) {
                System.out.print(errorWord);
            }
        }
    }
}
