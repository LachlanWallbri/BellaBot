package com.pudutech.voiceinteraction.component;

import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: VoiceInteractionHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"com/pudutech/voiceinteraction/component/VoiceInteractionHelper$dismissCountdownRunnable$1", "Ljava/lang/Runnable;", "run", "", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoiceInteractionHelper$dismissCountdownRunnable$1 implements Runnable {
    final /* synthetic */ VoiceInteractionHelper this$0;

    VoiceInteractionHelper$dismissCountdownRunnable$1(VoiceInteractionHelper voiceInteractionHelper) {
        this.this$0 = voiceInteractionHelper;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (VoiceInteractionHelper.access$getInterruptDismissCountdown$p(this.this$0)) {
            return;
        }
        if (VoiceInteractionHelper.access$getDismissCountdown$p(this.this$0) < VoiceInteractionHelper.access$getCountDownTimeout$p(this.this$0)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new VoiceInteractionHelper$dismissCountdownRunnable$1$run$1(this, null), 2, null);
            VoiceInteractionHelper.access$getHandler$p(this.this$0).postDelayed(this, 1000L);
        } else {
            VoiceInteractionHelper.access$getHandler$p(this.this$0).obtainMessage(104).sendToTarget();
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new VoiceInteractionHelper$dismissCountdownRunnable$1$run$2(this, null), 2, null);
        }
        VoiceInteractionHelper voiceInteractionHelper = this.this$0;
        VoiceInteractionHelper.access$setDismissCountdown$p(voiceInteractionHelper, VoiceInteractionHelper.access$getDismissCountdown$p(voiceInteractionHelper) + 1000);
    }
}
