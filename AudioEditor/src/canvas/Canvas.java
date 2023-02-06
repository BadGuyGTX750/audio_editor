package canvas;

import java.util.ArrayList;
import java.util.List;

import static canvas.CanvasHistory.getCanvasHistory;
import static utility.Logger.logger;

public class Canvas {
    private static Canvas canvasImplementation;
    private byte[] fileHeader;
    private byte[] fileData;
    private byte[] startClip;
    private byte[] middleClip;
    private byte[] finalClip;
    private Canvas() {
        logger(4, "Canvas is being initialized");
    }
    public static Canvas getCanvas() {
        if (canvasImplementation == null)
            canvasImplementation = new Canvas();
        return canvasImplementation;
    }
    public void setFileHeaderAndFileData(byte[] file) {
        fileHeader = new byte[44];
        for (int i = 0; i < 44; i++) {
            fileHeader[i] = file[i];
        }
        int clipLength = file.length;
        fileData = new byte[clipLength-44];
        for (int i = 44; i < clipLength; i++) {
            fileData[i-44] = file[i];
        }
    }
    public byte[] getFileHeader() {
        return fileHeader;
    }
    public byte[] getFileData() {
        return fileData;
    }
    public CanvasState createSnapshot() {
        return new CanvasState(this.fileHeader, this.fileData);
    }
    public void restoreState(CanvasState canvasState) {
        this.fileHeader = canvasState.getfHeader();
        this.fileData = canvasState.getfData();
    }
    public void showFileData() {
        System.out.println("File imported to Canvas represented byte by byte:");
        int charsOnALine = 44;
        for (byte b : fileData) {
            String b_hex = Integer.toHexString(b & 0xFF);
            int b_uint = Byte.toUnsignedInt(b);
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
    public byte[] clipFromTo(int s1, int s2) {
        int clipLength = fileData.length;

        // check if the clip cutting conditions are met
        if (s1 < 0) {
            throw new IllegalArgumentException("Sample from which to clip cannot be lower than 0");
        }
        if (s2 > clipLength) {
            throw new IllegalArgumentException(String.format("Sample from which to end the clip cannot be higher than %d", clipLength));
        }
        if (s2 < s1) {
            throw new IllegalArgumentException("Second argument cannot be smaller than the first argument");
        }

        //allocate memory in the heap for the clips
        startClip = new byte[s1];
        middleClip = new byte[s2-s1];
        finalClip = new byte[clipLength-s2];
        for (int i = 0; i < s1; i++) {
            startClip[i] = fileData[i];
        }
        //logger(4, "startClip: " + startClip.length);
        for (int i = s1; i < s2; i++) {
            middleClip[i-s1] = fileData[i];
        }
        //logger(4, "middleClip: " + middleClip.length);
        for (int i = s2; i < clipLength; i++) {
            finalClip[i-s2] = fileData[i];
        }
        //logger(4, "finalClip: " + finalClip.length);
        return middleClip;
    }
    public void stitchBack(byte[] insertion) {
        int s1 = startClip.length;
        int s2 = s1 + insertion.length;
        int s3 = s2 + finalClip.length;
        fileData = new byte[startClip.length + insertion.length + finalClip.length];
        for (int i = 0; i < s1; i++) {
            fileData[i] = startClip[i];
        }
        for (int i = s1; i < s2; i++) {
            fileData[i] = insertion[i-s1];
        }
        for (int i = s2; i < s3; i++) {
            fileData[i] = finalClip[i - s2];
        }
    }
    private void clearCanvas() {
        fileHeader = null;
        fileData = null;
    }
}
