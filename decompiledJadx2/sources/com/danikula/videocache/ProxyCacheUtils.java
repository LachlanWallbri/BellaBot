package com.danikula.videocache;

import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: classes.dex */
public class ProxyCacheUtils {
    static final int DEFAULT_BUFFER_SIZE = 8192;
    private static final Logger LOG = LoggerFactory.getLogger("ProxyCacheUtils");
    static final int MAX_ARRAY_PREVIEW = 16;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getSupposablyMime(String str) {
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if (TextUtils.isEmpty(fileExtensionFromUrl)) {
            return null;
        }
        return singleton.getMimeTypeFromExtension(fileExtensionFromUrl);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void assertBuffer(byte[] bArr, long j, int i) {
        Preconditions.checkNotNull(bArr, "Buffer must be not null!");
        Preconditions.checkArgument(j >= 0, "Data offset must be positive!");
        Preconditions.checkArgument(i >= 0 && i <= bArr.length, "Length must be in range [0..buffer.length]");
    }

    static String preview(byte[] bArr, int i) {
        int min = Math.min(16, Math.max(i, 0));
        String arrays = Arrays.toString(Arrays.copyOfRange(bArr, 0, min));
        if (min >= i) {
            return arrays;
        }
        return arrays.substring(0, arrays.length() - 1) + ", ...]";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String encode(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error encoding url", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String decode(String str) {
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error decoding url", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                LOG.error("Error closing resource", (Throwable) e);
            }
        }
    }

    public static String computeMD5(String str) {
        try {
            return bytesToHexString(MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(str.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    private static String bytesToHexString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(String.format("%02x", Byte.valueOf(b)));
        }
        return stringBuffer.toString();
    }
}
