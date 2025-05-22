package com.pudutech.peanut.robot_ui.p063ui;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.fragment.LanguageSelectFragment;
import com.pudutech.peanut.robot_ui.p063ui.fragment.RobotActiveFragment;
import com.pudutech.peanut.robot_ui.util.FragmentUtils;
import com.pudutech.peanut.robot_ui.util.NetStatusUtil;
import com.pudutech.peanut.robot_ui.util.NetWorkChangeEvent;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RobotActiveActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\bH\u0002J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u000e\u001a\u00020\u000bH\u0014J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u000bH\u0014J\u0010\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\rH\u0014J\b\u0010\u0015\u001a\u00020\u000bH\u0014J\b\u0010\u0016\u001a\u00020\u000bH\u0014J\b\u0010\u0017\u001a\u00020\u000bH\u0002J\u0010\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0018\u00010\u0006R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/RobotActiveActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/MyBaseActivity;", "()V", "TAG", "", "netWorkChangeReceiver", "Lcom/pudutech/peanut/robot_ui/ui/RobotActiveActivity$NetWorkChangeReceiver;", "getLanguageFragment", "Landroidx/fragment/app/Fragment;", "getRobotActiveFragment", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onNetStatus", "isconnect", "", "onResume", "onSaveInstanceState", "outState", "onStart", "onStop", "registerNet", "showFragment", C3898x.f4339h, "NetWorkChangeReceiver", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class RobotActiveActivity extends MyBaseActivity {
    private final String TAG = "RobotActiveActivity";
    private HashMap _$_findViewCache;
    private NetWorkChangeReceiver netWorkChangeReceiver;

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pdlog.m3273d(this.TAG, "onCreate ");
        setContentView(C5508R.layout.activity_robtot_active);
        registerNet();
        NetWorkChangeReceiver netWorkChangeReceiver = this.netWorkChangeReceiver;
        if (netWorkChangeReceiver != null) {
            netWorkChangeReceiver.onNetworkChange();
        }
        showFragment(getLanguageFragment());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkParameterIsNotNull(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.remove("android:support:fragments");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Fragment getLanguageFragment() {
        LanguageSelectFragment languageSelectFragment = new LanguageSelectFragment();
        languageSelectFragment.setOnLanguageSelect(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.RobotActiveActivity$getLanguageFragment$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                String str;
                Fragment robotActiveFragment;
                str = RobotActiveActivity.this.TAG;
                Pdlog.m3273d(str, " onLanguageSelect ");
                RobotActiveActivity robotActiveActivity = RobotActiveActivity.this;
                robotActiveFragment = robotActiveActivity.getRobotActiveFragment();
                robotActiveActivity.showFragment(robotActiveFragment);
            }
        });
        return languageSelectFragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Fragment getRobotActiveFragment() {
        RobotActiveFragment robotActiveFragment = new RobotActiveFragment();
        robotActiveFragment.setOnBackClick(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.RobotActiveActivity$getRobotActiveFragment$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                String str;
                Fragment languageFragment;
                str = RobotActiveActivity.this.TAG;
                Pdlog.m3273d(str, " onBackClick");
                RobotActiveActivity robotActiveActivity = RobotActiveActivity.this;
                languageFragment = robotActiveActivity.getLanguageFragment();
                robotActiveActivity.showFragment(languageFragment);
            }
        });
        return robotActiveFragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showFragment(Fragment f) {
        FragmentUtils.replace(getSupportFragmentManager(), f, C5508R.id.fragment_container);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "onDestroy ");
        try {
            unregisterReceiver(this.netWorkChangeReceiver);
        } catch (Exception unused) {
        }
    }

    private final void registerNet() {
        if (this.netWorkChangeReceiver == null) {
            this.netWorkChangeReceiver = new NetWorkChangeReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.setPriority(Integer.MAX_VALUE);
        try {
            registerReceiver(this.netWorkChangeReceiver, intentFilter);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: RobotActiveActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/RobotActiveActivity$NetWorkChangeReceiver;", "Lcom/pudutech/peanut/robot_ui/util/NetWorkChangeEvent;", "(Lcom/pudutech/peanut/robot_ui/ui/RobotActiveActivity;)V", "onNetworkChange", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final class NetWorkChangeReceiver extends NetWorkChangeEvent {
        public NetWorkChangeReceiver() {
        }

        @Override // com.pudutech.peanut.robot_ui.util.NetWorkChangeEvent
        public void onNetworkChange() {
            if (NetStatusUtil.isConnected(RobotContext.INSTANCE.getContext())) {
                RobotActiveActivity.this.onNetStatus(true);
            } else {
                RobotActiveActivity.this.onNetStatus(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onNetStatus(boolean isconnect) {
        Pdlog.m3273d(this.TAG, "onNetStatus = " + isconnect);
        if (isconnect) {
            ((ImageView) _$_findCachedViewById(C5508R.id.wifi_status_show)).setImageResource(C5508R.drawable.nav_icon_wifi_connected);
        } else {
            ((ImageView) _$_findCachedViewById(C5508R.id.wifi_status_show)).setImageResource(C5508R.drawable.nav_icon_wifi_unconnected);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        Pdlog.m3273d(this.TAG, "onStart ");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Pdlog.m3273d(this.TAG, "onResume ");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        Pdlog.m3273d(this.TAG, "onStop ");
    }
}
