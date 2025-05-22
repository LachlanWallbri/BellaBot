package io.netty.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.util.AsciiString;
import io.netty.util.CharsetUtil;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public final class AsciiHeadersEncoder {
    private final ByteBuf buf;
    private final NewlineType newlineType;
    private final SeparatorType separatorType;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes7.dex
     */
    /* loaded from: classes.dex */
    public enum NewlineType {
        LF,
        CRLF
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes7.dex
     */
    /* loaded from: classes.dex */
    public enum SeparatorType {
        COLON,
        COLON_SPACE
    }

    public AsciiHeadersEncoder(ByteBuf byteBuf) {
        this(byteBuf, SeparatorType.COLON_SPACE, NewlineType.CRLF);
    }

    public AsciiHeadersEncoder(ByteBuf byteBuf, SeparatorType separatorType, NewlineType newlineType) {
        if (byteBuf == null) {
            throw new NullPointerException("buf");
        }
        if (separatorType == null) {
            throw new NullPointerException("separatorType");
        }
        if (newlineType == null) {
            throw new NullPointerException("newlineType");
        }
        this.buf = byteBuf;
        this.separatorType = separatorType;
        this.newlineType = newlineType;
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
        int i4 = C70931.f8464xcf2033ba[this.separatorType.ordinal()];
        if (i4 == 1) {
            byteBuf.setByte(i3, 58);
            i = i3 + 1;
        } else if (i4 == 2) {
            int i5 = i3 + 1;
            byteBuf.setByte(i3, 58);
            i = i5 + 1;
            byteBuf.setByte(i5, 32);
        } else {
            throw new Error();
        }
        writeAscii(byteBuf, i, value);
        int i6 = i + length2;
        int i7 = C70931.f8463x354ada9[this.newlineType.ordinal()];
        if (i7 == 1) {
            byteBuf.setByte(i6, 10);
            i2 = i6 + 1;
        } else if (i7 == 2) {
            int i8 = i6 + 1;
            byteBuf.setByte(i6, 13);
            i2 = i8 + 1;
            byteBuf.setByte(i8, 10);
        } else {
            throw new Error();
        }
        byteBuf.writerIndex(i2);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes7.dex
     */
    /* renamed from: io.netty.handler.codec.AsciiHeadersEncoder$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C70931 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$AsciiHeadersEncoder$NewlineType */
        static final /* synthetic */ int[] f8463x354ada9;

        /* renamed from: $SwitchMap$io$netty$handler$codec$AsciiHeadersEncoder$SeparatorType */
        static final /* synthetic */ int[] f8464xcf2033ba;

        static {
            int[] iArr = new int[NewlineType.values().length];
            f8463x354ada9 = iArr;
            try {
                iArr[NewlineType.LF.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8463x354ada9[NewlineType.CRLF.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[SeparatorType.values().length];
            f8464xcf2033ba = iArr2;
            try {
                iArr2[SeparatorType.COLON.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8464xcf2033ba[SeparatorType.COLON_SPACE.ordinal()] = 2;
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
