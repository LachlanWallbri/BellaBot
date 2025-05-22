package com.pudutech.antichannelconflict.escape;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.pudutech.antichannelconflict.escape.listener.EscapeDetectListener;
import com.pudutech.antichannelconflict.escape.network.NetworkUtil;
import com.pudutech.antichannelconflict.escape.util.EscapeStatus;
import com.pudutech.antichannelconflict.escape.util.MapStatus;
import com.pudutech.antichannelconflict.escape.util.ProductType;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: EscapeManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.antichannelconflict.escape.EscapeManager$checkEscapeStatus$1", m3970f = "EscapeManager.kt", m3971i = {0}, m3972l = {168}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
public final class EscapeManager$checkEscapeStatus$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $autoLock;
    final /* synthetic */ Context $context;
    final /* synthetic */ Ref.ObjectRef $mLatitude;
    final /* synthetic */ Ref.ObjectRef $mLongitude;
    final /* synthetic */ String $mac;
    final /* synthetic */ MapStatus $mapChange;
    final /* synthetic */ String $mbts;
    final /* synthetic */ ProductType $productType;
    final /* synthetic */ String $softVersion;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4465p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EscapeManager$checkEscapeStatus$1(String str, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, boolean z, MapStatus mapStatus, String str2, ProductType productType, String str3, Context context, Continuation continuation) {
        super(2, continuation);
        this.$mac = str;
        this.$mLongitude = objectRef;
        this.$mLatitude = objectRef2;
        this.$autoLock = z;
        this.$mapChange = mapStatus;
        this.$mbts = str2;
        this.$productType = productType;
        this.$softVersion = str3;
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        EscapeManager$checkEscapeStatus$1 escapeManager$checkEscapeStatus$1 = new EscapeManager$checkEscapeStatus$1(this.$mac, this.$mLongitude, this.$mLatitude, this.$autoLock, this.$mapChange, this.$mbts, this.$productType, this.$softVersion, this.$context, completion);
        escapeManager$checkEscapeStatus$1.f4465p$ = (CoroutineScope) obj;
        return escapeManager$checkEscapeStatus$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((EscapeManager$checkEscapeStatus$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: EscapeManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u008a@¢\u0006\u0004\b\b\u0010\t"}, m3961d2 = {"<anonymous>", "", "code", "", "lockStatus", "", NotificationCompat.CATEGORY_MESSAGE, "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.antichannelconflict.escape.EscapeManager$checkEscapeStatus$1$1", m3970f = "EscapeManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.antichannelconflict.escape.EscapeManager$checkEscapeStatus$1$1 */
    /* loaded from: classes4.dex */
    public static final class C39851 extends SuspendLambda implements Function4<Integer, Boolean, String, Continuation<? super Unit>, Object> {
        int label;
        private int p$0;
        private boolean p$1;
        private String p$2;

        C39851(Continuation continuation) {
            super(4, continuation);
        }

        public final Continuation<Unit> create(int i, boolean z, String str, Continuation<? super Unit> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "continuation");
            C39851 c39851 = new C39851(continuation);
            c39851.p$0 = i;
            c39851.p$1 = z;
            c39851.p$2 = str;
            return c39851;
        }

        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(Integer num, Boolean bool, String str, Continuation<? super Unit> continuation) {
            return ((C39851) create(num.intValue(), bool.booleanValue(), str, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            EscapeDetectListener escapeDetectListener;
            EscapeDetectListener escapeDetectListener2;
            EscapeDetectListener escapeDetectListener3;
            EscapeDetectListener escapeDetectListener4;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            int i = this.p$0;
            boolean z = this.p$1;
            try {
                Pdlog.m3273d("EscapeManager", "checkEscape result", Boxing.boxInt(i), Boxing.boxBoolean(z), this.p$2);
                if (i == -1) {
                    if (EscapeManager$checkEscapeStatus$1.this.$mapChange == MapStatus.NO_CHANGE && !EscapeManager.INSTANCE.getLastEscapeStatus(EscapeManager$checkEscapeStatus$1.this.$context)) {
                        Pdlog.m3273d("EscapeManager", "checkEscapeStatus: map not change");
                        EscapeManager escapeManager = EscapeManager.INSTANCE;
                        escapeDetectListener2 = EscapeManager.listener;
                        if (escapeDetectListener2 != null) {
                            escapeDetectListener2.onEscapeDetect(EscapeStatus.UNLOCKED);
                        }
                    }
                    Pdlog.m3273d("EscapeManager", "checkEscapeStatus: map is change:" + EscapeManager$checkEscapeStatus$1.this.$mapChange + " getLastEscapeStatus(context):" + EscapeManager.INSTANCE.getLastEscapeStatus(EscapeManager$checkEscapeStatus$1.this.$context) + ' ');
                    EscapeManager escapeManager2 = EscapeManager.INSTANCE;
                    escapeDetectListener = EscapeManager.listener;
                    if (escapeDetectListener != null) {
                        escapeDetectListener.onEscapeDetect(EscapeStatus.EXCEPTION_OF_ESCAPE);
                    }
                } else if (i == 0) {
                    if (z) {
                        EscapeManager escapeManager3 = EscapeManager.INSTANCE;
                        escapeDetectListener4 = EscapeManager.listener;
                        if (escapeDetectListener4 != null) {
                            escapeDetectListener4.onEscapeDetect(EscapeStatus.LOCKED);
                        }
                        EscapeManager.INSTANCE.saveLastEscapeStatus(EscapeManager$checkEscapeStatus$1.this.$context, true);
                    } else {
                        EscapeManager escapeManager4 = EscapeManager.INSTANCE;
                        escapeDetectListener3 = EscapeManager.listener;
                        if (escapeDetectListener3 != null) {
                            escapeDetectListener3.onEscapeDetect(EscapeStatus.UNLOCKED);
                        }
                        EscapeManager.INSTANCE.saveMapListToSpf(EscapeManager$checkEscapeStatus$1.this.$context);
                        EscapeManager.INSTANCE.saveLastEscapeStatus(EscapeManager$checkEscapeStatus$1.this.$context, false);
                    }
                }
            } catch (Exception e) {
                Pdlog.m3274e("EscapeManager", "checkEscapeStatus Exception", e);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4465p$;
            NetworkUtil networkUtil = NetworkUtil.INSTANCE;
            String str = this.$mac;
            String str2 = (String) this.$mLongitude.element;
            String str3 = (String) this.$mLatitude.element;
            boolean z = this.$autoLock;
            MapStatus mapStatus = this.$mapChange;
            String str4 = this.$mbts;
            int code = this.$productType.getCode();
            String str5 = this.$softVersion;
            C39851 c39851 = new C39851(null);
            this.L$0 = coroutineScope;
            this.label = 1;
            if (networkUtil.checkEscape(str, str2, str3, z, mapStatus, str4, code, str5, c39851, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
