package com.google.api.client.util;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface NanoClock {
    public static final NanoClock SYSTEM = new NanoClock() { // from class: com.google.api.client.util.NanoClock.1
        @Override // com.google.api.client.util.NanoClock
        public long nanoTime() {
            return System.nanoTime();
        }
    };

    long nanoTime();
}
