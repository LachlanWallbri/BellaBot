package org.jboss.netty.channel.socket.nio;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.WritableByteChannel;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.CompositeChannelBuffer;
import org.jboss.netty.channel.DefaultFileRegion;
import org.jboss.netty.channel.FileRegion;

/* loaded from: classes7.dex */
final class SocketSendBufferPool {
    private static final int ALIGN_MASK = 15;
    private static final int ALIGN_SHIFT = 4;
    private static final int DEFAULT_PREALLOCATION_SIZE = 65536;
    private static final SendBuffer EMPTY_BUFFER = new EmptySendBuffer();
    Preallocation current = new Preallocation(65536);
    PreallocationRef poolHead;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public interface SendBuffer {
        boolean finished();

        void release();

        long totalBytes();

        long transferTo(DatagramChannel datagramChannel, SocketAddress socketAddress) throws IOException;

        long transferTo(WritableByteChannel writableByteChannel) throws IOException;

        long writtenBytes();
    }

    private static int align(int i) {
        int i2 = i >>> 4;
        if ((i & 15) != 0) {
            i2++;
        }
        return i2 << 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SendBuffer acquire(Object obj) {
        if (obj instanceof ChannelBuffer) {
            return acquire((ChannelBuffer) obj);
        }
        if (obj instanceof FileRegion) {
            return acquire((FileRegion) obj);
        }
        throw new IllegalArgumentException("unsupported message type: " + obj.getClass());
    }

    private SendBuffer acquire(FileRegion fileRegion) {
        if (fileRegion.getCount() == 0) {
            return EMPTY_BUFFER;
        }
        return new FileSendBuffer(fileRegion);
    }

    private SendBuffer acquire(ChannelBuffer channelBuffer) {
        PooledSendBuffer pooledSendBuffer;
        int readableBytes = channelBuffer.readableBytes();
        if (readableBytes == 0) {
            return EMPTY_BUFFER;
        }
        if ((channelBuffer instanceof CompositeChannelBuffer) && ((CompositeChannelBuffer) channelBuffer).useGathering()) {
            return new GatheringSendBuffer(channelBuffer.toByteBuffers());
        }
        if (channelBuffer.isDirect()) {
            return new UnpooledSendBuffer(channelBuffer.toByteBuffer());
        }
        if (channelBuffer.readableBytes() > 65536) {
            return new UnpooledSendBuffer(channelBuffer.toByteBuffer());
        }
        Preallocation preallocation = this.current;
        ByteBuffer byteBuffer = preallocation.buffer;
        int remaining = byteBuffer.remaining();
        if (readableBytes < remaining) {
            int position = byteBuffer.position() + readableBytes;
            ByteBuffer duplicate = byteBuffer.duplicate();
            byteBuffer.position(align(position));
            duplicate.limit(position);
            preallocation.refCnt++;
            pooledSendBuffer = new PooledSendBuffer(preallocation, duplicate);
        } else if (readableBytes > remaining) {
            Preallocation preallocation2 = getPreallocation();
            this.current = preallocation2;
            ByteBuffer byteBuffer2 = preallocation2.buffer;
            ByteBuffer duplicate2 = byteBuffer2.duplicate();
            byteBuffer2.position(align(readableBytes));
            duplicate2.limit(readableBytes);
            preallocation2.refCnt++;
            pooledSendBuffer = new PooledSendBuffer(preallocation2, duplicate2);
        } else {
            preallocation.refCnt++;
            this.current = getPreallocation0();
            pooledSendBuffer = new PooledSendBuffer(preallocation, preallocation.buffer);
        }
        ByteBuffer byteBuffer3 = pooledSendBuffer.buffer;
        byteBuffer3.mark();
        channelBuffer.getBytes(channelBuffer.readerIndex(), byteBuffer3);
        byteBuffer3.reset();
        return pooledSendBuffer;
    }

    private Preallocation getPreallocation() {
        Preallocation preallocation = this.current;
        if (preallocation.refCnt == 0) {
            preallocation.buffer.clear();
            return preallocation;
        }
        return getPreallocation0();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x000e, code lost:
    
        r2.poolHead = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0010, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x001c, code lost:
    
        return new org.jboss.netty.channel.socket.nio.SocketSendBufferPool.Preallocation(r2, 65536);
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0002, code lost:
    
        if (r0 != null) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0004, code lost:
    
        r1 = r0.get();
        r0 = r0.next;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000c, code lost:
    
        if (r1 == null) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0011, code lost:
    
        if (r0 != null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0013, code lost:
    
        r2.poolHead = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Preallocation getPreallocation0() {
        PreallocationRef preallocationRef = this.poolHead;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public final class Preallocation {
        final ByteBuffer buffer;
        int refCnt;

        Preallocation(int i) {
            this.buffer = ByteBuffer.allocateDirect(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public final class PreallocationRef extends SoftReference<Preallocation> {
        final PreallocationRef next;

        PreallocationRef(Preallocation preallocation, PreallocationRef preallocationRef) {
            super(preallocation);
            this.next = preallocationRef;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class UnpooledSendBuffer implements SendBuffer {
        final ByteBuffer buffer;
        final int initialPos;

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public void release() {
        }

        UnpooledSendBuffer(ByteBuffer byteBuffer) {
            this.buffer = byteBuffer;
            this.initialPos = byteBuffer.position();
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public final boolean finished() {
            return !this.buffer.hasRemaining();
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public final long writtenBytes() {
            return this.buffer.position() - this.initialPos;
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public final long totalBytes() {
            return this.buffer.limit() - this.initialPos;
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public final long transferTo(WritableByteChannel writableByteChannel) throws IOException {
            return writableByteChannel.write(this.buffer);
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public final long transferTo(DatagramChannel datagramChannel, SocketAddress socketAddress) throws IOException {
            return datagramChannel.send(this.buffer, socketAddress);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public final class PooledSendBuffer implements SendBuffer {
        final ByteBuffer buffer;
        final int initialPos;
        private final Preallocation parent;

        PooledSendBuffer(Preallocation preallocation, ByteBuffer byteBuffer) {
            this.parent = preallocation;
            this.buffer = byteBuffer;
            this.initialPos = byteBuffer.position();
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public boolean finished() {
            return !this.buffer.hasRemaining();
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public long writtenBytes() {
            return this.buffer.position() - this.initialPos;
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public long totalBytes() {
            return this.buffer.limit() - this.initialPos;
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public long transferTo(WritableByteChannel writableByteChannel) throws IOException {
            return writableByteChannel.write(this.buffer);
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public long transferTo(DatagramChannel datagramChannel, SocketAddress socketAddress) throws IOException {
            return datagramChannel.send(this.buffer, socketAddress);
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public void release() {
            Preallocation preallocation = this.parent;
            int i = preallocation.refCnt - 1;
            preallocation.refCnt = i;
            if (i == 0) {
                preallocation.buffer.clear();
                if (preallocation != SocketSendBufferPool.this.current) {
                    SocketSendBufferPool socketSendBufferPool = SocketSendBufferPool.this;
                    socketSendBufferPool.poolHead = new PreallocationRef(preallocation, socketSendBufferPool.poolHead);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class GatheringSendBuffer implements SendBuffer {
        private final ByteBuffer[] buffers;
        private final int last;
        private final int total;
        private long written;

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public void release() {
        }

        GatheringSendBuffer(ByteBuffer[] byteBufferArr) {
            this.buffers = byteBufferArr;
            this.last = byteBufferArr.length - 1;
            int i = 0;
            for (ByteBuffer byteBuffer : byteBufferArr) {
                i += byteBuffer.remaining();
            }
            this.total = i;
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public boolean finished() {
            return !this.buffers[this.last].hasRemaining();
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public long writtenBytes() {
            return this.written;
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public long totalBytes() {
            return this.total;
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public long transferTo(WritableByteChannel writableByteChannel) throws IOException {
            if (writableByteChannel instanceof GatheringByteChannel) {
                long write = ((GatheringByteChannel) writableByteChannel).write(this.buffers);
                this.written += write;
                return write;
            }
            int i = 0;
            for (ByteBuffer byteBuffer : this.buffers) {
                if (byteBuffer.hasRemaining()) {
                    int write2 = writableByteChannel.write(byteBuffer);
                    if (write2 == 0) {
                        break;
                    }
                    i += write2;
                }
            }
            long j = i;
            this.written += j;
            return j;
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public long transferTo(DatagramChannel datagramChannel, SocketAddress socketAddress) throws IOException {
            int i = 0;
            for (ByteBuffer byteBuffer : this.buffers) {
                if (byteBuffer.hasRemaining()) {
                    int send = datagramChannel.send(byteBuffer, socketAddress);
                    if (send == 0) {
                        break;
                    }
                    i += send;
                }
            }
            long j = i;
            this.written += j;
            return j;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public final class FileSendBuffer implements SendBuffer {
        private final FileRegion file;
        private long writtenBytes;

        FileSendBuffer(FileRegion fileRegion) {
            this.file = fileRegion;
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public boolean finished() {
            return this.writtenBytes >= this.file.getCount();
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public long writtenBytes() {
            return this.writtenBytes;
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public long totalBytes() {
            return this.file.getCount();
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public long transferTo(WritableByteChannel writableByteChannel) throws IOException {
            long transferTo = this.file.transferTo(writableByteChannel, this.writtenBytes);
            this.writtenBytes += transferTo;
            return transferTo;
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public long transferTo(DatagramChannel datagramChannel, SocketAddress socketAddress) throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public void release() {
            FileRegion fileRegion = this.file;
            if ((fileRegion instanceof DefaultFileRegion) && ((DefaultFileRegion) fileRegion).releaseAfterTransfer()) {
                this.file.releaseExternalResources();
            }
        }
    }

    /* loaded from: classes7.dex */
    static final class EmptySendBuffer implements SendBuffer {
        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public boolean finished() {
            return true;
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public void release() {
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public long totalBytes() {
            return 0L;
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public long transferTo(DatagramChannel datagramChannel, SocketAddress socketAddress) throws IOException {
            return 0L;
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public long transferTo(WritableByteChannel writableByteChannel) throws IOException {
            return 0L;
        }

        @Override // org.jboss.netty.channel.socket.nio.SocketSendBufferPool.SendBuffer
        public long writtenBytes() {
            return 0L;
        }

        EmptySendBuffer() {
        }
    }
}
