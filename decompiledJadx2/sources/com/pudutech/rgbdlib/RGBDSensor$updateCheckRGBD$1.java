package com.pudutech.rgbdlib;

import android.os.ParcelFileDescriptor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: RGBDSensor.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "it", "Lcom/pudutech/rgbdlib/RGBDDataCatcher;", "<anonymous parameter 1>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class RGBDSensor$updateCheckRGBD$1 extends Lambda implements Function2<RGBDDataCatcher, String, Unit> {
    final /* synthetic */ int $cols;
    final /* synthetic */ int $memorySize;
    final /* synthetic */ ParcelFileDescriptor $parcelFileDescriptor;
    final /* synthetic */ int $rows;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RGBDSensor$updateCheckRGBD$1(ParcelFileDescriptor parcelFileDescriptor, int i, int i2, int i3) {
        super(2);
        this.$parcelFileDescriptor = parcelFileDescriptor;
        this.$rows = i;
        this.$cols = i2;
        this.$memorySize = i3;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(RGBDDataCatcher rGBDDataCatcher, String str) {
        invoke2(rGBDDataCatcher, str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(RGBDDataCatcher it, String str) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
        ParcelFileDescriptor parcelFileDescriptor = this.$parcelFileDescriptor;
        Intrinsics.checkExpressionValueIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
        it.onFrameDescriptor(parcelFileDescriptor, this.$rows, this.$cols, this.$memorySize);
    }
}
