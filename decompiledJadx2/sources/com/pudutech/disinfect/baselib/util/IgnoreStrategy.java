package com.pudutech.disinfect.baselib.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.loc.C3898x;
import kotlin.Metadata;

/* compiled from: GsonSingleton.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/util/IgnoreStrategy;", "Lcom/google/gson/ExclusionStrategy;", "()V", "shouldSkipClass", "", "clazz", "Ljava/lang/Class;", "shouldSkipField", C3898x.f4339h, "Lcom/google/gson/FieldAttributes;", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final class IgnoreStrategy implements ExclusionStrategy {
    @Override // com.google.gson.ExclusionStrategy
    public boolean shouldSkipField(FieldAttributes f) {
        return (f != null ? (IgnoreAnnotation) f.getAnnotation(IgnoreAnnotation.class) : null) != null;
    }

    @Override // com.google.gson.ExclusionStrategy
    public boolean shouldSkipClass(Class<?> clazz) {
        return (clazz != null ? (IgnoreAnnotation) clazz.getAnnotation(IgnoreAnnotation.class) : null) != null;
    }
}
