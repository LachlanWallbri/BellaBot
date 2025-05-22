package com.pudutech.bumblebee.robot_ui.manager;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: lora_bean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00072\u00020\u0001:\u0007\u0007\b\t\n\u000b\f\rB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0006\u000e\u000f\u0010\u0011\u0012\u0013¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState;", "", "code", "", "(I)V", "getCode", "()I", "Companion", "Erasing", "Idle", "Programming", "Starting", "UpdateFail", "UpdateSuccess", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState$Idle;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState$Starting;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState$Erasing;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState$Programming;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState$UpdateSuccess;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState$UpdateFail;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public abstract class LoRaUpdateState {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int code;

    /* compiled from: lora_bean.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState$Idle;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Idle extends LoRaUpdateState {
        public static final Idle INSTANCE = new Idle();

        private Idle() {
            super(1, null);
        }
    }

    private LoRaUpdateState(int i) {
        this.code = i;
    }

    public /* synthetic */ LoRaUpdateState(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    public final int getCode() {
        return this.code;
    }

    /* compiled from: lora_bean.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState$Starting;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Starting extends LoRaUpdateState {
        public static final Starting INSTANCE = new Starting();

        private Starting() {
            super(2, null);
        }
    }

    /* compiled from: lora_bean.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState$Erasing;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Erasing extends LoRaUpdateState {
        public static final Erasing INSTANCE = new Erasing();

        private Erasing() {
            super(3, null);
        }
    }

    /* compiled from: lora_bean.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState$Programming;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Programming extends LoRaUpdateState {
        public static final Programming INSTANCE = new Programming();

        private Programming() {
            super(4, null);
        }
    }

    /* compiled from: lora_bean.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState$UpdateSuccess;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class UpdateSuccess extends LoRaUpdateState {
        public static final UpdateSuccess INSTANCE = new UpdateSuccess();

        private UpdateSuccess() {
            super(5, null);
        }
    }

    /* compiled from: lora_bean.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState$UpdateFail;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class UpdateFail extends LoRaUpdateState {
        public static final UpdateFail INSTANCE = new UpdateFail();

        private UpdateFail() {
            super(6, null);
        }
    }

    /* compiled from: lora_bean.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState$Companion;", "", "()V", "fromCode", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState;", "code", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final LoRaUpdateState fromCode(int code) {
            switch (code) {
                case 1:
                    return Idle.INSTANCE;
                case 2:
                    return Starting.INSTANCE;
                case 3:
                    return Erasing.INSTANCE;
                case 4:
                    return Programming.INSTANCE;
                case 5:
                    return UpdateSuccess.INSTANCE;
                case 6:
                    return UpdateFail.INSTANCE;
                default:
                    throw new IllegalArgumentException("LoRaUpdateState.fromCode error " + code);
            }
        }
    }
}
