package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: utils.kt */
/* loaded from: classes2.dex */
public final class UtilsKt {
    public static final KotlinType findCorrespondingSupertype(KotlinType subtype, KotlinType supertype, TypeCheckingProcedureCallbacks typeCheckingProcedureCallbacks) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(subtype, "subtype");
        Intrinsics.checkParameterIsNotNull(supertype, "supertype");
        Intrinsics.checkParameterIsNotNull(typeCheckingProcedureCallbacks, "typeCheckingProcedureCallbacks");
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(new SubtypePathNode(subtype, null));
        TypeConstructor constructor = supertype.getConstructor();
        while (!arrayDeque.isEmpty()) {
            SubtypePathNode subtypePathNode = (SubtypePathNode) arrayDeque.poll();
            KotlinType type = subtypePathNode.getType();
            TypeConstructor constructor2 = type.getConstructor();
            if (typeCheckingProcedureCallbacks.assertEqualTypeConstructors(constructor2, constructor)) {
                boolean isMarkedNullable = type.isMarkedNullable();
                for (SubtypePathNode previous = subtypePathNode.getPrevious(); previous != null; previous = previous.getPrevious()) {
                    KotlinType type2 = previous.getType();
                    List<TypeProjection> arguments = type2.getArguments();
                    if (!(arguments instanceof Collection) || !arguments.isEmpty()) {
                        Iterator<T> it = arguments.iterator();
                        while (it.hasNext()) {
                            if (((TypeProjection) it.next()).getProjectionKind() != Variance.INVARIANT) {
                                z = true;
                                break;
                            }
                        }
                    }
                    z = false;
                    if (z) {
                        KotlinType safeSubstitute = CapturedTypeConstructorKt.wrapWithCapturingSubstitution$default(TypeConstructorSubstitution.Companion.create(type2), false, 1, null).buildSubstitutor().safeSubstitute(type, Variance.INVARIANT);
                        Intrinsics.checkExpressionValueIsNotNull(safeSubstitute, "TypeConstructorSubstitut…uted, Variance.INVARIANT)");
                        type = approximate(safeSubstitute);
                    } else {
                        type = TypeConstructorSubstitution.Companion.create(type2).buildSubstitutor().safeSubstitute(type, Variance.INVARIANT);
                        Intrinsics.checkExpressionValueIsNotNull(type, "TypeConstructorSubstitut…uted, Variance.INVARIANT)");
                    }
                    isMarkedNullable = isMarkedNullable || type2.isMarkedNullable();
                }
                TypeConstructor constructor3 = type.getConstructor();
                if (!typeCheckingProcedureCallbacks.assertEqualTypeConstructors(constructor3, constructor)) {
                    throw new AssertionError("Type constructors should be equals!\nsubstitutedSuperType: " + debugInfo(constructor3) + ", \n\nsupertype: " + debugInfo(constructor) + " \n" + typeCheckingProcedureCallbacks.assertEqualTypeConstructors(constructor3, constructor));
                }
                return TypeUtils.makeNullableAsSpecified(type, isMarkedNullable);
            }
            for (KotlinType immediateSupertype : constructor2.mo5461getSupertypes()) {
                Intrinsics.checkExpressionValueIsNotNull(immediateSupertype, "immediateSupertype");
                arrayDeque.add(new SubtypePathNode(immediateSupertype, subtypePathNode));
            }
        }
        return null;
    }

    private static final KotlinType approximate(KotlinType kotlinType) {
        return CapturedTypeApproximationKt.approximateCapturedTypes(kotlinType).getUpper();
    }

    private static final String debugInfo(TypeConstructor typeConstructor) {
        final StringBuilder sb = new StringBuilder();
        Function1<String, StringBuilder> function1 = new Function1<String, StringBuilder>() { // from class: kotlin.reflect.jvm.internal.impl.types.checker.UtilsKt$debugInfo$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final StringBuilder invoke(String unaryPlus) {
                Intrinsics.checkParameterIsNotNull(unaryPlus, "$this$unaryPlus");
                StringBuilder sb2 = sb;
                sb2.append(unaryPlus);
                Intrinsics.checkExpressionValueIsNotNull(sb2, "append(value)");
                return StringsKt.appendln(sb2);
            }
        };
        function1.invoke("type: " + typeConstructor);
        function1.invoke("hashCode: " + typeConstructor.hashCode());
        function1.invoke("javaClass: " + typeConstructor.getClass().getCanonicalName());
        for (ClassifierDescriptor mo5460getDeclarationDescriptor = typeConstructor.mo5460getDeclarationDescriptor(); mo5460getDeclarationDescriptor != null; mo5460getDeclarationDescriptor = mo5460getDeclarationDescriptor.getContainingDeclaration()) {
            function1.invoke("fqName: " + DescriptorRenderer.FQ_NAMES_IN_TYPES.render(mo5460getDeclarationDescriptor));
            function1.invoke("javaClass: " + mo5460getDeclarationDescriptor.getClass().getCanonicalName());
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
