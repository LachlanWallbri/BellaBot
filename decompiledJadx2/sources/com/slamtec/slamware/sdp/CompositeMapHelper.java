package com.slamtec.slamware.sdp;

import com.slamtec.slamware.robot.CompositeMap;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class CompositeMapHelper {
    public native CompositeMap loadFile(String str);

    public native String saveFile(String str, CompositeMap compositeMap);

    static {
        System.loadLibrary("rpsdk");
    }
}
