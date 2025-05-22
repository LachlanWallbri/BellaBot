package com.pudutech.freeinstall_ui.activity;

import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_ui.manager.AbnormalManager;
import com.pudutech.freeinstall_ui.viewmodel.CreateMapViewModel;
import com.pudutech.mirsdk.aidl.mapify.LoadCoreListener;
import com.pudutech.mirsdk.aidl.serialize.CoreStepType;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitState;
import kotlin.Metadata;

/* compiled from: CreateMapNoticeActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/freeinstall_ui/activity/CreateMapNoticeActivity$addLocateListener$1", "Lcom/pudutech/mirsdk/aidl/mapify/LoadCoreListener$Stub;", "coreInitStepResult", "", "p0", "Lcom/pudutech/mirsdk/aidl/serialize/CoreStepType;", "p1", "Lcom/pudutech/mirsdk/mircore/coreparcel/CoreInitState;", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CreateMapNoticeActivity$addLocateListener$1 extends LoadCoreListener.Stub {
    final /* synthetic */ CreateMapNoticeActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CreateMapNoticeActivity$addLocateListener$1(CreateMapNoticeActivity createMapNoticeActivity) {
        this.this$0 = createMapNoticeActivity;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LoadCoreListener
    public void coreInitStepResult(CoreStepType p0, CoreInitState p1) {
        if (this.this$0.getIsOnResume()) {
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("coreInitStepResult ");
            sb.append(p0 != null ? p0.name() : null);
            sb.append(' ');
            sb.append(p1 != null ? p1.name() : null);
            objArr[0] = sb.toString();
            Pdlog.m3273d(CreateMapNoticeActivity.TAG, objArr);
            if (p0 == CoreStepType.ReinitAlgoModules && p1 == CoreInitState.Success) {
                this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.freeinstall_ui.activity.CreateMapNoticeActivity$addLocateListener$1$coreInitStepResult$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.lang.Runnable
                    public final void run() {
                        boolean z;
                        z = CreateMapNoticeActivity$addLocateListener$1.this.this$0.isFromEdit;
                        if (z) {
                            AbnormalManager.INSTANCE.removeLocateStatusListener();
                        }
                        ((CreateMapViewModel) CreateMapNoticeActivity$addLocateListener$1.this.this$0.getMViewModel()).reInitModules();
                    }
                });
            }
        }
    }
}
