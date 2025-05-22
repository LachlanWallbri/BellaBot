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
import com.pudutech.bumblebee.robot.aidl.IRobotListener;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import com.pudutech.bumblebee.robot.aidl.serialize.FaultLevel;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDevice;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDeviceStatus;
import com.pudutech.bumblebee.robot.aidl.serialize.PowerOffEvent;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchPlace;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchState;
import com.pudutech.factory_test.C4491R;
import com.pudutech.factory_test.LayoutHelperKt;
import com.pudutech.factory_test.ServiceConnectionKt;
import com.pudutech.factory_test.single_test.TouchSensorTestActivity;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import com.pudutech.factory_test.test_pack.TestItem;
import com.pudutech.factory_test.test_pack.TestStage;
import com.pudutech.factory_test.test_pack.TestStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: TouchSensorTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u00013B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010$\u001a\u00020\bJ\u0012\u0010%\u001a\u00020\b2\b\u0010&\u001a\u0004\u0018\u00010'H\u0014J\u0006\u0010(\u001a\u00020\bJ\u0006\u0010)\u001a\u00020\bJ\u0006\u0010*\u001a\u00020\bJ\u0006\u0010+\u001a\u00020\bJ\u0006\u0010,\u001a\u00020\bJ\u0006\u0010-\u001a\u00020\bJ\u0019\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0086@ø\u0001\u0000¢\u0006\u0002\u00102R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R-\u0010\u0005\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007`\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/TouchSensorTestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "allCases", "Ljava/util/ArrayList;", "Lkotlin/Function0;", "", "Lkotlin/collections/ArrayList;", "getAllCases", "()Ljava/util/ArrayList;", "caseNum", "", "getCaseNum", "()I", "setCaseNum", "(I)V", "job", "Lkotlinx/coroutines/Job;", "getJob", "()Lkotlinx/coroutines/Job;", "setJob", "(Lkotlinx/coroutines/Job;)V", "mTestItem", "Lcom/pudutech/factory_test/test_pack/TestItem;", "getMTestItem", "()Lcom/pudutech/factory_test/test_pack/TestItem;", "setMTestItem", "(Lcom/pudutech/factory_test/test_pack/TestItem;)V", "step", "Lcom/pudutech/factory_test/single_test/TouchSensorTestActivity$Step;", "getStep", "()Lcom/pudutech/factory_test/single_test/TouchSensorTestActivity$Step;", "setStep", "(Lcom/pudutech/factory_test/single_test/TouchSensorTestActivity$Step;)V", "FSM", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "runStepFail", "runStepPlaying", "runStepSuccess", "setBellaCases", "setCases", "setHolaCases", "waitTouch", "", "place", "Lcom/pudutech/bumblebee/robot/aidl/serialize/TouchPlace;", "(Lcom/pudutech/bumblebee/robot/aidl/serialize/TouchPlace;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Step", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class TouchSensorTestActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private int caseNum;
    private Job job;
    public TestItem mTestItem;
    private final String TAG = "TouchSensorTestActivity";
    private final ArrayList<Function0<Unit>> allCases = new ArrayList<>();
    private Step step = Step.IDLE;

    /* compiled from: TouchSensorTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/TouchSensorTestActivity$Step;", "", "(Ljava/lang/String;I)V", "IDLE", "CHECKING", "FAIL", "SUCCESS", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum Step {
        IDLE,
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
            $EnumSwitchMapping$0[Step.CHECKING.ordinal()] = 2;
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
                if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.TOUCH_SENSOR) && testItem2.getStage() == fromValue) {
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
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TouchSensorTestActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Pdlog.m3275i(TouchSensorTestActivity.this.TAG, "click quit");
                TouchSensorTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnErrorQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TouchSensorTestActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Pdlog.m3275i(TouchSensorTestActivity.this.TAG, "click error quit");
                TouchSensorTestActivity.this.getMTestItem().setStatus(TestStatus.FAIL);
                TouchSensorTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TouchSensorTestActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Pdlog.m3275i(TouchSensorTestActivity.this.TAG, "click reset");
                TouchSensorTestActivity.this.getMTestItem().setStatus(TestStatus.TESTING);
                TouchSensorTestActivity.this.setStep(TouchSensorTestActivity.Step.IDLE);
                TouchSensorTestActivity.this.FSM();
            }
        });
        FSM();
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
            TestItem testItem = this.mTestItem;
            if (testItem == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
            }
            testItem.setFailDescription((String) null);
            setCases();
            this.step = Step.CHECKING;
            FSM();
            return;
        }
        if (i == 2) {
            runStepPlaying();
        } else if (i == 3) {
            runStepSuccess();
        } else {
            if (i != 4) {
                return;
            }
            runStepFail();
        }
    }

    public final void runStepPlaying() {
        Pdlog.m3273d(this.TAG, "runStepPlaying left size=" + this.allCases.size());
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
        tvGuide.setText("触控和功能键测试结束\n测试结果是：成功");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("完成", "下一个未测试项"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TouchSensorTestActivity$runStepSuccess$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TouchSensorTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TouchSensorTestActivity$runStepSuccess$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> action;
                TestItem nextUntested = AllTestItem.INSTANCE.getNextUntested(TouchSensorTestActivity.this.getMTestItem());
                if (nextUntested != null && (action = nextUntested.getAction()) != null) {
                    action.invoke();
                }
                TouchSensorTestActivity.this.finish();
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
        sb.append("触控和功能键测试结束\n测试结果是：失败\n描述：");
        TestItem testItem2 = this.mTestItem;
        if (testItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        sb.append(testItem2.getFailDescription());
        tvGuide.setText(sb.toString());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重新测试"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TouchSensorTestActivity$runStepFail$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TouchSensorTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TouchSensorTestActivity$runStepFail$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((AppCompatButton) TouchSensorTestActivity.this._$_findCachedViewById(C4491R.id.btnReset)).callOnClick();
            }
        });
    }

    public final Job getJob() {
        return this.job;
    }

    public final void setJob(Job job) {
        this.job = job;
    }

    public final void setCases() {
        Pdlog.m3273d(this.TAG, "setCases ");
        if (AllTestItem.INSTANCE.isBella()) {
            setBellaCases();
        }
        if (AllTestItem.INSTANCE.isHola()) {
            setHolaCases();
        }
    }

    public final void setBellaCases() {
        this.allCases.clear();
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.TouchSensorTestActivity$setBellaCases$1
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
                Job launch$default;
                int caseNum = TouchSensorTestActivity.this.getCaseNum() - TouchSensorTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) TouchSensorTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("触控和功能键测试 ( " + caseNum + " | " + TouchSensorTestActivity.this.getCaseNum() + " )：\n请触发左耳触摸传感器");
                TouchSensorTestActivity touchSensorTestActivity = TouchSensorTestActivity.this;
                launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C45291(null), 2, null);
                touchSensorTestActivity.setJob(launch$default);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: TouchSensorTestActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.TouchSensorTestActivity$setBellaCases$1$1", m3970f = "TouchSensorTestActivity.kt", m3971i = {0}, m3972l = {126}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
            /* renamed from: com.pudutech.factory_test.single_test.TouchSensorTestActivity$setBellaCases$1$1 */
            /* loaded from: classes.dex */
            public static final class C45291 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5200p$;

                C45291(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C45291 c45291 = new C45291(completion);
                    c45291.f5200p$ = (CoroutineScope) obj;
                    return c45291;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C45291) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f5200p$;
                        TouchSensorTestActivity touchSensorTestActivity = TouchSensorTestActivity.this;
                        TouchPlace touchPlace = TouchPlace.LeftEar;
                        this.L$0 = coroutineScope;
                        this.label = 1;
                        obj = touchSensorTestActivity.waitTouch(touchPlace, this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    if (!((Boolean) obj).booleanValue()) {
                        TouchSensorTestActivity.this.setStep(TouchSensorTestActivity.Step.FAIL);
                        TouchSensorTestActivity.this.getMTestItem().setFailDescription("左耳传感器无法触发");
                    }
                    TouchSensorTestActivity.this.FSM();
                    return Unit.INSTANCE;
                }
            }
        });
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.TouchSensorTestActivity$setBellaCases$2
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
                Job launch$default;
                int caseNum = TouchSensorTestActivity.this.getCaseNum() - TouchSensorTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) TouchSensorTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("触控和功能键测试 ( " + caseNum + " | " + TouchSensorTestActivity.this.getCaseNum() + " )：\n请触发右耳触摸传感器");
                TouchSensorTestActivity touchSensorTestActivity = TouchSensorTestActivity.this;
                launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C45301(null), 2, null);
                touchSensorTestActivity.setJob(launch$default);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: TouchSensorTestActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.TouchSensorTestActivity$setBellaCases$2$1", m3970f = "TouchSensorTestActivity.kt", m3971i = {0}, m3972l = {138}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
            /* renamed from: com.pudutech.factory_test.single_test.TouchSensorTestActivity$setBellaCases$2$1 */
            /* loaded from: classes.dex */
            public static final class C45301 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5201p$;

                C45301(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C45301 c45301 = new C45301(completion);
                    c45301.f5201p$ = (CoroutineScope) obj;
                    return c45301;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C45301) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f5201p$;
                        TouchSensorTestActivity touchSensorTestActivity = TouchSensorTestActivity.this;
                        TouchPlace touchPlace = TouchPlace.RightEar;
                        this.L$0 = coroutineScope;
                        this.label = 1;
                        obj = touchSensorTestActivity.waitTouch(touchPlace, this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    if (!((Boolean) obj).booleanValue()) {
                        TouchSensorTestActivity.this.setStep(TouchSensorTestActivity.Step.FAIL);
                        TouchSensorTestActivity.this.getMTestItem().setFailDescription("右耳传感器无法触发");
                    }
                    TouchSensorTestActivity.this.FSM();
                    return Unit.INSTANCE;
                }
            }
        });
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.TouchSensorTestActivity$setBellaCases$3
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
                Job launch$default;
                int caseNum = TouchSensorTestActivity.this.getCaseNum() - TouchSensorTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) TouchSensorTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("触控和功能键测试 ( " + caseNum + " | " + TouchSensorTestActivity.this.getCaseNum() + " )：\n请触发额头触摸传感器");
                TouchSensorTestActivity touchSensorTestActivity = TouchSensorTestActivity.this;
                launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C45311(null), 2, null);
                touchSensorTestActivity.setJob(launch$default);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: TouchSensorTestActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.TouchSensorTestActivity$setBellaCases$3$1", m3970f = "TouchSensorTestActivity.kt", m3971i = {0}, m3972l = {150}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
            /* renamed from: com.pudutech.factory_test.single_test.TouchSensorTestActivity$setBellaCases$3$1 */
            /* loaded from: classes.dex */
            public static final class C45311 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5202p$;

                C45311(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C45311 c45311 = new C45311(completion);
                    c45311.f5202p$ = (CoroutineScope) obj;
                    return c45311;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C45311) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f5202p$;
                        TouchSensorTestActivity touchSensorTestActivity = TouchSensorTestActivity.this;
                        TouchPlace touchPlace = TouchPlace.Head;
                        this.L$0 = coroutineScope;
                        this.label = 1;
                        obj = touchSensorTestActivity.waitTouch(touchPlace, this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    if (!((Boolean) obj).booleanValue()) {
                        TouchSensorTestActivity.this.setStep(TouchSensorTestActivity.Step.FAIL);
                        TouchSensorTestActivity.this.getMTestItem().setFailDescription("额头传感器无法触发");
                    }
                    TouchSensorTestActivity.this.FSM();
                    return Unit.INSTANCE;
                }
            }
        });
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.TouchSensorTestActivity$setBellaCases$4
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
                Job launch$default;
                int caseNum = TouchSensorTestActivity.this.getCaseNum() - TouchSensorTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) TouchSensorTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("触控和功能键测试 ( " + caseNum + " | " + TouchSensorTestActivity.this.getCaseNum() + " )：\n请按功能键开关");
                TouchSensorTestActivity touchSensorTestActivity = TouchSensorTestActivity.this;
                launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C45321(null), 2, null);
                touchSensorTestActivity.setJob(launch$default);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: TouchSensorTestActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.TouchSensorTestActivity$setBellaCases$4$1", m3970f = "TouchSensorTestActivity.kt", m3971i = {0}, m3972l = {162}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
            /* renamed from: com.pudutech.factory_test.single_test.TouchSensorTestActivity$setBellaCases$4$1 */
            /* loaded from: classes.dex */
            public static final class C45321 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5203p$;

                C45321(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C45321 c45321 = new C45321(completion);
                    c45321.f5203p$ = (CoroutineScope) obj;
                    return c45321;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C45321) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f5203p$;
                        TouchSensorTestActivity touchSensorTestActivity = TouchSensorTestActivity.this;
                        TouchPlace touchPlace = TouchPlace.FunctionButton;
                        this.L$0 = coroutineScope;
                        this.label = 1;
                        obj = touchSensorTestActivity.waitTouch(touchPlace, this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    if (!((Boolean) obj).booleanValue()) {
                        TouchSensorTestActivity.this.setStep(TouchSensorTestActivity.Step.FAIL);
                        TouchSensorTestActivity.this.getMTestItem().setFailDescription("功能键无法触发");
                    }
                    TouchSensorTestActivity.this.FSM();
                    return Unit.INSTANCE;
                }
            }
        });
        this.caseNum = this.allCases.size();
    }

    public final void setHolaCases() {
        this.allCases.clear();
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.TouchSensorTestActivity$setHolaCases$1
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
                Job launch$default;
                int caseNum = TouchSensorTestActivity.this.getCaseNum() - TouchSensorTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) TouchSensorTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("触控和功能键测试 ( " + caseNum + " | " + TouchSensorTestActivity.this.getCaseNum() + " )：\n请触发非接触传感器");
                TouchSensorTestActivity touchSensorTestActivity = TouchSensorTestActivity.this;
                launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C45331(null), 2, null);
                touchSensorTestActivity.setJob(launch$default);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: TouchSensorTestActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.TouchSensorTestActivity$setHolaCases$1$1", m3970f = "TouchSensorTestActivity.kt", m3971i = {0}, m3972l = {181}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
            /* renamed from: com.pudutech.factory_test.single_test.TouchSensorTestActivity$setHolaCases$1$1 */
            /* loaded from: classes.dex */
            public static final class C45331 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5204p$;

                C45331(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C45331 c45331 = new C45331(completion);
                    c45331.f5204p$ = (CoroutineScope) obj;
                    return c45331;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C45331) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f5204p$;
                        TouchSensorTestActivity touchSensorTestActivity = TouchSensorTestActivity.this;
                        TouchPlace touchPlace = TouchPlace.NonTouchKey;
                        this.L$0 = coroutineScope;
                        this.label = 1;
                        obj = touchSensorTestActivity.waitTouch(touchPlace, this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    if (!((Boolean) obj).booleanValue()) {
                        TouchSensorTestActivity.this.setStep(TouchSensorTestActivity.Step.FAIL);
                        TouchSensorTestActivity.this.getMTestItem().setFailDescription("非接触传感器无法触发");
                    }
                    TouchSensorTestActivity.this.FSM();
                    return Unit.INSTANCE;
                }
            }
        });
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.TouchSensorTestActivity$setHolaCases$2
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
                Job launch$default;
                int caseNum = TouchSensorTestActivity.this.getCaseNum() - TouchSensorTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) TouchSensorTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("触控和功能键测试 ( " + caseNum + " | " + TouchSensorTestActivity.this.getCaseNum() + " )：\n请触摸功能键开关");
                TouchSensorTestActivity touchSensorTestActivity = TouchSensorTestActivity.this;
                launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C45341(null), 2, null);
                touchSensorTestActivity.setJob(launch$default);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: TouchSensorTestActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.TouchSensorTestActivity$setHolaCases$2$1", m3970f = "TouchSensorTestActivity.kt", m3971i = {0}, m3972l = {193}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
            /* renamed from: com.pudutech.factory_test.single_test.TouchSensorTestActivity$setHolaCases$2$1 */
            /* loaded from: classes.dex */
            public static final class C45341 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5205p$;

                C45341(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C45341 c45341 = new C45341(completion);
                    c45341.f5205p$ = (CoroutineScope) obj;
                    return c45341;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C45341) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f5205p$;
                        TouchSensorTestActivity touchSensorTestActivity = TouchSensorTestActivity.this;
                        TouchPlace touchPlace = TouchPlace.TouchKey;
                        this.L$0 = coroutineScope;
                        this.label = 1;
                        obj = touchSensorTestActivity.waitTouch(touchPlace, this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    if (!((Boolean) obj).booleanValue()) {
                        TouchSensorTestActivity.this.setStep(TouchSensorTestActivity.Step.FAIL);
                        TouchSensorTestActivity.this.getMTestItem().setFailDescription("功能键无法触发");
                    }
                    TouchSensorTestActivity.this.FSM();
                    return Unit.INSTANCE;
                }
            }
        });
        this.caseNum = this.allCases.size();
    }

    public final Object waitTouch(final TouchPlace touchPlace, Continuation<? super Boolean> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        Pdlog.m3273d(this.TAG, "waitTouch place=" + touchPlace);
        IRobotListener.Stub stub = new IRobotListener.Stub() { // from class: com.pudutech.factory_test.single_test.TouchSensorTestActivity$waitTouch$$inlined$suspendCancellableCoroutine$lambda$1
            @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
            public void onDeviceStatusChange(PeripheralDevice p0, PeripheralDeviceStatus p1, String p2) {
            }

            @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
            public void onFault(FaultLevel p0, String p1) {
            }

            @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
            public void onPowerOffEvent(PowerOffEvent p0) {
            }

            @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
            public void onTouch(TouchPlace p0, TouchState p1) {
                Pdlog.m3273d(this.TAG, "onTouch p0=" + p0 + "  p1=" + p1);
                if (p1 == TouchState.DOWN) {
                    if (p0 == touchPlace) {
                        if (CancellableContinuation.this.isActive()) {
                            CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                            Result.Companion companion = Result.INSTANCE;
                            cancellableContinuation.resumeWith(Result.m4510constructorimpl(true));
                            return;
                        }
                        return;
                    }
                    if (CancellableContinuation.this.isActive()) {
                        CancellableContinuation cancellableContinuation2 = CancellableContinuation.this;
                        Result.Companion companion2 = Result.INSTANCE;
                        cancellableContinuation2.resumeWith(Result.m4510constructorimpl(false));
                    }
                }
            }
        };
        cancellableContinuationImpl2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: com.pudutech.factory_test.single_test.TouchSensorTestActivity$waitTouch$$inlined$suspendCancellableCoroutine$lambda$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                Pdlog.m3273d(TouchSensorTestActivity.this.TAG, "cancel wait");
                RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
                if (rbInterface != null) {
                    rbInterface.removeListener(TouchSensorTestActivity.this.TAG);
                }
            }
        });
        RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
        if (rbInterface != null) {
            rbInterface.addListener(this.TAG, stub);
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
