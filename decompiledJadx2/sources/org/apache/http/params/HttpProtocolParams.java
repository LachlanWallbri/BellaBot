package org.apache.http.params;

import java.nio.charset.CodingErrorAction;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.Args;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
@Deprecated
/* loaded from: classes9.dex */
public final class HttpProtocolParams implements CoreProtocolPNames {
    private HttpProtocolParams() {
    }

    public static String getHttpElementCharset(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        String str = (String) httpParams.getParameter(CoreProtocolPNames.HTTP_ELEMENT_CHARSET);
        return str == null ? HTTP.DEF_PROTOCOL_CHARSET.name() : str;
    }

    public static void setHttpElementCharset(HttpParams httpParams, String str) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setParameter(CoreProtocolPNames.HTTP_ELEMENT_CHARSET, str);
    }

    public static String getContentCharset(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        String str = (String) httpParams.getParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET);
        return str == null ? HTTP.DEF_CONTENT_CHARSET.name() : str;
    }

    public static void setContentCharset(HttpParams httpParams, String str) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, str);
    }

    public static ProtocolVersion getVersion(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        Object parameter = httpParams.getParameter(CoreProtocolPNames.PROTOCOL_VERSION);
        if (parameter == null) {
            return HttpVersion.HTTP_1_1;
        }
        return (ProtocolVersion) parameter;
    }

    public static void setVersion(HttpParams httpParams, ProtocolVersion protocolVersion) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, protocolVersion);
    }

    public static String getUserAgent(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        return (String) httpParams.getParameter(CoreProtocolPNames.USER_AGENT);
    }

    public static void setUserAgent(HttpParams httpParams, String str) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setParameter(CoreProtocolPNames.USER_AGENT, str);
    }

    public static boolean useExpectContinue(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        return httpParams.getBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
    }

    public static void setUseExpectContinue(HttpParams httpParams, boolean z) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, z);
    }

    public static CodingErrorAction getMalformedInputAction(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        Object parameter = httpParams.getParameter(CoreProtocolPNames.HTTP_MALFORMED_INPUT_ACTION);
        if (parameter == null) {
            return CodingErrorAction.REPORT;
        }
        return (CodingErrorAction) parameter;
    }

    public static void setMalformedInputAction(HttpParams httpParams, CodingErrorAction codingErrorAction) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setParameter(CoreProtocolPNames.HTTP_MALFORMED_INPUT_ACTION, codingErrorAction);
    }

    public static CodingErrorAction getUnmappableInputAction(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        Object parameter = httpParams.getParameter(CoreProtocolPNames.HTTP_UNMAPPABLE_INPUT_ACTION);
        if (parameter == null) {
            return CodingErrorAction.REPORT;
        }
        return (CodingErrorAction) parameter;
    }

    public static void setUnmappableInputAction(HttpParams httpParams, CodingErrorAction codingErrorAction) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setParameter(CoreProtocolPNames.HTTP_UNMAPPABLE_INPUT_ACTION, codingErrorAction);
    }
}
