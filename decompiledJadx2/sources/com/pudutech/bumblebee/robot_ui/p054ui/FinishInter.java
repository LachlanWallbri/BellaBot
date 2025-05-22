package com.pudutech.bumblebee.robot_ui.p054ui;

import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FinishInter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/FinishInter;", "", "jumpAndFinish", "", "intent", "Landroid/content/Intent;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public interface FinishInter {

    /* compiled from: FinishInter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static void jumpAndFinish(FinishInter finishInter, Intent intent) {
            Intrinsics.checkParameterIsNotNull(intent, "intent");
        }
    }

    void jumpAndFinish(Intent intent);
}
