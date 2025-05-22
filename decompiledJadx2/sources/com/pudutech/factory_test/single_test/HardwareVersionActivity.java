package com.pudutech.factory_test.single_test;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.os.EnvironmentCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.C4491R;
import com.pudutech.factory_test.LayoutHelperKt;
import com.pudutech.factory_test.ServiceConnectionKt;
import com.pudutech.factory_test.single_test.HardwareVersionActivity;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import com.pudutech.factory_test.test_pack.TestItem;
import com.pudutech.factory_test.test_pack.TestStage;
import com.pudutech.factory_test.test_pack.TestStatus;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.ScheduleCommunication;
import com.pudutech.mirsdk.hardware.serialize.HardwareBoard;
import com.pudutech.mirsdk.hardware.serialize.HardwareVersion;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.io.FilenameUtils;

/* compiled from: HardwareVersionActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0011\u001a\u00020\u0012J\u0012\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\u0006\u0010\u0016\u001a\u00020\u0012J\u0006\u0010\u0017\u001a\u00020\u0012J\u0006\u0010\u0018\u001a\u00020\u0012J\u000e\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/HardwareVersionActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "mTestItem", "Lcom/pudutech/factory_test/test_pack/TestItem;", "getMTestItem", "()Lcom/pudutech/factory_test/test_pack/TestItem;", "setMTestItem", "(Lcom/pudutech/factory_test/test_pack/TestItem;)V", "step", "Lcom/pudutech/factory_test/single_test/HardwareVersionActivity$Step;", "getStep", "()Lcom/pudutech/factory_test/single_test/HardwareVersionActivity$Step;", "setStep", "(Lcom/pudutech/factory_test/single_test/HardwareVersionActivity$Step;)V", "FSM", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "runStepFail", "runStepIDLE", "runStepSuccess", "transToCN", "board", "Lcom/pudutech/mirsdk/hardware/serialize/HardwareBoard;", "Step", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class HardwareVersionActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    public TestItem mTestItem;
    private final String TAG = "HardwareVersionActivity";
    private Step step = Step.IDLE;

    /* compiled from: HardwareVersionActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/HardwareVersionActivity$Step;", "", "(Ljava/lang/String;I)V", "IDLE", "FAIL", "SUCCESS", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum Step {
        IDLE,
        FAIL,
        SUCCESS
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Step.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Step.IDLE.ordinal()] = 1;
            $EnumSwitchMapping$0[Step.SUCCESS.ordinal()] = 2;
            $EnumSwitchMapping$0[Step.FAIL.ordinal()] = 3;
            int[] iArr2 = new int[HardwareBoard.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[HardwareBoard.Main.ordinal()] = 1;
            $EnumSwitchMapping$1[HardwareBoard.MarkerMCU.ordinal()] = 2;
            $EnumSwitchMapping$1[HardwareBoard.Extern3399.ordinal()] = 3;
            $EnumSwitchMapping$1[HardwareBoard.Tail.ordinal()] = 4;
            $EnumSwitchMapping$1[HardwareBoard.Tray1.ordinal()] = 5;
            $EnumSwitchMapping$1[HardwareBoard.Tray2.ordinal()] = 6;
            $EnumSwitchMapping$1[HardwareBoard.Tray3.ordinal()] = 7;
            $EnumSwitchMapping$1[HardwareBoard.Tray4.ordinal()] = 8;
            $EnumSwitchMapping$1[HardwareBoard.AtomizationUV.ordinal()] = 9;
            $EnumSwitchMapping$1[HardwareBoard.RotationUV.ordinal()] = 10;
            $EnumSwitchMapping$1[HardwareBoard.PowerManagement.ordinal()] = 11;
            $EnumSwitchMapping$1[HardwareBoard.PeaunatHead.ordinal()] = 12;
            $EnumSwitchMapping$1[HardwareBoard.CAN2USB.ordinal()] = 13;
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
                if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.HARDWARE_VERSION) && testItem2.getStage() == fromValue) {
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
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.HardwareVersionActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = HardwareVersionActivity.this.TAG;
                Pdlog.m3275i(str, "click quit");
                HardwareVersionActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnErrorQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.HardwareVersionActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = HardwareVersionActivity.this.TAG;
                Pdlog.m3275i(str, "click error quit");
                HardwareVersionActivity.this.getMTestItem().setStatus(TestStatus.FAIL);
                HardwareVersionActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.HardwareVersionActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = HardwareVersionActivity.this.TAG;
                Pdlog.m3275i(str, "click reset");
                HardwareVersionActivity.this.getMTestItem().setStatus(TestStatus.TESTING);
                HardwareVersionActivity.this.setStep(HardwareVersionActivity.Step.IDLE);
                HardwareVersionActivity.this.FSM();
            }
        });
        FSM();
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
        } else if (i == 2) {
            runStepSuccess();
        } else {
            if (i != 3) {
                return;
            }
            runStepFail();
        }
    }

    public final String transToCN(HardwareBoard board) {
        Intrinsics.checkParameterIsNotNull(board, "board");
        switch (WhenMappings.$EnumSwitchMapping$1[board.ordinal()]) {
            case 1:
                return "底盘主控板";
            case 2:
                return "mark底盘固件";
            case 3:
                return "3399扩展板";
            case 4:
                return "功能板";
            case 5:
                return "第一层托盘检测板";
            case 6:
                return "第二层托盘检测板";
            case 7:
                return "第三层托盘检测板";
            case 8:
                return "第四层托盘检测板";
            case 9:
                return "雾化紫外板";
            case 10:
                return "旋转电机板";
            case 11:
                return "电源管理板";
            case 12:
                return "小花生头部usb控制板";
            case 13:
                return "usb转can";
            default:
                return "";
        }
    }

    public final void runStepIDLE() {
        ScheduleCommunication scheduler;
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        AppCompatTextView tvDebugInfo = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvDebugInfo);
        Intrinsics.checkExpressionValueIsNotNull(tvDebugInfo, "tvDebugInfo");
        tvDebugInfo.setText("");
        String str = "请确认以下信息是否正确：\n硬件版本：";
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        HardwareVersion[] hardwareVersion = hdInterface != null ? hdInterface.getHardwareVersion() : null;
        if (hardwareVersion != null) {
            for (HardwareVersion hardwareVersion2 : hardwareVersion) {
                str = str + "\n    " + transToCN(hardwareVersion2.getBoard()) + ": " + hardwareVersion2.getVer0() + FilenameUtils.EXTENSION_SEPARATOR + hardwareVersion2.getVer1() + FilenameUtils.EXTENSION_SEPARATOR + hardwareVersion2.getVer2();
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("\nESP: ");
        HardwareInterface hdInterface2 = ServiceConnectionKt.getHdInterface();
        sb.append((hdInterface2 == null || (scheduler = hdInterface2.getScheduler()) == null) ? null : scheduler.getESPVersion());
        String str2 = sb.toString() + "\n\nMachineInfo:";
        HardwareInterface hdInterface3 = ServiceConnectionKt.getHdInterface();
        MachineInfo machineInfo = hdInterface3 != null ? hdInterface3.getMachineInfo() : null;
        StringBuffer stringBuffer = new StringBuffer();
        if (machineInfo != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("    LidarVersion:");
            Object obj = machineInfo.getInt(MachineInfo.IntInfo.ldsSensorVersion);
            if (obj == null) {
                obj = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            sb2.append(obj);
            sb2.append('\n');
            stringBuffer.append(sb2.toString());
            stringBuffer.append("    ESPMode:" + machineInfo.getESPMode().name() + '\n');
            stringBuffer.append("    RGBDMode:" + machineInfo.getRGBDMode().name() + '\n');
            stringBuffer.append("    Product:" + machineInfo.getProductType().getModel().name() + '\n');
            stringBuffer.append("    Audio:" + machineInfo.getAudioType().name() + '\n');
            stringBuffer.append("    Lora:" + machineInfo.getLoraType().name() + '\n');
            stringBuffer.append("    ScanCode:" + machineInfo.getScanCodeDeviceType().name() + '\n');
            stringBuffer.append("    Monocular:" + machineInfo.getMonocularDeviceType().name() + '\n');
        }
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText(str2 + '\n' + stringBuffer);
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("正确", "错误"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.HardwareVersionActivity$runStepIDLE$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareVersionActivity.this.setStep(HardwareVersionActivity.Step.SUCCESS);
                HardwareVersionActivity.this.FSM();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.HardwareVersionActivity$runStepIDLE$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareVersionActivity.this.setStep(HardwareVersionActivity.Step.FAIL);
                HardwareVersionActivity.this.FSM();
            }
        });
    }

    public final void runStepSuccess() {
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setStatus(TestStatus.SUCCESS);
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("硬件版本验证正确\n测试结果是：成功");
        ((AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide)).scrollTo(0, 0);
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("完成", "下一个未测试项"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.HardwareVersionActivity$runStepSuccess$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareVersionActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.HardwareVersionActivity$runStepSuccess$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> action;
                TestItem nextUntested = AllTestItem.INSTANCE.getNextUntested(HardwareVersionActivity.this.getMTestItem());
                if (nextUntested != null && (action = nextUntested.getAction()) != null) {
                    action.invoke();
                }
                HardwareVersionActivity.this.finish();
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
        tvGuide.setText("硬件版本验证失败\n测试结果是：失败");
        ((AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide)).scrollTo(0, 0);
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重新测试"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.HardwareVersionActivity$runStepFail$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HardwareVersionActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.HardwareVersionActivity$runStepFail$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((AppCompatButton) HardwareVersionActivity.this._$_findCachedViewById(C4491R.id.btnReset)).callOnClick();
            }
        });
    }
}
