package com.pudutech.mirsdk.activity;

import android.app.Activity;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.aidl.BluetoothChargeInterface;
import com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.aidl.serialize.BluetoothChargeUpdateErrorType;
import com.pudutech.mirsdk.aidl.serialize.ChargingPileInfo;
import com.pudutech.mirsdk.bluetooth.BluetoothBleHelper;
import com.pudutech.mirsdk.bluetooth.BluetoothBleListener;
import com.pudutech.mirsdk.charge.ChargeMessageCenter;
import com.pudutech.mirsdk.charge.ChargeMessageParser;
import com.pudutech.mirsdk.function.C4946R;
import com.pudutech.mirsdk.mircore.coreparcel.DockerDetectResult;
import com.pudutech.mirsdk.utiles.BluetoothRecyclerAdapter;
import com.pudutech.mirsdk.utiles.RecyclerAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: ChargeSetActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000w\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0005\u000e\u001c '*\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u0004H\u0002J\u0010\u0010/\u001a\u00020\u00042\u0006\u00100\u001a\u000201H\u0002J\u0010\u00102\u001a\u00020-2\u0006\u0010.\u001a\u00020\u0004H\u0002J\b\u00103\u001a\u00020-H\u0002J\u0010\u00104\u001a\u00020-2\u0006\u0010.\u001a\u00020\u0004H\u0002J\b\u00105\u001a\u00020-H\u0002J\u0010\u00106\u001a\u00020\u00152\u0006\u0010.\u001a\u00020\u0004H\u0002J\b\u00107\u001a\u00020-H\u0003J\u0012\u00108\u001a\u00020-2\b\u00109\u001a\u0004\u0018\u00010:H\u0014J\b\u0010;\u001a\u00020-H\u0014J\b\u0010<\u001a\u00020-H\u0014J\u0010\u0010=\u001a\u00020-2\u0006\u0010.\u001a\u00020\u0004H\u0002J\b\u0010>\u001a\u00020-H\u0002J\u0010\u0010?\u001a\u00020-2\u0006\u0010@\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018R\u0010\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001dR\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0004\n\u0002\u0010!R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\f¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0010\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0004\n\u0002\u0010(R\u0010\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0004\n\u0002\u0010+¨\u0006A"}, m3961d2 = {"Lcom/pudutech/mirsdk/activity/ChargeSetActivity;", "Landroid/app/Activity;", "()V", "TAG", "", "cacheName", "callback", "Lcom/pudutech/mirsdk/bluetooth/BluetoothBleListener;", "debugAdapter", "Lcom/pudutech/mirsdk/utiles/RecyclerAdapter;", "debugCallback", "debugDataList", "", "debugMessageParserListener", "com/pudutech/mirsdk/activity/ChargeSetActivity$debugMessageParserListener$1", "Lcom/pudutech/mirsdk/activity/ChargeSetActivity$debugMessageParserListener$1;", "debugParser", "Lcom/pudutech/mirsdk/charge/ChargeMessageParser;", "deviceAdapter", "Lcom/pudutech/mirsdk/utiles/BluetoothRecyclerAdapter;", "isRecMessage", "", "()Z", "setRecMessage", "(Z)V", "isScanning", "setScanning", "messageParserListener", "com/pudutech/mirsdk/activity/ChargeSetActivity$messageParserListener$1", "Lcom/pudutech/mirsdk/activity/ChargeSetActivity$messageParserListener$1;", "parser", "scanCallback", "com/pudutech/mirsdk/activity/ChargeSetActivity$scanCallback$1", "Lcom/pudutech/mirsdk/activity/ChargeSetActivity$scanCallback$1;", "scanResultList", "Landroid/bluetooth/le/ScanResult;", "getScanResultList", "()Ljava/util/List;", "scanStateListener", "com/pudutech/mirsdk/activity/ChargeSetActivity$scanStateListener$1", "Lcom/pudutech/mirsdk/activity/ChargeSetActivity$scanStateListener$1;", "updateChargeListener", "com/pudutech/mirsdk/activity/ChargeSetActivity$updateChargeListener$1", "Lcom/pudutech/mirsdk/activity/ChargeSetActivity$updateChargeListener$1;", "addChargePile", "", "mac", "getConnectStateStr", "state", "", "initCallBack", "initData", "initDebugCallback", "initView", "isMac", "notifyData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onStop", "startVersionReq", "updateAutoUpdateText", "updateDebugInfo", "info", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ChargeSetActivity extends Activity {
    private HashMap _$_findViewCache;
    private BluetoothBleListener callback;
    private RecyclerAdapter debugAdapter;
    private BluetoothBleListener debugCallback;
    private ChargeMessageParser debugParser;
    private BluetoothRecyclerAdapter deviceAdapter;
    private boolean isRecMessage;
    private boolean isScanning;
    private ChargeMessageParser parser;
    private final String TAG = "ChargeSetActivity";
    private List<String> debugDataList = new ArrayList();
    private final List<ScanResult> scanResultList = new ArrayList();
    private final ChargeSetActivity$scanStateListener$1 scanStateListener = new ChargeSetActivity$scanStateListener$1(this);
    private final ChargeSetActivity$scanCallback$1 scanCallback = new ChargeSetActivity$scanCallback$1(this);
    private final ChargeSetActivity$messageParserListener$1 messageParserListener = new ChargeMessageParser.OnChargeMessageParserListener() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$messageParserListener$1
        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onVersion(String version) {
            ChargeMessageParser chargeMessageParser;
            Intrinsics.checkParameterIsNotNull(version, "version");
            ChargeSetActivity.this.setRecMessage(true);
            ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
            StringBuilder sb = new StringBuilder();
            chargeMessageParser = ChargeSetActivity.this.parser;
            sb.append(chargeMessageParser != null ? chargeMessageParser.getMac() : null);
            sb.append(" Version:V");
            sb.append(version);
            chargeSetActivity.updateDebugInfo(sb.toString());
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onChargeState(int errorCode, int busyState, int inVol, int outVol, int current, int temp) {
            ChargeMessageParser chargeMessageParser;
            ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
            StringBuilder sb = new StringBuilder();
            chargeMessageParser = ChargeSetActivity.this.parser;
            sb.append(chargeMessageParser != null ? chargeMessageParser.getMac() : null);
            sb.append(" errorCode:");
            sb.append(errorCode);
            sb.append(" busyState:");
            sb.append(busyState);
            sb.append(' ');
            sb.append("inVol:");
            sb.append(inVol);
            sb.append("mV outVol:");
            sb.append(outVol);
            sb.append("mV current:");
            sb.append(current);
            sb.append("mA temp:");
            sb.append(temp);
            chargeSetActivity.updateDebugInfo(sb.toString());
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onChargeStartReply(boolean isSuccess) {
            ChargeMessageParser chargeMessageParser;
            ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
            StringBuilder sb = new StringBuilder();
            chargeMessageParser = ChargeSetActivity.this.parser;
            sb.append(chargeMessageParser != null ? chargeMessageParser.getMac() : null);
            sb.append(" Charge Start Reply ");
            sb.append(isSuccess);
            chargeSetActivity.updateDebugInfo(sb.toString());
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onChargeStopReply(boolean isSuccess) {
            ChargeMessageParser chargeMessageParser;
            ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
            StringBuilder sb = new StringBuilder();
            chargeMessageParser = ChargeSetActivity.this.parser;
            sb.append(chargeMessageParser != null ? chargeMessageParser.getMac() : null);
            sb.append(" Charge Stop Reply ");
            sb.append(isSuccess);
            chargeSetActivity.updateDebugInfo(sb.toString());
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onCmdFail() {
            ChargeMessageParser chargeMessageParser;
            ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
            StringBuilder sb = new StringBuilder();
            chargeMessageParser = ChargeSetActivity.this.parser;
            sb.append(chargeMessageParser != null ? chargeMessageParser.getMac() : null);
            sb.append("  Cmd Fail");
            chargeSetActivity.updateDebugInfo(sb.toString());
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onChargeArrived() {
            ChargeMessageParser chargeMessageParser;
            ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
            StringBuilder sb = new StringBuilder();
            chargeMessageParser = ChargeSetActivity.this.parser;
            sb.append(chargeMessageParser != null ? chargeMessageParser.getMac() : null);
            sb.append("  Charge Arrived");
            chargeSetActivity.updateDebugInfo(sb.toString());
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onChargeLeave() {
            ChargeMessageParser chargeMessageParser;
            ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
            StringBuilder sb = new StringBuilder();
            chargeMessageParser = ChargeSetActivity.this.parser;
            sb.append(chargeMessageParser != null ? chargeMessageParser.getMac() : null);
            sb.append("  Charge Leave");
            chargeSetActivity.updateDebugInfo(sb.toString());
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onChargeTimeout() {
            ChargeMessageParser chargeMessageParser;
            ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
            StringBuilder sb = new StringBuilder();
            chargeMessageParser = ChargeSetActivity.this.parser;
            sb.append(chargeMessageParser != null ? chargeMessageParser.getMac() : null);
            sb.append("  Charge Timeout");
            chargeSetActivity.updateDebugInfo(sb.toString());
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onRebootReply() {
            ChargeMessageParser chargeMessageParser;
            ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
            StringBuilder sb = new StringBuilder();
            chargeMessageParser = ChargeSetActivity.this.parser;
            sb.append(chargeMessageParser != null ? chargeMessageParser.getMac() : null);
            sb.append("  Reboot Reply");
            chargeSetActivity.updateDebugInfo(sb.toString());
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onCheckPileSuccess() {
            ChargeMessageParser chargeMessageParser;
            ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
            StringBuilder sb = new StringBuilder();
            chargeMessageParser = ChargeSetActivity.this.parser;
            sb.append(chargeMessageParser != null ? chargeMessageParser.getMac() : null);
            sb.append(" Check Charge Success");
            chargeSetActivity.updateDebugInfo(sb.toString());
        }
    };
    private final ChargeSetActivity$updateChargeListener$1 updateChargeListener = new BluetoothChargeUpdateListener.Stub() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$updateChargeListener$1
        @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
        public void onStartUpdate(String mac, String targetVersion) {
            ChargeSetActivity.this.updateDebugInfo("start Update targetVersion " + targetVersion);
        }

        @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
        public void onConnectingDevice(String mac) {
            ChargeSetActivity.this.notifyData();
            ChargeSetActivity.this.updateDebugInfo(mac + " connecting Device ");
        }

        @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
        public void onCheckingVersionBefore(String mac) {
            ChargeSetActivity.this.notifyData();
            ChargeSetActivity.this.updateDebugInfo(mac + " checking version before update");
        }

        @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
        public void onRequestToIAPMode(String mac) {
            ChargeSetActivity.this.notifyData();
            ChargeSetActivity.this.updateDebugInfo(mac + " request to IAP mode");
        }

        @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
        public void onSendData(String mac, int total, int progress) {
            ChargeSetActivity.this.notifyData();
            ChargeSetActivity.this.updateDebugInfo(mac + " send pack " + progress + '/' + total);
        }

        @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
        public void onRequestToAPPMode(String mac) {
            ChargeSetActivity.this.notifyData();
            ChargeSetActivity.this.updateDebugInfo(mac + " request to APP mode");
        }

        @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
        public void onUpdateSuccess(String mac, String targetVersion) {
            ChargeSetActivity.this.notifyData();
            ChargeSetActivity.this.updateDebugInfo(mac + " Update Success version " + targetVersion);
        }

        @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
        public void onUpdateError(String mac, BluetoothChargeUpdateErrorType p1, String describe) {
            ChargeSetActivity.this.notifyData();
            ChargeSetActivity.this.updateDebugInfo(mac + " update error " + describe);
        }
    };
    private String cacheName = "";
    private final ChargeSetActivity$debugMessageParserListener$1 debugMessageParserListener = new ChargeMessageParser.OnChargeMessageParserListener() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$debugMessageParserListener$1
        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onVersion(String version) {
            ChargeMessageParser chargeMessageParser;
            Intrinsics.checkParameterIsNotNull(version, "version");
            ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
            StringBuilder sb = new StringBuilder();
            chargeMessageParser = ChargeSetActivity.this.debugParser;
            sb.append(chargeMessageParser != null ? chargeMessageParser.getMac() : null);
            sb.append(" Version:V");
            sb.append(version);
            chargeSetActivity.updateDebugInfo(sb.toString());
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onChargeState(int errorCode, int busyState, int inVol, int outVol, int current, int temp) {
            ChargeMessageParser chargeMessageParser;
            ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
            StringBuilder sb = new StringBuilder();
            chargeMessageParser = ChargeSetActivity.this.debugParser;
            sb.append(chargeMessageParser != null ? chargeMessageParser.getMac() : null);
            sb.append(" errorCode:");
            sb.append(errorCode);
            sb.append(" busyState:");
            sb.append(busyState);
            sb.append(' ');
            sb.append("inVol:");
            sb.append(inVol);
            sb.append("mV outVol:");
            sb.append(outVol);
            sb.append("mV current:");
            sb.append(current);
            sb.append("mA temp:");
            sb.append(temp);
            chargeSetActivity.updateDebugInfo(sb.toString());
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onChargeStartReply(boolean isSuccess) {
            ChargeMessageParser chargeMessageParser;
            ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
            StringBuilder sb = new StringBuilder();
            chargeMessageParser = ChargeSetActivity.this.debugParser;
            sb.append(chargeMessageParser != null ? chargeMessageParser.getMac() : null);
            sb.append(" Charge Start Reply ");
            sb.append(isSuccess);
            chargeSetActivity.updateDebugInfo(sb.toString());
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onChargeStopReply(boolean isSuccess) {
            ChargeMessageParser chargeMessageParser;
            ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
            StringBuilder sb = new StringBuilder();
            chargeMessageParser = ChargeSetActivity.this.debugParser;
            sb.append(chargeMessageParser != null ? chargeMessageParser.getMac() : null);
            sb.append(" Charge Stop Reply ");
            sb.append(isSuccess);
            chargeSetActivity.updateDebugInfo(sb.toString());
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onCmdFail() {
            ChargeMessageParser chargeMessageParser;
            ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
            StringBuilder sb = new StringBuilder();
            chargeMessageParser = ChargeSetActivity.this.debugParser;
            sb.append(chargeMessageParser != null ? chargeMessageParser.getMac() : null);
            sb.append("  Cmd Fail");
            chargeSetActivity.updateDebugInfo(sb.toString());
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onChargeArrived() {
            ChargeMessageParser chargeMessageParser;
            ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
            StringBuilder sb = new StringBuilder();
            chargeMessageParser = ChargeSetActivity.this.debugParser;
            sb.append(chargeMessageParser != null ? chargeMessageParser.getMac() : null);
            sb.append("  Charge Arrived");
            chargeSetActivity.updateDebugInfo(sb.toString());
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onChargeLeave() {
            ChargeMessageParser chargeMessageParser;
            ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
            StringBuilder sb = new StringBuilder();
            chargeMessageParser = ChargeSetActivity.this.debugParser;
            sb.append(chargeMessageParser != null ? chargeMessageParser.getMac() : null);
            sb.append("  Charge Leave");
            chargeSetActivity.updateDebugInfo(sb.toString());
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onChargeTimeout() {
            ChargeMessageParser chargeMessageParser;
            ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
            StringBuilder sb = new StringBuilder();
            chargeMessageParser = ChargeSetActivity.this.debugParser;
            sb.append(chargeMessageParser != null ? chargeMessageParser.getMac() : null);
            sb.append("  Charge Timeout");
            chargeSetActivity.updateDebugInfo(sb.toString());
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onRebootReply() {
            ChargeMessageParser chargeMessageParser;
            ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
            StringBuilder sb = new StringBuilder();
            chargeMessageParser = ChargeSetActivity.this.debugParser;
            sb.append(chargeMessageParser != null ? chargeMessageParser.getMac() : null);
            sb.append("  Reboot Reply");
            chargeSetActivity.updateDebugInfo(sb.toString());
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onCheckPileSuccess() {
            ChargeMessageParser chargeMessageParser;
            ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
            StringBuilder sb = new StringBuilder();
            chargeMessageParser = ChargeSetActivity.this.debugParser;
            sb.append(chargeMessageParser != null ? chargeMessageParser.getMac() : null);
            sb.append(" Check Charge Success");
            chargeSetActivity.updateDebugInfo(sb.toString());
        }
    };

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

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

    public final List<ScanResult> getScanResultList() {
        return this.scanResultList;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4946R.layout.activity_charge_set);
        initData();
        initView();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        BluetoothChargeInterface bluetoothChargeInterface;
        super.onDestroy();
        BluetoothBleListener bluetoothBleListener = this.callback;
        if (bluetoothBleListener != null) {
            BluetoothBleHelper.INSTANCE.removeBluetoothBleListeners(bluetoothBleListener);
        }
        BluetoothBleListener bluetoothBleListener2 = this.debugCallback;
        if (bluetoothBleListener2 != null) {
            BluetoothBleHelper.INSTANCE.removeBluetoothBleListeners(bluetoothBleListener2);
        }
        BluetoothBleHelper.scanBleDevice$default(BluetoothBleHelper.INSTANCE, false, 0L, null, 6, null);
        BluetoothBleHelper.INSTANCE.removeBleScanCallBack(this.scanCallback);
        BluetoothBleHelper.INSTANCE.removeBluetoothScanStateListener(this.scanStateListener);
        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
        if (sDKInterface != null && (bluetoothChargeInterface = sDKInterface.getBluetoothChargeInterface()) != null) {
            bluetoothChargeInterface.removeBluetoothChargeUpdateListener(this.TAG);
        }
        SDKServiceConnection.INSTANCE.setOnAddChargePointResult((Function2) null);
    }

    private final void initData() {
        BluetoothBleHelper.INSTANCE.initHelper(this);
        BluetoothBleHelper.INSTANCE.addBleScanCallback(this.scanCallback);
        BluetoothBleHelper.INSTANCE.addBluetoothScanStateListener(this.scanStateListener);
        SDKServiceConnection.INSTANCE.setOnAddChargePointResult(new Function2<Boolean, String, Unit>() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$initData$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                invoke(bool.booleanValue(), str);
                return Unit.INSTANCE;
            }

            public final void invoke(final boolean z, final String str) {
                String str2;
                str2 = ChargeSetActivity.this.TAG;
                Pdlog.m3275i(str2, "showing add charge point result " + z + ' ' + str);
                ChargeSetActivity.this.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$initData$1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Button button_set_charge_pose = (Button) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.button_set_charge_pose);
                        Intrinsics.checkExpressionValueIsNotNull(button_set_charge_pose, "button_set_charge_pose");
                        button_set_charge_pose.setEnabled(true);
                        if (z) {
                            TextView tv_docker = (TextView) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.tv_docker);
                            Intrinsics.checkExpressionValueIsNotNull(tv_docker, "tv_docker");
                            tv_docker.setText("add charge pose success");
                            Toast.makeText(ChargeSetActivity.this, "add charge pose success", 0).show();
                            return;
                        }
                        TextView tv_docker2 = (TextView) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.tv_docker);
                        Intrinsics.checkExpressionValueIsNotNull(tv_docker2, "tv_docker");
                        tv_docker2.setText("add charge pose fail," + str);
                        Toast.makeText(ChargeSetActivity.this, "add charge pose fail," + str, 0).show();
                    }
                });
            }
        });
        updateAutoUpdateText();
    }

    /* renamed from: isScanning, reason: from getter */
    public final boolean getIsScanning() {
        return this.isScanning;
    }

    public final void setScanning(boolean z) {
        this.isScanning = z;
    }

    /* renamed from: isRecMessage, reason: from getter */
    public final boolean getIsRecMessage() {
        return this.isRecMessage;
    }

    public final void setRecMessage(boolean z) {
        this.isRecMessage = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyData() {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new ChargeSetActivity$notifyData$1(this, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAutoUpdateText() {
        BluetoothChargeInterface bluetoothChargeInterface;
        Button button_auto_update = (Button) _$_findCachedViewById(C4946R.id.button_auto_update);
        Intrinsics.checkExpressionValueIsNotNull(button_auto_update, "button_auto_update");
        Resources resources = getResources();
        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
        button_auto_update.setText(resources.getText((sDKInterface == null || (bluetoothChargeInterface = sDKInterface.getBluetoothChargeInterface()) == null || !bluetoothChargeInterface.isAutoUpdate()) ? C4946R.string.auto_update_charge_close : C4946R.string.auto_update_charge_open));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initCallBack(String mac) {
        if (!Intrinsics.areEqual(mac, this.callback != null ? r0.getMac() : null)) {
            ChargeMessageParser chargeMessageParser = this.parser;
            if (chargeMessageParser != null) {
                chargeMessageParser.setMessageParserListener((ChargeMessageParser.OnChargeMessageParserListener) null);
            }
            ChargeMessageParser chargeMessageParser2 = new ChargeMessageParser(mac);
            chargeMessageParser2.setMessageParserListener(this.messageParserListener);
            this.parser = chargeMessageParser2;
            BluetoothBleListener bluetoothBleListener = this.callback;
            if (bluetoothBleListener != null) {
                BluetoothBleHelper.INSTANCE.removeBluetoothBleListeners(bluetoothBleListener);
            }
            this.callback = new ChargeSetActivity$initCallBack$3(this, mac, mac);
        }
        BluetoothBleHelper bluetoothBleHelper = BluetoothBleHelper.INSTANCE;
        BluetoothBleListener bluetoothBleListener2 = this.callback;
        if (bluetoothBleListener2 == null) {
            Intrinsics.throwNpe();
        }
        bluetoothBleHelper.addBluetoothBleListeners(bluetoothBleListener2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startVersionReq(String mac) {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getIO(), null, new ChargeSetActivity$startVersionReq$1(this, mac, null), 2, null);
    }

    private final void initView() {
        ((Button) _$_findCachedViewById(C4946R.id.button_set_charge_pose)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$initView$1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r7v4 */
            /* JADX WARN: Type inference failed for: r7v5 */
            /* JADX WARN: Type inference failed for: r7v6, types: [java.lang.Object] */
            /* JADX WARN: Type inference failed for: r8v9, types: [java.lang.Object] */
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChargingPileInfo chargingPileInfo;
                boolean isMac;
                String str;
                BluetoothBleListener bluetoothBleListener;
                String str2;
                String str3;
                String str4;
                ChargingPileInfo chargingPileInfo2;
                MoveActionInterface moveActionInterface;
                SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                if (sDKInterface == null || !sDKInterface.isRelocalizationSuccess()) {
                    ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
                    Toast.makeText(chargeSetActivity, chargeSetActivity.getResources().getString(C4946R.string.location_fail), 1).show();
                    TextView tv_docker = (TextView) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.tv_docker);
                    Intrinsics.checkExpressionValueIsNotNull(tv_docker, "tv_docker");
                    tv_docker.setText(ChargeSetActivity.this.getResources().getString(C4946R.string.location_fail));
                    return;
                }
                EditText et_charge_name = (EditText) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.et_charge_name);
                Intrinsics.checkExpressionValueIsNotNull(et_charge_name, "et_charge_name");
                if (et_charge_name.getText().toString().length() == 0) {
                    Toast.makeText(ChargeSetActivity.this, "name not allow empty", 1).show();
                    return;
                }
                SDKInterface sDKInterface2 = SDKServiceConnection.INSTANCE.getInterface();
                ChargingPileInfo chargingPileInfo3 = null;
                List<ChargingPileInfo> chargingPileInfos = (sDKInterface2 == null || (moveActionInterface = sDKInterface2.getMoveActionInterface()) == null) ? null : moveActionInterface.getChargingPileInfos();
                EditText et_charge_name2 = (EditText) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.et_charge_name);
                Intrinsics.checkExpressionValueIsNotNull(et_charge_name2, "et_charge_name");
                String obj = et_charge_name2.getText().toString();
                if (chargingPileInfos != null) {
                    Iterator it = chargingPileInfos.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            chargingPileInfo2 = 0;
                            break;
                        } else {
                            chargingPileInfo2 = it.next();
                            if (Intrinsics.areEqual(((ChargingPileInfo) chargingPileInfo2).getId(), obj)) {
                                break;
                            }
                        }
                    }
                    chargingPileInfo = chargingPileInfo2;
                } else {
                    chargingPileInfo = null;
                }
                if (chargingPileInfo != null) {
                    TextView tv_docker2 = (TextView) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.tv_docker);
                    Intrinsics.checkExpressionValueIsNotNull(tv_docker2, "tv_docker");
                    tv_docker2.setText("error: name of existing charging point");
                    return;
                }
                EditText et_charge_mac = (EditText) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.et_charge_mac);
                Intrinsics.checkExpressionValueIsNotNull(et_charge_mac, "et_charge_mac");
                Editable text = et_charge_mac.getText();
                Intrinsics.checkExpressionValueIsNotNull(text, "et_charge_mac.text");
                if (text.length() == 0) {
                    str4 = ChargeSetActivity.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("add charge name ");
                    EditText et_charge_name3 = (EditText) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.et_charge_name);
                    Intrinsics.checkExpressionValueIsNotNull(et_charge_name3, "et_charge_name");
                    sb.append((Object) et_charge_name3.getText());
                    Pdlog.m3275i(str4, sb.toString());
                    ChargeSetActivity.this.cacheName = obj;
                    ChargeSetActivity.this.addChargePile("");
                    return;
                }
                EditText et_charge_mac2 = (EditText) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.et_charge_mac);
                Intrinsics.checkExpressionValueIsNotNull(et_charge_mac2, "et_charge_mac");
                Editable text2 = et_charge_mac2.getText();
                Intrinsics.checkExpressionValueIsNotNull(text2, "et_charge_mac.text");
                String obj2 = StringsKt.trim(text2).toString();
                isMac = ChargeSetActivity.this.isMac(obj2);
                if (!isMac) {
                    Toast.makeText(ChargeSetActivity.this, "Not a MAC address ", 0).show();
                    return;
                }
                if (chargingPileInfos != null) {
                    Iterator it2 = chargingPileInfos.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        ?? next = it2.next();
                        if (Intrinsics.areEqual(((ChargingPileInfo) next).getMac(), obj2)) {
                            chargingPileInfo3 = next;
                            break;
                        }
                    }
                    chargingPileInfo3 = chargingPileInfo3;
                }
                if (chargingPileInfo3 == null) {
                    ChargeSetActivity.this.cacheName = obj;
                    Button button_set_charge_pose = (Button) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.button_set_charge_pose);
                    Intrinsics.checkExpressionValueIsNotNull(button_set_charge_pose, "button_set_charge_pose");
                    button_set_charge_pose.setEnabled(false);
                    str = ChargeSetActivity.this.TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("add charge name ");
                    EditText et_charge_name4 = (EditText) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.et_charge_name);
                    Intrinsics.checkExpressionValueIsNotNull(et_charge_name4, "et_charge_name");
                    sb2.append((Object) et_charge_name4.getText());
                    sb2.append(" mac ");
                    EditText et_charge_mac3 = (EditText) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.et_charge_mac);
                    Intrinsics.checkExpressionValueIsNotNull(et_charge_mac3, "et_charge_mac");
                    sb2.append((Object) et_charge_mac3.getText());
                    Pdlog.m3275i(str, sb2.toString());
                    TextView tv_docker3 = (TextView) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.tv_docker);
                    Intrinsics.checkExpressionValueIsNotNull(tv_docker3, "tv_docker");
                    tv_docker3.setText("detecting...");
                    BluetoothBleHelper.scanBleDevice$default(BluetoothBleHelper.INSTANCE, false, 0L, null, 6, null);
                    ChargeSetActivity.this.initCallBack(obj2);
                    ChargeSetActivity.this.setRecMessage(false);
                    bluetoothBleListener = ChargeSetActivity.this.callback;
                    if (bluetoothBleListener != null) {
                        if (!Intrinsics.areEqual(bluetoothBleListener.getMac(), obj2)) {
                            str3 = ChargeSetActivity.this.TAG;
                            Pdlog.m3273d(str3, "swap device");
                            BluetoothBleHelper.INSTANCE.disconnectDevice(bluetoothBleListener.getMac());
                            BluetoothBleHelper.INSTANCE.connectDevice(obj2);
                        } else if (BluetoothBleHelper.INSTANCE.getConnectState(obj2) == 2) {
                            str2 = ChargeSetActivity.this.TAG;
                            Pdlog.m3273d(str2, "device already connected");
                            ChargeSetActivity.this.startVersionReq(obj2);
                        } else {
                            BluetoothBleHelper.INSTANCE.connectDevice(obj2);
                        }
                        if (bluetoothBleListener != null) {
                            return;
                        }
                    }
                    BluetoothBleHelper.INSTANCE.connectDevice(obj2);
                    Unit unit = Unit.INSTANCE;
                    return;
                }
                TextView tv_docker4 = (TextView) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.tv_docker);
                Intrinsics.checkExpressionValueIsNotNull(tv_docker4, "tv_docker");
                tv_docker4.setText("error: MAC of existing charging point");
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.button_scan_device)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$initView$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (ChargeSetActivity.this.getIsScanning()) {
                    Button button_scan_device = (Button) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.button_scan_device);
                    Intrinsics.checkExpressionValueIsNotNull(button_scan_device, "button_scan_device");
                    button_scan_device.setText(ChargeSetActivity.this.getString(C4946R.string.scan_devices));
                    BluetoothBleHelper.scanBleDevice$default(BluetoothBleHelper.INSTANCE, false, 0L, null, 6, null);
                    return;
                }
                Button button_scan_device2 = (Button) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.button_scan_device);
                Intrinsics.checkExpressionValueIsNotNull(button_scan_device2, "button_scan_device");
                button_scan_device2.setText(ChargeSetActivity.this.getString(C4946R.string.scan_devices_stop));
                ArrayList arrayList = new ArrayList();
                ScanFilter build = new ScanFilter.Builder().setDeviceName("PDCharger").build();
                Intrinsics.checkExpressionValueIsNotNull(build, "ScanFilter.Builder().set…Name(\"PDCharger\").build()");
                arrayList.add(build);
                ScanFilter build2 = new ScanFilter.Builder().setDeviceName("PdInstall").build();
                Intrinsics.checkExpressionValueIsNotNull(build2, "ScanFilter.Builder().set…Name(\"PdInstall\").build()");
                arrayList.add(build2);
                BluetoothBleHelper.INSTANCE.scanBleDevice(true, 5000L, arrayList);
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.button_connect_device)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$initView$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean isMac;
                BluetoothBleListener bluetoothBleListener;
                String str;
                EditText et_charge_mac = (EditText) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.et_charge_mac);
                Intrinsics.checkExpressionValueIsNotNull(et_charge_mac, "et_charge_mac");
                Editable text = et_charge_mac.getText();
                Intrinsics.checkExpressionValueIsNotNull(text, "et_charge_mac.text");
                String obj = StringsKt.trim(text).toString();
                isMac = ChargeSetActivity.this.isMac(obj);
                if (isMac) {
                    BluetoothBleHelper.scanBleDevice$default(BluetoothBleHelper.INSTANCE, false, 0L, null, 6, null);
                    ChargeSetActivity.this.initDebugCallback(obj);
                    ChargeSetActivity.this.setRecMessage(false);
                    bluetoothBleListener = ChargeSetActivity.this.debugCallback;
                    if (bluetoothBleListener != null) {
                        if (!Intrinsics.areEqual(bluetoothBleListener.getMac(), obj)) {
                            BluetoothBleHelper.INSTANCE.disconnectDevice(bluetoothBleListener.getMac());
                            BluetoothBleHelper.INSTANCE.connectDevice(obj);
                        } else if (BluetoothBleHelper.INSTANCE.getConnectState(obj) == 2) {
                            str = ChargeSetActivity.this.TAG;
                            Pdlog.m3273d(str, "device already connected");
                        } else {
                            BluetoothBleHelper.INSTANCE.connectDevice(obj);
                        }
                        if (bluetoothBleListener != null) {
                            return;
                        }
                    }
                    BluetoothBleHelper.INSTANCE.connectDevice(obj);
                    Unit unit = Unit.INSTANCE;
                    return;
                }
                Toast.makeText(ChargeSetActivity.this, "Not a MAC address ", 0).show();
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.button_disconnect_device)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$initView$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BluetoothBleListener bluetoothBleListener;
                bluetoothBleListener = ChargeSetActivity.this.debugCallback;
                if (bluetoothBleListener != null) {
                    ChargeSetActivity.this.updateDebugInfo("disconnectDevice " + bluetoothBleListener.getMac());
                    BluetoothBleHelper.INSTANCE.disconnectDevice(bluetoothBleListener.getMac());
                    if (bluetoothBleListener != null) {
                        return;
                    }
                }
                ChargeSetActivity.this.updateDebugInfo("disconnectDevice unknown mac");
                Unit unit = Unit.INSTANCE;
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.button_vision)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$initView$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BluetoothBleListener bluetoothBleListener;
                bluetoothBleListener = ChargeSetActivity.this.debugCallback;
                if (bluetoothBleListener != null) {
                    ChargeMessageCenter.INSTANCE.sendVersionReq(bluetoothBleListener.getMac());
                }
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.button_status)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$initView$6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BluetoothBleListener bluetoothBleListener;
                bluetoothBleListener = ChargeSetActivity.this.debugCallback;
                if (bluetoothBleListener != null) {
                    ChargeMessageCenter.INSTANCE.sendStateReq(bluetoothBleListener.getMac());
                }
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.button_charge_start)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$initView$7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BluetoothBleListener bluetoothBleListener;
                bluetoothBleListener = ChargeSetActivity.this.debugCallback;
                if (bluetoothBleListener != null) {
                    ChargeMessageCenter.INSTANCE.sendStartCharge(bluetoothBleListener.getMac());
                }
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.button_charge_stop)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$initView$8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BluetoothBleListener bluetoothBleListener;
                bluetoothBleListener = ChargeSetActivity.this.debugCallback;
                if (bluetoothBleListener != null) {
                    ChargeMessageCenter.INSTANCE.sendStopCharge(bluetoothBleListener.getMac());
                }
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.button_reboot)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$initView$9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BluetoothBleListener bluetoothBleListener;
                bluetoothBleListener = ChargeSetActivity.this.debugCallback;
                if (bluetoothBleListener != null) {
                    ChargeMessageCenter.INSTANCE.sendRebootReq(bluetoothBleListener.getMac());
                }
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.btn_clear_charge_log)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$initView$10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                List list;
                RecyclerAdapter recyclerAdapter;
                list = ChargeSetActivity.this.debugDataList;
                list.clear();
                recyclerAdapter = ChargeSetActivity.this.debugAdapter;
                if (recyclerAdapter != null) {
                    recyclerAdapter.notifyDataSetChanged();
                }
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.button_update_device)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$initView$11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean isMac;
                BluetoothChargeInterface bluetoothChargeInterface;
                BluetoothChargeInterface bluetoothChargeInterface2;
                String str;
                ChargeSetActivity$updateChargeListener$1 chargeSetActivity$updateChargeListener$1;
                BluetoothChargeInterface bluetoothChargeInterface3;
                EditText et_charge_mac = (EditText) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.et_charge_mac);
                Intrinsics.checkExpressionValueIsNotNull(et_charge_mac, "et_charge_mac");
                Editable text = et_charge_mac.getText();
                Intrinsics.checkExpressionValueIsNotNull(text, "et_charge_mac.text");
                String obj = StringsKt.trim(text).toString();
                SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                if (sDKInterface == null || (bluetoothChargeInterface3 = sDKInterface.getBluetoothChargeInterface()) == null || !bluetoothChargeInterface3.isUpdating(obj)) {
                    isMac = ChargeSetActivity.this.isMac(obj);
                    if (isMac) {
                        SDKInterface sDKInterface2 = SDKServiceConnection.INSTANCE.getInterface();
                        if (sDKInterface2 != null && (bluetoothChargeInterface2 = sDKInterface2.getBluetoothChargeInterface()) != null) {
                            str = ChargeSetActivity.this.TAG;
                            chargeSetActivity$updateChargeListener$1 = ChargeSetActivity.this.updateChargeListener;
                            bluetoothChargeInterface2.addBluetoothChargeUpdateListener(str, chargeSetActivity$updateChargeListener$1);
                        }
                        SDKInterface sDKInterface3 = SDKServiceConnection.INSTANCE.getInterface();
                        if (sDKInterface3 == null || (bluetoothChargeInterface = sDKInterface3.getBluetoothChargeInterface()) == null) {
                            return;
                        }
                        bluetoothChargeInterface.startUpdateTask(obj);
                        return;
                    }
                    Toast.makeText(ChargeSetActivity.this, "Not a MAC address ", 0).show();
                    return;
                }
                Toast.makeText(ChargeSetActivity.this, "current device is In updating", 0).show();
            }
        });
        ((Button) _$_findCachedViewById(C4946R.id.button_auto_update)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$initView$12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BluetoothChargeInterface bluetoothChargeInterface;
                BluetoothChargeInterface bluetoothChargeInterface2;
                SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                if (sDKInterface != null && (bluetoothChargeInterface = sDKInterface.getBluetoothChargeInterface()) != null) {
                    boolean isAutoUpdate = bluetoothChargeInterface.isAutoUpdate();
                    SDKInterface sDKInterface2 = SDKServiceConnection.INSTANCE.getInterface();
                    if (sDKInterface2 != null && (bluetoothChargeInterface2 = sDKInterface2.getBluetoothChargeInterface()) != null) {
                        bluetoothChargeInterface2.setAutoUpdate(!isAutoUpdate);
                    }
                }
                ChargeSetActivity.this.updateAutoUpdateText();
            }
        });
        this.deviceAdapter = new BluetoothRecyclerAdapter(this.scanResultList);
        BluetoothRecyclerAdapter bluetoothRecyclerAdapter = this.deviceAdapter;
        if (bluetoothRecyclerAdapter != null) {
            bluetoothRecyclerAdapter.setOnItemClickListener(new BluetoothRecyclerAdapter.OnItemClickListener() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$initView$13
                @Override // com.pudutech.mirsdk.utiles.BluetoothRecyclerAdapter.OnItemClickListener
                public void onItemClick(int position, String mac) {
                    Toast.makeText(ChargeSetActivity.this, "Choose Device: " + mac, 0).show();
                    EditText et_charge_mac = (EditText) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.et_charge_mac);
                    Intrinsics.checkExpressionValueIsNotNull(et_charge_mac, "et_charge_mac");
                    et_charge_mac.setText(Editable.Factory.getInstance().newEditable(mac));
                    if (mac == null || BluetoothBleHelper.INSTANCE.getConnectState(mac) != 2) {
                        return;
                    }
                    ChargeSetActivity.this.initDebugCallback(mac);
                }
            });
        }
        ChargeSetActivity chargeSetActivity = this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(chargeSetActivity);
        RecyclerView device_recyclerview = (RecyclerView) _$_findCachedViewById(C4946R.id.device_recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(device_recyclerview, "device_recyclerview");
        device_recyclerview.setLayoutManager(linearLayoutManager);
        RecyclerView device_recyclerview2 = (RecyclerView) _$_findCachedViewById(C4946R.id.device_recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(device_recyclerview2, "device_recyclerview");
        device_recyclerview2.setAdapter(this.deviceAdapter);
        this.debugAdapter = new RecyclerAdapter(this.debugDataList);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(chargeSetActivity);
        RecyclerView charge_log_recyclerview = (RecyclerView) _$_findCachedViewById(C4946R.id.charge_log_recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(charge_log_recyclerview, "charge_log_recyclerview");
        charge_log_recyclerview.setLayoutManager(linearLayoutManager2);
        RecyclerView charge_log_recyclerview2 = (RecyclerView) _$_findCachedViewById(C4946R.id.charge_log_recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(charge_log_recyclerview2, "charge_log_recyclerview");
        charge_log_recyclerview2.setAdapter(this.debugAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v24 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [T] */
    public final void addChargePile(String mac) {
        ChargingPileInfo chargingPileInfo;
        ChargingPileInfo chargingPileInfo2;
        MoveActionInterface moveActionInterface;
        if (this.cacheName.length() == 0) {
            return;
        }
        Button button_set_charge_pose = (Button) _$_findCachedViewById(C4946R.id.button_set_charge_pose);
        Intrinsics.checkExpressionValueIsNotNull(button_set_charge_pose, "button_set_charge_pose");
        button_set_charge_pose.setEnabled(false);
        TextView tv_docker = (TextView) _$_findCachedViewById(C4946R.id.tv_docker);
        Intrinsics.checkExpressionValueIsNotNull(tv_docker, "tv_docker");
        tv_docker.setText("detecting...");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
        objectRef.element = sDKInterface != null ? sDKInterface.detectChargePile() : 0;
        Pdlog.m3273d(this.TAG, "addChargePile " + mac);
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append(getResources().getString(C4946R.string.charge_detect_success));
        sb.append("  ");
        DockerDetectResult dockerDetectResult = (DockerDetectResult) objectRef.element;
        sb.append(dockerDetectResult != null ? Double.valueOf(dockerDetectResult.getX()) : null);
        sb.append("  ");
        DockerDetectResult dockerDetectResult2 = (DockerDetectResult) objectRef.element;
        sb.append(dockerDetectResult2 != null ? Double.valueOf(dockerDetectResult2.getY()) : null);
        sb.append("  ");
        DockerDetectResult dockerDetectResult3 = (DockerDetectResult) objectRef.element;
        sb.append(dockerDetectResult3 != null ? Double.valueOf(dockerDetectResult3.getTheta()) : null);
        sb.append('\"');
        objArr[0] = sb.toString();
        Pdlog.m3275i(str, objArr);
        if (((DockerDetectResult) objectRef.element) != null && ((DockerDetectResult) objectRef.element).getX() != 0.0d) {
            runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$addChargePile$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public final void run() {
                    TextView tv_docker2 = (TextView) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.tv_docker);
                    Intrinsics.checkExpressionValueIsNotNull(tv_docker2, "tv_docker");
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(ChargeSetActivity.this.getResources().getString(C4946R.string.charge_detect_success));
                    sb2.append("  ");
                    DockerDetectResult dockerDetectResult4 = (DockerDetectResult) objectRef.element;
                    sb2.append((dockerDetectResult4 != null ? Double.valueOf(dockerDetectResult4.getX()) : null).doubleValue());
                    sb2.append("  ");
                    DockerDetectResult dockerDetectResult5 = (DockerDetectResult) objectRef.element;
                    sb2.append((dockerDetectResult5 != null ? Double.valueOf(dockerDetectResult5.getY()) : null).doubleValue());
                    sb2.append("  ");
                    DockerDetectResult dockerDetectResult6 = (DockerDetectResult) objectRef.element;
                    sb2.append((dockerDetectResult6 != null ? Double.valueOf(dockerDetectResult6.getTheta()) : null).doubleValue());
                    tv_docker2.setText(sb2.toString());
                }
            });
            if (mac.length() == 0) {
                SDKInterface sDKInterface2 = SDKServiceConnection.INSTANCE.getInterface();
                if (sDKInterface2 != null) {
                    sDKInterface2.addChargePile(this.cacheName, (DockerDetectResult) objectRef.element);
                }
            } else {
                SDKInterface sDKInterface3 = SDKServiceConnection.INSTANCE.getInterface();
                List<ChargingPileInfo> chargingPileInfos = (sDKInterface3 == null || (moveActionInterface = sDKInterface3.getMoveActionInterface()) == null) ? null : moveActionInterface.getChargingPileInfos();
                if (chargingPileInfos != null) {
                    Iterator it = chargingPileInfos.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            chargingPileInfo2 = 0;
                            break;
                        } else {
                            chargingPileInfo2 = it.next();
                            if (Intrinsics.areEqual(((ChargingPileInfo) chargingPileInfo2).getId(), mac)) {
                                break;
                            }
                        }
                    }
                    chargingPileInfo = chargingPileInfo2;
                } else {
                    chargingPileInfo = null;
                }
                if (chargingPileInfo != null) {
                    TextView tv_docker2 = (TextView) _$_findCachedViewById(C4946R.id.tv_docker);
                    Intrinsics.checkExpressionValueIsNotNull(tv_docker2, "tv_docker");
                    tv_docker2.setText("error: name of existing charging point");
                    return;
                } else {
                    SDKInterface sDKInterface4 = SDKServiceConnection.INSTANCE.getInterface();
                    if (sDKInterface4 != null) {
                        sDKInterface4.addBluetoothChargePile(this.cacheName, mac, (DockerDetectResult) objectRef.element);
                    }
                    BluetoothBleListener bluetoothBleListener = this.callback;
                    if (bluetoothBleListener != null) {
                        BluetoothBleHelper.INSTANCE.removeBluetoothBleListeners(bluetoothBleListener);
                    }
                    this.callback = (BluetoothBleListener) null;
                }
            }
            this.cacheName = "";
            return;
        }
        Pdlog.m3275i(this.TAG, "detect fail");
        runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$addChargePile$4
            @Override // java.lang.Runnable
            public final void run() {
                ChargeSetActivity chargeSetActivity = ChargeSetActivity.this;
                Toast.makeText(chargeSetActivity, chargeSetActivity.getResources().getString(C4946R.string.charge_detect_fail), 1).show();
                Button button_set_charge_pose2 = (Button) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.button_set_charge_pose);
                Intrinsics.checkExpressionValueIsNotNull(button_set_charge_pose2, "button_set_charge_pose");
                button_set_charge_pose2.setEnabled(true);
                TextView tv_docker3 = (TextView) ChargeSetActivity.this._$_findCachedViewById(C4946R.id.tv_docker);
                Intrinsics.checkExpressionValueIsNotNull(tv_docker3, "tv_docker");
                tv_docker3.setText(ChargeSetActivity.this.getResources().getString(C4946R.string.charge_detect_fail));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initDebugCallback(String mac) {
        if (!Intrinsics.areEqual(this.debugCallback != null ? r0.getMac() : null, mac)) {
            ChargeMessageParser chargeMessageParser = this.debugParser;
            if (chargeMessageParser != null) {
                chargeMessageParser.setMessageParserListener((ChargeMessageParser.OnChargeMessageParserListener) null);
            }
            ChargeMessageParser chargeMessageParser2 = new ChargeMessageParser(mac);
            chargeMessageParser2.setMessageParserListener(this.debugMessageParserListener);
            this.debugParser = chargeMessageParser2;
            BluetoothBleListener bluetoothBleListener = this.debugCallback;
            if (bluetoothBleListener != null) {
                BluetoothBleHelper.INSTANCE.removeBluetoothBleListeners(bluetoothBleListener);
            }
            this.debugCallback = new ChargeSetActivity$initDebugCallback$3(this, mac, mac);
        }
        BluetoothBleHelper bluetoothBleHelper = BluetoothBleHelper.INSTANCE;
        BluetoothBleListener bluetoothBleListener2 = this.debugCallback;
        if (bluetoothBleListener2 == null) {
            Intrinsics.throwNpe();
        }
        bluetoothBleHelper.addBluetoothBleListeners(bluetoothBleListener2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getConnectStateStr(int state) {
        if (state == 0) {
            return "未连接[DISCONNECTED]";
        }
        if (state == 1) {
            return "连接中[CONNECTING]";
        }
        if (state == 2) {
            return "已连接[CONNECTED]";
        }
        if (state == 3) {
            return "断开中[DISCONNECTING]";
        }
        return "Unknown State " + state;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isMac(String mac) {
        return new Regex("([A-Fa-f0-9]{2}:){5}[A-Fa-f0-9]{2}").matches(mac);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateDebugInfo(String info) {
        Pdlog.m3273d(this.TAG, info);
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new ChargeSetActivity$updateDebugInfo$1(this, info, null), 2, null);
    }
}
