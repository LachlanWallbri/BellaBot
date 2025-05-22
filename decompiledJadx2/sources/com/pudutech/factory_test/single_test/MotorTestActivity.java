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
import com.pudutech.factory_test.single_test.MotorTestActivity;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import com.pudutech.factory_test.test_pack.TestItem;
import com.pudutech.factory_test.test_pack.TestStage;
import com.pudutech.factory_test.test_pack.TestStatus;
import com.pudutech.mirsdk.hardware.Encoder;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.ICANData;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: MotorTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001:\u00013B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010&\u001a\u00020'J\u0012\u0010(\u001a\u00020'2\b\u0010)\u001a\u0004\u0018\u00010*H\u0014J\b\u0010+\u001a\u00020'H\u0014J\u0006\u0010,\u001a\u00020'J\u0006\u0010-\u001a\u00020'J\u0006\u0010.\u001a\u00020'J\u0006\u0010/\u001a\u00020'J\u0006\u00100\u001a\u00020'J\u0006\u00101\u001a\u00020'J\u0006\u00102\u001a\u00020'R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u00064"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/MotorTestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "delayJob", "Lkotlinx/coroutines/Job;", "getDelayJob", "()Lkotlinx/coroutines/Job;", "setDelayJob", "(Lkotlinx/coroutines/Job;)V", "distanceLeft", "", "getDistanceLeft", "()D", "setDistanceLeft", "(D)V", "distanceRight", "getDistanceRight", "setDistanceRight", "encoder", "Lcom/pudutech/mirsdk/hardware/Encoder;", "getEncoder", "()Lcom/pudutech/mirsdk/hardware/Encoder;", "isLoaded", "", "mTestItem", "Lcom/pudutech/factory_test/test_pack/TestItem;", "getMTestItem", "()Lcom/pudutech/factory_test/test_pack/TestItem;", "setMTestItem", "(Lcom/pudutech/factory_test/test_pack/TestItem;)V", "step", "Lcom/pudutech/factory_test/single_test/MotorTestActivity$Step;", "getStep", "()Lcom/pudutech/factory_test/single_test/MotorTestActivity$Step;", "setStep", "(Lcom/pudutech/factory_test/single_test/MotorTestActivity$Step;)V", "FSM", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "removeListener", "runStepFail", "runStepIDLE", "runStepSuccess", "runStepTurnAround", "setListener", "setListener2", "Step", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class MotorTestActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private Job delayJob;
    private double distanceLeft;
    private double distanceRight;
    private final Encoder encoder;
    private boolean isLoaded;
    public TestItem mTestItem;
    private final String TAG = "MotorTestActivity";
    private Step step = Step.IDLE;

    /* compiled from: MotorTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/MotorTestActivity$Step;", "", "(Ljava/lang/String;I)V", "IDLE", "TURN_AROUND", "FAIL", "SUCCESS", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum Step {
        IDLE,
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
            $EnumSwitchMapping$0[Step.TURN_AROUND.ordinal()] = 2;
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

    public MotorTestActivity() {
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
                if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.MOTOR) && testItem2.getStage() == fromValue) {
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
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.MotorTestActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = MotorTestActivity.this.TAG;
                Pdlog.m3275i(str, "click quit");
                MotorTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnErrorQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.MotorTestActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = MotorTestActivity.this.TAG;
                Pdlog.m3275i(str, "click error quit");
                MotorTestActivity.this.getMTestItem().setStatus(TestStatus.FAIL);
                MotorTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.MotorTestActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = MotorTestActivity.this.TAG;
                Pdlog.m3275i(str, "click reset");
                MotorTestActivity.this.setStep(MotorTestActivity.Step.IDLE);
                MotorTestActivity.this.FSM();
            }
        });
        setListener2();
        System.currentTimeMillis();
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
            runStepTurnAround();
        } else if (i == 3) {
            runStepSuccess();
        } else {
            if (i != 4) {
                return;
            }
            runStepFail();
        }
    }

    public final void runStepIDLE() {
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        removeListener();
        this.distanceLeft = 0.0d;
        this.distanceRight = 0.0d;
        Job job = this.delayJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface != null) {
            hdInterface.controlWheel(0.0d, 0.0d, false);
        }
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("电机测试：\n点击 ‘开始’ 之后机器将会自动旋转，完成测试");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ((Button) LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("开始"), 0.0f, 4, null).get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.MotorTestActivity$runStepIDLE$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean z;
                z = MotorTestActivity.this.isLoaded;
                if (!z) {
                    MotorTestActivity.this.setStep(MotorTestActivity.Step.TURN_AROUND);
                } else {
                    MotorTestActivity.this.setStep(MotorTestActivity.Step.FAIL);
                    MotorTestActivity.this.getMTestItem().setFailDescription("开始测试前急停开关被按下，请松开急停开关后重新测试");
                }
                MotorTestActivity.this.FSM();
            }
        });
    }

    public final Job getDelayJob() {
        return this.delayJob;
    }

    public final void setDelayJob(Job job) {
        this.delayJob = job;
    }

    public final void runStepTurnAround() {
        Job launch$default;
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("电机测试：\n请勿干扰机器人自转");
        ((LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions)).removeAllViews();
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new MotorTestActivity$runStepTurnAround$1(this, null), 2, null);
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
        tvGuide.setText("电机测试结束\n测试结果是：成功");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("完成", "下一个未测试项"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.MotorTestActivity$runStepSuccess$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MotorTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.MotorTestActivity$runStepSuccess$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> action;
                TestItem nextUntested = AllTestItem.INSTANCE.getNextUntested(MotorTestActivity.this.getMTestItem());
                if (nextUntested != null && (action = nextUntested.getAction()) != null) {
                    action.invoke();
                }
                MotorTestActivity.this.finish();
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
        sb.append("电机测试结束\n测试结果是：失败\n描述：");
        TestItem testItem2 = this.mTestItem;
        if (testItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        sb.append(testItem2.getFailDescription());
        tvGuide.setText(sb.toString());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重新测试"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.MotorTestActivity$runStepFail$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MotorTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.MotorTestActivity$runStepFail$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((AppCompatButton) MotorTestActivity.this._$_findCachedViewById(C4491R.id.btnReset)).callOnClick();
            }
        });
    }

    public final Encoder getEncoder() {
        return this.encoder;
    }

    public final double getDistanceLeft() {
        return this.distanceLeft;
    }

    public final void setDistanceLeft(double d) {
        this.distanceLeft = d;
    }

    public final double getDistanceRight() {
        return this.distanceRight;
    }

    public final void setDistanceRight(double d) {
        this.distanceRight = d;
    }

    public final void setListener() {
        byte[] bArr = {1};
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface != null) {
            hdInterface.addCANDataListener(this.TAG, bArr, new ICANData.Stub() { // from class: com.pudutech.factory_test.single_test.MotorTestActivity$setListener$1
                @Override // com.pudutech.mirsdk.hardware.ICANData
                public void onData(int id, byte[] data) {
                    if (data == null) {
                        Intrinsics.throwNpe();
                    }
                    byte[] copyOf = Arrays.copyOf(data, data.length);
                    Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                    byte[] m4572constructorimpl = UByteArray.m4572constructorimpl(copyOf);
                    MotorTestActivity.this.getEncoder().update((short) (((UByteArray.m4577getimpl(m4572constructorimpl, 1) & 255) << 8) | (UByteArray.m4577getimpl(m4572constructorimpl, 2) & 255)), (short) ((UByteArray.m4577getimpl(m4572constructorimpl, 4) & 255) | ((UByteArray.m4577getimpl(m4572constructorimpl, 3) & 255) << 8)));
                    MotorTestActivity motorTestActivity = MotorTestActivity.this;
                    motorTestActivity.setDistanceLeft(motorTestActivity.getDistanceLeft() + MotorTestActivity.this.getEncoder().get_distance().getLeft());
                    MotorTestActivity motorTestActivity2 = MotorTestActivity.this;
                    motorTestActivity2.setDistanceRight(motorTestActivity2.getDistanceRight() + MotorTestActivity.this.getEncoder().get_distance().getRight());
                }
            });
        }
    }

    public final void setListener2() {
        Pdlog.m3273d(this.TAG, "setListener ");
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface == null) {
            Intrinsics.throwNpe();
        }
        hdInterface.addListener(this.TAG, new MotorTestActivity$setListener2$1(this));
    }

    public final void removeListener() {
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface != null) {
            hdInterface.removeCANDataListener(this.TAG);
        }
    }
}
