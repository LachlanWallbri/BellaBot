package com.pudutech.pd_network;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: NetworkTypeComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\r\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\r\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"¨\u0006#"}, m3961d2 = {"Lcom/pudutech/pd_network/PdNetworkType;", "", "type", "", "(I)V", "getType", "()I", "isConnect", "", "Bluetooth", "Cellular", "CellularUnCon", "Connect", "Ethernet", "Lowpan", "None", "UnKnow", "Usb", "Vpn", "Wifi", "WifiAware", "WifiUnCon", "Lcom/pudutech/pd_network/PdNetworkType$UnKnow;", "Lcom/pudutech/pd_network/PdNetworkType$Wifi;", "Lcom/pudutech/pd_network/PdNetworkType$Cellular;", "Lcom/pudutech/pd_network/PdNetworkType$Ethernet;", "Lcom/pudutech/pd_network/PdNetworkType$Bluetooth;", "Lcom/pudutech/pd_network/PdNetworkType$Lowpan;", "Lcom/pudutech/pd_network/PdNetworkType$Usb;", "Lcom/pudutech/pd_network/PdNetworkType$Vpn;", "Lcom/pudutech/pd_network/PdNetworkType$WifiAware;", "Lcom/pudutech/pd_network/PdNetworkType$Connect;", "Lcom/pudutech/pd_network/PdNetworkType$None;", "Lcom/pudutech/pd_network/PdNetworkType$WifiUnCon;", "Lcom/pudutech/pd_network/PdNetworkType$CellularUnCon;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public abstract class PdNetworkType {
    private final int type;

    private PdNetworkType(int i) {
        this.type = i;
    }

    public /* synthetic */ PdNetworkType(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    public final int getType() {
        return this.type;
    }

    /* compiled from: NetworkTypeComponent.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/PdNetworkType$UnKnow;", "Lcom/pudutech/pd_network/PdNetworkType;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class UnKnow extends PdNetworkType {
        public static final UnKnow INSTANCE = new UnKnow();

        private UnKnow() {
            super(0, null);
        }
    }

    /* compiled from: NetworkTypeComponent.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/PdNetworkType$Wifi;", "Lcom/pudutech/pd_network/PdNetworkType;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Wifi extends PdNetworkType {
        public static final Wifi INSTANCE = new Wifi();

        private Wifi() {
            super(1, null);
        }
    }

    /* compiled from: NetworkTypeComponent.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/PdNetworkType$Cellular;", "Lcom/pudutech/pd_network/PdNetworkType;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Cellular extends PdNetworkType {
        public static final Cellular INSTANCE = new Cellular();

        private Cellular() {
            super(6, null);
        }
    }

    /* compiled from: NetworkTypeComponent.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/PdNetworkType$Ethernet;", "Lcom/pudutech/pd_network/PdNetworkType;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Ethernet extends PdNetworkType {
        public static final Ethernet INSTANCE = new Ethernet();

        private Ethernet() {
            super(7, null);
        }
    }

    /* compiled from: NetworkTypeComponent.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/PdNetworkType$Bluetooth;", "Lcom/pudutech/pd_network/PdNetworkType;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Bluetooth extends PdNetworkType {
        public static final Bluetooth INSTANCE = new Bluetooth();

        private Bluetooth() {
            super(9, null);
        }
    }

    /* compiled from: NetworkTypeComponent.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/PdNetworkType$Lowpan;", "Lcom/pudutech/pd_network/PdNetworkType;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Lowpan extends PdNetworkType {
        public static final Lowpan INSTANCE = new Lowpan();

        private Lowpan() {
            super(10, null);
        }
    }

    /* compiled from: NetworkTypeComponent.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/PdNetworkType$Usb;", "Lcom/pudutech/pd_network/PdNetworkType;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Usb extends PdNetworkType {
        public static final Usb INSTANCE = new Usb();

        private Usb() {
            super(11, null);
        }
    }

    /* compiled from: NetworkTypeComponent.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/PdNetworkType$Vpn;", "Lcom/pudutech/pd_network/PdNetworkType;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Vpn extends PdNetworkType {
        public static final Vpn INSTANCE = new Vpn();

        private Vpn() {
            super(12, null);
        }
    }

    /* compiled from: NetworkTypeComponent.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/PdNetworkType$WifiAware;", "Lcom/pudutech/pd_network/PdNetworkType;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class WifiAware extends PdNetworkType {
        public static final WifiAware INSTANCE = new WifiAware();

        private WifiAware() {
            super(13, null);
        }
    }

    /* compiled from: NetworkTypeComponent.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/PdNetworkType$Connect;", "Lcom/pudutech/pd_network/PdNetworkType;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Connect extends PdNetworkType {
        public static final Connect INSTANCE = new Connect();

        private Connect() {
            super(14, null);
        }
    }

    /* compiled from: NetworkTypeComponent.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/PdNetworkType$None;", "Lcom/pudutech/pd_network/PdNetworkType;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class None extends PdNetworkType {
        public static final None INSTANCE = new None();

        private None() {
            super(-1, null);
        }
    }

    /* compiled from: NetworkTypeComponent.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/PdNetworkType$WifiUnCon;", "Lcom/pudutech/pd_network/PdNetworkType;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class WifiUnCon extends PdNetworkType {
        public static final WifiUnCon INSTANCE = new WifiUnCon();

        private WifiUnCon() {
            super(-2, null);
        }
    }

    /* compiled from: NetworkTypeComponent.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/PdNetworkType$CellularUnCon;", "Lcom/pudutech/pd_network/PdNetworkType;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class CellularUnCon extends PdNetworkType {
        public static final CellularUnCon INSTANCE = new CellularUnCon();

        private CellularUnCon() {
            super(-3, null);
        }
    }

    public final boolean isConnect() {
        return this.type > 0;
    }
}
