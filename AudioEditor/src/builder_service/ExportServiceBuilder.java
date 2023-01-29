package builder_service;

import export_service.ExportClient;
import export_service.ExportFileContainer;
import export_service.ExportFileWriter;

import static export_service.ExportClient.getExportClient;
import static export_service.ExportFileContainer.getExportFileContainer;
import static utility.Logger.logger;

public class ExportServiceBuilder implements Builder{
    private static ExportServiceBuilder exportServiceBuilderImplementation;
    private ExportClient exportClient;
    private ExportFileContainer exportFileContainer;
    private ExportFileWriter exportFileWriter;
    public static ExportServiceBuilder getExportServiceBuilder() {
        if (exportServiceBuilderImplementation == null)
            exportServiceBuilderImplementation = new ExportServiceBuilder();
        return exportServiceBuilderImplementation;
    }
    private ExportServiceBuilder() {

    }
    @Override
    public void build() {
        logger(4, "Resolving dependencies for ExportService");
        this.exportFileContainer = getExportFileContainer();
        this.exportFileWriter = new ExportFileWriter();
        this.exportClient = getExportClient(this.exportFileContainer, this.exportFileWriter);
    }
}
