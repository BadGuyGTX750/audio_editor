package modifier_service;

import java.util.HashMap;

import static utility.Logger.logger;

public class ModifierContainer {
    private HashMap<String, Modifier> modifierHashMap = new HashMap<>();
    public ModifierContainer() {
        logger(4, "ModifierContainer is being initialized");
        modifierHashMap.put("chgvol", new ChangeVolume());
    }

    public HashMap<String, Modifier> getModifierHashMap() {
        return modifierHashMap;
    }
}
