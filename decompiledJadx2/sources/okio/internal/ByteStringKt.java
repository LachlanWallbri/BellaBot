package okio.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudu.library.loracall.SlipConfig;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.Base64;
import okio.ByteString;
import okio.Platform;
import okio.Util;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: ByteString.kt */
@Metadata(m3959bv = {1, 0, 2}, m3960d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0017\u001a\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0002\u001a\u0010\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\tH\u0000\u001a\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0002\u001a\f\u0010\u0010\u001a\u00020\u0011*\u00020\u0001H\u0000\u001a\f\u0010\u0012\u001a\u00020\u0011*\u00020\u0001H\u0000\u001a\u0014\u0010\u0013\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0001H\u0000\u001a\u000e\u0010\u0015\u001a\u0004\u0018\u00010\u0001*\u00020\u0011H\u0000\u001a\f\u0010\u0016\u001a\u00020\u0001*\u00020\u0011H\u0000\u001a\f\u0010\u0017\u001a\u00020\u0001*\u00020\u0011H\u0000\u001a\u0014\u0010\u0018\u001a\u00020\u0019*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\tH\u0000\u001a\u0014\u0010\u0018\u001a\u00020\u0019*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u0001H\u0000\u001a\u0016\u0010\u001b\u001a\u00020\u0019*\u00020\u00012\b\u0010\u0014\u001a\u0004\u0018\u00010\u001cH\u0000\u001a\u0014\u0010\u001d\u001a\u00020\u001e*\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u0007H\u0000\u001a\f\u0010 \u001a\u00020\u0007*\u00020\u0001H\u0000\u001a\f\u0010!\u001a\u00020\u0007*\u00020\u0001H\u0000\u001a\f\u0010\"\u001a\u00020\u0011*\u00020\u0001H\u0000\u001a\u001c\u0010#\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010$\u001a\u00020\u0007H\u0000\u001a\f\u0010%\u001a\u00020\t*\u00020\u0001H\u0000\u001a\u001c\u0010&\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010$\u001a\u00020\u0007H\u0000\u001a,\u0010'\u001a\u00020\u0019*\u00020\u00012\u0006\u0010(\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0007H\u0000\u001a,\u0010'\u001a\u00020\u0019*\u00020\u00012\u0006\u0010(\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00012\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0007H\u0000\u001a\u0014\u0010+\u001a\u00020\u0019*\u00020\u00012\u0006\u0010,\u001a\u00020\tH\u0000\u001a\u0014\u0010+\u001a\u00020\u0019*\u00020\u00012\u0006\u0010,\u001a\u00020\u0001H\u0000\u001a\u001c\u0010-\u001a\u00020\u0001*\u00020\u00012\u0006\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u0007H\u0000\u001a\f\u00100\u001a\u00020\u0001*\u00020\u0001H\u0000\u001a\f\u00101\u001a\u00020\u0001*\u00020\u0001H\u0000\u001a\f\u00102\u001a\u00020\t*\u00020\u0001H\u0000\u001a\f\u00103\u001a\u00020\u0011*\u00020\u0001H\u0000\u001a\f\u00104\u001a\u00020\u0011*\u00020\u0001H\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00065"}, m3961d2 = {"COMMON_EMPTY", "Lokio/ByteString;", "getCOMMON_EMPTY", "()Lokio/ByteString;", "HEX_DIGITS", "", "codePointIndexToCharIndex", "", "s", "", "codePointCount", "commonOf", "data", "decodeHexDigit", "c", "", "commonBase64", "", "commonBase64Url", "commonCompareTo", "other", "commonDecodeBase64", "commonDecodeHex", "commonEncodeUtf8", "commonEndsWith", "", "suffix", "commonEquals", "", "commonGetByte", "", "pos", "commonGetSize", "commonHashCode", "commonHex", "commonIndexOf", "fromIndex", "commonInternalArray", "commonLastIndexOf", "commonRangeEquals", TypedValues.Cycle.S_WAVE_OFFSET, "otherOffset", "byteCount", "commonStartsWith", RequestParameters.PREFIX, "commonSubstring", "beginIndex", "endIndex", "commonToAsciiLowercase", "commonToAsciiUppercase", "commonToByteArray", "commonToString", "commonUtf8", "jvm"}, m3962k = 2, m3963mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class ByteStringKt {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final ByteString COMMON_EMPTY = ByteString.Companion.m3995of(new byte[0]);

    public static final String commonUtf8(ByteString receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        String utf8$jvm = receiver.getUtf8$jvm();
        if (utf8$jvm != null) {
            return utf8$jvm;
        }
        String utf8String = Platform.toUtf8String(receiver.internalArray$jvm());
        receiver.setUtf8$jvm(utf8String);
        return utf8String;
    }

    public static final String commonBase64(ByteString receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Base64.encodeBase64$default(receiver.getData$jvm(), null, 1, null);
    }

    public static final String commonBase64Url(ByteString receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Base64.encodeBase64(receiver.getData$jvm(), Base64.getBASE64_URL_SAFE());
    }

    public static final String commonHex(ByteString receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        char[] cArr = new char[receiver.getData$jvm().length * 2];
        int i = 0;
        for (byte b : receiver.getData$jvm()) {
            int i2 = i + 1;
            char[] cArr2 = HEX_DIGITS;
            cArr[i] = cArr2[(b >> 4) & 15];
            i = i2 + 1;
            cArr[i2] = cArr2[b & 15];
        }
        return new String(cArr);
    }

    public static final ByteString commonToAsciiLowercase(ByteString receiver) {
        byte b;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        for (int i = 0; i < receiver.getData$jvm().length; i++) {
            byte b2 = receiver.getData$jvm()[i];
            byte b3 = (byte) 65;
            if (b2 >= b3 && b2 <= (b = (byte) 90)) {
                byte[] data$jvm = receiver.getData$jvm();
                byte[] copyOf = Arrays.copyOf(data$jvm, data$jvm.length);
                Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                copyOf[i] = (byte) (b2 + 32);
                for (int i2 = i + 1; i2 < copyOf.length; i2++) {
                    byte b4 = copyOf[i2];
                    if (b4 >= b3 && b4 <= b) {
                        copyOf[i2] = (byte) (b4 + 32);
                    }
                }
                return new ByteString(copyOf);
            }
        }
        return receiver;
    }

    public static final ByteString commonToAsciiUppercase(ByteString receiver) {
        byte b;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        for (int i = 0; i < receiver.getData$jvm().length; i++) {
            byte b2 = receiver.getData$jvm()[i];
            byte b3 = (byte) 97;
            if (b2 >= b3 && b2 <= (b = (byte) 122)) {
                byte[] data$jvm = receiver.getData$jvm();
                byte[] copyOf = Arrays.copyOf(data$jvm, data$jvm.length);
                Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                copyOf[i] = (byte) (b2 - 32);
                for (int i2 = i + 1; i2 < copyOf.length; i2++) {
                    byte b4 = copyOf[i2];
                    if (b4 >= b3 && b4 <= b) {
                        copyOf[i2] = (byte) (b4 - 32);
                    }
                }
                return new ByteString(copyOf);
            }
        }
        return receiver;
    }

    public static final ByteString commonSubstring(ByteString receiver, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(i >= 0)) {
            throw new IllegalArgumentException("beginIndex < 0".toString());
        }
        if (!(i2 <= receiver.getData$jvm().length)) {
            throw new IllegalArgumentException(("endIndex > length(" + receiver.getData$jvm().length + ')').toString());
        }
        int i3 = i2 - i;
        if (!(i3 >= 0)) {
            throw new IllegalArgumentException("endIndex < beginIndex".toString());
        }
        if (i == 0 && i2 == receiver.getData$jvm().length) {
            return receiver;
        }
        byte[] bArr = new byte[i3];
        Platform.arraycopy(receiver.getData$jvm(), i, bArr, 0, i3);
        return new ByteString(bArr);
    }

    public static final byte commonGetByte(ByteString receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver.getData$jvm()[i];
    }

    public static final int commonGetSize(ByteString receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver.getData$jvm().length;
    }

    public static final byte[] commonToByteArray(ByteString receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        byte[] data$jvm = receiver.getData$jvm();
        byte[] copyOf = Arrays.copyOf(data$jvm, data$jvm.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    public static final byte[] commonInternalArray(ByteString receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver.getData$jvm();
    }

    public static final boolean commonRangeEquals(ByteString receiver, int i, ByteString other, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return other.rangeEquals(i2, receiver.getData$jvm(), i, i3);
    }

    public static final boolean commonRangeEquals(ByteString receiver, int i, byte[] other, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return i >= 0 && i <= receiver.getData$jvm().length - i3 && i2 >= 0 && i2 <= other.length - i3 && Util.arrayRangeEquals(receiver.getData$jvm(), i, other, i2, i3);
    }

    public static final boolean commonStartsWith(ByteString receiver, ByteString prefix) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        return receiver.rangeEquals(0, prefix, 0, prefix.size());
    }

    public static final boolean commonStartsWith(ByteString receiver, byte[] prefix) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        return receiver.rangeEquals(0, prefix, 0, prefix.length);
    }

    public static final boolean commonEndsWith(ByteString receiver, ByteString suffix) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(suffix, "suffix");
        return receiver.rangeEquals(receiver.size() - suffix.size(), suffix, 0, suffix.size());
    }

    public static final boolean commonEndsWith(ByteString receiver, byte[] suffix) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(suffix, "suffix");
        return receiver.rangeEquals(receiver.size() - suffix.length, suffix, 0, suffix.length);
    }

    public static final int commonIndexOf(ByteString receiver, byte[] other, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        int length = receiver.getData$jvm().length - other.length;
        int max = Math.max(i, 0);
        if (max > length) {
            return -1;
        }
        while (!Util.arrayRangeEquals(receiver.getData$jvm(), max, other, 0, other.length)) {
            if (max == length) {
                return -1;
            }
            max++;
        }
        return max;
    }

    public static final int commonLastIndexOf(ByteString receiver, byte[] other, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        for (int min = Math.min(i, receiver.getData$jvm().length - other.length); min >= 0; min--) {
            if (Util.arrayRangeEquals(receiver.getData$jvm(), min, other, 0, other.length)) {
                return min;
            }
        }
        return -1;
    }

    public static final boolean commonEquals(ByteString receiver, Object obj) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (obj == receiver) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.size() == receiver.getData$jvm().length && byteString.rangeEquals(0, receiver.getData$jvm(), 0, receiver.getData$jvm().length)) {
                return true;
            }
        }
        return false;
    }

    public static final int commonHashCode(ByteString receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        int hashCode$jvm = receiver.getHashCode$jvm();
        if (hashCode$jvm != 0) {
            return hashCode$jvm;
        }
        receiver.setHashCode$jvm(Arrays.hashCode(receiver.getData$jvm()));
        return receiver.getHashCode$jvm();
    }

    public static final int commonCompareTo(ByteString receiver, ByteString other) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        int size = receiver.size();
        int size2 = other.size();
        int min = Math.min(size, size2);
        for (int i = 0; i < min; i++) {
            int i2 = receiver.getByte(i) & 255;
            int i3 = other.getByte(i) & 255;
            if (i2 != i3) {
                return i2 < i3 ? -1 : 1;
            }
        }
        if (size == size2) {
            return 0;
        }
        return size < size2 ? -1 : 1;
    }

    public static final ByteString getCOMMON_EMPTY() {
        return COMMON_EMPTY;
    }

    public static final ByteString commonOf(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        byte[] copyOf = Arrays.copyOf(data, data.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return new ByteString(copyOf);
    }

    public static final ByteString commonEncodeUtf8(String receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        ByteString byteString = new ByteString(Platform.asUtf8ToByteArray(receiver));
        byteString.setUtf8$jvm(receiver);
        return byteString;
    }

    public static final ByteString commonDecodeBase64(String receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        byte[] decodeBase64ToArray = Base64.decodeBase64ToArray(receiver);
        if (decodeBase64ToArray != null) {
            return new ByteString(decodeBase64ToArray);
        }
        return null;
    }

    public static final ByteString commonDecodeHex(String receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(receiver.length() % 2 == 0)) {
            throw new IllegalArgumentException(("Unexpected hex string: " + receiver).toString());
        }
        int length = receiver.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) ((decodeHexDigit(receiver.charAt(i2)) << 4) + decodeHexDigit(receiver.charAt(i2 + 1)));
        }
        return new ByteString(bArr);
    }

    private static final int decodeHexDigit(char c) {
        if ('0' <= c && '9' >= c) {
            return c - '0';
        }
        char c2 = 'a';
        if ('a' > c || 'f' < c) {
            c2 = 'A';
            if ('A' > c || 'F' < c) {
                throw new IllegalArgumentException("Unexpected hex digit: " + c);
            }
        }
        return (c - c2) + 10;
    }

    public static final String commonToString(ByteString receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.getData$jvm().length == 0) {
            return "[size=0]";
        }
        int codePointIndexToCharIndex = codePointIndexToCharIndex(receiver.getData$jvm(), 64);
        if (codePointIndexToCharIndex == -1) {
            if (receiver.getData$jvm().length <= 64) {
                return "[hex=" + receiver.hex() + ']';
            }
            return "[size=" + receiver.getData$jvm().length + " hex=" + commonSubstring(receiver, 0, 64).hex() + "…]";
        }
        String utf8 = receiver.utf8();
        if (utf8 != null) {
            String substring = utf8.substring(0, codePointIndexToCharIndex);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            String replace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(substring, "\\", "\\\\", false, 4, (Object) null), "\n", "\\n", false, 4, (Object) null), "\r", "\\r", false, 4, (Object) null);
            if (codePointIndexToCharIndex < utf8.length()) {
                return "[size=" + receiver.getData$jvm().length + " text=" + replace$default + "…]";
            }
            return "[text=" + replace$default + ']';
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:232:0x0068, code lost:
    
        return -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final int codePointIndexToCharIndex(byte[] bArr, int i) {
        int i2;
        int length = bArr.length;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        loop0: while (i3 < length) {
            byte b = bArr[i3];
            if (b >= 0) {
                int i6 = i5 + 1;
                if (i5 == i) {
                    return i4;
                }
                if (b != 10 && b != 13) {
                    if ((b >= 0 && 31 >= b) || (Byte.MAX_VALUE <= b && 159 >= b)) {
                        return -1;
                    }
                }
                if (b == 65533) {
                    return -1;
                }
                i4 += b < 65536 ? 1 : 2;
                i3++;
                while (true) {
                    i5 = i6;
                    if (i3 < length && bArr[i3] >= 0) {
                        int i7 = i3 + 1;
                        byte b2 = bArr[i3];
                        i6 = i5 + 1;
                        if (i5 == i) {
                            return i4;
                        }
                        if (b2 != 10 && b2 != 13) {
                            if ((b2 >= 0 && 31 >= b2) || (Byte.MAX_VALUE <= b2 && 159 >= b2)) {
                                break loop0;
                            }
                        }
                        if (b2 == 65533) {
                            break loop0;
                        }
                        i4 += b2 < 65536 ? 1 : 2;
                        i3 = i7;
                    }
                }
            } else {
                if ((b >> 5) == -2) {
                    int i8 = i3 + 1;
                    if (length <= i8) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    byte b3 = bArr[i3];
                    byte b4 = bArr[i8];
                    if (!((b4 & SlipConfig.END) == 128)) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    int i9 = (b4 ^ 3968) ^ (b3 << 6);
                    if (i9 < 128) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    i2 = i5 + 1;
                    if (i5 == i) {
                        return i4;
                    }
                    if (i9 != 10 && i9 != 13) {
                        if ((i9 >= 0 && 31 >= i9) || (127 <= i9 && 159 >= i9)) {
                            return -1;
                        }
                    }
                    if (i9 == 65533) {
                        return -1;
                    }
                    i4 += i9 < 65536 ? 1 : 2;
                    i3 += 2;
                } else if ((b >> 4) == -2) {
                    int i10 = i3 + 2;
                    if (length <= i10) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    byte b5 = bArr[i3];
                    byte b6 = bArr[i3 + 1];
                    if (!((b6 & SlipConfig.END) == 128)) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    byte b7 = bArr[i10];
                    if (!((b7 & SlipConfig.END) == 128)) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    int i11 = ((b7 ^ (-123008)) ^ (b6 << 6)) ^ (b5 << 12);
                    if (i11 < 2048) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    if (55296 <= i11 && 57343 >= i11) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    i2 = i5 + 1;
                    if (i5 == i) {
                        return i4;
                    }
                    if (i11 != 10 && i11 != 13) {
                        if ((i11 >= 0 && 31 >= i11) || (127 <= i11 && 159 >= i11)) {
                            return -1;
                        }
                    }
                    if (i11 == 65533) {
                        return -1;
                    }
                    i4 += i11 < 65536 ? 1 : 2;
                    i3 += 3;
                } else {
                    if ((b >> 3) != -2) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    int i12 = i3 + 3;
                    if (length <= i12) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    byte b8 = bArr[i3];
                    byte b9 = bArr[i3 + 1];
                    if (!((b9 & SlipConfig.END) == 128)) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    byte b10 = bArr[i3 + 2];
                    if (!((b10 & SlipConfig.END) == 128)) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    byte b11 = bArr[i12];
                    if (!((b11 & SlipConfig.END) == 128)) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    int i13 = (((b11 ^ 3678080) ^ (b10 << 6)) ^ (b9 << 12)) ^ (b8 << 18);
                    if (i13 > 1114111) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    if (55296 <= i13 && 57343 >= i13) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    if (i13 < 65536) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    i2 = i5 + 1;
                    if (i5 == i) {
                        return i4;
                    }
                    if (i13 != 10 && i13 != 13) {
                        if ((i13 >= 0 && 31 >= i13) || (127 <= i13 && 159 >= i13)) {
                            return -1;
                        }
                    }
                    if (i13 == 65533) {
                        return -1;
                    }
                    i4 += i13 < 65536 ? 1 : 2;
                    i3 += 4;
                }
                i5 = i2;
            }
        }
        return i4;
    }
}
