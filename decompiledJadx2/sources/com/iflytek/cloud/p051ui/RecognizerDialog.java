package com.iflytek.cloud.p051ui;

import android.content.Context;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.resource.Resource;
import com.iflytek.cloud.thirdparty.DialogC3708at;
import java.util.Locale;

/* loaded from: classes3.dex */
public class RecognizerDialog extends DialogC3708at {
    public RecognizerDialog(Context context, InitListener initListener) {
        super(context);
        this.f3083a = new ViewOnClickListenerC3748a(context, initListener);
    }

    public void setListener(RecognizerDialogListener recognizerDialogListener) {
        ((ViewOnClickListenerC3748a) this.f3083a).setResultListener(recognizerDialogListener);
    }

    public void setParameter(String str, String str2) {
        ((ViewOnClickListenerC3748a) this.f3083a).m2297a(str, str2);
    }

    public void setUILanguage(Locale locale) {
        Resource.setUILanguage(locale);
    }

    @Override // com.iflytek.cloud.thirdparty.DialogC3708at, android.app.Dialog
    public void show() {
        super.show();
    }

    @Override // com.iflytek.cloud.thirdparty.DialogC3708at, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
    }
}
