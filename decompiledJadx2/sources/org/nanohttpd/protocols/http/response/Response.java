package org.nanohttpd.protocols.http.response;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.p022h2.stream.utils.StreamUtil;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.io.IOUtils;
import org.nanohttpd.protocols.http.NanoHTTPD;
import org.nanohttpd.protocols.http.content.ContentType;
import org.nanohttpd.protocols.http.request.Method;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class Response implements Closeable {
    private boolean chunkedTransfer;
    private long contentLength;
    private List<String> cookieHeaders;
    private InputStream data;
    private boolean keepAlive;
    private String mimeType;
    private Method requestMethod;
    private IStatus status;
    private final Map<String, String> header = new HashMap<String, String>() { // from class: org.nanohttpd.protocols.http.response.Response.1
        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public String put(String str, String str2) {
            Response.this.lowerCaseHeader.put(str == null ? str : str.toLowerCase(), str2);
            return (String) super.put((C88891) str, str2);
        }
    };
    private final Map<String, String> lowerCaseHeader = new HashMap();
    private GzipUsage gzipUsage = GzipUsage.DEFAULT;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public enum GzipUsage {
        DEFAULT,
        ALWAYS,
        NEVER
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Response(IStatus iStatus, String str, InputStream inputStream, long j) {
        this.status = iStatus;
        this.mimeType = str;
        if (inputStream == null) {
            this.data = new ByteArrayInputStream(new byte[0]);
            this.contentLength = 0L;
        } else {
            this.data = inputStream;
            this.contentLength = j;
        }
        this.chunkedTransfer = this.contentLength < 0;
        this.keepAlive = true;
        this.cookieHeaders = new ArrayList(10);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this.data;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    public void addCookieHeader(String str) {
        this.cookieHeaders.add(str);
    }

    public List<String> getCookieHeaders() {
        return this.cookieHeaders;
    }

    public void addHeader(String str, String str2) {
        this.header.put(str, str2);
    }

    public void closeConnection(boolean z) {
        if (z) {
            this.header.put("connection", "close");
        } else {
            this.header.remove("connection");
        }
    }

    public boolean isCloseConnection() {
        return "close".equals(getHeader("connection"));
    }

    public InputStream getData() {
        return this.data;
    }

    public String getHeader(String str) {
        return this.lowerCaseHeader.get(str.toLowerCase());
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public Method getRequestMethod() {
        return this.requestMethod;
    }

    public IStatus getStatus() {
        return this.status;
    }

    public void setKeepAlive(boolean z) {
        this.keepAlive = z;
    }

    public void send(OutputStream outputStream) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            if (this.status == null) {
                throw new Error("sendResponse(): Status can't be null.");
            }
            PrintWriter printWriter = new PrintWriter((Writer) new BufferedWriter(new OutputStreamWriter(outputStream, new ContentType(this.mimeType).getEncoding())), false);
            printWriter.append("HTTP/1.1 ").append((CharSequence) this.status.getDescription()).append(" \r\n");
            if (this.mimeType != null) {
                printHeader(printWriter, "Content-Type", this.mimeType);
            }
            if (getHeader(TmpConstant.TYPE_VALUE_DATE) == null) {
                printHeader(printWriter, "Date", simpleDateFormat.format(new Date()));
            }
            for (Map.Entry<String, String> entry : this.header.entrySet()) {
                printHeader(printWriter, entry.getKey(), entry.getValue());
            }
            Iterator<String> it = this.cookieHeaders.iterator();
            while (it.hasNext()) {
                printHeader(printWriter, "Set-Cookie", it.next());
            }
            if (getHeader("connection") == null) {
                printHeader(printWriter, "Connection", this.keepAlive ? "keep-alive" : "close");
            }
            if (getHeader(StreamUtil.CONTENT_LENGTH) != null) {
                setUseGzip(false);
            }
            if (useGzipWhenAccepted()) {
                printHeader(printWriter, "Content-Encoding", "gzip");
                setChunkedTransfer(true);
            }
            long j = this.data != null ? this.contentLength : 0L;
            if (this.requestMethod != Method.HEAD && this.chunkedTransfer) {
                printHeader(printWriter, "Transfer-Encoding", "chunked");
            } else if (!useGzipWhenAccepted()) {
                j = sendContentLengthHeaderIfNotAlreadyPresent(printWriter, j);
            }
            printWriter.append(IOUtils.LINE_SEPARATOR_WINDOWS);
            printWriter.flush();
            sendBodyWithCorrectTransferAndEncoding(outputStream, j);
            outputStream.flush();
            NanoHTTPD.safeClose(this.data);
        } catch (IOException e) {
            NanoHTTPD.LOG.log(Level.SEVERE, "Could not send response to the client", (Throwable) e);
        }
    }

    protected void printHeader(PrintWriter printWriter, String str, String str2) {
        printWriter.append((CharSequence) str).append(": ").append((CharSequence) str2).append(IOUtils.LINE_SEPARATOR_WINDOWS);
    }

    protected long sendContentLengthHeaderIfNotAlreadyPresent(PrintWriter printWriter, long j) {
        String header = getHeader(StreamUtil.CONTENT_LENGTH);
        if (header != null) {
            try {
                return Long.parseLong(header);
            } catch (NumberFormatException unused) {
                NanoHTTPD.LOG.severe("content-length was no number " + header);
                return j;
            }
        }
        printWriter.print("Content-Length: " + j + IOUtils.LINE_SEPARATOR_WINDOWS);
        return j;
    }

    private void sendBodyWithCorrectTransferAndEncoding(OutputStream outputStream, long j) throws IOException {
        if (this.requestMethod != Method.HEAD && this.chunkedTransfer) {
            ChunkedOutputStream chunkedOutputStream = new ChunkedOutputStream(outputStream);
            sendBodyWithCorrectEncoding(chunkedOutputStream, -1L);
            try {
                chunkedOutputStream.finish();
                return;
            } catch (Exception unused) {
                InputStream inputStream = this.data;
                if (inputStream != null) {
                    inputStream.close();
                    return;
                }
                return;
            }
        }
        sendBodyWithCorrectEncoding(outputStream, j);
    }

    private void sendBodyWithCorrectEncoding(OutputStream outputStream, long j) throws IOException {
        if (useGzipWhenAccepted()) {
            GZIPOutputStream gZIPOutputStream = null;
            try {
                gZIPOutputStream = new GZIPOutputStream(outputStream);
            } catch (Exception unused) {
                InputStream inputStream = this.data;
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            if (gZIPOutputStream != null) {
                sendBody(gZIPOutputStream, -1L);
                gZIPOutputStream.finish();
                return;
            }
            return;
        }
        sendBody(outputStream, j);
    }

    private void sendBody(OutputStream outputStream, long j) throws IOException {
        byte[] bArr = new byte[(int) 16384];
        boolean z = j == -1;
        while (true) {
            if (j <= 0 && !z) {
                return;
            }
            int read = this.data.read(bArr, 0, (int) (z ? 16384L : Math.min(j, 16384L)));
            if (read <= 0) {
                return;
            }
            try {
                outputStream.write(bArr, 0, read);
            } catch (Exception unused) {
                InputStream inputStream = this.data;
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            if (!z) {
                j -= read;
            }
        }
    }

    public void setChunkedTransfer(boolean z) {
        this.chunkedTransfer = z;
    }

    public void setData(InputStream inputStream) {
        this.data = inputStream;
    }

    public void setMimeType(String str) {
        this.mimeType = str;
    }

    public void setRequestMethod(Method method) {
        this.requestMethod = method;
    }

    public void setStatus(IStatus iStatus) {
        this.status = iStatus;
    }

    public static Response newChunkedResponse(IStatus iStatus, String str, InputStream inputStream) {
        return new Response(iStatus, str, inputStream, -1L);
    }

    public static Response newFixedLengthResponse(IStatus iStatus, String str, byte[] bArr) {
        return newFixedLengthResponse(iStatus, str, new ByteArrayInputStream(bArr), bArr.length);
    }

    public static Response newFixedLengthResponse(IStatus iStatus, String str, InputStream inputStream, long j) {
        return new Response(iStatus, str, inputStream, j);
    }

    public static Response newFixedLengthResponse(IStatus iStatus, String str, String str2) {
        byte[] bArr;
        ContentType contentType = new ContentType(str);
        if (str2 == null) {
            return newFixedLengthResponse(iStatus, str, new ByteArrayInputStream(new byte[0]), 0L);
        }
        try {
            if (!Charset.forName(contentType.getEncoding()).newEncoder().canEncode(str2)) {
                contentType = contentType.tryUTF8();
            }
            bArr = str2.getBytes(contentType.getEncoding());
        } catch (UnsupportedEncodingException e) {
            NanoHTTPD.LOG.log(Level.SEVERE, "encoding problem, responding nothing", (Throwable) e);
            bArr = new byte[0];
        }
        return newFixedLengthResponse(iStatus, contentType.getContentTypeHeader(), new ByteArrayInputStream(bArr), bArr.length);
    }

    public static Response newFixedLengthResponse(String str) {
        return newFixedLengthResponse(Status.OK, "text/html", str);
    }

    public Response setUseGzip(boolean z) {
        this.gzipUsage = z ? GzipUsage.ALWAYS : GzipUsage.NEVER;
        return this;
    }

    public boolean useGzipWhenAccepted() {
        return this.gzipUsage == GzipUsage.DEFAULT ? getMimeType() != null && (getMimeType().toLowerCase().contains("text/") || getMimeType().toLowerCase().contains("/json")) : this.gzipUsage == GzipUsage.ALWAYS;
    }
}
