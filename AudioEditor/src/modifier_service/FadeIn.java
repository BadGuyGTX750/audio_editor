package modifier_service;

import static utility.Logger.logger;

public class FadeIn implements Modifier{
    @Override
    public byte[] executeModifier(String command, byte[] file) {
        logger(4, "Execute FadeIn Modifier");
        int length = file.length;
        for (int i = 0; i < length-2; i+=2) {
            // compose the sample into a manipulable number
            short little = file[i];
            short big = file[i+1];
            little = (short)(little & 0xff);
            big = (short)((big & 0xff) << 8);
            short number = (short)(big | little);
            // apply fadein filter
            number = (short) (number * (i/(float)length));
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