package me.hgj.jetpackmvvm.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ParameterizedTypeImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016¢\u0006\u0002\u0010\u000bJ\n\u0010\f\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016R\u001e\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u000e"}, m3961d2 = {"Lme/hgj/jetpackmvvm/util/ParameterizedTypeImpl;", "Ljava/lang/reflect/ParameterizedType;", "clazz", "Ljava/lang/Class;", "(Ljava/lang/Class;)V", "getClazz", "()Ljava/lang/Class;", "setClazz", "getActualTypeArguments", "", "Ljava/lang/reflect/Type;", "()[Ljava/lang/reflect/Type;", "getOwnerType", "getRawType", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class ParameterizedTypeImpl implements ParameterizedType {
    private Class<?> clazz;

    @Override // java.lang.reflect.ParameterizedType
    public Type getOwnerType() {
        return null;
    }

    public ParameterizedTypeImpl(Class<?> clazz) {
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        this.clazz = clazz;
    }

    public final Class<?> getClazz() {
        return this.clazz;
    }

    public final void setClazz(Class<?> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "<set-?>");
        this.clazz = cls;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type[] getActualTypeArguments() {
        return new Type[]{this.clazz};
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getRawType() {
        return List.class;
    }
}
