package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import com.pudutech.base.Pdlog;
import com.warkiz.widget.IndicatorSeekBar;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SpeedFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "indicatorSeekBar", "Lcom/warkiz/widget/IndicatorSeekBar;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SpeedFragment$deliverSpeed$1 extends Lambda implements Function1<IndicatorSeekBar, Unit> {
    final /* synthetic */ int $levelSize;
    final /* synthetic */ List $speedData;
    final /* synthetic */ SpeedFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SpeedFragment$deliverSpeed$1(SpeedFragment speedFragment, int i, List list) {
        super(1);
        this.this$0 = speedFragment;
        this.$levelSize = i;
        this.$speedData = list;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(IndicatorSeekBar indicatorSeekBar) {
        invoke2(indicatorSeekBar);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(IndicatorSeekBar indicatorSeekBar) {
        String str;
        boolean isTooFast;
        int i;
        if (indicatorSeekBar != null) {
            int rint = (int) Math.rint((indicatorSeekBar.getProgressFloat() / 100.0f) * (this.$levelSize - 1));
            str = this.this$0.TAG;
            Pdlog.m3275i(str, "deliverSpeed onStopTrackingTouch:  " + indicatorSeekBar.getProgressFloat() + ' ' + rint + ' ' + ((String) this.$speedData.get(rint)));
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SpeedFragment$deliverSpeed$1$$special$$inlined$let$lambda$1(rint, null, this), 2, null);
            isTooFast = this.this$0.isTooFast(rint, this.$speedData);
            if (isTooFast) {
                SpeedFragment speedFragment = this.this$0;
                i = speedFragment.TYPE_DELIVER;
                speedFragment.showFastSpeedTip(i);
            }
        }
    }
}
