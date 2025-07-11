package org.bouncycastle.crypto.tls;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;

/* loaded from: classes9.dex */
public class HashAlgorithm {
    public static final short md5 = 1;
    public static final short none = 0;
    public static final short sha1 = 2;
    public static final short sha224 = 3;
    public static final short sha256 = 4;
    public static final short sha384 = 5;
    public static final short sha512 = 6;

    public static String getName(short s) {
        switch (s) {
            case 0:
                return "none";
            case 1:
                return "md5";
            case 2:
                return "sha1";
            case 3:
                return "sha224";
            case 4:
                return TmpConstant.VALUE_SHA256;
            case 5:
                return "sha384";
            case 6:
                return "sha512";
            default:
                return "UNKNOWN";
        }
    }

    public static String getText(short s) {
        return getName(s) + "(" + ((int) s) + ")";
    }

    public static boolean isPrivate(short s) {
        return 224 <= s && s <= 255;
    }

    public static boolean isRecognized(short s) {
        switch (s) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return true;
            default:
                return false;
        }
    }
}
