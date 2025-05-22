package org.jboss.netty.buffer;

/* loaded from: classes7.dex */
public interface ChannelBufferIndexFinder {
    public static final ChannelBufferIndexFinder NUL = new ChannelBufferIndexFinder() { // from class: org.jboss.netty.buffer.ChannelBufferIndexFinder.1
        @Override // org.jboss.netty.buffer.ChannelBufferIndexFinder
        public boolean find(ChannelBuffer channelBuffer, int i) {
            return channelBuffer.getByte(i) == 0;
        }
    };
    public static final ChannelBufferIndexFinder NOT_NUL = new ChannelBufferIndexFinder() { // from class: org.jboss.netty.buffer.ChannelBufferIndexFinder.2
        @Override // org.jboss.netty.buffer.ChannelBufferIndexFinder
        public boolean find(ChannelBuffer channelBuffer, int i) {
            return channelBuffer.getByte(i) != 0;
        }
    };

    /* renamed from: CR */
    public static final ChannelBufferIndexFinder f10015CR = new ChannelBufferIndexFinder() { // from class: org.jboss.netty.buffer.ChannelBufferIndexFinder.3
        @Override // org.jboss.netty.buffer.ChannelBufferIndexFinder
        public boolean find(ChannelBuffer channelBuffer, int i) {
            return channelBuffer.getByte(i) == 13;
        }
    };
    public static final ChannelBufferIndexFinder NOT_CR = new ChannelBufferIndexFinder() { // from class: org.jboss.netty.buffer.ChannelBufferIndexFinder.4
        @Override // org.jboss.netty.buffer.ChannelBufferIndexFinder
        public boolean find(ChannelBuffer channelBuffer, int i) {
            return channelBuffer.getByte(i) != 13;
        }
    };

    /* renamed from: LF */
    public static final ChannelBufferIndexFinder f10016LF = new ChannelBufferIndexFinder() { // from class: org.jboss.netty.buffer.ChannelBufferIndexFinder.5
        @Override // org.jboss.netty.buffer.ChannelBufferIndexFinder
        public boolean find(ChannelBuffer channelBuffer, int i) {
            return channelBuffer.getByte(i) == 10;
        }
    };
    public static final ChannelBufferIndexFinder NOT_LF = new ChannelBufferIndexFinder() { // from class: org.jboss.netty.buffer.ChannelBufferIndexFinder.6
        @Override // org.jboss.netty.buffer.ChannelBufferIndexFinder
        public boolean find(ChannelBuffer channelBuffer, int i) {
            return channelBuffer.getByte(i) != 10;
        }
    };
    public static final ChannelBufferIndexFinder CRLF = new ChannelBufferIndexFinder() { // from class: org.jboss.netty.buffer.ChannelBufferIndexFinder.7
        @Override // org.jboss.netty.buffer.ChannelBufferIndexFinder
        public boolean find(ChannelBuffer channelBuffer, int i) {
            byte b = channelBuffer.getByte(i);
            return b == 13 || b == 10;
        }
    };
    public static final ChannelBufferIndexFinder NOT_CRLF = new ChannelBufferIndexFinder() { // from class: org.jboss.netty.buffer.ChannelBufferIndexFinder.8
        @Override // org.jboss.netty.buffer.ChannelBufferIndexFinder
        public boolean find(ChannelBuffer channelBuffer, int i) {
            byte b = channelBuffer.getByte(i);
            return (b == 13 || b == 10) ? false : true;
        }
    };
    public static final ChannelBufferIndexFinder LINEAR_WHITESPACE = new ChannelBufferIndexFinder() { // from class: org.jboss.netty.buffer.ChannelBufferIndexFinder.9
        @Override // org.jboss.netty.buffer.ChannelBufferIndexFinder
        public boolean find(ChannelBuffer channelBuffer, int i) {
            byte b = channelBuffer.getByte(i);
            return b == 32 || b == 9;
        }
    };
    public static final ChannelBufferIndexFinder NOT_LINEAR_WHITESPACE = new ChannelBufferIndexFinder() { // from class: org.jboss.netty.buffer.ChannelBufferIndexFinder.10
        @Override // org.jboss.netty.buffer.ChannelBufferIndexFinder
        public boolean find(ChannelBuffer channelBuffer, int i) {
            byte b = channelBuffer.getByte(i);
            return (b == 32 || b == 9) ? false : true;
        }
    };

    boolean find(ChannelBuffer channelBuffer, int i);
}
