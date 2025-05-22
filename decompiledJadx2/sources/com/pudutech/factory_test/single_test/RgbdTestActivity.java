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
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import com.pudutech.factory_test.test_pack.TestItem;
import com.pudutech.factory_test.test_pack.TestStage;
import com.pudutech.factory_test.test_pack.TestStatus;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.RGBDInterface;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RgbdTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0013\u001a\u00020\u000eJ\u0012\u0010\u0014\u001a\u00020\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u000eH\u0014J\u0006\u0010\u0018\u001a\u00020\u000eJ\u0006\u0010\u0019\u001a\u00020\u000eJ\u0006\u0010\u001a\u001a\u00020\u000eJ\u0006\u0010\u001b\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR(\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/RgbdTestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "mTestItem", "Lcom/pudutech/factory_test/test_pack/TestItem;", "getMTestItem", "()Lcom/pudutech/factory_test/test_pack/TestItem;", "setMTestItem", "(Lcom/pudutech/factory_test/test_pack/TestItem;)V", "onOpen", "Lkotlin/Function1;", "", "", "getOnOpen", "()Lkotlin/jvm/functions/Function1;", "setOnOpen", "(Lkotlin/jvm/functions/Function1;)V", "autoTest", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "removeListener", "runStepFail", "runStepSuccess", "setRGBDListener", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class RgbdTestActivity extends AppCompatActivity {
    private final String TAG = "RgbdTestActivity";
    private HashMap _$_findViewCache;
    public TestItem mTestItem;
    private Function1<? super Boolean, Unit> onOpen;

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
        MachineInfo machineInfo;
        TestItem testItem;
        super.onCreate(savedInstanceState);
        setContentView(2131427397);
        MachineInfo.RGBDType rGBDType = null;
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
                if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.RGBD) && testItem2.getStage() == fromValue) {
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
        AppCompatTextView tvDebugInfo = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvDebugInfo);
        Intrinsics.checkExpressionValueIsNotNull(tvDebugInfo, "tvDebugInfo");
        StringBuilder sb = new StringBuilder();
        sb.append("RGBD类型 : ");
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface != null && (machineInfo = hdInterface.getMachineInfo()) != null) {
            rGBDType = machineInfo.getRGBDMode();
        }
        sb.append(rGBDType);
        tvDebugInfo.setText(sb.toString());
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.RgbdTestActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = RgbdTestActivity.this.TAG;
                Pdlog.m3275i(str, "click quit");
                RgbdTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnErrorQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.RgbdTestActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = RgbdTestActivity.this.TAG;
                Pdlog.m3275i(str, "click error quit");
                RgbdTestActivity.this.getMTestItem().setStatus(TestStatus.FAIL);
                RgbdTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.RgbdTestActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = RgbdTestActivity.this.TAG;
                Pdlog.m3275i(str, "click reset");
                RgbdTestActivity.this.getMTestItem().setStatus(TestStatus.TESTING);
                RgbdTestActivity.this.autoTest();
            }
        });
        TestItem testItem3 = this.mTestItem;
        if (testItem3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem3.setStatus(TestStatus.TESTING);
        autoTest();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        removeListener();
    }

    public final void setRGBDListener() {
        Pdlog.m3273d(this.TAG, "setRGBDListener ");
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface == null) {
            Intrinsics.throwNpe();
        }
        hdInterface.getRGBDInterface().addStateListener(this.TAG, new RgbdTestActivity$setRGBDListener$1(this));
    }

    public final void removeListener() {
        RGBDInterface rGBDInterface;
        Pdlog.m3273d(this.TAG, "removeListener ");
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface == null || (rGBDInterface = hdInterface.getRGBDInterface()) == null) {
            return;
        }
        rGBDInterface.removeStateListener(this.TAG);
    }

    public final void runStepSuccess() {
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setStatus(TestStatus.SUCCESS);
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("RGBD测试结束\n测试结果是：成功");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("完成", "下一个未测试项"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.RgbdTestActivity$runStepSuccess$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RgbdTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.RgbdTestActivity$runStepSuccess$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> action;
                TestItem nextUntested = AllTestItem.INSTANCE.getNextUntested(RgbdTestActivity.this.getMTestItem());
                if (nextUntested != null && (action = nextUntested.getAction()) != null) {
                    action.invoke();
                }
                RgbdTestActivity.this.removeListener();
                RgbdTestActivity.this.finish();
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
        sb.append("RGBD测试结束\n测试结果是：失败\n描述：");
        TestItem testItem2 = this.mTestItem;
        if (testItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        sb.append(testItem2.getFailDescription());
        tvGuide.setText(sb.toString());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重新测试"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.RgbdTestActivity$runStepFail$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RgbdTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.RgbdTestActivity$runStepFail$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((AppCompatButton) RgbdTestActivity.this._$_findCachedViewById(C4491R.id.btnReset)).callOnClick();
            }
        });
    }

    public final Function1<Boolean, Unit> getOnOpen() {
        return this.onOpen;
    }

    public final void setOnOpen(Function1<? super Boolean, Unit> function1) {
        this.onOpen = function1;
    }

    public final void autoTest() {
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        this.onOpen = (Function1) null;
        ((LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions)).removeAllViews();
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("自动测试中，请稍等");
        setRGBDListener();
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface != null) {
            hdInterface.controlRGBD(false);
        }
        this.onOpen = new Function1<Boolean, Unit>() { // from class: com.pudutech.factory_test.single_test.RgbdTestActivity$autoTest$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                String str;
                RgbdTestActivity.this.setOnOpen((Function1) null);
                if (z) {
                    RgbdTestActivity.this.getMTestItem().setFailDescription("关闭RGBD失败，可能是固件问题");
                    RgbdTestActivity.this.runStepFail();
                    return;
                }
                HardwareInterface hdInterface2 = ServiceConnectionKt.getHdInterface();
                if (hdInterface2 != null) {
                    hdInterface2.controlRGBD(true);
                }
                str = RgbdTestActivity.this.TAG;
                Pdlog.m3273d(str, "control rgbd true");
                RgbdTestActivity.this.setOnOpen(new Function1<Boolean, Unit>() { // from class: com.pudutech.factory_test.single_test.RgbdTestActivity$autoTest$1.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z2) {
                        RgbdTestActivity.this.setOnOpen((Function1) null);
                        if (z2) {
                            RgbdTestActivity.this.runStepSuccess();
                        } else {
                            RgbdTestActivity.this.getMTestItem().setFailDescription("打开RGBD失败，可能是连线或者固件问题");
                            RgbdTestActivity.this.runStepFail();
                        }
                    }
                });
            }
        };
    }
}
