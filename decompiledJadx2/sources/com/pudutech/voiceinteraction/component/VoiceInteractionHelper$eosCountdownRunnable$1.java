package com.pudutech.voiceinteraction.component;

import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: VoiceInteractionHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"com/pudutech/voiceinteraction/component/VoiceInteractionHelper$eosCountdownRunnable$1", "Ljava/lang/Runnable;", "run", "", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoiceInteractionHelper$eosCountdownRunnable$1 implements Runnable {
    final /* synthetic */ VoiceInteractionHelper this$0;

    VoiceInteractionHelper$eosCountdownRunnable$1(VoiceInteractionHelper voiceInteractionHelper) {
        this.this$0 = voiceInteractionHelper;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (!VoiceInteractionHelper.access$getInterruptEosCountdown$p(this.this$0) && VoiceInteractionHelper.access$getEosCountdown$p(this.this$0) <= 5000) {
            if (!VoiceInteractionHelper.access$isResponse$p(this.this$0)) {
                if (VoiceInteractionHelper.access$getEosCountdown$p(this.this$0) == 5000) {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new VoiceInteractionHelper$eosCountdownRunnable$1$run$1(this, null), 2, null);
                } else {
                    VoiceInteractionHelper.access$getHandler$p(this.this$0).postDelayed(this, 1000L);
                }
            } else {
                VoiceInteractionHelper.access$getHandler$p(this.this$0).obtainMessage(102).sendToTarget();
            }
        }
        VoiceInteractionHelper voiceInteractionHelper = this.this$0;
        VoiceInteractionHelper.access$setEosCountdown$p(voiceInteractionHelper, VoiceInteractionHelper.access$getEosCountdown$p(voiceInteractionHelper) + 1000);
    }
}
