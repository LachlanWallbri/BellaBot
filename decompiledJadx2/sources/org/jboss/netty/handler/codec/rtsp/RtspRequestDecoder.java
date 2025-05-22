package org.jboss.netty.handler.codec.rtsp;

import org.jboss.netty.handler.codec.http.DefaultHttpRequest;
import org.jboss.netty.handler.codec.http.HttpMessage;

/* loaded from: classes7.dex */
public class RtspRequestDecoder extends RtspMessageDecoder {
    @Override // org.jboss.netty.handler.codec.http.HttpMessageDecoder
    protected boolean isDecodingRequest() {
        return true;
    }

    public RtspRequestDecoder() {
    }

    public RtspRequestDecoder(int i, int i2, int i3) {
        super(i, i2, i3);
    }

    @Override // org.jboss.netty.handler.codec.http.HttpMessageDecoder
    protected HttpMessage createMessage(String[] strArr) throws Exception {
        return new DefaultHttpRequest(RtspVersions.valueOf(strArr[2]), RtspMethods.valueOf(strArr[0]), strArr[1]);
    }
}
