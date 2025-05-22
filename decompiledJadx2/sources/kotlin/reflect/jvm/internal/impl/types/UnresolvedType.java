package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: ErrorType.kt */
/* loaded from: classes2.dex */
public final class UnresolvedType extends ErrorType {
    private final String presentableName;

    public final String getPresentableName() {
        return this.presentableName;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnresolvedType(String presentableName, TypeConstructor constructor, MemberScope memberScope, List<? extends TypeProjection> arguments, boolean z) {
        super(constructor, memberScope, arguments, z);
        Intrinsics.checkParameterIsNotNull(presentableName, "presentableName");
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        Intrinsics.checkParameterIsNotNull(memberScope, "memberScope");
        Intrinsics.checkParameterIsNotNull(arguments, "arguments");
        this.presentableName = presentableName;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.ErrorType, kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public SimpleType makeNullableAsSpecified(boolean z) {
        return new UnresolvedType(this.presentableName, getConstructor(), getMemberScope(), getArguments(), z);
    }
}
