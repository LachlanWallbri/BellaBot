package org.apache.commons.compress.archivers.zip;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Iterator;

/* loaded from: classes8.dex */
public abstract class ZipEncodingHelper {
    static final String UTF8 = "UTF8";
    static final ZipEncoding UTF8_ZIP_ENCODING = getZipEncoding(UTF8);

    public static ZipEncoding getZipEncoding(String str) {
        Charset defaultCharset = Charset.defaultCharset();
        if (str != null) {
            try {
                defaultCharset = Charset.forName(str);
            } catch (UnsupportedCharsetException unused) {
            }
        }
        return new NioZipEncoding(defaultCharset, isUTF8(defaultCharset.name()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isUTF8(String str) {
        if (str == null) {
            str = Charset.defaultCharset().name();
        }
        if (StandardCharsets.UTF_8.name().equalsIgnoreCase(str)) {
            return true;
        }
        Iterator<String> it = StandardCharsets.UTF_8.aliases().iterator();
        while (it.hasNext()) {
            if (it.next().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ByteBuffer growBufferBy(ByteBuffer byteBuffer, int i) {
        byteBuffer.limit(byteBuffer.position());
        byteBuffer.rewind();
        ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.capacity() + i);
        allocate.put(byteBuffer);
        return allocate;
    }
}
