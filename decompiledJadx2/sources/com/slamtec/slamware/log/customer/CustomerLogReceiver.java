package com.slamtec.slamware.log.customer;

import com.slamtec.slamware.exceptions.ConnectionFailException;
import com.slamtec.slamware.exceptions.ConnectionLostException;
import com.slamtec.slamware.exceptions.ConnectionTimeOutException;
import com.slamtec.slamware.exceptions.CxxStdException;
import com.slamtec.slamware.exceptions.InvalidArgumentException;
import com.slamtec.slamware.exceptions.ParseInvalidException;
import com.slamtec.slamware.exceptions.RequestFailException;
import com.slamtec.slamware.exceptions.UnauthorizedRequestException;
import com.slamtec.slamware.exceptions.UnsupportedCommandException;
import com.slamtec.slamware.utils.StdPair;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
class CustomerLogReceiver implements ICustomerLogReceiver {
    protected long _cPointer = 0;

    @Override // com.slamtec.slamware.log.customer.ICustomerLogReceiver
    public native CustomerLogReceiverLastRecvStatus getLastRecvStatus();

    @Override // com.slamtec.slamware.log.customer.ICustomerLogReceiver
    public native ResultCode init(CustomerLogReceiverInitArg customerLogReceiverInitArg) throws RequestFailException, ConnectionFailException, ConnectionLostException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, CxxStdException;

    @Override // com.slamtec.slamware.log.customer.ICustomerLogReceiver
    public native boolean isInitialized();

    @Override // com.slamtec.slamware.log.customer.ICustomerLogReceiver
    public native StdPair<ResultCode, ServerStatus> queryServerStatus() throws RequestFailException, ConnectionFailException, ConnectionLostException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, CxxStdException;

    @Override // com.slamtec.slamware.log.customer.ICustomerLogReceiver
    public native StdPair<ResultCode, ReadResult> recvLogs(int i) throws RequestFailException, ConnectionFailException, ConnectionLostException, ConnectionTimeOutException, UnauthorizedRequestException, UnsupportedCommandException, ParseInvalidException, InvalidArgumentException, CxxStdException;

    public native void releaseCPointer();

    @Override // com.slamtec.slamware.log.customer.ICustomerLogReceiver
    public native void resetNextReadStartSeqNum();

    @Override // com.slamtec.slamware.log.customer.ICustomerLogReceiver
    public native void setNextReadStartSeqNum(long j);

    static {
        System.loadLibrary("rpsdk");
    }

    protected void finalize() {
        releaseCPointer();
    }
}
