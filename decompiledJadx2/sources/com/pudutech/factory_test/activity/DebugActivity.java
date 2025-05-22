package com.pudutech.factory_test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.activity.PeripheralsActivity;
import com.pudutech.bumblebee.robot.activity.RecycleRobotPeripheralsActivity;
import com.pudutech.factory_test.C4491R;
import com.pudutech.factory_test.ServiceConnectionKt;
import com.pudutech.factory_test.WaterMarkUtil;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.NavigationBar;
import com.pudutech.factory_test.test_pack.WifiUtil;
import com.pudutech.factory_test.test_pack.cloud_server.CloudServerUtil;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.activity.HardwareActivity;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: DebugActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\b\u0010\t\u001a\u00020\u0006H\u0014J\b\u0010\n\u001a\u00020\u0006H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/factory_test/activity/DebugActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onStart", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class DebugActivity extends AppCompatActivity {
    private final String TAG = "DebugActivity";
    private HashMap _$_findViewCache;

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
        MachineInfo machineInfo;
        ProductMachineType productType;
        MachineModel model;
        super.onCreate(savedInstanceState);
        setContentView(2131427359);
        TextView tvVersion = (TextView) _$_findCachedViewById(C4491R.id.tvVersion);
        Intrinsics.checkExpressionValueIsNotNull(tvVersion, "tvVersion");
        StringBuilder sb = new StringBuilder();
        sb.append("版本：3.11 release\nip: ");
        sb.append(WifiUtil.INSTANCE.getLocalIpAddress(this));
        sb.append("\nmac: ");
        sb.append(WifiUtil.INSTANCE.getMac());
        sb.append("\n机器类型：");
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        sb.append((hdInterface == null || (machineInfo = hdInterface.getMachineInfo()) == null || (productType = machineInfo.getProductType()) == null || (model = productType.getModel()) == null) ? null : model.name());
        tvVersion.setText(sb.toString());
        Pdlog.m3273d(this.TAG, "onCreate");
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.activity.DebugActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DebugActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnHardware)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.activity.DebugActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DebugActivity.this.startActivity(new Intent(DebugActivity.this, (Class<?>) HardwareActivity.class));
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnRobotApp)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.activity.DebugActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Intent intent;
                if (AllTestItem.INSTANCE.isHola()) {
                    intent = new Intent(DebugActivity.this, (Class<?>) RecycleRobotPeripheralsActivity.class);
                } else {
                    intent = new Intent(DebugActivity.this, (Class<?>) PeripheralsActivity.class);
                }
                DebugActivity.this.startActivity(intent);
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnBusiness)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.activity.DebugActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                it.setEnabled(false);
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C44921(new CloudServerUtil(DebugActivity.this), it, null), 2, null);
            }

            /* compiled from: DebugActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.factory_test.activity.DebugActivity$onCreate$4$1", m3970f = "DebugActivity.kt", m3971i = {0, 1, 2}, m3972l = {58, 63, 64}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0", "L$0"})
            /* renamed from: com.pudutech.factory_test.activity.DebugActivity$onCreate$4$1 */
            /* loaded from: classes.dex */
            static final class C44921 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ CloudServerUtil $cloudServerUtil;
                final /* synthetic */ View $it;
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5163p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C44921(CloudServerUtil cloudServerUtil, View view, Continuation continuation) {
                    super(2, continuation);
                    this.$cloudServerUtil = cloudServerUtil;
                    this.$it = view;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C44921 c44921 = new C44921(this.$cloudServerUtil, this.$it, completion);
                    c44921.f5163p$ = (CoroutineScope) obj;
                    return c44921;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C44921) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:13:0x00bd  */
                /* JADX WARN: Removed duplicated region for block: B:19:0x008a A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:9:0x0093  */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final Object invokeSuspend(Object obj) {
                    CoroutineScope coroutineScope;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope2 = this.f5163p$;
                        CloudServerUtil cloudServerUtil = this.$cloudServerUtil;
                        this.L$0 = coroutineScope2;
                        this.label = 1;
                        Object checkInStorage = cloudServerUtil.checkInStorage(this);
                        if (checkInStorage == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        coroutineScope = coroutineScope2;
                        obj = checkInStorage;
                    } else {
                        if (i != 1) {
                            if (i == 2) {
                                coroutineScope = (CoroutineScope) this.L$0;
                                ResultKt.throwOnFailure(obj);
                                CloudServerUtil cloudServerUtil2 = this.$cloudServerUtil;
                                this.L$0 = coroutineScope;
                                this.label = 3;
                                obj = cloudServerUtil2.checkTestingStatus(this);
                                if (obj == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                if (((Boolean) obj).booleanValue()) {
                                }
                                return Unit.INSTANCE;
                            }
                            if (i == 3) {
                                ResultKt.throwOnFailure(obj);
                                if (((Boolean) obj).booleanValue()) {
                                    Toast.makeText(DebugActivity.this, "机器人登记为测试状态", 1).show();
                                    View it = this.$it;
                                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                                    it.setEnabled(true);
                                    DebugActivity.this.setResult(100);
                                    DebugActivity.this.finish();
                                } else {
                                    Toast.makeText(DebugActivity.this, "机器人无法变为测试状态，无法从此项进行APP切换", 1).show();
                                    View it2 = this.$it;
                                    Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                                    it2.setEnabled(true);
                                }
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        coroutineScope = (CoroutineScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                    }
                    if (Intrinsics.areEqual((Boolean) obj, Boxing.boxBoolean(true))) {
                        Toast.makeText(DebugActivity.this, "机器人已经入库，无法从此项进行APP切换", 1).show();
                        View it3 = this.$it;
                        Intrinsics.checkExpressionValueIsNotNull(it3, "it");
                        it3.setEnabled(true);
                        return Unit.INSTANCE;
                    }
                    CloudServerUtil cloudServerUtil3 = this.$cloudServerUtil;
                    this.L$0 = coroutineScope;
                    this.label = 2;
                    if (cloudServerUtil3.registerTesting(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    CloudServerUtil cloudServerUtil22 = this.$cloudServerUtil;
                    this.L$0 = coroutineScope;
                    this.label = 3;
                    obj = cloudServerUtil22.checkTestingStatus(this);
                    if (obj == coroutine_suspended) {
                    }
                    if (((Boolean) obj).booleanValue()) {
                    }
                    return Unit.INSTANCE;
                }
            }
        });
        ((SwitchCompat) _$_findCachedViewById(C4491R.id.swCloudServerDebug)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.factory_test.activity.DebugActivity$onCreate$5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton view, boolean z) {
                String str;
                str = DebugActivity.this.TAG;
                Pdlog.m3273d(str, "swCloudServerDebug OnCheckedChange  isChecked=" + z);
                if (z) {
                    Intrinsics.checkExpressionValueIsNotNull(view, "view");
                    view.setText("正式服务器");
                    CloudServerUtil.INSTANCE.setTest(false);
                } else {
                    Intrinsics.checkExpressionValueIsNotNull(view, "view");
                    view.setText("测试服务器");
                    CloudServerUtil.INSTANCE.setTest(true);
                }
            }
        });
        SwitchCompat swCloudServerDebug = (SwitchCompat) _$_findCachedViewById(C4491R.id.swCloudServerDebug);
        Intrinsics.checkExpressionValueIsNotNull(swCloudServerDebug, "swCloudServerDebug");
        swCloudServerDebug.setChecked(true ^ CloudServerUtil.INSTANCE.isTest());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        NavigationBar.statusBarDisable(62849024, this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        WaterMarkUtil.INSTANCE.onActivityStart(this);
    }
}
