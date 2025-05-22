package com.slamtec.slamware.diagnosis;

import com.slamtec.slamware.exceptions.ConnectionFailException;
import com.slamtec.slamware.exceptions.ConnectionLostException;
import com.slamtec.slamware.exceptions.ConnectionTimeOutException;
import com.slamtec.slamware.exceptions.CxxStdException;
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
public interface IMessageLogFetcher {
    long getSnapshotSize(int i) throws RequestFailException, ConnectionFailException, ConnectionLostException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, CxxStdException;

    byte[] readSnapshot(int i, long j, long j2) throws RequestFailException, ConnectionFailException, ConnectionLostException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, CxxStdException;

    void releaseSnapshot(int i) throws RequestFailException, ConnectionFailException, ConnectionLostException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, CxxStdException;

    int updateSnapshot() throws RequestFailException, ConnectionFailException, ConnectionLostException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, CxxStdException;
}
