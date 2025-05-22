package org.slf4j.impl;

import android.util.Log;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;

/* loaded from: classes9.dex */
class AndroidLoggerAdapter extends MarkerIgnoringBase {
    private static final long serialVersionUID = -1227274521521287937L;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AndroidLoggerAdapter(String str) {
        this.name = str;
    }

    @Override // org.slf4j.Logger
    public boolean isTraceEnabled() {
        return isLoggable(2);
    }

    @Override // org.slf4j.Logger
    public void trace(String str) {
        log(2, str, null);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object obj) {
        formatAndLog(2, str, obj);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object obj, Object obj2) {
        formatAndLog(2, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object... objArr) {
        formatAndLog(2, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Throwable th) {
        log(2, str, th);
    }

    @Override // org.slf4j.Logger
    public boolean isDebugEnabled() {
        return isLoggable(3);
    }

    @Override // org.slf4j.Logger
    public void debug(String str) {
        log(3, str, null);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj) {
        formatAndLog(3, str, obj);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj, Object obj2) {
        formatAndLog(3, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object... objArr) {
        formatAndLog(3, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Throwable th) {
        log(2, str, th);
    }

    @Override // org.slf4j.Logger
    public boolean isInfoEnabled() {
        return isLoggable(4);
    }

    @Override // org.slf4j.Logger
    public void info(String str) {
        log(4, str, null);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object obj) {
        formatAndLog(4, str, obj);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object obj, Object obj2) {
        formatAndLog(4, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object... objArr) {
        formatAndLog(4, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Throwable th) {
        log(4, str, th);
    }

    @Override // org.slf4j.Logger
    public boolean isWarnEnabled() {
        return isLoggable(5);
    }

    @Override // org.slf4j.Logger
    public void warn(String str) {
        log(5, str, null);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj) {
        formatAndLog(5, str, obj);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj, Object obj2) {
        formatAndLog(5, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object... objArr) {
        formatAndLog(5, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Throwable th) {
        log(5, str, th);
    }

    @Override // org.slf4j.Logger
    public boolean isErrorEnabled() {
        return isLoggable(6);
    }

    @Override // org.slf4j.Logger
    public void error(String str) {
        log(6, str, null);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object obj) {
        formatAndLog(6, str, obj);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object obj, Object obj2) {
        formatAndLog(6, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object... objArr) {
        formatAndLog(6, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Throwable th) {
        log(6, str, th);
    }

    private void formatAndLog(int i, String str, Object... objArr) {
        if (isLoggable(i)) {
            FormattingTuple arrayFormat = MessageFormatter.arrayFormat(str, objArr);
            logInternal(i, arrayFormat.getMessage(), arrayFormat.getThrowable());
        }
    }

    private void log(int i, String str, Throwable th) {
        if (isLoggable(i)) {
            logInternal(i, str, th);
        }
    }

    private boolean isLoggable(int i) {
        return Log.isLoggable(this.name, i);
    }

    private void logInternal(int i, String str, Throwable th) {
        if (th != null) {
            str = str + '\n' + Log.getStackTraceString(th);
        }
        Log.println(i, this.name, str);
    }
}
