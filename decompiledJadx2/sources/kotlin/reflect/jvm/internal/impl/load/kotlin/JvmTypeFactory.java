package kotlin.reflect.jvm.internal.impl.load.kotlin;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: typeSignatureMapping.kt */
/* loaded from: classes2.dex */
public interface JvmTypeFactory<T> {
    T boxType(T t);

    T createFromString(String str);

    T createObjectType(String str);

    T getJavaLangClassType();

    String toString(T t);
}
