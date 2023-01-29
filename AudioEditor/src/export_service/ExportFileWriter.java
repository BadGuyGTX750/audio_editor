package export_service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static utility.Logger.logger;

public class ExportFileWriter {
    private static String secretSeparator = "  %%#% # %#%%  ";
    public ExportFileWriter() {
        logger(4, "ExportFileWriter is being initialized");
    }

    public void writeFile(byte[] wavFile, String separatedFilePath) throws IOException {
        String[] splitString = separatedFilePath.split(secretSeparator);
        String filePath = splitString[0] + splitString[1];
        File f = new File(filePath);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(wavFile);
        fos.close();
    }
}
