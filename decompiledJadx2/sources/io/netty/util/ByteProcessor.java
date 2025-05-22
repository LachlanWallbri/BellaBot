package io.netty.util;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface ByteProcessor {
    public static final ByteProcessor FIND_NUL = new IndexOfProcessor((byte) 0);
    public static final ByteProcessor FIND_NON_NUL = new IndexNotOfProcessor((byte) 0);
    public static final ByteProcessor FIND_CR = new IndexOfProcessor((byte) 13);
    public static final ByteProcessor FIND_NON_CR = new IndexNotOfProcessor((byte) 13);
    public static final ByteProcessor FIND_LF = new IndexOfProcessor((byte) 10);
    public static final ByteProcessor FIND_NON_LF = new IndexNotOfProcessor((byte) 10);
    public static final ByteProcessor FIND_SEMI_COLON = new IndexOfProcessor((byte) 59);
    public static final ByteProcessor FIND_COMMA = new IndexOfProcessor((byte) 44);
    public static final ByteProcessor FIND_ASCII_SPACE = new IndexOfProcessor((byte) 32);
    public static final ByteProcessor FIND_CRLF = new ByteProcessor() { // from class: io.netty.util.ByteProcessor.1
        @Override // io.netty.util.ByteProcessor
        public boolean process(byte b) {
            return (b == 13 || b == 10) ? false : true;
        }
    };
    public static final ByteProcessor FIND_NON_CRLF = new ByteProcessor() { // from class: io.netty.util.ByteProcessor.2
        @Override // io.netty.util.ByteProcessor
        public boolean process(byte b) {
            return b == 13 || b == 10;
        }
    };
    public static final ByteProcessor FIND_LINEAR_WHITESPACE = new ByteProcessor() { // from class: io.netty.util.ByteProcessor.3
        @Override // io.netty.util.ByteProcessor
        public boolean process(byte b) {
            return (b == 32 || b == 9) ? false : true;
        }
    };
    public static final ByteProcessor FIND_NON_LINEAR_WHITESPACE = new ByteProcessor() { // from class: io.netty.util.ByteProcessor.4
        @Override // io.netty.util.ByteProcessor
        public boolean process(byte b) {
            return b == 32 || b == 9;
        }
    };

    boolean process(byte b) throws Exception;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static class IndexOfProcessor implements ByteProcessor {
        private final byte byteToFind;

        public IndexOfProcessor(byte b) {
            this.byteToFind = b;
        }

        @Override // io.netty.util.ByteProcessor
        public boolean process(byte b) {
            return b != this.byteToFind;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static class IndexNotOfProcessor implements ByteProcessor {
        private final byte byteToNotFind;

        public IndexNotOfProcessor(byte b) {
            this.byteToNotFind = b;
        }

        @Override // io.netty.util.ByteProcessor
        public boolean process(byte b) {
            return b == this.byteToNotFind;
        }
    }
}
