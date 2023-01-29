package canvas;

import java.util.ArrayList;
import java.util.List;

public class CanvasHistory {
    private static CanvasHistory canvasHistoryImplementation;
    private List<CanvasState> canvasStates = new ArrayList<>();
    private int undoMax = 10;
    public static CanvasHistory getCanvasHistory() {
        if (canvasHistoryImplementation == null)
            canvasHistoryImplementation = new CanvasHistory();
        return canvasHistoryImplementation;
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
