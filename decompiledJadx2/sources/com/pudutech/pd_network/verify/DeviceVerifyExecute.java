package com.pudutech.pd_network.verify;

import com.pudutech.pd_network.IAutoVerifyInternal;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.AutoVerify;
import com.pudutech.pd_network.bean.BaseResponse;
import com.pudutech.pd_network.bean.GetSecretRes;
import com.pudutech.pd_network.bean.GetTokenRes;
import com.pudutech.pd_network.bean.InternalGetSecretReq;
import com.pudutech.pd_network.bean.InternalGetTokenReq;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.utils.NetDataUtils;
import com.pudutech.pd_network.utils.NetSpUtils;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeviceVerifyExecute.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\u0014\u001a\u00020\u0003H\u0002J\u001a\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u000eH\u0002J\b\u0010\u0017\u001a\u00020\u0003H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0003H\u0002J\u0010\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0003H\u0002J\b\u0010\u001d\u001a\u00020\u0019H\u0002J\b\u0010\u001e\u001a\u00020\u0003H\u0016J\b\u0010\u001c\u001a\u00020\u0003H\u0016R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/pd_network/verify/DeviceVerifyExecute;", "Lcom/pudutech/pd_network/IAutoVerifyInternal;", "host", "", "appType", "(Ljava/lang/String;Ljava/lang/String;)V", "TAG", "api", "Lcom/pudutech/pd_network/verify/Api;", "getApi", "()Lcom/pudutech/pd_network/verify/Api;", "api$delegate", "Lkotlin/Lazy;", "canGetSecret", "", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "mSecret", "mToken", "secretLock", "getSecretFromNet", "getToken", "handler", "getTokenExecute", "onSecretChange", "", "crtSecret", "onTokenChange", "token", "refreshSecret", "refreshToken", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DeviceVerifyExecute implements IAutoVerifyInternal {
    private final String TAG;

    /* renamed from: api$delegate, reason: from kotlin metadata */
    private final Lazy api;
    private final String appType;
    private boolean canGetSecret;
    private final String host;
    private final ReentrantLock lock;
    private volatile String mSecret;
    private volatile String mToken;
    private final ReentrantLock secretLock;

    private final Api getApi() {
        return (Api) this.api.getValue();
    }

    public DeviceVerifyExecute(String host, String appType) {
        Intrinsics.checkParameterIsNotNull(host, "host");
        Intrinsics.checkParameterIsNotNull(appType, "appType");
        this.host = host;
        this.appType = appType;
        this.TAG = "DeviceVerifyExecute";
        this.api = LazyKt.lazy(new Function0<Api>() { // from class: com.pudutech.pd_network.verify.DeviceVerifyExecute$api$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Api invoke() {
                return (Api) PdNetworkManager.f10310INSTANCE.createService(Api.class);
            }
        });
        this.mSecret = "";
        this.mToken = "";
        this.canGetSecret = true;
        this.mSecret = NetSpUtils.INSTANCE.getSecret(this.host);
        this.mToken = NetSpUtils.INSTANCE.getToken(this.host);
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "init > " + this.host);
        this.lock = new ReentrantLock();
        this.secretLock = new ReentrantLock();
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
                    onTokenChange(getTokenExecute());
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

    private final String getTokenExecute() {
        String token$default = getToken$default(this, this.mSecret, false, 2, null);
        if (!(token$default.length() == 0)) {
            return token$default;
        }
        String str = this.mSecret;
        if ((str == null || str.length() == 0) || this.canGetSecret) {
            refreshSecret();
            String str2 = this.mSecret;
            this.canGetSecret = str2 == null || str2.length() == 0;
            return getToken(this.mSecret, true);
        }
        throw new Exception("秘钥失效,请重新获取秘钥");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0067 A[Catch: all -> 0x0094, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0042, B:7:0x0048, B:9:0x0050, B:10:0x0056, B:12:0x005a, B:18:0x0067, B:20:0x006d, B:21:0x0070, B:27:0x0078), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final synchronized String getSecretFromNet() {
        boolean z;
        BaseResponse<GetSecretRes> body = getApi().getSecret(new AutoVerify(NetDataUtils.INSTANCE.mac(), NetDataUtils.INSTANCE.pid()).toRequest()).execute().body();
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "getSecretFromNet > " + body);
        if (body != null && body.isSuccess()) {
            GetSecretRes data = body.getData();
            String secret = data != null ? data.getSecret() : null;
            if (secret != null && secret.length() != 0) {
                z = false;
                if (z) {
                    GetSecretRes data2 = body.getData();
                    if (data2 == null) {
                        Intrinsics.throwNpe();
                    }
                    return data2.getSecret();
                }
            }
            z = true;
            if (z) {
            }
        }
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "getSecretFromNet.error > " + body);
        return "";
    }

    static /* synthetic */ String getToken$default(DeviceVerifyExecute deviceVerifyExecute, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return deviceVerifyExecute.getToken(str, z);
    }

    private final String getToken(String mSecret, boolean handler) {
        AutoVerify autoVerify = new AutoVerify(NetDataUtils.INSTANCE.mac(), NetDataUtils.INSTANCE.pid());
        boolean z = true;
        if (this.mToken.length() > 0) {
            return this.mToken;
        }
        if (mSecret.length() == 0) {
            return "";
        }
        InternalGetSecretReq request = autoVerify.toRequest();
        BaseResponse<GetTokenRes> body = getApi().getToken(new InternalGetTokenReq(mSecret, request.getKey_type(), request.getMac(), autoVerify.getPid(), this.appType)).execute().body();
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "getToken > mSecret:" + mSecret + " handler:" + handler + " res:" + body);
        if (body != null && body.isSuccess()) {
            GetTokenRes data = body.getData();
            String access_token = data != null ? data.getAccess_token() : null;
            if (access_token != null && access_token.length() != 0) {
                z = false;
            }
            if (!z) {
                GetTokenRes data2 = body.getData();
                String access_token2 = data2 != null ? data2.getAccess_token() : null;
                if (access_token2 == null) {
                    Intrinsics.throwNpe();
                }
                NetWorkLog.INSTANCE.mo3280i(this.TAG, "getToken.res " + this.appType + ":newToken " + access_token2);
                return access_token2;
            }
        }
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "getToken.error");
        return "";
    }

    private final void refreshSecret() {
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "refreshSecret > " + Thread.currentThread());
        onSecretChange("");
        try {
            try {
                this.secretLock.lock();
                if (this.mSecret.length() == 0) {
                    onSecretChange(getSecretFromNet());
                }
            } catch (Exception e) {
                NetWorkLog.INSTANCE.mo3278d(this.TAG, "refreshSecret.error " + e.getMessage());
                throw e;
            }
        } finally {
            this.secretLock.unlock();
        }
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
