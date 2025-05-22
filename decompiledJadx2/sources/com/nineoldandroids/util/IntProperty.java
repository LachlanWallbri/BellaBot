package com.nineoldandroids.util;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes4.dex */
public abstract class IntProperty<T> extends Property<T, Integer> {
    public abstract void setValue(T t, int i);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.nineoldandroids.util.Property
    public /* bridge */ /* synthetic */ void set(Object obj, Integer num) {
        set2((IntProperty<T>) obj, num);
    }

    public IntProperty(String str) {
        super(Integer.class, str);
    }

    /* renamed from: set, reason: avoid collision after fix types in other method */
    public final void set2(T t, Integer num) {
        set2((IntProperty<T>) t, Integer.valueOf(num.intValue()));
    }
}
