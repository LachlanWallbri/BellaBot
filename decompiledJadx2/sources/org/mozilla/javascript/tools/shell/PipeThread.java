package org.mozilla.javascript.tools.shell;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.mozilla.javascript.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* compiled from: Global.java */
/* loaded from: classes2.dex */
public class PipeThread extends Thread {
    private InputStream from;
    private boolean fromProcess;

    /* renamed from: to */
    private OutputStream f10275to;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PipeThread(boolean z, InputStream inputStream, OutputStream outputStream) {
        setDaemon(true);
        this.fromProcess = z;
        this.from = inputStream;
        this.f10275to = outputStream;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            Global.pipe(this.fromProcess, this.from, this.f10275to);
        } catch (IOException e) {
            throw Context.throwAsScriptRuntimeEx(e);
        }
    }
}
