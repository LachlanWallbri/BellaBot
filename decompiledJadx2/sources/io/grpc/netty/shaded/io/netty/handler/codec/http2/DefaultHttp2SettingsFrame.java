package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultHttp2SettingsFrame implements Http2SettingsFrame {
    private final Http2Settings settings;

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2SettingsFrame, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Frame
    public String name() {
        return "SETTINGS";
    }

    public DefaultHttp2SettingsFrame(Http2Settings http2Settings) {
        this.settings = (Http2Settings) ObjectUtil.checkNotNull(http2Settings, "settings");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2SettingsFrame
    public Http2Settings settings() {
        return this.settings;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Http2SettingsFrame) {
            return this.settings.equals(((Http2SettingsFrame) obj).settings());
        }
        return false;
    }

    public int hashCode() {
        return this.settings.hashCode();
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + "(settings=" + this.settings + ')';
    }
}
