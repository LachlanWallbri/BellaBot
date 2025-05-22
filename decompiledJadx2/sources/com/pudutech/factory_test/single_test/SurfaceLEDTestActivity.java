package com.pudutech.factory_test.single_test;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import com.pudutech.bumblebee.robot.aidl.serialize.SurfaceLED;
import com.pudutech.factory_test.C4491R;
import com.pudutech.factory_test.LayoutHelperKt;
import com.pudutech.factory_test.ServiceConnectionKt;
import com.pudutech.factory_test.single_test.SurfaceLEDTestActivity;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import com.pudutech.factory_test.test_pack.TestItem;
import com.pudutech.factory_test.test_pack.TestStage;
import com.pudutech.factory_test.test_pack.TestStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.StringsKt;

/* compiled from: SurfaceLEDTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u00017B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010#\u001a\u00020\u001bJ\u0006\u0010$\u001a\u00020\u001bJ&\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00142\u0006\u0010)\u001a\u00020\u00142\u0006\u0010*\u001a\u00020\u0014J\u0012\u0010+\u001a\u00020\u001b2\b\u0010,\u001a\u0004\u0018\u00010-H\u0014J\b\u0010.\u001a\u00020\u001bH\u0014J\u000e\u0010/\u001a\u00020\u001b2\u0006\u00100\u001a\u00020\u0014J\u0006\u00101\u001a\u00020\u001bJ\u0006\u00102\u001a\u00020\u001bJ\u0006\u00103\u001a\u00020\u001bJ\u0006\u00104\u001a\u00020\u001bJ\u0006\u00105\u001a\u00020\u001bJ\u0006\u00106\u001a\u00020\u001bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R*\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R-\u0010\u0019\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\u0006j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a`\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\nR\u001a\u0010\u001d\u001a\u00020\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"¨\u00068"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/SurfaceLEDTestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "btnOptions", "Ljava/util/ArrayList;", "Landroid/widget/Button;", "Lkotlin/collections/ArrayList;", "getBtnOptions", "()Ljava/util/ArrayList;", "setBtnOptions", "(Ljava/util/ArrayList;)V", "mTestItem", "Lcom/pudutech/factory_test/test_pack/TestItem;", "getMTestItem", "()Lcom/pudutech/factory_test/test_pack/TestItem;", "setMTestItem", "(Lcom/pudutech/factory_test/test_pack/TestItem;)V", "questionNum", "", "getQuestionNum", "()I", "setQuestionNum", "(I)V", "questions", "Lkotlin/Function0;", "", "getQuestions", "step", "Lcom/pudutech/factory_test/single_test/SurfaceLEDTestActivity$Step;", "getStep", "()Lcom/pudutech/factory_test/single_test/SurfaceLEDTestActivity$Step;", "setStep", "(Lcom/pudutech/factory_test/single_test/SurfaceLEDTestActivity$Step;)V", "FSM", "askNextQuestion", "controlRGB", "surfaceLED", "Lcom/pudutech/bumblebee/robot/aidl/serialize/SurfaceLED;", "r", C3898x.f4336e, "b", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "resetRightAnswer", "index", "runStepColor", "runStepConnectivity", "runStepFail", "runStepSuccess", "setColorQuestion", "setConnectivityQuestion", "Step", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class SurfaceLEDTestActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    public TestItem mTestItem;
    private int questionNum;
    private final String TAG = "SurfaceLEDTestActivity";
    private ArrayList<Button> btnOptions = new ArrayList<>();
    private final ArrayList<Function0<Unit>> questions = new ArrayList<>();
    private Step step = Step.IDLE;

    /* compiled from: SurfaceLEDTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/SurfaceLEDTestActivity$Step;", "", "(Ljava/lang/String;I)V", "IDLE", "CONNECTIVITY", "COLOR", "FAIL", "SUCCESS", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum Step {
        IDLE,
        CONNECTIVITY,
        COLOR,
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
            $EnumSwitchMapping$0[Step.CONNECTIVITY.ordinal()] = 2;
            $EnumSwitchMapping$0[Step.COLOR.ordinal()] = 3;
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        TestItem testItem;
        super.onCreate(savedInstanceState);
        setContentView(2131427369);
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
                if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.SURFACE_LED) && testItem2.getStage() == fromValue) {
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
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.SurfaceLEDTestActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = SurfaceLEDTestActivity.this.TAG;
                Pdlog.m3275i(str, "click quit");
                SurfaceLEDTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnErrorQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.SurfaceLEDTestActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = SurfaceLEDTestActivity.this.TAG;
                Pdlog.m3275i(str, "click error quit");
                SurfaceLEDTestActivity.this.getMTestItem().setStatus(TestStatus.FAIL);
                SurfaceLEDTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.SurfaceLEDTestActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = SurfaceLEDTestActivity.this.TAG;
                Pdlog.m3275i(str, "click reset");
                SurfaceLEDTestActivity.this.getMTestItem().setStatus(TestStatus.TESTING);
                SurfaceLEDTestActivity.this.setStep(SurfaceLEDTestActivity.Step.IDLE);
                SurfaceLEDTestActivity.this.FSM();
            }
        });
        FSM();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "onDestroy ");
        for (SurfaceLED surfaceLED : SurfaceLED.values()) {
            controlRGB(surfaceLED, 0, 0, 0);
        }
    }

    public final ArrayList<Button> getBtnOptions() {
        return this.btnOptions;
    }

    public final void setBtnOptions(ArrayList<Button> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.btnOptions = arrayList;
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

    public final void resetRightAnswer(final int index) {
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("resetRightAnswer index=");
        sb.append(index);
        sb.append(' ');
        Button button = this.btnOptions.get(index);
        Intrinsics.checkExpressionValueIsNotNull(button, "btnOptions[index]");
        sb.append(button.getText());
        final int i = 0;
        Pdlog.m3273d(str, sb.toString());
        for (Object obj : this.btnOptions) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            final Button button2 = (Button) obj;
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.SurfaceLEDTestActivity$resetRightAnswer$$inlined$forEachIndexed$lambda$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    String str2;
                    String str3;
                    if (this.getMTestItem().getStatus() != TestStatus.TESTING) {
                        this.getMTestItem().setStatus(TestStatus.TESTING);
                    }
                    str2 = this.TAG;
                    Pdlog.m3273d(str2, "click " + button2.getText() + " i=" + i);
                    if (i == index) {
                        str3 = this.TAG;
                        Pdlog.m3275i(str3, "right answer");
                    } else {
                        this.getMTestItem().setFailDescription("选择选项错误，可能是连线或者固件有故障");
                        this.setStep(SurfaceLEDTestActivity.Step.FAIL);
                    }
                    this.FSM();
                }
            });
            i = i2;
        }
    }

    public final ArrayList<Function0<Unit>> getQuestions() {
        return this.questions;
    }

    public final int getQuestionNum() {
        return this.questionNum;
    }

    public final void setQuestionNum(int i) {
        this.questionNum = i;
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
            setConnectivityQuestion();
            this.step = Step.CONNECTIVITY;
            FSM();
            return;
        }
        if (i == 2) {
            runStepConnectivity();
            return;
        }
        if (i == 3) {
            runStepColor();
        } else if (i == 4) {
            runStepSuccess();
        } else {
            if (i != 5) {
                return;
            }
            runStepFail();
        }
    }

    public final void runStepConnectivity() {
        if (this.questions.isEmpty()) {
            setColorQuestion();
            this.step = Step.COLOR;
            FSM();
            return;
        }
        askNextQuestion();
    }

    public final void runStepColor() {
        if (this.questions.isEmpty()) {
            this.step = Step.SUCCESS;
            FSM();
        } else {
            askNextQuestion();
        }
    }

    public final void runStepSuccess() {
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setStatus(TestStatus.SUCCESS);
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("灯带测试结束\n测试结果是：成功");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList<Button> layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("完成", "下一个未测试项"), 0.0f, 4, null);
        this.btnOptions = layoutBtnOptions$default;
        layoutBtnOptions$default.get(0).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.SurfaceLEDTestActivity$runStepSuccess$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SurfaceLEDTestActivity.this.finish();
            }
        });
        this.btnOptions.get(1).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.SurfaceLEDTestActivity$runStepSuccess$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> action;
                TestItem nextUntested = AllTestItem.INSTANCE.getNextUntested(SurfaceLEDTestActivity.this.getMTestItem());
                if (nextUntested != null && (action = nextUntested.getAction()) != null) {
                    action.invoke();
                }
                SurfaceLEDTestActivity.this.finish();
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
        sb.append("灯带测试结束\n测试结果是：失败\n描述：");
        TestItem testItem2 = this.mTestItem;
        if (testItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        sb.append(testItem2.getFailDescription());
        tvGuide.setText(sb.toString());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList<Button> layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重新测试"), 0.0f, 4, null);
        this.btnOptions = layoutBtnOptions$default;
        layoutBtnOptions$default.get(0).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.SurfaceLEDTestActivity$runStepFail$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SurfaceLEDTestActivity.this.finish();
            }
        });
        this.btnOptions.get(1).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.SurfaceLEDTestActivity$runStepFail$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((AppCompatButton) SurfaceLEDTestActivity.this._$_findCachedViewById(C4491R.id.btnReset)).callOnClick();
            }
        });
    }

    public final void askNextQuestion() {
        int nextInt = Random.INSTANCE.nextInt(this.questions.size());
        Pdlog.m3273d(this.TAG, "random next. size=" + this.questions.size() + " next=" + nextInt);
        Function0<Unit> function0 = this.questions.get(nextInt);
        Intrinsics.checkExpressionValueIsNotNull(function0, "questions[num]");
        this.questions.remove(nextInt);
        function0.invoke();
    }

    public final void setConnectivityQuestion() {
        Pdlog.m3273d(this.TAG, "setConnectivityQuestion ");
        this.questions.clear();
        ArrayList arrayListOf = CollectionsKt.arrayListOf("全亮", "部分亮", "全灭");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        this.btnOptions = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, arrayListOf, 0.0f, 4, null);
        final Function0<Unit> function0 = new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.SurfaceLEDTestActivity$setConnectivityQuestion$updateGuide$1
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
                int questionNum = SurfaceLEDTestActivity.this.getQuestionNum() - SurfaceLEDTestActivity.this.getQuestions().size();
                AppCompatTextView tvGuide = (AppCompatTextView) SurfaceLEDTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("连通性测试 ( " + questionNum + " | " + SurfaceLEDTestActivity.this.getQuestionNum() + " )：\n当前所有灯带的亮灭状态是？");
            }
        };
        for (int i = 1; i <= 2; i++) {
            this.questions.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.SurfaceLEDTestActivity$setConnectivityQuestion$1
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
                    function0.invoke();
                    for (SurfaceLED surfaceLED : SurfaceLED.values()) {
                        SurfaceLEDTestActivity.this.controlRGB(surfaceLED, 255, 255, 255);
                    }
                    SurfaceLEDTestActivity.this.resetRightAnswer(0);
                }
            });
        }
        this.questions.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.SurfaceLEDTestActivity$setConnectivityQuestion$2
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
                function0.invoke();
                for (SurfaceLED surfaceLED : SurfaceLED.values()) {
                    SurfaceLEDTestActivity.this.controlRGB(surfaceLED, 0, 0, 0);
                }
                SurfaceLEDTestActivity.this.resetRightAnswer(2);
            }
        });
        if (AllTestItem.INSTANCE.isHLS()) {
            this.questions.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.SurfaceLEDTestActivity$setConnectivityQuestion$3
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
                    function0.invoke();
                    SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.HlsLeft, 0, 0, 0);
                    SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.HlsRight, 255, 255, 255);
                    SurfaceLEDTestActivity.this.resetRightAnswer(1);
                }
            });
            this.questions.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.SurfaceLEDTestActivity$setConnectivityQuestion$4
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
                    function0.invoke();
                    SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.HlsLeft, 255, 255, 255);
                    SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.HlsRight, 0, 0, 0);
                    SurfaceLEDTestActivity.this.resetRightAnswer(1);
                }
            });
        }
        if (AllTestItem.INSTANCE.isBella()) {
            this.questions.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.SurfaceLEDTestActivity$setConnectivityQuestion$5
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
                    function0.invoke();
                    for (SurfaceLED surfaceLED : SurfaceLED.values()) {
                        SurfaceLEDTestActivity.this.controlRGB(surfaceLED, 255, 255, 255);
                    }
                    SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.LeftEar, 0, 0, 0);
                    SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.Bottom, 0, 0, 0);
                    SurfaceLEDTestActivity.this.resetRightAnswer(1);
                }
            });
            this.questions.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.SurfaceLEDTestActivity$setConnectivityQuestion$6
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
                    function0.invoke();
                    for (SurfaceLED surfaceLED : SurfaceLED.values()) {
                        SurfaceLEDTestActivity.this.controlRGB(surfaceLED, 255, 255, 255);
                    }
                    SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.RightEar, 0, 0, 0);
                    SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.FunctionButton, 0, 0, 0);
                    SurfaceLEDTestActivity.this.resetRightAnswer(1);
                }
            });
        }
        if (AllTestItem.INSTANCE.isHola()) {
            this.questions.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.SurfaceLEDTestActivity$setConnectivityQuestion$7
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
                    function0.invoke();
                    for (SurfaceLED surfaceLED : SurfaceLED.values()) {
                        SurfaceLEDTestActivity.this.controlRGB(surfaceLED, 255, 255, 255);
                    }
                    SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.HolaHead, 0, 0, 0);
                    SurfaceLEDTestActivity.this.resetRightAnswer(1);
                }
            });
            this.questions.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.SurfaceLEDTestActivity$setConnectivityQuestion$8
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
                    function0.invoke();
                    for (SurfaceLED surfaceLED : SurfaceLED.values()) {
                        SurfaceLEDTestActivity.this.controlRGB(surfaceLED, 255, 255, 255);
                    }
                    SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.Bottom, 0, 0, 0);
                    SurfaceLEDTestActivity.this.resetRightAnswer(1);
                }
            });
        }
        if (AllTestItem.INSTANCE.isNineTales()) {
            this.questions.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.SurfaceLEDTestActivity$setConnectivityQuestion$9
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
                    function0.invoke();
                    for (SurfaceLED surfaceLED : SurfaceLED.values()) {
                        SurfaceLEDTestActivity.this.controlRGB(surfaceLED, 255, 255, 255);
                    }
                    SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.DisinfectionSprayHead, 0, 0, 0);
                    SurfaceLEDTestActivity.this.resetRightAnswer(1);
                }
            });
            this.questions.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.SurfaceLEDTestActivity$setConnectivityQuestion$10
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
                    function0.invoke();
                    for (SurfaceLED surfaceLED : SurfaceLED.values()) {
                        SurfaceLEDTestActivity.this.controlRGB(surfaceLED, 255, 255, 255);
                    }
                    SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.Bottom, 0, 0, 0);
                    SurfaceLEDTestActivity.this.resetRightAnswer(1);
                }
            });
        }
        this.questionNum = this.questions.size();
    }

    public final void setColorQuestion() {
        Pdlog.m3273d(this.TAG, "setColorQuestion ");
        this.questions.clear();
        for (SurfaceLED surfaceLED : SurfaceLED.values()) {
            controlRGB(surfaceLED, 0, 0, 0);
        }
        ArrayList arrayListOf = CollectionsKt.arrayListOf("红色", "绿色", "蓝色");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        this.btnOptions = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, arrayListOf, 0.0f, 4, null);
        SurfaceLEDTestActivity$setColorQuestion$2 surfaceLEDTestActivity$setColorQuestion$2 = SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE;
        for (final int i = 0; i < 3; i++) {
            this.questions.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.SurfaceLEDTestActivity$setColorQuestion$3
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

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r9v0 */
                /* JADX WARN: Type inference failed for: r9v15, types: [int, boolean] */
                /* JADX WARN: Type inference failed for: r9v16 */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    int questionNum = SurfaceLEDTestActivity.this.getQuestionNum() - SurfaceLEDTestActivity.this.getQuestions().size();
                    int i2 = 1;
                    ?? r9 = 0;
                    if (AllTestItem.INSTANCE.isHLS()) {
                        AppCompatTextView tvGuide = (AppCompatTextView) SurfaceLEDTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                        tvGuide.setText("颜色测试 ( " + questionNum + " | " + SurfaceLEDTestActivity.this.getQuestionNum() + " )：\n当前灯带的颜色是？");
                        SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.HlsLeft, SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(0, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(1, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(2, i));
                        SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.HlsRight, SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(0, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(1, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(2, i));
                    }
                    if (AllTestItem.INSTANCE.isBella()) {
                        AppCompatTextView tvGuide2 = (AppCompatTextView) SurfaceLEDTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                        Intrinsics.checkExpressionValueIsNotNull(tvGuide2, "tvGuide");
                        tvGuide2.setText("颜色测试 ( " + questionNum + " | " + SurfaceLEDTestActivity.this.getQuestionNum() + " )：\n当前亮着的灯带的颜色是？");
                        SurfaceLED[] values = SurfaceLED.values();
                        if (values != null) {
                            int length = values.length;
                            int i3 = 0;
                            while (i3 < length) {
                                SurfaceLED surfaceLED2 = values[i3];
                                if (!StringsKt.contains$default(surfaceLED2.name(), "Pallet", (boolean) r9, 2, (Object) null)) {
                                    SurfaceLEDTestActivity.this.controlRGB(surfaceLED2, SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke((int) r9, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(i2, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(2, i));
                                }
                                i3++;
                                i2 = 1;
                                r9 = 0;
                            }
                        }
                    }
                    if (AllTestItem.INSTANCE.isHola()) {
                        AppCompatTextView tvGuide3 = (AppCompatTextView) SurfaceLEDTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                        Intrinsics.checkExpressionValueIsNotNull(tvGuide3, "tvGuide");
                        tvGuide3.setText("颜色测试 ( " + questionNum + " | " + SurfaceLEDTestActivity.this.getQuestionNum() + " )：\n当前灯带的颜色是？");
                        SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.HolaFunctionButton, SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(0, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(1, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(2, i));
                        SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.HolaHead, SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(0, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(1, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(2, i));
                        SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.Bottom, SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(0, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(1, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(2, i));
                    }
                    if (AllTestItem.INSTANCE.isNineTales()) {
                        AppCompatTextView tvGuide4 = (AppCompatTextView) SurfaceLEDTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                        Intrinsics.checkExpressionValueIsNotNull(tvGuide4, "tvGuide");
                        tvGuide4.setText("颜色测试 ( " + questionNum + " | " + SurfaceLEDTestActivity.this.getQuestionNum() + " )：\n当前灯带的颜色是？");
                        SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.DisinfectionSprayHead, SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(0, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(1, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(2, i));
                        SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.Bottom, SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(0, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(1, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(2, i));
                    }
                    if (AllTestItem.INSTANCE.isPeanut()) {
                        AppCompatTextView tvGuide5 = (AppCompatTextView) SurfaceLEDTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                        Intrinsics.checkExpressionValueIsNotNull(tvGuide5, "tvGuide");
                        tvGuide5.setText("颜色测试 ( " + questionNum + " | " + SurfaceLEDTestActivity.this.getQuestionNum() + " )：\n当前灯带的颜色是？");
                        SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.HlsLeft, SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(0, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(1, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(2, i));
                        SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.HlsRight, SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(0, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(1, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(2, i));
                        SurfaceLEDTestActivity.this.controlRGB(SurfaceLED.Bottom, SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(0, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(1, i), SurfaceLEDTestActivity$setColorQuestion$2.INSTANCE.invoke(2, i));
                    }
                    SurfaceLEDTestActivity.this.resetRightAnswer(i);
                }
            });
        }
        this.questionNum = this.questions.size();
    }

    public final void controlRGB(SurfaceLED surfaceLED, int r, int g, int b) {
        Intrinsics.checkParameterIsNotNull(surfaceLED, "surfaceLED");
        int i = (r << 16) | (g << 8) | b;
        RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
        if (rbInterface != null) {
            rbInterface.controlRGB(surfaceLED, i, i, 100);
        }
    }
}
