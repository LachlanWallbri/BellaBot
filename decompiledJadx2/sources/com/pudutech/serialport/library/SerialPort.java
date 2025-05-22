package com.pudutech.serialport.library;

import android.util.Log;
import com.iflytek.speech.UtilityConfig;
import com.pudutech.lib_update.util.ShellUtils;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* compiled from: SerialPort.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u000e\u001a\u00020\u000fH\u0086 J\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J!\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0082 J\t\u0010\u0017\u001a\u00020\u000fH\u0086 R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/serialport/library/SerialPort;", "", UtilityConfig.KEY_DEVICE_INFO, "Ljava/io/File;", "baudrate", "", "flags", "(Ljava/io/File;II)V", "fd", "Ljava/io/FileDescriptor;", "fileInputStream", "Ljava/io/FileInputStream;", "fileOutputStream", "Ljava/io/FileOutputStream;", "close", "", "getInputStream", "Ljava/io/InputStream;", "getOutputStream", "Ljava/io/OutputStream;", "open", "path", "", "tcflush", "Companion", "library_serialport_release"}, m3962k = 1, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class SerialPort {
    public static final String TAG = "SerialPort";
    private FileDescriptor fd;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;

    private final native FileDescriptor open(String path, int baudrate, int flags);

    public final native void close();

    public final native void tcflush();

    public SerialPort(File device, int i, int i2) throws SecurityException, IOException {
        Intrinsics.checkParameterIsNotNull(device, "device");
        System.loadLibrary("serial-port");
        if (!device.canRead() || !device.canWrite()) {
            try {
                Process exec = Runtime.getRuntime().exec("/system/bin/su");
                Intrinsics.checkExpressionValueIsNotNull(exec, "Runtime.getRuntime().exec(\"/system/bin/su\")");
                String str = "chmod 666 " + device.getAbsolutePath() + "\n" + ShellUtils.COMMAND_EXIT;
                OutputStream outputStream = exec.getOutputStream();
                Charset charset = Charsets.UTF_8;
                if (str == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                byte[] bytes = str.getBytes(charset);
                Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                outputStream.write(bytes);
                if (exec.waitFor() != 0 || !device.canRead() || !device.canWrite()) {
                    throw new SecurityException();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new SecurityException();
            }
        }
        String absolutePath = device.getAbsolutePath();
        Intrinsics.checkExpressionValueIsNotNull(absolutePath, "device.absolutePath");
        FileDescriptor open = open(absolutePath, i, i2);
        this.fd = open;
        if (open == null) {
            Log.e(TAG, "native open returns null");
            throw new IOException();
        }
        this.fileInputStream = new FileInputStream(this.fd);
        this.fileOutputStream = new FileOutputStream(this.fd);
    }

    public final InputStream getInputStream() {
        return this.fileInputStream;
    }

    public final OutputStream getOutputStream() {
        return this.fileOutputStream;
    }
}
