package com.iflytek.cloud.util;

import android.content.Context;
import com.iflytek.cloud.thirdparty.C3714az;

/* loaded from: classes3.dex */
public abstract class ContactManager {

    /* loaded from: classes3.dex */
    public interface ContactListener {
        void onContactQueryFinish(String str, boolean z);
    }

    public abstract void asyncQueryAllContactsName();

    public abstract String queryAllContactsName();

    public static ContactManager getManager() {
        return C3714az.m1997a();
    }

    public static ContactManager createManager(Context context, ContactListener contactListener) {
        return C3714az.m1998a(context, contactListener);
    }

    public static void destroy() {
        C3714az.m2001c();
    }
}
