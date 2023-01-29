package command_service;

public class ResponsesSignals {
    private String response;
    private boolean cmdHistSignal;
    private boolean terminationSignal;
    private boolean importSignal;
    private boolean migrate2CanvasSignal;
    private boolean exportSignal;
    private boolean migrateFromCanvasSignal;
    private boolean modifierSignal;
    private boolean undoSignal;

    public ResponsesSignals(String response, boolean cmdHistSignal, boolean terminationSignal, boolean importSignal, boolean migrate2CanvasSignal, boolean exportSignal, boolean migrateFromCanvasSignal, boolean modifierSignal, boolean undoSignal) {
        this.response = response;
        this.cmdHistSignal = cmdHistSignal;
        this.terminationSignal = terminationSignal;
        this.importSignal = importSignal;
        this.migrate2CanvasSignal = migrate2CanvasSignal;
        this.exportSignal = exportSignal;
        this.migrateFromCanvasSignal = migrateFromCanvasSignal;
        this.modifierSignal = modifierSignal;
        this.undoSignal = undoSignal;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean getCmdHistSignal() {
        return cmdHistSignal;
    }

    public void setCmdHistSignal(boolean cmdHistSignal) {
        this.cmdHistSignal = cmdHistSignal;
    }

    public boolean getTerminationSignal() {
        return terminationSignal;
    }

    public void setTerminationSignal(boolean terminationSignal) {
        this.terminationSignal = terminationSignal;
    }

    public boolean getImportSignal() {
        return importSignal;
    }

    public void setImportSignal(boolean importSignal) {
        this.importSignal = importSignal;
    }

    public boolean getMigrate2CanvasSignal() {
        return migrate2CanvasSignal;
    }

    public void setMigrate2CanvasSignal(boolean migrate2CanvasSignal) {
        this.migrate2CanvasSignal = migrate2CanvasSignal;
    }

    public boolean getExportSignal() {
        return exportSignal;
    }

    public void setExportSignal(boolean exportSignal) {
        this.exportSignal = exportSignal;
    }

    public boolean getMigrateFromCanvasSignal() {
        return migrateFromCanvasSignal;
    }

    public void setMigrateFromCanvasSignal(boolean migrateFromCanvasSignal) {
        this.migrateFromCanvasSignal = migrateFromCanvasSignal;
    }

    public boolean getModifierSignal() {
        return modifierSignal;
    }

    public void setModifierSignal(boolean modifierSignal) {
        this.modifierSignal = modifierSignal;
    }

    public boolean getUndoSignal() {
        return undoSignal;
    }

    public void setUndoSignal(boolean undoSignal) {
        this.undoSignal = undoSignal;
    }
}
