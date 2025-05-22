package com.pudutech.mirsdkwrap.lib.interf;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveByGroupStateListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/interf/MoveByGroupStateListener;", "Lcom/pudutech/mirsdkwrap/lib/interf/BaseRobotMoveStateListener;", "onArrive", "", "groupName", "", "onCancel", "onFillIn", "onPause", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface MoveByGroupStateListener extends BaseRobotMoveStateListener {
    void onArrive(String groupName);

    void onCancel();

    void onFillIn();

    void onPause(String groupName);
}
