package com.pudutech.mirsdk.mircore;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: MirCoreBinder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\n¢\u0006\u0002\b\t"}, m3961d2 = {"<anonymous>", "", "flag", "", "px", "", "py", "dist", "degree", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MirCoreBinder$addChildrenListener$1 extends Lambda implements Function5<Integer, Double, Double, Double, Double, Unit> {
    public static final MirCoreBinder$addChildrenListener$1 INSTANCE = new MirCoreBinder$addChildrenListener$1();

    MirCoreBinder$addChildrenListener$1() {
        super(5);
    }

    @Override // kotlin.jvm.functions.Function5
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Double d, Double d2, Double d3, Double d4) {
        invoke(num.intValue(), d.doubleValue(), d2.doubleValue(), d3.doubleValue(), d4.doubleValue());
        return Unit.INSTANCE;
    }

    public final void invoke(final int i, final double d, final double d2, final double d3, final double d4) {
        MirCoreImpl.INSTANCE.getChildrenListener$mircore_packRelease().notify(new Function2<ChildrenListener, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreBinder$addChildrenListener$1.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ChildrenListener childrenListener, String str) {
                invoke2(childrenListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ChildrenListener it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.onDetection(i, d, d2, d3, d4);
            }
        });
    }
}
