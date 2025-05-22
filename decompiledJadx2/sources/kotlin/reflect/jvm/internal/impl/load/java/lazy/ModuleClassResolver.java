package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: ModuleClassResolver.kt */
/* loaded from: classes2.dex */
public interface ModuleClassResolver {
    ClassDescriptor resolveClass(JavaClass javaClass);
}
