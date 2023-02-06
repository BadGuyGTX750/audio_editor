package modifier_service;

import builder_service.Client;
import canvas.Canvas;
import canvas.CanvasHistory;
import command_service.ResponsesSignals;

import java.util.HashMap;

import static utility.Logger.logger;

public class ModifierClient implements Client {
    private static ModifierClient modifierClientImplementiation;
    private Canvas canvas;
    private ModifierContainer modifierContainer;
    private CanvasHistory canvasHistory;

    private ModifierClient(Canvas canvas, ModifierContainer modifierContainer, CanvasHistory canvasHistory) {
        logger(4, "ModifierClient is being initialized");
        this.canvas = canvas;
        this.modifierContainer = modifierContainer;
        this.canvasHistory = canvasHistory;
    }
    public static ModifierClient getModifierClient() {
        return modifierClientImplementiation;
    }
    public static ModifierClient getModifierClient(Canvas canvas, ModifierContainer modifierContainer, CanvasHistory canvasHistory) {
        if (modifierClientImplementiation == null)
            modifierClientImplementiation = new ModifierClient(canvas, modifierContainer, canvasHistory);
        return modifierClientImplementiation;
    }

    @Override
    public void runClient(ResponsesSignals responsesSignals) {
        if (!responsesSignals.getModifierSignal()) {
            //logger(4, "skipping runClient() of ModifierService");
            return;
        }
        logger(4, "runClient() of ModifierService");

        // save the state of the current canvas for UNDO SERVICE
        canvasHistory.pushToList(canvas.createSnapshot());

        // get the apropiate modifier object from the first argument of the command
        HashMap<String, Modifier> modifierHashMap = modifierContainer.getModifierHashMap();
        String command = responsesSignals.getResponse();
        String modifier = command.split(" ")[0];

        // get the extraction points (number of the sample from-to)
        // from the next 2 arguments of the command
        // and get the clip
        try {
            int s1 = Integer.parseInt(command.split(" ")[1]);
            int s2 = Integer.parseInt(command.split(" ")[2]);
            byte[] file = canvas.clipFromTo(s1, s2);

            // execute the modifier and stich back the resulting insertion
            byte[] insertion = modifierHashMap.get(modifier).executeModifier(command, file);
            canvas.stitchBack(insertion);
        } catch(Exception e) {
            logger(2, e.getMessage());
        }

    }
}
