package command_service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utility.Logger.logger;

public class CommandInterpreter {
    private static String secretSeparator = "  %%#% # %#%%  ";
    private String filePath;
    private String fileName;
    private boolean cmdHistSignal;
    private boolean terminationSignal;
    private boolean importSignal;
    private boolean migrate2CanvasSignal;
    private boolean exportSignal;
    private boolean migrateFromCanvasSignal;
    private boolean modifierSignal;
    private boolean undoSignal;

    private static String CMD_HIST_REGEX = "(?i)(history|hist)";
    private static String QUIT_REGEX = "(?i)(quit|exit|q)";
    private static String IMPORT_REGEX = "((?i)(import ))((?i)([A-Z]:(((\\\\)([a-zA-Z0-9_ ]+))+)\\.wav))";
    private static String MIGRATE2CANVAS_REGEX = "(migrate to canvas)";
    private static String EXPORT_REGEX = "((?i)(export ))((?i)([A-Z]:(((\\\\)([a-zA-Z0-9_ ]+))+)\\.wav))|(([a-zA-Z0-9_ ]+)\\.wav)";
    private static String FILE_PATH_REGEX = "((?i)([A-Z]:(((\\\\)([a-zA-Z0-9_ ]+))+)\\.wav))";
    private static String EXPORT_FILE_PATH_REGEX = "((?i)([A-Z]:((((\\\\)([a-zA-Z0-9_ ]+))+)(\\\\)))|([A-Z]:\\\\))";
    private static String EXPORT_FILE_NAME_REGEX = "(([a-zA-Z0-9_ ]+)\\.wav)";
    private static String MIGRATE4CANVAS_REGEX = "(migrate from canvas)";
    private static String CHANGE_VOLUME_REGEX = "((chgvol )([0-9]+ [0-9]+ [0-9]+))";
    private static String UNDO_REGEX = "undo";
    public CommandInterpreter() {
        logger(4, "ComandInterpreter is being initialized");
    }
    public ResponsesSignals interpretCommand(String userInput) {
        // TODO: check valid command
        if (!CheckValidCommand(userInput))
            return new ResponsesSignals(
                    String.format("Command \'%s\' is not valid.", userInput),
                    cmdHistSignal,
                    terminationSignal,
                    importSignal,
                    migrate2CanvasSignal,
                    exportSignal,
                    migrateFromCanvasSignal,
                    modifierSignal,
                    undoSignal
            );
        if (cmdHistSignal)
            return new ResponsesSignals(
                    "Command history",
                    cmdHistSignal,
                    terminationSignal,
                    importSignal,
                    migrate2CanvasSignal,
                    exportSignal,
                    migrateFromCanvasSignal,
                    modifierSignal,
                    undoSignal
            );
        // check if I want to quit
        if (terminationSignal) {
            return new ResponsesSignals(
                    "Quitting main loop",
                    cmdHistSignal,
                    terminationSignal,
                    importSignal,
                    migrate2CanvasSignal,
                    exportSignal,
                    migrateFromCanvasSignal,
                    modifierSignal,
                    undoSignal
            );
        }
        if (importSignal) {
            // import C:\Proiecte\Programming_PROJECTS\audio_editor\sine.wav
            return new ResponsesSignals(
                    this.filePath,
                    cmdHistSignal,
                    terminationSignal,
                    importSignal,
                    migrate2CanvasSignal,
                    exportSignal,
                    migrateFromCanvasSignal,
                    modifierSignal,
                    undoSignal
            );
        }
        if (migrate2CanvasSignal) {
            return new ResponsesSignals(
                    "Migrating importFile to canvas",
                    cmdHistSignal,
                    terminationSignal,
                    importSignal,
                    migrate2CanvasSignal,
                    exportSignal,
                    migrateFromCanvasSignal,
                    modifierSignal,
                    undoSignal
            );
        }
        if (exportSignal) {
            // export C:\Proiecte\Programming_PROJECTS\audio_editor\sin.wav
            return new ResponsesSignals(
                    this.filePath + secretSeparator + this.fileName,
                    cmdHistSignal,
                    terminationSignal,
                    importSignal,
                    migrate2CanvasSignal,
                    exportSignal,
                    migrateFromCanvasSignal,
                    modifierSignal,
                    undoSignal
            );
        }
        if (migrateFromCanvasSignal) {
            return new ResponsesSignals(
                    "Migrating Canvas to ExportFileContainer",
                    cmdHistSignal,
                    terminationSignal,
                    importSignal,
                    migrate2CanvasSignal,
                    exportSignal,
                    migrateFromCanvasSignal,
                    modifierSignal,
                    undoSignal
            );
        }
        if (modifierSignal) {
            return new ResponsesSignals(
                    userInput,
                    cmdHistSignal,
                    terminationSignal,
                    importSignal,
                    migrate2CanvasSignal,
                    exportSignal,
                    migrateFromCanvasSignal,
                    modifierSignal,
                    undoSignal
            );
        }
        if (undoSignal) {
            return new ResponsesSignals(
                    "Returning Canvas to previous state",
                    cmdHistSignal,
                    terminationSignal,
                    importSignal,
                    migrate2CanvasSignal,
                    exportSignal,
                    migrateFromCanvasSignal,
                    modifierSignal,
                    undoSignal
            );
        }
        return new ResponsesSignals(
                "No response",
                cmdHistSignal,
                terminationSignal,
                importSignal,
                migrate2CanvasSignal,
                exportSignal,
                migrateFromCanvasSignal,
                modifierSignal,
                undoSignal
        );
    }
    public boolean CheckValidCommand(String userInput) {
        filePath = "";
        fileName = "";
        cmdHistSignal = false;
        terminationSignal = false;
        importSignal = false;
        migrate2CanvasSignal = false;
        exportSignal = false;
        migrateFromCanvasSignal = false;
        modifierSignal = false;
        undoSignal = false;
        if (userInput.matches(CMD_HIST_REGEX)) {
            cmdHistSignal = true;
            return true;
        }
        if (userInput.matches(QUIT_REGEX)) {
            terminationSignal = true;
            return true;
        }
        if (userInput.matches(IMPORT_REGEX)) {

            Pattern p = Pattern.compile(FILE_PATH_REGEX);
            Matcher m = p.matcher(userInput);
            this.filePath = "";
            if (m.find()) {
                this.filePath = m.group(0);
            }
            if (Files.exists(Paths.get(this.filePath))) {
                importSignal = true;
                return true;
            }
        }
        if (userInput.matches(MIGRATE2CANVAS_REGEX)) {
            migrate2CanvasSignal = true;
            return true;
        }
        if (userInput.matches(EXPORT_REGEX)) {
            Pattern p = Pattern.compile(EXPORT_FILE_PATH_REGEX);
            Matcher m = p.matcher(userInput);
            if (m.find()) {
                this.filePath = m.group(0);
            }
            if (Files.exists(Paths.get(this.filePath))) {
                exportSignal = true;
                p = Pattern.compile(EXPORT_FILE_NAME_REGEX);
                m = p.matcher(userInput);
                if (m.find()) {
                    this.fileName = m.group(0);
                }
                return true;
            }
        }
        if (userInput.matches(MIGRATE4CANVAS_REGEX)) {
            migrateFromCanvasSignal = true;
            return true;
        }
        if (userInput.matches(CHANGE_VOLUME_REGEX)) {
            modifierSignal = true;
            return true;
        }
        if (userInput.matches(UNDO_REGEX)) {
            undoSignal = true;
            return true;
        }
        return false;
    }

}
