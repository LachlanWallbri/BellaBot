package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: NameResolver.kt */
/* loaded from: classes2.dex */
public interface NameResolver {
    String getQualifiedClassName(int i);

    String getString(int i);

    boolean isLocalClassName(int i);
}
