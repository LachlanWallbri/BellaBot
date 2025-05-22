package org.apache.commons.compress.compressors.pack200;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import org.apache.commons.compress.java.util.jar.Pack200;

/* loaded from: classes8.dex */
public class Pack200Utils {
    private Pack200Utils() {
    }

    public static void normalize(File file) throws IOException {
        normalize(file, file, null);
    }

    public static void normalize(File file, Map<String, String> map) throws IOException {
        normalize(file, file, map);
    }

    public static void normalize(File file, File file2) throws IOException {
        normalize(file, file2, null);
    }

    public static void normalize(File file, File file2, Map<String, String> map) throws IOException {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(Pack200.Packer.SEGMENT_LIMIT, "-1");
        File createTempFile = File.createTempFile("commons-compress", "pack200normalize");
        try {
            OutputStream newOutputStream = Files.newOutputStream(createTempFile.toPath(), new OpenOption[0]);
            try {
                JarFile jarFile = new JarFile(file);
                try {
                    Pack200.Packer newPacker = Pack200.newPacker();
                    newPacker.properties().putAll(map);
                    newPacker.pack(jarFile, newOutputStream);
                    jarFile.close();
                    if (newOutputStream != null) {
                        newOutputStream.close();
                    }
                    Pack200.Unpacker newUnpacker = Pack200.newUnpacker();
                    JarOutputStream jarOutputStream = new JarOutputStream(Files.newOutputStream(file2.toPath(), new OpenOption[0]));
                    try {
                        newUnpacker.unpack(createTempFile, jarOutputStream);
                        jarOutputStream.close();
                    } finally {
                    }
                } finally {
                }
            } finally {
            }
        } finally {
            if (!createTempFile.delete()) {
                createTempFile.deleteOnExit();
            }
        }
    }
}
