package com.pudutech.mirsdk.activity;

import android.widget.Button;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.function.C4946R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MirSDKActivity$onCreate$19 extends Lambda implements Function0<Unit> {
    public static final MirSDKActivity$onCreate$19 INSTANCE = new MirSDKActivity$onCreate$19();

    MirSDKActivity$onCreate$19() {
        super(0);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
    }

    /* compiled from: MirSDKActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$19$1 */
    /* loaded from: classes4.dex */
    static final class C48631 extends Lambda implements Function0<Unit> {
        C48631() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            MirSDKActivity$onCreate$19.this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity.onCreate.19.1.1
                @Override // java.lang.Runnable
                public final void run() {
                    Button button_boot_pose_list = (Button) MirSDKActivity$onCreate$19.this.this$0._$_findCachedViewById(C4946R.id.button_boot_pose_list);
                    Intrinsics.checkExpressionValueIsNotNull(button_boot_pose_list, "button_boot_pose_list");
                    button_boot_pose_list.setEnabled(false);
                }
            });
            String valueOf = String.valueOf(System.currentTimeMillis() / 1000);
            SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
            if (sDKInterface != null) {
                sDKInterface.addRelocationPoint(valueOf);
            }
        }
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }
}
