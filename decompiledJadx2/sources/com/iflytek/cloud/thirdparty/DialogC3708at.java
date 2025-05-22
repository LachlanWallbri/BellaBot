package com.iflytek.cloud.thirdparty;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

/* renamed from: com.iflytek.cloud.thirdparty.at */
/* loaded from: classes3.dex */
public class DialogC3708at extends Dialog {

    /* renamed from: a */
    protected C3709au f3083a;

    /* renamed from: b */
    private a f3084b;

    /* renamed from: com.iflytek.cloud.thirdparty.at$a */
    /* loaded from: classes3.dex */
    public interface a {
        /* renamed from: a */
        void mo1979a();
    }

    public DialogC3708at(Context context) {
        super(context);
        this.f3083a = null;
        this.f3084b = new a() { // from class: com.iflytek.cloud.thirdparty.at.1
            @Override // com.iflytek.cloud.thirdparty.DialogC3708at.a
            /* renamed from: a */
            public void mo1979a() {
                DialogC3708at.this.dismiss();
            }
        };
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(this.f3083a);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }

    @Override // android.app.Dialog
    public void show() {
        setCanceledOnTouchOutside(true);
        this.f3083a.setExitCallBack(this.f3084b);
        this.f3083a.mo1981b();
        super.show();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        this.f3083a.mo1982c();
        super.dismiss();
    }

    public boolean destroy() {
        if (isShowing()) {
            return false;
        }
        boolean mo1983d = this.f3083a.mo1983d();
        this.f3083a = null;
        return mo1983d;
    }
}
