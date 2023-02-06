package import_service;

import java.util.ArrayList;
import java.util.List;

import static utility.Logger.logger;

public class ImportFileContainer {
    private byte[] fileContainer;
    private static ImportFileContainer importFileContainerImplementation;
    private ImportFileContainer() {
        logger(4, "ImportFileContainer is being initialized");
    }
    public static ImportFileContainer getImportFileContainer() {
        if (importFileContainerImplementation == null)
            importFileContainerImplementation = new ImportFileContainer();
        return importFileContainerImplementation;
    }
    public byte[] getFileContainer() {
        return fileContainer;
    }
    public void setFileContainer(byte[] fileContainer) {
        this.fileContainer = fileContainer;
    }
    public void clearFileContainer() {
        this.fileContainer = null;
    }
    public void showFileContainer() {
        System.out.println("File imported represented byte by byte:");
        int charsOnALine = 44;
        for (byte b : fileContainer) {
            String b_hex = Integer.toHexString(b & 0xFF);
            if (b_hex.length() == 1)
                b_hex = "0" + b_hex;
            if (charsOnALine > 1)
                System.out.print(b + " ");
            else {
                System.out.print(b + " ");
                System.out.println();
                charsOnALine = 44;
                continue;
            }
            charsOnALine--;
        }
        System.out.println();
    }
}
