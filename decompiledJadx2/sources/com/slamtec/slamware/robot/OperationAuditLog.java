package com.slamtec.slamware.robot;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class OperationAuditLog {
    private String content;
    private OperationAuditLevel operationAuditLevel;
    private String peerIp;
    private String source;
    private int timestamp;
    private String timestampstr;

    public int getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(int i) {
        this.timestamp = i;
    }

    public String getPeerIp() {
        return this.peerIp;
    }

    public void setPeerIp(String str) {
        this.peerIp = str;
    }

    public OperationAuditLevel getOperationAuditLevel() {
        return this.operationAuditLevel;
    }

    public void setOperationAuditLevel(OperationAuditLevel operationAuditLevel) {
        this.operationAuditLevel = operationAuditLevel;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getTimestampstr() {
        return this.timestampstr;
    }

    public void setTimestampstr(String str) {
        this.timestampstr = str;
    }
}
