package com.slamtec.slamware.log;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class LogData {
    private String logSource = "";
    private LogLevel logLevel = LogLevel.Debug;
    private boolean isJsonLog = false;
    private String stringOfLog = "";

    public String getLogSource() {
        return this.logSource;
    }

    public void setLogSource(String str) {
        this.logSource = str;
    }

    public LogLevel getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public boolean getIsJsonLog() {
        return this.isJsonLog;
    }

    public String getStringOfLog() {
        return this.stringOfLog;
    }

    public void setStringOfLog(boolean z, String str) {
        this.isJsonLog = z;
        this.stringOfLog = str;
    }
}
