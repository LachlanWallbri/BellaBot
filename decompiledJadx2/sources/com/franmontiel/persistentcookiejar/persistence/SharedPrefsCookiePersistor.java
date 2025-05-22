package com.franmontiel.persistentcookiejar.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.Cookie;
import org.apache.http.HttpHost;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public class SharedPrefsCookiePersistor implements CookiePersistor {
    private final SharedPreferences sharedPreferences;

    public SharedPrefsCookiePersistor(Context context) {
        this(context.getSharedPreferences("CookiePersistence", 0));
    }

    public SharedPrefsCookiePersistor(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override // com.franmontiel.persistentcookiejar.persistence.CookiePersistor
    public List<Cookie> loadAll() {
        ArrayList arrayList = new ArrayList(this.sharedPreferences.getAll().size());
        Iterator<Map.Entry<String, ?>> it = this.sharedPreferences.getAll().entrySet().iterator();
        while (it.hasNext()) {
            Cookie decode = new SerializableCookie().decode((String) it.next().getValue());
            if (decode != null) {
                arrayList.add(decode);
            }
        }
        return arrayList;
    }

    @Override // com.franmontiel.persistentcookiejar.persistence.CookiePersistor
    public void saveAll(Collection<Cookie> collection) {
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        for (Cookie cookie : collection) {
            edit.putString(createCookieKey(cookie), new SerializableCookie().encode(cookie));
        }
        edit.commit();
    }

    @Override // com.franmontiel.persistentcookiejar.persistence.CookiePersistor
    public void removeAll(Collection<Cookie> collection) {
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        Iterator<Cookie> it = collection.iterator();
        while (it.hasNext()) {
            edit.remove(createCookieKey(it.next()));
        }
        edit.commit();
    }

    private static String createCookieKey(Cookie cookie) {
        StringBuilder sb = new StringBuilder();
        sb.append(cookie.secure() ? "https" : HttpHost.DEFAULT_SCHEME_NAME);
        sb.append("://");
        sb.append(cookie.domain());
        sb.append(cookie.path());
        sb.append("|");
        sb.append(cookie.name());
        return sb.toString();
    }

    @Override // com.franmontiel.persistentcookiejar.persistence.CookiePersistor
    public void clear() {
        this.sharedPreferences.edit().clear().commit();
    }
}
