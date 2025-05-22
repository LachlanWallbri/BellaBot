package org.eclipse.paho.android.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

/* loaded from: classes9.dex */
public class MqttService extends Service implements MqttTraceHandler {
    static final String TAG = "MqttService";
    private BackgroundDataPreferenceReceiver backgroundDataPreferenceMonitor;
    MessageStore messageStore;
    private MqttServiceBinder mqttServiceBinder;
    private NetworkConnectionIntentReceiver networkConnectionMonitor;
    private String traceCallbackId;
    private boolean traceEnabled = false;
    private volatile boolean backgroundDataEnabled = true;
    private Map<String, MqttConnection> connections = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void callbackToActivity(String str, Status status, Bundle bundle) {
        Intent intent = new Intent(MqttServiceConstants.CALLBACK_TO_ACTIVITY);
        if (str != null) {
            intent.putExtra(MqttServiceConstants.CALLBACK_CLIENT_HANDLE, str);
        }
        intent.putExtra(MqttServiceConstants.CALLBACK_STATUS, status);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public String getClient(String str, String str2, String str3, MqttClientPersistence mqttClientPersistence) {
        String str4 = str + ":" + str2 + ":" + str3;
        if (!this.connections.containsKey(str4)) {
            this.connections.put(str4, new MqttConnection(this, str, str2, mqttClientPersistence, str4));
        }
        return str4;
    }

    public void connect(String str, MqttConnectOptions mqttConnectOptions, String str2, String str3) throws MqttSecurityException, MqttException {
        getConnection(str).connect(mqttConnectOptions, null, str3);
    }

    void reconnect() {
        traceDebug("MqttService", "Reconnect to server, client size=" + this.connections.size());
        for (MqttConnection mqttConnection : this.connections.values()) {
            traceDebug("Reconnect Client:", mqttConnection.getClientId() + '/' + mqttConnection.getServerURI());
            if (isOnline()) {
                mqttConnection.reconnect();
            }
        }
    }

    public void close(String str) {
        getConnection(str).close();
    }

    public void disconnect(String str, String str2, String str3) {
        getConnection(str).disconnect(str2, str3);
        this.connections.remove(str);
        stopSelf();
    }

    public void disconnect(String str, long j, String str2, String str3) {
        getConnection(str).disconnect(j, str2, str3);
        this.connections.remove(str);
        stopSelf();
    }

    public boolean isConnected(String str) {
        return getConnection(str).isConnected();
    }

    public IMqttDeliveryToken publish(String str, String str2, byte[] bArr, int i, boolean z, String str3, String str4) throws MqttPersistenceException, MqttException {
        return getConnection(str).publish(str2, bArr, i, z, str3, str4);
    }

    public IMqttDeliveryToken publish(String str, String str2, MqttMessage mqttMessage, String str3, String str4) throws MqttPersistenceException, MqttException {
        return getConnection(str).publish(str2, mqttMessage, str3, str4);
    }

    public void subscribe(String str, String str2, int i, String str3, String str4) {
        getConnection(str).subscribe(str2, i, str3, str4);
    }

    public void subscribe(String str, String[] strArr, int[] iArr, String str2, String str3) {
        getConnection(str).subscribe(strArr, iArr, str2, str3);
    }

    public void subscribe(String str, String[] strArr, int[] iArr, String str2, String str3, IMqttMessageListener[] iMqttMessageListenerArr) {
        getConnection(str).subscribe(strArr, iArr, str2, str3, iMqttMessageListenerArr);
    }

    public void unsubscribe(String str, String str2, String str3, String str4) {
        getConnection(str).unsubscribe(str2, str3, str4);
    }

    public void unsubscribe(String str, String[] strArr, String str2, String str3) {
        getConnection(str).unsubscribe(strArr, str2, str3);
    }

    public IMqttDeliveryToken[] getPendingDeliveryTokens(String str) {
        return getConnection(str).getPendingDeliveryTokens();
    }

    private MqttConnection getConnection(String str) {
        MqttConnection mqttConnection = this.connections.get(str);
        if (mqttConnection != null) {
            return mqttConnection;
        }
        throw new IllegalArgumentException("Invalid ClientHandle");
    }

    public Status acknowledgeMessageArrival(String str, String str2) {
        if (this.messageStore.discardArrived(str, str2)) {
            return Status.OK;
        }
        return Status.ERROR;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mqttServiceBinder = new MqttServiceBinder(this);
        this.messageStore = new DatabaseMessageStore(this, this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        Iterator<MqttConnection> it = this.connections.values().iterator();
        while (it.hasNext()) {
            it.next().disconnect(null, null);
        }
        if (this.mqttServiceBinder != null) {
            this.mqttServiceBinder = null;
        }
        unregisterBroadcastReceivers();
        MessageStore messageStore = this.messageStore;
        if (messageStore != null) {
            messageStore.close();
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        this.mqttServiceBinder.setActivityToken(intent.getStringExtra(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN));
        return this.mqttServiceBinder;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        registerBroadcastReceivers();
        return 1;
    }

    public void setTraceCallbackId(String str) {
        this.traceCallbackId = str;
    }

    public void setTraceEnabled(boolean z) {
        this.traceEnabled = z;
    }

    public boolean isTraceEnabled() {
        return this.traceEnabled;
    }

    @Override // org.eclipse.paho.android.service.MqttTraceHandler
    public void traceDebug(String str, String str2) {
        traceCallback("debug", str, str2);
    }

    @Override // org.eclipse.paho.android.service.MqttTraceHandler
    public void traceError(String str, String str2) {
        traceCallback("error", str, str2);
    }

    private void traceCallback(String str, String str2, String str3) {
        if (this.traceCallbackId == null || !this.traceEnabled) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.TRACE_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_TRACE_SEVERITY, str);
        bundle.putString(MqttServiceConstants.CALLBACK_TRACE_TAG, str2);
        bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, str3);
        callbackToActivity(this.traceCallbackId, Status.ERROR, bundle);
    }

    @Override // org.eclipse.paho.android.service.MqttTraceHandler
    public void traceException(String str, String str2, Exception exc) {
        if (this.traceCallbackId != null) {
            Bundle bundle = new Bundle();
            bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.TRACE_ACTION);
            bundle.putString(MqttServiceConstants.CALLBACK_TRACE_SEVERITY, MqttServiceConstants.TRACE_EXCEPTION);
            bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, str2);
            bundle.putSerializable(MqttServiceConstants.CALLBACK_EXCEPTION, exc);
            bundle.putString(MqttServiceConstants.CALLBACK_TRACE_TAG, str);
            callbackToActivity(this.traceCallbackId, Status.ERROR, bundle);
        }
    }

    private void registerBroadcastReceivers() {
        if (this.networkConnectionMonitor == null) {
            this.networkConnectionMonitor = new NetworkConnectionIntentReceiver();
            registerReceiver(this.networkConnectionMonitor, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
        if (Build.VERSION.SDK_INT < 14) {
            this.backgroundDataEnabled = ((ConnectivityManager) getSystemService("connectivity")).getBackgroundDataSetting();
            if (this.backgroundDataPreferenceMonitor == null) {
                this.backgroundDataPreferenceMonitor = new BackgroundDataPreferenceReceiver();
                registerReceiver(this.backgroundDataPreferenceMonitor, new IntentFilter("android.net.conn.BACKGROUND_DATA_SETTING_CHANGED"));
            }
        }
    }

    private void unregisterBroadcastReceivers() {
        BackgroundDataPreferenceReceiver backgroundDataPreferenceReceiver;
        NetworkConnectionIntentReceiver networkConnectionIntentReceiver = this.networkConnectionMonitor;
        if (networkConnectionIntentReceiver != null) {
            unregisterReceiver(networkConnectionIntentReceiver);
            this.networkConnectionMonitor = null;
        }
        if (Build.VERSION.SDK_INT >= 14 || (backgroundDataPreferenceReceiver = this.backgroundDataPreferenceMonitor) == null) {
            return;
        }
        unregisterReceiver(backgroundDataPreferenceReceiver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class NetworkConnectionIntentReceiver extends BroadcastReceiver {
        private NetworkConnectionIntentReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MqttService.this.traceDebug("MqttService", "Internal network status receive.");
            PowerManager.WakeLock newWakeLock = ((PowerManager) MqttService.this.getSystemService("power")).newWakeLock(1, "MQTT");
            newWakeLock.acquire();
            MqttService.this.traceDebug("MqttService", "Reconnect for Network recovery.");
            if (!MqttService.this.isOnline()) {
                MqttService.this.notifyClientsOffline();
            } else {
                MqttService.this.traceDebug("MqttService", "Online,reconnect.");
                MqttService.this.reconnect();
            }
            newWakeLock.release();
        }
    }

    public boolean isOnline() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected() && this.backgroundDataEnabled;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyClientsOffline() {
        Iterator<MqttConnection> it = this.connections.values().iterator();
        while (it.hasNext()) {
            it.next().offline();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class BackgroundDataPreferenceReceiver extends BroadcastReceiver {
        private BackgroundDataPreferenceReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) MqttService.this.getSystemService("connectivity");
            MqttService.this.traceDebug("MqttService", "Reconnect since BroadcastReceiver.");
            if (connectivityManager.getBackgroundDataSetting()) {
                if (MqttService.this.backgroundDataEnabled) {
                    return;
                }
                MqttService.this.backgroundDataEnabled = true;
                MqttService.this.reconnect();
                return;
            }
            MqttService.this.backgroundDataEnabled = false;
            MqttService.this.notifyClientsOffline();
        }
    }

    public void setBufferOpts(String str, DisconnectedBufferOptions disconnectedBufferOptions) {
        getConnection(str).setBufferOpts(disconnectedBufferOptions);
    }

    public int getBufferedMessageCount(String str) {
        return getConnection(str).getBufferedMessageCount();
    }

    public MqttMessage getBufferedMessage(String str, int i) {
        return getConnection(str).getBufferedMessage(i);
    }

    public void deleteBufferedMessage(String str, int i) {
        getConnection(str).deleteBufferedMessage(i);
    }
}
