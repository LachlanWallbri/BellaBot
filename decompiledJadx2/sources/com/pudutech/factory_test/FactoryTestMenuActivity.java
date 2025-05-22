package com.pudutech.factory_test;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import com.livinglifetechway.quickpermissions_kotlin.PermissionsManagerKt;
import com.livinglifetechway.quickpermissions_kotlin.util.QuickPermissionsOptions;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import com.pudutech.factory_test.FactoryTestMenuActivity;
import com.pudutech.factory_test.activity.DebugActivity;
import com.pudutech.factory_test.activity.ExtraInfoActivity;
import com.pudutech.factory_test.activity.UploadActivity;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.JumpToRobot;
import com.pudutech.factory_test.test_pack.NavigationBar;
import com.pudutech.factory_test.test_pack.NetStatusUtil;
import com.pudutech.factory_test.test_pack.NetWorkChangeEvent;
import com.pudutech.factory_test.test_pack.Recorder;
import com.pudutech.factory_test.test_pack.TestItem;
import com.pudutech.factory_test.test_pack.TestStage;
import com.pudutech.factory_test.test_pack.TestStatus;
import com.pudutech.factory_test.test_pack.WifiUtil;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdk.hardware.serialize.DeviceType;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.StepState;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadPoolDispatcherKt;

