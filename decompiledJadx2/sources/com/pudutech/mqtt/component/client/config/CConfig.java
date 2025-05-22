package com.pudutech.mqtt.component.client.config;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: CConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/config/CConfig;", "", "()V", "ANIMATOR_PROPERTY_TRANSLATION_X", "", "MQTT_CONNECT_TIMEOUT", "", "MQTT_HEARTBEAT_INTERVAL_TIME", "MQTT_RECONNECT_COUNT", "MQTT_RECONNECT_INTERVAL_TIME", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CConfig {
    public static final String ANIMATOR_PROPERTY_TRANSLATION_X = "translationX";
    public static final CConfig INSTANCE = new CConfig();
    public static final int MQTT_CONNECT_TIMEOUT = 15000;
    public static final int MQTT_HEARTBEAT_INTERVAL_TIME = 45000;
    public static final int MQTT_RECONNECT_COUNT = 3;
    public static final int MQTT_RECONNECT_INTERVAL_TIME = 5000;

    private CConfig() {
    }
}
