package com.pudutech.voiceinteraction.component.p069ui;

import android.animation.Animator;
import android.view.View;
import kotlin.Metadata;

/* compiled from: VoiceInteractionDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\t"}, m3961d2 = {"com/pudutech/voiceinteraction/component/ui/VoiceInteractionDialog$startFadeInAndTranslationAnim$1", "Landroid/animation/Animator$AnimatorListener;", "onAnimationCancel", "", "animation", "Landroid/animation/Animator;", "onAnimationEnd", "onAnimationRepeat", "onAnimationStart", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoiceInteractionDialog$startFadeInAndTranslationAnim$1 implements Animator.AnimatorListener {

    /* renamed from: $v */
    final /* synthetic */ View f7580$v;

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animation) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animation) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animation) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VoiceInteractionDialog$startFadeInAndTranslationAnim$1(View view) {
        this.f7580$v = view;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animation) {
        this.f7580$v.postDelayed(new Runnable() { // from class: com.pudutech.voiceinteraction.component.ui.VoiceInteractionDialog$startFadeInAndTranslationAnim$1$onAnimationStart$1
            @Override // java.lang.Runnable
            public final void run() {
                VoiceInteractionDialog$startFadeInAndTranslationAnim$1.this.f7580$v.setVisibility(0);
            }
        }, 250L);
    }
}
