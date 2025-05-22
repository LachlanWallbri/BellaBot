package com.pudutech.pd_network.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.google.gson.Gson;
import com.pudutech.pd_network.bean.GatewayPlace;
import com.pudutech.pd_network.bean.ServiceGatewayConfig;
import com.pudutech.pd_network.log.NetWorkLog;
import defpackage.CHARSET;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: NetSpUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0011J\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0011J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004J\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\u000e\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004J\u0016\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0004J\u0010\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u0004H\u0002J\u0016\u0010#\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0004J\u0010\u0010%\u001a\u00020\u00142\b\u0010&\u001a\u0004\u0018\u00010\u001bJ\u0016\u0010'\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0004J\u000e\u0010)\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u0004J\u0010\u0010*\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004H\u0002J\u0010\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u0004H\u0002J\u0010\u0010-\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004H\u0002J\u0010\u0010.\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000¨\u0006/"}, m3961d2 = {"Lcom/pudutech/pd_network/utils/NetSpUtils;", "", "()V", "BASE_URL", "", "GATEWAY_PLACE", "SECRET_KEY", "SERVICE_CONFIG", "SERVICE_CONFIG_ISP", "TAG", "TOKEN_KEY", "TOKEN_SP", "gson", "Lcom/google/gson/Gson;", "sp", "Landroid/content/SharedPreferences;", "allSecret", "", "allToken", "clearSecret", "", "clearToken", "getGatewayPlace", "Lcom/pudutech/pd_network/bean/GatewayPlace;", "getSecret", "host", "getServiceConfigWithIsp", "Lcom/pudutech/pd_network/bean/ServiceGatewayConfig;", "getToken", "init", "context", "Landroid/content/Context;", "baseUrl", "putGatewayPlace", "gatewayPlace", "putSecret", "secret", "putServiceConfigIsp", "bean", "putToken", "token", "refreshSp", "secretKey", "secretKeyHost", TransferTable.COLUMN_KEY, "tokenKey", "tokenKeyHost", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class NetSpUtils {
    private static SharedPreferences sp;
    public static final NetSpUtils INSTANCE = new NetSpUtils();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String TOKEN_SP = TOKEN_SP;
    private static final String TOKEN_SP = TOKEN_SP;
    private static String TOKEN_KEY = "TOKEN_KEY";
    private static String SECRET_KEY = "SECRET_KEY";
    private static String SERVICE_CONFIG = "SERVICE_CONFIG";
    private static String SERVICE_CONFIG_ISP = "SERVICE_CONFIG_ISP";
    private static String GATEWAY_PLACE = "GATEWAY_PLACE";
    private static String BASE_URL = "BASE_URL";
    private static final Gson gson = new Gson();

    private NetSpUtils() {
    }

    public final void init(Context context, String baseUrl) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(baseUrl, "baseUrl");
        String currentProcessName = ProcessUtil.INSTANCE.getCurrentProcessName(context);
        if (currentProcessName == null) {
            currentProcessName = "";
        }
        NetWorkLog.INSTANCE.mo3280i(TAG, "init " + currentProcessName);
        TOKEN_KEY = CHARSET.encryptTry(currentProcessName + TOKEN_KEY);
        SECRET_KEY = CHARSET.encryptTry(SECRET_KEY);
        SERVICE_CONFIG = CHARSET.encryptTry(SERVICE_CONFIG);
        SERVICE_CONFIG_ISP = CHARSET.encryptTry(SERVICE_CONFIG_ISP);
        GATEWAY_PLACE = CHARSET.encryptTry(GATEWAY_PLACE);
        BASE_URL = CHARSET.encryptTry(BASE_URL);
        SharedPreferences sharedPreferences = context.getSharedPreferences(TOKEN_SP, 0);
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "context.getSharedPrefere…SP, Context.MODE_PRIVATE)");
        sp = sharedPreferences;
        refreshSp(baseUrl);
    }

    public final void refreshSp(String baseUrl) {
        Intrinsics.checkParameterIsNotNull(baseUrl, "baseUrl");
        NetWorkLog.INSTANCE.mo3280i(TAG, "refreshSp > baseUrl:" + baseUrl + ' ');
        SharedPreferences sharedPreferences = sp;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sp");
        }
        String string = sharedPreferences.getString(BASE_URL, "");
        String decryptTry = string != null ? CHARSET.decryptTry(string) : null;
        if (!StringsKt.equals$default(decryptTry, baseUrl, false, 2, null)) {
            NetWorkLog.INSTANCE.mo3280i(TAG, "init.changeUrl preBaseUrl:" + decryptTry + " currentUrl:" + baseUrl);
            clearSecret();
            clearToken();
            putGatewayPlace("");
            putServiceConfigIsp(null);
        }
        SharedPreferences sharedPreferences2 = sp;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sp");
        }
        SharedPreferences.Editor editor = sharedPreferences2.edit();
        Intrinsics.checkExpressionValueIsNotNull(editor, "editor");
        editor.putString(BASE_URL, CHARSET.encryptTry(baseUrl));
        editor.apply();
    }

    public final void putToken(String host, String token) {
        Intrinsics.checkParameterIsNotNull(host, "host");
        Intrinsics.checkParameterIsNotNull(token, "token");
        SharedPreferences sharedPreferences = sp;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sp");
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Intrinsics.checkExpressionValueIsNotNull(editor, "editor");
        editor.putString(INSTANCE.tokenKey(host), CHARSET.encryptTry(token));
        editor.apply();
    }

    public final String getToken(String host) {
        String decryptTry;
        Intrinsics.checkParameterIsNotNull(host, "host");
        SharedPreferences sharedPreferences = sp;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sp");
        }
        String string = sharedPreferences.getString(tokenKey(host), "");
        return (string == null || (decryptTry = CHARSET.decryptTry(string)) == null) ? "" : decryptTry;
    }

    public final Map<String, String> allToken() {
        SharedPreferences sharedPreferences = sp;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sp");
        }
        Map<String, ?> all = sharedPreferences.getAll();
        Intrinsics.checkExpressionValueIsNotNull(all, "sp.all");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String key = entry.getKey();
            Intrinsics.checkExpressionValueIsNotNull(key, "it.key");
            if (StringsKt.startsWith$default(key, TOKEN_KEY, false, 2, (Object) null)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        ArrayList arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            NetSpUtils netSpUtils = INSTANCE;
            Object key2 = entry2.getKey();
            Intrinsics.checkExpressionValueIsNotNull(key2, "it.key");
            arrayList.add(TuplesKt.m3968to(netSpUtils.tokenKeyHost((String) key2), CHARSET.decryptTry(String.valueOf(entry2.getValue()))));
        }
        return MapsKt.toMap(arrayList);
    }

    public final void putSecret(String host, String secret) {
        Intrinsics.checkParameterIsNotNull(host, "host");
        Intrinsics.checkParameterIsNotNull(secret, "secret");
        SharedPreferences sharedPreferences = sp;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sp");
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Intrinsics.checkExpressionValueIsNotNull(editor, "editor");
        editor.putString(INSTANCE.secretKey(host), CHARSET.encryptTry(secret));
        editor.apply();
    }

    public final String getSecret(String host) {
        String decryptTry;
        Intrinsics.checkParameterIsNotNull(host, "host");
        SharedPreferences sharedPreferences = sp;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sp");
        }
        String string = sharedPreferences.getString(secretKey(host), "");
        return (string == null || (decryptTry = CHARSET.decryptTry(string)) == null) ? "" : decryptTry;
    }

    public final Map<String, String> allSecret() {
        SharedPreferences sharedPreferences = sp;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sp");
        }
        Map<String, ?> all = sharedPreferences.getAll();
        Intrinsics.checkExpressionValueIsNotNull(all, "sp.all");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String key = entry.getKey();
            Intrinsics.checkExpressionValueIsNotNull(key, "it.key");
            if (StringsKt.startsWith$default(key, SECRET_KEY, false, 2, (Object) null)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        ArrayList arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            NetSpUtils netSpUtils = INSTANCE;
            Object key2 = entry2.getKey();
            Intrinsics.checkExpressionValueIsNotNull(key2, "it.key");
            arrayList.add(TuplesKt.m3968to(netSpUtils.secretKeyHost((String) key2), CHARSET.decryptTry(String.valueOf(entry2.getValue()))));
        }
        return MapsKt.toMap(arrayList);
    }

    public final void putServiceConfigIsp(ServiceGatewayConfig bean) {
        if (bean == null) {
            SharedPreferences sharedPreferences = sp;
            if (sharedPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sp");
            }
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Intrinsics.checkExpressionValueIsNotNull(editor, "editor");
            editor.putString(SERVICE_CONFIG_ISP, "");
            editor.apply();
            return;
        }
        String json = gson.toJson(bean);
        SharedPreferences sharedPreferences2 = sp;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sp");
        }
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
        Intrinsics.checkExpressionValueIsNotNull(editor2, "editor");
        String str = SERVICE_CONFIG_ISP;
        Intrinsics.checkExpressionValueIsNotNull(json, "json");
        editor2.putString(str, CHARSET.encryptTry(json));
        editor2.apply();
    }

    public final ServiceGatewayConfig getServiceConfigWithIsp() {
        String str;
        SharedPreferences sharedPreferences = sp;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sp");
        }
        String string = sharedPreferences.getString(SERVICE_CONFIG_ISP, "");
        if (string == null || (str = CHARSET.decryptTry(string)) == null) {
            str = "";
        }
        if (str.length() == 0) {
            return null;
        }
        try {
            return (ServiceGatewayConfig) gson.fromJson(str, ServiceGatewayConfig.class);
        } catch (Exception unused) {
            return null;
        }
    }

    public final GatewayPlace getGatewayPlace() {
        String str;
        SharedPreferences sharedPreferences = sp;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sp");
        }
        String string = sharedPreferences.getString(GATEWAY_PLACE, "");
        if (string == null || (str = CHARSET.decryptTry(string)) == null) {
            str = "";
        }
        return GatewayPlace.INSTANCE.fromTag(str);
    }

    private final void putGatewayPlace(String gatewayPlace) {
        SharedPreferences sharedPreferences = sp;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sp");
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Intrinsics.checkExpressionValueIsNotNull(editor, "editor");
        editor.putString(SERVICE_CONFIG, CHARSET.encryptTry(gatewayPlace));
        editor.apply();
    }

    private final void clearToken() {
        SharedPreferences sharedPreferences = sp;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sp");
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Intrinsics.checkExpressionValueIsNotNull(editor, "editor");
        SharedPreferences sharedPreferences2 = sp;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sp");
        }
        Map<String, ?> all = sharedPreferences2.getAll();
        Intrinsics.checkExpressionValueIsNotNull(all, "sp.all");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String key = entry.getKey();
            Intrinsics.checkExpressionValueIsNotNull(key, "it.key");
            if (StringsKt.startsWith$default(key, TOKEN_KEY, false, 2, (Object) null)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            editor.putString((String) ((Map.Entry) it.next()).getKey(), "");
        }
        editor.apply();
    }

    private final void clearSecret() {
        SharedPreferences sharedPreferences = sp;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sp");
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Intrinsics.checkExpressionValueIsNotNull(editor, "editor");
        SharedPreferences sharedPreferences2 = sp;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sp");
        }
        Map<String, ?> all = sharedPreferences2.getAll();
        Intrinsics.checkExpressionValueIsNotNull(all, "sp.all");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String key = entry.getKey();
            Intrinsics.checkExpressionValueIsNotNull(key, "it.key");
            if (StringsKt.startsWith$default(key, SECRET_KEY, false, 2, (Object) null)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            editor.putString((String) ((Map.Entry) it.next()).getKey(), "");
        }
        editor.apply();
    }

    private final String tokenKey(String host) {
        return TOKEN_KEY + CHARSET.encryptTry(host);
    }

    private final String tokenKeyHost(String key) {
        return CHARSET.decryptTry(StringsKt.replace$default(key, TOKEN_KEY, "", false, 4, (Object) null));
    }

    private final String secretKeyHost(String key) {
        return CHARSET.decryptTry(StringsKt.replace$default(key, SECRET_KEY, "", false, 4, (Object) null));
    }

    private final String secretKey(String host) {
        return SECRET_KEY + CHARSET.encryptTry(host);
    }
}
