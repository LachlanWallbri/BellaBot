package com.pudutech.factory_test.single_test;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import com.pudutech.bumblebee.robot.aidl.serialize.Pallet;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDevice;
import com.pudutech.factory_test.C4491R;
import com.pudutech.factory_test.LayoutHelperKt;
import com.pudutech.factory_test.ServiceConnectionKt;
import com.pudutech.factory_test.single_test.TrayTestActivity;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import com.pudutech.factory_test.test_pack.TestItem;
import com.pudutech.factory_test.test_pack.TestStage;
import com.pudutech.factory_test.test_pack.TestStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: TrayTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u0001EB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u00100\u001a\u000201J\u0016\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u00062\u0006\u00105\u001a\u000203J\u000e\u00106\u001a\u0002012\u0006\u00107\u001a\u000203J\u0006\u00108\u001a\u000201J\u0012\u00109\u001a\u0002012\b\u0010:\u001a\u0004\u0018\u00010;H\u0014J\b\u0010<\u001a\u000201H\u0014J\u0006\u0010=\u001a\u000201J\u0006\u0010>\u001a\u000201J\u0006\u0010?\u001a\u000201J\u0006\u0010@\u001a\u000201J\u0006\u0010A\u001a\u000201J\u0006\u0010B\u001a\u000201J\u0006\u0010C\u001a\u000201J\u0006\u0010D\u001a\u000201R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R&\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR \u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\b\"\u0004\b,\u0010\nR\u001a\u0010-\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\b\"\u0004\b/\u0010\n¨\u0006F"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/TrayTestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "allCaseNum", "", "getAllCaseNum", "()I", "setAllCaseNum", "(I)V", "caseCnt", "getCaseCnt", "setCaseCnt", "delayJob", "Lkotlinx/coroutines/Job;", "getDelayJob", "()Lkotlinx/coroutines/Job;", "setDelayJob", "(Lkotlinx/coroutines/Job;)V", TmpConstant.DEVICE_MODEL_EVENTS, "", "Lcom/pudutech/bumblebee/robot/aidl/serialize/Pallet;", "getEvents", "()Ljava/util/List;", "setEvents", "(Ljava/util/List;)V", "lastEvent", "getLastEvent", "setLastEvent", "mTestItem", "Lcom/pudutech/factory_test/test_pack/TestItem;", "getMTestItem", "()Lcom/pudutech/factory_test/test_pack/TestItem;", "setMTestItem", "(Lcom/pudutech/factory_test/test_pack/TestItem;)V", "step", "Lcom/pudutech/factory_test/single_test/TrayTestActivity$Step;", "getStep", "()Lcom/pudutech/factory_test/single_test/TrayTestActivity$Step;", "setStep", "(Lcom/pudutech/factory_test/single_test/TrayTestActivity$Step;)V", "trayCount", "getTrayCount", "setTrayCount", "trayID", "getTrayID", "setTrayID", "FSM", "", "checkEventsSame", "", "id", "isPlaced", "delayCheckTask", "isCheckPlace", "layoutRetryPlaceAndRemove", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "removeListener", "runStepFail", "runStepIDLE", "runStepInstalled", "runStepPlaceAndRemove", "runStepSelectTrayCount", "runStepSuccess", "setListener", "Step", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class TrayTestActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private Job delayJob;
    public TestItem mTestItem;
    private final String TAG = "TrayTestActivity";
    private Step step = Step.IDLE;
    private List<List<Pallet>> events = new ArrayList();
    private List<Pallet> lastEvent = new ArrayList();
    private int caseCnt = 1;
    private int trayID = 1;
    private int allCaseNum = 6;
    private int trayCount = 3;

    /* compiled from: TrayTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/TrayTestActivity$Step;", "", "(Ljava/lang/String;I)V", "IDLE", "SELECT_TRAY_COUNT", "INSTALLED", "PLACE_REMOVE", "FAIL", "SUCCESS", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum Step {
        IDLE,
        SELECT_TRAY_COUNT,
        INSTALLED,
        PLACE_REMOVE,
        FAIL,
        SUCCESS
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Step.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Step.IDLE.ordinal()] = 1;
            $EnumSwitchMapping$0[Step.SELECT_TRAY_COUNT.ordinal()] = 2;
            $EnumSwitchMapping$0[Step.INSTALLED.ordinal()] = 3;
            $EnumSwitchMapping$0[Step.PLACE_REMOVE.ordinal()] = 4;
            $EnumSwitchMapping$0[Step.SUCCESS.ordinal()] = 5;
            $EnumSwitchMapping$0[Step.FAIL.ordinal()] = 6;
        }
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

    public final TestItem getMTestItem() {
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        return testItem;
    }

    public final void setMTestItem(TestItem testItem) {
        Intrinsics.checkParameterIsNotNull(testItem, "<set-?>");
        this.mTestItem = testItem;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        TestItem testItem;
        super.onCreate(savedInstanceState);
        if (getIntent() != null) {
            TestStage.Companion companion = TestStage.INSTANCE;
            String stringExtra = getIntent().getStringExtra(AllTestItem.INSTANCE.getEXTER_TEST_STAGE_KEY());
            Intrinsics.checkExpressionValueIsNotNull(stringExtra, "intent.getStringExtra(Al…tem.EXTER_TEST_STAGE_KEY)");
            TestStage fromValue = companion.fromValue(stringExtra);
            Iterator<TestItem> it = AllTestItem.INSTANCE.iterator();
            while (true) {
                if (!it.hasNext()) {
                    testItem = null;
                    break;
                }
                testItem = it.next();
                TestItem testItem2 = testItem;
                if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.TRAY_SENSOR) && testItem2.getStage() == fromValue) {
                    break;
                }
            }
            if (testItem == null) {
                Intrinsics.throwNpe();
            }
            this.mTestItem = testItem;
        }
        setContentView(2131427397);
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setMovementMethod(ScrollingMovementMethod.getInstance());
        Pdlog.m3275i(this.TAG, "onCreate");
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TrayTestActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = TrayTestActivity.this.TAG;
                Pdlog.m3275i(str, "click quit");
                TrayTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnErrorQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TrayTestActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = TrayTestActivity.this.TAG;
                Pdlog.m3275i(str, "click error quit");
                TrayTestActivity.this.getMTestItem().setStatus(TestStatus.FAIL);
                TrayTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TrayTestActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = TrayTestActivity.this.TAG;
                Pdlog.m3275i(str, "click reset");
                TrayTestActivity.this.setStep(TrayTestActivity.Step.IDLE);
                TrayTestActivity.this.FSM();
            }
        });
        setListener();
        FSM();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3275i(this.TAG, "onDestroy ");
        removeListener();
        Job job = this.delayJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }

    public final Step getStep() {
        return this.step;
    }

    public final void setStep(Step step) {
        Intrinsics.checkParameterIsNotNull(step, "<set-?>");
        this.step = step;
    }

    public final void FSM() {
        Pdlog.m3273d(this.TAG, "FSM. now=" + this.step);
        switch (WhenMappings.$EnumSwitchMapping$0[this.step.ordinal()]) {
            case 1:
                runStepIDLE();
                return;
            case 2:
                runStepSelectTrayCount();
                return;
            case 3:
                runStepInstalled();
                return;
            case 4:
                runStepPlaceAndRemove();
                return;
            case 5:
                runStepSuccess();
                return;
            case 6:
                runStepFail();
                return;
            default:
                return;
        }
    }

    public final void runStepIDLE() {
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        Job job = this.delayJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.caseCnt = 1;
        this.trayID = 1;
        this.step = Step.SELECT_TRAY_COUNT;
        ((LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions)).removeAllViews();
        FSM();
    }

    public final void runStepSelectTrayCount() {
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("托盘测试: \n请选择当前机器的托盘数");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("3托盘", "4托盘"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TrayTestActivity$runStepSelectTrayCount$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrayTestActivity.this.setStep(TrayTestActivity.Step.INSTALLED);
                TrayTestActivity.this.setTrayCount(3);
                TrayTestActivity.this.setAllCaseNum(6);
                TrayTestActivity.this.FSM();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TrayTestActivity$runStepSelectTrayCount$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrayTestActivity.this.setStep(TrayTestActivity.Step.INSTALLED);
                TrayTestActivity.this.setTrayCount(4);
                TrayTestActivity.this.setAllCaseNum(8);
                TrayTestActivity.this.FSM();
            }
        });
    }

    public final Job getDelayJob() {
        return this.delayJob;
    }

    public final void setDelayJob(Job job) {
        this.delayJob = job;
    }

    public final List<List<Pallet>> getEvents() {
        return this.events;
    }

    public final void setEvents(List<List<Pallet>> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.events = list;
    }

    public final List<Pallet> getLastEvent() {
        return this.lastEvent;
    }

    public final void setLastEvent(List<Pallet> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.lastEvent = list;
    }

    public final void runStepInstalled() {
        Job launch$default;
        this.lastEvent = new ArrayList();
        for (PeripheralDevice peripheralDevice : CollectionsKt.arrayListOf(PeripheralDevice.Pallet1, PeripheralDevice.Pallet2, PeripheralDevice.Pallet3, PeripheralDevice.Pallet4)) {
            Pdlog.m3273d(this.TAG, "runStepInstalled. set " + peripheralDevice + " power on");
            RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
            if (rbInterface != null) {
                rbInterface.setPeripheralDevicePower(peripheralDevice, true);
            }
        }
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("连通性测试:\n正在自动检查托盘是否正确安装, 请保持托盘均无物体, 请稍等20秒");
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new TrayTestActivity$runStepInstalled$2(this, null), 2, null);
        this.delayJob = launch$default;
    }

    public final int getCaseCnt() {
        return this.caseCnt;
    }

    public final void setCaseCnt(int i) {
        this.caseCnt = i;
    }

    public final int getTrayID() {
        return this.trayID;
    }

    public final void setTrayID(int i) {
        this.trayID = i;
    }

    public final int getAllCaseNum() {
        return this.allCaseNum;
    }

    public final void setAllCaseNum(int i) {
        this.allCaseNum = i;
    }

    public final int getTrayCount() {
        return this.trayCount;
    }

    public final void setTrayCount(int i) {
        this.trayCount = i;
    }

    public final void runStepPlaceAndRemove() {
        int i = this.caseCnt;
        if (i > this.allCaseNum) {
            this.step = Step.SUCCESS;
            FSM();
            return;
        }
        if (i % 2 == 1) {
            this.trayID = (int) Math.ceil(i / 2.0d);
            AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
            Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
            tvGuide.setText("托盘检测 ( " + this.caseCnt + " | " + this.allCaseNum + " ):\n 请在第 " + this.trayID + " 层托盘放入物体后点击 ‘确认’ ");
            LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
            Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
            ((Button) LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("确认"), 0.0f, 4, null).get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TrayTestActivity$runStepPlaceAndRemove$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TrayTestActivity.this.delayCheckTask(true);
                }
            });
            return;
        }
        AppCompatTextView tvGuide2 = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide2, "tvGuide");
        tvGuide2.setText("托盘检测 ( " + this.caseCnt + " | " + this.allCaseNum + " ):\n 请取走第 " + this.trayID + " 层托盘物体后点击 ‘确认’ ");
        LinearLayoutCompat layoutOptions2 = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions2, "layoutOptions");
        ((Button) LayoutHelperKt.layoutBtnOptions$default(layoutOptions2, this, CollectionsKt.arrayListOf("确认"), 0.0f, 4, null).get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TrayTestActivity$runStepPlaceAndRemove$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrayTestActivity.this.delayCheckTask(false);
            }
        });
    }

    public final void layoutRetryPlaceAndRemove() {
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        StringBuilder sb = new StringBuilder();
        sb.append("托盘检测 ( ");
        sb.append(this.caseCnt);
        sb.append(" | ");
        sb.append(this.allCaseNum);
        sb.append("):\n托盘检测失败\n描述：");
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        sb.append(testItem.getFailDescription());
        tvGuide.setText(sb.toString());
        TestItem testItem2 = this.mTestItem;
        if (testItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem2.setStatus(TestStatus.FAIL);
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重试"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TrayTestActivity$layoutRetryPlaceAndRemove$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((AppCompatButton) TrayTestActivity.this._$_findCachedViewById(C4491R.id.btnQuit)).callOnClick();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TrayTestActivity$layoutRetryPlaceAndRemove$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrayTestActivity.this.getMTestItem().setStatus(TestStatus.TESTING);
                TrayTestActivity.this.FSM();
            }
        });
    }

    public final void delayCheckTask(boolean isCheckPlace) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new TrayTestActivity$delayCheckTask$1(this, isCheckPlace, null), 2, null);
        this.delayJob = launch$default;
    }

    public final boolean checkEventsSame(int id, boolean isPlaced) {
        Object obj;
        Iterator<T> it = this.events.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((List) it.next()).iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it2.next();
                if (((Pallet) obj).getPalletId() == id) {
                    break;
                }
            }
            Pallet pallet = (Pallet) obj;
            if (pallet != null && pallet.getIsPlaced() != isPlaced) {
                TestItem testItem = this.mTestItem;
                if (testItem == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
                }
                testItem.setFailDescription("第 " + id + " 层托盘的检测状态不正确");
                return false;
            }
        }
        return true;
    }

    public final void runStepSuccess() {
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setStatus(TestStatus.SUCCESS);
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("托盘测试结束\n测试结果是：成功");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("完成", "下一个未测试项"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TrayTestActivity$runStepSuccess$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrayTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TrayTestActivity$runStepSuccess$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> action;
                TestItem nextUntested = AllTestItem.INSTANCE.getNextUntested(TrayTestActivity.this.getMTestItem());
                if (nextUntested != null && (action = nextUntested.getAction()) != null) {
                    action.invoke();
                }
                TrayTestActivity.this.finish();
            }
        });
    }

    public final void runStepFail() {
        Pdlog.m3274e(this.TAG, "test fail");
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setStatus(TestStatus.FAIL);
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        StringBuilder sb = new StringBuilder();
        sb.append("托盘测试结束\n测试结果是：失败\n描述：");
        TestItem testItem2 = this.mTestItem;
        if (testItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        sb.append(testItem2.getFailDescription());
        tvGuide.setText(sb.toString());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重新测试"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TrayTestActivity$runStepFail$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrayTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TrayTestActivity$runStepFail$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((AppCompatButton) TrayTestActivity.this._$_findCachedViewById(C4491R.id.btnReset)).callOnClick();
            }
        });
    }

    public final void setListener() {
        Pdlog.m3273d(this.TAG, "setListener ");
        RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
        if (rbInterface != null) {
            rbInterface.addDeliveryRobotListener(this.TAG, new TrayTestActivity$setListener$1(this));
        }
    }

    public final void removeListener() {
        Pdlog.m3273d(this.TAG, "removeListener ");
        RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
        if (rbInterface != null) {
            rbInterface.removeListener(this.TAG);
        }
    }
}
