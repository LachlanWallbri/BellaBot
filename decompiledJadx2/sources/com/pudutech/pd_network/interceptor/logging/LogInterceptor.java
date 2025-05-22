package com.pudutech.pd_network.interceptor.logging;

import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.utils.UrlEncoderUtils;
import com.pudutech.pd_network.utils.ZipHelper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import org.apache.commons.codec.language.bm.Rule;

/* compiled from: LogInterceptor.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u001a2\u00020\u0001:\u0002\u001a\u001bB\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0011\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J&\u0010\u000f\u001a\u0004\u0018\u00010\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\"\u0010\u0015\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/pd_network/interceptor/logging/LogInterceptor;", "Lokhttp3/Interceptor;", "()V", "printLevel", "Lcom/pudutech/pd_network/interceptor/logging/LogInterceptor$Level;", "(Lcom/pudutech/pd_network/interceptor/logging/LogInterceptor$Level;)V", "mPrinter", "Lcom/pudutech/pd_network/interceptor/logging/FormatPrinter;", "getResponse", "", "response", "Lokhttp3/Response;", "intercept", "chain", "Lokhttp3/Interceptor$Chain;", "parseContent", "responseBody", "Lokhttp3/ResponseBody;", "encoding", "clone", "Lokio/Buffer;", "printResult", "request", "Lokhttp3/Request;", "logResponse", "", "Companion", "Level", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class LogInterceptor implements Interceptor {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "LogInterceptor";
    private final FormatPrinter mPrinter = new DefaultFormatPrinter();
    private final Level printLevel = Level.ALL;

    /* compiled from: LogInterceptor.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/pd_network/interceptor/logging/LogInterceptor$Level;", "", "(Ljava/lang/String;I)V", "NONE", "REQUEST", "RESPONSE", Rule.ALL, "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum Level {
        NONE,
        REQUEST,
        RESPONSE,
        ALL
    }

    public LogInterceptor() {
    }

    public LogInterceptor(Level level) {
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        String headers;
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        Request request = chain.request();
        boolean z = false;
        if (this.printLevel == Level.ALL || (this.printLevel != Level.NONE && this.printLevel == Level.REQUEST)) {
            if (request.body() != null) {
                Companion companion = INSTANCE;
                RequestBody body = request.body();
                if (body == null) {
                    Intrinsics.throwNpe();
                }
                if (companion.isParseable(body.get$contentType())) {
                    this.mPrinter.printJsonRequest(request, INSTANCE.parseParams(request));
                }
            }
            this.mPrinter.printFileRequest(request);
        }
        if (this.printLevel == Level.ALL || (this.printLevel != Level.NONE && this.printLevel == Level.REQUEST)) {
            z = true;
        }
        long nanoTime = z ? System.nanoTime() : 0L;
        try {
            Response proceed = chain.proceed(request);
            long nanoTime2 = z ? System.nanoTime() : 0L;
            ResponseBody body2 = proceed.body();
            String str = (String) null;
            if (body2 != null && INSTANCE.isParseable(body2.get$contentType())) {
                str = printResult(request, proceed, z);
            }
            String str2 = str;
            if (z) {
                List<String> encodedPathSegments = request.url().encodedPathSegments();
                if (proceed.networkResponse() == null) {
                    headers = proceed.headers().toString();
                } else {
                    Response networkResponse = proceed.networkResponse();
                    if (networkResponse == null) {
                        Intrinsics.throwNpe();
                    }
                    headers = networkResponse.request().headers().toString();
                }
                String str3 = headers;
                int code = proceed.code();
                boolean isSuccessful = proceed.isSuccessful();
                String message = proceed.message();
                String httpUrl = proceed.request().url().toString();
                if (body2 != null && INSTANCE.isParseable(body2.get$contentType())) {
                    this.mPrinter.printJsonResponse(TimeUnit.NANOSECONDS.toMillis(nanoTime2 - nanoTime), isSuccessful, code, str3, body2.get$contentType(), str2, encodedPathSegments, message, httpUrl);
                } else {
                    this.mPrinter.printFileResponse(TimeUnit.NANOSECONDS.toMillis(nanoTime2 - nanoTime), isSuccessful, code, str3, encodedPathSegments, message, httpUrl);
                }
            }
            return proceed;
        } catch (Exception e) {
            NetWorkLog.INSTANCE.mo3279e(TAG, "response error  " + e.getMessage());
            throw e;
        }
    }

    private final String printResult(Request request, Response response, boolean logResponse) throws IOException {
        try {
            ResponseBody body = response.newBuilder().build().body();
            if (body == null) {
                Intrinsics.throwNpe();
            }
            BufferedSource bufferedSource = body.get$this_asResponseBody();
            bufferedSource.request(Long.MAX_VALUE);
            return parseContent(body, response.headers().get("Content-Encoding"), bufferedSource.buffer().clone());
        } catch (IOException e) {
            e.printStackTrace();
            NetWorkLog.INSTANCE.mo3279e(TAG, "request{\"error\":\"" + e.getMessage() + "\"}");
            return Unit.INSTANCE.toString();
        }
    }

    private final String parseContent(ResponseBody responseBody, String encoding, Buffer clone) {
        Charset charset = Charset.forName("UTF-8");
        if (responseBody == null) {
            Intrinsics.throwNpe();
        }
        MediaType mediaType = responseBody.get$contentType();
        if (mediaType != null) {
            charset = mediaType.charset(charset);
        }
        if (StringsKt.equals("gzip", encoding, true)) {
            return ZipHelper.INSTANCE.decompressForGzip(clone.readByteArray(), INSTANCE.convertCharset(charset));
        }
        if (StringsKt.equals("zlib", encoding, true)) {
            return ZipHelper.INSTANCE.decompressToStringForZlib(clone.readByteArray(), INSTANCE.convertCharset(charset));
        }
        Intrinsics.checkExpressionValueIsNotNull(charset, "charset");
        return clone.readString(charset);
    }

    private final String getResponse(Response response) {
        ResponseBody body = response.newBuilder().build().body();
        if (body == null) {
            Intrinsics.throwNpe();
        }
        BufferedSource bufferedSource = body.get$this_asResponseBody();
        Charset charset = Charset.forName("UTF-8");
        MediaType mediaType = body.get$contentType();
        if (mediaType != null) {
            charset = mediaType.charset(charset);
        }
        bufferedSource.request(Long.MAX_VALUE);
        Buffer buffer = bufferedSource.buffer();
        response.headers().get("Content-Encoding");
        Buffer clone = buffer.clone();
        Intrinsics.checkExpressionValueIsNotNull(charset, "charset");
        String readString = clone.readString(charset);
        NetWorkLog.INSTANCE.mo3278d(TAG, "getResponse " + readString);
        return readString;
    }

    /* compiled from: LogInterceptor.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007J\u0010\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010\f\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010\r\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010\u000e\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010\u000f\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010\u0010\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010\u0011\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u000e\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/pd_network/interceptor/logging/LogInterceptor$Companion;", "", "()V", "TAG", "", "convertCharset", "charset", "Ljava/nio/charset/Charset;", "isForm", "", "mediaType", "Lokhttp3/MediaType;", "isHtml", "isJson", "isParseable", "isPlain", "isText", "isXml", "parseParams", "request", "Lokhttp3/Request;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String parseParams(Request request) throws UnsupportedEncodingException {
            Intrinsics.checkParameterIsNotNull(request, "request");
            try {
                RequestBody body = request.newBuilder().build().body();
                if (body == null) {
                    return "";
                }
                Buffer buffer = new Buffer();
                body.writeTo(buffer);
                Charset charset = Charset.forName("UTF-8");
                MediaType mediaType = body.get$contentType();
                if (mediaType != null) {
                    charset = mediaType.charset(charset);
                }
                Intrinsics.checkExpressionValueIsNotNull(charset, "charset");
                String readString = buffer.readString(charset);
                if (!UrlEncoderUtils.INSTANCE.hasUrlEncoded(readString)) {
                    return readString;
                }
                String decode = URLDecoder.decode(readString, convertCharset(charset));
                Intrinsics.checkExpressionValueIsNotNull(decode, "URLDecoder.decode(\n     …et)\n                    )");
                return decode;
            } catch (IOException e) {
                e.printStackTrace();
                return "{\"error\":\"" + e.getMessage() + "\"}";
            }
        }

        public final boolean isParseable(MediaType mediaType) {
            if ((mediaType != null ? mediaType.type() : null) == null) {
                return false;
            }
            Companion companion = this;
            return companion.isText(mediaType) || companion.isPlain(mediaType) || companion.isJson(mediaType) || companion.isForm(mediaType) || companion.isHtml(mediaType) || companion.isXml(mediaType);
        }

        public final boolean isText(MediaType mediaType) {
            if ((mediaType != null ? mediaType.type() : null) == null) {
                return false;
            }
            return Intrinsics.areEqual("text", mediaType.type());
        }

        public final boolean isPlain(MediaType mediaType) {
            if ((mediaType != null ? mediaType.subtype() : null) == null) {
                return false;
            }
            String subtype = mediaType.subtype();
            if (subtype == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String lowerCase = subtype.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
            if (lowerCase != null) {
                return lowerCase.contentEquals(r1);
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }

        public final boolean isJson(MediaType mediaType) {
            if ((mediaType != null ? mediaType.subtype() : null) == null) {
                return false;
            }
            String subtype = mediaType.subtype();
            Locale locale = Locale.getDefault();
            Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
            if (subtype == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String lowerCase = subtype.toLowerCase(locale);
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            return StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) "json", false, 2, (Object) null);
        }

        public final boolean isForm(MediaType mediaType) {
            if ((mediaType != null ? mediaType.subtype() : null) == null) {
                return false;
            }
            String subtype = mediaType.subtype();
            Locale locale = Locale.getDefault();
            Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
            if (subtype == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String lowerCase = subtype.toLowerCase(locale);
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            return StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) "x-www-form-urlencoded", false, 2, (Object) null);
        }

        public final boolean isHtml(MediaType mediaType) {
            if ((mediaType != null ? mediaType.subtype() : null) == null) {
                return false;
            }
            String subtype = mediaType.subtype();
            Locale locale = Locale.getDefault();
            Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
            if (subtype == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String lowerCase = subtype.toLowerCase(locale);
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            return StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) "html", false, 2, (Object) null);
        }

        public final boolean isXml(MediaType mediaType) {
            if ((mediaType != null ? mediaType.subtype() : null) == null) {
                return false;
            }
            String subtype = mediaType.subtype();
            Locale locale = Locale.getDefault();
            Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
            if (subtype == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String lowerCase = subtype.toLowerCase(locale);
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            return StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) "html", false, 2, (Object) null);
        }

        public final String convertCharset(Charset charset) {
            String valueOf = String.valueOf(charset);
            int indexOf$default = StringsKt.indexOf$default((CharSequence) valueOf, "[", 0, false, 6, (Object) null);
            if (indexOf$default == -1) {
                return valueOf;
            }
            int i = indexOf$default + 1;
            int length = valueOf.length() - 1;
            if (valueOf == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String substring = valueOf.substring(i, length);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return substring;
        }
    }
}
