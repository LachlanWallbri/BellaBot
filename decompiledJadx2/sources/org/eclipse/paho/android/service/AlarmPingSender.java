package org.eclipse.paho.android.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttPingSender;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class AlarmPingSender implements MqttPingSender {
    private static final String TAG = "AlarmPingSender";
    private BroadcastReceiver alarmReceiver;
    private ClientComms comms;
    private volatile boolean hasStarted = false;
    private PendingIntent pendingIntent;
    private MqttService service;
    private AlarmPingSender that;

    public AlarmPingSender(MqttService mqttService) {
        if (mqttService == null) {
            throw new IllegalArgumentException("Neither service nor client can be null.");
        }
        this.service = mqttService;
        this.that = this;
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void init(ClientComms clientComms) {
        this.comms = clientComms;
        this.alarmReceiver = new AlarmReceiver();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void start() {
        String str = MqttServiceConstants.PING_SENDER + this.comms.getClient().getClientId();
        Log.d(TAG, "Register alarmreceiver to MqttService" + str);
        this.service.registerReceiver(this.alarmReceiver, new IntentFilter(str));
        this.pendingIntent = PendingIntent.getBroadcast(this.service, 0, new Intent(str), 134217728);
        schedule(this.comms.getKeepAlive());
        this.hasStarted = true;
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void stop() {
        Log.d(TAG, "Unregister alarmreceiver to MqttService" + this.comms.getClient().getClientId());
        if (this.hasStarted) {
            if (this.pendingIntent != null) {
                ((AlarmManager) this.service.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(this.pendingIntent);
            }
            this.hasStarted = false;
            try {
                this.service.unregisterReceiver(this.alarmReceiver);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void schedule(long j) {
        long currentTimeMillis = System.currentTimeMillis() + j;
        Log.d(TAG, "Schedule next alarm at " + currentTimeMillis);
        AlarmManager alarmManager = (AlarmManager) this.service.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (Build.VERSION.SDK_INT >= 23) {
            Log.d(TAG, "Alarm scheule using setExactAndAllowWhileIdle, next: " + j);
            alarmManager.setExactAndAllowWhileIdle(0, currentTimeMillis, this.pendingIntent);
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            Log.d(TAG, "Alarm scheule using setExact, delay: " + j);
            alarmManager.setExact(0, currentTimeMillis, this.pendingIntent);
            return;
        }
        alarmManager.set(0, currentTimeMillis, this.pendingIntent);
    }

    /* loaded from: classes9.dex */
    class AlarmReceiver extends BroadcastReceiver {
        private final String wakeLockTag;
        private PowerManager.WakeLock wakelock;

        AlarmReceiver() {
            this.wakeLockTag = MqttServiceConstants.PING_WAKELOCK + AlarmPingSender.this.that.comms.getClient().getClientId();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.d(AlarmPingSender.TAG, "Sending Ping at:" + System.currentTimeMillis());
            this.wakelock = ((PowerManager) AlarmPingSender.this.service.getSystemService("power")).newWakeLock(1, this.wakeLockTag);
            this.wakelock.acquire();
            if (AlarmPingSender.this.comms.checkForActivity(new IMqttActionListener() { // from class: org.eclipse.paho.android.service.AlarmPingSender.AlarmReceiver.1
                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onSuccess(IMqttToken iMqttToken) {
                    Log.d(AlarmPingSender.TAG, "Success. Release lock(" + AlarmReceiver.this.wakeLockTag + "):" + System.currentTimeMillis());
                    AlarmReceiver.this.wakelock.release();
                }

                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onFailure(IMqttToken iMqttToken, Throwable th) {
                    Log.d(AlarmPingSender.TAG, "Failure. Release lock(" + AlarmReceiver.this.wakeLockTag + "):" + System.currentTimeMillis());
                    AlarmReceiver.this.wakelock.release();
                }
            }) == null && this.wakelock.isHeld()) {
                this.wakelock.release();
            }
        }
    }
}
