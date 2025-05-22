package org.jboss.netty.handler.codec.http.multipart;

import java.nio.charset.Charset;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.util.CharsetUtil;

/* loaded from: classes7.dex */
final class HttpPostBodyUtil {
    public static final String ATTACHMENT = "attachment";
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String DEFAULT_BINARY_CONTENT_TYPE = "application/octet-stream";
    public static final String DEFAULT_TEXT_CONTENT_TYPE = "text/plain";
    public static final String FILE = "file";
    public static final String FILENAME = "filename";
    public static final String FORM_DATA = "form-data";
    public static final String MULTIPART_MIXED = "multipart/mixed";
    public static final String NAME = "name";
    public static int chunkSize = 8096;
    public static final Charset ISO_8859_1 = CharsetUtil.ISO_8859_1;
    public static final Charset US_ASCII = CharsetUtil.US_ASCII;

    /* loaded from: classes7.dex */
    public enum TransferEncodingMechanism {
        BIT7("7bit"),
        BIT8("8bit"),
        BINARY("binary");

        public String value;

        TransferEncodingMechanism(String str) {
            this.value = str;
        }

        TransferEncodingMechanism() {
            this.value = name();
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }
    }

    private HttpPostBodyUtil() {
    }

    /* loaded from: classes7.dex */
    static class SeekAheadNoBackArrayException extends Exception {
        private static final long serialVersionUID = -630418804938699495L;

        SeekAheadNoBackArrayException() {
        }
    }

    /* loaded from: classes7.dex */
    static class SeekAheadOptimize {
        ChannelBuffer buffer;
        byte[] bytes;
        int limit;
        int pos;
        int readerIndex;

        /* JADX INFO: Access modifiers changed from: package-private */
        public SeekAheadOptimize(ChannelBuffer channelBuffer) throws SeekAheadNoBackArrayException {
            if (!channelBuffer.hasArray()) {
                throw new SeekAheadNoBackArrayException();
            }
            this.buffer = channelBuffer;
            this.bytes = channelBuffer.array();
            int readerIndex = channelBuffer.readerIndex();
            this.readerIndex = readerIndex;
            this.pos = readerIndex;
            this.limit = channelBuffer.writerIndex();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void setReadPosition(int i) {
            this.pos -= i;
            this.readerIndex = this.pos;
            this.buffer.readerIndex(this.readerIndex);
        }

        void clear() {
            this.buffer = null;
            this.bytes = null;
            this.limit = 0;
            this.pos = 0;
            this.readerIndex = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int findNonWhitespace(String str, int i) {
        while (i < str.length() && Character.isWhitespace(str.charAt(i))) {
            i++;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int findWhitespace(String str, int i) {
        while (i < str.length() && !Character.isWhitespace(str.charAt(i))) {
            i++;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int findEndOfString(String str) {
        int length = str.length();
        while (length > 0 && Character.isWhitespace(str.charAt(length - 1))) {
            length--;
        }
        return length;
    }
}
