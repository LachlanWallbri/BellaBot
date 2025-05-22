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
import com.pudutech.factory_test.C4491R;
import com.pudutech.factory_test.LayoutHelperKt;
import com.pudutech.factory_test.ServiceConnectionKt;
import com.pudutech.factory_test.single_test.IMUTestActivity;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import com.pudutech.factory_test.test_pack.TestItem;
import com.pudutech.factory_test.test_pack.TestStage;
import com.pudutech.factory_test.test_pack.TestStatus;
import com.pudutech.mirsdk.hardware.Gyroscope;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.ICANData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

/* compiled from: IMUTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001:\u0002IJB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u00105\u001a\u000206J\u0006\u00107\u001a\u000208J\u0006\u00109\u001a\u000208J\u0012\u0010:\u001a\u0002062\b\u0010;\u001a\u0004\u0018\u00010<H\u0014J\b\u0010=\u001a\u000206H\u0014J\u0006\u0010>\u001a\u000206J\u0006\u0010?\u001a\u000206J\u0006\u0010@\u001a\u000206J\u0006\u0010A\u001a\u000206J\u0006\u0010B\u001a\u000206J\u0006\u0010C\u001a\u000206J\u0006\u0010D\u001a\u000206J\u000e\u0010E\u001a\u0002062\u0006\u0010F\u001a\u00020\bJ\n\u0010G\u001a\u00020\b*\u00020\u0004J*\u0010H\u001a\u00020\b*\"\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0!j\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b`\"R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR!\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u001cj\b\u0012\u0004\u0012\u00020\u0010`\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR1\u0010 \u001a\"\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0!j\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b`\"¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020&X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u0011\u0010+\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0006R\u0011\u0010-\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0006R\u001a\u0010/\u001a\u000200X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104¨\u0006K"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/IMUTestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "PERIOD_s", "", "getPERIOD_s", "()D", "TAG", "", "delayJob", "Lkotlinx/coroutines/Job;", "getDelayJob", "()Lkotlinx/coroutines/Job;", "setDelayJob", "(Lkotlinx/coroutines/Job;)V", "drift", "Lcom/pudutech/factory_test/single_test/IMUTestActivity$XYZ;", "getDrift", "()Lcom/pudutech/factory_test/single_test/IMUTestActivity$XYZ;", "setDrift", "(Lcom/pudutech/factory_test/single_test/IMUTestActivity$XYZ;)V", "gyro", "Lcom/pudutech/mirsdk/hardware/Gyroscope;", "getGyro", "()Lcom/pudutech/mirsdk/hardware/Gyroscope;", "setGyro", "(Lcom/pudutech/mirsdk/hardware/Gyroscope;)V", "history", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getHistory", "()Ljava/util/ArrayList;", "keyInfo", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "getKeyInfo", "()Ljava/util/LinkedHashMap;", "mTestItem", "Lcom/pudutech/factory_test/test_pack/TestItem;", "getMTestItem", "()Lcom/pudutech/factory_test/test_pack/TestItem;", "setMTestItem", "(Lcom/pudutech/factory_test/test_pack/TestItem;)V", "maxDiff", "getMaxDiff", "maxSpeed", "getMaxSpeed", "step", "Lcom/pudutech/factory_test/single_test/IMUTestActivity$Step;", "getStep", "()Lcom/pudutech/factory_test/single_test/IMUTestActivity$Step;", "setStep", "(Lcom/pudutech/factory_test/single_test/IMUTestActivity$Step;)V", "FSM", "", "checkDriftOK", "", "checkRotationOK", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "removeListener", "runStepFail", "runStepIDLE", "runStepNoMove", "runStepSuccess", "runStepTurnAround", "setListener", "showKeyInfo", "string", "toDegreeString", "toStr", "Step", "XYZ", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class IMUTestActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private Job delayJob;
    public TestItem mTestItem;
    private final String TAG = "IMUTestActivity";
    private Step step = Step.IDLE;
    private Gyroscope gyro = new Gyroscope();
    private XYZ drift = new XYZ(0.0d, 0.0d, 0.0d);
    private final ArrayList<XYZ> history = new ArrayList<>();
    private final double PERIOD_s = 0.025d;
    private final double maxSpeed = Math.toRadians(20.0d);
    private final double maxDiff = Math.toRadians(1.0d);
    private final LinkedHashMap<String, String> keyInfo = new LinkedHashMap<>();

    /* compiled from: IMUTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/IMUTestActivity$Step;", "", "(Ljava/lang/String;I)V", "IDLE", "NO_MOVE", "TURN_AROUND", "FAIL", "SUCCESS", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum Step {
        IDLE,
        NO_MOVE,
        TURN_AROUND,
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
            $EnumSwitchMapping$0[Step.NO_MOVE.ordinal()] = 2;
            $EnumSwitchMapping$0[Step.TURN_AROUND.ordinal()] = 3;
            $EnumSwitchMapping$0[Step.SUCCESS.ordinal()] = 4;
            $EnumSwitchMapping$0[Step.FAIL.ordinal()] = 5;
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
                if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.IMU) && testItem2.getStage() == fromValue) {
                    break;
                }
            }
            if (testItem == null) {
                Intrinsics.throwNpe();
            }
            this.mTestItem = testItem;
        }
        setContentView(2131427397);
        Pdlog.m3275i(this.TAG, "onCreate");
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setMovementMethod(ScrollingMovementMethod.getInstance());
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.IMUTestActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = IMUTestActivity.this.TAG;
                Pdlog.m3275i(str, "click quit");
                IMUTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnErrorQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.IMUTestActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = IMUTestActivity.this.TAG;
                Pdlog.m3275i(str, "click error quit");
                IMUTestActivity.this.getMTestItem().setStatus(TestStatus.FAIL);
                IMUTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.IMUTestActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = IMUTestActivity.this.TAG;
                Pdlog.m3275i(str, "click reset");
                IMUTestActivity.this.setStep(IMUTestActivity.Step.IDLE);
                IMUTestActivity.this.FSM();
            }
        });
        FSM();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3275i(this.TAG, "onDestroy ");
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
        int i = WhenMappings.$EnumSwitchMapping$0[this.step.ordinal()];
        if (i == 1) {
            runStepIDLE();
            return;
        }
        if (i == 2) {
            runStepNoMove();
            return;
        }
        if (i == 3) {
            runStepTurnAround();
        } else if (i == 4) {
            runStepSuccess();
        } else {
            if (i != 5) {
                return;
            }
            runStepFail();
        }
    }

    public final void runStepIDLE() {
        this.keyInfo.clear();
        AppCompatTextView tvDebugInfo = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvDebugInfo);
        Intrinsics.checkExpressionValueIsNotNull(tvDebugInfo, "tvDebugInfo");
        tvDebugInfo.setText("");
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        removeListener();
        this.gyro = new Gyroscope();
        this.drift = new XYZ(0.0d, 0.0d, 0.0d);
        this.history.clear();
        Job job = this.delayJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("陀螺仪测试( 1 | 2 )：\n点击 ‘开始’ 之后保持机器人静止");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ((Button) LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("开始"), 0.0f, 4, null).get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.IMUTestActivity$runStepIDLE$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IMUTestActivity.this.setStep(IMUTestActivity.Step.NO_MOVE);
                IMUTestActivity.this.FSM();
            }
        });
    }

    public final Job getDelayJob() {
        return this.delayJob;
    }

    public final void setDelayJob(Job job) {
        this.delayJob = job;
    }

    public final void runStepNoMove() {
        Job launch$default;
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("陀螺仪测试( 1 | 2 )：\n请保持机器人静止5秒钟");
        ((LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions)).removeAllViews();
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new IMUTestActivity$runStepNoMove$1(this, null), 2, null);
        this.delayJob = launch$default;
    }

    public final void runStepTurnAround() {
        removeListener();
        this.gyro = new Gyroscope();
        this.history.clear();
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("陀螺仪测试( 2 | 2 ): \n请逆时针旋转机器人一圈后点击 ‘结束’");
        setListener();
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("重新旋转", "结束"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.IMUTestActivity$runStepTurnAround$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IMUTestActivity.this.FSM();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.IMUTestActivity$runStepTurnAround$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IMUTestActivity.Step step;
                IMUTestActivity.this.removeListener();
                IMUTestActivity iMUTestActivity = IMUTestActivity.this;
                if (iMUTestActivity.checkRotationOK()) {
                    step = IMUTestActivity.Step.SUCCESS;
                } else {
                    step = IMUTestActivity.Step.FAIL;
                }
                iMUTestActivity.setStep(step);
                IMUTestActivity.this.FSM();
            }
        });
    }

    public final void runStepSuccess() {
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setStatus(TestStatus.SUCCESS);
        showKeyInfo(toStr(this.keyInfo));
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("陀螺仪测试结束\n测试结果是：成功");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("完成", "下一个未测试项"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.IMUTestActivity$runStepSuccess$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IMUTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.IMUTestActivity$runStepSuccess$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> action;
                TestItem nextUntested = AllTestItem.INSTANCE.getNextUntested(IMUTestActivity.this.getMTestItem());
                if (nextUntested != null && (action = nextUntested.getAction()) != null) {
                    action.invoke();
                }
                IMUTestActivity.this.finish();
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
        showKeyInfo(toStr(this.keyInfo));
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        StringBuilder sb = new StringBuilder();
        sb.append("陀螺仪测试结束\n测试结果是：失败\n描述：");
        TestItem testItem2 = this.mTestItem;
        if (testItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        sb.append(testItem2.getFailDescription());
        tvGuide.setText(sb.toString());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重新测试"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.IMUTestActivity$runStepFail$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IMUTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.IMUTestActivity$runStepFail$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((AppCompatButton) IMUTestActivity.this._$_findCachedViewById(C4491R.id.btnReset)).callOnClick();
            }
        });
    }

    /* compiled from: IMUTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/IMUTestActivity$XYZ;", "", "x", "", "y", CompressorStreamFactory.f8930Z, "(DDD)V", "getX", "()D", "setX", "(D)V", "getY", "setY", "getZ", "setZ", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final /* data */ class XYZ {
        private double x;
        private double y;
        private double z;

        public static /* synthetic */ XYZ copy$default(XYZ xyz, double d, double d2, double d3, int i, Object obj) {
            if ((i & 1) != 0) {
                d = xyz.x;
            }
            double d4 = d;
            if ((i & 2) != 0) {
                d2 = xyz.y;
            }
            double d5 = d2;
            if ((i & 4) != 0) {
                d3 = xyz.z;
            }
            return xyz.copy(d4, d5, d3);
        }

        /* renamed from: component1, reason: from getter */
        public final double getX() {
            return this.x;
        }

        /* renamed from: component2, reason: from getter */
        public final double getY() {
            return this.y;
        }

        /* renamed from: component3, reason: from getter */
        public final double getZ() {
            return this.z;
        }

        public final XYZ copy(double x, double y, double z) {
            return new XYZ(x, y, z);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof XYZ)) {
                return false;
            }
            XYZ xyz = (XYZ) other;
            return Double.compare(this.x, xyz.x) == 0 && Double.compare(this.y, xyz.y) == 0 && Double.compare(this.z, xyz.z) == 0;
        }

        public int hashCode() {
            long doubleToLongBits = Double.doubleToLongBits(this.x);
            long doubleToLongBits2 = Double.doubleToLongBits(this.y);
            int i = ((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31;
            long doubleToLongBits3 = Double.doubleToLongBits(this.z);
            return i + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)));
        }

        public String toString() {
            return "XYZ(x=" + this.x + ", y=" + this.y + ", z=" + this.z + ")";
        }

        public XYZ(double d, double d2, double d3) {
            this.x = d;
            this.y = d2;
            this.z = d3;
        }

        public final double getX() {
            return this.x;
        }

        public final double getY() {
            return this.y;
        }

        public final double getZ() {
            return this.z;
        }

        public final void setX(double d) {
            this.x = d;
        }

        public final void setY(double d) {
            this.y = d;
        }

        public final void setZ(double d) {
            this.z = d;
        }
    }

    public final Gyroscope getGyro() {
        return this.gyro;
    }

    public final void setGyro(Gyroscope gyroscope) {
        Intrinsics.checkParameterIsNotNull(gyroscope, "<set-?>");
        this.gyro = gyroscope;
    }

    public final XYZ getDrift() {
        return this.drift;
    }

    public final void setDrift(XYZ xyz) {
        Intrinsics.checkParameterIsNotNull(xyz, "<set-?>");
        this.drift = xyz;
    }

    public final ArrayList<XYZ> getHistory() {
        return this.history;
    }

    public final void setListener() {
        byte[] bArr = {16};
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface != null) {
            hdInterface.addCANDataListener(this.TAG, bArr, new ICANData.Stub() { // from class: com.pudutech.factory_test.single_test.IMUTestActivity$setListener$1
                @Override // com.pudutech.mirsdk.hardware.ICANData
                public void onData(int id, byte[] data) {
                    String str;
                    if (data == null) {
                        Intrinsics.throwNpe();
                    }
                    byte[] copyOf = Arrays.copyOf(data, data.length);
                    Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                    byte[] m4572constructorimpl = UByteArray.m4572constructorimpl(copyOf);
                    if (IMUTestActivity.this.getHistory().size() > (1 / IMUTestActivity.this.getPERIOD_s()) * 60) {
                        str = IMUTestActivity.this.TAG;
                        Pdlog.m3277w(str, "spent too much time to rotate. return");
                    } else {
                        IMUTestActivity.this.getGyro().update((short) (((UByteArray.m4577getimpl(m4572constructorimpl, 1) & 255) << 8) | (UByteArray.m4577getimpl(m4572constructorimpl, 2) & 255)), (short) (((UByteArray.m4577getimpl(m4572constructorimpl, 3) & 255) << 8) | (UByteArray.m4577getimpl(m4572constructorimpl, 4) & 255)), (short) ((UByteArray.m4577getimpl(m4572constructorimpl, 6) & 255) | ((UByteArray.m4577getimpl(m4572constructorimpl, 5) & 255) << 8)));
                        IMUTestActivity.this.getHistory().add(new IMUTestActivity.XYZ(IMUTestActivity.this.getGyro().get_last().getX(), IMUTestActivity.this.getGyro().get_last().getY(), IMUTestActivity.this.getGyro().get_last().getZ()));
                    }
                }
            });
        }
    }

    public final void removeListener() {
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface != null) {
            hdInterface.removeCANDataListener(this.TAG);
        }
    }

    public final double getPERIOD_s() {
        return this.PERIOD_s;
    }

    public final double getMaxSpeed() {
        return this.maxSpeed;
    }

    public final double getMaxDiff() {
        return this.maxDiff;
    }

    public final boolean checkDriftOK() {
        char c = 0;
        Pdlog.m3273d(this.TAG, "checkDriftOK ");
        this.keyInfo.put("陀螺仪数量", String.valueOf(this.history.size()));
        if (this.history.size() < 100) {
            Pdlog.m3274e(this.TAG, "too few. size=" + this.history.size());
            TestItem testItem = this.mTestItem;
            if (testItem == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
            }
            testItem.setFailDescription("采样到的数据少，连线或者固件可能有问题");
            return false;
        }
        this.drift.setX((this.gyro.get_accumulate().getX() / this.history.size()) / this.PERIOD_s);
        this.drift.setY((this.gyro.get_accumulate().getY() / this.history.size()) / this.PERIOD_s);
        this.drift.setZ((this.gyro.get_accumulate().getZ() / this.history.size()) / this.PERIOD_s);
        this.keyInfo.put("x轴零偏", toDegreeString(this.drift.getX()));
        this.keyInfo.put("y轴零偏", toDegreeString(this.drift.getY()));
        this.keyInfo.put("z轴零偏", toDegreeString(this.drift.getZ()));
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("drift=");
        sb.append(this.drift);
        char c2 = ' ';
        sb.append(' ');
        sb.append(Math.toDegrees(this.drift.getZ()));
        sb.append(". gyro.z=");
        sb.append(this.gyro.get_accumulate().getZ());
        sb.append(' ');
        sb.append(Math.toDegrees(this.gyro.get_accumulate().getZ()));
        Pdlog.m3275i(str, sb.toString());
        int i = 0;
        for (Object obj : this.history) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            XYZ xyz = (XYZ) obj;
            String str2 = this.TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb2 = new StringBuilder();
            sb2.append(i);
            sb2.append(c2);
            sb2.append(xyz);
            objArr[c] = sb2.toString();
            Pdlog.m3273d(str2, objArr);
            if (Math.abs(xyz.getX()) > this.maxSpeed || Math.abs(xyz.getY()) > this.maxSpeed || Math.abs(xyz.getZ()) > this.maxSpeed) {
                this.keyInfo.put("静止时异常index", String.valueOf(i));
                this.keyInfo.put("静止时异常数值x", toDegreeString(xyz.getX()));
                this.keyInfo.put("静止时异常数值y", toDegreeString(xyz.getY()));
                this.keyInfo.put("静止时异常数值z", toDegreeString(xyz.getZ()));
                this.keyInfo.put("maxSpeed", String.valueOf(this.maxSpeed));
                Pdlog.m3274e(this.TAG, "wasn't no move. " + xyz + " > maxSpeed=" + this.maxSpeed);
                TestItem testItem2 = this.mTestItem;
                if (testItem2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
                }
                testItem2.setFailDescription("有移动，陀螺仪可能有故障");
                return false;
            }
            if (Math.abs(xyz.getX() - this.drift.getX()) > this.maxDiff || Math.abs(xyz.getY() - this.drift.getY()) > this.maxDiff || Math.abs(xyz.getZ() - this.drift.getZ()) > this.maxDiff) {
                this.keyInfo.put("静止时异常index", String.valueOf(i));
                this.keyInfo.put("静止时异常数值x", toDegreeString(xyz.getX()));
                this.keyInfo.put("静止时异常数值y", toDegreeString(xyz.getY()));
                this.keyInfo.put("静止时异常数值z", toDegreeString(xyz.getZ()));
                this.keyInfo.put("maxDiff", String.valueOf(this.maxDiff));
                Pdlog.m3274e(this.TAG, "drift is illegal. maxDiff=" + this.maxDiff);
                TestItem testItem3 = this.mTestItem;
                if (testItem3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
                }
                testItem3.setFailDescription("有移动，陀螺仪可能有故障");
                return false;
            }
            i = i2;
            c = 0;
            c2 = ' ';
        }
        return true;
    }

    public final boolean checkRotationOK() {
        this.keyInfo.put("陀螺仪数量(旋转)", String.valueOf(this.history.size()));
        if (this.history.size() < 100) {
            Pdlog.m3274e(this.TAG, "too few. size=" + this.history.size());
            TestItem testItem = this.mTestItem;
            if (testItem == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
            }
            testItem.setFailDescription("采样到的数据少，连线或者固件可能有问题");
            return false;
        }
        double x = this.gyro.get_accumulate().getX() - ((this.history.size() * this.drift.getX()) * this.PERIOD_s);
        double y = this.gyro.get_accumulate().getY() - ((this.history.size() * this.drift.getY()) * this.PERIOD_s);
        double z = this.gyro.get_accumulate().getZ() - ((this.history.size() * this.drift.getZ()) * this.PERIOD_s);
        this.keyInfo.put("x轴角度", toDegreeString(x));
        this.keyInfo.put("y轴角度", toDegreeString(y));
        this.keyInfo.put("z轴角度", toDegreeString(z));
        double radians = Math.toRadians(10.0d);
        if (Math.abs(x) > radians || Math.abs(y) > radians) {
            Pdlog.m3274e(this.TAG, "x and y shouldn't been rotate. " + x + ' ' + y);
            TestItem testItem2 = this.mTestItem;
            if (testItem2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
            }
            testItem2.setFailDescription("俯仰角和横滚角有移动，安装或者固件有故障");
            return false;
        }
        double d = 0.0d;
        Iterator<T> it = this.history.iterator();
        while (it.hasNext()) {
            d += ((XYZ) it.next()).getZ() * this.PERIOD_s;
        }
        Pdlog.m3273d(this.TAG, "checkRotationOK z=" + this.gyro.get_accumulate().getZ() + ". after fix drift z=" + z + "  sum=" + d);
        if (z < 5.654866776461628d) {
            Pdlog.m3274e(this.TAG, "z should be close to 2pi");
            TestItem testItem3 = this.mTestItem;
            if (testItem3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
            }
            testItem3.setFailDescription("操作不满足逆时针一圈，可能陀螺仪有故障");
            return false;
        }
        if (z <= 6.911503837897546d) {
            return true;
        }
        Pdlog.m3274e(this.TAG, "z should be close to 2pi");
        TestItem testItem4 = this.mTestItem;
        if (testItem4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem4.setFailDescription("操作不满足逆时针一圈，可能陀螺仪有故障");
        return false;
    }

    public final LinkedHashMap<String, String> getKeyInfo() {
        return this.keyInfo;
    }

    public final String toStr(LinkedHashMap<String, String> toStr) {
        Intrinsics.checkParameterIsNotNull(toStr, "$this$toStr");
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : toStr.entrySet()) {
            sb.append(entry.getKey() + " : " + entry.getValue() + '\n');
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "strBuilder.toString()");
        return sb2;
    }

    public final String toDegreeString(double d) {
        StringBuilder sb = new StringBuilder();
        String format = String.format("%.2f", Arrays.copyOf(new Object[]{Double.valueOf(Math.toDegrees(d))}, 1));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
        sb.append(format);
        sb.append(Typography.degree);
        return sb.toString();
    }

    public final void showKeyInfo(String string) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        Pdlog.m3275i(this.TAG, "showKeyInfo string=" + string);
        AppCompatTextView tvDebugInfo = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvDebugInfo);
        Intrinsics.checkExpressionValueIsNotNull(tvDebugInfo, "tvDebugInfo");
        tvDebugInfo.setText(string);
    }
}
