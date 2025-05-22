package com.pudutech.bumblebee.robot_ui.module.check_self;

import android.content.Intent;
import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.Observer;
import com.pudutech.antichannelconflict.escape.util.EscapeStatus;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.module.check_self.SelfCheckActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.LockMachineDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.UIDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.UIDialogKt;
import com.pudutech.bumblebee.robot_ui.util.NavigationBar;
import com.pudutech.bumblebee.robot_ui.viewmodel.LockRobotVM;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LiveData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m3961d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "t", "kotlin.jvm.PlatformType", "onChanged", "(Ljava/lang/Object;)V", "androidx/lifecycle/LiveDataKt$observe$wrappedObserver$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SelfCheckActivity$initObserver$$inlined$observe$1<T> implements Observer<T> {
    final /* synthetic */ SelfCheckActivity this$0;

    public SelfCheckActivity$initObserver$$inlined$observe$1(SelfCheckActivity selfCheckActivity) {
        this.this$0 = selfCheckActivity;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0088, code lost:
    
        r6 = r5.this$0.escapeDialog;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a3, code lost:
    
        r6 = r5.this$0.escapeDialog;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00db, code lost:
    
        r6 = r5.this$0.lockDialog;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00f5, code lost:
    
        r6 = r5.this$0.escapeDialog;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x010e, code lost:
    
        r6 = r5.this$0.lockDialog;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.lifecycle.Observer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void onChanged(T t) {
        String str;
        UIDialog uIDialog;
        LockMachineDialog lockMachineDialog;
        UIDialog uIDialog2;
        LockMachineDialog lockMachineDialog2;
        LockMachineDialog lockMachineDialog3;
        UIDialog uIDialog3;
        UIDialog uIDialog4;
        LockMachineDialog lockMachineDialog4;
        LockMachineDialog lockMachineDialog5;
        LockMachineDialog lockMachineDialog6;
        UIDialog uIDialog5;
        UIDialog uIDialog6;
        UIDialog uIDialog7;
        UIDialog uIDialog8;
        EscapeStatus escapeStatus = (EscapeStatus) t;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "initObserver escapeStatusLD: " + escapeStatus);
        uIDialog = this.this$0.escapeDialog;
        if (uIDialog != null) {
            uIDialog.loadImgShow(false);
        }
        lockMachineDialog = this.this$0.lockDialog;
        if (lockMachineDialog != null) {
            lockMachineDialog.loadImgShow(false);
        }
        int i = SelfCheckActivity.WhenMappings.$EnumSwitchMapping$1[escapeStatus.ordinal()];
        if (i == 1) {
            uIDialog2 = this.this$0.escapeDialog;
            if (uIDialog2 != null && uIDialog2.isShowing() && uIDialog3 != null) {
                uIDialog3.dismiss();
            }
            lockMachineDialog2 = this.this$0.lockDialog;
            if (lockMachineDialog2 != null && lockMachineDialog2.isShowing() && lockMachineDialog3 != null) {
                lockMachineDialog3.dismiss();
            }
            this.this$0.jumpLastResult();
            return;
        }
        if (i != 2) {
            if (i != 3) {
                return;
            }
            uIDialog6 = this.this$0.escapeDialog;
            if (uIDialog6 == null) {
                SelfCheckActivity selfCheckActivity = this.this$0;
                String string = selfCheckActivity.getString(C4188R.string.pdStr5_1);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr5_1)");
                String string2 = this.this$0.getString(C4188R.string.escape_check_map_tip);
                Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.escape_check_map_tip)");
                selfCheckActivity.escapeDialog = UIDialogKt.dialog(selfCheckActivity, string, string2, new Function1<UIDialog, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.check_self.SelfCheckActivity$initObserver$$inlined$observe$1$lambda$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(UIDialog uIDialog9) {
                        invoke2(uIDialog9);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(final UIDialog receiver) {
                        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                        receiver.closeVisible(false);
                        String string3 = SelfCheckActivity$initObserver$$inlined$observe$1.this.this$0.getString(C4188R.string.escape_check_map);
                        Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.escape_check_map)");
                        UIDialog.startButton$default(receiver, false, string3, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.check_self.SelfCheckActivity$initObserver$$inlined$observe$1$lambda$2.1
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
                                UIDialog uIDialog9;
                                LockRobotVM lockRobotVM;
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                uIDialog9 = SelfCheckActivity$initObserver$$inlined$observe$1.this.this$0.escapeDialog;
                                if (uIDialog9 != null) {
                                    uIDialog9.loadImgShow(true);
                                }
                                lockRobotVM = SelfCheckActivity$initObserver$$inlined$observe$1.this.this$0.lockRobotVM;
                                lockRobotVM.checkDetectEscape();
                            }
                        }, 1, null);
                        String string4 = SelfCheckActivity$initObserver$$inlined$observe$1.this.this$0.getString(C4188R.string.pdStr1_31);
                        Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.pdStr1_31)");
                        UIDialog.endButton$default(receiver, false, string4, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.check_self.SelfCheckActivity$initObserver$$inlined$observe$1$lambda$2.2
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
                                String str2;
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                NavigationBar.statusBarDisable(62849024, receiver.getContext());
                                SelfCheckActivity$initObserver$$inlined$observe$1.this.this$0.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
                                str2 = SelfCheckActivity$initObserver$$inlined$observe$1.this.this$0.TAG;
                                Pdlog.m3273d(str2, "notNetworkLiveData() open wifi");
                            }
                        }, 1, null);
                    }
                });
            }
            uIDialog7 = this.this$0.escapeDialog;
            if ((uIDialog7 == null || !uIDialog7.isShowing()) && uIDialog8 != null) {
                uIDialog8.show();
                return;
            }
            return;
        }
        uIDialog4 = this.this$0.escapeDialog;
        if (uIDialog4 != null && uIDialog4.isShowing() && uIDialog5 != null) {
            uIDialog5.dismiss();
        }
        lockMachineDialog4 = this.this$0.lockDialog;
        if (lockMachineDialog4 == null) {
            SelfCheckActivity selfCheckActivity2 = this.this$0;
            final LockMachineDialog lockMachineDialog7 = new LockMachineDialog(selfCheckActivity2);
            lockMachineDialog7.setOnNegativeButtonClicked(new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.check_self.SelfCheckActivity$initObserver$$inlined$observe$1$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
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
                    LockRobotVM lockRobotVM;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    LockMachineDialog.this.loadImgShow(true);
                    lockRobotVM = this.this$0.lockRobotVM;
                    lockRobotVM.checkLockState();
                }
            });
            selfCheckActivity2.lockDialog = lockMachineDialog7;
        }
        lockMachineDialog5 = this.this$0.lockDialog;
        if ((lockMachineDialog5 == null || !lockMachineDialog5.isShowing()) && lockMachineDialog6 != null) {
            lockMachineDialog6.show();
        }
    }
}
