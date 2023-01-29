package export_service;

import static utility.Logger.logger;

public class ExportFileContainer {
    private byte[] fileContainer;
    private static ExportFileContainer exportFileContainerImplementation;
    private ExportFileContainer() {
        logger(4, "ExportFileContainer is being initialized");
    }
    public static ExportFileContainer getExportFileContainer() {
        if (exportFileContainerImplementation == null)
            exportFileContainerImplementation = new ExportFileContainer();
        return exportFileContainerImplementation;
    }
    public void setFileContainer(byte[] file) {
        fileContainer = file;
    }
    public byte[] getFileContainer() {
        return fileContainer;
    }
    public void clearFileContainer() {
        this.fileContainer = null;
    }
    public void showFileContainer() {
        System.out.println("File exported represented byte by byte:");
        int charsOnALine = 44;
        for (byte b : fileContainer) {
            String b_hex = Integer.toHexString(b & 0xFF);
            if (b_hex.length() == 1)
                b_hex = "0" + b_hex;
            if (charsOnALine > 1)
                System.out.print(b_hex + " ");
            else {
                System.out.print(b_hex + " ");
                System.out.println();
                charsOnALine = 44;
                continue;
            }
            charsOnALine--;
        }
        System.out.println();
    }
}
