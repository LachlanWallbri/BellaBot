package io.netty.util.internal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class ThrowableUtil {
    private ThrowableUtil() {
    }

    public static <T extends Throwable> T unknownStackTrace(T t, Class<?> cls, String str) {
        t.setStackTrace(new StackTraceElement[]{new StackTraceElement(cls.getName(), str, null, -1)});
        return t;
    }

    public static String stackTraceToString(Throwable th) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        th.printStackTrace(printStream);
        printStream.flush();
        try {
            return new String(byteArrayOutputStream.toByteArray());
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public static boolean haveSuppressed() {
        return PlatformDependent.javaVersion() >= 7;
    }

    public static void addSuppressed(Throwable th, Throwable th2) {
        if (haveSuppressed()) {
            th.addSuppressed(th2);
        }
    }

    public static void addSuppressedAndClear(Throwable th, List<Throwable> list) {
        addSuppressed(th, list);
        list.clear();
    }

    public static void addSuppressed(Throwable th, List<Throwable> list) {
        Iterator<Throwable> it = list.iterator();
        while (it.hasNext()) {
            addSuppressed(th, it.next());
        }
    }
}
