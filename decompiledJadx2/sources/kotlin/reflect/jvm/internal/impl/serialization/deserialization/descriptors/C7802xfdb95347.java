package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.Collection;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* compiled from: DeserializedClassDescriptor.kt */
/* renamed from: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$DeserializedClassMemberScope$refinedSupertypes$1 */
/* loaded from: classes8.dex */
final class C7802xfdb95347 extends Lambda implements Function0<Collection<? extends KotlinType>> {
    final /* synthetic */ DeserializedClassDescriptor.DeserializedClassMemberScope this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C7802xfdb95347(DeserializedClassDescriptor.DeserializedClassMemberScope deserializedClassMemberScope) {
        super(0);
        this.this$0 = deserializedClassMemberScope;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Collection<? extends KotlinType> invoke() {
        return DeserializedClassDescriptor.DeserializedClassMemberScope.access$getKotlinTypeRefiner$p(this.this$0).refineSupertypes(DeserializedClassDescriptor.DeserializedClassMemberScope.access$getClassDescriptor$p(this.this$0));
    }
}
