package com.pudutech.remotemaintenance.utils;

import android.util.Base64;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* compiled from: FileUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/utils/FileUtil;", "", "()V", "bytesToHexString", "", "bytes", "", "compress", "primStr", "deleteFile", "", "filePath", "getFileMD5", "file", "Ljava/io/File;", "readStringFromFile", "uncompress", "compressedStr", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
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
            Intrinsics.checkExpressionValueIsNotNull(hexString, "Integer.toHexString(byte.toInt() and 0XFF)");
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
        if (primStr == null || primStr.length() == 0) {
            return primStr;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = (GZIPOutputStream) null;
        try {
            try {
                try {
                    GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(byteArrayOutputStream);
                    try {
                        byte[] bytes = primStr.getBytes(Charsets.UTF_8);
                        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                        gZIPOutputStream2.write(bytes);
                        gZIPOutputStream2.close();
                    } catch (IOException e) {
                        e = e;
                        gZIPOutputStream = gZIPOutputStream2;
                        e.printStackTrace();
                        if (gZIPOutputStream != null) {
                            gZIPOutputStream.close();
                        }
                        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
                    } catch (Throwable th) {
                        th = th;
                        gZIPOutputStream = gZIPOutputStream2;
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
                }
            } catch (IOException e3) {
                e = e3;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0057, code lost:
    
        if (r5 == null) goto L50;
     */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0064 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x005f A[EXC_TOP_SPLITTER, SYNTHETIC] */
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
                        byteArrayInputStream2 = byteArrayInputStream;
                        if (gZIPInputStream != null) {
                            try {
                                gZIPInputStream.close();
                            } catch (IOException unused3) {
                            }
                        }
                        if (byteArrayInputStream2 != null) {
                            try {
                                byteArrayInputStream2.close();
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
            if (gZIPInputStream != null) {
            }
            if (byteArrayInputStream2 != null) {
            }
            byteArrayOutputStream.close();
            throw th;
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:49:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00a8  */
    /* JADX WARN: Type inference failed for: r5v0, types: [T, java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String readStringFromFile(String filePath) {
        Exception e;
        InputStreamReader inputStreamReader;
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader2;
        BufferedReader bufferedReader;
        String str = filePath;
        if (str == null || str.length() == 0) {
            return null;
        }
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        FileInputStream fileInputStream2 = (FileInputStream) null;
        InputStreamReader inputStreamReader3 = (InputStreamReader) null;
        BufferedReader bufferedReader2 = (BufferedReader) null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                inputStreamReader2 = new InputStreamReader(fileInputStream);
                try {
                    bufferedReader = new BufferedReader(inputStreamReader2);
                } catch (Exception e2) {
                    e = e2;
                } catch (Throwable th) {
                    th = th;
                    inputStreamReader3 = inputStreamReader2;
                }
            } catch (Exception e3) {
                inputStreamReader = inputStreamReader3;
                e = e3;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e4) {
            e = e4;
            inputStreamReader = inputStreamReader3;
        } catch (Throwable th3) {
            th = th3;
        }
        try {
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            StringBuilder sb = new StringBuilder();
            while (true) {
                ?? readLine = bufferedReader.readLine();
                objectRef.element = readLine;
                if (readLine != 0) {
                    sb.append((String) objectRef.element);
                } else {
                    String sb2 = sb.toString();
                    fileInputStream.close();
                    inputStreamReader2.close();
                    bufferedReader.close();
                    return sb2;
                }
            }
        } catch (Exception e5) {
            e = e5;
            bufferedReader2 = bufferedReader;
            inputStreamReader = inputStreamReader2;
            fileInputStream2 = fileInputStream;
            try {
                e.printStackTrace();
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                return null;
            } catch (Throwable th4) {
                th = th4;
                inputStreamReader3 = inputStreamReader;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (inputStreamReader3 != null) {
                    inputStreamReader3.close();
                }
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            inputStreamReader3 = inputStreamReader2;
            bufferedReader2 = bufferedReader;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
            }
            if (inputStreamReader3 != null) {
            }
            if (bufferedReader2 != null) {
            }
            throw th;
        }
    }
}
