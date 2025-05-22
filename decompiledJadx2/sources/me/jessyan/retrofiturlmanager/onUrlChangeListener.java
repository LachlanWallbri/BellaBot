package me.jessyan.retrofiturlmanager;

import okhttp3.HttpUrl;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public interface onUrlChangeListener {
    void onUrlChangeBefore(HttpUrl httpUrl, String str);

    void onUrlChanged(HttpUrl httpUrl, HttpUrl httpUrl2);
}
