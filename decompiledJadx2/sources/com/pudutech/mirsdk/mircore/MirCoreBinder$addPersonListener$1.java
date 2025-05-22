package com.pudutech.mirsdk.mircore;

import com.pudutech.mirsdk.compat.topo.MapElement;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: MirCoreBinder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\n¢\u0006\u0002\b\u000b"}, m3961d2 = {"<anonymous>", "", "flag", "", "id", "yaw", "", "distance", MapElement.Key.DIR, "vx", "vy", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MirCoreBinder$addPersonListener$1 extends Lambda implements Function7<Integer, Integer, Double, Double, Double, Double, Double, Unit> {
    public static final MirCoreBinder$addPersonListener$1 INSTANCE = new MirCoreBinder$addPersonListener$1();

    MirCoreBinder$addPersonListener$1() {
        super(7);
    }

    @Override // kotlin.jvm.functions.Function7
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2, Double d, Double d2, Double d3, Double d4, Double d5) {
        invoke(num.intValue(), num2.intValue(), d.doubleValue(), d2.doubleValue(), d3.doubleValue(), d4.doubleValue(), d5.doubleValue());
        return Unit.INSTANCE;
    }

    public final void invoke(final int i, final int i2, final double d, final double d2, final double d3, final double d4, final double d5) {
        MirCoreImpl.INSTANCE.getPersonDetectListener$mircore_packRelease().notify(new Function2<PersonListener, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreBinder$addPersonListener$1.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(PersonListener personListener, String str) {
                invoke2(personListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PersonListener it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.onDetection(i, i2, d, d2, d3, d4, d5);
            }
        });
    }
}
