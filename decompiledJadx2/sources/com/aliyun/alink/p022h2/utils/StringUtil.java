package com.aliyun.alink.p022h2.utils;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class StringUtil {
    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence charSequence) {
        return !isEmpty(charSequence);
    }
}
