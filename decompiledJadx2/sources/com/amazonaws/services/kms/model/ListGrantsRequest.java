package com.amazonaws.services.kms.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* loaded from: classes.dex */
public class ListGrantsRequest extends AmazonWebServiceRequest implements Serializable {
    private String grantId;
    private String granteePrincipal;
    private String keyId;
    private Integer limit;
    private String marker;

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer num) {
        this.limit = num;
    }

    public ListGrantsRequest withLimit(Integer num) {
        this.limit = num;
        return this;
    }

    public String getMarker() {
        return this.marker;
    }

    public void setMarker(String str) {
        this.marker = str;
    }

    public ListGrantsRequest withMarker(String str) {
        this.marker = str;
        return this;
    }

    public String getKeyId() {
        return this.keyId;
    }

    public void setKeyId(String str) {
        this.keyId = str;
    }

    public ListGrantsRequest withKeyId(String str) {
        this.keyId = str;
        return this;
    }

    public String getGrantId() {
        return this.grantId;
    }

    public void setGrantId(String str) {
        this.grantId = str;
    }

    public ListGrantsRequest withGrantId(String str) {
        this.grantId = str;
        return this;
    }

    public String getGranteePrincipal() {
        return this.granteePrincipal;
    }

    public void setGranteePrincipal(String str) {
        this.granteePrincipal = str;
    }

    public ListGrantsRequest withGranteePrincipal(String str) {
        this.granteePrincipal = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getLimit() != null) {
            sb.append("Limit: " + getLimit() + ",");
        }
        if (getMarker() != null) {
            sb.append("Marker: " + getMarker() + ",");
        }
        if (getKeyId() != null) {
            sb.append("KeyId: " + getKeyId() + ",");
        }
        if (getGrantId() != null) {
            sb.append("GrantId: " + getGrantId() + ",");
        }
        if (getGranteePrincipal() != null) {
            sb.append("GranteePrincipal: " + getGranteePrincipal());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getLimit() == null ? 0 : getLimit().hashCode()) + 31) * 31) + (getMarker() == null ? 0 : getMarker().hashCode())) * 31) + (getKeyId() == null ? 0 : getKeyId().hashCode())) * 31) + (getGrantId() == null ? 0 : getGrantId().hashCode())) * 31) + (getGranteePrincipal() != null ? getGranteePrincipal().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListGrantsRequest)) {
            return false;
        }
        ListGrantsRequest listGrantsRequest = (ListGrantsRequest) obj;
        if ((listGrantsRequest.getLimit() == null) ^ (getLimit() == null)) {
            return false;
        }
        if (listGrantsRequest.getLimit() != null && !listGrantsRequest.getLimit().equals(getLimit())) {
            return false;
        }
        if ((listGrantsRequest.getMarker() == null) ^ (getMarker() == null)) {
            return false;
        }
        if (listGrantsRequest.getMarker() != null && !listGrantsRequest.getMarker().equals(getMarker())) {
            return false;
        }
        if ((listGrantsRequest.getKeyId() == null) ^ (getKeyId() == null)) {
            return false;
        }
        if (listGrantsRequest.getKeyId() != null && !listGrantsRequest.getKeyId().equals(getKeyId())) {
            return false;
        }
        if ((listGrantsRequest.getGrantId() == null) ^ (getGrantId() == null)) {
            return false;
        }
        if (listGrantsRequest.getGrantId() != null && !listGrantsRequest.getGrantId().equals(getGrantId())) {
            return false;
        }
        if ((listGrantsRequest.getGranteePrincipal() == null) ^ (getGranteePrincipal() == null)) {
            return false;
        }
        return listGrantsRequest.getGranteePrincipal() == null || listGrantsRequest.getGranteePrincipal().equals(getGranteePrincipal());
    }
}
