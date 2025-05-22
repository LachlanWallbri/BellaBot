package org.nanohttpd.protocols.http;

import com.aliyun.alink.p022h2.stream.utils.StreamUtil;
import com.iflytek.aiui.AIUIConstant;
import io.grpc.internal.GrpcUtil;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.regex.Matcher;
import javax.net.ssl.SSLException;
import org.nanohttpd.protocols.http.NanoHTTPD;
import org.nanohttpd.protocols.http.content.ContentType;
import org.nanohttpd.protocols.http.content.CookieHandler;
import org.nanohttpd.protocols.http.request.Method;
import org.nanohttpd.protocols.http.response.Response;
import org.nanohttpd.protocols.http.response.Status;
import org.nanohttpd.protocols.http.tempfiles.ITempFile;
import org.nanohttpd.protocols.http.tempfiles.ITempFileManager;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class HTTPSession implements IHTTPSession {
    public static final int BUFSIZE = 8192;
    public static final int MAX_HEADER_SIZE = 1024;
    private static final int MEMORY_STORE_LIMIT = 1024;
    public static final String POST_DATA = "postData";
    private static final int REQUEST_BUFFER_LEN = 512;
    private CookieHandler cookies;
    private Map<String, String> headers;
    private final NanoHTTPD httpd;
    private final BufferedInputStream inputStream;
    private Method method;
    private final OutputStream outputStream;
    private Map<String, List<String>> parms;
    private String protocolVersion;
    private String queryParameterString;
    private String remoteIp;
    private int rlen;
    private int splitbyte;
    private final ITempFileManager tempFileManager;
    private String uri;

    public HTTPSession(NanoHTTPD nanoHTTPD, ITempFileManager iTempFileManager, InputStream inputStream, OutputStream outputStream) {
        this.httpd = nanoHTTPD;
        this.tempFileManager = iTempFileManager;
        this.inputStream = new BufferedInputStream(inputStream, 8192);
        this.outputStream = outputStream;
    }

    public HTTPSession(NanoHTTPD nanoHTTPD, ITempFileManager iTempFileManager, InputStream inputStream, OutputStream outputStream, InetAddress inetAddress) {
        this.httpd = nanoHTTPD;
        this.tempFileManager = iTempFileManager;
        this.inputStream = new BufferedInputStream(inputStream, 8192);
        this.outputStream = outputStream;
        this.remoteIp = (inetAddress.isLoopbackAddress() || inetAddress.isAnyLocalAddress()) ? "127.0.0.1" : inetAddress.getHostAddress().toString();
        this.headers = new HashMap();
    }

    private void decodeHeader(BufferedReader bufferedReader, Map<String, String> map, Map<String, List<String>> map2, Map<String, String> map3) throws NanoHTTPD.ResponseException {
        String decodePercent;
        try {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(readLine);
            if (!stringTokenizer.hasMoreTokens()) {
                throw new NanoHTTPD.ResponseException(Status.BAD_REQUEST, "BAD REQUEST: Syntax error. Usage: GET /example/file.html");
            }
            map.put("method", stringTokenizer.nextToken());
            if (!stringTokenizer.hasMoreTokens()) {
                throw new NanoHTTPD.ResponseException(Status.BAD_REQUEST, "BAD REQUEST: Missing URI. Usage: GET /example/file.html");
            }
            String nextToken = stringTokenizer.nextToken();
            int indexOf = nextToken.indexOf(63);
            if (indexOf >= 0) {
                decodeParms(nextToken.substring(indexOf + 1), map2);
                decodePercent = NanoHTTPD.decodePercent(nextToken.substring(0, indexOf));
            } else {
                decodePercent = NanoHTTPD.decodePercent(nextToken);
            }
            if (stringTokenizer.hasMoreTokens()) {
                this.protocolVersion = stringTokenizer.nextToken();
            } else {
                this.protocolVersion = "HTTP/1.1";
                NanoHTTPD.LOG.log(Level.FINE, "no protocol version specified, strange. Assuming HTTP/1.1.");
            }
            String readLine2 = bufferedReader.readLine();
            while (readLine2 != null && !readLine2.trim().isEmpty()) {
                int indexOf2 = readLine2.indexOf(58);
                if (indexOf2 >= 0) {
                    map3.put(readLine2.substring(0, indexOf2).trim().toLowerCase(Locale.US), readLine2.substring(indexOf2 + 1).trim());
                }
                readLine2 = bufferedReader.readLine();
            }
            map.put("uri", decodePercent);
        } catch (IOException e) {
            throw new NanoHTTPD.ResponseException(Status.INTERNAL_ERROR, "SERVER INTERNAL ERROR: IOException: " + e.getMessage(), e);
        }
    }

    private void decodeMultipartFormData(ContentType contentType, ByteBuffer byteBuffer, Map<String, List<String>> map, Map<String, String> map2) throws NanoHTTPD.ResponseException {
        int i;
        String str;
        try {
            int[] boundaryPositions = getBoundaryPositions(byteBuffer, contentType.getBoundary().getBytes());
            int i2 = 2;
            if (boundaryPositions.length < 2) {
                throw new NanoHTTPD.ResponseException(Status.BAD_REQUEST, "BAD REQUEST: Content type is multipart/form-data but contains less than two boundary strings.");
            }
            int i3 = 1024;
            byte[] bArr = new byte[1024];
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (true) {
                int i7 = 1;
                if (i5 >= boundaryPositions.length - 1) {
                    return;
                }
                byteBuffer.position(boundaryPositions[i5]);
                int remaining = byteBuffer.remaining() < i3 ? byteBuffer.remaining() : i3;
                byteBuffer.get(bArr, i4, remaining);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr, i4, remaining), Charset.forName(contentType.getEncoding())), remaining);
                String readLine = bufferedReader.readLine();
                if (readLine == null || !readLine.contains(contentType.getBoundary())) {
                    break;
                }
                String readLine2 = bufferedReader.readLine();
                String str2 = null;
                int i8 = i6;
                String str3 = null;
                String str4 = null;
                int i9 = i2;
                while (readLine2 != null && readLine2.trim().length() > 0) {
                    Matcher matcher = NanoHTTPD.CONTENT_DISPOSITION_PATTERN.matcher(readLine2);
                    if (matcher.matches()) {
                        Matcher matcher2 = NanoHTTPD.CONTENT_DISPOSITION_ATTRIBUTE_PATTERN.matcher(matcher.group(i2));
                        while (matcher2.find()) {
                            String group = matcher2.group(i7);
                            if ("name".equalsIgnoreCase(group)) {
                                str = matcher2.group(2);
                            } else {
                                if ("filename".equalsIgnoreCase(group)) {
                                    String group2 = matcher2.group(2);
                                    if (!group2.isEmpty()) {
                                        if (i8 > 0) {
                                            str = str2 + String.valueOf(i8);
                                            str3 = group2;
                                            i8++;
                                        } else {
                                            i8++;
                                        }
                                    }
                                    str3 = group2;
                                }
                                i7 = 1;
                            }
                            str2 = str;
                            i7 = 1;
                        }
                    }
                    Matcher matcher3 = NanoHTTPD.CONTENT_TYPE_PATTERN.matcher(readLine2);
                    if (matcher3.matches()) {
                        i = 2;
                        str4 = matcher3.group(2).trim();
                    } else {
                        i = 2;
                    }
                    readLine2 = bufferedReader.readLine();
                    i9++;
                    i2 = i;
                    i7 = 1;
                }
                int i10 = i2;
                int i11 = 0;
                while (true) {
                    int i12 = i9 - 1;
                    if (i9 <= 0) {
                        break;
                    }
                    i11 = scipOverNewLine(bArr, i11);
                    i9 = i12;
                }
                if (i11 >= remaining - 4) {
                    throw new NanoHTTPD.ResponseException(Status.INTERNAL_ERROR, "Multipart header size exceeds MAX_HEADER_SIZE.");
                }
                int i13 = boundaryPositions[i5] + i11;
                i5++;
                int i14 = boundaryPositions[i5] - 4;
                byteBuffer.position(i13);
                List<String> list = map.get(str2);
                if (list == null) {
                    list = new ArrayList<>();
                    map.put(str2, list);
                }
                if (str4 == null) {
                    byte[] bArr2 = new byte[i14 - i13];
                    byteBuffer.get(bArr2);
                    list.add(new String(bArr2, contentType.getEncoding()));
                } else {
                    String saveTmpFile = saveTmpFile(byteBuffer, i13, i14 - i13, str3);
                    if (!map2.containsKey(str2)) {
                        map2.put(str2, saveTmpFile);
                    } else {
                        int i15 = i10;
                        while (true) {
                            if (!map2.containsKey(str2 + i15)) {
                                break;
                            } else {
                                i15++;
                            }
                        }
                        map2.put(str2 + i15, saveTmpFile);
                    }
                    list.add(str3);
                }
                i2 = i10;
                i6 = i8;
                i3 = 1024;
                i4 = 0;
            }
            throw new NanoHTTPD.ResponseException(Status.BAD_REQUEST, "BAD REQUEST: Content type is multipart/form-data but chunk does not start with boundary.");
        } catch (NanoHTTPD.ResponseException e) {
            throw e;
        } catch (Exception e2) {
            throw new NanoHTTPD.ResponseException(Status.INTERNAL_ERROR, e2.toString());
        }
    }

    private int scipOverNewLine(byte[] bArr, int i) {
        while (bArr[i] != 10) {
            i++;
        }
        return i + 1;
    }

    private void decodeParms(String str, Map<String, List<String>> map) {
        String trim;
        String str2;
        if (str == null) {
            this.queryParameterString = "";
            return;
        }
        this.queryParameterString = str;
        StringTokenizer stringTokenizer = new StringTokenizer(str, "&");
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            int indexOf = nextToken.indexOf(61);
            if (indexOf >= 0) {
                trim = NanoHTTPD.decodePercent(nextToken.substring(0, indexOf)).trim();
                str2 = NanoHTTPD.decodePercent(nextToken.substring(indexOf + 1));
            } else {
                trim = NanoHTTPD.decodePercent(nextToken).trim();
                str2 = "";
            }
            List<String> list = map.get(trim);
            if (list == null) {
                list = new ArrayList<>();
                map.put(trim, list);
            }
            list.add(str2);
        }
    }

    @Override // org.nanohttpd.protocols.http.IHTTPSession
    public void execute() throws IOException {
        byte[] bArr;
        int read;
        Response response = null;
        try {
            try {
                try {
                    try {
                        try {
                            bArr = new byte[8192];
                            this.splitbyte = 0;
                            this.rlen = 0;
                            this.inputStream.mark(8192);
                            try {
                                read = this.inputStream.read(bArr, 0, 8192);
                            } catch (SSLException e) {
                                throw e;
                            } catch (IOException unused) {
                                NanoHTTPD.safeClose(this.inputStream);
                                NanoHTTPD.safeClose(this.outputStream);
                                throw new SocketException("NanoHttpd Shutdown");
                            }
                        } catch (SSLException e2) {
                            Response.newFixedLengthResponse(Status.INTERNAL_ERROR, "text/plain", "SSL PROTOCOL FAILURE: " + e2.getMessage()).send(this.outputStream);
                            NanoHTTPD.safeClose(this.outputStream);
                        } catch (IOException e3) {
                            Response.newFixedLengthResponse(Status.INTERNAL_ERROR, "text/plain", "SERVER INTERNAL ERROR: IOException: " + e3.getMessage()).send(this.outputStream);
                            NanoHTTPD.safeClose(this.outputStream);
                        }
                    } catch (SocketException e4) {
                        throw e4;
                    }
                } catch (NanoHTTPD.ResponseException e5) {
                    Response.newFixedLengthResponse(e5.getStatus(), "text/plain", e5.getMessage()).send(this.outputStream);
                    NanoHTTPD.safeClose(this.outputStream);
                }
                if (read == -1) {
                    NanoHTTPD.safeClose(this.inputStream);
                    NanoHTTPD.safeClose(this.outputStream);
                    throw new SocketException("NanoHttpd Shutdown");
                }
                while (read > 0) {
                    this.rlen += read;
                    this.splitbyte = findHeaderEnd(bArr, this.rlen);
                    if (this.splitbyte > 0) {
                        break;
                    } else {
                        read = this.inputStream.read(bArr, this.rlen, 8192 - this.rlen);
                    }
                }
                if (this.splitbyte < this.rlen) {
                    this.inputStream.reset();
                    this.inputStream.skip(this.splitbyte);
                }
                this.parms = new HashMap();
                if (this.headers == null) {
                    this.headers = new HashMap();
                } else {
                    this.headers.clear();
                }
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr, 0, this.rlen)));
                HashMap hashMap = new HashMap();
                decodeHeader(bufferedReader, hashMap, this.parms, this.headers);
                if (this.remoteIp != null) {
                    this.headers.put("remote-addr", this.remoteIp);
                    this.headers.put("http-client-ip", this.remoteIp);
                }
                this.method = Method.lookup(hashMap.get("method"));
                if (this.method == null) {
                    throw new NanoHTTPD.ResponseException(Status.BAD_REQUEST, "BAD REQUEST: Syntax error. HTTP verb " + hashMap.get("method") + " unhandled.");
                }
                this.uri = hashMap.get("uri");
                this.cookies = new CookieHandler(this.headers);
                String str = this.headers.get("connection");
                boolean z = "HTTP/1.1".equals(this.protocolVersion) && (str == null || !str.matches("(?i).*close.*"));
                response = this.httpd.handle(this);
                if (response == null) {
                    throw new NanoHTTPD.ResponseException(Status.INTERNAL_ERROR, "SERVER INTERNAL ERROR: Serve() returned a null response.");
                }
                String str2 = this.headers.get(GrpcUtil.CONTENT_ACCEPT_ENCODING);
                this.cookies.unloadQueue(response);
                response.setRequestMethod(this.method);
                if (str2 == null || !str2.contains("gzip")) {
                    response.setUseGzip(false);
                }
                response.setKeepAlive(z);
                response.send(this.outputStream);
                if (!z || response.isCloseConnection()) {
                    throw new SocketException("NanoHttpd Shutdown");
                }
            } catch (SocketTimeoutException e6) {
                throw e6;
            }
        } finally {
            NanoHTTPD.safeClose(null);
            this.tempFileManager.clear();
        }
    }

    private int findHeaderEnd(byte[] bArr, int i) {
        int i2;
        int i3 = 0;
        while (true) {
            int i4 = i3 + 1;
            if (i4 >= i) {
                return 0;
            }
            if (bArr[i3] == 13 && bArr[i4] == 10 && (i2 = i3 + 3) < i && bArr[i3 + 2] == 13 && bArr[i2] == 10) {
                return i3 + 4;
            }
            if (bArr[i3] == 10 && bArr[i4] == 10) {
                return i3 + 2;
            }
            i3 = i4;
        }
    }

    private int[] getBoundaryPositions(ByteBuffer byteBuffer, byte[] bArr) {
        int[] iArr = new int[0];
        if (byteBuffer.remaining() < bArr.length) {
            return iArr;
        }
        byte[] bArr2 = new byte[bArr.length + 4096];
        int remaining = byteBuffer.remaining() < bArr2.length ? byteBuffer.remaining() : bArr2.length;
        byteBuffer.get(bArr2, 0, remaining);
        int length = remaining - bArr.length;
        int[] iArr2 = iArr;
        int i = 0;
        while (true) {
            int[] iArr3 = iArr2;
            int i2 = 0;
            while (i2 < length) {
                int[] iArr4 = iArr3;
                for (int i3 = 0; i3 < bArr.length && bArr2[i2 + i3] == bArr[i3]; i3++) {
                    if (i3 == bArr.length - 1) {
                        int[] iArr5 = new int[iArr4.length + 1];
                        System.arraycopy(iArr4, 0, iArr5, 0, iArr4.length);
                        iArr5[iArr4.length] = i + i2;
                        iArr4 = iArr5;
                    }
                }
                i2++;
                iArr3 = iArr4;
            }
            i += length;
            System.arraycopy(bArr2, bArr2.length - bArr.length, bArr2, 0, bArr.length);
            length = bArr2.length - bArr.length;
            if (byteBuffer.remaining() < length) {
                length = byteBuffer.remaining();
            }
            byteBuffer.get(bArr2, bArr.length, length);
            if (length <= 0) {
                return iArr3;
            }
            iArr2 = iArr3;
        }
    }

    @Override // org.nanohttpd.protocols.http.IHTTPSession
    public CookieHandler getCookies() {
        return this.cookies;
    }

    @Override // org.nanohttpd.protocols.http.IHTTPSession
    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override // org.nanohttpd.protocols.http.IHTTPSession
    public final InputStream getInputStream() {
        return this.inputStream;
    }

    @Override // org.nanohttpd.protocols.http.IHTTPSession
    public final Method getMethod() {
        return this.method;
    }

    @Override // org.nanohttpd.protocols.http.IHTTPSession
    @Deprecated
    public final Map<String, String> getParms() {
        HashMap hashMap = new HashMap();
        for (String str : this.parms.keySet()) {
            hashMap.put(str, this.parms.get(str).get(0));
        }
        return hashMap;
    }

    @Override // org.nanohttpd.protocols.http.IHTTPSession
    public final Map<String, List<String>> getParameters() {
        return this.parms;
    }

    @Override // org.nanohttpd.protocols.http.IHTTPSession
    public String getQueryParameterString() {
        return this.queryParameterString;
    }

    private RandomAccessFile getTmpBucket() {
        try {
            return new RandomAccessFile(this.tempFileManager.createTempFile(null).getName(), "rw");
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    @Override // org.nanohttpd.protocols.http.IHTTPSession
    public final String getUri() {
        return this.uri;
    }

    public long getBodySize() {
        if (this.headers.containsKey(StreamUtil.CONTENT_LENGTH)) {
            return Long.parseLong(this.headers.get(StreamUtil.CONTENT_LENGTH));
        }
        if (this.splitbyte < this.rlen) {
            return r1 - r0;
        }
        return 0L;
    }

    @Override // org.nanohttpd.protocols.http.IHTTPSession
    public void parseBody(Map<String, String> map) throws IOException, NanoHTTPD.ResponseException {
        RandomAccessFile randomAccessFile;
        long bodySize;
        ByteArrayOutputStream byteArrayOutputStream;
        DataOutput dataOutput;
        ByteBuffer map2;
        try {
            bodySize = getBodySize();
            if (bodySize < 1024) {
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                dataOutput = new DataOutputStream(byteArrayOutputStream2);
                byteArrayOutputStream = byteArrayOutputStream2;
                randomAccessFile = null;
            } else {
                randomAccessFile = getTmpBucket();
                byteArrayOutputStream = null;
                dataOutput = randomAccessFile;
            }
        } catch (Throwable th) {
            th = th;
            randomAccessFile = null;
        }
        try {
            byte[] bArr = new byte[512];
            while (this.rlen >= 0 && bodySize > 0) {
                this.rlen = this.inputStream.read(bArr, 0, (int) Math.min(bodySize, 512L));
                bodySize -= this.rlen;
                if (this.rlen > 0) {
                    dataOutput.write(bArr, 0, this.rlen);
                }
            }
            if (byteArrayOutputStream != null) {
                map2 = ByteBuffer.wrap(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size());
            } else {
                map2 = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, randomAccessFile.length());
                randomAccessFile.seek(0L);
            }
            if (Method.POST.equals(this.method)) {
                ContentType contentType = new ContentType(this.headers.get("content-type"));
                if (contentType.isMultipart()) {
                    if (contentType.getBoundary() == null) {
                        throw new NanoHTTPD.ResponseException(Status.BAD_REQUEST, "BAD REQUEST: Content type is multipart/form-data but boundary missing. Usage: GET /example/file.html");
                    }
                    decodeMultipartFormData(contentType, map2, this.parms, map);
                } else {
                    byte[] bArr2 = new byte[map2.remaining()];
                    map2.get(bArr2);
                    String trim = new String(bArr2, contentType.getEncoding()).trim();
                    if ("application/x-www-form-urlencoded".equalsIgnoreCase(contentType.getContentType())) {
                        decodeParms(trim, this.parms);
                    } else if (trim.length() != 0) {
                        map.put(POST_DATA, trim);
                    }
                }
            } else if (Method.PUT.equals(this.method)) {
                map.put(AIUIConstant.KEY_CONTENT, saveTmpFile(map2, 0, map2.limit(), null));
            }
            NanoHTTPD.safeClose(randomAccessFile);
        } catch (Throwable th2) {
            th = th2;
            NanoHTTPD.safeClose(randomAccessFile);
            throw th;
        }
    }

    private String saveTmpFile(ByteBuffer byteBuffer, int i, int i2, String str) {
        if (i2 <= 0) {
            return "";
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                ITempFile createTempFile = this.tempFileManager.createTempFile(str);
                ByteBuffer duplicate = byteBuffer.duplicate();
                FileOutputStream fileOutputStream2 = new FileOutputStream(createTempFile.getName());
                try {
                    FileChannel channel = fileOutputStream2.getChannel();
                    duplicate.position(i).limit(i + i2);
                    channel.write(duplicate.slice());
                    String name = createTempFile.getName();
                    NanoHTTPD.safeClose(fileOutputStream2);
                    return name;
                } catch (Exception e) {
                    e = e;
                    fileOutputStream = fileOutputStream2;
                    throw new Error(e);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    NanoHTTPD.safeClose(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    @Override // org.nanohttpd.protocols.http.IHTTPSession
    public String getRemoteIpAddress() {
        return this.remoteIp;
    }
}
