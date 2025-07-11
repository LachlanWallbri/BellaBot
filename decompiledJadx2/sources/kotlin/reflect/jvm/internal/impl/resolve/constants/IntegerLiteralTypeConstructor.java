package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.Variance;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: IntegerLiteralTypeConstructor.kt */
/* loaded from: classes2.dex */
public final class IntegerLiteralTypeConstructor implements TypeConstructor {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(IntegerLiteralTypeConstructor.class), "supertypes", "getSupertypes()Ljava/util/List;"))};
    public static final Companion Companion = new Companion(null);
    private final ModuleDescriptor module;
    private final Set<KotlinType> possibleTypes;
    private final Lazy supertypes$delegate;
    private final SimpleType type;
    private final long value;

    private final List<KotlinType> getSupertypes() {
        Lazy lazy = this.supertypes$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (List) lazy.getValue();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    /* renamed from: getDeclarationDescriptor */
    public ClassifierDescriptor mo5460getDeclarationDescriptor() {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public boolean isDenotable() {
        return false;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: IntegerLiteralTypeConstructor.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
          classes8.dex
         */
        /* compiled from: IntegerLiteralTypeConstructor.kt */
        /* loaded from: classes2.dex */
        public enum Mode {
            COMMON_SUPER_TYPE,
            INTERSECTION_TYPE
        }

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
          classes8.dex
         */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Mode.values().length];
                $EnumSwitchMapping$0 = iArr;
                iArr[Mode.COMMON_SUPER_TYPE.ordinal()] = 1;
                $EnumSwitchMapping$0[Mode.INTERSECTION_TYPE.ordinal()] = 2;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SimpleType findIntersectionType(Collection<? extends SimpleType> types) {
            Intrinsics.checkParameterIsNotNull(types, "types");
            return findCommonSuperTypeOrIntersectionType(types, Mode.INTERSECTION_TYPE);
        }

        private final SimpleType findCommonSuperTypeOrIntersectionType(Collection<? extends SimpleType> collection, Mode mode) {
            if (collection.isEmpty()) {
                return null;
            }
            Iterator<T> it = collection.iterator();
            if (!it.hasNext()) {
                throw new UnsupportedOperationException("Empty collection can't be reduced.");
            }
            Object next = it.next();
            while (it.hasNext()) {
                SimpleType simpleType = (SimpleType) it.next();
                next = IntegerLiteralTypeConstructor.Companion.fold((SimpleType) next, simpleType, mode);
            }
            return (SimpleType) next;
        }

        private final SimpleType fold(SimpleType simpleType, SimpleType simpleType2, Mode mode) {
            if (simpleType == null || simpleType2 == null) {
                return null;
            }
            TypeConstructor constructor = simpleType.getConstructor();
            TypeConstructor constructor2 = simpleType2.getConstructor();
            boolean z = constructor instanceof IntegerLiteralTypeConstructor;
            if (z && (constructor2 instanceof IntegerLiteralTypeConstructor)) {
                return fold((IntegerLiteralTypeConstructor) constructor, (IntegerLiteralTypeConstructor) constructor2, mode);
            }
            if (z) {
                return fold((IntegerLiteralTypeConstructor) constructor, simpleType2);
            }
            if (constructor2 instanceof IntegerLiteralTypeConstructor) {
                return fold((IntegerLiteralTypeConstructor) constructor2, simpleType);
            }
            return null;
        }

        private final SimpleType fold(IntegerLiteralTypeConstructor integerLiteralTypeConstructor, IntegerLiteralTypeConstructor integerLiteralTypeConstructor2, Mode mode) {
            Set intersect;
            int i = WhenMappings.$EnumSwitchMapping$0[mode.ordinal()];
            if (i == 1) {
                intersect = CollectionsKt.intersect(integerLiteralTypeConstructor.getPossibleTypes(), integerLiteralTypeConstructor2.getPossibleTypes());
            } else {
                if (i != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                intersect = CollectionsKt.union(integerLiteralTypeConstructor.getPossibleTypes(), integerLiteralTypeConstructor2.getPossibleTypes());
            }
            return KotlinTypeFactory.integerLiteralType(Annotations.Companion.getEMPTY(), new IntegerLiteralTypeConstructor(integerLiteralTypeConstructor.value, integerLiteralTypeConstructor.module, intersect, null), false);
        }

        private final SimpleType fold(IntegerLiteralTypeConstructor integerLiteralTypeConstructor, SimpleType simpleType) {
            if (integerLiteralTypeConstructor.getPossibleTypes().contains(simpleType)) {
                return simpleType;
            }
            return null;
        }
    }

    public /* synthetic */ IntegerLiteralTypeConstructor(long j, ModuleDescriptor moduleDescriptor, Set set, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, moduleDescriptor, set);
    }

    public final Set<KotlinType> getPossibleTypes() {
        return this.possibleTypes;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private IntegerLiteralTypeConstructor(long j, ModuleDescriptor moduleDescriptor, Set<? extends KotlinType> set) {
        this.type = KotlinTypeFactory.integerLiteralType(Annotations.Companion.getEMPTY(), this, false);
        this.supertypes$delegate = LazyKt.lazy(new Function0<List<SimpleType>>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor$supertypes$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<SimpleType> invoke() {
                SimpleType simpleType;
                boolean isContainsOnlyUnsignedTypes;
                ClassDescriptor comparable = IntegerLiteralTypeConstructor.this.getBuiltIns().getComparable();
                Intrinsics.checkExpressionValueIsNotNull(comparable, "builtIns.comparable");
                SimpleType defaultType = comparable.getDefaultType();
                Intrinsics.checkExpressionValueIsNotNull(defaultType, "builtIns.comparable.defaultType");
                Variance variance = Variance.IN_VARIANCE;
                simpleType = IntegerLiteralTypeConstructor.this.type;
                List<SimpleType> mutableListOf = CollectionsKt.mutableListOf(TypeSubstitutionKt.replace$default(defaultType, CollectionsKt.listOf(new TypeProjectionImpl(variance, simpleType)), (Annotations) null, 2, (Object) null));
                isContainsOnlyUnsignedTypes = IntegerLiteralTypeConstructor.this.isContainsOnlyUnsignedTypes();
                if (!isContainsOnlyUnsignedTypes) {
                    mutableListOf.add(IntegerLiteralTypeConstructor.this.getBuiltIns().getNumberType());
                }
                return mutableListOf;
            }
        });
        this.value = j;
        this.module = moduleDescriptor;
        this.possibleTypes = set;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isContainsOnlyUnsignedTypes() {
        Collection<KotlinType> allSignedLiteralTypes = PrimitiveTypeUtilKt.getAllSignedLiteralTypes(this.module);
        if ((allSignedLiteralTypes instanceof Collection) && allSignedLiteralTypes.isEmpty()) {
            return true;
        }
        Iterator<T> it = allSignedLiteralTypes.iterator();
        while (it.hasNext()) {
            if (!(!this.possibleTypes.contains((KotlinType) it.next()))) {
                return false;
            }
        }
        return true;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public List<TypeParameterDescriptor> getParameters() {
        return CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    /* renamed from: getSupertypes, reason: collision with other method in class */
    public Collection<KotlinType> mo5461getSupertypes() {
        return getSupertypes();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public KotlinBuiltIns getBuiltIns() {
        return this.module.getBuiltIns();
    }

    public String toString() {
        return "IntegerLiteralType" + valueToString();
    }

    public final boolean checkConstructor(TypeConstructor constructor) {
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        Set<KotlinType> set = this.possibleTypes;
        if ((set instanceof Collection) && set.isEmpty()) {
            return false;
        }
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(((KotlinType) it.next()).getConstructor(), constructor)) {
                return true;
            }
        }
        return false;
    }

    private final String valueToString() {
        return '[' + CollectionsKt.joinToString$default(this.possibleTypes, ",", null, null, 0, null, new Function1<KotlinType, String>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor$valueToString$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(KotlinType it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.toString();
            }
        }, 30, null) + ']';
    }
}
