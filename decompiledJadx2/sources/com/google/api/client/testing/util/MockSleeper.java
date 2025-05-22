package com.google.api.client.testing.util;

import com.google.api.client.util.Sleeper;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class MockSleeper implements Sleeper {
    private int count;
    private long lastMillis;

    @Override // com.google.api.client.util.Sleeper
    public void sleep(long j) throws InterruptedException {
        this.count++;
        this.lastMillis = j;
    }

    public final int getCount() {
        return this.count;
    }

    public final long getLastMillis() {
        return this.lastMillis;
    }
}
