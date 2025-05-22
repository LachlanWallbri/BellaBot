package io.netty.handler.codec.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.compression.ZlibWrapper;
import io.netty.handler.codec.http.HttpContentEncoder;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class HttpContentCompressor extends HttpContentEncoder {
    private final int compressionLevel;
    private final int contentSizeThreshold;
    private ChannelHandlerContext ctx;
    private final int memLevel;
    private final int windowBits;

    public HttpContentCompressor() {
        this(6);
    }

    public HttpContentCompressor(int i) {
        this(i, 15, 8, 0);
    }

    public HttpContentCompressor(int i, int i2, int i3) {
        this(i, i2, i3, 0);
    }

    public HttpContentCompressor(int i, int i2, int i3, int i4) {
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i + " (expected: 0-9)");
        }
        if (i2 < 9 || i2 > 15) {
            throw new IllegalArgumentException("windowBits: " + i2 + " (expected: 9-15)");
        }
        if (i3 < 1 || i3 > 9) {
            throw new IllegalArgumentException("memLevel: " + i3 + " (expected: 1-9)");
        }
        if (i4 < 0) {
            throw new IllegalArgumentException("contentSizeThreshold: " + i4 + " (expected: non negative number)");
        }
        this.compressionLevel = i;
        this.windowBits = i2;
        this.memLevel = i3;
        this.contentSizeThreshold = i4;
    }

    @Override // io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
    }

    @Override // io.netty.handler.codec.http.HttpContentEncoder
    protected HttpContentEncoder.Result beginEncode(HttpResponse httpResponse, String str) throws Exception {
        ZlibWrapper determineWrapper;
        String str2;
        if ((this.contentSizeThreshold > 0 && (httpResponse instanceof HttpContent) && ((HttpContent) httpResponse).content().readableBytes() < this.contentSizeThreshold) || httpResponse.headers().get(HttpHeaderNames.CONTENT_ENCODING) != null || (determineWrapper = determineWrapper(str)) == null) {
            return null;
        }
        int i = C71411.$SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[determineWrapper.ordinal()];
        if (i == 1) {
            str2 = "gzip";
        } else {
            if (i != 2) {
                throw new Error();
            }
            str2 = "deflate";
        }
        return new HttpContentEncoder.Result(str2, new EmbeddedChannel(this.ctx.channel().mo3929id(), this.ctx.channel().metadata().hasDisconnect(), this.ctx.channel().config(), ZlibCodecFactory.newZlibEncoder(determineWrapper, this.compressionLevel, this.windowBits, this.memLevel)));
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes8.dex
     */
    /* renamed from: io.netty.handler.codec.http.HttpContentCompressor$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C71411 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper;

        static {
            int[] iArr = new int[ZlibWrapper.values().length];
            $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper = iArr;
            try {
                iArr[ZlibWrapper.GZIP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.ZLIB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    protected ZlibWrapper determineWrapper(String str) {
        String[] split = str.split(",");
        int length = split.length;
        int i = 0;
        float f = -1.0f;
        float f2 = -1.0f;
        float f3 = -1.0f;
        while (true) {
            float f4 = 0.0f;
            if (i >= length) {
                break;
            }
            String str2 = split[i];
            int indexOf = str2.indexOf(61);
            if (indexOf != -1) {
                try {
                    f4 = Float.parseFloat(str2.substring(indexOf + 1));
                } catch (NumberFormatException unused) {
                }
            } else {
                f4 = 1.0f;
            }
            if (str2.contains("*")) {
                f3 = f4;
            } else if (str2.contains("gzip") && f4 > f) {
                f = f4;
            } else if (str2.contains("deflate") && f4 > f2) {
                f2 = f4;
            }
            i++;
        }
        if (f > 0.0f || f2 > 0.0f) {
            if (f >= f2) {
                return ZlibWrapper.GZIP;
            }
            return ZlibWrapper.ZLIB;
        }
        if (f3 <= 0.0f) {
            return null;
        }
        if (f == -1.0f) {
            return ZlibWrapper.GZIP;
        }
        if (f2 == -1.0f) {
            return ZlibWrapper.ZLIB;
        }
        return null;
    }
}
