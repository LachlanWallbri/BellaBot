package com.pudutech.bumblebee.robot_ui.viewmodel;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.repo.CallSettingRepo;
import com.pudutech.disinfect.baselib.network.response.G4CodeData;
import com.pudutech.robot.opensdk.aliyun.bean.BindCodeData;
import java.util.concurrent.CancellationException;
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
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CallSettingVM.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.viewmodel.CallSettingVM$fetch4GCode$1", m3970f = "CallSettingVM.kt", m3971i = {0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2}, m3972l = {132, 139, 148}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "shopID", "$this$launch", "shopID", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_RES, "mac", "mapName", "$this$launch", "shopID", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_RES, "mac", "mapName", "clusterID", "secrete", "code"}, m3975s = {"L$0", "I$0", "L$0", "I$0", "L$1", "L$2", "L$3", "L$0", "I$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"})
/* loaded from: classes4.dex */
public final class CallSettingVM$fetch4GCode$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4978p$;
    final /* synthetic */ CallSettingVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CallSettingVM$fetch4GCode$1(CallSettingVM callSettingVM, Continuation continuation) {
        super(2, continuation);
        this.this$0 = callSettingVM;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CallSettingVM$fetch4GCode$1 callSettingVM$fetch4GCode$1 = new CallSettingVM$fetch4GCode$1(this.this$0, completion);
        callSettingVM$fetch4GCode$1.f4978p$ = (CoroutineScope) obj;
        return callSettingVM$fetch4GCode$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CallSettingVM$fetch4GCode$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00e7 A[Catch: Exception -> 0x01d2, TryCatch #0 {Exception -> 0x01d2, blocks: (B:8:0x0032, B:15:0x0051, B:17:0x00d3, B:21:0x00e7, B:25:0x00f5, B:29:0x0103, B:33:0x0111, B:39:0x018f, B:40:0x01c7, B:43:0x0061, B:45:0x0090, B:47:0x0095, B:51:0x01c8, B:52:0x01d1, B:54:0x006d), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00e4  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        String str;
        MutableLiveData mutableLiveData;
        MutableLiveData mutableLiveData2;
        Object fetch4GCode;
        CoroutineScope coroutineScope;
        int i;
        G4CodeData g4CodeData;
        String replace$default;
        Object clusterID;
        int i2;
        String str2;
        CoroutineScope coroutineScope2;
        String str3;
        MutableLiveData mutableLiveData3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        try {
        } catch (Exception e) {
            str = this.this$0.TAG;
            Pdlog.m3275i(str, "fetch4GCode.error > " + Log.getStackTraceString(e));
            if (!(e instanceof CancellationException)) {
                mutableLiveData = this.this$0._crt4gCodeLD;
                mutableLiveData.setValue(null);
                mutableLiveData2 = this.this$0._toastLD;
                mutableLiveData2.setValue(Boxing.boxInt(C4188R.string.pdStr7_136));
            }
        }
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope3 = this.f4978p$;
            int shopID = this.this$0.callRepo.shopID();
            CallSettingRepo callSettingRepo = this.this$0.callRepo;
            String valueOf = String.valueOf(shopID);
            this.L$0 = coroutineScope3;
            this.I$0 = shopID;
            this.label = 1;
            fetch4GCode = callSettingRepo.fetch4GCode(valueOf, this);
            if (fetch4GCode == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope3;
            i = shopID;
        } else {
            if (i3 != 1) {
                if (i3 != 2) {
                    if (i3 == 3) {
                        int i4 = this.I$0;
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                str2 = (String) this.L$3;
                String str4 = (String) this.L$2;
                g4CodeData = (G4CodeData) this.L$1;
                int i5 = this.I$0;
                CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                coroutineScope2 = coroutineScope4;
                i2 = i5;
                replace$default = str4;
                clusterID = obj;
                String str5 = (String) clusterID;
                String secrete = g4CodeData.getSecrete();
                if (!(replace$default.length() != 0)) {
                    if (!(str2.length() == 0)) {
                        if (!(str5.length() == 0)) {
                            if (!(secrete.length() == 0)) {
                                String str6 = "temp:" + replace$default + ":temp:" + i2 + ':' + str2 + ':' + secrete + ':' + str5;
                                str3 = this.this$0.TAG;
                                Pdlog.m3275i(str3, "fetch4GCode.code > " + str6);
                                mutableLiveData3 = this.this$0._crt4gCodeLD;
                                mutableLiveData3.setValue(new BindCodeData(str6, g4CodeData.getExpire()));
                                long expire = g4CodeData.getExpire() * ((long) 1000);
                                this.L$0 = coroutineScope2;
                                this.I$0 = i2;
                                this.L$1 = g4CodeData;
                                this.L$2 = replace$default;
                                this.L$3 = str2;
                                this.L$4 = str5;
                                this.L$5 = secrete;
                                this.L$6 = str6;
                                this.label = 3;
                                if (DelayKt.delay(expire, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                return Unit.INSTANCE;
                            }
                        }
                    }
                }
                throw new IllegalArgumentException("fetch4GCode.error args cant empty : shopID:" + i2 + " mac:" + replace$default + " mapName:" + str2 + " clusterID:" + str5 + " secrete:" + secrete);
            }
            i = this.I$0;
            CoroutineScope coroutineScope5 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            fetch4GCode = obj;
            coroutineScope = coroutineScope5;
        }
        g4CodeData = (G4CodeData) fetch4GCode;
        if (g4CodeData != null) {
            replace$default = StringsKt.replace$default(this.this$0.callRepo.mac(), ":", "", false, 4, (Object) null);
            String selectMapName = this.this$0.callRepo.selectMapName();
            CallSettingRepo callSettingRepo2 = this.this$0.callRepo;
            this.L$0 = coroutineScope;
            this.I$0 = i;
            this.L$1 = g4CodeData;
            this.L$2 = replace$default;
            this.L$3 = selectMapName;
            this.label = 2;
            clusterID = callSettingRepo2.clusterID(this);
            if (clusterID == coroutine_suspended) {
                return coroutine_suspended;
            }
            CoroutineScope coroutineScope6 = coroutineScope;
            i2 = i;
            str2 = selectMapName;
            coroutineScope2 = coroutineScope6;
            String str52 = (String) clusterID;
            String secrete2 = g4CodeData.getSecrete();
            if (!(replace$default.length() != 0)) {
            }
            throw new IllegalArgumentException("fetch4GCode.error args cant empty : shopID:" + i2 + " mac:" + replace$default + " mapName:" + str2 + " clusterID:" + str52 + " secrete:" + secrete2);
        }
        throw new Exception("callRepo.fetch4GCode.error");
    }
}
