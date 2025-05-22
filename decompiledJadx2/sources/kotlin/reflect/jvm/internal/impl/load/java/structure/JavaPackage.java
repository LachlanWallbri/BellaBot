package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: javaElements.kt */
/* loaded from: classes2.dex */
public interface JavaPackage extends JavaAnnotationOwner, JavaElement {
    Collection<JavaClass> getClasses(Function1<? super Name, Boolean> function1);

    FqName getFqName();

    Collection<JavaPackage> getSubPackages();
}
