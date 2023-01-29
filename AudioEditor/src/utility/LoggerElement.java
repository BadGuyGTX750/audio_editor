package utility;

public class LoggerElement {
    private String timeStamp;
    private String logLevel;
    private String pid;
    private String callerMethod;
    private String callerClass;
    private String message;

    public LoggerElement(String timeStamp, String logLevel, String pid, String callerMethod, String callerClass, String message) {
        this.timeStamp = timeStamp;
        this.logLevel = logLevel;
        this.pid = pid;
        this.callerMethod = callerMethod;
        this.callerClass = callerClass;
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCallerMethod() {
        return callerMethod;
    }

    public void setCallerMethod(String callerMethod) {
        this.callerMethod = callerMethod;
    }

    public String getCallerClass() {
        return callerClass;
    }

    public void setCallerClass(String callerClass) {
        this.callerClass = callerClass;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
