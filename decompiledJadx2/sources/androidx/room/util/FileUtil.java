package androidx.room.util;

import android.os.Build;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/* loaded from: classes.dex */
public class FileUtil {
    public static void copy(ReadableByteChannel readableByteChannel, FileChannel fileChannel) throws IOException {
        try {
            if (Build.VERSION.SDK_INT > 23) {
                fileChannel.transferFrom(readableByteChannel, 0L, Long.MAX_VALUE);
            } else {
                InputStream newInputStream = Channels.newInputStream(readableByteChannel);
                OutputStream newOutputStream = Channels.newOutputStream(fileChannel);
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = newInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    } else {
                        newOutputStream.write(bArr, 0, read);
                    }
                }
            }
            fileChannel.force(false);
        } finally {
            readableByteChannel.close();
            fileChannel.close();
        }
    }

    private FileUtil() {
    }
}
