package okio;

import com.pudutech.mirsdk.compat.topo.MapElement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: -Base64.kt */
@Metadata(m3959bv = {1, 0, 2}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u000e\u0010\u0006\u001a\u0004\u0018\u00010\u0001*\u00020\u0007H\u0000\u001a\u0016\u0010\b\u001a\u00020\u0007*\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\u0001H\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003¨\u0006\n"}, m3961d2 = {"BASE64", "", "getBASE64", "()[B", "BASE64_URL_SAFE", "getBASE64_URL_SAFE", "decodeBase64ToArray", "", "encodeBase64", MapElement.Key.MAP, "jvm"}, m3962k = 2, m3963mv = {1, 1, 11})
/* renamed from: okio.-Base64, reason: invalid class name */
/* loaded from: classes2.dex */
public final class Base64 {
    private static final byte[] BASE64 = ByteString.Companion.encodeUtf8("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/").getData$jvm();
    private static final byte[] BASE64_URL_SAFE = ByteString.Companion.encodeUtf8("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_").getData$jvm();

    public static final byte[] getBASE64() {
        return BASE64;
    }

    public static final byte[] getBASE64_URL_SAFE() {
        return BASE64_URL_SAFE;
    }

    public static final byte[] decodeBase64ToArray(String receiver) {
        int i;
        char charAt;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        int length = receiver.length();
        while (length > 0 && ((charAt = receiver.charAt(length - 1)) == '=' || charAt == '\n' || charAt == '\r' || charAt == ' ' || charAt == '\t')) {
            length--;
        }
        int i2 = (int) ((length * 6) / 8);
        byte[] bArr = new byte[i2];
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < length; i6++) {
            char charAt2 = receiver.charAt(i6);
            if ('A' <= charAt2 && 'Z' >= charAt2) {
                i = charAt2 - 'A';
            } else if ('a' <= charAt2 && 'z' >= charAt2) {
                i = charAt2 - 'G';
            } else if ('0' <= charAt2 && '9' >= charAt2) {
                i = charAt2 + 4;
            } else if (charAt2 == '+' || charAt2 == '-') {
                i = 62;
            } else if (charAt2 == '/' || charAt2 == '_') {
                i = 63;
            } else {
                if (charAt2 != '\n' && charAt2 != '\r' && charAt2 != ' ' && charAt2 != '\t') {
                    return null;
                }
            }
            i4 = (i4 << 6) | i;
            i3++;
            if (i3 % 4 == 0) {
                int i7 = i5 + 1;
                bArr[i5] = (byte) (i4 >> 16);
                int i8 = i7 + 1;
                bArr[i7] = (byte) (i4 >> 8);
                bArr[i8] = (byte) i4;
                i5 = i8 + 1;
            }
        }
        int i9 = i3 % 4;
        if (i9 == 1) {
            return null;
        }
        if (i9 == 2) {
            bArr[i5] = (byte) ((i4 << 12) >> 16);
            i5++;
        } else if (i9 == 3) {
            int i10 = i4 << 6;
            int i11 = i5 + 1;
            bArr[i5] = (byte) (i10 >> 16);
            i5 = i11 + 1;
            bArr[i11] = (byte) (i10 >> 8);
        }
        if (i5 == i2) {
            return bArr;
        }
        byte[] bArr2 = new byte[i5];
        Platform.arraycopy(bArr, 0, bArr2, 0, i5);
        return bArr2;
    }

    public static /* bridge */ /* synthetic */ String encodeBase64$default(byte[] bArr, byte[] bArr2, int i, Object obj) {
        if ((i & 1) != 0) {
            bArr2 = BASE64;
        }
        return encodeBase64(bArr, bArr2);
    }

    public static final String encodeBase64(byte[] receiver, byte[] map) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(map, "map");
        byte[] bArr = new byte[((receiver.length + 2) / 3) * 4];
        int length = receiver.length - (receiver.length % 3);
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i + 1;
            byte b = receiver[i];
            int i4 = i3 + 1;
            byte b2 = receiver[i3];
            int i5 = i4 + 1;
            byte b3 = receiver[i4];
            int i6 = i2 + 1;
            bArr[i2] = map[(b & 255) >> 2];
            int i7 = i6 + 1;
            bArr[i6] = map[((b & 3) << 4) | ((b2 & 255) >> 4)];
            int i8 = i7 + 1;
            bArr[i7] = map[((b2 & 15) << 2) | ((b3 & 255) >> 6)];
            i2 = i8 + 1;
            bArr[i8] = map[b3 & 63];
            i = i5;
        }
        int length2 = receiver.length - length;
        if (length2 == 1) {
            byte b4 = receiver[i];
            int i9 = i2 + 1;
            bArr[i2] = map[(b4 & 255) >> 2];
            int i10 = i9 + 1;
            bArr[i9] = map[(b4 & 3) << 4];
            byte b5 = (byte) 61;
            bArr[i10] = b5;
            bArr[i10 + 1] = b5;
        } else if (length2 == 2) {
            int i11 = i + 1;
            byte b6 = receiver[i];
            byte b7 = receiver[i11];
            int i12 = i2 + 1;
            bArr[i2] = map[(b6 & 255) >> 2];
            int i13 = i12 + 1;
            bArr[i12] = map[((b6 & 3) << 4) | ((b7 & 255) >> 4)];
            bArr[i13] = map[(b7 & 15) << 2];
            bArr[i13 + 1] = (byte) 61;
        }
        return Platform.toUtf8String(bArr);
    }
}
