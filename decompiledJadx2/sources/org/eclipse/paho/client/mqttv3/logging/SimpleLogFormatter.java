package org.eclipse.paho.client.mqttv3.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class SimpleLogFormatter extends Formatter {

    /* renamed from: LS */
    private static final String f9999LS = System.getProperty("line.separator");

    @Override // java.util.logging.Formatter
    public String format(LogRecord logRecord) {
        String str;
        StringWriter stringWriter;
        PrintWriter printWriter;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(logRecord.getLevel().getName());
        stringBuffer.append("\t");
        stringBuffer.append(MessageFormat.format("{0, date, yy-MM-dd} {0, time, kk:mm:ss.SSSS} ", new Date(logRecord.getMillis())) + "\t");
        String sourceClassName = logRecord.getSourceClassName();
        if (sourceClassName != null) {
            int length = sourceClassName.length();
            if (length > 20) {
                str = logRecord.getSourceClassName().substring(length - 19);
            } else {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(sourceClassName);
                stringBuffer2.append(new char[]{' '}, 0, 1);
                str = stringBuffer2.toString();
            }
        } else {
            str = "";
        }
        stringBuffer.append(str);
        stringBuffer.append("\t");
        stringBuffer.append(" ");
        stringBuffer.append(left(logRecord.getSourceMethodName(), 23, ' '));
        stringBuffer.append("\t");
        stringBuffer.append(logRecord.getThreadID());
        stringBuffer.append("\t");
        stringBuffer.append(formatMessage(logRecord));
        stringBuffer.append(f9999LS);
        if (logRecord.getThrown() != null) {
            stringBuffer.append("Throwable occurred: ");
            Throwable thrown = logRecord.getThrown();
            PrintWriter printWriter2 = null;
            try {
                stringWriter = new StringWriter();
                printWriter = new PrintWriter(stringWriter);
            } catch (Throwable th) {
                th = th;
            }
            try {
                thrown.printStackTrace(printWriter);
                stringBuffer.append(stringWriter.toString());
                try {
                    printWriter.close();
                } catch (Exception unused) {
                }
            } catch (Throwable th2) {
                th = th2;
                printWriter2 = printWriter;
                if (printWriter2 != null) {
                    try {
                        printWriter2.close();
                    } catch (Exception unused2) {
                    }
                }
                throw th;
            }
        }
        return stringBuffer.toString();
    }

    public static String left(String str, int i, char c) {
        if (str.length() >= i) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        stringBuffer.append(str);
        int length = i - str.length();
        while (true) {
            length--;
            if (length >= 0) {
                stringBuffer.append(c);
            } else {
                return stringBuffer.toString();
            }
        }
    }
}
