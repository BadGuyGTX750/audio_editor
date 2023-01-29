package export_service;

import builder_service.Client;
import command_service.ResponsesSignals;

import java.io.IOException;

import static utility.Logger.logger;

public class ExportClient implements Client {
    private static ExportClient exportClientImplementation;
    private ExportFileContainer exportFileContainer;
    private ExportFileWriter exportFileWriter;

    public static ExportClient getExportClient() {
        return exportClientImplementation;
    }
    public static ExportClient getExportClient(ExportFileContainer exportFileContainer, ExportFileWriter exportFileWriter) {
        if (exportClientImplementation == null)
            exportClientImplementation = new ExportClient(exportFileContainer, exportFileWriter);
        return exportClientImplementation;
    }
    private ExportClient(ExportFileContainer exportFileContainer, ExportFileWriter exportFileWriter) {
        logger(4, "ExportClient is being initialized");
        this.exportFileContainer = exportFileContainer;
        this.exportFileWriter = exportFileWriter;
    }

    @Override
    public void runClient(ResponsesSignals responsesSignals) {
        if (!responsesSignals.getExportSignal()) {
            logger(4, "skipping runClient() of ExportService");
            return;
        }
        logger(4, "runClient() of ExportService");
        try {
            exportFileWriter.writeFile(exportFileContainer.getFileContainer(), responsesSignals.getResponse());
            logger(4, "exporting File Container writing with exportFileWriter.writeFile(filePath)");
        }
        catch(Exception e) {
            logger(2, "exportFileWriter.writeFile(filePath) could not write file");
        }
    }
}
