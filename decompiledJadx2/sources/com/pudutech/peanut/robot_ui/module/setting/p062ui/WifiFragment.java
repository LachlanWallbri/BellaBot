package com.pudutech.peanut.robot_ui.module.setting.p062ui;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.util.NetStatusUtil;
import com.pudutech.peanut.robot_ui.util.NetWorkChangeEvent;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WifiFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J&\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\bH\u0016J\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u001a\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0019\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0018\u00010\u0006R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/WifiFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "netWorkChangeReceiver", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/WifiFragment$NetWorkChangeReceiver;", "onAttach", "", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "onNetStatus", "b", "", "onViewCreated", "view", "translatePage", "NetWorkChangeReceiver", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class WifiFragment extends Fragment {
    private final String TAG = "WifiFragment";
    private HashMap _$_findViewCache;
    private NetWorkChangeReceiver netWorkChangeReceiver;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C5508R.layout.fragment_wifi_setup, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        ((Button) _$_findCachedViewById(C5508R.id.btn_set_wifi)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.WifiFragment$onViewCreated$1
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                WifiFragment.this.translatePage();
            }
        });
        onNetStatus(NetStatusUtil.isConnected(RobotContext.INSTANCE.getContext()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onNetStatus(boolean b) {
        if (getContext() == null) {
            return;
        }
        if (b) {
            TextView textView = (TextView) _$_findCachedViewById(C5508R.id.wifi_status_fragment);
            if (textView != null) {
                textView.setTextColor(Color.parseColor("#1CC33D"));
            }
            TextView textView2 = (TextView) _$_findCachedViewById(C5508R.id.wifi_status_fragment);
            if (textView2 != null) {
                textView2.setText(getString(C5508R.string.pdStr7_17));
                return;
            }
            return;
        }
        TextView textView3 = (TextView) _$_findCachedViewById(C5508R.id.wifi_status_fragment);
        if (textView3 != null) {
            textView3.setTextColor(Color.parseColor("#222222"));
        }
        TextView textView4 = (TextView) _$_findCachedViewById(C5508R.id.wifi_status_fragment);
        if (textView4 != null) {
            textView4.setText(getString(C5508R.string.pdStr7_18));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super.onAttach(context);
        if (this.netWorkChangeReceiver == null) {
            this.netWorkChangeReceiver = new NetWorkChangeReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        try {
            context.registerReceiver(this.netWorkChangeReceiver, intentFilter);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        Context context;
        super.onDetach();
        try {
            NetWorkChangeReceiver netWorkChangeReceiver = this.netWorkChangeReceiver;
            if (netWorkChangeReceiver == null || (context = getContext()) == null) {
                return;
            }
            context.unregisterReceiver(netWorkChangeReceiver);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void translatePage() {
        startActivity(new Intent("android.settings.WIFI_SETTINGS"));
    }

    /* compiled from: WifiFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/WifiFragment$NetWorkChangeReceiver;", "Lcom/pudutech/peanut/robot_ui/util/NetWorkChangeEvent;", "(Lcom/pudutech/peanut/robot_ui/module/setting/ui/WifiFragment;)V", "onNetworkChange", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final class NetWorkChangeReceiver extends NetWorkChangeEvent {
        public NetWorkChangeReceiver() {
        }

        @Override // com.pudutech.peanut.robot_ui.util.NetWorkChangeEvent
        public void onNetworkChange() {
            if (!NetStatusUtil.isConnected(RobotContext.INSTANCE.getContext()) || !NetStatusUtil.isWifi(RobotContext.INSTANCE.getContext())) {
                WifiFragment.this.onNetStatus(false);
            } else {
                WifiFragment.this.onNetStatus(true);
            }
        }
    }
}
