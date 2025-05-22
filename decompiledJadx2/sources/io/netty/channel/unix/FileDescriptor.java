package io.netty.channel.unix;

import io.netty.channel.unix.Errors;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.ThrowableUtil;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public class FileDescriptor {
    private static final int STATE_ALL_MASK = 7;
    private static final int STATE_CLOSED_MASK = 1;
    private static final int STATE_INPUT_SHUTDOWN_MASK = 2;
    private static final int STATE_OUTPUT_SHUTDOWN_MASK = 4;

    /* renamed from: fd */
    final int f8461fd;
    volatile int state;
    private static final ClosedChannelException WRITE_CLOSED_CHANNEL_EXCEPTION = (ClosedChannelException) ThrowableUtil.unknownStackTrace(new ClosedChannelException(), FileDescriptor.class, "write(..)");
    private static final ClosedChannelException WRITE_ADDRESS_CLOSED_CHANNEL_EXCEPTION = (ClosedChannelException) ThrowableUtil.unknownStackTrace(new ClosedChannelException(), FileDescriptor.class, "writeAddress(..)");
    private static final ClosedChannelException WRITEV_CLOSED_CHANNEL_EXCEPTION = (ClosedChannelException) ThrowableUtil.unknownStackTrace(new ClosedChannelException(), FileDescriptor.class, "writev(..)");
    private static final ClosedChannelException WRITEV_ADDRESSES_CLOSED_CHANNEL_EXCEPTION = (ClosedChannelException) ThrowableUtil.unknownStackTrace(new ClosedChannelException(), FileDescriptor.class, "writevAddresses(..)");
    private static final ClosedChannelException READ_CLOSED_CHANNEL_EXCEPTION = (ClosedChannelException) ThrowableUtil.unknownStackTrace(new ClosedChannelException(), FileDescriptor.class, "read(..)");
    private static final ClosedChannelException READ_ADDRESS_CLOSED_CHANNEL_EXCEPTION = (ClosedChannelException) ThrowableUtil.unknownStackTrace(new ClosedChannelException(), FileDescriptor.class, "readAddress(..)");
    private static final Errors.NativeIoException WRITE_CONNECTION_RESET_EXCEPTION = (Errors.NativeIoException) ThrowableUtil.unknownStackTrace(Errors.newConnectionResetException("syscall:write", Errors.ERRNO_EPIPE_NEGATIVE), FileDescriptor.class, "write(..)");
    private static final Errors.NativeIoException WRITE_ADDRESS_CONNECTION_RESET_EXCEPTION = (Errors.NativeIoException) ThrowableUtil.unknownStackTrace(Errors.newConnectionResetException("syscall:write", Errors.ERRNO_EPIPE_NEGATIVE), FileDescriptor.class, "writeAddress(..)");
    private static final Errors.NativeIoException WRITEV_CONNECTION_RESET_EXCEPTION = (Errors.NativeIoException) ThrowableUtil.unknownStackTrace(Errors.newConnectionResetException("syscall:writev", Errors.ERRNO_EPIPE_NEGATIVE), FileDescriptor.class, "writev(..)");
    private static final Errors.NativeIoException WRITEV_ADDRESSES_CONNECTION_RESET_EXCEPTION = (Errors.NativeIoException) ThrowableUtil.unknownStackTrace(Errors.newConnectionResetException("syscall:writev", Errors.ERRNO_EPIPE_NEGATIVE), FileDescriptor.class, "writeAddresses(..)");
    private static final Errors.NativeIoException READ_CONNECTION_RESET_EXCEPTION = (Errors.NativeIoException) ThrowableUtil.unknownStackTrace(Errors.newConnectionResetException("syscall:read", Errors.ERRNO_ECONNRESET_NEGATIVE), FileDescriptor.class, "read(..)");
    private static final Errors.NativeIoException READ_ADDRESS_CONNECTION_RESET_EXCEPTION = (Errors.NativeIoException) ThrowableUtil.unknownStackTrace(Errors.newConnectionResetException("syscall:read", Errors.ERRNO_ECONNRESET_NEGATIVE), FileDescriptor.class, "readAddress(..)");
    private static final AtomicIntegerFieldUpdater<FileDescriptor> stateUpdater = AtomicIntegerFieldUpdater.newUpdater(FileDescriptor.class, "state");

    private static native int close(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int inputShutdown(int i) {
        return i | 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isClosed(int i) {
        return (i & 1) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isInputShutdown(int i) {
        return (i & 2) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isOutputShutdown(int i) {
        return (i & 4) != 0;
    }

    private static native long newPipe();

    private static native int open(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int outputShutdown(int i) {
        return i | 4;
    }

    private static native int read(int i, ByteBuffer byteBuffer, int i2, int i3);

    private static native int readAddress(int i, long j, int i2, int i3);

    private static native int write(int i, ByteBuffer byteBuffer, int i2, int i3);

    private static native int writeAddress(int i, long j, int i2, int i3);

    private static native long writev(int i, ByteBuffer[] byteBufferArr, int i2, int i3, long j);

    private static native long writevAddresses(int i, long j, int i2);

    public FileDescriptor(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("fd must be >= 0");
        }
        this.f8461fd = i;
    }

    public final int intValue() {
        return this.f8461fd;
    }

    public void close() throws IOException {
        int i;
        do {
            i = this.state;
            if (isClosed(i)) {
                return;
            }
        } while (!casState(i, i | 7));
        int close = close(this.f8461fd);
        if (close < 0) {
            throw Errors.newIOException("close", close);
        }
    }

    public boolean isOpen() {
        return !isClosed(this.state);
    }

    public final int write(ByteBuffer byteBuffer, int i, int i2) throws IOException {
        int write = write(this.f8461fd, byteBuffer, i, i2);
        return write >= 0 ? write : Errors.ioResult("write", write, WRITE_CONNECTION_RESET_EXCEPTION, WRITE_CLOSED_CHANNEL_EXCEPTION);
    }

    public final int writeAddress(long j, int i, int i2) throws IOException {
        int writeAddress = writeAddress(this.f8461fd, j, i, i2);
        return writeAddress >= 0 ? writeAddress : Errors.ioResult("writeAddress", writeAddress, WRITE_ADDRESS_CONNECTION_RESET_EXCEPTION, WRITE_ADDRESS_CLOSED_CHANNEL_EXCEPTION);
    }

    public final long writev(ByteBuffer[] byteBufferArr, int i, int i2, long j) throws IOException {
        long writev = writev(this.f8461fd, byteBufferArr, i, Math.min(Limits.IOV_MAX, i2), j);
        return writev >= 0 ? writev : Errors.ioResult("writev", (int) writev, WRITEV_CONNECTION_RESET_EXCEPTION, WRITEV_CLOSED_CHANNEL_EXCEPTION);
    }

    public final long writevAddresses(long j, int i) throws IOException {
        long writevAddresses = writevAddresses(this.f8461fd, j, i);
        return writevAddresses >= 0 ? writevAddresses : Errors.ioResult("writevAddresses", (int) writevAddresses, WRITEV_ADDRESSES_CONNECTION_RESET_EXCEPTION, WRITEV_ADDRESSES_CLOSED_CHANNEL_EXCEPTION);
    }

    public final int read(ByteBuffer byteBuffer, int i, int i2) throws IOException {
        int read = read(this.f8461fd, byteBuffer, i, i2);
        if (read > 0) {
            return read;
        }
        if (read == 0) {
            return -1;
        }
        return Errors.ioResult("read", read, READ_CONNECTION_RESET_EXCEPTION, READ_CLOSED_CHANNEL_EXCEPTION);
    }

    public final int readAddress(long j, int i, int i2) throws IOException {
        int readAddress = readAddress(this.f8461fd, j, i, i2);
        if (readAddress > 0) {
            return readAddress;
        }
        if (readAddress == 0) {
            return -1;
        }
        return Errors.ioResult("readAddress", readAddress, READ_ADDRESS_CONNECTION_RESET_EXCEPTION, READ_ADDRESS_CLOSED_CHANNEL_EXCEPTION);
    }

    public String toString() {
        return "FileDescriptor{fd=" + this.f8461fd + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof FileDescriptor) && this.f8461fd == ((FileDescriptor) obj).f8461fd;
    }

    public int hashCode() {
        return this.f8461fd;
    }

    public static FileDescriptor from(String str) throws IOException {
        ObjectUtil.checkNotNull(str, "path");
        int open = open(str);
        if (open < 0) {
            throw Errors.newIOException("open", open);
        }
        return new FileDescriptor(open);
    }

    public static FileDescriptor from(File file) throws IOException {
        return from(((File) ObjectUtil.checkNotNull(file, "file")).getPath());
    }

    public static FileDescriptor[] pipe() throws IOException {
        long newPipe = newPipe();
        if (newPipe >= 0) {
            return new FileDescriptor[]{new FileDescriptor((int) (newPipe >>> 32)), new FileDescriptor((int) newPipe)};
        }
        throw Errors.newIOException("newPipe", (int) newPipe);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean casState(int i, int i2) {
        return stateUpdater.compareAndSet(this, i, i2);
    }
}
