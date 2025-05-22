package com.pudutech.bumblebee.presenter.authorizaton_pack_task;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.presenter.utils.WifiUtil;
import com.pudutech.disinfect.baselib.network.response.AuthorizationPackData;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: AuthorizationPackHelp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\"B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\u0016J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000bJ\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\b\u0010\u001c\u001a\u00020\u0015H\u0002J \u0010\u001d\u001a\u00020\u00152\u0016\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\f0\u001fj\b\u0012\u0004\u0012\u00020\f` H\u0002J\b\u0010!\u001a\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R*\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nj\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/authorizaton_pack_task/AuthorizationPackHelp;", "", "()V", "KEY_AUTHORIZATION_PACK_CONFIG", "", "KEY_AUTHORIZATION_POSITION_CONFIG", "getKEY_AUTHORIZATION_POSITION_CONFIG", "()Ljava/lang/String;", "TAG", "authMap", "Ljava/util/HashMap;", "Lcom/pudutech/bumblebee/presenter/authorizaton_pack_task/AuthorizationPackHelp$PackName;", "Lcom/pudutech/disinfect/baselib/network/response/AuthorizationPackData;", "Lkotlin/collections/HashMap;", "context", "Landroid/content/Context;", "networkCallback", "Landroid/net/ConnectivityManager$NetworkCallback;", "retryCount", "", "initAuthorizationPackConfig", "", "initAuthorizationPackConfig$module_bumblebee_presenter_robotRelease", "isAuthorizationPack", "", "pack", "parse", "name", "requestServerData", "resetAuthMap", "list", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "unRegisterNetworkCallback", "PackName", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class AuthorizationPackHelp {
    private static Context context;
    private static ConnectivityManager.NetworkCallback networkCallback;
    private static int retryCount;
    public static final AuthorizationPackHelp INSTANCE = new AuthorizationPackHelp();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final HashMap<PackName, AuthorizationPackData> authMap = new HashMap<>();
    private static final String KEY_AUTHORIZATION_PACK_CONFIG = KEY_AUTHORIZATION_PACK_CONFIG;
    private static final String KEY_AUTHORIZATION_PACK_CONFIG = KEY_AUTHORIZATION_PACK_CONFIG;
    private static final String KEY_AUTHORIZATION_POSITION_CONFIG = KEY_AUTHORIZATION_POSITION_CONFIG;
    private static final String KEY_AUTHORIZATION_POSITION_CONFIG = KEY_AUTHORIZATION_POSITION_CONFIG;

    private AuthorizationPackHelp() {
    }

    public static final /* synthetic */ Context access$getContext$p(AuthorizationPackHelp authorizationPackHelp) {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context2;
    }

    public final String getKEY_AUTHORIZATION_POSITION_CONFIG() {
        return KEY_AUTHORIZATION_POSITION_CONFIG;
    }

    /* compiled from: AuthorizationPackHelp.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/authorizaton_pack_task/AuthorizationPackHelp$PackName;", "", "id", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "BIRTHDAY_MODE", "MAPS", "SPECIAL_MODE", "DIRECT_MODE", "BEEPER_CALL", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum PackName {
        BIRTHDAY_MODE("birthday_mode"),
        MAPS("maps"),
        SPECIAL_MODE("special_mode"),
        DIRECT_MODE("direct_mode"),
        BEEPER_CALL("beeper_call");

        private String id;

        PackName(String str) {
            this.id = str;
        }

        public final String getId() {
            return this.id;
        }

        public final void setId(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.id = str;
        }
    }

    private final PackName parse(String name) {
        if (Intrinsics.areEqual(name, PackName.BIRTHDAY_MODE.getId())) {
            return PackName.BIRTHDAY_MODE;
        }
        if (Intrinsics.areEqual(name, PackName.MAPS.getId())) {
            return PackName.MAPS;
        }
        if (Intrinsics.areEqual(name, PackName.SPECIAL_MODE.getId())) {
            return PackName.SPECIAL_MODE;
        }
        if (Intrinsics.areEqual(name, PackName.DIRECT_MODE.getId())) {
            return PackName.DIRECT_MODE;
        }
        if (Intrinsics.areEqual(name, PackName.BEEPER_CALL.getId())) {
            return PackName.BEEPER_CALL;
        }
        return null;
    }

    /* renamed from: initAuthorizationPackConfig$module_bumblebee_presenter_robotRelease */
    public final void m3282x69b2ebe5(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Context applicationContext = context2.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
        context = applicationContext;
        String str = SpUtils.get(context2, KEY_AUTHORIZATION_PACK_CONFIG, "");
        Pdlog.m3273d(TAG, "initAuthorizationPackConfig localConfig = " + str);
        String str2 = str;
        if (!(str2 == null || StringsKt.isBlank(str2))) {
            try {
                ArrayList<AuthorizationPackData> config = (ArrayList) new Gson().fromJson(str, new TypeToken<ArrayList<AuthorizationPackData>>() { // from class: com.pudutech.bumblebee.presenter.authorizaton_pack_task.AuthorizationPackHelp$initAuthorizationPackConfig$config$1
                }.getType());
                Intrinsics.checkExpressionValueIsNotNull(config, "config");
                resetAuthMap(config);
                Pdlog.m3273d(TAG, "local config init finish , " + authMap);
            } catch (Exception e) {
                Pdlog.m3274e(TAG, Log.getStackTraceString(e));
            }
        }
        if (!WifiUtil.INSTANCE.isNetworkAvailable(context2)) {
            Pdlog.m3273d(TAG, "net is wrong ");
            try {
                if (networkCallback == null) {
                    networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.pudutech.bumblebee.presenter.authorizaton_pack_task.AuthorizationPackHelp$initAuthorizationPackConfig$1
                        @Override // android.net.ConnectivityManager.NetworkCallback
                        public void onAvailable(Network network) {
                            String str3;
                            super.onAvailable(network);
                            AuthorizationPackHelp authorizationPackHelp = AuthorizationPackHelp.INSTANCE;
                            str3 = AuthorizationPackHelp.TAG;
                            Pdlog.m3273d(str3, "networkCallback onAvailable");
                            AuthorizationPackHelp authorizationPackHelp2 = AuthorizationPackHelp.INSTANCE;
                            AuthorizationPackHelp.retryCount = 0;
                            AuthorizationPackHelp.INSTANCE.requestServerData();
                        }

                        @Override // android.net.ConnectivityManager.NetworkCallback
                        public void onLost(Network network) {
                            String str3;
                            super.onLost(network);
                            AuthorizationPackHelp authorizationPackHelp = AuthorizationPackHelp.INSTANCE;
                            str3 = AuthorizationPackHelp.TAG;
                            Pdlog.m3273d(str3, "networkCallback onLost");
                        }
                    };
                    NetworkRequest build = new NetworkRequest.Builder().build();
                    Object systemService = context2.getSystemService("connectivity");
                    if (systemService == null) {
                        throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
                    }
                    ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
                    if (connectivityManager != null) {
                        connectivityManager.registerNetworkCallback(build, networkCallback);
                        return;
                    }
                    return;
                }
                return;
            } catch (Exception e2) {
                Pdlog.m3274e(TAG, Log.getStackTraceString(e2));
                return;
            }
        }
        retryCount = 0;
        requestServerData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetAuthMap(ArrayList<AuthorizationPackData> list) {
        authMap.clear();
        for (AuthorizationPackData authorizationPackData : list) {
            PackName parse = INSTANCE.parse(authorizationPackData.getName());
            if (parse != null) {
                authMap.put(parse, authorizationPackData);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestServerData() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AuthorizationPackHelp$requestServerData$1(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unRegisterNetworkCallback() {
        Pdlog.m3273d(TAG, "unRegisterNetworkCallback");
        if (networkCallback != null) {
            try {
                Context context2 = context;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                Object systemService = context2.getSystemService("connectivity");
                if (systemService == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
                }
                ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
                if (connectivityManager != null) {
                    connectivityManager.unregisterNetworkCallback(networkCallback);
                }
            } catch (Exception e) {
                Pdlog.m3273d(TAG, Log.getStackTraceString(e));
            }
        }
        networkCallback = (ConnectivityManager.NetworkCallback) null;
    }

    public final boolean isAuthorizationPack(PackName pack) {
        Intrinsics.checkParameterIsNotNull(pack, "pack");
        AuthorizationPackData authorizationPackData = authMap.get(pack);
        if (authorizationPackData != null) {
            return authorizationPackData.getStatus() == 1;
        }
        Pdlog.m3273d(TAG, "isAuthorizationPack " + pack + "  is null");
        return false;
    }
}
