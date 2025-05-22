package com.slamtec.slamware.sdp;

import android.graphics.RectF;
import com.slamtec.slamware.action.ISweepMoveAction;
import com.slamtec.slamware.exceptions.ConnectionFailException;
import com.slamtec.slamware.exceptions.ConnectionTimeOutException;
import com.slamtec.slamware.exceptions.InvalidArgumentException;
import com.slamtec.slamware.exceptions.OperationFailException;
import com.slamtec.slamware.exceptions.ParseInvalidException;
import com.slamtec.slamware.exceptions.RequestFailException;
import com.slamtec.slamware.exceptions.UnauthorizedRequestException;
import com.slamtec.slamware.exceptions.UnsupportedCommandException;
import com.slamtec.slamware.robot.MapType;
import java.util.Vector;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
class SweepMoveAction extends MoveAction implements ISweepMoveAction {
    public native Vector<MapType> getAvailableSweepMaps();

    public native Map getSweepMap(MapType mapType, RectF rectF) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, OperationFailException;

    public native RectF getSweepMapArea(MapType mapType) throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    @Override // com.slamtec.slamware.action.ISweepMoveAction
    public native void pause() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException;

    @Override // com.slamtec.slamware.sdp.MoveAction, com.slamtec.slamware.sdp.Action
    public native void releaseCPointer();

    @Override // com.slamtec.slamware.action.ISweepMoveAction
    public native void resume() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException;

    static {
        System.loadLibrary("rpsdk");
    }

    @Override // com.slamtec.slamware.sdp.MoveAction, com.slamtec.slamware.sdp.Action
    protected void finalize() {
        releaseCPointer();
    }
}
