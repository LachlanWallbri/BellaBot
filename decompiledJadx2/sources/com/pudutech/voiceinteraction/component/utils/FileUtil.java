package com.pudutech.voiceinteraction.component.utils;

import android.content.Context;
import com.pudutech.voiceinteraction.component.log.LogProxy;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: FileUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004J\u001e\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004J\u000e\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/FileUtil;", "", "()V", "TAG", "", "closeIO", "", "closeable", "Ljava/io/Closeable;", "copyAssetsFileToSDCard", "", "context", "Landroid/content/Context;", "assetsFilePath", "filePath", "isAssetsFileExists", "assetsPath", "assetsFileName", "readFile", "inputStream", "Ljava/io/InputStream;", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class FileUtil {
    public static final FileUtil INSTANCE = new FileUtil();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private FileUtil() {
    }

    public final boolean copyAssetsFileToSDCard(Context context, String assetsFilePath, String filePath) {
        FileOutputStream fileOutputStream;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(assetsFilePath, "assetsFilePath");
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        InputStream inputStream = (InputStream) null;
        FileOutputStream fileOutputStream2 = (FileOutputStream) null;
        try {
            try {
                inputStream = context.getAssets().open(assetsFilePath);
                byte[] bArr = new byte[1024];
                fileOutputStream = new FileOutputStream(filePath);
                try {
                    Ref.IntRef intRef = new Ref.IntRef();
                    while (true) {
                        int read = inputStream.read(bArr);
                        intRef.element = read;
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, intRef.element);
                    }
                    fileOutputStream.flush();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    fileOutputStream.close();
                    return true;
                } catch (Exception e) {
                    e = e;
                    fileOutputStream2 = fileOutputStream;
                    e.printStackTrace();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public final boolean isAssetsFileExists(Context context, String assetsPath, String assetsFileName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(assetsPath, "assetsPath");
        Intrinsics.checkParameterIsNotNull(assetsFileName, "assetsFileName");
        try {
            String[] list = context.getAssets().list(assetsPath);
            if (list != null) {
                for (String str : list) {
                    if (Intrinsics.areEqual(str, assetsFileName)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.Object, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r9v0, types: [com.pudutech.voiceinteraction.component.utils.FileUtil] */
    public final String readFile(InputStream inputStream) {
        Closeable closeable;
        Exception exc;
        Exception e;
        Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
        StringBuilder sb = new StringBuilder();
        Object obj = (BufferedReader) null;
        InputStreamReader inputStreamReader = (InputStreamReader) null;
        try {
            try {
                closeable = new InputStreamReader((InputStream) inputStream, "utf-8");
            } catch (Exception e2) {
                exc = e2;
                closeable = inputStreamReader;
                obj = obj;
            } catch (Throwable th) {
                th = th;
                closeable = inputStreamReader;
            }
            try {
                BufferedReader bufferedReader = new BufferedReader((Reader) closeable);
                try {
                    String readLine = bufferedReader.readLine();
                    while (readLine != null) {
                        sb.append(readLine);
                        readLine = bufferedReader.readLine();
                    }
                    closeIO(bufferedReader);
                    obj = readLine;
                } catch (Exception e3) {
                    exc = e3;
                    obj = bufferedReader;
                    e = exc;
                    LogProxy.INSTANCE.m3306e(TAG, "readFile e = " + e.getMessage());
                    Closeable closeable2 = (Closeable) obj;
                    closeIO(closeable2);
                    obj = closeable2;
                    closeable = closeable;
                    closeIO(closeable);
                    inputStream = (Closeable) inputStream;
                    closeIO(inputStream);
                    String sb2 = sb.toString();
                    Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
                    return sb2;
                } catch (Throwable th2) {
                    th = th2;
                    obj = bufferedReader;
                    closeIO((Closeable) obj);
                    closeIO(closeable);
                    closeIO((Closeable) inputStream);
                    throw th;
                }
            } catch (Exception e4) {
                e = e4;
                LogProxy.INSTANCE.m3306e(TAG, "readFile e = " + e.getMessage());
                Closeable closeable22 = (Closeable) obj;
                closeIO(closeable22);
                obj = closeable22;
                closeable = closeable;
                closeIO(closeable);
                inputStream = (Closeable) inputStream;
                closeIO(inputStream);
                String sb22 = sb.toString();
                Intrinsics.checkExpressionValueIsNotNull(sb22, "sb.toString()");
                return sb22;
            }
            closeable = closeable;
            closeIO(closeable);
            inputStream = (Closeable) inputStream;
            closeIO(inputStream);
            String sb222 = sb.toString();
            Intrinsics.checkExpressionValueIsNotNull(sb222, "sb.toString()");
            return sb222;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public final void closeIO(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                LogProxy.INSTANCE.m3306e(TAG, "closeIO e = " + e.getMessage());
            }
        }
    }
}