/* compiled from: FactoryTestMenuActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u0002FGB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#J,\u0010!\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00042\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001e0(J\u000e\u0010)\u001a\u00020*2\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010+\u001a\u0004\u0018\u00010,J\u000e\u0010-\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010.\u001a\u00020\u001e2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010/\u001a\u00020\u001eJ\"\u00100\u001a\u00020\u001e2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u0002022\b\u00104\u001a\u0004\u0018\u000105H\u0014J\b\u00106\u001a\u00020\u001eH\u0016J\u0012\u00107\u001a\u00020\u001e2\b\u00108\u001a\u0004\u0018\u000109H\u0014J\b\u0010:\u001a\u00020\u001eH\u0014J\u000e\u0010;\u001a\u00020\u001e2\u0006\u0010<\u001a\u00020=J\b\u0010>\u001a\u00020\u001eH\u0014J\b\u0010?\u001a\u00020\u001eH\u0014J\u0006\u0010@\u001a\u00020\u001eJ\u000e\u0010A\u001a\u00020\u001e2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010B\u001a\u00020=2\u0006\u0010C\u001a\u00020\u0004J\u0006\u0010D\u001a\u00020\u001eJ\u0006\u0010E\u001a\u00020\u001eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R \u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0012\u0010\u001b\u001a\u00060\u001cR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006H"}, m3961d2 = {"Lcom/pudutech/factory_test/FactoryTestMenuActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "job", "Lkotlinx/coroutines/Job;", "getJob", "()Lkotlinx/coroutines/Job;", "setJob", "(Lkotlinx/coroutines/Job;)V", "pidFilePath", "getPidFilePath", "()Ljava/lang/String;", "stage", "Lcom/pudutech/factory_test/test_pack/TestStage;", "getStage", "()Lcom/pudutech/factory_test/test_pack/TestStage;", "setStage", "(Lcom/pudutech/factory_test/test_pack/TestStage;)V", "stepStateList", "", "Lcom/pudutech/factory_test/FactoryTestMenuActivity$InitState;", "getStepStateList", "()Ljava/util/List;", "setStepStateList", "(Ljava/util/List;)V", "wifiChangeReceiver", "Lcom/pudutech/factory_test/FactoryTestMenuActivity$WifiChangeReceiver;", "connectService", "", "context", "Landroid/content/Context;", "createItemCardView", "item", "Lcom/pudutech/factory_test/test_pack/TestItem;", "name", "description", "actionName", "method", "Lkotlin/Function0;", "getTestState", "Lcom/pudutech/factory_test/test_pack/TestStatus;", "handlePermission", "", "killMirsdk", "layoutTestStageItems", "layoutUpload", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onNetStatus", "boolean", "", "onResume", "onStart", "relayout", "relayoutItems", "savePid", "pid", "setPowerListener", "showPidInputDialog", "InitState", "WifiChangeReceiver", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class FactoryTestMenuActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private Job job;
    private final String TAG = "FactoryTestMenuActivity";
    private TestStage stage = TestStage.NULL;
    private final WifiChangeReceiver wifiChangeReceiver = new WifiChangeReceiver();
    private final String pidFilePath = "/sdcard/pudu/config/pid";
    private List<InitState> stepStateList = new ArrayList();

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

    public final TestStage getStage() {
        return this.stage;
    }

    public final void setStage(TestStage testStage) {
        Intrinsics.checkParameterIsNotNull(testStage, "<set-?>");
        this.stage = testStage;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(2131427362);
        ((Button) _$_findCachedViewById(C4491R.id.btnExit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                if (FactoryTestMenuActivity.this.getStage() == TestStage.NULL) {
                    str = FactoryTestMenuActivity.this.TAG;
                    Pdlog.m3273d(str, "click exit");
                    FactoryTestMenuActivity factoryTestMenuActivity = FactoryTestMenuActivity.this;
                    factoryTestMenuActivity.killMirsdk(factoryTestMenuActivity);
                    FactoryTestMenuActivity.this.finish();
                    return;
                }
                FactoryTestMenuActivity.this.setStage(TestStage.NULL);
                FactoryTestMenuActivity.this.relayout();
            }
        });
        ((Button) _$_findCachedViewById(C4491R.id.btnNetwork)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = FactoryTestMenuActivity.this.TAG;
                Pdlog.m3273d(str, "click network");
                NavigationBar.statusBarDisable(62849024, FactoryTestMenuActivity.this);
                FactoryTestMenuActivity.this.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
            }
        });
        ((Button) _$_findCachedViewById(C4491R.id.btnResetAll)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                new AlertDialog.Builder(FactoryTestMenuActivity.this).setTitle("重置").setMessage("确认需要重置所有测试结果吗？（点击\"取消\"或屏幕其他位置可以取消）").setCancelable(true).setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$onCreate$3.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).setPositiveButton("确认", new DialogInterface.OnClickListener() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$onCreate$3.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        String str;
                        str = FactoryTestMenuActivity.this.TAG;
                        Pdlog.m3273d(str, "click reset all");
                        AllTestItem.INSTANCE.resetAll();
                        FactoryTestMenuActivity.this.relayout();
                    }
                }).show();
            }
        });
        ((Button) _$_findCachedViewById(C4491R.id.btnDebug)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = FactoryTestMenuActivity.this.TAG;
                Pdlog.m3273d(str, "click debug");
                FactoryTestMenuActivity.this.startActivityForResult(new Intent(FactoryTestMenuActivity.this, (Class<?>) DebugActivity.class), 100);
            }
        });
        Recorder.INSTANCE.init(this);
        handlePermission();
        WaterMarkUtil.INSTANCE.setEnable(true);
        WaterMarkUtil.INSTANCE.setText(String.valueOf(WifiUtil.INSTANCE.getMac()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        WaterMarkUtil.INSTANCE.onActivityStart(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Pdlog.m3275i(this.TAG, "onActivityResult requestCode=" + requestCode + "  resultCode=" + resultCode + "  data=" + data);
        if (requestCode == 100 && resultCode == 100) {
            FactoryTestMenuActivity factoryTestMenuActivity = this;
            killMirsdk(factoryTestMenuActivity);
            JumpToRobot.INSTANCE.launchRobotApp(factoryTestMenuActivity);
            finish();
        }
    }

    public final void killMirsdk(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        AIDLConnection<HardwareInterface> hdService = ServiceConnectionKt.getHdService();
        if (hdService != null) {
            hdService.disconnect(this);
        }
        AIDLConnection<RobotInterface> rbService = ServiceConnectionKt.getRbService();
        if (rbService != null) {
            rbService.disconnect(this);
        }
        JumpToRobot.INSTANCE.killMirsdk(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.wifiChangeReceiver);
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        Pdlog.m3274e(this.TAG, "onDestroy");
        Process.killProcess(Process.myPid());
    }

    public final Object handlePermission() {
        return PermissionsManagerKt.runWithPermissions$default(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.ACCESS_NETWORK_STATE", "android.permission.RECORD_AUDIO"}, (QuickPermissionsOptions) null, new Function0<Unit>() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$handlePermission$1
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
                FactoryTestMenuActivity.WifiChangeReceiver wifiChangeReceiver;
                Toast.makeText(FactoryTestMenuActivity.this, "permissions granted", 1).show();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.wifi.STATE_CHANGE");
                intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                try {
                    FactoryTestMenuActivity factoryTestMenuActivity = FactoryTestMenuActivity.this;
                    wifiChangeReceiver = FactoryTestMenuActivity.this.wifiChangeReceiver;
                    factoryTestMenuActivity.registerReceiver(wifiChangeReceiver, intentFilter);
                } catch (Exception e) {
                    str = FactoryTestMenuActivity.this.TAG;
                    Pdlog.m3274e(str, Log.getStackTraceString(e));
                }
                AllTestItem.INSTANCE.setActivity(FactoryTestMenuActivity.this);
                FactoryTestMenuActivity factoryTestMenuActivity2 = FactoryTestMenuActivity.this;
                factoryTestMenuActivity2.connectService(factoryTestMenuActivity2);
            }
        }, 2, (Object) null);
    }

    public final TestStatus getTestState(TestStage stage) {
        Intrinsics.checkParameterIsNotNull(stage, "stage");
        Iterator<TestItem> it = AllTestItem.INSTANCE.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().getStage() == stage) {
                i++;
            }
        }
        if (AllTestItem.INSTANCE.size() > 0) {
            AllTestItem allTestItem = AllTestItem.INSTANCE;
            ArrayList arrayList = new ArrayList();
            for (TestItem testItem : allTestItem) {
                TestItem testItem2 = testItem;
                if (testItem2.getStatus() == TestStatus.SUCCESS && testItem2.getStage() == stage) {
                    arrayList.add(testItem);
                }
            }
            if (arrayList.size() == i) {
                return TestStatus.SUCCESS;
            }
        }
        if (AllTestItem.INSTANCE.size() > 0) {
            AllTestItem allTestItem2 = AllTestItem.INSTANCE;
            ArrayList arrayList2 = new ArrayList();
            for (TestItem testItem3 : allTestItem2) {
                TestItem testItem4 = testItem3;
                if (testItem4.getStatus() == TestStatus.FAIL && testItem4.getStage() == stage) {
                    arrayList2.add(testItem3);
                }
            }
            if (arrayList2.size() > 0) {
                return TestStatus.FAIL;
            }
        }
        if (AllTestItem.INSTANCE.size() > 0) {
            AllTestItem allTestItem3 = AllTestItem.INSTANCE;
            ArrayList arrayList3 = new ArrayList();
            for (TestItem testItem5 : allTestItem3) {
                TestItem testItem6 = testItem5;
                if (testItem6.getStatus() == TestStatus.UNTESTED && testItem6.getStage() == stage) {
                    arrayList3.add(testItem5);
                }
            }
            if (arrayList3.size() == i) {
                return TestStatus.UNTESTED;
            }
        }
        return TestStatus.TESTING;
    }

    public final void relayout() {
        if (this.stage == TestStage.INIT_TEST) {
            relayoutItems(TestStage.INIT_TEST);
            return;
        }
        if (this.stage == TestStage.FINAL_TEST) {
            relayoutItems(TestStage.FINAL_TEST);
            return;
        }
        if (AllTestItem.INSTANCE.isEmpty()) {
            Pdlog.m3277w(this.TAG, "all test item is empty");
            return;
        }
        View childAt = ((LinearLayout) _$_findCachedViewById(C4491R.id.layoutItem)).getChildAt(0);
        ((LinearLayout) _$_findCachedViewById(C4491R.id.layoutItem)).removeAllViews();
        ((LinearLayout) _$_findCachedViewById(C4491R.id.layoutItem)).addView(childAt);
        FactoryTestMenuActivity factoryTestMenuActivity = this;
        onNetStatus(NetStatusUtil.isConnected(factoryTestMenuActivity) && NetStatusUtil.isWifi(factoryTestMenuActivity));
        createItemCardView(TestStage.INIT_TEST.getStr(), getTestState(TestStage.INIT_TEST).getStr(), "进入测试", new Function0<Unit>() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$relayout$1
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
                if (FactoryTestMenuActivity.this.getTestState(TestStage.INIT_TEST) == TestStatus.SUCCESS) {
                    Toast.makeText(FactoryTestMenuActivity.this, "初测已完成，请继续完成接下来的测试！如需重新进行初测，请点击'重置'按钮！", 1).show();
                } else {
                    FactoryTestMenuActivity.this.setStage(TestStage.INIT_TEST);
                    FactoryTestMenuActivity.this.relayoutItems(TestStage.INIT_TEST);
                }
            }
        });
        createItemCardView(TestStage.FINAL_TEST.getStr(), getTestState(TestStage.FINAL_TEST).getStr(), "进入测试", new Function0<Unit>() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$relayout$2
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
                if (FactoryTestMenuActivity.this.getTestState(TestStage.INIT_TEST) != TestStatus.SUCCESS) {
                    Toast.makeText(FactoryTestMenuActivity.this, "请先完成初测，才可进行终测", 1).show();
                } else {
                    FactoryTestMenuActivity.this.setStage(TestStage.FINAL_TEST);
                    FactoryTestMenuActivity.this.relayoutItems(TestStage.FINAL_TEST);
                }
            }
        });
        if (AllTestItem.INSTANCE.size() > 0) {
            AllTestItem allTestItem = AllTestItem.INSTANCE;
            ArrayList arrayList = new ArrayList();
            for (TestItem testItem : allTestItem) {
                if (testItem.getStatus() == TestStatus.SUCCESS) {
                    arrayList.add(testItem);
                }
            }
            if (arrayList.size() == AllTestItem.INSTANCE.size()) {
                String str = this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("all=");
                sb.append(AllTestItem.INSTANCE.size());
                sb.append(" success=");
                AllTestItem allTestItem2 = AllTestItem.INSTANCE;
                ArrayList arrayList2 = new ArrayList();
                for (TestItem testItem2 : allTestItem2) {
                    if (testItem2.getStatus() == TestStatus.SUCCESS) {
                        arrayList2.add(testItem2);
                    }
                }
                sb.append(arrayList2.size());
                objArr[0] = sb.toString();
                Pdlog.m3275i(str, objArr);
                layoutUpload();
            }
        }
    }

    public final void relayoutItems(TestStage stage) {
        Intrinsics.checkParameterIsNotNull(stage, "stage");
        if (AllTestItem.INSTANCE.isEmpty()) {
            Pdlog.m3277w(this.TAG, "all test item is empty");
            return;
        }
        View childAt = ((LinearLayout) _$_findCachedViewById(C4491R.id.layoutItem)).getChildAt(0);
        ((LinearLayout) _$_findCachedViewById(C4491R.id.layoutItem)).removeAllViews();
        ((LinearLayout) _$_findCachedViewById(C4491R.id.layoutItem)).addView(childAt);
        FactoryTestMenuActivity factoryTestMenuActivity = this;
        onNetStatus(NetStatusUtil.isConnected(factoryTestMenuActivity) && NetStatusUtil.isWifi(factoryTestMenuActivity));
        for (TestItem testItem : AllTestItem.INSTANCE) {
            if (testItem.getStage() == stage) {
                createItemCardView(testItem);
            }
        }
    }

    public final void layoutTestStageItems(TestStage stage) {
        Intrinsics.checkParameterIsNotNull(stage, "stage");
        for (TestItem testItem : AllTestItem.INSTANCE) {
            if (testItem.getStage() == stage) {
                createItemCardView(testItem);
            }
        }
    }

    public final String getPidFilePath() {
        return this.pidFilePath;
    }

    public final boolean savePid(String pid) {
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        File file = new File(this.pidFilePath);
        try {
            if (file.exists()) {
                file.delete();
            }
            FileWriter fileWriter = new FileWriter(this.pidFilePath, false);
            fileWriter.write(pid);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final void showPidInputDialog() {
        FactoryTestMenuActivity factoryTestMenuActivity = this;
        View inflate = LayoutInflater.from(factoryTestMenuActivity).inflate(2131427365, (ViewGroup) null);
        final EditText editText = (EditText) inflate.findViewById(2131231147);
        final EditText editText2 = (EditText) inflate.findViewById(2131231146);
        AlertDialog.Builder builder = new AlertDialog.Builder(factoryTestMenuActivity);
        builder.setTitle("PID输入");
        builder.setView(inflate);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$showPidInputDialog$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$showPidInputDialog$2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                EditText editPid = editText;
                Intrinsics.checkExpressionValueIsNotNull(editPid, "editPid");
                String obj = editPid.getText().toString();
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                String obj2 = StringsKt.trim((CharSequence) obj).toString();
                EditText editPidAgain = editText2;
                Intrinsics.checkExpressionValueIsNotNull(editPidAgain, "editPidAgain");
                String obj3 = editPidAgain.getText().toString();
                if (obj3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                if (Intrinsics.areEqual(obj2, StringsKt.trim((CharSequence) obj3).toString())) {
                    Toast.makeText(FactoryTestMenuActivity.this, "添加PID成功", 1).show();
                    FactoryTestMenuActivity.this.savePid(obj2);
                } else {
                    Toast.makeText(FactoryTestMenuActivity.this, "添加PID失败", 1).show();
                }
            }
        });
        builder.show();
    }

    public final void layoutUpload() {
        FactoryTestMenuActivity factoryTestMenuActivity = this;
        Button button = new Button(factoryTestMenuActivity);
        button.setText("补充信息录入");
        button.setTextSize(50.0f);
        button.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        button.setBackgroundColor(getColor(2131034162));
        button.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutHelperKt.getMATCH_PARENT(), LayoutHelperKt.getWRAP_CONTENT());
        layoutParams.setMargins(5, 5, 5, 5);
        button.setLayoutParams(layoutParams);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$layoutUpload$$inlined$apply$lambda$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = FactoryTestMenuActivity.this.TAG;
                Pdlog.m3273d(str, "click upload");
                FactoryTestMenuActivity.this.startActivity(new Intent(FactoryTestMenuActivity.this, (Class<?>) ExtraInfoActivity.class));
            }
        });
        ((LinearLayout) _$_findCachedViewById(C4491R.id.layoutItem)).addView(button);
        Button button2 = new Button(factoryTestMenuActivity);
        button2.setText("PID录入");
        button2.setTextSize(50.0f);
        button2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        button2.setBackgroundColor(getColor(2131034162));
        button2.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LayoutHelperKt.getMATCH_PARENT(), LayoutHelperKt.getWRAP_CONTENT());
        layoutParams2.setMargins(5, 5, 5, 5);
        button2.setLayoutParams(layoutParams2);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$layoutUpload$$inlined$apply$lambda$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = FactoryTestMenuActivity.this.TAG;
                Pdlog.m3273d(str, "click upload");
                if (new File(FactoryTestMenuActivity.this.getPidFilePath()).exists()) {
                    new AlertDialog.Builder(FactoryTestMenuActivity.this).setTitle("警告").setMessage("PID已存在，请确认是否需要重新录入？").setCancelable(true).setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$layoutUpload$pidInfo$1$1$1
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).setPositiveButton("确认", new DialogInterface.OnClickListener() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$layoutUpload$$inlined$apply$lambda$2.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            FactoryTestMenuActivity.this.showPidInputDialog();
                        }
                    }).show();
                } else {
                    FactoryTestMenuActivity.this.showPidInputDialog();
                }
            }
        });
        ((LinearLayout) _$_findCachedViewById(C4491R.id.layoutItem)).addView(button2);
        Button button3 = new Button(factoryTestMenuActivity);
        button3.setTextSize(50.0f);
        button3.setText("入库");
        button3.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        button3.setBackgroundColor(getColor(2131034162));
        button3.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(LayoutHelperKt.getMATCH_PARENT(), LayoutHelperKt.getWRAP_CONTENT());
        layoutParams3.setMargins(5, 5, 5, 5);
        button3.setLayoutParams(layoutParams3);
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$layoutUpload$$inlined$apply$lambda$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                if (new File(FactoryTestMenuActivity.this.getPidFilePath()).exists()) {
                    str = FactoryTestMenuActivity.this.TAG;
                    Pdlog.m3275i(str, "click to put in storage");
                    FactoryTestMenuActivity.this.startActivity(new Intent(FactoryTestMenuActivity.this, (Class<?>) UploadActivity.class));
                    return;
                }
                Toast.makeText(FactoryTestMenuActivity.this, "请先完成PID录入，才能进行入库", 1).show();
            }
        });
        ((LinearLayout) _$_findCachedViewById(C4491R.id.layoutItem)).addView(button3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Pdlog.m3273d(this.TAG, "onResume ");
        NavigationBar.statusBarDisable(67043328, this);
        relayout();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.stage != TestStage.NULL) {
            this.stage = TestStage.NULL;
            relayout();
        } else {
            super.onBackPressed();
        }
    }

    /* compiled from: FactoryTestMenuActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/factory_test/FactoryTestMenuActivity$InitState;", "", "step", "", "state", "Lcom/pudutech/mirsdk/hardware/serialize/StepState;", "description", "(Ljava/lang/String;Lcom/pudutech/mirsdk/hardware/serialize/StepState;Ljava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "setDescription", "(Ljava/lang/String;)V", "getState", "()Lcom/pudutech/mirsdk/hardware/serialize/StepState;", "setState", "(Lcom/pudutech/mirsdk/hardware/serialize/StepState;)V", "getStep", "setStep", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final /* data */ class InitState {
        private String description;
        private StepState state;
        private String step;

        public static /* synthetic */ InitState copy$default(InitState initState, String str, StepState stepState, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = initState.step;
            }
            if ((i & 2) != 0) {
                stepState = initState.state;
            }
            if ((i & 4) != 0) {
                str2 = initState.description;
            }
            return initState.copy(str, stepState, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getStep() {
            return this.step;
        }

        /* renamed from: component2, reason: from getter */
        public final StepState getState() {
            return this.state;
        }

        /* renamed from: component3, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        public final InitState copy(String step, StepState state, String description) {
            Intrinsics.checkParameterIsNotNull(step, "step");
            Intrinsics.checkParameterIsNotNull(state, "state");
            Intrinsics.checkParameterIsNotNull(description, "description");
            return new InitState(step, state, description);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InitState)) {
                return false;
            }
            InitState initState = (InitState) other;
            return Intrinsics.areEqual(this.step, initState.step) && Intrinsics.areEqual(this.state, initState.state) && Intrinsics.areEqual(this.description, initState.description);
        }

        public int hashCode() {
            String str = this.step;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            StepState stepState = this.state;
            int hashCode2 = (hashCode + (stepState != null ? stepState.hashCode() : 0)) * 31;
            String str2 = this.description;
            return hashCode2 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "InitState(step=" + this.step + ", state=" + this.state + ", description=" + this.description + ")";
        }

        public InitState(String step, StepState state, String description) {
            Intrinsics.checkParameterIsNotNull(step, "step");
            Intrinsics.checkParameterIsNotNull(state, "state");
            Intrinsics.checkParameterIsNotNull(description, "description");
            this.step = step;
            this.state = state;
            this.description = description;
        }

        public final String getDescription() {
            return this.description;
        }

        public final StepState getState() {
            return this.state;
        }

        public final String getStep() {
            return this.step;
        }

        public final void setDescription(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.description = str;
        }

        public final void setState(StepState stepState) {
            Intrinsics.checkParameterIsNotNull(stepState, "<set-?>");
            this.state = stepState;
        }

        public final void setStep(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.step = str;
        }
    }

    public final Job getJob() {
        return this.job;
    }

    public final void setJob(Job job) {
        this.job = job;
    }

    public final List<InitState> getStepStateList() {
        return this.stepStateList;
    }

    public final void setStepStateList(List<InitState> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.stepStateList = list;
    }

    public final void connectService(Context context) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(context, "context");
        final ExecutorCoroutineDispatcher newSingleThreadContext = ThreadPoolDispatcherKt.newSingleThreadContext("FactoryTestConnectHardware");
        ServiceConnectionKt.setOnHardwareOpenDone(new Function3<HardwareOpenStep, StepState, String, Unit>() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$connectService$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(HardwareOpenStep hardwareOpenStep, StepState stepState, String str) {
                invoke2(hardwareOpenStep, stepState, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final HardwareOpenStep hardwareOpenStep, final StepState stepState, final String str) {
                String str2;
                str2 = FactoryTestMenuActivity.this.TAG;
                Pdlog.m3275i(str2, "open " + hardwareOpenStep + ' ' + stepState + ' ' + str);
                FactoryTestMenuActivity.this.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$connectService$1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Object obj;
                        Iterator<T> it = FactoryTestMenuActivity.this.getStepStateList().iterator();
                        while (true) {
                            if (it.hasNext()) {
                                obj = it.next();
                                if (Intrinsics.areEqual(((FactoryTestMenuActivity.InitState) obj).getStep(), String.valueOf(hardwareOpenStep))) {
                                    break;
                                }
                            } else {
                                obj = null;
                                break;
                            }
                        }
                        FactoryTestMenuActivity.InitState initState = (FactoryTestMenuActivity.InitState) obj;
                        if (initState == null) {
                            List<FactoryTestMenuActivity.InitState> stepStateList = FactoryTestMenuActivity.this.getStepStateList();
                            String valueOf = String.valueOf(hardwareOpenStep);
                            StepState stepState2 = stepState;
                            if (stepState2 == null) {
                                Intrinsics.throwNpe();
                            }
                            String str3 = str;
                            if (str3 == null) {
                                Intrinsics.throwNpe();
                            }
                            stepStateList.add(0, new FactoryTestMenuActivity.InitState(valueOf, stepState2, str3));
                        } else {
                            StepState stepState3 = stepState;
                            if (stepState3 == null) {
                                Intrinsics.throwNpe();
                            }
                            initState.setState(stepState3);
                            String str4 = str;
                            if (str4 == null) {
                                Intrinsics.throwNpe();
                            }
                            initState.setDescription(str4);
                        }
                        TextView tvHardwareOpen = (TextView) FactoryTestMenuActivity.this._$_findCachedViewById(C4491R.id.tvHardwareOpen);
                        Intrinsics.checkExpressionValueIsNotNull(tvHardwareOpen, "tvHardwareOpen");
                        tvHardwareOpen.setText("");
                        for (FactoryTestMenuActivity.InitState initState2 : FactoryTestMenuActivity.this.getStepStateList()) {
                            TextView tvHardwareOpen2 = (TextView) FactoryTestMenuActivity.this._$_findCachedViewById(C4491R.id.tvHardwareOpen);
                            Intrinsics.checkExpressionValueIsNotNull(tvHardwareOpen2, "tvHardwareOpen");
                            TextView tvHardwareOpen3 = (TextView) FactoryTestMenuActivity.this._$_findCachedViewById(C4491R.id.tvHardwareOpen);
                            Intrinsics.checkExpressionValueIsNotNull(tvHardwareOpen3, "tvHardwareOpen");
                            tvHardwareOpen2.setText(tvHardwareOpen3.getText().toString() + '\n' + initState2.getStep() + ' ' + initState2.getState() + ' ' + initState2.getDescription());
                        }
                    }
                });
                if (hardwareOpenStep == HardwareOpenStep.CanConnect && stepState == StepState.Success) {
                    ShutDownHelper.INSTANCE.addCanDataShutDownListener(FactoryTestMenuActivity.this);
                }
                if (stepState == StepState.Fail) {
                    FactoryTestMenuActivity.this.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$connectService$1.2
                        @Override // java.lang.Runnable
                        public final void run() {
                            String str3;
                            if (hardwareOpenStep == HardwareOpenStep.RGBDConnectCheck && (str3 = str) != null && StringsKt.contains$default((CharSequence) str3, (CharSequence) "reset the location", false, 2, (Object) null)) {
                                Toast.makeText(FactoryTestMenuActivity.this, "RGBD未配置，请配置完成后，保存配置，重启apk", 1).show();
                                NavigationBar.statusBarDisable(62849024, FactoryTestMenuActivity.this);
                                HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
                                if (hdInterface == null) {
                                    Intrinsics.throwNpe();
                                }
                                hdInterface.getRGBDInterface().startPreviewActivity();
                                return;
                            }
                            Toast.makeText(FactoryTestMenuActivity.this, "初始化失败，请退出重试", 1).show();
                            TextView tvHardwareOpen = (TextView) FactoryTestMenuActivity.this._$_findCachedViewById(C4491R.id.tvHardwareOpen);
                            Intrinsics.checkExpressionValueIsNotNull(tvHardwareOpen, "tvHardwareOpen");
                            tvHardwareOpen.setText("");
                            for (FactoryTestMenuActivity.InitState initState : FactoryTestMenuActivity.this.getStepStateList()) {
                                if (initState.getState() == StepState.Fail) {
                                    TextView tvHardwareOpen2 = (TextView) FactoryTestMenuActivity.this._$_findCachedViewById(C4491R.id.tvHardwareOpen);
                                    Intrinsics.checkExpressionValueIsNotNull(tvHardwareOpen2, "tvHardwareOpen");
                                    TextView tvHardwareOpen3 = (TextView) FactoryTestMenuActivity.this._$_findCachedViewById(C4491R.id.tvHardwareOpen);
                                    Intrinsics.checkExpressionValueIsNotNull(tvHardwareOpen3, "tvHardwareOpen");
                                    tvHardwareOpen2.setText(tvHardwareOpen3.getText().toString() + '\n' + initState.getStep() + ' ' + initState.getState() + ' ' + initState.getDescription());
                                }
                            }
                        }
                    });
                } else if (stepState == StepState.Success && hardwareOpenStep == HardwareOpenStep.Finish) {
                    HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
                    if (hdInterface == null) {
                        Intrinsics.throwNpe();
                    }
                    UByte uByte = hdInterface.getMachineInfo().getByte23Data().get(MachineInfo.Byte23Info.ESP32Version);
                    if (uByte == null) {
                        Intrinsics.throwNpe();
                    }
                    if ((uByte.getData() & 255) != 4) {
                        HardwareInterface hdInterface2 = ServiceConnectionKt.getHdInterface();
                        if (hdInterface2 == null) {
                            Intrinsics.throwNpe();
                        }
                        hdInterface2.controlDevice(DeviceType.ESP, false);
                    }
                    FactoryTestMenuActivity.this.setPowerListener();
                    FactoryTestMenuActivity.this.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$connectService$1.3
                        @Override // java.lang.Runnable
                        public final void run() {
                            Toast.makeText(FactoryTestMenuActivity.this, "初始化成功", 1).show();
                            AllTestItem.INSTANCE.initItems();
                            FactoryTestMenuActivity.this.relayout();
                        }
                    });
                }
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, newSingleThreadContext, null, new C44874(hardwareOpenStep, null), 2, null);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: FactoryTestMenuActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.factory_test.FactoryTestMenuActivity$connectService$1$4", m3970f = "FactoryTestMenuActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.factory_test.FactoryTestMenuActivity$connectService$1$4 */
            /* loaded from: classes.dex */
            public static final class C44874 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ HardwareOpenStep $step;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5132p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C44874(HardwareOpenStep hardwareOpenStep, Continuation continuation) {
                    super(2, continuation);
                    this.$step = hardwareOpenStep;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C44874 c44874 = new C44874(this.$step, completion);
                    c44874.f5132p$ = (CoroutineScope) obj;
                    return c44874;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C44874) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    if (this.$step == HardwareOpenStep.Finish) {
                        ServiceConnectionKt.setOnHardwareOpenDone((Function3) null);
                    }
                    return Unit.INSTANCE;
                }
            }
        });
        ExecutorCoroutineDispatcher executorCoroutineDispatcher = newSingleThreadContext;
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, executorCoroutineDispatcher, null, new FactoryTestMenuActivity$connectService$2(this, null), 2, null);
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, executorCoroutineDispatcher, null, new FactoryTestMenuActivity$connectService$3(context, null), 2, null);
        this.job = launch$default;
    }

    public final void createItemCardView(TestItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        createItemCardView(item.getName(), item.getStatus().getStr(), "进入测试", item.getAction());
    }

    public final void createItemCardView(final String name, String description, final String actionName, final Function0<Unit> method) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(description, "description");
        Intrinsics.checkParameterIsNotNull(actionName, "actionName");
        Intrinsics.checkParameterIsNotNull(method, "method");
        FactoryTestMenuActivity factoryTestMenuActivity = this;
        CardView cardView = new CardView(factoryTestMenuActivity);
        cardView.setRadius(10.0f);
        TextView textView = new TextView(factoryTestMenuActivity);
        textView.setTextSize(50.0f);
        textView.setText(name + " : " + description);
        textView.setTextColor(-1);
        Button button = new Button(factoryTestMenuActivity);
        button.setTextSize(50.0f);
        button.setText(actionName);
        button.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        button.setBackgroundColor(getColor(2131034162));
        button.setPadding(10, 10, 10, 10);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$createItemCardView$$inlined$apply$lambda$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = FactoryTestMenuActivity.this.TAG;
                Pdlog.m3275i(str, "click " + name);
                method.invoke();
            }
        });
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(LayoutHelperKt.getWRAP_CONTENT(), LayoutHelperKt.getWRAP_CONTENT());
        layoutParams.gravity = 16;
        layoutParams.setMargins(5, 5, 5, 5);
        textView.setLayoutParams(layoutParams);
        cardView.addView(textView);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(LayoutHelperKt.getWRAP_CONTENT(), LayoutHelperKt.getWRAP_CONTENT());
        layoutParams2.gravity = GravityCompat.END;
        layoutParams2.setMargins(5, 5, 5, 5);
        button.setLayoutParams(layoutParams2);
        cardView.addView(button);
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(C4491R.id.layoutItem);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(LayoutHelperKt.getMATCH_PARENT(), LayoutHelperKt.getWRAP_CONTENT());
        layoutParams3.setMargins(5, 5, 5, 5);
        cardView.setLayoutParams(layoutParams3);
        linearLayout.addView(cardView);
    }

    public final void onNetStatus(boolean r5) {
        Pdlog.m3273d(this.TAG, "onNetStatus boolean=" + r5);
        if (r5) {
            Button btnNetwork = (Button) _$_findCachedViewById(C4491R.id.btnNetwork);
            Intrinsics.checkExpressionValueIsNotNull(btnNetwork, "btnNetwork");
            btnNetwork.setText("网络设置：已连接");
        } else {
            Button btnNetwork2 = (Button) _$_findCachedViewById(C4491R.id.btnNetwork);
            Intrinsics.checkExpressionValueIsNotNull(btnNetwork2, "btnNetwork");
            btnNetwork2.setText("网络设置：未连接");
        }
    }

    /* compiled from: FactoryTestMenuActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/factory_test/FactoryTestMenuActivity$WifiChangeReceiver;", "Lcom/pudutech/factory_test/test_pack/NetWorkChangeEvent;", "(Lcom/pudutech/factory_test/FactoryTestMenuActivity;)V", "onNetworkChange", "", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final class WifiChangeReceiver extends NetWorkChangeEvent {
        public WifiChangeReceiver() {
        }

        @Override // com.pudutech.factory_test.test_pack.NetWorkChangeEvent
        public void onNetworkChange() {
            Pdlog.m3273d("FactoryTestMenuActivity", "net work change. isConnect=" + NetStatusUtil.isConnected(FactoryTestMenuActivity.this) + " isWifi=" + NetStatusUtil.isWifi(FactoryTestMenuActivity.this));
            if (NetStatusUtil.isConnected(FactoryTestMenuActivity.this) && NetStatusUtil.isWifi(FactoryTestMenuActivity.this)) {
                FactoryTestMenuActivity.this.onNetStatus(true);
            } else {
                FactoryTestMenuActivity.this.onNetStatus(false);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [T, com.pudutech.mirsdk.hardware.serialize.ChargeState] */
    public final void setPowerListener() {
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = ChargeState.Idle;
        byte[] bArr = {115, 9};
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface != null) {
            hdInterface.addCANDataListener(this.TAG, bArr, new FactoryTestMenuActivity$setPowerListener$1(this, intRef, objectRef));
        }
    }
}
