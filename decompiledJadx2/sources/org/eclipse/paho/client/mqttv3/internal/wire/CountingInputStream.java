package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class CountingInputStream extends InputStream {
    private int counter = 0;

    /* renamed from: in */
    private InputStream f9997in;

    public CountingInputStream(InputStream inputStream) {
        this.f9997in = inputStream;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int read = this.f9997in.read();
        if (read != -1) {
            this.counter++;
        }
        return read;
    }

    public int getCounter() {
        return this.counter;
    }

    public void resetCounter() {
        this.counter = 0;
    }
}
