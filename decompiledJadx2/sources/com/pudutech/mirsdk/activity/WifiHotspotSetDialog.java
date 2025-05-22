package com.pudutech.mirsdk.activity;

import android.app.Dialog;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.pudutech.mirsdk.WifiHotUtil;
import com.pudutech.mirsdk.function.C4946R;

/* loaded from: classes5.dex */
public class WifiHotspotSetDialog extends Dialog {
    private Button closeWH;
    private Context context;
    private EditText nameWH;
    private Button openWH;
    private EditText pdWH;
    private TextView statusWH;

    public WifiHotspotSetDialog(Context context) {
        super(context);
        init(context);
    }

    public WifiHotspotSetDialog(Context context, int i) {
        super(context, i);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        View inflate = getLayoutInflater().inflate(C4946R.layout.wifi_hotspot_set_dialog, (ViewGroup) null);
        this.statusWH = (TextView) inflate.findViewById(C4946R.id.status_wh);
        this.openWH = (Button) inflate.findViewById(C4946R.id.open_wh);
        this.closeWH = (Button) inflate.findViewById(C4946R.id.close_wh);
        this.nameWH = (EditText) inflate.findViewById(C4946R.id.name_wh);
        this.pdWH = (EditText) inflate.findViewById(C4946R.id.pd_wh);
        this.openWH.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.WifiHotspotSetDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TextUtils.isEmpty(WifiHotspotSetDialog.this.nameWH.getText().toString())) {
                    Toast.makeText(WifiHotspotSetDialog.this.context, "wifi ap ssid is null!", 0).show();
                    return;
                }
                if (TextUtils.isEmpty(WifiHotspotSetDialog.this.pdWH.getText().toString())) {
                    Toast.makeText(WifiHotspotSetDialog.this.context, "wifi ap password is null!", 0).show();
                } else if (new WifiHotUtil(WifiHotspotSetDialog.this.context).startWifiAp(WifiHotspotSetDialog.this.nameWH.getText().toString(), WifiHotspotSetDialog.this.pdWH.getText().toString())) {
                    WifiHotspotSetDialog.this.statusWH.setText(C4946R.string.ap_open);
                } else {
                    WifiHotspotSetDialog.this.statusWH.setText(C4946R.string.ap_close);
                }
            }
        });
        this.closeWH.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.WifiHotspotSetDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new WifiHotUtil(WifiHotspotSetDialog.this.context).closeWifiAp();
                WifiHotspotSetDialog.this.statusWH.setText(C4946R.string.ap_close);
                WifiHotspotSetDialog.this.dismiss();
            }
        });
        WifiHotUtil wifiHotUtil = new WifiHotUtil(this.context);
        WifiConfiguration wifiApConfig = wifiHotUtil.getWifiApConfig();
        if (wifiApConfig != null) {
            if (!TextUtils.isEmpty(wifiApConfig.SSID)) {
                this.nameWH.setText(wifiApConfig.SSID);
            }
            if (!TextUtils.isEmpty(wifiApConfig.preSharedKey)) {
                this.pdWH.setText(wifiApConfig.preSharedKey);
            }
        }
        if (wifiHotUtil.isWifiApEnabled()) {
            this.statusWH.setText(C4946R.string.ap_open);
        } else {
            this.statusWH.setText(C4946R.string.ap_close);
        }
        setContentView(inflate);
    }
}
