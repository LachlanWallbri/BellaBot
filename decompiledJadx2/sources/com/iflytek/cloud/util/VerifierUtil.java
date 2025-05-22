package com.iflytek.cloud.util;

import android.graphics.Bitmap;
import com.iflytek.msc.MSC;
import java.security.SecureRandom;

/* loaded from: classes3.dex */
public class VerifierUtil {
    public static String generateNumberPassword(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        SecureRandom secureRandom = new SecureRandom();
        String str = "023456789".charAt(secureRandom.nextInt(9)) + "";
        stringBuffer.append(str);
        int i2 = 0;
        while (i2 < i - 1) {
            String str2 = str;
            while (true) {
                for (Boolean bool = false; !bool.booleanValue(); bool = true) {
                    str2 = "023456789".charAt(secureRandom.nextInt(9)) + "";
                    if (stringBuffer.indexOf(str2) >= 0) {
                        break;
                    }
                    if (Integer.parseInt(stringBuffer.charAt(stringBuffer.length() - 1) + "") * Integer.parseInt(str2) == 10) {
                        break;
                    }
                }
            }
            stringBuffer.append(str2);
            i2++;
            str = str2;
        }
        return stringBuffer.toString();
    }

    public static Bitmap ARGB2Gray(Bitmap bitmap) {
        if (bitmap.getConfig() != Bitmap.Config.ARGB_8888) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ALPHA_8);
        if (m2307a(bitmap, createBitmap)) {
            return createBitmap;
        }
        return null;
    }

    /* renamed from: a */
    private static boolean m2307a(Bitmap bitmap, Bitmap bitmap2) {
        return MSC.doARGB2Gray(bitmap, bitmap2);
    }

    public static int getBitmapsize(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }
}
