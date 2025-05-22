package com.amazonaws.services.kms.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* loaded from: classes.dex */
public class UpdatePrimaryRegionRequest extends AmazonWebServiceRequest implements Serializable {
    private String keyId;
    private String primaryRegion;

    public String getKeyId() {
        return this.keyId;
    }

    public void setKeyId(String str) {
        this.keyId = str;
    }

    public UpdatePrimaryRegionRequest withKeyId(String str) {
        this.keyId = str;
        return this;
    }

    public String getPrimaryRegion() {
        return this.primaryRegion;
    }

    public void setPrimaryRegion(String str) {
        this.primaryRegion = str;
    }

    public UpdatePrimaryRegionRequest withPrimaryRegion(String str) {
        this.primaryRegion = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getKeyId() != null) {
            sb.append("KeyId: " + getKeyId() + ",");
        }
        if (getPrimaryRegion() != null) {
            sb.append("PrimaryRegion: " + getPrimaryRegion());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getKeyId() == null ? 0 : getKeyId().hashCode()) + 31) * 31) + (getPrimaryRegion() != null ? getPrimaryRegion().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdatePrimaryRegionRequest)) {
            return false;
        }
        UpdatePrimaryRegionRequest updatePrimaryRegionRequest = (UpdatePrimaryRegionRequest) obj;
        if ((updatePrimaryRegionRequest.getKeyId() == null) ^ (getKeyId() == null)) {
            return false;
        }
        if (updatePrimaryRegionRequest.getKeyId() != null && !updatePrimaryRegionRequest.getKeyId().equals(getKeyId())) {
            return false;
        }
        if ((updatePrimaryRegionRequest.getPrimaryRegion() == null) ^ (getPrimaryRegion() == null)) {
            return false;
        }
        return updatePrimaryRegionRequest.getPrimaryRegion() == null || updatePrimaryRegionRequest.getPrimaryRegion().equals(getPrimaryRegion());
    }
}
