package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import com.jcraft.jzlib.Deflater;
import com.jcraft.jzlib.Inflater;
import com.jcraft.jzlib.JZlib;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class ZlibUtil {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void fail(Inflater inflater, String str, int i) {
        throw inflaterException(inflater, str, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void fail(Deflater deflater, String str, int i) {
        throw deflaterException(deflater, str, i);
    }

    static DecompressionException inflaterException(Inflater inflater, String str, int i) {
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" (");
        sb.append(i);
        sb.append(')');
        if (inflater.msg != null) {
            str2 = ": " + inflater.msg;
        } else {
            str2 = "";
        }
        sb.append(str2);
        return new DecompressionException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CompressionException deflaterException(Deflater deflater, String str, int i) {
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" (");
        sb.append(i);
        sb.append(')');
        if (deflater.msg != null) {
            str2 = ": " + deflater.msg;
        } else {
            str2 = "";
        }
        sb.append(str2);
        return new CompressionException(sb.toString());
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.compression.ZlibUtil$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C64681 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper = new int[ZlibWrapper.values().length];

        static {
            try {
                $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.ZLIB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.GZIP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.ZLIB_OR_NONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JZlib.WrapperType convertWrapperType(ZlibWrapper zlibWrapper) {
        int i = C64681.$SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[zlibWrapper.ordinal()];
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
            return JZlib.W_ANY;
        }
        throw new Error();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int wrapperOverhead(ZlibWrapper zlibWrapper) {
        int i = C64681.$SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[zlibWrapper.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 2;
        }
        if (i == 3) {
            return 10;
        }
        if (i == 4) {
            return 2;
        }
        throw new Error();
    }

    private ZlibUtil() {
    }
}
