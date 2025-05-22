package com.pudutech.mirsdk.mircore;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: MirCoreImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "status", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MirCoreImpl$initializerOnLoadTopoMap$1 extends Lambda implements Function1<Integer, Unit> {
    public static final MirCoreImpl$initializerOnLoadTopoMap$1 INSTANCE = new MirCoreImpl$initializerOnLoadTopoMap$1();

    MirCoreImpl$initializerOnLoadTopoMap$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
        invoke(num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(int i) {
        MirCoreImpl.INSTANCE.getNavigationer().updateCostmapStatus(i);
    }
}
