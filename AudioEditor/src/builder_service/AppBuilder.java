package builder_service;

import command_service.CommandLineClient;

import java.util.ArrayList;
import java.util.List;

import static builder_service.CommandServiceBuilder.getCommandServiceBuilder;
import static builder_service.ExportServiceBuilder.getExportServiceBuilder;
import static builder_service.HistoryServiceBuilder.getHistoryServiceBuilder;
import static builder_service.ImportServiceBuilder.getImportServiceBuilder;
import static builder_service.MigrationServiceBuilder.getMigrationServiceBuilder;
import static builder_service.ModifierServiceBuilder.getModifierServiceBuilder;
import static canvas.CanvasHistoryClient.getCanvasHistoryClient;
import static command_service.CommandLineClient.getCommandLineClient;
import static export_service.ExportClient.getExportClient;
import static import_service.ImportClient.getImportClient;
import static migration_service.MigrationClient.getMigrationClient;
import static modifier_service.ModifierClient.getModifierClient;
import static utility.Logger.logger;

public final class AppBuilder {
    private static AppBuilder appBuilderImplementation;
    private List<Builder> builderList = new ArrayList<>();
    private CommandLineClient commandClient;
    private ClientContainer clientContainer;
    public static AppBuilder getAppBuilder() {
        if (appBuilderImplementation == null)
            appBuilderImplementation = new AppBuilder();
        return appBuilderImplementation;
    }
    private AppBuilder() {
        logger(4, "AppBuilder is being initialized");
        // add Builder which resolve dependencies for each service
        addBuilder(getCommandServiceBuilder());
        addBuilder(getImportServiceBuilder());
        addBuilder(getMigrationServiceBuilder());
        addBuilder(getExportServiceBuilder());
        addBuilder(getModifierServiceBuilder());
        addBuilder(getHistoryServiceBuilder());
    }
    public void mainBuild() {
        for (Builder builder : builderList) {
            builder.build();
        }
        // add clients to ClientContainer to deliver it to CommandLineClient
        // in order to run every Client runClient
        clientContainer = new ClientContainer();
        clientContainer.addClient(getImportClient());
        clientContainer.addClient(getMigrationClient());
        clientContainer.addClient(getExportClient());
        clientContainer.addClient(getModifierClient());
        clientContainer.addClient(getCanvasHistoryClient());

        // run the command client to give commands to the app
        commandClient = getCommandLineClient();
        commandClient.runCommandLineClient(clientContainer);
    }
    public void addBuilder(Builder builder) {
        builderList.add(builder);
    }
    public void removeBuilder(Builder builder) {
        builderList.remove(builder);
    }
}
