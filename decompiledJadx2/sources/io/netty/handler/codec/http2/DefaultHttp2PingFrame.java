package io.netty.handler.codec.http2;

import io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class DefaultHttp2PingFrame implements Http2PingFrame {
    private final boolean ack;
    private final long content;

    @Override // io.netty.handler.codec.http2.Http2Frame
    public String name() {
        return "PING";
    }

    public DefaultHttp2PingFrame(long j) {
        this(j, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultHttp2PingFrame(long j, boolean z) {
        this.content = j;
        this.ack = z;
    }

    @Override // io.netty.handler.codec.http2.Http2PingFrame
    public boolean ack() {
        return this.ack;
    }

    @Override // io.netty.handler.codec.http2.Http2PingFrame
    public long content() {
        return this.content;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Http2PingFrame)) {
            return false;
        }
        Http2PingFrame http2PingFrame = (Http2PingFrame) obj;
        return this.ack == http2PingFrame.ack() && this.content == http2PingFrame.content();
    }

    public int hashCode() {
        return (super.hashCode() * 31) + (this.ack ? 1 : 0);
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + "(content=" + this.content + ", ack=" + this.ack + ')';
    }
}
