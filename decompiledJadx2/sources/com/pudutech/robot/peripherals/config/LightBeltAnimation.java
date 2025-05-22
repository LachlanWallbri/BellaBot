package com.pudutech.robot.peripherals.config;

import android.util.Log;
import com.pudutech.base.Pdlog;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LightBeltAnimation.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u0019\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007HÆ\u0003J-\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\r\u0010\u0019\u001a\u00020\u001aH\u0000¢\u0006\u0002\b\u001bJ\b\u0010\u001c\u001a\u0004\u0018\u00010\u0006J\t\u0010\u001d\u001a\u00020\u000eHÖ\u0001J\b\u0010\u001e\u001a\u00020\u001fH\u0016R*\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006 "}, m3961d2 = {"Lcom/pudutech/robot/peripherals/config/LightBeltAnimation;", "", "type", "Lcom/pudutech/robot/peripherals/config/LightBeltAnimationType;", "frames", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/peripherals/config/LightBeltAnimationFrame;", "Lkotlin/collections/ArrayList;", "(Lcom/pudutech/robot/peripherals/config/LightBeltAnimationType;Ljava/util/ArrayList;)V", "getFrames", "()Ljava/util/ArrayList;", "setFrames", "(Ljava/util/ArrayList;)V", "framesIndex", "", "getType", "()Lcom/pudutech/robot/peripherals/config/LightBeltAnimationType;", "setType", "(Lcom/pudutech/robot/peripherals/config/LightBeltAnimationType;)V", "component1", "component2", "copy", "equals", "", "other", "filterNull", "", "filterNull$module_robot_peripherals_release", "getNextFrames", "hashCode", "toString", "", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class LightBeltAnimation {
    private ArrayList<LightBeltAnimationFrame> frames;
    private int framesIndex;
    private LightBeltAnimationType type;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LightBeltAnimation copy$default(LightBeltAnimation lightBeltAnimation, LightBeltAnimationType lightBeltAnimationType, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            lightBeltAnimationType = lightBeltAnimation.type;
        }
        if ((i & 2) != 0) {
            arrayList = lightBeltAnimation.frames;
        }
        return lightBeltAnimation.copy(lightBeltAnimationType, arrayList);
    }

    /* renamed from: component1, reason: from getter */
    public final LightBeltAnimationType getType() {
        return this.type;
    }

    public final ArrayList<LightBeltAnimationFrame> component2() {
        return this.frames;
    }

    public final LightBeltAnimation copy(LightBeltAnimationType type, ArrayList<LightBeltAnimationFrame> frames) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(frames, "frames");
        return new LightBeltAnimation(type, frames);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LightBeltAnimation)) {
            return false;
        }
        LightBeltAnimation lightBeltAnimation = (LightBeltAnimation) other;
        return Intrinsics.areEqual(this.type, lightBeltAnimation.type) && Intrinsics.areEqual(this.frames, lightBeltAnimation.frames);
    }

    public int hashCode() {
        LightBeltAnimationType lightBeltAnimationType = this.type;
        int hashCode = (lightBeltAnimationType != null ? lightBeltAnimationType.hashCode() : 0) * 31;
        ArrayList<LightBeltAnimationFrame> arrayList = this.frames;
        return hashCode + (arrayList != null ? arrayList.hashCode() : 0);
    }

    public LightBeltAnimation(LightBeltAnimationType type, ArrayList<LightBeltAnimationFrame> frames) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(frames, "frames");
        this.type = type;
        this.frames = frames;
        this.framesIndex = -1;
    }

    public /* synthetic */ LightBeltAnimation(LightBeltAnimationType lightBeltAnimationType, ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? LightBeltAnimationType.MODE : lightBeltAnimationType, arrayList);
    }

    public final LightBeltAnimationType getType() {
        return this.type;
    }

    public final void setType(LightBeltAnimationType lightBeltAnimationType) {
        Intrinsics.checkParameterIsNotNull(lightBeltAnimationType, "<set-?>");
        this.type = lightBeltAnimationType;
    }

    public final ArrayList<LightBeltAnimationFrame> getFrames() {
        return this.frames;
    }

    public final void setFrames(ArrayList<LightBeltAnimationFrame> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.frames = arrayList;
    }

    public String toString() {
        return "LightBeltAnimation(type=" + this.type + ", frames=" + this.frames + ')';
    }

    public final void filterNull$module_robot_peripherals_release() {
        try {
            ArrayList<LightBeltAnimationFrame> arrayList = this.frames;
            List filterNotNull = arrayList != null ? CollectionsKt.filterNotNull(arrayList) : null;
            if (filterNotNull == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<com.pudutech.robot.peripherals.config.LightBeltAnimationFrame> /* = java.util.ArrayList<com.pudutech.robot.peripherals.config.LightBeltAnimationFrame> */");
            }
            this.frames = (ArrayList) filterNotNull;
        } catch (Exception e) {
            Pdlog.m3274e("LightBeltAnimation", "filterNull : " + Log.getStackTraceString(e));
        }
    }

    public final LightBeltAnimationFrame getNextFrames() {
        if (this.frames.isEmpty()) {
            return null;
        }
        this.framesIndex++;
        if (this.framesIndex >= this.frames.size()) {
            if (this.type != LightBeltAnimationType.MODE) {
                return null;
            }
            this.framesIndex = 0;
        }
        return this.frames.get(this.framesIndex);
    }
}
