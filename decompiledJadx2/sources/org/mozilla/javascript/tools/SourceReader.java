package org.mozilla.javascript.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.commonjs.module.provider.ParsedContentType;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class SourceReader {
    public static URL toUrl(String str) {
        if (str.indexOf(58) < 2) {
            return null;
        }
        try {
            return new URL(str);
        } catch (MalformedURLException unused) {
            return null;
        }
    }

    public static Object readFileOrUrl(String str, boolean z, String str2) throws IOException {
        String contentType;
        int contentLength;
        String str3;
        URL url = toUrl(str);
        InputStream inputStream = null;
        String encoding = null;
        try {
            if (url == null) {
                File file = new File(str);
                contentLength = (int) file.length();
                FileInputStream fileInputStream = new FileInputStream(file);
                str3 = null;
                inputStream = fileInputStream;
                contentType = null;
            } else {
                URLConnection openConnection = url.openConnection();
                InputStream inputStream2 = openConnection.getInputStream();
                if (z) {
                    try {
                        ParsedContentType parsedContentType = new ParsedContentType(openConnection.getContentType());
                        contentType = parsedContentType.getContentType();
                        encoding = parsedContentType.getEncoding();
                    } catch (Throwable th) {
                        th = th;
                        inputStream = inputStream2;
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        throw th;
                    }
                } else {
                    contentType = null;
                }
                contentLength = openConnection.getContentLength();
                if (contentLength > 1048576) {
                    contentLength = -1;
                }
                str3 = encoding;
                inputStream = inputStream2;
            }
            if (contentLength <= 0) {
                contentLength = 4096;
            }
            byte[] readStream = Kit.readStream(inputStream, contentLength);
            if (inputStream != null) {
                inputStream.close();
            }
            if (!z) {
                return readStream;
            }
            if (str3 != null) {
                str2 = str3;
            } else if (readStream.length > 3 && readStream[0] == -1 && readStream[1] == -2 && readStream[2] == 0 && readStream[3] == 0) {
                str2 = "UTF-32LE";
            } else if (readStream.length > 3 && readStream[0] == 0 && readStream[1] == 0 && readStream[2] == -2 && readStream[3] == -1) {
                str2 = "UTF-32BE";
            } else {
                if (readStream.length <= 2 || readStream[0] != -17 || readStream[1] != -69 || readStream[2] != -65) {
                    if (readStream.length > 1 && readStream[0] == -1 && readStream[1] == -2) {
                        str2 = "UTF-16LE";
                    } else if (readStream.length > 1 && readStream[0] == -2 && readStream[1] == -1) {
                        str2 = "UTF-16BE";
                    } else if (str2 == null) {
                        if (url == null) {
                            str2 = System.getProperty("file.encoding");
                        } else if (contentType == null || !contentType.startsWith("application/")) {
                            str2 = "US-ASCII";
                        }
                    }
                }
                str2 = "UTF-8";
            }
            String str4 = new String(readStream, str2);
            return (str4.length() <= 0 || str4.charAt(0) != 65279) ? str4 : str4.substring(1);
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
