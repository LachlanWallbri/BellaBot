package io.netty.handler.codec.spdy;

import io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class DefaultSpdyPingFrame implements SpdyPingFrame {

    /* renamed from: id */
    private int f8540id;

    public DefaultSpdyPingFrame(int i) {
        setId(i);
    }

    @Override // io.netty.handler.codec.spdy.SpdyPingFrame
    /* renamed from: id */
    public int mo3942id() {
        return this.f8540id;
    }

    @Override // io.netty.handler.codec.spdy.SpdyPingFrame
    public SpdyPingFrame setId(int i) {
        this.f8540id = i;
        return this;
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + StringUtil.NEWLINE + "--> ID = " + mo3942id();
    }
}
