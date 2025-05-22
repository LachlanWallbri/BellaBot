package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: typeEnhancement.kt */
/* loaded from: classes2.dex */
class Result {
    private final int subtreeSize;
    private final KotlinType type;
    private final boolean wereChanges;

    public Result(KotlinType type, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.type = type;
        this.subtreeSize = i;
        this.wereChanges = z;
    }

    public final int getSubtreeSize() {
        return this.subtreeSize;
    }

    public KotlinType getType() {
        return this.type;
    }

    public final boolean getWereChanges() {
        return this.wereChanges;
    }

    public final KotlinType getTypeIfChanged() {
        KotlinType type = getType();
        if (this.wereChanges) {
            return type;
        }
        return null;
    }
}
