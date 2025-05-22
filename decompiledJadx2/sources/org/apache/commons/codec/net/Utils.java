package org.apache.commons.codec.net;

import org.apache.commons.codec.DecoderException;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
class Utils {
    private static final int RADIX = 16;

    Utils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int digit16(byte b) throws DecoderException {
        int digit = Character.digit((char) b, 16);
        if (digit != -1) {
            return digit;
        }
        throw new DecoderException("Invalid URL encoding: not a valid digit (radix 16): " + ((int) b));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static char hexDigit(int i) {
        return Character.toUpperCase(Character.forDigit(i & 15, 16));
    }
}
