package kotlin.reflect.jvm.internal.impl.types;

import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public interface TypeProjection extends TypeArgumentMarker {
    Variance getProjectionKind();

    KotlinType getType();

    boolean isStarProjection();
}
