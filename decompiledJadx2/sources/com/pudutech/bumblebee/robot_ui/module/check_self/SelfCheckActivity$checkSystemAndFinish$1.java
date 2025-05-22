package com.pudutech.bumblebee.robot_ui.module.check_self;

import android.os.Handler;
import android.os.Looper;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.report_cloud.task.ReportUpgrade;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.manager.SystemUpdateManager;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowTipMsgDialog;
import com.pudutech.lib_update.PuduSystemVersionManager;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SelfCheckActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "it", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SelfCheckActivity$checkSystemAndFinish$1 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ SelfCheckActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelfCheckActivity$checkSystemAndFinish$1(SelfCheckActivity selfCheckActivity) {
        super(1);
        this.this$0 = selfCheckActivity;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(final String str) {
        String str2;
        String str3;
        String str4 = str;
        if (str4 == null || StringsKt.isBlank(str4)) {
            SystemUpdateManager.INSTANCE.startCheckFromServer();
            this.this$0.initFinishAndJump();
            return;
        }
        SystemUpdateManager.SystemUpdateCount updateCount = SystemUpdateManager.INSTANCE.getUpdateCount(str);
        if ((BatteryInfoManager.INSTANCE.getPower() >= 50 || BatteryInfoManager.INSTANCE.isCharging()) && updateCount.getCount() < 3) {
            str2 = this.this$0.TAG;
            Pdlog.m3273d(str2, "start system update");
            final Handler handler = new Handler(Looper.getMainLooper());
            final ShowTipMsgDialog showTipMsgDialog = new ShowTipMsgDialog(this.this$0);
            String string = this.this$0.getString(C4188R.string.pdStr1_8);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr1_8)");
            showTipMsgDialog.showTipMsg(string);
            showTipMsgDialog.setCanCancel(false);
            showTipMsgDialog.show();
            PuduSystemVersionManager puduSystemVersionManager = PuduSystemVersionManager.INSTANCE;
            String name = new File(str).getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "File(it).name");
            String versionCodeFormFileName = puduSystemVersionManager.getVersionCodeFormFileName(name);
            if (versionCodeFormFileName != null) {
                ReportUpgrade.INSTANCE.recordSystemUpgrade(this.this$0, versionCodeFormFileName);
            }
            handler.postDelayed(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.module.check_self.SelfCheckActivity$checkSystemAndFinish$1.2
                @Override // java.lang.Runnable
                public final void run() {
                    if (SystemUpdateManager.INSTANCE.startSystemUpdate(SelfCheckActivity$checkSystemAndFinish$1.this.this$0, str)) {
                        handler.postDelayed(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.module.check_self.SelfCheckActivity.checkSystemAndFinish.1.2.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                String str5;
                                str5 = SelfCheckActivity$checkSystemAndFinish$1.this.this$0.TAG;
                                Pdlog.m3274e(str5, "system update failed ?????");
                                showTipMsgDialog.dismiss();
                                SelfCheckActivity$checkSystemAndFinish$1.this.this$0.initFinishAndJump();
                            }
                        }, 5000L);
                    } else {
                        showTipMsgDialog.dismiss();
                        SelfCheckActivity$checkSystemAndFinish$1.this.this$0.initFinishAndJump();
                    }
                }
            }, 2000L);
            return;
        }
        str3 = this.this$0.TAG;
        Pdlog.m3273d(str3, "do not start system update , powerPercent is " + BatteryInfoManager.INSTANCE.getPower() + " , updateCount = " + updateCount);
        if (updateCount.getCount() >= 3) {
            SystemUpdateManager.INSTANCE.requestNewUpdateFileIfNeed(str);
        }
        this.this$0.initFinishAndJump();
    }
}
