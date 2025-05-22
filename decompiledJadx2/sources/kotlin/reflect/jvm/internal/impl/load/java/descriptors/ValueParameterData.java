package kotlin.reflect.jvm.internal.impl.load.java.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: util.kt */
/* loaded from: classes2.dex */
public final class ValueParameterData {
    private final boolean hasDefaultValue;
    private final KotlinType type;

    public ValueParameterData(KotlinType type, boolean z) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.type = type;
        this.hasDefaultValue = z;
    }

    public final boolean getHasDefaultValue() {
        return this.hasDefaultValue;
    }

    public final KotlinType getType() {
        return this.type;
    }
}
