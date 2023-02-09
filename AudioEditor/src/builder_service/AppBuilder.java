package builder_service;

import canvas.Canvas;
import canvas.CanvasHistory;
import canvas.CanvasHistoryClient;
import canvas.CanvasState;
import command_service.CommandInterpreter;
import command_service.CommandLineClient;
import dependency_injection.DependencyContainer;
import dependency_injection.DependencyResolver;
import export_service.ExportClient;
import export_service.ExportFileContainer;
import export_service.ExportFileWriter;
import import_service.ImportClient;
import import_service.ImportFileContainer;
import import_service.ImportFileReader;
import migration_service.MigrationClient;
import modifier_service.*;

import static utility.Logger.logger;
import static utility.Logger.printAllLogs;

public final class AppBuilder {
    private static AppBuilder appBuilderImplementation;
    private CommandLineClient commandClient;
    private ClientContainer clientContainer;
    private DependencyContainer dependencyContainer;
    private DependencyResolver dependencyResolver;
    public static AppBuilder getAppBuilder() {
        if (appBuilderImplementation == null)
            appBuilderImplementation = new AppBuilder();
        return appBuilderImplementation;
    }
    private AppBuilder() {
        logger(4, "AppBuilder is being initialized");
        dependencyContainer = new DependencyContainer();
        // Add all dependencies
        // CommandLineClient dependencies
        dependencyContainer.add(CommandLineClient.class, true);
        dependencyContainer.add(CommandInterpreter.class, true);
        // ImportClient dependencies
        dependencyContainer.add(ImportClient.class, true);
        dependencyContainer.add(ImportFileContainer.class, true);
        dependencyContainer.add(ImportFileReader.class, true);
        // MigrationClient dependencies
        dependencyContainer.add(MigrationClient.class, true);
        // ExportClient dependencies
        dependencyContainer.add(ExportClient.class, true);
        dependencyContainer.add(ExportFileContainer.class, true);
        dependencyContainer.add(ExportFileWriter.class, true);
        // ModifierClient dependencies
        dependencyContainer.add(ModifierClient.class, true);
        dependencyContainer.add(ModifierContainer.class, true);
        dependencyContainer.add(ChangeVolume.class, true);
        dependencyContainer.add(FadeIn.class, true);
        dependencyContainer.add(FadeOut.class, true);
        dependencyContainer.add(Delete.class, true);
        // CanvasHistoryClient dependencies
        dependencyContainer.add(Canvas.class, true);
        dependencyContainer.add(CanvasHistoryClient.class, true);
        dependencyContainer.add(CanvasHistory.class, true);
        dependencyContainer.add(CanvasState.class, true);
        // CommandLineClient dependencies
        dependencyContainer.add(CommandLineClient.class, true);
        dependencyContainer.add(CommandInterpreter.class, true);
        // Instantiate resolver with the dependency container
        dependencyResolver = new DependencyResolver(dependencyContainer);
    }
    public void mainBuild() {
        // Add clients to ClientContainer to deliver it to CommandLineClient
        // in order to run every Client runClient
        clientContainer = new ClientContainer();
        try {
            clientContainer.addClient((ImportClient)dependencyResolver.getService(ImportClient.class));
            clientContainer.addClient((MigrationClient)dependencyResolver.getService(MigrationClient.class));
            clientContainer.addClient((ExportClient)dependencyResolver.getService(ExportClient.class));
            clientContainer.addClient((ModifierClient)dependencyResolver.getService(ModifierClient.class));
            clientContainer.addClient((CanvasHistoryClient)dependencyResolver.getService(CanvasHistoryClient.class));
            // Run the command client to give commands to the app
            commandClient = (CommandLineClient)dependencyResolver.getService(CommandLineClient.class);
            commandClient.runCommandLineClient(clientContainer);
        } catch(Exception e) {
            logger(2, e.getMessage());
            printAllLogs();
        }
    }
}
