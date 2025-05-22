package com.google.api.client.util;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface Clock {
    public static final Clock SYSTEM = new Clock() { // from class: com.google.api.client.util.Clock.1
        @Override // com.google.api.client.util.Clock
        public long currentTimeMillis() {
            return System.currentTimeMillis();
        }
    };

    long currentTimeMillis();
}
