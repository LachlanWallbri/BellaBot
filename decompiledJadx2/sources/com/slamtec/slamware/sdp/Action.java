package com.slamtec.slamware.sdp;

import com.slamtec.slamware.action.ActionStatus;
import com.slamtec.slamware.action.IAction;
import com.slamtec.slamware.exceptions.ConnectionFailException;
import com.slamtec.slamware.exceptions.ConnectionTimeOutException;
import com.slamtec.slamware.exceptions.InvalidArgumentException;
import com.slamtec.slamware.exceptions.ParseInvalidException;
import com.slamtec.slamware.exceptions.RequestFailException;
import com.slamtec.slamware.exceptions.UnauthorizedRequestException;
import com.slamtec.slamware.exceptions.UnsupportedCommandException;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
class Action implements IAction {
    protected long _cPointer = 0;

    @Override // com.slamtec.slamware.action.IAction
    public native void cancel() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException;

    @Override // com.slamtec.slamware.action.IAction
    public native int getActionId();

    @Override // com.slamtec.slamware.action.IAction
    public native String getActionName();

    @Override // com.slamtec.slamware.action.IAction
    public native double getProgress();

    @Override // com.slamtec.slamware.action.IAction
    public native String getReason() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    @Override // com.slamtec.slamware.action.IAction
    public native ActionStatus getStatus() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    @Override // com.slamtec.slamware.action.IAction
    public native boolean isEmpty();

    public native void releaseCPointer();

    @Override // com.slamtec.slamware.action.IAction
    public native ActionStatus waitUntilDone() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    static {
        System.loadLibrary("rpsdk");
    }

    protected void finalize() {
        releaseCPointer();
    }
}
