package kotlin.reflect.jvm.internal.impl.protobuf;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
final class Utf8 {
    private static int incompleteStateFor(int i) {
        if (i > -12) {
            return -1;
        }
        return i;
    }

    private static int incompleteStateFor(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }

    private static int incompleteStateFor(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    public static boolean isValidUtf8(byte[] bArr) {
        return isValidUtf8(bArr, 0, bArr.length);
    }

    public static boolean isValidUtf8(byte[] bArr, int i, int i2) {
        return partialIsValidUtf8(bArr, i, i2) == 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0015, code lost:
    
        if (r7[r8] > (-65)) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0046, code lost:
    
        if (r7[r8] > (-65)) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x007f, code lost:
    
        if (r7[r8] > (-65)) goto L53;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int partialIsValidUtf8(int i, byte[] bArr, int i2, int i3) {
        int i4;
        if (i != 0) {
            if (i2 >= i3) {
                return i;
            }
            byte b = (byte) i;
            if (b < -32) {
                if (b >= -62) {
                    i4 = i2 + 1;
                }
                return -1;
            }
            if (b < -16) {
                byte b2 = (byte) (~(i >> 8));
                if (b2 == 0) {
                    int i5 = i2 + 1;
                    byte b3 = bArr[i2];
                    if (i5 >= i3) {
                        return incompleteStateFor(b, b3);
                    }
                    i2 = i5;
                    b2 = b3;
                }
                if (b2 <= -65 && ((b != -32 || b2 >= -96) && (b != -19 || b2 < -96))) {
                    i4 = i2 + 1;
                }
                return -1;
            }
            byte b4 = (byte) (~(i >> 8));
            byte b5 = 0;
            if (b4 == 0) {
                int i6 = i2 + 1;
                b4 = bArr[i2];
                if (i6 >= i3) {
                    return incompleteStateFor(b, b4);
                }
                i2 = i6;
            } else {
                b5 = (byte) (i >> 16);
            }
            if (b5 == 0) {
                int i7 = i2 + 1;
                b5 = bArr[i2];
                if (i7 >= i3) {
                    return incompleteStateFor(b, b4, b5);
                }
                i2 = i7;
            }
            if (b4 <= -65 && (((b << 28) + (b4 + 112)) >> 30) == 0 && b5 <= -65) {
                i4 = i2 + 1;
            }
            return -1;
            i2 = i4;
        }
        return partialIsValidUtf8(bArr, i2, i3);
    }

    public static int partialIsValidUtf8(byte[] bArr, int i, int i2) {
        while (i < i2 && bArr[i] >= 0) {
            i++;
        }
        if (i >= i2) {
            return 0;
        }
        return partialIsValidUtf8NonAscii(bArr, i, i2);
    }

    private static int partialIsValidUtf8NonAscii(byte[] bArr, int i, int i2) {
        while (i < i2) {
            int i3 = i + 1;
            byte b = bArr[i];
            if (b < 0) {
                if (b < -32) {
                    if (i3 >= i2) {
                        return b;
                    }
                    if (b >= -62) {
                        i = i3 + 1;
                        if (bArr[i3] > -65) {
                        }
                    }
                    return -1;
                }
                if (b >= -16) {
                    if (i3 >= i2 - 2) {
                        return incompleteStateFor(bArr, i3, i2);
                    }
                    int i4 = i3 + 1;
                    byte b2 = bArr[i3];
                    if (b2 <= -65 && (((b << 28) + (b2 + 112)) >> 30) == 0) {
                        int i5 = i4 + 1;
                        if (bArr[i4] <= -65) {
                            i3 = i5 + 1;
                            if (bArr[i5] > -65) {
                            }
                        }
                    }
                    return -1;
                }
                if (i3 >= i2 - 1) {
                    return incompleteStateFor(bArr, i3, i2);
                }
                int i6 = i3 + 1;
                byte b3 = bArr[i3];
                if (b3 <= -65 && ((b != -32 || b3 >= -96) && (b != -19 || b3 < -96))) {
                    i = i6 + 1;
                    if (bArr[i6] > -65) {
                    }
                }
                return -1;
            }
            i = i3;
        }
        return 0;
    }

    private static int incompleteStateFor(byte[] bArr, int i, int i2) {
        byte b = bArr[i - 1];
        int i3 = i2 - i;
        if (i3 == 0) {
            return incompleteStateFor(b);
        }
        if (i3 == 1) {
            return incompleteStateFor(b, bArr[i]);
        }
        if (i3 == 2) {
            return incompleteStateFor(b, bArr[i], bArr[i + 1]);
        }
        throw new AssertionError();
    }
}
