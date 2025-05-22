package com.pudutech.robot.opensdk.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.pudutech.base.Pdlog;
import com.pudutech.pdmqtt.config.MosquittoMqttConfig;
import com.pudutech.robot.opensdk.RemoteConnectState;
import com.pudutech.robot.opensdk.RobotOpenSdk;
import com.pudutech.robot.opensdk.interf.IRemoteConnectStateListener;
import com.pudutech.robot_open_sdk.C5723R;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* compiled from: ConnectFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0002J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J&\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u001a\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\r2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/activity/ConnectFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "param1", "param2", "getWIFIMac", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "Companion", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ConnectFragment extends Fragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String TAG = "ConnectFragment";
    private HashMap _$_findViewCache;
    private String param1;
    private String param2;

    @JvmStatic
    public static final ConnectFragment newInstance(String str, String str2) {
        return INSTANCE.newInstance(str, str2);
    }

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
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.param1 = arguments.getString("param1");
            this.param2 = arguments.getString("param2");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        TextView textView_robot_id = (TextView) _$_findCachedViewById(C5723R.id.textView_robot_id);
        Intrinsics.checkExpressionValueIsNotNull(textView_robot_id, "textView_robot_id");
        textView_robot_id.setText("RobotID: " + RobotOpenSdk.INSTANCE.getDeviceId());
        RobotOpenSdk.INSTANCE.addConnectStateListener(new IRemoteConnectStateListener() { // from class: com.pudutech.robot.opensdk.activity.ConnectFragment$onViewCreated$1
            @Override // com.pudutech.robot.opensdk.interf.IRemoteConnectStateListener
            public void onConnectState(RemoteConnectState state) {
                Intrinsics.checkParameterIsNotNull(state, "state");
                TextView textView_connect_state = (TextView) ConnectFragment.this._$_findCachedViewById(C5723R.id.textView_connect_state);
                Intrinsics.checkExpressionValueIsNotNull(textView_connect_state, "textView_connect_state");
                textView_connect_state.setText("State: " + state);
                TextView textView_robot_id2 = (TextView) ConnectFragment.this._$_findCachedViewById(C5723R.id.textView_robot_id);
                Intrinsics.checkExpressionValueIsNotNull(textView_robot_id2, "textView_robot_id");
                textView_robot_id2.setText("DeviceID: " + RobotOpenSdk.INSTANCE.getDeviceId());
                if (state == RemoteConnectState.CONNECTED) {
                    Button button_connect = (Button) ConnectFragment.this._$_findCachedViewById(C5723R.id.button_connect);
                    Intrinsics.checkExpressionValueIsNotNull(button_connect, "button_connect");
                    button_connect.setText("Disconnect");
                } else {
                    Button button_connect2 = (Button) ConnectFragment.this._$_findCachedViewById(C5723R.id.button_connect);
                    Intrinsics.checkExpressionValueIsNotNull(button_connect2, "button_connect");
                    button_connect2.setText("Connect");
                }
            }
        });
        ((Button) _$_findCachedViewById(C5723R.id.button_connect)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.robot.opensdk.activity.ConnectFragment$onViewCreated$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                String wIFIMac;
                String str;
                if (RobotOpenSdk.INSTANCE.getConnectState() == RemoteConnectState.CONNECTED) {
                    RobotOpenSdk.INSTANCE.release();
                    return;
                }
                EditText edittext_broker_host = (EditText) ConnectFragment.this._$_findCachedViewById(C5723R.id.edittext_broker_host);
                Intrinsics.checkExpressionValueIsNotNull(edittext_broker_host, "edittext_broker_host");
                String obj = edittext_broker_host.getText().toString();
                RobotOpenSdk robotOpenSdk = RobotOpenSdk.INSTANCE;
                Context requireContext = ConnectFragment.this.requireContext();
                Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
                wIFIMac = ConnectFragment.this.getWIFIMac();
                boolean connectMosquitto$default = RobotOpenSdk.connectMosquitto$default(robotOpenSdk, requireContext, new MosquittoMqttConfig("a1ZNnqHo7Cu", wIFIMac, obj, "8443"), null, 4, null);
                str = ConnectFragment.this.TAG;
                Pdlog.m3273d(str, "connectMosquitto : " + connectMosquitto$default);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getWIFIMac() {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface nif = (NetworkInterface) it.next();
                Intrinsics.checkExpressionValueIsNotNull(nif, "nif");
                if (StringsKt.equals(nif.getName(), "wlan0", true)) {
                    byte[] hardwareAddress = nif.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "020000000000";
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        Object[] objArr = {Byte.valueOf(b)};
                        String format = String.format("%02X", Arrays.copyOf(objArr, objArr.length));
                        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                        sb.append(format);
                    }
                    String sb2 = sb.toString();
                    Intrinsics.checkExpressionValueIsNotNull(sb2, "mac_str.toString()");
                    return sb2;
                }
            }
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "get mac address fail:" + e.getMessage());
        }
        return "020000000000";
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C5723R.layout.fragment_connect, container, false);
    }

    /* compiled from: ConnectFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/activity/ConnectFragment$Companion;", "", "()V", "newInstance", "Lcom/pudutech/robot/opensdk/activity/ConnectFragment;", "param1", "", "param2", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final ConnectFragment newInstance(String param1, String param2) {
            Intrinsics.checkParameterIsNotNull(param1, "param1");
            Intrinsics.checkParameterIsNotNull(param2, "param2");
            ConnectFragment connectFragment = new ConnectFragment();
            Bundle bundle = new Bundle();
            bundle.putString("param1", param1);
            bundle.putString("param2", param2);
            connectFragment.setArguments(bundle);
            return connectFragment;
        }
    }
}
