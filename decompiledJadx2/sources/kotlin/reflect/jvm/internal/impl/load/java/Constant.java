package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: utils.kt */
/* loaded from: classes2.dex */
public final class Constant extends JavaDefaultValue {
    private final Object value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Constant(Object value) {
        super(null);
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.value = value;
    }
}
