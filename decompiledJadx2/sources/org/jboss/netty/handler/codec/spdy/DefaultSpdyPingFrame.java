package org.jboss.netty.handler.codec.spdy;

import org.jboss.netty.util.internal.StringUtil;

/* loaded from: classes7.dex */
public class DefaultSpdyPingFrame implements SpdyPingFrame {

    /* renamed from: id */
    private int f10040id;

    public DefaultSpdyPingFrame(int i) {
        setId(i);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyPingFrame
    public int getID() {
        return getId();
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyPingFrame
    public int getId() {
        return this.f10040id;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyPingFrame
    public void setID(int i) {
        setId(i);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyPingFrame
    public void setId(int i) {
        this.f10040id = i;
    }

    public String toString() {
        return getClass().getSimpleName() + StringUtil.NEWLINE + "--> ID = " + this.f10040id;
    }
}
