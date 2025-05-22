package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.util.ByteProcessor;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes7.dex */
public interface ByteBufProcessor extends ByteProcessor {

    @Deprecated
    public static final ByteBufProcessor FIND_NUL = new ByteBufProcessor() { // from class: io.grpc.netty.shaded.io.netty.buffer.ByteBufProcessor.1
        @Override // io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) throws Exception {
            return b != 0;
        }
    };

    @Deprecated
    public static final ByteBufProcessor FIND_NON_NUL = new ByteBufProcessor() { // from class: io.grpc.netty.shaded.io.netty.buffer.ByteBufProcessor.2
        @Override // io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) throws Exception {
            return b == 0;
        }
    };

    @Deprecated
    public static final ByteBufProcessor FIND_CR = new ByteBufProcessor() { // from class: io.grpc.netty.shaded.io.netty.buffer.ByteBufProcessor.3
        @Override // io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) throws Exception {
            return b != 13;
        }
    };

    @Deprecated
    public static final ByteBufProcessor FIND_NON_CR = new ByteBufProcessor() { // from class: io.grpc.netty.shaded.io.netty.buffer.ByteBufProcessor.4
        @Override // io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) throws Exception {
            return b == 13;
        }
    };

    @Deprecated
    public static final ByteBufProcessor FIND_LF = new ByteBufProcessor() { // from class: io.grpc.netty.shaded.io.netty.buffer.ByteBufProcessor.5
        @Override // io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) throws Exception {
            return b != 10;
        }
    };

    @Deprecated
    public static final ByteBufProcessor FIND_NON_LF = new ByteBufProcessor() { // from class: io.grpc.netty.shaded.io.netty.buffer.ByteBufProcessor.6
        @Override // io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) throws Exception {
            return b == 10;
        }
    };

    @Deprecated
    public static final ByteBufProcessor FIND_CRLF = new ByteBufProcessor() { // from class: io.grpc.netty.shaded.io.netty.buffer.ByteBufProcessor.7
        @Override // io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) throws Exception {
            return (b == 13 || b == 10) ? false : true;
        }
    };

    @Deprecated
    public static final ByteBufProcessor FIND_NON_CRLF = new ByteBufProcessor() { // from class: io.grpc.netty.shaded.io.netty.buffer.ByteBufProcessor.8
        @Override // io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) throws Exception {
            return b == 13 || b == 10;
        }
    };

    @Deprecated
    public static final ByteBufProcessor FIND_LINEAR_WHITESPACE = new ByteBufProcessor() { // from class: io.grpc.netty.shaded.io.netty.buffer.ByteBufProcessor.9
        @Override // io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) throws Exception {
            return (b == 32 || b == 9) ? false : true;
        }
    };

    @Deprecated
    public static final ByteBufProcessor FIND_NON_LINEAR_WHITESPACE = new ByteBufProcessor() { // from class: io.grpc.netty.shaded.io.netty.buffer.ByteBufProcessor.10
        @Override // io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) throws Exception {
            return b == 32 || b == 9;
        }
    };
}
