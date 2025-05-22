package com.pudutech.mirsdk.compat.topo;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.base.Pdlog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: FileTools.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0006H\u0007J \u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/mirsdk/compat/topo/FileTools;", "", "()V", "TAG", "", "copyZip", "", "zipFilePath", "fileName", TypedValues.Attributes.S_TARGET, "targetName", RequestParameters.SUBRESOURCE_DELETE, "fileToZip", "sourceFilePath", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class FileTools {
    public static final FileTools INSTANCE = new FileTools();
    private static final String TAG = "CompatMap";

    private FileTools() {
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x0133 A[Catch: IOException -> 0x012f, TRY_LEAVE, TryCatch #4 {IOException -> 0x012f, blocks: (B:70:0x012b, B:63:0x0133), top: B:69:0x012b }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x012b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName) {
        ZipOutputStream zipOutputStream;
        Intrinsics.checkParameterIsNotNull(sourceFilePath, "sourceFilePath");
        Intrinsics.checkParameterIsNotNull(zipFilePath, "zipFilePath");
        Intrinsics.checkParameterIsNotNull(fileName, "fileName");
        File file = new File(sourceFilePath);
        BufferedInputStream bufferedInputStream = (BufferedInputStream) null;
        ZipOutputStream zipOutputStream2 = (ZipOutputStream) null;
        boolean z = false;
        try {
            if (!file.exists()) {
                Pdlog.m3275i(TAG, "待压缩的文件目录：" + sourceFilePath + "不存在.");
            } else {
                try {
                    File file2 = new File(zipFilePath);
                    if (!file2.exists()) {
                        file2.mkdirs();
                    }
                    File file3 = new File(zipFilePath + '/' + fileName);
                    File[] listFiles = file.listFiles();
                    if (listFiles != null && listFiles.length >= 1) {
                        Pdlog.m3275i(TAG, "待压缩的文件目录：" + sourceFilePath + "压缩到。。。。" + file3);
                        zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file3)));
                        try {
                            byte[] bArr = new byte[TarConstants.DEFAULT_BLKSIZE];
                            int length = listFiles.length;
                            BufferedInputStream bufferedInputStream2 = bufferedInputStream;
                            int i = 0;
                            while (i < length) {
                                try {
                                    File file4 = listFiles[i];
                                    Intrinsics.checkExpressionValueIsNotNull(file4, "sourceFiles[i]");
                                    zipOutputStream.putNextEntry(new ZipEntry(file4.getName()));
                                    BufferedInputStream bufferedInputStream3 = new BufferedInputStream(new FileInputStream(listFiles[i]), TarConstants.DEFAULT_BLKSIZE);
                                    while (true) {
                                        try {
                                            int read = bufferedInputStream3.read(bArr, 0, TarConstants.DEFAULT_BLKSIZE);
                                            if (read != -1) {
                                                zipOutputStream.write(bArr, 0, read);
                                            }
                                        } catch (FileNotFoundException e) {
                                            e = e;
                                            e.printStackTrace();
                                            throw new RuntimeException(e);
                                        } catch (IOException e2) {
                                            e = e2;
                                            e.printStackTrace();
                                            throw new RuntimeException(e);
                                        } catch (Throwable th) {
                                            th = th;
                                            zipOutputStream2 = zipOutputStream;
                                            bufferedInputStream = bufferedInputStream3;
                                            if (bufferedInputStream != null) {
                                                try {
                                                    bufferedInputStream.close();
                                                } catch (IOException e3) {
                                                    e3.printStackTrace();
                                                    throw new RuntimeException(e3);
                                                }
                                            }
                                            if (zipOutputStream2 != null) {
                                                zipOutputStream2.close();
                                            }
                                            throw th;
                                        }
                                    }
                                    i++;
                                    bufferedInputStream2 = bufferedInputStream3;
                                } catch (FileNotFoundException e4) {
                                    e = e4;
                                } catch (IOException e5) {
                                    e = e5;
                                } catch (Throwable th2) {
                                    th = th2;
                                    zipOutputStream2 = zipOutputStream;
                                    bufferedInputStream = bufferedInputStream2;
                                }
                            }
                            bufferedInputStream = bufferedInputStream2;
                            z = true;
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e6) {
                                    e6.printStackTrace();
                                    throw new RuntimeException(e6);
                                }
                            }
                            if (zipOutputStream != null) {
                                zipOutputStream.close();
                            }
                        } catch (FileNotFoundException e7) {
                            e = e7;
                        } catch (IOException e8) {
                            e = e8;
                        } catch (Throwable th3) {
                            th = th3;
                            zipOutputStream2 = zipOutputStream;
                        }
                    }
                    Pdlog.m3275i(TAG, "待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
                    zipOutputStream = zipOutputStream2;
                    if (bufferedInputStream != null) {
                    }
                    if (zipOutputStream != null) {
                    }
                } catch (FileNotFoundException e9) {
                    e = e9;
                } catch (IOException e10) {
                    e = e10;
                }
            }
            return z;
        } catch (Throwable th4) {
            th = th4;
        }
    }

    @JvmStatic
    public static final boolean copyZip(String zipFilePath, String fileName, String target, String targetName, boolean delete) {
        Intrinsics.checkParameterIsNotNull(zipFilePath, "zipFilePath");
        Intrinsics.checkParameterIsNotNull(fileName, "fileName");
        Intrinsics.checkParameterIsNotNull(target, "target");
        Intrinsics.checkParameterIsNotNull(targetName, "targetName");
        try {
            File file = new File(zipFilePath + '/' + fileName);
            File file2 = new File(target);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(target + '/' + targetName);
            byte[] bArr = new byte[1444];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            fileInputStream.close();
            fileOutputStream.close();
            if (!delete) {
                return true;
            }
            Pdlog.m3275i(TAG, "删除文件：" + file.delete() + "     " + file.getAbsolutePath());
            return true;
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "复制单个文件操作出错" + e);
            return false;
        }
    }
}
