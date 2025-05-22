package com.pudutech.pd_network.bean;

import com.iflytek.aiui.AIUIConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: gateway.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \n2\u00020\u0001:\u000e\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\r\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !¨\u0006\""}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GatewayName;", "", AIUIConstant.KEY_TAG, "", "(Ljava/lang/String;)V", "getTag", "()Ljava/lang/String;", GatewayConstant.AUTH_PLATFORM, GatewayConstant.CLOUD_OAPI, GatewayConstant.CLUSTERS_CFG, "Companion", GatewayConstant.DEVICE_BIZ, GatewayConstant.HK_CENTER_URL, GatewayConstant.OAUTH_PLATFORM, GatewayConstant.OTA_URL, GatewayConstant.PUBLISHER, GatewayConstant.REMOTE_PLATFORM, GatewayConstant.ROBOT_AI, GatewayConstant.ROBOT_BIZ, GatewayConstant.ROBOT_COLL, GatewayConstant.ROBOT_MQTT, "Lcom/pudutech/pd_network/bean/GatewayName$ROBOT_COLL;", "Lcom/pudutech/pd_network/bean/GatewayName$CLUSTERS_CFG;", "Lcom/pudutech/pd_network/bean/GatewayName$CLOUD_OAPI;", "Lcom/pudutech/pd_network/bean/GatewayName$ROBOT_AI;", "Lcom/pudutech/pd_network/bean/GatewayName$ROBOT_MQTT;", "Lcom/pudutech/pd_network/bean/GatewayName$AUTH_PLATFORM;", "Lcom/pudutech/pd_network/bean/GatewayName$OAUTH_PLATFORM;", "Lcom/pudutech/pd_network/bean/GatewayName$REMOTE_PLATFORM;", "Lcom/pudutech/pd_network/bean/GatewayName$HK_CENTER_URL;", "Lcom/pudutech/pd_network/bean/GatewayName$OTA_URL;", "Lcom/pudutech/pd_network/bean/GatewayName$DEVICE_BIZ;", "Lcom/pudutech/pd_network/bean/GatewayName$ROBOT_BIZ;", "Lcom/pudutech/pd_network/bean/GatewayName$PUBLISHER;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public abstract class GatewayName {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String tag;

    /* compiled from: gateway.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GatewayName$ROBOT_COLL;", "Lcom/pudutech/pd_network/bean/GatewayName;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class ROBOT_COLL extends GatewayName {
        public static final ROBOT_COLL INSTANCE = new ROBOT_COLL();

        private ROBOT_COLL() {
            super(GatewayConstant.ROBOT_COLL, null);
        }
    }

    private GatewayName(String str) {
        this.tag = str;
    }

    public /* synthetic */ GatewayName(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    public final String getTag() {
        return this.tag;
    }

    /* compiled from: gateway.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GatewayName$CLUSTERS_CFG;", "Lcom/pudutech/pd_network/bean/GatewayName;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class CLUSTERS_CFG extends GatewayName {
        public static final CLUSTERS_CFG INSTANCE = new CLUSTERS_CFG();

        private CLUSTERS_CFG() {
            super(GatewayConstant.CLUSTERS_CFG, null);
        }
    }

    /* compiled from: gateway.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GatewayName$CLOUD_OAPI;", "Lcom/pudutech/pd_network/bean/GatewayName;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class CLOUD_OAPI extends GatewayName {
        public static final CLOUD_OAPI INSTANCE = new CLOUD_OAPI();

        private CLOUD_OAPI() {
            super(GatewayConstant.CLOUD_OAPI, null);
        }
    }

    /* compiled from: gateway.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GatewayName$ROBOT_AI;", "Lcom/pudutech/pd_network/bean/GatewayName;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class ROBOT_AI extends GatewayName {
        public static final ROBOT_AI INSTANCE = new ROBOT_AI();

        private ROBOT_AI() {
            super(GatewayConstant.ROBOT_AI, null);
        }
    }

    /* compiled from: gateway.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GatewayName$ROBOT_MQTT;", "Lcom/pudutech/pd_network/bean/GatewayName;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class ROBOT_MQTT extends GatewayName {
        public static final ROBOT_MQTT INSTANCE = new ROBOT_MQTT();

        private ROBOT_MQTT() {
            super(GatewayConstant.ROBOT_MQTT, null);
        }
    }

    /* compiled from: gateway.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GatewayName$AUTH_PLATFORM;", "Lcom/pudutech/pd_network/bean/GatewayName;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class AUTH_PLATFORM extends GatewayName {
        public static final AUTH_PLATFORM INSTANCE = new AUTH_PLATFORM();

        private AUTH_PLATFORM() {
            super(GatewayConstant.AUTH_PLATFORM, null);
        }
    }

    /* compiled from: gateway.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GatewayName$OAUTH_PLATFORM;", "Lcom/pudutech/pd_network/bean/GatewayName;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class OAUTH_PLATFORM extends GatewayName {
        public static final OAUTH_PLATFORM INSTANCE = new OAUTH_PLATFORM();

        private OAUTH_PLATFORM() {
            super(GatewayConstant.OAUTH_PLATFORM, null);
        }
    }

    /* compiled from: gateway.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GatewayName$REMOTE_PLATFORM;", "Lcom/pudutech/pd_network/bean/GatewayName;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class REMOTE_PLATFORM extends GatewayName {
        public static final REMOTE_PLATFORM INSTANCE = new REMOTE_PLATFORM();

        private REMOTE_PLATFORM() {
            super(GatewayConstant.REMOTE_PLATFORM, null);
        }
    }

    /* compiled from: gateway.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GatewayName$HK_CENTER_URL;", "Lcom/pudutech/pd_network/bean/GatewayName;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class HK_CENTER_URL extends GatewayName {
        public static final HK_CENTER_URL INSTANCE = new HK_CENTER_URL();

        private HK_CENTER_URL() {
            super(GatewayConstant.HK_CENTER_URL, null);
        }
    }

    /* compiled from: gateway.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GatewayName$OTA_URL;", "Lcom/pudutech/pd_network/bean/GatewayName;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class OTA_URL extends GatewayName {
        public static final OTA_URL INSTANCE = new OTA_URL();

        private OTA_URL() {
            super(GatewayConstant.OTA_URL, null);
        }
    }

    /* compiled from: gateway.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GatewayName$DEVICE_BIZ;", "Lcom/pudutech/pd_network/bean/GatewayName;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class DEVICE_BIZ extends GatewayName {
        public static final DEVICE_BIZ INSTANCE = new DEVICE_BIZ();

        private DEVICE_BIZ() {
            super(GatewayConstant.DEVICE_BIZ, null);
        }
    }

    /* compiled from: gateway.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GatewayName$ROBOT_BIZ;", "Lcom/pudutech/pd_network/bean/GatewayName;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class ROBOT_BIZ extends GatewayName {
        public static final ROBOT_BIZ INSTANCE = new ROBOT_BIZ();

        private ROBOT_BIZ() {
            super(GatewayConstant.ROBOT_BIZ, null);
        }
    }

    /* compiled from: gateway.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GatewayName$PUBLISHER;", "Lcom/pudutech/pd_network/bean/GatewayName;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class PUBLISHER extends GatewayName {
        public static final PUBLISHER INSTANCE = new PUBLISHER();

        private PUBLISHER() {
            super(GatewayConstant.PUBLISHER, null);
        }
    }

    /* compiled from: gateway.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GatewayName$Companion;", "", "()V", "fromTag", "Lcom/pudutech/pd_network/bean/GatewayName;", AIUIConstant.KEY_TAG, "", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public final GatewayName fromTag(String tag) {
            Intrinsics.checkParameterIsNotNull(tag, "tag");
            switch (tag.hashCode()) {
                case -1334850623:
                    if (tag.equals(GatewayConstant.ROBOT_COLL)) {
                        return ROBOT_COLL.INSTANCE;
                    }
                    return null;
                case -1334550535:
                    if (tag.equals(GatewayConstant.ROBOT_MQTT)) {
                        return ROBOT_MQTT.INSTANCE;
                    }
                    return null;
                case -1151439490:
                    if (tag.equals(GatewayConstant.ROBOT_BIZ)) {
                        return ROBOT_BIZ.INSTANCE;
                    }
                    return null;
                case -433861236:
                    if (tag.equals(GatewayConstant.OTA_URL)) {
                        return OTA_URL.INSTANCE;
                    }
                    return null;
                case -117202143:
                    if (tag.equals(GatewayConstant.HK_CENTER_URL)) {
                        return HK_CENTER_URL.INSTANCE;
                    }
                    return null;
                case -60968484:
                    if (tag.equals(GatewayConstant.PUBLISHER)) {
                        return PUBLISHER.INSTANCE;
                    }
                    return null;
                case 70043963:
                    if (tag.equals(GatewayConstant.OAUTH_PLATFORM)) {
                        return OAUTH_PLATFORM.INSTANCE;
                    }
                    return null;
                case 199311157:
                    if (tag.equals(GatewayConstant.CLOUD_OAPI)) {
                        return CLOUD_OAPI.INSTANCE;
                    }
                    return null;
                case 239951421:
                    if (tag.equals(GatewayConstant.ROBOT_AI)) {
                        return ROBOT_AI.INSTANCE;
                    }
                    return null;
                case 485035084:
                    if (tag.equals(GatewayConstant.REMOTE_PLATFORM)) {
                        return REMOTE_PLATFORM.INSTANCE;
                    }
                    return null;
                case 1267541898:
                    if (tag.equals(GatewayConstant.DEVICE_BIZ)) {
                        return DEVICE_BIZ.INSTANCE;
                    }
                    return null;
                case 1318177982:
                    if (tag.equals(GatewayConstant.CLUSTERS_CFG)) {
                        return CLUSTERS_CFG.INSTANCE;
                    }
                    return null;
                case 1354469674:
                    if (tag.equals(GatewayConstant.AUTH_PLATFORM)) {
                        return AUTH_PLATFORM.INSTANCE;
                    }
                    return null;
                default:
                    return null;
            }
        }
    }
}
