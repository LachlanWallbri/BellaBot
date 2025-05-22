package com.pudutech.bumblebee.presenter.authorizaton_pack_task;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.disinfect.baselib.network.response.AuthorizationPackData;
import com.pudutech.disinfect.baselib.network.response.AuthorizationPackResp;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AuthorizationPackHelp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/bumblebee/presenter/authorizaton_pack_task/AuthorizationPackHelp$requestServerData$1$5$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.bumblebee.presenter.authorizaton_pack_task.AuthorizationPackHelp$requestServerData$1$invokeSuspend$$inlined$collect$1$lambda$1 */
/* loaded from: classes4.dex */
final class C4034x7f33d092 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AuthorizationPackResp $it;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4640p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C4034x7f33d092(AuthorizationPackResp authorizationPackResp, Continuation continuation) {
        super(2, continuation);
        this.$it = authorizationPackResp;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C4034x7f33d092 c4034x7f33d092 = new C4034x7f33d092(this.$it, completion);
        c4034x7f33d092.f4640p$ = (CoroutineScope) obj;
        return c4034x7f33d092;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C4034x7f33d092) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        String str3;
        String str4;
        HashMap hashMap;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4640p$;
        if (this.$it.getAuthorization() != null) {
            try {
                AuthorizationPackHelp authorizationPackHelp = AuthorizationPackHelp.INSTANCE;
                ArrayList<AuthorizationPackData> authorization = this.$it.getAuthorization();
                if (authorization == null) {
                    Intrinsics.throwNpe();
                }
                authorizationPackHelp.resetAuthMap(authorization);
                String json = new Gson().toJson(this.$it.getAuthorization());
                Context access$getContext$p = AuthorizationPackHelp.access$getContext$p(AuthorizationPackHelp.INSTANCE);
                AuthorizationPackHelp authorizationPackHelp2 = AuthorizationPackHelp.INSTANCE;
                str2 = AuthorizationPackHelp.KEY_AUTHORIZATION_PACK_CONFIG;
                Intrinsics.checkExpressionValueIsNotNull(json, "json");
                SpUtils.set(access$getContext$p, str2, json);
            } catch (Exception e) {
                AuthorizationPackHelp authorizationPackHelp3 = AuthorizationPackHelp.INSTANCE;
                str = AuthorizationPackHelp.TAG;
                Pdlog.m3274e(str, Log.getStackTraceString(e));
            }
        } else {
            AuthorizationPackHelp authorizationPackHelp4 = AuthorizationPackHelp.INSTANCE;
            hashMap = AuthorizationPackHelp.authMap;
            hashMap.clear();
        }
        ArrayList<String> positions = this.$it.getPositions();
        if (positions != null) {
            str3 = "market";
            for (String str5 : positions) {
                if (Intrinsics.areEqual(str5, "laser") && (!Intrinsics.areEqual(str3, "slamware"))) {
                    str3 = str5;
                }
                if (Intrinsics.areEqual(str5, "slamware")) {
                    str3 = str5;
                }
            }
        } else {
            str3 = "market";
        }
        AuthorizationPackHelp authorizationPackHelp5 = AuthorizationPackHelp.INSTANCE;
        str4 = AuthorizationPackHelp.TAG;
        Pdlog.m3273d(str4, "requestServerData  position=" + str3);
        if (Intrinsics.areEqual(str3, "market")) {
            SpUtils.set(AuthorizationPackHelp.access$getContext$p(AuthorizationPackHelp.INSTANCE), AuthorizationPackHelp.INSTANCE.getKEY_AUTHORIZATION_POSITION_CONFIG(), LocateCase.Marker.ordinal());
        } else if (Intrinsics.areEqual(str3, "laser")) {
            SpUtils.set(AuthorizationPackHelp.access$getContext$p(AuthorizationPackHelp.INSTANCE), AuthorizationPackHelp.INSTANCE.getKEY_AUTHORIZATION_POSITION_CONFIG(), LocateCase.LaserMark.ordinal());
        } else if (Intrinsics.areEqual(str3, "slamware")) {
            SpUtils.set(AuthorizationPackHelp.access$getContext$p(AuthorizationPackHelp.INSTANCE), AuthorizationPackHelp.INSTANCE.getKEY_AUTHORIZATION_POSITION_CONFIG(), LocateCase.Slamware.ordinal());
        }
        AuthorizationPackHelp.INSTANCE.unRegisterNetworkCallback();
        return Unit.INSTANCE;
    }
}
