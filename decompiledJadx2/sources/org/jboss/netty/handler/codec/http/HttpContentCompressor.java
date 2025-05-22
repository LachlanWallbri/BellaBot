package org.jboss.netty.handler.codec.http;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.handler.codec.compression.JdkZlibEncoder;
import org.jboss.netty.handler.codec.compression.ZlibEncoder;
import org.jboss.netty.handler.codec.compression.ZlibWrapper;
import org.jboss.netty.handler.codec.embedder.EncoderEmbedder;
import org.jboss.netty.util.internal.DetectionUtil;

/* loaded from: classes7.dex */
public class HttpContentCompressor extends HttpContentEncoder {
    private final int compressionLevel;
    private final int memLevel;
    private final int windowBits;

    public HttpContentCompressor() {
        this(6);
    }

    public HttpContentCompressor(int i) {
        this(i, 15, 8);
    }

    public HttpContentCompressor(int i, int i2, int i3) {
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i + " (expected: 0-9)");
        }
        if (i2 < 9 || i2 > 15) {
            throw new IllegalArgumentException("windowBits: " + i2 + " (expected: 9-15)");
        }
        if (i3 < 1 || i3 > 9) {
            throw new IllegalArgumentException("memLevel: " + i3 + " (expected: 1-9)");
        }
        this.compressionLevel = i;
        this.windowBits = i2;
        this.memLevel = i3;
    }

    @Override // org.jboss.netty.handler.codec.http.HttpContentEncoder
    protected EncoderEmbedder<ChannelBuffer> newContentEncoder(HttpMessage httpMessage, String str) throws Exception {
        ZlibWrapper determineWrapper;
        String header = httpMessage.getHeader("Content-Encoding");
        if ((header != null && !"identity".equalsIgnoreCase(header)) || (determineWrapper = determineWrapper(str)) == null) {
            return null;
        }
        if (DetectionUtil.javaVersion() >= 7) {
            return new EncoderEmbedder<>(new JdkZlibEncoder(determineWrapper, this.compressionLevel));
        }
        return new EncoderEmbedder<>(new ZlibEncoder(determineWrapper, this.compressionLevel, this.windowBits, this.memLevel));
    }

    @Override // org.jboss.netty.handler.codec.http.HttpContentEncoder
    protected String getTargetContentEncoding(String str) throws Exception {
        ZlibWrapper determineWrapper = determineWrapper(str);
        if (determineWrapper == null) {
            return null;
        }
        int i = C87091.$SwitchMap$org$jboss$netty$handler$codec$compression$ZlibWrapper[determineWrapper.ordinal()];
        if (i == 1) {
            return "gzip";
        }
        if (i == 2) {
            return "deflate";
        }
        throw new Error();
    }

    /* renamed from: org.jboss.netty.handler.codec.http.HttpContentCompressor$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C87091 {
        static final /* synthetic */ int[] $SwitchMap$org$jboss$netty$handler$codec$compression$ZlibWrapper = new int[ZlibWrapper.values().length];

        static {
            try {
                $SwitchMap$org$jboss$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.GZIP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jboss$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.ZLIB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static ZlibWrapper determineWrapper(String str) {
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
                    f4 = Float.valueOf(str2.substring(indexOf + 1)).floatValue();
                } catch (NumberFormatException unused) {
                }
            } else {
                f4 = 1.0f;
            }
            if (str2.indexOf("*") >= 0) {
                f3 = f4;
            } else if (str2.indexOf("gzip") >= 0 && f4 > f) {
                f = f4;
            } else if (str2.indexOf("deflate") >= 0 && f4 > f2) {
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
