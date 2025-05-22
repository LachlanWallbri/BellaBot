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
import com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import com.pudutech.bumblebee.robot.aidl.serialize.OpenState;
import com.pudutech.bumblebee.robot.aidl.serialize.SprayDeviceError;
import com.pudutech.bumblebee.robot.aidl.serialize.UvLampDeviceError;
import com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager;
import com.pudutech.factory_test.C4491R;
import com.pudutech.factory_test.LayoutHelperKt;
import com.pudutech.factory_test.ServiceConnectionKt;
import com.pudutech.factory_test.single_test.AtomizationModuleTestActivity;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import com.pudutech.factory_test.test_pack.TestItem;
import com.pudutech.factory_test.test_pack.TestStage;
import com.pudutech.factory_test.test_pack.TestStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.random.Random;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: AtomizationModuleTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001:\u0001<B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010-\u001a\u00020\bJ\u0012\u0010.\u001a\u00020\b2\b\u0010/\u001a\u0004\u0018\u000100H\u0014J\b\u00101\u001a\u00020\bH\u0014J\u0006\u00102\u001a\u00020\bJ\u0006\u00103\u001a\u00020\bJ\u0006\u00104\u001a\u00020\bJ\u0006\u00105\u001a\u00020\bJ\u0006\u00106\u001a\u00020\bJ\u0006\u00107\u001a\u00020\bJ\u0006\u00108\u001a\u00020\bJ\u0006\u00109\u001a\u00020\bJ\u0006\u0010:\u001a\u00020\bJ\u0006\u0010;\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R-\u0010\u0005\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007`\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001b\"\u0004\b&\u0010\u001dR\u001a\u0010'\u001a\u00020(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,¨\u0006="}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/AtomizationModuleTestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "allCases", "Ljava/util/ArrayList;", "Lkotlin/Function0;", "", "Lkotlin/collections/ArrayList;", "getAllCases", "()Ljava/util/ArrayList;", "caseNum", "", "getCaseNum", "()I", "setCaseNum", "(I)V", "delayJob", "Lkotlinx/coroutines/Job;", "getDelayJob", "()Lkotlinx/coroutines/Job;", "setDelayJob", "(Lkotlinx/coroutines/Job;)V", "liquidLevel", "", "getLiquidLevel", "()D", "setLiquidLevel", "(D)V", "mTestItem", "Lcom/pudutech/factory_test/test_pack/TestItem;", "getMTestItem", "()Lcom/pudutech/factory_test/test_pack/TestItem;", "setMTestItem", "(Lcom/pudutech/factory_test/test_pack/TestItem;)V", "sprayChamberLevel", "getSprayChamberLevel", "setSprayChamberLevel", "step", "Lcom/pudutech/factory_test/single_test/AtomizationModuleTestActivity$Step;", "getStep", "()Lcom/pudutech/factory_test/single_test/AtomizationModuleTestActivity$Step;", "setStep", "(Lcom/pudutech/factory_test/single_test/AtomizationModuleTestActivity$Step;)V", "FSM", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "removeListener", "runStepChecking", "runStepFail", "runStepIDLE", "runStepSuccess", "runStepUVOpen", "runTesting1", "runTesting2", "setCases", "setListener", "Step", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class AtomizationModuleTestActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private int caseNum;
    private Job delayJob;
    private double liquidLevel;
    public TestItem mTestItem;
    private double sprayChamberLevel;
    private final String TAG = "AtomizationModuleTestActivity";
    private final ArrayList<Function0<Unit>> allCases = new ArrayList<>();
    private Step step = Step.IDLE;

    /* compiled from: AtomizationModuleTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/AtomizationModuleTestActivity$Step;", "", "(Ljava/lang/String;I)V", "IDLE", "UVOPEN", "DELAY1", "DELAY2", "CHECKING", "FAIL", "SUCCESS", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum Step {
        IDLE,
        UVOPEN,
        DELAY1,
        DELAY2,
        CHECKING,
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
            $EnumSwitchMapping$0[Step.DELAY1.ordinal()] = 2;
            $EnumSwitchMapping$0[Step.DELAY2.ordinal()] = 3;
            $EnumSwitchMapping$0[Step.UVOPEN.ordinal()] = 4;
            $EnumSwitchMapping$0[Step.CHECKING.ordinal()] = 5;
            $EnumSwitchMapping$0[Step.SUCCESS.ordinal()] = 6;
            $EnumSwitchMapping$0[Step.FAIL.ordinal()] = 7;
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

    public final Job getDelayJob() {
        return this.delayJob;
    }

    public final void setDelayJob(Job job) {
        this.delayJob = job;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        TestItem testItem;
        Pdlog.m3275i(this.TAG, "onCreate");
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
                if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.ATOMIZATIONMODULE) && testItem2.getStage() == fromValue) {
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
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AtomizationModuleTestActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = AtomizationModuleTestActivity.this.TAG;
                Pdlog.m3275i(str, "click quit");
                AtomizationModuleTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnErrorQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AtomizationModuleTestActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = AtomizationModuleTestActivity.this.TAG;
                Pdlog.m3275i(str, "click error quit");
                AtomizationModuleTestActivity.this.getMTestItem().setStatus(TestStatus.FAIL);
                AtomizationModuleTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AtomizationModuleTestActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = AtomizationModuleTestActivity.this.TAG;
                Pdlog.m3275i(str, "click reset");
                AtomizationModuleTestActivity.this.getMTestItem().setStatus(TestStatus.TESTING);
                AtomizationModuleTestActivity.this.setStep(AtomizationModuleTestActivity.Step.IDLE);
                AtomizationModuleTestActivity.this.FSM();
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
        Job job = this.delayJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
        if (rbInterface != null) {
            rbInterface.openSprayDevice(false, false);
        }
        RobotInterface rbInterface2 = ServiceConnectionKt.getRbInterface();
        if (rbInterface2 != null) {
            rbInterface2.openUvLampDevice(false, true);
        }
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

    public final double getLiquidLevel() {
        return this.liquidLevel;
    }

    public final void setLiquidLevel(double d) {
        this.liquidLevel = d;
    }

    public final double getSprayChamberLevel() {
        return this.sprayChamberLevel;
    }

    public final void setSprayChamberLevel(double d) {
        this.sprayChamberLevel = d;
    }

    public final void FSM() {
        Pdlog.m3273d(this.TAG, "FSM. now=" + this.step);
        switch (WhenMappings.$EnumSwitchMapping$0[this.step.ordinal()]) {
            case 1:
                runStepIDLE();
                return;
            case 2:
                runTesting1();
                return;
            case 3:
                runTesting2();
                return;
            case 4:
                runStepUVOpen();
                return;
            case 5:
                runStepChecking();
                return;
            case 6:
                runStepSuccess();
                return;
            case 7:
                runStepFail();
                return;
            default:
                return;
        }
    }

    public final void runStepIDLE() {
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("雾化模组测试（1｜2）:\n请确认磁浮子已放下后，点击 ‘下一步’ ");
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ((Button) LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("下一步"), 0.0f, 4, null).get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AtomizationModuleTestActivity$runStepIDLE$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AtomizationModuleTestActivity.this.setStep(AtomizationModuleTestActivity.Step.DELAY1);
                AtomizationModuleTestActivity.this.FSM();
            }
        });
    }

    public final void runTesting1() {
        Job launch$default;
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, new ArrayList(), 0.0f, 4, null);
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("检测中...请等待10秒");
        Job job = this.delayJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AtomizationModuleTestActivity$runTesting1$1(this, null), 2, null);
        this.delayJob = launch$default;
    }

    public final void runStepUVOpen() {
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("雾化模组测试（2｜2）:\n 请确认磁浮子已抬起后，点击 ‘下一步’ ");
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ((Button) LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("下一步"), 0.0f, 4, null).get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AtomizationModuleTestActivity$runStepUVOpen$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = AtomizationModuleTestActivity.this.TAG;
                Pdlog.m3273d(str, "雾化室液位高度 " + AtomizationModuleTestActivity.this.getSprayChamberLevel());
                AtomizationModuleTestActivity.this.setStep(AtomizationModuleTestActivity.Step.DELAY2);
                AtomizationModuleTestActivity.this.FSM();
            }
        });
    }

    public final void runTesting2() {
        Job launch$default;
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, new ArrayList(), 0.0f, 4, null);
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("检测中...请等待10秒");
        Job job = this.delayJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AtomizationModuleTestActivity$runTesting2$1(this, null), 2, null);
        this.delayJob = launch$default;
    }

    public final void runStepChecking() {
        Pdlog.m3273d(this.TAG, "runStepChecking left size=" + this.allCases.size());
        if (this.allCases.size() == 0) {
            this.step = Step.SUCCESS;
            FSM();
            return;
        }
        int nextInt = Random.INSTANCE.nextInt(this.allCases.size());
        Pdlog.m3273d(this.TAG, "random next. size=" + this.allCases.size() + " next=" + nextInt);
        Function0<Unit> function0 = this.allCases.get(nextInt);
        Intrinsics.checkExpressionValueIsNotNull(function0, "allCases[num]");
        this.allCases.remove(nextInt);
        function0.invoke();
    }

    public final void runStepSuccess() {
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setStatus(TestStatus.SUCCESS);
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("雾化模组测试结束\n测试结果是：成功");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList<Button> layoutBtnOptions = LayoutHelperKt.layoutBtnOptions(layoutOptions, this, CollectionsKt.arrayListOf("完成", "下一个未测试项"), 25.0f);
        layoutBtnOptions.get(0).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AtomizationModuleTestActivity$runStepSuccess$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AtomizationModuleTestActivity.this.finish();
            }
        });
        layoutBtnOptions.get(1).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AtomizationModuleTestActivity$runStepSuccess$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> action;
                TestItem nextUntested = AllTestItem.INSTANCE.getNextUntested(AtomizationModuleTestActivity.this.getMTestItem());
                if (nextUntested != null && (action = nextUntested.getAction()) != null) {
                    action.invoke();
                }
                AtomizationModuleTestActivity.this.finish();
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
        sb.append("雾化模组测试结束\n测试结果是：失败\n描述：");
        TestItem testItem2 = this.mTestItem;
        if (testItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        sb.append(testItem2.getFailDescription());
        tvGuide.setText(sb.toString());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList<Button> layoutBtnOptions = LayoutHelperKt.layoutBtnOptions(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重新测试"), 30.0f);
        layoutBtnOptions.get(0).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AtomizationModuleTestActivity$runStepFail$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AtomizationModuleTestActivity.this.finish();
            }
        });
        layoutBtnOptions.get(1).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AtomizationModuleTestActivity$runStepFail$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((AppCompatButton) AtomizationModuleTestActivity.this._$_findCachedViewById(C4491R.id.btnReset)).callOnClick();
            }
        });
    }

    public final void setListener() {
        Pdlog.m3273d(this.TAG, ServiceConnectionKt.getRbInterface() == null ? "rbInterface 空指针" : "rbInterface 非空");
        RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
        if (rbInterface != null) {
            rbInterface.addDisinfectionRobotListener(this.TAG, new IDisinfectionRobotListener.Stub() { // from class: com.pudutech.factory_test.single_test.AtomizationModuleTestActivity$setListener$1
                @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
                public void onSprayLiquidStatus(boolean p0) {
                }

                @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
                public void onUvLampPlateOpenState(OpenState p0) {
                }

                @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
                public void onLiquidLevelChange(double p0) {
                    String str;
                    str = AtomizationModuleTestActivity.this.TAG;
                    Pdlog.m3273d(str, "onLiquidLevelChange " + p0);
                    AtomizationModuleTestActivity.this.setLiquidLevel(p0);
                }

                @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
                public void onSprayChamberLevelChange(double p0) {
                    String str;
                    str = AtomizationModuleTestActivity.this.TAG;
                    Pdlog.m3273d(str, "雾化室液位高度onSprayChamberLevelChange " + p0);
                    AtomizationModuleTestActivity.this.setSprayChamberLevel(p0);
                }

                @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
                public void onSprayDiveceError(SprayDeviceError[] p0) {
                    String str;
                    str = AtomizationModuleTestActivity.this.TAG;
                    Pdlog.m3273d(str, "onSprayDiveceError " + p0);
                }

                @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
                public void onSprayDiveceOpen(boolean p0) {
                    String str;
                    str = AtomizationModuleTestActivity.this.TAG;
                    Pdlog.m3273d(str, "onSprayDiveceOpen " + p0);
                }

                @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
                public void onSpringOpenStatus(boolean p0) {
                    String str;
                    str = AtomizationModuleTestActivity.this.TAG;
                    Pdlog.m3273d(str, "onSpringOpenStatus " + p0);
                }

                @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
                public void onUvLampDeviceError(UvLampDeviceError[] p0) {
                    String str;
                    str = AtomizationModuleTestActivity.this.TAG;
                    Pdlog.m3273d(str, "onUvLampDeviceError " + p0);
                }

                @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
                public void onUvLampDeviceOpen(boolean p0) {
                    String str;
                    str = AtomizationModuleTestActivity.this.TAG;
                    Pdlog.m3273d(str, "onUvLampDeviceOpen " + p0);
                }
            });
        }
    }

    public final void removeListener() {
        DisinfectionModuleManager.INSTANCE.removeListener(this.TAG);
    }

    public final void setCases() {
        final int i = 0;
        Pdlog.m3273d(this.TAG, "setCases");
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        this.allCases.clear();
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -1;
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        for (Object obj : LayoutHelperKt.layoutBtnOptions(layoutOptions, this, CollectionsKt.arrayListOf("有", "没有"), 30.0f)) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ((Button) obj).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.AtomizationModuleTestActivity$setCases$$inlined$forEachIndexed$lambda$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    String str;
                    str = this.TAG;
                    Pdlog.m3275i(str, "click option. index=" + i + " when rightAnswerIndex=" + intRef.element);
                    this.getMTestItem().setStatus(TestStatus.TESTING);
                    if (intRef.element != i) {
                        this.setStep(AtomizationModuleTestActivity.Step.FAIL);
                        this.getMTestItem().setFailDescription("选择选项错误，可能是消毒或紫外模块有异常");
                    }
                    this.FSM();
                }
            });
            i = i2;
        }
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.AtomizationModuleTestActivity$setCases$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                int caseNum = AtomizationModuleTestActivity.this.getCaseNum() - AtomizationModuleTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) AtomizationModuleTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("消毒测试 ( " + caseNum + " | " + AtomizationModuleTestActivity.this.getCaseNum() + " )：\n请稍等3秒钟,确认紫外灯是否正常打开");
                intRef.element = 0;
                RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
                if (rbInterface != null) {
                    rbInterface.openUvLampDevice(true, true);
                }
            }
        });
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.AtomizationModuleTestActivity$setCases$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                int caseNum = AtomizationModuleTestActivity.this.getCaseNum() - AtomizationModuleTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) AtomizationModuleTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("消毒测试 ( " + caseNum + " | " + AtomizationModuleTestActivity.this.getCaseNum() + " )：\n请稍等3秒钟,确认紫外灯是否正常打开");
                intRef.element = 1;
                RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
                if (rbInterface != null) {
                    rbInterface.openUvLampDevice(false, false);
                }
                str = AtomizationModuleTestActivity.this.TAG;
                Pdlog.m3275i(str, "DisinfectionModuleManager.boot(");
            }
        });
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.AtomizationModuleTestActivity$setCases$4
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                int caseNum = AtomizationModuleTestActivity.this.getCaseNum() - AtomizationModuleTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) AtomizationModuleTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("消毒测试 ( " + caseNum + " | " + AtomizationModuleTestActivity.this.getCaseNum() + " )：\n请稍等3秒钟,确认紫外设备是否正常旋转出来");
                intRef.element = 1;
                RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
                if (rbInterface != null) {
                    rbInterface.openUvLampDevice(false, false);
                }
            }
        });
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.AtomizationModuleTestActivity$setCases$5
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                int caseNum = AtomizationModuleTestActivity.this.getCaseNum() - AtomizationModuleTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) AtomizationModuleTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("消毒测试 ( " + caseNum + " | " + AtomizationModuleTestActivity.this.getCaseNum() + " )：\n请稍等3秒钟,确认紫外设备是否正常旋转出来");
                intRef.element = 0;
                RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
                if (rbInterface != null) {
                    rbInterface.openUvLampDevice(true, false);
                }
            }
        });
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.AtomizationModuleTestActivity$setCases$6
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                int caseNum = AtomizationModuleTestActivity.this.getCaseNum() - AtomizationModuleTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) AtomizationModuleTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("消毒测试 ( " + caseNum + " | " + AtomizationModuleTestActivity.this.getCaseNum() + " )：\n请稍等3秒钟,确认设备是否能正常喷雾");
                intRef.element = 0;
                RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
                if (rbInterface != null) {
                    rbInterface.openSprayDevice(true, true);
                }
            }
        });
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.AtomizationModuleTestActivity$setCases$7
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                int caseNum = AtomizationModuleTestActivity.this.getCaseNum() - AtomizationModuleTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) AtomizationModuleTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("消毒测试 ( " + caseNum + " | " + AtomizationModuleTestActivity.this.getCaseNum() + " )：\n请稍等3秒钟,确认设备是否能正常喷雾");
                intRef.element = 1;
                RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
                if (rbInterface != null) {
                    rbInterface.openSprayDevice(false, true);
                }
            }
        });
        this.caseNum = this.allCases.size();
    }
}
