package com.pudutech.peanut.robot_ui.p063ui.wifi;

import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.KtxKt;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.listener.OnLazyItemClickListener;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.CheckPermissionDialog;
import com.pudutech.peanut.robot_ui.p063ui.adapter.WifiListAdapter;
import com.pudutech.peanut.robot_ui.p063ui.adapter.WifiListItem;
import com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.view.MaxHeightRecyclerView;
import com.pudutech.peanut.robot_ui.p063ui.view.MyStatusBarLayout;
import com.pudutech.peanut.robot_ui.receiver.WifiBroadcastReceiver;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;

/* compiled from: WifiConnectActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010\u0018\u001a\u00020\u0016H\u0002J\b\u0010\u0019\u001a\u00020\u0016H\u0003J\b\u0010\u001a\u001a\u00020\u0016H\u0002J1\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u001dH\u0016¢\u0006\u0002\u0010#J\u0012\u0010$\u001a\u00020\u00162\b\u0010%\u001a\u0004\u0018\u00010&H\u0014J\b\u0010'\u001a\u00020\u0016H\u0014J\b\u0010(\u001a\u00020\u0016H\u0016J\u0010\u0010)\u001a\u00020\u00162\u0006\u0010*\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/wifi/WifiConnectActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/BatteryBaseActivity;", "Lcom/pudutech/peanut/robot_ui/receiver/WifiBroadcastReceiver$WifiBroadCastListener;", "()V", "TAG", "", "checkPermissionDialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/CheckPermissionDialog;", "curWifiInfo", "Landroid/net/wifi/WifiInfo;", "curWifiName", "wifiBroadcastReceiver", "Lcom/pudutech/peanut/robot_ui/receiver/WifiBroadcastReceiver;", "wifiConnectViewModel", "Lcom/pudutech/peanut/robot_ui/ui/wifi/WifiConnectViewModel;", "getWifiConnectViewModel", "()Lcom/pudutech/peanut/robot_ui/ui/wifi/WifiConnectViewModel;", "wifiConnectViewModel$delegate", "Lkotlin/Lazy;", "wifiListAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/WifiListAdapter;", "connectSuccess", "", "wifiInfo", "initData", "initVM", "initView", "notifyBatteryInfo", "state", "", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "", "i", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "pswError", "showPswDialog", "wifiSsid", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class WifiConnectActivity extends BatteryBaseActivity implements WifiBroadcastReceiver.WifiBroadCastListener {
    private HashMap _$_findViewCache;
    private CheckPermissionDialog checkPermissionDialog;
    private WifiInfo curWifiInfo;
    private WifiBroadcastReceiver wifiBroadcastReceiver;
    private WifiListAdapter wifiListAdapter;
    private final String TAG = "WifiConnectActivity";

    /* renamed from: wifiConnectViewModel$delegate, reason: from kotlin metadata */
    private final Lazy wifiConnectViewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(WifiConnectViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.ui.wifi.WifiConnectActivity$$special$$inlined$viewModels$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStore invoke() {
            ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
            Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "viewModelStore");
            return viewModelStore;
        }
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.peanut.robot_ui.ui.wifi.WifiConnectActivity$$special$$inlined$viewModels$1
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelProvider.Factory invoke() {
            ViewModelProvider.Factory defaultViewModelProviderFactory = ComponentActivity.this.getDefaultViewModelProviderFactory();
            Intrinsics.checkExpressionValueIsNotNull(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
            return defaultViewModelProviderFactory;
        }
    });
    private String curWifiName = "";

    /* JADX INFO: Access modifiers changed from: private */
    public final WifiConnectViewModel getWifiConnectViewModel() {
        return (WifiConnectViewModel) this.wifiConnectViewModel.getValue();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public WifiConnectActivity() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5508R.layout.fragment_wifi_connect);
        initData();
        initView();
        initVM();
    }

    private final void initData() {
        this.wifiBroadcastReceiver = new WifiBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.supplicant.STATE_CHANGE");
        registerReceiver(this.wifiBroadcastReceiver, intentFilter);
        WifiBroadcastReceiver wifiBroadcastReceiver = this.wifiBroadcastReceiver;
        if (wifiBroadcastReceiver == null) {
            Intrinsics.throwNpe();
        }
        wifiBroadcastReceiver.setWifiBroadCastListener(this);
    }

    private final void initView() {
        ((TextView) _$_findCachedViewById(C5508R.id.back_tv)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.wifi.WifiConnectActivity$initView$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiConnectActivity.this.finish();
            }
        });
        this.wifiListAdapter = new WifiListAdapter();
        MaxHeightRecyclerView rlv_wifi_list = (MaxHeightRecyclerView) _$_findCachedViewById(C5508R.id.rlv_wifi_list);
        Intrinsics.checkExpressionValueIsNotNull(rlv_wifi_list, "rlv_wifi_list");
        WifiConnectActivity wifiConnectActivity = this;
        rlv_wifi_list.setLayoutManager(new LinearLayoutManager(wifiConnectActivity));
        MaxHeightRecyclerView rlv_wifi_list2 = (MaxHeightRecyclerView) _$_findCachedViewById(C5508R.id.rlv_wifi_list);
        Intrinsics.checkExpressionValueIsNotNull(rlv_wifi_list2, "rlv_wifi_list");
        rlv_wifi_list2.setAdapter(this.wifiListAdapter);
        getWifiConnectViewModel().startGetWifiList();
        getWifiConnectViewModel().isWifiEnable();
        WifiListAdapter wifiListAdapter = this.wifiListAdapter;
        if (wifiListAdapter == null) {
            Intrinsics.throwNpe();
        }
        wifiListAdapter.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.wifi.WifiConnectActivity$initView$2
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyItemClickListener
            public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                String str;
                String str2;
                WifiConnectViewModel wifiConnectViewModel;
                String str3;
                WifiConnectViewModel wifiConnectViewModel2;
                Intrinsics.checkParameterIsNotNull(adapter, "adapter");
                Intrinsics.checkParameterIsNotNull(view, "view");
                List<?> data = adapter.getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "adapter.data");
                int i = 0;
                for (Object obj : data) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    if (obj != null) {
                        WifiListItem wifiListItem = (WifiListItem) obj;
                        if (i == position) {
                            String str4 = wifiListItem.getScanResult().capabilities;
                            Intrinsics.checkExpressionValueIsNotNull(str4, "any.scanResult.capabilities");
                            if (StringsKt.contains$default((CharSequence) str4, (CharSequence) "WPA", false, 2, (Object) null)) {
                                WifiConnectActivity wifiConnectActivity2 = WifiConnectActivity.this;
                                String str5 = wifiListItem.getScanResult().SSID;
                                Intrinsics.checkExpressionValueIsNotNull(str5, "any.scanResult.SSID");
                                wifiConnectActivity2.showPswDialog(str5);
                            } else {
                                WifiConnectActivity wifiConnectActivity3 = WifiConnectActivity.this;
                                String str6 = wifiListItem.getScanResult().SSID;
                                Intrinsics.checkExpressionValueIsNotNull(str6, "any.scanResult.SSID");
                                wifiConnectActivity3.curWifiName = str6;
                                str = WifiConnectActivity.this.TAG;
                                StringBuilder sb = new StringBuilder();
                                sb.append("curWifiName ");
                                str2 = WifiConnectActivity.this.curWifiName;
                                sb.append(str2);
                                Pdlog.m3273d(str, sb.toString());
                                wifiConnectViewModel = WifiConnectActivity.this.getWifiConnectViewModel();
                                str3 = WifiConnectActivity.this.curWifiName;
                                wifiConnectViewModel.connectWifiNoPws(str3);
                                wifiConnectViewModel2 = WifiConnectActivity.this.getWifiConnectViewModel();
                                wifiConnectViewModel2.setWifiConnectState(3);
                            }
                        }
                        i = i2;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.adapter.WifiListItem");
                    }
                }
            }
        });
        ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).setWhiteBg(true);
        ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).startChangeListener(wifiConnectActivity);
    }

    private final void initVM() {
        WifiConnectActivity wifiConnectActivity = this;
        getWifiConnectViewModel().isOpenWifi().observe(wifiConnectActivity, new Observer<Boolean>() { // from class: com.pudutech.peanut.robot_ui.ui.wifi.WifiConnectActivity$initVM$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean it) {
                WifiConnectViewModel wifiConnectViewModel;
                WifiConnectViewModel wifiConnectViewModel2;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.booleanValue()) {
                    wifiConnectViewModel2 = WifiConnectActivity.this.getWifiConnectViewModel();
                    wifiConnectViewModel2.getConnectWifiSsid();
                    return;
                }
                RelativeLayout rlt_cur_wifi = (RelativeLayout) WifiConnectActivity.this._$_findCachedViewById(C5508R.id.rlt_cur_wifi);
                Intrinsics.checkExpressionValueIsNotNull(rlt_cur_wifi, "rlt_cur_wifi");
                rlt_cur_wifi.setVisibility(8);
                wifiConnectViewModel = WifiConnectActivity.this.getWifiConnectViewModel();
                wifiConnectViewModel.openWifi();
            }
        });
        final CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        WifiListAdapter wifiListAdapter = this.wifiListAdapter;
        if (wifiListAdapter != null) {
            wifiListAdapter.setNewData(copyOnWriteArrayList);
        }
        getWifiConnectViewModel().getWifiList().observe(wifiConnectActivity, new Observer<List<? extends ScanResult>>() { // from class: com.pudutech.peanut.robot_ui.ui.wifi.WifiConnectActivity$initVM$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(List<? extends ScanResult> list) {
                WifiConnectViewModel wifiConnectViewModel;
                WifiListAdapter wifiListAdapter2;
                String str;
                String str2;
                copyOnWriteArrayList.clear();
                if (list == null || list.isEmpty()) {
                    wifiConnectViewModel = WifiConnectActivity.this.getWifiConnectViewModel();
                    wifiConnectViewModel.isWifiEnable();
                } else {
                    for (ScanResult scanResult : list) {
                        if (scanResult.SSID != null || (!Intrinsics.areEqual(scanResult.SSID, ""))) {
                            str = WifiConnectActivity.this.TAG;
                            Pdlog.m3273d(str, "scanResult.ssid null");
                        }
                        String str3 = scanResult.SSID;
                        str2 = WifiConnectActivity.this.curWifiName;
                        if ((!Intrinsics.areEqual(str3, str2)) && scanResult.SSID != null && (!Intrinsics.areEqual(scanResult.SSID, ""))) {
                            copyOnWriteArrayList.add(new WifiListItem(scanResult));
                        }
                    }
                }
                wifiListAdapter2 = WifiConnectActivity.this.wifiListAdapter;
                if (wifiListAdapter2 != null) {
                    wifiListAdapter2.notifyDataSetChanged();
                }
            }
        });
        getWifiConnectViewModel().getWifiConnectState().observe(wifiConnectActivity, new Observer<Integer>() { // from class: com.pudutech.peanut.robot_ui.ui.wifi.WifiConnectActivity$initVM$3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Integer num) {
                String str;
                String str2;
                String str3;
                if (num != null && num.intValue() == 2) {
                    RelativeLayout rlt_cur_wifi = (RelativeLayout) WifiConnectActivity.this._$_findCachedViewById(C5508R.id.rlt_cur_wifi);
                    Intrinsics.checkExpressionValueIsNotNull(rlt_cur_wifi, "rlt_cur_wifi");
                    rlt_cur_wifi.setVisibility(8);
                    return;
                }
                if (num != null && num.intValue() == 1) {
                    RelativeLayout rlt_cur_wifi2 = (RelativeLayout) WifiConnectActivity.this._$_findCachedViewById(C5508R.id.rlt_cur_wifi);
                    Intrinsics.checkExpressionValueIsNotNull(rlt_cur_wifi2, "rlt_cur_wifi");
                    rlt_cur_wifi2.setVisibility(0);
                    TextView tv_cur_wifi_state = (TextView) WifiConnectActivity.this._$_findCachedViewById(C5508R.id.tv_cur_wifi_state);
                    Intrinsics.checkExpressionValueIsNotNull(tv_cur_wifi_state, "tv_cur_wifi_state");
                    tv_cur_wifi_state.setText(WifiConnectActivity.this.getString(C5508R.string.wifi_connect_success));
                    TextView tv_cur_wifi = (TextView) WifiConnectActivity.this._$_findCachedViewById(C5508R.id.tv_cur_wifi);
                    Intrinsics.checkExpressionValueIsNotNull(tv_cur_wifi, "tv_cur_wifi");
                    str3 = WifiConnectActivity.this.curWifiName;
                    tv_cur_wifi.setText(str3);
                    TextView tv_cur_wifi2 = (TextView) WifiConnectActivity.this._$_findCachedViewById(C5508R.id.tv_cur_wifi);
                    Intrinsics.checkExpressionValueIsNotNull(tv_cur_wifi2, "tv_cur_wifi");
                    tv_cur_wifi2.setVisibility(0);
                    TextView tv_cur_wifi_state2 = (TextView) WifiConnectActivity.this._$_findCachedViewById(C5508R.id.tv_cur_wifi_state);
                    Intrinsics.checkExpressionValueIsNotNull(tv_cur_wifi_state2, "tv_cur_wifi_state");
                    tv_cur_wifi_state2.setVisibility(0);
                    ImageView iv_wifi_loading = (ImageView) WifiConnectActivity.this._$_findCachedViewById(C5508R.id.iv_wifi_loading);
                    Intrinsics.checkExpressionValueIsNotNull(iv_wifi_loading, "iv_wifi_loading");
                    iv_wifi_loading.setVisibility(8);
                    return;
                }
                if (num != null && num.intValue() == 0) {
                    RelativeLayout rlt_cur_wifi3 = (RelativeLayout) WifiConnectActivity.this._$_findCachedViewById(C5508R.id.rlt_cur_wifi);
                    Intrinsics.checkExpressionValueIsNotNull(rlt_cur_wifi3, "rlt_cur_wifi");
                    rlt_cur_wifi3.setVisibility(0);
                    TextView tv_cur_wifi3 = (TextView) WifiConnectActivity.this._$_findCachedViewById(C5508R.id.tv_cur_wifi);
                    Intrinsics.checkExpressionValueIsNotNull(tv_cur_wifi3, "tv_cur_wifi");
                    str2 = WifiConnectActivity.this.curWifiName;
                    tv_cur_wifi3.setText(str2);
                    TextView tv_cur_wifi_state3 = (TextView) WifiConnectActivity.this._$_findCachedViewById(C5508R.id.tv_cur_wifi_state);
                    Intrinsics.checkExpressionValueIsNotNull(tv_cur_wifi_state3, "tv_cur_wifi_state");
                    tv_cur_wifi_state3.setText(WifiConnectActivity.this.getString(C5508R.string.wifi_connect_failure));
                    ImageView iv_wifi_loading2 = (ImageView) WifiConnectActivity.this._$_findCachedViewById(C5508R.id.iv_wifi_loading);
                    Intrinsics.checkExpressionValueIsNotNull(iv_wifi_loading2, "iv_wifi_loading");
                    iv_wifi_loading2.setVisibility(8);
                    TextView tv_cur_wifi_state4 = (TextView) WifiConnectActivity.this._$_findCachedViewById(C5508R.id.tv_cur_wifi_state);
                    Intrinsics.checkExpressionValueIsNotNull(tv_cur_wifi_state4, "tv_cur_wifi_state");
                    tv_cur_wifi_state4.setVisibility(0);
                    TextView tv_cur_wifi4 = (TextView) WifiConnectActivity.this._$_findCachedViewById(C5508R.id.tv_cur_wifi);
                    Intrinsics.checkExpressionValueIsNotNull(tv_cur_wifi4, "tv_cur_wifi");
                    tv_cur_wifi4.setVisibility(0);
                    ToastUtils.show(KtxKt.getAppContext(), "wifi连接失败", new Object[0]);
                    return;
                }
                if (num != null && num.intValue() == 3) {
                    RelativeLayout rlt_cur_wifi4 = (RelativeLayout) WifiConnectActivity.this._$_findCachedViewById(C5508R.id.rlt_cur_wifi);
                    Intrinsics.checkExpressionValueIsNotNull(rlt_cur_wifi4, "rlt_cur_wifi");
                    rlt_cur_wifi4.setVisibility(0);
                    TextView textView = (TextView) WifiConnectActivity.this._$_findCachedViewById(C5508R.id.tv_cur_wifi);
                    str = WifiConnectActivity.this.curWifiName;
                    textView.setText(str);
                    TextView tv_cur_wifi_state5 = (TextView) WifiConnectActivity.this._$_findCachedViewById(C5508R.id.tv_cur_wifi_state);
                    Intrinsics.checkExpressionValueIsNotNull(tv_cur_wifi_state5, "tv_cur_wifi_state");
                    tv_cur_wifi_state5.setVisibility(8);
                    ImageView iv_wifi_loading3 = (ImageView) WifiConnectActivity.this._$_findCachedViewById(C5508R.id.iv_wifi_loading);
                    Intrinsics.checkExpressionValueIsNotNull(iv_wifi_loading3, "iv_wifi_loading");
                    iv_wifi_loading3.setVisibility(0);
                }
            }
        });
        getWifiConnectViewModel().getCurWifiInfo().observe(wifiConnectActivity, new Observer<WifiInfo>() { // from class: com.pudutech.peanut.robot_ui.ui.wifi.WifiConnectActivity$initVM$4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(WifiInfo wifiInfo) {
                WifiConnectViewModel wifiConnectViewModel;
                WifiConnectViewModel wifiConnectViewModel2;
                if (wifiInfo == null || wifiInfo.getSupplicantState() != SupplicantState.COMPLETED) {
                    wifiConnectViewModel = WifiConnectActivity.this.getWifiConnectViewModel();
                    wifiConnectViewModel.setWifiConnectState(2);
                    return;
                }
                WifiConnectActivity wifiConnectActivity2 = WifiConnectActivity.this;
                String ssid = wifiInfo.getSSID();
                Intrinsics.checkExpressionValueIsNotNull(ssid, "wifiInfo.ssid");
                wifiConnectActivity2.curWifiName = (String) StringsKt.split$default((CharSequence) ssid, new char[]{'\"'}, false, 0, 6, (Object) null).get(1);
                WifiConnectActivity.this.curWifiInfo = wifiInfo;
                wifiConnectViewModel2 = WifiConnectActivity.this.getWifiConnectViewModel();
                wifiConnectViewModel2.setWifiConnectState(1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPswDialog(final String wifiSsid) {
        if (this.checkPermissionDialog == null) {
            this.checkPermissionDialog = new CheckPermissionDialog(this);
        }
        Pdlog.m3273d(this.TAG, "showCheckPermissionDialog");
        CheckPermissionDialog checkPermissionDialog = this.checkPermissionDialog;
        if (checkPermissionDialog == null) {
            Intrinsics.throwNpe();
        }
        checkPermissionDialog.setOnPswResult(new Function1<String, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.wifi.WifiConnectActivity$showPswDialog$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String psw) {
                String str;
                WifiConnectViewModel wifiConnectViewModel;
                WifiConnectViewModel wifiConnectViewModel2;
                CheckPermissionDialog checkPermissionDialog2;
                Intrinsics.checkParameterIsNotNull(psw, "psw");
                str = WifiConnectActivity.this.TAG;
                Pdlog.m3273d(str, "showCheckPermissionDialog onPswResult " + psw + " wifiSSid: " + wifiSsid);
                if (psw.length() >= 8) {
                    WifiConnectActivity.this.curWifiName = wifiSsid;
                    wifiConnectViewModel = WifiConnectActivity.this.getWifiConnectViewModel();
                    wifiConnectViewModel.connectWifiPws(wifiSsid, psw);
                    wifiConnectViewModel2 = WifiConnectActivity.this.getWifiConnectViewModel();
                    wifiConnectViewModel2.setWifiConnectState(3);
                    checkPermissionDialog2 = WifiConnectActivity.this.checkPermissionDialog;
                    if (checkPermissionDialog2 == null) {
                        Intrinsics.throwNpe();
                    }
                    checkPermissionDialog2.dismiss();
                    return;
                }
                ToastUtils.show(KtxKt.getAppContext(), "wifi不小于8位", new Object[0]);
            }
        });
        CheckPermissionDialog checkPermissionDialog2 = this.checkPermissionDialog;
        if (checkPermissionDialog2 == null) {
            Intrinsics.throwNpe();
        }
        checkPermissionDialog2.show();
    }

    @Override // com.pudutech.peanut.robot_ui.receiver.WifiBroadcastReceiver.WifiBroadCastListener
    public void pswError() {
        this.curWifiName = "";
        getWifiConnectViewModel().setWifiConnectState(0);
    }

    @Override // com.pudutech.peanut.robot_ui.receiver.WifiBroadcastReceiver.WifiBroadCastListener
    public void connectSuccess(WifiInfo wifiInfo) {
        this.curWifiInfo = wifiInfo;
        if (wifiInfo != null) {
            String ssid = wifiInfo.getSSID();
            Intrinsics.checkExpressionValueIsNotNull(ssid, "wifiInfo.ssid");
            this.curWifiName = (String) StringsKt.split$default((CharSequence) ssid, new char[]{'\"'}, false, 0, 6, (Object) null).get(1);
        }
        getWifiConnectViewModel().setWifiConnectState(1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        getWifiConnectViewModel().closeVm();
        unregisterReceiver(this.wifiBroadcastReceiver);
        ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).stopChangeListener();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void notifyBatteryInfo(int state, ChargeState model, boolean isCharging, Integer i) {
        if (state == 2 && i != null) {
            i.intValue();
            ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).setBattery(i.intValue());
        }
    }
}
