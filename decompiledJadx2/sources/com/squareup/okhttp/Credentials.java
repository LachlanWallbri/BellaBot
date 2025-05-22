package com.squareup.okhttp;

import java.io.UnsupportedEncodingException;
import okio.ByteString;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class Credentials {
    private Credentials() {
    }

    public static String basic(String str, String str2) {
        try {
            return "Basic " + ByteString.m3992of((str + ":" + str2).getBytes("ISO-8859-1")).base64();
        } catch (UnsupportedEncodingException unused) {
            throw new AssertionError();
        }
    }
}
