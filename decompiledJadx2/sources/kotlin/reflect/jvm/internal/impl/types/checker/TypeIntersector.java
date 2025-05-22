package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: IntersectionType.kt */
/* loaded from: classes2.dex */
public final class TypeIntersector {
    public static final TypeIntersector INSTANCE = new TypeIntersector();

    private TypeIntersector() {
    }

    public final SimpleType intersectTypes$descriptors(List<? extends SimpleType> types) {
        Intrinsics.checkParameterIsNotNull(types, "types");
        boolean z = types.size() > 1;
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Size should be at least 2, but it is " + types.size());
        }
        ArrayList arrayList = new ArrayList();
        for (SimpleType simpleType : types) {
            if (simpleType.getConstructor() instanceof IntersectionTypeConstructor) {
                Collection<KotlinType> mo5461getSupertypes = simpleType.getConstructor().mo5461getSupertypes();
                Intrinsics.checkExpressionValueIsNotNull(mo5461getSupertypes, "type.constructor.supertypes");
                Collection<KotlinType> collection = mo5461getSupertypes;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
                for (KotlinType it : collection) {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    SimpleType upperIfFlexible = FlexibleTypesKt.upperIfFlexible(it);
                    if (simpleType.isMarkedNullable()) {
                        upperIfFlexible = upperIfFlexible.makeNullableAsSpecified(true);
                    }
                    arrayList2.add(upperIfFlexible);
                }
                arrayList.addAll(arrayList2);
            } else {
                arrayList.add(simpleType);
            }
        }
        ArrayList<SimpleType> arrayList3 = arrayList;
        ResultNullability resultNullability = ResultNullability.START;
        Iterator it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            resultNullability = resultNullability.combine((UnwrappedType) it2.next());
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (SimpleType simpleType2 : arrayList3) {
            if (resultNullability == ResultNullability.NOT_NULL) {
                simpleType2 = SpecialTypesKt.makeSimpleTypeDefinitelyNotNullOrNotNull(simpleType2);
            }
            linkedHashSet.add(simpleType2);
        }
        return intersectTypesWithoutIntersectionType(linkedHashSet);
    }

    private final SimpleType intersectTypesWithoutIntersectionType(final Set<? extends SimpleType> set) {
        if (set.size() == 1) {
            return (SimpleType) CollectionsKt.single(set);
        }
        Function0<String> function0 = new Function0<String>() { // from class: kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector$intersectTypesWithoutIntersectionType$errorMessage$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "This collections cannot be empty! input types: " + CollectionsKt.joinToString$default(set, null, null, null, 0, null, null, 63, null);
            }
        };
        Set<? extends SimpleType> set2 = set;
        Collection<SimpleType> filterTypes = filterTypes(set2, new C7825x702eebb8(this));
        boolean z = !filterTypes.isEmpty();
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError(function0.invoke());
        }
        SimpleType findIntersectionType = IntegerLiteralTypeConstructor.Companion.findIntersectionType(filterTypes);
        if (findIntersectionType != null) {
            return findIntersectionType;
        }
        Collection<SimpleType> filterTypes2 = filterTypes(filterTypes, new C7826xc97d8c34(NewKotlinTypeChecker.INSTANCE));
        boolean isEmpty = true ^ filterTypes2.isEmpty();
        if (_Assertions.ENABLED && !isEmpty) {
            throw new AssertionError(function0.invoke());
        }
        if (filterTypes2.size() < 2) {
            return (SimpleType) CollectionsKt.single(filterTypes2);
        }
        IntersectionTypeConstructor intersectionTypeConstructor = new IntersectionTypeConstructor(set2);
        return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(Annotations.Companion.getEMPTY(), intersectionTypeConstructor, CollectionsKt.emptyList(), false, intersectionTypeConstructor.createScopeForKotlinType());
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005b A[EDGE_INSN: B:25:0x005b->B:9:0x005b BREAK  A[LOOP:1: B:16:0x0032->B:26:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[LOOP:1: B:16:0x0032->B:26:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final Collection<SimpleType> filterTypes(Collection<? extends SimpleType> collection, Function2<? super SimpleType, ? super SimpleType, Boolean> function2) {
        boolean z;
        ArrayList arrayList = new ArrayList(collection);
        Iterator it = arrayList.iterator();
        Intrinsics.checkExpressionValueIsNotNull(it, "filteredTypes.iterator()");
        while (it.hasNext()) {
            SimpleType upper = (SimpleType) it.next();
            ArrayList<SimpleType> arrayList2 = arrayList;
            boolean z2 = true;
            if (!(arrayList2 instanceof Collection) || !arrayList2.isEmpty()) {
                for (SimpleType lower : arrayList2) {
                    if (lower != upper) {
                        Intrinsics.checkExpressionValueIsNotNull(lower, "lower");
                        Intrinsics.checkExpressionValueIsNotNull(upper, "upper");
                        if (function2.invoke(lower, upper).booleanValue()) {
                            z = true;
                            if (!z) {
                                break;
                            }
                        }
                    }
                    z = false;
                    if (!z) {
                    }
                }
            }
            z2 = false;
            if (z2) {
                it.remove();
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isStrictSupertype(KotlinType kotlinType, KotlinType kotlinType2) {
        NewKotlinTypeChecker newKotlinTypeChecker = NewKotlinTypeChecker.INSTANCE;
        return newKotlinTypeChecker.isSubtypeOf(kotlinType, kotlinType2) && !newKotlinTypeChecker.isSubtypeOf(kotlinType2, kotlinType);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: IntersectionType.kt */
    /* loaded from: classes2.dex */
    private static final class ResultNullability {
        private static final /* synthetic */ ResultNullability[] $VALUES;
        public static final ResultNullability ACCEPT_NULL;
        public static final ResultNullability NOT_NULL;
        public static final ResultNullability START;
        public static final ResultNullability UNKNOWN;

        static {
            START start = new START("START", 0);
            START = start;
            ACCEPT_NULL accept_null = new ACCEPT_NULL("ACCEPT_NULL", 1);
            ACCEPT_NULL = accept_null;
            UNKNOWN unknown = new UNKNOWN("UNKNOWN", 2);
            UNKNOWN = unknown;
            NOT_NULL not_null = new NOT_NULL("NOT_NULL", 3);
            NOT_NULL = not_null;
            $VALUES = new ResultNullability[]{start, accept_null, unknown, not_null};
        }

        public static ResultNullability valueOf(String str) {
            return (ResultNullability) Enum.valueOf(ResultNullability.class, str);
        }

        public static ResultNullability[] values() {
            return (ResultNullability[]) $VALUES.clone();
        }

        public abstract ResultNullability combine(UnwrappedType unwrappedType);

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
          classes8.dex
         */
        /* compiled from: IntersectionType.kt */
        /* loaded from: classes2.dex */
        static final class START extends ResultNullability {
            START(String str, int i) {
                super(str, i, null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.ResultNullability
            public ResultNullability combine(UnwrappedType nextType) {
                Intrinsics.checkParameterIsNotNull(nextType, "nextType");
                return getResultNullability(nextType);
            }
        }

        private ResultNullability(String str, int i) {
        }

        public /* synthetic */ ResultNullability(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, i);
        }

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
          classes8.dex
         */
        /* compiled from: IntersectionType.kt */
        /* loaded from: classes2.dex */
        static final class ACCEPT_NULL extends ResultNullability {
            ACCEPT_NULL(String str, int i) {
                super(str, i, null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.ResultNullability
            public ResultNullability combine(UnwrappedType nextType) {
                Intrinsics.checkParameterIsNotNull(nextType, "nextType");
                return getResultNullability(nextType);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
          classes8.dex
         */
        /* compiled from: IntersectionType.kt */
        /* loaded from: classes2.dex */
        static final class UNKNOWN extends ResultNullability {
            UNKNOWN(String str, int i) {
                super(str, i, null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.ResultNullability
            public ResultNullability combine(UnwrappedType nextType) {
                Intrinsics.checkParameterIsNotNull(nextType, "nextType");
                ResultNullability resultNullability = getResultNullability(nextType);
                return resultNullability == ResultNullability.ACCEPT_NULL ? this : resultNullability;
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
          classes8.dex
         */
        /* compiled from: IntersectionType.kt */
        /* loaded from: classes2.dex */
        static final class NOT_NULL extends ResultNullability {
            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.ResultNullability
            public NOT_NULL combine(UnwrappedType nextType) {
                Intrinsics.checkParameterIsNotNull(nextType, "nextType");
                return this;
            }

            NOT_NULL(String str, int i) {
                super(str, i, null);
            }
        }

        protected final ResultNullability getResultNullability(UnwrappedType resultNullability) {
            Intrinsics.checkParameterIsNotNull(resultNullability, "$this$resultNullability");
            return resultNullability.isMarkedNullable() ? ACCEPT_NULL : NullabilityChecker.INSTANCE.isSubtypeOfAny(resultNullability) ? NOT_NULL : UNKNOWN;
        }
    }
}
