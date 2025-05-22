package com.pudutech.factory_test.single_test;

import android.graphics.Canvas;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.C4491R;
import com.pudutech.factory_test.LayoutHelperKt;
import com.pudutech.factory_test.single_test.ScreenTestActivity;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.TestCanvas;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import com.pudutech.factory_test.test_pack.TestItem;
import com.pudutech.factory_test.test_pack.TestStage;
import com.pudutech.factory_test.test_pack.TestStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScreenTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u00015B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010*\u001a\u00020+J\u0012\u0010,\u001a\u00020+2\b\u0010-\u001a\u0004\u0018\u00010.H\u0014J\u0006\u0010/\u001a\u00020+J\u0006\u00100\u001a\u00020+J\u0006\u00101\u001a\u00020+J\u0006\u00102\u001a\u00020+J\u0006\u00103\u001a\u00020+J\u0006\u00104\u001a\u00020+R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R!\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\f0\u0012j\b\u0012\u0004\u0012\u00020\f`\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u001bR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)¨\u00066"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/ScreenTestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "canvas", "Lcom/pudutech/factory_test/test_pack/TestCanvas;", "getCanvas", "()Lcom/pudutech/factory_test/test_pack/TestCanvas;", "canvas$delegate", "Lkotlin/Lazy;", "caseIndex", "", "getCaseIndex", "()I", "setCaseIndex", "(I)V", "colorCases", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getColorCases", "()Ljava/util/ArrayList;", "hasBadPixel", "", "getHasBadPixel", "()Z", "setHasBadPixel", "(Z)V", "isRightHalf", "setRightHalf", "mTestItem", "Lcom/pudutech/factory_test/test_pack/TestItem;", "getMTestItem", "()Lcom/pudutech/factory_test/test_pack/TestItem;", "setMTestItem", "(Lcom/pudutech/factory_test/test_pack/TestItem;)V", "step", "Lcom/pudutech/factory_test/single_test/ScreenTestActivity$Step;", "getStep", "()Lcom/pudutech/factory_test/single_test/ScreenTestActivity$Step;", "setStep", "(Lcom/pudutech/factory_test/single_test/ScreenTestActivity$Step;)V", "FSM", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "resetAllCases", "runStepChecking", "runStepFail", "runStepIdle", "runStepSuccess", "setCase", "Step", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class ScreenTestActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private int caseIndex;
    private boolean hasBadPixel;
    private boolean isRightHalf;
    public TestItem mTestItem;
    private final String TAG = "ScreenTestActivity";

    /* renamed from: canvas$delegate, reason: from kotlin metadata */
    private final Lazy canvas = LazyKt.lazy(new Function0<TestCanvas>() { // from class: com.pudutech.factory_test.single_test.ScreenTestActivity$canvas$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final TestCanvas invoke() {
            return new TestCanvas(ScreenTestActivity.this);
        }
    });
    private Step step = Step.IDLE;
    private final ArrayList<Integer> colorCases = new ArrayList<>();

    /* compiled from: ScreenTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/ScreenTestActivity$Step;", "", "(Ljava/lang/String;I)V", "IDLE", "CHECKING", "FAIL", "SUCCESS", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
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

    public final TestCanvas getCanvas() {
        return (TestCanvas) this.canvas.getValue();
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

    /* renamed from: isRightHalf, reason: from getter */
    public final boolean getIsRightHalf() {
        return this.isRightHalf;
    }

    public final void setRightHalf(boolean z) {
        this.isRightHalf = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        TestItem testItem;
        super.onCreate(savedInstanceState);
        setContentView(2131427366);
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
                if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.SCREEN) && testItem2.getStage() == fromValue) {
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
        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutScreenTest);
        TestCanvas canvas = getCanvas();
        LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(0, LayoutHelperKt.getMATCH_PARENT());
        layoutParams.weight = 1.0f;
        canvas.setLayoutParams(layoutParams);
        canvas.setOnLayoutDrawDone(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.ScreenTestActivity$onCreate$$inlined$apply$lambda$1
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
                String str;
                str = ScreenTestActivity.this.TAG;
                Pdlog.m3275i(str, "on layout draw done");
                ScreenTestActivity.this.FSM();
            }
        });
        linearLayoutCompat.addView(canvas, 0);
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ScreenTestActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = ScreenTestActivity.this.TAG;
                Pdlog.m3275i(str, "click quit");
                ScreenTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnErrorQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ScreenTestActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = ScreenTestActivity.this.TAG;
                Pdlog.m3275i(str, "click error quit");
                ScreenTestActivity.this.getMTestItem().setStatus(TestStatus.FAIL);
                ScreenTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ScreenTestActivity$onCreate$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = ScreenTestActivity.this.TAG;
                Pdlog.m3275i(str, "click reset");
                if (ScreenTestActivity.this.getIsRightHalf()) {
                    ((LinearLayoutCompat) ScreenTestActivity.this._$_findCachedViewById(C4491R.id.layoutScreenTest)).removeViewAt(1);
                    ((LinearLayoutCompat) ScreenTestActivity.this._$_findCachedViewById(C4491R.id.layoutScreenTest)).addView(ScreenTestActivity.this.getCanvas(), 0);
                }
                ScreenTestActivity.this.getMTestItem().setStatus(TestStatus.TESTING);
                ScreenTestActivity.this.setStep(ScreenTestActivity.Step.IDLE);
                ScreenTestActivity.this.FSM();
            }
        });
    }

    public final Step getStep() {
        return this.step;
    }

    public final void setStep(Step step) {
        Intrinsics.checkParameterIsNotNull(step, "<set-?>");
        this.step = step;
    }

    public final void FSM() {
        Pdlog.m3273d(this.TAG, "FSM now=" + this.step);
        int i = WhenMappings.$EnumSwitchMapping$0[this.step.ordinal()];
        if (i == 1) {
            runStepIdle();
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

    public final void runStepIdle() {
        resetAllCases();
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        this.step = Step.CHECKING;
        FSM();
    }

    public final void runStepChecking() {
        if (this.caseIndex == this.colorCases.size()) {
            if (this.hasBadPixel) {
                this.step = Step.FAIL;
            } else {
                this.step = Step.SUCCESS;
            }
            if (this.step == Step.FAIL) {
                TestItem testItem = this.mTestItem;
                if (testItem == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
                }
                testItem.setFailDescription("人工判定屏幕有坏点");
            }
            FSM();
            return;
        }
        if (this.caseIndex == this.colorCases.size() / 2 && !this.isRightHalf) {
            Pdlog.m3275i(this.TAG, "left screen done");
            this.isRightHalf = true;
            ((LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutScreenTest)).removeViewAt(0);
            ((LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutScreenTest)).addView(getCanvas());
        }
        setCase();
    }

    public final void runStepSuccess() {
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setStatus(TestStatus.SUCCESS);
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("屏幕测试结束\n测试结果是\n成功");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList<Button> layoutBtnOptions = LayoutHelperKt.layoutBtnOptions(layoutOptions, this, CollectionsKt.arrayListOf("完成", "下一个未测试项"), 20.0f);
        layoutBtnOptions.get(0).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ScreenTestActivity$runStepSuccess$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ScreenTestActivity.this.finish();
            }
        });
        layoutBtnOptions.get(1).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ScreenTestActivity$runStepSuccess$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> action;
                TestItem nextUntested = AllTestItem.INSTANCE.getNextUntested(ScreenTestActivity.this.getMTestItem());
                if (nextUntested != null && (action = nextUntested.getAction()) != null) {
                    action.invoke();
                }
                ScreenTestActivity.this.finish();
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
        sb.append("屏幕测试结束\n测试结果是\n失败\n描述：");
        TestItem testItem2 = this.mTestItem;
        if (testItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        sb.append(testItem2.getFailDescription());
        tvGuide.setText(sb.toString());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList<Button> layoutBtnOptions = LayoutHelperKt.layoutBtnOptions(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重新测试"), 30.0f);
        layoutBtnOptions.get(0).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ScreenTestActivity$runStepFail$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ScreenTestActivity.this.finish();
            }
        });
        layoutBtnOptions.get(1).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ScreenTestActivity$runStepFail$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((AppCompatButton) ScreenTestActivity.this._$_findCachedViewById(C4491R.id.btnReset)).callOnClick();
            }
        });
    }

    public final ArrayList<Integer> getColorCases() {
        return this.colorCases;
    }

    public final int getCaseIndex() {
        return this.caseIndex;
    }

    public final void setCaseIndex(int i) {
        this.caseIndex = i;
    }

    public final boolean getHasBadPixel() {
        return this.hasBadPixel;
    }

    public final void setHasBadPixel(boolean z) {
        this.hasBadPixel = z;
    }

    public final void resetAllCases() {
        this.colorCases.clear();
        this.caseIndex = 0;
        this.isRightHalf = false;
        this.hasBadPixel = false;
        for (int i = 0; i <= 1; i++) {
            this.colorCases.add(-1);
            this.colorCases.add(Integer.valueOf(ViewCompat.MEASURED_STATE_MASK));
            this.colorCases.add(Integer.valueOf(SupportMenu.CATEGORY_MASK));
            this.colorCases.add(-16711936);
            this.colorCases.add(-16776961);
            this.colorCases.add(Integer.valueOf(InputDeviceCompat.SOURCE_ANY));
        }
    }

    public final void setCase() {
        Pdlog.m3273d(this.TAG, "setCase index=" + this.caseIndex);
        Canvas mCanvas = getCanvas().getMCanvas();
        Integer num = this.colorCases.get(this.caseIndex);
        Intrinsics.checkExpressionValueIsNotNull(num, "colorCases[caseIndex]");
        mCanvas.drawColor(num.intValue());
        getCanvas().refreshScreen();
        if (this.isRightHalf) {
            AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
            Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
            tvGuide.setText("屏幕测试 ( " + (this.caseIndex + 1) + " | " + this.colorCases.size() + " ):\n右半屏的颜色是否均匀一致（没有颜色异常的像素点或区域）？");
        } else {
            AppCompatTextView tvGuide2 = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
            Intrinsics.checkExpressionValueIsNotNull(tvGuide2, "tvGuide");
            tvGuide2.setText("屏幕测试 ( " + (this.caseIndex + 1) + " | " + this.colorCases.size() + " ):\n左半屏的颜色是否均匀一致（没有颜色异常的像素点或区域）？");
        }
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList<Button> layoutBtnOptions = LayoutHelperKt.layoutBtnOptions(layoutOptions, this, CollectionsKt.arrayListOf("有异常", "一致"), 30.0f);
        layoutBtnOptions.get(0).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ScreenTestActivity$setCase$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = ScreenTestActivity.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("click ");
                if (view == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.Button");
                }
                sb.append(((Button) view).getText());
                objArr[0] = sb.toString();
                Pdlog.m3273d(str, objArr);
                ScreenTestActivity.this.getMTestItem().setStatus(TestStatus.TESTING);
                ScreenTestActivity.this.setHasBadPixel(true);
                ScreenTestActivity screenTestActivity = ScreenTestActivity.this;
                screenTestActivity.setCaseIndex(screenTestActivity.getCaseIndex() + 1);
                ScreenTestActivity.this.FSM();
            }
        });
        layoutBtnOptions.get(1).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ScreenTestActivity$setCase$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = ScreenTestActivity.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("click ");
                if (view == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.Button");
                }
                sb.append(((Button) view).getText());
                objArr[0] = sb.toString();
                Pdlog.m3273d(str, objArr);
                ScreenTestActivity.this.getMTestItem().setStatus(TestStatus.TESTING);
                ScreenTestActivity screenTestActivity = ScreenTestActivity.this;
                screenTestActivity.setCaseIndex(screenTestActivity.getCaseIndex() + 1);
                ScreenTestActivity.this.FSM();
            }
        });
    }
}
