package com.pudutech.factory_test.single_test;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
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
import com.pudutech.factory_test.single_test.WifiTestActivity2;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import com.pudutech.factory_test.test_pack.TestItem;
import com.pudutech.factory_test.test_pack.TestStage;
import com.pudutech.factory_test.test_pack.TestStatus;
import com.pudutech.factory_test.test_pack.WifiUtil;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: WifiTestActivity2.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u0001OB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010@\u001a\u00020/J\u0006\u0010A\u001a\u00020\u001cJ\u0006\u0010B\u001a\u00020/J\u0012\u0010C\u001a\u00020/2\b\u0010D\u001a\u0004\u0018\u00010EH\u0014J\b\u0010F\u001a\u00020/H\u0014J\u0006\u0010G\u001a\u00020/J\u0006\u0010H\u001a\u00020/J\u0006\u0010I\u001a\u00020/J\u0006\u0010J\u001a\u00020/J\u0006\u0010K\u001a\u00020/J\u000e\u0010L\u001a\u00020/2\u0006\u0010M\u001a\u00020\u0004J*\u0010N\u001a\u00020\u0004*\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\"j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004`#R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0019\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\n\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR$\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001c@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R1\u0010!\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\"j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004`#¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020'X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R(\u0010,\u001a\u0010\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020/\u0018\u00010-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u0011\u00104\u001a\u000205¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0011\u00108\u001a\u000205¢\u0006\b\n\u0000\u001a\u0004\b9\u00107R\u001a\u0010:\u001a\u00020;X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?¨\u0006P"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/WifiTestActivity2;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "BROADCAST_IP", "", "getBROADCAST_IP", "()Ljava/lang/String;", "BROADCAST_PORT", "", "getBROADCAST_PORT", "()I", "TAG", "address", "Ljava/net/InetAddress;", "kotlin.jvm.PlatformType", "getAddress", "()Ljava/net/InetAddress;", "allReceive", "getAllReceive", "setAllReceive", "(I)V", "autoJob", "Lkotlinx/coroutines/Job;", "getAutoJob", "()Lkotlinx/coroutines/Job;", "setAutoJob", "(Lkotlinx/coroutines/Job;)V", ES6Iterator.VALUE_PROPERTY, "", "isReading", "()Z", "setReading", "(Z)V", "keyInfo", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "getKeyInfo", "()Ljava/util/LinkedHashMap;", "mTestItem", "Lcom/pudutech/factory_test/test_pack/TestItem;", "getMTestItem", "()Lcom/pudutech/factory_test/test_pack/TestItem;", "setMTestItem", "(Lcom/pudutech/factory_test/test_pack/TestItem;)V", "onReceive", "Lkotlin/Function1;", "", "", "getOnReceive", "()Lkotlin/jvm/functions/Function1;", "setOnReceive", "(Lkotlin/jvm/functions/Function1;)V", "receiveSocket", "Ljava/net/DatagramSocket;", "getReceiveSocket", "()Ljava/net/DatagramSocket;", "sendSocket", "getSendSocket", "step", "Lcom/pudutech/factory_test/single_test/WifiTestActivity2$Step;", "getStep", "()Lcom/pudutech/factory_test/single_test/WifiTestActivity2$Step;", "setStep", "(Lcom/pudutech/factory_test/single_test/WifiTestActivity2$Step;)V", "FSM", "checkRSSIOK", "initReading", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "runStepChecking", "runStepFail", "runStepIDLE", "runStepServer", "runStepSuccess", "showKeyInfo", "string", "toStr", "Step", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class WifiTestActivity2 extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private int allReceive;
    private Job autoJob;
    public TestItem mTestItem;
    private Function1<? super byte[], Unit> onReceive;
    private final String TAG = "WifiTestActivity";
    private Step step = Step.IDLE;
    private boolean isReading = true;
    private final int BROADCAST_PORT = 8686;
    private final String BROADCAST_IP = "255.255.255.255";
    private final InetAddress address = InetAddress.getByName("255.255.255.255");
    private final DatagramSocket receiveSocket = new DatagramSocket(this.BROADCAST_PORT);
    private final DatagramSocket sendSocket = new DatagramSocket();
    private final LinkedHashMap<String, String> keyInfo = new LinkedHashMap<>();

    /* compiled from: WifiTestActivity2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/WifiTestActivity2$Step;", "", "(Ljava/lang/String;I)V", "IDLE", "SERVER", "CHECKING", "FAIL", "SUCCESS", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
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
                if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.WIFI) && testItem2.getStage() == fromValue) {
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
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.WifiTestActivity2$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = WifiTestActivity2.this.TAG;
                Pdlog.m3275i(str, "click quit");
                WifiTestActivity2.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnErrorQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.WifiTestActivity2$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = WifiTestActivity2.this.TAG;
                Pdlog.m3275i(str, "click error quit");
                WifiTestActivity2.this.getMTestItem().setStatus(TestStatus.FAIL);
                WifiTestActivity2.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnReset)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.WifiTestActivity2$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = WifiTestActivity2.this.TAG;
                Pdlog.m3275i(str, "click reset");
                WifiTestActivity2.this.getMTestItem().setStatus(TestStatus.TESTING);
                WifiTestActivity2.this.setStep(WifiTestActivity2.Step.IDLE);
                WifiTestActivity2.this.FSM();
            }
        });
        initReading();
        FSM();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        setReading(false);
        Job job = this.autoJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.receiveSocket.close();
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
        this.keyInfo.clear();
        AppCompatTextView tvDebugInfo = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvDebugInfo);
        Intrinsics.checkExpressionValueIsNotNull(tvDebugInfo, "tvDebugInfo");
        tvDebugInfo.setText("");
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("wifi通信测试：\n该测试需要另外一台机器作为服务端");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("作为服务端", "开始测试"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.WifiTestActivity2$runStepIDLE$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiTestActivity2.this.setStep(WifiTestActivity2.Step.SERVER);
                WifiTestActivity2.this.FSM();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.WifiTestActivity2$runStepIDLE$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiTestActivity2.this.getMTestItem().setStatus(TestStatus.TESTING);
                WifiTestActivity2.this.setStep(WifiTestActivity2.Step.CHECKING);
                WifiTestActivity2.this.FSM();
            }
        });
    }

    public final void runStepServer() {
        Pdlog.m3273d(this.TAG, "runStepServer ");
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("wifi通信测试：\n目前该机作为服务端");
        this.keyInfo.put("本机mac", WifiUtil.INSTANCE.getMac());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("停止"), 0.0f, 4, null);
        checkRSSIOK();
        showKeyInfo(toStr(this.keyInfo));
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        this.onReceive = (Function1) new Function1<byte[], Unit>() { // from class: com.pudutech.factory_test.single_test.WifiTestActivity2$runStepServer$1
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
                String str2;
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                if (bytes.length == 18) {
                    str = WifiTestActivity2.this.TAG;
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
                    byte[] plus = ArraysKt.plus(bytes, bytes2);
                    try {
                        WifiTestActivity2.this.getSendSocket().send(new DatagramPacket(plus, plus.length, WifiTestActivity2.this.getAddress(), WifiTestActivity2.this.getBROADCAST_PORT()));
                    } catch (Exception e) {
                        str2 = WifiTestActivity2.this.TAG;
                        Pdlog.m3277w(str2, String.valueOf(e));
                    }
                }
            }
        };
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.WifiTestActivity2$runStepServer$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiTestActivity2.this.setOnReceive((Function1) null);
                WifiTestActivity2.this.setStep(WifiTestActivity2.Step.IDLE);
                WifiTestActivity2.this.FSM();
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
        Pdlog.m3273d(this.TAG, "runStepChecking ");
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("wifi通信测试:\n自动测试中，请稍等");
        ((LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions)).removeAllViews();
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (String) 0;
        this.keyInfo.put("服务端mac", null);
        this.onReceive = (Function1) new Function1<byte[], Unit>() { // from class: com.pudutech.factory_test.single_test.WifiTestActivity2$runStepChecking$1
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
                        WifiTestActivity2.this.getKeyInfo().put("服务端mac", str3);
                    }
                    str = WifiTestActivity2.this.TAG;
                    Pdlog.m3275i(str, "receive string=" + str2 + " from " + ((String) str3) + " index=" + ((int) b));
                    if (Intrinsics.areEqual(str2, WifiUtil.INSTANCE.getMac()) && Intrinsics.areEqual((String) objectRef.element, (Object) str3)) {
                        intRef.element++;
                    }
                }
            }
        };
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new WifiTestActivity2$runStepChecking$2(this, intRef, null), 2, null);
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
        tvGuide.setText("wifi通信测试结束\n测试结果是：成功");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("完成", "下一个未测试项"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.WifiTestActivity2$runStepSuccess$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiTestActivity2.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.WifiTestActivity2$runStepSuccess$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> action;
                TestItem nextUntested = AllTestItem.INSTANCE.getNextUntested(WifiTestActivity2.this.getMTestItem());
                if (nextUntested != null && (action = nextUntested.getAction()) != null) {
                    action.invoke();
                }
                WifiTestActivity2.this.finish();
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
        sb.append("wifi通信测试结束\n测试结果是：失败\n描述：");
        TestItem testItem2 = this.mTestItem;
        if (testItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        sb.append(testItem2.getFailDescription());
        tvGuide.setText(sb.toString());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重新测试"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.WifiTestActivity2$runStepFail$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiTestActivity2.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.WifiTestActivity2$runStepFail$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((AppCompatButton) WifiTestActivity2.this._$_findCachedViewById(C4491R.id.btnReset)).callOnClick();
            }
        });
    }

    public final int getBROADCAST_PORT() {
        return this.BROADCAST_PORT;
    }

    public final String getBROADCAST_IP() {
        return this.BROADCAST_IP;
    }

    public final InetAddress getAddress() {
        return this.address;
    }

    public final DatagramSocket getReceiveSocket() {
        return this.receiveSocket;
    }

    public final DatagramSocket getSendSocket() {
        return this.sendSocket;
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
        Pdlog.m3273d(this.TAG, "initReading ");
        new Thread(new Runnable() { // from class: com.pudutech.factory_test.single_test.WifiTestActivity2$initReading$1
            @Override // java.lang.Runnable
            public final void run() {
                String str;
                String str2;
                String str3;
                String str4;
                str = WifiTestActivity2.this.TAG;
                Pdlog.m3275i(str, "start reading");
                byte[] bArr = new byte[1024];
                DatagramPacket datagramPacket = new DatagramPacket(bArr, 1024);
                while (WifiTestActivity2.this.getIsReading()) {
                    try {
                        WifiTestActivity2.this.getReceiveSocket().receive(datagramPacket);
                        if (datagramPacket.getLength() > 0) {
                            byte[] copyOfRange = ArraysKt.copyOfRange(bArr, 0, datagramPacket.getLength());
                            str4 = WifiTestActivity2.this.TAG;
                            Object[] objArr = new Object[1];
                            StringBuilder sb = new StringBuilder();
                            sb.append("receive size=");
                            sb.append((copyOfRange != null ? Integer.valueOf(copyOfRange.length) : null).intValue());
                            sb.append(" allReceive=");
                            WifiTestActivity2 wifiTestActivity2 = WifiTestActivity2.this;
                            int allReceive = wifiTestActivity2.getAllReceive();
                            wifiTestActivity2.setAllReceive(allReceive + 1);
                            sb.append(allReceive);
                            objArr[0] = sb.toString();
                            Pdlog.m3276v(str4, objArr);
                            Function1<byte[], Unit> onReceive = WifiTestActivity2.this.getOnReceive();
                            if (onReceive != null) {
                                onReceive.invoke(copyOfRange);
                            }
                        }
                    } catch (Exception e) {
                        WifiTestActivity2.this.setReading(false);
                        str3 = WifiTestActivity2.this.TAG;
                        Pdlog.m3277w(str3, String.valueOf(e));
                    }
                }
                WifiTestActivity2.this.getReceiveSocket().close();
                str2 = WifiTestActivity2.this.TAG;
                Pdlog.m3275i(str2, "reading end");
            }
        }).start();
    }

    public final boolean checkRSSIOK() {
        Object systemService = getSystemService("wifi");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.wifi.WifiManager");
        }
        WifiInfo info = ((WifiManager) systemService).getConnectionInfo();
        Intrinsics.checkExpressionValueIsNotNull(info, "info");
        int rssi = info.getRssi();
        this.keyInfo.put("SSID", info.getSSID());
        this.keyInfo.put("RSSI", String.valueOf(rssi));
        this.keyInfo.put("RSSI要求", "大于 -60");
        Pdlog.m3273d(this.TAG, "checkRSSIOK rssi=" + rssi);
        Pdlog.m3273d(this.TAG, "checkRSSIOK rssi=" + rssi);
        return rssi >= -60;
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
