package org.apache.commons.compress.harmony.archive.internal.nls;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* loaded from: classes4.dex */
public class Messages {
    private static ResourceBundle bundle;

    public static String getString(String str) {
        ResourceBundle resourceBundle = bundle;
        if (resourceBundle == null) {
            return str;
        }
        try {
            return resourceBundle.getString(str);
        } catch (MissingResourceException unused) {
            return "Missing message: " + str;
        }
    }

    public static String getString(String str, Object obj) {
        return getString(str, new Object[]{obj});
    }

    public static String getString(String str, int i) {
        return getString(str, new Object[]{Integer.toString(i)});
    }

    public static String getString(String str, char c) {
        return getString(str, new Object[]{String.valueOf(c)});
    }

    public static String getString(String str, Object obj, Object obj2) {
        return getString(str, new Object[]{obj, obj2});
    }

    public static String getString(String str, Object[] objArr) {
        ResourceBundle resourceBundle = bundle;
        if (resourceBundle != null) {
            try {
                str = resourceBundle.getString(str);
            } catch (MissingResourceException unused) {
            }
        }
        return format(str, objArr);
    }

    public static String format(String str, Object[] objArr) {
        int i;
        StringBuilder sb = new StringBuilder(str.length() + (objArr.length * 20));
        String[] strArr = new String[objArr.length];
        int i2 = 0;
        for (int i3 = 0; i3 < objArr.length; i3++) {
            if (objArr[i3] == null) {
                strArr[i3] = "<null>";
            } else {
                strArr[i3] = objArr[i3].toString();
            }
        }
        while (true) {
            int indexOf = str.indexOf(123, i2);
            if (indexOf < 0) {
                break;
            }
            if (indexOf != 0) {
                int i4 = indexOf - 1;
                if (str.charAt(i4) == '\\') {
                    if (indexOf != 1) {
                        sb.append(str.substring(i2, i4));
                    }
                    sb.append('{');
                    i = indexOf + 1;
                    i2 = i;
                }
            }
            if (indexOf > str.length() - 3) {
                sb.append(str.substring(i2));
                i2 = str.length();
            } else {
                int i5 = indexOf + 1;
                byte digit = (byte) Character.digit(str.charAt(i5), 10);
                if (digit < 0 || str.charAt(indexOf + 2) != '}') {
                    sb.append(str.substring(i2, i5));
                    i2 = i5;
                } else {
                    sb.append(str.substring(i2, indexOf));
                    if (digit >= strArr.length) {
                        sb.append("<missing argument>");
                    } else {
                        sb.append(strArr[digit]);
                    }
                    i = indexOf + 3;
                    i2 = i;
                }
            }
        }
        if (i2 < str.length()) {
            sb.append(str.substring(i2));
        }
        return sb.toString();
    }

    public static ResourceBundle setLocale(final Locale locale, final String str) {
        final ClassLoader classLoader = null;
        try {
            return (ResourceBundle) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.apache.commons.compress.harmony.archive.internal.nls.-$$Lambda$Messages$s00GUrZUTN71U4Y3ctj6JB6l-jQ
                @Override // java.security.PrivilegedAction
                public final Object run() {
                    return Messages.lambda$setLocale$0(str, locale, classLoader);
                }
            });
        } catch (MissingResourceException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$setLocale$0(String str, Locale locale, ClassLoader classLoader) {
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        return ResourceBundle.getBundle(str, locale, classLoader);
    }

    static {
        try {
            bundle = setLocale(Locale.getDefault(), "org.apache.commons.compress.harmony.archive.internal.nls.messages");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
