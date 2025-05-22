package com.iflytek.cloud.thirdparty;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/* renamed from: com.iflytek.cloud.thirdparty.ba */
/* loaded from: classes3.dex */
public class C3716ba {
    /* renamed from: a */
    public static String m2021a(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            String str2 = new String(bArr, "utf-8");
            try {
                fileInputStream.close();
                return str2;
            } catch (IOException unused) {
                return str2;
            }
        } catch (IOException unused2) {
            return null;
        }
    }

    /* renamed from: a */
    public static int m2020a(String str, String str2, boolean z) {
        int i = 0;
        try {
            File file = new File(str);
            if (!file.exists()) {
                file.createNewFile();
            } else if (z) {
                file.delete();
                file.createNewFile();
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.write(str2.getBytes("utf-8"));
            i = (int) randomAccessFile.length();
            randomAccessFile.close();
            return i;
        } catch (IOException unused) {
            return i;
        }
    }
}
