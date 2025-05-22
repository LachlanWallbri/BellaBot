package com.pudutech.schedulerlib.p065ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.sdksafe.SDKSafe;
import com.pudutech.mirsdk.update.ApiConstants;
import com.pudutech.schedulerlib.C5725R;
import com.pudutech.schedulerlib.ScheduleController;
import com.pudutech.schedulerlib.connection.ESPConnection;
import com.pudutech.schedulerlib.connection.PeanutEspService;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: SchedulerTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\b\n\u0018\u0000 ;2\u00020\u0001:\u0005;<=>?B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\u0012\u0010!\u001a\u00020\n2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u0004H\u0002J\b\u0010%\u001a\u00020\u001eH\u0002J\b\u0010&\u001a\u00020\u001eH\u0002J\u001a\u0010'\u001a\u00020\n2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010(\u001a\u00020#H\u0002J\u0012\u0010)\u001a\u00020\u001e2\b\u0010*\u001a\u0004\u0018\u00010+H\u0014J\b\u0010,\u001a\u00020\u001eH\u0014J\u0012\u0010-\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\b\u00100\u001a\u00020\u001eH\u0014J\b\u00101\u001a\u00020\u001eH\u0014J\u000e\u00102\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\b\u00103\u001a\u00020\u001eH\u0002J(\u00104\u001a\u00020\u001e2\u001e\u00105\u001a\u001a\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001c0606H\u0003J\u000e\u00107\u001a\u00020\u001e2\u0006\u00108\u001a\u00020\u0016J\u000e\u00109\u001a\u00020\u001e2\u0006\u00108\u001a\u00020\u0018J\b\u0010:\u001a\u00020\u001eH\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006@"}, m3961d2 = {"Lcom/pudutech/schedulerlib/ui/SchedulerTestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "controller", "Lcom/pudutech/schedulerlib/ScheduleController;", "debuger", "", "delayTime", "", "dialog", "Lcom/pudutech/schedulerlib/ui/ChannelChoiceBottomSheetDialog;", "errorFragmentList", "", "Landroidx/fragment/app/Fragment;", "errorMsgListFragment", "Lcom/pudutech/schedulerlib/ui/ErrorMsgFragment;", "fragmentList", "onErrorMsgListener", "Lcom/pudutech/schedulerlib/ui/SchedulerTestActivity$OnErrorFrameListener;", "onReceivedMsgListener", "Lcom/pudutech/schedulerlib/ui/SchedulerTestActivity$OnReceivedMsgListener;", "receivedReceivedMsgListFragment", "Lcom/pudutech/schedulerlib/ui/ReceivedMsgListFragment;", "sendIndex", "", "choiceChannel", "", "v", "Landroid/view/View;", "dispatchTouchEvent", "ev", "Landroid/view/MotionEvent;", "getWIFIMac", "initData", "initWidget", "isShouldHideInput", "event", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPause", "onResume", "openAll", "registerEvents", "resetStatus", "info", "", "setOnErrorMsgListener", "listener", "setOnReceivedMsgListener", "startSend", "Companion", "ErrListFragmentPagerAdapter", "MsgListFragmentPagerAdapter", "OnErrorFrameListener", "OnReceivedMsgListener", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class SchedulerTestActivity extends AppCompatActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static boolean isClick;
    private HashMap _$_findViewCache;
    private boolean debuger;
    private ChannelChoiceBottomSheetDialog dialog;
    private OnErrorFrameListener onErrorMsgListener;
    private OnReceivedMsgListener onReceivedMsgListener;
    private int sendIndex;
    private final String TAG = "SchedulerTestActivity";
    private final ScheduleController controller = ScheduleController.INSTANCE.getInstance();
    private List<Fragment> fragmentList = new ArrayList();
    private List<Fragment> errorFragmentList = new ArrayList();
    private ReceivedMsgListFragment receivedReceivedMsgListFragment = new ReceivedMsgListFragment();
    private ErrorMsgFragment errorMsgListFragment = new ErrorMsgFragment();
    private long delayTime = 100;

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* compiled from: SchedulerTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u001e\u0010\u0004\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00050\u0005H&¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/schedulerlib/ui/SchedulerTestActivity$OnErrorFrameListener;", "", "onReceivedMsg", "", "info", "", "", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public interface OnErrorFrameListener {
        void onReceivedMsg(Map<String, Map<String, String>> info);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* compiled from: SchedulerTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u001e\u0010\u0004\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0005H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/schedulerlib/ui/SchedulerTestActivity$OnReceivedMsgListener;", "", "onReceivedMsg", "", "info", "", "", "", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public interface OnReceivedMsgListener {
        void onReceivedMsg(Map<String, Map<String, Integer>> info);
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
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final void openAll(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
    }

    public final String getTAG() {
        return this.TAG;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* compiled from: SchedulerTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/schedulerlib/ui/SchedulerTestActivity$Companion;", "", "()V", "isClick", "", "()Z", "setClick", "(Z)V", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isClick() {
            return SchedulerTestActivity.isClick;
        }

        public final void setClick(boolean z) {
            SchedulerTestActivity.isClick = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5725R.layout.schedulerlib_activity_scheduler_test);
        initData();
        initWidget();
        registerEvents();
        this.controller.setDebuger$schedulerlib_release(true);
        Boolean espIsConnected = this.controller.espIsConnected();
        if (espIsConnected == null) {
            Intrinsics.throwNpe();
        }
        if (espIsConnected.booleanValue() || this.controller.udpIsConnected()) {
            ((Button) _$_findCachedViewById(C5725R.id.comm_key)).setText("通信开关（已打开）");
        } else {
            ((Button) _$_findCachedViewById(C5725R.id.comm_key)).setText("通信开关（已关闭）");
        }
        TextView textView = (TextView) _$_findCachedViewById(C5725R.id.map_flag);
        String map_md5 = this.controller.getMap_md5();
        if (map_md5 == null) {
            map_md5 = "未获取到地图";
        }
        textView.setText(map_md5);
        Pdlog.m3273d(this.TAG, "pdlog inited");
        PeanutEspService.INSTANCE.setOnTestListener(new Function1<Integer, Unit>() { // from class: com.pudutech.schedulerlib.ui.SchedulerTestActivity$onCreate$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final int i) {
                ((TextView) SchedulerTestActivity.this._$_findCachedViewById(C5725R.id.rec_tv)).post(new Runnable() { // from class: com.pudutech.schedulerlib.ui.SchedulerTestActivity$onCreate$1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        TextView rec_tv = (TextView) SchedulerTestActivity.this._$_findCachedViewById(C5725R.id.rec_tv);
                        Intrinsics.checkExpressionValueIsNotNull(rec_tv, "rec_tv");
                        rec_tv.setText("接收：" + i);
                    }
                });
            }
        });
        ESPConnection.INSTANCE.setOnTestListener(new Function1<Integer, Unit>() { // from class: com.pudutech.schedulerlib.ui.SchedulerTestActivity$onCreate$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final int i) {
                ((TextView) SchedulerTestActivity.this._$_findCachedViewById(C5725R.id.rec_tv)).post(new Runnable() { // from class: com.pudutech.schedulerlib.ui.SchedulerTestActivity$onCreate$2.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        TextView rec_tv = (TextView) SchedulerTestActivity.this._$_findCachedViewById(C5725R.id.rec_tv);
                        Intrinsics.checkExpressionValueIsNotNull(rec_tv, "rec_tv");
                        rec_tv.setText("接收：" + i);
                    }
                });
            }
        });
        Button pause_btn = (Button) _$_findCachedViewById(C5725R.id.pause_btn);
        Intrinsics.checkExpressionValueIsNotNull(pause_btn, "pause_btn");
        pause_btn.setEnabled(this.debuger);
        if (this.debuger) {
            Button pause_btn2 = (Button) _$_findCachedViewById(C5725R.id.pause_btn);
            Intrinsics.checkExpressionValueIsNotNull(pause_btn2, "pause_btn");
            pause_btn2.setText(isClick ? "开始" : "暂停");
        }
        ((Button) _$_findCachedViewById(C5725R.id.pause_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.schedulerlib.ui.SchedulerTestActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                SchedulerTestActivity.INSTANCE.setClick(!SchedulerTestActivity.INSTANCE.isClick());
                Button pause_btn3 = (Button) SchedulerTestActivity.this._$_findCachedViewById(C5725R.id.pause_btn);
                Intrinsics.checkExpressionValueIsNotNull(pause_btn3, "pause_btn");
                if (SchedulerTestActivity.INSTANCE.isClick()) {
                    PeanutEspService.INSTANCE.setPause(true);
                } else {
                    PeanutEspService.INSTANCE.setPause(false);
                }
                pause_btn3.setText(str);
            }
        });
    }

    private final void initWidget() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), null, new SchedulerTestActivity$initWidget$1(this, null), 2, null);
        TextView textView = (TextView) _$_findCachedViewById(C5725R.id.multi_ip);
        Intrinsics.checkExpressionValueIsNotNull(textView, "this@SchedulerTestActivity.multi_ip");
        textView.setText(this.controller.getMulticastAddress$schedulerlib_release());
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "supportFragmentManager");
        MsgListFragmentPagerAdapter msgListFragmentPagerAdapter = new MsgListFragmentPagerAdapter(this, supportFragmentManager);
        FragmentManager supportFragmentManager2 = getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager2, "supportFragmentManager");
        ErrListFragmentPagerAdapter errListFragmentPagerAdapter = new ErrListFragmentPagerAdapter(this, supportFragmentManager2);
        ViewPager viewPager = (ViewPager) _$_findCachedViewById(C5725R.id.viewPager);
        Intrinsics.checkExpressionValueIsNotNull(viewPager, "viewPager");
        viewPager.setAdapter(msgListFragmentPagerAdapter);
        ViewPager errPager = (ViewPager) _$_findCachedViewById(C5725R.id.errPager);
        Intrinsics.checkExpressionValueIsNotNull(errPager, "errPager");
        errPager.setAdapter(errListFragmentPagerAdapter);
    }

    private final void initData() {
        this.fragmentList.add(this.receivedReceivedMsgListFragment);
        this.errorFragmentList.add(this.errorMsgListFragment);
    }

    private final void registerEvents() {
        this.controller.getFspListener$schedulerlib_release().add(this.TAG, new SchedulerTestActivity$registerEvents$1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startSend() {
        if (this.debuger) {
            return;
        }
        String wIFIMac = getWIFIMac();
        this.debuger = true;
        ((Button) _$_findCachedViewById(C5725R.id.pause_btn)).post(new Runnable() { // from class: com.pudutech.schedulerlib.ui.SchedulerTestActivity$startSend$1
            @Override // java.lang.Runnable
            public final void run() {
                Button pause_btn = (Button) SchedulerTestActivity.this._$_findCachedViewById(C5725R.id.pause_btn);
                Intrinsics.checkExpressionValueIsNotNull(pause_btn, "pause_btn");
                pause_btn.setEnabled(true);
                Button pause_btn2 = (Button) SchedulerTestActivity.this._$_findCachedViewById(C5725R.id.pause_btn);
                Intrinsics.checkExpressionValueIsNotNull(pause_btn2, "pause_btn");
                pause_btn2.setText("暂停");
            }
        });
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SchedulerTestActivity$startSend$2(this, wIFIMac, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.controller.getFspListener$schedulerlib_release().remove(this.TAG);
        this.controller.setDebuger$schedulerlib_release(false);
        this.debuger = false;
        super.onDestroy();
        Function1<? super Integer, Unit> function1 = (Function1) null;
        PeanutEspService.INSTANCE.setOnTestListener(function1);
        ESPConnection.INSTANCE.setOnTestListener(function1);
        this.onReceivedMsgListener = (OnReceivedMsgListener) null;
        this.onErrorMsgListener = (OnErrorFrameListener) null;
        this.fragmentList.clear();
        this.errorFragmentList.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    public final void choiceChannel(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        SDKSafe.INSTANCE.checkControlAuth(this, "swchnl", new SchedulerTestActivity$choiceChannel$1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetStatus(Map<String, Map<String, Integer>> info) {
        Map<String, Integer> map = info.get("udp");
        Boolean valueOf = map != null ? Boolean.valueOf(map.isEmpty()) : null;
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        String str = valueOf.booleanValue() ? "无数据" : "数据正常";
        Map<String, Integer> map2 = info.get("esp");
        Boolean valueOf2 = map2 != null ? Boolean.valueOf(map2.isEmpty()) : null;
        if (valueOf2 == null) {
            Intrinsics.throwNpe();
        }
        String str2 = valueOf2.booleanValue() ? "无数据" : "数据正常";
        String str3 = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("udp msg ");
        sb.append(str);
        sb.append(' ');
        Map<String, Integer> map3 = info.get("udp");
        sb.append(map3 != null ? Boolean.valueOf(map3.isEmpty()) : null);
        sb.append(' ');
        sb.append(info);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str3, objArr);
        TextView tv_status = (TextView) _$_findCachedViewById(C5725R.id.tv_status);
        Intrinsics.checkExpressionValueIsNotNull(tv_status, "tv_status");
        tv_status.setText("Status: udp is connected:" + this.controller.udpIsConnected() + ' ' + str + "\t\tesp is connected:" + this.controller.espIsConnected() + ' ' + str2);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev != null && ev.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (isShouldHideInput(currentFocus, ev)) {
                Object systemService = getSystemService("input_method");
                if (systemService == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                }
                InputMethodManager inputMethodManager = (InputMethodManager) systemService;
                if (currentFocus == null) {
                    Intrinsics.throwNpe();
                }
                inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            }
            return super.dispatchTouchEvent(ev);
        }
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    private final boolean isShouldHideInput(View v, MotionEvent event) {
        if (v == null || !(v instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        v.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        EditText editText = (EditText) v;
        return event.getX() <= ((float) i) || event.getX() >= ((float) (editText.getWidth() + i)) || event.getY() <= ((float) i2) || event.getY() >= ((float) (editText.getHeight() + i2));
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        Integer valueOf = item != null ? Integer.valueOf(item.getItemId()) : null;
        if (valueOf != null && valueOf.intValue() == 16908332) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* compiled from: SchedulerTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0016¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/schedulerlib/ui/SchedulerTestActivity$MsgListFragmentPagerAdapter;", "Landroidx/fragment/app/FragmentStatePagerAdapter;", "fm", "Landroidx/fragment/app/FragmentManager;", "(Lcom/pudutech/schedulerlib/ui/SchedulerTestActivity;Landroidx/fragment/app/FragmentManager;)V", "getCount", "", "getItem", "Landroidx/fragment/app/Fragment;", RequestParameters.POSITION, "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public final class MsgListFragmentPagerAdapter extends FragmentStatePagerAdapter {
        final /* synthetic */ SchedulerTestActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MsgListFragmentPagerAdapter(SchedulerTestActivity schedulerTestActivity, FragmentManager fm) {
            super(fm, 1);
            Intrinsics.checkParameterIsNotNull(fm, "fm");
            this.this$0 = schedulerTestActivity;
        }

        @Override // androidx.fragment.app.FragmentStatePagerAdapter
        public Fragment getItem(int position) {
            return (Fragment) this.this$0.fragmentList.get(position);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.this$0.fragmentList.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* compiled from: SchedulerTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0016¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/schedulerlib/ui/SchedulerTestActivity$ErrListFragmentPagerAdapter;", "Landroidx/fragment/app/FragmentStatePagerAdapter;", "fm", "Landroidx/fragment/app/FragmentManager;", "(Lcom/pudutech/schedulerlib/ui/SchedulerTestActivity;Landroidx/fragment/app/FragmentManager;)V", "getCount", "", "getItem", "Landroidx/fragment/app/Fragment;", RequestParameters.POSITION, "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public final class ErrListFragmentPagerAdapter extends FragmentStatePagerAdapter {
        final /* synthetic */ SchedulerTestActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ErrListFragmentPagerAdapter(SchedulerTestActivity schedulerTestActivity, FragmentManager fm) {
            super(fm, 1);
            Intrinsics.checkParameterIsNotNull(fm, "fm");
            this.this$0 = schedulerTestActivity;
        }

        @Override // androidx.fragment.app.FragmentStatePagerAdapter
        public Fragment getItem(int position) {
            return (Fragment) this.this$0.errorFragmentList.get(position);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.this$0.errorFragmentList.size();
        }
    }

    public final void setOnReceivedMsgListener(OnReceivedMsgListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.onReceivedMsgListener = listener;
    }

    public final void setOnErrorMsgListener(OnErrorFrameListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.onErrorMsgListener = listener;
    }

    private final String getWIFIMac() {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface nif = (NetworkInterface) it.next();
                Intrinsics.checkExpressionValueIsNotNull(nif, "nif");
                if (StringsKt.equals(nif.getName(), "wlan0", true)) {
                    byte[] hardwareAddress = nif.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return ApiConstants.MAC_ADDRESS;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String format = String.format("%02X:", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
                        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                        sb.append(format);
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    String sb2 = sb.toString();
                    Intrinsics.checkExpressionValueIsNotNull(sb2, "mac_str.toString()");
                    return sb2;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiConstants.MAC_ADDRESS;
    }
}
