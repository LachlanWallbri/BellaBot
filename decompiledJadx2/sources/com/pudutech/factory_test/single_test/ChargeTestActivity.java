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
import com.pudutech.factory_test.single_test.ChargeTestActivity;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import com.pudutech.factory_test.test_pack.TestItem;
import com.pudutech.factory_test.test_pack.TestStage;
import com.pudutech.factory_test.test_pack.TestStatus;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChargeTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001:\u0001ZB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010L\u001a\u00020>J\u0012\u0010M\u001a\u00020>2\b\u0010N\u001a\u0004\u0018\u00010OH\u0014J\b\u0010P\u001a\u00020>H\u0014J\u0006\u0010Q\u001a\u00020>J\u0006\u0010R\u001a\u00020>J\u0006\u0010S\u001a\u00020>J\u0006\u0010T\u001a\u00020>J\u0006\u0010U\u001a\u00020>J\u0006\u0010V\u001a\u00020>J\u000e\u0010W\u001a\u00020>2\u0006\u0010X\u001a\u00020\u0004J*\u0010Y\u001a\u00020\u0004*\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0011j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004`\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\r\"\u0004\b\u000e\u0010\u000fR1\u0010\u0010\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0011j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004`\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001b\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001e\u0010!\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010&\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001c\u0010'\u001a\u0004\u0018\u00010(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001e\u00103\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b4\u0010\u001d\"\u0004\b5\u0010\u001fR\u001a\u00106\u001a\u000207X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\"\u0010<\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010=X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001a\u0010C\u001a\u00020DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u001a\u0010I\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u0018\"\u0004\bK\u0010\u001a¨\u0006["}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/ChargeTestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "cnt", "", "getCnt", "()I", "setCnt", "(I)V", "isChecking", "", "()Z", "setChecking", "(Z)V", "keyInfo", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "getKeyInfo", "()Ljava/util/LinkedHashMap;", "lastCurrent", "", "getLastCurrent", "()D", "setLastCurrent", "(D)V", "lastInvokeVoltage", "getLastInvokeVoltage", "()Ljava/lang/Double;", "setLastInvokeVoltage", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "lastPowerPercent", "getLastPowerPercent", "()Ljava/lang/Integer;", "setLastPowerPercent", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "lastState", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "getLastState", "()Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "setLastState", "(Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;)V", "lastTmpTimestamps", "", "getLastTmpTimestamps", "()J", "setLastTmpTimestamps", "(J)V", "lastVoltage", "getLastVoltage", "setLastVoltage", "mTestItem", "Lcom/pudutech/factory_test/test_pack/TestItem;", "getMTestItem", "()Lcom/pudutech/factory_test/test_pack/TestItem;", "setMTestItem", "(Lcom/pudutech/factory_test/test_pack/TestItem;)V", "onCharge", "Lkotlin/Function0;", "", "getOnCharge", "()Lkotlin/jvm/functions/Function0;", "setOnCharge", "(Lkotlin/jvm/functions/Function0;)V", "step", "Lcom/pudutech/factory_test/single_test/ChargeTestActivity$Step;", "getStep", "()Lcom/pudutech/factory_test/single_test/ChargeTestActivity$Step;", "setStep", "(Lcom/pudutech/factory_test/single_test/ChargeTestActivity$Step;)V", "sumCurrent", "getSumCurrent", "setSumCurrent", "FSM", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "removeListener", "runStepCharging", "runStepFail", "runStepIDLE", "runStepSuccess", "setListener", "showKeyInfo", "string", "toStr", "Step", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class ChargeTestActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private int cnt;
    private boolean isChecking;
    private double lastCurrent;
    private Double lastInvokeVoltage;
    private Integer lastPowerPercent;
    private ChargeState lastState;
    private long lastTmpTimestamps;
    private Double lastVoltage;
    public TestItem mTestItem;
    private Function0<Unit> onCharge;
    private double sumCurrent;
    private final String TAG = "ChargeTestActivity";
    private Step step = Step.IDLE;
    private final LinkedHashMap<String, String> keyInfo = new LinkedHashMap<>();

    /* compiled from: ChargeTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/ChargeTestActivity$Step;", "", "(Ljava/lang/String;I)V", "IDLE", "CHARGING", "FAIL", "SUCCESS", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum Step {
        IDLE,
        CHARGING,
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
            $EnumSwitchMapping$0[Step.CHARGING.ordinal()] = 2;
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
                if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.CHARGE) && testItem2.getStage() == fromValue) {
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
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ChargeTestActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = ChargeTestActivity.this.TAG;
                Pdlog.m3275i(str, "click quit");
                ChargeTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnErrorQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ChargeTestActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = ChargeTestActivity.this.TAG;
                Pdlog.m3275i(str, "click error quit");
                ChargeTestActivity.this.getMTestItem().setStatus(TestStatus.FAIL);
                ChargeTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ChargeTestActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = ChargeTestActivity.this.TAG;
                Pdlog.m3275i(str, "click reset");
                ChargeTestActivity.this.setStep(ChargeTestActivity.Step.IDLE);
                ChargeTestActivity.this.FSM();
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
        this.onCharge = (Function0) null;
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
            runStepCharging();
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
        this.isChecking = false;
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("充电测试:\n请确认机器人没有连接充电器后点击 ‘开始测试’ ");
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        this.onCharge = (Function0) null;
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ((Button) LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("开始测试"), 0.0f, 4, null).get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ChargeTestActivity$runStepIDLE$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (ChargeTestActivity.this.getLastState() == ChargeState.Idle || ChargeTestActivity.this.getLastState() == null) {
                    ChargeTestActivity.this.setStep(ChargeTestActivity.Step.CHARGING);
                } else {
                    ChargeTestActivity.this.setStep(ChargeTestActivity.Step.FAIL);
                    ChargeTestActivity.this.getMTestItem().setFailDescription("开始测试前已经处于充电状态");
                }
                ChargeTestActivity.this.FSM();
            }
        });
    }

    public final Function0<Unit> getOnCharge() {
        return this.onCharge;
    }

    public final void setOnCharge(Function0<Unit> function0) {
        this.onCharge = function0;
    }

    /* renamed from: isChecking, reason: from getter */
    public final boolean getIsChecking() {
        return this.isChecking;
    }

    public final void setChecking(boolean z) {
        this.isChecking = z;
    }

    public final void runStepCharging() {
        ((LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions)).removeAllViews();
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("充电测试 ( 1 | 2 ):\n请接入充电器");
        Pdlog.m3273d(this.TAG, "runStepCharging. wait connect charger");
        this.lastInvokeVoltage = Double.valueOf(0.0d);
        this.onCharge = new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.ChargeTestActivity$runStepCharging$1
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
                ChargeTestActivity.this.setOnCharge((Function0) null);
                Double lastInvokeVoltage = ChargeTestActivity.this.getLastInvokeVoltage();
                if (lastInvokeVoltage == null) {
                    Intrinsics.throwNpe();
                }
                double d = 20;
                if (lastInvokeVoltage.doubleValue() > d && ChargeTestActivity.this.getLastCurrent() > 0.4d) {
                    ChargeTestActivity.this.setChecking(true);
                    ChargeTestActivity.this.setSumCurrent(0.0d);
                    ChargeTestActivity.this.setCnt(0);
                    AppCompatTextView tvGuide2 = (AppCompatTextView) ChargeTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                    Intrinsics.checkExpressionValueIsNotNull(tvGuide2, "tvGuide");
                    tvGuide2.setText("充电测试 ( 2 | 2 ):\n请拔出充电器");
                    ChargeTestActivity.this.setOnCharge(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.ChargeTestActivity$runStepCharging$1.1
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
                            Double lastInvokeVoltage2 = ChargeTestActivity.this.getLastInvokeVoltage();
                            if (lastInvokeVoltage2 == null) {
                                Intrinsics.throwNpe();
                            }
                            if (lastInvokeVoltage2.doubleValue() <= 20) {
                                ChargeTestActivity.this.setChecking(false);
                                double sumCurrent = ChargeTestActivity.this.getCnt() > 0 ? ChargeTestActivity.this.getSumCurrent() / ChargeTestActivity.this.getCnt() : 0.0d;
                                ChargeTestActivity.this.getKeyInfo().put("平均充电电流", String.valueOf(sumCurrent >= 0.1d ? sumCurrent : 0.0d));
                                ChargeTestActivity.this.getKeyInfo().put("计数", String.valueOf(ChargeTestActivity.this.getCnt()));
                                if (sumCurrent > 2.0d) {
                                    ChargeTestActivity.this.setStep(ChargeTestActivity.Step.SUCCESS);
                                } else {
                                    ChargeTestActivity.this.setStep(ChargeTestActivity.Step.FAIL);
                                    ChargeTestActivity.this.getMTestItem().setFailDescription("充电电流异常，或太快拔出充电器");
                                }
                            } else {
                                ChargeTestActivity.this.setStep(ChargeTestActivity.Step.FAIL);
                                ChargeTestActivity.this.getMTestItem().setFailDescription("检测到触发事件不是拔出充电器");
                            }
                            ChargeTestActivity.this.FSM();
                        }
                    });
                    return;
                }
                Double lastInvokeVoltage2 = ChargeTestActivity.this.getLastInvokeVoltage();
                if (lastInvokeVoltage2 == null) {
                    Intrinsics.throwNpe();
                }
                if (lastInvokeVoltage2.doubleValue() > d && ChargeTestActivity.this.getLastCurrent() <= 0.4d) {
                    ChargeTestActivity.this.setStep(ChargeTestActivity.Step.FAIL);
                    ChargeTestActivity.this.getMTestItem().setFailDescription("充电异常，检测到有充电电压，但无充电电流");
                    ChargeTestActivity.this.FSM();
                } else {
                    ChargeTestActivity.this.setStep(ChargeTestActivity.Step.FAIL);
                    ChargeTestActivity.this.getMTestItem().setFailDescription("检测到触发事件不是接入充电器");
                    ChargeTestActivity.this.FSM();
                }
            }
        };
    }

    public final void runStepSuccess() {
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setStatus(TestStatus.SUCCESS);
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("充电测试结束\n测试结果是：成功");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("完成", "下一个未测试项"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ChargeTestActivity$runStepSuccess$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChargeTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ChargeTestActivity$runStepSuccess$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> action;
                TestItem nextUntested = AllTestItem.INSTANCE.getNextUntested(ChargeTestActivity.this.getMTestItem());
                if (nextUntested != null && (action = nextUntested.getAction()) != null) {
                    action.invoke();
                }
                ChargeTestActivity.this.finish();
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
        sb.append("充电测试结束\n测试结果是：失败\n描述:");
        TestItem testItem2 = this.mTestItem;
        if (testItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        sb.append(testItem2.getFailDescription());
        tvGuide.setText(sb.toString());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重新测试"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ChargeTestActivity$runStepFail$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChargeTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ChargeTestActivity$runStepFail$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((AppCompatButton) ChargeTestActivity.this._$_findCachedViewById(C4491R.id.btnReset)).callOnClick();
            }
        });
    }

    public final ChargeState getLastState() {
        return this.lastState;
    }

    public final void setLastState(ChargeState chargeState) {
        this.lastState = chargeState;
    }

    public final Integer getLastPowerPercent() {
        return this.lastPowerPercent;
    }

    public final void setLastPowerPercent(Integer num) {
        this.lastPowerPercent = num;
    }

    public final double getSumCurrent() {
        return this.sumCurrent;
    }

    public final void setSumCurrent(double d) {
        this.sumCurrent = d;
    }

    public final int getCnt() {
        return this.cnt;
    }

    public final void setCnt(int i) {
        this.cnt = i;
    }

    public final long getLastTmpTimestamps() {
        return this.lastTmpTimestamps;
    }

    public final void setLastTmpTimestamps(long j) {
        this.lastTmpTimestamps = j;
    }

    public final Double getLastVoltage() {
        return this.lastVoltage;
    }

    public final void setLastVoltage(Double d) {
        this.lastVoltage = d;
    }

    public final Double getLastInvokeVoltage() {
        return this.lastInvokeVoltage;
    }

    public final void setLastInvokeVoltage(Double d) {
        this.lastInvokeVoltage = d;
    }

    public final double getLastCurrent() {
        return this.lastCurrent;
    }

    public final void setLastCurrent(double d) {
        this.lastCurrent = d;
    }

    public final void setListener() {
        byte[] bArr = {115, 116, 9};
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface != null) {
            hdInterface.addCANDataListener(this.TAG, bArr, new ChargeTestActivity$setListener$1(this));
        }
    }

    public final void removeListener() {
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface != null) {
            hdInterface.removeListener(this.TAG);
        }
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

    public final void showKeyInfo(String string) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        AppCompatTextView tvDebugInfo = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvDebugInfo);
        Intrinsics.checkExpressionValueIsNotNull(tvDebugInfo, "tvDebugInfo");
        tvDebugInfo.setText(string);
    }
}
