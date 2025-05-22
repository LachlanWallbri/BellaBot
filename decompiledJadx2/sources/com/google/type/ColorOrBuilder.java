package com.google.type;

import com.google.protobuf.FloatValue;
import com.google.protobuf.FloatValueOrBuilder;
import com.google.protobuf.MessageOrBuilder;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public interface ColorOrBuilder extends MessageOrBuilder {
    FloatValue getAlpha();

    FloatValueOrBuilder getAlphaOrBuilder();

    float getBlue();

    float getGreen();

    float getRed();

    boolean hasAlpha();
}
