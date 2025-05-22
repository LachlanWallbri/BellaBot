package com.pudutech.mirsdk.activity;

import android.content.Context;
import android.widget.Switch;
import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.aidl.serialize.MapPackageConfig;
import com.pudutech.mirsdk.function.C4946R;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$1", m3970f = "MirSDKActivity.kt", m3971i = {0}, m3972l = {650}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
public final class MirSDKActivity$connectSDK$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5621p$;
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MirSDKActivity$connectSDK$1(MirSDKActivity mirSDKActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mirSDKActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirSDKActivity$connectSDK$1 mirSDKActivity$connectSDK$1 = new MirSDKActivity$connectSDK$1(this.this$0, completion);
        mirSDKActivity$connectSDK$1.f5621p$ = (CoroutineScope) obj;
        return mirSDKActivity$connectSDK$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MirSDKActivity$connectSDK$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        String str3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5621p$;
            SDKServiceConnection sDKServiceConnection = SDKServiceConnection.INSTANCE;
            Context applicationContext = this.this$0.getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = AIDLConnection.connect$default(sDKServiceConnection, applicationContext, null, this, 2, null);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        if (((Boolean) obj).booleanValue()) {
            str2 = this.this$0.TAG;
            Pdlog.m3275i(str2, "connect core service success");
            SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
            Boolean boxBoolean = sDKInterface != null ? Boxing.boxBoolean(sDKInterface.getInstallMode()) : null;
            str3 = this.this$0.TAG;
            Pdlog.m3275i(str3, "installMode:" + boxBoolean);
            if (boxBoolean != null) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new C48241(boxBoolean, null), 2, null);
            }
            this.this$0.initDetDropSwitch();
            this.this$0.initSlipControlSwitch();
            this.this$0.initSpeedLimit();
            this.this$0.initReflectorSwitch();
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new C48252(null), 2, null);
        } else {
            str = this.this$0.TAG;
            Pdlog.m3274e(str, "connect core service fail");
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new C48263(null), 2, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MirSDKActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$1$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$1$1 */
    /* loaded from: classes5.dex */
    public static final class C48241 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Boolean $installMode;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5622p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C48241(Boolean bool, Continuation continuation) {
            super(2, continuation);
            this.$installMode = bool;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C48241 c48241 = new C48241(this.$installMode, completion);
            c48241.f5622p$ = (CoroutineScope) obj;
            return c48241;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C48241) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5622p$;
            Switch switchInstallMode = (Switch) MirSDKActivity$connectSDK$1.this.this$0._$_findCachedViewById(C4946R.id.switchInstallMode);
            Intrinsics.checkExpressionValueIsNotNull(switchInstallMode, "switchInstallMode");
            switchInstallMode.setChecked(this.$installMode.booleanValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MirSDKActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$1$2", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$1$2 */
    /* loaded from: classes5.dex */
    public static final class C48252 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5623p$;

        C48252(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C48252 c48252 = new C48252(completion);
            c48252.f5623p$ = (CoroutineScope) obj;
            return c48252;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C48252) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x003b, code lost:
        
            if ((r5.length() == 0) != false) goto L15;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            String str;
            LocateCase locateCase;
            MapPackageConfig pdmapNameList;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5623p$;
            SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
            String str2 = null;
            String hardwareVersion = sDKInterface != null ? sDKInterface.getHardwareVersion() : null;
            TextView textViewHardwareVer = (TextView) MirSDKActivity$connectSDK$1.this.this$0._$_findCachedViewById(C4946R.id.textViewHardwareVer);
            Intrinsics.checkExpressionValueIsNotNull(textViewHardwareVer, "textViewHardwareVer");
            if (hardwareVersion != null) {
                str = hardwareVersion;
            }
            textViewHardwareVer.setText(str);
            TextView tx_locate_case = (TextView) MirSDKActivity$connectSDK$1.this.this$0._$_findCachedViewById(C4946R.id.tx_locate_case);
            Intrinsics.checkExpressionValueIsNotNull(tx_locate_case, "tx_locate_case");
            SDKInterface sDKInterface2 = SDKServiceConnection.INSTANCE.getInterface();
            if (sDKInterface2 == null || (locateCase = sDKInterface2.getLocateCase()) == null) {
                locateCase = LocateCase.Marker;
            }
            tx_locate_case.setText(locateCase.name());
            TextView current_map = (TextView) MirSDKActivity$connectSDK$1.this.this$0._$_findCachedViewById(C4946R.id.current_map);
            Intrinsics.checkExpressionValueIsNotNull(current_map, "current_map");
            StringBuilder sb = new StringBuilder();
            sb.append(MirSDKActivity$connectSDK$1.this.this$0.getResources().getString(C4946R.string.current_map));
            sb.append((char) 65306);
            SDKInterface sDKInterface3 = SDKServiceConnection.INSTANCE.getInterface();
            if (sDKInterface3 != null && (pdmapNameList = sDKInterface3.getPdmapNameList()) != null) {
                str2 = pdmapNameList.getDef_map();
            }
            sb.append(str2);
            current_map.setText(sb.toString());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MirSDKActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$1$3", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$1$3 */
    /* loaded from: classes5.dex */
    public static final class C48263 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5624p$;

        C48263(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C48263 c48263 = new C48263(completion);
            c48263.f5624p$ = (CoroutineScope) obj;
            return c48263;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C48263) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5624p$;
            TextView textViewStatus = (TextView) MirSDKActivity$connectSDK$1.this.this$0._$_findCachedViewById(C4946R.id.textViewStatus);
            Intrinsics.checkExpressionValueIsNotNull(textViewStatus, "textViewStatus");
            textViewStatus.setText("connect sdk service fail");
            return Unit.INSTANCE;
        }
    }
}
