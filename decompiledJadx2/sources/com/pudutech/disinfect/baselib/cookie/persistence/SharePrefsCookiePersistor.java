package com.pudutech.disinfect.baselib.cookie.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Cookie;

/* compiled from: SharePrefsCookiePersistor.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000eH\u0016J\u0016\u0010\u000f\u001a\u00020\b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u0011H\u0016J\u0016\u0010\u0012\u001a\u00020\b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u0011H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/cookie/persistence/SharePrefsCookiePersistor;", "Lcom/pudutech/disinfect/baselib/cookie/persistence/CookiePersistor;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "clear", "", "createCookieKey", "", "cookie", "Lokhttp3/Cookie;", "loadAll", "", "removeAll", "cookies", "", "saveAll", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SharePrefsCookiePersistor implements CookiePersistor {
    private SharedPreferences sharedPreferences;

    public SharePrefsCookiePersistor(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.sharedPreferences = context.getSharedPreferences("CookiePersistence", 0);
    }

    @Override // com.pudutech.disinfect.baselib.cookie.persistence.CookiePersistor
    public List<Cookie> loadAll() {
        SharedPreferences sharedPreferences = this.sharedPreferences;
        if (sharedPreferences == null) {
            Intrinsics.throwNpe();
        }
        ArrayList arrayList = new ArrayList(sharedPreferences.getAll().size());
        SharedPreferences sharedPreferences2 = this.sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwNpe();
        }
        Iterator<Map.Entry<String, ?>> it = sharedPreferences2.getAll().entrySet().iterator();
        while (it.hasNext()) {
            Object value = it.next().getValue();
            if (value == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
            }
            Cookie decode = new SerializableCookie().decode((String) value);
            if (decode != null) {
                arrayList.add(decode);
            }
        }
        return arrayList;
    }

    @Override // com.pudutech.disinfect.baselib.cookie.persistence.CookiePersistor
    public void saveAll(Collection<Cookie> cookies) {
        Intrinsics.checkParameterIsNotNull(cookies, "cookies");
        SharedPreferences sharedPreferences = this.sharedPreferences;
        SharedPreferences.Editor edit = sharedPreferences != null ? sharedPreferences.edit() : null;
        for (Cookie cookie : cookies) {
            if (edit != null) {
                edit.putString(createCookieKey(cookie), new SerializableCookie().encode(cookie));
            }
        }
        if (edit != null) {
            edit.commit();
        }
    }

    @Override // com.pudutech.disinfect.baselib.cookie.persistence.CookiePersistor
    public void removeAll(Collection<Cookie> cookies) {
        Intrinsics.checkParameterIsNotNull(cookies, "cookies");
        SharedPreferences sharedPreferences = this.sharedPreferences;
        SharedPreferences.Editor edit = sharedPreferences != null ? sharedPreferences.edit() : null;
        for (Cookie cookie : cookies) {
            if (edit != null) {
                edit.remove(createCookieKey(cookie));
            }
        }
        if (edit != null) {
            edit.commit();
        }
    }

    @Override // com.pudutech.disinfect.baselib.cookie.persistence.CookiePersistor
    public void clear() {
        SharedPreferences.Editor edit;
        SharedPreferences.Editor clear;
        SharedPreferences sharedPreferences = this.sharedPreferences;
        if (sharedPreferences == null || (edit = sharedPreferences.edit()) == null || (clear = edit.clear()) == null) {
            return;
        }
        clear.commit();
    }

    private final String createCookieKey(Cookie cookie) {
        if (cookie.secure()) {
            return "https:";
        }
        return "http://" + cookie.domain() + cookie.path() + "|" + cookie.name();
    }
}
