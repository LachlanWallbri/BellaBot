package io.netty.channel.socket;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class ChannelOutputShutdownException extends IOException {
    private static final long serialVersionUID = 6712549938359321378L;

    public ChannelOutputShutdownException(String str) {
        super(str);
    }

    public ChannelOutputShutdownException(String str, Throwable th) {
        super(str, th);
    }
}
