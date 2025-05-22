package com.google.protobuf.util;

import com.google.protobuf.Struct;
import com.google.protobuf.Value;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class Structs {
    /* renamed from: of */
    public static Struct m789of(String str, Value value) {
        return Struct.newBuilder().putFields(str, value).build();
    }

    /* renamed from: of */
    public static Struct m790of(String str, Value value, String str2, Value value2) {
        return Struct.newBuilder().putFields(str, value).putFields(str2, value2).build();
    }

    /* renamed from: of */
    public static Struct m791of(String str, Value value, String str2, Value value2, String str3, Value value3) {
        return Struct.newBuilder().putFields(str, value).putFields(str2, value2).putFields(str3, value3).build();
    }

    private Structs() {
    }
}
