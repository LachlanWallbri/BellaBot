package com.pudutech.robot.module.report.protocol.bean;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;

/* compiled from: SolicitDetailInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u00002\u00020\u0001:\u0001(B\u0005¢\u0006\u0002\u0010\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001e\u0010\f\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001e\u0010\u000f\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001e\u0010\u0012\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR&\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00168\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001e\u0010\u001c\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0006\"\u0004\b\u001e\u0010\bR\u001e\u0010\u001f\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0006\"\u0004\b!\u0010\bR\u001e\u0010\"\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0006\"\u0004\b$\u0010\bR\u001e\u0010%\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0006\"\u0004\b'\u0010\b¨\u0006)"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/bean/SolicitDetailInfo;", "", "()V", "click_coupon", "", "getClick_coupon", "()J", "setClick_coupon", "(J)V", "click_dishes", "getClick_dishes", "setClick_dishes", TypedValues.Transition.S_DURATION, "getDuration", "setDuration", "enter_shop", "getEnter_shop", "setEnter_shop", "f_type", "getF_type", "setF_type", "gongge", "", "Lcom/pudutech/robot/module/report/protocol/bean/SolicitDetailInfo$Gongge;", "getGongge", "()Ljava/util/List;", "setGongge", "(Ljava/util/List;)V", "interactive", "getInteractive", "setInteractive", "passenger_flow", "getPassenger_flow", "setPassenger_flow", "voice_usher", "getVoice_usher", "setVoice_usher", "wake", "getWake", "setWake", "Gongge", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class SolicitDetailInfo {

    @SerializedName("click_coupon")
    private long click_coupon;

    @SerializedName("click_dishes")
    private long click_dishes;

    @SerializedName(TypedValues.Transition.S_DURATION)
    private long duration;

    @SerializedName("enter_shop")
    private long enter_shop;

    @SerializedName("f_type")
    private long f_type;

    @SerializedName("gongge")
    private List<Gongge> gongge;

    @SerializedName("interactive")
    private long interactive;

    @SerializedName("passenger_flow")
    private long passenger_flow;

    @SerializedName("voice_usher")
    private long voice_usher;

    @SerializedName("wake")
    private long wake;

    public final long getPassenger_flow() {
        return this.passenger_flow;
    }

    public final void setPassenger_flow(long j) {
        this.passenger_flow = j;
    }

    public final long getWake() {
        return this.wake;
    }

    public final void setWake(long j) {
        this.wake = j;
    }

    public final long getInteractive() {
        return this.interactive;
    }

    public final void setInteractive(long j) {
        this.interactive = j;
    }

    public final long getClick_dishes() {
        return this.click_dishes;
    }

    public final void setClick_dishes(long j) {
        this.click_dishes = j;
    }

    public final long getClick_coupon() {
        return this.click_coupon;
    }

    public final void setClick_coupon(long j) {
        this.click_coupon = j;
    }

    public final long getEnter_shop() {
        return this.enter_shop;
    }

    public final void setEnter_shop(long j) {
        this.enter_shop = j;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final void setDuration(long j) {
        this.duration = j;
    }

    public final long getVoice_usher() {
        return this.voice_usher;
    }

    public final void setVoice_usher(long j) {
        this.voice_usher = j;
    }

    public final long getF_type() {
        return this.f_type;
    }

    public final void setF_type(long j) {
        this.f_type = j;
    }

    public final List<Gongge> getGongge() {
        return this.gongge;
    }

    public final void setGongge(List<Gongge> list) {
        this.gongge = list;
    }

    /* compiled from: SolicitDetailInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001e\u0010\f\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\b¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/bean/SolicitDetailInfo$Gongge;", "", "()V", "index", "", "getIndex", "()I", "setIndex", "(I)V", "times", "getTimes", "setTimes", "type", "getType", "setType", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class Gongge {

        @SerializedName("index")
        private int index;

        @SerializedName("times")
        private int times;

        @SerializedName("type")
        private int type;

        public final int getType() {
            return this.type;
        }

        public final void setType(int i) {
            this.type = i;
        }

        public final int getIndex() {
            return this.index;
        }

        public final void setIndex(int i) {
            this.index = i;
        }

        public final int getTimes() {
            return this.times;
        }

        public final void setTimes(int i) {
            this.times = i;
        }
    }
}
