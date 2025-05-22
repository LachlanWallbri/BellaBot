package com.pudutech.mirsdk.sdksafe;

import android.content.Intent;
import android.view.View;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: LoginActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class LoginActivity$onCreate$1$onReceivedError$1 implements View.OnClickListener {
    final /* synthetic */ LoginActivity$onCreate$1 this$0;

    LoginActivity$onCreate$1$onReceivedError$1(LoginActivity$onCreate$1 loginActivity$onCreate$1) {
        this.this$0 = loginActivity$onCreate$1;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.this$0.this$0.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
    }
}
