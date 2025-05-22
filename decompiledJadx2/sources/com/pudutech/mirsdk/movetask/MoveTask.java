package com.pudutech.mirsdk.movetask;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.base.SDKRobotState;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationMode;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegments;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\u0014\u0010#\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u000e0$H&J\u0018\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H&J\b\u0010,\u001a\u00020-H&J\b\u0010.\u001a\u00020-H\u0016J\b\u0010/\u001a\u00020-H&J\b\u00100\u001a\u00020-H&J\b\u00101\u001a\u00020-H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"¨\u00062"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/MoveTask;", "", "action", "Lcom/pudutech/mirsdk/MoveAction;", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "businessType", "Lcom/pudutech/mirsdk/movetask/BusinessType;", "pathSegments", "Lcom/pudutech/mirsdk/mircore/coreparcel/PathSegments;", "steadyMode", "Lcom/pudutech/mirsdk/aidl/serialize/MoveTaskMode;", "isFillIn", "", "(Lcom/pudutech/mirsdk/MoveAction;Lcom/pudutech/base/architecture/AIDLConnection;Lcom/pudutech/mirsdk/movetask/BusinessType;Lcom/pudutech/mirsdk/mircore/coreparcel/PathSegments;Lcom/pudutech/mirsdk/aidl/serialize/MoveTaskMode;Z)V", "getAction", "()Lcom/pudutech/mirsdk/MoveAction;", "getBusinessType", "()Lcom/pudutech/mirsdk/movetask/BusinessType;", "setBusinessType", "(Lcom/pudutech/mirsdk/movetask/BusinessType;)V", "getCoreService", "()Lcom/pudutech/base/architecture/AIDLConnection;", "()Z", "setFillIn", "(Z)V", "getPathSegments", "()Lcom/pudutech/mirsdk/mircore/coreparcel/PathSegments;", "setPathSegments", "(Lcom/pudutech/mirsdk/mircore/coreparcel/PathSegments;)V", "getSteadyMode", "()Lcom/pudutech/mirsdk/aidl/serialize/MoveTaskMode;", "setSteadyMode", "(Lcom/pudutech/mirsdk/aidl/serialize/MoveTaskMode;)V", "checkNavigationMode", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationMode;", "onStateChange", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "state", "Lcom/pudutech/mirsdk/base/SDKRobotState;", TmpConstant.SERVICE_DESC, "", "pause", "", "reset", "resume", "startMoveAction", "taskStop", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public abstract class MoveTask {
    private final MoveAction action;
    private BusinessType businessType;
    private final AIDLConnection<MirCoreInterface> coreService;
    private boolean isFillIn;
    private PathSegments pathSegments;
    private MoveTaskMode steadyMode;

    public abstract Pair<NavigationMode, Boolean> checkNavigationMode();

    public abstract RobotState onStateChange(SDKRobotState state, String desc);

    public abstract void pause();

    public void reset() {
    }

    public abstract void resume();

    public abstract void startMoveAction();

    public void taskStop() {
    }

    public MoveTask(MoveAction action, AIDLConnection<MirCoreInterface> coreService, BusinessType businessType, PathSegments pathSegments, MoveTaskMode steadyMode, boolean z) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        Intrinsics.checkParameterIsNotNull(coreService, "coreService");
        Intrinsics.checkParameterIsNotNull(businessType, "businessType");
        Intrinsics.checkParameterIsNotNull(steadyMode, "steadyMode");
        this.action = action;
        this.coreService = coreService;
        this.businessType = businessType;
        this.pathSegments = pathSegments;
        this.steadyMode = steadyMode;
        this.isFillIn = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final MoveAction getAction() {
        return this.action;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final AIDLConnection<MirCoreInterface> getCoreService() {
        return this.coreService;
    }

    public /* synthetic */ MoveTask(MoveAction moveAction, AIDLConnection aIDLConnection, BusinessType businessType, PathSegments pathSegments, MoveTaskMode moveTaskMode, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(moveAction, aIDLConnection, (i & 4) != 0 ? BusinessType.Deliver : businessType, (i & 8) != 0 ? (PathSegments) null : pathSegments, (i & 16) != 0 ? MoveTaskMode.Normal : moveTaskMode, (i & 32) != 0 ? false : z);
    }

    public final BusinessType getBusinessType() {
        return this.businessType;
    }

    public final void setBusinessType(BusinessType businessType) {
        Intrinsics.checkParameterIsNotNull(businessType, "<set-?>");
        this.businessType = businessType;
    }

    public final PathSegments getPathSegments() {
        return this.pathSegments;
    }

    public final void setPathSegments(PathSegments pathSegments) {
        this.pathSegments = pathSegments;
    }

    public final MoveTaskMode getSteadyMode() {
        return this.steadyMode;
    }

    public final void setSteadyMode(MoveTaskMode moveTaskMode) {
        Intrinsics.checkParameterIsNotNull(moveTaskMode, "<set-?>");
        this.steadyMode = moveTaskMode;
    }

    /* renamed from: isFillIn, reason: from getter */
    public final boolean getIsFillIn() {
        return this.isFillIn;
    }

    public final void setFillIn(boolean z) {
        this.isFillIn = z;
    }
}
