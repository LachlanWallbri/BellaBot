package android.serialport;

import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class SerialPort {
    private static final String TAG = "Lidar";
    private static String sSuPath;
    private FileDescriptor mFd;
    private FileInputStream mFileInputStream;
    private FileOutputStream mFileOutputStream;

    private static native FileDescriptor open(String str, int i, int i2);

    public native void close();

    public static void setSuPath(String str) {
        if (str == null) {
            return;
        }
        sSuPath = str;
    }

    public SerialPort(File file, int i, int i2) throws SecurityException, IOException {
        if (!file.canRead() || !file.canWrite()) {
            try {
                Tools.execCommand("chmod 666 " + (sSuPath == null ? file.getAbsolutePath() : sSuPath), true);
                if (!file.canRead() || !file.canWrite()) {
                    Pdlog.m3274e("Lidar", "no device permission");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Pdlog.m3274e("Lidar", e);
            }
        }
        this.mFd = open(file.getAbsolutePath(), i, i2);
        Pdlog.m3273d("Lidar", "open device done. fd=" + this.mFd);
        if (this.mFd == null) {
            Pdlog.m3274e("Lidar", "native open returns null");
        } else {
            this.mFileInputStream = new FileInputStream(this.mFd);
            this.mFileOutputStream = new FileOutputStream(this.mFd);
        }
    }

    public SerialPort(String str, int i, int i2) throws SecurityException, IOException {
        this(new File(str), i, i2);
    }

    public SerialPort(File file, int i) throws SecurityException, IOException {
        this(file, i, 0);
    }

    public SerialPort(String str, int i) throws SecurityException, IOException {
        this(new File(str), i, 0);
    }

    public InputStream getInputStream() {
        return this.mFileInputStream;
    }

    public OutputStream getOutputStream() {
        return this.mFileOutputStream;
    }

    static {
        System.loadLibrary("serial_port");
    }
}
