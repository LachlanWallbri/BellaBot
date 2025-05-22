package com.slamtec.slamware.action;

import com.slamtec.slamware.exceptions.ConnectionFailException;
import com.slamtec.slamware.exceptions.ConnectionTimeOutException;
import com.slamtec.slamware.exceptions.RequestFailException;
import com.slamtec.slamware.exceptions.UnauthorizedRequestException;
import com.slamtec.slamware.exceptions.UnsupportedCommandException;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public interface ISweepMoveAction extends IMoveAction {
    void pause() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException;

    void resume() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException;
}
