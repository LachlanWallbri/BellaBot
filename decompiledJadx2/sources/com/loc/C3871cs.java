package com.loc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: Reflect.java */
/* renamed from: com.loc.cs */
/* loaded from: classes4.dex */
public final class C3871cs {
    /* renamed from: a */
    public static Object m2916a(Object obj, Class<?> cls, String str, Object... objArr) throws Exception {
        Class<?>[] clsArr = new Class[objArr.length];
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
            if (clsArr[i] == Integer.class) {
                clsArr[i] = Integer.TYPE;
            }
        }
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        if (!declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        try {
            return declaredMethod.invoke(obj, objArr);
        } catch (Throwable th) {
            try {
                if (!(th instanceof InvocationTargetException)) {
                    return null;
                }
                C3880f.m3097a(th.getTargetException(), "Reflect", "invokeMethod " + str);
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    /* renamed from: a */
    public static Object m2917a(Object obj, String str, Object... objArr) {
        try {
            return m2916a(obj, obj.getClass(), str, objArr);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static Object m2918a(String str, String str2) throws Exception {
        Class<?> cls = Class.forName(str);
        Field field = cls.getField(str2);
        field.setAccessible(true);
        return field.get(cls);
    }

    /* renamed from: a */
    public static Object m2919a(String str, String str2, Object[] objArr, Class<?>[] clsArr) throws Exception {
        Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, clsArr);
        if (!declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        return declaredMethod.invoke(null, objArr);
    }

    /* renamed from: b */
    public static int m2920b(Object obj, String str, Object... objArr) throws Exception {
        return ((Integer) m2917a(obj, str, objArr)).intValue();
    }
}
