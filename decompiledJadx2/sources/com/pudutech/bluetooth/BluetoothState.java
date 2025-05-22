package com.pudutech.bluetooth;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: interface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00072\u00020\u0001:\u0006\u0007\b\t\n\u000b\fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0005\r\u000e\u000f\u0010\u0011¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/bluetooth/BluetoothState;", "", "state", "", "(I)V", "getState", "()I", "Companion", "TurnOff", "TurnOn", "TurningOff", "TurningOn", "UnKnow", "Lcom/pudutech/bluetooth/BluetoothState$UnKnow;", "Lcom/pudutech/bluetooth/BluetoothState$TurnOn;", "Lcom/pudutech/bluetooth/BluetoothState$TurningOn;", "Lcom/pudutech/bluetooth/BluetoothState$TurnOff;", "Lcom/pudutech/bluetooth/BluetoothState$TurningOff;", "bluetooth_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class BluetoothState {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int state;

    /* compiled from: interface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bluetooth/BluetoothState$UnKnow;", "Lcom/pudutech/bluetooth/BluetoothState;", "()V", "bluetooth_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class UnKnow extends BluetoothState {
        public static final UnKnow INSTANCE = new UnKnow();

        private UnKnow() {
            super(-1, null);
        }
    }

    private BluetoothState(int i) {
        this.state = i;
    }

    public /* synthetic */ BluetoothState(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    public final int getState() {
        return this.state;
    }

    /* compiled from: interface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bluetooth/BluetoothState$TurnOn;", "Lcom/pudutech/bluetooth/BluetoothState;", "()V", "bluetooth_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class TurnOn extends BluetoothState {
        public static final TurnOn INSTANCE = new TurnOn();

        private TurnOn() {
            super(12, null);
        }
    }

    /* compiled from: interface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bluetooth/BluetoothState$TurningOn;", "Lcom/pudutech/bluetooth/BluetoothState;", "()V", "bluetooth_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class TurningOn extends BluetoothState {
        public static final TurningOn INSTANCE = new TurningOn();

        private TurningOn() {
            super(11, null);
        }
    }

    /* compiled from: interface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bluetooth/BluetoothState$TurnOff;", "Lcom/pudutech/bluetooth/BluetoothState;", "()V", "bluetooth_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class TurnOff extends BluetoothState {
        public static final TurnOff INSTANCE = new TurnOff();

        private TurnOff() {
            super(10, null);
        }
    }

    /* compiled from: interface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bluetooth/BluetoothState$TurningOff;", "Lcom/pudutech/bluetooth/BluetoothState;", "()V", "bluetooth_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class TurningOff extends BluetoothState {
        public static final TurningOff INSTANCE = new TurningOff();

        private TurningOff() {
            super(13, null);
        }
    }

    /* compiled from: interface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bluetooth/BluetoothState$Companion;", "", "()V", "fromState", "Lcom/pudutech/bluetooth/BluetoothState;", "state", "", "bluetooth_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final BluetoothState fromState(int state) {
            return state == TurnOn.INSTANCE.getState() ? TurnOn.INSTANCE : state == TurningOn.INSTANCE.getState() ? TurningOn.INSTANCE : state == TurnOff.INSTANCE.getState() ? TurnOff.INSTANCE : state == TurningOff.INSTANCE.getState() ? TurningOff.INSTANCE : UnKnow.INSTANCE;
        }
    }
}
