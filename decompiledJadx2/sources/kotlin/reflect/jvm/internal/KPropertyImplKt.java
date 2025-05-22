package kotlin.reflect.jvm.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin._Assertions;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.JvmFunctionSignature;
import kotlin.reflect.jvm.internal.JvmPropertySignature;
import kotlin.reflect.jvm.internal.KPropertyImpl;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.calls.CallerImpl;
import kotlin.reflect.jvm.internal.calls.InlineClassAwareCallerKt;
import kotlin.reflect.jvm.internal.calls.InternalUnderlyingValOfInlineClass;
import kotlin.reflect.jvm.internal.calls.ThrowingCaller;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;

/* compiled from: KPropertyImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\f\u0010\t\u001a\u00020\b*\u00020\nH\u0002\"\"\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u000b"}, m3961d2 = {"boundReceiver", "", "Lkotlin/reflect/jvm/internal/KPropertyImpl$Accessor;", "getBoundReceiver", "(Lkotlin/reflect/jvm/internal/KPropertyImpl$Accessor;)Ljava/lang/Object;", "computeCallerForAccessor", "Lkotlin/reflect/jvm/internal/calls/Caller;", "isGetter", "", "isJvmFieldPropertyInCompanionObject", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "kotlin-reflection"}, m3962k = 2, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class KPropertyImplKt {
    public static final /* synthetic */ Caller access$computeCallerForAccessor(KPropertyImpl.Accessor accessor, boolean z) {
        return computeCallerForAccessor(accessor, z);
    }

    public static final Object getBoundReceiver(KPropertyImpl.Accessor<?, ?> boundReceiver) {
        Intrinsics.checkParameterIsNotNull(boundReceiver, "$this$boundReceiver");
        return boundReceiver.getProperty().getBoundReceiver();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0083  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Caller<?> computeCallerForAccessor(final KPropertyImpl.Accessor<?, ?> accessor, final boolean z) {
        JvmFunctionSignature.KotlinFunction setterSignature;
        Method setterMethod;
        Caller boundInstance;
        JvmProtoBuf.JvmMethodSignature setter;
        Method findMethodBySignature;
        Method unboxMethod;
        if (KDeclarationContainerImpl.INSTANCE.getLOCAL_PROPERTY_SIGNATURE$kotlin_reflection().matches(accessor.getProperty().getSignature())) {
            return ThrowingCaller.INSTANCE;
        }
        final KPropertyImplKt$computeCallerForAccessor$1 kPropertyImplKt$computeCallerForAccessor$1 = new KPropertyImplKt$computeCallerForAccessor$1(accessor);
        final KPropertyImplKt$computeCallerForAccessor$2 kPropertyImplKt$computeCallerForAccessor$2 = new KPropertyImplKt$computeCallerForAccessor$2(accessor);
        Function1<Field, CallerImpl<? extends Field>> function1 = new Function1<Field, CallerImpl<? extends Field>>() { // from class: kotlin.reflect.jvm.internal.KPropertyImplKt$computeCallerForAccessor$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final CallerImpl<Field> invoke(Field field) {
                boolean isJvmFieldPropertyInCompanionObject;
                Intrinsics.checkParameterIsNotNull(field, "field");
                isJvmFieldPropertyInCompanionObject = KPropertyImplKt.isJvmFieldPropertyInCompanionObject(KPropertyImpl.Accessor.this.getProperty().getDescriptor());
                if (isJvmFieldPropertyInCompanionObject || !Modifier.isStatic(field.getModifiers())) {
                    if (z) {
                        return KPropertyImpl.Accessor.this.isBound() ? new CallerImpl.FieldGetter.BoundInstance(field, KPropertyImplKt.getBoundReceiver(KPropertyImpl.Accessor.this)) : new CallerImpl.FieldGetter.Instance(field);
                    }
                    return KPropertyImpl.Accessor.this.isBound() ? new CallerImpl.FieldSetter.BoundInstance(field, kPropertyImplKt$computeCallerForAccessor$2.invoke2(), KPropertyImplKt.getBoundReceiver(KPropertyImpl.Accessor.this)) : new CallerImpl.FieldSetter.Instance(field, kPropertyImplKt$computeCallerForAccessor$2.invoke2());
                }
                if (!kPropertyImplKt$computeCallerForAccessor$1.invoke2()) {
                    return z ? new CallerImpl.FieldGetter.Static(field) : new CallerImpl.FieldSetter.Static(field, kPropertyImplKt$computeCallerForAccessor$2.invoke2());
                }
                if (z) {
                    return KPropertyImpl.Accessor.this.isBound() ? new CallerImpl.FieldGetter.BoundJvmStaticInObject(field) : new CallerImpl.FieldGetter.JvmStaticInObject(field);
                }
                return KPropertyImpl.Accessor.this.isBound() ? new CallerImpl.FieldSetter.BoundJvmStaticInObject(field, kPropertyImplKt$computeCallerForAccessor$2.invoke2()) : new CallerImpl.FieldSetter.JvmStaticInObject(field, kPropertyImplKt$computeCallerForAccessor$2.invoke2());
            }
        };
        JvmPropertySignature mapPropertySignature = RuntimeTypeMapper.INSTANCE.mapPropertySignature(accessor.getProperty().getDescriptor());
        if (mapPropertySignature instanceof JvmPropertySignature.KotlinProperty) {
            JvmPropertySignature.KotlinProperty kotlinProperty = (JvmPropertySignature.KotlinProperty) mapPropertySignature;
            JvmProtoBuf.JvmPropertySignature signature = kotlinProperty.getSignature();
            if (z) {
                if (signature.hasGetter()) {
                    setter = signature.getGetter();
                    findMethodBySignature = setter == null ? accessor.getProperty().getContainer().findMethodBySignature(kotlinProperty.getNameResolver().getString(setter.getName()), kotlinProperty.getNameResolver().getString(setter.getDesc())) : null;
                    if (findMethodBySignature != null) {
                        if (InlineClassesUtilsKt.isUnderlyingPropertyOfInlineClass(accessor.getProperty().getDescriptor()) && Intrinsics.areEqual(accessor.getProperty().getDescriptor().getVisibility(), Visibilities.INTERNAL)) {
                            Class<?> inlineClass = InlineClassAwareCallerKt.toInlineClass(accessor.getProperty().getDescriptor().getContainingDeclaration());
                            if (inlineClass == null || (unboxMethod = InlineClassAwareCallerKt.getUnboxMethod(inlineClass, accessor.getProperty().getDescriptor())) == null) {
                                throw new KotlinReflectionInternalError("Underlying property of inline class " + accessor.getProperty() + " should have a field");
                            }
                            boundInstance = accessor.isBound() ? new InternalUnderlyingValOfInlineClass.Bound(unboxMethod, getBoundReceiver(accessor)) : new InternalUnderlyingValOfInlineClass.Unbound(unboxMethod);
                        } else {
                            Field javaField = accessor.getProperty().getJavaField();
                            if (javaField == null) {
                                throw new KotlinReflectionInternalError("No accessors or field is found for property " + accessor.getProperty());
                            }
                            boundInstance = function1.invoke(javaField);
                        }
                    } else if (!Modifier.isStatic(findMethodBySignature.getModifiers())) {
                        boundInstance = accessor.isBound() ? new CallerImpl.Method.BoundInstance(findMethodBySignature, getBoundReceiver(accessor)) : new CallerImpl.Method.Instance(findMethodBySignature);
                    } else if (kPropertyImplKt$computeCallerForAccessor$1.invoke2()) {
                        boundInstance = accessor.isBound() ? new CallerImpl.Method.BoundJvmStaticInObject(findMethodBySignature) : new CallerImpl.Method.JvmStaticInObject(findMethodBySignature);
                    } else {
                        boundInstance = accessor.isBound() ? new CallerImpl.Method.BoundStatic(findMethodBySignature, getBoundReceiver(accessor)) : new CallerImpl.Method.Static(findMethodBySignature);
                    }
                }
                setter = null;
                if (setter == null) {
                }
                if (findMethodBySignature != null) {
                }
            } else {
                if (signature.hasSetter()) {
                    setter = signature.getSetter();
                    if (setter == null) {
                    }
                    if (findMethodBySignature != null) {
                    }
                }
                setter = null;
                if (setter == null) {
                }
                if (findMethodBySignature != null) {
                }
            }
        } else if (mapPropertySignature instanceof JvmPropertySignature.JavaField) {
            boundInstance = function1.invoke(((JvmPropertySignature.JavaField) mapPropertySignature).getField());
        } else if (mapPropertySignature instanceof JvmPropertySignature.JavaMethodProperty) {
            if (z) {
                setterMethod = ((JvmPropertySignature.JavaMethodProperty) mapPropertySignature).getGetterMethod();
            } else {
                JvmPropertySignature.JavaMethodProperty javaMethodProperty = (JvmPropertySignature.JavaMethodProperty) mapPropertySignature;
                setterMethod = javaMethodProperty.getSetterMethod();
                if (setterMethod == null) {
                    throw new KotlinReflectionInternalError("No source found for setter of Java method property: " + javaMethodProperty.getGetterMethod());
                }
            }
            boundInstance = accessor.isBound() ? new CallerImpl.Method.BoundInstance(setterMethod, getBoundReceiver(accessor)) : new CallerImpl.Method.Instance(setterMethod);
        } else {
            if (mapPropertySignature instanceof JvmPropertySignature.MappedKotlinProperty) {
                if (z) {
                    setterSignature = ((JvmPropertySignature.MappedKotlinProperty) mapPropertySignature).getGetterSignature();
                } else {
                    setterSignature = ((JvmPropertySignature.MappedKotlinProperty) mapPropertySignature).getSetterSignature();
                    if (setterSignature == null) {
                        throw new KotlinReflectionInternalError("No setter found for property " + accessor.getProperty());
                    }
                }
                Method findMethodBySignature2 = accessor.getProperty().getContainer().findMethodBySignature(setterSignature.getMethodName(), setterSignature.getMethodDesc());
                if (findMethodBySignature2 == null) {
                    throw new KotlinReflectionInternalError("No accessor found for property " + accessor.getProperty());
                }
                boolean z2 = !Modifier.isStatic(findMethodBySignature2.getModifiers());
                if (!_Assertions.ENABLED || z2) {
                    return accessor.isBound() ? new CallerImpl.Method.BoundInstance(findMethodBySignature2, getBoundReceiver(accessor)) : new CallerImpl.Method.Instance(findMethodBySignature2);
                }
                throw new AssertionError("Mapped property cannot have a static accessor: " + accessor.getProperty());
            }
            throw new NoWhenBranchMatchedException();
        }
        return InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(boundInstance, accessor.getDescriptor(), false, 2, null);
    }

    public static final boolean isJvmFieldPropertyInCompanionObject(PropertyDescriptor propertyDescriptor) {
        DeclarationDescriptor containingDeclaration = propertyDescriptor.getContainingDeclaration();
        Intrinsics.checkExpressionValueIsNotNull(containingDeclaration, "containingDeclaration");
        if (!DescriptorUtils.isCompanionObject(containingDeclaration)) {
            return false;
        }
        DeclarationDescriptor containingDeclaration2 = containingDeclaration.getContainingDeclaration();
        return !(DescriptorUtils.isInterface(containingDeclaration2) || DescriptorUtils.isAnnotationClass(containingDeclaration2)) || ((propertyDescriptor instanceof DeserializedPropertyDescriptor) && JvmProtoBufUtil.isMovedFromInterfaceCompanion(((DeserializedPropertyDescriptor) propertyDescriptor).getProto()));
    }
}
