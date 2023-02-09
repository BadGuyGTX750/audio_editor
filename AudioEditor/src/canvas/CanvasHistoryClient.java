package canvas;

import builder_service.Client;
import command_service.ResponsesSignals;
import dependency_injection.Inject;

import static utility.Logger.logger;

public class CanvasHistoryClient implements Client {
    private CanvasHistory canvasHistory;
    private Canvas canvas;
    @Inject
    public CanvasHistoryClient(CanvasHistory canvasHistory, Canvas canvas) {
        logger(4, "CanvasHistoryClient is being initialized");
        this.canvasHistory = canvasHistory;
        this.canvas = canvas;
    }
    @Override
    public void runClient(ResponsesSignals responsesSignals) {
        if (!responsesSignals.getUndoSignal()) {
            //logger(4, "skipping runClient() of HistoryService");
            return;
        }
        logger(4, "runClient() of HistoryService");
        try {
            canvas.restoreState(canvasHistory.popFromList());
        }
        catch(Exception e) {
            logger(2, e.getMessage());
        }
    }
}
