package com.pudutech.factory_test.single_test;

import android.os.Build;
import android.os.Bundle;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.C4491R;
import com.pudutech.factory_test.LayoutHelperKt;
import com.pudutech.factory_test.single_test.TouchPlaneTestActivity;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.Coord;
import com.pudutech.factory_test.test_pack.TestCanvas;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import com.pudutech.factory_test.test_pack.TestItem;
import com.pudutech.factory_test.test_pack.TestStage;
import com.pudutech.factory_test.test_pack.TestStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TouchPlaneTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001:\u0001SB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010@\u001a\u00020*J\u0006\u0010A\u001a\u00020\u0012J\u0012\u0010B\u001a\u00020*2\b\u0010C\u001a\u0004\u0018\u00010DH\u0014J\b\u0010E\u001a\u00020*H\u0014J\u0010\u0010F\u001a\u00020\u00122\u0006\u0010G\u001a\u00020HH\u0016J\u0010\u0010I\u001a\u00020*2\u0006\u0010J\u001a\u00020\u0012H\u0016J\u0006\u0010K\u001a\u00020*J\u0006\u0010L\u001a\u00020*J\u0006\u0010M\u001a\u00020*J\u0006\u0010N\u001a\u00020*J\u0006\u0010O\u001a\u00020*J\u0006\u0010P\u001a\u00020*J\u0006\u0010Q\u001a\u00020*J\b\u0010R\u001a\u00020*H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR9\u0010\u001c\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u001e0\u001dj\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u001e`\u001f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020#X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\"\u0010(\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010/\u001a\u000200X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R!\u00105\u001a\u0012\u0012\u0004\u0012\u00020\u00170\u001dj\b\u0012\u0004\u0012\u00020\u0017`\u001f¢\u0006\b\n\u0000\u001a\u0004\b6\u0010!R\u001a\u00107\u001a\u000200X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00102\"\u0004\b9\u00104R\u001a\u0010:\u001a\u00020;X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?¨\u0006T"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/TouchPlaneTestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "canvas", "Lcom/pudutech/factory_test/test_pack/TestCanvas;", "getCanvas", "()Lcom/pudutech/factory_test/test_pack/TestCanvas;", "canvas$delegate", "Lkotlin/Lazy;", "caseIndex", "", "getCaseIndex", "()I", "setCaseIndex", "(I)V", "isRightHalf", "", "()Z", "setRightHalf", "(Z)V", "lastPoints", "Lcom/pudutech/factory_test/test_pack/Coord;", "getLastPoints", "()Lcom/pudutech/factory_test/test_pack/Coord;", "setLastPoints", "(Lcom/pudutech/factory_test/test_pack/Coord;)V", "lineCases", "Ljava/util/ArrayList;", "Lkotlin/Pair;", "Lkotlin/collections/ArrayList;", "getLineCases", "()Ljava/util/ArrayList;", "mTestItem", "Lcom/pudutech/factory_test/test_pack/TestItem;", "getMTestItem", "()Lcom/pudutech/factory_test/test_pack/TestItem;", "setMTestItem", "(Lcom/pudutech/factory_test/test_pack/TestItem;)V", "onRelease", "Lkotlin/Function0;", "", "getOnRelease", "()Lkotlin/jvm/functions/Function0;", "setOnRelease", "(Lkotlin/jvm/functions/Function0;)V", "padding", "", "getPadding", "()F", "setPadding", "(F)V", "points", "getPoints", "raduis", "getRaduis", "setRaduis", "step", "Lcom/pudutech/factory_test/single_test/TouchPlaneTestActivity$Step;", "getStep", "()Lcom/pudutech/factory_test/single_test/TouchPlaneTestActivity$Step;", "setStep", "(Lcom/pudutech/factory_test/single_test/TouchPlaneTestActivity$Step;)V", "FSM", "checkCaseOK", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "onWindowFocusChanged", "hasFocus", "resetAllCases", "runStepFail", "runStepIdle", "runStepPaint", "runStepSuccess", "setCase", "setResolution", "translucent", "Step", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class TouchPlaneTestActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private int caseIndex;
    private boolean isRightHalf;
    private Coord lastPoints;
    public TestItem mTestItem;
    private Function0<Unit> onRelease;
    private final String TAG = "TouchPlaneTestActivity";

    /* renamed from: canvas$delegate, reason: from kotlin metadata */
    private final Lazy canvas = LazyKt.lazy(new Function0<TestCanvas>() { // from class: com.pudutech.factory_test.single_test.TouchPlaneTestActivity$canvas$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final TestCanvas invoke() {
            return new TestCanvas(TouchPlaneTestActivity.this);
        }
    });
    private Step step = Step.IDLE;
    private final ArrayList<Coord> points = new ArrayList<>();
    private float padding = 40.0f;
    private float raduis = 40.0f;
    private final ArrayList<Pair<Coord, Coord>> lineCases = new ArrayList<>();

    /* compiled from: TouchPlaneTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/TouchPlaneTestActivity$Step;", "", "(Ljava/lang/String;I)V", "IDLE", "PAINT", "FAIL", "SUCCESS", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum Step {
        IDLE,
        PAINT,
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
            $EnumSwitchMapping$0[Step.PAINT.ordinal()] = 2;
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
                if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.TOUCH_PALNE) && testItem2.getStage() == fromValue) {
                    break;
                }
            }
            if (testItem == null) {
                Intrinsics.throwNpe();
            }
            this.mTestItem = testItem;
        }
        supportRequestWindowFeature(1);
        Window window = getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "window");
        window.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.pudutech.factory_test.single_test.TouchPlaneTestActivity$onCreate$2
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public final void onSystemUiVisibilityChange(int i) {
                TouchPlaneTestActivity.this.translucent();
            }
        });
        setContentView(2131427370);
        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutTouchPlaneTest);
        TestCanvas canvas = getCanvas();
        LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(0, LayoutHelperKt.getMATCH_PARENT());
        layoutParams.weight = 1.0f;
        canvas.setLayoutParams(layoutParams);
        canvas.setOnLayoutDrawDone(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.TouchPlaneTestActivity$onCreate$$inlined$apply$lambda$1
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
                str = TouchPlaneTestActivity.this.TAG;
                Pdlog.m3275i(str, "on layout draw done");
                TouchPlaneTestActivity.this.FSM();
            }
        });
        linearLayoutCompat.addView(canvas, 0);
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TouchPlaneTestActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = TouchPlaneTestActivity.this.TAG;
                Pdlog.m3275i(str, "click quit");
                TouchPlaneTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnErrorQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TouchPlaneTestActivity$onCreate$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = TouchPlaneTestActivity.this.TAG;
                Pdlog.m3275i(str, "click error quit");
                TouchPlaneTestActivity.this.getMTestItem().setStatus(TestStatus.FAIL);
                TouchPlaneTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TouchPlaneTestActivity$onCreate$6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = TouchPlaneTestActivity.this.TAG;
                Pdlog.m3275i(str, "click reset");
                if (TouchPlaneTestActivity.this.getIsRightHalf()) {
                    ((LinearLayoutCompat) TouchPlaneTestActivity.this._$_findCachedViewById(C4491R.id.layoutTouchPlaneTest)).removeViewAt(1);
                    ((LinearLayoutCompat) TouchPlaneTestActivity.this._$_findCachedViewById(C4491R.id.layoutTouchPlaneTest)).addView(TouchPlaneTestActivity.this.getCanvas(), 0);
                }
                TouchPlaneTestActivity.this.getMTestItem().setStatus(TestStatus.TESTING);
                TouchPlaneTestActivity.this.setStep(TouchPlaneTestActivity.Step.IDLE);
                TouchPlaneTestActivity.this.FSM();
            }
        });
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

    public final void runStepIdle() {
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        resetAllCases();
        this.step = Step.PAINT;
        FSM();
    }

    public final void runStepPaint() {
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setMovementMethod((MovementMethod) null);
        if (this.caseIndex == this.lineCases.size()) {
            this.step = Step.SUCCESS;
            FSM();
            return;
        }
        if (this.caseIndex == this.lineCases.size() / 2 && !this.isRightHalf) {
            Pdlog.m3275i(this.TAG, "left screen done");
            this.isRightHalf = true;
            ((LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutTouchPlaneTest)).removeViewAt(0);
            ((LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutTouchPlaneTest)).addView(getCanvas());
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
        tvGuide.setText("触屏测试结束\n测试结果是：成功");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList<Button> layoutBtnOptions = LayoutHelperKt.layoutBtnOptions(layoutOptions, this, CollectionsKt.arrayListOf("完成", "下一个未测试项"), 20.0f);
        layoutBtnOptions.get(0).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TouchPlaneTestActivity$runStepSuccess$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TouchPlaneTestActivity.this.finish();
            }
        });
        layoutBtnOptions.get(1).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TouchPlaneTestActivity$runStepSuccess$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> action;
                TestItem nextUntested = AllTestItem.INSTANCE.getNextUntested(TouchPlaneTestActivity.this.getMTestItem());
                if (nextUntested != null && (action = nextUntested.getAction()) != null) {
                    action.invoke();
                }
                TouchPlaneTestActivity.this.finish();
            }
        });
    }

    public final void runStepFail() {
        Pdlog.m3274e(this.TAG, "test fail");
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setMovementMethod(ScrollingMovementMethod.getInstance());
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setStatus(TestStatus.FAIL);
        AppCompatTextView tvGuide2 = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide2, "tvGuide");
        StringBuilder sb = new StringBuilder();
        sb.append("触屏测试结束\n测试结果是：失败\n描述：");
        TestItem testItem2 = this.mTestItem;
        if (testItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        sb.append(testItem2.getFailDescription());
        tvGuide2.setText(sb.toString());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重画"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TouchPlaneTestActivity$runStepFail$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TouchPlaneTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TouchPlaneTestActivity$runStepFail$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = TouchPlaneTestActivity.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb2 = new StringBuilder();
                sb2.append("click ");
                if (view == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.Button");
                }
                sb2.append(((Button) view).getText());
                objArr[0] = sb2.toString();
                Pdlog.m3273d(str, objArr);
                TouchPlaneTestActivity.this.getMTestItem().setStatus(TestStatus.TESTING);
                TouchPlaneTestActivity.this.setStep(TouchPlaneTestActivity.Step.PAINT);
                TouchPlaneTestActivity.this.FSM();
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
            runStepPaint();
        } else if (i == 3) {
            runStepSuccess();
        } else {
            if (i != 4) {
                return;
            }
            runStepFail();
        }
    }

    public final ArrayList<Coord> getPoints() {
        return this.points;
    }

    public final Coord getLastPoints() {
        return this.lastPoints;
    }

    public final void setLastPoints(Coord coord) {
        this.lastPoints = coord;
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        float x = event.getX();
        float y = event.getY();
        Pdlog.m3276v(this.TAG, "onTouchEvent event=" + event + " index=" + event.getActionIndex() + ' ');
        Pdlog.m3273d(this.TAG, "canvas.mCanvas.width=" + getCanvas().getMCanvas().getWidth() + " isRightHalf=" + this.isRightHalf + " lastPoints=" + this.lastPoints);
        if (event.getPointerCount() > 1) {
            Pdlog.m3277w(this.TAG, "pointerCount > 1");
            this.lastPoints = (Coord) null;
            this.onRelease = (Function0) null;
            TestItem testItem = this.mTestItem;
            if (testItem == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
            }
            testItem.setFailDescription("有多个触摸点");
            this.step = Step.FAIL;
            FSM();
            return super.onTouchEvent(event);
        }
        if (x > getCanvas().getMCanvas().getWidth() && !this.isRightHalf) {
            Pdlog.m3277w(this.TAG, "should be touch in left. " + x + " > " + getCanvas().getMCanvas().getWidth());
            if (this.lastPoints != null) {
                this.lastPoints = (Coord) null;
                Function0<Unit> function0 = this.onRelease;
                if (function0 != null) {
                    function0.invoke();
                }
            }
            return super.onTouchEvent(event);
        }
        if (x < getCanvas().getMCanvas().getWidth() && this.isRightHalf) {
            Pdlog.m3277w(this.TAG, "should be touch in right. " + x + " < " + getCanvas().getMCanvas().getWidth());
            if (this.lastPoints != null) {
                this.lastPoints = (Coord) null;
                Function0<Unit> function02 = this.onRelease;
                if (function02 != null) {
                    function02.invoke();
                }
            }
            return super.onTouchEvent(event);
        }
        if (this.isRightHalf) {
            x -= getCanvas().getMCanvas().getWidth();
        }
        int actionMasked = event.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                this.lastPoints = (Coord) null;
                Function0<Unit> function03 = this.onRelease;
                if (function03 != null) {
                    function03.invoke();
                }
            } else if (actionMasked == 2) {
                if (this.points.isEmpty()) {
                    Coord coord = new Coord(x, y);
                    this.lastPoints = coord;
                    ArrayList<Coord> arrayList = this.points;
                    if (coord == null) {
                        Intrinsics.throwNpe();
                    }
                    arrayList.add(coord);
                }
                Coord coord2 = this.lastPoints;
                if (coord2 != null) {
                    TestItem testItem2 = this.mTestItem;
                    if (testItem2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
                    }
                    if (testItem2.getStatus() == TestStatus.UNTESTED) {
                        TestItem testItem3 = this.mTestItem;
                        if (testItem3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
                        }
                        testItem3.setStatus(TestStatus.TESTING);
                    }
                    Pdlog.m3275i(this.TAG, '(' + coord2.getX() + ", " + coord2.getY() + ")draw to (" + x + ", " + y + ')');
                    getCanvas().getMCanvas().drawLine(coord2.getX(), coord2.getY(), x, y, getCanvas().getPainter());
                    Coord coord3 = new Coord(x, y);
                    this.lastPoints = coord3;
                    ArrayList<Coord> arrayList2 = this.points;
                    if (coord3 == null) {
                        Intrinsics.throwNpe();
                    }
                    arrayList2.add(coord3);
                    getCanvas().refreshScreen();
                }
            }
        } else if (this.points.isEmpty()) {
            Coord coord4 = new Coord(x, y);
            this.lastPoints = coord4;
            ArrayList<Coord> arrayList3 = this.points;
            if (coord4 == null) {
                Intrinsics.throwNpe();
            }
            arrayList3.add(coord4);
        }
        return super.onTouchEvent(event);
    }

    public final float getPadding() {
        return this.padding;
    }

    public final void setPadding(float f) {
        this.padding = f;
    }

    public final float getRaduis() {
        return this.raduis;
    }

    public final void setRaduis(float f) {
        this.raduis = f;
    }

    public final ArrayList<Pair<Coord, Coord>> getLineCases() {
        return this.lineCases;
    }

    public final int getCaseIndex() {
        return this.caseIndex;
    }

    public final void setCaseIndex(int i) {
        this.caseIndex = i;
    }

    public final void setResolution() {
        if (AllTestItem.INSTANCE.isHLS()) {
            return;
        }
        this.padding = 60.0f;
        this.raduis = 60.0f;
    }

    public final void resetAllCases() {
        Pdlog.m3273d(this.TAG, "resetAllCases ");
        setResolution();
        this.lineCases.clear();
        this.caseIndex = 0;
        this.isRightHalf = false;
        float width = getCanvas().getMCanvas().getWidth();
        float height = getCanvas().getMCanvas().getHeight();
        for (int i = 0; i <= 1; i++) {
            this.lineCases.add(new Pair<>(new Coord(0.0f, this.padding), new Coord(width, this.padding)));
            float f = 2;
            float f2 = height / f;
            this.lineCases.add(new Pair<>(new Coord(0.0f, f2), new Coord(width, f2)));
            this.lineCases.add(new Pair<>(new Coord(0.0f, height - this.padding), new Coord(width, height - this.padding)));
            this.lineCases.add(new Pair<>(new Coord(this.padding, 0.0f), new Coord(this.padding, height)));
            float f3 = width / f;
            this.lineCases.add(new Pair<>(new Coord(f3, 0.0f), new Coord(f3, height)));
            this.lineCases.add(new Pair<>(new Coord(width - this.padding, 0.0f), new Coord(width - this.padding, height)));
            ArrayList<Pair<Coord, Coord>> arrayList = this.lineCases;
            float f4 = this.padding;
            Coord coord = new Coord(f4 / f, f4 / f);
            float f5 = this.padding;
            arrayList.add(new Pair<>(coord, new Coord(width - (f5 / f), height - (f5 / f))));
            ArrayList<Pair<Coord, Coord>> arrayList2 = this.lineCases;
            float f6 = this.padding;
            Coord coord2 = new Coord(width - (f6 / f), f6 / f);
            float f7 = this.padding;
            arrayList2.add(new Pair<>(coord2, new Coord(f7 / f, height - (f7 / f))));
        }
    }

    public final void setCase() {
        Pdlog.m3273d(this.TAG, "setCase index=" + this.caseIndex);
        this.points.clear();
        Coord first = this.lineCases.get(this.caseIndex).getFirst();
        Coord second = this.lineCases.get(this.caseIndex).getSecond();
        getCanvas().getMCanvas().drawColor(-1);
        TestCanvas canvas = getCanvas();
        canvas.m4338switch(canvas.getPainter(), InputDeviceCompat.SOURCE_ANY, 2 * this.raduis);
        canvas.getMCanvas().drawLine(first.getX(), first.getY(), second.getX(), second.getY(), canvas.getPainter());
        TestCanvas.switch$default(canvas, canvas.getPainter(), -16776961, 0.0f, 2, null);
        canvas.getMCanvas().drawCircle(first.getX(), first.getY(), this.raduis, canvas.getPainter());
        TestCanvas.switch$default(canvas, canvas.getPainter(), -16711936, 0.0f, 2, null);
        canvas.getMCanvas().drawCircle(second.getX(), second.getY(), this.raduis, canvas.getPainter());
        canvas.refreshScreen();
        TestCanvas.switch$default(canvas, canvas.getPainter(), SupportMenu.CATEGORY_MASK, 0.0f, 2, null);
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("触屏测试 ( " + (this.caseIndex + 1) + " | " + this.lineCases.size() + " )：\n从蓝色标记画线到绿色标记，不能超过黄色区域的宽度，一次画完");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ((Button) LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("重画"), 0.0f, 4, null).get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.TouchPlaneTestActivity$setCase$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = TouchPlaneTestActivity.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("click ");
                if (view == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.Button");
                }
                sb.append(((Button) view).getText());
                objArr[0] = sb.toString();
                Pdlog.m3273d(str, objArr);
                TouchPlaneTestActivity.this.getMTestItem().setStatus(TestStatus.TESTING);
                TouchPlaneTestActivity.this.setStep(TouchPlaneTestActivity.Step.PAINT);
                TouchPlaneTestActivity.this.FSM();
            }
        });
        this.onRelease = new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.TouchPlaneTestActivity$setCase$3
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
                TouchPlaneTestActivity.this.setOnRelease((Function0) null);
                if (TouchPlaneTestActivity.this.checkCaseOK()) {
                    TouchPlaneTestActivity touchPlaneTestActivity = TouchPlaneTestActivity.this;
                    touchPlaneTestActivity.setCaseIndex(touchPlaneTestActivity.getCaseIndex() + 1);
                } else {
                    TouchPlaneTestActivity.this.setStep(TouchPlaneTestActivity.Step.FAIL);
                }
                TouchPlaneTestActivity.this.FSM();
            }
        };
    }

    public final Function0<Unit> getOnRelease() {
        return this.onRelease;
    }

    public final void setOnRelease(Function0<Unit> function0) {
        this.onRelease = function0;
    }

    public final boolean checkCaseOK() {
        boolean z;
        if (this.points.size() < 2) {
            return false;
        }
        Coord first = this.lineCases.get(this.caseIndex).getFirst();
        Coord second = this.lineCases.get(this.caseIndex).getSecond();
        Pdlog.m3275i(this.TAG, "checkCaseOK points.size=" + this.points.size());
        if (!((Coord) CollectionsKt.first((List) this.points)).checkInCircle(first, this.raduis)) {
            TestItem testItem = this.mTestItem;
            if (testItem == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
            }
            testItem.setFailDescription("起点不在指定区域内");
            return false;
        }
        if (!((Coord) CollectionsKt.last((List) this.points)).checkInCircle(second, this.raduis)) {
            TestItem testItem2 = this.mTestItem;
            if (testItem2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
            }
            testItem2.setFailDescription("终点不在指定区域内，可能存在触屏不灵敏的区域");
            return false;
        }
        ArrayList<Coord> arrayList = this.points;
        if (!(arrayList instanceof Collection) || !arrayList.isEmpty()) {
            Iterator<T> it = arrayList.iterator();
            while (it.hasNext()) {
                if (!((Coord) it.next()).checkInLineRange(first, second, this.raduis)) {
                    z = false;
                    break;
                }
            }
        }
        z = true;
        if (z) {
            return true;
        }
        TestItem testItem3 = this.mTestItem;
        if (testItem3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem3.setFailDescription("有绘制的曲线偏移的情况，可能存在触屏飘移的情况");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void translucent() {
        if (Build.VERSION.SDK_INT >= 19) {
            Window window = getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window, "window");
            View decorView = window.getDecorView();
            Intrinsics.checkExpressionValueIsNotNull(decorView, "decorView");
            decorView.setSystemUiVisibility(3846);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        translucent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        translucent();
    }
}
