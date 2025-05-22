package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: javaElements.kt */
/* loaded from: classes2.dex */
public interface JavaConstructor extends JavaMember, JavaTypeParameterListOwner {
    List<JavaValueParameter> getValueParameters();
}
