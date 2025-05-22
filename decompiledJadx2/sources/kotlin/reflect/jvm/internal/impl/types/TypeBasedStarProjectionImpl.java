package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: StarProjectionImpl.kt */
/* loaded from: classes2.dex */
public final class TypeBasedStarProjectionImpl extends TypeProjectionBase {
    private final KotlinType _type;

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeProjection
    public boolean isStarProjection() {
        return true;
    }

    public TypeBasedStarProjectionImpl(KotlinType _type) {
        Intrinsics.checkParameterIsNotNull(_type, "_type");
        this._type = _type;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeProjection
    public Variance getProjectionKind() {
        return Variance.OUT_VARIANCE;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeProjection
    public KotlinType getType() {
        return this._type;
    }
}
