package com.chad.library.adapter.base.util;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TouchEventUtil {
    public static String getTouchAction(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "Unknow:id=" + i : "ACTION_OUTSIDE" : "ACTION_CANCEL" : "ACTION_MOVE" : "ACTION_UP" : "ACTION_DOWN";
    }
}
