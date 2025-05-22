package com.aliyun.alink.apiclient.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AcsURLEncoder {
    public static final String URL_ENCODING = "UTF-8";

    public static String encode(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8");
    }

    public static String percentEncode(String str) throws UnsupportedEncodingException {
        if (str != null) {
            return URLEncoder.encode(str, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
        }
        return null;
    }
}
