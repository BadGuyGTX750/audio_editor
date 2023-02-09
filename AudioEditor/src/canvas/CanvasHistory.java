package canvas;

import dependency_injection.Injectable;

import java.util.ArrayList;
import java.util.List;

import static utility.Logger.logger;

public class CanvasHistory {
    private List<CanvasState> canvasStates = new ArrayList<>();
    private int undoMax = 10;

    @Injectable
    public CanvasHistory() {
        logger(4, "CanvasHistory is being initialized");
    }

    public void pushToList(CanvasState canvasState) {
        if (canvasStates.size() > undoMax) {
            canvasStates.add(canvasState);
            canvasStates.remove(0);
            return;
        }
        canvasStates.add(canvasState);
    };
    public CanvasState popFromList() {
        int lastIndex = canvasStates.size() - 1;
        CanvasState state = canvasStates.get(lastIndex);
        canvasStates.remove(lastIndex);
        return state;
    }
}
