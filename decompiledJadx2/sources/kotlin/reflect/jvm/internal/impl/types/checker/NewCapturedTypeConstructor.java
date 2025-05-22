package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: NewCapturedType.kt */
/* loaded from: classes2.dex */
public final class NewCapturedTypeConstructor implements CapturedTypeConstructor {
    private final TypeProjection projection;
    private List<? extends UnwrappedType> supertypes;

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    /* renamed from: getDeclarationDescriptor */
    public ClassifierDescriptor mo5460getDeclarationDescriptor() {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public boolean isDenotable() {
        return false;
    }

    public NewCapturedTypeConstructor(TypeProjection projection, List<? extends UnwrappedType> list) {
        Intrinsics.checkParameterIsNotNull(projection, "projection");
        this.projection = projection;
        this.supertypes = list;
    }

    public /* synthetic */ NewCapturedTypeConstructor(TypeProjection typeProjection, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(typeProjection, (i & 2) != 0 ? (List) null : list);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructor
    public TypeProjection getProjection() {
        return this.projection;
    }

    public final void initializeSupertypes(List<? extends UnwrappedType> supertypes) {
        Intrinsics.checkParameterIsNotNull(supertypes, "supertypes");
        boolean z = this.supertypes == null;
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Already initialized! oldValue = " + this.supertypes + ", newValue = " + supertypes);
        }
        this.supertypes = supertypes;
    }

    /* compiled from: NewCapturedType.kt */
    /* renamed from: kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedTypeConstructor$1 */
    /* loaded from: classes8.dex */
    static final class C78221 extends Lambda implements Function0<List<? extends UnwrappedType>> {
        final /* synthetic */ List $supertypes;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C78221(List list) {
            super(0);
            this.$supertypes = list;
        }

        @Override // kotlin.jvm.functions.Function0
        public final List<? extends UnwrappedType> invoke() {
            return this.$supertypes;
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    /* renamed from: getSupertypes */
    public List<UnwrappedType> mo5461getSupertypes() {
        List list = this.supertypes;
        return list != null ? list : CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public List<TypeParameterDescriptor> getParameters() {
        return CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public KotlinBuiltIns getBuiltIns() {
        KotlinType type = getProjection().getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "projection.type");
        return TypeUtilsKt.getBuiltIns(type);
    }

    public String toString() {
        return "CapturedType(" + getProjection() + ')';
    }
}
