package com.aliyun.alink.linksdk.tmp.network;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.aliyun.alink.linksdk.tmp.network.NoNetworkException;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.HashSet;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class NetworkManager {
    public static final String TAG = "[Tmp]NetworkManager";
    public static final int UNCONNECTED = -9999;
    private static NetworkManager networkManager;
    private Context applicationContext;
    private boolean isConnected = true;
    private boolean mLastIsConnected = false;
    private HashSet<INetworkListener> listenerSet = new HashSet<>();
    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.aliyun.alink.linksdk.tmp.network.NetworkManager.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            INetworkListener iNetworkListener;
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                NetworkManager networkManager2 = NetworkManager.this;
                networkManager2.mLastIsConnected = networkManager2.isConnected;
                NetworkManager.this.isConnected = NetworkManager.isNetworkAvailable(context);
                ALog.m479d(NetworkManager.TAG, "CONNECTIVITY_ACTION mLastIsConnected:" + NetworkManager.this.mLastIsConnected + " isConnected:" + NetworkManager.this.isConnected);
                if (NetworkManager.this.listenerSet == null || NetworkManager.this.listenerSet.isEmpty()) {
                    return;
                }
                Iterator it = NetworkManager.this.listenerSet.iterator();
                if (!it.hasNext() || (iNetworkListener = (INetworkListener) it.next()) == null) {
                    return;
                }
                iNetworkListener.onNetworkChanged(NetworkManager.this.isConnected, NetworkManager.this.mLastIsConnected);
            }
        }
    };

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public interface INetworkListener {
        void onNetworkChanged(boolean z, boolean z2);
    }

    private NetworkManager() {
    }

    public static NetworkManager instance() {
        if (networkManager == null) {
            networkManager = new NetworkManager();
        }
        return networkManager;
    }

    public void init(Context context) {
        init(context, null);
    }

    public void init(Context context, NoNetworkException.NoNetworkHanler noNetworkHanler) {
        Context applicationContext;
        this.applicationContext = context;
        if ((context instanceof Activity) && (applicationContext = context.getApplicationContext()) != null) {
            this.applicationContext = applicationContext;
        }
        this.applicationContext.registerReceiver(this.mBroadcastReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        NoNetworkException.setNoNetworkHanler(noNetworkHanler);
        boolean isNetworkAvailable = isNetworkAvailable(context);
        this.isConnected = isNetworkAvailable;
        this.mLastIsConnected = isNetworkAvailable;
    }

    public void release() {
        this.applicationContext.unregisterReceiver(this.mBroadcastReceiver);
    }

    public void registerStateChangedListener(INetworkListener iNetworkListener) {
        this.listenerSet.add(iNetworkListener);
        Log.i(TAG, "registerStateChangedListener, size:" + this.listenerSet.size());
    }

    public void unregisterStateChangedListener(INetworkListener iNetworkListener) {
        this.listenerSet.remove(iNetworkListener);
        Log.i(TAG, "unregisterStateChangedListener, size:" + this.listenerSet.size());
    }

    public boolean isNetworkConnected() {
        return this.isConnected;
    }

    public Context getApplicationContext() {
        return this.applicationContext;
    }

    public static int getNetworkType(Context context) {
        Context applicationContext;
        if ((context instanceof Activity) && (applicationContext = context.getApplicationContext()) != null) {
            context = applicationContext;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return (activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.isAvailable()) ? activeNetworkInfo.getType() : UNCONNECTED;
    }

    public static boolean isNetworkAvailable(Context context) {
        return -9999 != getNetworkType(context);
    }
}
