package com.loc;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: Util.java */
/* renamed from: com.loc.bh */
/* loaded from: classes4.dex */
public final class C3833bh {

    /* renamed from: a */
    public static final Charset f3676a = Charset.forName("US-ASCII");

    /* renamed from: b */
    static final Charset f3677b = Charset.forName("UTF-8");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2598a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2599a(File file) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                m2599a(file2);
            }
            if (!file2.delete()) {
                throw new IOException("failed to delete file: " + file2);
            }
        }
    }
}
