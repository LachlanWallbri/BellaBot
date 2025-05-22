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
import com.pudutech.factory_test.esp32.ESP32Lib;
import com.pudutech.factory_test.single_test.ESP32TestActivity;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import com.pudutech.factory_test.test_pack.TestItem;
import com.pudutech.factory_test.test_pack.TestStage;
import com.pudutech.factory_test.test_pack.TestStatus;
import com.pudutech.factory_test.test_pack.WifiUtil;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.SchCommunicateInfoListener;
import com.pudutech.mirsdk.hardware.ScheduleInfoTransportor;
import com.pudutech.mirsdk.hardware.serialize.DeviceType;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.RobotScheduleInfo;
import com.pudutech.mirsdk.hardware.serialize.ScheduleCommunicateInfo;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.TimeoutKt;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: ESP32TestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001:\u0001HB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u00107\u001a\u00020,J\u0006\u00108\u001a\u00020,J\u0006\u00109\u001a\u00020\u0016J\u0012\u0010:\u001a\u00020,2\b\u0010;\u001a\u0004\u0018\u00010<H\u0014J\b\u0010=\u001a\u00020,H\u0014J\u0006\u0010>\u001a\u00020,J\u0006\u0010?\u001a\u00020,J\u0006\u0010@\u001a\u00020,J\u0006\u0010A\u001a\u00020,J\u0006\u0010B\u001a\u00020,J\u0011\u0010C\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010DJ\u000e\u0010E\u001a\u00020,2\u0006\u0010F\u001a\u00020\u0004J*\u0010G\u001a\u00020\u0004*\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u001fj\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004` R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR$\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0016@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR1\u0010\u001e\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u001fj\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004` ¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020$X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R(\u0010)\u001a\u0010\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020,\u0018\u00010*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001a\u00101\u001a\u000202X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006I"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/ESP32TestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "allReceive", "", "getAllReceive", "()I", "setAllReceive", "(I)V", "autoJob", "Lkotlinx/coroutines/Job;", "getAutoJob", "()Lkotlinx/coroutines/Job;", "setAutoJob", "(Lkotlinx/coroutines/Job;)V", "esp32", "Lcom/pudutech/factory_test/esp32/ESP32Lib;", "getEsp32", "()Lcom/pudutech/factory_test/esp32/ESP32Lib;", "initDone", "", "getInitDone", "()Z", "setInitDone", "(Z)V", ES6Iterator.VALUE_PROPERTY, "isReading", "setReading", "keyInfo", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "getKeyInfo", "()Ljava/util/LinkedHashMap;", "mTestItem", "Lcom/pudutech/factory_test/test_pack/TestItem;", "getMTestItem", "()Lcom/pudutech/factory_test/test_pack/TestItem;", "setMTestItem", "(Lcom/pudutech/factory_test/test_pack/TestItem;)V", "onReceive", "Lkotlin/Function1;", "", "", "getOnReceive", "()Lkotlin/jvm/functions/Function1;", "setOnReceive", "(Lkotlin/jvm/functions/Function1;)V", "step", "Lcom/pudutech/factory_test/single_test/ESP32TestActivity$Step;", "getStep", "()Lcom/pudutech/factory_test/single_test/ESP32TestActivity$Step;", "setStep", "(Lcom/pudutech/factory_test/single_test/ESP32TestActivity$Step;)V", "FSM", "initReading", "isEasyNodeVersion", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "runStepChecking", "runStepFail", "runStepIDLE", "runStepServer", "runStepSuccess", "setESP32", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "showKeyInfo", "string", "toStr", "Step", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class ESP32TestActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private int allReceive;
    private Job autoJob;
    private boolean initDone;
    public TestItem mTestItem;
    private Function1<? super byte[], Unit> onReceive;
    private final String TAG = "ESP32TestActivity";
    private final ESP32Lib esp32 = new ESP32Lib();
    private Step step = Step.IDLE;
    private boolean isReading = true;
    private final LinkedHashMap<String, String> keyInfo = new LinkedHashMap<>();

    /* compiled from: ESP32TestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/ESP32TestActivity$Step;", "", "(Ljava/lang/String;I)V", "IDLE", "SERVER", "CHECKING", "FAIL", "SUCCESS", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum Step {
        IDLE,
        SERVER,
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
            $EnumSwitchMapping$0[Step.SERVER.ordinal()] = 2;
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

    public final ESP32Lib getEsp32() {
        return this.esp32;
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
                if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.ESP32) && testItem2.getStage() == fromValue) {
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
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ESP32TestActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = ESP32TestActivity.this.TAG;
                Pdlog.m3275i(str, "click quit");
                ESP32TestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnErrorQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ESP32TestActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = ESP32TestActivity.this.TAG;
                Pdlog.m3275i(str, "click error quit");
                ESP32TestActivity.this.getMTestItem().setStatus(TestStatus.FAIL);
                ESP32TestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ESP32TestActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = ESP32TestActivity.this.TAG;
                Pdlog.m3275i(str, "click reset");
                ESP32TestActivity.this.getMTestItem().setStatus(TestStatus.TESTING);
                ESP32TestActivity.this.setStep(ESP32TestActivity.Step.IDLE);
                ESP32TestActivity.this.FSM();
            }
        });
        FSM();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.onReceive = (Function1) null;
        setReading(false);
        Job job = this.autoJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        if (!isEasyNodeVersion()) {
            this.esp32.close();
            return;
        }
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface == null) {
            Intrinsics.throwNpe();
        }
        hdInterface.getScheduler().removeScheduleInfoProducer(this.TAG);
        HardwareInterface hdInterface2 = ServiceConnectionKt.getHdInterface();
        if (hdInterface2 == null) {
            Intrinsics.throwNpe();
        }
        hdInterface2.getScheduler().removeSchCommInfoListener(this.TAG);
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
            runStepServer();
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

    public final void runStepIDLE() {
        Job launch$default;
        this.keyInfo.clear();
        AppCompatTextView tvDebugInfo = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvDebugInfo);
        Intrinsics.checkExpressionValueIsNotNull(tvDebugInfo, "tvDebugInfo");
        tvDebugInfo.setText("");
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setFailDescription((String) null);
        this.onReceive = (Function1) null;
        Job job = this.autoJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        if (!isEasyNodeVersion()) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new ESP32TestActivity$runStepIDLE$5(this, null), 2, null);
            this.autoJob = launch$default;
            return;
        }
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface == null) {
            Intrinsics.throwNpe();
        }
        hdInterface.getScheduler().addScheduleInfoProducer(this.TAG, new ScheduleInfoTransportor.Stub() { // from class: com.pudutech.factory_test.single_test.ESP32TestActivity$runStepIDLE$1
            @Override // com.pudutech.mirsdk.hardware.ScheduleInfoTransportor
            public void updateOtherRobotInfo(RobotScheduleInfo info) {
                String str;
                Function1<byte[], Unit> onReceive;
                str = ESP32TestActivity.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("receive other robot ");
                sb.append(info != null ? info.getRobot_id() : null);
                sb.append(" schedule info from hardware, then send to native interface");
                objArr[0] = sb.toString();
                Pdlog.m3273d(str, objArr);
                if (info == null || (onReceive = ESP32TestActivity.this.getOnReceive()) == null) {
                    return;
                }
                byte[] bytes = "test".getBytes(Charsets.UTF_8);
                Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                onReceive.invoke(bytes);
            }
        });
        HardwareInterface hdInterface2 = ServiceConnectionKt.getHdInterface();
        if (hdInterface2 == null) {
            Intrinsics.throwNpe();
        }
        hdInterface2.getScheduler().addSchCommInfoListener(this.TAG, new SchCommunicateInfoListener.Stub() { // from class: com.pudutech.factory_test.single_test.ESP32TestActivity$runStepIDLE$2
            @Override // com.pudutech.mirsdk.hardware.SchCommunicateInfoListener
            public void scheduleCommunicateInfo(ScheduleCommunicateInfo p0) {
                String str;
                Function1<byte[], Unit> onReceive;
                str = ESP32TestActivity.this.TAG;
                Pdlog.m3273d(str, "scheduleCommunicateInfo");
                if (p0 == null || (onReceive = ESP32TestActivity.this.getOnReceive()) == null) {
                    return;
                }
                byte[] bytes = "test".getBytes(Charsets.UTF_8);
                Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                onReceive.invoke(bytes);
            }
        });
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("调度通信测试：\n该测试需要另外一台机器作为服务端");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("作为服务端", "开始测试"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ESP32TestActivity$runStepIDLE$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ESP32TestActivity.this.setStep(ESP32TestActivity.Step.SERVER);
                ESP32TestActivity.this.FSM();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ESP32TestActivity$runStepIDLE$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ESP32TestActivity.this.getMTestItem().setStatus(TestStatus.TESTING);
                ESP32TestActivity.this.setStep(ESP32TestActivity.Step.CHECKING);
                ESP32TestActivity.this.FSM();
            }
        });
    }

    public final boolean isEasyNodeVersion() {
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface == null) {
            Intrinsics.throwNpe();
        }
        UByte uByte = hdInterface.getMachineInfo().getByte23Data().get(MachineInfo.Byte23Info.ESP32Version);
        if (uByte == null) {
            Intrinsics.throwNpe();
        }
        return (uByte.getData() & 255) == 4;
    }

    public final void runStepServer() {
        Pdlog.m3273d(this.TAG, "runStepServer ");
        this.keyInfo.put("本机mac", WifiUtil.INSTANCE.getMac());
        showKeyInfo(toStr(this.keyInfo));
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("调度通信测试：\n目前该机作为服务端");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("停止"), 0.0f, 4, null);
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        if (isEasyNodeVersion()) {
            this.onReceive = new Function1<byte[], Unit>() { // from class: com.pudutech.factory_test.single_test.ESP32TestActivity$runStepServer$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(byte[] bArr) {
                    invoke2(bArr);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(byte[] bytes) {
                    Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                    RobotScheduleInfo robotScheduleInfo = new RobotScheduleInfo();
                    robotScheduleInfo.setMap_flag(String.valueOf(WifiUtil.INSTANCE.getMac()));
                    HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
                    if (hdInterface == null) {
                        Intrinsics.throwNpe();
                    }
                    hdInterface.getScheduler().sendRobotScheduleCommunicationInfo(robotScheduleInfo);
                }
            };
        } else {
            this.onReceive = (Function1) new Function1<byte[], Unit>() { // from class: com.pudutech.factory_test.single_test.ESP32TestActivity$runStepServer$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(byte[] bArr) {
                    invoke2(bArr);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(byte[] bytes) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                    if (bytes.length == 18) {
                        str = ESP32TestActivity.this.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("cnt=");
                        Ref.IntRef intRef2 = intRef;
                        int i = intRef2.element;
                        intRef2.element = i + 1;
                        sb.append(i);
                        sb.append(". send it back. ");
                        sb.append(new String(bytes, Charsets.UTF_8));
                        sb.append(' ');
                        sb.append((int) ArraysKt.last(bytes));
                        Pdlog.m3273d(str, sb.toString());
                        ESP32Lib esp32 = ESP32TestActivity.this.getEsp32();
                        String mac = WifiUtil.INSTANCE.getMac();
                        if (mac == null) {
                            Intrinsics.throwNpe();
                        }
                        Charset charset = Charsets.UTF_8;
                        if (mac == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        byte[] bytes2 = mac.getBytes(charset);
                        Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
                        esp32.sendData(ArraysKt.plus(bytes, bytes2));
                    }
                }
            };
        }
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ESP32TestActivity$runStepServer$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ESP32TestActivity.this.setStep(ESP32TestActivity.Step.IDLE);
                ESP32TestActivity.this.FSM();
            }
        });
    }

    public final Job getAutoJob() {
        return this.autoJob;
    }

    public final void setAutoJob(Job job) {
        this.autoJob = job;
    }

    /* renamed from: isReading, reason: from getter */
    public final boolean getIsReading() {
        return this.isReading;
    }

    public final void setReading(boolean z) {
        this.isReading = z;
        Pdlog.m3273d(this.TAG, "set isReading=" + z);
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [T, java.lang.String] */
    public final void runStepChecking() {
        Job launch$default;
        Job launch$default2;
        Pdlog.m3273d(this.TAG, "runStepChecking ");
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("调度通信测试:\n自动测试中，请稍等");
        this.keyInfo.put("本机mac", WifiUtil.INSTANCE.getMac());
        ((LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions)).removeAllViews();
        if (isEasyNodeVersion()) {
            final Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = 0;
            this.onReceive = (Function1) new Function1<byte[], Unit>() { // from class: com.pudutech.factory_test.single_test.ESP32TestActivity$runStepChecking$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(byte[] bArr) {
                    invoke2(bArr);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(byte[] bytes) {
                    Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                    Ref.IntRef.this.element++;
                }
            };
            launch$default2 = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new ESP32TestActivity$runStepChecking$2(this, intRef, null), 2, null);
            this.autoJob = launch$default2;
            return;
        }
        final Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = 0;
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (String) 0;
        this.onReceive = (Function1) new Function1<byte[], Unit>() { // from class: com.pudutech.factory_test.single_test.ESP32TestActivity$runStepChecking$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(byte[] bArr) {
                invoke2(bArr);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v5, types: [T, java.lang.Object, java.lang.String] */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(byte[] bytes) {
                String str;
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                if (bytes.length == 35) {
                    byte b = bytes[17];
                    String str2 = new String(ArraysKt.copyOfRange(bytes, 0, 17), Charsets.UTF_8);
                    ?? str3 = new String(ArraysKt.copyOfRange(bytes, 18, bytes.length), Charsets.UTF_8);
                    if (((String) objectRef.element) == null) {
                        objectRef.element = str3;
                        ESP32TestActivity.this.getKeyInfo().put("服务端", (String) objectRef.element);
                    }
                    str = ESP32TestActivity.this.TAG;
                    Pdlog.m3275i(str, "receive string=" + str2 + " from " + ((String) str3) + " index=" + ((int) b));
                    if (Intrinsics.areEqual(str2, WifiUtil.INSTANCE.getMac()) && Intrinsics.areEqual((String) objectRef.element, (Object) str3)) {
                        intRef2.element++;
                    }
                }
            }
        };
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new ESP32TestActivity$runStepChecking$4(this, intRef2, null), 2, null);
        this.autoJob = launch$default;
    }

    public final void runStepSuccess() {
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setStatus(TestStatus.SUCCESS);
        showKeyInfo(toStr(this.keyInfo));
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("调度通信模块测试结束\n测试结果是：成功");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("完成", "下一个未测试项"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ESP32TestActivity$runStepSuccess$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ESP32TestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ESP32TestActivity$runStepSuccess$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> action;
                TestItem nextUntested = AllTestItem.INSTANCE.getNextUntested(ESP32TestActivity.this.getMTestItem());
                if (nextUntested != null && (action = nextUntested.getAction()) != null) {
                    action.invoke();
                }
                ESP32TestActivity.this.finish();
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
        showKeyInfo(toStr(this.keyInfo));
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        StringBuilder sb = new StringBuilder();
        sb.append("调度通信模块测试结束\n测试结果是：失败\n描述：");
        TestItem testItem2 = this.mTestItem;
        if (testItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        sb.append(testItem2.getFailDescription());
        tvGuide.setText(sb.toString());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重新测试"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ESP32TestActivity$runStepFail$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ESP32TestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ESP32TestActivity$runStepFail$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((AppCompatButton) ESP32TestActivity.this._$_findCachedViewById(C4491R.id.btnReset)).callOnClick();
            }
        });
    }

    public final boolean getInitDone() {
        return this.initDone;
    }

    public final void setInitDone(boolean z) {
        this.initDone = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r1v27, types: [T, java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r7v6, types: [T, java.lang.Boolean] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x01a3 -> B:13:0x01d9). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x01d0 -> B:12:0x01d4). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object setESP32(Continuation<? super Boolean> continuation) {
        ESP32TestActivity$setESP32$1 eSP32TestActivity$setESP32$1;
        int i;
        int data;
        ESP32TestActivity eSP32TestActivity;
        Boolean bool;
        int i2;
        int i3;
        int i4;
        Ref.ObjectRef objectRef;
        ESP32TestActivity eSP32TestActivity2;
        Ref.ObjectRef objectRef2;
        int i5;
        int i6;
        Boolean bool2;
        if (continuation instanceof ESP32TestActivity$setESP32$1) {
            eSP32TestActivity$setESP32$1 = (ESP32TestActivity$setESP32$1) continuation;
            if ((eSP32TestActivity$setESP32$1.label & Integer.MIN_VALUE) != 0) {
                eSP32TestActivity$setESP32$1.label -= Integer.MIN_VALUE;
                Object obj = eSP32TestActivity$setESP32$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = eSP32TestActivity$setESP32$1.label;
                int i7 = 2;
                Continuation continuation2 = null;
                char c = 0;
                boolean z = true;
                int i8 = 1;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (this.initDone) {
                        return Boxing.boxBoolean(true);
                    }
                    HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
                    if (hdInterface == null) {
                        Intrinsics.throwNpe();
                    }
                    UByte uByte = hdInterface.getMachineInfo().getByte23Data().get(MachineInfo.Byte23Info.ESP32Version);
                    if (uByte == null) {
                        Intrinsics.throwNpe();
                    }
                    if ((uByte.getData() & 255) != 4) {
                        HardwareInterface hdInterface2 = ServiceConnectionKt.getHdInterface();
                        if (hdInterface2 == null) {
                            Intrinsics.throwNpe();
                        }
                        hdInterface2.controlDevice(DeviceType.ESP, false);
                    }
                    Pdlog.m3273d(this.TAG, "setESP32 ");
                    HardwareInterface hdInterface3 = ServiceConnectionKt.getHdInterface();
                    if (hdInterface3 == null) {
                        Intrinsics.throwNpe();
                    }
                    UByte uByte2 = hdInterface3.getMachineInfo().getByte23Data().get(MachineInfo.Byte23Info.ESP32Version);
                    if (uByte2 == null) {
                        Intrinsics.throwNpe();
                    }
                    data = uByte2.getData() & 255;
                    Pdlog.m3275i(this.TAG, "espVersion=" + data);
                    ESP32TestActivity$setESP32$result$1 eSP32TestActivity$setESP32$result$1 = new ESP32TestActivity$setESP32$result$1(this, data, null);
                    eSP32TestActivity$setESP32$1.L$0 = this;
                    eSP32TestActivity$setESP32$1.I$0 = data;
                    eSP32TestActivity$setESP32$1.label = 1;
                    obj = TimeoutKt.withTimeoutOrNull(5000L, eSP32TestActivity$setESP32$result$1, eSP32TestActivity$setESP32$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    eSP32TestActivity = this;
                } else if (i == 1) {
                    data = eSP32TestActivity$setESP32$1.I$0;
                    eSP32TestActivity = (ESP32TestActivity) eSP32TestActivity$setESP32$1.L$0;
                    ResultKt.throwOnFailure(obj);
                } else if (i == 2) {
                    objectRef = (Ref.ObjectRef) eSP32TestActivity$setESP32$1.L$3;
                    int i9 = eSP32TestActivity$setESP32$1.I$3;
                    int i10 = eSP32TestActivity$setESP32$1.I$2;
                    int i11 = eSP32TestActivity$setESP32$1.I$1;
                    objectRef2 = (Ref.ObjectRef) eSP32TestActivity$setESP32$1.L$2;
                    Boolean bool3 = (Boolean) eSP32TestActivity$setESP32$1.L$1;
                    int i12 = eSP32TestActivity$setESP32$1.I$0;
                    eSP32TestActivity2 = (ESP32TestActivity) eSP32TestActivity$setESP32$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    i6 = i12;
                    bool2 = bool3;
                    i5 = i9;
                    i2 = i10;
                    i3 = i11;
                    objectRef.element = (Boolean) obj;
                    if (Intrinsics.areEqual((Boolean) objectRef2.element, Boxing.boxBoolean(i8))) {
                    }
                } else {
                    if (i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    int i13 = eSP32TestActivity$setESP32$1.I$3;
                    int i14 = eSP32TestActivity$setESP32$1.I$2;
                    i3 = eSP32TestActivity$setESP32$1.I$1;
                    Ref.ObjectRef objectRef3 = (Ref.ObjectRef) eSP32TestActivity$setESP32$1.L$2;
                    Boolean bool4 = (Boolean) eSP32TestActivity$setESP32$1.L$1;
                    int i15 = eSP32TestActivity$setESP32$1.I$0;
                    ESP32TestActivity eSP32TestActivity3 = (ESP32TestActivity) eSP32TestActivity$setESP32$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    i6 = i15;
                    eSP32TestActivity2 = eSP32TestActivity3;
                    i4 = i6;
                    int i16 = 1;
                    i2 = i14;
                    objectRef = objectRef3;
                    i3 += i16;
                    bool = bool4;
                    i7 = 2;
                    continuation2 = null;
                    c = 0;
                    z = true;
                    if (i3 >= i2) {
                        i5 = Boxing.boxInt(i3).intValue();
                        ESP32TestActivity$setESP32$$inlined$repeat$lambda$1 eSP32TestActivity$setESP32$$inlined$repeat$lambda$1 = new ESP32TestActivity$setESP32$$inlined$repeat$lambda$1(continuation2, eSP32TestActivity2, objectRef, eSP32TestActivity$setESP32$1);
                        eSP32TestActivity$setESP32$1.L$0 = eSP32TestActivity2;
                        eSP32TestActivity$setESP32$1.I$0 = i4;
                        eSP32TestActivity$setESP32$1.L$1 = bool;
                        eSP32TestActivity$setESP32$1.L$2 = objectRef;
                        eSP32TestActivity$setESP32$1.I$1 = i3;
                        eSP32TestActivity$setESP32$1.I$2 = i2;
                        eSP32TestActivity$setESP32$1.I$3 = i5;
                        eSP32TestActivity$setESP32$1.L$3 = objectRef;
                        eSP32TestActivity$setESP32$1.label = i7;
                        Object withTimeoutOrNull = TimeoutKt.withTimeoutOrNull(100L, eSP32TestActivity$setESP32$$inlined$repeat$lambda$1, eSP32TestActivity$setESP32$1);
                        if (withTimeoutOrNull == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        i6 = i4;
                        bool2 = bool;
                        obj = withTimeoutOrNull;
                        objectRef2 = objectRef;
                        i8 = z;
                        objectRef.element = (Boolean) obj;
                        if (Intrinsics.areEqual((Boolean) objectRef2.element, Boxing.boxBoolean(i8))) {
                            String str = eSP32TestActivity2.TAG;
                            Object[] objArr = new Object[i8];
                            objArr[c] = "response is false. try again";
                            Pdlog.m3273d(str, objArr);
                            eSP32TestActivity$setESP32$1.L$0 = eSP32TestActivity2;
                            eSP32TestActivity$setESP32$1.I$0 = i6;
                            eSP32TestActivity$setESP32$1.L$1 = bool2;
                            eSP32TestActivity$setESP32$1.L$2 = objectRef2;
                            eSP32TestActivity$setESP32$1.I$1 = i3;
                            eSP32TestActivity$setESP32$1.I$2 = i2;
                            eSP32TestActivity$setESP32$1.I$3 = i5;
                            eSP32TestActivity$setESP32$1.label = 3;
                            if (DelayKt.delay(100L, eSP32TestActivity$setESP32$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            i14 = i2;
                            objectRef3 = objectRef2;
                            bool4 = bool2;
                            eSP32TestActivity3 = eSP32TestActivity2;
                            eSP32TestActivity2 = eSP32TestActivity3;
                            i4 = i6;
                            int i162 = 1;
                            i2 = i14;
                            objectRef = objectRef3;
                            i3 += i162;
                            bool = bool4;
                            i7 = 2;
                            continuation2 = null;
                            c = 0;
                            z = true;
                            if (i3 >= i2) {
                            }
                        } else {
                            objectRef = objectRef2;
                            bool4 = bool2;
                            i4 = i6;
                            i162 = 1;
                            i3 += i162;
                            bool = bool4;
                            i7 = 2;
                            continuation2 = null;
                            c = 0;
                            z = true;
                            if (i3 >= i2) {
                                eSP32TestActivity2.keyInfo.put("应答检查", Intrinsics.areEqual((Boolean) objectRef.element, Boxing.boxBoolean(true)) ? "成功" : "失败");
                                Pdlog.m3275i(eSP32TestActivity2.TAG, "checkHardwareResponse()=" + ((Boolean) objectRef.element));
                                if (!Intrinsics.areEqual((Boolean) objectRef.element, Boxing.boxBoolean(true))) {
                                    return Boxing.boxBoolean(false);
                                }
                                eSP32TestActivity2.initReading();
                                eSP32TestActivity2.initDone = true;
                                return Boxing.boxBoolean(true);
                            }
                        }
                    }
                }
                bool = (Boolean) obj;
                if (!(!Intrinsics.areEqual(bool, Boxing.boxBoolean(true)))) {
                    return Boxing.boxBoolean(false);
                }
                eSP32TestActivity.keyInfo.put("测试信道", String.valueOf((int) eSP32TestActivity.esp32.getChannel()));
                Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                objectRef4.element = (Boolean) 0;
                i2 = 5;
                i3 = 0;
                ESP32TestActivity eSP32TestActivity4 = eSP32TestActivity;
                i4 = data;
                objectRef = objectRef4;
                eSP32TestActivity2 = eSP32TestActivity4;
                if (i3 >= i2) {
                }
            }
        }
        eSP32TestActivity$setESP32$1 = new ESP32TestActivity$setESP32$1(this, continuation);
        Object obj2 = eSP32TestActivity$setESP32$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = eSP32TestActivity$setESP32$1.label;
        int i72 = 2;
        Continuation continuation22 = null;
        char c2 = 0;
        boolean z2 = true;
        int i82 = 1;
        if (i != 0) {
        }
        bool = (Boolean) obj2;
        if (!(!Intrinsics.areEqual(bool, Boxing.boxBoolean(true)))) {
        }
    }

    public final Function1<byte[], Unit> getOnReceive() {
        return this.onReceive;
    }

    public final void setOnReceive(Function1<? super byte[], Unit> function1) {
        this.onReceive = function1;
    }

    public final int getAllReceive() {
        return this.allReceive;
    }

    public final void setAllReceive(int i) {
        this.allReceive = i;
    }

    public final void initReading() {
        this.esp32.setOnReceiveData((Function1) new Function1<byte[], Unit>() { // from class: com.pudutech.factory_test.single_test.ESP32TestActivity$initReading$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(byte[] bArr) {
                invoke2(bArr);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(byte[] bytes) {
                String str;
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                int length = bytes.length;
                str = ESP32TestActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("receive size=");
                sb.append(bytes.length);
                sb.append(" allReceive=");
                ESP32TestActivity eSP32TestActivity = ESP32TestActivity.this;
                int allReceive = eSP32TestActivity.getAllReceive();
                eSP32TestActivity.setAllReceive(allReceive + 1);
                sb.append(allReceive);
                Pdlog.m3276v(str, sb.toString());
                Function1<byte[], Unit> onReceive = ESP32TestActivity.this.getOnReceive();
                if (onReceive != null) {
                    onReceive.invoke(bytes);
                }
            }
        });
        Pdlog.m3273d(this.TAG, "initReading ");
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
        Pdlog.m3275i(this.TAG, "showKeyInfo string=" + string);
        AppCompatTextView tvDebugInfo = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvDebugInfo);
        Intrinsics.checkExpressionValueIsNotNull(tvDebugInfo, "tvDebugInfo");
        tvDebugInfo.setText(string);
    }
}
