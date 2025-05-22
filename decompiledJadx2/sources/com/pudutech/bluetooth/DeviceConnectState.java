package com.pudutech.bluetooth;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: interface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00072\u00020\u0001:\u0005\u0007\b\t\n\u000bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0004\f\r\u000e\u000f¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bluetooth/DeviceConnectState;", "", "state", "", "(I)V", "getState", "()I", "Companion", "Connected", "Connecting", "Disconnected", "Disconnecting", "Lcom/pudutech/bluetooth/DeviceConnectState$Connected;", "Lcom/pudutech/bluetooth/DeviceConnectState$Connecting;", "Lcom/pudutech/bluetooth/DeviceConnectState$Disconnected;", "Lcom/pudutech/bluetooth/DeviceConnectState$Disconnecting;", "bluetooth_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class DeviceConnectState {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int state;

    /* compiled from: interface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bluetooth/DeviceConnectState$Connected;", "Lcom/pudutech/bluetooth/DeviceConnectState;", "()V", "bluetooth_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Connected extends DeviceConnectState {
        public static final Connected INSTANCE = new Connected();

        private Connected() {
            super(2, null);
        }
    }

    private DeviceConnectState(int i) {
        this.state = i;
    }

    public /* synthetic */ DeviceConnectState(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    public final int getState() {
        return this.state;
    }

    /* compiled from: interface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bluetooth/DeviceConnectState$Connecting;", "Lcom/pudutech/bluetooth/DeviceConnectState;", "()V", "bluetooth_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Connecting extends DeviceConnectState {
        public static final Connecting INSTANCE = new Connecting();

        private Connecting() {
            super(1, null);
        }
    }

    /* compiled from: interface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bluetooth/DeviceConnectState$Disconnected;", "Lcom/pudutech/bluetooth/DeviceConnectState;", "()V", "bluetooth_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Disconnected extends DeviceConnectState {
        public static final Disconnected INSTANCE = new Disconnected();

        private Disconnected() {
            super(0, null);
        }
    }

    /* compiled from: interface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bluetooth/DeviceConnectState$Disconnecting;", "Lcom/pudutech/bluetooth/DeviceConnectState;", "()V", "bluetooth_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Disconnecting extends DeviceConnectState {
        public static final Disconnecting INSTANCE = new Disconnecting();

        private Disconnecting() {
            super(3, null);
        }
    }

    /* compiled from: interface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bluetooth/DeviceConnectState$Companion;", "", "()V", "fromState", "Lcom/pudutech/bluetooth/DeviceConnectState;", "state", "", "bluetooth_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final DeviceConnectState fromState(int state) {
            if (state == Connected.INSTANCE.getState()) {
                return Connected.INSTANCE;
            }
            if (state == Connecting.INSTANCE.getState()) {
                return Connecting.INSTANCE;
            }
            if (state == Disconnected.INSTANCE.getState()) {
                return Disconnected.INSTANCE;
            }
            if (state == Disconnecting.INSTANCE.getState()) {
                return Disconnecting.INSTANCE;
            }
            throw new IllegalArgumentException("un know state " + state);
        }
    }
}
