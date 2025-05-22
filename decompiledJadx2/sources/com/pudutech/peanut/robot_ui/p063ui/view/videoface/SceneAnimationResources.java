package com.pudutech.peanut.robot_ui.p063ui.view.videoface;

import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoView;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: SceneAnimationResources.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0004J\u0006\u0010\u0007\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0004J\u0006\u0010\t\u001a\u00020\u0004J\u0006\u0010\n\u001a\u00020\u0004J\u0006\u0010\u000b\u001a\u00020\u0004J\u0006\u0010\f\u001a\u00020\u0004J\u0006\u0010\r\u001a\u00020\u0004J\u0006\u0010\u000e\u001a\u00020\u0004J\u0006\u0010\u000f\u001a\u00020\u0004J\u0006\u0010\u0010\u001a\u00020\u0004J\u0006\u0010\u0011\u001a\u00020\u0004J\u0006\u0010\u0012\u001a\u00020\u0004J\u0006\u0010\u0013\u001a\u00020\u0004J\u0006\u0010\u0014\u001a\u00020\u0004J\u0006\u0010\u0015\u001a\u00020\u0004J\u0006\u0010\u0016\u001a\u00020\u0004J\u0006\u0010\u0017\u001a\u00020\u0004J\u0006\u0010\u0018\u001a\u00020\u0004J\u0006\u0010\u0019\u001a\u00020\u0004¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/view/videoface/SceneAnimationResources;", "", "()V", "backSolicit", "Lcom/pudutech/peanut/robot_ui/ui/view/videoface/FaceVideoAnimation;", "getAngry", "getCruiseDeliver", "getCruiseSolicit", "getCustomerMode", "getDeliver", "getGreeterFace", "getImpatient", "getLostLocation", "getOnTheWay", "getSayHi", "getSchedule", "getSleep", "getSolicit", "getSolicitStrengthFour", "getSolicitStrengthOne", "getSolicitStrengthSecond", "getSolicitStrengthThird", "getStandby", "getThanks", "getWelCome", "goUsher", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SceneAnimationResources {
    public static final SceneAnimationResources INSTANCE = new SceneAnimationResources();

    private SceneAnimationResources() {
    }

    public final FaceVideoAnimation getCruiseSolicit() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/animation_cruisemode.mp4", true)), FaceVideoPlayMode.Random, true, false, FaceVideoView.SceneLayout.LostLocation, 8, null);
    }

    public final FaceVideoAnimation getCruiseDeliver() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/blink.mp4", false), new FaceVideoData("animation/video/curious.mp4", false), new FaceVideoData("animation/video/happy.mp4", false), new FaceVideoData("animation/video/excited.mp4", false)), FaceVideoPlayMode.Random, true, false, null, 16, null);
    }

    public final FaceVideoAnimation getSolicit() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/video_animation.mp4", true)), FaceVideoPlayMode.Order, true, false, FaceVideoView.SceneLayout.LostLocation, 8, null);
    }

    public final FaceVideoAnimation getSolicitStrengthOne() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/blink.mp4", false), new FaceVideoData("animation/video/curious.mp4", false)), FaceVideoPlayMode.Order, false, false, null, 16, null);
    }

    public final FaceVideoAnimation getSolicitStrengthSecond() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/happy.mp4", false), new FaceVideoData("animation/video/blink.mp4", false), new FaceVideoData("animation/video/curious.mp4", false)), FaceVideoPlayMode.Order, false, false, null, 16, null);
    }

    public final FaceVideoAnimation getSolicitStrengthThird() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/excited.mp4", false), new FaceVideoData("animation/video/blink.mp4", false), new FaceVideoData("animation/video/happy.mp4", false)), FaceVideoPlayMode.Order, false, false, null, 16, null);
    }

    public final FaceVideoAnimation getSolicitStrengthFour() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/blink.mp4", false), new FaceVideoData("animation/video/impatient.mp4", false), new FaceVideoData("animation/video/angry.mp4", false)), FaceVideoPlayMode.Order, false, false, null, 16, null);
    }

    public final FaceVideoAnimation getImpatient() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/impatient.mp4", false)), FaceVideoPlayMode.Order, true, false, null, 16, null);
    }

    public final FaceVideoAnimation getDeliver() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/happy.mp4", false), new FaceVideoData("animation/video/blink.mp4", false), new FaceVideoData("animation/video/curious.mp4", false)), FaceVideoPlayMode.Random, true, false, null, 16, null);
    }

    public final FaceVideoAnimation backSolicit() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/happy.mp4", false), new FaceVideoData("animation/video/curious.mp4", false)), FaceVideoPlayMode.Order, false, false, null, 16, null);
    }

    public final FaceVideoAnimation goUsher() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/blink.mp4", false), new FaceVideoData("animation/video/curious.mp4", false)), FaceVideoPlayMode.Random, true, false, null, 16, null);
    }

    public final FaceVideoAnimation getLostLocation() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/dizzy.mp4", true)), FaceVideoPlayMode.Random, true, false, FaceVideoView.SceneLayout.LostLocation, 8, null);
    }

    public final FaceVideoAnimation getWelCome() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/blink.mp4", true)), FaceVideoPlayMode.Order, true, false, FaceVideoView.SceneLayout.LostLocation, 8, null);
    }

    public final FaceVideoAnimation getOnTheWay() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/blink.mp4", false), new FaceVideoData("animation/video/blink.mp4", false), new FaceVideoData("animation/video/blink.mp4", false), new FaceVideoData("animation/video/blink.mp4", false)), FaceVideoPlayMode.Random, true, false, null, 24, null);
    }

    public final FaceVideoAnimation getStandby() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/blink.mp4", true)), FaceVideoPlayMode.Order, true, false, null, 24, null);
    }

    public final FaceVideoAnimation getSayHi() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/blink.mp4", false), new FaceVideoData("animation/video/happy.mp4", false)), FaceVideoPlayMode.Random, false, true, null, 16, null);
    }

    public final FaceVideoAnimation getAngry() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/angry.mp4", false)), FaceVideoPlayMode.Order, true, false, null, 16, null);
    }

    public final FaceVideoAnimation getThanks() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/happy.mp4", false)), FaceVideoPlayMode.Order, false, true, null, 16, null);
    }

    public final FaceVideoAnimation getSleep() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/sleep.mp4", true)), FaceVideoPlayMode.Order, true, false, null, 24, null);
    }

    public final FaceVideoAnimation getSchedule() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/schedule.mp4", true)), FaceVideoPlayMode.Order, true, false, FaceVideoView.SceneLayout.Schedule, 8, null);
    }

    public final FaceVideoAnimation getGreeterFace() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/blink.mp4", false), new FaceVideoData("animation/video/blink.mp4", false), new FaceVideoData("animation/video/blink.mp4", false)), FaceVideoPlayMode.Random, true, false, null, 24, null);
    }

    public final FaceVideoAnimation getCustomerMode() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData("animation/video/video_customermode_home.mp4", true)), FaceVideoPlayMode.Random, true, false, FaceVideoView.SceneLayout.None, 8, null);
    }
}
