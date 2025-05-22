package com.pudutech.mirsdk.hardware.activity;

import android.view.View;
import android.widget.TextView;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.library.C5193R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class HardwareActivity$setupRGBD$3 implements View.OnClickListener {
    final /* synthetic */ HardwareActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HardwareActivity$setupRGBD$3(HardwareActivity hardwareActivity) {
        this.this$0 = hardwareActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.this$0.checkAuth("oilsta", new Function0<Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupRGBD$3.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                HardwareConnection hardwareConnection;
                hardwareConnection = HardwareActivity$setupRGBD$3.this.this$0.hardware;
                HardwareInterface hardwareInterface = hardwareConnection.getInterface();
                final Boolean valueOf = hardwareInterface != null ? Boolean.valueOf(hardwareInterface.oilStainCheck()) : null;
                HardwareActivity$setupRGBD$3.this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity.setupRGBD.3.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        TextView textViewCheckResult = (TextView) HardwareActivity$setupRGBD$3.this.this$0._$_findCachedViewById(C5193R.id.textViewCheckResult);
                        Intrinsics.checkExpressionValueIsNotNull(textViewCheckResult, "textViewCheckResult");
                        textViewCheckResult.setText(Intrinsics.areEqual((Object) valueOf, (Object) true) ? "Clean" : "Dirty");
                    }
                });
            }
        });
    }
}
