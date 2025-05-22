package retrofit2;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
abstract class ServiceMethod<T> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract T invoke(Object[] objArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> ServiceMethod<T> parseAnnotations(Retrofit retrofit, Method method) {
        RequestFactory parseAnnotations = RequestFactory.parseAnnotations(retrofit, method);
        Type genericReturnType = method.getGenericReturnType();
        if (Utils.hasUnresolvableType(genericReturnType)) {
            throw Utils.methodError(method, "Method return type must not include a type variable or wildcard: %s", genericReturnType);
        }
        if (genericReturnType == Void.TYPE) {
            throw Utils.methodError(method, "Service methods cannot return void.", new Object[0]);
        }
        return HttpServiceMethod.parseAnnotations(retrofit, method, parseAnnotations);
    }
}
