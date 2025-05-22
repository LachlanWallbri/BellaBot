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
import com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import com.pudutech.factory_test.C4491R;
import com.pudutech.factory_test.LayoutHelperKt;
import com.pudutech.factory_test.ServiceConnectionKt;
import com.pudutech.factory_test.single_test.NFCTestActivity;
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

/* compiled from: NFCTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u00011B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010$\u001a\u00020\bJ\u0006\u0010%\u001a\u00020\bJ\u0012\u0010&\u001a\u00020\b2\b\u0010'\u001a\u0004\u0018\u00010(H\u0014J\b\u0010)\u001a\u00020\bH\u0014J\u0006\u0010*\u001a\u00020\bJ\u0006\u0010+\u001a\u00020\bJ\u0006\u0010,\u001a\u00020\bJ\u0006\u0010-\u001a\u00020\bJ\u0011\u0010.\u001a\u00020/H\u0086@ø\u0001\u0000¢\u0006\u0002\u00100R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R-\u0010\u0005\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007`\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#\u0082\u0002\u0004\n\u0002\b\u0019¨\u00062"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/NFCTestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "allCases", "Ljava/util/ArrayList;", "Lkotlin/Function0;", "", "Lkotlin/collections/ArrayList;", "getAllCases", "()Ljava/util/ArrayList;", "caseNum", "", "getCaseNum", "()I", "setCaseNum", "(I)V", "job", "Lkotlinx/coroutines/Job;", "getJob", "()Lkotlinx/coroutines/Job;", "setJob", "(Lkotlinx/coroutines/Job;)V", "mTestItem", "Lcom/pudutech/factory_test/test_pack/TestItem;", "getMTestItem", "()Lcom/pudutech/factory_test/test_pack/TestItem;", "setMTestItem", "(Lcom/pudutech/factory_test/test_pack/TestItem;)V", "step", "Lcom/pudutech/factory_test/single_test/NFCTestActivity$Step;", "getStep", "()Lcom/pudutech/factory_test/single_test/NFCTestActivity$Step;", "setStep", "(Lcom/pudutech/factory_test/single_test/NFCTestActivity$Step;)V", "FSM", "checkNFCExist", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "runStepChecking", "runStepFail", "runStepSuccess", "setCases", "waitDetected", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Step", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class NFCTestActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private int caseNum;
    private Job job;
    public TestItem mTestItem;
    private final String TAG = "TouchSensorTestActivity";
    private final ArrayList<Function0<Unit>> allCases = new ArrayList<>();
    private Step step = Step.IDLE;

    /* compiled from: NFCTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/NFCTestActivity$Step;", "", "(Ljava/lang/String;I)V", "IDLE", "CHECKING", "FAIL", "SUCCESS", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
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
                if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.NFC) && testItem2.getStage() == fromValue) {
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
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.NFCTestActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Pdlog.m3275i(NFCTestActivity.this.TAG, "click quit");
                NFCTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnErrorQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.NFCTestActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Pdlog.m3275i(NFCTestActivity.this.TAG, "click error quit");
                NFCTestActivity.this.getMTestItem().setStatus(TestStatus.FAIL);
                NFCTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.NFCTestActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Pdlog.m3275i(NFCTestActivity.this.TAG, "click reset");
                NFCTestActivity.this.getMTestItem().setStatus(TestStatus.TESTING);
                NFCTestActivity.this.setStep(NFCTestActivity.Step.IDLE);
                NFCTestActivity.this.FSM();
            }
        });
        checkNFCExist();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
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
            runStepChecking();
        } else if (i == 3) {
            runStepSuccess();
        } else {
            if (i != 4) {
                return;
            }
            runStepFail();
        }
    }

    public final void checkNFCExist() {
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("请确认该机器是否有NFC，选择无NFC则会直接跳转到测试成功");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("有NFC", "无NFC"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.NFCTestActivity$checkNFCExist$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NFCTestActivity.this.FSM();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.NFCTestActivity$checkNFCExist$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NFCTestActivity.this.setStep(NFCTestActivity.Step.SUCCESS);
                NFCTestActivity.this.FSM();
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
        tvGuide.setText("NFC模块测试结束\n测试结果是：成功");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("完成", "下一个未测试项"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.NFCTestActivity$runStepSuccess$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NFCTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.NFCTestActivity$runStepSuccess$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> action;
                TestItem nextUntested = AllTestItem.INSTANCE.getNextUntested(NFCTestActivity.this.getMTestItem());
                if (nextUntested != null && (action = nextUntested.getAction()) != null) {
                    action.invoke();
                }
                NFCTestActivity.this.finish();
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
        sb.append("NFC模块测试结束\n测试结果是：失败\n描述：");
        TestItem testItem2 = this.mTestItem;
        if (testItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        sb.append(testItem2.getFailDescription());
        tvGuide.setText(sb.toString());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重新测试"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.NFCTestActivity$runStepFail$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NFCTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.NFCTestActivity$runStepFail$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((AppCompatButton) NFCTestActivity.this._$_findCachedViewById(C4491R.id.btnReset)).callOnClick();
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
        this.allCases.clear();
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.NFCTestActivity$setCases$1
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
                int caseNum = NFCTestActivity.this.getCaseNum() - NFCTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) NFCTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("NFC模块测试 ( " + caseNum + " | " + NFCTestActivity.this.getCaseNum() + " )：\n请刷卡");
                NFCTestActivity nFCTestActivity = NFCTestActivity.this;
                launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C45221(null), 2, null);
                nFCTestActivity.setJob(launch$default);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: NFCTestActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.NFCTestActivity$setCases$1$1", m3970f = "NFCTestActivity.kt", m3971i = {0}, m3972l = {136}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
            /* renamed from: com.pudutech.factory_test.single_test.NFCTestActivity$setCases$1$1 */
            /* loaded from: classes.dex */
            public static final class C45221 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5195p$;

                C45221(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C45221 c45221 = new C45221(completion);
                    c45221.f5195p$ = (CoroutineScope) obj;
                    return c45221;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C45221) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f5195p$;
                        NFCTestActivity nFCTestActivity = NFCTestActivity.this;
                        this.L$0 = coroutineScope;
                        this.label = 1;
                        obj = nFCTestActivity.waitDetected(this);
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
                        NFCTestActivity.this.setStep(NFCTestActivity.Step.FAIL);
                        NFCTestActivity.this.getMTestItem().setFailDescription("NFC模块无法触发");
                    }
                    NFCTestActivity.this.FSM();
                    return Unit.INSTANCE;
                }
            }
        });
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.NFCTestActivity$setCases$2
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
                int caseNum = NFCTestActivity.this.getCaseNum() - NFCTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) NFCTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("NFC模块测试 ( " + caseNum + " | " + NFCTestActivity.this.getCaseNum() + " )：\n请刷卡");
                NFCTestActivity nFCTestActivity = NFCTestActivity.this;
                launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C45231(null), 2, null);
                nFCTestActivity.setJob(launch$default);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: NFCTestActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.NFCTestActivity$setCases$2$1", m3970f = "NFCTestActivity.kt", m3971i = {0}, m3972l = {149}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
            /* renamed from: com.pudutech.factory_test.single_test.NFCTestActivity$setCases$2$1 */
            /* loaded from: classes.dex */
            public static final class C45231 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5196p$;

                C45231(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C45231 c45231 = new C45231(completion);
                    c45231.f5196p$ = (CoroutineScope) obj;
                    return c45231;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C45231) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f5196p$;
                        NFCTestActivity nFCTestActivity = NFCTestActivity.this;
                        this.L$0 = coroutineScope;
                        this.label = 1;
                        obj = nFCTestActivity.waitDetected(this);
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
                        NFCTestActivity.this.setStep(NFCTestActivity.Step.FAIL);
                        NFCTestActivity.this.getMTestItem().setFailDescription("NFC模块无法触发");
                    }
                    NFCTestActivity.this.FSM();
                    return Unit.INSTANCE;
                }
            }
        });
        this.caseNum = this.allCases.size();
    }

    public final Object waitDetected(Continuation<? super Boolean> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        Pdlog.m3273d(this.TAG, "wait");
        IRecycleRobotListener.Stub stub = new IRecycleRobotListener.Stub() { // from class: com.pudutech.factory_test.single_test.NFCTestActivity$waitDetected$$inlined$suspendCancellableCoroutine$lambda$1
            @Override // com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener
            public void onRemoteDeviceMsg(byte[] p0) {
            }

            @Override // com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener
            public void onRemoteDeviceResponseChecking(int p0) {
            }

            @Override // com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener
            public void onNFCSignDetected(String p0) {
                Pdlog.m3273d(this.TAG, "onNFCSignDetected id=" + p0);
                if (CancellableContinuation.this.isActive()) {
                    RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
                    if (rbInterface != null) {
                        rbInterface.removeListener(this.TAG);
                    }
                    CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                    Result.Companion companion = Result.INSTANCE;
                    cancellableContinuation.resumeWith(Result.m4510constructorimpl(true));
                }
            }
        };
        cancellableContinuationImpl2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: com.pudutech.factory_test.single_test.NFCTestActivity$waitDetected$$inlined$suspendCancellableCoroutine$lambda$2
            /* JADX INFO: Access modifiers changed from: package-private */
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
                Pdlog.m3273d(NFCTestActivity.this.TAG, "cancel wait");
                RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
                if (rbInterface != null) {
                    rbInterface.removeListener(NFCTestActivity.this.TAG);
                }
            }
        });
        RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
        if (rbInterface != null) {
            rbInterface.addRecycleRobotListener(this.TAG, stub);
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
