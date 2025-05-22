package com.pudutech.factory_test.single_test;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* compiled from: SurfaceLEDTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"same", "", "a", "b", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
final class SurfaceLEDTestActivity$setColorQuestion$2 extends Lambda implements Function2<Integer, Integer, Integer> {
    public static final SurfaceLEDTestActivity$setColorQuestion$2 INSTANCE = new SurfaceLEDTestActivity$setColorQuestion$2();

    SurfaceLEDTestActivity$setColorQuestion$2() {
        super(2);
    }

    public final int invoke(int i, int i2) {
        return i == i2 ? 255 : 0;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Integer invoke(Integer num, Integer num2) {
        return Integer.valueOf(invoke(num.intValue(), num2.intValue()));
    }
}
