package io.netty.handler.codec.rtsp;

import io.netty.handler.codec.http.HttpVersion;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class RtspVersions {
    public static final HttpVersion RTSP_1_0 = new HttpVersion("RTSP", 1, 0, true);

    public static HttpVersion valueOf(String str) {
        if (str == null) {
            throw new NullPointerException("text");
        }
        String upperCase = str.trim().toUpperCase();
        if ("RTSP/1.0".equals(upperCase)) {
            return RTSP_1_0;
        }
        return new HttpVersion(upperCase, true);
    }

    private RtspVersions() {
    }
}
