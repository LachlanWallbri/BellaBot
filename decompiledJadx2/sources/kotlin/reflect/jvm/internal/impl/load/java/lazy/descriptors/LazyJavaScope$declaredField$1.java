package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* compiled from: LazyJavaScope.kt */
/* loaded from: classes8.dex */
final class LazyJavaScope$declaredField$1 extends Lambda implements Function1<Name, PropertyDescriptor> {
    final /* synthetic */ LazyJavaScope this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LazyJavaScope$declaredField$1(LazyJavaScope lazyJavaScope) {
        super(1);
        this.this$0 = lazyJavaScope;
    }

    @Override // kotlin.jvm.functions.Function1
    public final PropertyDescriptor invoke(Name name) {
        PropertyDescriptor resolveProperty;
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (this.this$0.getMainScope() != null) {
            return (PropertyDescriptor) LazyJavaScope.access$getDeclaredField$p(this.this$0.getMainScope()).invoke(name);
        }
        JavaField findFieldByName = this.this$0.getDeclaredMemberIndex().invoke().findFieldByName(name);
        if (findFieldByName == null || findFieldByName.isEnumEntry()) {
            return null;
        }
        resolveProperty = this.this$0.resolveProperty(findFieldByName);
        return resolveProperty;
    }
}
