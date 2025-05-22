package com.pudutech.remotemaintenance.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: StringUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0004J\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\t\u001a\u00020\u0004J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007J\u0010\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000b\u001a\u00020\u0007J\u001a\u0010\f\u001a\u0004\u0018\u00010\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/utils/StringUtil;", "", "()V", "GZIP_ENCODE_ISO_8859_1", "", "GZIP_ENCODE_UTF_8", "compress", "", "str", "encoding", "uncompress", "bytes", "uncompressToString", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class StringUtil {
    public static final String GZIP_ENCODE_ISO_8859_1 = "ISO-8859-1";
    public static final String GZIP_ENCODE_UTF_8 = "UTF-8";
    public static final StringUtil INSTANCE = new StringUtil();

    private StringUtil() {
    }

    public final byte[] compress(String str) {
        Intrinsics.checkParameterIsNotNull(str, "str");
        return compress(str, "UTF-8");
    }

    public final byte[] compress(String str, String encoding) {
        Intrinsics.checkParameterIsNotNull(encoding, "encoding");
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            Charset forName = Charset.forName(encoding);
            Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(charsetName)");
            byte[] bytes = str.getBytes(forName);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            gZIPOutputStream.write(bytes);
            gZIPOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public final byte[] uncompress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(bytes));
            byte[] bArr = new byte[256];
            Ref.IntRef intRef = new Ref.IntRef();
            while (true) {
                int read = gZIPInputStream.read(bArr);
                intRef.element = read;
                if (read < 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, intRef.element);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public final String uncompressToString(byte[] bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        return uncompressToString(bytes, "UTF-8");
    }

    public final String uncompressToString(byte[] bytes, String encoding) {
        Intrinsics.checkParameterIsNotNull(encoding, "encoding");
        if (bytes != null) {
            if (!(bytes.length == 0)) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(bytes));
                    byte[] bArr = new byte[256];
                    Ref.IntRef intRef = new Ref.IntRef();
                    while (true) {
                        int read = gZIPInputStream.read(bArr);
                        intRef.element = read;
                        if (read >= 0) {
                            byteArrayOutputStream.write(bArr, 0, intRef.element);
                        } else {
                            return byteArrayOutputStream.toString(encoding);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
