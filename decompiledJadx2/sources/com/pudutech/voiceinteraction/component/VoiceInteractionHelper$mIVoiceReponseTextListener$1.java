package com.pudutech.voiceinteraction.component;

import androidx.core.internal.view.SupportMenu;
import com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener;
import com.pudutech.voiceinteraction.component.media.CVoiceMediaPlayer;
import com.pudutech.voiceinteraction.component.p069ui.VoiceInteractionDialog;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: VoiceInteractionHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\n*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0005H\u0016J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u001a\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0016J\b\u0010\u0011\u001a\u00020\u0003H\u0016J\u0012\u0010\u0012\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0005H\u0016J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0005H\u0016J\u001a\u0010\u0018\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0014\u001a\u00020\u0005H\u0016¨\u0006\u0019"}, m3961d2 = {"com/pudutech/voiceinteraction/component/VoiceInteractionHelper$mIVoiceReponseTextListener$1", "Lcom/pudutech/voiceinteraction/component/listener/IVoiceReponseTextListener;", "dismissCountdown", "", "countdown", "", "finishVoice", "loading", "isShow", "", "palyCompelete", "state", "reWakeup", "requestText", "data", "", "isFinsh", "requestTimeout", "responseText", "showDialog", "random", "volumeChange", "volumeValue", ES6Iterator.VALUE_PROPERTY, "wakeup", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoiceInteractionHelper$mIVoiceReponseTextListener$1 implements IVoiceReponseTextListener {
    final /* synthetic */ VoiceInteractionHelper this$0;

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void palyCompelete(int state) {
    }

    VoiceInteractionHelper$mIVoiceReponseTextListener$1(VoiceInteractionHelper voiceInteractionHelper) {
        this.this$0 = voiceInteractionHelper;
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void requestTimeout() {
        VoiceInteractionDialog access$getVoiceInteractionDialog$p = VoiceInteractionHelper.access$getVoiceInteractionDialog$p(this.this$0);
        if (access$getVoiceInteractionDialog$p != null) {
            access$getVoiceInteractionDialog$p.setRequestTextColor(SupportMenu.CATEGORY_MASK);
        }
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void dismissCountdown(int countdown) {
        VoiceInteractionDialog access$getVoiceInteractionDialog$p = VoiceInteractionHelper.access$getVoiceInteractionDialog$p(this.this$0);
        if (access$getVoiceInteractionDialog$p != null) {
            access$getVoiceInteractionDialog$p.setReWakeupTipsTextCountdown(countdown);
        }
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void requestText(String data, boolean isFinsh) {
        VoiceInteractionDialog access$getVoiceInteractionDialog$p = VoiceInteractionHelper.access$getVoiceInteractionDialog$p(this.this$0);
        if (access$getVoiceInteractionDialog$p != null) {
            access$getVoiceInteractionDialog$p.setRequestText(data);
        }
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void responseText(String data) {
        VoiceInteractionDialog access$getVoiceInteractionDialog$p = VoiceInteractionHelper.access$getVoiceInteractionDialog$p(this.this$0);
        if (access$getVoiceInteractionDialog$p != null) {
            access$getVoiceInteractionDialog$p.setResponseText(data);
        }
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void volumeValue(int value) {
        VoiceInteractionDialog access$getVoiceInteractionDialog$p = VoiceInteractionHelper.access$getVoiceInteractionDialog$p(this.this$0);
        if (access$getVoiceInteractionDialog$p != null) {
            access$getVoiceInteractionDialog$p.setVolume(value);
        }
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void wakeup(String data, int random) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new VoiceInteractionHelper$mIVoiceReponseTextListener$1$wakeup$1(this, random, data, null), 2, null);
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void volumeChange(boolean isShow) {
        VoiceInteractionDialog access$getVoiceInteractionDialog$p = VoiceInteractionHelper.access$getVoiceInteractionDialog$p(this.this$0);
        if (access$getVoiceInteractionDialog$p != null) {
            access$getVoiceInteractionDialog$p.showHideVolumeChangeView(isShow);
        }
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void reWakeup(boolean isShow) {
        VoiceInteractionDialog access$getVoiceInteractionDialog$p = VoiceInteractionHelper.access$getVoiceInteractionDialog$p(this.this$0);
        if (access$getVoiceInteractionDialog$p != null) {
            access$getVoiceInteractionDialog$p.showHideReWakeupTipsTextView(isShow);
        }
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void loading(boolean isShow) {
        VoiceInteractionDialog access$getVoiceInteractionDialog$p = VoiceInteractionHelper.access$getVoiceInteractionDialog$p(this.this$0);
        if (access$getVoiceInteractionDialog$p != null) {
            access$getVoiceInteractionDialog$p.showHideLoadingView(isShow);
        }
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void finishVoice() {
        VoiceInteractionDialog access$getVoiceInteractionDialog$p = VoiceInteractionHelper.access$getVoiceInteractionDialog$p(this.this$0);
        if (access$getVoiceInteractionDialog$p != null) {
            CVoiceMediaPlayer.INSTANCE.getINSTANCE().release();
            if (access$getVoiceInteractionDialog$p.isShowing()) {
                access$getVoiceInteractionDialog$p.dismiss();
                Function1 access$getOnDialogDismissListener$p = VoiceInteractionHelper.access$getOnDialogDismissListener$p(this.this$0);
                if (access$getOnDialogDismissListener$p != null) {
                }
            }
        }
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
    public void showDialog(int random) {
        VoiceInteractionHelper.access$showVoiceAIDialog(this.this$0, random);
    }
}
