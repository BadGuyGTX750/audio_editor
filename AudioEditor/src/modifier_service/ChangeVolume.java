package modifier_service;

import static utility.Logger.logger;

public class ChangeVolume implements Modifier {
    @Override
    public byte[] executeModifier(String command, byte[] file) {
        logger(4, "Execute ChangeVolume Modifier");
        float amplification = Float.parseFloat(command.split(" ")[3]);
        int length = file.length;
        for (int i = 0; i < length - 2; i+=2) {
            // compose the sample into a manipulable number
            short little = file[i];
            short big = file[i+1];
            little = (short)(little & 0xff);
            big = (short)((big & 0xff) << 8);
            short number = (short)(big | little);
            // apply chgvol filter
            number = (short) (number * amplification);
            // reconvert back to byte
            file[i] = (byte)(number);
            file[i+1] = (byte)(number >> 8);
        }
        return file;
    }
}
// import C:\Proiecte\Programming_PROJECTS\audio_editor\sine.wav
// import -mg C:\Proiecte\Programming_PROJECTS\audio_editor\sine.wav

// export C:\Proiecte\Programming_PROJECTS\audio_editor\sin.wav
// export -mg C:\Proiecte\Programming_PROJECTS\audio_editor\sin.wav