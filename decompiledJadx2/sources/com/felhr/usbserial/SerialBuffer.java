package com.felhr.usbserial;

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public class SerialBuffer {
    public static final int DEFAULT_READ_BUFFER_SIZE = 16384;
    public static final int DEFAULT_WRITE_BUFFER_SIZE = 16384;
    private ByteBuffer readBuffer;
    private byte[] readBuffer_compatible;
    private boolean debugging = false;
    private SynchronizedBuffer writeBuffer = new SynchronizedBuffer();

    public SerialBuffer(boolean z) {
        if (z) {
            this.readBuffer = ByteBuffer.allocate(16384);
        } else {
            this.readBuffer_compatible = new byte[16384];
        }
    }

    public void debug(boolean z) {
        this.debugging = z;
    }

    public void putReadBuffer(ByteBuffer byteBuffer) {
        synchronized (this) {
            try {
                this.readBuffer.put(byteBuffer);
            } catch (BufferOverflowException unused) {
            }
        }
    }

    public ByteBuffer getReadBuffer() {
        ByteBuffer byteBuffer;
        synchronized (this) {
            byteBuffer = this.readBuffer;
        }
        return byteBuffer;
    }

    public byte[] getDataReceived() {
        byte[] bArr;
        synchronized (this) {
            int position = this.readBuffer.position();
            bArr = new byte[position];
            this.readBuffer.position(0);
            this.readBuffer.get(bArr, 0, position);
            if (this.debugging) {
                UsbSerialDebugger.printReadLogGet(bArr, true);
            }
        }
        return bArr;
    }

    public void clearReadBuffer() {
        synchronized (this) {
            this.readBuffer.clear();
        }
    }

    public byte[] getWriteBuffer() {
        return this.writeBuffer.get();
    }

    public void putWriteBuffer(byte[] bArr) {
        this.writeBuffer.put(bArr);
    }

    public void resetWriteBuffer() {
        this.writeBuffer.reset();
    }

    public byte[] getBufferCompatible() {
        return this.readBuffer_compatible;
    }

    public byte[] getDataReceivedCompatible(int i) {
        return Arrays.copyOfRange(this.readBuffer_compatible, 0, i);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes.dex */
    private class SynchronizedBuffer {
        private byte[] buffer = new byte[16384];
        private int position = -1;

        public SynchronizedBuffer() {
        }

        public synchronized void put(byte[] bArr) {
            if (this.position == -1) {
                this.position = 0;
            }
            if (SerialBuffer.this.debugging) {
                UsbSerialDebugger.printLogPut(bArr, true);
            }
            if (this.position + bArr.length > 16383) {
                if (this.position < 16384) {
                    System.arraycopy(bArr, 0, this.buffer, this.position, 16384 - this.position);
                }
                this.position = 16384;
                notify();
            } else {
                System.arraycopy(bArr, 0, this.buffer, this.position, bArr.length);
                this.position += bArr.length;
                notify();
            }
        }

        public synchronized byte[] get() {
            byte[] copyOfRange;
            if (this.position == -1) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            copyOfRange = Arrays.copyOfRange(this.buffer, 0, this.position);
            if (SerialBuffer.this.debugging) {
                UsbSerialDebugger.printLogGet(copyOfRange, true);
            }
            this.position = -1;
            return copyOfRange;
        }

        public synchronized void reset() {
            this.position = -1;
        }
    }
}
