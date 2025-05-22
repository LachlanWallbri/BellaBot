package com.pudutech.disinfect.baselib.callback;

import android.view.animation.Animation;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: SimpleAnimationListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/callback/SimpleAnimationListener;", "Landroid/view/animation/Animation$AnimationListener;", "onAnimationEnd", "", "animation", "Landroid/view/animation/Animation;", "onAnimationRepeat", "onAnimationStart", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public interface SimpleAnimationListener extends Animation.AnimationListener {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: SimpleAnimationListener.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static void onAnimationEnd(SimpleAnimationListener simpleAnimationListener, Animation animation) {
        }

        public static void onAnimationRepeat(SimpleAnimationListener simpleAnimationListener, Animation animation) {
        }

        public static void onAnimationStart(SimpleAnimationListener simpleAnimationListener, Animation animation) {
        }
    }

    @Override // android.view.animation.Animation.AnimationListener
    void onAnimationEnd(Animation animation);

    @Override // android.view.animation.Animation.AnimationListener
    void onAnimationRepeat(Animation animation);

    @Override // android.view.animation.Animation.AnimationListener
    void onAnimationStart(Animation animation);
}
