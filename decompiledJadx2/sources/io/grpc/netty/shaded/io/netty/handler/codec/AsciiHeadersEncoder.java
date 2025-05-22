package io.grpc.netty.shaded.io.netty.handler.codec;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class AsciiHeadersEncoder {
    private final ByteBuf buf;
    private final NewlineType newlineType;
    private final SeparatorType separatorType;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum NewlineType {
        LF,
        CRLF
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum SeparatorType {
        COLON,
        COLON_SPACE
    }

    public AsciiHeadersEncoder(ByteBuf byteBuf) {
        this(byteBuf, SeparatorType.COLON_SPACE, NewlineType.CRLF);
    }

    public AsciiHeadersEncoder(ByteBuf byteBuf, SeparatorType separatorType, NewlineType newlineType) {
        this.buf = (ByteBuf) ObjectUtil.checkNotNull(byteBuf, "buf");
        this.separatorType = (SeparatorType) ObjectUtil.checkNotNull(separatorType, "separatorType");
        this.newlineType = (NewlineType) ObjectUtil.checkNotNull(newlineType, "newlineType");
    }

    public void encode(Map.Entry<CharSequence, CharSequence> entry) {
        int i;
        int i2;
        CharSequence key = entry.getKey();
        CharSequence value = entry.getValue();
        ByteBuf byteBuf = this.buf;
        int length = key.length();
        int length2 = value.length();
        int writerIndex = byteBuf.writerIndex();
        byteBuf.ensureWritable(length + length2 + 4);
        writeAscii(byteBuf, writerIndex, key);
        int i3 = writerIndex + length;
        int i4 = C64311.f8346xcf2033ba[this.separatorType.ordinal()];
        if (i4 == 1) {
            i = i3 + 1;
            byteBuf.setByte(i3, 58);
        } else if (i4 == 2) {
            int i5 = i3 + 1;
            byteBuf.setByte(i3, 58);
            byteBuf.setByte(i5, 32);
            i = i5 + 1;
        } else {
            throw new Error();
        }
        writeAscii(byteBuf, i, value);
        int i6 = i + length2;
        int i7 = C64311.f8345x354ada9[this.newlineType.ordinal()];
        if (i7 == 1) {
            i2 = i6 + 1;
            byteBuf.setByte(i6, 10);
        } else if (i7 == 2) {
            int i8 = i6 + 1;
            byteBuf.setByte(i6, 13);
            byteBuf.setByte(i8, 10);
            i2 = i8 + 1;
        } else {
            throw new Error();
        }
        byteBuf.writerIndex(i2);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.AsciiHeadersEncoder$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C64311 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$AsciiHeadersEncoder$NewlineType */
        static final /* synthetic */ int[] f8345x354ada9 = new int[NewlineType.values().length];

        /* renamed from: $SwitchMap$io$netty$handler$codec$AsciiHeadersEncoder$SeparatorType */
        static final /* synthetic */ int[] f8346xcf2033ba;

        static {
            try {
                f8345x354ada9[NewlineType.LF.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8345x354ada9[NewlineType.CRLF.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            f8346xcf2033ba = new int[SeparatorType.values().length];
            try {
                f8346xcf2033ba[SeparatorType.COLON.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8346xcf2033ba[SeparatorType.COLON_SPACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static void writeAscii(ByteBuf byteBuf, int i, CharSequence charSequence) {
        if (charSequence instanceof AsciiString) {
            ByteBufUtil.copy((AsciiString) charSequence, 0, byteBuf, i, charSequence.length());
        } else {
            byteBuf.setCharSequence(i, charSequence, CharsetUtil.US_ASCII);
        }
    }
}
