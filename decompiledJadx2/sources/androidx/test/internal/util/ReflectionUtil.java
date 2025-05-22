package androidx.test.internal.util;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class ReflectionUtil {
    private static final String TAG = "ReflectionUtil";

    public static void reflectivelyInvokeRemoteMethod(final String className, final String methodName) {
        Checks.checkNotNull(className);
        Checks.checkNotNull(methodName);
        String valueOf = String.valueOf(methodName);
        Log.i(TAG, valueOf.length() != 0 ? "Attempting to reflectively call: ".concat(valueOf) : new String("Attempting to reflectively call: "));
        try {
            Method declaredMethod = Class.forName(className).getDeclaredMethod(methodName, new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e(TAG, "Reflective call failed: ", e);
        }
    }
}
