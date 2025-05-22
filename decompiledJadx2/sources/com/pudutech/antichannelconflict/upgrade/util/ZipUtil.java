package com.pudutech.antichannelconflict.upgrade.util;

import com.pudutech.base.Pdlog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.TypeCastException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import okhttp3.internal.Util;

/* compiled from: ZipUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0002J!\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/antichannelconflict/upgrade/util/ZipUtil;", "", "()V", "TAG", "", "createFile", "Ljava/io/File;", "filePath", "unzip", "", "zipFile", "descDir", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ZipUtil {
    public static final ZipUtil INSTANCE = new ZipUtil();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private ZipUtil() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File createFile(String filePath) {
        Pdlog.m3273d(TAG, filePath);
        File file = new File(filePath);
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            Intrinsics.throwNpe();
        }
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        if (!file.exists()) {
            if ((true ^ StringsKt.isBlank(FilesKt.getExtension(file))) || Intrinsics.areEqual(parentFile.getName(), "fastboot")) {
                file.createNewFile();
            } else {
                file.mkdirs();
            }
        }
        return file;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x00f4, code lost:
    
        if (r5 != null) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00f7, code lost:
    
        r12 = r0.getResult();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00ff, code lost:
    
        if (r12 != kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0101, code lost:
    
        kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0104, code lost:
    
        return r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c7, code lost:
    
        okhttp3.internal.Util.closeQuietly(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00c5, code lost:
    
        if (r5 != null) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object unzip(String str, String str2, Continuation<? super Boolean> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        byte[] bArr = new byte[1024];
        OutputStream outputStream = (OutputStream) null;
        InputStream inputStream = (InputStream) null;
        try {
            try {
                ZipFile zipFile = new ZipFile(str);
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry nextElement = entries.nextElement();
                    if (nextElement == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.util.zip.ZipEntry");
                    }
                    ZipEntry zipEntry = nextElement;
                    String name = zipEntry.getName();
                    Intrinsics.checkExpressionValueIsNotNull(name, "zipEntry.name");
                    inputStream = zipFile.getInputStream(zipEntry);
                    File createFile = INSTANCE.createFile(str2 + File.separator + name);
                    if (createFile.isFile()) {
                        FileOutputStream fileOutputStream = new FileOutputStream(createFile);
                        try {
                            Ref.IntRef intRef = new Ref.IntRef();
                            while (true) {
                                Integer boxInt = Boxing.boxInt(inputStream.read(bArr));
                                intRef.element = boxInt.intValue();
                                if (boxInt.intValue() <= 0) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, intRef.element);
                            }
                            outputStream = fileOutputStream;
                        } catch (Exception e) {
                            e = e;
                            outputStream = fileOutputStream;
                            Pdlog.m3273d("TAG", "unzip exception", e);
                            Boolean boxBoolean = Boxing.boxBoolean(false);
                            Result.Companion companion = Result.INSTANCE;
                            cancellableContinuationImpl2.resumeWith(Result.m4510constructorimpl(boxBoolean));
                            if (inputStream != null) {
                                Util.closeQuietly(inputStream);
                            }
                        } catch (Throwable th) {
                            th = th;
                            outputStream = fileOutputStream;
                            if (inputStream != null) {
                                Util.closeQuietly(inputStream);
                            }
                            if (outputStream != null) {
                                Util.closeQuietly(outputStream);
                            }
                            throw th;
                        }
                    }
                    Intrinsics.checkExpressionValueIsNotNull(inputStream, "inputStream");
                    Util.closeQuietly(inputStream);
                    if (outputStream != null) {
                        Util.closeQuietly(outputStream);
                    }
                }
                Boolean boxBoolean2 = Boxing.boxBoolean(true);
                Result.Companion companion2 = Result.INSTANCE;
                cancellableContinuationImpl2.resumeWith(Result.m4510constructorimpl(boxBoolean2));
                if (inputStream != null) {
                    Util.closeQuietly(inputStream);
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
