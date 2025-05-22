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
public final class DeliverSettingActivity$initPalletTtsView$11 implements View.OnClickListener {
    final /* synthetic */ DeliverSettingActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeliverSettingActivity$initPalletTtsView$11(DeliverSettingActivity deliverSettingActivity) {
        this.this$0 = deliverSettingActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        PalletTtsScheme palletTtsScheme;
        TtsVoiceHelper.TtsConfigData ttsPalletArrived;
        LottieAnimationView avPalletTtsArrived = (LottieAnimationView) this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsArrived);
        Intrinsics.checkExpressionValueIsNotNull(avPalletTtsArrived, "avPalletTtsArrived");
        if (!(avPalletTtsArrived.getVisibility() == 0)) {
            palletTtsScheme = this.this$0.currentPalletTtsScheme;
            if (palletTtsScheme == null || (ttsPalletArrived = palletTtsScheme.getTtsPalletArrived()) == null) {
                return;
            }
            TtsVoiceHelper.INSTANCE.stopCruiseTts();
            ((LottieAnimationView) this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsOnTheWay)).cancelAnimation();
            LottieAnimationView avPalletTtsOnTheWay = (LottieAnimationView) this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsOnTheWay);
            Intrinsics.checkExpressionValueIsNotNull(avPalletTtsOnTheWay, "avPalletTtsOnTheWay");
            avPalletTtsOnTheWay.setVisibility(8);
            ((ImageView) this.this$0._$_findCachedViewById(C4188R.id.ivPlayPalletTtsOnTheWay)).setImageResource(C4188R.drawable.ic_stop);
            TtsVoiceHelper.INSTANCE.playPcm(ttsPalletArrived, new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$initPalletTtsView$11$$special$$inlined$let$lambda$1
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
                        ((ImageView) DeliverSettingActivity$initPalletTtsView$11.this.this$0._$_findCachedViewById(C4188R.id.ivPlayPalletTtsArrived)).setImageResource(C4188R.drawable.ic_play);
                        LottieAnimationView avPalletTtsArrived2 = (LottieAnimationView) DeliverSettingActivity$initPalletTtsView$11.this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsArrived);
                        Intrinsics.checkExpressionValueIsNotNull(avPalletTtsArrived2, "avPalletTtsArrived");
                        avPalletTtsArrived2.setRepeatMode(1);
                        LottieAnimationView avPalletTtsArrived3 = (LottieAnimationView) DeliverSettingActivity$initPalletTtsView$11.this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsArrived);
                        Intrinsics.checkExpressionValueIsNotNull(avPalletTtsArrived3, "avPalletTtsArrived");
                        avPalletTtsArrived3.setRepeatCount(-1);
                        LottieAnimationView avPalletTtsArrived4 = (LottieAnimationView) DeliverSettingActivity$initPalletTtsView$11.this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsArrived);
                        Intrinsics.checkExpressionValueIsNotNull(avPalletTtsArrived4, "avPalletTtsArrived");
                        avPalletTtsArrived4.setVisibility(0);
                        ((LottieAnimationView) DeliverSettingActivity$initPalletTtsView$11.this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsArrived)).playAnimation();
                        return;
                    }
                    if (it1 == AudioTrackUtils.AudioPlayEvent.COMPLETE) {
                        ((ImageView) DeliverSettingActivity$initPalletTtsView$11.this.this$0._$_findCachedViewById(C4188R.id.ivPlayPalletTtsArrived)).setImageResource(C4188R.drawable.ic_stop);
                        ((LottieAnimationView) DeliverSettingActivity$initPalletTtsView$11.this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsArrived)).cancelAnimation();
                        LottieAnimationView avPalletTtsArrived5 = (LottieAnimationView) DeliverSettingActivity$initPalletTtsView$11.this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsArrived);
                        Intrinsics.checkExpressionValueIsNotNull(avPalletTtsArrived5, "avPalletTtsArrived");
                        avPalletTtsArrived5.setVisibility(8);
                    }
                }
            });
            return;
        }
        TtsVoiceHelper.INSTANCE.stopCruiseTts();
        ((LottieAnimationView) this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsArrived)).cancelAnimation();
        LottieAnimationView avPalletTtsArrived2 = (LottieAnimationView) this.this$0._$_findCachedViewById(C4188R.id.avPalletTtsArrived);
        Intrinsics.checkExpressionValueIsNotNull(avPalletTtsArrived2, "avPalletTtsArrived");
        avPalletTtsArrived2.setVisibility(8);
        ((ImageView) this.this$0._$_findCachedViewById(C4188R.id.ivPlayPalletTtsArrived)).setImageResource(C4188R.drawable.ic_stop);
    }
}
