package com.pudutech.factory_test.single_test;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.view.ViewCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.C4491R;
import com.pudutech.factory_test.LayoutHelperKt;
import com.pudutech.factory_test.ServiceConnectionKt;
import com.pudutech.factory_test.single_test.LidarTestActivity;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.TestCanvas;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import com.pudutech.factory_test.test_pack.TestItem;
import com.pudutech.factory_test.test_pack.TestStage;
import com.pudutech.factory_test.test_pack.TestStatus;
import com.pudutech.lidar.LidarVersion;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.LidarInterface;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.random.Random;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: LidarTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001:\u00019B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010,\u001a\u00020\bJ\u0006\u0010-\u001a\u00020\bJ\u0006\u0010.\u001a\u00020\bJ\u0012\u0010/\u001a\u00020\b2\b\u00100\u001a\u0004\u0018\u000101H\u0014J\b\u00102\u001a\u00020\bH\u0014J\u0006\u00103\u001a\u00020\bJ\u0006\u00104\u001a\u00020\bJ\u0006\u00105\u001a\u00020\bJ\u0006\u00106\u001a\u00020\bJ\u0006\u00107\u001a\u00020\bJ\u0006\u00108\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R-\u0010\u0005\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007`\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020'X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+¨\u0006:"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/LidarTestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "allCases", "Ljava/util/ArrayList;", "Lkotlin/Function0;", "", "Lkotlin/collections/ArrayList;", "getAllCases", "()Ljava/util/ArrayList;", "caseNum", "", "getCaseNum", "()I", "setCaseNum", "(I)V", "checkLidarAvg", "checkLidarSum", "delayJob", "Lkotlinx/coroutines/Job;", "getDelayJob", "()Lkotlinx/coroutines/Job;", "setDelayJob", "(Lkotlinx/coroutines/Job;)V", "frameCounter", "isOpen", "", "()Z", "setOpen", "(Z)V", "mTestItem", "Lcom/pudutech/factory_test/test_pack/TestItem;", "getMTestItem", "()Lcom/pudutech/factory_test/test_pack/TestItem;", "setMTestItem", "(Lcom/pudutech/factory_test/test_pack/TestItem;)V", "step", "Lcom/pudutech/factory_test/single_test/LidarTestActivity$Step;", "getStep", "()Lcom/pudutech/factory_test/single_test/LidarTestActivity$Step;", "setStep", "(Lcom/pudutech/factory_test/single_test/LidarTestActivity$Step;)V", "FSM", "autoTest", "drawAxis", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "removeListener", "runStepChecking", "runStepFail", "runStepSuccess", "setCases", "setListener", "Step", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class LidarTestActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private int caseNum;
    private int checkLidarAvg;
    private int checkLidarSum;
    private Job delayJob;
    private int frameCounter;
    public TestItem mTestItem;
    private final String TAG = "LocateCameraTestActivity";
    private final ArrayList<Function0<Unit>> allCases = new ArrayList<>();
    private Step step = Step.IDLE;
    private boolean isOpen = true;

    /* compiled from: LidarTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/LidarTestActivity$Step;", "", "(Ljava/lang/String;I)V", "IDLE", "AUTO_TESTING", "CHECKING", "FAIL", "SUCCESS", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum Step {
        IDLE,
        AUTO_TESTING,
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
            $EnumSwitchMapping$0[Step.AUTO_TESTING.ordinal()] = 2;
            $EnumSwitchMapping$0[Step.CHECKING.ordinal()] = 3;
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
                if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.LIDAR) && testItem2.getStage() == fromValue) {
                    break;
                }
            }
            if (testItem == null) {
                Intrinsics.throwNpe();
            }
            this.mTestItem = testItem;
        }
        setContentView(2131427364);
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setMovementMethod(ScrollingMovementMethod.getInstance());
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.LidarTestActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = LidarTestActivity.this.TAG;
                Pdlog.m3275i(str, "click quit");
                LidarTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnErrorQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.LidarTestActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = LidarTestActivity.this.TAG;
                Pdlog.m3275i(str, "click error quit");
                LidarTestActivity.this.getMTestItem().setStatus(TestStatus.FAIL);
                LidarTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.LidarTestActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = LidarTestActivity.this.TAG;
                Pdlog.m3275i(str, "click reset");
                LidarTestActivity.this.checkLidarSum = 0;
                LidarTestActivity.this.checkLidarAvg = 0;
                LidarTestActivity.this.frameCounter = 0;
                LidarTestActivity.this.getMTestItem().setStatus(TestStatus.TESTING);
                LidarTestActivity.this.setStep(LidarTestActivity.Step.IDLE);
                LidarTestActivity.this.FSM();
            }
        });
        ((TestCanvas) _$_findCachedViewById(C4491R.id.canvasLidar)).setOnLayoutDrawDone(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.LidarTestActivity$onCreate$5
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
                LidarTestActivity.this.setListener();
                LidarTestActivity.this.FSM();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        removeListener();
        Job job = this.delayJob;
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
            this.step = Step.AUTO_TESTING;
            FSM();
            return;
        }
        if (i == 2) {
            autoTest();
            return;
        }
        if (i == 3) {
            runStepChecking();
        } else if (i == 4) {
            runStepSuccess();
        } else {
            if (i != 5) {
                return;
            }
            runStepFail();
        }
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
        tvGuide.setText("雷达测试结束\n测试结果是：成功");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList<Button> layoutBtnOptions = LayoutHelperKt.layoutBtnOptions(layoutOptions, this, CollectionsKt.arrayListOf("完成", "下一个未测试项"), 20.0f);
        layoutBtnOptions.get(0).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.LidarTestActivity$runStepSuccess$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LidarTestActivity.this.finish();
            }
        });
        layoutBtnOptions.get(1).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.LidarTestActivity$runStepSuccess$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> action;
                TestItem nextUntested = AllTestItem.INSTANCE.getNextUntested(LidarTestActivity.this.getMTestItem());
                if (nextUntested != null && (action = nextUntested.getAction()) != null) {
                    action.invoke();
                }
                LidarTestActivity.this.finish();
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
        sb.append("雷达测试结束\n测试结果是：失败\n描述：");
        TestItem testItem2 = this.mTestItem;
        if (testItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        sb.append(testItem2.getFailDescription());
        tvGuide.setText(sb.toString());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList<Button> layoutBtnOptions = LayoutHelperKt.layoutBtnOptions(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重新测试"), 30.0f);
        layoutBtnOptions.get(0).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.LidarTestActivity$runStepFail$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LidarTestActivity.this.finish();
            }
        });
        layoutBtnOptions.get(1).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.LidarTestActivity$runStepFail$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((AppCompatButton) LidarTestActivity.this._$_findCachedViewById(C4491R.id.btnReset)).callOnClick();
            }
        });
    }

    /* renamed from: isOpen, reason: from getter */
    public final boolean getIsOpen() {
        return this.isOpen;
    }

    public final void setOpen(boolean z) {
        this.isOpen = z;
    }

    public final void setListener() {
        Pdlog.m3273d(this.TAG, "setListener ");
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface == null) {
            Intrinsics.throwNpe();
        }
        hdInterface.getLidarInterface().addDataListener(this.TAG, new LidarTestActivity$setListener$1(this));
    }

    public final void removeListener() {
        LidarInterface lidarInterface;
        Pdlog.m3273d(this.TAG, "removeListener ");
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface == null || (lidarInterface = hdInterface.getLidarInterface()) == null) {
            return;
        }
        lidarInterface.removeDataListener(this.TAG);
    }

    public final void drawAxis() {
        TestCanvas canvasLidar = (TestCanvas) _$_findCachedViewById(C4491R.id.canvasLidar);
        Intrinsics.checkExpressionValueIsNotNull(canvasLidar, "canvasLidar");
        float width = canvasLidar.getWidth() / 2.0f;
        TestCanvas canvasLidar2 = (TestCanvas) _$_findCachedViewById(C4491R.id.canvasLidar);
        Intrinsics.checkExpressionValueIsNotNull(canvasLidar2, "canvasLidar");
        float height = canvasLidar2.getHeight() / 2.0f;
        ((TestCanvas) _$_findCachedViewById(C4491R.id.canvasLidar)).getMCanvas().drawColor(ViewCompat.MEASURED_STATE_MASK);
        ((TestCanvas) _$_findCachedViewById(C4491R.id.canvasLidar)).getPainter().setStrokeWidth(2.0f);
        ((TestCanvas) _$_findCachedViewById(C4491R.id.canvasLidar)).getPainter().setStyle(Paint.Style.STROKE);
        Canvas mCanvas = ((TestCanvas) _$_findCachedViewById(C4491R.id.canvasLidar)).getMCanvas();
        TestCanvas canvasLidar3 = (TestCanvas) _$_findCachedViewById(C4491R.id.canvasLidar);
        Intrinsics.checkExpressionValueIsNotNull(canvasLidar3, "canvasLidar");
        mCanvas.drawLine(width, 0.0f, width, canvasLidar3.getHeight(), ((TestCanvas) _$_findCachedViewById(C4491R.id.canvasLidar)).getPainter());
        Canvas mCanvas2 = ((TestCanvas) _$_findCachedViewById(C4491R.id.canvasLidar)).getMCanvas();
        TestCanvas canvasLidar4 = (TestCanvas) _$_findCachedViewById(C4491R.id.canvasLidar);
        Intrinsics.checkExpressionValueIsNotNull(canvasLidar4, "canvasLidar");
        mCanvas2.drawLine(0.0f, height, canvasLidar4.getWidth(), height, ((TestCanvas) _$_findCachedViewById(C4491R.id.canvasLidar)).getPainter());
        ((TestCanvas) _$_findCachedViewById(C4491R.id.canvasLidar)).getMCanvas().drawCircle(width, height, 25.0f, ((TestCanvas) _$_findCachedViewById(C4491R.id.canvasLidar)).getPainter());
    }

    public final Job getDelayJob() {
        return this.delayJob;
    }

    public final void setDelayJob(Job job) {
        this.delayJob = job;
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
        for (Object obj : LayoutHelperKt.layoutBtnOptions(layoutOptions, this, CollectionsKt.arrayListOf("有图像", "没有图像"), 30.0f)) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ((Button) obj).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.LidarTestActivity$setCases$$inlined$forEachIndexed$lambda$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    String str;
                    str = this.TAG;
                    Pdlog.m3275i(str, "click option. index=" + i + " when rightAnswerIndex=" + intRef.element);
                    this.getMTestItem().setStatus(TestStatus.TESTING);
                    if (intRef.element != i) {
                        this.setStep(LidarTestActivity.Step.FAIL);
                        this.getMTestItem().setFailDescription("选择选项错误，可能是雷达配置或者连线故障");
                    }
                    this.FSM();
                }
            });
            i = i2;
        }
        final Function0<Unit> function0 = new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.LidarTestActivity$setCases$updateGuide$1
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
                int caseNum = LidarTestActivity.this.getCaseNum() - LidarTestActivity.this.getAllCases().size();
                AppCompatTextView tvGuide = (AppCompatTextView) LidarTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("雷达测试 ( " + caseNum + " | " + LidarTestActivity.this.getCaseNum() + " )：\n左侧预览画面否能看雷达的扫描图像？");
            }
        };
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.LidarTestActivity$setCases$2
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
                Job launch$default;
                LidarInterface lidarInterface;
                HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
                if (hdInterface != null && (lidarInterface = hdInterface.getLidarInterface()) != null) {
                    lidarInterface.open();
                }
                LinearLayoutCompat layoutOptions2 = (LinearLayoutCompat) LidarTestActivity.this._$_findCachedViewById(C4491R.id.layoutOptions);
                Intrinsics.checkExpressionValueIsNotNull(layoutOptions2, "layoutOptions");
                layoutOptions2.setVisibility(4);
                AppCompatTextView tvGuide = (AppCompatTextView) LidarTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("指令处理中，请稍等 5 秒钟");
                LidarTestActivity.this.drawAxis();
                ((TestCanvas) LidarTestActivity.this._$_findCachedViewById(C4491R.id.canvasLidar)).refreshScreen();
                Job delayJob = LidarTestActivity.this.getDelayJob();
                if (delayJob != null) {
                    Job.DefaultImpls.cancel$default(delayJob, (CancellationException) null, 1, (Object) null);
                }
                LidarTestActivity lidarTestActivity = LidarTestActivity.this;
                launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C45131(null), 2, null);
                lidarTestActivity.setDelayJob(launch$default);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: LidarTestActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.LidarTestActivity$setCases$2$1", m3970f = "LidarTestActivity.kt", m3971i = {0}, m3972l = {237}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
            /* renamed from: com.pudutech.factory_test.single_test.LidarTestActivity$setCases$2$1 */
            /* loaded from: classes.dex */
            public static final class C45131 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5186p$;

                C45131(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C45131 c45131 = new C45131(completion);
                    c45131.f5186p$ = (CoroutineScope) obj;
                    return c45131;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C45131) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.L$0 = this.f5186p$;
                        this.label = 1;
                        if (DelayKt.delay(5000L, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    LidarTestActivity.this.setOpen(true);
                    LinearLayoutCompat layoutOptions = (LinearLayoutCompat) LidarTestActivity.this._$_findCachedViewById(C4491R.id.layoutOptions);
                    Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
                    layoutOptions.setVisibility(0);
                    intRef.element = 0;
                    function0.invoke();
                    return Unit.INSTANCE;
                }
            }
        });
        this.allCases.add(new Function0<Unit>() { // from class: com.pudutech.factory_test.single_test.LidarTestActivity$setCases$3
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
                Job launch$default;
                LidarInterface lidarInterface;
                LidarTestActivity.this.setOpen(false);
                HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
                if (hdInterface != null && (lidarInterface = hdInterface.getLidarInterface()) != null) {
                    lidarInterface.stop();
                }
                LinearLayoutCompat layoutOptions2 = (LinearLayoutCompat) LidarTestActivity.this._$_findCachedViewById(C4491R.id.layoutOptions);
                Intrinsics.checkExpressionValueIsNotNull(layoutOptions2, "layoutOptions");
                layoutOptions2.setVisibility(4);
                AppCompatTextView tvGuide = (AppCompatTextView) LidarTestActivity.this._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("指令处理中，请稍等 5 秒钟");
                LidarTestActivity.this.drawAxis();
                ((TestCanvas) LidarTestActivity.this._$_findCachedViewById(C4491R.id.canvasLidar)).refreshScreen();
                Job delayJob = LidarTestActivity.this.getDelayJob();
                if (delayJob != null) {
                    Job.DefaultImpls.cancel$default(delayJob, (CancellationException) null, 1, (Object) null);
                }
                LidarTestActivity lidarTestActivity = LidarTestActivity.this;
                launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C45141(null), 2, null);
                lidarTestActivity.setDelayJob(launch$default);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: LidarTestActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.LidarTestActivity$setCases$3$1", m3970f = "LidarTestActivity.kt", m3971i = {0}, m3972l = {254}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
            /* renamed from: com.pudutech.factory_test.single_test.LidarTestActivity$setCases$3$1 */
            /* loaded from: classes.dex */
            public static final class C45141 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5187p$;

                C45141(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C45141 c45141 = new C45141(completion);
                    c45141.f5187p$ = (CoroutineScope) obj;
                    return c45141;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C45141) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.L$0 = this.f5187p$;
                        this.label = 1;
                        if (DelayKt.delay(5000L, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    LinearLayoutCompat layoutOptions = (LinearLayoutCompat) LidarTestActivity.this._$_findCachedViewById(C4491R.id.layoutOptions);
                    Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
                    layoutOptions.setVisibility(0);
                    intRef.element = 1;
                    function0.invoke();
                    return Unit.INSTANCE;
                }
            }
        });
        this.caseNum = this.allCases.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [T, java.lang.Integer] */
    public final void autoTest() {
        Job launch$default;
        LidarInterface lidarInterface;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface == null) {
            Intrinsics.throwNpe();
        }
        objectRef.element = hdInterface.getMachineInfo().getInt(MachineInfo.IntInfo.ldsSensorVersion);
        Pdlog.m3276v(this.TAG, "lidarVersion=" + ((Integer) objectRef.element));
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 15;
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("自动检测中，请稍等大约 " + intRef.element + " 秒钟");
        Integer num = (Integer) objectRef.element;
        int value = LidarVersion.getValue(LidarVersion.LTME_02A);
        if (num != null && num.intValue() == value) {
            intRef.element = 20;
            AppCompatTextView tvGuide2 = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
            Intrinsics.checkExpressionValueIsNotNull(tvGuide2, "tvGuide");
            tvGuide2.setText("请把机器人推到指定区域, 自动检测中，请稍等大约 " + intRef.element + " 秒钟");
        }
        ((LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions)).removeAllViews();
        HardwareInterface hdInterface2 = ServiceConnectionKt.getHdInterface();
        if (hdInterface2 != null && (lidarInterface = hdInterface2.getLidarInterface()) != null) {
            lidarInterface.stop();
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new LidarTestActivity$autoTest$1(this, intRef, objectRef, null), 3, null);
        this.delayJob = launch$default;
    }
}
