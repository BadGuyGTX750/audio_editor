package builder_service;

import canvas.Canvas;
import canvas.CanvasHistory;
import import_service.ImportClient;
import modifier_service.ModifierClient;
import modifier_service.ModifierContainer;

import static canvas.Canvas.getCanvas;
import static canvas.CanvasHistory.getCanvasHistory;
import static modifier_service.ModifierClient.getModifierClient;
import static utility.Logger.logger;

public class ModifierServiceBuilder implements Builder{
    private static ModifierServiceBuilder modifierServiceBuilderImplementation;
    private ModifierClient modifierClient;
    private Canvas canvas;
    private CanvasHistory canvasHistory;
    private ModifierContainer modifierContainer;
    private ModifierServiceBuilder() {

    }
    public static ModifierServiceBuilder getModifierServiceBuilder() {
        if (modifierServiceBuilderImplementation == null)
            modifierServiceBuilderImplementation = new ModifierServiceBuilder();
        return modifierServiceBuilderImplementation;
    }

    @Override
    public void build() {
        logger(4, "Resolving dependencies for ModifierService");
        this.canvas = getCanvas();
        this.modifierContainer = new ModifierContainer();
        this.canvasHistory = getCanvasHistory();
        this.modifierClient = getModifierClient(canvas, modifierContainer, canvasHistory);
    }
}
