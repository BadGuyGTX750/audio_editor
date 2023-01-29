package canvas;

import builder_service.Client;
import command_service.ResponsesSignals;

import static utility.Logger.logger;

public class CanvasHistoryClient implements Client {
    private static CanvasHistoryClient canvasHistoryClientImplementation;
    private CanvasHistory canvasHistory;
    private Canvas canvas;

    private CanvasHistoryClient(CanvasHistory canvasHistory, Canvas canvas) {
        this.canvasHistory = canvasHistory;
        this.canvas = canvas;
    }
    public static CanvasHistoryClient getCanvasHistoryClient() {
        return canvasHistoryClientImplementation;
    }
    public static CanvasHistoryClient getCanvasHistoryClient(CanvasHistory canvasHistory, Canvas canvas) {
        if (canvasHistoryClientImplementation == null)
            canvasHistoryClientImplementation = new CanvasHistoryClient(canvasHistory, canvas);
        return canvasHistoryClientImplementation;
    }

    @Override
    public void runClient(ResponsesSignals responsesSignals) {
        if (!responsesSignals.getUndoSignal()) {
            logger(4, "skipping runClient() of HistoryService");
            return;
        }
        logger(4, "runClient() of HistoryService");
        canvas.restoreState(canvasHistory.popFromList());
    }
}
