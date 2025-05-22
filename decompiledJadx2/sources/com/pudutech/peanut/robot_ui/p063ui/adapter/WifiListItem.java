package com.pudutech.peanut.robot_ui.p063ui.adapter;

import android.net.wifi.ScanResult;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WifiListAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/WifiListItem;", "", "scanResult", "Landroid/net/wifi/ScanResult;", "(Landroid/net/wifi/ScanResult;)V", "getScanResult", "()Landroid/net/wifi/ScanResult;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class WifiListItem {
    private final ScanResult scanResult;

    public static /* synthetic */ WifiListItem copy$default(WifiListItem wifiListItem, ScanResult scanResult, int i, Object obj) {
        if ((i & 1) != 0) {
            scanResult = wifiListItem.scanResult;
        }
        return wifiListItem.copy(scanResult);
    }

    /* renamed from: component1, reason: from getter */
    public final ScanResult getScanResult() {
        return this.scanResult;
    }

    public final WifiListItem copy(ScanResult scanResult) {
        Intrinsics.checkParameterIsNotNull(scanResult, "scanResult");
        return new WifiListItem(scanResult);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof WifiListItem) && Intrinsics.areEqual(this.scanResult, ((WifiListItem) other).scanResult);
        }
        return true;
    }

    public int hashCode() {
        ScanResult scanResult = this.scanResult;
        if (scanResult != null) {
            return scanResult.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "WifiListItem(scanResult=" + this.scanResult + ")";
    }

    public WifiListItem(ScanResult scanResult) {
        Intrinsics.checkParameterIsNotNull(scanResult, "scanResult");
        this.scanResult = scanResult;
    }

    public final ScanResult getScanResult() {
        return this.scanResult;
    }
}
