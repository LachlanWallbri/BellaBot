package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.model.StubTypeMarker;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: StubType.kt */
/* loaded from: classes2.dex */
public final class StubType extends SimpleType implements StubTypeMarker {
    private final TypeConstructor constructor;
    private final boolean isMarkedNullable;
    private final MemberScope memberScope;
    private final TypeConstructor originalTypeVariable;

    @Override // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public boolean isMarkedNullable() {
        return this.isMarkedNullable;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public TypeConstructor getConstructor() {
        return this.constructor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public MemberScope getMemberScope() {
        return this.memberScope;
    }

    public StubType(TypeConstructor originalTypeVariable, boolean z, TypeConstructor constructor, MemberScope memberScope) {
        Intrinsics.checkParameterIsNotNull(originalTypeVariable, "originalTypeVariable");
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        Intrinsics.checkParameterIsNotNull(memberScope, "memberScope");
        this.originalTypeVariable = originalTypeVariable;
        this.isMarkedNullable = z;
        this.constructor = constructor;
        this.memberScope = memberScope;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public List<TypeProjection> getArguments() {
        return CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    public Annotations getAnnotations() {
        return Annotations.Companion.getEMPTY();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public SimpleType replaceAnnotations(Annotations newAnnotations) {
        Intrinsics.checkParameterIsNotNull(newAnnotations, "newAnnotations");
        return this;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public SimpleType makeNullableAsSpecified(boolean z) {
        if (z == isMarkedNullable()) {
            return this;
        }
        return new StubType(this.originalTypeVariable, z, getConstructor(), getMemberScope());
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public String toString() {
        return "NonFixed: " + this.originalTypeVariable;
    }
}
