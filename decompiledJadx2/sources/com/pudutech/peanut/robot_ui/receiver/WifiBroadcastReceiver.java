package com.pudutech.peanut.robot_ui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.os.Parcelable;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.Ktx;
import com.pudutech.peanut.robot_ui.util.WifiUtils;

/* loaded from: classes5.dex */
public class WifiBroadcastReceiver extends BroadcastReceiver {
    private String TAG = "WifiBroadcastReceiver";
    private WifiBroadCastListener wifiBroadCastListener;

    /* loaded from: classes5.dex */
    public interface WifiBroadCastListener {
        void connectSuccess(WifiInfo wifiInfo);

        void pswError();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent != null && "android.net.wifi.STATE_CHANGE".equals(intent.getAction())) {
            Pdlog.m3273d(this.TAG, "android.net.wifi.STATE_CHANGE");
            Parcelable parcelableExtra = intent.getParcelableExtra("networkInfo");
            if (parcelableExtra != null) {
                boolean z = ((NetworkInfo) parcelableExtra).getState() == NetworkInfo.State.CONNECTED;
                if (z) {
                    WifiInfo connectWifiSsid = WifiUtils.getInstance(Ktx.app).getConnectWifiSsid();
                    WifiBroadCastListener wifiBroadCastListener = this.wifiBroadCastListener;
                    if (wifiBroadCastListener != null) {
                        wifiBroadCastListener.connectSuccess(connectWifiSsid);
                    }
                }
                Pdlog.m3273d(this.TAG, "isConnect:" + z);
            }
        }
        if (intent != null && intent.getAction().equals("android.net.wifi.supplicant.STATE_CHANGE") && intent.getIntExtra("supplicantError", 123) == 1) {
            Pdlog.m3273d(this.TAG, "wifi psw fail");
            WifiBroadCastListener wifiBroadCastListener2 = this.wifiBroadCastListener;
            if (wifiBroadCastListener2 != null) {
                wifiBroadCastListener2.pswError();
            }
        }
    }

    public void setWifiBroadCastListener(WifiBroadCastListener wifiBroadCastListener) {
        this.wifiBroadCastListener = wifiBroadCastListener;
    }
}
