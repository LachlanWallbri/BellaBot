package com.pudutech.bumblebee.robot_ui.p054ui;

import android.view.View;
import android.widget.ImageView;
import com.airbnb.lottie.LottieAnimationView;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.bean.PalletTtsScheme;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.tts_sdk.utils.AudioTrackUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DeliverSettingActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class DeliverSettingActivity$initPalletTtsView$10 implements View.OnClickListener {
    final /* synthetic */ DeliverSettingActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeliverSettingActivity$initPalletTtsView$10(DeliverSettingActivity deliverSettingActivity) {
        this.this$0 = deliverSettingActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        PalletTtsScheme palletTtsScheme;
        TtsVoiceHelper.TtsConfigData ttsPalletMoving;
        LottieAnimationView avPalletTtsOnTheWay = (LottieAnimationView) this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsOnTheWay);
        Intrinsics.checkExpressionValueIsNotNull(avPalletTtsOnTheWay, "avPalletTtsOnTheWay");
        if (!(avPalletTtsOnTheWay.getVisibility() == 0)) {
            palletTtsScheme = this.this$0.currentPalletTtsScheme;
            if (palletTtsScheme == null || (ttsPalletMoving = palletTtsScheme.getTtsPalletMoving()) == null) {
                return;
            }
            TtsVoiceHelper.INSTANCE.stopCruiseTts();
            ((LottieAnimationView) this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsArrived)).cancelAnimation();
            LottieAnimationView avPalletTtsArrived = (LottieAnimationView) this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsArrived);
            Intrinsics.checkExpressionValueIsNotNull(avPalletTtsArrived, "avPalletTtsArrived");
            avPalletTtsArrived.setVisibility(8);
            ((ImageView) this.this$0._$_findCachedViewById(C4188R.id.ivPlayPalletTtsArrived)).setImageResource(C4188R.drawable.ic_stop);
            TtsVoiceHelper.INSTANCE.playPcm(ttsPalletMoving, new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$initPalletTtsView$10$$special$$inlined$let$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AudioTrackUtils.AudioPlayEvent audioPlayEvent) {
                    invoke2(audioPlayEvent);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AudioTrackUtils.AudioPlayEvent it1) {
                    Intrinsics.checkParameterIsNotNull(it1, "it1");
                    if (it1 == AudioTrackUtils.AudioPlayEvent.PLAYING) {
                        ((ImageView) DeliverSettingActivity$initPalletTtsView$10.this.this$0._$_findCachedViewById(C4188R.id.ivPlayPalletTtsOnTheWay)).setImageResource(C4188R.drawable.ic_play);
                        LottieAnimationView avPalletTtsOnTheWay2 = (LottieAnimationView) DeliverSettingActivity$initPalletTtsView$10.this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsOnTheWay);
                        Intrinsics.checkExpressionValueIsNotNull(avPalletTtsOnTheWay2, "avPalletTtsOnTheWay");
                        avPalletTtsOnTheWay2.setRepeatMode(1);
                        LottieAnimationView avPalletTtsOnTheWay3 = (LottieAnimationView) DeliverSettingActivity$initPalletTtsView$10.this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsOnTheWay);
                        Intrinsics.checkExpressionValueIsNotNull(avPalletTtsOnTheWay3, "avPalletTtsOnTheWay");
                        avPalletTtsOnTheWay3.setRepeatCount(-1);
                        LottieAnimationView avPalletTtsOnTheWay4 = (LottieAnimationView) DeliverSettingActivity$initPalletTtsView$10.this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsOnTheWay);
                        Intrinsics.checkExpressionValueIsNotNull(avPalletTtsOnTheWay4, "avPalletTtsOnTheWay");
                        avPalletTtsOnTheWay4.setVisibility(0);
                        ((LottieAnimationView) DeliverSettingActivity$initPalletTtsView$10.this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsOnTheWay)).playAnimation();
                        return;
                    }
                    if (it1 == AudioTrackUtils.AudioPlayEvent.COMPLETE) {
                        ((ImageView) DeliverSettingActivity$initPalletTtsView$10.this.this$0._$_findCachedViewById(C4188R.id.ivPlayPalletTtsOnTheWay)).setImageResource(C4188R.drawable.ic_stop);
                        ((LottieAnimationView) DeliverSettingActivity$initPalletTtsView$10.this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsOnTheWay)).cancelAnimation();
                        LottieAnimationView avPalletTtsOnTheWay5 = (LottieAnimationView) DeliverSettingActivity$initPalletTtsView$10.this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsOnTheWay);
                        Intrinsics.checkExpressionValueIsNotNull(avPalletTtsOnTheWay5, "avPalletTtsOnTheWay");
                        avPalletTtsOnTheWay5.setVisibility(8);
                    }
                }
            });
            return;
        }
        TtsVoiceHelper.INSTANCE.stopCruiseTts();
        ((LottieAnimationView) this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsOnTheWay)).cancelAnimation();
        LottieAnimationView avPalletTtsOnTheWay2 = (LottieAnimationView) this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsOnTheWay);
        Intrinsics.checkExpressionValueIsNotNull(avPalletTtsOnTheWay2, "avPalletTtsOnTheWay");
        avPalletTtsOnTheWay2.setVisibility(8);
        ((ImageView) this.this$0._$_findCachedViewById(C4188R.id.ivPlayPalletTtsOnTheWay)).setImageResource(C4188R.drawable.ic_stop);
    }
}
