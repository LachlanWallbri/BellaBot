package org.eclipse.paho.client.mqttv3.internal.wire;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class MultiByteInteger {
    private int length;
    private long value;

    public MultiByteInteger(long j) {
        this(j, -1);
    }

    public MultiByteInteger(long j, int i) {
        this.value = j;
        this.length = i;
    }

    public int getEncodedLength() {
        return this.length;
    }

    public long getValue() {
        return this.value;
    }
}
