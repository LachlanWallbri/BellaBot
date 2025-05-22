package com.amazonaws.services.kms.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class MultiRegionKey implements Serializable {
    private String arn;
    private String region;

    public String getArn() {
        return this.arn;
    }

    public void setArn(String str) {
        this.arn = str;
    }

    public MultiRegionKey withArn(String str) {
        this.arn = str;
        return this;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String str) {
        this.region = str;
    }

    public MultiRegionKey withRegion(String str) {
        this.region = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getArn() != null) {
            sb.append("Arn: " + getArn() + ",");
        }
        if (getRegion() != null) {
            sb.append("Region: " + getRegion());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getArn() == null ? 0 : getArn().hashCode()) + 31) * 31) + (getRegion() != null ? getRegion().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MultiRegionKey)) {
            return false;
        }
        MultiRegionKey multiRegionKey = (MultiRegionKey) obj;
        if ((multiRegionKey.getArn() == null) ^ (getArn() == null)) {
            return false;
        }
        if (multiRegionKey.getArn() != null && !multiRegionKey.getArn().equals(getArn())) {
            return false;
        }
        if ((multiRegionKey.getRegion() == null) ^ (getRegion() == null)) {
            return false;
        }
        return multiRegionKey.getRegion() == null || multiRegionKey.getRegion().equals(getRegion());
    }
}
