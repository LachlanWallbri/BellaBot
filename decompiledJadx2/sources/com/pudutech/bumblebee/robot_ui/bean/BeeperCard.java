package com.pudutech.bumblebee.robot_ui.bean;

import java.util.Objects;

/* loaded from: classes2.dex */
public class BeeperCard {
    private String callPointName;
    private String callPointType;

    /* renamed from: id */
    private Long f4840id;
    private String rfid;
    private long timestamp;

    public BeeperCard(Long l, String str, String str2, String str3, long j) {
        this.f4840id = l;
        this.rfid = str;
        this.callPointName = str2;
        this.callPointType = str3;
        this.timestamp = j;
    }

    public BeeperCard() {
    }

    public String toString() {
        return "BeeperCard{id=" + this.f4840id + ", rfid='" + this.rfid + "', callPointName='" + this.callPointName + "', callPointType='" + this.callPointType + "', timestamp=" + this.timestamp + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.rfid, ((BeeperCard) obj).rfid);
    }

    public int hashCode() {
        return Objects.hash(this.rfid);
    }

    public Long getId() {
        return this.f4840id;
    }

    public void setId(Long l) {
        this.f4840id = l;
    }

    public String getRfid() {
        return this.rfid;
    }

    public void setRfid(String str) {
        this.rfid = str;
    }

    public String getCallPointName() {
        return this.callPointName;
    }

    public void setCallPointName(String str) {
        this.callPointName = str;
    }

    public String getCallPointType() {
        return this.callPointType;
    }

    public void setCallPointType(String str) {
        this.callPointType = str;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }
}
