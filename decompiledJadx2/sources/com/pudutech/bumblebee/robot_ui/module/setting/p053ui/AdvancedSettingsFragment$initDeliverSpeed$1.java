package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import com.pudutech.base.Pdlog;
import com.warkiz.widget.IndicatorSeekBar;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AdvancedSettingsFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "indicatorSeekBar", "Lcom/warkiz/widget/IndicatorSeekBar;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class AdvancedSettingsFragment$initDeliverSpeed$1 extends Lambda implements Function1<IndicatorSeekBar, Unit> {
    final /* synthetic */ int $level_number;
    final /* synthetic */ AdvancedSettingsFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdvancedSettingsFragment$initDeliverSpeed$1(AdvancedSettingsFragment advancedSettingsFragment, int i) {
        super(1);
        this.this$0 = advancedSettingsFragment;
        this.$level_number = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(IndicatorSeekBar indicatorSeekBar) {
        invoke2(indicatorSeekBar);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(IndicatorSeekBar indicatorSeekBar) {
        String str;
        ArrayList arrayList;
        ArrayList arrayList2;
        int i;
        if (indicatorSeekBar != null) {
            int round = Math.round((indicatorSeekBar.getProgressFloat() / 100.0f) * (this.$level_number - 1));
            str = this.this$0.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onStopTrackingTouch:  ");
            sb.append(indicatorSeekBar.getProgressFloat());
            sb.append(' ');
            sb.append(round);
            sb.append(' ');
            arrayList = this.this$0.speedList;
            sb.append((String) arrayList.get(round));
            Pdlog.m3275i(str, sb.toString());
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), null, new C4213x293d5fe1(round, null, this), 2, null);
            arrayList2 = this.this$0.speedList;
            if (arrayList2.size() - 4 <= round) {
                AdvancedSettingsFragment advancedSettingsFragment = this.this$0;
                i = advancedSettingsFragment.TYPE_DELIVER;
                advancedSettingsFragment.showFastSpeedTip(i);
            }
        }
    }
}
