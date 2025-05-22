package com.liulishuo.okdownload;

import com.liulishuo.okdownload.core.connection.DownloadConnection;
import java.io.IOException;
import java.net.ProtocolException;

/* loaded from: classes4.dex */
public class RedirectUtil {
    static final int HTTP_PERMANENT_REDIRECT = 308;
    static final int HTTP_TEMPORARY_REDIRECT = 307;
    public static final int MAX_REDIRECT_TIMES = 10;

    public static boolean isRedirect(int i) {
        return i == 301 || i == 302 || i == 303 || i == 300 || i == 307 || i == 308;
    }

    public static String getRedirectedUrl(DownloadConnection.Connected connected, int i) throws IOException {
        String responseHeaderField = connected.getResponseHeaderField("Location");
        if (responseHeaderField != null) {
            return responseHeaderField;
        }
        throw new ProtocolException("Response code is " + i + " but can't find Location field");
    }
}
