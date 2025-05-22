package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;

/* compiled from: ReflectKotlinClass.kt */
/* loaded from: classes8.dex */
final class SignatureSerializer {
    public static final SignatureSerializer INSTANCE = new SignatureSerializer();

    private SignatureSerializer() {
    }

    public final String methodDesc(Method method) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (Class<?> parameterType : method.getParameterTypes()) {
            Intrinsics.checkExpressionValueIsNotNull(parameterType, "parameterType");
            sb.append(ReflectClassUtilKt.getDesc(parameterType));
        }
        sb.append(")");
        Class<?> returnType = method.getReturnType();
        Intrinsics.checkExpressionValueIsNotNull(returnType, "method.returnType");
        sb.append(ReflectClassUtilKt.getDesc(returnType));
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
        return sb2;
    }

    public final String constructorDesc(Constructor<?> constructor) {
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (Class<?> parameterType : constructor.getParameterTypes()) {
            Intrinsics.checkExpressionValueIsNotNull(parameterType, "parameterType");
            sb.append(ReflectClassUtilKt.getDesc(parameterType));
        }
        sb.append(")V");
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
        return sb2;
    }

    public final String fieldDesc(Field field) {
        Intrinsics.checkParameterIsNotNull(field, "field");
        Class<?> type = field.getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "field.type");
        return ReflectClassUtilKt.getDesc(type);
    }
}
