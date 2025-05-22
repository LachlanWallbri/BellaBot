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
import com.pudutech.factory_test.single_test.EncoderTestActivity;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import com.pudutech.factory_test.test_pack.TestItem;
import com.pudutech.factory_test.test_pack.TestStage;
import com.pudutech.factory_test.test_pack.TestStatus;
import com.pudutech.mirsdk.hardware.Encoder;
import com.pudutech.mirsdk.hardware.Gyroscope;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.ICANData;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
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

/* compiled from: EncoderTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001:\u0001LB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u00108\u001a\u000209J\u0006\u0010:\u001a\u00020;J\u0006\u0010<\u001a\u00020;J\u0012\u0010=\u001a\u0002092\b\u0010>\u001a\u0004\u0018\u00010?H\u0014J\b\u0010@\u001a\u000209H\u0014J\u0006\u0010A\u001a\u000209J\u0006\u0010B\u001a\u000209J\u0006\u0010C\u001a\u000209J\u0006\u0010D\u001a\u000209J\u0006\u0010E\u001a\u000209J\u0006\u0010F\u001a\u000209J\u0006\u0010G\u001a\u000209J\u000e\u0010H\u001a\u0002092\u0006\u0010I\u001a\u00020\bJ\n\u0010J\u001a\u00020\b*\u00020\u0004J*\u0010K\u001a\u00020\b*\"\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0(j\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b`)R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0006\"\u0004\b\u001f\u0010\u0012R!\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\u00040!j\b\u0012\u0004\u0012\u00020\u0004`\"¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R!\u0010%\u001a\u0012\u0012\u0004\u0012\u00020\u00040!j\b\u0012\u0004\u0012\u00020\u0004`\"¢\u0006\b\n\u0000\u001a\u0004\b&\u0010$R1\u0010'\u001a\"\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0(j\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b`)¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020-X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001a\u00102\u001a\u000203X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107¨\u0006M"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/EncoderTestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "PERIOD_s", "", "getPERIOD_s", "()D", "TAG", "", "delayJob", "Lkotlinx/coroutines/Job;", "getDelayJob", "()Lkotlinx/coroutines/Job;", "setDelayJob", "(Lkotlinx/coroutines/Job;)V", "distance", "getDistance", "setDistance", "(D)V", "encoder", "Lcom/pudutech/mirsdk/hardware/Encoder;", "getEncoder", "()Lcom/pudutech/mirsdk/hardware/Encoder;", "gyro", "Lcom/pudutech/mirsdk/hardware/Gyroscope;", "getGyro", "()Lcom/pudutech/mirsdk/hardware/Gyroscope;", "setGyro", "(Lcom/pudutech/mirsdk/hardware/Gyroscope;)V", "gyroDrift", "getGyroDrift", "setGyroDrift", "historyEncoderSpd", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getHistoryEncoderSpd", "()Ljava/util/ArrayList;", "historyGyroSpd", "getHistoryGyroSpd", "keyInfo", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "getKeyInfo", "()Ljava/util/LinkedHashMap;", "mTestItem", "Lcom/pudutech/factory_test/test_pack/TestItem;", "getMTestItem", "()Lcom/pudutech/factory_test/test_pack/TestItem;", "setMTestItem", "(Lcom/pudutech/factory_test/test_pack/TestItem;)V", "step", "Lcom/pudutech/factory_test/single_test/EncoderTestActivity$Step;", "getStep", "()Lcom/pudutech/factory_test/single_test/EncoderTestActivity$Step;", "setStep", "(Lcom/pudutech/factory_test/single_test/EncoderTestActivity$Step;)V", "FSM", "", "checkNoMove", "", "checkRotationOK", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "removeListener", "runStepFail", "runStepIDLE", "runStepNoMove", "runStepSuccess", "runStepTurnAround", "setListener", "showKeyInfo", "string", "toDegreeString", "toStr", "Step", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class EncoderTestActivity extends AppCompatActivity {
    private final double PERIOD_s;
    private HashMap _$_findViewCache;
    private Job delayJob;
    private double distance;
    private final Encoder encoder;
    private double gyroDrift;
    private final ArrayList<Double> historyEncoderSpd;
    private final ArrayList<Double> historyGyroSpd;
    private final LinkedHashMap<String, String> keyInfo;
    public TestItem mTestItem;
    private final String TAG = "EncoderTestActivity";
    private Step step = Step.IDLE;
    private Gyroscope gyro = new Gyroscope();

    /* compiled from: EncoderTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/EncoderTestActivity$Step;", "", "(Ljava/lang/String;I)V", "IDLE", "NO_MOVE", "TURN_AROUND", "FAIL", "SUCCESS", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
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

    public EncoderTestActivity() {
        Encoder encoder = new Encoder();
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface == null) {
            Intrinsics.throwNpe();
        }
        MachineInfo machineInfo = hdInterface.getMachineInfo();
        Float f = machineInfo.getFloat(MachineInfo.FloatInfo.encoderPulsePerCircle);
        if (f == null) {
            Intrinsics.throwNpe();
        }
        float floatValue = f.floatValue();
        Float f2 = machineInfo.getFloat(MachineInfo.FloatInfo.reductionRatio);
        if (f2 == null) {
            Intrinsics.throwNpe();
        }
        float floatValue2 = f2.floatValue();
        Float f3 = machineInfo.getFloat(MachineInfo.FloatInfo.encoderSampleTimesPerPulse);
        if (f3 == null) {
            Intrinsics.throwNpe();
        }
        float floatValue3 = f3.floatValue();
        Float f4 = machineInfo.getFloat(MachineInfo.FloatInfo.machineWheelPerimeter);
        if (f4 == null) {
            Intrinsics.throwNpe();
        }
        float floatValue4 = f4.floatValue();
        Float f5 = machineInfo.getFloat(MachineInfo.FloatInfo.machineWheelbase);
        if (f5 == null) {
            Intrinsics.throwNpe();
        }
        float floatValue5 = f5.floatValue();
        Pdlog.m3275i(this.TAG, "set encoder configure from machine info, encoderPulsePerCircle:" + floatValue + " reductionRatio:" + floatValue2 + " encoderSampleTimesPerPulse:" + floatValue3 + " machineWheelPerimeter:" + floatValue4 + " wheelSpacing:" + floatValue5);
        encoder.setConfigure(floatValue, floatValue2, floatValue3, floatValue4, floatValue5);
        this.encoder = encoder;
        this.historyGyroSpd = new ArrayList<>();
        this.historyEncoderSpd = new ArrayList<>();
        this.PERIOD_s = 0.025d;
        this.keyInfo = new LinkedHashMap<>();
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
                if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.ENCODER) && testItem2.getStage() == fromValue) {
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
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.EncoderTestActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = EncoderTestActivity.this.TAG;
                Pdlog.m3275i(str, "click quit");
                EncoderTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnErrorQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.EncoderTestActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = EncoderTestActivity.this.TAG;
                Pdlog.m3275i(str, "click error quit");
                EncoderTestActivity.this.getMTestItem().setStatus(TestStatus.FAIL);
                EncoderTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.EncoderTestActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = EncoderTestActivity.this.TAG;
                Pdlog.m3275i(str, "click reset");
                EncoderTestActivity.this.setStep(EncoderTestActivity.Step.IDLE);
                EncoderTestActivity.this.FSM();
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
        removeListener();
        this.keyInfo.clear();
        AppCompatTextView tvDebugInfo = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvDebugInfo);
        Intrinsics.checkExpressionValueIsNotNull(tvDebugInfo, "tvDebugInfo");
        tvDebugInfo.setText("");
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        this.gyro = new Gyroscope();
        this.gyroDrift = 0.0d;
        this.historyGyroSpd.clear();
        this.historyEncoderSpd.clear();
        Job job = this.delayJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("编码器测试( 1 | 2 )：\n点击 ‘开始’ 之后保持机器人静止");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ((Button) LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("开始"), 0.0f, 4, null).get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.EncoderTestActivity$runStepIDLE$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EncoderTestActivity.this.setStep(EncoderTestActivity.Step.NO_MOVE);
                EncoderTestActivity.this.FSM();
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
        tvGuide.setText("编码器测试( 1 | 2 )：\n请保持机器人静止5秒钟");
        this.distance = 0.0d;
        ((LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions)).removeAllViews();
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new EncoderTestActivity$runStepNoMove$1(this, null), 2, null);
        this.delayJob = launch$default;
    }

    public final void runStepTurnAround() {
        removeListener();
        this.gyro = new Gyroscope();
        this.historyGyroSpd.clear();
        this.historyEncoderSpd.clear();
        setListener();
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("编码器测试( 2 | 2 ): \n请逆时针旋转机器人一圈后点击 ‘确认’ ");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("重新旋转", "确认"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.EncoderTestActivity$runStepTurnAround$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EncoderTestActivity.this.FSM();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.EncoderTestActivity$runStepTurnAround$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EncoderTestActivity.this.removeListener();
                if (EncoderTestActivity.this.checkRotationOK()) {
                    EncoderTestActivity.this.setStep(EncoderTestActivity.Step.SUCCESS);
                } else {
                    EncoderTestActivity.this.setStep(EncoderTestActivity.Step.FAIL);
                }
                EncoderTestActivity.this.FSM();
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
        tvGuide.setText("编码器测试结束\n测试结果是：成功");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("完成", "下一个未测试项"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.EncoderTestActivity$runStepSuccess$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EncoderTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.EncoderTestActivity$runStepSuccess$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> action;
                TestItem nextUntested = AllTestItem.INSTANCE.getNextUntested(EncoderTestActivity.this.getMTestItem());
                if (nextUntested != null && (action = nextUntested.getAction()) != null) {
                    action.invoke();
                }
                EncoderTestActivity.this.finish();
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
        sb.append("编码器测试结束\n测试结果是：失败\n描述：");
        TestItem testItem2 = this.mTestItem;
        if (testItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        sb.append(testItem2.getFailDescription());
        tvGuide.setText(sb.toString());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重新测试"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.EncoderTestActivity$runStepFail$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EncoderTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.EncoderTestActivity$runStepFail$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((AppCompatButton) EncoderTestActivity.this._$_findCachedViewById(C4491R.id.btnReset)).callOnClick();
            }
        });
    }

    public final Gyroscope getGyro() {
        return this.gyro;
    }

    public final void setGyro(Gyroscope gyroscope) {
        Intrinsics.checkParameterIsNotNull(gyroscope, "<set-?>");
        this.gyro = gyroscope;
    }

    public final Encoder getEncoder() {
        return this.encoder;
    }

    public final ArrayList<Double> getHistoryGyroSpd() {
        return this.historyGyroSpd;
    }

    public final ArrayList<Double> getHistoryEncoderSpd() {
        return this.historyEncoderSpd;
    }

    public final double getDistance() {
        return this.distance;
    }

    public final void setDistance(double d) {
        this.distance = d;
    }

    public final double getGyroDrift() {
        return this.gyroDrift;
    }

    public final void setGyroDrift(double d) {
        this.gyroDrift = d;
    }

    public final void setListener() {
        byte[] bArr = {16, 1};
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface != null) {
            hdInterface.addCANDataListener(this.TAG, bArr, new ICANData.Stub() { // from class: com.pudutech.factory_test.single_test.EncoderTestActivity$setListener$1
                @Override // com.pudutech.mirsdk.hardware.ICANData
                public void onData(int id, byte[] data) {
                    String str;
                    String str2;
                    if (data == null) {
                        Intrinsics.throwNpe();
                    }
                    byte[] copyOf = Arrays.copyOf(data, data.length);
                    Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                    byte[] m4572constructorimpl = UByteArray.m4572constructorimpl(copyOf);
                    if (id == 1) {
                        if (EncoderTestActivity.this.getHistoryEncoderSpd().size() > (1 / EncoderTestActivity.this.getPERIOD_s()) * 60) {
                            str2 = EncoderTestActivity.this.TAG;
                            Pdlog.m3277w(str2, "spent too much time to rotate. return");
                            return;
                        } else {
                            EncoderTestActivity.this.getEncoder().update((short) (((UByteArray.m4577getimpl(m4572constructorimpl, 1) & 255) << 8) | (UByteArray.m4577getimpl(m4572constructorimpl, 2) & 255)), (short) ((UByteArray.m4577getimpl(m4572constructorimpl, 4) & 255) | ((UByteArray.m4577getimpl(m4572constructorimpl, 3) & 255) << 8)));
                            EncoderTestActivity.this.getHistoryEncoderSpd().add(Double.valueOf(EncoderTestActivity.this.getEncoder().get_speed().getAngularSpeed()));
                            EncoderTestActivity encoderTestActivity = EncoderTestActivity.this;
                            encoderTestActivity.setDistance(encoderTestActivity.getDistance() + Math.abs(EncoderTestActivity.this.getEncoder().get_distance().getLeft()) + Math.abs(EncoderTestActivity.this.getEncoder().get_distance().getRight()));
                            return;
                        }
                    }
                    if (id == 16) {
                        if (EncoderTestActivity.this.getHistoryGyroSpd().size() > (1 / EncoderTestActivity.this.getPERIOD_s()) * 60) {
                            str = EncoderTestActivity.this.TAG;
                            Pdlog.m3277w(str, "spent too much time to rotate. return");
                        } else {
                            EncoderTestActivity.this.getGyro().update((short) (((UByteArray.m4577getimpl(m4572constructorimpl, 1) & 255) << 8) | (UByteArray.m4577getimpl(m4572constructorimpl, 2) & 255)), (short) (((UByteArray.m4577getimpl(m4572constructorimpl, 3) & 255) << 8) | (UByteArray.m4577getimpl(m4572constructorimpl, 4) & 255)), (short) ((UByteArray.m4577getimpl(m4572constructorimpl, 6) & 255) | ((UByteArray.m4577getimpl(m4572constructorimpl, 5) & 255) << 8)));
                            EncoderTestActivity.this.getHistoryGyroSpd().add(Double.valueOf(EncoderTestActivity.this.getGyro().get_last().getZ()));
                        }
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

    public final boolean checkNoMove() {
        this.keyInfo.put("陀螺仪接收数量", String.valueOf(this.historyGyroSpd.size()));
        this.keyInfo.put("编码器接收数据", String.valueOf(this.historyEncoderSpd.size()));
        if (this.historyGyroSpd.size() < 100 || this.historyEncoderSpd.size() < 100) {
            Pdlog.m3274e(this.TAG, "too few. gyro size=" + this.historyGyroSpd.size() + " encoder size=" + this.historyEncoderSpd.size());
            TestItem testItem = this.mTestItem;
            if (testItem == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
            }
            testItem.setFailDescription("采样到的数据少，连线或者固件可能有问题");
            return false;
        }
        if (this.distance > 0.1d) {
            Pdlog.m3274e(this.TAG, "robot moved");
            TestItem testItem2 = this.mTestItem;
            if (testItem2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
            }
            testItem2.setFailDescription("有移动，编码器可能有故障");
            return false;
        }
        this.gyroDrift = (this.gyro.get_accumulate().getZ() / this.historyGyroSpd.size()) / this.PERIOD_s;
        Pdlog.m3273d(this.TAG, "check no move ok. " + (this.gyro.get_accumulate().getZ() / this.historyGyroSpd.size()) + " drift=" + this.gyroDrift + " to degree=" + Math.toDegrees(this.gyroDrift));
        return true;
    }

    public final boolean checkRotationOK() {
        this.keyInfo.put("陀螺仪接收数量", String.valueOf(this.historyGyroSpd.size()));
        this.keyInfo.put("编码器接收数据", String.valueOf(this.historyEncoderSpd.size()));
        int i = 1;
        if (this.historyGyroSpd.size() < 100 || this.historyEncoderSpd.size() < 100) {
            Pdlog.m3274e(this.TAG, "data too few.gyro size=" + this.historyGyroSpd.size() + " encoder size=" + this.historyEncoderSpd.size());
            TestItem testItem = this.mTestItem;
            if (testItem == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
            }
            testItem.setFailDescription("采集的数量过少，连线或者固件可能有问题");
            return false;
        }
        double radians = Math.toRadians(5.0d);
        Pdlog.m3273d(this.TAG, "gyro size=" + this.historyGyroSpd.size() + " encoder size=" + this.historyEncoderSpd.size());
        int min = Math.min(this.historyGyroSpd.size(), this.historyEncoderSpd.size());
        int i2 = 0;
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        while (i2 < min) {
            if (i2 % 40 == 0) {
                String str = this.TAG;
                Object[] objArr = new Object[i];
                objArr[0] = "sumGyro=" + d2 + " sumEncoder=" + d3;
                Pdlog.m3273d(str, objArr);
                if (Math.abs(d3 - d2) > radians) {
                    this.keyInfo.put("一致性误差过大sumEncoder", toDegreeString(d3));
                    this.keyInfo.put("一致性误差过大sumGyro", toDegreeString(d2));
                    this.keyInfo.put("一致性误差过大max", toDegreeString(radians));
                    Pdlog.m3274e(this.TAG, "sumEncoder=" + d3 + " should be close to sumGyro=" + d2 + ". diff should be < " + radians);
                    TestItem testItem2 = this.mTestItem;
                    if (testItem2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
                    }
                    testItem2.setFailDescription("编码器与陀螺仪的旋转速度不一致");
                    return false;
                }
                d2 = 0.0d;
                d3 = 0.0d;
            }
            Pdlog.m3273d(this.TAG, i2 + " gyro=" + (this.historyGyroSpd.get(i2).doubleValue() - this.gyroDrift) + " encoder=" + this.historyEncoderSpd.get(i2));
            d3 += this.historyEncoderSpd.get(i2).doubleValue() * this.PERIOD_s;
            d2 += (this.historyGyroSpd.get(i2).doubleValue() - this.gyroDrift) * this.PERIOD_s;
            d += this.historyEncoderSpd.get(i2).doubleValue() * this.PERIOD_s;
            i2++;
            radians = radians;
            i = 1;
        }
        double z = this.gyro.get_accumulate().getZ();
        double size = this.historyGyroSpd.size();
        double d4 = this.gyroDrift;
        double d5 = z - ((size * d4) * this.PERIOD_s);
        this.keyInfo.put("陀螺仪零偏", toDegreeString(d4));
        this.keyInfo.put("陀螺仪姿态角（矫正）", toDegreeString(d5));
        this.keyInfo.put("编码器姿态角", toDegreeString(d));
        Pdlog.m3273d(this.TAG, "checkRotationOK z=" + d5 + ". angle=" + d);
        if (d < 5.654866776461628d) {
            Pdlog.m3274e(this.TAG, "angle should be close to 2pi");
            TestItem testItem3 = this.mTestItem;
            if (testItem3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
            }
            testItem3.setFailDescription("操作不满足逆时针一圈，可能编码器有故障");
            return false;
        }
        if (d > 6.911503837897546d) {
            Pdlog.m3274e(this.TAG, "angle should be close to 2pi");
            TestItem testItem4 = this.mTestItem;
            if (testItem4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
            }
            testItem4.setFailDescription("操作不满足逆时针一圈，可能编码器有故障");
            return false;
        }
        double d6 = d5 - d;
        this.keyInfo.put("误差", toDegreeString(d6));
        LinkedHashMap<String, String> linkedHashMap = this.keyInfo;
        StringBuilder sb = new StringBuilder();
        sb.append(15.0d);
        sb.append(Typography.degree);
        linkedHashMap.put("要求", sb.toString());
        if (Math.abs(d6) <= Math.toRadians(15.0d)) {
            return true;
        }
        Pdlog.m3274e(this.TAG, "encoder angle should be close to gyro angle. diff=" + d6 + " to degree=" + Math.toDegrees(d6) + " > 15.0");
        TestItem testItem5 = this.mTestItem;
        if (testItem5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem5.setFailDescription("编码器和陀螺仪误差较大，可能编码器有故障");
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
