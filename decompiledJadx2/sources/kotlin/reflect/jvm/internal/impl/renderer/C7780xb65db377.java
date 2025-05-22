package kotlin.reflect.jvm.internal.impl.renderer;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: DescriptorRenderer.kt */
/* renamed from: kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer$Companion$FQ_NAMES_IN_TYPES_WITH_ANNOTATIONS$1 */
/* loaded from: classes8.dex */
final class C7780xb65db377 extends Lambda implements Function1<DescriptorRendererOptions, Unit> {
    public static final C7780xb65db377 INSTANCE = new C7780xb65db377();

    C7780xb65db377() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(DescriptorRendererOptions descriptorRendererOptions) {
        invoke2(descriptorRendererOptions);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(DescriptorRendererOptions receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.setModifiers(DescriptorRendererModifier.ALL);
    }
}
