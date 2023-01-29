package builder_service;

import canvas.Canvas;
import export_service.ExportFileContainer;
import import_service.ImportFileContainer;
import migration_service.MigrationClient;

import static canvas.Canvas.getCanvas;
import static export_service.ExportFileContainer.getExportFileContainer;
import static import_service.ImportFileContainer.getImportFileContainer;
import static migration_service.MigrationClient.getMigrationClient;
import static utility.Logger.logger;

public class MigrationServiceBuilder implements Builder{
    private static MigrationServiceBuilder migrationServiceBuilderImplementation;
    private ImportFileContainer importFileContainer;
    private ExportFileContainer exportFileContainer;
    private Canvas canvas;
    private MigrationClient migrationClient;

    private MigrationServiceBuilder() {
        logger(4, "MigrationServiceBuilder is being initialized");
    }
    public static MigrationServiceBuilder getMigrationServiceBuilder() {
        if (migrationServiceBuilderImplementation == null)
            migrationServiceBuilderImplementation = new MigrationServiceBuilder();
        return migrationServiceBuilderImplementation;
    }
    @Override
    public void build() {
        logger(4, "Resolving dependencies for MigrationService");
        this.importFileContainer = getImportFileContainer();
        this.exportFileContainer = getExportFileContainer();
        this.canvas = getCanvas();
        this.migrationClient = getMigrationClient(importFileContainer, exportFileContainer, canvas);
    }
}
