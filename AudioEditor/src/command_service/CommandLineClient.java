package command_service;

import builder_service.Client;
import builder_service.ClientContainer;

import java.util.List;
import java.util.Scanner;

import static utility.Logger.logger;
import static utility.Logger.printAllLogs;

public class CommandLineClient {
    private CommandInterpreter commandInterpreter;
    private String command = "";
    private String response = "No response";
    private boolean terminationSignal = false;
    private static CommandLineClient commandLineClientImplementation;

    public CommandLineClient(CommandInterpreter commandInterpreter) {
        this.commandInterpreter = commandInterpreter;
    }

    public static CommandLineClient getCommandLineClient() {
        return commandLineClientImplementation;
    }
    public static CommandLineClient getCommandLineClient(CommandInterpreter commandInterpreter) {
        if (commandLineClientImplementation == null)
            commandLineClientImplementation = new CommandLineClient(commandInterpreter);
        return commandLineClientImplementation;
    }

    public void runCommandLineClient(ClientContainer clientContainer) {
        logger(4, "CommandLineClient is running");
        printAllLogs();
        Scanner input = new Scanner(System.in);
        List<Client> clientList = clientContainer.getClientList();
        while (!terminationSignal) {
            System.out.print("Command >> ");
            String userInput = input.nextLine();
            ResponsesSignals responsesSignals = commandInterpreter.interpretCommand(userInput);
            response = responsesSignals.getResponse();
            terminationSignal = responsesSignals.getTerminationSignal();
            if (terminationSignal) {
                System.out.println("Response: " + response);
                logger(4, "Entering program quiting routine");
                // quiting logic
                break;
            }
            System.out.println("Response: " + response);
            for (Client client : clientList) {
                client.runClient(responsesSignals);
            }
            printAllLogs();
        }
        logger(4, "Program execution finished");
        printAllLogs();
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
