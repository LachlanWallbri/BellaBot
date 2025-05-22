package io.grpc.netty.shaded.io.netty.handler.codec.rtsp;

import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMessage;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseStatus;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class RtspDecoder extends HttpObjectDecoder {
    public static final int DEFAULT_MAX_CONTENT_LENGTH = 8192;
    private static final HttpResponseStatus UNKNOWN_STATUS = new HttpResponseStatus(999, "Unknown");
    private static final Pattern versionPattern = Pattern.compile("RTSP/\\d\\.\\d");
    private boolean isDecodingRequest;

    public RtspDecoder() {
        this(4096, 8192, 8192);
    }

    public RtspDecoder(int i, int i2, int i3) {
        super(i, i2, i3 * 2, false);
    }

    public RtspDecoder(int i, int i2, int i3, boolean z) {
        super(i, i2, i3 * 2, false, z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder
    protected HttpMessage createMessage(String[] strArr) throws Exception {
        if (versionPattern.matcher(strArr[0]).matches()) {
            this.isDecodingRequest = false;
            return new DefaultHttpResponse(RtspVersions.valueOf(strArr[0]), new HttpResponseStatus(Integer.parseInt(strArr[1]), strArr[2]), this.validateHeaders);
        }
        this.isDecodingRequest = true;
        return new DefaultHttpRequest(RtspVersions.valueOf(strArr[2]), RtspMethods.valueOf(strArr[0]), strArr[1], this.validateHeaders);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder
    protected boolean isContentAlwaysEmpty(HttpMessage httpMessage) {
        return super.isContentAlwaysEmpty(httpMessage) || !httpMessage.headers().contains(RtspHeaderNames.CONTENT_LENGTH);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder
    protected HttpMessage createInvalidMessage() {
        if (this.isDecodingRequest) {
            return new DefaultFullHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.OPTIONS, "/bad-request", this.validateHeaders);
        }
        return new DefaultFullHttpResponse(RtspVersions.RTSP_1_0, UNKNOWN_STATUS, this.validateHeaders);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder
    protected boolean isDecodingRequest() {
        return this.isDecodingRequest;
    }
}
