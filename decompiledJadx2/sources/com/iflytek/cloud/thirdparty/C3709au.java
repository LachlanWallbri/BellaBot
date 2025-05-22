package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.DialogC3708at;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

/* renamed from: com.iflytek.cloud.thirdparty.au */
/* loaded from: classes3.dex */
public class C3709au extends LinearLayout {

    /* renamed from: a */
    private DialogC3708at.a f3086a;

    /* renamed from: b */
    protected boolean f3087b;

    /* renamed from: c */
    protected boolean f3088c;

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public void mo1981b() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public void mo1982c() {
    }

    public C3709au(Context context) {
        super(context);
        this.f3086a = null;
        this.f3087b = true;
        this.f3088c = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: e */
    public void m1984e() {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT > 10) {
            intent.setAction("android.settings.SETTINGS");
        } else {
            intent.setAction("android.settings.WIRELESS_SETTINGS");
        }
        intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        getContext().startActivity(intent);
    }

    public void setExitCallBack(DialogC3708at.a aVar) {
        this.f3086a = aVar;
    }

    /* renamed from: f */
    public void m1985f() {
        DialogC3708at.a aVar = this.f3086a;
        if (aVar != null) {
            aVar.mo1979a();
        }
    }

    /* renamed from: a */
    protected static boolean m1980a(ViewGroup viewGroup) {
        try {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                childAt.setOnClickListener(null);
                childAt.setBackgroundDrawable(null);
            }
            viewGroup.removeAllViews();
            viewGroup.setOnClickListener(null);
            viewGroup.setBackgroundDrawable(null);
            return true;
        } catch (Exception e) {
            DebugLog.LogE(e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public boolean mo1983d() {
        try {
            m1980a(this);
            return true;
        } catch (Exception e) {
            DebugLog.LogE(e);
            return false;
        }
    }
}
