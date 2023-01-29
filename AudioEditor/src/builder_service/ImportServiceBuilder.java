package builder_service;

import import_service.ImportClient;
import import_service.ImportFileContainer;
import import_service.ImportFileReader;

import static import_service.ImportClient.getImportClient;
import static import_service.ImportFileContainer.getImportFileContainer;
import static utility.Logger.logger;

public final class ImportServiceBuilder implements Builder{
    private static ImportServiceBuilder importServiceBuilderImplementation;
    private ImportClient importClient;
    private ImportFileContainer importFileContainer;
    private ImportFileReader importFileReader;
    private ImportServiceBuilder() {
        logger(4, "ImportBuilder is being initialized");
    }
    public static ImportServiceBuilder getImportServiceBuilder() {
        if (importServiceBuilderImplementation == null)
            importServiceBuilderImplementation = new ImportServiceBuilder();
        return importServiceBuilderImplementation;
    }
    @Override
    public void build() {
        logger(4, "Resolving dependencies for ImportService");
        this.importFileContainer = getImportFileContainer();
        this.importFileReader = new ImportFileReader();
        this.importClient = getImportClient(this.importFileContainer, this.importFileReader);
    }
}
