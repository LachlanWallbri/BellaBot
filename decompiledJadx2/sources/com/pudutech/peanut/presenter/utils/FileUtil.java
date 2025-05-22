package com.pudutech.peanut.presenter.utils;

import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* compiled from: FileUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/utils/FileUtil;", "", "()V", "bytesToHexString", "", "bytes", "", "compress", "primStr", "deleteFile", "", "filePath", "getFileMD5", "file", "Ljava/io/File;", "uncompress", "compressedStr", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class FileUtil {
    public static final FileUtil INSTANCE = new FileUtil();

    private FileUtil() {
    }

    public final void deleteFile(String filePath) {
        String str = filePath;
        if (str == null || str.length() == 0) {
            return;
        }
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    public final String getFileMD5(File file) {
        if (file != null && file.isFile() && file.exists()) {
            byte[] bArr = new byte[1024];
            Ref.IntRef intRef = new Ref.IntRef();
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                FileInputStream fileInputStream = new FileInputStream(file);
                while (true) {
                    int read = fileInputStream.read(bArr);
                    intRef.element = read;
                    if (read != -1) {
                        messageDigest.update(bArr, 0, intRef.element);
                    } else {
                        fileInputStream.close();
                        byte[] digest = messageDigest.digest();
                        Intrinsics.checkExpressionValueIsNotNull(digest, "digest.digest()");
                        return bytesToHexString(digest);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public final String bytesToHexString(byte[] bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bytes) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                stringBuffer.append(0);
            }
            stringBuffer.append(hexString);
        }
        String stringBuffer2 = stringBuffer.toString();
        Intrinsics.checkExpressionValueIsNotNull(stringBuffer2, "sb.toString()");
        return stringBuffer2;
    }

    public final String compress(String primStr) {
        GZIPOutputStream gZIPOutputStream;
        if (primStr == null || primStr.length() == 0) {
            return primStr;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream2 = (GZIPOutputStream) null;
        try {
            try {
                try {
                    gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                    try {
                        byte[] bytes = primStr.getBytes(Charsets.UTF_8);
                        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                        gZIPOutputStream.write(bytes);
                        gZIPOutputStream.close();
                    } catch (IOException e) {
                        e = e;
                        gZIPOutputStream2 = gZIPOutputStream;
                        e.printStackTrace();
                        if (gZIPOutputStream2 != null) {
                            gZIPOutputStream2.close();
                        }
                        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
                    } catch (Throwable th) {
                        th = th;
                        if (gZIPOutputStream != null) {
                            try {
                                gZIPOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    gZIPOutputStream = gZIPOutputStream2;
                }
            } catch (IOException e3) {
                e = e3;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0058, code lost:
    
        if (r5 == null) goto L60;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String uncompress(String compressedStr) {
        ByteArrayInputStream byteArrayInputStream;
        IOException e;
        if (compressedStr == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteArrayInputStream byteArrayInputStream2 = (ByteArrayInputStream) null;
        GZIPInputStream gZIPInputStream = (GZIPInputStream) null;
        String str = (String) null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(Base64.decode(compressedStr, 0));
            try {
                try {
                    GZIPInputStream gZIPInputStream2 = new GZIPInputStream(byteArrayInputStream);
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = gZIPInputStream2.read(bArr);
                            if (read < 0) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, read);
                        }
                        str = byteArrayOutputStream.toString();
                        try {
                            gZIPInputStream2.close();
                        } catch (IOException unused) {
                        }
                    } catch (IOException e2) {
                        e = e2;
                        gZIPInputStream = gZIPInputStream2;
                        e.printStackTrace();
                        if (gZIPInputStream != null) {
                            try {
                                gZIPInputStream.close();
                            } catch (IOException unused2) {
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        gZIPInputStream = gZIPInputStream2;
                        if (gZIPInputStream != null) {
                            try {
                                gZIPInputStream.close();
                            } catch (IOException unused3) {
                            }
                        }
                        if (byteArrayInputStream != null) {
                            try {
                                byteArrayInputStream.close();
                            } catch (IOException unused4) {
                            }
                        }
                        try {
                            byteArrayOutputStream.close();
                            throw th;
                        } catch (IOException unused5) {
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e3) {
                e = e3;
            }
        } catch (IOException e4) {
            byteArrayInputStream = byteArrayInputStream2;
            e = e4;
        } catch (Throwable th3) {
            th = th3;
            byteArrayInputStream = byteArrayInputStream2;
        }
        try {
            byteArrayInputStream.close();
        } catch (IOException unused6) {
        }
        try {
            byteArrayOutputStream.close();
        } catch (IOException unused7) {
            return str;
        }
    }
}
