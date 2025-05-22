package com.pudutech.bumblebee.robot_ui.manager;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: lora_bean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00072\u00020\u0001:\u0006\u0007\b\t\n\u000b\fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0005\r\u000e\u000f\u0010\u0011¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LoRaConnectState;", "", "code", "", "(I)V", "getCode", "()I", "Companion", "Default", "Disconnect", "Login", "Register", "UnRegister", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaConnectState$Default;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaConnectState$UnRegister;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaConnectState$Register;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaConnectState$Login;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaConnectState$Disconnect;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public abstract class LoRaConnectState {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int code;

    /* compiled from: lora_bean.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LoRaConnectState$Default;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaConnectState;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Default extends LoRaConnectState {
        public static final Default INSTANCE = new Default();

        private Default() {
            super(-1, null);
        }
    }

    private LoRaConnectState(int i) {
        this.code = i;
    }

    public /* synthetic */ LoRaConnectState(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    public final int getCode() {
        return this.code;
    }

    /* compiled from: lora_bean.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LoRaConnectState$UnRegister;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaConnectState;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class UnRegister extends LoRaConnectState {
        public static final UnRegister INSTANCE = new UnRegister();

        private UnRegister() {
            super(0, null);
        }
    }

    /* compiled from: lora_bean.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LoRaConnectState$Register;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaConnectState;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Register extends LoRaConnectState {
        public static final Register INSTANCE = new Register();

        private Register() {
            super(1, null);
        }
    }

    /* compiled from: lora_bean.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LoRaConnectState$Login;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaConnectState;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Login extends LoRaConnectState {
        public static final Login INSTANCE = new Login();

        private Login() {
            super(2, null);
        }
    }

    /* compiled from: lora_bean.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LoRaConnectState$Disconnect;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaConnectState;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Disconnect extends LoRaConnectState {
        public static final Disconnect INSTANCE = new Disconnect();

        private Disconnect() {
            super(3, null);
        }
    }

    /* compiled from: lora_bean.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LoRaConnectState$Companion;", "", "()V", "fromCode", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaConnectState;", "code", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final LoRaConnectState fromCode(int code) {
            if (code == 0) {
                return UnRegister.INSTANCE;
            }
            if (code == 1) {
                return Register.INSTANCE;
            }
            if (code == 2) {
                return Login.INSTANCE;
            }
            if (code == 3) {
                return Disconnect.INSTANCE;
            }
            throw new IllegalArgumentException("LoRaConnectState.fromCode error " + code);
        }
    }
}
