package com.pudutech.mirsdk.activity;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.Toast;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.mapify.util.CamerConfigHelper;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/mirsdk/activity/MirSDKActivity$connectHardware$4", "Landroid/widget/CompoundButton$OnCheckedChangeListener;", "onCheckedChanged", "", "buttonView", "Landroid/widget/CompoundButton;", "isChecked", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MirSDKActivity$connectHardware$4 implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MirSDKActivity$connectHardware$4(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked) {
            this.this$0.checkAuth("dbgmd", true, new Function0<Unit>() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$connectHardware$4$onCheckedChanged$2
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
                    try {
                        HardwareInterface hardwareInterface = HardWareServiceConnection.INSTANCE.getInterface();
                        if (hardwareInterface != null) {
                            hardwareInterface.geomagneticCalibration(CamerConfigHelper.INSTANCE.getMagic_sensor_left(), CamerConfigHelper.INSTANCE.getMagic_sensor_right(), false);
                        }
                        SpUtils.set((Context) MirSDKActivity$connectHardware$4.this.this$0, "mirhardware", "magic_enable", false);
                    } catch (Exception unused) {
                        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new C48231(null), 2, null);
                    }
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: MirSDKActivity.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$connectHardware$4$onCheckedChanged$2$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$connectHardware$4$onCheckedChanged$2$1 */
                /* loaded from: classes5.dex */
                public static final class C48231 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f5620p$;

                    C48231(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        C48231 c48231 = new C48231(completion);
                        c48231.f5620p$ = (CoroutineScope) obj;
                        return c48231;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C48231) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        String str;
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f5620p$;
                        Toast.makeText(MirSDKActivity$connectHardware$4.this.this$0, "缺少磁场环境标定文件，请完成标定", 0).show();
                        str = MirSDKActivity$connectHardware$4.this.this$0.TAG;
                        Pdlog.m3273d(str, "magneticConfig parse fail");
                        return Unit.INSTANCE;
                    }
                }
            });
            return;
        }
        try {
            HardwareInterface hardwareInterface = HardWareServiceConnection.INSTANCE.getInterface();
            if (hardwareInterface != null) {
                hardwareInterface.geomagneticCalibration(CamerConfigHelper.INSTANCE.getMagic_sensor_left(), CamerConfigHelper.INSTANCE.getMagic_sensor_right(), true);
            }
            SpUtils.set((Context) this.this$0, "mirhardware", "magic_enable", true);
        } catch (Exception unused) {
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$connectHardware$4$onCheckedChanged$1(this, null), 2, null);
        }
    }
}
