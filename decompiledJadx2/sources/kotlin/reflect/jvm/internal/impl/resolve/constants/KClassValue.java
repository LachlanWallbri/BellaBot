package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: constantValues.kt */
/* loaded from: classes2.dex */
public final class KClassValue extends ConstantValue<Value> {
    public static final Companion Companion = new Companion(null);

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: constantValues.kt */
    /* loaded from: classes2.dex */
    public static abstract class Value {

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
          classes8.dex
         */
        /* compiled from: constantValues.kt */
        /* loaded from: classes2.dex */
        public static final class NormalClass extends Value {
            private final ClassLiteralValue value;

            public boolean equals(Object obj) {
                if (this != obj) {
                    return (obj instanceof NormalClass) && Intrinsics.areEqual(this.value, ((NormalClass) obj).value);
                }
                return true;
            }

            public int hashCode() {
                ClassLiteralValue classLiteralValue = this.value;
                if (classLiteralValue != null) {
                    return classLiteralValue.hashCode();
                }
                return 0;
            }

            public String toString() {
                return "NormalClass(value=" + this.value + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public NormalClass(ClassLiteralValue value) {
                super(null);
                Intrinsics.checkParameterIsNotNull(value, "value");
                this.value = value;
            }

            public final ClassLiteralValue getValue() {
                return this.value;
            }

            public final ClassId getClassId() {
                return this.value.getClassId();
            }

            public final int getArrayDimensions() {
                return this.value.getArrayNestedness();
            }
        }

        private Value() {
        }

        public /* synthetic */ Value(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
          classes8.dex
         */
        /* compiled from: constantValues.kt */
        /* loaded from: classes2.dex */
        public static final class LocalClass extends Value {
            private final KotlinType type;

            public boolean equals(Object obj) {
                if (this != obj) {
                    return (obj instanceof LocalClass) && Intrinsics.areEqual(this.type, ((LocalClass) obj).type);
                }
                return true;
            }

            public int hashCode() {
                KotlinType kotlinType = this.type;
                if (kotlinType != null) {
                    return kotlinType.hashCode();
                }
                return 0;
            }

            public String toString() {
                return "LocalClass(type=" + this.type + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public LocalClass(KotlinType type) {
                super(null);
                Intrinsics.checkParameterIsNotNull(type, "type");
                this.type = type;
            }

            public final KotlinType getType() {
                return this.type;
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KClassValue(Value value) {
        super(value);
        Intrinsics.checkParameterIsNotNull(value, "value");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public KClassValue(ClassLiteralValue value) {
        this(new Value.NormalClass(value));
        Intrinsics.checkParameterIsNotNull(value, "value");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public KClassValue(ClassId classId, int i) {
        this(new ClassLiteralValue(classId, i));
        Intrinsics.checkParameterIsNotNull(classId, "classId");
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    public KotlinType getType(ModuleDescriptor module) {
        Intrinsics.checkParameterIsNotNull(module, "module");
        Annotations empty = Annotations.Companion.getEMPTY();
        ClassDescriptor kClass = module.getBuiltIns().getKClass();
        Intrinsics.checkExpressionValueIsNotNull(kClass, "module.builtIns.kClass");
        return KotlinTypeFactory.simpleNotNullType(empty, kClass, CollectionsKt.listOf(new TypeProjectionImpl(getArgumentType(module))));
    }

    public final KotlinType getArgumentType(ModuleDescriptor module) {
        Intrinsics.checkParameterIsNotNull(module, "module");
        Value value = getValue();
        if (value instanceof Value.LocalClass) {
            return ((Value.LocalClass) getValue()).getType();
        }
        if (value instanceof Value.NormalClass) {
            ClassLiteralValue value2 = ((Value.NormalClass) getValue()).getValue();
            ClassId component1 = value2.component1();
            int component2 = value2.component2();
            ClassDescriptor findClassAcrossModuleDependencies = FindClassInModuleKt.findClassAcrossModuleDependencies(module, component1);
            if (findClassAcrossModuleDependencies == null) {
                SimpleType createErrorType = ErrorUtils.createErrorType("Unresolved type: " + component1 + " (arrayDimensions=" + component2 + ')');
                Intrinsics.checkExpressionValueIsNotNull(createErrorType, "ErrorUtils.createErrorTy…sions=$arrayDimensions)\")");
                return createErrorType;
            }
            SimpleType defaultType = findClassAcrossModuleDependencies.getDefaultType();
            Intrinsics.checkExpressionValueIsNotNull(defaultType, "descriptor.defaultType");
            SimpleType replaceArgumentsWithStarProjections = TypeUtilsKt.replaceArgumentsWithStarProjections(defaultType);
            for (int i = 0; i < component2; i++) {
                SimpleType arrayType = module.getBuiltIns().getArrayType(Variance.INVARIANT, replaceArgumentsWithStarProjections);
                Intrinsics.checkExpressionValueIsNotNull(arrayType, "module.builtIns.getArray…Variance.INVARIANT, type)");
                replaceArgumentsWithStarProjections = arrayType;
            }
            return replaceArgumentsWithStarProjections;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: constantValues.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ConstantValue<?> create(KotlinType argumentType) {
            Intrinsics.checkParameterIsNotNull(argumentType, "argumentType");
            if (KotlinTypeKt.isError(argumentType)) {
                return null;
            }
            KotlinType kotlinType = argumentType;
            int i = 0;
            while (KotlinBuiltIns.isArray(kotlinType)) {
                kotlinType = ((TypeProjection) CollectionsKt.single((List) kotlinType.getArguments())).getType();
                Intrinsics.checkExpressionValueIsNotNull(kotlinType, "type.arguments.single().type");
                i++;
            }
            ClassifierDescriptor mo5460getDeclarationDescriptor = kotlinType.getConstructor().mo5460getDeclarationDescriptor();
            if (mo5460getDeclarationDescriptor instanceof ClassDescriptor) {
                ClassId classId = DescriptorUtilsKt.getClassId(mo5460getDeclarationDescriptor);
                if (classId == null) {
                    return new KClassValue(new Value.LocalClass(argumentType));
                }
                return new KClassValue(classId, i);
            }
            if (!(mo5460getDeclarationDescriptor instanceof TypeParameterDescriptor)) {
                return null;
            }
            ClassId classId2 = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.any.toSafe());
            Intrinsics.checkExpressionValueIsNotNull(classId2, "ClassId.topLevel(KotlinB…ns.FQ_NAMES.any.toSafe())");
            return new KClassValue(classId2, 0);
        }
    }
}
