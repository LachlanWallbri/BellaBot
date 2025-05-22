package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public interface TypeConstructor extends TypeConstructorMarker {
    KotlinBuiltIns getBuiltIns();

    /* renamed from: getDeclarationDescriptor */
    ClassifierDescriptor mo5460getDeclarationDescriptor();

    List<TypeParameterDescriptor> getParameters();

    /* renamed from: getSupertypes */
    Collection<KotlinType> mo5461getSupertypes();

    boolean isDenotable();
}
