package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Set;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: AbstractTypeChecker.kt */
/* loaded from: classes2.dex */
public final class AbstractNullabilityChecker {
    public static final AbstractNullabilityChecker INSTANCE = new AbstractNullabilityChecker();

    private AbstractNullabilityChecker() {
    }

    public final boolean isPossibleSubtype(AbstractTypeCheckerContext context, SimpleTypeMarker subType, SimpleTypeMarker superType) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(subType, "subType");
        Intrinsics.checkParameterIsNotNull(superType, "superType");
        return runIsPossibleSubtype(context, subType, superType);
    }

    private final boolean runIsPossibleSubtype(AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        boolean z = abstractTypeCheckerContext.isSingleClassifierType(simpleTypeMarker) || abstractTypeCheckerContext.isIntersection(abstractTypeCheckerContext.typeConstructor(simpleTypeMarker)) || abstractTypeCheckerContext.isAllowedTypeVariable(simpleTypeMarker);
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Not singleClassifierType and not intersection subType: " + simpleTypeMarker);
        }
        boolean z2 = abstractTypeCheckerContext.isSingleClassifierType(simpleTypeMarker2) || abstractTypeCheckerContext.isAllowedTypeVariable(simpleTypeMarker2);
        if (_Assertions.ENABLED && !z2) {
            throw new AssertionError("Not singleClassifierType superType: " + simpleTypeMarker2);
        }
        if (abstractTypeCheckerContext.isMarkedNullable(simpleTypeMarker2) || abstractTypeCheckerContext.isDefinitelyNotNullType(simpleTypeMarker) || hasNotNullSupertype(abstractTypeCheckerContext, simpleTypeMarker, AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE)) {
            return true;
        }
        if (abstractTypeCheckerContext.isDefinitelyNotNullType(simpleTypeMarker2) || hasNotNullSupertype(abstractTypeCheckerContext, simpleTypeMarker2, AbstractTypeCheckerContext.SupertypesPolicy.UpperIfFlexible.INSTANCE) || abstractTypeCheckerContext.isClassType(simpleTypeMarker)) {
            return false;
        }
        return hasPathByNotMarkedNullableNodes(abstractTypeCheckerContext, simpleTypeMarker, abstractTypeCheckerContext.typeConstructor(simpleTypeMarker2));
    }

    public final boolean hasNotNullSupertype(AbstractTypeCheckerContext hasNotNullSupertype, SimpleTypeMarker type, AbstractTypeCheckerContext.SupertypesPolicy supertypesPolicy) {
        Intrinsics.checkParameterIsNotNull(hasNotNullSupertype, "$this$hasNotNullSupertype");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(supertypesPolicy, "supertypesPolicy");
        if (!((hasNotNullSupertype.isClassType(type) && !hasNotNullSupertype.isMarkedNullable(type)) || hasNotNullSupertype.isDefinitelyNotNullType(type))) {
            hasNotNullSupertype.initialize();
            ArrayDeque<SimpleTypeMarker> supertypesDeque = hasNotNullSupertype.getSupertypesDeque();
            if (supertypesDeque == null) {
                Intrinsics.throwNpe();
            }
            Set<SimpleTypeMarker> supertypesSet = hasNotNullSupertype.getSupertypesSet();
            if (supertypesSet == null) {
                Intrinsics.throwNpe();
            }
            supertypesDeque.push(type);
            while (!supertypesDeque.isEmpty()) {
                if (supertypesSet.size() > 1000) {
                    throw new IllegalStateException(("Too many supertypes for type: " + type + ". Supertypes = " + CollectionsKt.joinToString$default(supertypesSet, null, null, null, 0, null, null, 63, null)).toString());
                }
                SimpleTypeMarker current = supertypesDeque.pop();
                Intrinsics.checkExpressionValueIsNotNull(current, "current");
                if (supertypesSet.add(current)) {
                    AbstractTypeCheckerContext.SupertypesPolicy.None none = hasNotNullSupertype.isMarkedNullable(current) ? AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE : supertypesPolicy;
                    if (!(!Intrinsics.areEqual(none, AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE))) {
                        none = null;
                    }
                    if (none != null) {
                        Iterator<KotlinTypeMarker> it = hasNotNullSupertype.supertypes(hasNotNullSupertype.typeConstructor(current)).iterator();
                        while (it.hasNext()) {
                            SimpleTypeMarker mo5464transformType = none.mo5464transformType(hasNotNullSupertype, it.next());
                            if ((hasNotNullSupertype.isClassType(mo5464transformType) && !hasNotNullSupertype.isMarkedNullable(mo5464transformType)) || hasNotNullSupertype.isDefinitelyNotNullType(mo5464transformType)) {
                                hasNotNullSupertype.clear();
                            } else {
                                supertypesDeque.add(mo5464transformType);
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
            hasNotNullSupertype.clear();
            return false;
        }
        return true;
    }

    public final boolean hasPathByNotMarkedNullableNodes(AbstractTypeCheckerContext hasPathByNotMarkedNullableNodes, SimpleTypeMarker start, TypeConstructorMarker end) {
        Intrinsics.checkParameterIsNotNull(hasPathByNotMarkedNullableNodes, "$this$hasPathByNotMarkedNullableNodes");
        Intrinsics.checkParameterIsNotNull(start, "start");
        Intrinsics.checkParameterIsNotNull(end, "end");
        if (!(hasPathByNotMarkedNullableNodes.isNotNullNothing(start) || (!hasPathByNotMarkedNullableNodes.isMarkedNullable(start) && hasPathByNotMarkedNullableNodes.isEqualTypeConstructors(hasPathByNotMarkedNullableNodes.typeConstructor(start), end)))) {
            hasPathByNotMarkedNullableNodes.initialize();
            ArrayDeque<SimpleTypeMarker> supertypesDeque = hasPathByNotMarkedNullableNodes.getSupertypesDeque();
            if (supertypesDeque == null) {
                Intrinsics.throwNpe();
            }
            Set<SimpleTypeMarker> supertypesSet = hasPathByNotMarkedNullableNodes.getSupertypesSet();
            if (supertypesSet == null) {
                Intrinsics.throwNpe();
            }
            supertypesDeque.push(start);
            while (!supertypesDeque.isEmpty()) {
                if (supertypesSet.size() > 1000) {
                    throw new IllegalStateException(("Too many supertypes for type: " + start + ". Supertypes = " + CollectionsKt.joinToString$default(supertypesSet, null, null, null, 0, null, null, 63, null)).toString());
                }
                SimpleTypeMarker current = supertypesDeque.pop();
                Intrinsics.checkExpressionValueIsNotNull(current, "current");
                if (supertypesSet.add(current)) {
                    AbstractTypeCheckerContext.SupertypesPolicy supertypesPolicy = hasPathByNotMarkedNullableNodes.isMarkedNullable(current) ? AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE : AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                    if (!(!Intrinsics.areEqual(supertypesPolicy, AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE))) {
                        supertypesPolicy = null;
                    }
                    if (supertypesPolicy != null) {
                        Iterator<KotlinTypeMarker> it = hasPathByNotMarkedNullableNodes.supertypes(hasPathByNotMarkedNullableNodes.typeConstructor(current)).iterator();
                        while (it.hasNext()) {
                            SimpleTypeMarker mo5464transformType = supertypesPolicy.mo5464transformType(hasPathByNotMarkedNullableNodes, it.next());
                            if (hasPathByNotMarkedNullableNodes.isNotNullNothing(mo5464transformType) || (!hasPathByNotMarkedNullableNodes.isMarkedNullable(mo5464transformType) && hasPathByNotMarkedNullableNodes.isEqualTypeConstructors(hasPathByNotMarkedNullableNodes.typeConstructor(mo5464transformType), end))) {
                                hasPathByNotMarkedNullableNodes.clear();
                            } else {
                                supertypesDeque.add(mo5464transformType);
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
            hasPathByNotMarkedNullableNodes.clear();
            return false;
        }
        return true;
    }
}
