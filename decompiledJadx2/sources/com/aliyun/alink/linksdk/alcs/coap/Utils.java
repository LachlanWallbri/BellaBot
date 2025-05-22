package com.aliyun.alink.linksdk.alcs.coap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class Utils {
    private Utils() {
    }

    public static String toHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null) {
            sb.append("null");
        } else {
            for (byte b : bArr) {
                sb.append(String.format("%02x", Integer.valueOf(b & 255)));
            }
        }
        return sb.toString();
    }

    public static String toHexText(byte[] bArr, int i) {
        if (bArr == null) {
            return "null";
        }
        if (i > bArr.length) {
            i = bArr.length;
        }
        StringBuilder sb = new StringBuilder();
        if (16 < i) {
            sb.append(System.lineSeparator());
        }
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(String.format("%02x", Integer.valueOf(bArr[i2] & 255)));
            if (31 == (i2 & 31)) {
                sb.append(System.lineSeparator());
            } else {
                sb.append(' ');
            }
        }
        if (i < bArr.length) {
            sb.append(" .. ");
            sb.append(bArr.length);
            sb.append(" bytes");
        }
        return sb.toString();
    }
}
