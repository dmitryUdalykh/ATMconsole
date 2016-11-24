/**
 * Created by Test on 10/30/2016.
 */

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

class Atm {
    // TODO: according to Java code conventions (http://www.oracle.com/technetwork/java/javase/documentation/codeconventions-135099.html)
    // constants should look like QUIT_WORD etc
    private static final String quitWord = "QUIT";
    private static final String okWord = "OK";
    private static final String errorWord = "ERROR";

    private AtmCommandFactory atmCommandFactory;

    Atm() {
        // TODO: why declaration and assignments are on 2 different lines?
        MoneyStorage moneyStorage;
        moneyStorage = new MoneyStorage();
        atmCommandFactory = new AtmCommandFactory(moneyStorage);
    }

    Map<BankNote, Integer> runCommand(String command, String... arguments) throws AtmStateException {

        AtmCommand atmCommand = atmCommandFactory.create(command);
        return atmCommand.execute(arguments);
    }

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        Atm atm = new Atm();
        while (true) {
            // pass parameters from console to ATM
            try {
                final String input = scanner.nextLine();
                //TODO: why these two declarations are here and not there the variables are assigned?
                String command;
                String[] arguments;

                //TODO: why this if is needed? Is it possible that "nextLine" returns null ?
                if (input != null) {
                    String[] lineToRead = input.split("\\s");
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
                // TODO: should be "println"
                System.out.print(errorWord);
            }
        }
    }
}
