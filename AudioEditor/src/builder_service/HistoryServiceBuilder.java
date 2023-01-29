package builder_service;

import canvas.Canvas;
import canvas.CanvasHistory;
import canvas.CanvasHistoryClient;

import static canvas.Canvas.getCanvas;
import static canvas.CanvasHistory.getCanvasHistory;
import static canvas.CanvasHistoryClient.getCanvasHistoryClient;

public class HistoryServiceBuilder implements Builder{
    private static HistoryServiceBuilder historyServiceBuilderImplementation;
    private CanvasHistory canvasHistory;
    private Canvas canvas;
    private CanvasHistoryClient canvasHistoryClient;

    private HistoryServiceBuilder() {

    }
    public static HistoryServiceBuilder getHistoryServiceBuilder() {
        if (historyServiceBuilderImplementation == null)
            historyServiceBuilderImplementation = new HistoryServiceBuilder();
        return historyServiceBuilderImplementation;
    }
    @Override
    public void build() {
        this.canvasHistory = getCanvasHistory();
        this.canvas = getCanvas();
        this.canvasHistoryClient = getCanvasHistoryClient(this.canvasHistory, this.canvas);
    }
}
