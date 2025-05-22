package kotlin.reflect.jvm.internal.impl.load.java.structure;

import kotlin.reflect.jvm.internal.impl.name.Name;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: javaElements.kt */
/* loaded from: classes2.dex */
public interface JavaValueParameter extends JavaAnnotationOwner {
    Name getName();

    JavaType getType();

    boolean isVararg();
}
