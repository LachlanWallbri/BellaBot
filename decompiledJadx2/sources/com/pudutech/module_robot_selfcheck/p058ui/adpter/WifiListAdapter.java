package com.pudutech.module_robot_selfcheck.p058ui.adpter;

import android.net.wifi.ScanResult;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.module_robot_selfcheck.C5365R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: WifiListAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0002H\u0014J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/ui/adpter/WifiListAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/module_robot_selfcheck/ui/adpter/WifiListItem;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "()V", "convert", "", "helper", "item", "wifiLevel", "", "level", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class WifiListAdapter extends BaseQuickAdapter<WifiListItem, BaseViewHolder> {
    private final int wifiLevel(int level) {
        if (level > 0 || level < -50) {
            return (level >= -50 || level < -70) ? 1 : 2;
        }
        return 3;
    }

    public WifiListAdapter() {
        super(C5365R.layout.item_wifi_list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, WifiListItem item) {
        String str;
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        TextView textView = (TextView) helper.getView(C5365R.id.tv_wifi_name);
        ImageView isWifiLock = (ImageView) helper.getView(C5365R.id.iv_wifi_lock);
        ImageView imageView = (ImageView) helper.getView(C5365R.id.iv_wifi_level);
        ScanResult scanResult = item.getScanResult();
        Boolean bool = null;
        textView.setText(scanResult != null ? scanResult.SSID : null);
        ScanResult scanResult2 = item.getScanResult();
        if (scanResult2 != null && (str = scanResult2.capabilities) != null) {
            bool = Boolean.valueOf(StringsKt.contains$default((CharSequence) str, (CharSequence) "WPA", false, 2, (Object) null));
        }
        if (bool == null) {
            Intrinsics.throwNpe();
        }
        if (bool.booleanValue()) {
            Intrinsics.checkExpressionValueIsNotNull(isWifiLock, "isWifiLock");
            isWifiLock.setVisibility(0);
        } else {
            Intrinsics.checkExpressionValueIsNotNull(isWifiLock, "isWifiLock");
            isWifiLock.setVisibility(8);
        }
        int wifiLevel = wifiLevel(item.getScanResult().level);
        if (wifiLevel == 1) {
            imageView.setImageResource(C5365R.drawable.ic_icon_settings_wifi_1);
        } else if (wifiLevel == 2) {
            imageView.setImageResource(C5365R.drawable.ic_icon_settings_wifi_2);
        } else {
            if (wifiLevel != 3) {
                return;
            }
            imageView.setImageResource(C5365R.drawable.ic_icon_settings_wifi_3);
        }
    }
}
