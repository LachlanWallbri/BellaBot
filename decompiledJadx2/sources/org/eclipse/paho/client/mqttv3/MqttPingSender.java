package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.internal.ClientComms;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public interface MqttPingSender {
    void init(ClientComms clientComms);

    void schedule(long j);

    void start();

    void stop();
}
