package com.pudutech.pd_network.interceptor.logging;

import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.pd_network.interceptor.logging.DefaultFormatPrinter;
import com.pudutech.pd_network.interceptor.logging.LogInterceptor;
import com.pudutech.pd_network.utils.CharacterHandler;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.bouncycastle.cms.CMSAttributeTableGenerator;

/* compiled from: DefaultFormatPrinter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016JH\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u000e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u00112\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000fH\u0016J\u0018\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u000fH\u0016J\\\u0010\u0016\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0015\u001a\u0004\u0018\u00010\u000f2\u000e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u00112\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000fH\u0016¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/pd_network/interceptor/logging/DefaultFormatPrinter;", "Lcom/pudutech/pd_network/interceptor/logging/FormatPrinter;", "()V", "printFileRequest", "", "request", "Lokhttp3/Request;", "printFileResponse", "chainMs", "", "isSuccessful", "", "code", "", "headers", "", "segments", "", "message", "responseUrl", "printJsonRequest", "bodyString", "printJsonResponse", CMSAttributeTableGenerator.CONTENT_TYPE, "Lokhttp3/MediaType;", "Companion", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DefaultFormatPrinter implements FormatPrinter {
    private static final String[] ARMS;
    private static final String BODY_TAG = "Body:";
    private static final String CENTER_LINE = "├ ";
    private static final String CORNER_BOTTOM = "└ ";
    private static final String CORNER_UP = "┌ ";
    private static final String DEFAULT_LINE = "│ ";
    private static final String END_LINE = "└────────────────────────────────────────────────────────────────";
    private static final String HEADERS_TAG = "Headers:";
    private static final String METHOD_TAG = "Method: @";

    /* renamed from: N */
    private static final String f6841N = "\n";
    private static final String[] OMITTED_REQUEST;
    private static final String[] OMITTED_RESPONSE;
    private static final String RECEIVED_TAG = "Received in: ";
    private static final String REQUEST_UP_LINE = "┌────── Request ─────────────────────────────────────────────────";
    private static final String RESPONSE_UP_LINE = "┌────── Response ────────────────────────────────────────────────";
    private static final String STATUS_CODE_TAG = "Status Code: ";

    /* renamed from: T */
    private static final String f6842T = "\t";
    private static final String TAG = "HttpLog";
    private static final String URL_TAG = "URL: ";
    private static final ThreadLocal<Integer> last;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String DOUBLE_SEPARATOR = LINE_SEPARATOR + LINE_SEPARATOR;

    @Override // com.pudutech.pd_network.interceptor.logging.FormatPrinter
    public void printJsonRequest(final Request request, final String bodyString) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        Intrinsics.checkParameterIsNotNull(bodyString, "bodyString");
        mExecutor.submit(new Runnable() { // from class: com.pudutech.pd_network.interceptor.logging.DefaultFormatPrinter$printJsonRequest$1
            @Override // java.lang.Runnable
            public final void run() {
                String tag;
                String[] request2;
                String str = DefaultFormatPrinter.LINE_SEPARATOR + "Body:" + DefaultFormatPrinter.LINE_SEPARATOR + bodyString;
                tag = DefaultFormatPrinter.INSTANCE.getTag(true);
                LogUtils.INSTANCE.debugInfo(tag, "┌────── Request ─────────────────────────────────────────────────");
                String str2 = bodyString;
                LogInterceptor.Companion companion = LogInterceptor.INSTANCE;
                RequestBody body = request.body();
                if (body == null) {
                    Intrinsics.throwNpe();
                }
                if (companion.isJson(body.get$contentType())) {
                    str2 = CharacterHandler.INSTANCE.jsonFormat(str2);
                } else {
                    LogInterceptor.Companion companion2 = LogInterceptor.INSTANCE;
                    RequestBody body2 = request.body();
                    if (body2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (companion2.isXml(body2.get$contentType())) {
                        str2 = CharacterHandler.INSTANCE.xmlFormat(str2);
                    }
                }
                String str3 = DefaultFormatPrinter.LINE_SEPARATOR + "Body:" + DefaultFormatPrinter.LINE_SEPARATOR + str2;
                DefaultFormatPrinter.INSTANCE.logLines(tag, new String[]{"URL: " + request.url()}, false);
                DefaultFormatPrinter.Companion companion3 = DefaultFormatPrinter.INSTANCE;
                request2 = DefaultFormatPrinter.INSTANCE.getRequest(request);
                companion3.logLines(tag, request2, true);
                DefaultFormatPrinter.Companion companion4 = DefaultFormatPrinter.INSTANCE;
                String str4 = str3;
                String[] strArr = new String[1];
                String str5 = DefaultFormatPrinter.LINE_SEPARATOR;
                if (str5 == null) {
                    Intrinsics.throwNpe();
                }
                strArr[0] = str5;
                Object[] array = StringsKt.split$default((CharSequence) str4, strArr, false, 0, 6, (Object) null).toArray(new String[0]);
                if (array != null) {
                    companion4.logLines(tag, (String[]) array, true);
                    LogUtils.INSTANCE.debugInfo(tag, "└────────────────────────────────────────────────────────────────");
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        });
    }

    @Override // com.pudutech.pd_network.interceptor.logging.FormatPrinter
    public void printFileRequest(final Request request) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        mExecutor.submit(new Runnable() { // from class: com.pudutech.pd_network.interceptor.logging.DefaultFormatPrinter$printFileRequest$1
            @Override // java.lang.Runnable
            public final void run() {
                String tag;
                String[] request2;
                String[] strArr;
                tag = DefaultFormatPrinter.INSTANCE.getTag(true);
                LogUtils.INSTANCE.debugInfo(tag, "┌────── Request ─────────────────────────────────────────────────");
                DefaultFormatPrinter.INSTANCE.logLines(tag, new String[]{"URL: " + Request.this.url()}, false);
                DefaultFormatPrinter.Companion companion = DefaultFormatPrinter.INSTANCE;
                request2 = DefaultFormatPrinter.INSTANCE.getRequest(Request.this);
                companion.logLines(tag, request2, true);
                DefaultFormatPrinter.Companion companion2 = DefaultFormatPrinter.INSTANCE;
                strArr = DefaultFormatPrinter.OMITTED_REQUEST;
                companion2.logLines(tag, strArr, true);
                LogUtils.INSTANCE.debugInfo(tag, "└────────────────────────────────────────────────────────────────");
            }
        });
    }

    @Override // com.pudutech.pd_network.interceptor.logging.FormatPrinter
    public void printJsonResponse(final long chainMs, final boolean isSuccessful, final int code, final String headers, final MediaType contentType, final String bodyString, final List<String> segments, final String message, final String responseUrl) {
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        Intrinsics.checkParameterIsNotNull(segments, "segments");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(responseUrl, "responseUrl");
        mExecutor.submit(new Runnable() { // from class: com.pudutech.pd_network.interceptor.logging.DefaultFormatPrinter$printJsonResponse$1
            @Override // java.lang.Runnable
            public final void run() {
                String tag;
                String[] response;
                String str = bodyString;
                if (LogInterceptor.INSTANCE.isJson(contentType)) {
                    CharacterHandler.Companion companion = CharacterHandler.INSTANCE;
                    if (str == null) {
                        Intrinsics.throwNpe();
                    }
                    str = companion.jsonFormat(str);
                } else if (LogInterceptor.INSTANCE.isXml(contentType)) {
                    str = CharacterHandler.INSTANCE.xmlFormat(str);
                }
                String str2 = DefaultFormatPrinter.LINE_SEPARATOR + "Body:" + DefaultFormatPrinter.LINE_SEPARATOR + str;
                tag = DefaultFormatPrinter.INSTANCE.getTag(false);
                String[] strArr = {"URL: " + responseUrl, "\n"};
                LogUtils.INSTANCE.debugInfo(tag, "┌────── Response ────────────────────────────────────────────────");
                DefaultFormatPrinter.INSTANCE.logLines(tag, strArr, true);
                DefaultFormatPrinter.Companion companion2 = DefaultFormatPrinter.INSTANCE;
                response = DefaultFormatPrinter.INSTANCE.getResponse(headers, chainMs, code, isSuccessful, segments, message);
                companion2.logLines(tag, response, true);
                DefaultFormatPrinter.Companion companion3 = DefaultFormatPrinter.INSTANCE;
                String str3 = str2;
                String[] strArr2 = new String[1];
                String str4 = DefaultFormatPrinter.LINE_SEPARATOR;
                if (str4 == null) {
                    Intrinsics.throwNpe();
                }
                strArr2[0] = str4;
                Object[] array = StringsKt.split$default((CharSequence) str3, strArr2, false, 0, 6, (Object) null).toArray(new String[0]);
                if (array != null) {
                    companion3.logLines(tag, (String[]) array, true);
                    LogUtils.INSTANCE.debugInfo(tag, "└────────────────────────────────────────────────────────────────");
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        });
    }

    @Override // com.pudutech.pd_network.interceptor.logging.FormatPrinter
    public void printFileResponse(final long chainMs, final boolean isSuccessful, final int code, final String headers, final List<String> segments, final String message, final String responseUrl) {
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        Intrinsics.checkParameterIsNotNull(segments, "segments");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(responseUrl, "responseUrl");
        mExecutor.submit(new Runnable() { // from class: com.pudutech.pd_network.interceptor.logging.DefaultFormatPrinter$printFileResponse$1
            @Override // java.lang.Runnable
            public final void run() {
                String tag;
                String[] response;
                String[] strArr;
                tag = DefaultFormatPrinter.INSTANCE.getTag(false);
                String[] strArr2 = {"URL: " + responseUrl, "\n"};
                LogUtils.INSTANCE.debugInfo(tag, "┌────── Response ────────────────────────────────────────────────");
                DefaultFormatPrinter.INSTANCE.logLines(tag, strArr2, true);
                DefaultFormatPrinter.Companion companion = DefaultFormatPrinter.INSTANCE;
                response = DefaultFormatPrinter.INSTANCE.getResponse(headers, chainMs, code, isSuccessful, segments, message);
                companion.logLines(tag, response, true);
                DefaultFormatPrinter.Companion companion2 = DefaultFormatPrinter.INSTANCE;
                strArr = DefaultFormatPrinter.OMITTED_RESPONSE;
                companion2.logLines(tag, strArr, true);
                LogUtils.INSTANCE.debugInfo(tag, "└────────────────────────────────────────────────────────────────");
            }
        });
    }

    /* compiled from: DefaultFormatPrinter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010!\u001a\u00020\u0005H\u0002J\u0010\u0010\"\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u0005H\u0002J\u001d\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u0006\u0010%\u001a\u00020&H\u0002¢\u0006\u0002\u0010'JM\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u0006\u0010#\u001a\u00020\u00052\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020-2\u000e\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050/2\u0006\u00100\u001a\u00020\u0005H\u0002¢\u0006\u0002\u00101J\u0010\u00102\u001a\u00020\u00052\u0006\u00103\u001a\u00020-H\u0002J\u0010\u00104\u001a\u00020-2\u0006\u00105\u001a\u00020\u0005H\u0002J-\u00106\u001a\u0002072\u0006\u00108\u001a\u00020\u00052\u000e\u00109\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u0006\u0010:\u001a\u00020-H\u0002¢\u0006\u0002\u0010;J\u0010\u0010<\u001a\u00020\u00052\u0006\u00108\u001a\u00020\u0005H\u0002J\u0018\u0010=\u001a\u00020\u00052\u000e\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050/H\u0002R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n \u0010*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u0010\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u001e\u0010\u0014\u001a\u0010\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\n \u0010*\u0004\u0018\u00010 0 X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006>"}, m3961d2 = {"Lcom/pudutech/pd_network/interceptor/logging/DefaultFormatPrinter$Companion;", "", "()V", "ARMS", "", "", "[Ljava/lang/String;", "BODY_TAG", "CENTER_LINE", "CORNER_BOTTOM", "CORNER_UP", "DEFAULT_LINE", "DOUBLE_SEPARATOR", "END_LINE", "HEADERS_TAG", "LINE_SEPARATOR", "kotlin.jvm.PlatformType", "METHOD_TAG", "N", "OMITTED_REQUEST", "OMITTED_RESPONSE", "RECEIVED_TAG", "REQUEST_UP_LINE", "RESPONSE_UP_LINE", "STATUS_CODE_TAG", ExifInterface.GPS_DIRECTION_TRUE, "TAG", "URL_TAG", "last", "Ljava/lang/ThreadLocal;", "", "mExecutor", "Ljava/util/concurrent/ExecutorService;", "computeKey", "dotHeaders", "header", "getRequest", "request", "Lokhttp3/Request;", "(Lokhttp3/Request;)[Ljava/lang/String;", "getResponse", "tookMs", "", "code", "isSuccessful", "", "segments", "", "message", "(Ljava/lang/String;JIZLjava/util/List;Ljava/lang/String;)[Ljava/lang/String;", "getTag", "isRequest", "isEmpty", "line", "logLines", "", AIUIConstant.KEY_TAG, "lines", "withLineSize", "(Ljava/lang/String;[Ljava/lang/String;Z)V", "resolveTag", "slashSegments", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final String getTag(boolean isRequest) {
            return isRequest ? "HttpLog-Req" : "HttpLog-Res";
        }

        private final String resolveTag(String tag) {
            return tag;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final boolean isEmpty(String line) {
            String str = line;
            if (!TextUtils.isEmpty(str) && !Intrinsics.areEqual("\n", line) && !Intrinsics.areEqual(DefaultFormatPrinter.f6842T, line)) {
                int length = str.length() - 1;
                int i = 0;
                boolean z = false;
                while (i <= length) {
                    boolean z2 = str.charAt(!z ? i : length) <= ' ';
                    if (z) {
                        if (!z2) {
                            break;
                        }
                        length--;
                    } else if (z2) {
                        i++;
                    } else {
                        z = true;
                    }
                }
                if (!TextUtils.isEmpty(str.subSequence(i, length + 1).toString())) {
                    return false;
                }
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void logLines(String tag, String[] lines, boolean withLineSize) {
            int i;
            for (String str : lines) {
                if (str == null) {
                    Intrinsics.throwNpe();
                }
                int length = str.length();
                int i2 = withLineSize ? 110 : length;
                int i3 = length / i2;
                if (i3 >= 0) {
                    while (true) {
                        int i4 = i * i2;
                        int i5 = i + 1;
                        int i6 = i5 * i2;
                        if (i6 > str.length()) {
                            i6 = str.length();
                        }
                        LogUtils logUtils = LogUtils.INSTANCE;
                        String resolveTag = resolveTag(tag);
                        StringBuilder sb = new StringBuilder();
                        sb.append(DefaultFormatPrinter.DEFAULT_LINE);
                        String substring = str.substring(i4, i6);
                        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        sb.append(substring);
                        logUtils.debugInfo(resolveTag, sb.toString());
                        i = i != i3 ? i5 : 0;
                    }
                }
            }
        }

        private final String computeKey() {
            Object obj = DefaultFormatPrinter.last.get();
            if (obj == null) {
                Intrinsics.throwNpe();
            }
            if (((Number) obj).intValue() >= 4) {
                DefaultFormatPrinter.last.set(0);
            }
            String[] strArr = DefaultFormatPrinter.ARMS;
            Object obj2 = DefaultFormatPrinter.last.get();
            if (obj2 == null) {
                Intrinsics.throwNpe();
            }
            String str = strArr[((Number) obj2).intValue()];
            ThreadLocal threadLocal = DefaultFormatPrinter.last;
            Object obj3 = DefaultFormatPrinter.last.get();
            if (obj3 == null) {
                Intrinsics.throwNpe();
            }
            threadLocal.set(Integer.valueOf(((Number) obj3).intValue() + 1));
            return str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String[] getRequest(Request request) {
            String str;
            String headers = request.headers().toString();
            StringBuilder sb = new StringBuilder();
            sb.append(DefaultFormatPrinter.METHOD_TAG);
            sb.append(request.method());
            sb.append(DefaultFormatPrinter.DOUBLE_SEPARATOR);
            Companion companion = this;
            if (companion.isEmpty(headers)) {
                str = "";
            } else {
                str = DefaultFormatPrinter.HEADERS_TAG + DefaultFormatPrinter.LINE_SEPARATOR + companion.dotHeaders(headers);
            }
            sb.append(str);
            String sb2 = sb.toString();
            String[] strArr = new String[1];
            String str2 = DefaultFormatPrinter.LINE_SEPARATOR;
            if (str2 == null) {
                Intrinsics.throwNpe();
            }
            strArr[0] = str2;
            Object[] array = StringsKt.split$default((CharSequence) sb2, strArr, false, 0, 6, (Object) null).toArray(new String[0]);
            if (array != null) {
                return (String[]) array;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String[] getResponse(String header, long tookMs, int code, boolean isSuccessful, List<String> segments, String message) {
            String str;
            Companion companion = this;
            String slashSegments = companion.slashSegments(segments);
            StringBuilder sb = new StringBuilder();
            String str2 = "";
            if (TextUtils.isEmpty(slashSegments)) {
                str = "";
            } else {
                str = slashSegments + " - ";
            }
            sb.append(str);
            sb.append("is success : ");
            sb.append(isSuccessful);
            sb.append(" - ");
            sb.append(DefaultFormatPrinter.RECEIVED_TAG);
            sb.append(tookMs);
            sb.append("ms");
            sb.append(DefaultFormatPrinter.DOUBLE_SEPARATOR);
            sb.append(DefaultFormatPrinter.STATUS_CODE_TAG);
            sb.append(code);
            sb.append(" / ");
            sb.append(message);
            sb.append(DefaultFormatPrinter.DOUBLE_SEPARATOR);
            if (!companion.isEmpty(header)) {
                str2 = DefaultFormatPrinter.HEADERS_TAG + DefaultFormatPrinter.LINE_SEPARATOR + companion.dotHeaders(header);
            }
            sb.append(str2);
            String sb2 = sb.toString();
            String[] strArr = new String[1];
            String str3 = DefaultFormatPrinter.LINE_SEPARATOR;
            if (str3 == null) {
                Intrinsics.throwNpe();
            }
            strArr[0] = str3;
            Object[] array = StringsKt.split$default((CharSequence) sb2, strArr, false, 0, 6, (Object) null).toArray(new String[0]);
            if (array != null) {
                return (String[]) array;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }

        private final String slashSegments(List<String> segments) {
            StringBuilder sb = new StringBuilder();
            for (String str : segments) {
                sb.append("/");
                sb.append(str);
            }
            String sb2 = sb.toString();
            Intrinsics.checkExpressionValueIsNotNull(sb2, "segmentString.toString()");
            return sb2;
        }

        private final String dotHeaders(String header) {
            String str;
            String str2 = header;
            String[] strArr = new String[1];
            String str3 = DefaultFormatPrinter.LINE_SEPARATOR;
            if (str3 == null) {
                Intrinsics.throwNpe();
            }
            int i = 0;
            strArr[0] = str3;
            Object[] array = StringsKt.split$default((CharSequence) str2, strArr, false, 0, 6, (Object) null).toArray(new String[0]);
            if (array != null) {
                String[] strArr2 = (String[]) array;
                StringBuilder sb = new StringBuilder();
                if (strArr2.length > 1) {
                    int length = strArr2.length;
                    while (i < length) {
                        if (i == 0) {
                            str = DefaultFormatPrinter.CORNER_UP;
                        } else {
                            str = i == strArr2.length - 1 ? DefaultFormatPrinter.CORNER_BOTTOM : DefaultFormatPrinter.CENTER_LINE;
                        }
                        sb.append(str);
                        sb.append(strArr2[i]);
                        sb.append("\n");
                        i++;
                    }
                } else {
                    int length2 = strArr2.length;
                    while (i < length2) {
                        String str4 = strArr2[i];
                        sb.append("─ ");
                        sb.append(str4);
                        sb.append("\n");
                        i++;
                    }
                }
                String sb2 = sb.toString();
                Intrinsics.checkExpressionValueIsNotNull(sb2, "builder.toString()");
                return sb2;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }

    static {
        String str = LINE_SEPARATOR;
        OMITTED_RESPONSE = new String[]{str, "Omitted response body"};
        OMITTED_REQUEST = new String[]{str, "Omitted request body"};
        ARMS = new String[]{"-A-", "-R-", "-M-", "-S-"};
        last = new ThreadLocal<Integer>() { // from class: com.pudutech.pd_network.interceptor.logging.DefaultFormatPrinter$Companion$last$1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.lang.ThreadLocal
            public Integer initialValue() {
                return 0;
            }
        };
    }
}
