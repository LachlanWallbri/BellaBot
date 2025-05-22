package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: typeEnhancement.kt */
/* loaded from: classes2.dex */
final class SimpleResult extends Result {
    private final SimpleType type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SimpleResult(SimpleType type, int i, boolean z) {
        super(type, i, z);
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.type = type;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.Result
    public SimpleType getType() {
        return this.type;
    }
}
