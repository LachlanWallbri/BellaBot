package com.pudutech.voiceinteraction.component.utils;

import android.util.Log;
import com.xxjy.amrwbenc.AmrWbEncoder;
import io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.jetbrains.anko.DimensionsKt;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class AudioConvertTool {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.String] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:50:0x00a3 -> B:26:0x00a6). Please report as a decompilation issue!!! */
    public static void pcm2Amrwb(File file, InputStream inputStream) {
        int encode;
        if (file == null || !file.exists() || !file.isFile()) {
            return;
        }
        FileOutputStream fileOutputStream = null;
        FileOutputStream fileOutputStream2 = null;
        fileOutputStream = null;
        try {
        } catch (Exception e) {
            e.printStackTrace();
            fileOutputStream = fileOutputStream;
        }
        try {
            try {
                inputStream.skip(44L);
                byte[] bArr = {BinaryMemcacheOpcodes.GATK, BinaryMemcacheOpcodes.SASL_AUTH, 65, 77, 82, 45, 87, 66, 10};
                byte[] bArr2 = new byte[DimensionsKt.XXXHDPI];
                byte[] bArr3 = new byte[DimensionsKt.XXXHDPI];
                FileOutputStream fileOutputStream3 = new FileOutputStream(file);
                try {
                    try {
                        fileOutputStream3.write(bArr);
                        AmrWbEncoder.init();
                        while (true) {
                            int read = inputStream.read(bArr2);
                            if (read == -1) {
                                break;
                            }
                            if (read > 0 && (encode = AmrWbEncoder.encode(AmrWbEncoder.Mode.MD1825.ordinal(), bytes2shorts(bArr2), bArr3, 0)) > 0) {
                                try {
                                    fileOutputStream3.write(bArr3, 0, encode);
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                        ?? r0 = "Convert Success";
                        Log.d("freddy", "Convert Success");
                        closeStream(inputStream);
                        closeStream(fileOutputStream3);
                        AmrWbEncoder.exit();
                        fileOutputStream = r0;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream3;
                        closeStream(inputStream);
                        closeStream(fileOutputStream);
                        try {
                            AmrWbEncoder.exit();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                        throw th;
                    }
                } catch (IOException e4) {
                    e = e4;
                    fileOutputStream2 = fileOutputStream3;
                    e.printStackTrace();
                    closeStream(inputStream);
                    closeStream(fileOutputStream2);
                    AmrWbEncoder.exit();
                    fileOutputStream = fileOutputStream2;
                }
            } catch (IOException e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static short[] bytes2shorts(byte[] bArr) {
        short[] sArr = new short[bArr.length / 2];
        for (int i = 0; i < sArr.length; i++) {
            int i2 = i * 2;
            sArr[i] = (short) (((bArr[i2 + 1] & 255) << 8) | (bArr[i2] & 255));
        }
        return sArr;
    }

    public static short[] toShortArray(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length >> 1;
        short[] sArr = new short[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            sArr[i] = (short) (((bArr[i2 + 1] & 255) << 8) | (bArr[i2] & 255));
        }
        return sArr;
    }
}
