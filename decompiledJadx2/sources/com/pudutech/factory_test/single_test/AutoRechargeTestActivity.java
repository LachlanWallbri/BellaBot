package com.pudutech.factory_test.single_test;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.utils.SystemTool;
import com.pudutech.factory_test.C4491R;
import com.pudutech.factory_test.LayoutHelperKt;
import com.pudutech.factory_test.ServiceConnectionKt;
import com.pudutech.factory_test.single_test.AutoRechargeTestActivity;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import com.pudutech.factory_test.test_pack.TestItem;
import com.pudutech.factory_test.test_pack.TestStage;
import com.pudutech.factory_test.test_pack.TestStatus;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.ICANData;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import okhttp3.internal.Util;

/* compiled from: AutoRechargeTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0001@B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u00105\u001a\u00020\bJ\u0012\u00106\u001a\u00020\b2\b\u00107\u001a\u0004\u0018\u000108H\u0014J\b\u00109\u001a\u00020\bH\u0014J\u0006\u0010:\u001a\u00020\bJ\u0006\u0010;\u001a\u00020\bJ\u0006\u0010<\u001a\u00020\bJ\u0006\u0010=\u001a\u00020\bJ\u0006\u0010>\u001a\u00020\bJ\u0006\u0010?\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R-\u0010\u0005\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007`\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020%X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\"\u0010*\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010/\u001a\u000200X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104¨\u0006A"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/AutoRechargeTestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "allCases", "Ljava/util/ArrayList;", "Lkotlin/Function0;", "", "Lkotlin/collections/ArrayList;", "getAllCases", "()Ljava/util/ArrayList;", "caseNum", "", "getCaseNum", "()I", "setCaseNum", "(I)V", "delayJob", "Lkotlinx/coroutines/Job;", "getDelayJob", "()Lkotlinx/coroutines/Job;", "setDelayJob", "(Lkotlinx/coroutines/Job;)V", "hasCharging", "", "getHasCharging", "()Z", "setHasCharging", "(Z)V", "lastChargeState", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "getLastChargeState", "()Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "setLastChargeState", "(Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;)V", "mTestItem", "Lcom/pudutech/factory_test/test_pack/TestItem;", "getMTestItem", "()Lcom/pudutech/factory_test/test_pack/TestItem;", "setMTestItem", "(Lcom/pudutech/factory_test/test_pack/TestItem;)V", "onAutoRechargeCharging", "getOnAutoRechargeCharging", "()Lkotlin/jvm/functions/Function0;", "setOnAutoRechargeCharging", "(Lkotlin/jvm/functions/Function0;)V", "step", "Lcom/pudutech/factory_test/single_test/AutoRechargeTestActivity$Step;", "getStep", "()Lcom/pudutech/factory_test/single_test/AutoRechargeTestActivity$Step;", "setStep", "(Lcom/pudutech/factory_test/single_test/AutoRechargeTestActivity$Step;)V", "FSM", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "removeListener", "runStepChargingTrue", "runStepFail", "runStepIDLE", "runStepSuccess", "setListener", "Step", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class AutoRechargeTestActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private int caseNum;
    private Job delayJob;
    private boolean hasCharging;
    private ChargeState lastChargeState;
    public TestItem mTestItem;
    private Function0<Unit> onAutoRechargeCharging;
    private final String TAG = "AutoRechargeTestActivity";
    private final ArrayList<Function0<Unit>> allCases = new ArrayList<>();
    private Step step = Step.IDLE;

    /* compiled from: AutoRechargeTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/AutoRechargeTestActivity$Step;", "", "(Ljava/lang/String;I)V", "IDLE", "CHARGING_TRUE", "FAIL", "SUCCESS", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum Step {
        IDLE,
        CHARGING_TRUE,
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
            $EnumSwitchMapping$0[Step.CHARGING_TRUE.ordinal()] = 2;
            $EnumSwitchMapping$0[Step.SUCCESS.ordinal()] = 3;
            $EnumSwitchMapping$0[Step.FAIL.ordinal()] = 4;
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
                if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.AUTO_RECHARGE) && testItem2.getStage() == fromValue) {
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
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AutoRechargeTestActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = AutoRechargeTestActivity.this.TAG;
                Pdlog.m3275i(str, "click quit");
                AutoRechargeTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnErrorQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AutoRechargeTestActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = AutoRechargeTestActivity.this.TAG;
                Pdlog.m3275i(str, "click error quit");
                AutoRechargeTestActivity.this.getMTestItem().setStatus(TestStatus.FAIL);
                AutoRechargeTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AutoRechargeTestActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = AutoRechargeTestActivity.this.TAG;
                Pdlog.m3275i(str, "click reset");
                AutoRechargeTestActivity.this.setStep(AutoRechargeTestActivity.Step.IDLE);
                AutoRechargeTestActivity.this.FSM();
            }
        });
        setListener();
        FSM();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        removeListener();
    }

    public final ArrayList<Function0<Unit>> getAllCases() {
        return this.allCases;
    }

    public final int getCaseNum() {
        return this.caseNum;
    }

    public final void setCaseNum(int i) {
        this.caseNum = i;
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
        int i = WhenMappings.$EnumSwitchMapping$0[this.step.ordinal()];
        if (i == 1) {
            runStepIDLE();
            return;
        }
        if (i == 2) {
            runStepChargingTrue();
        } else if (i == 3) {
            runStepSuccess();
        } else {
            if (i != 4) {
                return;
            }
            runStepFail();
        }
    }

    public final boolean getHasCharging() {
        return this.hasCharging;
    }

    public final void setHasCharging(boolean z) {
        this.hasCharging = z;
    }

    public final void runStepIDLE() {
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("自动回充测试:\n请确认机器人没有连接充电桩后点击 ‘开始测试’  ");
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        this.hasCharging = false;
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ((Button) LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("开始测试"), 0.0f, 4, null).get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AutoRechargeTestActivity$runStepIDLE$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (AutoRechargeTestActivity.this.getLastChargeState() == ChargeState.CharingUsePile) {
                    AutoRechargeTestActivity.this.setStep(AutoRechargeTestActivity.Step.FAIL);
                    AutoRechargeTestActivity.this.getMTestItem().setFailDescription("测试前请断开与充电桩的连接");
                } else {
                    AutoRechargeTestActivity.this.setStep(AutoRechargeTestActivity.Step.CHARGING_TRUE);
                }
                AutoRechargeTestActivity.this.FSM();
            }
        });
    }

    public final Job getDelayJob() {
        return this.delayJob;
    }

    public final void setDelayJob(Job job) {
        this.delayJob = job;
    }

    public final Function0<Unit> getOnAutoRechargeCharging() {
        return this.onAutoRechargeCharging;
    }

    public final void setOnAutoRechargeCharging(Function0<Unit> function0) {
        this.onAutoRechargeCharging = function0;
    }

    public final void runStepChargingTrue() {
        Job launch$default;
        ((LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions)).removeAllViews();
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("自动回充测试:\n请在20秒内手动将机器人推向充电桩，使机器人处于充电状态");
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        this.hasCharging = false;
        this.onAutoRechargeCharging = new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.AutoRechargeTestActivity$runStepChargingTrue$1
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
                AutoRechargeTestActivity.this.setOnAutoRechargeCharging((Function0) null);
                if (AutoRechargeTestActivity.this.getHasCharging() && AutoRechargeTestActivity.this.getLastChargeState() == ChargeState.CharingUsePile) {
                    AutoRechargeTestActivity.this.setStep(AutoRechargeTestActivity.Step.SUCCESS);
                    Job delayJob = AutoRechargeTestActivity.this.getDelayJob();
                    if (delayJob != null) {
                        Job.DefaultImpls.cancel$default(delayJob, (CancellationException) null, 1, (Object) null);
                    }
                    AutoRechargeTestActivity.this.FSM();
                }
            }
        };
        Job job = this.delayJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AutoRechargeTestActivity$runStepChargingTrue$2(this, null), 2, null);
        this.delayJob = launch$default;
    }

    public final void runStepSuccess() {
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setStatus(TestStatus.SUCCESS);
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("自动回充测试结束\n测试结果是：成功");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList<Button> layoutBtnOptions = LayoutHelperKt.layoutBtnOptions(layoutOptions, this, CollectionsKt.arrayListOf("完成", "下一个未测试项"), 25.0f);
        layoutBtnOptions.get(0).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AutoRechargeTestActivity$runStepSuccess$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AutoRechargeTestActivity.this.finish();
            }
        });
        layoutBtnOptions.get(1).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AutoRechargeTestActivity$runStepSuccess$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> action;
                TestItem nextUntested = AllTestItem.INSTANCE.getNextUntested(AutoRechargeTestActivity.this.getMTestItem());
                if (nextUntested != null && (action = nextUntested.getAction()) != null) {
                    action.invoke();
                }
                AutoRechargeTestActivity.this.finish();
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
        sb.append("自动回充测试结束\n测试结果是：失败\n描述：");
        TestItem testItem2 = this.mTestItem;
        if (testItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        sb.append(testItem2.getFailDescription());
        tvGuide.setText(sb.toString());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList<Button> layoutBtnOptions = LayoutHelperKt.layoutBtnOptions(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重新测试"), 30.0f);
        layoutBtnOptions.get(0).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AutoRechargeTestActivity$runStepFail$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AutoRechargeTestActivity.this.finish();
            }
        });
        layoutBtnOptions.get(1).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AutoRechargeTestActivity$runStepFail$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((AppCompatButton) AutoRechargeTestActivity.this._$_findCachedViewById(C4491R.id.btnReset)).callOnClick();
            }
        });
    }

    public final ChargeState getLastChargeState() {
        return this.lastChargeState;
    }

    public final void setLastChargeState(ChargeState chargeState) {
        this.lastChargeState = chargeState;
    }

    public final void setListener() {
        byte[] bArr = {(byte) 163, 115};
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface != null) {
            hdInterface.addCANDataListener(this.TAG, bArr, new ICANData.Stub() { // from class: com.pudutech.factory_test.single_test.AutoRechargeTestActivity$setListener$1
                @Override // com.pudutech.mirsdk.hardware.ICANData
                public void onData(int p0, byte[] p1) {
                    String str;
                    String str2;
                    String str3;
                    Function0<Unit> onAutoRechargeCharging;
                    ChargeState m4433fromValue7apg3OU;
                    str = AutoRechargeTestActivity.this.TAG;
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("0x");
                    sb.append(Util.toHexString(p0));
                    sb.append(",p1:");
                    SystemTool systemTool = SystemTool.INSTANCE;
                    if (p1 == null) {
                        Intrinsics.throwNpe();
                    }
                    sb.append(systemTool.toHexString(p1));
                    objArr[0] = sb.toString();
                    Pdlog.m3276v(str, objArr);
                    if (UByte.m4528constructorimpl((byte) p0) == UByte.m4528constructorimpl((byte) 163)) {
                        AutoRechargeTestActivity.this.setHasCharging(true);
                    }
                    str2 = AutoRechargeTestActivity.this.TAG;
                    Pdlog.m3276v(str2, "p0 " + p0 + ", _ = 115");
                    if (p0 == 115 && AutoRechargeTestActivity.this.getLastChargeState() != (m4433fromValue7apg3OU = ChargeState.INSTANCE.m4433fromValue7apg3OU(UByte.m4528constructorimpl(p1[1])))) {
                        AutoRechargeTestActivity.this.setLastChargeState(m4433fromValue7apg3OU);
                    }
                    str3 = AutoRechargeTestActivity.this.TAG;
                    Pdlog.m3276v(str3, "lastChargeState:" + AutoRechargeTestActivity.this.getLastChargeState() + " hasCharging:" + AutoRechargeTestActivity.this.getHasCharging());
                    if (AutoRechargeTestActivity.this.getHasCharging() && AutoRechargeTestActivity.this.getLastChargeState() == ChargeState.CharingUsePile && (onAutoRechargeCharging = AutoRechargeTestActivity.this.getOnAutoRechargeCharging()) != null) {
                        onAutoRechargeCharging.invoke();
                    }
                }
            });
        }
    }

    public final void removeListener() {
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface != null) {
            hdInterface.removeListener(this.TAG);
        }
    }
}
