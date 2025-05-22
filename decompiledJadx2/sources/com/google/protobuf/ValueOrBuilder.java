package com.google.protobuf;

import com.google.protobuf.Value;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public interface ValueOrBuilder extends MessageOrBuilder {
    boolean getBoolValue();

    Value.KindCase getKindCase();

    ListValue getListValue();

    ListValueOrBuilder getListValueOrBuilder();

    NullValue getNullValue();

    int getNullValueValue();

    double getNumberValue();

    String getStringValue();

    ByteString getStringValueBytes();

    Struct getStructValue();

    StructOrBuilder getStructValueOrBuilder();

    boolean hasListValue();

    boolean hasStructValue();
}
