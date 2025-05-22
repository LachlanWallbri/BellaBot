package com.pudutech.pdmqtt;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.pudutech.pdmqtt.MqttManager;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* compiled from: PuduMqttManagerWrap.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\t"}, m3961d2 = {"com/pudutech/pdmqtt/PuduMqttManagerWrap$connection$1", "Landroid/content/ServiceConnection;", "onServiceConnected", "", "name", "Landroid/content/ComponentName;", "service", "Landroid/os/IBinder;", "onServiceDisconnected", "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class PuduMqttManagerWrap$connection$1 implements ServiceConnection {
    final /* synthetic */ PuduMqttManagerWrap this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PuduMqttManagerWrap$connection$1(PuduMqttManagerWrap puduMqttManagerWrap) {
        this.this$0 = puduMqttManagerWrap;
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x001c, code lost:
    
        r0 = r3.this$0.mMqttManager;
     */
    @Override // android.content.ServiceConnection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onServiceConnected(ComponentName name, IBinder service) {
        String str;
        Map map;
        MqttManager mqttManager;
        PuduMqttManagerWrap puduMqttManagerWrap = this.this$0;
        str = puduMqttManagerWrap.TAG;
        puduMqttManagerWrap.localLog(str, "onServiceConnected");
        this.this$0.mMqttManager = MqttManager.Stub.asInterface(service);
        MqttLogger localLog = this.this$0.getLocalLog();
        if (localLog != null && mqttManager != null) {
            mqttManager.setLogger(localLog);
        }
        map = this.this$0.fetchClientBuilderMap;
        for (Map.Entry entry : map.entrySet()) {
            if (!((Collection) entry.getValue()).isEmpty()) {
                this.this$0._newClient((MqttClientBuilder) ((List) entry.getValue()).get(0));
            }
        }
        if (service != null) {
            service.linkToDeath(new IBinder.DeathRecipient() { // from class: com.pudutech.pdmqtt.PuduMqttManagerWrap$connection$1$onServiceConnected$3
                @Override // android.os.IBinder.DeathRecipient
                public final void binderDied() {
                    PuduMqttManagerWrap$connection$1.this.this$0.releaseService();
                }
            }, 0);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName name) {
        String str;
        PuduMqttManagerWrap puduMqttManagerWrap = this.this$0;
        str = puduMqttManagerWrap.TAG;
        puduMqttManagerWrap.localLog(str, "onServiceDisconnected");
        this.this$0.releaseService();
    }
}
