package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

/* compiled from: LazyJavaClassDescriptor.kt */
/* loaded from: classes8.dex */
final class LazyJavaClassDescriptor$scopeHolder$1 extends Lambda implements Function1<KotlinTypeRefiner, LazyJavaClassMemberScope> {
    final /* synthetic */ LazyJavaClassDescriptor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LazyJavaClassDescriptor$scopeHolder$1(LazyJavaClassDescriptor lazyJavaClassDescriptor) {
        super(1);
        this.this$0 = lazyJavaClassDescriptor;
    }

    @Override // kotlin.jvm.functions.Function1
    public final LazyJavaClassMemberScope invoke(KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
        LazyJavaResolverContext lazyJavaResolverContext = this.this$0.f8763c;
        LazyJavaClassDescriptor lazyJavaClassDescriptor = this.this$0;
        return new LazyJavaClassMemberScope(lazyJavaResolverContext, lazyJavaClassDescriptor, lazyJavaClassDescriptor.getJClass(), this.this$0.additionalSupertypeClassDescriptor != null, LazyJavaClassDescriptor.access$getUnsubstitutedMemberScope$p(this.this$0));
    }
}
