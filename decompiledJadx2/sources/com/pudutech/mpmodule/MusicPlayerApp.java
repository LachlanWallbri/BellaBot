package com.pudutech.mpmodule;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.mpmodule.database.DatabaseManagerFactory;
import com.pudutech.mpmodule.utils.CThreadPoolExecutor;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class MusicPlayerApp {
    private Context mContext;

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public interface ISharedParamsCallback {
        Context getContext();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    private static class Singleton {
        private static final MusicPlayerApp INSTANCE = new MusicPlayerApp();

        private Singleton() {
        }
    }

    public static MusicPlayerApp getInstance() {
        return Singleton.INSTANCE;
    }

    public void setSharedParamsCallback(ISharedParamsCallback iSharedParamsCallback) {
        if (iSharedParamsCallback != null) {
            this.mContext = iSharedParamsCallback.getContext();
        }
        CThreadPoolExecutor.runInBackground(new Runnable() { // from class: com.pudutech.mpmodule.-$$Lambda$MusicPlayerApp$oi-2m1TfFdqhHaEJTAsa_bK0zPk
            @Override // java.lang.Runnable
            public final void run() {
                DatabaseManagerFactory.getDatabaseManager().dealWithLegacy();
            }
        });
    }

    public Context getContext() {
        if (this.mContext == null) {
            Pdlog.m3274e("MusicPlayerApp", "Context is null, please let the caller must initialize and call setSharedParamsCallback() method!");
        }
        return this.mContext;
    }
}
