package modifier_service;

import static utility.Logger.logger;

public class ChangeVolume implements Modifier {
    @Override
    public byte[] executeModifier(String command, byte[] file) {
        logger(4, "Execute ChangeVolume Modifier");
        int amplification = Integer.parseInt(command.split(" ")[3]);
        for (int i = 0; i < file.length-2; i+=2) {
            int little = Byte.toUnsignedInt(file[i]);
            int big = Byte.toUnsignedInt(file[i+1]);
            int number = (big * 256 + little) * amplification;
            if (number > 65535)
                number = 65535;
            big = number / 256;
            little = number - big * 256;
            file[i] = (byte) little;
            file[i+1] = (byte) big;
        }
        return file;
    }
}
