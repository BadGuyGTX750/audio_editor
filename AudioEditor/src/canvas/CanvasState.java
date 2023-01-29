package canvas;

public class CanvasState implements  Memento{
    private byte[] fHeader;
    private byte[] fData;

    public CanvasState(byte[] fHeader, byte[] fData) {
        this.fHeader = fHeader;
        this.fData = fData;
    }

    public byte[] getfHeader() {
        return fHeader;
    }

    public byte[] getfData() {
        return fData;
    }
}
