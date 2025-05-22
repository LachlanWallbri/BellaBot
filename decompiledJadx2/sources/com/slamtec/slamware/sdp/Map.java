package com.slamtec.slamware.sdp;

import android.graphics.RectF;
import com.slamtec.slamware.geometry.PointF;
import com.slamtec.slamware.geometry.Size;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
@Deprecated
/* loaded from: classes2.dex */
public class Map {
    private long _cPointer = 0;

    public native RectF getMapArea();

    public native byte[] getMapData();

    public native Size getMapDimension();

    public native PointF getMapPosition();

    public native PointF getMapResolution();

    public native long getTimestamp();

    public native void releaseCPointer();

    static {
        System.loadLibrary("rpsdk");
    }

    protected void finalize() {
        releaseCPointer();
    }
}
