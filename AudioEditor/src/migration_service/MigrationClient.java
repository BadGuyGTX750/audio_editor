package migration_service;

import builder_service.Client;
import canvas.Canvas;
import command_service.ResponsesSignals;
import export_service.ExportFileContainer;
import import_service.ImportFileContainer;

import static utility.Logger.logger;

public class MigrationClient implements Client {
    private static MigrationClient migrationClientImplementation;
    private ImportFileContainer importFileContainer;
    private ExportFileContainer exportFileContainer;
    private Canvas canvas;

    private MigrationClient(ImportFileContainer importFileContainer,
                            ExportFileContainer exportFileContainer, Canvas canvas) {
        logger(4, "MigrationClient is being initialized");
        this.importFileContainer = importFileContainer;
        this.exportFileContainer = exportFileContainer;
        this.canvas = canvas;
    }
    public static MigrationClient getMigrationClient() {
        return migrationClientImplementation;
    }
    public static MigrationClient getMigrationClient(ImportFileContainer importFileContainer,
                                                     ExportFileContainer exportFileContainer,
                                                     Canvas canvas) {
        if (migrationClientImplementation == null)
            migrationClientImplementation = new MigrationClient(importFileContainer, exportFileContainer, canvas);
        return migrationClientImplementation;
    }

    @Override
    public void runClient(ResponsesSignals responsesSignals) {
        if (!responsesSignals.getMigrate2CanvasSignal() && !responsesSignals.getMigrateFromCanvasSignal()) {
            //logger(4, "skipping runClient() of MigrationService");
            return;
        }
        logger(4, "runClient() of MigrationService");
        if (responsesSignals.getMigrate2CanvasSignal()) {
            try {
                byte[] file = importFileContainer.getFileContainer();
                canvas.setFileHeaderAndFileData(file);
                importFileContainer.clearFileContainer();
                //canvas.showFileData();
            } catch(Exception e) {
                logger(2, e.getMessage());
            }

        }
        if (responsesSignals.getMigrateFromCanvasSignal()) {
            try {
                byte[] fileHeader = canvas.getFileHeader();
                byte[] fileData = canvas.getFileData();
                byte[] file = new byte[fileHeader.length + fileData.length];
                for (int i = 0; i < fileHeader.length; i++) {
                    file[i] = fileHeader[i];
                }
                for (int i = 44; i < fileHeader.length + fileData.length; i++) {
                    file[i] = fileData[i-fileHeader.length];
                }
                exportFileContainer.setFileContainer(file);
                //exportFileContainer.showFileContainer();
            } catch(Exception e) {
                logger(2, e.getMessage());
            }
        }
    }
}
