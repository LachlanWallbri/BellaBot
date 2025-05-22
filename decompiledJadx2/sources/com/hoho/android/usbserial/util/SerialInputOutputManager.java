package com.hoho.android.usbserial.util;

import android.os.Process;
import android.util.Log;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public class SerialInputOutputManager implements Runnable {
    private static final int BUFSIZ = 4096;
    private Listener mListener;
    private ByteBuffer mReadBuffer;
    private final UsbSerialPort mSerialPort;
    private static final String TAG = SerialInputOutputManager.class.getSimpleName();
    public static boolean DEBUG = false;
    private int mReadTimeout = 0;
    private int mWriteTimeout = 0;
    private final Object mReadBufferLock = new Object();
    private final Object mWriteBufferLock = new Object();
    private ByteBuffer mWriteBuffer = ByteBuffer.allocate(4096);
    private int mThreadPriority = -19;
    private State mState = State.STOPPED;

    /* loaded from: classes4.dex */
    public interface Listener {
        void onNewData(byte[] data);

        void onRunError(Exception e);
    }

    /* loaded from: classes4.dex */
    public enum State {
        STOPPED,
        RUNNING,
        STOPPING
    }

    public SerialInputOutputManager(UsbSerialPort serialPort) {
        this.mSerialPort = serialPort;
        this.mReadBuffer = ByteBuffer.allocate(serialPort.getReadEndpoint().getMaxPacketSize());
    }

    public SerialInputOutputManager(UsbSerialPort serialPort, Listener listener) {
        this.mSerialPort = serialPort;
        this.mListener = listener;
        this.mReadBuffer = ByteBuffer.allocate(serialPort.getReadEndpoint().getMaxPacketSize());
    }

    public synchronized void setListener(Listener listener) {
        this.mListener = listener;
    }

    public synchronized Listener getListener() {
        return this.mListener;
    }

    public void setThreadPriority(int threadPriority) {
        if (this.mState != State.STOPPED) {
            throw new IllegalStateException("threadPriority only configurable before SerialInputOutputManager is started");
        }
        this.mThreadPriority = threadPriority;
    }

    public void setReadTimeout(int timeout) {
        if (this.mReadTimeout == 0 && timeout != 0 && this.mState != State.STOPPED) {
            throw new IllegalStateException("readTimeout only configurable before SerialInputOutputManager is started");
        }
        this.mReadTimeout = timeout;
    }

    public int getReadTimeout() {
        return this.mReadTimeout;
    }

    public void setWriteTimeout(int timeout) {
        this.mWriteTimeout = timeout;
    }

    public int getWriteTimeout() {
        return this.mWriteTimeout;
    }

    public void setReadBufferSize(int bufferSize) {
        if (getReadBufferSize() == bufferSize) {
            return;
        }
        synchronized (this.mReadBufferLock) {
            this.mReadBuffer = ByteBuffer.allocate(bufferSize);
        }
    }

    public int getReadBufferSize() {
        return this.mReadBuffer.capacity();
    }

    public void setWriteBufferSize(int bufferSize) {
        if (getWriteBufferSize() == bufferSize) {
            return;
        }
        synchronized (this.mWriteBufferLock) {
            ByteBuffer allocate = ByteBuffer.allocate(bufferSize);
            if (this.mWriteBuffer.position() > 0) {
                allocate.put(this.mWriteBuffer.array(), 0, this.mWriteBuffer.position());
            }
            this.mWriteBuffer = allocate;
        }
    }

    public int getWriteBufferSize() {
        return this.mWriteBuffer.capacity();
    }

    public void writeAsync(byte[] data) {
        synchronized (this.mWriteBufferLock) {
            this.mWriteBuffer.put(data);
        }
    }

    public void start() {
        if (this.mState != State.STOPPED) {
            throw new IllegalStateException("already started");
        }
        new Thread(this, getClass().getSimpleName()).start();
    }

    public synchronized void stop() {
        if (getState() == State.RUNNING) {
            Log.i(TAG, "Stop requested");
            this.mState = State.STOPPING;
        }
    }

    public synchronized State getState() {
        return this.mState;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this) {
            if (getState() != State.STOPPED) {
                throw new IllegalStateException("Already running");
            }
            this.mState = State.RUNNING;
        }
        Log.i(TAG, "Running ...");
        try {
            try {
                if (this.mThreadPriority != 0) {
                    Process.setThreadPriority(this.mThreadPriority);
                }
                while (getState() == State.RUNNING) {
                    step();
                }
                Log.i(TAG, "Stopping mState=" + getState());
                synchronized (this) {
                    this.mState = State.STOPPED;
                    Log.i(TAG, "Stopped");
                }
            } catch (Exception e) {
                Log.w(TAG, "Run ending due to exception: " + e.getMessage(), e);
                Listener listener = getListener();
                if (listener != null) {
                    listener.onRunError(e);
                }
                synchronized (this) {
                    this.mState = State.STOPPED;
                    Log.i(TAG, "Stopped");
                }
            }
        } catch (Throwable th) {
            synchronized (this) {
                this.mState = State.STOPPED;
                Log.i(TAG, "Stopped");
                throw th;
            }
        }
    }

    private void step() throws IOException {
        byte[] array;
        int position;
        synchronized (this.mReadBufferLock) {
            array = this.mReadBuffer.array();
        }
        int read = this.mSerialPort.read(array, this.mReadTimeout);
        if (read > 0) {
            if (DEBUG) {
                Log.d(TAG, "Read data len=" + read);
            }
            Listener listener = getListener();
            if (listener != null) {
                byte[] bArr = new byte[read];
                System.arraycopy(array, 0, bArr, 0, read);
                listener.onNewData(bArr);
            }
        }
        byte[] bArr2 = null;
        synchronized (this.mWriteBufferLock) {
            position = this.mWriteBuffer.position();
            if (position > 0) {
                bArr2 = new byte[position];
                this.mWriteBuffer.rewind();
                this.mWriteBuffer.get(bArr2, 0, position);
                this.mWriteBuffer.clear();
            }
        }
        if (bArr2 != null) {
            if (DEBUG) {
                Log.d(TAG, "Writing data len=" + position);
            }
            this.mSerialPort.write(bArr2, this.mWriteTimeout);
        }
    }
}
