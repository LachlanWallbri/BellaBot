package com.pudutech.mirsdk.locate;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: LocateHandleFactory.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/mirsdk/locate/LocateHandleFactory;", "", "()V", "create", "Lcom/pudutech/mirsdk/locate/LocateHandle;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LocateHandleFactory {
    public static final LocateHandleFactory INSTANCE = new LocateHandleFactory();

    private LocateHandleFactory() {
    }

    public final LocateHandle create() {
        MarkerHandle markerHandle = new MarkerHandle(0);
        MarkerHandle markerHandle2 = new MarkerHandle(1);
        LaserHandle laserHandle = new LaserHandle(2);
        LaserMarkerHandle laserMarkerHandle = new LaserMarkerHandle(3);
        SlamwareHandle slamwareHandle = new SlamwareHandle(4);
        markerHandle.setNextHandle(markerHandle2);
        markerHandle2.setNextHandle(laserHandle);
        laserHandle.setNextHandle(laserMarkerHandle);
        laserMarkerHandle.setNextHandle(slamwareHandle);
        return markerHandle;
    }
}
