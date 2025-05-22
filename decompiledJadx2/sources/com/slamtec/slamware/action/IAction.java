package com.slamtec.slamware.action;

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
public interface IAction {
    void cancel() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException;

    int getActionId();

    String getActionName();

    double getProgress();

    String getReason() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    ActionStatus getStatus() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;

    boolean isEmpty();

    ActionStatus waitUntilDone() throws RequestFailException, ConnectionFailException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException;
}
