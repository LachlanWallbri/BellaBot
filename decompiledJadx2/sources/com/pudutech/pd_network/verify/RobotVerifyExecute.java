package com.pudutech.pd_network.verify;

import com.pudutech.pd_network.IAutoVerifyInternal;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.BaseResponse;
import com.pudutech.pd_network.bean.GetSecretRes;
import com.pudutech.pd_network.bean.GetTokenRes;
import com.pudutech.pd_network.bean.InternalGetSecretReq;
import com.pudutech.pd_network.bean.InternalGetTokenReq;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.sn.SNComponent;
import com.pudutech.pd_network.utils.NetDataUtils;
import com.pudutech.pd_network.utils.NetSpUtils;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Lazy;
import kotlin.LazyKt;
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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: RobotVerifyExecute.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\u0016\u001a\u00020\u0003H\u0002J\b\u0010\u0017\u001a\u00020\u0003H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0003H\u0002J\u0010\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0003H\u0002J\b\u0010\u001d\u001a\u00020\u0003H\u0016J\b\u0010\u001e\u001a\u00020\u0019H\u0002J\b\u0010\u001c\u001a\u00020\u0003H\u0016J\b\u0010\u001f\u001a\u00020\u0019H\u0002R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, m3961d2 = {"Lcom/pudutech/pd_network/verify/RobotVerifyExecute;", "Lcom/pudutech/pd_network/IAutoVerifyInternal;", "host", "", "appType", "(Ljava/lang/String;Ljava/lang/String;)V", "TAG", "api", "Lcom/pudutech/pd_network/verify/Api;", "getApi", "()Lcom/pudutech/pd_network/verify/Api;", "api$delegate", "Lkotlin/Lazy;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "mSN", "mSecret", "mToken", "scope", "Lkotlinx/coroutines/CoroutineScope;", "trueRefreshCount", "", "getSecret", "getToken", "onSecretChange", "", "crtSecret", "onTokenChange", "token", "refreshToken", "resetSN", "trueRefresh", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RobotVerifyExecute implements IAutoVerifyInternal {
    private final String TAG;

    /* renamed from: api$delegate, reason: from kotlin metadata */
    private final Lazy api;
    private final String appType;
    private final String host;
    private final ReentrantLock lock;
    private volatile String mSN;
    private volatile String mSecret;
    private volatile String mToken;
    private final CoroutineScope scope;
    private int trueRefreshCount;

    private final Api getApi() {
        return (Api) this.api.getValue();
    }

    public RobotVerifyExecute(String host, String appType) {
        Intrinsics.checkParameterIsNotNull(host, "host");
        Intrinsics.checkParameterIsNotNull(appType, "appType");
        this.host = host;
        this.appType = appType;
        this.TAG = "RobotVerifyExecute";
        this.api = LazyKt.lazy(new Function0<Api>() { // from class: com.pudutech.pd_network.verify.RobotVerifyExecute$api$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Api invoke() {
                return (Api) PdNetworkManager.f10310INSTANCE.createService(Api.class);
            }
        });
        this.scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
        this.mSecret = "";
        this.mToken = "";
        this.mSN = "";
        this.mSecret = NetSpUtils.INSTANCE.getSecret(this.host);
        this.mToken = NetSpUtils.INSTANCE.getToken(this.host);
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "init > " + this.host);
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C54851(null), 3, null);
        this.lock = new ReentrantLock();
    }

    /* compiled from: RobotVerifyExecute.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.pd_network.verify.RobotVerifyExecute$1", m3970f = "RobotVerifyExecute.kt", m3971i = {0}, m3972l = {44}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
    /* renamed from: com.pudutech.pd_network.verify.RobotVerifyExecute$1 */
    /* loaded from: classes6.dex */
    static final class C54851 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6875p$;

        C54851(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C54851 c54851 = new C54851(completion);
            c54851.f6875p$ = (CoroutineScope) obj;
            return c54851;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C54851) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            RobotVerifyExecute robotVerifyExecute;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f6875p$;
                RobotVerifyExecute robotVerifyExecute2 = RobotVerifyExecute.this;
                SNComponent sNComponent = SNComponent.INSTANCE;
                this.L$0 = coroutineScope;
                this.L$1 = robotVerifyExecute2;
                this.label = 1;
                obj = sNComponent.mo3303sn(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                robotVerifyExecute = robotVerifyExecute2;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                robotVerifyExecute = (RobotVerifyExecute) this.L$1;
                ResultKt.throwOnFailure(obj);
            }
            robotVerifyExecute.mSN = (String) obj;
            return Unit.INSTANCE;
        }
    }

    @Override // com.pudutech.pd_network.IAutoVerifyInternal
    /* renamed from: token, reason: from getter */
    public String getMToken() {
        return this.mToken;
    }

    @Override // com.pudutech.pd_network.IAutoVerifyInternal
    public String refreshToken() {
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "refreshToken > " + Thread.currentThread());
        onTokenChange("");
        try {
            try {
                this.lock.lock();
                if (this.mToken.length() == 0) {
                    this.trueRefreshCount = 0;
                    trueRefresh();
                }
                this.lock.unlock();
                return this.mToken;
            } catch (Exception e) {
                NetWorkLog.INSTANCE.mo3278d(this.TAG, "refreshToken.error " + e.getMessage());
                throw e;
            }
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    private final void trueRefresh() {
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "trueRefresh > mSN:" + this.mSN + " mToken:" + this.mToken + " mSecret:" + this.mSecret + " trueRefreshCount:" + this.trueRefreshCount);
        this.trueRefreshCount = this.trueRefreshCount + 1;
        int i = this.trueRefreshCount;
        if (i > 10) {
            this.trueRefreshCount = 0;
            throw new IllegalArgumentException("trueRefresh error :refresh too many time " + i);
        }
        if (this.mSecret.length() == 0) {
            onSecretChange(getSecret());
            if (this.mSecret.length() == 0) {
                resetSN();
            }
            trueRefresh();
            return;
        }
        if (this.mToken.length() == 0) {
            onTokenChange(getToken());
            if (this.mToken.length() == 0) {
                onSecretChange("");
                trueRefresh();
            }
        }
    }

    private final void resetSN() {
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "resetSN > ");
        this.mSN = "";
        SNComponent.INSTANCE.refreshSN();
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new RobotVerifyExecute$resetSN$1(this, null), 3, null);
    }

    private final synchronized String getSecret() {
        InternalGetSecretReq internalGetSecretReq = new InternalGetSecretReq(this.mSN, NetDataUtils.INSTANCE.mac(), 0);
        BaseResponse<GetSecretRes> body = getApi().getSecretInner(internalGetSecretReq, this.host + ApiKt.ROBOT_GET_SECRET).execute().body();
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "getSecretFromNet > " + body);
        if (body != null && body.isSuccess()) {
            GetSecretRes data = body.getData();
            String secret = data != null ? data.getSecret() : null;
            if (!(secret == null || secret.length() == 0)) {
                GetSecretRes data2 = body.getData();
                if (data2 == null) {
                    Intrinsics.throwNpe();
                }
                return data2.getSecret();
            }
        }
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "getSecretFromNet.error");
        if (body != null && body.getCode() == 1310) {
            return "";
        }
        throw new IOException("getSecret error: data error > cant getSecret " + body);
    }

    private final synchronized String getToken() {
        boolean z = true;
        if (this.mSecret.length() == 0) {
            return "";
        }
        InternalGetTokenReq internalGetTokenReq = new InternalGetTokenReq(this.mSecret, 0, NetDataUtils.INSTANCE.mac(), this.mSN, this.appType);
        BaseResponse<GetTokenRes> body = getApi().getTokenInner(internalGetTokenReq, this.host + ApiKt.ROBOT_REFRESH_TOKEN).execute().body();
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "getToken > mSecret:" + this.mSecret + " res:" + body);
        if (body != null && body.isSuccess()) {
            GetTokenRes data = body.getData();
            String access_token = data != null ? data.getAccess_token() : null;
            if (access_token != null && access_token.length() != 0) {
                z = false;
            }
            GetTokenRes data2 = body.getData();
            String access_token2 = data2 != null ? data2.getAccess_token() : null;
            if (access_token2 == null) {
                Intrinsics.throwNpe();
            }
            NetWorkLog.INSTANCE.mo3280i(this.TAG, "getToken.res " + this.appType + ":newToken " + access_token2);
            return access_token2;
        }
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "getToken.error");
        if (body != null && body.getCode() == 1307) {
            return "";
        }
        if (body != null && body.getCode() == 1303) {
            return "";
        }
        throw new IOException("getToken error:data error > cant getToken " + body);
    }

    private final void onTokenChange(String token) {
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "onTokenChange > newToken:" + token + " oldToken:" + this.mToken);
        this.mToken = token;
        NetSpUtils.INSTANCE.putToken(this.host, token);
    }

    private final void onSecretChange(String crtSecret) {
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "onSecretChange > newSecret:" + crtSecret + " oldSecret:" + this.mSecret);
        NetSpUtils.INSTANCE.putSecret(this.host, crtSecret);
        this.mSecret = crtSecret;
    }
}
