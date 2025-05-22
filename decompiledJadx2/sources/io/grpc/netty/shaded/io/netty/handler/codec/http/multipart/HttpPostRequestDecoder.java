package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import io.grpc.netty.shaded.io.netty.handler.codec.DecoderException;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpConstants;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderValues;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.nio.charset.Charset;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class HttpPostRequestDecoder implements InterfaceHttpPostRequestDecoder {
    static final int DEFAULT_DISCARD_THRESHOLD = 10485760;
    private final InterfaceHttpPostRequestDecoder decoder;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class EndOfDataDecoderException extends DecoderException {
        private static final long serialVersionUID = 1336267941020800769L;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    protected enum MultiPartStatus {
        NOTSTARTED,
        PREAMBLE,
        HEADERDELIMITER,
        DISPOSITION,
        FIELD,
        FILEUPLOAD,
        MIXEDPREAMBLE,
        MIXEDDELIMITER,
        MIXEDDISPOSITION,
        MIXEDFILEUPLOAD,
        MIXEDCLOSEDELIMITER,
        CLOSEDELIMITER,
        PREEPILOGUE,
        EPILOGUE
    }

    public HttpPostRequestDecoder(HttpRequest httpRequest) {
        this(new DefaultHttpDataFactory(16384L), httpRequest, HttpConstants.DEFAULT_CHARSET);
    }

    public HttpPostRequestDecoder(HttpDataFactory httpDataFactory, HttpRequest httpRequest) {
        this(httpDataFactory, httpRequest, HttpConstants.DEFAULT_CHARSET);
    }

    public HttpPostRequestDecoder(HttpDataFactory httpDataFactory, HttpRequest httpRequest, Charset charset) {
        ObjectUtil.checkNotNull(httpDataFactory, "factory");
        ObjectUtil.checkNotNull(httpRequest, "request");
        ObjectUtil.checkNotNull(charset, "charset");
        if (isMultipart(httpRequest)) {
            this.decoder = new HttpPostMultipartRequestDecoder(httpDataFactory, httpRequest, charset);
        } else {
            this.decoder = new HttpPostStandardRequestDecoder(httpDataFactory, httpRequest, charset);
        }
    }

    public static boolean isMultipart(HttpRequest httpRequest) {
        String str = httpRequest.headers().get(HttpHeaderNames.CONTENT_TYPE);
        return (str == null || !str.startsWith(HttpHeaderValues.MULTIPART_FORM_DATA.toString()) || getMultipartDataBoundary(str) == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String[] getMultipartDataBoundary(String str) {
        char c;
        char c2;
        String substringAfter;
        String[] splitHeaderContentType = splitHeaderContentType(str);
        String asciiString = HttpHeaderValues.MULTIPART_FORM_DATA.toString();
        if (splitHeaderContentType[0].regionMatches(true, 0, asciiString, 0, asciiString.length())) {
            String asciiString2 = HttpHeaderValues.BOUNDARY.toString();
            if (splitHeaderContentType[1].regionMatches(true, 0, asciiString2, 0, asciiString2.length())) {
                c2 = 1;
                c = 2;
            } else if (splitHeaderContentType[2].regionMatches(true, 0, asciiString2, 0, asciiString2.length())) {
                c = 1;
                c2 = 2;
            }
            String substringAfter2 = StringUtil.substringAfter(splitHeaderContentType[c2], '=');
            if (substringAfter2 == null) {
                throw new ErrorDataDecoderException("Needs a boundary value");
            }
            if (substringAfter2.charAt(0) == '\"') {
                String trim = substringAfter2.trim();
                int length = trim.length() - 1;
                if (trim.charAt(length) == '\"') {
                    substringAfter2 = trim.substring(1, length);
                }
            }
            String asciiString3 = HttpHeaderValues.CHARSET.toString();
            if (splitHeaderContentType[c].regionMatches(true, 0, asciiString3, 0, asciiString3.length()) && (substringAfter = StringUtil.substringAfter(splitHeaderContentType[c], '=')) != null) {
                return new String[]{"--" + substringAfter2, substringAfter};
            }
            return new String[]{"--" + substringAfter2};
        }
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public boolean isMultipart() {
        return this.decoder.isMultipart();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public void setDiscardThreshold(int i) {
        this.decoder.setDiscardThreshold(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public int getDiscardThreshold() {
        return this.decoder.getDiscardThreshold();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public List<InterfaceHttpData> getBodyHttpDatas() {
        return this.decoder.getBodyHttpDatas();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public List<InterfaceHttpData> getBodyHttpDatas(String str) {
        return this.decoder.getBodyHttpDatas(str);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public InterfaceHttpData getBodyHttpData(String str) {
        return this.decoder.getBodyHttpData(str);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public InterfaceHttpPostRequestDecoder offer(HttpContent httpContent) {
        return this.decoder.offer(httpContent);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public boolean hasNext() {
        return this.decoder.hasNext();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public InterfaceHttpData next() {
        return this.decoder.next();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public InterfaceHttpData currentPartialHttpData() {
        return this.decoder.currentPartialHttpData();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public void destroy() {
        this.decoder.destroy();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public void cleanFiles() {
        this.decoder.cleanFiles();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public void removeHttpDataFromClean(InterfaceHttpData interfaceHttpData) {
        this.decoder.removeHttpDataFromClean(interfaceHttpData);
    }

    private static String[] splitHeaderContentType(String str) {
        int findNonWhitespace = HttpPostBodyUtil.findNonWhitespace(str, 0);
        int indexOf = str.indexOf(59);
        if (indexOf == -1) {
            return new String[]{str, "", ""};
        }
        int findNonWhitespace2 = HttpPostBodyUtil.findNonWhitespace(str, indexOf + 1);
        if (str.charAt(indexOf - 1) == ' ') {
            indexOf--;
        }
        int indexOf2 = str.indexOf(59, findNonWhitespace2);
        if (indexOf2 == -1) {
            return new String[]{str.substring(findNonWhitespace, indexOf), str.substring(findNonWhitespace2, HttpPostBodyUtil.findEndOfString(str)), ""};
        }
        int findNonWhitespace3 = HttpPostBodyUtil.findNonWhitespace(str, indexOf2 + 1);
        if (str.charAt(indexOf2 - 1) == ' ') {
            indexOf2--;
        }
        return new String[]{str.substring(findNonWhitespace, indexOf), str.substring(findNonWhitespace2, indexOf2), str.substring(findNonWhitespace3, HttpPostBodyUtil.findEndOfString(str))};
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class NotEnoughDataDecoderException extends DecoderException {
        private static final long serialVersionUID = -7846841864603865638L;

        public NotEnoughDataDecoderException() {
        }

        public NotEnoughDataDecoderException(String str) {
            super(str);
        }

        public NotEnoughDataDecoderException(Throwable th) {
            super(th);
        }

        public NotEnoughDataDecoderException(String str, Throwable th) {
            super(str, th);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class ErrorDataDecoderException extends DecoderException {
        private static final long serialVersionUID = 5020247425493164465L;

        public ErrorDataDecoderException() {
        }

        public ErrorDataDecoderException(String str) {
            super(str);
        }

        public ErrorDataDecoderException(Throwable th) {
            super(th);
        }

        public ErrorDataDecoderException(String str, Throwable th) {
            super(str, th);
        }
    }
}
