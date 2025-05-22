package com.pudutech.bumblebee.robot_ui.p054ui.view.videoface;

import android.os.Environment;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import java.io.File;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SceneAnimationResources.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u00012B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0018J\u0006\u0010\u001a\u001a\u00020\u0018J\u000e\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u0018J\u0006\u0010\u001f\u001a\u00020\u0018J\u0006\u0010 \u001a\u00020\u0018J\u0006\u0010!\u001a\u00020\u0018J\u000e\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\rJ\u0006\u0010#\u001a\u00020\u0018J\u0006\u0010$\u001a\u00020\u0018J\u0006\u0010%\u001a\u00020\u0018J\u0006\u0010&\u001a\u00020\u0018J\u0006\u0010'\u001a\u00020\u0018J\u0006\u0010(\u001a\u00020\u0018J\u0006\u0010)\u001a\u00020\u0018J\u0006\u0010*\u001a\u00020\u0018J\u0006\u0010+\u001a\u00020\u0018J\u0006\u0010,\u001a\u00020\u0018J\u000e\u0010-\u001a\u00020\u00182\u0006\u0010.\u001a\u00020/J\u000e\u00100\u001a\u00020\u00182\u0006\u0010.\u001a\u00020/J\u0006\u00101\u001a\u00020\u0018R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0007\"\u0004\b\n\u0010\u000bR\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0012\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0013\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0007R\u0011\u0010\u0015\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0007¨\u00063"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/SceneAnimationResources;", "", "()V", "ASSETS_PATH", "", "SDCARD_PATH", "getSDCARD_PATH", "()Ljava/lang/String;", "basePath", "getBasePath", "setBasePath", "(Ljava/lang/String;)V", "hasCustomFile", "", "getHasCustomFile", "()Z", "hasCustomFile$delegate", "Lkotlin/Lazy;", "isCustom", "sleepVideoPath", "getSleepVideoPath", "smileVideoPath", "getSmileVideoPath", "getAngry", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoAnimation;", "getAvoid", "getBirthdayOnTheWay", "getGreeterClickFace", "time", "", "getGreeterFace", "getLittleHappy", "getLostLocation", "getOnTheWay", "isOpen", "getOnTheWayAround", "getSayHi", "getSchedule", "getSleep", "getStandby", "getThanks", "getTouchLeftEar", "getTouchRightEar", "getVeryAngry", "getVeryHappy", "sleepMoreTimes", "times", "", "smileMoreTimes", "test", "SleepFace", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SceneAnimationResources {
    private static final String ASSETS_PATH = "animation/video";
    public static final SceneAnimationResources INSTANCE;
    private static final String SDCARD_PATH;
    private static String basePath;

    /* renamed from: hasCustomFile$delegate, reason: from kotlin metadata */
    private static final Lazy hasCustomFile;

    /* compiled from: SceneAnimationResources.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/SceneAnimationResources$SleepFace;", "", "(Ljava/lang/String;I)V", "Smile", "Sleep", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum SleepFace {
        Smile,
        Sleep
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SleepFace.values().length];

        static {
            $EnumSwitchMapping$0[SleepFace.Sleep.ordinal()] = 1;
            $EnumSwitchMapping$0[SleepFace.Smile.ordinal()] = 2;
        }
    }

    private final boolean getHasCustomFile() {
        return ((Boolean) hasCustomFile.getValue()).booleanValue();
    }

    static {
        SceneAnimationResources sceneAnimationResources = new SceneAnimationResources();
        INSTANCE = sceneAnimationResources;
        StringBuilder sb = new StringBuilder();
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        Intrinsics.checkExpressionValueIsNotNull(externalStorageDirectory, "Environment.getExternalStorageDirectory()");
        sb.append(externalStorageDirectory.getPath());
        sb.append("/pudu/decoration/emo");
        SDCARD_PATH = sb.toString();
        hasCustomFile = LazyKt.lazy(new Function0<Boolean>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.videoface.SceneAnimationResources$hasCustomFile$2
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Boolean invoke() {
                return Boolean.valueOf(invoke2());
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2() {
                return new File(SceneAnimationResources.INSTANCE.getSDCARD_PATH()).exists();
            }
        });
        basePath = sceneAnimationResources.isCustom() ? SDCARD_PATH : ASSETS_PATH;
    }

    private SceneAnimationResources() {
    }

    public final String getSDCARD_PATH() {
        return SDCARD_PATH;
    }

    public final String getBasePath() {
        return basePath;
    }

    public final void setBasePath(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        basePath = str;
    }

    public final boolean isCustom() {
        try {
            return getHasCustomFile();
        } catch (Exception unused) {
            return false;
        }
    }

    public final FaceVideoAnimation getLostLocation() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/dizzy.mp4", true, 0, 4, null)), FaceVideoPlayMode.Order, true, false, FaceVideoView.SceneLayout.LostLocation, 8, null);
    }

    public final FaceVideoAnimation getOnTheWay(boolean isOpen) {
        if (isOpen) {
            return getOnTheWay();
        }
        return getOnTheWayAround();
    }

    public final FaceVideoAnimation getOnTheWayAround() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/on_the_way_around.mp4", false, 0, 4, null), new FaceVideoData(basePath + "/on_the_way_around.mp4", false, 0, 4, null)), FaceVideoPlayMode.Random, true, false, FaceVideoView.SceneLayout.turnAround);
    }

    public final FaceVideoAnimation getOnTheWay() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/blink.mp4", false, 0, 4, null), new FaceVideoData(basePath + "/lock_around.mp4", false, 0, 4, null), new FaceVideoData(basePath + "/lock_around.mp4", false, 0, 4, null), new FaceVideoData(basePath + "/lock_around.mp4", false, 0, 4, null)), FaceVideoPlayMode.Random, true, false, null, 24, null);
    }

    public final FaceVideoAnimation getBirthdayOnTheWay() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/happy.mp4", false, 0, 4, null), new FaceVideoData(basePath + "/lovely.mp4", false, 0, 4, null), new FaceVideoData(basePath + "/ear/left_ear.mp4", false, 0, 4, null), new FaceVideoData(basePath + "/ear/right_ear.mp4", false, 0, 4, null)), FaceVideoPlayMode.Random, true, false, null, 24, null);
    }

    public final FaceVideoAnimation getAvoid() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/blink.mp4", false, 0, 4, null), new FaceVideoData(basePath + "/blink.mp4", false, 0, 4, null), new FaceVideoData(basePath + "/blink.mp4", false, 0, 4, null), new FaceVideoData(basePath + "/blink.mp4", false, 0, 4, null)), FaceVideoPlayMode.Order, false, true, null, 16, null);
    }

    public final FaceVideoAnimation getStandby() {
        return getSleep();
    }

    public final FaceVideoAnimation getLittleHappy() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/happy.mp4", false, 0, 4, null)), FaceVideoPlayMode.Order, false, true, null, 16, null);
    }

    public final FaceVideoAnimation getSayHi() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/happy.mp4", true, 0, 4, null)), FaceVideoPlayMode.Order, false, false, null, 16, null);
    }

    public final FaceVideoAnimation getVeryHappy() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/lovely.mp4", false, 0, 4, null)), FaceVideoPlayMode.Order, false, true, null, 16, null);
    }

    public final FaceVideoAnimation getTouchLeftEar() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/ear/left_ear.mp4", false, 0, 4, null)), FaceVideoPlayMode.Order, false, true, null, 16, null);
    }

    public final FaceVideoAnimation getTouchRightEar() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/ear/right_ear.mp4", false, 0, 4, null)), FaceVideoPlayMode.Order, false, true, null, 16, null);
    }

    public final FaceVideoAnimation getAngry() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/angry.mp4", false, 0, 4, null)), FaceVideoPlayMode.Order, false, true, null, 16, null);
    }

    public final FaceVideoAnimation getVeryAngry() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/very_angry.mp4", false, 0, 4, null)), FaceVideoPlayMode.Order, false, true, null, 16, null);
    }

    public final FaceVideoAnimation getThanks() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/thanks.mp4", false, 0, 4, null)), FaceVideoPlayMode.Order, false, true, null, 16, null);
    }

    public final FaceVideoAnimation getSleep() {
        int i = WhenMappings.$EnumSwitchMapping$0[Constans.INSTANCE.getSleepFace().ordinal()];
        if (i != 1) {
            if (i == 2) {
                return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(getSmileVideoPath(), true, 0, 4, null)), FaceVideoPlayMode.Order, true, false, null, 24, null);
            }
            throw new NoWhenBranchMatchedException();
        }
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/sleep/looper.mp4", true, 0, 4, null)), FaceVideoPlayMode.Order, true, false, null, 24, null);
    }

    public final FaceVideoAnimation getSchedule() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/schedule.mp4", true, 0, 4, null)), FaceVideoPlayMode.Order, true, false, FaceVideoView.SceneLayout.Schedule, 8, null);
    }

    public final FaceVideoAnimation getGreeterFace() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/blink.mp4", false, 0, 4, null), new FaceVideoData(basePath + "/lock_around.mp4", false, 0, 4, null), new FaceVideoData(basePath + "/thanks.mp4", false, 0, 4, null)), FaceVideoPlayMode.Order, true, false, null, 24, null);
    }

    public final FaceVideoAnimation getGreeterClickFace(long time) {
        long j = time % 6;
        if (j == 0) {
            return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/angry.mp4", false, 0, 4, null)), FaceVideoPlayMode.Order, false, true, null, 16, null);
        }
        if (j == 1) {
            return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/happy.mp4", false, 0, 4, null)), FaceVideoPlayMode.Order, false, true, null, 16, null);
        }
        if (j == 2) {
            return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/lovely.mp4", false, 0, 4, null)), FaceVideoPlayMode.Order, false, true, null, 16, null);
        }
        if (j == 3) {
            return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/ear/left_ear.mp4", false, 0, 4, null)), FaceVideoPlayMode.Order, false, true, null, 16, null);
        }
        if (j == 4) {
            return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/thanks.mp4", false, 0, 4, null)), FaceVideoPlayMode.Order, false, true, null, 16, null);
        }
        if (j == 5) {
            return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/ear/right_ear.mp4", false, 0, 4, null)), FaceVideoPlayMode.Order, false, true, null, 16, null);
        }
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/happy.mp4", false, 0, 4, null)), FaceVideoPlayMode.Order, false, true, null, 16, null);
    }

    public final String getSleepVideoPath() {
        return basePath + "/sleep/start.mp4";
    }

    public final String getSmileVideoPath() {
        return basePath + "/happy.mp4";
    }

    public final FaceVideoAnimation sleepMoreTimes(int times) {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(getSleepVideoPath(), false, times)), FaceVideoPlayMode.Order, false, false, null, 24, null);
    }

    public final FaceVideoAnimation smileMoreTimes(int times) {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(getSmileVideoPath(), false, times)), FaceVideoPlayMode.Order, false, false, null, 24, null);
    }

    public final FaceVideoAnimation test() {
        return new FaceVideoAnimation(CollectionsKt.arrayListOf(new FaceVideoData(basePath + "/blink.mp4", false, 0, 4, null), new FaceVideoData(basePath + "/lock_around.mp4", false, 0, 4, null), new FaceVideoData(basePath + "/blink.mp4", false, 0, 4, null), new FaceVideoData(basePath + "/thanks.mp4", false, 0, 4, null), new FaceVideoData(basePath + "/happy.mp4", false, 0, 4, null)), FaceVideoPlayMode.Order, true, false, null, 24, null);
    }
}
