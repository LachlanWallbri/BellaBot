package kotlin.reflect.full;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KProperty1;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: KClasses.kt */
@Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
final /* synthetic */ class KClasses$isSubclassOf$1 extends PropertyReference1 {
    public static final KProperty1 INSTANCE = new KClasses$isSubclassOf$1();

    KClasses$isSubclassOf$1() {
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "superclasses";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinPackage(KClasses.class, "kotlin-reflection");
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getSuperclasses(Lkotlin/reflect/KClass;)Ljava/util/List;";
    }

    @Override // kotlin.reflect.KProperty1
    public Object get(Object obj) {
        return KClasses.getSuperclasses((KClass) obj);
    }
}
