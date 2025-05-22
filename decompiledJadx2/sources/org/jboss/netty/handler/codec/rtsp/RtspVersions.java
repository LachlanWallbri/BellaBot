package org.jboss.netty.handler.codec.rtsp;

import org.jboss.netty.handler.codec.http.HttpVersion;

/* loaded from: classes7.dex */
public final class RtspVersions {
    public static final HttpVersion RTSP_1_0 = new HttpVersion("RTSP", 1, 0, true);

    public static HttpVersion valueOf(String str) {
        if (str == null) {
            throw new NullPointerException("text");
        }
        String upperCase = str.trim().toUpperCase();
        if (upperCase.equals("RTSP/1.0")) {
            return RTSP_1_0;
        }
        return new HttpVersion(upperCase, true);
    }

    private RtspVersions() {
    }
}
