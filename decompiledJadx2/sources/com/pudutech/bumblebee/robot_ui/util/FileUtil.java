package com.pudutech.bumblebee.robot_ui.util;

import android.content.Context;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.FullLoadDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* compiled from: FileUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006JJ\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\u001e\b\u0002\u0010\u000e\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000fø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0004J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u0004J$\u0010\u0019\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00042\b\u0010\u001a\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u001b\u001a\u00020\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/util/FileUtil;", "", "()V", "bytesToHexString", "", "bytes", "", "cleanFactory", "", "context", "Landroid/content/Context;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "tip", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getFileMD5", "file", "Ljava/io/File;", "isExists", "", "path", "readContentFromFile", "saveContentToFile", AIUIConstant.KEY_CONTENT, "isAppend", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class FileUtil {
    public static final FileUtil INSTANCE = new FileUtil();

    private FileUtil() {
    }

    public final boolean isExists(String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        return new File(path).exists();
    }

    public static /* synthetic */ void saveContentToFile$default(FileUtil fileUtil, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        fileUtil.saveContentToFile(str, str2, z);
    }

    public final void saveContentToFile(String path, String content, boolean isAppend) {
        String str = path;
        if (str == null || str.length() == 0) {
            return;
        }
        FileWriter fileWriter = (FileWriter) null;
        try {
            try {
                try {
                    File file = new File(path);
                    if (isAppend) {
                        if (file.exists()) {
                            file.delete();
                        }
                        file.createNewFile();
                        Pdlog.m3273d("FileUtil", "删除创建");
                    } else {
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        Pdlog.m3273d("FileUtil", "不存在创建");
                    }
                    FileWriter fileWriter2 = new FileWriter(file, isAppend);
                    if (content == null) {
                        content = "";
                    }
                    try {
                        fileWriter2.write(content);
                        fileWriter2.close();
                    } catch (Exception e) {
                        fileWriter = fileWriter2;
                        e = e;
                        e.printStackTrace();
                        if (fileWriter != null) {
                            fileWriter.close();
                        }
                    } catch (Throwable th) {
                        fileWriter = fileWriter2;
                        th = th;
                        if (fileWriter != null) {
                            try {
                                fileWriter.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0082 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String readContentFromFile(String path) {
        FileInputStream fileInputStream;
        Throwable th;
        Exception e;
        String str = path;
        if (str == null || str.length() == 0) {
            return null;
        }
        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        InputStream inputStream = (InputStream) null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Exception e2) {
            fileInputStream = inputStream;
            e = e2;
        } catch (Throwable th2) {
            fileInputStream = inputStream;
            th = th2;
            if (fileInputStream != null) {
            }
            throw th;
        }
        try {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                String str2 = (String) null;
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        str2 = readLine;
                    } else {
                        readLine = null;
                    }
                    if (readLine == null) {
                        break;
                    }
                    sb.append(str2);
                    sb.append("\n");
                }
                String sb2 = sb.toString();
                try {
                    fileInputStream.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                return sb2;
            } catch (Throwable th3) {
                th = th3;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            e.printStackTrace();
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
            }
            return null;
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

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void cleanFactory$default(FileUtil fileUtil, Context context, CoroutineScope coroutineScope, String str, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            str = (String) null;
        }
        if ((i & 8) != 0) {
            function1 = new FileUtil$cleanFactory$1(null);
        }
        fileUtil.cleanFactory(context, coroutineScope, str, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [T, com.pudutech.bumblebee.robot_ui.ui.dialog.FullLoadDialog] */
    public final void cleanFactory(Context context, CoroutineScope coroutineScope, String tip, Function1<? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "coroutineScope");
        Intrinsics.checkParameterIsNotNull(block, "block");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new FullLoadDialog(context);
        if (tip != null) {
            ((FullLoadDialog) objectRef.element).setContent(tip);
        } else {
            FullLoadDialog fullLoadDialog = (FullLoadDialog) objectRef.element;
            String string = context.getString(C4188R.string.advance_clean_wait_content);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.stri…vance_clean_wait_content)");
            fullLoadDialog.setContent(string);
        }
        ((FullLoadDialog) objectRef.element).show();
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new FileUtil$cleanFactory$4(block, context, objectRef, null), 3, null);
    }
}
