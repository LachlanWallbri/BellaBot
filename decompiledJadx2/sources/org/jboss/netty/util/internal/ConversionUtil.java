package org.jboss.netty.util.internal;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public final class ConversionUtil {
    private static final String[] INTEGERS = {"0", "1", "2", "3", TmpConstant.MODEL_TYPE_ALI_LCA_CLOUD, "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};

    public static int toInt(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        return Integer.parseInt(String.valueOf(obj));
    }

    public static boolean toBoolean(Object obj) {
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        if (obj instanceof Number) {
            return ((Number) obj).intValue() != 0;
        }
        String valueOf = String.valueOf(obj);
        if (valueOf.length() == 0) {
            return false;
        }
        try {
            return Integer.parseInt(valueOf) != 0;
        } catch (NumberFormatException unused) {
            char upperCase = Character.toUpperCase(valueOf.charAt(0));
            return upperCase == 'T' || upperCase == 'Y';
        }
    }

    public static String[] toStringArray(Object obj) {
        if (obj instanceof String[]) {
            return (String[]) obj;
        }
        if (obj instanceof Iterable) {
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : (Iterable) obj) {
                if (obj2 == null) {
                    arrayList.add(null);
                } else {
                    arrayList.add(String.valueOf(obj2));
                }
            }
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        return String.valueOf(obj).split("[, \\t\\n\\r\\f\\e\\a]");
    }

    public static String toString(int i) {
        if (i >= 0) {
            String[] strArr = INTEGERS;
            if (i < strArr.length) {
                return strArr[i];
            }
        }
        return Integer.toString(i);
    }

    private ConversionUtil() {
    }
}
