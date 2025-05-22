package kotlin.reflect.jvm.internal;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KClassImpl;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: KClassImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlin/reflect/jvm/internal/KTypeImpl;", ExifInterface.GPS_DIRECTION_TRUE, "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class KClassImpl$Data$supertypes$2 extends Lambda implements Function0<List<? extends KTypeImpl>> {
    final /* synthetic */ KClassImpl.Data this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KClassImpl$Data$supertypes$2(KClassImpl.Data data) {
        super(0);
        this.this$0 = data;
    }

    @Override // kotlin.jvm.functions.Function0
    public final List<? extends KTypeImpl> invoke() {
        TypeConstructor typeConstructor = this.this$0.getDescriptor().getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "descriptor.typeConstructor");
        Collection<KotlinType> mo5461getSupertypes = typeConstructor.mo5461getSupertypes();
        Intrinsics.checkExpressionValueIsNotNull(mo5461getSupertypes, "descriptor.typeConstructor.supertypes");
        ArrayList arrayList = new ArrayList(mo5461getSupertypes.size());
        for (final KotlinType kotlinType : mo5461getSupertypes) {
            Intrinsics.checkExpressionValueIsNotNull(kotlinType, "kotlinType");
            arrayList.add(new KTypeImpl(kotlinType, new Function0<Type>() { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2$$special$$inlined$mapTo$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Type invoke() {
                    ClassifierDescriptor mo5460getDeclarationDescriptor = KotlinType.this.getConstructor().mo5460getDeclarationDescriptor();
                    if (!(mo5460getDeclarationDescriptor instanceof ClassDescriptor)) {
                        throw new KotlinReflectionInternalError("Supertype not a class: " + mo5460getDeclarationDescriptor);
                    }
                    Class<?> javaClass = UtilKt.toJavaClass((ClassDescriptor) mo5460getDeclarationDescriptor);
                    if (javaClass == null) {
                        throw new KotlinReflectionInternalError("Unsupported superclass of " + this.this$0 + ": " + mo5460getDeclarationDescriptor);
                    }
                    if (Intrinsics.areEqual(KClassImpl.this.getJClass().getSuperclass(), javaClass)) {
                        Type genericSuperclass = KClassImpl.this.getJClass().getGenericSuperclass();
                        Intrinsics.checkExpressionValueIsNotNull(genericSuperclass, "jClass.genericSuperclass");
                        return genericSuperclass;
                    }
                    Class<?>[] interfaces = KClassImpl.this.getJClass().getInterfaces();
                    Intrinsics.checkExpressionValueIsNotNull(interfaces, "jClass.interfaces");
                    int indexOf = ArraysKt.indexOf(interfaces, javaClass);
                    if (indexOf < 0) {
                        throw new KotlinReflectionInternalError("No superclass of " + this.this$0 + " in Java reflection for " + mo5460getDeclarationDescriptor);
                    }
                    Type type = KClassImpl.this.getJClass().getGenericInterfaces()[indexOf];
                    Intrinsics.checkExpressionValueIsNotNull(type, "jClass.genericInterfaces[index]");
                    return type;
                }
            }));
        }
        ArrayList arrayList2 = arrayList;
        if (!KotlinBuiltIns.isSpecialClassWithNoSupertypes(this.this$0.getDescriptor())) {
            ArrayList arrayList3 = arrayList;
            boolean z = false;
            if (!(arrayList3 instanceof Collection) || !arrayList3.isEmpty()) {
                Iterator it = arrayList3.iterator();
                while (it.hasNext()) {
                    ClassDescriptor classDescriptorForType = DescriptorUtils.getClassDescriptorForType(((KTypeImpl) it.next()).getType());
                    Intrinsics.checkExpressionValueIsNotNull(classDescriptorForType, "DescriptorUtils.getClassDescriptorForType(it.type)");
                    ClassKind kind = classDescriptorForType.getKind();
                    Intrinsics.checkExpressionValueIsNotNull(kind, "DescriptorUtils.getClass…ptorForType(it.type).kind");
                    if (!(kind == ClassKind.INTERFACE || kind == ClassKind.ANNOTATION_CLASS)) {
                        break;
                    }
                }
            }
            z = true;
            if (z) {
                SimpleType anyType = DescriptorUtilsKt.getBuiltIns(this.this$0.getDescriptor()).getAnyType();
                Intrinsics.checkExpressionValueIsNotNull(anyType, "descriptor.builtIns.anyType");
                arrayList2.add(new KTypeImpl(anyType, new Function0<Class<Object>>() { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2.3
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Class<Object> invoke() {
                        return Object.class;
                    }
                }));
            }
        }
        return CollectionsKt.compact(arrayList);
    }
}
