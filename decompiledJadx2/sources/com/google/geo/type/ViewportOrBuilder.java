package com.google.geo.type;

import com.google.protobuf.MessageOrBuilder;
import com.google.type.LatLng;
import com.google.type.LatLngOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public interface ViewportOrBuilder extends MessageOrBuilder {
    LatLng getHigh();

    LatLngOrBuilder getHighOrBuilder();

    LatLng getLow();

    LatLngOrBuilder getLowOrBuilder();

    boolean hasHigh();

    boolean hasLow();
}
