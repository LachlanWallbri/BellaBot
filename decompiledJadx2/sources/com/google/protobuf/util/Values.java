package com.google.protobuf.util;

import com.google.protobuf.ListValue;
import com.google.protobuf.NullValue;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class Values {
    private static final Value NULL_VALUE = Value.newBuilder().setNullValue(NullValue.NULL_VALUE).build();

    public static Value ofNull() {
        return NULL_VALUE;
    }

    /* renamed from: of */
    public static Value m797of(boolean z) {
        return Value.newBuilder().setBoolValue(z).build();
    }

    /* renamed from: of */
    public static Value m792of(double d) {
        return Value.newBuilder().setNumberValue(d).build();
    }

    /* renamed from: of */
    public static Value m796of(String str) {
        return Value.newBuilder().setStringValue(str).build();
    }

    /* renamed from: of */
    public static Value m794of(Struct struct) {
        return Value.newBuilder().setStructValue(struct).build();
    }

    /* renamed from: of */
    public static Value m793of(ListValue listValue) {
        return Value.newBuilder().setListValue(listValue).build();
    }

    /* renamed from: of */
    public static Value m795of(Iterable<Value> iterable) {
        Value.Builder newBuilder = Value.newBuilder();
        newBuilder.getListValueBuilder().addAllValues(iterable);
        return newBuilder.build();
    }

    private Values() {
    }
}
