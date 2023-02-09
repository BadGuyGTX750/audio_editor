package import_service;

import builder_service.Client;
import command_service.ResponsesSignals;
import dependency_injection.Inject;

import static utility.Logger.logger;

public class ImportClient implements Client {
    private ImportFileContainer importFileContainer;
    private ImportFileReader importFileReader;

    @Inject
    public ImportClient(ImportFileContainer importFileContainer,
                         ImportFileReader importFileReader) {
        logger(4, "ImportClient is being initialized");
        this.importFileContainer = importFileContainer;
        this.importFileReader = importFileReader;
    }
    @Override
    public void runClient(ResponsesSignals responsesSignals) {
        if (!responsesSignals.getImportSignal()) {
            //logger(4, "skipping runClient() of ImportService");
            return;
        }
        logger(4, "runClient() of ImportService");
        String filePath = responsesSignals.getResponse();
        try {
            importFileContainer.setFileContainer(importFileReader.readFile(filePath));
            logger(4, "setting File Container by reading with importFileReader.readFile(filePath)");
            //importFileContainer.showFileContainer();
        }
        catch(Exception e) {
            logger(2, "importFileReader.readFile(filePath) could not read file");
        }
    }
}
