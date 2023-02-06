package modifier_service;

import static utility.Logger.logger;

public class Delete implements Modifier{

    @Override
    public byte[] executeModifier(String command, byte[] file) {
        logger(4, "Execute Delete Modifier");
        return new byte[0];
    }
}
