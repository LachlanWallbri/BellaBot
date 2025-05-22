package kotlin.reflect.jvm;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty;
import kotlin.reflect.KProperty;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KType;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.internal.KCallableImpl;
import kotlin.reflect.jvm.internal.KPackageImpl;
import kotlin.reflect.jvm.internal.KPropertyImpl;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: ReflectJvmMapping.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010%\u001a\u0004\u0018\u00010&*\u00020'H\u0002\"/\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00038F¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u001b\u0010\b\u001a\u0004\u0018\u00010\t*\u0006\u0012\u0002\b\u00030\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u001b\u0010\r\u001a\u0004\u0018\u00010\u000e*\u0006\u0012\u0002\b\u00030\n8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\"\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u0006\u0012\u0002\b\u00030\u00038F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\"\u001b\u0010\u0014\u001a\u0004\u0018\u00010\u000e*\u0006\u0012\u0002\b\u00030\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017\"\u0015\u0010\u0018\u001a\u00020\u0019*\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\"-\u0010\u001d\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0003\"\b\b\u0000\u0010\u0002*\u00020\u001e*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 \"\u001b\u0010\u001d\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003*\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010!\"\u001b\u0010\"\u001a\b\u0012\u0002\b\u0003\u0018\u00010\n*\u00020\t8F¢\u0006\u0006\u001a\u0004\b#\u0010$¨\u0006("}, m3961d2 = {"javaConstructor", "Ljava/lang/reflect/Constructor;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/reflect/KFunction;", "javaConstructor$annotations", "(Lkotlin/reflect/KFunction;)V", "getJavaConstructor", "(Lkotlin/reflect/KFunction;)Ljava/lang/reflect/Constructor;", "javaField", "Ljava/lang/reflect/Field;", "Lkotlin/reflect/KProperty;", "getJavaField", "(Lkotlin/reflect/KProperty;)Ljava/lang/reflect/Field;", "javaGetter", "Ljava/lang/reflect/Method;", "getJavaGetter", "(Lkotlin/reflect/KProperty;)Ljava/lang/reflect/Method;", "javaMethod", "getJavaMethod", "(Lkotlin/reflect/KFunction;)Ljava/lang/reflect/Method;", "javaSetter", "Lkotlin/reflect/KMutableProperty;", "getJavaSetter", "(Lkotlin/reflect/KMutableProperty;)Ljava/lang/reflect/Method;", "javaType", "Ljava/lang/reflect/Type;", "Lkotlin/reflect/KType;", "getJavaType", "(Lkotlin/reflect/KType;)Ljava/lang/reflect/Type;", "kotlinFunction", "", "getKotlinFunction", "(Ljava/lang/reflect/Constructor;)Lkotlin/reflect/KFunction;", "(Ljava/lang/reflect/Method;)Lkotlin/reflect/KFunction;", "kotlinProperty", "getKotlinProperty", "(Ljava/lang/reflect/Field;)Lkotlin/reflect/KProperty;", "getKPackage", "Lkotlin/reflect/KDeclarationContainer;", "Ljava/lang/reflect/Member;", "kotlin-reflection"}, m3962k = 2, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class ReflectJvmMapping {

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 15})
    /* loaded from: classes2.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KotlinClassHeader.Kind.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[KotlinClassHeader.Kind.FILE_FACADE.ordinal()] = 1;
            $EnumSwitchMapping$0[KotlinClassHeader.Kind.MULTIFILE_CLASS.ordinal()] = 2;
            $EnumSwitchMapping$0[KotlinClassHeader.Kind.MULTIFILE_CLASS_PART.ordinal()] = 3;
        }
    }

    public static /* synthetic */ void javaConstructor$annotations(KFunction kFunction) {
    }

    public static final Field getJavaField(KProperty<?> javaField) {
        Intrinsics.checkParameterIsNotNull(javaField, "$this$javaField");
        KPropertyImpl<?> asKPropertyImpl = UtilKt.asKPropertyImpl(javaField);
        if (asKPropertyImpl != null) {
            return asKPropertyImpl.getJavaField();
        }
        return null;
    }

    public static final Method getJavaGetter(KProperty<?> javaGetter) {
        Intrinsics.checkParameterIsNotNull(javaGetter, "$this$javaGetter");
        return getJavaMethod(javaGetter.getGetter());
    }

    public static final Method getJavaSetter(KMutableProperty<?> javaSetter) {
        Intrinsics.checkParameterIsNotNull(javaSetter, "$this$javaSetter");
        return getJavaMethod(javaSetter.getSetter());
    }

    public static final Method getJavaMethod(KFunction<?> javaMethod) {
        Caller<?> caller;
        Intrinsics.checkParameterIsNotNull(javaMethod, "$this$javaMethod");
        KCallableImpl<?> asKCallableImpl = UtilKt.asKCallableImpl(javaMethod);
        Object mo5452getMember = (asKCallableImpl == null || (caller = asKCallableImpl.getCaller()) == null) ? null : caller.mo5452getMember();
        return (Method) (mo5452getMember instanceof Method ? mo5452getMember : null);
    }

    public static final <T> Constructor<T> getJavaConstructor(KFunction<? extends T> javaConstructor) {
        Caller<?> caller;
        Intrinsics.checkParameterIsNotNull(javaConstructor, "$this$javaConstructor");
        KCallableImpl<?> asKCallableImpl = UtilKt.asKCallableImpl(javaConstructor);
        Object mo5452getMember = (asKCallableImpl == null || (caller = asKCallableImpl.getCaller()) == null) ? null : caller.mo5452getMember();
        return (Constructor) (mo5452getMember instanceof Constructor ? mo5452getMember : null);
    }

    public static final Type getJavaType(KType javaType) {
        Intrinsics.checkParameterIsNotNull(javaType, "$this$javaType");
        return ((KTypeImpl) javaType).getJavaType$kotlin_reflection();
    }

    public static final KProperty<?> getKotlinProperty(Field kotlinProperty) {
        Intrinsics.checkParameterIsNotNull(kotlinProperty, "$this$kotlinProperty");
        Object obj = null;
        if (kotlinProperty.isSynthetic()) {
            return null;
        }
        KDeclarationContainer kPackage = getKPackage(kotlinProperty);
        if (kPackage != null) {
            Collection<KCallable<?>> members = kPackage.getMembers();
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : members) {
                if (obj2 instanceof KProperty) {
                    arrayList.add(obj2);
                }
            }
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (Intrinsics.areEqual(getJavaField((KProperty) next), kotlinProperty)) {
                    obj = next;
                    break;
                }
            }
            return (KProperty) obj;
        }
        Class<?> declaringClass = kotlinProperty.getDeclaringClass();
        Intrinsics.checkExpressionValueIsNotNull(declaringClass, "declaringClass");
        Iterator it2 = KClasses.getMemberProperties(JvmClassMappingKt.getKotlinClass(declaringClass)).iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Object next2 = it2.next();
            if (Intrinsics.areEqual(getJavaField((KProperty1) next2), kotlinProperty)) {
                obj = next2;
                break;
            }
        }
        return (KProperty) obj;
    }

    private static final KDeclarationContainer getKPackage(Member member) {
        KotlinClassHeader classHeader;
        ReflectKotlinClass.Companion companion = ReflectKotlinClass.INSTANCE;
        Class<?> declaringClass = member.getDeclaringClass();
        Intrinsics.checkExpressionValueIsNotNull(declaringClass, "declaringClass");
        ReflectKotlinClass create = companion.create(declaringClass);
        String str = null;
        byte b = 0;
        KotlinClassHeader.Kind kind = (create == null || (classHeader = create.getClassHeader()) == null) ? null : classHeader.getKind();
        if (kind == null) {
            return null;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
        int i2 = 2;
        if (i != 1 && i != 2 && i != 3) {
            return null;
        }
        Class<?> declaringClass2 = member.getDeclaringClass();
        Intrinsics.checkExpressionValueIsNotNull(declaringClass2, "declaringClass");
        return new KPackageImpl(declaringClass2, str, i2, b == true ? 1 : 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00ca A[EDGE_INSN: B:47:0x00ca->B:48:0x00ca BREAK  A[LOOP:2: B:31:0x007c->B:51:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:? A[LOOP:2: B:31:0x007c->B:51:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final KFunction<?> getKotlinFunction(Method kotlinFunction) {
        Object obj;
        boolean z;
        Intrinsics.checkParameterIsNotNull(kotlinFunction, "$this$kotlinFunction");
        Object obj2 = null;
        if (Modifier.isStatic(kotlinFunction.getModifiers())) {
            KDeclarationContainer kPackage = getKPackage(kotlinFunction);
            if (kPackage != null) {
                Collection<KCallable<?>> members = kPackage.getMembers();
                ArrayList arrayList = new ArrayList();
                for (Object obj3 : members) {
                    if (obj3 instanceof KFunction) {
                        arrayList.add(obj3);
                    }
                }
                Iterator it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object next = it.next();
                    if (Intrinsics.areEqual(getJavaMethod((KFunction) next), kotlinFunction)) {
                        obj2 = next;
                        break;
                    }
                }
                return (KFunction) obj2;
            }
            Class<?> declaringClass = kotlinFunction.getDeclaringClass();
            Intrinsics.checkExpressionValueIsNotNull(declaringClass, "declaringClass");
            KClass<?> companionObject = KClasses.getCompanionObject(JvmClassMappingKt.getKotlinClass(declaringClass));
            if (companionObject != null) {
                Iterator<T> it2 = KClasses.getFunctions(companionObject).iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it2.next();
                    Method javaMethod = getJavaMethod((KFunction) obj);
                    if (javaMethod != null && Intrinsics.areEqual(javaMethod.getName(), kotlinFunction.getName())) {
                        Class<?>[] parameterTypes = javaMethod.getParameterTypes();
                        if (parameterTypes == null) {
                            Intrinsics.throwNpe();
                        }
                        Class<?>[] parameterTypes2 = kotlinFunction.getParameterTypes();
                        Intrinsics.checkExpressionValueIsNotNull(parameterTypes2, "this.parameterTypes");
                        if (Arrays.equals(parameterTypes, parameterTypes2) && Intrinsics.areEqual(javaMethod.getReturnType(), kotlinFunction.getReturnType())) {
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
                KFunction<?> kFunction = (KFunction) obj;
                if (kFunction != null) {
                    return kFunction;
                }
            }
        }
        Class<?> declaringClass2 = kotlinFunction.getDeclaringClass();
        Intrinsics.checkExpressionValueIsNotNull(declaringClass2, "declaringClass");
        Iterator<T> it3 = KClasses.getFunctions(JvmClassMappingKt.getKotlinClass(declaringClass2)).iterator();
        while (true) {
            if (!it3.hasNext()) {
                break;
            }
            Object next2 = it3.next();
            if (Intrinsics.areEqual(getJavaMethod((KFunction) next2), kotlinFunction)) {
                obj2 = next2;
                break;
            }
        }
        return (KFunction) obj2;
    }

    public static final <T> KFunction<T> getKotlinFunction(Constructor<T> kotlinFunction) {
        T t;
        Intrinsics.checkParameterIsNotNull(kotlinFunction, "$this$kotlinFunction");
        Class<T> declaringClass = kotlinFunction.getDeclaringClass();
        Intrinsics.checkExpressionValueIsNotNull(declaringClass, "declaringClass");
        Iterator<T> it = JvmClassMappingKt.getKotlinClass(declaringClass).getConstructors().iterator();
        while (true) {
            if (!it.hasNext()) {
                t = null;
                break;
            }
            t = it.next();
            if (Intrinsics.areEqual(getJavaConstructor((KFunction) t), kotlinFunction)) {
                break;
            }
        }
        return (KFunction) t;
    }
}
