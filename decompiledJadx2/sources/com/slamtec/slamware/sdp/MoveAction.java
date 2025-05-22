package com.slamtec.slamware.sdp;

import com.slamtec.slamware.action.IMoveAction;
import com.slamtec.slamware.action.Path;
import com.slamtec.slamware.exceptions.ConnectionFailException;
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
class MoveAction extends Action implements IMoveAction {
    @Override // com.slamtec.slamware.action.IMoveAction
    public native Path getRemainingMilestones() throws RequestFailException, ConnectionFailException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    @Override // com.slamtec.slamware.action.IMoveAction
    public native Path getRemainingPath() throws RequestFailException, ConnectionFailException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    @Override // com.slamtec.slamware.sdp.Action
    public native void releaseCPointer();

    static {
        System.loadLibrary("rpsdk");
    }

    @Override // com.slamtec.slamware.sdp.Action
    protected void finalize() {
        releaseCPointer();
    }
}
