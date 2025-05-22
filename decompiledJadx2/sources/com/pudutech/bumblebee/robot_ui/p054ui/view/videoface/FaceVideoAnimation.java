package com.pudutech.bumblebee.robot_ui.p054ui.view.videoface;

import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FaceVideoAnimation.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0019\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0007HÆ\u0003J\t\u0010#\u001a\u00020\tHÆ\u0003J\t\u0010$\u001a\u00020\tHÆ\u0003J\t\u0010%\u001a\u00020\fHÆ\u0003JK\u0010&\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010'\u001a\u00020\t2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\u0006\u0010+\u001a\u00020\u0013J\u0006\u0010,\u001a\u00020\tJ\u0006\u0010-\u001a\u00020\u0013J\u001c\u0010.\u001a\u00020\u00002\u0014\u0010,\u001a\u0010\u0012\u0004\u0012\u00020\t\u0018\u00010\u0010j\u0004\u0018\u0001`\u0011J1\u0010/\u001a\u00020\u00002)\u00100\u001a%\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0016j\u0004\u0018\u0001`\u001aJ\u001c\u00101\u001a\u00020\u00002\u0014\u00102\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0010j\u0004\u0018\u0001`\u0014J\t\u00103\u001a\u000204HÖ\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u000eR\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000eR\u001c\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\t\u0018\u00010\u0010j\u0004\u0018\u0001`\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0010j\u0004\u0018\u0001`\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R1\u0010\u0015\u001a%\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0016j\u0004\u0018\u0001`\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u00065"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoAnimation;", "", "videoDatas", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoData;", "Lkotlin/collections/ArrayList;", "playMode", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoPlayMode;", "isLooper", "", "isTemp", "sceneLayout", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoView$SceneLayout;", "(Ljava/util/ArrayList;Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoPlayMode;ZZLcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoView$SceneLayout;)V", "()Z", "onClickListener", "Lkotlin/Function0;", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/OnFaceVideoAnimationClick;", "onFaceVideoStopListener", "", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/OnFaceVideoStop;", "onFinishListener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "animation", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/OnFaceVideoAnimationFinish;", "getPlayMode", "()Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoPlayMode;", "getSceneLayout", "()Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoView$SceneLayout;", "getVideoDatas", "()Ljava/util/ArrayList;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "onAnimationFinish", "onClick", "onFaceVideoStop", "setOnAnimationClick", "setOnAnimationFinish", "onFinish", "setOnFaceVideoStopListener", "onStop", "toString", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class FaceVideoAnimation {
    private final boolean isLooper;
    private final boolean isTemp;
    private Function0<Boolean> onClickListener;
    private Function0<Unit> onFaceVideoStopListener;
    private Function1<? super FaceVideoAnimation, Unit> onFinishListener;
    private final FaceVideoPlayMode playMode;
    private final FaceVideoView.SceneLayout sceneLayout;
    private final ArrayList<FaceVideoData> videoDatas;

    public static /* synthetic */ FaceVideoAnimation copy$default(FaceVideoAnimation faceVideoAnimation, ArrayList arrayList, FaceVideoPlayMode faceVideoPlayMode, boolean z, boolean z2, FaceVideoView.SceneLayout sceneLayout, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = faceVideoAnimation.videoDatas;
        }
        if ((i & 2) != 0) {
            faceVideoPlayMode = faceVideoAnimation.playMode;
        }
        FaceVideoPlayMode faceVideoPlayMode2 = faceVideoPlayMode;
        if ((i & 4) != 0) {
            z = faceVideoAnimation.isLooper;
        }
        boolean z3 = z;
        if ((i & 8) != 0) {
            z2 = faceVideoAnimation.isTemp;
        }
        boolean z4 = z2;
        if ((i & 16) != 0) {
            sceneLayout = faceVideoAnimation.sceneLayout;
        }
        return faceVideoAnimation.copy(arrayList, faceVideoPlayMode2, z3, z4, sceneLayout);
    }

    public final ArrayList<FaceVideoData> component1() {
        return this.videoDatas;
    }

    /* renamed from: component2, reason: from getter */
    public final FaceVideoPlayMode getPlayMode() {
        return this.playMode;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsLooper() {
        return this.isLooper;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getIsTemp() {
        return this.isTemp;
    }

    /* renamed from: component5, reason: from getter */
    public final FaceVideoView.SceneLayout getSceneLayout() {
        return this.sceneLayout;
    }

    public final FaceVideoAnimation copy(ArrayList<FaceVideoData> videoDatas, FaceVideoPlayMode playMode, boolean isLooper, boolean isTemp, FaceVideoView.SceneLayout sceneLayout) {
        Intrinsics.checkParameterIsNotNull(videoDatas, "videoDatas");
        Intrinsics.checkParameterIsNotNull(playMode, "playMode");
        Intrinsics.checkParameterIsNotNull(sceneLayout, "sceneLayout");
        return new FaceVideoAnimation(videoDatas, playMode, isLooper, isTemp, sceneLayout);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FaceVideoAnimation)) {
            return false;
        }
        FaceVideoAnimation faceVideoAnimation = (FaceVideoAnimation) other;
        return Intrinsics.areEqual(this.videoDatas, faceVideoAnimation.videoDatas) && Intrinsics.areEqual(this.playMode, faceVideoAnimation.playMode) && this.isLooper == faceVideoAnimation.isLooper && this.isTemp == faceVideoAnimation.isTemp && Intrinsics.areEqual(this.sceneLayout, faceVideoAnimation.sceneLayout);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        ArrayList<FaceVideoData> arrayList = this.videoDatas;
        int hashCode = (arrayList != null ? arrayList.hashCode() : 0) * 31;
        FaceVideoPlayMode faceVideoPlayMode = this.playMode;
        int hashCode2 = (hashCode + (faceVideoPlayMode != null ? faceVideoPlayMode.hashCode() : 0)) * 31;
        boolean z = this.isLooper;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode2 + i) * 31;
        boolean z2 = this.isTemp;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        int i4 = (i2 + i3) * 31;
        FaceVideoView.SceneLayout sceneLayout = this.sceneLayout;
        return i4 + (sceneLayout != null ? sceneLayout.hashCode() : 0);
    }

    public String toString() {
        return "FaceVideoAnimation(videoDatas=" + this.videoDatas + ", playMode=" + this.playMode + ", isLooper=" + this.isLooper + ", isTemp=" + this.isTemp + ", sceneLayout=" + this.sceneLayout + ")";
    }

    public FaceVideoAnimation(ArrayList<FaceVideoData> videoDatas, FaceVideoPlayMode playMode, boolean z, boolean z2, FaceVideoView.SceneLayout sceneLayout) {
        Intrinsics.checkParameterIsNotNull(videoDatas, "videoDatas");
        Intrinsics.checkParameterIsNotNull(playMode, "playMode");
        Intrinsics.checkParameterIsNotNull(sceneLayout, "sceneLayout");
        this.videoDatas = videoDatas;
        this.playMode = playMode;
        this.isLooper = z;
        this.isTemp = z2;
        this.sceneLayout = sceneLayout;
    }

    public final ArrayList<FaceVideoData> getVideoDatas() {
        return this.videoDatas;
    }

    public final FaceVideoPlayMode getPlayMode() {
        return this.playMode;
    }

    public final boolean isLooper() {
        return this.isLooper;
    }

    public final boolean isTemp() {
        return this.isTemp;
    }

    public /* synthetic */ FaceVideoAnimation(ArrayList arrayList, FaceVideoPlayMode faceVideoPlayMode, boolean z, boolean z2, FaceVideoView.SceneLayout sceneLayout, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(arrayList, faceVideoPlayMode, z, (i & 8) != 0 ? false : z2, (i & 16) != 0 ? FaceVideoView.SceneLayout.None : sceneLayout);
    }

    public final FaceVideoView.SceneLayout getSceneLayout() {
        return this.sceneLayout;
    }

    public final FaceVideoAnimation setOnAnimationClick(Function0<Boolean> onClick) {
        this.onClickListener = onClick;
        return this;
    }

    public final FaceVideoAnimation setOnAnimationFinish(Function1<? super FaceVideoAnimation, Unit> onFinish) {
        this.onFinishListener = onFinish;
        return this;
    }

    public final FaceVideoAnimation setOnFaceVideoStopListener(Function0<Unit> onStop) {
        this.onFaceVideoStopListener = onStop;
        return this;
    }

    public final boolean onClick() {
        Boolean invoke;
        Function0<Boolean> function0 = this.onClickListener;
        if (function0 == null || (invoke = function0.invoke()) == null) {
            return false;
        }
        return invoke.booleanValue();
    }

    public final void onAnimationFinish() {
        Function1<? super FaceVideoAnimation, Unit> function1 = this.onFinishListener;
        if (function1 != null) {
            function1.invoke(this);
        }
    }

    public final void onFaceVideoStop() {
        Function0<Unit> function0 = this.onFaceVideoStopListener;
        if (function0 != null) {
            function0.invoke();
        }
    }
}
