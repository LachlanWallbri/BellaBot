package org.apache.commons.compress.harmony.unpack200;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.GZIPInputStream;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;

/* loaded from: classes9.dex */
public class Archive {
    private boolean deflateHint;
    private String inputFileName;
    private InputStream inputStream;
    private FileOutputStream logFile;
    private int logLevel = 1;
    private String outputFileName;
    private final JarOutputStream outputStream;
    private boolean overrideDeflateHint;
    private boolean removePackFile;

    public Archive(String str, String str2) throws FileNotFoundException, IOException {
        this.inputFileName = str;
        this.outputFileName = str2;
        this.inputStream = new FileInputStream(str);
        this.outputStream = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(str2)));
    }

    public Archive(InputStream inputStream, JarOutputStream jarOutputStream) throws IOException {
        this.inputStream = inputStream;
        this.outputStream = jarOutputStream;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(23:1|2|3|(2:5|(1:7)(2:8|9))|11|(1:13)(1:84)|14|(2:17|15)|18|19|(4:22|(2:24|25)(1:27)|26|20)|28|29|(9:31|(2:32|(4:34|(2:37|35)|38|39)(0))|41|42|43|44|(2:56|57)|46|(3:48|(1:50)|(1:54)(2:52|53))(1:55))(2:64|(10:67|(1:69)(1:83)|70|(1:72)|73|(1:75)|76|(3:78|79|80)(1:82)|81|65))|40|41|42|43|44|(0)|46|(0)(0)|(1:(0))) */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x017e A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x015f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void unpack() throws Pack200Exception, IOException {
        FileOutputStream fileOutputStream;
        this.outputStream.setComment("PACK200");
        try {
            if (!this.inputStream.markSupported()) {
                this.inputStream = new BufferedInputStream(this.inputStream);
                if (!this.inputStream.markSupported()) {
                    throw new IllegalStateException();
                }
            }
            this.inputStream.mark(2);
            if (((this.inputStream.read() & 255) | ((this.inputStream.read() & 255) << 8)) == 35615) {
                this.inputStream.reset();
                this.inputStream = new BufferedInputStream(new GZIPInputStream(this.inputStream));
            } else {
                this.inputStream.reset();
            }
            this.inputStream.mark(4);
            int[] iArr = {202, 254, 208, 13};
            int[] iArr2 = new int[4];
            for (int i = 0; i < iArr2.length; i++) {
                iArr2[i] = this.inputStream.read();
            }
            boolean z = false;
            for (int i2 = 0; i2 < iArr.length; i2++) {
                if (iArr2[i2] != iArr[i2]) {
                    z = true;
                }
            }
            this.inputStream.reset();
            if (z) {
                JarInputStream jarInputStream = new JarInputStream(this.inputStream);
                while (true) {
                    JarEntry nextJarEntry = jarInputStream.getNextJarEntry();
                    if (nextJarEntry != null) {
                        this.outputStream.putNextEntry(nextJarEntry);
                        byte[] bArr = new byte[16384];
                        for (int read = jarInputStream.read(bArr); read != -1; read = jarInputStream.read(bArr)) {
                            this.outputStream.write(bArr, 0, read);
                        }
                        this.outputStream.closeEntry();
                    }
                }
                this.inputStream.close();
                this.outputStream.close();
                fileOutputStream = this.logFile;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception unused) {
                    }
                }
                if (this.removePackFile) {
                    return;
                }
                String str = this.inputFileName;
                if (!(str != null ? new File(str).delete() : false)) {
                    throw new Pack200Exception("Failed to delete the input file.");
                }
                return;
            }
            int i3 = 0;
            while (available(this.inputStream)) {
                i3++;
                Segment segment = new Segment();
                segment.setLogLevel(this.logLevel);
                segment.setLogStream(this.logFile != null ? this.logFile : System.out);
                segment.setPreRead(false);
                if (i3 == 1) {
                    segment.log(2, "Unpacking from " + this.inputFileName + " to " + this.outputFileName);
                }
                segment.log(2, "Reading segment " + i3);
                if (this.overrideDeflateHint) {
                    segment.overrideDeflateHint(this.deflateHint);
                }
                segment.unpack(this.inputStream, this.outputStream);
                this.outputStream.flush();
                if (this.inputStream instanceof FileInputStream) {
                    this.inputFileName = ((FileInputStream) this.inputStream).getFD().toString();
                }
            }
            this.inputStream.close();
            this.outputStream.close();
            fileOutputStream = this.logFile;
            if (fileOutputStream != null) {
            }
            if (this.removePackFile) {
            }
        } finally {
        }
    }

    private boolean available(InputStream inputStream) throws IOException {
        inputStream.mark(1);
        int read = inputStream.read();
        inputStream.reset();
        return read != -1;
    }

    public void setRemovePackFile(boolean z) {
        this.removePackFile = z;
    }

    public void setVerbose(boolean z) {
        if (z) {
            this.logLevel = 2;
        } else if (this.logLevel == 2) {
            this.logLevel = 1;
        }
    }

    public void setQuiet(boolean z) {
        if (z) {
            this.logLevel = 0;
        } else if (this.logLevel == 0) {
            this.logLevel = 0;
        }
    }

    public void setLogFile(String str) throws FileNotFoundException {
        this.logFile = new FileOutputStream(str);
    }

    public void setLogFile(String str, boolean z) throws FileNotFoundException {
        this.logFile = new FileOutputStream(str, z);
    }

    public void setDeflateHint(boolean z) {
        this.overrideDeflateHint = true;
        this.deflateHint = z;
    }
}
