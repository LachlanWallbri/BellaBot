package com.pudutech.bumblebee.robot_ui.module.escape;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.UIDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.UIDialogKt;
import com.pudutech.bumblebee.robot_ui.util.NavigationBar;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: EscapeObserver.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0003*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, m3961d2 = {"<anonymous>", "", "it", "kotlin.jvm.PlatformType", "onChanged", "(Lkotlin/Unit;)V", "com/pudutech/bumblebee/robot_ui/module/escape/EscapeObserver$observe$1$4"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class EscapeObserver$observe$$inlined$apply$lambda$4<T> implements Observer<Unit> {
    final /* synthetic */ AppCompatActivity $this_apply;
    final /* synthetic */ EscapeObserver this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EscapeObserver$observe$$inlined$apply$lambda$4(AppCompatActivity appCompatActivity, EscapeObserver escapeObserver) {
        this.$this_apply = appCompatActivity;
        this.this$0 = escapeObserver;
    }

    @Override // androidx.lifecycle.Observer
    public final void onChanged(Unit unit) {
        AppCompatActivity appCompatActivity = this.$this_apply;
        AppCompatActivity appCompatActivity2 = appCompatActivity;
        String string = appCompatActivity.getString(C4188R.string.pdStr5_1);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr5_1)");
        String string2 = this.$this_apply.getString(C4188R.string.escape_connect_net_content);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.escape_connect_net_content)");
        UIDialogKt.dialog(appCompatActivity2, string, string2, new Function1<UIDialog, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.escape.EscapeObserver$observe$$inlined$apply$lambda$4.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UIDialog uIDialog) {
                invoke2(uIDialog);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final UIDialog receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                UIDialog.startButton$default(receiver, false, null, null, 6, null);
                String string3 = EscapeObserver$observe$$inlined$apply$lambda$4.this.$this_apply.getString(C4188R.string.pdStr1_31);
                Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.pdStr1_31)");
                UIDialog.endButton$default(receiver, false, string3, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.escape.EscapeObserver$observe$.inlined.apply.lambda.4.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(View view) {
                        invoke2(view);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(View it) {
                        String str;
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        NavigationBar.statusBarDisable(62849024, receiver.getContext());
                        EscapeObserver$observe$$inlined$apply$lambda$4.this.$this_apply.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
                        str = EscapeObserver$observe$$inlined$apply$lambda$4.this.this$0.TAG;
                        Pdlog.m3273d(str, "notNetworkLiveData() open wifi");
                        receiver.dismiss();
                    }
                }, 1, null);
                receiver.show();
            }
        });
    }
}
