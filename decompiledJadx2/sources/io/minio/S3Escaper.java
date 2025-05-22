package io.minio;

import com.google.common.escape.Escaper;
import com.google.common.net.UrlEscapers;

/* loaded from: classes7.dex */
public class S3Escaper {
    private static final Escaper ESCAPER = UrlEscapers.urlPathSegmentEscaper();

    public static String encode(String str) {
        return str == null ? "" : ESCAPER.escape(str).replaceAll("\\!", "%21").replaceAll("\\$", "%24").replaceAll("\\&", "%26").replaceAll("\\'", "%27").replaceAll("\\(", "%28").replaceAll("\\)", "%29").replaceAll("\\*", "%2A").replaceAll("\\+", "%2B").replaceAll("\\,", "%2C").replaceAll("\\/", "%2F").replaceAll("\\:", "%3A").replaceAll("\\;", "%3B").replaceAll("\\=", "%3D").replaceAll("\\@", "%40").replaceAll("\\[", "%5B").replaceAll("\\]", "%5D");
    }

    public static String encodePath(String str) {
        StringBuilder sb = new StringBuilder();
        for (String str2 : str.split("/")) {
            if (!str2.isEmpty()) {
                if (sb.length() > 0) {
                    sb.append("/");
                }
                sb.append(encode(str2));
            }
        }
        if (str.startsWith("/")) {
            sb.insert(0, "/");
        }
        if (str.endsWith("/")) {
            sb.append("/");
        }
        return sb.toString();
    }
}
