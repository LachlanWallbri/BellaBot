package org.nanohttpd.protocols.webserver;

import androidx.core.os.EnvironmentCompat;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.StringTokenizer;
import org.nanohttpd.protocols.http.IHTTPSession;
import org.nanohttpd.protocols.http.NanoHTTPD;
import org.nanohttpd.protocols.http.request.Method;
import org.nanohttpd.protocols.http.response.IStatus;
import org.nanohttpd.protocols.http.response.Response;
import org.nanohttpd.protocols.http.response.Status;
import org.nanohttpd.protocols.util.ServerRunner;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class SimpleWebServer extends NanoHTTPD {
    public static final String ACCESS_CONTROL_ALLOW_HEADER_PROPERTY_NAME = "AccessControlAllowHeader";
    private static final String ALLOWED_METHODS = "GET, POST, PUT, DELETE, OPTIONS, HEAD";
    public static final String DEFAULT_ALLOWED_HEADERS = "origin,accept,content-type";
    public static final List<String> INDEX_FILE_NAMES = new ArrayList<String>() { // from class: org.nanohttpd.protocols.webserver.SimpleWebServer.1
        {
            add("index.html");
            add("index.htm");
        }
    };
    private static final String LICENCE;
    private static final int MAX_AGE = 151200;
    private static Map<String, WebServerPlugin> mimeTypeHandlers;
    private final String cors;
    private final boolean quiet;
    protected List<File> rootDirs;

    public void init() {
    }

    static {
        String str;
        mimeTypes();
        try {
            InputStream resourceAsStream = SimpleWebServer.class.getResourceAsStream("/LICENSE.txt");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = resourceAsStream.read(bArr);
                if (read < 0) {
                    break;
                } else {
                    byteArrayOutputStream.write(bArr, 0, read);
                }
            }
            str = byteArrayOutputStream.toString("UTF-8");
        } catch (Exception unused) {
            str = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        LICENCE = str;
        mimeTypeHandlers = new HashMap();
    }

    public static void main(String[] strArr) {
        Iterator it;
        int indexOf;
        ArrayList<File> arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        int i = 0;
        String str = null;
        boolean z = false;
        int i2 = 8080;
        String str2 = null;
        for (int i3 = 0; i3 < strArr.length; i3++) {
            if ("-h".equalsIgnoreCase(strArr[i3]) || "--host".equalsIgnoreCase(strArr[i3])) {
                str2 = strArr[i3 + 1];
            } else if ("-p".equalsIgnoreCase(strArr[i3]) || "--port".equalsIgnoreCase(strArr[i3])) {
                i2 = Integer.parseInt(strArr[i3 + 1]);
            } else if ("-q".equalsIgnoreCase(strArr[i3]) || "--quiet".equalsIgnoreCase(strArr[i3])) {
                z = true;
            } else if ("-d".equalsIgnoreCase(strArr[i3]) || "--dir".equalsIgnoreCase(strArr[i3])) {
                arrayList.add(new File(strArr[i3 + 1]).getAbsoluteFile());
            } else if (strArr[i3].startsWith("--cors")) {
                int indexOf2 = strArr[i3].indexOf(61);
                str = indexOf2 > 0 ? strArr[i3].substring(indexOf2 + 1) : "*";
            } else if ("--licence".equalsIgnoreCase(strArr[i3])) {
                System.out.println(LICENCE + "\n");
            } else if (strArr[i3].startsWith("-X:") && (indexOf = strArr[i3].indexOf(61)) > 0) {
                hashMap.put(strArr[i3].substring(0, indexOf), strArr[i3].substring(indexOf + 1, strArr[i3].length()));
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new File(".").getAbsoluteFile());
        }
        hashMap.put("host", str2);
        hashMap.put("port", "" + i2);
        hashMap.put("quiet", String.valueOf(z));
        StringBuilder sb = new StringBuilder();
        for (File file : arrayList) {
            if (sb.length() > 0) {
                sb.append(":");
            }
            try {
                sb.append(file.getCanonicalPath());
            } catch (IOException unused) {
            }
        }
        hashMap.put("home", sb.toString());
        Iterator it2 = ServiceLoader.load(WebServerPluginInfo.class).iterator();
        while (it2.hasNext()) {
            WebServerPluginInfo webServerPluginInfo = (WebServerPluginInfo) it2.next();
            String[] mimeTypes = webServerPluginInfo.getMimeTypes();
            int length = mimeTypes.length;
            int i4 = i;
            while (i4 < length) {
                String str3 = mimeTypes[i4];
                String[] indexFilesForMimeType = webServerPluginInfo.getIndexFilesForMimeType(str3);
                if (z) {
                    it = it2;
                } else {
                    System.out.print("# Found plugin for Mime type: \"" + str3 + "\"");
                    if (indexFilesForMimeType != null) {
                        System.out.print(" (serving index files: ");
                        int i5 = 0;
                        for (int length2 = indexFilesForMimeType.length; i5 < length2; length2 = length2) {
                            String str4 = indexFilesForMimeType[i5];
                            Iterator it3 = it2;
                            System.out.print(str4 + " ");
                            i5++;
                            it2 = it3;
                        }
                    }
                    it = it2;
                    System.out.println(").");
                }
                registerPluginForMimeType(indexFilesForMimeType, str3, webServerPluginInfo.getWebServerPlugin(str3), hashMap);
                i4++;
                it2 = it;
                i = 0;
            }
        }
        ServerRunner.executeInstance(new SimpleWebServer(str2, i2, arrayList, z, str));
    }

    protected static void registerPluginForMimeType(String[] strArr, String str, WebServerPlugin webServerPlugin, Map<String, String> map) {
        if (str == null || webServerPlugin == null) {
            return;
        }
        if (strArr != null) {
            for (String str2 : strArr) {
                int lastIndexOf = str2.lastIndexOf(46);
                if (lastIndexOf >= 0) {
                    mimeTypes().put(str2.substring(lastIndexOf + 1).toLowerCase(), str);
                }
            }
            INDEX_FILE_NAMES.addAll(Arrays.asList(strArr));
        }
        mimeTypeHandlers.put(str, webServerPlugin);
        webServerPlugin.initialize(map);
    }

    public SimpleWebServer(String str, int i, File file, boolean z, String str2) {
        this(str, i, (List<File>) Collections.singletonList(file), z, str2);
    }

    public SimpleWebServer(String str, int i, File file, boolean z) {
        this(str, i, (List<File>) Collections.singletonList(file), z, (String) null);
    }

    public SimpleWebServer(String str, int i, List<File> list, boolean z) {
        this(str, i, list, z, (String) null);
    }

    public SimpleWebServer(String str, int i, List<File> list, boolean z, String str2) {
        super(str, i);
        this.quiet = z;
        this.cors = str2;
        this.rootDirs = new ArrayList(list);
        init();
    }

    private boolean canServeUri(String str, File file) {
        WebServerPlugin webServerPlugin;
        boolean exists = new File(file, str).exists();
        return (exists || (webServerPlugin = mimeTypeHandlers.get(getMimeTypeForFile(str))) == null) ? exists : webServerPlugin.canServeUri(str, file);
    }

    private String encodeUri(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, "/ ", true);
        String str2 = "";
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if ("/".equals(nextToken)) {
                str2 = str2 + "/";
            } else if (" ".equals(nextToken)) {
                str2 = str2 + "%20";
            } else {
                try {
                    str2 = str2 + URLEncoder.encode(nextToken, "UTF-8");
                } catch (UnsupportedEncodingException unused) {
                }
            }
        }
        return str2;
    }

    private String findIndexFileInDirectory(File file) {
        for (String str : INDEX_FILE_NAMES) {
            if (new File(file, str).isFile()) {
                return str;
            }
        }
        return null;
    }

    protected Response getForbiddenResponse(String str) {
        return Response.newFixedLengthResponse(Status.FORBIDDEN, "text/plain", "FORBIDDEN: " + str);
    }

    protected Response getInternalErrorResponse(String str) {
        return Response.newFixedLengthResponse(Status.INTERNAL_ERROR, "text/plain", "INTERNAL ERROR: " + str);
    }

    protected Response getNotFoundResponse() {
        return Response.newFixedLengthResponse(Status.NOT_FOUND, "text/plain", "Error 404, file not found.");
    }

    protected String listDirectory(String str, File file) {
        String substring;
        int lastIndexOf;
        String str2 = "Directory " + str;
        StringBuilder sb = new StringBuilder("<html><head><title>" + str2 + "</title><style><!--\nspan.dirname { font-weight: bold; }\nspan.filesize { font-size: 75%; }\n// -->\n</style></head><body><h1>" + str2 + "</h1>");
        String substring2 = (str.length() <= 1 || (lastIndexOf = (substring = str.substring(0, str.length() - 1)).lastIndexOf(47)) < 0 || lastIndexOf >= substring.length()) ? null : str.substring(0, lastIndexOf + 1);
        List<String> asList = Arrays.asList(file.list(new FilenameFilter() { // from class: org.nanohttpd.protocols.webserver.SimpleWebServer.2
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str3) {
                return new File(file2, str3).isFile();
            }
        }));
        Collections.sort(asList);
        List asList2 = Arrays.asList(file.list(new FilenameFilter() { // from class: org.nanohttpd.protocols.webserver.SimpleWebServer.3
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str3) {
                return new File(file2, str3).isDirectory();
            }
        }));
        Collections.sort(asList2);
        if (substring2 != null || asList2.size() + asList.size() > 0) {
            sb.append("<ul>");
            if (substring2 != null || asList2.size() > 0) {
                sb.append("<section class=\"directories\">");
                if (substring2 != null) {
                    sb.append("<li><a rel=\"directory\" href=\"");
                    sb.append(substring2);
                    sb.append("\"><span class=\"dirname\">..</span></a></li>");
                }
                Iterator it = asList2.iterator();
                while (it.hasNext()) {
                    String str3 = ((String) it.next()) + "/";
                    sb.append("<li><a rel=\"directory\" href=\"");
                    sb.append(encodeUri(str + str3));
                    sb.append("\"><span class=\"dirname\">");
                    sb.append(str3);
                    sb.append("</span></a></li>");
                }
                sb.append("</section>");
            }
            if (asList.size() > 0) {
                sb.append("<section class=\"files\">");
                for (String str4 : asList) {
                    sb.append("<li><a href=\"");
                    sb.append(encodeUri(str + str4));
                    sb.append("\"><span class=\"filename\">");
                    sb.append(str4);
                    sb.append("</span></a>");
                    long length = new File(file, str4).length();
                    sb.append("&nbsp;<span class=\"filesize\">(");
                    if (length < 1024) {
                        sb.append(length);
                        sb.append(" bytes");
                    } else if (length < 1048576) {
                        sb.append(length / 1024);
                        sb.append(".");
                        sb.append(((length % 1024) / 10) % 100);
                        sb.append(" KB");
                    } else {
                        sb.append(length / 1048576);
                        sb.append(".");
                        sb.append(((length % 1048576) / 10000) % 100);
                        sb.append(" MB");
                    }
                    sb.append(")</span></li>");
                }
                sb.append("</section>");
            }
            sb.append("</ul>");
        }
        sb.append("</body></html>");
        return sb.toString();
    }

    public static Response newFixedLengthResponse(IStatus iStatus, String str, String str2) {
        Response newFixedLengthResponse = Response.newFixedLengthResponse(iStatus, str, str2);
        newFixedLengthResponse.addHeader("Accept-Ranges", "bytes");
        return newFixedLengthResponse;
    }

    private Response respond(Map<String, String> map, IHTTPSession iHTTPSession, String str) {
        Response defaultRespond;
        if (this.cors != null && Method.OPTIONS.equals(iHTTPSession.getMethod())) {
            defaultRespond = Response.newFixedLengthResponse(Status.OK, "text/plain", null, 0L);
        } else {
            defaultRespond = defaultRespond(map, iHTTPSession, str);
        }
        String str2 = this.cors;
        return str2 != null ? addCORSHeaders(map, defaultRespond, str2) : defaultRespond;
    }

    private Response defaultRespond(Map<String, String> map, IHTTPSession iHTTPSession, String str) {
        Response serveFile;
        String replace = str.trim().replace(File.separatorChar, '/');
        boolean z = false;
        if (replace.indexOf(63) >= 0) {
            replace = replace.substring(0, replace.indexOf(63));
        }
        String str2 = replace;
        if (str2.contains("../")) {
            return getForbiddenResponse("Won't serve ../ for security reasons.");
        }
        File file = null;
        for (int i = 0; !z && i < this.rootDirs.size(); i++) {
            file = this.rootDirs.get(i);
            z = canServeUri(str2, file);
        }
        if (!z) {
            return getNotFoundResponse();
        }
        File file2 = new File(file, str2);
        if (file2.isDirectory() && !str2.endsWith("/")) {
            String str3 = str2 + "/";
            Response newFixedLengthResponse = newFixedLengthResponse(Status.REDIRECT, "text/html", "<html><body>Redirected: <a href=\"" + str3 + "\">" + str3 + "</a></body></html>");
            newFixedLengthResponse.addHeader("Location", str3);
            return newFixedLengthResponse;
        }
        if (file2.isDirectory()) {
            String findIndexFileInDirectory = findIndexFileInDirectory(file2);
            if (findIndexFileInDirectory == null) {
                if (file2.canRead()) {
                    return newFixedLengthResponse(Status.OK, "text/html", listDirectory(str2, file2));
                }
                return getForbiddenResponse("No directory listing.");
            }
            return respond(map, iHTTPSession, str2 + findIndexFileInDirectory);
        }
        String mimeTypeForFile = getMimeTypeForFile(str2);
        WebServerPlugin webServerPlugin = mimeTypeHandlers.get(mimeTypeForFile);
        if (webServerPlugin != null && webServerPlugin.canServeUri(str2, file)) {
            serveFile = webServerPlugin.serveFile(str2, map, iHTTPSession, file2, mimeTypeForFile);
            if (serveFile != null && (serveFile instanceof InternalRewrite)) {
                InternalRewrite internalRewrite = (InternalRewrite) serveFile;
                return respond(internalRewrite.getHeaders(), iHTTPSession, internalRewrite.getUri());
            }
        } else {
            serveFile = serveFile(str2, map, file2, mimeTypeForFile);
        }
        return serveFile != null ? serveFile : getNotFoundResponse();
    }

    @Override // org.nanohttpd.protocols.http.NanoHTTPD
    public Response serve(IHTTPSession iHTTPSession) {
        Map<String, String> headers = iHTTPSession.getHeaders();
        Map<String, String> parms = iHTTPSession.getParms();
        String uri = iHTTPSession.getUri();
        if (!this.quiet) {
            System.out.println(iHTTPSession.getMethod() + " '" + uri + "' ");
            for (String str : headers.keySet()) {
                System.out.println("  HDR: '" + str + "' = '" + headers.get(str) + "'");
            }
            for (String str2 : parms.keySet()) {
                System.out.println("  PRM: '" + str2 + "' = '" + parms.get(str2) + "'");
            }
        }
        for (File file : this.rootDirs) {
            if (!file.isDirectory()) {
                return getInternalErrorResponse("given path is not a directory (" + file + ").");
            }
        }
        return respond(Collections.unmodifiableMap(headers), iHTTPSession, uri);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0088 A[Catch: IOException -> 0x0194, TryCatch #2 {IOException -> 0x0194, blocks: (B:3:0x000a, B:5:0x0041, B:7:0x0049, B:10:0x0056, B:13:0x005f, B:14:0x006a, B:16:0x0074, B:20:0x007e, B:22:0x0088, B:24:0x0090, B:27:0x0098, B:64:0x0179), top: B:2:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00a4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x012d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0157 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x016a A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    Response serveFile(String str, Map<String, String> map, File file, String str2) {
        String hexString;
        long j;
        String str3;
        boolean z;
        long j2;
        String str4;
        boolean z2;
        String str5;
        long length;
        Response newFixedFileResponse;
        Response newFixedLengthResponse;
        int indexOf;
        SimpleWebServer simpleWebServer = this;
        try {
            hexString = Integer.toHexString((file.getAbsolutePath() + file.lastModified() + "" + file.length()).hashCode());
            j = -1;
            str3 = map.get("range");
            z = true;
        } catch (IOException unused) {
        }
        try {
            if (str3 != null && str3.startsWith("bytes=") && (indexOf = (str3 = str3.substring(6)).indexOf(45)) > 0) {
                try {
                    j2 = Long.parseLong(str3.substring(0, indexOf));
                    try {
                        j = Long.parseLong(str3.substring(indexOf + 1));
                    } catch (NumberFormatException unused2) {
                    }
                } catch (NumberFormatException unused3) {
                }
                str4 = map.get("if-range");
                if (str4 != null && !hexString.equals(str4)) {
                    z2 = false;
                    str5 = map.get("if-none-match");
                    if (str5 != null || (!"*".equals(str5) && !str5.equals(hexString))) {
                        z = false;
                    }
                    length = file.length();
                    if (z2 || str3 == null || j2 < 0 || j2 >= length) {
                        if (z2 || str3 == null || j2 < length) {
                            if (str3 != null && z) {
                                newFixedLengthResponse = newFixedLengthResponse(Status.NOT_MODIFIED, str2, "");
                                newFixedLengthResponse.addHeader("ETag", hexString);
                            } else if (z2 && z) {
                                newFixedLengthResponse = newFixedLengthResponse(Status.NOT_MODIFIED, str2, "");
                                newFixedLengthResponse.addHeader("ETag", hexString);
                            } else {
                                simpleWebServer = this;
                                newFixedFileResponse = simpleWebServer.newFixedFileResponse(file, str2);
                                newFixedFileResponse.addHeader("Content-Length", "" + length);
                                newFixedFileResponse.addHeader("ETag", hexString);
                            }
                            return newFixedLengthResponse;
                        }
                        newFixedFileResponse = newFixedLengthResponse(Status.RANGE_NOT_SATISFIABLE, "text/plain", "");
                        newFixedFileResponse.addHeader("Content-Range", "bytes */" + length);
                        newFixedFileResponse.addHeader("ETag", hexString);
                    } else {
                        if (z) {
                            newFixedLengthResponse = newFixedLengthResponse(Status.NOT_MODIFIED, str2, "");
                            newFixedLengthResponse.addHeader("ETag", hexString);
                            return newFixedLengthResponse;
                        }
                        if (j < 0) {
                            j = length - 1;
                        }
                        long j3 = (j - j2) + 1;
                        long j4 = j3 < 0 ? 0L : j3;
                        FileInputStream fileInputStream = new FileInputStream(file);
                        fileInputStream.skip(j2);
                        newFixedFileResponse = Response.newFixedLengthResponse(Status.PARTIAL_CONTENT, str2, fileInputStream, j4);
                        newFixedFileResponse.addHeader("Accept-Ranges", "bytes");
                        newFixedFileResponse.addHeader("Content-Length", "" + j4);
                        newFixedFileResponse.addHeader("Content-Range", "bytes " + j2 + "-" + j + "/" + length);
                        newFixedFileResponse.addHeader("ETag", hexString);
                    }
                    return newFixedFileResponse;
                }
                z2 = true;
                str5 = map.get("if-none-match");
                if (str5 != null) {
                }
                z = false;
                length = file.length();
                if (z2) {
                }
                if (z2) {
                }
                if (str3 != null) {
                }
                if (z2) {
                }
                simpleWebServer = this;
                newFixedFileResponse = simpleWebServer.newFixedFileResponse(file, str2);
                newFixedFileResponse.addHeader("Content-Length", "" + length);
                newFixedFileResponse.addHeader("ETag", hexString);
                return newFixedFileResponse;
            }
            if (str4 != null) {
                z2 = false;
                str5 = map.get("if-none-match");
                if (str5 != null) {
                }
                z = false;
                length = file.length();
                if (z2) {
                }
                if (z2) {
                }
                if (str3 != null) {
                }
                if (z2) {
                }
                simpleWebServer = this;
                newFixedFileResponse = simpleWebServer.newFixedFileResponse(file, str2);
                newFixedFileResponse.addHeader("Content-Length", "" + length);
                newFixedFileResponse.addHeader("ETag", hexString);
                return newFixedFileResponse;
            }
            if (z2) {
            }
            if (z2) {
            }
            if (str3 != null) {
            }
            if (z2) {
            }
            simpleWebServer = this;
            newFixedFileResponse = simpleWebServer.newFixedFileResponse(file, str2);
            newFixedFileResponse.addHeader("Content-Length", "" + length);
            newFixedFileResponse.addHeader("ETag", hexString);
            return newFixedFileResponse;
        } catch (IOException unused4) {
            simpleWebServer = this;
            return simpleWebServer.getForbiddenResponse("Reading file failed.");
        }
        j2 = 0;
        str4 = map.get("if-range");
        z2 = true;
        str5 = map.get("if-none-match");
        if (str5 != null) {
        }
        z = false;
        length = file.length();
    }

    private Response newFixedFileResponse(File file, String str) throws FileNotFoundException {
        Response newFixedLengthResponse = Response.newFixedLengthResponse(Status.OK, str, new FileInputStream(file), (int) file.length());
        newFixedLengthResponse.addHeader("Accept-Ranges", "bytes");
        return newFixedLengthResponse;
    }

    protected Response addCORSHeaders(Map<String, String> map, Response response, String str) {
        response.addHeader("Access-Control-Allow-Origin", str);
        response.addHeader("Access-Control-Allow-Headers", calculateAllowHeaders(map));
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Methods", ALLOWED_METHODS);
        response.addHeader("Access-Control-Max-Age", "151200");
        return response;
    }

    private String calculateAllowHeaders(Map<String, String> map) {
        return System.getProperty(ACCESS_CONTROL_ALLOW_HEADER_PROPERTY_NAME, DEFAULT_ALLOWED_HEADERS);
    }
}
