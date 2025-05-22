package com.pudutech.mirsdk.mircore;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: MirCoreBinder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\n¢\u0006\u0002\b\t"}, m3961d2 = {"<anonymous>", "", "flag", "", "classIdx", "angle", "", "width", "dist", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MirCoreBinder$addObjDetectListener$1 extends Lambda implements Function5<Integer, Integer, Double, Double, Double, Unit> {
    public static final MirCoreBinder$addObjDetectListener$1 INSTANCE = new MirCoreBinder$addObjDetectListener$1();

    MirCoreBinder$addObjDetectListener$1() {
        super(5);
    }

    @Override // kotlin.jvm.functions.Function5
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2, Double d, Double d2, Double d3) {
        invoke(num.intValue(), num2.intValue(), d.doubleValue(), d2.doubleValue(), d3.doubleValue());
        return Unit.INSTANCE;
    }

    public final void invoke(final int i, final int i2, final double d, final double d2, final double d3) {
        MirCoreImpl.INSTANCE.getObjDetectListener$mircore_packRelease().notify(new Function2<ObjDetectListener, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreBinder$addObjDetectListener$1.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ObjDetectListener objDetectListener, String str) {
                invoke2(objDetectListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ObjDetectListener it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.onDetection(i, i2, d, d2, d3);
            }
        });
    }
}
