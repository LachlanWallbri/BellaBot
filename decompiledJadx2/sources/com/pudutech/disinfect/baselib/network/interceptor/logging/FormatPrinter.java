package com.pudutech.disinfect.baselib.network.interceptor.logging;

import java.util.List;
import kotlin.Metadata;
import okhttp3.MediaType;
import okhttp3.Request;
import org.bouncycastle.cms.CMSAttributeTableGenerator;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: FormatPrinter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&JH\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u00102\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000eH&J\u0018\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u000eH&J\\\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u00102\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000eH&¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/interceptor/logging/FormatPrinter;", "", "printFileRequest", "", "request", "Lokhttp3/Request;", "printFileResponse", "chainMsg", "", "isSuccessful", "", "code", "", "headers", "", "segments", "", "message", "responseUrl", "printJsonRequest", "bodyString", "printJsonResponse", CMSAttributeTableGenerator.CONTENT_TYPE, "Lokhttp3/MediaType;", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface FormatPrinter {
    void printFileRequest(Request request);

    void printFileResponse(long chainMsg, boolean isSuccessful, int code, String headers, List<String> segments, String message, String responseUrl);

    void printJsonRequest(Request request, String bodyString);

    void printJsonResponse(long chainMsg, boolean isSuccessful, int code, String headers, MediaType contentType, String bodyString, List<String> segments, String message, String responseUrl);
}
