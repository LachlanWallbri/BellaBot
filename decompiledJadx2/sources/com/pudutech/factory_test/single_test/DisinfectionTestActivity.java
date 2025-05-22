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
import com.pudutech.factory_test.single_test.DisinfectionTestActivity;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import com.pudutech.factory_test.test_pack.TestItem;
import com.pudutech.factory_test.test_pack.TestStage;
import com.pudutech.factory_test.test_pack.TestStatus;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
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

/* compiled from: DisinfectionTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0001SB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010A\u001a\u00020\bJ\u0012\u0010B\u001a\u00020\b2\b\u0010C\u001a\u0004\u0018\u00010DH\u0014J\b\u0010E\u001a\u00020\bH\u0014J\u0006\u0010F\u001a\u00020\bJ\u0006\u0010G\u001a\u00020\bJ\u0006\u0010H\u001a\u00020\bJ\u0006\u0010I\u001a\u00020\bJ\u0006\u0010J\u001a\u00020\bJ\u0006\u0010K\u001a\u00020\bJ\u0006\u0010L\u001a\u00020\bJ\u0006\u0010M\u001a\u00020\bJ\u0006\u0010N\u001a\u00020\bJ\u0006\u0010O\u001a\u00020\bJ\u000e\u0010P\u001a\u00020\b2\u0006\u0010Q\u001a\u00020\u0004J*\u0010R\u001a\u00020\u0004*\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040(j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004`)R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R-\u0010\u0005\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007`\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001a\u0010\u0015\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001a\u0010\u0018\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000f\"\u0004\b\u001a\u0010\u0011R\u001a\u0010\u001b\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000f\"\u0004\b\u001d\u0010\u0011R\u001a\u0010\u001e\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u000f\"\u0004\b \u0010\u0011R\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R1\u0010'\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040(j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004`)¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001a\u00102\u001a\u000203X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001a\u00108\u001a\u00020-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010/\"\u0004\b:\u00101R\u001a\u0010;\u001a\u00020<X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@¨\u0006T"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/DisinfectionTestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "allCases", "Ljava/util/ArrayList;", "Lkotlin/Function0;", "", "Lkotlin/collections/ArrayList;", "getAllCases", "()Ljava/util/ArrayList;", "caseNum", "", "getCaseNum", "()I", "setCaseNum", "(I)V", "current1", "getCurrent1", "setCurrent1", "current2", "getCurrent2", "setCurrent2", "current3", "getCurrent3", "setCurrent3", "current4", "getCurrent4", "setCurrent4", "fanSpeed", "getFanSpeed", "setFanSpeed", "getFanSpeedJob", "Lkotlinx/coroutines/Job;", "getGetFanSpeedJob", "()Lkotlinx/coroutines/Job;", "setGetFanSpeedJob", "(Lkotlinx/coroutines/Job;)V", "keyInfo", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "getKeyInfo", "()Ljava/util/LinkedHashMap;", "liquidLevel", "", "getLiquidLevel", "()D", "setLiquidLevel", "(D)V", "mTestItem", "Lcom/pudutech/factory_test/test_pack/TestItem;", "getMTestItem", "()Lcom/pudutech/factory_test/test_pack/TestItem;", "setMTestItem", "(Lcom/pudutech/factory_test/test_pack/TestItem;)V", "sprayChamberLevel", "getSprayChamberLevel", "setSprayChamberLevel", "step", "Lcom/pudutech/factory_test/single_test/DisinfectionTestActivity$Step;", "getStep", "()Lcom/pudutech/factory_test/single_test/DisinfectionTestActivity$Step;", "setStep", "(Lcom/pudutech/factory_test/single_test/DisinfectionTestActivity$Step;)V", "FSM", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "removeListener", "runInstallLamp", "runLampLight", "runStepChecking", "runStepFail", "runStepIDLE", "runStepSuccess", "runStepUVOpen", "setCases", "setListener", "showKeyInfo", "string", "toStr", "Step", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class DisinfectionTestActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private int caseNum;
    private int current1;
    private int current2;
    private int current3;
    private int current4;
    private int fanSpeed;
    private Job getFanSpeedJob;
    private double liquidLevel;
    public TestItem mTestItem;
    private double sprayChamberLevel;
    private final String TAG = "DisinfectionTestActivity";
    private final ArrayList<Function0<Unit>> allCases = new ArrayList<>();
    private Step step = Step.IDLE;
    private final LinkedHashMap<String, String> keyInfo = new LinkedHashMap<>();

    /* compiled from: DisinfectionTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/DisinfectionTestActivity$Step;", "", "(Ljava/lang/String;I)V", "IDLE", "UVOPEN", "INSTALL_LAMP", "LAMP_LIGHT", "CHECKING", "FAIL", "SUCCESS", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum Step {
        IDLE,
        UVOPEN,
        INSTALL_LAMP,
        LAMP_LIGHT,
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
            $EnumSwitchMapping$0[Step.UVOPEN.ordinal()] = 2;
            $EnumSwitchMapping$0[Step.INSTALL_LAMP.ordinal()] = 3;
            $EnumSwitchMapping$0[Step.LAMP_LIGHT.ordinal()] = 4;
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

    public final int getCurrent1() {
        return this.current1;
    }

    public final void setCurrent1(int i) {
        this.current1 = i;
    }

    public final int getCurrent2() {
        return this.current2;
    }

    public final void setCurrent2(int i) {
        this.current2 = i;
    }

    public final int getCurrent3() {
        return this.current3;
    }

    public final void setCurrent3(int i) {
        this.current3 = i;
    }

    public final int getCurrent4() {
        return this.current4;
    }

    public final void setCurrent4(int i) {
        this.current4 = i;
    }

    public final int getFanSpeed() {
        return this.fanSpeed;
    }

    public final void setFanSpeed(int i) {
        this.fanSpeed = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        TestItem testItem;
        Pdlog.m3275i(this.TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(2131427397);
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
                if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.DISINFECTION) && testItem2.getStage() == fromValue) {
                    break;
                }
            }
            if (testItem == null) {
                Intrinsics.throwNpe();
            }
            this.mTestItem = testItem;
        }
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setMovementMethod(ScrollingMovementMethod.getInstance());
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = DisinfectionTestActivity.this.TAG;
                Pdlog.m3275i(str, "click quit");
                DisinfectionTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnErrorQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = DisinfectionTestActivity.this.TAG;
                Pdlog.m3275i(str, "click error quit");
                DisinfectionTestActivity.this.getMTestItem().setStatus(TestStatus.FAIL);
                DisinfectionTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = DisinfectionTestActivity.this.TAG;
                Pdlog.m3275i(str, "click reset");
                DisinfectionTestActivity.this.getMTestItem().setStatus(TestStatus.TESTING);
                DisinfectionTestActivity.this.setStep(DisinfectionTestActivity.Step.IDLE);
                DisinfectionTestActivity.this.FSM();
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
                runStepUVOpen();
                return;
            case 3:
                runInstallLamp();
                return;
            case 4:
                runLampLight();
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
        tvGuide.setText("消毒测试:\n请手动注入2L水后，点击 ‘下一步’ ");
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ((Button) LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("下一步"), 0.0f, 4, null).get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$runStepIDLE$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (DisinfectionTestActivity.this.getLiquidLevel() > 20) {
                    DisinfectionTestActivity.this.setStep(DisinfectionTestActivity.Step.UVOPEN);
                } else {
                    DisinfectionTestActivity.this.setStep(DisinfectionTestActivity.Step.FAIL);
                    DisinfectionTestActivity.this.getMTestItem().setFailDescription("开始测试前水不足2L");
                }
                DisinfectionTestActivity.this.FSM();
            }
        });
    }

    public final void runStepUVOpen() {
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("消毒测试:\n请稍等3秒钟,确认紫外设备是否正常旋转出来");
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
        if (rbInterface != null) {
            rbInterface.openUvLampDevice(true, false);
        }
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList<Button> layoutBtnOptions = LayoutHelperKt.layoutBtnOptions(layoutOptions, this, CollectionsKt.arrayListOf("是", "否"), 25.0f);
        layoutBtnOptions.get(0).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$runStepUVOpen$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectionTestActivity.this.setStep(DisinfectionTestActivity.Step.INSTALL_LAMP);
                DisinfectionTestActivity.this.FSM();
            }
        });
        layoutBtnOptions.get(1).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$runStepUVOpen$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectionTestActivity.this.setStep(DisinfectionTestActivity.Step.FAIL);
                DisinfectionTestActivity.this.getMTestItem().setFailDescription("紫外设备无法正常旋转出来");
                DisinfectionTestActivity.this.FSM();
            }
        });
    }

    public final void runInstallLamp() {
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("消毒测试:\n请插入测试日光灯，插入完成后点击'完成,进行下一步'");
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ((Button) LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("完成,进行下一步"), 0.0f, 4, null).get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$runInstallLamp$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectionTestActivity.this.setStep(DisinfectionTestActivity.Step.LAMP_LIGHT);
                DisinfectionTestActivity.this.FSM();
            }
        });
    }

    public final void runLampLight() {
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("消毒测试:\n请稍等3秒钟,确认日光灯是否正常点亮");
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
        if (rbInterface != null) {
            rbInterface.openUvLampDevice(true, true);
        }
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList<Button> layoutBtnOptions = LayoutHelperKt.layoutBtnOptions(layoutOptions, this, CollectionsKt.arrayListOf("是", "否"), 25.0f);
        layoutBtnOptions.get(0).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$runLampLight$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectionTestActivity.this.setStep(DisinfectionTestActivity.Step.CHECKING);
                DisinfectionTestActivity.this.setCases();
                DisinfectionTestActivity.this.FSM();
            }
        });
        layoutBtnOptions.get(1).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$runLampLight$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectionTestActivity.this.setStep(DisinfectionTestActivity.Step.FAIL);
                DisinfectionTestActivity.this.getMTestItem().setFailDescription("日光灯无法正常点亮");
                DisinfectionTestActivity.this.FSM();
            }
        });
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
        tvGuide.setText("消毒模块测试结束\n测试结果是：成功");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList<Button> layoutBtnOptions = LayoutHelperKt.layoutBtnOptions(layoutOptions, this, CollectionsKt.arrayListOf("完成", "下一个未测试项"), 25.0f);
        layoutBtnOptions.get(0).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$runStepSuccess$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectionTestActivity.this.finish();
            }
        });
        layoutBtnOptions.get(1).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$runStepSuccess$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> action;
                TestItem nextUntested = AllTestItem.INSTANCE.getNextUntested(DisinfectionTestActivity.this.getMTestItem());
                if (nextUntested != null && (action = nextUntested.getAction()) != null) {
                    action.invoke();
                }
                DisinfectionTestActivity.this.finish();
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
        sb.append("消毒模块测试结束\n测试结果是：失败\n描述：");
        TestItem testItem2 = this.mTestItem;
        if (testItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        sb.append(testItem2.getFailDescription());
        tvGuide.setText(sb.toString());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList<Button> layoutBtnOptions = LayoutHelperKt.layoutBtnOptions(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重新测试"), 30.0f);
        layoutBtnOptions.get(0).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$runStepFail$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DisinfectionTestActivity.this.finish();
            }
        });
        layoutBtnOptions.get(1).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$runStepFail$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((AppCompatButton) DisinfectionTestActivity.this._$_findCachedViewById(C4491R.id.btnReset)).callOnClick();
            }
        });
    }

    public final void setListener() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new DisinfectionTestActivity$setListener$1(null), 2, null);
        this.getFanSpeedJob = launch$default;
        RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
        if (rbInterface != null) {
            rbInterface.addDisinfectionRobotListener(this.TAG, new IDisinfectionRobotListener.Stub() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$setListener$2
                @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
                public void onSprayLiquidStatus(boolean p0) {
                }

                @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
                public void onUvLampPlateOpenState(OpenState p0) {
                }

                @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
                public void onLiquidLevelChange(double p0) {
                    String str;
                    str = DisinfectionTestActivity.this.TAG;
                    Pdlog.m3273d(str, "onLiquidLevelChange " + p0);
                    DisinfectionTestActivity.this.setLiquidLevel(p0);
                }

                @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
                public void onSprayChamberLevelChange(double p0) {
                    String str;
                    str = DisinfectionTestActivity.this.TAG;
                    Pdlog.m3273d(str, "onSprayChamberLevelChange " + p0);
                    DisinfectionTestActivity.this.setSprayChamberLevel(p0);
                }

                @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
                public void onSprayDiveceError(SprayDeviceError[] p0) {
                    String str;
                    str = DisinfectionTestActivity.this.TAG;
                    Pdlog.m3273d(str, "onSprayDiveceError " + p0);
                }

                @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
                public void onSprayDiveceOpen(boolean p0) {
                    String str;
                    str = DisinfectionTestActivity.this.TAG;
                    Pdlog.m3273d(str, "onSprayDiveceOpen " + p0);
                }

                @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
                public void onSpringOpenStatus(boolean p0) {
                    String str;
                    str = DisinfectionTestActivity.this.TAG;
                    Pdlog.m3273d(str, "onSpringOpenStatus " + p0);
                }

                @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
                public void onUvLampDeviceError(UvLampDeviceError[] p0) {
                    String str;
                    str = DisinfectionTestActivity.this.TAG;
                    Pdlog.m3273d(str, "onUvLampDeviceError " + p0);
                }

                @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
                public void onUvLampDeviceOpen(boolean p0) {
                    String str;
                    str = DisinfectionTestActivity.this.TAG;
                    Pdlog.m3273d(str, "onUvLampDeviceOpen " + p0);
                }
            });
        }
        byte[] bArr = {(byte) 135, (byte) 137};
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface != null) {
            hdInterface.addCANDataListener(this.TAG, bArr, new DisinfectionTestActivity$setListener$3(this));
        }
    }

    public final void removeListener() {
        Job job = this.getFanSpeedJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        DisinfectionModuleManager.INSTANCE.removeListener(this.TAG);
    }

    public final Job getGetFanSpeedJob() {
        return this.getFanSpeedJob;
    }

    public final void setGetFanSpeedJob(Job job) {
        this.getFanSpeedJob = job;
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
            ((Button) obj).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$setCases$$inlined$forEachIndexed$lambda$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    String str;
                    str = this.TAG;
                    Pdlog.m3275i(str, "click option. index=" + i + " when rightAnswerIndex=" + intRef.element);
                    this.getMTestItem().setStatus(TestStatus.TESTING);
                    if (intRef.element != i) {
                        this.setStep(DisinfectionTestActivity.Step.FAIL);
                        this.getMTestItem().setFailDescription("选择选项错误，可能是消毒或紫外模块有异常");
                    }
                    this.FSM();
                }
            });
            i = i2;
        }
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$setCases$2
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
                int caseNum = DisinfectionTestActivity.this.getCaseNum() - DisinfectionTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) DisinfectionTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("消毒测试 ( " + caseNum + " | " + DisinfectionTestActivity.this.getCaseNum() + " )：\n请稍等3秒钟,确认紫外灯是否正常打开");
                intRef.element = 0;
                RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
                if (rbInterface != null) {
                    rbInterface.openUvLampDevice(true, true);
                }
            }
        });
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$setCases$3
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
                int caseNum = DisinfectionTestActivity.this.getCaseNum() - DisinfectionTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) DisinfectionTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("消毒测试 ( " + caseNum + " | " + DisinfectionTestActivity.this.getCaseNum() + " )：\n请稍等3秒钟,确认紫外灯是否正常打开");
                intRef.element = 1;
                RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
                if (rbInterface != null) {
                    rbInterface.openUvLampDevice(false, false);
                }
                str = DisinfectionTestActivity.this.TAG;
                Pdlog.m3275i(str, "DisinfectionModuleManager.boot(");
            }
        });
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$setCases$4
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
                int caseNum = DisinfectionTestActivity.this.getCaseNum() - DisinfectionTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) DisinfectionTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("消毒测试 ( " + caseNum + " | " + DisinfectionTestActivity.this.getCaseNum() + " )：\n请稍等3秒钟,确认紫外设备是否正常旋转出来");
                intRef.element = 1;
                RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
                if (rbInterface != null) {
                    rbInterface.openUvLampDevice(false, false);
                }
            }
        });
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$setCases$5
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
                int caseNum = DisinfectionTestActivity.this.getCaseNum() - DisinfectionTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) DisinfectionTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("消毒测试 ( " + caseNum + " | " + DisinfectionTestActivity.this.getCaseNum() + " )：\n请稍等3秒钟,确认紫外设备是否正常旋转出来");
                intRef.element = 0;
                RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
                if (rbInterface != null) {
                    rbInterface.openUvLampDevice(true, false);
                }
            }
        });
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$setCases$6
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
                int caseNum = DisinfectionTestActivity.this.getCaseNum() - DisinfectionTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) DisinfectionTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("消毒测试 ( " + caseNum + " | " + DisinfectionTestActivity.this.getCaseNum() + " )：\n请稍等3秒钟,确认下列事项：\n 1.设备能正常喷雾.\n2.喷雾时雾化片电流的值稳定在700-1100 \n3.请确定风机类型及转速值。0.16风机喷雾时转速稳定在80-120；0.8A风机喷雾时转速稳定在130-170");
                intRef.element = 0;
                RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
                if (rbInterface != null) {
                    rbInterface.openSprayDevice(true, true);
                }
            }
        });
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.DisinfectionTestActivity$setCases$7
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
                int caseNum = DisinfectionTestActivity.this.getCaseNum() - DisinfectionTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) DisinfectionTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("消毒测试 ( " + caseNum + " | " + DisinfectionTestActivity.this.getCaseNum() + " )：\n请稍等3秒钟,确认下列事项：\n 1.设备能正常喷雾.\n2.喷雾时雾化片电流的值稳定在700-1100 \n3.请确定风机类型及转速值。0.16风机喷雾时转速稳定在80-120；0.8A风机喷雾时转速稳定在130-170");
                intRef.element = 1;
                RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
                if (rbInterface != null) {
                    rbInterface.openSprayDevice(false, true);
                }
            }
        });
        this.caseNum = this.allCases.size();
    }

    public final LinkedHashMap<String, String> getKeyInfo() {
        return this.keyInfo;
    }

    public final String toStr(LinkedHashMap<String, String> toStr) {
        Intrinsics.checkParameterIsNotNull(toStr, "$this$toStr");
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : toStr.entrySet()) {
            Pdlog.m3273d(this.TAG, entry.getKey() + " : " + entry.getValue() + '\n');
            sb.append(entry.getKey() + " : " + entry.getValue() + '\n');
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "strBuilder.toString()");
        return sb2;
    }

    public final void showKeyInfo(String string) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        AppCompatTextView tvDebugInfo = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvDebugInfo);
        Intrinsics.checkExpressionValueIsNotNull(tvDebugInfo, "tvDebugInfo");
        tvDebugInfo.setText(string);
    }
}
