package io.grpc.okhttp;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import com.pudutech.mirsdk.compat.topo.MapElement;
import io.grpc.okhttp.internal.framed.FrameWriter;
import java.io.IOException;
import javax.annotation.Nullable;
import okio.Buffer;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class OutboundFlowController {
    private final FrameWriter frameWriter;
    private final OkHttpClientTransport transport;
    private int initialWindowSize = 65535;
    private final OutboundFlowState connectionState = new OutboundFlowState(0, 65535);

    /* JADX INFO: Access modifiers changed from: package-private */
    public OutboundFlowController(OkHttpClientTransport okHttpClientTransport, FrameWriter frameWriter) {
        this.transport = (OkHttpClientTransport) Preconditions.checkNotNull(okHttpClientTransport, NotificationCompat.CATEGORY_TRANSPORT);
        this.frameWriter = (FrameWriter) Preconditions.checkNotNull(frameWriter, "frameWriter");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean initialOutboundWindowSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Invalid initial window size: " + i);
        }
        int i2 = i - this.initialWindowSize;
        this.initialWindowSize = i;
        for (OkHttpClientStream okHttpClientStream : this.transport.getActiveStreams()) {
            OutboundFlowState outboundFlowState = (OutboundFlowState) okHttpClientStream.getOutboundFlowState();
            if (outboundFlowState == null) {
                okHttpClientStream.setOutboundFlowState(new OutboundFlowState(this, okHttpClientStream, this.initialWindowSize));
            } else {
                outboundFlowState.incrementStreamWindow(i2);
            }
        }
        return i2 > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int windowUpdate(@Nullable OkHttpClientStream okHttpClientStream, int i) {
        if (okHttpClientStream == null) {
            int incrementStreamWindow = this.connectionState.incrementStreamWindow(i);
            writeStreams();
            return incrementStreamWindow;
        }
        OutboundFlowState state = state(okHttpClientStream);
        int incrementStreamWindow2 = state.incrementStreamWindow(i);
        WriteStatus writeStatus = new WriteStatus();
        state.writeBytes(state.writableWindow(), writeStatus);
        if (writeStatus.hasWritten()) {
            flush();
        }
        return incrementStreamWindow2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void data(boolean z, int i, Buffer buffer, boolean z2) {
        Preconditions.checkNotNull(buffer, MapElement.Source.SOURCE);
        OkHttpClientStream stream = this.transport.getStream(i);
        if (stream == null) {
            return;
        }
        OutboundFlowState state = state(stream);
        int writableWindow = state.writableWindow();
        boolean hasPendingData = state.hasPendingData();
        int size = (int) buffer.size();
        if (!hasPendingData && writableWindow >= size) {
            state.write(buffer, size, z);
        } else {
            if (!hasPendingData && writableWindow > 0) {
                state.write(buffer, writableWindow, false);
            }
            state.enqueue(buffer, (int) buffer.size(), z);
        }
        if (z2) {
            flush();
        }
    }

    void flush() {
        try {
            this.frameWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private OutboundFlowState state(OkHttpClientStream okHttpClientStream) {
        OutboundFlowState outboundFlowState = (OutboundFlowState) okHttpClientStream.getOutboundFlowState();
        if (outboundFlowState != null) {
            return outboundFlowState;
        }
        OutboundFlowState outboundFlowState2 = new OutboundFlowState(this, okHttpClientStream, this.initialWindowSize);
        okHttpClientStream.setOutboundFlowState(outboundFlowState2);
        return outboundFlowState2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeStreams() {
        int i;
        OkHttpClientStream[] activeStreams = this.transport.getActiveStreams();
        int window = this.connectionState.window();
        int length = activeStreams.length;
        while (true) {
            i = 0;
            if (length <= 0 || window <= 0) {
                break;
            }
            int ceil = (int) Math.ceil(window / length);
            int i2 = 0;
            while (i < length && window > 0) {
                OkHttpClientStream okHttpClientStream = activeStreams[i];
                OutboundFlowState state = state(okHttpClientStream);
                int min = Math.min(window, Math.min(state.unallocatedBytes(), ceil));
                if (min > 0) {
                    state.allocateBytes(min);
                    window -= min;
                }
                if (state.unallocatedBytes() > 0) {
                    activeStreams[i2] = okHttpClientStream;
                    i2++;
                }
                i++;
            }
            length = i2;
        }
        WriteStatus writeStatus = new WriteStatus();
        OkHttpClientStream[] activeStreams2 = this.transport.getActiveStreams();
        int length2 = activeStreams2.length;
        while (i < length2) {
            OutboundFlowState state2 = state(activeStreams2[i]);
            state2.writeBytes(state2.allocatedBytes(), writeStatus);
            state2.clearAllocatedBytes();
            i++;
        }
        if (writeStatus.hasWritten()) {
            flush();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class WriteStatus {
        int numWrites;

        private WriteStatus() {
        }

        void incrementNumWrites() {
            this.numWrites++;
        }

        boolean hasWritten() {
            return this.numWrites > 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class OutboundFlowState {
        int allocatedBytes;
        boolean pendingBufferHasEndOfStream;
        final Buffer pendingWriteBuffer;
        OkHttpClientStream stream;
        final int streamId;
        int window;

        OutboundFlowState(int i, int i2) {
            this.pendingBufferHasEndOfStream = false;
            this.streamId = i;
            this.window = i2;
            this.pendingWriteBuffer = new Buffer();
        }

        OutboundFlowState(OutboundFlowController outboundFlowController, OkHttpClientStream okHttpClientStream, int i) {
            this(okHttpClientStream.m3922id(), i);
            this.stream = okHttpClientStream;
        }

        int window() {
            return this.window;
        }

        void allocateBytes(int i) {
            this.allocatedBytes += i;
        }

        int allocatedBytes() {
            return this.allocatedBytes;
        }

        int unallocatedBytes() {
            return streamableBytes() - this.allocatedBytes;
        }

        void clearAllocatedBytes() {
            this.allocatedBytes = 0;
        }

        int incrementStreamWindow(int i) {
            if (i > 0 && Integer.MAX_VALUE - i < this.window) {
                throw new IllegalArgumentException("Window size overflow for stream: " + this.streamId);
            }
            this.window += i;
            return this.window;
        }

        int writableWindow() {
            return Math.min(this.window, OutboundFlowController.this.connectionState.window());
        }

        int streamableBytes() {
            return Math.max(0, Math.min(this.window, (int) this.pendingWriteBuffer.size()));
        }

        boolean hasPendingData() {
            return this.pendingWriteBuffer.size() > 0;
        }

        int writeBytes(int i, WriteStatus writeStatus) {
            int min = Math.min(i, writableWindow());
            int i2 = 0;
            while (hasPendingData() && min > 0) {
                if (min >= this.pendingWriteBuffer.size()) {
                    i2 += (int) this.pendingWriteBuffer.size();
                    Buffer buffer = this.pendingWriteBuffer;
                    write(buffer, (int) buffer.size(), this.pendingBufferHasEndOfStream);
                } else {
                    i2 += min;
                    write(this.pendingWriteBuffer, min, false);
                }
                writeStatus.incrementNumWrites();
                min = Math.min(i - i2, writableWindow());
            }
            return i2;
        }

        void write(Buffer buffer, int i, boolean z) {
            do {
                int min = Math.min(i, OutboundFlowController.this.frameWriter.maxDataLength());
                int i2 = -min;
                OutboundFlowController.this.connectionState.incrementStreamWindow(i2);
                incrementStreamWindow(i2);
                try {
                    OutboundFlowController.this.frameWriter.data(buffer.size() == ((long) min) && z, this.streamId, buffer, min);
                    this.stream.transportState().onSentBytes(min);
                    i -= min;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } while (i > 0);
        }

        void enqueue(Buffer buffer, int i, boolean z) {
            this.pendingWriteBuffer.write(buffer, i);
            this.pendingBufferHasEndOfStream |= z;
        }
    }
}
