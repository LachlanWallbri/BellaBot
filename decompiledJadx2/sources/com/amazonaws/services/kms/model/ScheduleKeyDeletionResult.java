package com.amazonaws.services.kms.model;

import java.io.Serializable;
import java.util.Date;

/* loaded from: classes.dex */
public class ScheduleKeyDeletionResult implements Serializable {
    private Date deletionDate;
    private String keyId;
    private String keyState;
    private Integer pendingWindowInDays;

    public String getKeyId() {
        return this.keyId;
    }

    public void setKeyId(String str) {
        this.keyId = str;
    }

    public ScheduleKeyDeletionResult withKeyId(String str) {
        this.keyId = str;
        return this;
    }

    public Date getDeletionDate() {
        return this.deletionDate;
    }

    public void setDeletionDate(Date date) {
        this.deletionDate = date;
    }

    public ScheduleKeyDeletionResult withDeletionDate(Date date) {
        this.deletionDate = date;
        return this;
    }

    public String getKeyState() {
        return this.keyState;
    }

    public void setKeyState(String str) {
        this.keyState = str;
    }

    public ScheduleKeyDeletionResult withKeyState(String str) {
        this.keyState = str;
        return this;
    }

    public void setKeyState(KeyState keyState) {
        this.keyState = keyState.toString();
    }

    public ScheduleKeyDeletionResult withKeyState(KeyState keyState) {
        this.keyState = keyState.toString();
        return this;
    }

    public Integer getPendingWindowInDays() {
        return this.pendingWindowInDays;
    }

    public void setPendingWindowInDays(Integer num) {
        this.pendingWindowInDays = num;
    }

    public ScheduleKeyDeletionResult withPendingWindowInDays(Integer num) {
        this.pendingWindowInDays = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getKeyId() != null) {
            sb.append("KeyId: " + getKeyId() + ",");
        }
        if (getDeletionDate() != null) {
            sb.append("DeletionDate: " + getDeletionDate() + ",");
        }
        if (getKeyState() != null) {
            sb.append("KeyState: " + getKeyState() + ",");
        }
        if (getPendingWindowInDays() != null) {
            sb.append("PendingWindowInDays: " + getPendingWindowInDays());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getKeyId() == null ? 0 : getKeyId().hashCode()) + 31) * 31) + (getDeletionDate() == null ? 0 : getDeletionDate().hashCode())) * 31) + (getKeyState() == null ? 0 : getKeyState().hashCode())) * 31) + (getPendingWindowInDays() != null ? getPendingWindowInDays().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ScheduleKeyDeletionResult)) {
            return false;
        }
        ScheduleKeyDeletionResult scheduleKeyDeletionResult = (ScheduleKeyDeletionResult) obj;
        if ((scheduleKeyDeletionResult.getKeyId() == null) ^ (getKeyId() == null)) {
            return false;
        }
        if (scheduleKeyDeletionResult.getKeyId() != null && !scheduleKeyDeletionResult.getKeyId().equals(getKeyId())) {
            return false;
        }
        if ((scheduleKeyDeletionResult.getDeletionDate() == null) ^ (getDeletionDate() == null)) {
            return false;
        }
        if (scheduleKeyDeletionResult.getDeletionDate() != null && !scheduleKeyDeletionResult.getDeletionDate().equals(getDeletionDate())) {
            return false;
        }
        if ((scheduleKeyDeletionResult.getKeyState() == null) ^ (getKeyState() == null)) {
            return false;
        }
        if (scheduleKeyDeletionResult.getKeyState() != null && !scheduleKeyDeletionResult.getKeyState().equals(getKeyState())) {
            return false;
        }
        if ((scheduleKeyDeletionResult.getPendingWindowInDays() == null) ^ (getPendingWindowInDays() == null)) {
            return false;
        }
        return scheduleKeyDeletionResult.getPendingWindowInDays() == null || scheduleKeyDeletionResult.getPendingWindowInDays().equals(getPendingWindowInDays());
    }
}
