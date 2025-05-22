package org.apache.commons.compress.harmony.unpack200;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarOutputStream;
import org.apache.commons.compress.harmony.pack200.Pack200Adapter;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.java.util.jar.Pack200;

/* loaded from: classes9.dex */
public class Pack200UnpackerAdapter extends Pack200Adapter implements Pack200.Unpacker {
    @Override // org.apache.commons.compress.java.util.jar.Pack200.Unpacker
    public void unpack(InputStream inputStream, JarOutputStream jarOutputStream) throws IOException {
        if (inputStream == null || jarOutputStream == null) {
            throw new IllegalArgumentException("Must specify both input and output streams");
        }
        completed(0.0d);
        try {
            new Archive(inputStream, jarOutputStream).unpack();
            completed(1.0d);
            inputStream.close();
        } catch (Pack200Exception e) {
            throw new IOException("Failed to unpack Jar:" + String.valueOf(e));
        }
    }

    @Override // org.apache.commons.compress.java.util.jar.Pack200.Unpacker
    public void unpack(File file, JarOutputStream jarOutputStream) throws IOException {
        if (file == null || jarOutputStream == null) {
            throw new IllegalArgumentException("Must specify both input and output streams");
        }
        int length = (int) file.length();
        if (length <= 0 || length >= 8192) {
            length = 8192;
        }
        unpack(new BufferedInputStream(new FileInputStream(file), length), jarOutputStream);
    }
}
