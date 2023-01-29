package builder_service;

import command_service.CommandLineClient;
import command_service.CommandInterpreter;

import static command_service.CommandLineClient.getCommandLineClient;
import static utility.Logger.logger;

public class CommandServiceBuilder implements Builder{
    private static CommandServiceBuilder commandServiceBuilderImplementation;
    private CommandLineClient commandClient;
    private CommandInterpreter commandInterpreter;
    private CommandServiceBuilder() {

    }
    public static CommandServiceBuilder getCommandServiceBuilder() {
        if (commandServiceBuilderImplementation == null)
            commandServiceBuilderImplementation = new CommandServiceBuilder();
        return commandServiceBuilderImplementation;
    }
    @Override
    public void build() {
        logger(4, "Resolving dependencies for CommandService");
        this.commandInterpreter = new CommandInterpreter();
        this.commandClient = getCommandLineClient(this.commandInterpreter);
    }
}
