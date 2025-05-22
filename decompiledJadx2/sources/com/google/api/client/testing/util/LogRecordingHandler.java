package com.google.api.client.testing.util;

import com.google.api.client.util.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class LogRecordingHandler extends Handler {
    private final List<LogRecord> records = Lists.newArrayList();

    @Override // java.util.logging.Handler
    public void close() throws SecurityException {
    }

    @Override // java.util.logging.Handler
    public void flush() {
    }

    @Override // java.util.logging.Handler
    public void publish(LogRecord logRecord) {
        this.records.add(logRecord);
    }

    public List<String> messages() {
        ArrayList newArrayList = Lists.newArrayList();
        Iterator<LogRecord> it = this.records.iterator();
        while (it.hasNext()) {
            newArrayList.add(it.next().getMessage());
        }
        return newArrayList;
    }
}
