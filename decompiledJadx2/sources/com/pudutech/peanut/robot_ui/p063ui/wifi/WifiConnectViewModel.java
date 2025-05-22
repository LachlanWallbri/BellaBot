package com.pudutech.peanut.robot_ui.p063ui.wifi;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import androidx.lifecycle.MutableLiveData;
import com.pudutech.disinfect.baselib.base.KtxKt;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.peanut.robot_ui.util.WifiUtils;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: WifiConnectViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u0000 -2\u00020\u0001:\u0001-B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u001eJ\u0006\u0010 \u001a\u00020\u001eJ\u000e\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#J\u0016\u0010$\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010%\u001a\u00020#J\u0006\u0010&\u001a\u00020\u001eJ\u0006\u0010'\u001a\u00020\u001eJ\u0006\u0010(\u001a\u00020\u001eJ\u000e\u0010)\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\u000bJ\u0006\u0010+\u001a\u00020\u001eJ\u0006\u0010,\u001a\u00020\u001eR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0007R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0007R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006."}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/wifi/WifiConnectViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "curWifiInfo", "Landroidx/lifecycle/MutableLiveData;", "Landroid/net/wifi/WifiInfo;", "getCurWifiInfo", "()Landroidx/lifecycle/MutableLiveData;", "isOpenWifi", "", "wifiConnectState", "", "getWifiConnectState", "wifiList", "", "Landroid/net/wifi/ScanResult;", "getWifiList", "wifiListJob", "Lkotlinx/coroutines/Job;", "getWifiListJob", "()Lkotlinx/coroutines/Job;", "setWifiListJob", "(Lkotlinx/coroutines/Job;)V", "wifiUtils", "Lcom/pudutech/peanut/robot_ui/util/WifiUtils;", "getWifiUtils", "()Lcom/pudutech/peanut/robot_ui/util/WifiUtils;", "setWifiUtils", "(Lcom/pudutech/peanut/robot_ui/util/WifiUtils;)V", "clearWifiList", "", "closeVm", "closeWifi", "connectWifiNoPws", "ssid", "", "connectWifiPws", "pws", "getConnectWifiSsid", "isWifiEnable", "openWifi", "setWifiConnectState", "state", "startGetWifiList", "stopGetWifiList", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class WifiConnectViewModel extends BaseViewModel {
    public static final int WIFI_CONNECT_DOING = 3;
    public static final int WIFI_CONNECT_FAIL = 0;
    public static final int WIFI_CONNECT_NO = 2;
    public static final int WIFI_CONNECT_SUCCESS = 1;
    private Job wifiListJob;
    private final MutableLiveData<List<ScanResult>> wifiList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isOpenWifi = new MutableLiveData<>();
    private final MutableLiveData<Integer> wifiConnectState = new MutableLiveData<>();
    private final MutableLiveData<WifiInfo> curWifiInfo = new MutableLiveData<>();
    private WifiUtils wifiUtils = WifiUtils.getInstance(KtxKt.getAppContext());

    public WifiConnectViewModel() {
        setWifiConnectState(2);
    }

    public final MutableLiveData<List<ScanResult>> getWifiList() {
        return this.wifiList;
    }

    public final MutableLiveData<Boolean> isOpenWifi() {
        return this.isOpenWifi;
    }

    public final WifiUtils getWifiUtils() {
        return this.wifiUtils;
    }

    public final void setWifiUtils(WifiUtils wifiUtils) {
        this.wifiUtils = wifiUtils;
    }

    public final MutableLiveData<Integer> getWifiConnectState() {
        return this.wifiConnectState;
    }

    public final MutableLiveData<WifiInfo> getCurWifiInfo() {
        return this.curWifiInfo;
    }

    public final void openWifi() {
        WifiUtils wifiUtils = this.wifiUtils;
        if (wifiUtils != null) {
            wifiUtils.openWifi();
        }
    }

    public final void closeWifi() {
        WifiUtils wifiUtils = this.wifiUtils;
        if (wifiUtils != null) {
            wifiUtils.closeWifi();
        }
    }

    public final void clearWifiList() {
        this.wifiList.postValue(null);
    }

    public final void isWifiEnable() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new WifiConnectViewModel$isWifiEnable$1(this, null), 3, null);
    }

    public final void startGetWifiList() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new WifiConnectViewModel$startGetWifiList$1(this, null), 3, null);
        this.wifiListJob = launch$default;
        Job job = this.wifiListJob;
        if (job == null) {
            Intrinsics.throwNpe();
        }
        job.start();
    }

    public final void stopGetWifiList() {
        Job job = this.wifiListJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }

    public final void connectWifiPws(String ssid, String pws) {
        Intrinsics.checkParameterIsNotNull(ssid, "ssid");
        Intrinsics.checkParameterIsNotNull(pws, "pws");
        WifiUtils wifiUtils = this.wifiUtils;
        if (wifiUtils != null) {
            wifiUtils.connectWifiPws(ssid, pws);
        }
    }

    public final void connectWifiNoPws(String ssid) {
        Intrinsics.checkParameterIsNotNull(ssid, "ssid");
        WifiUtils wifiUtils = this.wifiUtils;
        if (wifiUtils != null) {
            wifiUtils.connectWifiNoPws(ssid);
        }
    }

    public final Job getWifiListJob() {
        return this.wifiListJob;
    }

    public final void setWifiListJob(Job job) {
        this.wifiListJob = job;
    }

    public final void getConnectWifiSsid() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new WifiConnectViewModel$getConnectWifiSsid$1(this, null), 3, null);
    }

    public final void closeVm() {
        stopGetWifiList();
    }

    public final void setWifiConnectState(int state) {
        this.wifiConnectState.setValue(Integer.valueOf(state));
    }
}
