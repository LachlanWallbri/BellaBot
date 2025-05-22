package org.jboss.netty.handler.codec.compression;

import org.jboss.netty.util.internal.jzlib.JZlib;
import org.jboss.netty.util.internal.jzlib.ZStream;

/* loaded from: classes7.dex */
final class ZlibUtil {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void fail(ZStream zStream, String str, int i) {
        throw exception(zStream, str, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CompressionException exception(ZStream zStream, String str, int i) {
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" (");
        sb.append(i);
        sb.append(")");
        if (zStream.msg != null) {
            str2 = ": " + zStream.msg;
        } else {
            str2 = "";
        }
        sb.append(str2);
        return new CompressionException(sb.toString());
    }

    /* renamed from: org.jboss.netty.handler.codec.compression.ZlibUtil$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C87061 {
        static final /* synthetic */ int[] $SwitchMap$org$jboss$netty$handler$codec$compression$ZlibWrapper = new int[ZlibWrapper.values().length];

        static {
            try {
                $SwitchMap$org$jboss$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jboss$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.ZLIB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jboss$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.GZIP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jboss$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.ZLIB_OR_NONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Enum<?> convertWrapperType(ZlibWrapper zlibWrapper) {
        int i = C87061.$SwitchMap$org$jboss$netty$handler$codec$compression$ZlibWrapper[zlibWrapper.ordinal()];
        if (i == 1) {
            return JZlib.W_NONE;
        }
        if (i == 2) {
            return JZlib.W_ZLIB;
        }
        if (i == 3) {
            return JZlib.W_GZIP;
        }
        if (i == 4) {
            return JZlib.W_ZLIB_OR_NONE;
        }
        throw new Error();
    }

    private ZlibUtil() {
    }
}
