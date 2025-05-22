package io.netty.channel;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class ChannelMetadata {
    private final int defaultMaxMessagesPerRead;
    private final boolean hasDisconnect;

    public ChannelMetadata(boolean z) {
        this(z, 1);
    }

    public ChannelMetadata(boolean z, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("defaultMaxMessagesPerRead: " + i + " (expected > 0)");
        }
        this.hasDisconnect = z;
        this.defaultMaxMessagesPerRead = i;
    }

    public boolean hasDisconnect() {
        return this.hasDisconnect;
    }

    public int defaultMaxMessagesPerRead() {
        return this.defaultMaxMessagesPerRead;
    }
}
