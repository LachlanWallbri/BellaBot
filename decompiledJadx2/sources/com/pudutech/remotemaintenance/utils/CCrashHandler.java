package com.pudutech.remotemaintenance.utils;

import android.util.Log;
import com.loc.C3898x;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: CCrashHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u000f2\u00020\u0001:\u0002\u000f\u0010B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0003J\u0006\u0010\f\u001a\u00020\rJ\u001c\u0010\u000e\u001a\u00020\r2\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/utils/CCrashHandler;", "Ljava/lang/Thread$UncaughtExceptionHandler;", "()V", "TAG", "", "defaultExceptionHandler", "handleException", "", "t", "Ljava/lang/Thread;", C3898x.f4338g, "", "init", "", "uncaughtException", "Companion", "SingletonHolder", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class CCrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String FILE_NAME = "crash_remotemaintenance.log";
    private static final String PATH = "/sdcard/pudu/crash/";
    private final String TAG;
    private Thread.UncaughtExceptionHandler defaultExceptionHandler;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final CCrashHandler INSTANCE = SingletonHolder.INSTANCE.getHolder();

    public CCrashHandler() {
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
        this.TAG = simpleName;
    }

    /* compiled from: CCrashHandler.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u00020\u00048\u0002X\u0083T¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/utils/CCrashHandler$Companion;", "", "()V", "FILE_NAME", "", "INSTANCE", "Lcom/pudutech/remotemaintenance/utils/CCrashHandler;", "getINSTANCE", "()Lcom/pudutech/remotemaintenance/utils/CCrashHandler;", "PATH", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final CCrashHandler getINSTANCE() {
            return CCrashHandler.INSTANCE;
        }
    }

    /* compiled from: CCrashHandler.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/utils/CCrashHandler$SingletonHolder;", "", "()V", "holder", "Lcom/pudutech/remotemaintenance/utils/CCrashHandler;", "getHolder", "()Lcom/pudutech/remotemaintenance/utils/CCrashHandler;", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    private static final class SingletonHolder {
        public static final SingletonHolder INSTANCE = new SingletonHolder();
        private static final CCrashHandler holder = new CCrashHandler();

        private SingletonHolder() {
        }

        public final CCrashHandler getHolder() {
            return holder;
        }
    }

    public final void init() {
        this.defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread t, Throwable e) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        if (!handleException(t, e) && (uncaughtExceptionHandler = this.defaultExceptionHandler) != null) {
            uncaughtExceptionHandler.uncaughtException(t, e);
        }
        System.exit(0);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }

    private final boolean handleException(Thread t, Throwable e) {
        if (t != null && e != null && this.defaultExceptionHandler != null) {
            StringBuilder sb = new StringBuilder();
            String format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date(System.currentTimeMillis()));
            sb.append("\n");
            sb.append(format + "----------------------->");
            sb.append("\n");
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            String stringWriter2 = stringWriter.toString();
            Intrinsics.checkExpressionValueIsNotNull(stringWriter2, "writer.toString()");
            sb.append(stringWriter2);
            sb.append("\n");
            try {
                File file = new File(PATH);
                Log.d("CCrashHandler", "fileDirPath[" + file.getPath() + "], exists[" + file.exists() + ']');
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file, FILE_NAME);
                Log.d("CCrashHandler", "file[" + file2.getPath() + "], exists[" + file2.exists() + ']');
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file2, true);
                String sb2 = sb.toString();
                Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
                Charset charset = Charsets.UTF_8;
                if (sb2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                byte[] bytes = sb2.getBytes(charset);
                Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                fileOutputStream.write(bytes);
                fileOutputStream.close();
                if (new FileInputStream(file2).available() / 1024 > 10240) {
                    if (file2.delete()) {
                        File file3 = new File(file, FILE_NAME);
                        Log.d("CCrashHandler", "newFile[" + file3.getPath() + "], exists[" + file3.exists());
                        if (!file3.exists()) {
                            file3.createNewFile();
                        }
                    } else {
                        FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                        byte[] bytes2 = " ".getBytes(Charsets.UTF_8);
                        Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
                        fileOutputStream2.write(bytes2);
                    }
                }
                Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.defaultExceptionHandler;
                if (uncaughtExceptionHandler != null) {
                    uncaughtExceptionHandler.uncaughtException(t, e);
                }
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }
}
