package com.pudutech.module_robot_selfcheck.domain.request;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import androidx.lifecycle.MutableLiveData;
import com.pudutech.disinfect.baselib.base.BaseRequest;
import com.pudutech.disinfect.baselib.base.KtxKt;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.util.WifiUtils;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: WifiConnectRequest.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u0000 /2\u00020\u0001:\u0001/B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020 J\u0006\u0010\"\u001a\u00020 J\u000e\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020%J\u0016\u0010&\u001a\u00020 2\u0006\u0010$\u001a\u00020%2\u0006\u0010'\u001a\u00020%J\u0006\u0010(\u001a\u00020 J\u0006\u0010)\u001a\u00020 J\u0006\u0010*\u001a\u00020 J\u000e\u0010+\u001a\u00020 2\u0006\u0010,\u001a\u00020\rJ\u0006\u0010-\u001a\u00020 J\u0006\u0010.\u001a\u00020 R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\tR\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\tR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u00060"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/domain/request/WifiConnectRequest;", "Lcom/pudutech/disinfect/baselib/base/BaseRequest;", "VM", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "(Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;)V", "curWifiInfo", "Landroidx/lifecycle/MutableLiveData;", "Landroid/net/wifi/WifiInfo;", "getCurWifiInfo", "()Landroidx/lifecycle/MutableLiveData;", "isOpenWifi", "", "wifiConnectState", "", "getWifiConnectState", "wifiList", "", "Landroid/net/wifi/ScanResult;", "getWifiList", "wifiListJob", "Lkotlinx/coroutines/Job;", "getWifiListJob", "()Lkotlinx/coroutines/Job;", "setWifiListJob", "(Lkotlinx/coroutines/Job;)V", "wifiUtils", "Lcom/pudutech/disinfect/baselib/util/WifiUtils;", "getWifiUtils", "()Lcom/pudutech/disinfect/baselib/util/WifiUtils;", "setWifiUtils", "(Lcom/pudutech/disinfect/baselib/util/WifiUtils;)V", "clearWifiList", "", "closeVm", "closeWifi", "connectWifiNoPws", "ssid", "", "connectWifiPws", "pws", "getConnectWifiSsid", "isWifiEnable", "openWifi", "setWifiConnectState", "state", "startGetWifiList", "stopGetWifiList", "Companion", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class WifiConnectRequest extends BaseRequest {
    public static final int WIFI_CONNECT_DOING = 3;
    public static final int WIFI_CONNECT_FAIL = 0;
    public static final int WIFI_CONNECT_NO = 2;
    public static final int WIFI_CONNECT_SUCCESS = 1;
    private final MutableLiveData<WifiInfo> curWifiInfo;
    private final MutableLiveData<Boolean> isOpenWifi;
    private final MutableLiveData<Integer> wifiConnectState;
    private final MutableLiveData<List<ScanResult>> wifiList;
    private Job wifiListJob;
    private WifiUtils wifiUtils;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WifiConnectRequest(BaseViewModel VM) {
        super(VM);
        Intrinsics.checkParameterIsNotNull(VM, "VM");
        this.wifiList = new MutableLiveData<>();
        this.isOpenWifi = new MutableLiveData<>();
        this.wifiConnectState = new MutableLiveData<>();
        this.curWifiInfo = new MutableLiveData<>();
        this.wifiUtils = WifiUtils.getInstance(KtxKt.getAppContext());
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
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new WifiConnectRequest$isWifiEnable$1(this, null), 3, null);
    }

    public final void startGetWifiList() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new WifiConnectRequest$startGetWifiList$1(this, null), 3, null);
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
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new WifiConnectRequest$getConnectWifiSsid$1(this, null), 3, null);
    }

    public final void closeVm() {
        stopGetWifiList();
    }

    public final void setWifiConnectState(int state) {
        this.wifiConnectState.setValue(Integer.valueOf(state));
    }
}
