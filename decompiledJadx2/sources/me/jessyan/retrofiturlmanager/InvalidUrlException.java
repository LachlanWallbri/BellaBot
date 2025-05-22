package me.jessyan.retrofiturlmanager;

import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public class InvalidUrlException extends RuntimeException {
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public InvalidUrlException(String str) {
        super(r0.toString());
        StringBuilder sb = new StringBuilder();
        sb.append("You've configured an invalid url : ");
        sb.append(TextUtils.isEmpty(str) ? "EMPTY_OR_NULL_URL" : str);
    }
}
