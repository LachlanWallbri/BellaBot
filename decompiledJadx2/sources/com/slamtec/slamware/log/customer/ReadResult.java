package com.slamtec.slamware.log.customer;

import com.slamtec.slamware.log.LogData;
import com.slamtec.slamware.message.SeqNumRange;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class ReadResult {
    ServerStatus serverStatus = new ServerStatus();
    private SeqNumRange seqNumRange = new SeqNumRange();
    private ArrayList<LogData> logs = new ArrayList<>();

    public ServerStatus getServerStatus() {
        return this.serverStatus;
    }

    public void setServerStatus(ServerStatus serverStatus) {
        this.serverStatus = serverStatus;
    }

    public SeqNumRange getSeqNumRange() {
        return this.seqNumRange;
    }

    public void setSeqNumRange(SeqNumRange seqNumRange) {
        this.seqNumRange = seqNumRange;
    }

    public ArrayList<LogData> getLogs() {
        return this.logs;
    }

    public void setLogs(ArrayList<LogData> arrayList) {
        this.logs = arrayList;
    }
}
