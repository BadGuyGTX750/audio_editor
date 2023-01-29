package utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public final class Logger {
    private static Logger importBuilderImplementation;
    private static List<String> logLevel= List.of(
            "OFF",
            "FATAL",
            "ERROR",
            "WARN",
            "INFO",
            "DEBUG",
            "TRACE"
    ); //immutable list
    // LEVEL    VALUE   DESCRIPTION

    // OFF      0       No logging.
    // FATAL    1       The application is unusable. Action needs to be taken immediately.
    // ERROR    2       An error occurred in the application.
    // WARN     3       Something unexpected—though not necessarily an error—happened and needs to be watched.
    // INFO     4       A normal, expected, relevant event happened.
    // DEBUG    5       Used for debugging purposes
    // TRACE    6       Used for debugging purposes—includes the most detailed information
    private static int desiredLogLevelLength = 0;
    private static int desiredCallerMethodLength = 0;
    private static int desiredCallerClassLength = 0;

    private static List<LoggerElement> loggerElements = new ArrayList<>();
    private Logger() {

    }
    public static Logger getAppUtility() {
        if (importBuilderImplementation == null)
            importBuilderImplementation = new Logger();
        return importBuilderImplementation;
    }
    private static void addLoggerElement(LoggerElement loggerElement) {
        loggerElements.add(loggerElement);
        desiredLogLevelLength = Math.max(desiredLogLevelLength, loggerElement.getLogLevel().length());
        desiredCallerMethodLength = Math.max(desiredCallerMethodLength, loggerElement.getCallerMethod().length());
        desiredCallerClassLength = Math.max(desiredCallerClassLength, loggerElement.getCallerClass().length());
    }
    public static void logger(int level, String message) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new java.util.Date());
        String pid = String.format("%-" + 5 + "s", ProcessHandle.current().pid());
        StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
        String callerMethod = ste.getMethodName();
        String callerClass = ste.getClassName();
        addLoggerElement(new LoggerElement(timeStamp, logLevel.get(level), pid, callerMethod, callerClass, message));
    }
    public static void printAllLogs() {
        for (LoggerElement loggerElement : loggerElements) {
            String timeStamp = loggerElement.getTimeStamp();
            String logLevel = String.format("%-" + desiredLogLevelLength + "s", loggerElement.getLogLevel());
            String pid = loggerElement.getPid();
            String callerMethod = String.format("%" + desiredCallerMethodLength + "s", loggerElement.getCallerMethod());
            String callerClass = String.format("%-" + desiredCallerClassLength + "s", loggerElement.getCallerClass());
            String message = loggerElement.getMessage();
            System.out.println(timeStamp + " " + logLevel + " " + pid
                    + " --- [" + callerMethod + "] " + callerClass + " : " + message);
        }
        loggerElements.clear();
        desiredLogLevelLength = 0;
        desiredCallerMethodLength = 0;
        desiredCallerClassLength = 0;
    }
}
